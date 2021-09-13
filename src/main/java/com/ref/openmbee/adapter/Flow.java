package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;

public class Flow extends ActivityElement implements IFlow{	
	private String sourceId;
	private IActivityNode source;
	private String targetId;
	private IActivityNode target;

	private String guard;

	public Flow(String id, String type, String ownerId, String activityId, String sourceId,
			String targetId, String name, String[] stereotypes, String definition, String guard) {
		super(id, type, ownerId, activityId, name, stereotypes, definition);
		this.sourceId = sourceId;
		this.targetId = targetId;
		this.guard = guard;
	}
	
	@Override
	public IActivityNode getTarget() {
		return this.target;
	}

	@Override
	public IActivityNode getSource() {
		return this.source;
	}

	@Override
	public String getGuard() {
		return this.guard;
	}

	@Override
	public void setSource(IActivityNode node) {
		this.source = node;
		
	}

	@Override
	public void setTarget(IActivityNode node) {
		this.target = node;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	

}
