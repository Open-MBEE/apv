package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class Activity {
	private String type;
	private String ownerId;
	private String id;
	private ArrayList<String> ownedParameterIds;
	private ArrayList<String> nodeIds;
	private ArrayList<String> edgeIds;
	
	public Activity(String type, String ownerId, String id, ArrayList<String> ownedParameterIds,
			ArrayList<String> nodeIds, ArrayList<String> edgeIds) {
		super();
		this.type = type;
		this.ownerId = ownerId;
		this.id = id;
		this.ownedParameterIds = ownedParameterIds;
		this.nodeIds = nodeIds;
		this.edgeIds = edgeIds;
	}
	
	
}
