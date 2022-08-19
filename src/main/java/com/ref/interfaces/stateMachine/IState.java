package com.ref.interfaces.stateMachine;

public interface IState extends IVertex{
	IStateMachine getSubmachine();
	
	boolean isSubmachineState();
	
	String getEntry();
	
	String getDoActivity();
	
	String getExit();
	
	//ITransition[] getInternalTransitions();
	
	IState[] getSubstates();
	
	IPseudostate[] getSubpseudostates();
	
	IVertex[] getSubvertexes(int regionIndex);
	
	void setEntry(String entry);
	
	void setDoActivity(String doActivity);
	
	void setExit(String exit);
	
	void setSubstates(IState[] substate);
	
	void setSubpseudostates(IPseudostate[] subpseudostates);
	
	boolean isCompound();
	
	void setIsCompound(boolean b);

	//void setInternalTransitions(ITransition[] internal);
	
	//void addInternalTransition(String event, String guard, String action);
	
	//void deleteAllInternalTransitions();
	
}
