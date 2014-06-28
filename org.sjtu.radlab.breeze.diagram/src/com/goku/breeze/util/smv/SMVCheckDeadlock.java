package com.goku.breeze.util.smv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.goku.breeze.common.ListenerExtractPortName;
import com.goku.breeze.compiler.event.ParseListener;
import com.goku.breeze.compiler.event.TranslationListener;
import com.goku.breeze.compiler.event.impl.ActionAddLTLSPEC;
import com.goku.breeze.compiler.main.BreezeXMLParser;
import com.goku.breeze.compiler.main.MainTranslator;
import com.goku.breeze.compiler.main.textFormatter.SimpleSMVSourceFormatter;
import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsolePrinter;
import com.goku.breeze.ui.console.ConsoleUtil;
import com.goku.breeze.ui.editor.CorrectnessEditor;
import com.goku.breeze.ui.view.ViewNuSMV;

public class SMVCheckDeadlock extends Thread {
	class MessageRunnable implements Runnable {
		private final boolean clearConsole;
		private final String message;

		public MessageRunnable(boolean clearConsole, String msg) {
			this.message = msg;
			this.clearConsole = clearConsole;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (this.clearConsole)
				ConsoleFactory.getConsole().clearConsole();
			ConsoleUtil.printMessage(ConsoleFactory.getConsole(), this.message);
		}

	}

	class ThreadRunSmvCommand extends Thread {
		BreezeArch arch;
		String smvFilePath;

		public ThreadRunSmvCommand(String smvFilePath, BreezeArch arch) {
			this.smvFilePath = smvFilePath;
			this.arch = arch;
		}

		@Override
		public void run() {
			try {
				String err = null, msg = null;
				SMVFactory factory = SMVFactory.getInstance();
				SMV smv = factory.getSMVInstance();

				String[] cmds = new String[] { SmvCommand.READ_MODEL + this.smvFilePath, SmvCommand.FLATTEN_HIERARCHY,
						SmvCommand.ENCODE_VARIABLES, SmvCommand.BUILD_MODEL, SmvCommand.CHECK_LTLSPEC,
						SmvCommand.SHOW_TRACES + this.smvFilePath + "out 1" };

				for (String cmd : cmds) {
					Display.getDefault().asyncExec(new ConsolePrinter(cmd + "\n", ConsolePrinter.TYPE_MESSAGE));
					smv.sendCommand(cmd);

					err = msg = null;
					while (err == null && msg == null) {
						err = smv.popError();
						msg = smv.popMessage();
					}
					if (err != null) {
						System.out.println(err);
						Display.getDefault().asyncExec(new ConsolePrinter(err, ConsolePrinter.TYPE_ERROR));
					}
					if (msg != null)
						System.out.println(msg);
				}

				String correct = "There are no traces currently available";
				msg = (msg == null ? "" : msg);
				err = (err == null ? "" : err);
				if (msg.startsWith(correct) || err.startsWith(correct)) {
					Display.getDefault().asyncExec(
							new MessageRunnable(true, "##############################################\n#"
									+ "         None deadlock is detected          "
									+ "#\n##############################################\n"));
				} else {
					Display.getDefault().asyncExec(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							ConsoleFactory.getConsole().clearConsole();
							ConsoleUtil.printMessage(
									ConsoleFactory.getConsole(),
									"##############################################\n#          The deadlock is detected          #\n##############################################\n");

							IViewReference[] viewRefs = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
									.getViewReferences();
							for (IViewReference viewRef : viewRefs) {
								if (viewRef.getId().equals(ViewNuSMV.ID)) {
									try {
										PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
												.showView(ViewNuSMV.ID);
									} catch (PartInitException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									ViewNuSMV ivp = (ViewNuSMV) viewRef.getView(true);
									ivp.setInput(ThreadRunSmvCommand.this.smvFilePath + "out", ThreadRunSmvCommand.this.arch);
								}
							}
						}

					});
				}

				smv.stopSMV();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private final Map<String, Set<String>> listToAddSpec;
	private final String modelFileName, absPath;

	public SMVCheckDeadlock(String modelFileName, String absPath, Map<String, Set<String>> listToAddSpec) {
		this.modelFileName = modelFileName;
		this.absPath = absPath;
		this.listToAddSpec = listToAddSpec;
	}

	@Override
	public void run() {

		try {
			BreezeXMLParser bxp = new BreezeXMLParser(new File(this.modelFileName),
					new ParseListener[] { new ListenerExtractPortName() }, new TranslationListener[] { new ActionAddLTLSPEC(
							this.listToAddSpec) });
			MainTranslator mt = new MainTranslator(bxp.getTopArch(), new SimpleSMVSourceFormatter());

			String translation = mt.getTranslation();
			String smvFilePath = this.modelFileName.substring(0,
					this.modelFileName.length() - CorrectnessEditor.FILE_EXTENSION.length())
					+ "smv";
			File smvFile = new File(smvFilePath);
			FileOutputStream fos = new FileOutputStream(smvFile);
			fos.write(translation.getBytes());
			fos.close();

			fos = new FileOutputStream(this.absPath + "arch");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bxp.getTopArch());
			oos.close();
			fos.close();

			try {
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, null);
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ThreadRunSmvCommand thread = new ThreadRunSmvCommand(smvFilePath, bxp.getTopArch());
			thread.start();

			Calendar t0 = Calendar.getInstance();

			int count = 0;
			while (thread.isAlive()) {
				Display.getDefault().asyncExec(new MessageRunnable(false, "."));
				if (count++ % 80 == 0) {
					Display.getDefault().asyncExec(new MessageRunnable(false, "\n"));
					count = 0;
				}
				try {
					this.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			thread.join(1000);
			Display.getDefault().asyncExec(new MessageRunnable(false, "check result on NuSMV View"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Display.getDefault().asyncExec(new MessageRunnable(false, "error happen when read temperary smv file\n"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Display.getDefault().asyncExec(new MessageRunnable(false, "error happen when write temperary smv file\n"));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
