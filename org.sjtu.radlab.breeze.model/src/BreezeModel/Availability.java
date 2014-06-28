/**
 */
package BreezeModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Availability</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see BreezeModel.breezePackage#getAvailability()
 * @model
 * @generated
 */
public enum Availability implements Enumerator {
	/**
	 * The '<em><b>Undetermined</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNDETERMINED_VALUE
	 * @generated
	 * @ordered
	 */
	UNDETERMINED(0, "Undetermined", "Undetermined"),

	/**
	 * The '<em><b>Active</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ACTIVE_VALUE
	 * @generated
	 * @ordered
	 */
	ACTIVE(1, "Active", "Active"),

	/**
	 * The '<em><b>Idel</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IDEL_VALUE
	 * @generated
	 * @ordered
	 */
	IDEL(2, "Idel", "Idel"),

	/**
	 * The '<em><b>Unavailable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNAVAILABLE_VALUE
	 * @generated
	 * @ordered
	 */
	UNAVAILABLE(3, "Unavailable", "Unavailable");

	/**
	 * The '<em><b>Undetermined</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Undetermined</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNDETERMINED
	 * @model name="Undetermined"
	 * @generated
	 * @ordered
	 */
	public static final int UNDETERMINED_VALUE = 0;

	/**
	 * The '<em><b>Active</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Active</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ACTIVE
	 * @model name="Active"
	 * @generated
	 * @ordered
	 */
	public static final int ACTIVE_VALUE = 1;

	/**
	 * The '<em><b>Idel</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Idel</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IDEL
	 * @model name="Idel"
	 * @generated
	 * @ordered
	 */
	public static final int IDEL_VALUE = 2;

	/**
	 * The '<em><b>Unavailable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unavailable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNAVAILABLE
	 * @model name="Unavailable"
	 * @generated
	 * @ordered
	 */
	public static final int UNAVAILABLE_VALUE = 3;

	/**
	 * An array of all the '<em><b>Availability</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Availability[] VALUES_ARRAY =
		new Availability[] {
			UNDETERMINED,
			ACTIVE,
			IDEL,
			UNAVAILABLE,
		};

	/**
	 * A public read-only list of all the '<em><b>Availability</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Availability> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Availability</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Availability get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Availability result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Availability</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Availability getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Availability result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Availability</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Availability get(int value) {
		switch (value) {
			case UNDETERMINED_VALUE: return UNDETERMINED;
			case ACTIVE_VALUE: return ACTIVE;
			case IDEL_VALUE: return IDEL;
			case UNAVAILABLE_VALUE: return UNAVAILABLE;
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
	private Availability(int value, String name, String literal) {
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
	
} //Availability
