package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class SMDefineMemory {

	private SMUtils smu;
	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IState[] states;
	private ITransition[] transitions;

	public SMDefineMemory(SMUtils smu, IStateMachine sm, IStateMachineDiagram smDiagram, IState[] states, ITransition[] transitions) {
		this.smu = smu;
		this.sm = sm;
		this.smDiagram = smDiagram;
		this.states = sm.getStates();
		this.transitions = sm.getTransitions();
	}

	public String defineMemory() {
		StringBuilder stringMemory = new StringBuilder();
		StringBuilder stringSyncMemory = new StringBuilder();
		String guard;
		if(!smDiagram.getDefinition().equals("")) {
			String memoryParameters = "";
			String[] splitedString = smDiagram.getDefinition().split(";");
			String[] auxSplit;
			String nameMemory;
			String memoryValues;
			StringBuilder memoryInit = new StringBuilder();

			String auxSplitedString;

			for(int i = 0; i < splitedString.length; i++) {
				auxSplitedString = splitedString[i].replace(" ", "");
				auxSplit = auxSplitedString.split("/");
				nameMemory = SMUtils.nameResolver(auxSplit[0]);
				if(memoryInit.length() == 0) {
					memoryInit.append(auxSplit[2]);
				}else {
					memoryInit.append(", " + auxSplit[2]);
				}
				if(memoryParameters == "") {
					memoryParameters = nameMemory;
				}else {
					memoryParameters += ", " + nameMemory;
				}
			}

			for(int i = 0; i < splitedString.length; i++) {
				splitedString[i] = splitedString[i].replace(" ", "");
				auxSplit = splitedString[i].split("/");
				smu.addNameMemory(auxSplit[0]);
				nameMemory = SMUtils.nameResolver(auxSplit[0]);
				memoryValues = auxSplit[1];

				smu.addChannelMemory("channel get_" + nameMemory + "_" + SMUtils.nameResolver(smDiagram.getName()) + ", set_" + nameMemory + "_"  + SMUtils.nameResolver(smDiagram.getName()) + ": " + memoryValues);

				if(stringSyncMemory.length() == 0) {
					stringSyncMemory.append("[|{|get_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + ", set_" + nameMemory + "_"  + SMUtils.nameResolver(smDiagram.getName()));
				}else {
					stringSyncMemory.append(", get_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + ", set_" + nameMemory + "_"  + SMUtils.nameResolver(smDiagram.getName()));
				}

				if(stringMemory.length() == 0) {
					stringMemory.append("\nMEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters + ") = (end -> SKIP) []\n"
							+ "get_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!" + nameMemory +" -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters + ") [] " 
							+ "set_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?x -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters.replace(nameMemory, "x") +")");

				}else {
					stringMemory.append("[] get_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "!" + nameMemory +" -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters + ") [] " 
							+ "set_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + "?x -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters.replace(nameMemory, "x") +")");
				}
				for(ITransition tr : this.transitions) {
					if(!tr.getGuard().isEmpty()) {
						guard = tr.getGuard();
						if(tr.getGuard().contains(auxSplit[0])) {
							guard = guard.replace(auxSplit[0], nameMemory);
						}
						if(guard.equals("else")) {
							//replace guard for not guard and ...
							for(ITransition tra : tr.getSource().getOutgoings()) {
								if(tra.getGuard() != "") {
									if(tra.getId() != tr.getId()) {
										if(guard.equals("else")) {
											guard = "[] (not(" + tra.getGuard() + ")"; 
										}else {
											guard += " and not(" + tra.getGuard() + ")";
										}
									}
								}
							}
							guard += ")";
							stringMemory.append(guard + " & ");
						}else {
							stringMemory.append(" [] (" + guard + ") & ");
						}
						if(tr.getTrigger() != "") {
							stringMemory.append(SMUtils.nameResolver(tr.getTrigger()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
							stringSyncMemory.append(", " + SMUtils.nameResolver(tr.getTrigger()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							stringMemory.append("internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
							stringSyncMemory.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						}
						stringMemory.append(" -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters + ")\n");
					}
				}
			}
			stringSyncMemory.append(", end|}|] MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryInit +"))");
			smu.addSyncMemory(stringSyncMemory.toString());
			return stringMemory.toString();
		}else {
			//If there is no Memory Definition
			for(ITransition tr : this.transitions) {
				if(!tr.getGuard().isEmpty()) {
					guard = tr.getGuard();
					if(tr.getGuard().equals("else")) {
						for(ITransition tra : tr.getSource().getOutgoings()) {
							if(tra.getId() != tr.getId()) {
								if(tra.getGuard() != "") {
									if(guard.equals("else")) {
										guard = "not(" + tra.getGuard() + ") "; 
									}else {
										guard += "and not(" + tra.getGuard() + ")";
									}
								}
							}
						}
						if(stringMemory.length() == 0) {
							stringMemory.append("\nMEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + " = (end -> SKIP) [] \n");
							stringMemory.append("("+ guard +") & ");
						}else {
							stringMemory.append(" [] (" + guard + ") & ");	
						}
					}else{
						if(stringMemory.length() == 0) {
							stringMemory.append("\nMEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + " = (end -> SKIP) [] \n");
							stringMemory.append("("+ guard +") & ");
						}else {
							stringMemory.append(" [] (" + guard + ") & ");	
						}
					}

					if(tr.getTrigger() != "") {
						stringMemory.append(SMUtils.nameResolver(tr.getTrigger()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						if(stringSyncMemory.length() == 0) {
							stringSyncMemory.append("[|{|" + SMUtils.nameResolver(tr.getTrigger()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							stringSyncMemory.append(", " + SMUtils.nameResolver(tr.getTrigger()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						}
					}else {
						stringMemory.append("internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						if(stringSyncMemory.length() == 0) {
							stringSyncMemory.append("[|{|" + "internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							stringSyncMemory.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + "_tr_" + SMUtils.nameResolver(tr.getId()));
						}
					}
					stringMemory.append(" -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
					
				}
			}
			
			if(stringSyncMemory.length() != 0) {
				stringSyncMemory.append(", end|}|] MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
				smu.addSyncMemory(stringSyncMemory.toString());
			}
			return stringMemory.toString();
		}
	}
}
