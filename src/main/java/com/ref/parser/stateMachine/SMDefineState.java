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
		String stateNameDiagram;
		String pseudostateName;
		String pseudostateNameDiagram;
		String processEntry;
		String processDo;
		String processExit;
		String processPseudo;
		if(this.states != null) {
			for(IState state : this.states) {
				stateName = "st_" + SMUtils.nameResolver(state.getName());
				stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());

				processEntry = "State_" + stateName + "_Entry_" + SMUtils.nameResolver(smDiagram.getName());
				processDo = "State_" + stateName + "_Do_" + SMUtils.nameResolver(smDiagram.getName());
				processExit = "State_" + stateName + "_Exit_" + SMUtils.nameResolver(smDiagram.getName());
				boolean flag = true;
				if(state.isFirstState()) {
					processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) 
					+ "(init) = (init & enter." + stateNameDiagram + "-> " + processEntry +")");
					processStates.append(" [] ");
					processStates.append("(not(init) & " + processEntry + ")\n");
				}else {
					processStates.append("\nState_" + stateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = enter." + stateNameDiagram + " -> " + processEntry + "\n");
				}
				processStates.append(processEntry + " = EntryProc(" + stateNameDiagram + ")\n");
				processStates.append(processDo + " = ((");
				if(state.getOutgoings()  != null) {
					for(ITransition out : state.getOutgoings()) {
						if(flag) {
							processStates.append("activateTr.tr_" + SMUtils.nameResolver(out.getId()) + " -> SKIP");
							flag = false;
						}else {
							processStates.append(" ||| activateTr.tr_" + SMUtils.nameResolver(out.getId()) + " -> SKIP");
						}
					}
				}
				processStates.append("); DoProc(" + stateNameDiagram + ")) /\\ exit." + stateNameDiagram + " -> " + processExit +"\n");
				processStates.append(processExit + "= ExitProc(" + stateNameDiagram + ")\n");
			}
		}
		//Pseudostates
		if(this.pseudostates != null) {
			for(IPseudostate pst : this.pseudostates) {
				if(pst.isChoicePseudostate() || pst.isJunctionPseudostate()) {
					
					pseudostateName = "st_" + SMUtils.nameResolver(pst.getName());
					pseudostateNameDiagram = pseudostateName + "_" + SMUtils.nameResolver(smDiagram.getName());

					processPseudo = "State_" + pseudostateName + "_Sync_" + SMUtils.nameResolver(smDiagram.getName());
					boolean flag = true;
					if(pst.isFirstState()) {
						processStates.append("\nState_" + pseudostateName + "_" + SMUtils.nameResolver(smDiagram.getName()) 
						+ "(init) = (init & enter." + pseudostateNameDiagram + "-> " + processPseudo +")");
						processStates.append(" [] ");
						processStates.append("(not(init) & " + processPseudo + ")\n");
					}else {
						processStates.append("\nState_" + pseudostateName + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = enter." + pseudostateNameDiagram + " -> " + processPseudo + "\n");
					}
					processStates.append(processPseudo + " = ((");
					if(pst.getOutgoings()  != null) {
						for(ITransition out : pst.getOutgoings()) {
							if(flag) {
								processStates.append("activateTr.tr_" + SMUtils.nameResolver(out.getId()) + " -> SKIP");
								flag = false;
							}else {
								processStates.append(" ||| activateTr.tr_" + SMUtils.nameResolver(out.getId()) + " -> SKIP");
							}
						}
					}
					if(pst.isFirstState()) {
						processStates.append(")) /\\ exit." + pseudostateNameDiagram + " -> State_" + pseudostateNameDiagram +"(true)\n");
					}else {
						processStates.append(")) /\\ exit." + pseudostateNameDiagram + " -> State_" + pseudostateNameDiagram +"\n");
					}
				}
			}
		}
		//^^
		return processStates.toString();
	}
}
