/**
 */
package BreezeModel.impl;

import BreezeModel.breezePackage;
import BreezeModel.goku;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>goku</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link BreezeModel.impl.gokuImpl#getPower <em>Power</em>}</li>
 *   <li>{@link BreezeModel.impl.gokuImpl#getWin <em>Win</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class gokuImpl extends DocumentableImpl implements goku {
	/**
	 * The default value of the '{@link #getPower() <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPower()
	 * @generated
	 * @ordered
	 */
	protected static final float POWER_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getPower() <em>Power</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPower()
	 * @generated
	 * @ordered
	 */
	protected float power = POWER_EDEFAULT;

	/**
	 * The default value of the '{@link #getWin() <em>Win</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWin()
	 * @generated
	 * @ordered
	 */
	protected static final int WIN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getWin() <em>Win</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWin()
	 * @generated
	 * @ordered
	 */
	protected int win = WIN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected gokuImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return breezePackage.Literals.GOKU;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getPower() {
		return power;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPower(float newPower) {
		float oldPower = power;
		power = newPower;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.GOKU__POWER, oldPower, power));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getWin() {
		return win;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWin(int newWin) {
		int oldWin = win;
		win = newWin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.GOKU__WIN, oldWin, win));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case breezePackage.GOKU__POWER:
				return getPower();
			case breezePackage.GOKU__WIN:
				return getWin();
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
			case breezePackage.GOKU__POWER:
				setPower((Float)newValue);
				return;
			case breezePackage.GOKU__WIN:
				setWin((Integer)newValue);
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
			case breezePackage.GOKU__POWER:
				setPower(POWER_EDEFAULT);
				return;
			case breezePackage.GOKU__WIN:
				setWin(WIN_EDEFAULT);
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
			case breezePackage.GOKU__POWER:
				return power != POWER_EDEFAULT;
			case breezePackage.GOKU__WIN:
				return win != WIN_EDEFAULT;
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
		result.append(" (power: ");
		result.append(power);
		result.append(", win: ");
		result.append(win);
		result.append(')');
		return result.toString();
	}

} //gokuImpl
