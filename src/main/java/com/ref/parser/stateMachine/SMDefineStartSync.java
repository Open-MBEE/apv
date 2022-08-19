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
	private IPseudostate[] pseudostates;
	private ITransition[] transitions;
	private ArrayList<String> arrayTurn;
	private ArrayList<String> arrayMemorySync;

	public SMDefineStartSync(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, IPseudostate[] pseudostates, ITransition[] transitions, ArrayList<String> arrayTurn, ArrayList<String> arrayMemorySync) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.pseudostates = sm.getPseudostates();
		this.transitions = sm.getTransitions();
		this.arrayTurn = arrayTurn;
		this.arrayMemorySync = arrayMemorySync;
	}

	public String defineStartSyncProc() {
		StringBuilder stringStartSync = new StringBuilder();
		boolean flag = true;
		String idInitialState = "";

		for(IState st : this.states) {
			if(st.isCompound()) {
				stringStartSync.append(defineStartSyncProc(st));
			}
		}

		for(IState st : this.states) {
			if(flag) {
				stringStartSync.append("\nStates_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
				stringStartSync.append("[|{|end|}|] x: {");
				if(st.isCompound()) {
					stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_init_" + SMUtils.nameResolver(smDiagram.getName()));
				}else {
					stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				}
				flag = false;
			}else {
				stringStartSync.append(", State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
			}
		}

		for(IPseudostate st : sm.getPseudostates()) {
			if(!(st.isInitialPseudostate() || st.isFinalState())) {
				if(flag) {
					stringStartSync.append("\nStates_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
					stringStartSync.append("[|{|end|}|] x: {");
					stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
					flag = false;
				}else {
					stringStartSync.append(", State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
				}
			}
		}

		stringStartSync.append(", FinalProcess_" + SMUtils.nameResolver(smDiagram.getName()) + "} @ x");
		stringStartSync.append(")\n");
		flag = true;
		stringStartSync.append("Transitions_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
		String nameSource; 
		String nameTarget;
		String nameActivateTr;
		String nameTrigger;
		ArrayList<String> arrayConfirmedTurn = new ArrayList<String>();
		ArrayList<String> arrayConfirmedEvent = new ArrayList<String>();
		StringBuilder parentesesIniciais = new StringBuilder();
		boolean auxFlagEvent= true;
		boolean auxFlagTurn = true;

		for(IState st : this.states) {
			if(st.getOutgoings() != null) {
				if(st.getOutgoings().length >= 1) {
					for(ITransition tr : st.getOutgoings()) {
						nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
						if(tr.getTarget() != null) {
							nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
						}else {
							nameTarget = nameSource;
						}
						nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
						nameTrigger = SMUtils.nameResolver(tr.getTrigger());
						if(flag) {
							if(arrayTurn.contains(nameActivateTr)) {
								stringStartSync.append("(" + "Tr_Turn_" + nameActivateTr + "; " + "Transitions_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
								flag = false;
							}
						} else {
							if(arrayTurn.contains(nameActivateTr)) {
								stringStartSync.append(" [] (" + "Tr_Turn_" +nameActivateTr + "; " + "Transitions_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
							}
						}
					}

				}
			}
		}

		for(IPseudostate st : sm.getPseudostates()) {
			if(!(st.isFinalState() || st.isInitialPseudostate())) {
				if(st.getOutgoings() != null) {
					if(st.getOutgoings().length >= 1) {
						for(ITransition tr : st.getOutgoings()) {
							nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
							if(tr.getTarget() != null) {
								nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
							}else {
								nameTarget = nameSource;
							}
							nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
							nameTrigger = SMUtils.nameResolver(tr.getTrigger());
							if(flag) {
								if(arrayTurn.contains(nameActivateTr)) {
									stringStartSync.append("(" + "Tr_Turn_" + nameActivateTr + "; " + "Transitions_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
									flag = false;
								}
							} else {
								if(arrayTurn.contains(nameActivateTr)) {
									stringStartSync.append(" [] (" + "Tr_Turn_" + nameActivateTr + "; " + "Transitions_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
								}
							}
						}
					}
				}
			}
		}
		//^^
		stringStartSync.append(") [] (end -> SKIP)\n");


		if(this.arrayMemorySync.size() != 0) {
			parentesesIniciais.delete(0, parentesesIniciais.length());
			for(int i=0; i < this.arrayMemorySync.size(); i++) {
				parentesesIniciais.append("(");
			}
			stringStartSync.append("\nStartSync_" + SMUtils.nameResolver(smDiagram.getName()) + " =" + parentesesIniciais + "(States_" + SMUtils.nameResolver(smDiagram.getName()) + renameState() + " " + sincronizaStateTrans() + "(" + lookFirstState() + " -> Transitions_" 
					+ SMUtils.nameResolver(smDiagram.getName()) + "))");  
			for(String mem : this.arrayMemorySync) {
				stringStartSync.append(mem);
			}
			stringStartSync.append(" \\ {end} \n\n");
		}else {
			stringStartSync.append("\nStartSync_" + SMUtils.nameResolver(smDiagram.getName()) + "= (States_" + SMUtils.nameResolver(smDiagram.getName()) + renameState() + " " + sincronizaStateTrans() + " (" + lookFirstState() + " -> Transitions_" + SMUtils.nameResolver(smDiagram.getName()) +")) \\ {end} \n\n");
		}
		stringStartSync.append("FinalProcess_" + SMUtils.nameResolver(smDiagram.getName()) + " = final_" + SMUtils.nameResolver(smDiagram.getName()) +  " -> end -> SKIP\n\n");

		return stringStartSync.toString();
	}

	private String defineStartSyncProc(IState state) {
		StringBuilder stringStartSync = new StringBuilder();
		boolean flag = true;
		String idInitialState = "";
		if(state.getSubstates() != null) {
			if(state.getSubstates().length > 0) {

				for(IState st : state.getSubstates()) {
					if(st.isCompound()) {
						stringStartSync.append(defineStartSyncProc(st));
					}
				}

				for(IState st : state.getSubstates()) {

					if(flag) {

						stringStartSync.append("\nStates_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
						stringStartSync.append("[|{|end|}|] x: {");
						if(st.isCompound()) {
							stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_init_" + SMUtils.nameResolver(smDiagram.getName()));
						}else {
							stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
						}
						flag = false;
					}else {
						stringStartSync.append(", State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
					}
				}

			}
		}

		if(state.getSubpseudostates() != null) {
			if(state.getSubpseudostates().length > 0) {
				for(IPseudostate st : state.getSubpseudostates()) {
					if(!(st.isInitialPseudostate() || st.isFinalState())) {
						if(flag) {
							stringStartSync.append("\nStates_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
							stringStartSync.append("[|{|end|}|] x: {");
							stringStartSync.append("State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
							flag = false;
						}else {
							stringStartSync.append(", State_st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
						}
					}
				}
			}
		}
		if(flag) {
			flag = false;
			stringStartSync.append("FinalProcess_Compound_st_" + SMUtils.nameResolver(state.getName())+ "_" + SMUtils.nameResolver(smDiagram.getName()));
		}else {
			stringStartSync.append(", FinalProcess_Compound_st_" + SMUtils.nameResolver(state.getName())+ "_" + SMUtils.nameResolver(smDiagram.getName()));
		}
		stringStartSync.append("} @ x");

		//MUDANÇA^^
		stringStartSync.append(")\n");
		flag = true;
		stringStartSync.append("Transitions_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (");
		String nameSource; 
		String nameTarget;
		String nameActivateTr;
		String nameTrigger;
		ArrayList<String> arrayConfirmedTurn = new ArrayList<String>();
		ArrayList<String> arrayConfirmedEvent = new ArrayList<String>();
		StringBuilder parentesesIniciais = new StringBuilder();
		boolean auxFlagEvent= true;
		boolean auxFlagTurn = true;

		if(state.getSubstates() != null) {
			if(state.getSubstates().length > 0) {
				for(IState st : state.getSubstates()) {
					if(st.getOutgoings() != null) {
						if(st.getOutgoings().length >= 1) {
							for(ITransition tr : st.getOutgoings()) {
								nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
								if(tr.getTarget() != null) {
									nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
								}else {
									nameTarget = nameSource;
								}
								nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
								nameTrigger = SMUtils.nameResolver(tr.getTrigger());
								if(flag) {
									if(arrayTurn.contains(nameActivateTr)) {
										stringStartSync.append("(" + "Tr_Turn_" + nameActivateTr + "; " + "Transitions_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
										flag = false;
									}
								} else {
									if(arrayTurn.contains(nameActivateTr)) {
										stringStartSync.append(" [] (" + "Tr_Turn_" +nameActivateTr + "; " + "Transitions_Compound_" +  SMUtils.nameResolver(state.getName()) + "_" +SMUtils.nameResolver(smDiagram.getName()) + ")");
									}
								}
							}

						}
					}
				}
			}
		}

		if(state.getSubpseudostates() != null) {
			if(state.getSubpseudostates().length > 0) {
				for(IPseudostate st : state.getSubpseudostates()) {
					if(!(st.isFinalState() || st.isInitialPseudostate())) {
						if(st.getOutgoings() != null) {
							if(st.getOutgoings().length >= 1) {
								for(ITransition tr : st.getOutgoings()) {
									nameSource = "st_" + SMUtils.nameResolver(tr.getSource().getName());
									if(tr.getTarget() != null) {
										nameTarget = "st_" + SMUtils.nameResolver(tr.getTarget().getName());
									}else {
										nameTarget = nameSource;
									}
									nameActivateTr = nameTarget + "_BY_" + nameSource + "_" + SMUtils.nameResolver(tr.getId());
									nameTrigger = SMUtils.nameResolver(tr.getTrigger());
									if(flag) {
										if(arrayTurn.contains(nameActivateTr)) {
											stringStartSync.append("(" + "Tr_Turn_" + nameActivateTr + "; " + "Transitions_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
											flag = false;
										}
									} else {
										if(arrayTurn.contains(nameActivateTr)) {
											stringStartSync.append(" [] (" + "Tr_Turn_" + nameActivateTr + "; " + "Transitions_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
										}
									}
								}
							}
						}
					}
				}
			}
		}

		stringStartSync.append(") [] (interrupt.st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " -> exit?x:{" + getChildren(state) + "} -> exited.x -> end -> SKIP) [] (end -> SKIP)");
		stringStartSync.append("\n");
		stringStartSync.append("FinalProcess_Compound_st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " = (final_Compound_st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) 
		+ " -> interrupt.st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + " -> end -> SKIP) [] (end -> SKIP)\n");

		stringStartSync.append("StartSync_Compound_st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + "= (States_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) + renameState(state) + " " + sincronizaStateTrans(state) + " (" + lookFirstState(state) + " -> Transitions_Compound_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()) +"))");
	

		return stringStartSync.toString();
	}

	private String getChildren(IState st) {
		StringBuilder stringChildren = new StringBuilder();
		boolean flag = true;
		if(st.getSubstates() != null) {
			if(st.getSubstates().length > 0) {
				for(IState state : st.getSubstates()) {
					if(flag) {
						stringChildren.append("st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
						flag = false;
					}else {
						stringChildren.append(", st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
					}
				}
			}
		}

		if(st.getSubpseudostates() != null) {
			if(st.getSubpseudostates().length > 0) {
				for(IPseudostate pst : st.getSubpseudostates()) {
					if(pst.isChoicePseudostate() || pst.isJunctionPseudostate()) {
						if(flag) {
							stringChildren.append("st_" + SMUtils.nameResolver(pst.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
							flag = false;
						}else {
							stringChildren.append(", st_" + SMUtils.nameResolver(pst.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
						}
					}
				}
			}
		}

		return stringChildren.toString();
	}

	private String renameState() {
		StringBuilder stringRename = new StringBuilder();
		String stateName;
		String stateNameDiagram;
		boolean flag = true;

		stringRename.append(" [[");
		for(IState st : this.states) {
			if(st.getOutgoings() != null) {
				if(st.getOutgoings().length > 0) {
					for(ITransition tr : st.getOutgoings()) {
						stateName = "st_" + SMUtils.nameResolver(st.getName());
						stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
						if(flag) {
							if(tr.getTrigger() != "") {
								stringRename.append("interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
								flag = false;
							}else {
								stringRename.append("interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
								flag = false;
							}
						}else {
							if(tr.getTrigger() != "") {
								stringRename.append(", interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
							}else {
								stringRename.append(", interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
							}
						}
					}
				}
			}
		}

		for(IPseudostate st : this.pseudostates) {
			if(st.isChoicePseudostate() || st.isJunctionPseudostate()) {
				if(st.getOutgoings() != null) {
					if(st.getOutgoings().length > 0) {
						for(ITransition tr : st.getOutgoings()) {
							stateName = "st_" + SMUtils.nameResolver(st.getName());
							stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
							if(flag) {
								if(tr.getTrigger() != "") {
									stringRename.append("interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
									flag = false;
								}else {
									stringRename.append("interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
									flag = false;
								}
							}else {
								if(tr.getTrigger() != "") {
									stringRename.append(", interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
								}else {
									stringRename.append(", interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
								}
							}
						}
					}
				}
			}
		}
		stringRename.append("]] ");
		return stringRename.toString();
	}

	private String renameState(IState state) {
		StringBuilder stringRename = new StringBuilder();
		String stateName;
		String stateNameDiagram;
		String stateNameSuperior;
		String stateNameSuperiorDiagram;
		ArrayList<IState> superiors = getAllSuperior(state);
		boolean flag = true;

		stringRename.append(" [[");
		if(state.getSubstates() != null) {
			if(state.getSubstates().length > 0) {
				for(IState st : state.getSubstates()) {
					if(st.getOutgoings() != null) {
						if(st.getOutgoings().length > 0) {
							for(ITransition tr : st.getOutgoings()) {
								stateName = "st_" + SMUtils.nameResolver(st.getName());
								stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
								if(flag) {
									if(tr.getTrigger() != "") {
										stringRename.append("interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
										flag = false;
									}else {
										stringRename.append("interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
										flag = false;
									}
								}else {
									if(tr.getTrigger() != "") {
										stringRename.append(", interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
									}else {
										stringRename.append(", interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
									}
								}
							}
							//fazer interrupt dos pais
							stateName = "st_" + SMUtils.nameResolver(st.getName());
							stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
							stateNameSuperior = "st_" + SMUtils.nameResolver(state.getName());
							stateNameSuperiorDiagram = stateNameSuperior + "_" + SMUtils.nameResolver(smDiagram.getName());
							stringRename.append(", interrupt." + stateNameDiagram + " <- interrupt." + stateNameSuperiorDiagram);

							if(superiors != null) {
								for(IState s : superiors) {
									stateName = "st_" + SMUtils.nameResolver(st.getName());
									stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
									stateNameSuperior = "st_" + SMUtils.nameResolver(s.getName());
									stateNameSuperiorDiagram = stateNameSuperior + "_" + SMUtils.nameResolver(smDiagram.getName());
									stringRename.append(", interrupt." + stateNameDiagram + " <- interrupt." + stateNameSuperiorDiagram);
								}
							}
						}
					}
				}
			}
		}

		if(state.getSubpseudostates() != null) {
			if(state.getSubpseudostates().length > 0) {
				for(IPseudostate st : state.getSubpseudostates()) {
					if(st.isChoicePseudostate() || st.isJunctionPseudostate()) {
						if(st.getOutgoings() != null) {
							if(st.getOutgoings().length > 0) {
								for(ITransition tr : st.getOutgoings()) {
									stateName = "st_" + SMUtils.nameResolver(st.getName());
									stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
									if(flag) {
										if(tr.getTrigger() != "") {
											stringRename.append("interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
											flag = false;
										}else {
											stringRename.append("interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
											flag = false;
										}
									}else {
										if(tr.getTrigger() != "") {
											stringRename.append(", interrupt." + stateNameDiagram + " <- " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
										}else {
											stringRename.append(", interrupt." + stateNameDiagram + " <- internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
										}
									}
								}
							}
						}
						//parents interrupt
						stateName = "st_" + SMUtils.nameResolver(st.getName());
						stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
						stateNameSuperior = "st_" + SMUtils.nameResolver(state.getName());
						stateNameSuperiorDiagram = stateNameSuperior + "_" + SMUtils.nameResolver(smDiagram.getName());
						stringRename.append(", interrupt." + stateNameDiagram + " <- interrupt." + stateNameSuperiorDiagram);

						if(superiors != null) {
							for(IState s : superiors) {
								stateName = "st_" + SMUtils.nameResolver(st.getName());
								stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
								stateNameSuperior = "st_" + SMUtils.nameResolver(s.getName());
								stateNameSuperiorDiagram = stateNameSuperior + "_" + SMUtils.nameResolver(smDiagram.getName());
								stringRename.append(", interrupt." + stateNameDiagram + " <- interrupt." + stateNameSuperiorDiagram);
							}
						}
					}
				}
			}
		}

		stringRename.append("]] ");
		return stringRename.toString();
	}

	private ArrayList<IState> getAllSuperior(IState state) {
		ArrayList<IState> superiors = new ArrayList<>();

		if(state.getSuperiorState() != null) {
			superiors.add(state.getSuperiorState());
			getAllSuperior(state.getSuperiorState());
		}

		return superiors;
	}


	private String lookFirstState() {
		for(IState st : this.states) {
			if(st.isFirstState()) {
				return ("enter.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
			}
		}

		for(IPseudostate st : this.pseudostates) {
			if(st.isFirstState()) {
				return ("enter.st_" + SMUtils.nameResolver(st.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
			}
		}

		return null;
	}

	private String lookFirstState(IState state) {
		if(state.getSubpseudostates() != null) {
			if(state.getSubpseudostates().length > 0) {
				for(IPseudostate st : state.getSubpseudostates()) {
					if(st.isInitialPseudostate()) {
						if(st.getOutgoings() != null) {
							if(st.getOutgoings().length > 0) {
								st.getOutgoings()[0].getTarget().setIsFirstState(true);
								return ("enter.st_" + SMUtils.nameResolver(st.getOutgoings()[0].getTarget().getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
							}
						}
					}
				}
			}
		}


		return null;
	}

	private String sincronizaStateTrans() {
		StringBuilder stringSync = new StringBuilder();
		String stateName;
		String stateNameDiagram;
		boolean flag = true;
		stringSync.append("[|{|");
		for(IState st : this.states) {
			stateName = "st_" + SMUtils.nameResolver(st.getName());
			stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
			if(flag) {
				stringSync.append("enter." + stateNameDiagram);
				flag = false;
			}else {
				stringSync.append(", enter." + stateNameDiagram);
			}
			stringSync.append(", exit." + stateNameDiagram);
			stringSync.append(", exited." + stateNameDiagram);

			if(st.getOutgoings() != null) {
				if(st.getOutgoings().length > 0) {
					for(ITransition tr : st.getOutgoings()) {
						if(tr.getTrigger() != "") {
							stringSync.append(", " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							stringSync.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}
					}
				}
			}
		}

		for(IPseudostate st : this.pseudostates) {
			if(st.isChoicePseudostate() || st.isJunctionPseudostate()) {
				stateName = "st_" + SMUtils.nameResolver(st.getName());
				stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
				if(flag) {
					stringSync.append("enter." + stateNameDiagram);
					flag = false;
				}else {
					stringSync.append(", enter." + stateNameDiagram);
				}
				stringSync.append(", exit." + stateNameDiagram);
				stringSync.append(", exited." + stateNameDiagram);
				
				if(st.getOutgoings() != null) {
					if(st.getOutgoings().length > 0) {
						for(ITransition tr : st.getOutgoings()) {
							if(tr.getTrigger() != "") {
								stringSync.append(", " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
							}else {
								stringSync.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
							}
						}
					}
				}
			}
		}
		stringSync.append(", end, final_" + SMUtils.nameResolver(smDiagram.getName()) + "|}|]");
		return stringSync.toString();
	}

	private String sincronizaStateTrans(IState state) {
		StringBuilder stringSync = new StringBuilder();
		String stateName;
		String stateNameDiagram;
		String stateNameSuperior;
		String stateNameSuperiorDiagram;
		ArrayList<IState> superiors = getAllSuperior(state);
		boolean flag = true;
		stringSync.append("[|{|");
		if(state.getSubstates() != null) {
			if(state.getSubstates().length > 0) {
				for(IState st : state.getSubstates()) {
					stateName = "st_" + SMUtils.nameResolver(st.getName());
					stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
					if(flag) {
						stringSync.append("enter." + stateNameDiagram);
						flag = false;
					}else {
						stringSync.append(", enter." + stateNameDiagram);
					}
					stringSync.append(", exit." + stateNameDiagram);
					stringSync.append(", exited." + stateNameDiagram);

					if(st.getOutgoings() != null) {
						if(st.getOutgoings().length > 0) {
							for(ITransition tr : st.getOutgoings()) {
								if(tr.getTrigger() != "") {
									stringSync.append(", " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
								}else {
									stringSync.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
								}
							}
						}
					}
				}
			}
		}

		if(state.getSubpseudostates() != null) {
			if(state.getSubpseudostates().length > 0) {
				for(IPseudostate st : state.getSubpseudostates()) {
					if(st.isChoicePseudostate() || st.isJunctionPseudostate()) {
						stateName = "st_" + SMUtils.nameResolver(st.getName());
						stateNameDiagram = stateName + "_" + SMUtils.nameResolver(smDiagram.getName());
						if(flag) {
							stringSync.append("enter." + stateNameDiagram);
							flag = false;
						}else {
							stringSync.append(", enter." + stateNameDiagram);
						}
						stringSync.append(", exit." + stateNameDiagram);
						stringSync.append(", exited." + stateNameDiagram);
						
						if(st.getOutgoings() != null) {
							if(st.getOutgoings().length > 0) {
								for(ITransition tr : st.getOutgoings()) {
									if(tr.getTrigger() != "") {
										stringSync.append(", " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
									}else {
										stringSync.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
									}
								}
							}
						}
					}
				}
			}
		}
		if(flag) {
			stringSync.append("final_Compound_st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
			flag = false;
		}else {
			stringSync.append(", final_Compound_st_" + SMUtils.nameResolver(state.getName()) + "_" + SMUtils.nameResolver(smDiagram.getName()));
		}
		stateNameSuperior = "st_" + SMUtils.nameResolver(state.getName());
		stateNameSuperiorDiagram = stateNameSuperior + "_" + SMUtils.nameResolver(smDiagram.getName());
		stringSync.append(", interrupt." + stateNameSuperiorDiagram);

		if(superiors != null) {
			for(IState s : superiors) {
				stateNameSuperior = "st_" + SMUtils.nameResolver(s.getName());
				stateNameSuperiorDiagram = stateNameSuperior + "_" + SMUtils.nameResolver(smDiagram.getName());
				stringSync.append(", interrupt." + stateNameSuperiorDiagram);
			}
		}

		stringSync.append(", end|}|]");
		return stringSync.toString();
	}
}
