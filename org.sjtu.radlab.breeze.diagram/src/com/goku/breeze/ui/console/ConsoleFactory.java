package com.goku.breeze.ui.console;

import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;

public class ConsoleFactory implements IConsoleFactory {
	private static MessageConsole console = new MessageConsole("Breeze", null);

	public static void closeConsole() {
		IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();
		if (console != null) {
			manager.removeConsoles(new IConsole[] { console });
		}
	}

	public static MessageConsole getConsole() {
		return console;
	}

	@Override
	public void openConsole() {
		// TODO Auto-generated method stub
		if (console != null) {
			IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();
			IConsole[] consoles = manager.getConsoles();
			boolean existing = false;
			for (int i = 0; i < consoles.length; ++i) {
				if (console == consoles[i])
					existing = true;
			}
			if (!existing)
				manager.addConsoles(new IConsole[] { console });
			manager.showConsoleView(console);
		}
	}

}
