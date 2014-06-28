/**
 */
package BreezeModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arch</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Arch#getNode <em>Node</em>}</li>
 *   <li>{@link BreezeModel.Arch#getEdge <em>Edge</em>}</li>
 *   <li>{@link BreezeModel.Arch#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getArch()
 * @model
 * @generated
 */
public interface Arch extends Documentable, Nameable {
	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getArch_Node()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getNode();

	/**
	 * Returns the value of the '<em><b>Edge</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.Link}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edge</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edge</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getArch_Edge()
	 * @model containment="true"
	 * @generated
	 */
	EList<Link> getEdge();

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link BreezeModel.ArchType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see BreezeModel.ArchType
	 * @see #setType(ArchType)
	 * @see BreezeModel.breezePackage#getArch_Type()
	 * @model
	 * @generated
	 */
	ArchType getType();

	/**
	 * Sets the value of the '{@link BreezeModel.Arch#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see BreezeModel.ArchType
	 * @see #getType()
	 * @generated
	 */
	void setType(ArchType value);

} // Arch
