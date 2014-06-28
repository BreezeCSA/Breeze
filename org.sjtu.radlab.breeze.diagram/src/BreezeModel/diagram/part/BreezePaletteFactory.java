package BreezeModel.diagram.part;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.Tool;
import org.eclipse.gef.palette.PaletteContainer;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.ToolEntry;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeConnectionTool;
import org.eclipse.gmf.runtime.diagram.ui.tools.UnspecifiedTypeCreationTool;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;

/**
 * @generated
 */
public class BreezePaletteFactory {

	/**
	 * @generated
	 */
	private static class LinkToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> relationshipTypes;

		/**
		 * @generated
		 */
		private LinkToolEntry(String title, String description, List<IElementType> relationshipTypes) {
			super(title, description, null, null);
			this.relationshipTypes = relationshipTypes;
		}

		/**
		 * @generated
		 */
		@Override
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeConnectionTool(this.relationshipTypes);
			tool.setProperties(this.getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private static class NodeToolEntry extends ToolEntry {

		/**
		 * @generated
		 */
		private final List<IElementType> elementTypes;

		/**
		 * @generated
		 */
		private NodeToolEntry(String title, String description, List<IElementType> elementTypes) {
			super(title, description, null, null);
			this.elementTypes = elementTypes;
		}

		/**
		 * @generated
		 */
		@Override
		public Tool createTool() {
			Tool tool = new UnspecifiedTypeCreationTool(this.elementTypes);
			tool.setProperties(this.getToolProperties());
			return tool;
		}
	}

	/**
	 * @generated
	 */
	private ToolEntry createArchitecture1CreationTool() {
		ArrayList<IElementType> types = new ArrayList<IElementType>(3);
		types.add(BreezeModel.diagram.providers.BreezeElementTypes.Arch_2001);
		types.add(BreezeModel.diagram.providers.BreezeElementTypes.Arch_3012);
		types.add(BreezeModel.diagram.providers.BreezeElementTypes.Arch_3013);
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.Architecture1CreationTool_title,
				BreezeModel.diagram.part.Messages.Architecture1CreationTool_desc, types);
		entry.setId("createArchitecture1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Arch_2001));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * Creates "Architecture Element" palette tool group
	 * 
	 * @generated
	 */
	public PaletteContainer createArchitectureElement2Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(BreezeModel.diagram.part.Messages.ArchitectureElement2Group_title);
		paletteContainer.setId("createArchitectureElement2Group"); //$NON-NLS-1$
		paletteContainer.setDescription(BreezeModel.diagram.part.Messages.ArchitectureElement2Group_desc);
		paletteContainer.add(this.createArchitecture1CreationTool());
		paletteContainer.add(this.createComponent2CreationTool());
		paletteContainer.add(this.createLink3CreationTool());
		paletteContainer.add(this.createPort4CreationTool());
		paletteContainer.add(this.createConnector5CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createArchitectureStyle1CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.ArchitectureStyle1CreationTool_title,
				BreezeModel.diagram.part.Messages.ArchitectureStyle1CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Style_2002));
		entry.setId("createArchitectureStyle1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Style_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	private ToolEntry createArchitectureStyle1CreationTool_s() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.ArchitectureProduction1CreationTool_title,
				BreezeModel.diagram.part.Messages.ArchitectureProduction1CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Style_2002));
		entry.setId("createArchitectureStyle1CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Style_2002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * Creates "Architecture Style" palette tool group
	 * 
	 * @generated
	 */
	public PaletteContainer createArchitectureStyle4Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(BreezeModel.diagram.part.Messages.ArchitectureStyle4Group_title);
		paletteContainer.setId("createArchitectureStyle4Group"); //$NON-NLS-1$
		paletteContainer.add(this.createArchitectureStyle1CreationTool());
		paletteContainer.add(this.createTemplate2CreationTool());
		paletteContainer.add(this.createTemplateNode3CreationTool());
		//	paletteContainer.add(this.createProduction4CreationTool());
		return paletteContainer;
	}

