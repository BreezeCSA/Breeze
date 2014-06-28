/**
 */
package BreezeModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Relation Ship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.RelationShip#getSource <em>Source</em>}</li>
 *   <li>{@link BreezeModel.RelationShip#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getRelationShip()
 * @model
 * @generated
 */
public interface RelationShip extends ArchElement {
	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(ArchElement)
	 * @see BreezeModel.breezePackage#getRelationShip_Source()
	 * @model
	 * @generated
	 */
	ArchElement getSource();

	/**
	 * Sets the value of the '{@link BreezeModel.RelationShip#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(ArchElement value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(ArchElement)
	 * @see BreezeModel.breezePackage#getRelationShip_Target()
	 * @model
	 * @generated
	 */
	ArchElement getTarget();

	/**
	 * Sets the value of the '{@link BreezeModel.RelationShip#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(ArchElement value);

} // RelationShip
