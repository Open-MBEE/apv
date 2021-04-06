package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.ParameterMembership;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IPin;

public abstract class Pin extends ObjectNode implements IPin{
	protected IAction owner;
	
	public Pin(EObject pin) throws WellFormedException {
		super(pin);
		this.base = new Class(((ParameterMembership) pin).getOwnedMemberParameter_comp());
	}

	@Override
	public IClass getBase() {
		return this.base;
	}

	@Override
	public IFlow[] getIncomings() {
		return this.getIncomings();
	}

	@Override
	public IFlow[] getOutgoings() {
		return this.getOutgoings();
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
	public IAction getOwner() {
		return this.owner;
	}
	
	@Override
	public void setOwner(IAction owner) {
		this.owner = owner;
	}

}
