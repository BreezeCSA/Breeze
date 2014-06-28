/**
 */
package BreezeModel.impl;

import BreezeModel.Nameable;
import BreezeModel.Production;
import BreezeModel.Style;
import BreezeModel.StyleType;
import BreezeModel.Template;
import BreezeModel.breezePackage;
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
 * An implementation of the model object '<em><b>Style</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link BreezeModel.impl.StyleImpl#getName <em>Name</em>}</li>
 *   <li>{@link BreezeModel.impl.StyleImpl#getArch <em>Arch</em>}</li>
 *   <li>{@link BreezeModel.impl.StyleImpl#getProduction <em>Production</em>}</li>
 *   <li>{@link BreezeModel.impl.StyleImpl#getStyleType <em>Style Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StyleImpl extends DocumentableImpl implements Style {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getArch() <em>Arch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getArch()
	 * @generated
	 * @ordered
	 */
	protected Template arch;

	/**
	 * The cached value of the '{@link #getProduction() <em>Production</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProduction()
	 * @generated
	 * @ordered
	 */
	protected EList<Production> production;

	/**
	 * The default value of the '{@link #getStyleType() <em>Style Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleType()
	 * @generated
	 * @ordered
	 */
	protected static final StyleType STYLE_TYPE_EDEFAULT = StyleType.CS;

	/**
	 * The cached value of the '{@link #getStyleType() <em>Style Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStyleType()
	 * @generated
	 * @ordered
	 */
	protected StyleType styleType = STYLE_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StyleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return breezePackage.Literals.STYLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.STYLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Template getArch() {
		return arch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetArch(Template newArch, NotificationChain msgs) {
		Template oldArch = arch;
		arch = newArch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, breezePackage.STYLE__ARCH, oldArch, newArch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setArch(Template newArch) {
		if (newArch != arch) {
			NotificationChain msgs = null;
			if (arch != null)
				msgs = ((InternalEObject)arch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - breezePackage.STYLE__ARCH, null, msgs);
			if (newArch != null)
				msgs = ((InternalEObject)newArch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - breezePackage.STYLE__ARCH, null, msgs);
			msgs = basicSetArch(newArch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.STYLE__ARCH, newArch, newArch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Production> getProduction() {
		if (production == null) {
			production = new EObjectContainmentEList<Production>(Production.class, this, breezePackage.STYLE__PRODUCTION);
		}
		return production;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StyleType getStyleType() {
		return styleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStyleType(StyleType newStyleType) {
		StyleType oldStyleType = styleType;
		styleType = newStyleType == null ? STYLE_TYPE_EDEFAULT : newStyleType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, breezePackage.STYLE__STYLE_TYPE, oldStyleType, styleType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case breezePackage.STYLE__ARCH:
				return basicSetArch(null, msgs);
			case breezePackage.STYLE__PRODUCTION:
				return ((InternalEList<?>)getProduction()).basicRemove(otherEnd, msgs);
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
			case breezePackage.STYLE__NAME:
				return getName();
			case breezePackage.STYLE__ARCH:
				return getArch();
			case breezePackage.STYLE__PRODUCTION:
				return getProduction();
			case breezePackage.STYLE__STYLE_TYPE:
				return getStyleType();
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
			case breezePackage.STYLE__NAME:
				setName((String)newValue);
				return;
			case breezePackage.STYLE__ARCH:
				setArch((Template)newValue);
				return;
			case breezePackage.STYLE__PRODUCTION:
				getProduction().clear();
				getProduction().addAll((Collection<? extends Production>)newValue);
				return;
			case breezePackage.STYLE__STYLE_TYPE:
				setStyleType((StyleType)newValue);
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
			case breezePackage.STYLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case breezePackage.STYLE__ARCH:
				setArch((Template)null);
				return;
			case breezePackage.STYLE__PRODUCTION:
				getProduction().clear();
				return;
			case breezePackage.STYLE__STYLE_TYPE:
				setStyleType(STYLE_TYPE_EDEFAULT);
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
			case breezePackage.STYLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case breezePackage.STYLE__ARCH:
				return arch != null;
			case breezePackage.STYLE__PRODUCTION:
				return production != null && !production.isEmpty();
			case breezePackage.STYLE__STYLE_TYPE:
				return styleType != STYLE_TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Nameable.class) {
			switch (derivedFeatureID) {
				case breezePackage.STYLE__NAME: return breezePackage.NAMEABLE__NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Nameable.class) {
			switch (baseFeatureID) {
				case breezePackage.NAMEABLE__NAME: return breezePackage.STYLE__NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (name: ");
		result.append(name);
		result.append(", StyleType: ");
		result.append(styleType);
		result.append(')');
		return result.toString();
	}

} //StyleImpl
