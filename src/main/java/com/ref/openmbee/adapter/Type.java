package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IClass;

public class Type extends ActivityElement implements IClass{
	private String name;
	private String[] stereotypes;
	private String definition;
	
	public Type(String id, String type, String ownerId, String activityId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, name, stereotypes, definition);
		this.name = name;
		this.stereotypes = stereotypes;
		this.definition = definition;
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
