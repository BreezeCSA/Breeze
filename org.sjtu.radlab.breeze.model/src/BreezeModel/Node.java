/**
 */
package BreezeModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Node#getPort <em>Port</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getNode()
 * @model
 * @generated
 */
public interface Node extends ArchElement {
	/**
	 * Returns the value of the '<em><b>Port</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.Port}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Port</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Port</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getNode_Port()
	 * @model containment="true"
	 * @generated
	 */
	EList<Port> getPort();

} // Node
