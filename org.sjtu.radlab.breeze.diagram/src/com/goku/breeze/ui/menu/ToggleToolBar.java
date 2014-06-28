package com.goku.breeze.ui.menu;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;

public class ToggleToolBar extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		try {
			IHandlerService service = (IHandlerService) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
					.getService(IHandlerService.class);
			if (service != null)
				service.executeCommand("org.eclipse.ui.ToggleCoolbarAction", null);
		} catch (Exception e) {
			//handle error
			e.printStackTrace();
		}
		return null;
	}

}
