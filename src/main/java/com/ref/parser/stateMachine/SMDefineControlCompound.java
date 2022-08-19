package com.ref.parser.stateMachine;

import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;

public class SMDefineControlCompound {
	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private IPseudostate[] pseudostates;
	private IPseudostate initialNode;

	public SMDefineControlCompound(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, IPseudostate[] pseudostates, IPseudostate initialNode) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.pseudostates = sm.getPseudostates();
		this.initialNode = initialNode;
	}
	
	public String defineControlCompounds() {
		StringBuilder stringControlCompounds = new StringBuilder();
		
		stringControlCompounds.append("\nControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n");
		
		for(IState state : this.states) {
			if(state.isCompound()) {
				stringControlCompounds.append(defineControlCompounds(state));
			}
		}
		return stringControlCompounds.toString();
	}
	
	private String defineControlCompounds(IState st) {
		StringBuilder stringControlCompound = new StringBuilder();
		if(st.isCompound()) {
				String nameProcess = "Control_st_" + SMUtils.nameResolver(st.getName() + "_" + SMUtils.nameResolver(smDiagram.getName()));
				String nameActualState = "st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName());
				int depthSize = depthSize(st) + 3;
				stringControlCompound.append(nameProcess +"(s) = #s<" + depthSize + " & enter." + nameActualState + " -> " + nameProcess +"(<exit." + nameActualState + ",exited." + nameActualState + ">^s)\n"
						+ "			 [] \n"
						+ "			 #s<" + depthSize + " & enter?state:{" + getAllChildren(st)+ "} -> " + nameProcess +"(<exit.state, exited.state>^s)\n"
						+ "          	 []\n"
						+ "			 #s>0 & head(s) -> " + nameProcess + "(tail(s))\n"
						+ "			 []\n"
						+ "			 #s<" + depthSize + " & interrupt." + nameActualState +" -> ControlAux(s); " + nameProcess + "(<>)\n"
						+ "			 []\n"
						+ "		 	 end -> SKIP\n\n");
		}
		
		if(st.getSubstates() != null) {
			if(st.getSubstates().length > 0) {
				for(IState state : st.getSubstates()) {
					if(state.isCompound()) {
						stringControlCompound.append(defineControlCompounds(state));
					}
				}
			}
		}
		
		return stringControlCompound.toString();
	}
	
	private int depthSize(IState st) {
		int size = 0;
		int auxSize = 0;
		if(st.isCompound()) {
			size += 2;
			for(IState state: st.getSubstates()) {
				auxSize = 2;
				auxSize += depthSize(state);
				if(auxSize > size) {
					size = auxSize;
				}
			}
		}
		return size;
	}
	
	private String getAllChildren(IState st) {
		StringBuilder allChildren = new StringBuilder();
		boolean flag = true;
		if(st.getSubpseudostates() != null) {
			if(st.getSubpseudostates().length > 0) {
				for(IPseudostate pst : st.getSubpseudostates()) {
					if(!(pst.isInitialPseudostate() || pst.isFinalState())) {
						if(flag) {
							allChildren.append("st_" + SMUtils.nameResolver(pst.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
							flag = false;
						}else {
							allChildren.append(", st_" + SMUtils.nameResolver(pst.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
						}
					}
				}
			}
		}
		
		if(st.getSubstates() != null) {
			if(st.getSubstates().length > 0) {
				for(IState state : st.getSubstates()) {
					if(flag) {
						allChildren.append("st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
						flag = false;
					}else {
						allChildren.append(", st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
					}
					
					if(state.isCompound()) {
						allChildren.append(", " + getAllChildren(state));
					}
				}
			}
		}
		
		return allChildren.toString();
	}
}
