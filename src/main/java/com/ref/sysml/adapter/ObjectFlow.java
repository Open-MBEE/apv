package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.SuccessionItemFlow;
import org.omg.sysml.xtext.sysml.Usage;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ObjectFlow extends Flow implements IObjectFlow {
	private SuccessionItemFlow successionItemFlow;
	
	public ObjectFlow(EObject flow) throws WellFormedException {
		super(flow);
		this.successionItemFlow = (SuccessionItemFlow) flow;
	}
	@Override
	public IClass getBase() {
		String nameNodeFrom = successionItemFlow.getNameNodeFrom();
		String nameDataFrom = successionItemFlow.getNameDataFrom();
		
		Usage usage = AdapterUtils.nodesType.get(nameNodeFrom + "_" + nameDataFrom);

		if (usage != null) {
			return new Class(usage);
		}
		
		String nameNodeTo = successionItemFlow.getNameNodeTo();
		String nameDataTo = successionItemFlow.getNameDataTo();
		
		usage = AdapterUtils.nodesType.get(nameNodeTo + "_" + nameDataTo);
		
		return new Class(usage);
	}

	@Override
	public IActivityNode getTarget() {
		return target;
	}

	@Override
	public IActivityNode getSource() {
		return source;
	}

	public void setTarget(IActivityNode target) {
		this.target = target;
	}

	public void setSource(IActivityNode source) {
		this.source = source;
	}
}
