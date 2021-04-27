package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ObjectFlow extends Flow implements IObjectFlow{
	public Type type;
	public String typeId;
	
	public ObjectFlow(String id, String type, String ownerId, String activityId, String sourceId,
			String targetId, Type type2, String typeId, String name, String[] stereotypes, String definition, String guard) {
		super(id, type, ownerId, activityId, sourceId, targetId, name, stereotypes, definition, guard);
		this.type = type2;
		this.typeId = typeId;
	}

	@Override
	public IClass getBase() {
		return this.type;
	}
	
	

	

}
