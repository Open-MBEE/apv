package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineChannel {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;
	private ArrayList<String> auxTurnList;
	private ArrayList<String> arrayMemoryChannel;
	private ArrayList<String> arrayTriggers;

	public SMDefineChannel(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions, ArrayList<String> auxTurnList,ArrayList<String> arrayMemoryChannel) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
		this.auxTurnList = auxTurnList;
		this.arrayMemoryChannel = arrayMemoryChannel;
		this.arrayTriggers = new ArrayList<String>();
	}

	public String defineChannel() {
		StringBuilder stringChannel = new StringBuilder();
		StringBuilder trigger = new StringBuilder();

		boolean flag = true;
		for(IState st : this.states) {
			if(flag) {
				stringChannel.append("datatype STATES_ID_" + SMUtils.nameResolver(smDiagram.getName()) + " = ");
				stringChannel.append("st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				flag = false;
			}else {
				stringChannel.append(" | st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
			}
		}
		//MODIFICAÇÃO
		for(IPseudostate pst : this.sm.getPseudostates()) {
			if(!(pst.isInitialPseudostate() || pst.isFinalState())) {
				if(flag) {
					stringChannel.append("datatype STATES_ID_" + SMUtils.nameResolver(smDiagram.getName()) + " = ");
					stringChannel.append("st_" + SMUtils.nameResolver(pst.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
					flag = false;
				}else {
					stringChannel.append(" | st_" + SMUtils.nameResolver(pst.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				}
			}
		}
		//^^
		flag = true;
		for(ITransition tr : this.transitions) {
			if(!(tr.getSource() instanceof IPseudostate)) {
				if(flag) {
					stringChannel.append("\ndatatype TR_ID_" + SMUtils.nameResolver(smDiagram.getName()) + " = ");
					stringChannel.append("tr_" + SMUtils.nameResolver(tr.getId()));
					flag = false;
				}else {
					stringChannel.append(" | tr_" + SMUtils.nameResolver(tr.getId()));
				} 
			}//MODIFICAÇÃO
			else {
				IPseudostate p =  (IPseudostate) tr.getSource();

				if(!(p.isInitialPseudostate() || p.isFinalState())) {
					if(flag) {
						stringChannel.append("\ndatatype TR_ID_" + SMUtils.nameResolver(smDiagram.getName()) + " = ");
						stringChannel.append("tr_" + SMUtils.nameResolver(tr.getId()));
						flag = false;
					}else {
						stringChannel.append(" | tr_" + SMUtils.nameResolver(tr.getId()));
					}
				}
			}
			//^^
		}

		stringChannel.append("\n\nchannel activateTr: TR_ID_" + SMUtils.nameResolver(smDiagram.getName()) + "\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");

		stringChannel.append("channel final_" + SMUtils.nameResolver(smDiagram.getName()) + ", finalState_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");

		flag = true;
		for(ITransition tr : this.transitions) {
			if(!arrayTriggers.contains(tr.getTrigger())) {
				arrayTriggers.add(tr.getTrigger());
				if(SMUtils.nameResolver(tr.getTrigger()) != "") {
					if(flag) {
						trigger.append("channel ");
						trigger.append(SMUtils.nameResolver(tr.getTrigger()));
						flag = false;
					}else {
						trigger.append(", " + SMUtils.nameResolver(tr.getTrigger()));
					}
				}
			}
		}
		
		for(String aux : auxTurnList) {
			if(!arrayTriggers.contains(SMUtils.nameResolver(aux))) {
				arrayTriggers.add(SMUtils.nameResolver(aux));
				if(SMUtils.nameResolver(aux) != "") {
					if(flag) {
						trigger.append("channel ");
						trigger.append(SMUtils.nameResolver(aux));
						flag = false;
					}else {
						trigger.append(", " + SMUtils.nameResolver(aux));
					}
				}
			}
		}
		
		stringChannel.append(trigger + " : TR_ID_" + SMUtils.nameResolver(smDiagram.getName()));
		stringChannel.append("\n");
		if(arrayMemoryChannel.size() > 0) {
			for(String memChan : arrayMemoryChannel) {
				stringChannel.append(memChan);
				stringChannel.append("\n");
			}
		}

		return stringChannel.toString();
	}
}
