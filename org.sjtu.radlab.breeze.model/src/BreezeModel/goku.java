/**
 */
package BreezeModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>goku</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.goku#getPower <em>Power</em>}</li>
 *   <li>{@link BreezeModel.goku#getWin <em>Win</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getgoku()
 * @model
 * @generated
 */
public interface goku extends Documentable {
	/**
	 * Returns the value of the '<em><b>Power</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Power</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Power</em>' attribute.
	 * @see #setPower(float)
	 * @see BreezeModel.breezePackage#getgoku_Power()
	 * @model
	 * @generated
	 */
	float getPower();

	/**
	 * Sets the value of the '{@link BreezeModel.goku#getPower <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Power</em>' attribute.
	 * @see #getPower()
	 * @generated
	 */
	void setPower(float value);

	/**
	 * Returns the value of the '<em><b>Win</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Win</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Win</em>' attribute.
	 * @see #setWin(int)
	 * @see BreezeModel.breezePackage#getgoku_Win()
	 * @model
	 * @generated
	 */
	int getWin();

	/**
	 * Sets the value of the '{@link BreezeModel.goku#getWin <em>Win</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Win</em>' attribute.
	 * @see #getWin()
	 * @generated
	 */
	void setWin(int value);

} // goku
