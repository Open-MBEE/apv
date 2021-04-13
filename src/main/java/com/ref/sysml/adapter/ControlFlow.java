package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;

import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IControlFlow;

public class ControlFlow extends Flow implements IControlFlow{
	
	
	public ControlFlow(EObject flow) {
		super(flow);
	}
	
	@Override
	public IActivityNode getTarget() {
		return target;
	}

	@Override
	public IActivityNode getSource() {
		return source;
	}
	
	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(flow));
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public String getDefinition() {
		return "";
	}

	@Override
	public String[] getStereotypes() {
		return new String[0];
	}
	public void setTarget(IActivityNode target) {
		this.target = target;
	}

	public void setSource(IActivityNode source) {
		this.source = source;
	}
}
