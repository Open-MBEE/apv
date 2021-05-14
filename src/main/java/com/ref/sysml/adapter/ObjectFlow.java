package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ObjectFlow extends Flow implements IObjectFlow, Comparable<ObjectFlow> {
	private IClass base;
	private EObject owner;
	
	public ObjectFlow(EObject flow) throws WellFormedException {
		super(flow);
	}
	
	public ObjectFlow(EObject flow, EObject owner) throws WellFormedException {
		super(flow);
		this.owner = owner;
	}
	
	public EObject getOwner() {
		return owner;
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

	@Override
	public int compareTo(ObjectFlow o) {
		return "else".compareTo(this.getGuard());
	}
}
