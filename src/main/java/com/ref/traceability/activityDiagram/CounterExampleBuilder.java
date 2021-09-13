package com.ref.traceability.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.astah.adapter.Activity;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IControlNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectNode;
import com.ref.interfaces.activityDiagram.IPin;
import com.ref.parser.activityDiagram.ADAlphabet;
import com.ref.parser.activityDiagram.ADCompositeAlphabet;
import com.ref.parser.activityDiagram.Pair;

public class CounterExampleBuilder {

	private ADAlphabet alphabetAD;
	private List<String> traceCounterExample;
	private HashMap<String,Integer> IdSignals;

	public CounterExampleBuilder(List<String> traceCounterExample, IActivity activity, ADAlphabet alphabetAD, HashMap<String,Integer> IdSignals) {
		this.alphabetAD = alphabetAD;
		this.IdSignals = IdSignals;
		List<String> trace = new ArrayList<>();// necessary treatment of the trace
		for (String objTrace : traceCounterExample) {
            String[] objTracePartition = objTrace.split("\\.");
            if (objTracePartition.length > 1 && !objTracePartition[0].startsWith("startActivity_") && !objTracePartition[0].startsWith("endActivity_")) {
            	String aux = objTracePartition[0] + ".id";
            	if(!objTracePartition[0].startsWith("oe_")) {
	            	for(int i=2;i<objTracePartition.length;i++) {
	            		aux+="."+objTracePartition[i];
	            	}
            	}
            	else {
            		for(int i=2;i<objTracePartition.length-1;i++) {//TODO modificar isso aqui pra adaptar aos novos datatype (como?)
	            		aux+="."+objTracePartition[i];
	            	}
            	}
                trace.add(aux);
            } else {
                trace.add(objTrace);
            }
        }
		this.traceCounterExample = trace;
	}

	public HashMap<IActivity, List<String>> createCounterExample(IActivity diagram) {
		HashMap<IActivity,List<String>> nodesCE = new HashMap<>();
		System.out.println();
		nodesCE.put(diagram, searchDiagram(diagram));
		for (int i = 0; i < diagram.getActivityNodes().length ; i++) {
			IActivityNode node = diagram.getActivityNodes()[i]; 
			if(node instanceof IAction && ((IAction)node).isCallBehaviorAction()) {
				nodesCE.putAll(createCounterExample(((IAction)diagram.getActivityNodes()[i]).getCallingActivity()));
			}
		}
		return nodesCE;
	}
	
