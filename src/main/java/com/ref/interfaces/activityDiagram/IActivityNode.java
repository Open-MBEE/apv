package com.ref.interfaces.activityDiagram;

import com.ref.interfaces.INamedElement;

public interface IActivityNode extends INamedElement {

	IFlow[] getIncomings();

	IFlow[] getOutgoings();


}
