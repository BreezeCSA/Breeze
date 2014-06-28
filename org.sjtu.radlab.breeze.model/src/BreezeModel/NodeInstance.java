/**
 */
package BreezeModel;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.NodeInstance#getTR <em>TR</em>}</li>
 *   <li>{@link BreezeModel.NodeInstance#getAvailability <em>Availability</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getNodeInstance()
 * @model
 * @generated
 */
public interface NodeInstance extends Node {
	/**
	 * Returns the value of the '<em><b>TR</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>TR</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>TR</em>' reference.
	 * @see #setTR(NodeTemplate)
	 * @see BreezeModel.breezePackage#getNodeInstance_TR()
	 * @model
	 * @generated
	 */
	NodeTemplate getTR();

	/**
	 * Sets the value of the '{@link BreezeModel.NodeInstance#getTR <em>TR</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>TR</em>' reference.
	 * @see #getTR()
	 * @generated
	 */
	void setTR(NodeTemplate value);

	/**
	 * Returns the value of the '<em><b>Availability</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * The literals are from the enumeration {@link BreezeModel.Availability}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Availability</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Availability</em>' attribute.
	 * @see BreezeModel.Availability
	 * @see #setAvailability(Availability)
	 * @see BreezeModel.breezePackage#getNodeInstance_Availability()
	 * @model default=""
	 * @generated
	 */
	Availability getAvailability();

	/**
	 * Sets the value of the '{@link BreezeModel.NodeInstance#getAvailability <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Availability</em>' attribute.
	 * @see BreezeModel.Availability
	 * @see #getAvailability()
	 * @generated
	 */
	void setAvailability(Availability value);

} // NodeInstance
