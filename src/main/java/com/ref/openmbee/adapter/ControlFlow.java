package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IControlFlow;

public class ControlFlow extends Flow implements IControlFlow{

	public ControlFlow(String id, String type, String ownerId, String activityId, String sourceId,
			String targetId, String name, String[] stereotypes, String definition, String guard) {
		super(id, type, ownerId, activityId, sourceId, targetId, name, stereotypes, definition, guard);
	}

}
