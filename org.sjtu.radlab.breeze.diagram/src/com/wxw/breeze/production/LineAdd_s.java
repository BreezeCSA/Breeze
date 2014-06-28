package com.wxw.breeze.production;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramGraphicalViewer;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateConnectionViewAndElementRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequest;
import org.eclipse.gmf.runtime.diagram.ui.requests.CreateViewRequestFactory;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.ui.PlatformUI;

import BreezeModel.diagram.edit.commands.LinkCreateCommand;
import BreezeModel.diagram.edit.parts.BreezeEditPart;
import BreezeModel.diagram.edit.parts.LinkEditPart;
import BreezeModel.diagram.edit.parts.PortEditPart;
import BreezeModel.diagram.part.BreezeDiagramEditor;
import BreezeModel.diagram.part.BreezeDiagramEditorPlugin;
import BreezeModel.impl.BreezeImpl;
import BreezeModel.impl.LinkImpl;
import BreezeModel.impl.PortImpl;
import BreezeModel.impl.breezeFactoryImpl;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;

public class LineAdd_s {
	public port source_id,target_id;
	public architecture arc; 


	public LineAdd_s(port source_id, port target_id, architecture arc) {
		super();
		this.source_id = source_id;
		this.target_id = target_id;
		this.arc = arc;
		
		
		
		
	}


	public void drawLink(PortEditPart sourceModuleEditPart, PortEditPart targetModuleEditPart) {

        IElementType type = BreezeModel.diagram.providers.BreezeElementTypes.Link_4002 ; // 连接线类型

       CreateConnectionViewAndElementRequest createConnectionRequest 

                    = new CreateConnectionViewAndElementRequest(

                                         type, ((IHintedType) type).getSemanticHint(),

                                         PreferencesHint.USE_DEFAULTS);
       Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		
       org.eclipse.gef.commands.Command createConnection

                    = CreateConnectionViewAndElementRequest

                                       .getCreateCommand(createConnectionRequest,

                                      sourceModuleEditPart, targetModuleEditPart);

       testEditPart.getDiagramEditDomain()

                .getDiagramCommandStack().execute(createConnection);

 }



	public void exe()
	{
		
		
		
		Object o=PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();		
		BreezeEditPart  testEditPart =(BreezeEditPart) ((BreezeDiagramEditor) o).getDiagramEditPart();			
		//TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(testEditPart.getEditingDomain());
	//	final View testView = (View) testEditPart.getModel();// 某个节点的view
		BreezeDiagramEditor diagramEditor = (BreezeDiagramEditor) o;
		GraphicalViewer bgc=diagramEditor.getDiagramGraphicalViewer(); 
		
		IDiagramGraphicalViewer viewer = diagramEditor.getDiagramGraphicalViewer();
	//	Object obb=viewer.getEditPartRegistry();
		
		//final List<LinkEditPart> ooo
		HashMap map=(HashMap) viewer.getEditPartRegistry();//findEditPartsForElement(null, LinkEditPart.class);//e.g., get with viewer.findEditPartsForElement() or viewer.getEditPartRegistry()		
		Iterator iter = map.entrySet().iterator();
		Object key,val = null;
		while (iter.hasNext()) { 
		    Map.Entry entry = (Map.Entry) iter.next(); 
		    key = entry.getKey(); 
		    val = entry.getValue(); 
		    if(val instanceof LinkEditPart)
		    	break;
		} 
		LinkEditPart lep=new LinkEditPart((View) ((LinkEditPart)val).getModel());
		
	//	final View l_view=(View)lep.getModel();
		//final BreezeImpl breezeImpl = (BreezeImpl) testView.getElement(); // 某个节点的model 
	//	final LinkImpl l_impl=(LinkImpl) l_view.getElement(); 
		List<PortEditPart> ooo=viewer.findEditPartsForElement(source_id.id, PortEditPart.class);
		//PortImpl s_impl=(PortImpl)((View)ooo.get(0).getModel()).getElement();
		List<PortEditPart> ooo1=viewer.findEditPartsForElement(target_id.id, PortEditPart.class);
	//	PortImpl t_impl=(PortImpl)((View)ooo1.get(0).getModel()).getElement(); 
//		breezeFactoryImpl bfi=new breezeFactoryImpl();
//		final LinkImpl oobb=(LinkImpl) bfi.createLink();
//		oobb.setSource(s_impl);
//		oobb.setTarget(t_impl); 
		
//		  IElementType type = BreezeModel.diagram.providers.BreezeElementTypes.Link_4002 ; //要创建的图元对象的IElementType
//          CreateViewRequest createViewRequest= CreateViewRequestFactory.getCreateShapeRequest(type, BreezeDiagramEditorPlugin.DIAGRAM_PREFERENCES_HINT);
//          org.eclipse.gef.commands.Command createCommand =   (bgc.getFocusEditPart()).getCommand(createViewRequest); 
//          diagramEditor.getDiagramEditDomain().getDiagramCommandStack().execute(createCommand ); 
         
          
      
          
          

          drawLink(ooo.get(0),ooo1.get(0));
          


	
	}
}
