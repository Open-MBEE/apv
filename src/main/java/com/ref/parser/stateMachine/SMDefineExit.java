package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineExit {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;
	private ArrayList<String> arrayNameMemory;

	public SMDefineExit(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions,  ArrayList<String> arrayNameMemory) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
		this.arrayNameMemory = arrayNameMemory;
	}

	public String defineExit() {
		StringBuilder stringExit = new StringBuilder();
		String[] splited;
		String set = "", get = "";
		for(IState st : this.states) {
			if(st.getExit() != "") {
				stringExit.append("\n");
				stringExit.append("ExitProc(st_" + SMUtils.nameResolver(st.getName())  + "_" + SMUtils.nameResolver(smDiagram.getName()) + ") = " 
						+ "exit.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				set = "";
				get = "";
				splited = st.getExit().split("=");
				set = " -> set_" + SMUtils.nameResolver(splited[0])  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!(" + splited[1].replace(" ", "") + ")";
				if(arrayNameMemory != null) {
					for(String nameMem : arrayNameMemory) {
						if(splited[1].contains(nameMem)) {
							get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
						}
					}
				}

				stringExit.append(get);
				stringExit.append(set);

				stringExit.append(" -> exited.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName())); 

				if(st.isFirstState()) {
					stringExit.append(" -> State_st_" + SMUtils.nameResolver(st.getName()) + "_"+ SMUtils.nameResolver(smDiagram.getName()) + "\n");
				}else {
					stringExit.append(" -> State_st_"
							+ SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"\n");
				}

			}
			if(st.getSubstates() != null) {
				if(st.getSubstates().length > 0) {
					stringExit.append(defineExitCompound(st));
				}
			}

		}
		return stringExit.toString();
	}

	private String defineExitCompound(IState state) {
		StringBuilder stringExit = new StringBuilder();
		String[] splited;
		String set = "", get = "";
		for(IState st : state.getSubstates()) {
			if(st.getExit() != "") {
				stringExit.append("\n");
				stringExit.append("ExitProc(st_" + SMUtils.nameResolver(st.getName())  + "_" + SMUtils.nameResolver(smDiagram.getName()) + ") = " 
						+ "exit.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				set = "";
				get = "";
				splited = st.getExit().split("=");
				set = " -> set_" + SMUtils.nameResolver(splited[0])  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!(" + splited[1].replace(" ", "") + ")";
				if(arrayNameMemory != null) {
					for(String nameMem : arrayNameMemory) {
						if(splited[1].contains(nameMem)) {
							get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
						}
					}
				}

				stringExit.append(get);
				stringExit.append(set);

				stringExit.append(" -> exited.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName())); 

				if(st.isFirstState()) {
					stringExit.append(" -> State_st_" + SMUtils.nameResolver(st.getName()) + "_"+ SMUtils.nameResolver(smDiagram.getName()) + "\n");
				}else {
					stringExit.append(" -> State_st_"
							+ SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"\n");
				}
			}
			if(st.getSubstates() != null) {
				if(st.getSubstates().length > 0) {
					stringExit.append(defineExitCompound(st));
				}
			}

		}
		return stringExit.toString();
	}
}
