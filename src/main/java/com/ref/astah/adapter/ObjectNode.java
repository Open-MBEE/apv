package com.ref.astah.adapter;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectNode;

public class ObjectNode  extends ActivityNode implements IObjectNode{
	protected IClass base;
	
	public ObjectNode(com.change_vision.jude.api.inf.model.IObjectNode objectNode) throws WellFormedException {
		super(objectNode);
		if(objectNode.getBase() != null) {
			this.base = new Class(objectNode.getBase());
		}
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
		return ((com.change_vision.jude.api.inf.model.IObjectNode)activityNode).getId();
	}

	@Override
	public String getName() {
		return ((com.change_vision.jude.api.inf.model.IObjectNode)activityNode).getName();
	}

	@Override
	public String getDefinition() {
		return ((com.change_vision.jude.api.inf.model.IObjectNode)activityNode).getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return ((com.change_vision.jude.api.inf.model.IObjectNode)activityNode).getStereotypes();
	}

	@Override
	public IClass getBase() {
		return this.base;
	}

}
