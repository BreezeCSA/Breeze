package com.goku.breeze.compiler.main;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;

import com.goku.breeze.compiler.event.ParseEvent;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.event.TranslationListener;
import com.goku.breeze.compiler.exception.AttributeNullException;
import com.goku.breeze.compiler.exception.BaseException;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeConnection;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.compiler.model.BreezeMode;
import com.goku.breeze.compiler.model.BreezeModeTransition;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.compiler.model.BreezePort;
import com.goku.breeze.dom4j.DocumentFactoryWithLocator;
import com.goku.breeze.dom4j.GokuElement;
import com.goku.breeze.dom4j.GokuSAXReader;

public class BreezeXMLParser {
	public class EventNumberFactory {
		private final Map<String, Integer> idMap = new HashMap<String, Integer>();
		private int number = 2;

		public int nextEventNumber(String evtId) {
			Integer id = this.idMap.get(evtId);
			if (id == null) {
				id = ++this.number;
				this.idMap.put(evtId, id);
			}
			return id;
		}

	}

	private BreezeArch breezeTopArch;
	private final EventNumberFactory eventNumberFactory = new EventNumberFactory();
	private final List<BaseException> exceptionList = new LinkedList<BaseException>();
	private final List<ParseListener> listenerList = new LinkedList<ParseListener>();
	private TranslationListener[] specificationListeners = null;

	public BreezeXMLParser(File breezeFile, ParseListener[] listeners, TranslationListener[] speciListeners) {
		Locator locator = new LocatorImpl();
		DocumentFactory docFactory = new DocumentFactoryWithLocator(locator);
		SAXReader reader = new GokuSAXReader(docFactory, locator);
		Document doc = null;
		if (listeners != null) {
			for (ParseListener listener : listeners) {
				this.listenerList.add(listener);
			}
		}
		try {
			doc = reader.read(breezeFile);
		} catch (DocumentException e) {
			this.exceptionList.add(new BaseException("The input file is not a valid XML file", 0, 0));
			e.printStackTrace();
		}
		this.specificationListeners = speciListeners;

		this.parse(doc);
	}

	public void addParseListener(ParseListener listener) {
		this.listenerList.add(listener);
	}

	private boolean assertNotNull(Object value, String str, int lineNum, int colNum) {
		if (value == null) {
			this.exceptionList.add(new AttributeNullException(str, lineNum, colNum));
			return false;
		}
		return true;
	}

	public List<BaseException> getExceptionList() {
		return this.exceptionList;
	}

	public EventNumberFactory getFactory() {
		return this.eventNumberFactory;
	}

	public BreezeArch getTopArch() {
		return this.breezeTopArch;
	}

	private void parse(Document doc) {
		Element root = doc.getRootElement();
		int lineNum = ((GokuElement) root).getLineNumber(), columnNum = ((GokuElement) root).getColumnNumber();
		if (!root.getName().equals(BreezeObject.TYPE_ROOT))
			this.exceptionList.add(new BaseException("Invalid root element of input file", lineNum, columnNum));
		List<Element> archList = root.elements(BreezeObject.TYPE_ARCH);
		for (int i = 0; i < archList.size(); ++i) {
			if (i == 0) {
				this.breezeTopArch = this.parseArch(archList.get(i), true);
			} else {
				GokuElement gkElement = (GokuElement) archList.get(i);
				this.exceptionList.add(new BaseException("more than one arch under root element", gkElement.getLineNumber(),
						gkElement.getColumnNumber()));
			}
		}
	}

