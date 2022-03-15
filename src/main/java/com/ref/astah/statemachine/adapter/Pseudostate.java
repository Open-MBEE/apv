package com.ref.astah.statemachine.adapter;

import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.ITransition;
import com.ref.interfaces.stateMachine.IVertex;

public class Pseudostate implements IPseudostate, IVertex{
	
	private com.change_vision.jude.api.inf.model.IPseudostate pseudoState;
	private com.change_vision.jude.api.inf.model.IFinalState finalState;
	private ITransition[] incomings;
	private ITransition[] outgoings;
	private boolean isFirstState;
			
	
	public Pseudostate(com.change_vision.jude.api.inf.model.IPseudostate pst) {
		this.pseudoState = pst;
		this.isFirstState = false;
	}
	
	public Pseudostate(com.change_vision.jude.api.inf.model.IFinalState fs) {
		this.finalState = fs;
		this.isFirstState = false;
	}

	@Override
	public String getId() {
		if(this.isFinalState()) {
			return this.finalState.getId();
		}
		return this.pseudoState.getId();
	}

	@Override
	public String getName() {
		if(this.isFinalState()) {
			return this.finalState.getName();
		}
		return this.pseudoState.getName();
	}

	@Override
	public String getDefinition() {
		if(this.isFinalState()) {
			return this.finalState.getDefinition();
		}	
		return this.pseudoState.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		if(this.isFinalState()) {
			return this.finalState.getStereotypes();
		}
		return this.pseudoState.getStereotypes();
	}

	@Override
	public boolean isInitialPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isInitialPseudostate();
	}

	@Override
	public boolean isShallowHistoryPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isShallowHistoryPseudostate();
	}

	@Override
	public boolean isDeepHistoryPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isDeepHistoryPseudostate();
	}

	@Override
	public boolean isJunctionPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isJunctionPseudostate();
	}

	@Override
	public boolean isChoicePseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isChoicePseudostate();
	}

	@Override
	public boolean isForkPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isForkPseudostate();
	}

	@Override
	public boolean isJoinPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isJoinPseudostate();
	}

	@Override
	public boolean isStubState() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isStubState();
	}

	@Override
	public boolean isEntryPointPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isEntryPointPseudostate();
	}

	@Override
	public boolean isExitPointPseudostate() {
		if(this.isFinalState()) {
			return false;
		}
		return this.pseudoState.isExitPointPseudostate();
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
	public boolean isFinalState() {
		return this.finalState != null;
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
	public boolean isFirstState() {
		return this.isFirstState;
	}

	@Override
	public void setIsFirstState(boolean bool) {
		this.isFirstState = bool;
	}
	
	
	
}
