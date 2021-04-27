package com.ref.astah.adapter;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IFlow;

public class ActivityParameterNode extends ObjectNode implements IActivityParameterNode{
	private IClass base;

	public ActivityParameterNode(com.change_vision.jude.api.inf.model.IActivityParameterNode activityParameterNode) throws WellFormedException {
		super(activityParameterNode);		
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
		return ((com.change_vision.jude.api.inf.model.IActivityParameterNode)activityNode).getId();
	}

	@Override
	public String getName() {
		return ((com.change_vision.jude.api.inf.model.IActivityParameterNode)activityNode).getName();
	}

	@Override
	public String getDefinition() {
		return ((com.change_vision.jude.api.inf.model.IActivityParameterNode)activityNode).getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return ((com.change_vision.jude.api.inf.model.IActivityParameterNode)activityNode).getStereotypes();
	}

	@Override
	public IClass getBase() {
		return this.base;
	}

}