	@SuppressWarnings("unchecked")
	private BreezeArch parseArch(Element archElement, boolean isMainArch) {
		String archId = archElement.attributeValue(BreezeObject.ATTR_ID);
		int lineNum = ((GokuElement) archElement).getLineNumber(), columnNum = ((GokuElement) archElement).getColumnNumber();
		BreezeArch arch = new BreezeArch(archId, archElement.attributeValue(BreezeObject.ATTR_NAME), isMainArch);

		if (archId == null) {
			this.exceptionList.add(new BaseException(BreezeObject.ATTR_ID, lineNum, columnNum));
			archId = BreezeObject.TYPE_ARCH + arch.toString().split("@")[1];
			arch.setId(archId);
		}

		/*
		 * run parse arch listener
		 */
		ParseEvent event = new ParseEvent();
		event.container = arch;
		event.parseElement = archElement;
		for (ParseListener listener : this.listenerList) {
			listener.handleParsing(event);
		}

		// get events
		List<Element> eventElements = archElement.elements(BreezeObject.TYPE_EVENT);

		for (Element eventElement : eventElements) {
			String id = eventElement.attributeValue(BreezeObject.ATTR_ID);
			if (id == null) continue;
			Integer eventNumber = this.eventNumberFactory.nextEventNumber(id);
			BreezeEvent e = new BreezeEvent(id, eventNumber, eventElement.attributeValue(BreezeObject.ATTR_PROBABILITY, "100"));

			ParseEvent eventEvent = new ParseEvent();
			eventEvent.container = e;
			eventEvent.parseElement = eventElement;
			for (ParseListener listener : this.listenerList) {
				listener.handleParsing(eventEvent);
			}

			arch.addEvent(e);
		}

		List<Element> nList = archElement.elements(BreezeObject.TYPE_NODE);
		Map<String, BreezeNode> nodeMap = new HashMap<String, BreezeNode>();
		for (Element node : nList) {
			BreezeNode breezeNode = this.parseNode(node, arch);
			nodeMap.put(breezeNode.getId(), breezeNode);
		}
		arch.setNodeMap(nodeMap);

		BreezeConnection graph = new BreezeConnection("Graph_" + archId);
		List<Element> connectionElementList = archElement.elements(BreezeObject.TYPE_PORT_CONNECTION);
		for (Element connectionElement : connectionElementList) {
			String sourceId = connectionElement.attributeValue(BreezeObject.ATTR_SOURCE);
			String targetId = connectionElement.attributeValue(BreezeObject.ATTR_TARGET);

			int conLineNum = ((GokuElement) connectionElement).getLineNumber(), conColNum = ((GokuElement) connectionElement)
					.getColumnNumber();

			if (sourceId == null || targetId == null) {
				this.exceptionList.add(new BaseException("Edge should have source and target", conLineNum, conColNum));
				continue;
			}

			BreezeNode srcn = nodeMap.get(sourceId);
			BreezeNode tgtn = nodeMap.get(targetId);
			String attrSrcPort = connectionElement.attributeValue(BreezeObject.ATTR_SOURCE_PORT);
			String attrTgtPort = connectionElement.attributeValue(BreezeObject.ATTR_TARGET_PORT);

			if (srcn == null || tgtn == null) {
				this.exceptionList.add(new BaseException("Can't find corresponding source node or target node", conLineNum,
						conColNum));
				continue;
			}

			BreezePort srcp = srcn.getPortMap().get(attrSrcPort);
			BreezePort tgtp = tgtn.getPortMap().get(attrTgtPort);

			if (srcp == null || tgtp == null) {
				this.exceptionList.add(new BaseException("Can't find corresponding source port or target port", conLineNum,
						conColNum));
				continue;
			}

			String connectionType = connectionElement.attributeValue(BreezeObject.ATTR_TYPE);
			boolean connectError = false;
			if (BreezeConnection.CONNECTION_TYPE_BIDIRECTED.equals(connectionType)) {
				if (srcp.isInPort() && srcp.isOutPort() && tgtp.isInPort() && tgtp.isOutPort()) {
					graph.addConnection(srcn, srcp, tgtn, tgtp);
					graph.addConnection(tgtn, tgtp, srcn, srcp);
					srcp.addTarget(tgtp);
					srcp.addSource(tgtp);
					tgtp.addSource(srcp);
					tgtp.addTarget(srcp);
				} else {
					connectError = true;
				}
			} else if (BreezeConnection.CONNECTION_TYPE_DIRECTED.equals(connectionType)) {
				if (srcp.isOutPort() && tgtp.isInPort()) {
					graph.addConnection(srcn, srcp, tgtn, tgtp);
					srcp.addTarget(tgtp);
					tgtp.addSource(srcp);
				} else {
					connectError = true;
				}
			} else {
				this.exceptionList.add(new BaseException("Invalid type of connection, connection should be "
						+ BreezeConnection.CONNECTION_TYPE_BIDIRECTED + " or " + BreezeConnection.CONNECTION_TYPE_DIRECTED,
						conLineNum, conColNum));
			}

			if (connectError) {
				this.exceptionList.add(new BaseException("Type of ports connected are not compatible", conLineNum, conColNum));
			}
		}
		arch.setConnectionGraph(graph);
		return arch;
	}

