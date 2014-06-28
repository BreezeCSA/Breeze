package BreezeModel.diagram.sheet;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;


public abstract class AbstractBreezePropertySection extends AbstractPropertySection {
	 /**
     * The TabbedPropertySheetPage
     */
    protected TabbedPropertySheetPage fPage;
    
    private static int V_SPACING = 10;
    
    /**
     * Set this to true when executing command to stop unnecessary refreshing of controls
     */
    protected boolean fIsExecutingCommand;
    
    
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        fPage = tabbedPropertySheetPage;
        setLayout(parent);
        createControls(parent);
    }
    
    /**
     * Set the layout for the main parent composite
     * @param parent
     */
    protected void setLayout(Composite parent) {
        GridLayout layout = new GridLayout(2, false);
        layout.marginTop = V_SPACING;
        layout.marginHeight = 0;
        layout.marginLeft = 3;
        layout.marginBottom = shouldUseExtraSpace() ? 5 : 0; 
        layout.verticalSpacing = V_SPACING;
        parent.setLayout(layout);
        
        parent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, shouldUseExtraSpace()));
    }
    
    
    
    /**
     * @return The Command Stack in context
     */
    protected CommandStack getCommandStack() {
    	TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(getEObject());
    	return domain.getCommandStack();
    	//EObject object = getEObject();
//    	//EditingDomain ed =
//    	
//    	BreezeDiagramEditor breezeDiagramEditor = (BreezeDiagramEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
//    	BreezeEditPart breezeEditPart = (BreezeEditPart) breezeDiagramEditor.getDiagramEditPart();
//    	return breezeEditPart.getDiagramEditDomain().getDiagramCommandStack();
//    	//TransactionUtil.getDiagramEditDomain(object).getCommandStack();
//        if(object instanceof IAdaptable) {
//            return (CommandStack)((IAdaptable)getEObject()).getAdapter(CommandStack.class);
//        }
//        return null;
    }
    
    /**
     * Create the controls 
     * @param parent
     */
    protected abstract void createControls(Composite parent);
    
    /**
     * @return The ECore Adapter to listen to model changes
     */
    protected abstract Adapter getECoreAdapter();
    
    /**
     * @return The EObject that is the model for this Property Section
     */
    protected abstract EObject getEObject();
    
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        // stop double-firing
        if(selection != getSelection()) { 
            // Remove previous EObject from listener adapter
            if(getEObject() != null && getECoreAdapter() != null) {
                getEObject().eAdapters().remove(getECoreAdapter());
            }
            
            // Set the EObject element
            Object element = ((IStructuredSelection)selection).getFirstElement();
            setElement(element);
            
            // Add ECore listener adapter
            if(getEObject() != null && getECoreAdapter() != null && !getEObject().eAdapters().contains(getECoreAdapter())) {
                getEObject().eAdapters().add(getECoreAdapter());
            }
        }
        
        super.setInput(part, selection);
    }
    
    /**
     * Set the Property sheet to the current element
     * @param element
     */
    protected abstract void setElement(Object element);
    
    /**
     * If the Property sheet was Active (or Pinned) and the Element deleted then the Element's
     * info could still be showing.
     * @return True if alive
     */
    protected boolean isAlive() {
        return (getEObject() != null) || (getEObject().eContainer() != null);
    }

    @Override
    public void dispose() {
        if(getEObject() != null && getECoreAdapter() != null) {
            getEObject().eAdapters().remove(getECoreAdapter());
        }
    }
 
  
    
   
    
    /**
     * @param parent
     * @return A Composite
     */
    protected Composite createComposite(Composite parent, int numColumns) {
        Composite c = new Composite(parent, SWT.NULL);
        getWidgetFactory().adapt(c);
        
        GridLayout layout = new GridLayout(numColumns, false);
        layout.marginHeight = 0;
        layout.marginWidth = 0;
        layout.verticalSpacing = V_SPACING;
        c.setLayout(layout);
        c.setLayoutData(new GridData());
        
        return c;
    }
    
    /**
     * Create Label control. Style is set to SWT.WRAP
     * @param parent Parent composite
     * @param text Text to display
     * @param width Width of label in pixels
     * @param v_position Vertical position. Should be SWT.CENTER or SWT.NONE
     * @return
     */
    protected Label createLabel(Composite parent, String text, int width, int verticalPosition) {
        Label label = getWidgetFactory().createLabel(parent, text, SWT.WRAP);
        GridData gd = new GridData(SWT.NONE, verticalPosition, false, false);
        gd.widthHint = width;
        label.setLayoutData(gd);
        return label;
    }
    
    /**
     * Create Composite control to hold a Table control with UpdatingTableColumnLayout
     * @param parent
     * @param style
     * @return Composite control
     */
    protected Composite createTableComposite(Composite parent, int style) {
        Composite tableComp = new Composite(parent, style);
        
        // This ensures a minumum and equal size and no horizontal size creep
        GridData gd = new GridData(GridData.FILL_BOTH);
        gd.widthHint = 100;
        gd.heightHint = 50;
        tableComp.setLayoutData(gd);

        tableComp.setLayout(new UpdatingTableColumnLayout(tableComp));
        
        return tableComp;
    }
    
    /**
     * TableColumnLayout with public method so we can re-layout when the host table adds/removes vertical scroll bar
     * It's a kludge to stop a bogus horizontal scroll bar being shown.
     */
    static class UpdatingTableColumnLayout extends TableColumnLayout {
        private Composite fParent;

        public UpdatingTableColumnLayout(Composite parent) {
            fParent = parent;
        }

        public void doRelayout() {
            layout(fParent, true);
        }
    }

}
