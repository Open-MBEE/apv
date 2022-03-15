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
		if(smDiagram.getDefinition() != "") {
			System.out.println("ENTRE");
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
				if(memoryInit.isEmpty()) {
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

				if(stringSyncMemory.isEmpty()) {
					stringSyncMemory.append("[|{|get_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + ", set_" + nameMemory + "_"  + SMUtils.nameResolver(smDiagram.getName()));
				}else {
					stringSyncMemory.append(", get_" + nameMemory  + "_" + SMUtils.nameResolver(smDiagram.getName()) + ", set_" + nameMemory + "_"  + SMUtils.nameResolver(smDiagram.getName()));
				}

				if(stringMemory.isEmpty()) {
					stringMemory.append("\nMEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters + ") = "
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
							//substituir o guard por not guard and ...
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
							stringMemory.append(SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
							stringSyncMemory.append(", " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							stringMemory.append("internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
							stringSyncMemory.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}
						stringMemory.append(" -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryParameters + ")\n");
					}
				}
			}
			//Adicionar o FOR ITransition para o caso so stringMemory estar nulo
			stringSyncMemory.append("|}|] MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "(" + memoryInit +"))");
			smu.addSyncMemory(stringSyncMemory.toString());
			return stringMemory.toString();
		}else {
			//Caso não haja Definition de memoria
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
						if(stringMemory.isEmpty()) {
							stringMemory.append("\nMEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + " = ");
							stringMemory.append("("+ guard +") & ");
						}else {
							stringMemory.append(" [] (" + guard + ") & ");	
						}
					}else{
						if(stringMemory.isEmpty()) {
							stringMemory.append("\nMEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + " = ");
							stringMemory.append("("+ guard +") & ");
						}else {
							stringMemory.append(" [] (" + guard + ") & ");	
						}
					}

					if(tr.getTrigger() != "") {
						stringMemory.append(SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						if(stringSyncMemory.isEmpty()) {
							stringSyncMemory.append("[|{|" + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							stringSyncMemory.append(", " + SMUtils.nameResolver(tr.getTrigger()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}
					}else {
						stringMemory.append("internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						if(stringSyncMemory.isEmpty()) {
							stringSyncMemory.append("[|{|" + "internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}else {
							stringSyncMemory.append(", internal_" + SMUtils.nameResolver(smDiagram.getName()) + ".tr_" + SMUtils.nameResolver(tr.getId()));
						}
					}
					stringMemory.append(" -> MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + "\n");
					//}
				}
			}
			//Adicionar o FOR ITransition para o caso so stringMemory estar nulo
			if(!stringSyncMemory.isEmpty()) {
				stringSyncMemory.append("|}|] MEMORY_" + SMUtils.nameResolver(smDiagram.getName()) + ")");
				smu.addSyncMemory(stringSyncMemory.toString());
			}
			return stringMemory.toString();
		}
	}
}
