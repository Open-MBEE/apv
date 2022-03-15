package com.ref.interfaces.stateMachine;

import com.ref.interfaces.INamedElement;

public interface ITransition extends INamedElement{
	IVertex getSource();
	
	IVertex getTarget();
	
	String getGuard();
	
	String getAction();
	
	String getTrigger();
	
	void setGuard(String guard);
	
	void setAction(String action);
	
	void setTrigger(String event);
	
	void setSource(IVertex source);
	
	void setTarget(IVertex target);
}
