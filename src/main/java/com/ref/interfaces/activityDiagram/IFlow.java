package com.ref.interfaces.activityDiagram;

import com.ref.interfaces.INamedElement;

public interface IFlow extends INamedElement{	

	IActivityNode getTarget();

	IActivityNode getSource();

	String getGuard();
	
	void setSource(IActivityNode node);
	
	void setTarget(IActivityNode node);
}
