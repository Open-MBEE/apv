package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class Decision extends Element{
	private String decisionInputFlow;
	private String decisionInputFlowId;

	public Decision(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, boolean isControlType, String decisionInputFlow,
			String decisionInputFlowId) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, isControlType);
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
