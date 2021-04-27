package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IInputPin;

public class InputPin extends Pin implements IInputPin{

	public InputPin(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, Type type2, String typeId, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, type2, typeId, name, stereotypes, definition);
	}

	

}
