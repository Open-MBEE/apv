package com.ref.astah.adapter;

import com.ref.interfaces.activityDiagram.IPartition;

public class Partition implements IPartition{

	private com.change_vision.jude.api.inf.model.IPartition partition;
	private IPartition[] subpartitions;
	
	public Partition(com.change_vision.jude.api.inf.model.IPartition partition) {
		this.partition = partition;
		
		if (partition.getSubPartitions() != null && partition.getSubPartitions().length > 0) {
			subpartitions = new IPartition[partition.getSubPartitions().length];
			for (int i=0; i < partition.getSubPartitions().length; i++)  {
				subpartitions[i] = new Partition(partition.getSubPartitions()[i]);
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
		return subpartitions;
	}
}
