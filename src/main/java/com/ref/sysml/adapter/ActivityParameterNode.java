package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.ParameterMembership;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IClass;

public class ActivityParameterNode extends ObjectNode implements IActivityParameterNode{
	private IClass base;

	public ActivityParameterNode(EObject activityParameterNode) throws WellFormedException {
		super(activityParameterNode);	
		this.base = new Class(((ParameterMembership) activityParameterNode).getOwnedMemberParameter_comp());
	}

	@Override
	public String getName() {
		return this.name;
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
