package com.ref.interfaces.activityDiagram;

import com.ref.interfaces.INamedElement;

public interface IActivity extends INamedElement{

	IActivityDiagram getActivityDiagram();
	
	void setActivityDiagram(IActivityDiagram activityDiagram);

	IActivityNode[] getActivityNodes();

	void setName(String nameAD);
	
	IPartition [] getPartitions();

}
