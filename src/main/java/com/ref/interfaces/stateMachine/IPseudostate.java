package com.ref.interfaces.stateMachine;

public interface IPseudostate extends IVertex{
	
	boolean isInitialPseudostate();
	
	boolean isShallowHistoryPseudostate();
	
	boolean isDeepHistoryPseudostate();
	
	boolean isJunctionPseudostate();
	
	boolean isChoicePseudostate();
	
	boolean isForkPseudostate();
	
	boolean isJoinPseudostate();
	
	boolean isStubState();
	
	boolean isEntryPointPseudostate();
	
	boolean isExitPointPseudostate();
	
	boolean isFinalState();
}
