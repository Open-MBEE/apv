package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.INamedElement;

public abstract class ActivityElement implements INamedElement{
	private String id;
	private String type;
	private String ownerId;
	private String activityId;
	
	private String name;
	private String[] stereotypes;
	private String definition;
	/*
	 * TODO definition = concatenar todas as strings de definition
	 * */
	public ActivityElement(String id, String type, String ownerId, String activityId, 
			String name, String[] stereotypes, String definition) {
		super();
		this.id = id;
		this.type = type;
		this.ownerId = ownerId;
		this.activityId = activityId;
		this.name = name;
		this.stereotypes = stereotypes;
		this.definition = definition;
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
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDefinition() {
		return this.definition;
	}

	@Override
	public String[] getStereotypes() {
		return this.stereotypes;
	}

}
