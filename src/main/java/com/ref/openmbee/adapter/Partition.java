package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IPartition;

public class Partition extends ActivityElement implements IPartition{
	
	private ArrayList<String> nodeIds;
	private ArrayList<String> edgeIds;
	private ArrayList<String> subpartitionIds;
	private IPartition[] subpartitions;
	
	public Partition(String id, String type, String ownerId, String activityId, String name, String[] stereotypes,
			String definition, ArrayList<String> nodeIds, ArrayList<String> edgeIds, ArrayList<String> subpartitionIds) {
		super(id, type, ownerId, activityId, name, stereotypes, definition);
		this.nodeIds = nodeIds;
		this.edgeIds = edgeIds;
		this.subpartitionIds = subpartitionIds;
	}

	@Override
	public IPartition[] getSubPartitions() {
		return this.subpartitions;
	}

	public void setSubpartitions(IPartition[] subpartitions) {
		this.subpartitions = subpartitions;
	}

	public ArrayList<String> getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(ArrayList<String> nodeIds) {
		this.nodeIds = nodeIds;
	}

	public ArrayList<String> getEdgeIds() {
		return edgeIds;
	}

	public void setEdgeIds(ArrayList<String> edgeIds) {
		this.edgeIds = edgeIds;
	}

	public ArrayList<String> getSubpartitionIds() {
		return subpartitionIds;
	}

	public void setSubpartitionIds(ArrayList<String> subpartitionIds) {
		this.subpartitionIds = subpartitionIds;
	}



	
}
