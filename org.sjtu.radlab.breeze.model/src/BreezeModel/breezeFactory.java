/**
 */
package BreezeModel;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see BreezeModel.breezePackage
 * @generated
 */
public interface breezeFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	breezeFactory eINSTANCE = BreezeModel.impl.breezeFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Breeze</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Breeze</em>'.
	 * @generated
	 */
	Breeze createBreeze();

	/**
	 * Returns a new object of class '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Attribute</em>'.
	 * @generated
	 */
	Attribute createAttribute();

	/**
	 * Returns a new object of class '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Port</em>'.
	 * @generated
	 */
	Port createPort();

	/**
	 * Returns a new object of class '<em>Style</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Style</em>'.
	 * @generated
	 */
	Style createStyle();

	/**
	 * Returns a new object of class '<em>Arch</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arch</em>'.
	 * @generated
	 */
	Arch createArch();

	/**
	 * Returns a new object of class '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node</em>'.
	 * @generated
	 */
	Node createNode();

	/**
	 * Returns a new object of class '<em>Link</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Link</em>'.
	 * @generated
	 */
	Link createLink();

	/**
	 * Returns a new object of class '<em>Documentable</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Documentable</em>'.
	 * @generated
	 */
	Documentable createDocumentable();

	/**
	 * Returns a new object of class '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component</em>'.
	 * @generated
	 */
	Component createComponent();

	/**
	 * Returns a new object of class '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connector</em>'.
	 * @generated
	 */
	Connector createConnector();

	/**
	 * Returns a new object of class '<em>Relation Ship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Relation Ship</em>'.
	 * @generated
	 */
	RelationShip createRelationShip();

	/**
	 * Returns a new object of class '<em>Node Instance</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node Instance</em>'.
	 * @generated
	 */
	NodeInstance createNodeInstance();

	/**
	 * Returns a new object of class '<em>Node Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node Template</em>'.
	 * @generated
	 */
	NodeTemplate createNodeTemplate();

	/**
	 * Returns a new object of class '<em>Template</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Template</em>'.
	 * @generated
	 */
	Template createTemplate();

	/**
	 * Returns a new object of class '<em>Production</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Production</em>'.
	 * @generated
	 */
	Production createProduction();

	/**
	 * Returns a new object of class '<em>goku</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>goku</em>'.
	 * @generated
	 */
	goku creategoku();

	/**
	 * Returns a new object of class '<em>Method</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Method</em>'.
	 * @generated
	 */
	Method createMethod();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	breezePackage getbreezePackage();

} //breezeFactory
