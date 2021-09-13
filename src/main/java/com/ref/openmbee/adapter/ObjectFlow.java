package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ObjectFlow extends Flow implements IObjectFlow{
	public Type baseType;
	public String baseTypeId;
	
	public ObjectFlow(String id, String type, String ownerId, String activityId, String sourceId,
			String targetId, Type baseType, String baseTypeId, String name, String[] stereotypes, String definition, String guard) {
		super(id, type, ownerId, activityId, sourceId, targetId, name, stereotypes, definition, guard);
		this.baseType = baseType;
		this.baseTypeId = baseTypeId;
	}

	@Override
	public IClass getBase() {
		return this.baseType;
	}

	public void setBaseType(Type baseType) {
		this.baseType = baseType;
	}

	public String getBaseTypeId() {
		return baseTypeId;
	}

	public void setBaseTypeId(String typeId) {
		this.baseTypeId = typeId;
	}
	
	

	

}
