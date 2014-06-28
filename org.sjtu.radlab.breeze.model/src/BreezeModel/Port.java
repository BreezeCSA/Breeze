/**
 */
package BreezeModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Port#getType <em>Type</em>}</li>
 *   <li>{@link BreezeModel.Port#getDirection <em>Direction</em>}</li>
 *   <li>{@link BreezeModel.Port#getMultiaccess <em>Multiaccess</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getPort()
 * @model
 * @generated
 */
public interface Port extends ArchElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
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
	 * @see BreezeModel.breezePackage#getPort_Type()
	 * @model
	 * @generated
	 */
	AccessType getType();

	/**
	 * Sets the value of the '{@link BreezeModel.Port#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see BreezeModel.AccessType
	 * @see #getType()
	 * @generated
	 */
	void setType(AccessType value);

	/**
	 * Returns the value of the '<em><b>Direction</b></em>' attribute.
	 * The literals are from the enumeration {@link BreezeModel.DirectionType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direction</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direction</em>' attribute.
	 * @see BreezeModel.DirectionType
	 * @see #setDirection(DirectionType)
	 * @see BreezeModel.breezePackage#getPort_Direction()
	 * @model
	 * @generated
	 */
	DirectionType getDirection();

	/**
	 * Sets the value of the '{@link BreezeModel.Port#getDirection <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Direction</em>' attribute.
	 * @see BreezeModel.DirectionType
	 * @see #getDirection()
	 * @generated
	 */
	void setDirection(DirectionType value);

	/**
	 * Returns the value of the '<em><b>Multiaccess</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link BreezeModel.MultiAccess}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Multiaccess</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Multiaccess</em>' attribute.
	 * @see BreezeModel.MultiAccess
	 * @see #setMultiaccess(MultiAccess)
	 * @see BreezeModel.breezePackage#getPort_Multiaccess()
	 * @model default=""
	 * @generated
	 */
	MultiAccess getMultiaccess();

	/**
	 * Sets the value of the '{@link BreezeModel.Port#getMultiaccess <em>Multiaccess</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Multiaccess</em>' attribute.
	 * @see BreezeModel.MultiAccess
	 * @see #getMultiaccess()
	 * @generated
	 */
	void setMultiaccess(MultiAccess value);

} // Port
