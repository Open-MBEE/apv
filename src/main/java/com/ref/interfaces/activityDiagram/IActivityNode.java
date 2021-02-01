package com.ref.interfaces.activityDiagram;

public interface IActivityNode extends INamedElement {

	IFlow[] getIncomings();

	IFlow[] getOutgoings();


}
