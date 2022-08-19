package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineDo {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;
	private ArrayList<String> arrayNameMemory;

	public SMDefineDo(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions, ArrayList<String> arrayNameMemory) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
		this.arrayNameMemory = arrayNameMemory;
	}

	public String defineDo() {
		StringBuilder stringDo = new StringBuilder();
		String[] splited;
		String set = "", get = "";

		for(IState st : this.states) {
			if(st.getDoActivity() != "") {
				stringDo.append("\n");
				stringDo.append("DoProc(st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + ") = do.st_" + SMUtils.nameResolver(st.getName())  + "_" + SMUtils.nameResolver(smDiagram.getName()));

				set = "";
				get = "";
				splited = st.getDoActivity().split("=");
				set = " -> set_" + SMUtils.nameResolver(splited[0])  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!(" + splited[1].replace(" ", "") + ")";
				if(arrayNameMemory != null) {
					for(String nameMem : arrayNameMemory) {
						if(splited[1].contains(nameMem)) {
							get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
						}
					}
				}

				stringDo.append(get);
				stringDo.append(set);
				stringDo.append(" -> LOOP /\\ interrupt.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " -> SKIP\n");
			}
			if(st.getSubstates() != null) {
				if(st.getSubstates().length > 0) {
					stringDo.append(defineDoCompound(st));
				}
			}
		}
		return stringDo.toString();
	}

	private String defineDoCompound(IState state) {
		StringBuilder stringDo = new StringBuilder();
		String[] splited;
		String set = "", get = "";

		for(IState st : state.getSubstates()) {
			if(st.getDoActivity() != "") {
				stringDo.append("\n");
				stringDo.append("DoProc(st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + ") = do.st_" + SMUtils.nameResolver(st.getName())  + "_" + SMUtils.nameResolver(smDiagram.getName()));

				set = "";
				get = "";
				splited = st.getDoActivity().split("=");
				set = " -> set_" + SMUtils.nameResolver(splited[0])  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!(" + splited[1].replace(" ", "") + ")";
				if(arrayNameMemory != null) {
					for(String nameMem : arrayNameMemory) {
						if(splited[1].contains(nameMem)) {
							get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
						}
					}
				}

				stringDo.append(get);
				stringDo.append(set);
				stringDo.append(" -> LOOP /\\ interrupt.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " -> SKIP\n");
			}
			if(st.getSubstates() != null) {
				if(st.getSubstates().length > 0) {
					stringDo.append(defineDoCompound(st));
				}
			}
		}
		return stringDo.toString();
	}
}
