package com.ref.interfaces.activityDiagram;

public interface IPin extends IObjectNode {

	IAction getOwner();
	
	void setOwner(IAction action);
}
