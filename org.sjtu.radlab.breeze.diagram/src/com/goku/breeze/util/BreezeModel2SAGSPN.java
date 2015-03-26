package com.goku.breeze.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.goku.breeze.common.RBDAttribute;
import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.parser.BreezeModelParser;
import com.goku.breeze.parser.model.BreezeArch;
import com.goku.breeze.parser.model.BreezeEdge;
import com.goku.breeze.parser.model.BreezeNode;
import com.goku.breeze.parser.model.BreezePort;

public class BreezeModel2SAGSPN {

	private void branchWriter(Element element, BreezeArch arch, int sum, int rest) {
		int i;
		Element childEle1 = null;
		if (rest == 0)
			return;
		if (rest == 1) {
			Map<String, BreezeNode> children = arch.getNodes();
			i = 0;
			for (String key : children.keySet()) {
				BreezeNode bn = children.get(key);
				if (i + rest == sum) {
					Element childEle = element.addElement("ArchFailure");
					childEle.addAttribute("ArchFailureEventID", bn.getId());
					childEle.addAttribute("ArchFailureEventname", bn.getName());
				}
				i = i + 1;
			}
		}
		if (rest > 1) {
			Map<String, BreezeNode> children = arch.getNodes();
			i = 0;
			for (String key : children.keySet()) {
				BreezeNode bn = children.get(key);
				if (i + rest == sum) {
					childEle1 = element.addElement("FailureEventListofArch");
					childEle1.addAttribute("name", "Branch" + i);
					childEle1.addAttribute("OperationType", "and");
					Element childEle2 = childEle1.addElement("ArchFailure");
					childEle2.addAttribute("ArchFailureEventID", bn.getId());
					childEle2.addAttribute("ArchFailureEventname", bn.getName());
				}
				i = i + 1;
			}
			this.branchWriter(childEle1, arch, sum, rest - 1);
		}

	}

	public void convert(String modelFilePath, String saFilePath) throws DocumentException, IOException {
		BreezeModelParser bmp = new BreezeModelParser(modelFilePath);
		BreezeArch rootArch = bmp.getRootComponent();
		Document doc = DocumentHelper.createDocument();
		Element top = doc.addElement("breeze");
		Element root = top.addElement(BreezeObject.TYPE_ARCH);
		this.recurseConvert(root, rootArch);
		Element root2 = top.addElement(RBDAttribute.TYPE_RBD);
		Element root3 = root2.addElement(RBDAttribute.TYPE_RBDLIST);
		this.recurseConvertRBD(root3, rootArch);
		FileWriter writer = new FileWriter(new File(saFilePath));
		System.out.println("write to " + saFilePath);
		System.out.println(top);
		doc.write(writer);
		writer.close();
	}

	private void recurseConvert(Element element, BreezeArch arch) {
		element.addAttribute(BreezeObject.ATTR_ID, arch.getId());
		element.addAttribute(BreezeObject.ATTR_NAME, arch.getName());
		Map<String, BreezeNode> children = arch.getNodes();
		for (String key : children.keySet()) {
			BreezeNode bn = children.get(key);
			Element childEle = element.addElement(BreezeObject.TYPE_NODE);
			childEle.addAttribute(BreezeObject.ATTR_ID, bn.getId());
			childEle.addAttribute(BreezeObject.ATTR_NAME, bn.getName());
			childEle.addAttribute(BreezeObject.ATTR_PROBABILITY, "1");
			Map<String, BreezePort> portMap = bn.getPorts();
			for (String portKey : portMap.keySet()) {
				BreezePort bp = portMap.get(portKey);
				Element portEle = childEle.addElement(BreezeObject.TYPE_PORT);
				portEle.addAttribute(BreezeObject.ATTR_ID, bp.getId());
				portEle.addAttribute(BreezeObject.ATTR_TYPE,
						bp.getDirection() == null ? com.goku.breeze.compiler.model.BreezePort.PORT_IN_OUT : (bp.getDirection()
								.equals("inout") ? com.goku.breeze.compiler.model.BreezePort.PORT_IN_OUT : bp.getDirection()));
			}
		}

		List<BreezeEdge> edgeList = arch.getEdges();
		for (BreezeEdge edge : edgeList) {
			Element edgeEle = element.addElement(BreezeObject.TYPE_PORT_CONNECTION);
			edgeEle.addAttribute(BreezeObject.ATTR_ID, edge.getId());
			edgeEle.addAttribute(BreezeObject.ATTR_TYPE,
					edge.getDirection() == null ? com.goku.breeze.compiler.model.BreezeConnection.CONNECTION_TYPE_BIDIRECTED
							: edge.getDirection());
			edgeEle.addAttribute(BreezeObject.ATTR_SOURCE, edge.getSource().getContainer().getId());
			edgeEle.addAttribute(BreezeObject.ATTR_SOURCE_PORT, edge.getSource().getId());
			edgeEle.addAttribute(BreezeObject.ATTR_TARGET, edge.getTarget().getContainer().getId());
			edgeEle.addAttribute(BreezeObject.ATTR_TARGET_PORT, edge.getTarget().getId());
		}
	}

	private void recurseConvertRBD(Element element, BreezeArch arch) {
		Map<String, BreezeNode> children = arch.getNodes();
		int sum = 0;
		for (String key2 : children.keySet()) {
			sum++;
		}
		this.branchWriter(element, arch, sum, sum);

	}
}
