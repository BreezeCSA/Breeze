/**
 */
package BreezeModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Attribute#getKey <em>Key</em>}</li>
 *   <li>{@link BreezeModel.Attribute#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends EObject {
	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * The default value is <code>"name"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see BreezeModel.breezePackage#getAttribute_Key()
	 * @model default="name"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link BreezeModel.Attribute#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * The default value is <code>"value"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see BreezeModel.breezePackage#getAttribute_Value()
	 * @model default="value"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link BreezeModel.Attribute#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // Attribute
