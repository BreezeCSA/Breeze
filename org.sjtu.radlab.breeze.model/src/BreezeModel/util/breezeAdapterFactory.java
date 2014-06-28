/**
 */
package BreezeModel.util;

import BreezeModel.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see BreezeModel.breezePackage
 * @generated
 */
public class breezeAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static breezePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public breezeAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = breezePackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected breezeSwitch<Adapter> modelSwitch =
		new breezeSwitch<Adapter>() {
			@Override
			public Adapter caseBreeze(Breeze object) {
				return createBreezeAdapter();
			}
			@Override
			public Adapter caseAttribute(Attribute object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter casePort(Port object) {
				return createPortAdapter();
			}
			@Override
			public Adapter caseStyle(Style object) {
				return createStyleAdapter();
			}
			@Override
			public Adapter caseArch(Arch object) {
				return createArchAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseLink(Link object) {
				return createLinkAdapter();
			}
			@Override
			public Adapter caseAttributes(Attributes object) {
				return createAttributesAdapter();
			}
			@Override
			public Adapter caseNameable(Nameable object) {
				return createNameableAdapter();
			}
			@Override
			public Adapter caseDocumentable(Documentable object) {
				return createDocumentableAdapter();
			}
			@Override
			public Adapter caseArchElement(ArchElement object) {
				return createArchElementAdapter();
			}
			@Override
			public Adapter caseComponent(Component object) {
				return createComponentAdapter();
			}
			@Override
			public Adapter caseConnector(Connector object) {
				return createConnectorAdapter();
			}
			@Override
			public Adapter caseRelationShip(RelationShip object) {
				return createRelationShipAdapter();
			}
			@Override
			public Adapter caseNodeInstance(NodeInstance object) {
				return createNodeInstanceAdapter();
			}
			@Override
			public Adapter caseNodeTemplate(NodeTemplate object) {
				return createNodeTemplateAdapter();
			}
			@Override
			public Adapter caseTemplate(Template object) {
				return createTemplateAdapter();
			}
			@Override
			public Adapter caseProduction(Production object) {
				return createProductionAdapter();
			}
			@Override
			public Adapter casegoku(goku object) {
				return creategokuAdapter();
			}
			@Override
			public Adapter caseMethod(Method object) {
				return createMethodAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Breeze <em>Breeze</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Breeze
	 * @generated
	 */
	public Adapter createBreezeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Attribute <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Attribute
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Port <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Port
	 * @generated
	 */
	public Adapter createPortAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Style <em>Style</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Style
	 * @generated
	 */
	public Adapter createStyleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Arch <em>Arch</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Arch
	 * @generated
	 */
	public Adapter createArchAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Link <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Link
	 * @generated
	 */
	public Adapter createLinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Attributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Attributes
	 * @generated
	 */
	public Adapter createAttributesAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Nameable
	 * @generated
	 */
	public Adapter createNameableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Documentable <em>Documentable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Documentable
	 * @generated
	 */
	public Adapter createDocumentableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.ArchElement <em>Arch Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.ArchElement
	 * @generated
	 */
	public Adapter createArchElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Component
	 * @generated
	 */
	public Adapter createComponentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Connector <em>Connector</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Connector
	 * @generated
	 */
	public Adapter createConnectorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.RelationShip <em>Relation Ship</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.RelationShip
	 * @generated
	 */
	public Adapter createRelationShipAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.NodeInstance <em>Node Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.NodeInstance
	 * @generated
	 */
	public Adapter createNodeInstanceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.NodeTemplate <em>Node Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.NodeTemplate
	 * @generated
	 */
	public Adapter createNodeTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Template <em>Template</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Template
	 * @generated
	 */
	public Adapter createTemplateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Production <em>Production</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Production
	 * @generated
	 */
	public Adapter createProductionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.goku <em>goku</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.goku
	 * @generated
	 */
	public Adapter creategokuAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link BreezeModel.Method <em>Method</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see BreezeModel.Method
	 * @generated
	 */
	public Adapter createMethodAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //breezeAdapterFactory
