package com.ref.sysml.adapter;

import java.util.List;

import org.omg.sysml.xtext.sysml.ParameterMembership;
import org.omg.sysml.xtext.sysml.Usage;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IInputPin;

public class InputPin extends Pin implements IInputPin{
	protected IFlow[] incomings;
	protected IFlow[] outgoings;
	protected IAction owner;
	protected ParameterMembership pin;
	protected String name;
	protected IClass base;
	
	public InputPin(ParameterMembership pin) throws WellFormedException {
		super(pin);
		this.pin = pin;
		
		Usage usage = this.pin.getOwnedMemberParameter_comp();
		
		name = usage.getName();
		base = new Class(pin.getOwnedMemberParameter_comp());
	}

	@Override
	public IFlow[] getIncomings() {
		List<IFlow> flows = AdapterUtils.getInFlows(AdapterUtils.parameterName.get(this.getId()));
		
		return flows.toArray(new IFlow[flows.size()]);
	}

	@Override
	public IFlow[] getOutgoings() {
		List<IFlow> flows = AdapterUtils.getOutFlows(AdapterUtils.parameterName.get(this.getId()));
		
		return flows.toArray(new IFlow[flows.size()]);
	}

	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(this.pin));
	}

	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDefinition() {
		return ""; // Ainda nao definido
	}

	@Override
	public String[] getStereotypes() {
		return new String[0]; // Ainda nao definido
	}

	@Override
	public IAction getOwner() {
		return this.owner;
	}

	@Override
	public void setOwner(IAction owner) {
		this.owner = owner;
	}

	@Override
	public IClass getBase() {
		return base;
	}
	
}
