package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IActivity;

public class ReadStructuralFeatureAction extends Action{
	private String structuralFeatureId;
	private String objectId;
	private String resultId;
	
	public ReadStructuralFeatureAction(String id, String type, String ownerId, String activityId,
			ArrayList<String> outgoingsIds, ArrayList<String> incomingIds, String structuralFeatureId, String objectId,
			String resultId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.structuralFeatureId = structuralFeatureId;
		this.objectId = objectId;
		this.resultId = resultId;
	}

	public String getStructuralFeatureId() {
		return structuralFeatureId;
	}

	public void setStructuralFeatureId(String structuralFeatureId) {
		this.structuralFeatureId = structuralFeatureId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getResultId() {
		return resultId;
	}

	public void setResultId(String resultId) {
		this.resultId = resultId;
	}

	@Override
	public IActivity getCallingActivity() {
		return null;
	}

	@Override
	public boolean isCallBehaviorAction() {
		return false;
	}

	@Override
	public boolean isSendSignalAction() {
		return false;
	}

	@Override
	public boolean isAcceptEventAction() {
		return false;
	}
	
}
