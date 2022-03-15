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
		
		stringDo.append("\n");
		for(IState st : this.states) {
			stringDo.append("DoProc(st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + ") = do.st_" + SMUtils.nameResolver(st.getName())  + "_" + SMUtils.nameResolver(smDiagram.getName()));
			
			if(st.getDoActivity() != "") {
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
			}
			stringDo.append(" -> SKIP\n");
		}
		return stringDo.toString();
	}
}
