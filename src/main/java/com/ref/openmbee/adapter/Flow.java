package com.ref.openmbee.adapter;

public class Flow {
	private String id;
	private String type;
	private String ownerId;
	private String activityId;
	private String sourceId;
	private String targetId;
	
	public Flow(String id, String type, String ownerId, String activityId, String sourceId, String targetId) {
		super();
		this.id = id;
		this.type = type;
		this.ownerId = ownerId;
		this.activityId = activityId;
		this.sourceId = sourceId;
		this.targetId = targetId;
	}
	
	

}
