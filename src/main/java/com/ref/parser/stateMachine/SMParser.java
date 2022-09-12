package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;
import com.ref.interfaces.stateMachine.IVertex;

public class SMParser {

	private IStateMachine sm;
	private IStateMachineDiagram smDiagram;
	private IPseudostate initialNode;
	public static IState[] states;
	public static IPseudostate[] pseudostates;
	public static IVertex[] vertexes;
	public static ITransition[] transitions;
	public ArrayList<String> arrayTurn;
	public ArrayList<String> auxTurnList;
	public ArrayList<String> arrayMemorySync;
	public ArrayList<String> arrayMemoryChannel;
	public ArrayList<String> arrayNameMemory;
	public ArrayList<String> arrayRenamedTrigger;
	public SMDefineState dState;
	public SMDefineTransition dTurnTr;
	public SMDefineEntry dEntry;
	public SMDefineDo dDo;
	public SMDefineExit dExit;
	public SMDefineChannel dChannel;
	public SMDefineStartSync dStartSync;
	public SMDefineMemory dMem;
	public SMDefineControlCompound dControlCompound;
	public SMDefineDefaultFunctions dDefaultFunctions;

	public SMParser(IStateMachine sm, String nameSM, IStateMachineDiagram smDiagram) {
		this.sm = sm;
		this.smDiagram = smDiagram;
		setName(nameSM);
	}

	private void setName(String nameSM) {
		this.sm.setName(nameSM);
	}

	public String parserStateMachine() {
		String check = "";

		defineStateList();
		definePseudostateList();
		defineTransitionList();

		initialNode = defineInitialNode();
		String memory = defineMemoryProcess();
		String state = defineStateProcess();
		String trans = defineTurnTransition();
		String entry = defineEntryProcess();
		String doString = defineDoProcess();
		String exit = defineExitProcess();
		String channel = defineChannels();
		String startSync = defineStartSyncProcess();
		String controlCompound = defineControlCompoundProcess();
		String defaultFunctions = defineDefaultFunctions();
		
		if(arrayRenamedTrigger != null) {
			if(arrayRenamedTrigger.size() == 0) {
				check = "\nMAIN = wbisim((StartSync_" + SMUtils.nameResolver(smDiagram.getName()) + "); LOOP\\  {|loop,exit,exited,do,entry,end,interrupt|})\n" +
						"\nassert MAIN :[deadlock free[F]]" +
						"\nassert MAIN :[divergence free]" +
						"\nassert MAIN :[deterministic[F]]";
			}else {
				check = "\nMAIN = wbisim((StartSync_" + SMUtils.nameResolver(smDiagram.getName()) + "[[" + addRenamedTriggers() + "]]" + "); LOOP\\ {|loop,exit,exited,do,entry,end,interrupt|})\n" +
						"\nassert MAIN :[deadlock free[F]]" +
						"\nassert MAIN :[divergence free]" +
						"\nassert MAIN :[deterministic[F]]";
			}
		}

		String result = channel + defaultFunctions + memory + state +  trans + entry + doString + exit + startSync + controlCompound + check;
		return result;
	}

	public SMUtils defineSMUtils() {
		SMUtils smu = new SMUtils(sm, smDiagram);
		return smu;
	}

	public void defineStateList() {
		states = smDiagram.getStateMachine().getStates();
	}

	public void definePseudostateList() {
		pseudostates = smDiagram.getStateMachine().getPseudostates();
	}

	public void defineTransitionList() {
		transitions = smDiagram.getStateMachine().getTransitions();
	}

	public void defineVertexesList() {
		ArrayList<IVertex> listVertexes = new ArrayList<>();

		for(IState state : smDiagram.getStateMachine().getStates()) {
			listVertexes.add(state);
		}

		for(IPseudostate pseudostate : smDiagram.getStateMachine().getPseudostates()) {
			listVertexes.add(pseudostate);
		}
		vertexes = (IVertex[]) listVertexes.toArray();
	}

	public IPseudostate defineInitialNode() {
		for(IPseudostate pseudostate : sm.getPseudostates()) {
			if(pseudostate.isInitialPseudostate() == true) {
				return pseudostate;
			}
		}
		return null;
	}

	public String defineStateProcess() {
		SMUtils smu = defineSMUtils();
		dState = new SMDefineState(smu, sm, smDiagram, states, pseudostates, initialNode);
		return dState.defineStates();
	}

	public String defineTurnTransition() {
		SMUtils smu = defineSMUtils();
		dTurnTr = new SMDefineTransition(smu, sm, smDiagram, states, transitions, arrayNameMemory);
		arrayTurn = smu.getArrayTurnName();
		auxTurnList = smu.getArrayAuxTurn();
		arrayRenamedTrigger = smu.getArrayRenamedTriggers();
		return dTurnTr.defineTransition();
	}

	public String defineEntryProcess() {
		SMUtils smu = defineSMUtils();
		dEntry = new SMDefineEntry(smu,sm,smDiagram,states,transitions,arrayNameMemory);
		return dEntry.defineEntry();
	}

	public String defineDoProcess() {
		SMUtils smu = defineSMUtils();
		dDo = new SMDefineDo(smu,sm,smDiagram,states,transitions,arrayNameMemory);
		return dDo.defineDo();
	}

	public String defineExitProcess() {
		SMUtils smu = defineSMUtils();
		dExit = new SMDefineExit(smu,sm,smDiagram,states,transitions,arrayNameMemory);
		return dExit.defineExit();
	}

	public String defineChannels() {
		SMUtils smu = defineSMUtils();
		dChannel = new SMDefineChannel(smu,sm,smDiagram,states,transitions, auxTurnList, arrayMemoryChannel);
		return dChannel.defineChannel();
	}

	public String defineStartSyncProcess() {
		SMUtils smu = defineSMUtils();
		dStartSync = new SMDefineStartSync(smu,sm,smDiagram,states,pseudostates,transitions,arrayTurn,arrayMemorySync);
		return dStartSync.defineStartSyncProc();
	}

	public String defineMemoryProcess() {
		SMUtils smu = defineSMUtils();
		dMem = new SMDefineMemory(smu,sm,smDiagram,states,transitions);
		arrayMemorySync = smu.getArraySyncMemory();
		arrayMemoryChannel = smu.getArrayChannelMemory();
		arrayNameMemory = smu.getArrayNameMemory();
		return dMem.defineMemory();
	}
	
	public String defineControlCompoundProcess() {
		SMUtils smu = defineSMUtils();
		dControlCompound = new SMDefineControlCompound(smu, sm, smDiagram, states, pseudostates, initialNode);
		return dControlCompound.defineControlCompounds();
	}
	
	public String defineDefaultFunctions() {
		dDefaultFunctions = new SMDefineDefaultFunctions();
		return dDefaultFunctions.getDefaultFunctions();
	}
	
	public String addRenamedTriggers() {
		StringBuilder renamedTriggers = new StringBuilder();
		boolean flag = true;
		for(String s : arrayRenamedTrigger) {
			if(flag) {
				renamedTriggers.append(s);
				flag = false;
			}else {
				renamedTriggers.append(", " + s);
			}
		}
		return renamedTriggers.toString();
	}
}
