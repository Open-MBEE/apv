package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.SubActivity;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;
import com.ref.parser.activityDiagram.ADUtils;

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

		for(IState st : this.states) {
			stringDo.append(defaultDo(st));
		}
		
		return stringDo.toString();
	}

	private String defineDoCompound(IState state) {
		StringBuilder stringDo = new StringBuilder();

		for(IState st : state.getSubstates()) {
			stringDo.append(defaultDo(st));
		}
		return stringDo.toString();
	}
	
	private String defaultDo (IState st) {
		StringBuilder stringDo = new StringBuilder();
		String[] splited;
		String set = "", get = "";
		
		if(st.getDoActivity() != "") {
			stringDo.append("\n");
			stringDo.append("DoProc(st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + ") = do.st_" + SMUtils.nameResolver(st.getName())  + "_" + SMUtils.nameResolver(smDiagram.getName()));

			set = "";
			get = "";
			String getSubActivity = "";
			
			if(st.getDoActivity().contains("invActivity")) {
				if (st.getDoActivity().split(";").length > 1) {
					//aa
					String[] splitSubActivity = st.getDoActivity().replace(" ", "").split(";");

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
					//

					splited = splitSubActivity[1].split("=");
					set = " -> set_" + SMUtils.nameResolver(splited[0])  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!(" + splited[1].replace(" ", "") + ")";
					if(arrayNameMemory != null) {
						for(String nameMem : arrayNameMemory) {
							if(splited[1].contains(nameMem)) {
								get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
							}
						}
					}
					
					//
					String sync = " -> (" + activityName + "(1) [|{|startActivity_" + activityName + ", endActivity_" + activityName + "|}|] "
							+ "(startActivity_"+activityName+".1!"+parametroEntrada+" ->  endActivity_"+activityName+".1?"+quemRecebeSaida+
							get + set + " -> SKIP))";

					stringDo.append(getSubActivity);
					stringDo.append(sync);
					stringDo.append("; LOOP /\\ interrupt.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " -> SKIP\n");
				}
			}else {
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
		}
		if(st.getSubstates() != null) {
			if(st.getSubstates().length > 0) {
				stringDo.append(defineDoCompound(st));
			}
		}

		return stringDo.toString();
	}
}
