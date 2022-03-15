package com.ref.interfaces.stateMachine;

public interface IState extends IVertex{
	IStateMachine getSubmachine();
	
	//boolean isSubmachineState();
	
	String getEntry();
	
	String getDoActivity();
	
	String getExit();
	
	//ITransition[] getInternalTransitions();
	
	//IVertex[] getSubvertexes();
	
	//IVertex[] getSubvertexes(int regionIndex);
	
	void setEntry(String entry);
	
	void setDoActivity(String doActivity);
	
	void setExit(String exit);
	
	//void addInternalTransition(String event, String guard, String action);
	
	//void deleteAllInternalTransitions();
	
}
