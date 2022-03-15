package com.ref.astah.statemachine.adapter;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.ref.interfaces.stateMachine.ITransition;
import com.ref.interfaces.stateMachine.IVertex;

public class Transition implements ITransition{
	
	private com.change_vision.jude.api.inf.model.ITransition transition;
	private IVertex source;
	private IVertex target;
	
	public Transition(com.change_vision.jude.api.inf.model.ITransition tr) {
		this.transition = tr;
	}
	
	@Override
	public String getId() {
		return this.transition.getId();
	}

	@Override
	public String getName() {
		return this.transition.getName();
	}

	@Override
	public String getDefinition() {
		return this.transition.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return this.transition.getStereotypes();
	}

	@Override
	public IVertex getSource() {
		return this.source;
	}

	@Override
	public IVertex getTarget() {
		return this.target;
	}

	@Override
	public String getGuard() {
		return this.transition.getGuard();
	}

	@Override
	public String getAction() {
		return this.transition.getAction();
	}

	@Override
	public String getTrigger() {
		return this.transition.getEvent();
	}

	@Override
	public void setGuard(String guard) {
		// TODO Auto-generated method stub
		try {
			this.transition.setGuard(guard);
		} catch (InvalidEditingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setAction(String action) {
		// TODO Auto-generated method stub
		try {
			this.transition.setAction(action);
		} catch (InvalidEditingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void setTrigger(String event) {
		try {
			this.transition.setEvent(event);
		} catch (InvalidEditingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setSource(IVertex source) {
		this.source = source;
	}

	@Override
	public void setTarget(IVertex target) {
		this.target = target;
	}
	
}
