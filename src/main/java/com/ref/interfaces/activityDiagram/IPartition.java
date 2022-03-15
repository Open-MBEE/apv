package com.ref.interfaces.activityDiagram;

import com.ref.interfaces.INamedElement;

public interface IPartition extends INamedElement {
	
	IPartition[] getSubPartitions();

}
