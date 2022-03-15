package com.ref.interfaces.stateMachine;

import com.ref.interfaces.INamedElement;

public interface IStateMachine extends INamedElement{
	IStateMachineDiagram getStateMachineDiagram();
	
	ITransition[] getTransitions();
	
	IState[] getStates();
	
	IPseudostate[] getPseudostates();
	
	void setName(String nameSM);
	
	void setStateMachineDiagram(IStateMachineDiagram stateMachineDiagram);
}
