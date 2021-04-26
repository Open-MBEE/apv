package com.ref.astah.adapter;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IOutputPin;

public class OutputPin extends Pin implements IOutputPin{

	public OutputPin(com.change_vision.jude.api.inf.model.IOutputPin outputPin) throws WellFormedException {
		super(outputPin);
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
		return ((com.change_vision.jude.api.inf.model.IOutputPin)activityNode).getId();
	}

	@Override
	public String getName() {
		return ((com.change_vision.jude.api.inf.model.IOutputPin)activityNode).getName();
	}

	@Override
	public String getDefinition() {
		return ((com.change_vision.jude.api.inf.model.IOutputPin)activityNode).getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return ((com.change_vision.jude.api.inf.model.IOutputPin)activityNode).getStereotypes();
	}

	@Override
	public IAction getOwner() {
		return this.owner;
	}
	
	

}
