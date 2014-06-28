/**
 */
package BreezeModel;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Template</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Template#getNode <em>Node</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getTemplate()
 * @model
 * @generated
 */
public interface Template extends ArchElement {
	/**
	 * Returns the value of the '<em><b>Node</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.NodeTemplate}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Node</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Node</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getTemplate_Node()
	 * @model containment="true"
	 * @generated
	 */
	EList<NodeTemplate> getNode();

} // Template
