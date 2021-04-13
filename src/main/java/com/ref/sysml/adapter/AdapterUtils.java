package com.ref.sysml.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.omg.sysml.xtext.sysml.Usage;

import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.parser.activityDiagram.Pair;

public class AdapterUtils {
    public static HashMap<Pair<String, String>, IFlow> edges = new HashMap<>(); // 	<from, to> 	-> flow
    public static HashMap<String, IActivityNode> nodes = new HashMap<>(); // 		name 		-> node
    public static HashMap<String, Usage> nodesType = new HashMap<>(); // 			name 		-> type
    public static HashMap<String, String> parameterName = new HashMap<>(); // 		id 			-> Name
    
	public static List<IFlow> getInFlows(String input) {
		List<IFlow> flows = new ArrayList<>();
		
		for (Pair<String, String> x : edges.keySet()) {
			if (x.getValue().equals(input)) {
				flows.add(edges.get(x));
			}
		}
		
		return flows;
	}
	
	public static List<IFlow> getOutFlows(String input) {
		List<IFlow> flows = new ArrayList<>();
		
		for (Pair<String, String> x : edges.keySet()) {
			if (x.getKey().equals(input)) {
				flows.add(edges.get(x));
			}
		}
		
		return flows;
	}
	
	public static IActivityNode getSource(IFlow flow) {
		
		for (Pair<String, String> x : edges.keySet()) {
			if (edges.get(x) == flow) {
				return nodes.get(x.getKey());
			}
		}
		
		return null;
	}
	
	public static IActivityNode getTarget(IFlow flow) {
		
		for (Pair<String, String> x : edges.keySet()) {
			if (edges.get(x) == flow) {
				return nodes.get(x.getValue());
			}
		}
		
		return null;
	}
	
	public static void clearBuffer() {
		edges = new HashMap<>();
		nodes = new HashMap<>();
		nodesType = new HashMap<>();
		parameterName = new HashMap<>();
	}
	
}
