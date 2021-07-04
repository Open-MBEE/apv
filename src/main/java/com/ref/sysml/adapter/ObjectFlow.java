package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.Usage;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ObjectFlow extends Flow implements IObjectFlow, Comparable<ObjectFlow> {
	private Class base;
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
	public Class getBase() {
		if (this.base.getClasse() == null) {
			String paramName = AdapterUtils.getSourcePinName(this);
			Usage usage = AdapterUtils.nodesType.get(paramName);

			if (usage != null) {
				base = new Class(usage);
				this.setBase(base);
			} else {
				paramName = AdapterUtils.getTargetPinName(this);
				usage = AdapterUtils.nodesType.get(paramName);
				
				base = new Class(usage);
				this.setBase(base);
			}
		}
		
		return this.base;
	}
	
	public void setBase(Class base) {
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
