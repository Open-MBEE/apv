package com.ref.astah.adapter;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;

public abstract class ActivityNode implements IActivityNode{
	protected com.change_vision.jude.api.inf.model.IActivityNode activityNode;
	protected IFlow[] incomings;
	protected IFlow[] outgoings;

	public ActivityNode(com.change_vision.jude.api.inf.model.IActivityNode activityNode) throws WellFormedException {
		super();
		this.activityNode = activityNode;
		
		if (activityNode.getIncomings() != null) {
			this.incomings = new IFlow[activityNode.getIncomings().length];
			for (int i = 0; i < this.incomings.length; i++) {
				this.incomings[i] = AdapterUtils.setFlow(activityNode.getIncomings()[i], "target",this);
			} 
		}
		
		if (activityNode.getOutgoings() != null) {
			this.outgoings = new IFlow[activityNode.getOutgoings().length];
			for (int i = 0; i < this.outgoings.length; i++) {
				this.outgoings[i] = AdapterUtils.setFlow(activityNode.getOutgoings()[i], "source",this);
			} 
		}
		
	}

	@Override
	public String getId() {
		return activityNode.getId();
	}

	@Override
	public String getName() {
		return activityNode.getName();
	}

	@Override
	public String getDefinition() {
		return activityNode.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return activityNode.getStereotypes();
	}

	@Override
	public IFlow[] getIncomings() {
		return this.incomings;
	}

	@Override
	public IFlow[] getOutgoings() {
		return this.outgoings;
	}
	
}