	@SuppressWarnings("unchecked")
	private BreezeNode parseNode(Element nodeElement, BreezeArch parent) {
		String nodeId = nodeElement.attributeValue(BreezeObject.ATTR_ID);
		BreezeNode node = new BreezeNode(nodeId, nodeElement.attributeValue(BreezeObject.ATTR_NAME), parent);
		int lineNum = ((GokuElement) nodeElement).getLineNumber(), colNum = ((GokuElement) nodeElement).getColumnNumber();

		if (nodeId == null) {
			this.exceptionList.add(new BaseException(BreezeObject.ATTR_ID, lineNum, colNum));
			nodeId = BreezeObject.TYPE_NODE + node.toString().split("@")[1];
			node.setId(nodeId);
		}

		/*
		 * run parse node listener
		 */
		ParseEvent nodeEvent = new ParseEvent();
		nodeEvent.container = node;
		nodeEvent.parseElement = nodeElement;
		for (ParseListener listener : this.listenerList) {
			listener.handleParsing(nodeEvent);
		}

		/*
		 * add translation listener
		 */
		if (this.specificationListeners != null) {
			for (TranslationListener listener : this.specificationListeners)
				node.addSpecificationListener(listener);
		}

		// gain all states of current node
		List<Element> stateElements = nodeElement.elements(BreezeObject.TYPE_ATTRIBUTE);
		for (Element stateElement : stateElements) {
			String modeId = stateElement.attributeValue(BreezeObject.ATTR_ID);
			String modeType = stateElement.attributeValue(BreezeObject.ATTR_VALUE);
			String modeKey = stateElement.attributeValue(BreezeObject.ATTR_KEY);
			boolean isMode = BreezeMode.BREEZE_MODE_NAME.equals(modeKey);
			int stLineNum = ((GokuElement) stateElement).getLineNumber(), stColNum = ((GokuElement) stateElement)
					.getColumnNumber();

			if (isMode) {
				if (!this.assertNotNull(modeId, BreezeObject.ATTR_ID, stLineNum, stColNum)
						|| !this.assertNotNull(modeType, BreezeObject.ATTR_VALUE, stLineNum, stColNum)
						|| !this.assertNotNull(modeKey, BreezeObject.ATTR_KEY, stLineNum, stColNum)) continue;
				BreezeMode bm = new BreezeMode(modeId, modeType, true);
				node.addStates(bm);

				/*
				 * run listener
				 */
				ParseEvent modeEvent = new ParseEvent();
				modeEvent.container = bm;
				modeEvent.parseElement = stateElement;
				for (ParseListener listener : this.listenerList) {
					listener.handleParsing(modeEvent);
				}
			}
		}

		List<BreezeModeTransition> transitionList0 = new ArrayList<BreezeModeTransition>();

		// self generated event
		Element saElement = nodeElement.element(BreezeObject.TYPE_SAFATY_SPECIFICATION);
		if (saElement != null) {
			List<Element> transElementList = saElement.elements(BreezeObject.TYPE_TRANSITION);
			if (transElementList != null && transElementList.size() > 0) {
				for (Element transElement : transElementList) {
					this.parseSafetyTransition(transElement, transitionList0, null, node, parent);
				}
			}
		}

		// parse port of current node and state transition
		List<Element> portElementList = nodeElement.elements(BreezeObject.TYPE_PORT);
		Map<String, BreezePort> portMap = new HashMap<String, BreezePort>();

		// get transition of mode
		for (Element portElement : portElementList) {
			String portId = portElement.attributeValue(BreezeObject.ATTR_ID);
			String portType = portElement.attributeValue(BreezeObject.ATTR_TYPE);
			String portName = portElement.attributeValue(BreezeObject.ATTR_NAME);
			BreezePort port = new BreezePort(portId, portName, portType, node);

			int portLine = ((GokuElement) portElement).getLineNumber(), portCol = ((GokuElement) portElement).getColumnNumber();
			if (!this.assertNotNull(portId, BreezeObject.ATTR_ID, portLine, portCol)) {
				portId = BreezeObject.TYPE_PORT + port.toString().split("@")[0];
				port.setId(portId);
			}

			if (!this.assertNotNull(portType, BreezeObject.ATTR_TYPE, portLine, portCol)) {
				portType = BreezePort.PORT_IN_OUT;
			} else if (!BreezePort.PORT_IN.equals(portType) && !BreezePort.PORT_OUT.equals(portType)
					&& !BreezePort.PORT_IN_OUT.equals(portType)) {
				portType = BreezePort.PORT_IN_OUT;
				this.exceptionList.add(new BaseException("Invalid type of port", portLine, portCol));
			}

			/*
			 * run listener
			 */
			ParseEvent portEvent = new ParseEvent();
			portEvent.container = port;
			portEvent.parseElement = portElement;
			for (ParseListener listener : this.listenerList) {
				listener.handleParsing(portEvent);
			}

			portMap.put(portId, port);

			Element safetyElement = portElement.element(BreezeObject.TYPE_SAFATY_SPECIFICATION);
			if (safetyElement == null) continue;

			List<Element> transitionElementList = safetyElement.elements(BreezeObject.TYPE_TRANSITION);
			for (Element transitionElement : transitionElementList) {
				this.parseSafetyTransition(transitionElement, transitionList0, port, node, parent);
			}
		}

		// process empty source mode
		List<BreezeModeTransition> tmpTrans = new ArrayList<BreezeModeTransition>();

		for (BreezeModeTransition trans : transitionList0) {
			if (trans.getSourceState() == null) {
				for (BreezeMode bm : node.getModeList()) {
					if (!bm.getId().equals(trans.getTargetState().getId())) {
						tmpTrans.add(new BreezeModeTransition(trans.getPort(), bm, trans.getTargetState(), trans.getTrigger(),
								trans.getTriggerType()));
					}
				}
			} else tmpTrans.add(trans);
		}

		for (BreezeModeTransition bmt : tmpTrans) {
			BreezeMode bm0 = bmt.getSourceState(), bm1 = bmt.getTargetState();
			BreezeEvent event = bmt.getTrigger();
		}
		node.setPortList(portMap);
		node.setTransition(tmpTrans);

		// parse arch in node
		Element archElement = nodeElement.element(BreezeObject.TYPE_ARCH);
		if (archElement != null) {
			node.setChild(this.parseArch(archElement, false));
		}
		return node;
	}

