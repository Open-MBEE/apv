package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineStartSync {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;
	private ArrayList<String> arrayTurn;
	private ArrayList<String> arrayMemorySync;

	public SMDefineStartSync(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions, ArrayList<String> arrayTurn, ArrayList<String> arrayMemorySync) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
		this.arrayTurn = arrayTurn;
		this.arrayMemorySync = arrayMemorySync;
	}

	public String defineStartSyncProc() {
		StringBuilder stringStartSync = new StringBuilder();
		boolean flag = true;
		String idInitialState = "";
		stringStartSync.append("\nStates_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
		for(IState st : this.states) {
			if(st.isFirstState()) {
				idInitialState = st.getId();
				if(flag) {
					stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + "(false)");
					flag = false;
				}else {
					stringStartSync.append(" ||| State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + "(false)");
				}
			}else {
				if(flag) {
					stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
					flag = false;
				}else {
					stringStartSync.append(" ||| State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				}
			}
		}
		//MUDANÇA
		for(IPseudostate st : sm.getPseudostates()) {
			if(!(st.isInitialPseudostate() || st.isFinalState())) {
				if(st.isFirstState()) {
					idInitialState = st.getId();
					if(flag) {
						stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + "(false)");
						flag = false;
					}else {
						stringStartSync.append(" ||| State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + "(false)");
					}
				}else {
					if(flag) {
						stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
						flag = false;
					}else {
						stringStartSync.append(" ||| State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
					}
				}
			}
		}
		//MUDANÇA^^
		stringStartSync.append(")\n");
		flag = true;
		stringStartSync.append("Transitions_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
		String nameSource; 
		String nameTarget;
		String nameActivateTr;
		String nameTrigger;

		//TODO NULL POINTER
		for(IState st : this.states) {
			if(st.getOutgoings().length == 1) {
				nameSource = "st_" + SMUtils.nameResolver(st.getName());
				if(st.getOutgoings()[0].getTarget() != null) {
					nameTarget = "st_" + SMUtils.nameResolver(st.getOutgoings()[0].getTarget().getName());	
				}else {
					nameTarget = nameSource;
				}
				nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(st.getOutgoings()[0].getId());
				if(arrayTurn.contains(nameActivateTr)) {
					if(flag) {
						stringStartSync.append("(Tr_Turn_" + nameActivateTr + ")");
						flag = false;
					}else {
						stringStartSync.append(" ||| (Tr_Turn_" + nameActivateTr + ")");
					}
				}
			}
		}

		//MUDDANÇA
		for(IPseudostate st : sm.getPseudostates()) {
			if(!(st.isFinalState() || st.isInitialPseudostate())) {
				if(st.getOutgoings().length == 1) {
					nameSource = "st_" + SMUtils.nameResolver(st.getName());
					if(st.getOutgoings()[0].getTarget() != null) {
						nameTarget = "st_" + SMUtils.nameResolver(st.getOutgoings()[0].getTarget().getName());	
					}else {
						nameTarget = nameSource;
					}
					nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(st.getOutgoings()[0].getId());
					if(arrayTurn.contains(nameActivateTr)) {
						if(flag) {
							stringStartSync.append("(Tr_Turn_" + nameActivateTr + ")");
							flag = false;
						}else {
							stringStartSync.append(" ||| (Tr_Turn_" + nameActivateTr + ")");
						}
					}
				}
			}
		}
		//^^ 
		ArrayList<String> arrayConfirmedTurn = new ArrayList<String>();
		ArrayList<String> arrayConfirmedEvent = new ArrayList<String>();
		StringBuilder parentesesIniciais = new StringBuilder();
		boolean auxFlagEvent= true;
		boolean auxFlagTurn = true;

		for(IState st : this.states) {
			if(st.getOutgoings().length > 1) {
				for(ITransition tr : st.getOutgoings()) {
					nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
					if(tr.getTarget() != null) {
						nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
					}else {
						nameTarget = nameSource;
					}
					nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
					nameTrigger = SMUtils.nameResolver(tr.getTrigger());
					if(arrayTurn.contains(nameActivateTr)) {
						arrayConfirmedTurn.add(nameActivateTr);
						if(tr.getTrigger() != "") {
							arrayConfirmedEvent.add(nameTrigger + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							arrayConfirmedEvent.add("internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}
					}
				}

			}

			for(int i = 0; i < (arrayConfirmedTurn.size() - 1); i++) {
				parentesesIniciais.append("(");
			}
			auxFlagTurn = true;
			auxFlagEvent = true;
			for(String turn : arrayConfirmedTurn) {
				auxFlagEvent = true;
				if(flag) {
					stringStartSync.append(parentesesIniciais);
					stringStartSync.append(" Tr_Turn_"+ turn);
					auxFlagTurn = false;
				}else {
					if(auxFlagTurn) {
						auxFlagTurn = false;
						stringStartSync.append(" ||| ");
						stringStartSync.append(parentesesIniciais);
						stringStartSync.append("Tr_Turn_" + turn);
					}else {
						stringStartSync.append(" [|{|");
						for(String event : arrayConfirmedEvent) {
							if(auxFlagEvent) {
								stringStartSync.append(event);
								auxFlagEvent = false;
							}else {
								stringStartSync.append(", " + event);
							}
						}
						stringStartSync.append("|}|] Tr_Turn_" + turn + ")");
					}
				}
			}
			parentesesIniciais.delete(0, parentesesIniciais.length());
			arrayConfirmedTurn.clear();
			arrayConfirmedEvent.clear();
		}

		//MUDANÇA~^
		for(IPseudostate st : sm.getPseudostates()) {
			if(!(st.isFinalState() || st.isInitialPseudostate())) {
				if(st.getOutgoings().length > 1) {
					for(ITransition tr : st.getOutgoings()) {
						nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
						if(tr.getTarget() != null) {
							nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
						}else {
							nameTarget = nameSource;
						}
						nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
						nameTrigger = SMUtils.nameResolver(tr.getTrigger());
						if(arrayTurn.contains(nameActivateTr)) {
							arrayConfirmedTurn.add(nameActivateTr);
							if(tr.getTrigger() != "") {
								arrayConfirmedEvent.add(nameTrigger + ".tr_" + SMUtils.nameResolver(tr.getId()));
							}else {
								arrayConfirmedEvent.add("internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
							}
						}
					}
				}
			}

			for(int i = 0; i < (arrayConfirmedTurn.size() - 1); i++) {
				parentesesIniciais.append("(");
			}
			auxFlagTurn = true;
			auxFlagEvent = true;
			for(String turn : arrayConfirmedTurn) {
				auxFlagEvent = true;
				if(flag) {
					stringStartSync.append(parentesesIniciais);
					stringStartSync.append(" Tr_Turn_"+ turn);
					auxFlagTurn = false;
				}else {
					if(auxFlagTurn) {
						auxFlagTurn = false;
						stringStartSync.append(" ||| ");
						stringStartSync.append(parentesesIniciais);
						stringStartSync.append("Tr_Turn_" + turn);
					}else {
						stringStartSync.append(" [|{|");
						for(String event : arrayConfirmedEvent) {
							if(auxFlagEvent) {
								stringStartSync.append(event);
								auxFlagEvent = false;
							}else {
								stringStartSync.append(", " + event);
							}
						}
						stringStartSync.append("|}|] Tr_Turn_" + turn + ")");
					}
				}
			}
			parentesesIniciais.delete(0, parentesesIniciais.length());
			arrayConfirmedTurn.clear();
			arrayConfirmedEvent.clear();
		}
		//^^
		stringStartSync.append(")\n");


		if(this.arrayMemorySync.size() != 0) {
			parentesesIniciais.delete(0, parentesesIniciais.length());
			for(int i=0; i < this.arrayMemorySync.size(); i++) {
				parentesesIniciais.append("(");
			}
			stringStartSync.append("\nStartSync_" + SMUtils.nameResolver(smDiagram.getName()) + " =" + parentesesIniciais + "(States_" + SMUtils.nameResolver(smDiagram.getName()) + " [|{|activateTr, exit, exited, enter|}|] Transitions_" 
					+ SMUtils.nameResolver(smDiagram.getName()) + ")");  
			for(String mem : this.arrayMemorySync) {
				stringStartSync.append(mem);
			}
			stringStartSync.append("/\\ finalState_" + SMUtils.nameResolver(smDiagram.getName()) + " -> SKIP \n\n");
		}else {
			stringStartSync.append("\nStartSync_" + SMUtils.nameResolver(smDiagram.getName()) + "= (States_" + SMUtils.nameResolver(smDiagram.getName()) + " [|{|activateTr, exit, exited, enter|}|] Transitions_" + SMUtils.nameResolver(smDiagram.getName()) +") /\\ finalState_" + SMUtils.nameResolver(smDiagram.getName()) + "  -> SKIP\n\n");
		}
		stringStartSync.append("FinalProcess_" + SMUtils.nameResolver(smDiagram.getName()) + " = final_" + SMUtils.nameResolver(smDiagram.getName()) +  "-> finalState_" + SMUtils.nameResolver(smDiagram.getName()) +  "-> SKIP\n\n");

		stringStartSync.append("Start_" + SMUtils.nameResolver(smDiagram.getName()) + " = (StartSync_" + SMUtils.nameResolver(smDiagram.getName()) + " [|{|final_"+ SMUtils.nameResolver(smDiagram.getName()) + ",finalState_" + SMUtils.nameResolver(smDiagram.getName()) +"|}|] FinalProcess_" + SMUtils.nameResolver(smDiagram.getName()) + ")");


		return stringStartSync.toString();
	}
}
