package com.goku.breeze.ui.menu;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;

import com.goku.breeze.common.ListenerExtractSafetyAttr;
import com.goku.breeze.common.SafetyAttribute;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeEvent;
import com.goku.breeze.ui.editor.SafetyEditor;
import com.goku.breeze.util.FTATranslator;
import com.goku.breeze.util.FtaUtil;

public class ActionGenerateFta extends ActionFilesSelection {

	private void dfs(Element ele, Set<String> children, BreezeArch arch) {
		for (String eventId : children) {
			Element child = ele.addElement(FTATranslator.NODE_ARCH_FAILURE);
			BreezeEvent event = arch.getEvent(eventId);
			child.addAttribute(FTATranslator.ATTR_ID, event.getId());
			child.addAttribute(FTATranslator.ATTR_DESC, event.getId());

			children = (Set<String>) event.getProperty(SafetyAttribute.EVENT_CHILDREN);
			if (children != null) {
				if (children.size() > 0) {
					String type = (String) event.getProperty(SafetyAttribute.EVENT_GATE);
					child.addAttribute(FTATranslator.ATTR_TYPE, type == null ? SafetyAttribute.EVENT_GATE_AND : type);
				}
				this.dfs(child, children, arch);
			}
		}
	}

	@Override
	public void run(IAction action) {
		// TODO Auto-generated method stub
		if (this.getAbsolutePath() == null && this.getSelectedFileNames() == null)
			return;

		for (int i = 0; i < this.getSelectedFileNames().length; ++i) {
			if (this.getSelectedFileNames()[i] != null) {
				String fileName = this.getAbsolutePath() + this.getSelectedFileNames()[i];
				if (fileName.endsWith(SafetyEditor.FILE_EXTENSION)) {
					String xml = this.writeFtaXmlFile(fileName);
					FTATranslator ftat = new FTATranslator(xml, fileName, true);
					String fta = ftat.getFTAContent();
					String ped = ftat.getPEDContent();
					try {
						FileOutputStream fos0 = new FileOutputStream(fileName + ".fta");
						FileOutputStream fos1 = new FileOutputStream(fileName + ".ped");
						fos0.write(fta.getBytes());
						fos1.write(ped.getBytes());
						fos0.close();
						fos1.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}

		try {
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// write fta in xml format
	private String writeFtaXmlFile(String inputFilePath) {
		try {
			BreezeXMLParser bxp = new BreezeXMLParser(new File(inputFilePath),
					new ParseListener[] { new ListenerExtractSafetyAttr() }, null);

			BreezeArch arch = bxp.getTopArch();

			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement(FTATranslator.NODE_FAILURE_ANALYSIS);

			root.addAttribute(FTATranslator.ATTR_ID, arch.getName());
			root.addAttribute(FTATranslator.ATTR_DESC, arch.getName());

			Element basicEventListElement = root.addElement(FTATranslator.NODE_NODE_FAILURE_EVENT_LIST);
			basicEventListElement.addAttribute(FTATranslator.ATTR_ID, arch.getName() + "Events");
			basicEventListElement.addAttribute(FTATranslator.ATTR_DESC, arch.getName() + "Events");

			for (BreezeEvent event : arch.getEventList()) {
				Object type = event.getProperty(SafetyAttribute.EVENT_TYPE);
				if (!SafetyAttribute.EVENT_TYPE_COMPOSITE.equals(type)) {
					Element basicEventElement = basicEventListElement.addElement(FTATranslator.NODE_NODE_FAILURE_EVENT);
					basicEventElement.addAttribute(FTATranslator.ATTR_ID, event.getId());
					basicEventElement.addAttribute(FTATranslator.ATTR_DESC, event.getId());
				}
			}

			BreezeEvent maxHeightEvent = FtaUtil.constructFtaTree(arch);

			if (maxHeightEvent != null) {
				Set<String> set = new HashSet<String>();
				set.add(maxHeightEvent.getId());
				this.dfs(root, set, arch);
			}

			FileWriter writer = new FileWriter(new File(inputFilePath + ".fta.xml"));
			XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
			xmlWriter.write(doc);
			xmlWriter.close();
			writer.close();
			return inputFilePath + ".fta.xml";

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
