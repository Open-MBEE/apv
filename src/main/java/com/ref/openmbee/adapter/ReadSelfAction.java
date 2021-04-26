package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class ReadSelfAction extends Action{
	private String nameExpression;
	private String resultId;
	private ArrayList<String> localPostconditionIds;
	private ArrayList<String> localPreconditionIds;
	
	public ReadSelfAction(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, String nameExpression, String resultId,
			ArrayList<String> localPostconditionIds, ArrayList<String> localPreconditionIds,
			String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.nameExpression = nameExpression;
		this.resultId = resultId;
		this.localPostconditionIds = localPostconditionIds;
		this.localPreconditionIds = localPreconditionIds;
	}

	public String getNameExpression() {
		return nameExpression;
	}

	public void setNameExpression(String nameExpression) {
		this.nameExpression = nameExpression;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	public ArrayList<String> getLocalPostconditionIds() {
		return localPostconditionIds;
	}

	public void setLocalPostconditionIds(ArrayList<String> localPostconditionIds) {
		this.localPostconditionIds = localPostconditionIds;
	}

	public ArrayList<String> getLocalPreconditionIds() {
		return localPreconditionIds;
	}

	public void setLocalPreconditionIds(ArrayList<String> localPreconditionIds) {
		this.localPreconditionIds = localPreconditionIds;
	}

}
