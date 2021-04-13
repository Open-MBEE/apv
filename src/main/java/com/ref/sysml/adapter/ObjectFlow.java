package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ObjectFlow extends Flow implements IObjectFlow {
	private IClass base;
	
	public ObjectFlow(EObject flow) throws WellFormedException {
		super(flow);
	}
	
	@Override
	public IClass getBase() {
		return this.base;
	}
	
	public void setBase(IClass base) {
		this.base = base;
	}

	public void setTarget(IActivityNode target) {
		this.target = target;
	}

	public void setSource(IActivityNode source) {
		this.source = source;
	}
}