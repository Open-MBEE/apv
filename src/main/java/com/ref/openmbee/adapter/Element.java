package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class Element {
	private String id;//TODO add
	private String type;
	private String ownerId;
	private String activityId;
	private ArrayList<String> outgoingsIds;
	private ArrayList<String> incomingIds;
	private boolean isControlType;
	public Element(String id,String type, String ownerId, String activityId, ArrayList<String> outgoingsIds, ArrayList<String> incomingIds,
			boolean isControlType) {
		super();
		this.id = id;
		this.type = type;
		this.ownerId = ownerId;
		this.activityId = activityId;
		this.outgoingsIds = outgoingsIds;
		this.incomingIds = incomingIds;
		this.isControlType = isControlType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public ArrayList<String> getOutgoingsIds() {
		return outgoingsIds;
	}
	public void setOutgoingsIds(ArrayList<String> outgoingsIds) {
		this.outgoingsIds = outgoingsIds;
	}
	public ArrayList<String> getIncomingIds() {
		return incomingIds;
	}
	public void setIncomingIds(ArrayList<String> incomingIds) {
		this.incomingIds = incomingIds;
	}
	public boolean isControlType() {
		return isControlType;
	}
	public void setControlType(boolean isControlType) {
		this.isControlType = isControlType;
	}


	
}
