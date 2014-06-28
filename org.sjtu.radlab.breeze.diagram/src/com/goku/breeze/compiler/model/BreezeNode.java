package com.goku.breeze.compiler.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.goku.breeze.compiler.event.TranslationEvent;
import com.goku.breeze.compiler.event.TranslationListener;
import com.goku.breeze.compiler.main.textFormatter.BaseSMVSourceFormatter;
import com.goku.breeze.compiler.smv.SMVElement;

public class BreezeNode extends BreezeObject {
	private static final String BASE_EVENT = "BaseEvent";
	private static final String RAND_MODULE_INSTANCE_NAME = "rand";
	private static final String templateDefineEvent = "\tDEFINE ${event} := ${value};\n";
	private static final String templateInit = "\t\tinit(${var}) := ${value};\n";
	private static final String templateNext = "\t\tnext(${var}) := case\n${expr}\t\tesac;\n";
	private static final String templateVar = "\tVAR ${var} : ${value};\n";
	private BreezeArch child, parent;
	private final List<BreezeMode> modeList = new LinkedList<BreezeMode>();
	private final Map<String, BreezeMode> modeMap = new HashMap<String, BreezeMode>();
	private Map<String, BreezePort> portMap = null;
	private String smvInstanceId, smvParameterName;
	private transient final List<TranslationListener> specificationListeners = new ArrayList<TranslationListener>();
	private List<BreezeModeTransition> transition = null;

	public BreezeNode() {
	}

	public BreezeNode(String id, String name, BreezeArch parent) {
		this.setId(id);
		this.setName(name);
		this.parent = parent;
	}

	private Map<String, List<BreezeModeTransition>> addAssignModePart(StringBuilder sb, BreezeMode initState,
			Map<String, List<BreezeEvent>> outerEventMap) {
		Map<String, List<BreezeModeTransition>> outValTrans = new HashMap<String, List<BreezeModeTransition>>();
		if (initState == null) initState = this.modeList.get(0);

		sb.append(templateInit.replaceAll("\\$\\{var\\}", SMVElement.DECL_MODE_NAME).replaceAll("\\$\\{value\\}",
				initState.getSmvName()));

		if (this.transition != null && this.transition.size() >= 0) {
			StringBuffer caseExpr = new StringBuffer();

			for (BreezeModeTransition trans : this.transition) {
				if (trans.getSourceState().getId().equals(trans.getTargetState().getId())) continue;

				caseExpr.append("\t\t\t").append(SMVElement.DECL_MODE_NAME).append(" = ")
						.append(trans.getSourceState().getSmvName());

				if (BreezeModeTransition.TRIGGER_TYPE_RECEIVE.equals(trans.getTriggerType())) {
					Map<String, BreezePort> sourcePorts = trans.getPort().getSource();
					if (sourcePorts != null) {
						for (String sourcePortId : sourcePorts.keySet()) {
							String varId = trans.getPort().getSmvInName(sourcePorts.get(sourcePortId).getId());
							List<BreezeEvent> sourceEvents = outerEventMap.get(varId);
							for (BreezeEvent sourceEvent : sourceEvents) {
								if (trans.getTrigger().getId().equals(sourceEvent.getId())) {
									caseExpr.append(" & ").append(varId).append(" = ").append(trans.getTrigger().getId());
									break;
								}
							}
						}
					}
				} else if (BreezeModeTransition.TRIGGER_TYPE_SEND.equals(trans.getTriggerType())) {
					List<BreezeModeTransition> tList = outValTrans.get(trans.getPort().getId());
					if (tList == null) {
						tList = new LinkedList<BreezeModeTransition>();
						outValTrans.put(trans.getPort().getId(), tList);
					}
					tList.add(trans);
				} else if (BreezeModeTransition.TRIGGER_TYPE_SELF.equals(trans.getTriggerType())) {
					String rndEvtName = BreezeNode.RAND_MODULE_INSTANCE_NAME + trans.getTrigger().getId();
					String hitName = rndEvtName + SMVElement.DOT + BreezeRandModule.OUTPUT_PARA_NAME;
					caseExpr.append(" & ").append(hitName);
				}
				caseExpr.append(" : ").append(trans.getTargetState().getSmvName()).append(";\n");
			}
			caseExpr.append("\t\t\tTRUE : ").append(SMVElement.DECL_MODE_NAME).append(";\n");
			sb.append(templateNext.replaceAll("\\$\\{var\\}", SMVElement.DECL_MODE_NAME).replaceAll("\\$\\{expr\\}",
					caseExpr.toString()));
		}
		return outValTrans;
	}

