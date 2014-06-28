package com.goku.breeze.smv.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InteractiveController {
	private static int cmdGlobalNumber = 0;

	public static void main(String[] args) {
		InteractiveController ic = new InteractiveController();
		List<String> cmdList = new LinkedList<String>();

	}

	private File cmdFile;
	private List<String> cmdList;
	private final int cmdNumber = cmdGlobalNumber++;

	private final Map<String, String> response = new HashMap<String, String>();

	public InteractiveController() {
	}

	public void execute() throws IOException {
		Process proc = Runtime.getRuntime().exec("./resource/NuSMV-linux32 -source ./resource/" + this.cmdNumber + ".cmd");
		InputStream is = proc.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		Iterator<String> it = this.cmdList.iterator();
		String cmd = it.next();
		String cmdPrompt = "NuSMV > ";
		boolean getRlt = false;
		while (line != null) {
			getRlt = false;
			if (line.startsWith(cmdPrompt)) {
				if (line.indexOf(cmd) > 0) {
					getRlt = true;
				}
			}
			if (getRlt) {
				String rlt = "";
				while ((line = br.readLine()) != null) {
					if (line.startsWith(cmdPrompt))
						break;
					rlt += line + "\n";
				}
				this.response.put(cmd, rlt);
				cmd = it.next();
			} else line = br.readLine();
		}
		br.close();
		isr.close();
		is.close();
	}

	public Map<String, String> getResponse() {
		return this.response;
	}

	public void setCmdList(List<String> cmdList) throws IOException {
		if (cmdList == null)
			return;
		this.cmdList = cmdList;
		if (!"quit".equals(cmdList.get(cmdList.size() - 1)))
			cmdList.add("quit");
		this.cmdFile = new File("./resource/" + this.cmdNumber + ".cmd");
		FileOutputStream fos = new FileOutputStream(this.cmdFile);
		for (String cmd : cmdList) {
			String tcmd = cmd + ";\n";
			fos.write(tcmd.getBytes());
		}
		fos.flush();
		fos.close();
	}
}
