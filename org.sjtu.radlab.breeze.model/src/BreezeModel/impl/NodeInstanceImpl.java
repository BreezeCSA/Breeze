/**
 */
package BreezeModel.impl;

import BreezeModel.Availability;
import BreezeModel.NodeInstance;
import BreezeModel.NodeTemplate;
import BreezeModel.breezePackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node Instance</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link BreezeModel.impl.NodeInstanceImpl#getTR <em>TR</em>}</li>
 *   <li>{@link BreezeModel.impl.NodeInstanceImpl#getAvailability <em>Availability</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeInstanceImpl extends NodeImpl implements NodeInstance {
	/**
	 * The cached value of the '{@link #getTR() <em>TR</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTR()
	 * @generated
	 * @ordered
	 */
	protected NodeTemplate tr;

	/**
	 * The default value of the '{@link #getAvailability() <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailability()
	 * @generated
	 * @ordered
	 */
	protected static final Availability AVAILABILITY_EDEFAULT = Availability.UNDETERMINED;

	/**
	 * The cached value of the '{@link #getAvailability() <em>Availability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAvailability()
	 * @generated
	 * @ordered
	 */
	protected Availability availability = AVAILABILITY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodeInstanceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return breezePackage.Literals.NODE_INSTANCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeTemplate getTR() {
		if (tr != null && tr.eIsProxy()) {
			InternalEObject oldTR = (InternalEObject)tr;
			tr = (NodeTemplate)eResolveProxy(oldTR);
			if (tr != oldTR) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, breezePackage.NODE_INSTANCE__TR, oldTR, tr));
			}
		}
		return tr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodeTemplate basicGetTR() {
		return tr;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTR(NodeTemplate newTR) {
		NodeTemplate oldTR = tr;
		tr = newTR;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.NODE_INSTANCE__TR, oldTR, tr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Availability getAvailability() {
		return availability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAvailability(Availability newAvailability) {
		Availability oldAvailability = availability;
		availability = newAvailability == null ? AVAILABILITY_EDEFAULT : newAvailability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.NODE_INSTANCE__AVAILABILITY, oldAvailability, availability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case breezePackage.NODE_INSTANCE__TR:
				if (resolve) return getTR();
				return basicGetTR();
			case breezePackage.NODE_INSTANCE__AVAILABILITY:
				return getAvailability();
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
			case breezePackage.NODE_INSTANCE__TR:
				setTR((NodeTemplate)newValue);
				return;
			case breezePackage.NODE_INSTANCE__AVAILABILITY:
				setAvailability((Availability)newValue);
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
			case breezePackage.NODE_INSTANCE__TR:
				setTR((NodeTemplate)null);
				return;
			case breezePackage.NODE_INSTANCE__AVAILABILITY:
				setAvailability(AVAILABILITY_EDEFAULT);
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
			case breezePackage.NODE_INSTANCE__TR:
				return tr != null;
			case breezePackage.NODE_INSTANCE__AVAILABILITY:
				return availability != AVAILABILITY_EDEFAULT;
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
		result.append(" (availability: ");
		result.append(availability);
		result.append(')');
		return result.toString();
	}

} //NodeInstanceImpl
