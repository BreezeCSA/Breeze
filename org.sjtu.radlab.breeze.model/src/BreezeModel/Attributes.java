/**
 */
package BreezeModel;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attributes</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Attributes#getAttribute <em>Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getAttributes()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Attributes extends EObject {
	/**
	 * Returns the value of the '<em><b>Attribute</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.Attribute}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attribute</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attribute</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getAttributes_Attribute()
	 * @model containment="true"
	 * @generated
	 */
	EList<Attribute> getAttribute();

} // Attributes
