package com.ref.astah.statemachine.adapter;

import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.ITransition;
import com.ref.interfaces.stateMachine.IVertex;

public class State implements IState, IVertex{
	
	private com.change_vision.jude.api.inf.model.IState state;
	private ITransition[] incomings;
	private ITransition[] outgoings;
	private IStateMachine submachine;
	private boolean isFirstState;
	private boolean isSubmachineState;
	private IState[] substates;
	private IPseudostate[] subpseudostates;
	private ITransition[] internalTransitions;
	private boolean isCompound;
	private IState superiorState;
	
	public State(com.change_vision.jude.api.inf.model.IState st) throws WellFormedException {
		this.state = st;
		if(st.getSubmachine() != null) {
			this.submachine = new StateMachine(st.getSubmachine());
		}
		this.isFirstState = false;
		this.isCompound = false;
		this.superiorState = null;
	}
	
	@Override
	public ITransition[] getIncomings() {
		return this.incomings;
	}

	@Override
	public ITransition[] getOutgoings() {
		return this.outgoings;
	}

	@Override
	public String getId() {
		return this.state.getId();
	}

	@Override
	public String getName() {
		return this.state.getName();
	}

	@Override
	public String getDefinition() {
		return this.state.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return this.state.getStereotypes();
	}

	@Override
	public IStateMachine getSubmachine() {
		return this.submachine;
	}

	@Override
	public String getEntry() {
		return this.state.getEntry();
	}

	@Override
	public String getDoActivity() {
		return this.state.getDoActivity();
	}

	@Override
	public String getExit() {
		return this.state.getExit();
	}

	@Override
	public void setEntry(String entry) {
		try {
			this.state.setEntry(entry);
		} catch (InvalidEditingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setDoActivity(String doActivity) {
		try {
			this.state.setDoActivity(doActivity);
		} catch (InvalidEditingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setExit(String exit) {
		try {
			this.state.setExit(exit);
		} catch (InvalidEditingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setOutgoings(ITransition[] outgoings) {
		this.outgoings = outgoings;
	}

	@Override
	public void setIncomings(ITransition[] incomings) {
		this.incomings = incomings;
	}

	@Override
	public void setIsFirstState(boolean bool) {
		this.isFirstState = bool;
	}

	@Override
	public boolean isFirstState() {
		return this.isFirstState;
	}

	@Override
	public boolean isSubmachineState() {
		return this.state.isSubmachineState();
	}

	@Override
	public IVertex[] getSubvertexes(int regionIndex) {
		//return this.getSubvertexes(regionIndex);
		return null;
	}

	@Override
	public IState[] getSubstates() {
		return this.substates;
	}

	@Override
	public IPseudostate[] getSubpseudostates() {
		return this.subpseudostates;
	}

	@Override
	public void setSubstates(IState[] substate) {
		// TODO Auto-generated method stub
		this.substates = substate;
	}

	@Override
	public void setSubpseudostates(IPseudostate[] subpseudostates) {
		// TODO Auto-generated method stub
		this.subpseudostates = subpseudostates;
	}
	@Override
	public boolean isCompound() {
		return this.isCompound;
	}
	@Override
	public void setIsCompound(boolean b) {
		this.isCompound = b;
	}
	@Override
	public IState getSuperiorState() {
		return this.superiorState;
	}
	@Override
	public void setSuperiorState(IState s) {
		this.superiorState = s;
	}
	

}
