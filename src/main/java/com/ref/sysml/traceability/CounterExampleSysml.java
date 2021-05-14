package com.ref.sysml.traceability;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import org.omg.sysml.xtext.sysml.BindingConnector;
import org.omg.sysml.xtext.sysml.DecisionNode;
import org.omg.sysml.xtext.sysml.Succession;
import org.omg.sysml.xtext.sysml.SuccessionItemFlow;

import com.ref.ActivityController.VerificationType;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.sysml.adapter.Action;
import com.ref.sysml.adapter.ActivityParameterNode;
import com.ref.sysml.adapter.ControlFlow;
import com.ref.sysml.adapter.ControlNode;
import com.ref.sysml.adapter.ObjectFlow;

public class CounterExampleSysml {
	
	public static void createCounterExample(String sysmlfile,
			HashMap<com.ref.interfaces.activityDiagram.IActivity,List<String>> counterExample,
			IActivityNode[] nodes, Collection<IFlow> flows, VerificationType type) {
		
//		Date hoje = new Date();
//        SimpleDateFormat df;
//        df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
//        String data = df.format(hoje);
	            
		
        for (IActivity x : counterExample.keySet()) {
        	System.out.println("t: " + counterExample.get(x).size());
        	for (int i = 0; i < counterExample.get(x).size(); i++) {
        		String id = counterExample.get(x).get(i);
        		
        		for (int l = 0; l < nodes.length; l++) {
                	if (nodes[l].getId().equals(id)) { // find the node
                		//System.out.println(nodes[l].getName());
                		sysmlfile = FindNode(sysmlfile, x.getName(), nodes[l]);
                		
                		continue;
                	}
                }
        		
        		for (IFlow f : flows) {
        			if (f.getId().equals(id)) { // find the flow
        				//System.out.println("flow " + id);
	                	sysmlfile = FindFlow(sysmlfile, x.getName(), f);
	                	
	                	continue;
        			}
                }
        		
        	}
        }
        
        System.out.println(sysmlfile);
	}
	
	
	private static String FindNode(String sysmlFile, String diagramName, IActivityNode node) {
		
		String nodeIdentifier = "";
		
		if (node instanceof Action) {
			String[] splitName = node.getName().split("_");
			String nodeName = "";
			for (int i = 1; i < splitName.length; i++) {
				nodeName += splitName[i];
			}
			
			if (((Action) node).isCallBehaviorAction()) {
				nodeIdentifier = "action def " + nodeName;
			} else {
				nodeIdentifier = "action " + nodeName;
			}
		} else if (node instanceof ControlNode) {
			String[] splitName = node.getName().split("_");
			String nodeName = "";
			for (int i = 1; i < splitName.length; i++) {
				nodeName += splitName[i];
			}
			
			if (((ControlNode) node).isDecisionNode()) {
				nodeIdentifier = "decide " + nodeName;
			} else if (((ControlNode) node).isForkNode()) {
				nodeIdentifier = "fork " + nodeName;
			} else if (((ControlNode) node).isJoinNode()) {
				nodeIdentifier = "join " + nodeName;
			} else if (((ControlNode) node).isMergeNode()) {
				nodeIdentifier = "merge " + nodeName;
			} else if (((ControlNode) node).isInitialNode()) {
				nodeIdentifier = "first " + nodeName;
			} else {
				nodeIdentifier = "action " + nodeName;
			}
		} else if (node instanceof ActivityParameterNode) {
			nodeIdentifier = "action def " + diagramName;
		}
		
		try (Scanner sc = new Scanner(sysmlFile)) {
			String newSysmlFile = "";
			while (sc.hasNextLine()) {
			    String line = sc.nextLine();
			    
			    int index = line.indexOf(nodeIdentifier);

			    if(!nodeIdentifier.isEmpty() && index != -1){
			        line += " // [deadlock trace]"; 
			    }
			    
			    newSysmlFile += line + "\r\n";
			}
			
			return newSysmlFile;
		}
		
	}
	
	
	private static String CheckLastDecision(String line) {
		
		String lastDesicionName = "";
		
		String[] lineSplit = line.split(" ");

		if (lineSplit.length > 0 && lineSplit[0].trim().equals("decide")) {
			lastDesicionName = lineSplit[1].replace(";", "");
		}
		
		return lastDesicionName;
		
	}
	
