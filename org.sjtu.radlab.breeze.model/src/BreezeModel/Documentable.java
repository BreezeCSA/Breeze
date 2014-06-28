/**
 */
package BreezeModel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Documentable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Documentable#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getDocumentable()
 * @model
 * @generated
 */
public interface Documentable extends EObject {
	/**
	 * Returns the value of the '<em><b>Documentation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Documentation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Documentation</em>' attribute.
	 * @see #setDocumentation(String)
	 * @see BreezeModel.breezePackage#getDocumentable_Documentation()
	 * @model
	 * @generated
	 */
	String getDocumentation();

	/**
	 * Sets the value of the '{@link BreezeModel.Documentable#getDocumentation <em>Documentation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Documentation</em>' attribute.
	 * @see #getDocumentation()
	 * @generated
	 */
	void setDocumentation(String value);

} // Documentable
