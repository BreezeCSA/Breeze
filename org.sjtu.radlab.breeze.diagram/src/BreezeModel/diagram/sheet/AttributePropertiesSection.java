package BreezeModel.diagram.sheet;

import java.util.Collection;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.command.*;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;

import BreezeModel.Attributes;
import BreezeModel.breezeFactory;
import BreezeModel.breezePackage;


public class AttributePropertiesSection extends AbstractBreezePropertySection {

	/**
	 * Filter to show or reject this section depending on input value
	 */
	public static class Filter implements IFilter {
		@Override
		public boolean select(Object object) {
			if (object instanceof Attributes) {
				return true;
			}
			if (object instanceof IAdaptable) {
				return ((IAdaptable) object).getAdapter(Attributes.class) != null;
			}
			return false;
		}
	}

	/*
	 * Adapter to listen to changes made elsewhere (including Undo/Redo
	 * commands) This one is a EContentAdapter to listen to child IProperty
	 * changes
	 */
	private Adapter eAdapter = new EContentAdapter() {
		private boolean ignoreMessages;
		@Override
		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);
			
			switch (msg.getEventType()) {
			case Notification.ADD:
			case Notification.REMOVE:
			case Notification.SET:
				refreshControls();
				break;
			default:
					break;
			}
			
		}
	};

	private Attributes fPropertiesElement;
	private TableViewer fTableViewer;
	private UpdatingTableColumnLayout fTableLayout;
	private IAction fActionNewProperty, fActionRemoveProperty;

	@Override
	protected void createControls(Composite parent) {
		createTableControl(parent);
	}

	@Override
	protected void setElement(Object element) {
		if (element instanceof Attributes) {
			fPropertiesElement = (Attributes) element;
		} else if (element instanceof IAdaptable) {
			fPropertiesElement = (Attributes) ((IAdaptable) element)
					.getAdapter(Attributes.class);
		} else {
			System.err.println(getClass() + " wants to display for " + element); //$NON-NLS-1$
		}

		refreshControls();
	}

	protected void refreshControls() {
		fTableViewer.setInput(fPropertiesElement);
		fTableLayout.doRelayout();
	}

	@Override
	protected Adapter getECoreAdapter() {
		return eAdapter;
	}

	@Override
	protected EObject getEObject() {
		return fPropertiesElement;
	}


	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	private void createTableControl(Composite parent) {
		// Table Composite
		Composite tableComp = createTableComposite(parent, SWT.NULL);
		fTableLayout = (UpdatingTableColumnLayout) tableComp.getLayout();

		// Table Viewer
		fTableViewer = new TableViewer(tableComp, SWT.MULTI | SWT.BORDER
				| SWT.FULL_SELECTION);

		fTableViewer.getTable().setHeaderVisible(true);
		fTableViewer.getTable().setLinesVisible(true);

		TableViewerColumn columnKey = new TableViewerColumn(fTableViewer,
				SWT.NONE, 0);
		columnKey.getColumn().setText("Attribute");
		fTableLayout.setColumnData(columnKey.getColumn(), new ColumnWeightData(
				40, true));
		columnKey.setEditingSupport(new KeyEditingSupport(fTableViewer));

		TableViewerColumn columnValue = new TableViewerColumn(fTableViewer,
				SWT.NONE, 1);
		columnValue.getColumn().setText("Value");
		fTableLayout.setColumnData(columnValue.getColumn(),
				new ColumnWeightData(60, true));
		columnValue.setEditingSupport(new ValueEditingSupport(fTableViewer));
		
		// Content Provider
		fTableViewer.setContentProvider(new IStructuredContentProvider() {
			public void inputChanged(Viewer viewer, Object oldInput,
					Object newInput) {
			}

			public void dispose() {
			}

			public Object[] getElements(Object inputElement) {
				return ((Attributes) inputElement).getAttribute().toArray();
			}
		});

		// Label Provider
		fTableViewer.setLabelProvider(new LabelCellProvider());

		// Toolbar
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.VERTICAL);
		GridDataFactory.fillDefaults().align(SWT.END, SWT.TOP).applyTo(toolBar);
		ToolBarManager toolBarmanager = new ToolBarManager(toolBar);

		// New Property
		fActionNewProperty = new Action("New") {
			@Override
			public void run() {
				if (isAlive()) {
					BreezeModel.Attribute property = breezeFactory.eINSTANCE
							.createAttribute();
					EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getEObject());
					getCommandStack().execute(AddCommand.create(editingDomain, getEObject(), breezePackage.eINSTANCE.getAttributes_Attribute(), property));				
					fTableViewer.editElement(property, 0);
				}
			}

			

			@Override
			public String getToolTipText() {
				return getText();
			}
		};

		 // Remove Property
        fActionRemoveProperty = new Action("Remove") {
            @Override
            public void run() {
                if(isAlive()) {
                	EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getEObject());
                	Command cmd = new org.eclipse.emf.edit.command.RemoveCommand(editingDomain, getEObject(), breezePackage.eINSTANCE.getAttributes_Attribute(), ((IStructuredSelection)fTableViewer.getSelection()).toList());
                    
                    getCommandStack().execute(cmd);
                }
            }

            @Override
            public String getToolTipText() {
                return getText();
            }
           
        };
        fActionRemoveProperty.setEnabled(false);

	

		toolBarmanager.add(fActionNewProperty);
		toolBarmanager.add(fActionRemoveProperty);
		toolBarmanager.update(true);

		/*
		 * Selection Listener
		 */
		fTableViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						fActionRemoveProperty.setEnabled(!event.getSelection()
								.isEmpty());
					}
				});

		/*
		 * Table Double-click on cell
		 */
		fTableViewer.getTable().addListener(SWT.MouseDoubleClick, new Listener() {
            @Override
            public void handleEvent(Event event) {
                // Get Table item
                TableItem item = fTableViewer.getTable().getItem(new Point(event.x, event.y));
                // Double-click into empty table creates new Property
                if(item == null) {
                    fActionNewProperty.run();                    
                }
                // Handle selected item property double-clicked
                else {
                    if(item.getData() instanceof BreezeModel.Attribute) {
                       // handleDoubleClick((BreezeModel.Attribute)item.getData());
                    }
                }
            }
        });
		
		hookContextMenu();
	}
	/**
     * Key Editor
     */
    private class KeyEditingSupport extends EditingSupport {
    	TextCellEditor cellEditor;

        public KeyEditingSupport(ColumnViewer viewer) {
            super(viewer);
            cellEditor = new TextCellEditor((Composite)viewer.getControl());
            
            // Nullify some global Action Handlers so that this cell editor can handle them
            //hookCellEditorGlobalActionHandler(cellEditor);
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            
            return cellEditor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            return ((BreezeModel.Attribute)element).getKey();
        }

        @Override
        protected void setValue(Object element, Object value) {
            if(isAlive()) {
            	EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getEObject());
                getCommandStack().execute(new org.eclipse.emf.edit.command.SetCommand(editingDomain, (BreezeModel.Attribute)element,
                                            breezePackage.Literals.ATTRIBUTE__KEY, value));
            }
        }
    }

    /**
     * Value Editor
     */
    private class ValueEditingSupport extends EditingSupport {
        TextCellEditor cellEditor;

        public ValueEditingSupport(ColumnViewer viewer) {
            super(viewer);
            cellEditor = new TextCellEditor((Composite)viewer.getControl());
            
            // Nullify some global Action Handlers so that this cell editor can handle them
            //hookCellEditorGlobalActionHandler(cellEditor);
        }

        @Override
        protected CellEditor getCellEditor(Object element) {
            return cellEditor;
        }

        @Override
        protected boolean canEdit(Object element) {
            return true;
        }

        @Override
        protected Object getValue(Object element) {
            return ((BreezeModel.Attribute)element).getValue();
        }

        @Override
        protected void setValue(Object element, Object value) {
            if(isAlive()) {
            	EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(getEObject());
                getCommandStack().execute(new org.eclipse.emf.edit.command.SetCommand(editingDomain, (BreezeModel.Attribute)element,
                                            breezePackage.Literals.ATTRIBUTE__VALUE, value));
            }
        }
    }
    

	/**
	 * Hook into a right-click menu
	 */
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#AttributesPopupMenu"); //$NON-NLS-1$
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(fTableViewer.getControl());
		fTableViewer.getControl().setMenu(menu);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(fActionNewProperty);
		
		manager.add(fActionRemoveProperty);
	}

	// -----------------------------------------------------------------------------------------------------------------
	//
	// Table functions
	//
	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Label Provider
	 */
	private static class LabelCellProvider extends LabelProvider implements
			ITableLabelProvider, ITableColorProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
			case 0:
				String key = ((BreezeModel.Attribute) element).getKey();
				
				return key;

			case 1:
				return ((BreezeModel.Attribute) element).getValue();

			default:
				return null;
			}
		}

		
		@Override
		public Color getBackground(Object element, int columnIndex) {
			return null;
		}

		@Override
		public Color getForeground(Object element, int columnIndex) {
			// TODO Auto-generated method stub
			return null;
		}
	}

}
