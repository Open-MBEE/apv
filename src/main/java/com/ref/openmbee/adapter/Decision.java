package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IControlNode;

public class Decision extends ControlNode implements IControlNode{
	private String decisionInputFlow;
	private String decisionInputFlowId;

	public Decision(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, String decisionInputFlow,
			String decisionInputFlowId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.decisionInputFlow = decisionInputFlow;
		this.decisionInputFlowId = decisionInputFlowId;
	}

	public String getDecisionInputFlow() {
		return decisionInputFlow;
	}

	public void setDecisionInputFlow(String decisionInputFlow) {
		this.decisionInputFlow = decisionInputFlow;
	}

	public String getDecisionInputFlowId() {
		return decisionInputFlowId;
	}

	public void setDecisionInputFlowId(String decisionInputFlowId) {
		this.decisionInputFlowId = decisionInputFlowId;
	}
	
}
