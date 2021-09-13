package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IClass;

public class Type implements IClass{
	
	private String id;
	private String name;
	private String definition;
	private String[] stereotypes;
	private String typeId;

	public Type(String id, String name, String definition, String[] stereotypes, String typeId) {
		super();
		this.id = id;
		this.name = name;
		this.definition = definition;
		this.stereotypes = stereotypes;
		this.typeId = typeId;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDefinition() {
		return definition;
	}

	@Override
	public String[] getStereotypes() {
		return stereotypes;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public void setName(String name) {
		this.name = name;
	}


}
