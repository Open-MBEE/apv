package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;

import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;

public class Flow implements IFlow{

	protected EObject flow;
	protected IActivityNode target;
	protected IActivityNode source;
	
	public Flow(EObject flow) {
		this.flow = flow;	
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
		return new String[0]; // Ainda não definido
	}

	@Override
	public IActivityNode getTarget() {
		return AdapterUtils.getTarget(this);
	}

	@Override
	public IActivityNode getSource() {
		return AdapterUtils.getSource(this);
	}

	@Override
	public String getGuard() {
		return ""; // Ainda não definido
	}

	@Override
	public void setTarget(IActivityNode target) {
		this.target = target;
		
	}

	@Override
	public void setSource(IActivityNode source) {
		this.source = source;
		
	}

}
