package com.goku.breeze.ui.editor;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.StructuredSelection;

import com.goku.breeze.compiler.model.BreezeArch;
import com.goku.breeze.compiler.model.BreezeNode;
import com.goku.breeze.compiler.model.BreezePort;
import com.goku.breeze.util.DiagramNodeSelectionEvent;
import com.goku.breeze.util.DiagramNodeSelectionListener;

public class DiagramEditorSelectChangeListener implements DiagramNodeSelectionListener {
	private final ComboViewer selector;
	private final ModelEditor sme;

	public DiagramEditorSelectChangeListener(ModelEditor sme, ComboViewer componentSelectViewer) {
		// TODO Auto-generated constructor stub
		this.selector = componentSelectViewer;
		this.sme = sme;
	}

	@Override
	public void run(DiagramNodeSelectionEvent selection) {
		// TODO Auto-generated method stub
		if (this.selector != null) {
			Object obj = this.selector.getInput();
			Object[] items = (Object[]) obj;
			for (int i = 0; i < items.length; ++i) {
				if (items[i] instanceof BreezeArch) {
					if (selection.selectionId.equals(((BreezeArch) items[i]).getId())) {
						this.selector.setSelection(new StructuredSelection(items[i]));
						this.sme.updatePanel(items[i]);
					}
				} else if (items[i] instanceof BreezeNode) {
					if (selection.selectionId.equals(((BreezeNode) items[i]).getId())) {
						this.selector.setSelection(new StructuredSelection(items[i]));
						this.sme.updatePanel(items[i]);
					}
				} else if (items[i] instanceof BreezePort) {
					if (selection.selectionId.equals(((BreezePort) items[i]).getId())) {
						this.selector.setSelection(new StructuredSelection(items[i]));
						this.sme.updatePanel(items[i]);
					}
				}
			}
		}
	}

}
