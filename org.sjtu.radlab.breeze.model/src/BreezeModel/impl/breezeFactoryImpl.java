/**
 */
package BreezeModel.impl;

import BreezeModel.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class breezeFactoryImpl extends EFactoryImpl implements breezeFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static breezeFactory init() {
		try {
			breezeFactory thebreezeFactory = (breezeFactory)EPackage.Registry.INSTANCE.getEFactory(breezePackage.eNS_URI);
			if (thebreezeFactory != null) {
				return thebreezeFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new breezeFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public breezeFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case breezePackage.BREEZE: return createBreeze();
			case breezePackage.ATTRIBUTE: return createAttribute();
			case breezePackage.PORT: return createPort();
			case breezePackage.STYLE: return createStyle();
			case breezePackage.ARCH: return createArch();
			case breezePackage.NODE: return createNode();
			case breezePackage.LINK: return createLink();
			case breezePackage.DOCUMENTABLE: return createDocumentable();
			case breezePackage.COMPONENT: return createComponent();
			case breezePackage.CONNECTOR: return createConnector();
			case breezePackage.RELATION_SHIP: return createRelationShip();
			case breezePackage.NODE_INSTANCE: return createNodeInstance();
			case breezePackage.NODE_TEMPLATE: return createNodeTemplate();
			case breezePackage.TEMPLATE: return createTemplate();
			case breezePackage.PRODUCTION: return createProduction();
			case breezePackage.GOKU: return creategoku();
			case breezePackage.METHOD: return createMethod();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case breezePackage.ARCH_TYPE:
				return createArchTypeFromString(eDataType, initialValue);
			case breezePackage.ACCESS_TYPE:
				return createAccessTypeFromString(eDataType, initialValue);
			case breezePackage.DIRECTION_TYPE:
				return createDirectionTypeFromString(eDataType, initialValue);
			case breezePackage.NODE_TEMPLATE_TYPE:
				return createNodeTemplateTypeFromString(eDataType, initialValue);
			case breezePackage.STYLE_TYPE:
				return createStyleTypeFromString(eDataType, initialValue);
			case breezePackage.MULTI_ACCESS:
				return createMultiAccessFromString(eDataType, initialValue);
			case breezePackage.AVAILABILITY:
				return createAvailabilityFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case breezePackage.ARCH_TYPE:
				return convertArchTypeToString(eDataType, instanceValue);
			case breezePackage.ACCESS_TYPE:
				return convertAccessTypeToString(eDataType, instanceValue);
			case breezePackage.DIRECTION_TYPE:
				return convertDirectionTypeToString(eDataType, instanceValue);
			case breezePackage.NODE_TEMPLATE_TYPE:
				return convertNodeTemplateTypeToString(eDataType, instanceValue);
			case breezePackage.STYLE_TYPE:
				return convertStyleTypeToString(eDataType, instanceValue);
			case breezePackage.MULTI_ACCESS:
				return convertMultiAccessToString(eDataType, instanceValue);
			case breezePackage.AVAILABILITY:
				return convertAvailabilityToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Breeze createBreeze() {
		BreezeImpl breeze = new BreezeImpl();
		return breeze;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Attribute createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Port createPort() {
		PortImpl port = new PortImpl();
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Style createStyle() {
		StyleImpl style = new StyleImpl();
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Arch createArch() {
		ArchImpl arch = new ArchImpl();
		return arch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Link createLink() {
		LinkImpl link = new LinkImpl();
		return link;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Documentable createDocumentable() {
		DocumentableImpl documentable = new DocumentableImpl();
		return documentable;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component createComponent() {
		ComponentImpl component = new ComponentImpl();
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Connector createConnector() {
		ConnectorImpl connector = new ConnectorImpl();
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationShip createRelationShip() {
		RelationShipImpl relationShip = new RelationShipImpl();
		return relationShip;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeInstance createNodeInstance() {
		NodeInstanceImpl nodeInstance = new NodeInstanceImpl();
		return nodeInstance;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeTemplate createNodeTemplate() {
		NodeTemplateImpl nodeTemplate = new NodeTemplateImpl();
		return nodeTemplate;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template createTemplate() {
		TemplateImpl template = new TemplateImpl();
		return template;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Production createProduction() {
		ProductionImpl production = new ProductionImpl();
		return production;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public goku creategoku() {
		gokuImpl goku = new gokuImpl();
		return goku;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Method createMethod() {
		MethodImpl method = new MethodImpl();
		return method;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ArchType createArchTypeFromString(EDataType eDataType, String initialValue) {
		ArchType result = ArchType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertArchTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessType createAccessTypeFromString(EDataType eDataType, String initialValue) {
		AccessType result = AccessType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAccessTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirectionType createDirectionTypeFromString(EDataType eDataType, String initialValue) {
		DirectionType result = DirectionType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDirectionTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeTemplateType createNodeTemplateTypeFromString(EDataType eDataType, String initialValue) {
		NodeTemplateType result = NodeTemplateType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNodeTemplateTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleType createStyleTypeFromString(EDataType eDataType, String initialValue) {
		StyleType result = StyleType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStyleTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiAccess createMultiAccessFromString(EDataType eDataType, String initialValue) {
		MultiAccess result = MultiAccess.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMultiAccessToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Availability createAvailabilityFromString(EDataType eDataType, String initialValue) {
		Availability result = Availability.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertAvailabilityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public breezePackage getbreezePackage() {
		return (breezePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static breezePackage getPackage() {
		return breezePackage.eINSTANCE;
	}

} //breezeFactoryImpl
