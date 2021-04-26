package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IControlNode;

public class Initial extends ControlNode implements IControlNode{

	public Initial(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
	}

}