	private void parseSafetyTransition(Element transitionElement, List<BreezeModeTransition> transitionList, BreezePort port,
			BreezeNode node, BreezeArch arch) {
		int tranLine = ((GokuElement) transitionElement).getLineNumber(), tranCol = ((GokuElement) transitionElement)
				.getColumnNumber();

		/*
		 * find source mode and target mode
		 */
		BreezeMode srcMode = null, tgtMode = null;

		List<Element> modeElementList = transitionElement.elements(BreezeObject.TYPE_MODE);

		for (Element modeElement : modeElementList) {
			String modeType = modeElement.attributeValue(BreezeObject.ATTR_NAME);
			String modeId = modeElement.attributeValue(BreezeObject.ATTR_REF);
			if (modeId != null && modeType != null) {
				if (BreezeModeTransition.TRANSITION_SRC.equals(modeType))
					srcMode = node.getMode(modeId);
				else if (BreezeModeTransition.TRANSITION_TGT.equals(modeType)) tgtMode = node.getMode(modeId);
			}
		}

		/*
		 * find trigger events
		 */
		Element tgrElement = transitionElement.element(BreezeObject.TYPE_TRIGGER);
		String type = tgrElement.attributeValue(BreezeObject.ATTR_TYPE, BreezeModeTransition.TRIGGER_TYPE_SELF);
		if (tgrElement != null) {
			List<Element> evtElementList = tgrElement.elements(BreezeObject.TYPE_EVENT);
			List<BreezeEvent> evtList = new ArrayList<BreezeEvent>();

			/*
			 * if trigger events more than one, then create internal media state
			 */
			if (evtElementList != null && evtElementList.size() > 0) {
				for (Element evtElement : evtElementList) {
					String evtId = evtElement.attributeValue(BreezeObject.ATTR_REF);
					BreezeEvent tgr = arch.getEvent(evtId);
					if (evtId != null && tgr != null) {
						evtList.add(tgr);

						ParseEvent evtEvent = new ParseEvent();
						evtEvent.container = tgr;
						evtEvent.parseElement = evtElement;
						for (ParseListener listener : this.listenerList)
							listener.handleParsing(evtEvent);
					} else {
						this.assertNotNull(evtId, BreezeObject.ATTR_ID, ((GokuElement) evtElement).getLineNumber(),
								((GokuElement) evtElement).getColumnNumber());
					}
				}

				BreezeMode nextMode = srcMode;
				if (tgtMode == null) {
					this.exceptionList.add(new BaseException("target mode of transition is empty", tranLine, tranCol));
				}
				String srcName = srcMode != null ? srcMode.getId() : null, tgtName = tgtMode.getId();
				String interStateType = srcMode != null ? srcMode.getType() : BreezeMode.TYPE_NORMAL;
				for (int i = 0; i < evtList.size() - 1; ++i) {
					BreezeMode interMode = new BreezeMode(tgtMode.generateInternalStateName(srcName, tgtName), interStateType,
							false);
					transitionList.add(new BreezeModeTransition(port, nextMode, interMode, evtList.get(i), type));
					nextMode = interMode;
					node.addStates(nextMode);
				}
				if (evtList.size() > 0)
					transitionList.add(new BreezeModeTransition(port, nextMode, tgtMode, evtList.get(evtList.size() - 1), type));
			}
		}
	}
}
