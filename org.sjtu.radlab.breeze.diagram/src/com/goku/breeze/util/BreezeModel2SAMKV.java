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

import com.goku.breeze.compiler.model.BreezeObject;
import com.goku.breeze.parser.BreezeModelParser;
import com.goku.breeze.parser.model.BreezeArch;
import com.goku.breeze.parser.model.BreezeEdge;
import com.goku.breeze.parser.model.BreezeNode;
import com.goku.breeze.parser.model.BreezePort;

public class BreezeModel2SAMKV {

	public void convert(String modelFilePath, String saFilePath) throws DocumentException, IOException {
		BreezeModelParser bmp = new BreezeModelParser(modelFilePath);
		BreezeArch rootArch = bmp.getRootComponent();
		Document doc = DocumentHelper.createDocument();
		Element top = doc.addElement("breeze");
		Element root = top.addElement(BreezeObject.TYPE_ARCH);
		this.recurseConvert(root, rootArch);
		FileWriter writer = new FileWriter(new File(saFilePath));
		System.out.println("write to " + saFilePath);
		System.out.println(top);
		doc.write(writer);
		writer.close();
	}

	private void recurseConvert(Element element, BreezeArch arch) {
		int count=0;
		element.addAttribute(BreezeObject.ATTR_ID, arch.getId());
		element.addAttribute(BreezeObject.ATTR_NAME, arch.getName());
		Map<String, BreezeNode> children = arch.getNodes();
		
		Element childEle = element.addElement(BreezeObject.TYPE_NODE);
		childEle.addAttribute(BreezeObject.ATTR_ID, "nodeStartID");
		childEle.addAttribute(BreezeObject.ATTR_NAME, "start");
		childEle.addAttribute("probability", "1");
		childEle.addAttribute("isVirtual", "1");
		
		childEle = element.addElement(BreezeObject.TYPE_NODE);
		childEle.addAttribute(BreezeObject.ATTR_ID, "nodeCorrectID");
		childEle.addAttribute(BreezeObject.ATTR_NAME, "correct");
		childEle.addAttribute("probability", "1");
		childEle.addAttribute("isVirtual", "2");childEle = element.addElement(BreezeObject.TYPE_NODE);
		childEle.addAttribute(BreezeObject.ATTR_ID, "nodeFaultID");
		childEle.addAttribute(BreezeObject.ATTR_NAME, "fault");
		childEle.addAttribute("probability", "1");
		childEle.addAttribute("isVirtual", "3");
		
		for (String key : children.keySet()) {
			BreezeNode bn = children.get(key);
			childEle = element.addElement(BreezeObject.TYPE_NODE);
			childEle.addAttribute(BreezeObject.ATTR_ID, bn.getId());
			childEle.addAttribute(BreezeObject.ATTR_NAME, bn.getName());
			childEle.addAttribute("probability", "1");
			childEle.addAttribute("isVirtual", "0");
//			childEle.addAttribute("selfLoopProbability", "1");
			Map<String, BreezePort> portMap = bn.getPorts();
			for (String portKey : portMap.keySet()) {
				BreezePort bp = portMap.get(portKey);
				Element portEle = childEle.addElement(BreezeObject.TYPE_PORT);
				portEle.addAttribute(BreezeObject.ATTR_ID, bp.getId());
				portEle.addAttribute(BreezeObject.ATTR_TYPE,
						bp.getDirection() == null ? com.goku.breeze.compiler.model.BreezePort.PORT_IN_OUT : (bp.getDirection()
								.equals("inout") ? com.goku.breeze.compiler.model.BreezePort.PORT_IN_OUT : bp.getDirection()));
			}
				Element edgeEle = element.addElement(BreezeObject.TYPE_PORT_CONNECTION);
				edgeEle.addAttribute(BreezeObject.ATTR_ID, (bn.getId()+"ToStart"));
				edgeEle.addAttribute(BreezeObject.ATTR_TYPE,	"bidirected");
				edgeEle.addAttribute("source", "nodeStartID");
				edgeEle.addAttribute("sourceName", "start");
				edgeEle.addAttribute(BreezeObject.ATTR_SOURCE_PORT, bn.getId()+"SourcePort");
				edgeEle.addAttribute("target", bn.getId());
				edgeEle.addAttribute("targetName", bn.getName());
				edgeEle.addAttribute(BreezeObject.ATTR_TARGET_PORT,bn.getId()+"TargetPort");
				if (count==0)
					edgeEle.addAttribute("probability", "1");
				else
					edgeEle.addAttribute("probability", "0");


				edgeEle = element.addElement(BreezeObject.TYPE_PORT_CONNECTION);
				edgeEle.addAttribute(BreezeObject.ATTR_ID, (bn.getId()+"ToCorrect"));
				edgeEle.addAttribute(BreezeObject.ATTR_TYPE,	"bidirected");
				edgeEle.addAttribute("source", bn.getId());
				edgeEle.addAttribute("sourceName", bn.getName());
				edgeEle.addAttribute(BreezeObject.ATTR_SOURCE_PORT, bn.getId()+"TargetPort");
				edgeEle.addAttribute("target", "nodeCorrectID");
				edgeEle.addAttribute("targetName", "correct");
				edgeEle.addAttribute(BreezeObject.ATTR_TARGET_PORT,bn.getId()+"SourcePort");
				edgeEle.addAttribute("probability", "1");

				edgeEle = element.addElement(BreezeObject.TYPE_PORT_CONNECTION);
				edgeEle.addAttribute(BreezeObject.ATTR_ID, (bn.getId()+"ToFault"));
				edgeEle.addAttribute(BreezeObject.ATTR_TYPE,	"bidirected");
				edgeEle.addAttribute("source", bn.getId());
				edgeEle.addAttribute("sourceName", bn.getName());
				edgeEle.addAttribute(BreezeObject.ATTR_SOURCE_PORT, bn.getId()+"TargetPort");
				edgeEle.addAttribute("target", "nodeFaultID");
				edgeEle.addAttribute("targetName", "fault");
				edgeEle.addAttribute(BreezeObject.ATTR_TARGET_PORT,bn.getId()+"SourcePort");
				edgeEle.addAttribute("probability", "0");
			
				
			count=1;
		}
		

		List<BreezeEdge> edgeList = arch.getEdges();
		for (BreezeEdge edge : edgeList) {
			Element edgeEle = element.addElement(BreezeObject.TYPE_PORT_CONNECTION);
			edgeEle.addAttribute(BreezeObject.ATTR_ID, edge.getId()+"Forward");
			edgeEle.addAttribute(BreezeObject.ATTR_TYPE,
					edge.getDirection() == null ? com.goku.breeze.compiler.model.BreezeConnection.CONNECTION_TYPE_BIDIRECTED
							: edge.getDirection());
			edgeEle.addAttribute("source", edge.getSource().getContainer().getId());
			edgeEle.addAttribute("sourceName", edge.getSource().getContainer().getName());
			edgeEle.addAttribute(BreezeObject.ATTR_SOURCE_PORT, edge.getSource().getId());
			edgeEle.addAttribute("target", edge.getTarget().getContainer().getId());
			edgeEle.addAttribute("targetName", edge.getTarget().getContainer().getName());
			edgeEle.addAttribute(BreezeObject.ATTR_TARGET_PORT, edge.getTarget().getId());
			edgeEle.addAttribute("probability", "0");			
		}
		edgeList = arch.getEdges();
		for (BreezeEdge edge : edgeList) {
			Element edgeEle = element.addElement(BreezeObject.TYPE_PORT_CONNECTION);
			edgeEle.addAttribute(BreezeObject.ATTR_ID, edge.getId()+"Backward");
			edgeEle.addAttribute(BreezeObject.ATTR_TYPE,
					edge.getDirection() == null ? com.goku.breeze.compiler.model.BreezeConnection.CONNECTION_TYPE_BIDIRECTED
							: edge.getDirection());
			edgeEle.addAttribute("source", edge.getTarget().getContainer().getId());
			edgeEle.addAttribute("sourceName", edge.getTarget().getContainer().getName());
			edgeEle.addAttribute(BreezeObject.ATTR_SOURCE_PORT, edge.getTarget().getId());
			edgeEle.addAttribute("target", edge.getSource().getContainer().getId());
			edgeEle.addAttribute("targetName", edge.getSource().getContainer().getName());
			edgeEle.addAttribute(BreezeObject.ATTR_TARGET_PORT, edge.getSource().getId());
			edgeEle.addAttribute("probability", "0");			
		}
	}
}
