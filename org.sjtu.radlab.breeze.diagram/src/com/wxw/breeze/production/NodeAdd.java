package com.wxw.breeze.production;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.core.edithelpers.CreateElementRequestAdapter;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateUnspecifiedTypeRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.emf.type.core.requests.CreateElementRequest;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.ui.PlatformUI;

import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.edit.parts.ComponentEditPart;
import BreezeModel.diagram.edit.parts.LinkEditPart;
import BreezeModel.diagram.edit.parts.PortEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.diagram.part.BreezeDiagramEditorPlugin;

public class NodeAdd {
	public node node_from;
	public void drawLink() {//ComponentEditPart sourceModuleEditPart
		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
		DiagramEditPart diagramEditPart = diagramEditor.getDiagramEditPart();
		GraphicalViewer bgc=diagramEditor.getDiagramGraphicalViewer(); 
		IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
		final List<ComponentEditPart> ooo=viewer.findEditPartsForElement(node_from.id, ComponentEditPart.class);
		ComponentEditPart cep=ooo.get(0); //findEditPartsForElement(null, LinkEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		

        IElementType type = BreezeModel.diagram.providers.BreezeElementTypes.Component_3005; // 连接线类型
        CreateElementRequest createElementRequest = new CreateElementRequest(type);
      //  createElementRequest.setNewElement(cep);

        CreateElementRequestAdapter createElementRequestAdapter = new 
                CreateElementRequestAdapter(createElementRequest);
        CreateViewAndElementRequest.ViewAndElementDescriptor veDescriptor = new 
                CreateViewAndElementRequest.ViewAndElementDescriptor(
                    createElementRequestAdapter, Node.class,
        	    ((IHintedType) type).getSemanticHint(),
        	    diagramEditPart.getDiagramPreferencesHint());
        CreateViewAndElementRequest veRequest = new
                CreateViewAndElementRequest(veDescriptor);

        org.eclipse.gef.commands.Command command = diagramEditPart.getCommand(veRequest);
        command.execute();
        
//        Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
//		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		
//       org.eclipse.gef.commands.Command createConnection
//
//                    = CreateViewAndElementRequest
//
//                                       .getCreateCommand(createComponentRequest);
//		org.eclipse.gef.commands.Command command =  testEditPart.getCommand(createComponentRequest);
//		Object newObject = createComponentRequest.getNewObject();
//		command.execute();
//
//       testEditPart.getDiagramEditDomain()
//
//                .getDiagramCommandStack().execute( command);

 }

//	public void exe()
//	{	
//		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
//		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
//	
//		CreateUnspecifiedTypeRequest request = new
//			    CreateUnspecifiedTypeRequest(
//			        Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005),
//			        testEditPart.getDiagramPreferencesHint());
//
//			Command command = (Command) testEditPart.getCommand(request);
//			Object newObject = request.getNewObject();
//			command.execute();
//
//			System.out.println("dsfas");
//	}
	
	
//	public void exe()
//	{
//		
//		   IElementType type = BreezeModel.diagram.providers.BreezeElementTypes.Component_3005; ; //要创建的图元对象的IElementType
//           CreateViewRequest createViewRequest= CreateViewRequestFactory.getCreateShapeRequest(type, BreezeDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
//
//           org.eclipse.gef.commands.Command createCommand = xxxEditPart.getCommand(createViewRequest); 
//
//           xxxEditPart.getDiagramEditDomain().getDiagramCommandStack().execute(createCommand );
//		
//	}
	
}