	public PaletteContainer createArchitectureStyle4Group_s() {
		PaletteDrawer paletteContainer = new PaletteDrawer(BreezeModel.diagram.part.Messages.ArchitectureProduction4Group_title);
		paletteContainer.setId("createArchitectureProduction4Group"); //$NON-NLS-1$
		paletteContainer.add(this.createArchitectureStyle1CreationTool_s());
		//paletteContainer.add(this.createTemplate2CreationTool());
		//paletteContainer.add(this.createTemplateNode3CreationTool());
		paletteContainer.add(this.createProduction4CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComponent2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.Component2CreationTool_title,
				BreezeModel.diagram.part.Messages.Component2CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005));
		entry.setId("createComponent2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Component_3005));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createComponentTempleate1CreationTool() {
		ToolEntry entry = new ToolEntry(BreezeModel.diagram.part.Messages.ComponentTempleate1CreationTool_title,
				BreezeModel.diagram.part.Messages.ComponentTempleate1CreationTool_desc, null, null) {
		};
		entry.setId("createComponentTempleate1CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createConnector5CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.Connector5CreationTool_title,
				BreezeModel.diagram.part.Messages.Connector5CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003));
		entry.setId("createConnector5CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Connector_3003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * Creates "General Element" palette tool group
	 * 
	 * @generated
	 */
	public PaletteContainer createGeneralElement1Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(BreezeModel.diagram.part.Messages.GeneralElement1Group_title);
		paletteContainer.setId("createGeneralElement1Group"); //$NON-NLS-1$
		paletteContainer.add(this.createComponentTempleate1CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated
	 */
	private ToolEntry createGoku2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.Goku2CreationTool_title,
				BreezeModel.diagram.part.Messages.Goku2CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Goku_2003));
		entry.setId("createGoku2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Goku_2003));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createLink3CreationTool() {
		LinkToolEntry entry = new LinkToolEntry(BreezeModel.diagram.part.Messages.Link3CreationTool_title,
				BreezeModel.diagram.part.Messages.Link3CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002));
		entry.setId("createLink3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Link_4002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createPort4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.Port4CreationTool_title,
				BreezeModel.diagram.part.Messages.Port4CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Port_3002));
		entry.setId("createPort4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Port_3002));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createProduction4CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.Production4CreationTool_title, null,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Production_3011));
		entry.setId("createProduction4CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Production_3011));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createRDB1CreationTool() {
		ToolEntry entry = new ToolEntry(BreezeModel.diagram.part.Messages.RDB1CreationTool_title,
				BreezeModel.diagram.part.Messages.RDB1CreationTool_desc, null, null) {
		};
		entry.setId("createRDB1CreationTool"); //$NON-NLS-1$
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTemplate2CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.Template2CreationTool_title,
				BreezeModel.diagram.part.Messages.Template2CreationTool_desc,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.Template_3010));
		entry.setId("createTemplate2CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.Template_3010));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * @generated
	 */
	private ToolEntry createTemplateNode3CreationTool() {
		NodeToolEntry entry = new NodeToolEntry(BreezeModel.diagram.part.Messages.TemplateNode3CreationTool_title, null,
				Collections.singletonList(BreezeModel.diagram.providers.BreezeElementTypes.NodeTemplate_3009));
		entry.setId("createTemplateNode3CreationTool"); //$NON-NLS-1$
		entry.setSmallIcon(BreezeModel.diagram.providers.BreezeElementTypes
				.getImageDescriptor(BreezeModel.diagram.providers.BreezeElementTypes.NodeTemplate_3009));
		entry.setLargeIcon(entry.getSmallIcon());
		return entry;
	}

	/**
	 * Creates "Trustworthiness Element" palette tool group
	 * 
	 * @generated
	 */
	public PaletteContainer createTrustworthinessElement3Group() {
		PaletteDrawer paletteContainer = new PaletteDrawer(BreezeModel.diagram.part.Messages.TrustworthinessElement3Group_title);
		paletteContainer.setId("createTrustworthinessElement3Group"); //$NON-NLS-1$
		paletteContainer.add(this.createRDB1CreationTool());
		paletteContainer.add(this.createGoku2CreationTool());
		return paletteContainer;
	}

	/**
	 * @generated NOT
	 */
	public void fillPalette(PaletteRoot paletteRoot) {
		//	paletteRoot.add(this.createGeneralElement1Group());
		paletteRoot.add(this.createArchitectureElement2Group());
		//	paletteRoot.add(this.createTrustworthinessElement3Group());
		paletteRoot.add(this.createArchitectureStyle4Group());
	}

	public void fillPalette_s(PaletteRoot paletteRoot) {
		//	paletteRoot.add(this.createGeneralElement1Group());
		paletteRoot.add(this.createArchitectureElement2Group());
		//	paletteRoot.add(this.createTrustworthinessElement3Group());
		paletteRoot.add(this.createArchitectureStyle4Group_s());
	}
}
