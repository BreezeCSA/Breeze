/**
 */
package BreezeModel.impl;

import BreezeModel.Link;
import BreezeModel.breezePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link BreezeModel.impl.LinkImpl#isDirected <em>Directed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkImpl extends RelationShipImpl implements Link {
	/**
	 * The default value of the '{@link #isDirected() <em>Directed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirected()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DIRECTED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDirected() <em>Directed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirected()
	 * @generated
	 * @ordered
	 */
	protected boolean directed = DIRECTED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return breezePackage.Literals.LINK;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDirected() {
		return directed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirected(boolean newDirected) {
		boolean oldDirected = directed;
		directed = newDirected;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.LINK__DIRECTED, oldDirected, directed));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case breezePackage.LINK__DIRECTED:
				return isDirected();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case breezePackage.LINK__DIRECTED:
				setDirected((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case breezePackage.LINK__DIRECTED:
				setDirected(DIRECTED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case breezePackage.LINK__DIRECTED:
				return directed != DIRECTED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (directed: ");
		result.append(directed);
		result.append(')');
		return result.toString();
	}

} //LinkImpl
