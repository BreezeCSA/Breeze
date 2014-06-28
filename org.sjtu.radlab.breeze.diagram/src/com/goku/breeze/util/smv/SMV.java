package com.goku.breeze.util.smv;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;

public class SMV {
	private class ThreadGetSMVOutput extends Thread {
		private final String endMarker;
		private InputStream is = null;
		private boolean shouldExit = false;

		public ThreadGetSMVOutput(InputStream is, String endMarker) {
			this.is = is;
			this.endMarker = endMarker;
		}

		@Override
		public void run() {
			byte[] buf = new byte[1024];
			int len = 0;
			StringBuffer sb = new StringBuffer();
			System.out.println("start");
			while (!this.shouldExit) {
				try {
					if (this.is.available() > 0) {
						if (this.endMarker != null) {
							while ((len = this.is.read(buf)) > 0) {
								String content = new String(buf, 0, len);
								sb.append(content);

								if (sb.indexOf(this.endMarker) >= 0) {
									SMV.this.pushMessage(sb.toString());
									sb.delete(0, sb.length());
									break;
								}
							}
						} else {
							len = this.is.read(buf);
							if (len > 0)
								SMV.this.pushError(new String(buf, 0, len));
						}
					} else {
						this.sleep(200);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				this.is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void setShouldExit(boolean flag) {
			this.shouldExit = flag;
		}
	}

	private final static String CMD_SHOW_END = ";echo CMD_DONE;\n";
	private final static String END_MARKER = "CMD_DONE";
	private final LinkedList<String> errList = new LinkedList<String>();
	private final LinkedList<String> msgList = new LinkedList<String>();
	private Process process = null;
	private final InputStream stdin, stderr;
	private final OutputStream stdout;
	private final ThreadGetSMVOutput threadOut, threadErr;

	public SMV(String smvProg) throws IOException {
		Runtime rt = Runtime.getRuntime();
		this.process = rt.exec(smvProg);
		this.stdin = this.process.getInputStream();
		this.stderr = this.process.getErrorStream();
		this.stdout = this.process.getOutputStream();
		this.threadOut = new ThreadGetSMVOutput(this.stdin, END_MARKER);
		this.threadErr = new ThreadGetSMVOutput(this.stderr, null);
		this.threadOut.start();
		this.threadErr.start();
	}

	//
	//	public void buildModel() {
	//		this.sendCommand("build_model" + CMD_SHOW_END);
	//	}
	//
	//	public void checkFsm() {
	//		this.sendCommand("check_fsm" + CMD_SHOW_END);
	//	}
	//
	//	public void encodeVariables() {
	//		this.sendCommand("encode_variables" + CMD_SHOW_END);
	//	}
	//
	//	public void flattenHierarchy() {
	//		this.sendCommand("flatten_hierarchy" + CMD_SHOW_END);
	//	}

	public synchronized String popError() {
		if (this.errList.size() > 0)
			return this.errList.removeFirst();
		else return null;
	}

	public synchronized String popMessage() {
		if (this.msgList.size() > 0)
			return this.msgList.removeFirst();
		else return null;
	}

	public synchronized void pushError(String msg) {
		this.errList.add(msg);
	}

	public synchronized void pushMessage(String msg) {
		this.msgList.add(msg);
	}

	//	public void readModel(String modelFilePath) {
	//		this.sendCommand("read_model -i " + modelFilePath + CMD_SHOW_END);
	//	}
	//
	//	public void reset() {
	//		this.sendCommand("reset" + CMD_SHOW_END);
	//	}

	public void sendCommand(String command) {
		command += CMD_SHOW_END;
		try {
			if (command.charAt(command.length() - 1) != '\n')
				command = command + "\n";
			this.stdout.write(command.getBytes());
			this.stdout.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void stopSMV() {
		this.sendCommand("quit;");
		this.threadErr.setShouldExit(true);
		this.threadOut.setShouldExit(true);
		try {
			this.stdout.close();
			this.process.destroy();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
