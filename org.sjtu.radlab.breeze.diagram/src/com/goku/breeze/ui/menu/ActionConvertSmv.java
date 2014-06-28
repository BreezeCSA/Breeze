package com.goku.breeze.ui.menu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.IAction;

import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezePort;

public class ActionConvertSmv extends ActionFilesSelection {

	public ActionConvertSmv() {
		// TODO Auto-generated constructor stub
	}

	private String replace(String str, String rep, String nstr) {
		if (nstr == null)
			return str;
		while (str.indexOf(rep) >= 0)
			str = str.replace(rep, nstr);
		return str;
	}

	@Override
	public void run(IAction action) {
		if (this.getAbsolutePath() == null || this.getSelectedFileNames() == null)
			return;
		File archFile = new File(this.getAbsolutePath() + "arch");
		if (!archFile.exists())
			return;
		try {
			FileInputStream fis = new FileInputStream(archFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			BreezeArch arch = (BreezeArch) ois.readObject();
			ois.close();
			fis.close();

			fis = new FileInputStream(this.getAbsolutePath() + this.getSelectedFileNames()[0]);
			byte[] buf = new byte[1024];
			StringBuilder sb = new StringBuilder();
			int len = 0;
			while ((len = fis.read(buf)) > 0)
				sb.append(new String(buf, 0, len));
			fis.close();

			String str = sb.toString();

			str = this.replace(str, arch.getId(), arch.getName());

			Map<String, BreezeNode> nodeMap = arch.getNodeList();
			for (String key : nodeMap.keySet()) {
				BreezeNode bn = nodeMap.get(key);
				str = this.replace(str, bn.getId(), bn.getName());

				Map<String, BreezePort> portMap = bn.getPortMap();
				for (String portId : portMap.keySet()) {
					BreezePort bp = portMap.get(portId);
					str = this.replace(str, bp.getId(), bp.getName());
				}
			}

			FileOutputStream fos = new FileOutputStream(this.getAbsolutePath() + "read" + this.getSelectedFileNames()[0]);
			fos.write(str.getBytes());
			fos.close();
			ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
