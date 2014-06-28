package com.goku.breeze.ui.navigator;

import org.eclipse.core.internal.resources.File;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.emf.core.GMFEditingDomainFactory;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.navigator.NavigatorSafeRunnable;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.part.FileEditorInput;

import com.goku.breeze.ui.console.ConsoleFactory;
import com.goku.breeze.ui.console.ConsoleUtil;
import com.goku.breeze.ui.editor.CorrectnessEditor;
import com.goku.breeze.ui.editor.DTMCEditor;
import com.goku.breeze.ui.editor.MKVEditor;
import com.goku.breeze.ui.editor.RBDEditor;
import com.goku.breeze.ui.editor.SMVEditor;
import com.goku.breeze.ui.editor.SafetyEditor;
import com.goku.breeze.ui.editor.XMLEditor;

public class BreezeExplorer extends org.eclipse.ui.navigator.CommonNavigator {
	public BreezeExplorer() {
	}

	@Override
	protected void handleDoubleClick(DoubleClickEvent anEvent) {
		IStructuredSelection selection = (IStructuredSelection) anEvent.getSelection();
		Object obj = selection.getFirstElement();

		if (obj instanceof File) {
			File file = (File) obj;
			IFile ifile = ResourcesPlugin.getWorkspace().getRoot().getFile(file.getFullPath());
			String extension = file.getFileExtension();
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();

			if (SafetyEditor.FILE_EXTENSION.equals(extension)) {
				FileEditorInput fei = new FileEditorInput(ifile);
				IEditorPart editorPart = page.findEditor(fei);
				if (editorPart == null) {
					try {
						page.openEditor(fei, SafetyEditor.ID);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (CorrectnessEditor.FILE_EXTENSION.equals(extension)) {
				FileEditorInput fei = new FileEditorInput(ifile);
				IEditorPart editorPart = page.findEditor(fei);
				if (editorPart == null) {
					try {
						page.openEditor(fei, CorrectnessEditor.ID);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (RBDEditor.FILE_EXTENSION.equals(extension)) {
				FileEditorInput fei = new FileEditorInput(ifile);
				IEditorPart editorPart = page.findEditor(fei);
				if (editorPart == null) {
					try {
						page.openEditor(fei, RBDEditor.ID);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (MKVEditor.FILE_EXTENSION.equals(extension)) {
				FileEditorInput fei = new FileEditorInput(ifile);
				IEditorPart editorPart = page.findEditor(fei);
				if (editorPart == null) {
					try {
						page.openEditor(fei, MKVEditor.ID);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if (DTMCEditor.FILE_EXTENSION.equals(extension)) {
				FileEditorInput fei = new FileEditorInput(ifile);
				IEditorPart editorPart = page.findEditor(fei);
				if (editorPart == null) {
					try {
						page.openEditor(fei, DTMCEditor.ID);
					} catch (PartInitException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else if ("breeze_diagram".equals(extension)) {

				String pathStr = file.getLocation().toString();
				org.eclipse.emf.common.util.URI diagramURI = org.eclipse.emf.common.util.URI.createFileURI(pathStr);
				org.eclipse.emf.common.util.URI modelURI = org.eclipse.emf.common.util.URI.createFileURI(pathStr.substring(0,
						pathStr.length() - "_diagram".length()));

				Resource modelResource = null, diagramResource = null;
				TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();

				try {
					modelResource = editingDomain.getResourceSet().getResource(modelURI, true);
					diagramResource = editingDomain.getResourceSet().getResource(diagramURI, true);
					EObject diagramRoot = modelResource.getContents().get(0);
					int diagramVID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getDiagramVisualID(diagramRoot);
					if (diagramVID != BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID) {
						ConsoleUtil.printError(ConsoleFactory.getConsole(), "Invalid breeze input file\n");
						return;
					}
					page.openEditor(new URIEditorInput(diagramResource.getURI()), BreezeModel.diagram.part.BreezeDiagramEditor.ID);
				} catch (Exception e) {
					e.printStackTrace();
					ConsoleUtil.printError(ConsoleFactory.getConsole(), "Can't load resource of input file\n");
					return;
				}
			} else if ("production".equals(extension)) {
				String pathStr = file.getLocation().toString();
				org.eclipse.emf.common.util.URI diagramURI = org.eclipse.emf.common.util.URI.createFileURI(pathStr);
				org.eclipse.emf.common.util.URI modelURI = org.eclipse.emf.common.util.URI.createFileURI(pathStr.substring(0,
						pathStr.length() - "_production".length()));
				modelURI = modelURI.appendFileExtension("breeze");
				Resource modelResource = null, diagramResource = null;
				TransactionalEditingDomain editingDomain = GMFEditingDomainFactory.INSTANCE.createEditingDomain();

				try {
					modelResource = editingDomain.getResourceSet().getResource(modelURI, true);
					diagramResource = editingDomain.getResourceSet().getResource(diagramURI, true);
					EObject diagramRoot = modelResource.getContents().get(0);
					int diagramVID = BreezeModel.diagram.part.BreezeVisualIDRegistry.getDiagramVisualID(diagramRoot);
					if (diagramVID != BreezeModel.diagram.edit.parts.BreezeEditPart.VISUAL_ID) {
						ConsoleUtil.printError(ConsoleFactory.getConsole(), "Invalid breeze input file\n");
						return;
					}
					page.openEditor(new URIEditorInput(diagramResource.getURI()), BreezeModel.diagram.part.BreezeDiagramEditor.ID);
				} catch (Exception e) {
					e.printStackTrace();
					ConsoleUtil.printError(ConsoleFactory.getConsole(), "Can't load resource of input file\n");
					return;
				}
			} else if (SMVEditor.FILE_EXTENSION.equals(extension)) {
				FileEditorInput fei = new FileEditorInput(ifile);
				try {
					page.openEditor(fei, SMVEditor.ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (XMLEditor.FILE_EXTENSION.equals(extension) || "smvout".equals(extension) || "xml".equals(extension)) {
				FileEditorInput fei = new FileEditorInput(ifile);
				try {
					page.openEditor(fei, XMLEditor.ID);
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				FileEditorInput fei = new FileEditorInput(ifile);
				try {
					page.openEditor(fei, "org.eclipse.ui.DefaultTextEditor");
				} catch (PartInitException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		IAction openHandler = this.getViewSite().getActionBars().getGlobalActionHandler(ICommonActionConstants.OPEN);

		if (openHandler == null) {
			Object element = selection.getFirstElement();

			TreeViewer viewer = this.getCommonViewer();
			if (viewer.isExpandable(element)) {
				viewer.setExpandedState(element, !viewer.getExpandedState(element));
			}
		}
	}

	@Override
	protected void initListeners(TreeViewer viewer) {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(final DoubleClickEvent event) {
				SafeRunner.run(new NavigatorSafeRunnable() {
					@Override
					public void run() throws Exception {
						BreezeExplorer.this.handleDoubleClick(event);
					}
				});
			}
		});
	}
}
