package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IActivity;

public class ValueSpecificationAction extends Action{
	private String ValueType;
	private String ValueOwnerId;
	private String ValueInstanceId; 
	private String ValueTypeId;
	
	public ValueSpecificationAction(String id, String type, String ownerId, String activityId,
			ArrayList<String> outgoingsIds, ArrayList<String> incomingIds, String valueType, String valueOwnerId,
			String valueInstanceId, String valueTypeId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		ValueType = valueType;
		ValueOwnerId = valueOwnerId;
		ValueInstanceId = valueInstanceId;
		ValueTypeId = valueTypeId;
	}
	
	
	public String getValueType() {
		return ValueType;
	}


	public void setValueType(String valueType) {
		ValueType = valueType;
	}


	public String getValueOwnerId() {
		return ValueOwnerId;
	}


	public void setValueOwnerId(String valueOwnerId) {
		ValueOwnerId = valueOwnerId;
	}


	public String getValueInstanceId() {
		return ValueInstanceId;
	}


	public void setValueInstanceId(String valueInstanceId) {
		ValueInstanceId = valueInstanceId;
	}


	public String getValueTypeId() {
		return ValueTypeId;
	}


	public void setValueTypeId(String valueTypeId) {
		ValueTypeId = valueTypeId;
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

	@Override
	public IActivity getCallingActivity() {
		return null;
	}
	
}
