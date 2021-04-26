package com.ref.astah.adapter;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IPin;

public abstract class Pin extends ObjectNode implements IPin{
	protected IAction owner;
	
	public Pin(com.change_vision.jude.api.inf.model.IPin pin) throws WellFormedException {
		super(pin);
	}

	@Override
	public IClass getBase() {
		return this.base;
	}

	@Override
	public IFlow[] getIncomings() {
		return this.incomings;
	}

	@Override
	public IFlow[] getOutgoings() {
		return this.outgoings;
	}

	@Override
	public String getId() {
		return ((com.change_vision.jude.api.inf.model.IPin)activityNode).getId();
	}

	@Override
	public String getName() {
		return ((com.change_vision.jude.api.inf.model.IPin)activityNode).getName();
	}

	@Override
	public String getDefinition() {
		return ((com.change_vision.jude.api.inf.model.IPin)activityNode).getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return ((com.change_vision.jude.api.inf.model.IPin)activityNode).getStereotypes();
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
