/**
 */
package BreezeModel;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see BreezeModel.breezeFactory
 * @model kind="package"
 * @generated
 */
public interface breezePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "BreezeModel";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://org.sjtu.radlab.breeze/v2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Breeze";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	breezePackage eINSTANCE = BreezeModel.impl.breezePackageImpl.init();

	/**
	 * The meta object id for the '{@link BreezeModel.impl.DocumentableImpl <em>Documentable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.DocumentableImpl
	 * @see BreezeModel.impl.breezePackageImpl#getDocumentable()
	 * @generated
	 */
	int DOCUMENTABLE = 9;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTABLE__DOCUMENTATION = 0;

	/**
	 * The number of structural features of the '<em>Documentable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTABLE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Documentable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENTABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.BreezeImpl <em>Breeze</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.BreezeImpl
	 * @see BreezeModel.impl.breezePackageImpl#getBreeze()
	 * @generated
	 */
	int BREEZE = 0;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREEZE__DOCUMENTATION = DOCUMENTABLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Arch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREEZE__ARCH = DOCUMENTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREEZE__STYLE = DOCUMENTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Gkeee</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREEZE__GKEEE = DOCUMENTABLE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Breeze</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREEZE_FEATURE_COUNT = DOCUMENTABLE_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Breeze</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BREEZE_OPERATION_COUNT = DOCUMENTABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.AttributeImpl
	 * @see BreezeModel.impl.breezePackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link BreezeModel.Nameable <em>Nameable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.Nameable
	 * @see BreezeModel.impl.breezePackageImpl#getNameable()
	 * @generated
	 */
	int NAMEABLE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMEABLE__NAME = 0;

	/**
	 * The number of structural features of the '<em>Nameable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMEABLE_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Nameable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NAMEABLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.ArchElementImpl <em>Arch Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.ArchElementImpl
	 * @see BreezeModel.impl.breezePackageImpl#getArchElement()
	 * @generated
	 */
	int ARCH_ELEMENT = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH_ELEMENT__NAME = NAMEABLE__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH_ELEMENT__DOCUMENTATION = NAMEABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH_ELEMENT__ATTRIBUTE = NAMEABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Arch Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH_ELEMENT_FEATURE_COUNT = NAMEABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Arch Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH_ELEMENT_OPERATION_COUNT = NAMEABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.PortImpl <em>Port</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.PortImpl
	 * @see BreezeModel.impl.breezePackageImpl#getPort()
	 * @generated
	 */
	int PORT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__NAME = ARCH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__DOCUMENTATION = ARCH_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__ATTRIBUTE = ARCH_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__TYPE = ARCH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__DIRECTION = ARCH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Multiaccess</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT__MULTIACCESS = ARCH_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_FEATURE_COUNT = ARCH_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Port</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PORT_OPERATION_COUNT = ARCH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.StyleImpl <em>Style</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.StyleImpl
	 * @see BreezeModel.impl.breezePackageImpl#getStyle()
	 * @generated
	 */
	int STYLE = 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__DOCUMENTATION = DOCUMENTABLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__NAME = DOCUMENTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Arch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__ARCH = DOCUMENTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Production</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__PRODUCTION = DOCUMENTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Style Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE__STYLE_TYPE = DOCUMENTABLE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_FEATURE_COUNT = DOCUMENTABLE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Style</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STYLE_OPERATION_COUNT = DOCUMENTABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.ArchImpl <em>Arch</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.ArchImpl
	 * @see BreezeModel.impl.breezePackageImpl#getArch()
	 * @generated
	 */
	int ARCH = 4;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH__DOCUMENTATION = DOCUMENTABLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH__NAME = DOCUMENTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH__NODE = DOCUMENTABLE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Edge</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH__EDGE = DOCUMENTABLE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH__TYPE = DOCUMENTABLE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Arch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH_FEATURE_COUNT = DOCUMENTABLE_FEATURE_COUNT + 4;

	/**
	 * The number of operations of the '<em>Arch</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARCH_OPERATION_COUNT = DOCUMENTABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.NodeImpl
	 * @see BreezeModel.impl.breezePackageImpl#getNode()
	 * @generated
	 */
	int NODE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__NAME = ARCH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__DOCUMENTATION = ARCH_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__ATTRIBUTE = ARCH_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Port</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__PORT = ARCH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = ARCH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_OPERATION_COUNT = ARCH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.RelationShipImpl <em>Relation Ship</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.RelationShipImpl
	 * @see BreezeModel.impl.breezePackageImpl#getRelationShip()
	 * @generated
	 */
	int RELATION_SHIP = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__NAME = ARCH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__DOCUMENTATION = ARCH_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__ATTRIBUTE = ARCH_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__SOURCE = ARCH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP__TARGET = ARCH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Relation Ship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP_FEATURE_COUNT = ARCH_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Relation Ship</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RELATION_SHIP_OPERATION_COUNT = ARCH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.LinkImpl <em>Link</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.LinkImpl
	 * @see BreezeModel.impl.breezePackageImpl#getLink()
	 * @generated
	 */
	int LINK = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__NAME = RELATION_SHIP__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DOCUMENTATION = RELATION_SHIP__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__ATTRIBUTE = RELATION_SHIP__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__SOURCE = RELATION_SHIP__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__TARGET = RELATION_SHIP__TARGET;

	/**
	 * The feature id for the '<em><b>Directed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK__DIRECTED = RELATION_SHIP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_FEATURE_COUNT = RELATION_SHIP_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Link</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LINK_OPERATION_COUNT = RELATION_SHIP_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.Attributes <em>Attributes</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.Attributes
	 * @see BreezeModel.impl.breezePackageImpl#getAttributes()
	 * @generated
	 */
	int ATTRIBUTES = 7;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES__ATTRIBUTE = 0;

	/**
	 * The number of structural features of the '<em>Attributes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Attributes</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTES_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.NodeInstanceImpl <em>Node Instance</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.NodeInstanceImpl
	 * @see BreezeModel.impl.breezePackageImpl#getNodeInstance()
	 * @generated
	 */
	int NODE_INSTANCE = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__DOCUMENTATION = NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__ATTRIBUTE = NODE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Port</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__PORT = NODE__PORT;

	/**
	 * The feature id for the '<em><b>TR</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__TR = NODE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Availability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE__AVAILABILITY = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Node Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE_FEATURE_COUNT = NODE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Node Instance</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_INSTANCE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.ComponentImpl
	 * @see BreezeModel.impl.breezePackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAME = NODE_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DOCUMENTATION = NODE_INSTANCE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ATTRIBUTE = NODE_INSTANCE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Port</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PORT = NODE_INSTANCE__PORT;

	/**
	 * The feature id for the '<em><b>TR</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__TR = NODE_INSTANCE__TR;

	/**
	 * The feature id for the '<em><b>Availability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__AVAILABILITY = NODE_INSTANCE__AVAILABILITY;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = NODE_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_OPERATION_COUNT = NODE_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.ConnectorImpl <em>Connector</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.ConnectorImpl
	 * @see BreezeModel.impl.breezePackageImpl#getConnector()
	 * @generated
	 */
	int CONNECTOR = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__NAME = NODE_INSTANCE__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__DOCUMENTATION = NODE_INSTANCE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__ATTRIBUTE = NODE_INSTANCE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Port</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__PORT = NODE_INSTANCE__PORT;

	/**
	 * The feature id for the '<em><b>TR</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__TR = NODE_INSTANCE__TR;

	/**
	 * The feature id for the '<em><b>Availability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR__AVAILABILITY = NODE_INSTANCE__AVAILABILITY;

	/**
	 * The number of structural features of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_FEATURE_COUNT = NODE_INSTANCE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Connector</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONNECTOR_OPERATION_COUNT = NODE_INSTANCE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.NodeTemplateImpl <em>Node Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.NodeTemplateImpl
	 * @see BreezeModel.impl.breezePackageImpl#getNodeTemplate()
	 * @generated
	 */
	int NODE_TEMPLATE = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TEMPLATE__NAME = NODE__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TEMPLATE__DOCUMENTATION = NODE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TEMPLATE__ATTRIBUTE = NODE__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Port</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TEMPLATE__PORT = NODE__PORT;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TEMPLATE__TYPE = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Node Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TEMPLATE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Node Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_TEMPLATE_OPERATION_COUNT = NODE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.TemplateImpl <em>Template</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.TemplateImpl
	 * @see BreezeModel.impl.breezePackageImpl#getTemplate()
	 * @generated
	 */
	int TEMPLATE = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__NAME = ARCH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__DOCUMENTATION = ARCH_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__ATTRIBUTE = ARCH_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Node</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE__NODE = ARCH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_FEATURE_COUNT = ARCH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Template</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEMPLATE_OPERATION_COUNT = ARCH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.ProductionImpl <em>Production</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.ProductionImpl
	 * @see BreezeModel.impl.breezePackageImpl#getProduction()
	 * @generated
	 */
	int PRODUCTION = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION__NAME = ARCH_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION__DOCUMENTATION = ARCH_ELEMENT__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Attribute</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION__ATTRIBUTE = ARCH_ELEMENT__ATTRIBUTE;

	/**
	 * The feature id for the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION__LEFT = ARCH_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION__RIGHT = ARCH_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Production</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_FEATURE_COUNT = ARCH_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>Production</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRODUCTION_OPERATION_COUNT = ARCH_ELEMENT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.gokuImpl <em>goku</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.gokuImpl
	 * @see BreezeModel.impl.breezePackageImpl#getgoku()
	 * @generated
	 */
	int GOKU = 18;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOKU__DOCUMENTATION = DOCUMENTABLE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOKU__POWER = DOCUMENTABLE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Win</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOKU__WIN = DOCUMENTABLE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>goku</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOKU_FEATURE_COUNT = DOCUMENTABLE_FEATURE_COUNT + 2;

	/**
	 * The number of operations of the '<em>goku</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GOKU_OPERATION_COUNT = DOCUMENTABLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link BreezeModel.impl.MethodImpl <em>Method</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.impl.MethodImpl
	 * @see BreezeModel.impl.breezePackageImpl#getMethod()
	 * @generated
	 */
	int METHOD = 19;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD__TYPE = 0;

	/**
	 * The number of structural features of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Method</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int METHOD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link BreezeModel.ArchType <em>Arch Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.ArchType
	 * @see BreezeModel.impl.breezePackageImpl#getArchType()
	 * @generated
	 */
	int ARCH_TYPE = 20;

	/**
	 * The meta object id for the '{@link BreezeModel.AccessType <em>Access Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.AccessType
	 * @see BreezeModel.impl.breezePackageImpl#getAccessType()
	 * @generated
	 */
	int ACCESS_TYPE = 21;

	/**
	 * The meta object id for the '{@link BreezeModel.DirectionType <em>Direction Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.DirectionType
	 * @see BreezeModel.impl.breezePackageImpl#getDirectionType()
	 * @generated
	 */
	int DIRECTION_TYPE = 22;

	/**
	 * The meta object id for the '{@link BreezeModel.NodeTemplateType <em>Node Template Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.NodeTemplateType
	 * @see BreezeModel.impl.breezePackageImpl#getNodeTemplateType()
	 * @generated
	 */
	int NODE_TEMPLATE_TYPE = 23;


	/**
	 * The meta object id for the '{@link BreezeModel.StyleType <em>Style Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.StyleType
	 * @see BreezeModel.impl.breezePackageImpl#getStyleType()
	 * @generated
	 */
	int STYLE_TYPE = 24;


	/**
	 * The meta object id for the '{@link BreezeModel.MultiAccess <em>Multi Access</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.MultiAccess
	 * @see BreezeModel.impl.breezePackageImpl#getMultiAccess()
	 * @generated
	 */
	int MULTI_ACCESS = 25;

	/**
	 * The meta object id for the '{@link BreezeModel.Availability <em>Availability</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see BreezeModel.Availability
	 * @see BreezeModel.impl.breezePackageImpl#getAvailability()
	 * @generated
	 */
	int AVAILABILITY = 26;


	/**
	 * Returns the meta object for class '{@link BreezeModel.Breeze <em>Breeze</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Breeze</em>'.
	 * @see BreezeModel.Breeze
	 * @generated
	 */
	EClass getBreeze();

	/**
	 * Returns the meta object for the containment reference '{@link BreezeModel.Breeze#getArch <em>Arch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Arch</em>'.
	 * @see BreezeModel.Breeze#getArch()
	 * @see #getBreeze()
	 * @generated
	 */
	EReference getBreeze_Arch();

	/**
	 * Returns the meta object for the containment reference '{@link BreezeModel.Breeze#getStyle <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Style</em>'.
	 * @see BreezeModel.Breeze#getStyle()
	 * @see #getBreeze()
	 * @generated
	 */
	EReference getBreeze_Style();

	/**
	 * Returns the meta object for the containment reference list '{@link BreezeModel.Breeze#getGkeee <em>Gkeee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Gkeee</em>'.
	 * @see BreezeModel.Breeze#getGkeee()
	 * @see #getBreeze()
	 * @generated
	 */
	EReference getBreeze_Gkeee();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see BreezeModel.Attribute
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Attribute#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see BreezeModel.Attribute#getKey()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Key();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Attribute#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see BreezeModel.Attribute#getValue()
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Value();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Port</em>'.
	 * @see BreezeModel.Port
	 * @generated
	 */
	EClass getPort();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Port#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see BreezeModel.Port#getType()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Type();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Port#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see BreezeModel.Port#getDirection()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Direction();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Port#getMultiaccess <em>Multiaccess</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Multiaccess</em>'.
	 * @see BreezeModel.Port#getMultiaccess()
	 * @see #getPort()
	 * @generated
	 */
	EAttribute getPort_Multiaccess();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Style <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Style</em>'.
	 * @see BreezeModel.Style
	 * @generated
	 */
	EClass getStyle();

	/**
	 * Returns the meta object for the containment reference '{@link BreezeModel.Style#getArch <em>Arch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Arch</em>'.
	 * @see BreezeModel.Style#getArch()
	 * @see #getStyle()
	 * @generated
	 */
	EReference getStyle_Arch();

	/**
	 * Returns the meta object for the containment reference list '{@link BreezeModel.Style#getProduction <em>Production</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Production</em>'.
	 * @see BreezeModel.Style#getProduction()
	 * @see #getStyle()
	 * @generated
	 */
	EReference getStyle_Production();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Style#getStyleType <em>Style Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Style Type</em>'.
	 * @see BreezeModel.Style#getStyleType()
	 * @see #getStyle()
	 * @generated
	 */
	EAttribute getStyle_StyleType();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Arch <em>Arch</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arch</em>'.
	 * @see BreezeModel.Arch
	 * @generated
	 */
	EClass getArch();

	/**
	 * Returns the meta object for the containment reference list '{@link BreezeModel.Arch#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node</em>'.
	 * @see BreezeModel.Arch#getNode()
	 * @see #getArch()
	 * @generated
	 */
	EReference getArch_Node();

	/**
	 * Returns the meta object for the containment reference list '{@link BreezeModel.Arch#getEdge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edge</em>'.
	 * @see BreezeModel.Arch#getEdge()
	 * @see #getArch()
	 * @generated
	 */
	EReference getArch_Edge();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Arch#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see BreezeModel.Arch#getType()
	 * @see #getArch()
	 * @generated
	 */
	EAttribute getArch_Type();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see BreezeModel.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the containment reference list '{@link BreezeModel.Node#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Port</em>'.
	 * @see BreezeModel.Node#getPort()
	 * @see #getNode()
	 * @generated
	 */
	EReference getNode_Port();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Link</em>'.
	 * @see BreezeModel.Link
	 * @generated
	 */
	EClass getLink();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Link#isDirected <em>Directed</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Directed</em>'.
	 * @see BreezeModel.Link#isDirected()
	 * @see #getLink()
	 * @generated
	 */
	EAttribute getLink_Directed();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Attributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attributes</em>'.
	 * @see BreezeModel.Attributes
	 * @generated
	 */
	EClass getAttributes();

	/**
	 * Returns the meta object for the containment reference list '{@link BreezeModel.Attributes#getAttribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attribute</em>'.
	 * @see BreezeModel.Attributes#getAttribute()
	 * @see #getAttributes()
	 * @generated
	 */
	EReference getAttributes_Attribute();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nameable</em>'.
	 * @see BreezeModel.Nameable
	 * @generated
	 */
	EClass getNameable();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Nameable#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see BreezeModel.Nameable#getName()
	 * @see #getNameable()
	 * @generated
	 */
	EAttribute getNameable_Name();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Documentable <em>Documentable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Documentable</em>'.
	 * @see BreezeModel.Documentable
	 * @generated
	 */
	EClass getDocumentable();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Documentable#getDocumentation <em>Documentation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Documentation</em>'.
	 * @see BreezeModel.Documentable#getDocumentation()
	 * @see #getDocumentable()
	 * @generated
	 */
	EAttribute getDocumentable_Documentation();

	/**
	 * Returns the meta object for class '{@link BreezeModel.ArchElement <em>Arch Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arch Element</em>'.
	 * @see BreezeModel.ArchElement
	 * @generated
	 */
	EClass getArchElement();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see BreezeModel.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connector</em>'.
	 * @see BreezeModel.Connector
	 * @generated
	 */
	EClass getConnector();

	/**
	 * Returns the meta object for class '{@link BreezeModel.RelationShip <em>Relation Ship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Relation Ship</em>'.
	 * @see BreezeModel.RelationShip
	 * @generated
	 */
	EClass getRelationShip();

	/**
	 * Returns the meta object for the reference '{@link BreezeModel.RelationShip#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see BreezeModel.RelationShip#getSource()
	 * @see #getRelationShip()
	 * @generated
	 */
	EReference getRelationShip_Source();

	/**
	 * Returns the meta object for the reference '{@link BreezeModel.RelationShip#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see BreezeModel.RelationShip#getTarget()
	 * @see #getRelationShip()
	 * @generated
	 */
	EReference getRelationShip_Target();

	/**
	 * Returns the meta object for class '{@link BreezeModel.NodeInstance <em>Node Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Instance</em>'.
	 * @see BreezeModel.NodeInstance
	 * @generated
	 */
	EClass getNodeInstance();

	/**
	 * Returns the meta object for the reference '{@link BreezeModel.NodeInstance#getTR <em>TR</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>TR</em>'.
	 * @see BreezeModel.NodeInstance#getTR()
	 * @see #getNodeInstance()
	 * @generated
	 */
	EReference getNodeInstance_TR();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.NodeInstance#getAvailability <em>Availability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Availability</em>'.
	 * @see BreezeModel.NodeInstance#getAvailability()
	 * @see #getNodeInstance()
	 * @generated
	 */
	EAttribute getNodeInstance_Availability();

	/**
	 * Returns the meta object for class '{@link BreezeModel.NodeTemplate <em>Node Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node Template</em>'.
	 * @see BreezeModel.NodeTemplate
	 * @generated
	 */
	EClass getNodeTemplate();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.NodeTemplate#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see BreezeModel.NodeTemplate#getType()
	 * @see #getNodeTemplate()
	 * @generated
	 */
	EAttribute getNodeTemplate_Type();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template</em>'.
	 * @see BreezeModel.Template
	 * @generated
	 */
	EClass getTemplate();

	/**
	 * Returns the meta object for the containment reference list '{@link BreezeModel.Template#getNode <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Node</em>'.
	 * @see BreezeModel.Template#getNode()
	 * @see #getTemplate()
	 * @generated
	 */
	EReference getTemplate_Node();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Production <em>Production</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Production</em>'.
	 * @see BreezeModel.Production
	 * @generated
	 */
	EClass getProduction();

	/**
	 * Returns the meta object for the containment reference '{@link BreezeModel.Production#getLeft <em>Left</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Left</em>'.
	 * @see BreezeModel.Production#getLeft()
	 * @see #getProduction()
	 * @generated
	 */
	EReference getProduction_Left();

	/**
	 * Returns the meta object for the containment reference '{@link BreezeModel.Production#getRight <em>Right</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Right</em>'.
	 * @see BreezeModel.Production#getRight()
	 * @see #getProduction()
	 * @generated
	 */
	EReference getProduction_Right();

	/**
	 * Returns the meta object for class '{@link BreezeModel.goku <em>goku</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>goku</em>'.
	 * @see BreezeModel.goku
	 * @generated
	 */
	EClass getgoku();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.goku#getPower <em>Power</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Power</em>'.
	 * @see BreezeModel.goku#getPower()
	 * @see #getgoku()
	 * @generated
	 */
	EAttribute getgoku_Power();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.goku#getWin <em>Win</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Win</em>'.
	 * @see BreezeModel.goku#getWin()
	 * @see #getgoku()
	 * @generated
	 */
	EAttribute getgoku_Win();

	/**
	 * Returns the meta object for class '{@link BreezeModel.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Method</em>'.
	 * @see BreezeModel.Method
	 * @generated
	 */
	EClass getMethod();

	/**
	 * Returns the meta object for the attribute '{@link BreezeModel.Method#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see BreezeModel.Method#getType()
	 * @see #getMethod()
	 * @generated
	 */
	EAttribute getMethod_Type();

	/**
	 * Returns the meta object for enum '{@link BreezeModel.ArchType <em>Arch Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Arch Type</em>'.
	 * @see BreezeModel.ArchType
	 * @generated
	 */
	EEnum getArchType();

	/**
	 * Returns the meta object for enum '{@link BreezeModel.AccessType <em>Access Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Access Type</em>'.
	 * @see BreezeModel.AccessType
	 * @generated
	 */
	EEnum getAccessType();

	/**
	 * Returns the meta object for enum '{@link BreezeModel.DirectionType <em>Direction Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction Type</em>'.
	 * @see BreezeModel.DirectionType
	 * @generated
	 */
	EEnum getDirectionType();

	/**
	 * Returns the meta object for enum '{@link BreezeModel.NodeTemplateType <em>Node Template Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Node Template Type</em>'.
	 * @see BreezeModel.NodeTemplateType
	 * @generated
	 */
	EEnum getNodeTemplateType();

	/**
	 * Returns the meta object for enum '{@link BreezeModel.StyleType <em>Style Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Style Type</em>'.
	 * @see BreezeModel.StyleType
	 * @generated
	 */
	EEnum getStyleType();

	/**
	 * Returns the meta object for enum '{@link BreezeModel.MultiAccess <em>Multi Access</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Multi Access</em>'.
	 * @see BreezeModel.MultiAccess
	 * @generated
	 */
	EEnum getMultiAccess();

	/**
	 * Returns the meta object for enum '{@link BreezeModel.Availability <em>Availability</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Availability</em>'.
	 * @see BreezeModel.Availability
	 * @generated
	 */
	EEnum getAvailability();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	breezeFactory getbreezeFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link BreezeModel.impl.BreezeImpl <em>Breeze</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.BreezeImpl
		 * @see BreezeModel.impl.breezePackageImpl#getBreeze()
		 * @generated
		 */
		EClass BREEZE = eINSTANCE.getBreeze();

		/**
		 * The meta object literal for the '<em><b>Arch</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREEZE__ARCH = eINSTANCE.getBreeze_Arch();

		/**
		 * The meta object literal for the '<em><b>Style</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREEZE__STYLE = eINSTANCE.getBreeze_Style();

		/**
		 * The meta object literal for the '<em><b>Gkeee</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BREEZE__GKEEE = eINSTANCE.getBreeze_Gkeee();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.AttributeImpl
		 * @see BreezeModel.impl.breezePackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__KEY = eINSTANCE.getAttribute_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__VALUE = eINSTANCE.getAttribute_Value();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.PortImpl <em>Port</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.PortImpl
		 * @see BreezeModel.impl.breezePackageImpl#getPort()
		 * @generated
		 */
		EClass PORT = eINSTANCE.getPort();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__TYPE = eINSTANCE.getPort_Type();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__DIRECTION = eINSTANCE.getPort_Direction();

		/**
		 * The meta object literal for the '<em><b>Multiaccess</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PORT__MULTIACCESS = eINSTANCE.getPort_Multiaccess();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.StyleImpl <em>Style</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.StyleImpl
		 * @see BreezeModel.impl.breezePackageImpl#getStyle()
		 * @generated
		 */
		EClass STYLE = eINSTANCE.getStyle();

		/**
		 * The meta object literal for the '<em><b>Arch</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLE__ARCH = eINSTANCE.getStyle_Arch();

		/**
		 * The meta object literal for the '<em><b>Production</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STYLE__PRODUCTION = eINSTANCE.getStyle_Production();

		/**
		 * The meta object literal for the '<em><b>Style Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STYLE__STYLE_TYPE = eINSTANCE.getStyle_StyleType();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.ArchImpl <em>Arch</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.ArchImpl
		 * @see BreezeModel.impl.breezePackageImpl#getArch()
		 * @generated
		 */
		EClass ARCH = eINSTANCE.getArch();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCH__NODE = eINSTANCE.getArch_Node();

		/**
		 * The meta object literal for the '<em><b>Edge</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARCH__EDGE = eINSTANCE.getArch_Edge();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARCH__TYPE = eINSTANCE.getArch_Type();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.NodeImpl
		 * @see BreezeModel.impl.breezePackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Port</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE__PORT = eINSTANCE.getNode_Port();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.LinkImpl <em>Link</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.LinkImpl
		 * @see BreezeModel.impl.breezePackageImpl#getLink()
		 * @generated
		 */
		EClass LINK = eINSTANCE.getLink();

		/**
		 * The meta object literal for the '<em><b>Directed</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LINK__DIRECTED = eINSTANCE.getLink_Directed();

		/**
		 * The meta object literal for the '{@link BreezeModel.Attributes <em>Attributes</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.Attributes
		 * @see BreezeModel.impl.breezePackageImpl#getAttributes()
		 * @generated
		 */
		EClass ATTRIBUTES = eINSTANCE.getAttributes();

		/**
		 * The meta object literal for the '<em><b>Attribute</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ATTRIBUTES__ATTRIBUTE = eINSTANCE.getAttributes_Attribute();

		/**
		 * The meta object literal for the '{@link BreezeModel.Nameable <em>Nameable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.Nameable
		 * @see BreezeModel.impl.breezePackageImpl#getNameable()
		 * @generated
		 */
		EClass NAMEABLE = eINSTANCE.getNameable();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMEABLE__NAME = eINSTANCE.getNameable_Name();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.DocumentableImpl <em>Documentable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.DocumentableImpl
		 * @see BreezeModel.impl.breezePackageImpl#getDocumentable()
		 * @generated
		 */
		EClass DOCUMENTABLE = eINSTANCE.getDocumentable();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENTABLE__DOCUMENTATION = eINSTANCE.getDocumentable_Documentation();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.ArchElementImpl <em>Arch Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.ArchElementImpl
		 * @see BreezeModel.impl.breezePackageImpl#getArchElement()
		 * @generated
		 */
		EClass ARCH_ELEMENT = eINSTANCE.getArchElement();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.ComponentImpl
		 * @see BreezeModel.impl.breezePackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.ConnectorImpl <em>Connector</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.ConnectorImpl
		 * @see BreezeModel.impl.breezePackageImpl#getConnector()
		 * @generated
		 */
		EClass CONNECTOR = eINSTANCE.getConnector();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.RelationShipImpl <em>Relation Ship</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.RelationShipImpl
		 * @see BreezeModel.impl.breezePackageImpl#getRelationShip()
		 * @generated
		 */
		EClass RELATION_SHIP = eINSTANCE.getRelationShip();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION_SHIP__SOURCE = eINSTANCE.getRelationShip_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RELATION_SHIP__TARGET = eINSTANCE.getRelationShip_Target();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.NodeInstanceImpl <em>Node Instance</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.NodeInstanceImpl
		 * @see BreezeModel.impl.breezePackageImpl#getNodeInstance()
		 * @generated
		 */
		EClass NODE_INSTANCE = eINSTANCE.getNodeInstance();

		/**
		 * The meta object literal for the '<em><b>TR</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODE_INSTANCE__TR = eINSTANCE.getNodeInstance_TR();

		/**
		 * The meta object literal for the '<em><b>Availability</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_INSTANCE__AVAILABILITY = eINSTANCE.getNodeInstance_Availability();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.NodeTemplateImpl <em>Node Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.NodeTemplateImpl
		 * @see BreezeModel.impl.breezePackageImpl#getNodeTemplate()
		 * @generated
		 */
		EClass NODE_TEMPLATE = eINSTANCE.getNodeTemplate();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE_TEMPLATE__TYPE = eINSTANCE.getNodeTemplate_Type();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.TemplateImpl <em>Template</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.TemplateImpl
		 * @see BreezeModel.impl.breezePackageImpl#getTemplate()
		 * @generated
		 */
		EClass TEMPLATE = eINSTANCE.getTemplate();

		/**
		 * The meta object literal for the '<em><b>Node</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE__NODE = eINSTANCE.getTemplate_Node();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.ProductionImpl <em>Production</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.ProductionImpl
		 * @see BreezeModel.impl.breezePackageImpl#getProduction()
		 * @generated
		 */
		EClass PRODUCTION = eINSTANCE.getProduction();

		/**
		 * The meta object literal for the '<em><b>Left</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCTION__LEFT = eINSTANCE.getProduction_Left();

		/**
		 * The meta object literal for the '<em><b>Right</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRODUCTION__RIGHT = eINSTANCE.getProduction_Right();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.gokuImpl <em>goku</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.gokuImpl
		 * @see BreezeModel.impl.breezePackageImpl#getgoku()
		 * @generated
		 */
		EClass GOKU = eINSTANCE.getgoku();

		/**
		 * The meta object literal for the '<em><b>Power</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GOKU__POWER = eINSTANCE.getgoku_Power();

		/**
		 * The meta object literal for the '<em><b>Win</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute GOKU__WIN = eINSTANCE.getgoku_Win();

		/**
		 * The meta object literal for the '{@link BreezeModel.impl.MethodImpl <em>Method</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.impl.MethodImpl
		 * @see BreezeModel.impl.breezePackageImpl#getMethod()
		 * @generated
		 */
		EClass METHOD = eINSTANCE.getMethod();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute METHOD__TYPE = eINSTANCE.getMethod_Type();

		/**
		 * The meta object literal for the '{@link BreezeModel.ArchType <em>Arch Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.ArchType
		 * @see BreezeModel.impl.breezePackageImpl#getArchType()
		 * @generated
		 */
		EEnum ARCH_TYPE = eINSTANCE.getArchType();

		/**
		 * The meta object literal for the '{@link BreezeModel.AccessType <em>Access Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.AccessType
		 * @see BreezeModel.impl.breezePackageImpl#getAccessType()
		 * @generated
		 */
		EEnum ACCESS_TYPE = eINSTANCE.getAccessType();

		/**
		 * The meta object literal for the '{@link BreezeModel.DirectionType <em>Direction Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.DirectionType
		 * @see BreezeModel.impl.breezePackageImpl#getDirectionType()
		 * @generated
		 */
		EEnum DIRECTION_TYPE = eINSTANCE.getDirectionType();

		/**
		 * The meta object literal for the '{@link BreezeModel.NodeTemplateType <em>Node Template Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.NodeTemplateType
		 * @see BreezeModel.impl.breezePackageImpl#getNodeTemplateType()
		 * @generated
		 */
		EEnum NODE_TEMPLATE_TYPE = eINSTANCE.getNodeTemplateType();

		/**
		 * The meta object literal for the '{@link BreezeModel.StyleType <em>Style Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.StyleType
		 * @see BreezeModel.impl.breezePackageImpl#getStyleType()
		 * @generated
		 */
		EEnum STYLE_TYPE = eINSTANCE.getStyleType();

		/**
		 * The meta object literal for the '{@link BreezeModel.MultiAccess <em>Multi Access</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.MultiAccess
		 * @see BreezeModel.impl.breezePackageImpl#getMultiAccess()
		 * @generated
		 */
		EEnum MULTI_ACCESS = eINSTANCE.getMultiAccess();

		/**
		 * The meta object literal for the '{@link BreezeModel.Availability <em>Availability</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see BreezeModel.Availability
		 * @see BreezeModel.impl.breezePackageImpl#getAvailability()
		 * @generated
		 */
		EEnum AVAILABILITY = eINSTANCE.getAvailability();

	}

} //breezePackage
