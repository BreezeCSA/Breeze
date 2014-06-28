/**
 */
package BreezeModel;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Breeze</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Breeze#getArch <em>Arch</em>}</li>
 *   <li>{@link BreezeModel.Breeze#getStyle <em>Style</em>}</li>
 *   <li>{@link BreezeModel.Breeze#getGkeee <em>Gkeee</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getBreeze()
 * @model
 * @generated
 */
public interface Breeze extends Documentable {
	/**
	 * Returns the value of the '<em><b>Arch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arch</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arch</em>' containment reference.
	 * @see #setArch(Arch)
	 * @see BreezeModel.breezePackage#getBreeze_Arch()
	 * @model containment="true"
	 * @generated
	 */
	Arch getArch();

	/**
	 * Sets the value of the '{@link BreezeModel.Breeze#getArch <em>Arch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arch</em>' containment reference.
	 * @see #getArch()
	 * @generated
	 */
	void setArch(Arch value);

	/**
	 * Returns the value of the '<em><b>Style</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style</em>' containment reference.
	 * @see #setStyle(Style)
	 * @see BreezeModel.breezePackage#getBreeze_Style()
	 * @model containment="true"
	 * @generated
	 */
	Style getStyle();

	/**
	 * Sets the value of the '{@link BreezeModel.Breeze#getStyle <em>Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style</em>' containment reference.
	 * @see #getStyle()
	 * @generated
	 */
	void setStyle(Style value);

	/**
	 * Returns the value of the '<em><b>Gkeee</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.goku}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Gkeee</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Gkeee</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getBreeze_Gkeee()
	 * @model containment="true"
	 * @generated
	 */
	EList<goku> getGkeee();

} // Breeze
