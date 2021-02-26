package com.ref.openmbee.adapter;

public class ObjectFlow extends Flow{
	public String typeId;

	public ObjectFlow(String id, String type, String ownerId, String activityId, String sourceId, String targetId,
			String typeId) {
		super(id, type, ownerId, activityId, sourceId, targetId);
		this.typeId = typeId;
	}
	

}
