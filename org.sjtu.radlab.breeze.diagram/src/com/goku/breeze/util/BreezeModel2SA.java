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
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.parser.BreezeModelParser;
import com.goku.breeze.parser.model.BreezeArch;
import com.goku.breeze.parser.model.BreezeEdge;
import com.goku.breeze.parser.model.BreezeNode;
import com.goku.breeze.parser.model.BreezePort;

public class BreezeModel2SA {

	public void convert(String modelFilePath, String saFilePath) throws DocumentException, IOException {
		BreezeModelParser bmp = new BreezeModelParser(modelFilePath);
		BreezeArch rootArch = bmp.getRootComponent();
		Document doc = DocumentHelper.createDocument();
		Element top = doc.addElement("breeze");
		if (rootArch != null) {
			Element root = top.addElement(BreezeObject.TYPE_ARCH);
			this.recurseConvert(root, rootArch);
		}

		FileWriter writer = new FileWriter(new File(saFilePath));
		XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
		xmlWriter.write(doc);
		xmlWriter.close();
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
			Map<String, BreezePort> portMap = bn.getPorts();
			for (String portKey : portMap.keySet()) {
				BreezePort bp = portMap.get(portKey);
				Element portEle = childEle.addElement(BreezeObject.TYPE_PORT);
				portEle.addAttribute(BreezeObject.ATTR_ID, bp.getId());
				portEle.addAttribute(BreezeObject.ATTR_TYPE,
						bp.getDirection() == null ? com.goku.breeze.compiler.model.BreezePort.PORT_IN_OUT : (bp.getDirection()
								.equals("inout") ? com.goku.breeze.compiler.model.BreezePort.PORT_IN_OUT : bp.getDirection()));
				portEle.addAttribute(BreezeObject.ATTR_NAME, bp.getName() == null ? "" : bp.getName());
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
}
