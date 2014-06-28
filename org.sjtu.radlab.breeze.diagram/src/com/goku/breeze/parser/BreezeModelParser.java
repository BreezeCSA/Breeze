package com.goku.breeze.parser;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.goku.breeze.parser.model.BreezeArch;
import com.goku.breeze.parser.model.BreezeEdge;
import com.goku.breeze.parser.model.BreezeNode;
import com.goku.breeze.parser.model.BreezeObject;
import com.goku.breeze.parser.model.BreezePort;

public class BreezeModelParser {
	private final Map<String, BreezeObject> allComponents = new HashMap<String, BreezeObject>();
	private BreezeArch rootComponent = null;

	public BreezeModelParser(String file) throws DocumentException, UnsupportedEncodingException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(file));
		Element root = doc.getRootElement().element(BreezeObject.BREEZE_ARCH);
		if (root == null)
			return;
		this.rootComponent = new BreezeArch(root.attributeValue(BreezeObject.ATTR_ID),
				root.attributeValue(BreezeObject.ATTR_NAME));
		this.parse(this.rootComponent, root, this.allComponents);
		this.allComponents.put(this.rootComponent.getId(), this.rootComponent);
	}

	public Map<String, BreezeObject> getAllComponents() {
		return this.allComponents;
	}

	public BreezeArch getRootComponent() {
		return this.rootComponent;
	}

	@SuppressWarnings("unchecked")
	private void parse(BreezeArch father, Element fatherNode, Map<String, BreezeObject> allCom) {
		List<Element> children = null;
		Map<String, BreezeNode> nameObjectMap = new HashMap<String, BreezeNode>();
		Map<String, BreezePort> allPorts = new HashMap<String, BreezePort>();
		children = fatherNode.elements(BreezeObject.BREEZE_NODE);
		if (children == null || children.size() == 0)
			return;

		// container
		for (Element child : children) {
			String name = child.attributeValue(BreezeObject.ATTR_NAME);
			String id = child.attributeValue(BreezeObject.ATTR_ID);
			BreezeNode bn = new BreezeNode(id, name, child.attributeValue(BreezeObject.ATTR_TYPE));
			if (id == null)
				continue;
			nameObjectMap.put(id, bn);
			allCom.put(id, bn);

			// port list, map port id to corresponding component
			List<Element> portEleList = child.elements(BreezeObject.BREEZE_PORT);
			Map<String, BreezePort> portMap = new HashMap<String, BreezePort>();
			for (Element portEle : portEleList) {
				String portId = portEle.attributeValue(BreezeObject.ATTR_ID);
				String portName = portEle.attributeValue(BreezeObject.ATTR_NAME);
				String direction = portEle.attributeValue(BreezeObject.ATTR_DIRECTION);
				if (portId == null)
					continue;
				BreezePort bp = new BreezePort(portId, portName, direction);
				bp.setContainer(bn);
				allPorts.put(portId, bp);
				portMap.put(portId, bp);
			}
			bn.setPorts(portMap);
		}

		// connections
		List<Element> links = fatherNode.elements(BreezeObject.BREEZE_LINK);
		List<BreezeEdge> edgeList = new ArrayList<BreezeEdge>();

		for (Element link : links) {
			String srcPortId = link.attributeValue(BreezeObject.ATTR_SOURCE);
			String tgtPortId = link.attributeValue(BreezeObject.ATTR_TARGET);
			String type = link.attributeValue(BreezeObject.ATTR_TYPE);
			BreezePort src = null, tgt = null;
			if (srcPortId != null && tgtPortId != null) {
				src = allPorts.get(srcPortId);
				tgt = allPorts.get(tgtPortId);
			}
			BreezeEdge edge = new BreezeEdge(link.attributeValue(BreezeObject.ATTR_ID),
					link.attributeValue(BreezeObject.ATTR_NAME));
			edge.setSource(src);
			edge.setTarget(tgt);
			edge.setDirection(type);
			edgeList.add(edge);
		}
		father.setEdges(edgeList);
		father.setNodes(nameObjectMap);
	}
}
