package com.goku.breeze.ui.menu;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;

public class OpenPreference extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		try {
			IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getService(IHandlerService.class);
			if (service != null)
				service.executeCommand("org.eclipse.ui.window.preferences", null);
		} catch (Exception e) {
			//handle error
			ConsoleUtil.printError(ConsoleFactory.getConsole(), "Fail to open preference!\n");
		}
		return null;
	}

}
