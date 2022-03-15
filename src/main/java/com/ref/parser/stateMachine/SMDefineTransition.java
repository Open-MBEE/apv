package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineTransition {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;
	private ArrayList<String> arrayNameMemory;

	public SMDefineTransition(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions, ArrayList<String> arrayNameMemory) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
		this.arrayNameMemory = arrayNameMemory;
	}

	public String defineTransition() {
		StringBuilder stringTr = new StringBuilder();
		String set = "",get = "";
		String splited[];
		
		for(ITransition tr : this.transitions) {
			//Adicionar um for que percorra os outgoings do source e adicioneo-s num array list para serem verificados depois
			if(!(tr.getSource() instanceof IPseudostate)) {
				String nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
				String nameTarget = "";
				String nameSourceDiagram = nameSource + "_" + SMUtils.nameResolver(smDiagram.getName());
				
				if(tr.getTarget() != null) {
					nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
				}else {
					nameTarget = nameSource;
				}
				
				String nameTargetDiagram = nameTarget + "_" + SMUtils.nameResolver(smDiagram.getName());

				String nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
				String nameTrigger = SMUtils.nameResolver(tr.getTrigger());

				smu.addTurnName(nameActivateTr);
				String trId = "tr_" + SMUtils.nameResolver(tr.getId());

				stringTr.append("\nTr_Turn_" + nameActivateTr + 
						" = activateTr." + trId + " -> (" );

				if(nameTrigger != "") {
					stringTr.append("(" + nameTrigger + ".tr_" + SMUtils.nameResolver(tr.getId()) + " -> exit." + nameSourceDiagram  + " -> exited." + nameSourceDiagram + " -> "); 
				}else {
					if(!(smu.getArrayAuxTurn().contains("internal"))) {
						smu.addAuxTurn("internal_" + SMUtils.nameResolver(smDiagram.getName()));
					}
					stringTr.append("(internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
					stringTr.append(" -> exit." + nameSourceDiagram + " -> exited." + nameSourceDiagram + " -> ");
				} 
				if(tr.getAction() != "") {
					set = "";
					get = "";
					splited = tr.getAction().split("=");
					set = "set_" + SMUtils.nameResolver(splited[0]) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"!(" + splited[1].replace(" ", "") + ")";
					if(arrayNameMemory != null) {
						for(String nameMem : arrayNameMemory) {
							if(splited[1].contains(nameMem)) {
								get = "get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem) + " -> ";
							}
						}
					}
					
					stringTr.append(get);
					stringTr.append(set);
					stringTr.append(" -> ");
				}

				if(!(tr.getTarget() instanceof IPseudostate)) {
					stringTr.append("enter." + nameTargetDiagram + " -> Tr_Turn_" + nameActivateTr + ")");
				}else {
					IPseudostate pseudo = (IPseudostate) tr.getTarget();
					if(pseudo.isFinalState()) {
						stringTr.append("final_" + SMUtils.nameResolver(smDiagram.getName()) +"-> Tr_Turn_" + nameActivateTr + ")");
					}else if(pseudo.isChoicePseudostate() || pseudo.isJunctionPseudostate()) {
						stringTr.append("enter." + nameTargetDiagram + " -> Tr_Turn_" + nameActivateTr + ")");
					}else {
						stringTr.append("enter." + nameTargetDiagram + " -> Tr_Turn_" + nameActivateTr + ")");
					}
				}

				if(tr.getSource().getOutgoings().length > 1) {
					for(ITransition t : tr.getSource().getOutgoings()) {
						if(t.getId() != tr.getId()) {
							stringTr.append(" [] (");
							if(t.getTrigger() != "") {
								stringTr.append(SMUtils.nameResolver(t.getTrigger()) + ".tr_" + SMUtils.nameResolver(t.getId()) + " -> ");
							}else{
								stringTr.append("internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(t.getId()) + " -> ");
							}
							stringTr.append("Tr_Turn_" + nameActivateTr + ")");
						}


					}
				}
				stringTr.append(")\n");
			}else {
				IPseudostate initialPseudo = (IPseudostate) tr.getSource();
				if(!(initialPseudo.isInitialPseudostate())) {
					//MUDANÇA
					//FAZER OUTROS PSEUDOS
					String nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
					String nameTarget = "";
					String nameSourceDiagram = nameSource + "_" + SMUtils.nameResolver(smDiagram.getName());
					
					if(tr.getTarget() != null) {
						nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
					}else {
						nameTarget = nameSource;
					}
					
					String nameTargetDiagram = nameTarget + "_" + SMUtils.nameResolver(smDiagram.getName());

					String nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
					String nameTrigger = SMUtils.nameResolver(tr.getTrigger());

					smu.addTurnName(nameActivateTr);
					String trId = "tr_" + SMUtils.nameResolver(tr.getId());

					stringTr.append("\nTr_Turn_" + nameActivateTr + 
							" = activateTr." + trId + " -> (" );

					if(nameTrigger != "") {
						stringTr.append("(" + nameTrigger + ".tr_" + SMUtils.nameResolver(tr.getId()) + " -> exit." + nameSourceDiagram  + " -> "); 
					}else {
						if(!(smu.getArrayAuxTurn().contains("internal"))) {
							smu.addAuxTurn("internal_" + SMUtils.nameResolver(smDiagram.getName()));
						}
						stringTr.append("(internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						stringTr.append(" -> exit." + nameSourceDiagram +  " -> ");
					} 
					if(tr.getAction() != "") {
						set = "";
						get = "";
						splited = tr.getAction().split("=");
						set = "set_" + SMUtils.nameResolver(splited[0]) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"!(" + splited[1].replace(" ", "") + ")";
						if(arrayNameMemory != null) {
							for(String nameMem : arrayNameMemory) {
								if(splited[1].contains(nameMem)) {
									get = "get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem) + " -> ";
								}
							}
						}
						
						stringTr.append(get);
						stringTr.append(set);
						stringTr.append(" -> ");
					}
//Adicionar a Opção de qualquer pseudoestado junction -> decision
					if(!(tr.getTarget() instanceof IPseudostate)) {
						stringTr.append("enter." + nameTargetDiagram + " -> Tr_Turn_" + nameActivateTr + ")");
					}else {
						IPseudostate pseudo = (IPseudostate) tr.getTarget();
						if(pseudo.isFinalState()) {
							stringTr.append("final_" + SMUtils.nameResolver(smDiagram.getName()) +"-> Tr_Turn_" + nameActivateTr + ")");
						}else if(pseudo.isChoicePseudostate() || pseudo.isJunctionPseudostate()) {
							stringTr.append("enter." + nameTargetDiagram + " -> Tr_Turn_" + nameActivateTr + ")");
						}
						//AINDA PRECISA SER ALTERADO PARA ADICIONAR O ENTER DO PSEUDO
						else {
							stringTr.append(" Tr_Turn_" + nameActivateTr + ")");
						}
					}

					if(tr.getSource().getOutgoings().length > 1) {
						for(ITransition t : tr.getSource().getOutgoings()) {
							if(t.getId() != tr.getId()) {
								stringTr.append(" [] (");
								if(t.getTrigger() != "") {
									stringTr.append(SMUtils.nameResolver(t.getTrigger()) + ".tr_" + SMUtils.nameResolver(t.getId()) + " -> ");
								}else{
									stringTr.append("internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(t.getId()) + " -> ");
								}
								stringTr.append("Tr_Turn_" + nameActivateTr + ")");
							}


						}
					}
					stringTr.append(")\n");
				}
			}
		}
		return stringTr.toString();
	}
}