	private static String FindFlow(String sysmlFile, String diagramName, IFlow flow) {
		
		String nodeIdentifier = "";
		String nodeSourceName = "";
		String nodeTargetName = "";
		String newSysmlFile = "";
		String lastDecisionName = "";
		boolean isDecisionFlow = false;
		
		String[] splitName = flow.getSource().getName().split("_");
		
		for (int i = 1; i < splitName.length; i++) {
			nodeSourceName += splitName[i];
		}
		
		splitName = flow.getTarget().getName().split("_");
		for (int i = 1; i < splitName.length; i++) {
			nodeTargetName += splitName[i];
		}
		
		if (flow instanceof ObjectFlow) {
			ObjectFlow f = (ObjectFlow) flow;
			
			if (f.getFlow() instanceof Succession) {
				nodeIdentifier = "succession " + nodeSourceName + " then " + nodeTargetName + ";";
			} else if (f.getFlow() instanceof SuccessionItemFlow) {
				nodeIdentifier = "flow " + nodeSourceName + " to " + nodeTargetName + ";";
			} else if (f.getFlow() instanceof BindingConnector) {
				nodeIdentifier = "bind " + diagramName + "::" + flow.getSource().getName() + " = " + nodeTargetName + ";";
			} else if (!f.getGuard().isEmpty()) {
				if (f.getGuard().equals("else")) {
					nodeIdentifier = "else " + nodeTargetName + ";";
				} else {
					nodeIdentifier = "if " + f.getRealGuard() + " then " + nodeTargetName + ";";
				}
				
				isDecisionFlow = true;
			} else {
				nodeIdentifier = "then " + nodeTargetName + ";";
				isDecisionFlow = true;
			}
			
			
			try (Scanner sc = new Scanner(sysmlFile)) {
				
				while (sc.hasNextLine()) {
				    String line = sc.nextLine();
				    
				    lastDecisionName = CheckLastDecision(line).isEmpty() ? lastDecisionName : CheckLastDecision(line);
				    
				    int index = line.indexOf(nodeIdentifier);

				    if (isDecisionFlow) {
				    	
				    	if (((DecisionNode) f.getOwner()).getName().equals(lastDecisionName) && !nodeIdentifier.isEmpty() && index != -1) {
				    		line += " // [deadlock trace]"; 
				    	}
				    	
				    } else if(!nodeIdentifier.isEmpty() && index != -1){
				        line += " // [deadlock trace]"; 
				    }
				    
				    newSysmlFile += line + "\r\n";
				}
				
			}
			
		} else if (flow instanceof ControlFlow) {
			ControlFlow f = (ControlFlow) flow;
			
			if (f.getFlow() instanceof Succession) {
				nodeIdentifier = "succession " + nodeSourceName + " then " + nodeTargetName + ";";
			} else if (f.getFlow() instanceof SuccessionItemFlow) {
				nodeIdentifier = "flow " + nodeSourceName + " to " + nodeTargetName + ";";
			} else if (f.getFlow() instanceof BindingConnector) {
				nodeIdentifier = "bind " + diagramName + "::" + flow.getSource().getName() + " = " + nodeTargetName + ";";
			} else if (!f.getGuard().isEmpty()) {
				if (f.getGuard().equals("else")) {
					nodeIdentifier = "else " + nodeTargetName + ";";
				} else {
					nodeIdentifier = "if " + f.getRealGuard() + " then " + nodeTargetName + ";";
				}
				
				isDecisionFlow = true;
			} else {
				nodeIdentifier = "then " + nodeTargetName + ";";
				isDecisionFlow = true;				
			}
			
			
			try (Scanner sc = new Scanner(sysmlFile)) {
				
				while (sc.hasNextLine()) {
				    String line = sc.nextLine();
				    
				    lastDecisionName = CheckLastDecision(line).isEmpty() ? lastDecisionName : CheckLastDecision(line);
				    
				    int index = line.indexOf(nodeIdentifier);
				    
				    if (isDecisionFlow) {
				    	if (((DecisionNode) f.getOwner()).getName().equals(lastDecisionName) && !nodeIdentifier.isEmpty() && index != -1) {
				    		line += " // [deadlock trace]"; 
				    	}
				    	
				    } else if(!nodeIdentifier.isEmpty() && index != -1){
				        line += " // [deadlock trace]"; 
				    }
				    
				    newSysmlFile += line + "\r\n";
				}
				
			}
			
		}
		
		
		
		
		return newSysmlFile;
	}
	
}
