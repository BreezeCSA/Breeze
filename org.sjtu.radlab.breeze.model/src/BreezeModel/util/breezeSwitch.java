/**
 */
package BreezeModel.util;

import BreezeModel.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see BreezeModel.breezePackage
 * @generated
 */
public class breezeSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static breezePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public breezeSwitch() {
		if (modelPackage == null) {
			modelPackage = breezePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case breezePackage.BREEZE: {
				Breeze breeze = (Breeze)theEObject;
				T result = caseBreeze(breeze);
				if (result == null) result = caseDocumentable(breeze);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.ATTRIBUTE: {
				Attribute attribute = (Attribute)theEObject;
				T result = caseAttribute(attribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.PORT: {
				Port port = (Port)theEObject;
				T result = casePort(port);
				if (result == null) result = caseArchElement(port);
				if (result == null) result = caseNameable(port);
				if (result == null) result = caseDocumentable(port);
				if (result == null) result = caseAttributes(port);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.STYLE: {
				Style style = (Style)theEObject;
				T result = caseStyle(style);
				if (result == null) result = caseDocumentable(style);
				if (result == null) result = caseNameable(style);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.ARCH: {
				Arch arch = (Arch)theEObject;
				T result = caseArch(arch);
				if (result == null) result = caseDocumentable(arch);
				if (result == null) result = caseNameable(arch);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.NODE: {
				Node node = (Node)theEObject;
				T result = caseNode(node);
				if (result == null) result = caseArchElement(node);
				if (result == null) result = caseNameable(node);
				if (result == null) result = caseDocumentable(node);
				if (result == null) result = caseAttributes(node);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.LINK: {
				Link link = (Link)theEObject;
				T result = caseLink(link);
				if (result == null) result = caseRelationShip(link);
				if (result == null) result = caseArchElement(link);
				if (result == null) result = caseNameable(link);
				if (result == null) result = caseDocumentable(link);
				if (result == null) result = caseAttributes(link);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.ATTRIBUTES: {
				Attributes attributes = (Attributes)theEObject;
				T result = caseAttributes(attributes);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.NAMEABLE: {
				Nameable nameable = (Nameable)theEObject;
				T result = caseNameable(nameable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.DOCUMENTABLE: {
				Documentable documentable = (Documentable)theEObject;
				T result = caseDocumentable(documentable);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.ARCH_ELEMENT: {
				ArchElement archElement = (ArchElement)theEObject;
				T result = caseArchElement(archElement);
				if (result == null) result = caseNameable(archElement);
				if (result == null) result = caseDocumentable(archElement);
				if (result == null) result = caseAttributes(archElement);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.COMPONENT: {
				Component component = (Component)theEObject;
				T result = caseComponent(component);
				if (result == null) result = caseNodeInstance(component);
				if (result == null) result = caseNode(component);
				if (result == null) result = caseArchElement(component);
				if (result == null) result = caseNameable(component);
				if (result == null) result = caseDocumentable(component);
				if (result == null) result = caseAttributes(component);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.CONNECTOR: {
				Connector connector = (Connector)theEObject;
				T result = caseConnector(connector);
				if (result == null) result = caseNodeInstance(connector);
				if (result == null) result = caseNode(connector);
				if (result == null) result = caseArchElement(connector);
				if (result == null) result = caseNameable(connector);
				if (result == null) result = caseDocumentable(connector);
				if (result == null) result = caseAttributes(connector);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.RELATION_SHIP: {
				RelationShip relationShip = (RelationShip)theEObject;
				T result = caseRelationShip(relationShip);
				if (result == null) result = caseArchElement(relationShip);
				if (result == null) result = caseNameable(relationShip);
				if (result == null) result = caseDocumentable(relationShip);
				if (result == null) result = caseAttributes(relationShip);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.NODE_INSTANCE: {
				NodeInstance nodeInstance = (NodeInstance)theEObject;
				T result = caseNodeInstance(nodeInstance);
				if (result == null) result = caseNode(nodeInstance);
				if (result == null) result = caseArchElement(nodeInstance);
				if (result == null) result = caseNameable(nodeInstance);
				if (result == null) result = caseDocumentable(nodeInstance);
				if (result == null) result = caseAttributes(nodeInstance);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.NODE_TEMPLATE: {
				NodeTemplate nodeTemplate = (NodeTemplate)theEObject;
				T result = caseNodeTemplate(nodeTemplate);
				if (result == null) result = caseNode(nodeTemplate);
				if (result == null) result = caseArchElement(nodeTemplate);
				if (result == null) result = caseNameable(nodeTemplate);
				if (result == null) result = caseDocumentable(nodeTemplate);
				if (result == null) result = caseAttributes(nodeTemplate);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.TEMPLATE: {
				Template template = (Template)theEObject;
				T result = caseTemplate(template);
				if (result == null) result = caseArchElement(template);
				if (result == null) result = caseNameable(template);
				if (result == null) result = caseDocumentable(template);
				if (result == null) result = caseAttributes(template);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.PRODUCTION: {
				Production production = (Production)theEObject;
				T result = caseProduction(production);
				if (result == null) result = caseArchElement(production);
				if (result == null) result = caseNameable(production);
				if (result == null) result = caseDocumentable(production);
				if (result == null) result = caseAttributes(production);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.GOKU: {
				goku goku = (goku)theEObject;
				T result = casegoku(goku);
				if (result == null) result = caseDocumentable(goku);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case breezePackage.METHOD: {
				Method method = (Method)theEObject;
				T result = caseMethod(method);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Breeze</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Breeze</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBreeze(Breeze object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(Attribute object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Port</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Port</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePort(Port object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Style</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Style</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStyle(Style object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arch</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arch</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArch(Arch object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNode(Node object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Link</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLink(Link object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attributes</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attributes</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributes(Attributes object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNameable(Nameable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documentable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documentable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentable(Documentable object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Arch Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Arch Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseArchElement(ArchElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComponent(Component object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connector</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConnector(Connector object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Relation Ship</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Relation Ship</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRelationShip(RelationShip object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node Instance</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Instance</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodeInstance(NodeInstance object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Node Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNodeTemplate(NodeTemplate object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplate(Template object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Production</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Production</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseProduction(Production object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>goku</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>goku</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casegoku(goku object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Method</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Method</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMethod(Method object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //breezeSwitch