	private void addAssignPart(StringBuilder sb, BreezeMode initState, Map<String, List<BreezeEvent>> outerEventMap) {
		sb.append("\tASSIGN\n");
		Map<String, List<BreezeModeTransition>> outValTrans = null;
		if (this.modeList.size() > 0) {
			outValTrans = this.addAssignModePart(sb, initState, outerEventMap);
		}

		for (String portKey : this.portMap.keySet()) {
			BreezePort port = this.portMap.get(portKey);

			Map<String, BreezePort> sourcePorts = port.getSource();
			for (String sourcePortId : sourcePorts.keySet()) {
				BreezePort sourcePort = sourcePorts.get(sourcePortId);
				String varId = port.getSmvInName(sourcePort.getId());
				Integer count = (Integer) port.getProperty(varId);
				if (count != null && count > 0) {
					sb.append(templateInit.replaceAll("\\$\\{var\\}", varId).replaceAll("\\$\\{value\\}", BASE_EVENT));
				}
			}

			Integer sizeOfEventSend = (Integer) port.getProperty(port.getSmvOutName());

			if (sizeOfEventSend != null && sizeOfEventSend > 0) {
				sb.append(templateInit.replaceAll("\\$\\{var\\}", port.getSmvOutName()).replaceAll("\\$\\{value\\}", BASE_EVENT));

				StringBuffer value = new StringBuffer();
				List<BreezeModeTransition> tList = outValTrans.get(port.getId());
				if (tList != null) {
					for (BreezeModeTransition trans : tList) {
						value.append("\t\t\t").append(SMVElement.DECL_MODE_NAME).append(" = ")
								.append(trans.getTargetState().getSmvName()).append(" : ").append(trans.getTrigger().getId())
								.append(";\n");
					}
				}
				value.append("\t\t\tTRUE : ").append(BreezeNode.BASE_EVENT).append(";\n");
				sb.append(templateNext.replaceAll("\\$\\{var\\}", port.getSmvOutName()).replaceAll("\\$\\{expr\\}",
						value.toString()));
			}
		}
	}

	private void addDefinePart(StringBuilder sb, Map<String, BreezeEvent> eventMap) {
		sb.append(templateDefineEvent.replaceAll("\\$\\{event\\}", BASE_EVENT).replaceAll("\\$\\{value\\}", "0"));

		if (eventMap.size() > 0) {
			for (String eventId : eventMap.keySet()) {
				Integer eventNumber = eventMap.get(eventId).getNumber();
				sb.append(templateDefineEvent.replaceAll("\\$\\{event\\}", eventId).replaceAll("\\$\\{value\\}",
						eventNumber.toString()));
			}
		}
	}

	private void addRandVar(StringBuilder sb) {
		List<BreezeModeTransition> bmtList = this.getTransition();
		HashSet<String> defineRand = new HashSet<String>();
		for (BreezeModeTransition bmt : bmtList) {
			if (BreezeModeTransition.TRIGGER_TYPE_SELF.equals(bmt.getTriggerType())
					&& !defineRand.contains(bmt.getTrigger().getId())) {
				String rndEvtName = BreezeNode.RAND_MODULE_INSTANCE_NAME + bmt.getTrigger().getId();
				defineRand.add(bmt.getTrigger().getId());

				sb.append(templateVar.replaceAll("\\$\\{var\\}", rndEvtName).replaceAll("\\$\\{value\\}",
						BreezeRandModule.getId() + "(" + bmt.getTrigger().getProbability().toString() + ")"));
			}
		}
	}

