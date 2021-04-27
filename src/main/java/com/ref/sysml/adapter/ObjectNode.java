package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.ParameterMembership;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectNode;

public class ObjectNode extends ActivityNode implements IObjectNode{
	protected IClass base;
	
	public ObjectNode(EObject objectNode, IActivity owner) throws WellFormedException {
		super(objectNode, owner);
		
		if (objectNode instanceof ParameterMembership) {
			this.base = new Class(((ParameterMembership) objectNode).getOwnedMemberParameter_comp());		
		}
		
	}

	@Override
	public String getDefinition() {
		return "";
	}

	@Override
	public String[] getStereotypes() {
		return new String[0];
	}

	@Override
	public IClass getBase() {
		return this.base;
	}

}
