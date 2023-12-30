package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.SubActivity;
import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;
import com.ref.parser.activityDiagram.ADUtils;

public class SMDefineTransition {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;
	private ArrayList<String> arrayNameMemory;
	private ArrayList<String> renamedTriggers;

	public SMDefineTransition(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions, ArrayList<String> arrayNameMemory) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
		this.arrayNameMemory = arrayNameMemory;
	}

	public String defineTransition() {
		this.renamedTriggers = new ArrayList<String>();
		StringBuilder stringTr = new StringBuilder();
		String set = "",get = "";
		String splited[];
		
		for(ITransition tr : this.transitions) {
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
						" = (" );

				if(nameTrigger != "") {
					stringTr.append("(" + nameTrigger + "_tr_" + SMUtils.nameResolver(tr.getId()) + " -> tr_" + SMUtils.nameResolver(tr.getId()) +  " -> exit." + nameSourceDiagram  + " -> exited." + nameSourceDiagram + " -> "); 
					smu.addRenamedTriggers(nameTrigger + "_tr_" + SMUtils.nameResolver(tr.getId()) + " <- " + nameTrigger);
				}else {
					if(!(smu.getArrayAuxTurn().contains("internal"))) {
						smu.addAuxTurn("internal_" + SMUtils.nameResolver(smDiagram.getName()));
					}
					stringTr.append("(internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
					stringTr.append(" -> tr_" + SMUtils.nameResolver(tr.getId()) + " -> exit." + nameSourceDiagram + " -> exited." + nameSourceDiagram + " -> ");
					smu.addRenamedTriggers("internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()) + " <- internal");
				} 
				if(tr.getAction() != "") {
					set = "";
					get = "";
					if(tr.getAction().contains("invActivity")) {
						if (tr.getAction().split(";").length > 1) {
							String getSubActivity = "";
							String[] splitSubActivity = tr.getAction().replace(" ", "").split(";");

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
										getSubActivity = " get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
									}
								}
							}

							// After result
							splited = splitSubActivity[1].split("=");
							set = " -> set_" + SMUtils.nameResolver(splited[0]) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"!(" + splited[1].replace(" ", "") + ")";
							if(arrayNameMemory != null) {
								for(String nameMem : arrayNameMemory) {
									if(splited[1].contains(nameMem)) {
										get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem) + " -> ";
									}
								}
							}

							//Sync
							String sync = " -> (" + activityName + "(1) [|{|startActivity_" + activityName + ", endActivity_" + activityName + "|}|] "
									+ "(startActivity_"+activityName+".1!"+parametroEntrada+" ->  endActivity_"+activityName+".1?"+quemRecebeSaida+
									get + set + " -> SKIP))";

							stringTr.append(getSubActivity);
							stringTr.append(sync);
							stringTr.append("; ");
						}
					}else {
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
				}

				if(!(tr.getTarget() instanceof IPseudostate)) {
					stringTr.append("enter." + nameTargetDiagram + " -> SKIP)");
				}else {
					IPseudostate pseudo = (IPseudostate) tr.getTarget();
					if(pseudo.isFinalState() && pseudo.getSuperiorState() == null) {
						stringTr.append("final_" + SMUtils.nameResolver(smDiagram.getName()) +"-> SKIP)");
					}else if(pseudo.isFinalState() && pseudo.getSuperiorState() != null) {
						stringTr.append("final_Compound_st_" + SMUtils.nameResolver(pseudo.getSuperiorState().getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) +" -> interrupt.st_" + SMUtils.nameResolver(pseudo.getSuperiorState().getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + "-> SKIP)");
					}
					else if(pseudo.isChoicePseudostate() || pseudo.isJunctionPseudostate()) {
						stringTr.append("enter." + nameTargetDiagram + " -> SKIP)");
					}else {
						stringTr.append("enter." + nameTargetDiagram + " -> SKIP)");
					}
				}
				stringTr.append(")\n");
			}else {
				IPseudostate initialPseudo = (IPseudostate) tr.getSource();
				if(!(initialPseudo.isInitialPseudostate())) {
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
							" = (" );

					if(nameTrigger != "") {
						stringTr.append("(" + nameTrigger + "_tr_" + SMUtils.nameResolver(tr.getId()) + " -> tr_" + SMUtils.nameResolver(tr.getId()) + " -> exit." + nameSourceDiagram  + " -> exited." + nameSourceDiagram + " -> "); 
						smu.addRenamedTriggers(nameTrigger + "_tr_" + SMUtils.nameResolver(tr.getId()) + " <- " + nameTrigger);
					}else {
						if(!(smu.getArrayAuxTurn().contains("internal"))) {
							smu.addAuxTurn("internal_" + SMUtils.nameResolver(smDiagram.getName()));
						}
						stringTr.append("(internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						stringTr.append(" -> tr_" + SMUtils.nameResolver(tr.getId()) + " -> exit." + nameSourceDiagram + " -> exited." + nameSourceDiagram + " -> ");
						smu.addRenamedTriggers("internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()) + " <- internal");
					} 
					if(tr.getAction() != "") {
						set = "";
						get = "";
						if(tr.getAction().contains("invActivity")) {
							if (tr.getAction().split(";").length > 1) {
								String getSubActivity = "";
								String[] splitSubActivity = tr.getAction().replace(" ", "").split(";");

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
											getSubActivity = " get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem);
										}
									}
								}

								// After result
								splited = splitSubActivity[1].split("=");
								set = " -> set_" + SMUtils.nameResolver(splited[0]) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"!(" + splited[1].replace(" ", "") + ")";
								if(arrayNameMemory != null) {
									for(String nameMem : arrayNameMemory) {
										if(splited[1].contains(nameMem)) {
											get = " -> get_" + SMUtils.nameResolver(nameMem)  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?" + SMUtils.nameResolver(nameMem) + " -> ";
										}
									}
								}

								//Sync
								String sync = " -> (" + activityName + "(1) [|{|startActivity_" + activityName + ", endActivity_" + activityName + "|}|] "
										+ "(startActivity_"+activityName+".1!"+parametroEntrada+" ->  endActivity_"+activityName+".1?"+quemRecebeSaida+
										get + set + " -> SKIP))";

								stringTr.append(getSubActivity);
								stringTr.append(sync);
								stringTr.append("; ");
							}
						}else {
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
					}

					if(!(tr.getTarget() instanceof IPseudostate)) {
						stringTr.append("enter." + nameTargetDiagram + " -> SKIP)");
					}else {
						IPseudostate pseudo = (IPseudostate) tr.getTarget();
						if(pseudo.isFinalState()) {
							stringTr.append("final_" + SMUtils.nameResolver(smDiagram.getName()) +"-> SKIP)");
						}else if(pseudo.isChoicePseudostate() || pseudo.isJunctionPseudostate()) {
							stringTr.append("enter." + nameTargetDiagram + " -> SKIP)");
						}else {
							stringTr.append("enter." + nameTargetDiagram + " -> SKIP)");
						}
					}
					stringTr.append(")\n");
				}
			}
		}
		return stringTr.toString();
	}

	public ArrayList<String> getRenamedTriggers() {
		return renamedTriggers;
	}

	public void setRenamedTriggers(ArrayList<String> renamedTriggers) {
		this.renamedTriggers = renamedTriggers;
	}
}
