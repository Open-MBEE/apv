package com.ref.traceability.stateMachineDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.astah.adapter.Activity;
import com.ref.astah.ui.CounterView;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IControlNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectNode;
import com.ref.interfaces.activityDiagram.IPin;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.parser.activityDiagram.ADAlphabet;
import com.ref.parser.activityDiagram.ADCompositeAlphabet;
import com.ref.parser.activityDiagram.Pair;

public class CounterExampleBuilder {

	private ADAlphabet alphabetAD;
	private List<String> traceCounterExample;
	private HashMap<String,Integer> IdSignals;
	public CounterExampleBuilder(List<String> traceCounterExample, IStateMachine sm) {
		this.traceCounterExample = traceCounterExample;
	}
	
	public HashMap<IStateMachine, List<String>> createCounterExample(IStateMachine diagram) {
		HashMap<IStateMachine,List<String>> nodesCE = new HashMap<>();
		List<String> ids = new ArrayList<>();
		ids.add("TESTE");
		nodesCE.put(diagram, ids);
		return nodesCE;
	}
	
	private static String nameNodeResolver(String name) {
        return name.replace(" ", "").replace("!", "_").replace("@", "_")
                .replace("%", "_").replace("&", "_").replace("*", "_")
                .replace("(", "_").replace(")", "_").replace("+", "_")
                .replace("-", "_").replace("=", "_").replace("?", "_")
                .replace(":", "_").replace("/", "_").replace(";", "_")
                .replace(">", "_").replace("<", "_").replace(",", "_")
                .replace("{", "_").replace("}", "_").replace("|", "_")
                .replace("\\", "_");
    }
}