	public void addSpecificationListener(TranslationListener listener) {
		this.specificationListeners.add(listener);
	}

	public void addStates(BreezeMode mode) {
		this.modeList.add(mode);
		this.modeMap.put(mode.getId(), mode);
	}

	private BreezeMode addVarModePart(StringBuilder sb) {
		BreezeMode initState = null;
		if (this.modeList.size() > 0) {
			StringBuffer value = new StringBuffer("{");
			for (BreezeMode state : this.modeList) {
				if (state.isInitState()) {
					initState = state;
				}
				value.append(state.getSmvName()).append(',');
			}

			if (value.length() > 1) value.deleteCharAt(value.length() - 1);
			value.append('}');

			sb.append(templateVar.replaceAll("\\$\\{var\\}", SMVElement.DECL_MODE_NAME).replaceAll("\\$\\{value\\}",
					value.toString()));
		}
		return initState;
	}

	private void addVarPortPart(StringBuilder sb, Map<String, List<BreezeEvent>> portSendEventMap,
			Map<String, List<BreezeEvent>> outerEventBuffer) {
		for (String key : this.portMap.keySet()) {
			BreezePort port = this.portMap.get(key);

			List<BreezeEvent> sendLst = portSendEventMap.get(port.getId());

			if (port.isOutPort()) {
				sb.append(templateVar.replaceAll("\\$\\{var\\}", port.getSmvOutName()).replaceAll("\\$\\{value\\}",
						this.extractEventValues(sendLst)));
			}

			if (port.isInPort()) {
				Map<String, BreezePort> sourcePorts = port.getSource();

				for (String srcId : sourcePorts.keySet()) {
					String varId = port.getSmvInName(srcId);
					sb.append(templateVar.replaceAll("\\$\\{var\\}", varId).replaceAll("\\$\\{value\\}",
							this.extractEventValues(outerEventBuffer.get(varId))));
				}
			}
		}
	}

	private String extractEventValues(List<BreezeEvent> eventList) {
		StringBuffer value = new StringBuffer("{ 0");
		HashSet<Integer> eventNumber = new HashSet<Integer>();

		if (eventList != null) {
			for (BreezeEvent evt : eventList) {
				if (eventNumber.contains(evt.getNumber())) continue;
				eventNumber.add(evt.getNumber());
				value.append(", ").append(evt.getNumber().toString());
			}
		}
		value.append(" }");
		return value.toString();
	}

	public BreezeArch getChild() {
		return this.child;
	}

	public String getId() {
		return (String) this.getProperty(BreezeObject.ATTR_ID);
	}

	public BreezeMode getMode(String modeId) {
		return this.modeMap.get(modeId);
	}

	public List<BreezeMode> getModeList() {
		return this.modeList;
	}

	public String getName() {
		return (String) this.getProperty(BreezeObject.ATTR_NAME);
	}

	private Map<String, List<BreezeEvent>> getOuterEvents(Map<String, BreezeEvent> eventMap) {
		Map<String, List<BreezeEvent>> outerEventBuffer = new HashMap<String, List<BreezeEvent>>();
		for (String portId : this.portMap.keySet()) {
			BreezePort port = this.portMap.get(portId);
			if (!port.isInPort()) continue;
			Map<String, BreezePort> sources = port.getSource();

			for (String sourceKey : sources.keySet()) {
				BreezePort bp = sources.get(sourceKey);
				List<BreezeModeTransition> bmbmts = bp.getOwner().getTransition();

				for (BreezeModeTransition bmt : bmbmts) {
					if (BreezeModeTransition.TRIGGER_TYPE_SEND.equals(bmt.getTriggerType())) {
						eventMap.put(bmt.getTrigger().getId(), bmt.getTrigger());

						String inVar = port.getSmvInName(sourceKey);

						List<BreezeEvent> evtList = outerEventBuffer.get(inVar);
						if (evtList == null) {
							evtList = new LinkedList<BreezeEvent>();
							outerEventBuffer.put(inVar, evtList);
						}
						evtList.add(bmt.getTrigger());
						port.setProperty(inVar, evtList.size());
					}
				}
			}
		}
		return outerEventBuffer;
	}

