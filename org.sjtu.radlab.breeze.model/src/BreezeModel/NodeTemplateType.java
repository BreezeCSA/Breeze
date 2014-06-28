/**
 */
package BreezeModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Node Template Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see BreezeModel.breezePackage#getNodeTemplateType()
 * @model
 * @generated
 */
public enum NodeTemplateType implements Enumerator {
	/**
	 * The '<em><b>Component</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #COMPONENT_VALUE
	 * @generated
	 * @ordered
	 */
	COMPONENT(0, "Component", "COMPONENT"),

	/**
	 * The '<em><b>Connector</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONNECTOR_VALUE
	 * @generated
	 * @ordered
	 */
	CONNECTOR(0, "Connector", "CONNECTOR");

	/**
	 * The '<em><b>Component</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Component</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #COMPONENT
	 * @model name="Component" literal="COMPONENT"
	 * @generated
	 * @ordered
	 */
	public static final int COMPONENT_VALUE = 0;

	/**
	 * The '<em><b>Connector</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Connector</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONNECTOR
	 * @model name="Connector" literal="CONNECTOR"
	 * @generated
	 * @ordered
	 */
	public static final int CONNECTOR_VALUE = 0;

	/**
	 * An array of all the '<em><b>Node Template Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NodeTemplateType[] VALUES_ARRAY =
		new NodeTemplateType[] {
			COMPONENT,
			CONNECTOR,
		};

	/**
	 * A public read-only list of all the '<em><b>Node Template Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<NodeTemplateType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Node Template Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NodeTemplateType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NodeTemplateType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Node Template Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NodeTemplateType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NodeTemplateType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Node Template Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NodeTemplateType get(int value) {
		switch (value) {
			case COMPONENT_VALUE: return COMPONENT;
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
	private NodeTemplateType(int value, String name, String literal) {
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
	
} //NodeTemplateType
