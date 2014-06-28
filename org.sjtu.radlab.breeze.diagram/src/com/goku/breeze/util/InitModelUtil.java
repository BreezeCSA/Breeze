package com.goku.breeze.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.eclipse.core.runtime.IPath;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;

public class InitModelUtil {
	private static void changeElementHref(Element element, String oldModelName, String newModelName) {
		List<Element> children = element.elements();
		for (Element child : children) {
			if (child.getName().equals("element")) {
				String href = child.attributeValue("href", "");
				if (href.startsWith(oldModelName)) {
					href = newModelName + href.substring(oldModelName.length());
					child.addAttribute("href", href);
				}
			} else changeElementHref(child, oldModelName, newModelName);
		}
	}

	public static String copy(IPath selectedPath, String folder) {
		String fileName = selectedPath.lastSegment();
		if (selectedPath != null && fileName.endsWith(".breeze")) {
			String modelFilePathString = selectedPath.toString();
			String diagramFilePathString = modelFilePathString + "_diagram";
			String name = fileName.substring(0, fileName.length() - ".breeze".length());
			File modelFile = new File(modelFilePathString), diagramFile = new File(diagramFilePathString);
			if (!modelFile.exists() || !diagramFile.exists()) {
				MessageBox msgDlg = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_INFORMATION | SWT.OK);
				msgDlg.setMessage("Can't find model file or diagram file");
				msgDlg.setText("Initialization:");
				msgDlg.open();
				return null;
			}

			// copy breeze model file and diagram file to safety folder
			String safetyModelFile = selectedPath.removeLastSegments(1).append(folder).append(name + "_" + folder + ".breeze")
					.toString();
			String safetyDiagramFile = safetyModelFile + "_diagram";
			if (!copyFile(modelFilePathString, safetyModelFile) || !copyFile(diagramFilePathString, safetyDiagramFile)) {
				MessageBox msgDlg = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_INFORMATION | SWT.OK);
				msgDlg.setMessage("Can't Copy file");
				msgDlg.setText("Initialization:");
				msgDlg.open();
				return null;
			}

			try {
				transferDiagramFile(safetyDiagramFile, fileName, name + "_" + folder + ".breeze");
			} catch (Exception e) {
				MessageBox msgDlg = new MessageBox(Display.getCurrent().getActiveShell(), SWT.ICON_INFORMATION | SWT.OK);
				msgDlg.setMessage("Diagram file has been corrupted");
				msgDlg.setText("Initialization:");
				msgDlg.open();
				return null;
			}
			return safetyModelFile;
		}
		return null;
	}

	private static boolean copyFile(String initFile, String newFile) {
		try {
			byte[] buf = new byte[1024];
			int len = 0;
			FileInputStream fis = new FileInputStream(initFile);
			FileOutputStream fos = new FileOutputStream(newFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			while ((len = fis.read(buf)) > 0)
				osw.write(new String(buf, 0, len));

			fos.flush();
			osw.close();
			fos.close();
			fis.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private static int dfs(String path) {
		int sum = 0;
		File file = new File(path);
		System.out.println(path);
		String[] children = file.list();

		for (String child : children) {
			File childFile = new File(path + "/" + child);
			if (childFile.isFile()) {
				if (!child.endsWith("java")) continue;
				try {
					FileInputStream fis = new FileInputStream(childFile);
					InputStreamReader isr = new InputStreamReader(fis);
					BufferedReader br = new BufferedReader(isr);
					while (br.readLine() != null)
						sum++;
					br.close();
					isr.close();
					fis.close();
				} catch (Exception e) {
				}
			} else sum += dfs(childFile.getAbsolutePath());
		}

		return sum;
	}

	public static void main(String[] argv) {
		File file = new File("");
		String[] extension = new String[] { "java" };
		System.out.println(dfs("/home/goku/workspace/BreezeV2/org.sjtu.radlab.breeze.diagram/src/com"));
	}

	private static void transferDiagramFile(String diagramFileName, String oldModelName, String newModelName)
			throws DocumentException, IOException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File(diagramFileName));
		Element root = doc.getRootElement();
		root.addAttribute("name", newModelName + "_diagram");
		changeElementHref(root, oldModelName, newModelName);
		FileWriter writer = new FileWriter(diagramFileName);
		XMLWriter xmlWriter = new XMLWriter(writer, OutputFormat.createPrettyPrint());
		xmlWriter.write(doc);
		xmlWriter.close();
		writer.close();
	}
}
