package com.ref.astah.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ref.interfaces.activityDiagram.IPartition;

public class Partition implements IPartition{

	private com.change_vision.jude.api.inf.model.IPartition partition;
	private List<IPartition> subpartitions;
	
	public Partition(com.change_vision.jude.api.inf.model.IPartition partition) {
		this.partition = partition;
		subpartitions = new ArrayList<IPartition>();
		if (partition.getSubPartitions() != null && partition.getSubPartitions().length > 0) {
			for (com.change_vision.jude.api.inf.model.IPartition p : partition.getSubPartitions()) {
				subpartitions.add(new Partition(p));
			}
		}
	}

	@Override
	public String getId() {
		return partition.getId();
	}

	@Override
	public String getName() {
		return partition.getName();
	}

	@Override
	public String getDefinition() {
		return partition.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return partition.getStereotypes();
	}

	@Override
	public IPartition[] getSubPartitions() {
		return (IPartition[])subpartitions.toArray();
	}
}
