/**
 */
package BreezeModel.impl;

import BreezeModel.Arch;
import BreezeModel.Breeze;
import BreezeModel.Style;
import BreezeModel.breezePackage;

import BreezeModel.goku;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Breeze</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link BreezeModel.impl.BreezeImpl#getArch <em>Arch</em>}</li>
 *   <li>{@link BreezeModel.impl.BreezeImpl#getStyle <em>Style</em>}</li>
 *   <li>{@link BreezeModel.impl.BreezeImpl#getGkeee <em>Gkeee</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BreezeImpl extends DocumentableImpl implements Breeze {
	/**
	 * The cached value of the '{@link #getArch() <em>Arch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArch()
	 * @generated
	 * @ordered
	 */
	protected Arch arch;

	/**
	 * The cached value of the '{@link #getStyle() <em>Style</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyle()
	 * @generated
	 * @ordered
	 */
	protected Style style;

	/**
	 * The cached value of the '{@link #getGkeee() <em>Gkeee</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getGkeee()
	 * @generated
	 * @ordered
	 */
	protected EList<goku> gkeee;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BreezeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return breezePackage.Literals.BREEZE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Arch getArch() {
		return arch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArch(Arch newArch, NotificationChain msgs) {
		Arch oldArch = arch;
		arch = newArch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, breezePackage.BREEZE__ARCH, oldArch, newArch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArch(Arch newArch) {
		if (newArch != arch) {
			NotificationChain msgs = null;
			if (arch != null)
				msgs = ((InternalEObject)arch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - breezePackage.BREEZE__ARCH, null, msgs);
			if (newArch != null)
				msgs = ((InternalEObject)newArch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - breezePackage.BREEZE__ARCH, null, msgs);
			msgs = basicSetArch(newArch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.BREEZE__ARCH, newArch, newArch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Style getStyle() {
		return style;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStyle(Style newStyle, NotificationChain msgs) {
		Style oldStyle = style;
		style = newStyle;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, breezePackage.BREEZE__STYLE, oldStyle, newStyle);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyle(Style newStyle) {
		if (newStyle != style) {
			NotificationChain msgs = null;
			if (style != null)
				msgs = ((InternalEObject)style).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - breezePackage.BREEZE__STYLE, null, msgs);
			if (newStyle != null)
				msgs = ((InternalEObject)newStyle).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - breezePackage.BREEZE__STYLE, null, msgs);
			msgs = basicSetStyle(newStyle, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.BREEZE__STYLE, newStyle, newStyle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<goku> getGkeee() {
		if (gkeee == null) {
			gkeee = new EObjectContainmentEList<goku>(goku.class, this, breezePackage.BREEZE__GKEEE);
		}
		return gkeee;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case breezePackage.BREEZE__ARCH:
				return basicSetArch(null, msgs);
			case breezePackage.BREEZE__STYLE:
				return basicSetStyle(null, msgs);
			case breezePackage.BREEZE__GKEEE:
				return ((InternalEList<?>)getGkeee()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case breezePackage.BREEZE__ARCH:
				return getArch();
			case breezePackage.BREEZE__STYLE:
				return getStyle();
			case breezePackage.BREEZE__GKEEE:
				return getGkeee();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case breezePackage.BREEZE__ARCH:
				setArch((Arch)newValue);
				return;
			case breezePackage.BREEZE__STYLE:
				setStyle((Style)newValue);
				return;
			case breezePackage.BREEZE__GKEEE:
				getGkeee().clear();
				getGkeee().addAll((Collection<? extends goku>)newValue);
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
			case breezePackage.BREEZE__ARCH:
				setArch((Arch)null);
				return;
			case breezePackage.BREEZE__STYLE:
				setStyle((Style)null);
				return;
			case breezePackage.BREEZE__GKEEE:
				getGkeee().clear();
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
			case breezePackage.BREEZE__ARCH:
				return arch != null;
			case breezePackage.BREEZE__STYLE:
				return style != null;
			case breezePackage.BREEZE__GKEEE:
				return gkeee != null && !gkeee.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //BreezeImpl
