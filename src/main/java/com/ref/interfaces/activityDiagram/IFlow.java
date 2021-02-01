package com.ref.interfaces.activityDiagram;

public interface IFlow extends INamedElement{	

	IActivityNode getTarget();

	IActivityNode getSource();

	String getGuard();
	
	void setSource(IActivityNode node);
	
	void setTarget(IActivityNode node);
}
