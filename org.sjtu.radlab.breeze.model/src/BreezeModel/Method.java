/**
 */
package BreezeModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Method#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getMethod()
 * @model
 * @generated
 */
public interface Method extends EObject {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The default value is <code>"private"</code>.
	 * The literals are from the enumeration {@link BreezeModel.AccessType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see BreezeModel.AccessType
	 * @see #setType(AccessType)
	 * @see BreezeModel.breezePackage#getMethod_Type()
	 * @model default="private"
	 * @generated
	 */
	AccessType getType();

	/**
	 * Sets the value of the '{@link BreezeModel.Method#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see BreezeModel.AccessType
	 * @see #getType()
	 * @generated
	 */
	void setType(AccessType value);

} // Method
