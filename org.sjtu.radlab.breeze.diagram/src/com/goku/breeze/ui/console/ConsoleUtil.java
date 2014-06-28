package com.goku.breeze.ui.console;

import java.io.IOException;

import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class ConsoleUtil {
	public static void printError(IConsole console, String error) {
		if (console instanceof MessageConsole) {
			MessageConsole msgConsole = (MessageConsole) console;
			msgConsole.activate();
			MessageConsoleStream mcs = msgConsole.newMessageStream();
			mcs.print(error);
			try {
				mcs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void printMessage(IConsole console, String message) {
		if (console instanceof MessageConsole) {
			MessageConsole msgConsole = (MessageConsole) console;
			msgConsole.activate();
			MessageConsoleStream mcs = msgConsole.newMessageStream();
			mcs.print(message);
			try {
				mcs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
