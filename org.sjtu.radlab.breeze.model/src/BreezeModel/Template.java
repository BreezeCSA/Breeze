/**
 */
package BreezeModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Template#getNode <em>Node</em>}</li>
 *   <li>{@link BreezeModel.Template#getStyleType <em>Style Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getTemplate()
 * @model
 * @generated
 */
public interface Template extends ArchElement {
	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.NodeTemplate}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getTemplate_Node()
	 * @model containment="true"
	 * @generated
	 */
	EList<NodeTemplate> getNode();

	/**
	 * Returns the value of the '<em><b>Style Type</b></em>' attribute.
	 * The literals are from the enumeration {@link BreezeModel.StyleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style Type</em>' attribute.
	 * @see BreezeModel.StyleType
	 * @see #setStyleType(StyleType)
	 * @see BreezeModel.breezePackage#getTemplate_StyleType()
	 * @model
	 * @generated
	 */
	StyleType getStyleType();

	/**
	 * Sets the value of the '{@link BreezeModel.Template#getStyleType <em>Style Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style Type</em>' attribute.
	 * @see BreezeModel.StyleType
	 * @see #getStyleType()
	 * @generated
	 */
	void setStyleType(StyleType value);

} // Template
