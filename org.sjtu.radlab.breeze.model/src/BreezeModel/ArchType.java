/**
 */
package BreezeModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Arch Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see BreezeModel.breezePackage#getArchType()
 * @model
 * @generated
 */
public enum ArchType implements Enumerator {
	/**
	 * The '<em><b>Arch Template</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ARCH_TEMPLATE_VALUE
	 * @generated
	 * @ordered
	 */
	ARCH_TEMPLATE(0, "ArchTemplate", "ARCHTEMPLATE"),

	/**
	 * The '<em><b>Arch Instance</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ARCH_INSTANCE_VALUE
	 * @generated
	 * @ordered
	 */
	ARCH_INSTANCE(1, "ArchInstance", "ARCHINSTANCE"),

	/**
	 * The '<em><b>Arch General</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ARCH_GENERAL_VALUE
	 * @generated
	 * @ordered
	 */
	ARCH_GENERAL(2, "ArchGeneral", "ARCHGENERAL");

	/**
	 * The '<em><b>Arch Template</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Arch Template</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ARCH_TEMPLATE
	 * @model name="ArchTemplate" literal="ARCHTEMPLATE"
	 * @generated
	 * @ordered
	 */
	public static final int ARCH_TEMPLATE_VALUE = 0;

	/**
	 * The '<em><b>Arch Instance</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Arch Instance</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ARCH_INSTANCE
	 * @model name="ArchInstance" literal="ARCHINSTANCE"
	 * @generated
	 * @ordered
	 */
	public static final int ARCH_INSTANCE_VALUE = 1;

	/**
	 * The '<em><b>Arch General</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Arch General</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ARCH_GENERAL
	 * @model name="ArchGeneral" literal="ARCHGENERAL"
	 * @generated
	 * @ordered
	 */
	public static final int ARCH_GENERAL_VALUE = 2;

	/**
	 * An array of all the '<em><b>Arch Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ArchType[] VALUES_ARRAY =
		new ArchType[] {
			ARCH_TEMPLATE,
			ARCH_INSTANCE,
			ARCH_GENERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Arch Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ArchType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Arch Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ArchType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ArchType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Arch Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ArchType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ArchType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Arch Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ArchType get(int value) {
		switch (value) {
			case ARCH_TEMPLATE_VALUE: return ARCH_TEMPLATE;
			case ARCH_INSTANCE_VALUE: return ARCH_INSTANCE;
			case ARCH_GENERAL_VALUE: return ARCH_GENERAL;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ArchType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
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
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //ArchType
