package com.ref.parser.stateMachine;

import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineState {
	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private IPseudostate[] pseudostates;
	private IPseudostate initialNode;

	public SMDefineState(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, IPseudostate[] pseudostates, IPseudostate initialNode) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.pseudostates = sm.getPseudostates();
		this.initialNode = initialNode;
	}

	public String defineStates() {
		StringBuilder processStates = new StringBuilder();
		String stateName;
		String stateInit;
		String stateNameDiagram;
		String controlProcess;
		String pseudostateName;
		String pseudostateNameDiagram;
		String processInit;
		String processEntry;
		String processDo;
		String processExit;
		String processPseudo;
		if(this.states != null) {
			for(IState state : this.states) {
				if(!state.isCompound()) {
					stateName = "st_" + SMUtils.nameResolver(state.getName());
					stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());

					if(state.getEntry() != "") {
						processEntry = "State_" + stateName + "_Entry_" + SMUtils.nameResolver(smDiagram.getName());
						processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processEntry + ") [] (end -> SKIP)\n");
						processStates.append(processEntry + " = EntryProc(" + stateNameDiagram + ")\n");
					}else {
						processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
						processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processDo + ") [] (end -> SKIP)\n");
					}
					
					if(state.getDoActivity() != "") {
						processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
						processStates.append(processDo + " = (");
						processStates.append("DoProc(" + stateNameDiagram + ")); ");
					}else {
						processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
						processStates.append(processDo + " = ");
						processStates.append("interrupt."+ stateNameDiagram + " -> SKIP; ");
					}
					
					if(state.getExit() != "") {
						processStates.append("ExitProc(" + stateNameDiagram + ")\n");
					}else {
						processStates.append("exit." + stateNameDiagram + " -> exited." + stateNameDiagram + " -> State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
					}
				}else {
					stateInit = "State_st_" + SMUtils.nameResolver(state.getName() + "_init_" + SMUtils.nameResolver(smDiagram.getName()));
					stateName =  "st_" + SMUtils.nameResolver(state.getName());
					stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
					controlProcess = "Control_" +  stateNameDiagram;
					processInit = stateInit + " = State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "[|{|interrupt." + stateNameDiagram + getChildrenEventInternal(state) + ", end|}|]" + controlProcess + "(<>)";
					processEntry = "State_" + stateName + "_Entry_" + SMUtils.nameResolver(smDiagram.getName());
					processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
					
					processStates.append("\n" + processInit);
					if(state.getEntry() != "") {
						processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processEntry + ") [] (end -> SKIP)\n");
						processStates.append(processEntry + " = EntryProc(" + stateNameDiagram + ")\n");
					}else {
						processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processDo + ") [] (end -> SKIP)\n");
					}
					
					if(state.getDoActivity() != "") {
						processStates.append(processDo + " = (");
						processStates.append("DoProc(" + stateNameDiagram + ") " +  "[|{|" + getInterrupt(state, true) + "|}|]" + "StartSync_Compound_" + stateNameDiagram +  ") \\ {end}; ");
					}else {
						processStates.append(processDo + " = (");
						processStates.append("(interrupt." + stateNameDiagram + " -> SKIP) [|{|" + getInterrupt(state, true) + "|}|]" + "StartSync_Compound_" + stateNameDiagram +  ") \\ {end}; ");
					}
					
					if(state.getExit() != "") {
						processStates.append("ExitProc(" + stateNameDiagram + ")\n");
					}else {
						processStates.append("exit." + stateNameDiagram + " -> exited." + stateNameDiagram + " -> State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
					}
					
					processStates.append(defineStates(state));
				}
			}
		}
		//Pseudostates
		//TODO ALTERAR PSEUDOESTADOS PARA NOVO CÓDIGO
		if(this.pseudostates != null) {
			for(IPseudostate pst : this.pseudostates) {
				if(pst.isChoicePseudostate() || pst.isJunctionPseudostate()) {
					
					stateName = "st_" + SMUtils.nameResolver(pst.getName());
					stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());

					processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
					
					processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processDo + ") [] (end -> SKIP)\n");
					processStates.append(processDo + " = (");
					processStates.append("interrupt." + stateNameDiagram + " -> SKIP); exit." + stateNameDiagram + " -> exited." + stateNameDiagram + " -> State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
				}
			}
		}
		//^^
		return processStates.toString();
	}
	
	private String defineStates(IState st) {
		StringBuilder processStates = new StringBuilder();
		String stateName;
		String stateInit;
		String controlProcess;
		String processInit;
		String stateNameDiagram;
		String pseudostateName;
		String pseudostateNameDiagram;
		String processEntry;
		String processDo;
		String processExit;
		String processPseudo;
		if(st.getSubstates() != null) {
			if(st.getSubstates().length > 0) {
				for(IState state : st.getSubstates()) {
					if(!state.isCompound()) {
						stateName = "st_" + SMUtils.nameResolver(state.getName());
						stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());

						if(state.getEntry() != "") {
							processEntry = "State_" + stateName + "_Entry_" + SMUtils.nameResolver(smDiagram.getName());
							processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processEntry + ") [] (end -> SKIP)\n");
							processStates.append(processEntry + " = EntryProc(" + stateNameDiagram + ")\n");
						}else {
							processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
							processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processDo + ") [] (end -> SKIP)\n");
						}
						
						if(state.getDoActivity() != "") {
							processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
							processStates.append(processDo + " = (");
							processStates.append("DoProc(" + stateNameDiagram + ")); ");
						}else {
							processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName()); 
							processStates.append(processDo + " = ");
							processStates.append("interrupt."+ stateNameDiagram + " -> SKIP; ");
						}
						
						if(state.getExit() != "") {
							processStates.append("ExitProc(" + stateNameDiagram + ")\n");
						}else {
							processStates.append("exit." + stateNameDiagram + " -> exited." + stateNameDiagram + " -> State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
						}
					}else {
						// TODO - Estado composto, adaptar ao novo código
						stateInit = "State_st_" + SMUtils.nameResolver(state.getName() + "_init_" + SMUtils.nameResolver(smDiagram.getName()));
						stateName =  "st_" + SMUtils.nameResolver(state.getName());
						stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
						controlProcess = "Control_" +  stateNameDiagram;
						processInit = stateInit + " = State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "[|{|interrupt." + stateNameDiagram + getChildrenEventInternal(state) + ", end|}|]" + controlProcess + "(<>)" ;
						processEntry = "State_" + stateName + "_Entry_" + SMUtils.nameResolver(smDiagram.getName());
						processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
						
						processStates.append("\n" + processInit);
						if(state.getEntry() != "") {
							processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processEntry + ") [] (end -> SKIP)\n");
							processStates.append(processEntry + " = EntryProc(" + stateNameDiagram + ")\n");
						}else {
							processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processDo + ") [] (end -> SKIP)\n");
						}
						
						if(state.getDoActivity() != "") {
							processStates.append(processDo + " = (");
							processStates.append("DoProc(" + stateNameDiagram + ") " +  "[|{|" + getInterrupt(state, true) + "|}|]" + "StartSync_Compound_" + stateNameDiagram +  ") \\ {end}; ");
						}else {
							processStates.append(processDo + " = (");
							processStates.append("(interrupt." + stateNameDiagram + " -> SKIP) [|{|" + getInterrupt(state, true) + "|}|]" + "StartSync_Compound_" + stateNameDiagram +  ") \\ {end}; ");
						}
						
						if(state.getExit() != "") {
							processStates.append("ExitProc(" + stateNameDiagram + ")\n");
						}else {
							processStates.append("exit." + stateNameDiagram + " -> exited." + stateNameDiagram + " -> State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
						}
						
						processStates.append(defineStates(state));
					}
					
					
				}
			}
		}
		//Pseudostates
		if(st.getSubpseudostates() != null) {
			if(st.getSubpseudostates().length > 0) {
				for(IPseudostate pst : st.getSubpseudostates()) {
					if(pst.isChoicePseudostate() || pst.isJunctionPseudostate()) {
						
						stateName = "st_" + SMUtils.nameResolver(pst.getName());
						stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());

						processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
						
						processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (enter." + stateNameDiagram + " -> " + processDo + ") [] (end -> SKIP)\n");
						processStates.append(processDo + " = (");
						processStates.append("interrupt." + stateNameDiagram + " -> SKIP); exit." + stateNameDiagram + " -> exited." + stateNameDiagram + " -> State_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
					}
				}
			}
		}
		return processStates.toString();
	}
	
	private String getInterrupt(IState state, boolean flag) {
		StringBuilder stringInterrupts = new StringBuilder();
		
		if(flag) {
			stringInterrupts.append("interrupt.st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
		}else {
			stringInterrupts.append(", interrupt.st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
		}
		
		if(state.getSuperiorState() != null) {
			stringInterrupts.append(getInterrupt(state.getSuperiorState(), false));
		}
		
		
		
		return stringInterrupts.toString();
	}
	
	private String getChildrenEventInternal(IState state) {
		StringBuilder stringEvents = new StringBuilder();
		String stateName = "st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName());
		
		stringEvents.append(", enter." + stateName + ", exit." + stateName + ", exited." + stateName);
		if(state.isCompound()) {
			if(state.getSubstates() != null) {
				if(state.getSubstates().length > 0) {
					for(IState st : state.getSubstates()) {
						if(st.isCompound()) {
							stringEvents.append(getChildrenEventInternal(st));
						}else {
							stateName = "st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName());
							stringEvents.append(", enter." + stateName + ", exit." + stateName + ", exited." + stateName);
						}
					}
				}
			}
			
			if(state.getSubpseudostates() != null) {
				if(state.getSubpseudostates().length > 0) {
					for(IPseudostate pst : state.getSubpseudostates()) {
						if(pst.isChoicePseudostate() || pst.isJunctionPseudostate()) {
							stateName = "st_" + SMUtils.nameResolver(pst.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName());
							stringEvents.append(", enter." + stateName + ", exit." + stateName + ", exited." + stateName);
						}
					}
				}
			}
		}
		return stringEvents.toString();
	}
}