	//Sweeps the diagram and returns the id of who belongs to the trace
	private List<String> searchDiagram(IActivity diagram){
		List<String> ids = new ArrayList<>();
		Pair<IActivity, String> key = null;//diagram,node name 	
    	for (int i = 0; i < diagram.getActivityNodes().length ; i++) {
    		IActivityNode node = diagram.getActivityNodes()[i];
			if (node instanceof IAction) {
				if (((IAction) node).isAcceptEventAction()) {
					int signalNumber = IdSignals.get(node.getId());
					key = new Pair<IActivity, String>(diagram,"accept_"+nameNodeResolver(node.getName())+"_"+signalNumber);
				}
				else if(((IAction)node).isSendSignalAction()) {
					int signalNumber = IdSignals.get(node.getId());
					key = new Pair<IActivity, String>(diagram,"signal_"+nameNodeResolver(node.getName())+"_"+signalNumber);
				} else {
					key = new Pair<IActivity, String>(diagram,nameNodeResolver(node.getName()));
				}
			}else if(node instanceof IActivityParameterNode) {
				key = new Pair<IActivity, String>(diagram, "parameter_"+nameNodeResolver(node.getName()));
			}else {
				key = new Pair<IActivity, String>(diagram, nameNodeResolver(node.getName()));
			}
			if (alphabetAD instanceof ADCompositeAlphabet) {//If the most external diagram has CBAs
				HashMap<Pair<IActivity, String>, ArrayList<String>> aux = new HashMap<>();
				aux = ((ADCompositeAlphabet) alphabetAD).getAllAlphabetNodes();
				HashMap<Pair<IActivity,String>,String> allSyncChannels = ((ADCompositeAlphabet) alphabetAD).getAllsyncChannelsEdge(); 
				HashMap<Pair<IActivity,String>,String> allSyncObj = ((ADCompositeAlphabet) alphabetAD).getAllsyncObjectsEdge();
				
				if(node instanceof IActivityParameterNode) {
					aux = alphabetAD.getParameterAlphabetNode();
				}
				
				if (aux.containsKey(key)) {
					List<String> allflowsNode = aux.get(key);

					for (String objTrace : traceCounterExample) {//If there is a trace
						if (allflowsNode != null && allflowsNode.contains(objTrace)) {//If contains on trace TODO(is wrong)
							if (!ids.contains(node.getId()) && 
									((node instanceof IAction && !objTrace.startsWith("oe_") && !objTrace.startsWith("ce_")) 
											|| node instanceof IControlNode || node instanceof IObjectNode)) {
								ids.add(node.getId());
							}						
							for(int j = 0; j < node.getIncomings().length; j++) {//sweeps the incoming flows
								IFlow flow = node.getIncomings()[j];
								Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
								String channel = allSyncChannels.get(chave);//Verify the control edges
								String channelObj = allSyncObj.get(chave);//Verify the object edges
								if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
									if (!ids.contains(flow.getId())) {
										ids.add(flow.getId());
									}
								}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
									if (!ids.contains(flow.getId())) {
										ids.add(flow.getId());
									}
								}
							}
							
							for(int j = 0; j < node.getOutgoings().length; j++) {//sweeps the outgoings flows
								IFlow flow = node.getOutgoings()[j];
								Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
								String channel = allSyncChannels.get(chave);//Verify the control edges
								String channelObj = allSyncObj.get(chave);//Verify the object edges
								if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
									if (!ids.contains(flow.getId())) {
										ids.add(flow.getId());
									}
								}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
									if (!ids.contains(flow.getId())) {
										ids.add(flow.getId());
									}
								}
							}
							
							if(node instanceof IAction) {
								if(((IAction)node).getInputs().length>0) {
									for(int j = 0 ; j < ((IAction)node).getInputs().length; j++) {
										IPin pin = ((IAction)node).getInputs()[j];
										for(IFlow flow :pin.getIncomings()) {
											Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
											String channel = allSyncChannels.get(chave);//Verify the control edges
											String channelObj = allSyncObj.get(chave);//Verify the object edges
											if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
												if (!ids.contains(flow.getId())) {
													ids.add(flow.getId());
												}
											}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
												if (!ids.contains(flow.getId())) {
													ids.add(flow.getId());
												}
											}
										}
										
									}
								}
								if(((IAction)node).getOutputs().length>0) {
									for(int j = 0 ; j < ((IAction)node).getOutputs().length; j++) {
										IPin pin = ((IAction)node).getOutputs()[j];
										for(IFlow flow :pin.getOutgoings()) {
											Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
											String channel = allSyncChannels.get(chave);//Verify the control edges
											String channelObj = allSyncObj.get(chave);//Verify the object edges
											if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
												if (!ids.contains(flow.getId())) {
													ids.add(flow.getId());
												}
											}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
												if (!ids.contains(flow.getId())) {
													ids.add(flow.getId());
												}
											}
										}
										
									}
								}
							}
						}
					}
				}
			} else {
				HashMap<Pair<IActivity, String>, ArrayList<String>> aux = new HashMap<>();
				aux = alphabetAD.getAlphabetAD();//TODO not every pin is here
				HashMap<Pair<IActivity,String>,String> SyncChannels = alphabetAD.getSyncChannelsEdge(); 
				HashMap<Pair<IActivity,String>,String> SyncObj = alphabetAD.getSyncObjectsEdge();
				
				if(node instanceof IActivityParameterNode) {
					aux = alphabetAD.getParameterAlphabetNode();
				}
				
				List<String> allflowsNode = aux.get(key);
				for (String objTrace : traceCounterExample) {
					if (allflowsNode != null && allflowsNode.contains(objTrace)) {//If contains on trace TODO(is wrong)
						if (!ids.contains(node.getId()) && 
								((node instanceof IAction && !objTrace.startsWith("oe_") && !objTrace.startsWith("ce_")) 
										|| node instanceof IControlNode || node instanceof IObjectNode)) {
							ids.add(node.getId());
						}
						for(int j = 0; j < node.getIncomings().length; j++) {//sweeps the incoming flows
							IFlow flow = node.getIncomings()[j];
							Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
							String channel = SyncChannels.get(chave);//Verify the control edges
							String channelObj = SyncObj.get(chave);//Verify the object edges
							if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
								if (!ids.contains(flow.getId())) {
									ids.add(flow.getId());
								}
							}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
								if (!ids.contains(flow.getId())) {
									ids.add(flow.getId());
								}
							}
							
						}
						for(int j = 0; j < node.getOutgoings().length; j++) {//sweeps the outgoing flows
							IFlow flow = node.getOutgoings()[j];
							Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
							String channel = SyncChannels.get(chave);//Verify the control edges
							String channelObj = SyncObj.get(chave);//Verify the object edges
							if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
								if(!ids.contains(flow.getId())) {
									ids.add(flow.getId());
								}
							}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
								if(!ids.contains(flow.getId())) {
									ids.add(flow.getId());
								}
							}
						}
					}
					
					if(node instanceof IAction) {
						if(((IAction)node).getInputs().length>0) {
							for(int j = 0 ; j < ((IAction)node).getInputs().length; j++) {
								IPin pin = ((IAction)node).getInputs()[j];
								for(IFlow flow :pin.getIncomings()) {
									Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
									String channel = SyncChannels.get(chave);//Verify the control edges
									String channelObj = SyncObj.get(chave);//Verify the object edges
									if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
										if (!ids.contains(flow.getId())) {
											ids.add(flow.getId());
										}
									}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
										if (!ids.contains(flow.getId())) {
											ids.add(flow.getId());
										}
									}
								}
								
							}
						}
						if(((IAction)node).getOutputs().length>0) {
							for(int j = 0 ; j < ((IAction)node).getOutputs().length; j++) {
								IPin pin = ((IAction)node).getOutputs()[j];
								for(IFlow flow :pin.getOutgoings()) {
									Pair<IActivity, String> chave = new Pair<IActivity, String>(diagram,flow.getId());
									String channel = SyncChannels.get(chave);//Verify the control edges
									String channelObj = SyncObj.get(chave);//Verify the object edges
									if(channel != null && traceCounterExample.contains(channel)) {//If is a control flow and is on the trace
										if (!ids.contains(flow.getId())) {
											ids.add(flow.getId());
										}
									}else if(channelObj != null && traceCounterExample.contains(channelObj)) {//If is a object flow and is on the trace
										if (!ids.contains(flow.getId())) {
											ids.add(flow.getId());
										}
									}
								}
								
							}
						}
					}
				}
			} 
		}
    	return ids;
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
