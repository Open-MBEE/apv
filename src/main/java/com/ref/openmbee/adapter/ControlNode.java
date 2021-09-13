package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IControlNode;

public abstract class ControlNode extends ActivityNode implements IControlNode{

	public ControlNode(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
	}

	@Override
	public boolean isInitialNode() {
		return this instanceof Initial;
	}

	@Override
	public boolean isFlowFinalNode() {
		return this instanceof FinalFlow;
	}

	@Override
	public boolean isFinalNode() {
		return this instanceof Final;
	}

	@Override
	public boolean isForkNode() {	
		return this instanceof Fork;
	}

	@Override
	public boolean isJoinNode() {		
		return this instanceof Join;
	}

	@Override
	public boolean isDecisionNode() {
		return this instanceof Decision;
	}

	@Override
	public boolean isMergeNode() {
		return this instanceof Merge;
	}

	
	
}
