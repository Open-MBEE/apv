package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.SubActivity;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;
import com.ref.parser.activityDiagram.ADUtils;

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
		for(IState st : this.states) {
			stringExit.append(defaultExit(st));
		}
		return stringExit.toString();
	}

	private String defineExitCompound(IState state) {
		StringBuilder stringExit = new StringBuilder();
		for(IState st : state.getSubstates()) {
			stringExit.append(defaultExit(st));
		}
		return stringExit.toString();
	}

	private String defaultExit(IState st) {
		StringBuilder stringExit = new StringBuilder();
		String[] splited;
		String set = "", get = "";
		if(st.getExit() != "") {
			stringExit.append("\n");
			stringExit.append("ExitProc(st_" + SMUtils.nameResolver(st.getName())  + "_" + SMUtils.nameResolver(smDiagram.getName()) + ") = " 
					+ "exit.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
			set = "";
			get = "";
			String getSubActivity = "";
			
			if(st.getExit().contains("invActivity")) {
				if (st.getExit().split(";").length > 1) {
					String[] splitSubActivity = st.getExit().replace(" ", "").split(";");

					int startIndex = splitSubActivity[0].indexOf("[");
					int endIndex = splitSubActivity[0].indexOf("]");
					String insideParentheses = splitSubActivity[0].substring(startIndex + 1, endIndex);

					String[] elements = insideParentheses.split(",");

					for (int i = 0; i < elements.length; i++) {
						elements[i] = elements[i].trim();
					}

					String activityName = ADUtils.nameResolver(elements[0]);
					SubActivity.getInstance().setSubActivityDiagram(activityName);
					String parametroEntrada = elements[1];
					String quemRecebeSaida = elements[2];
					
					if(arrayNameMemory != null) {
						for(String nameMem : arrayNameMemory) {
							if(parametroEntrada.equals(nameMem)) {
								getSubActivity = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
							}
						}
					}
					
					// After result
					splited = splitSubActivity[1].split("=");
					set = " -> set_" + SMUtils.nameResolver(splited[0])  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!(" + splited[1].replace(" ", "") + ")";
					if(arrayNameMemory != null) {
						for(String nameMem : arrayNameMemory) {
							if(splited[1].contains(nameMem)) {
								get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
							}
						}
					}
					//Sync
					String sync = " -> (" + activityName + "(1) [|{|startActivity_" + activityName + ", endActivity_" + activityName + "|}|] "
							+ "(startActivity_"+activityName+".1!"+parametroEntrada+" ->  endActivity_"+activityName+".1?"+quemRecebeSaida+
							get + set + " -> SKIP))";

					stringExit.append(getSubActivity);
					stringExit.append(sync);
					stringExit.append("; exited.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName())+ "\n");
				}
			}else {
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
			}
			

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
		return stringExit.toString();
	}
}
