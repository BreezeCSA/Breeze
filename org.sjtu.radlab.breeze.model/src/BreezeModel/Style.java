/**
 */
package BreezeModel;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Style</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link BreezeModel.Style#getArch <em>Arch</em>}</li>
 *   <li>{@link BreezeModel.Style#getProduction <em>Production</em>}</li>
 *   <li>{@link BreezeModel.Style#getStyleType <em>Style Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see BreezeModel.breezePackage#getStyle()
 * @model
 * @generated
 */
public interface Style extends Documentable, Nameable {
	/**
	 * Returns the value of the '<em><b>Arch</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Arch</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Arch</em>' containment reference.
	 * @see #setArch(Template)
	 * @see BreezeModel.breezePackage#getStyle_Arch()
	 * @model containment="true"
	 * @generated
	 */
	Template getArch();

	/**
	 * Sets the value of the '{@link BreezeModel.Style#getArch <em>Arch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Arch</em>' containment reference.
	 * @see #getArch()
	 * @generated
	 */
	void setArch(Template value);

	/**
	 * Returns the value of the '<em><b>Production</b></em>' containment reference list.
	 * The list contents are of type {@link BreezeModel.Production}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Production</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Production</em>' containment reference list.
	 * @see BreezeModel.breezePackage#getStyle_Production()
	 * @model containment="true"
	 * @generated
	 */
	EList<Production> getProduction();

	/**
	 * Returns the value of the '<em><b>Style Type</b></em>' attribute.
	 * The literals are from the enumeration {@link BreezeModel.StyleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Style Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Style Type</em>' attribute.
	 * @see BreezeModel.StyleType
	 * @see #setStyleType(StyleType)
	 * @see BreezeModel.breezePackage#getStyle_StyleType()
	 * @model
	 * @generated
	 */
	StyleType getStyleType();

	/**
	 * Sets the value of the '{@link BreezeModel.Style#getStyleType <em>Style Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Style Type</em>' attribute.
	 * @see BreezeModel.StyleType
	 * @see #getStyleType()
	 * @generated
	 */
	void setStyleType(StyleType value);

} // Style
