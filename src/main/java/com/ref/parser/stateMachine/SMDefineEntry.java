package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineEntry {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;
	private ArrayList<String> arrayNameMemory;

	public SMDefineEntry(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions, ArrayList<String> arrayNameMemory) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
		this.arrayNameMemory = arrayNameMemory;
	}

	public String defineEntry() {
		StringBuilder stringEntry = new StringBuilder();
		String[] splited;
		String set = "", get = "";

		for(IState st : this.states) {
			if(st.getEntry() != "") {
				stringEntry.append("\n");
				stringEntry.append("EntryProc(st_" + SMUtils.nameResolver(st.getName() + "_" + SMUtils.nameResolver(smDiagram.getName()))
				+ ") = entry.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				set = "";
				get = "";
				splited = st.getEntry().split("=");
				set = " -> set_" + SMUtils.nameResolver(splited[0]) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"!(" + splited[1].replace(" ", "") + ")";
				if(arrayNameMemory != null) {
					for(String nameMem : arrayNameMemory) {
						if(splited[1].contains(nameMem)) {
							get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
						}
					}
				}

				stringEntry.append(get);
				stringEntry.append(set);
				stringEntry.append(" -> State_st_" + SMUtils.nameResolver(st.getName()) + "_Do_" +SMUtils.nameResolver(smDiagram.getName()) + "\n");
			}

			if(st.getSubstates() != null) {
				if(st.getSubstates().length > 0) {
					stringEntry.append(defineEntryCompound(st));
				}
			}
		}

		return stringEntry.toString();
	}

	private String defineEntryCompound(IState state) {
		StringBuilder stringEntry = new StringBuilder();
		String[] splited;
		String set = "", get = "";

		for(IState st : state.getSubstates()) {
			if(st.getEntry() != "") {
				stringEntry.append("\n");
				stringEntry.append("EntryProc(st_" + SMUtils.nameResolver(st.getName() + "_" + SMUtils.nameResolver(smDiagram.getName()))
				+ ") = entry.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				set = "";
				get = "";
				splited = st.getEntry().split("=");
				set = " -> set_" + SMUtils.nameResolver(splited[0]) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"!(" + splited[1].replace(" ", "") + ")";
				if(arrayNameMemory != null) {
					for(String nameMem : arrayNameMemory) {
						if(splited[1].contains(nameMem)) {
							get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
						}
					}
				}

				stringEntry.append(get);
				stringEntry.append(set);
				stringEntry.append(" -> State_st_" + SMUtils.nameResolver(st.getName()) + "_Do_" +SMUtils.nameResolver(smDiagram.getName()) + "\n");
			}

			if(st.getSubstates() != null) {
				if(st.getSubstates().length > 0) {
					stringEntry.append(defineEntryCompound(st));
				}
			}
		}

		return stringEntry.toString();
	}
}
