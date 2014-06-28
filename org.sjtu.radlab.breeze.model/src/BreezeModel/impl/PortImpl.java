/**
 */
package BreezeModel.impl;

import BreezeModel.AccessType;
import BreezeModel.DirectionType;
import BreezeModel.MultiAccess;
import BreezeModel.Port;
import BreezeModel.breezePackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link BreezeModel.impl.PortImpl#getType <em>Type</em>}</li>
 *   <li>{@link BreezeModel.impl.PortImpl#getDirection <em>Direction</em>}</li>
 *   <li>{@link BreezeModel.impl.PortImpl#getMultiaccess <em>Multiaccess</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortImpl extends ArchElementImpl implements Port {
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final AccessType TYPE_EDEFAULT = AccessType.PUBLIC;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected AccessType type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected static final DirectionType DIRECTION_EDEFAULT = DirectionType.IN;

	/**
	 * The cached value of the '{@link #getDirection() <em>Direction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDirection()
	 * @generated
	 * @ordered
	 */
	protected DirectionType direction = DIRECTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getMultiaccess() <em>Multiaccess</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiaccess()
	 * @generated
	 * @ordered
	 */
	protected static final MultiAccess MULTIACCESS_EDEFAULT = MultiAccess.YES;

	/**
	 * The cached value of the '{@link #getMultiaccess() <em>Multiaccess</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMultiaccess()
	 * @generated
	 * @ordered
	 */
	protected MultiAccess multiaccess = MULTIACCESS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return breezePackage.Literals.PORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AccessType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(AccessType newType) {
		AccessType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.PORT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DirectionType getDirection() {
		return direction;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDirection(DirectionType newDirection) {
		DirectionType oldDirection = direction;
		direction = newDirection == null ? DIRECTION_EDEFAULT : newDirection;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.PORT__DIRECTION, oldDirection, direction));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MultiAccess getMultiaccess() {
		return multiaccess;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiaccess(MultiAccess newMultiaccess) {
		MultiAccess oldMultiaccess = multiaccess;
		multiaccess = newMultiaccess == null ? MULTIACCESS_EDEFAULT : newMultiaccess;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.PORT__MULTIACCESS, oldMultiaccess, multiaccess));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case breezePackage.PORT__TYPE:
				return getType();
			case breezePackage.PORT__DIRECTION:
				return getDirection();
			case breezePackage.PORT__MULTIACCESS:
				return getMultiaccess();
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
			case breezePackage.PORT__TYPE:
				setType((AccessType)newValue);
				return;
			case breezePackage.PORT__DIRECTION:
				setDirection((DirectionType)newValue);
				return;
			case breezePackage.PORT__MULTIACCESS:
				setMultiaccess((MultiAccess)newValue);
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
			case breezePackage.PORT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case breezePackage.PORT__DIRECTION:
				setDirection(DIRECTION_EDEFAULT);
				return;
			case breezePackage.PORT__MULTIACCESS:
				setMultiaccess(MULTIACCESS_EDEFAULT);
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
			case breezePackage.PORT__TYPE:
				return type != TYPE_EDEFAULT;
			case breezePackage.PORT__DIRECTION:
				return direction != DIRECTION_EDEFAULT;
			case breezePackage.PORT__MULTIACCESS:
				return multiaccess != MULTIACCESS_EDEFAULT;
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
		result.append(" (type: ");
		result.append(type);
		result.append(", direction: ");
		result.append(direction);
		result.append(", multiaccess: ");
		result.append(multiaccess);
		result.append(')');
		return result.toString();
	}

} //PortImpl
