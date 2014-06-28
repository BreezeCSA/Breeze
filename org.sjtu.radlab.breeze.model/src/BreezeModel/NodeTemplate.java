/**
 */
package BreezeModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.NodeTemplate#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getNodeTemplate()
 * @model
 * @generated
 */
public interface NodeTemplate extends Node {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link BreezeModel.NodeTemplateType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see BreezeModel.NodeTemplateType
	 * @see #setType(NodeTemplateType)
	 * @see BreezeModel.breezePackage#getNodeTemplate_Type()
	 * @model
	 * @generated
	 */
	NodeTemplateType getType();

	/**
	 * Sets the value of the '{@link BreezeModel.NodeTemplate#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see BreezeModel.NodeTemplateType
	 * @see #getType()
	 * @generated
	 */
	void setType(NodeTemplateType value);

} // NodeTemplate
