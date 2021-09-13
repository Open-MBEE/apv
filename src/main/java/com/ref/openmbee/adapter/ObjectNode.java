package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectNode;

public class ObjectNode extends ActivityNode implements IObjectNode{
	private Type baseType;
	private String typeId;

	public ObjectNode(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingIds,
			ArrayList<String> incomingIds, Type baseType, String typeId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingIds, incomingIds, name, stereotypes, definition);
		this.baseType = baseType;
		this.typeId = typeId;
	}

	@Override
	public IClass getBase() {
		return this.baseType;
	}


	public String getTypeId() {
		return typeId;
	}


	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public void setBaseType(Type type) {
		this.baseType = type;
	}

	public Type getBaseType() {
		return baseType;
	}
	
	
}
