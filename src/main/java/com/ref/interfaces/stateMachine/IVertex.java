package com.ref.interfaces.stateMachine;

import com.ref.interfaces.INamedElement;

public interface IVertex extends INamedElement{
	ITransition[] getIncomings();
	
	ITransition[] getOutgoings();
	
	void setOutgoings(ITransition[] outgoings);
	
	void setIncomings(ITransition[] incomings);
	
	boolean isFirstState();
	
	void setIsFirstState(boolean bool);
}