	public BreezeArch getParent() {
		return this.parent;
	}

	public Map<String, BreezePort> getPortMap() {
		return this.portMap;
	}

	public String getSmvInstanceId() {
		return this.smvInstanceId;
	}

	public String getSmvParameterName() {
		return this.smvParameterName;
	}

	public List<BreezeModeTransition> getTransition() {
		return this.transition;
	}

	public void removeMode(String modeId) {
		BreezeMode bm = this.modeMap.remove(modeId);
		this.modeList.remove(bm);
	}

	public void renameMode(String oldName, String newName) {
		BreezeMode bm = this.modeMap.get(oldName);
		if (bm != null) {
			bm.setId(newName);
			this.modeMap.put(newName, bm);
		}
	}

	public void setChild(BreezeArch arch) {
		this.child = arch;
	}

	public void setId(String id) {
		this.setProperty(BreezeObject.ATTR_ID, id);
		this.smvInstanceId = SMVElement.DECL_MODULE_INSTANCE_PREFIX + id;
		this.smvParameterName = this.smvInstanceId;
	}

	public void setName(String name) {
		this.setProperty(BreezeObject.ATTR_NAME, name);
	}

	public void setParent(BreezeArch arch) {
		this.parent = arch;
	}

	public void setPortList(Map<String, BreezePort> lst) {
		this.portMap = lst;
	}

	public void setTransition(List<BreezeModeTransition> lst) {
		this.transition = lst;
	}

	@Override
	public String transalte(BaseSMVSourceFormatter formatter) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("MODULE ").append(this.getId()).append('\n');

		Map<String, List<BreezeEvent>> portSendEventMap = new HashMap<String, List<BreezeEvent>>();
		Map<String, BreezeEvent> eventMap = new HashMap<String, BreezeEvent>();
		for (BreezeModeTransition trans : this.transition) {
			BreezeEvent event = trans.getTrigger();
			eventMap.put(event.getId(), event);

			BreezePort bp = trans.getPort();

			if (!BreezeModeTransition.TRIGGER_TYPE_SEND.equals(trans.getTriggerType())) continue;

			List<BreezeEvent> evtList = portSendEventMap.get(bp.getId());

			if (evtList == null) {
				evtList = new LinkedList<BreezeEvent>();
				portSendEventMap.put(bp.getId(), evtList);
			}
			boolean dup = false;
			for (BreezeEvent evt : evtList)
				if (evt.getId().equals(event.getId())) {
					dup = true;
					break;
				}
			if (!dup) evtList.add(event);
			bp.setProperty(bp.getSmvOutName(), evtList.size());
		}

		Map<String, List<BreezeEvent>> outerEventMap = this.getOuterEvents(eventMap);

		this.addDefinePart(sb, eventMap);

		this.addRandVar(sb);

		this.addVarPortPart(sb, portSendEventMap, outerEventMap);

		BreezeMode initState = this.addVarModePart(sb);

		// value assign
		this.addAssignPart(sb, initState, outerEventMap);
		TranslationEvent translationEvent = new TranslationEvent();
		translationEvent.targetObject = this;
		translationEvent.translationBuffer = sb;
		translationEvent.formatter = formatter;
		for (TranslationListener listener : this.specificationListeners) {
			listener.handleTranslation(translationEvent);
		}

		return sb.toString();
	}
}
