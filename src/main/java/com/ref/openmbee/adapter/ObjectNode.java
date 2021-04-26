package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectNode;

public class ObjectNode extends ActivityNode implements IObjectNode{
	private Type type;
	private String typeId;
	
	private String name;
	private String[] stereotypes;
	private String definition;
	
	

	public ObjectNode(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingIds,
			ArrayList<String> incomingIds, Type type2, String typeId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingIds, incomingIds, name, stereotypes, definition);
		this.type = type2;
		this.typeId = typeId;
		
		this.name = name;
		this.stereotypes = stereotypes;
		this.definition = definition;
	}


	@Override
	public IClass getBase() {
		return this.type;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
