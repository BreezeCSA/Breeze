/**
 */
package BreezeModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Production</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Production#getLeft <em>Left</em>}</li>
 *   <li>{@link BreezeModel.Production#getRight <em>Right</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getProduction()
 * @model
 * @generated
 */
public interface Production extends ArchElement {
	/**
	 * Returns the value of the '<em><b>Left</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Left</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Left</em>' containment reference.
	 * @see #setLeft(Arch)
	 * @see BreezeModel.breezePackage#getProduction_Left()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Arch getLeft();

	/**
	 * Sets the value of the '{@link BreezeModel.Production#getLeft <em>Left</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Left</em>' containment reference.
	 * @see #getLeft()
	 * @generated
	 */
	void setLeft(Arch value);

	/**
	 * Returns the value of the '<em><b>Right</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Right</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Right</em>' containment reference.
	 * @see #setRight(Arch)
	 * @see BreezeModel.breezePackage#getProduction_Right()
	 * @model containment="true" required="true"
	 * @generated
	 */
	Arch getRight();

	/**
	 * Sets the value of the '{@link BreezeModel.Production#getRight <em>Right</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Right</em>' containment reference.
	 * @see #getRight()
	 * @generated
	 */
	void setRight(Arch value);

} // Production
