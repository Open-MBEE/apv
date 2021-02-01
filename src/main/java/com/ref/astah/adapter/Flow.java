package com.ref.astah.adapter;

import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;

public abstract class Flow implements IFlow{
	protected com.change_vision.jude.api.inf.model.IFlow flow;
	protected IActivityNode target;
	protected IActivityNode source;
	
	public Flow(com.change_vision.jude.api.inf.model.IFlow flow) {
		this.flow = flow;	
	}

	@Override
	public String getId() {
		return flow.getId();
	}

	@Override
	public String getName() {
		return flow.getName();
	}

	@Override
	public String getDefinition() {
		return flow.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return flow.getStereotypes();
	}

	@Override
	public IActivityNode getTarget() {
		return target;
	}

	@Override
	public IActivityNode getSource() {
		return source;
	}

	@Override
	public String getGuard() {
		return flow.getGuard();
	}

	public void setTarget(IActivityNode target) {
		this.target = target;
	}

	public void setSource(IActivityNode source) {
		this.source = source;
	}
	
}
