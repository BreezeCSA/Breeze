package com.goku.breeze.ui.console;


public class ConsolePrinter implements Runnable {
	public final static int TYPE_ERROR = 1;
	public final static int TYPE_MESSAGE = 0;
	String p;
	int type;

	public ConsolePrinter(String p, int type) {
		this.p = p;
		this.type = type;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		switch (this.type) {
		case TYPE_MESSAGE:
			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), this.p);
			break;
		case TYPE_ERROR:
			ConsoleUtil.printError(ConsoleFactory.getConsole(), this.p);
			break;
		}
	}

}
