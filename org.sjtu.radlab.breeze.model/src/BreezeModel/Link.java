/**
 */
package BreezeModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Link#isDirected <em>Directed</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getLink()
 * @model
 * @generated
 */
public interface Link extends RelationShip {
	/**
	 * Returns the value of the '<em><b>Directed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Directed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Directed</em>' attribute.
	 * @see #setDirected(boolean)
	 * @see BreezeModel.breezePackage#getLink_Directed()
	 * @model
	 * @generated
	 */
	boolean isDirected();

	/**
	 * Sets the value of the '{@link BreezeModel.Link#isDirected <em>Directed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Directed</em>' attribute.
	 * @see #isDirected()
	 * @generated
	 */
	void setDirected(boolean value);

} // Link
