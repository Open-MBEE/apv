package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;


public class ADDefineMemories {

    private IActivity ad;

    private HashMap<String, String> parameterNodesInput;
    private HashMap<String, String> parameterNodesOutput;
    private Map<Pair<String, String>,String> memoryLocal;
    private ADUtils adUtils;
    public static HashMap<IActivityNode,IActivity> CBAMemAlphabet = new HashMap<>();
    
    public ADDefineMemories(IActivity ad, HashMap<String, String> parameterNodesInput, HashMap<String, String> parameterNodesOutput,
                            Map<Pair<String, String>,String> memoryLocal, ADUtils adUtils) {
        this.ad = ad;
        this.parameterNodesInput = parameterNodesInput;
        this.parameterNodesOutput = parameterNodesOutput;
        this.memoryLocal = memoryLocal;
        this.adUtils = adUtils;
    }

    public String defineMemories() {
        StringBuilder memory = new StringBuilder();
        String nameDiagram = adUtils.nameDiagramResolver(ad.getName());
        StringBuilder alphabetMemory = new StringBuilder();
        
        defineMemoryLocal(memory, nameDiagram,alphabetMemory);
  
        if (parameterNodesInput.size() > 0 || parameterNodesOutput.size() > 0) {
            defineMemoryGlobal(memory, nameDiagram, parameterNodesInput,parameterNodesOutput.size()>0);
            defineMemoryGlobal(memory, nameDiagram, parameterNodesOutput,parameterNodesOutput.size()>0);

            memory.append("Mem_" + nameDiagram + "(id) = ");

            for (int i = 0; i < parameterNodesInput.size() + parameterNodesOutput.size() - 1; i++) {
                memory.append("(");
            }

            int i = 0;

            for (String input : parameterNodesInput.keySet()) {
                if (i <= 1) {
                    memory.append("Mem_" + nameDiagram + "_" + input + "_t(id," + adUtils.getDefaultValue(parameterNodesInput.get(input)) + ")");
                    if (i % 2 == 0 && (i + 1 < parameterNodesInput.size() || parameterNodesOutput.size() > 0)) {
                        memory.append(" [|{|endActivity_" + nameDiagram + "|}|] ");
                    } else if (parameterNodesInput.size() + parameterNodesOutput.size() > 1) {
                        memory.append(")");
                    }
                } else {
                    memory.append(" [|{|endActivity_" + nameDiagram + "|}|] ");
                    memory.append("Mem_" + nameDiagram + "_" + input + "_t(id," + adUtils.getDefaultValue(parameterNodesInput.get(input)) + "))");
                }

                i++;
            }

            for (String output : parameterNodesOutput.keySet()) {
                if (i <= 1) {
                    memory.append("Mem_" + nameDiagram + "_" + output + "_t(id," + adUtils.getDefaultValue(parameterNodesOutput.get(output)) + ")");
                    if (i % 2 == 0 && parameterNodesOutput.size() > 1 &&
                            i < parameterNodesOutput.size()) {
                        memory.append(" [|{|endActivity_" + nameDiagram + "|}|] ");
                    } else if (parameterNodesInput.size() + parameterNodesOutput.size() > 1) {
                        memory.append(")");
                    }
                } else {
                    memory.append(" [|{|endActivity_" + nameDiagram + "|}|] ");
                    memory.append("Mem_" + nameDiagram + "_" + output + "_t(id," + adUtils.getDefaultValue(parameterNodesOutput.get(output)) + "))");
                }

                i++;
            }

        }        
        memory.append("\n");
        return memory.toString();
    }

    private void defineMemoryLocal(StringBuilder memory, String nameDiagram, StringBuilder alphabetMemory) {
        HashMap<String,List<Pair<String,String>>> callBehaviors = new HashMap<String, List<Pair<String,String>>>();
        IActivityNode[] nodes = ad.getActivityNodes();
        for(IActivityNode node:nodes) {
        	if(node instanceof IAction) {
        		if(((IAction)node).isCallBehaviorAction()) {
        			callBehaviors.put(ADUtils.nameResolver(node.getName()), new ArrayList<>());
        		}
        	}
        }
        
    	for (Pair<String, String> pair : memoryLocal.keySet()) {
            memory.append("Mem_" + pair.getKey() + "_" + nameDiagram + "_" + pair.getValue() + "(id," + pair.getValue() + ") = ");
            memory.append("get_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ".id?c!" + pair.getValue() + " -> ");
            memory.append("Mem_" + pair.getKey() + "_" + nameDiagram + "_" + pair.getValue() + "(id," + pair.getValue() + ") [] ");
            memory.append("set_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ".id?c?" + pair.getValue() + " -> ");
            memory.append("Mem_" + pair.getKey() + "_" + nameDiagram + "_" + pair.getValue() + "(id," + pair.getValue() + ")\n");
            memory.append("Mem_" + pair.getKey() + "_" + nameDiagram + "_" + pair.getValue() + "_t" + "(id," + pair.getValue() + ") = ");
            memory.append("Mem_" + pair.getKey() + "_" + nameDiagram + "_" + pair.getValue() + "(id," + pair.getValue() + ") /\\ END_DIAGRAM_" + nameDiagram + "(id)\n");
            
            if(callBehaviors.containsKey(pair.getKey())) {
            	System.out.println();
            	List<Pair<String,String>> memories = new ArrayList<>();
            	memories = callBehaviors.get(pair.getKey());
            	memories.add(pair);
            	callBehaviors.put(pair.getKey(), memories);
	            alphabetMemory.append("get_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ".id,");
	            alphabetMemory.append("set_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ".id,");
            }
            
        }
    	
    	Set<String> keys = callBehaviors.keySet();
    	for(String CBAs : keys) {
    		memory.append("AlphabetMem"+CBAs+"_"+nameDiagram+"(id) = {|"+alphabetMemory+"endDiagram_"+nameDiagram+".id|}\n");
    		List<Pair<String,String>> aux = callBehaviors.get(CBAs);
    		List<Pair<String,Pair<String,String>>> memories = new ArrayList<>();
    		for (Pair<String,String> pair:aux) {
				Pair<String, String> newPair = new Pair<String, String>(pair.getValue(), memoryLocal.get(pair));
				memories.add(new Pair<String,Pair<String,String>>(CBAs,newPair));
			}
			//List<Pair<String,Pair<String,String>>> memories = callBehaviors.get(CBAs);
    		StringBuilder CSPCode = new StringBuilder();
    		CSPCode.append("Mem_"+CBAs+"_"+nameDiagram+"(id) =");
    		for(Pair<String,Pair<String,String>> memoria : memories) {
    			CSPCode.append(" Mem_" + memoria.getKey() + "_" + nameDiagram+ "_" + memoria.getValue().getKey() +"_t(id,"+adUtils.getDefaultValue(memoria.getValue().getValue())+") |||");//TODO mexer aqui
    		}
    		if(!(CSPCode.toString().equals("Mem_"+CBAs+"_"+nameDiagram+"(id) ="))) {
	    		CSPCode.replace(CSPCode.lastIndexOf("|||"), CSPCode.lastIndexOf("|||")+3, "\n");
	    		memory.append(CSPCode);
    		}
    	}
    }

    private void defineMemoryGlobal(StringBuilder memory, String nameDiagram, HashMap<String, String> memoryGlobal, boolean outputParam) {
    	for (String value : memoryGlobal.keySet()) {
            memory.append("Mem_" + nameDiagram + "_" + value + "(id," + value + ") = ");
            memory.append("get_" + value + "_" + nameDiagram + ".id?c!" + value + " -> ");
            memory.append("Mem_" + nameDiagram + "_" + value + "(id," + value + ") [] ");
            memory.append("set_" + value + "_" + nameDiagram + ".id?c?" + value + " -> ");
            memory.append("Mem_" + nameDiagram + "_" + value + "(id," + value + ")\n");
            memory.append("Mem_" + nameDiagram + "_" + value + "_t" + "(id," + value + ") = ");
            
            memory.append("Mem_" + nameDiagram + "_" + value + "(id," + value + ") /\\ (endActivity_" + nameDiagram + ".id"+(outputParam?"?" + value:"") + " -> SKIP)\n");
            //TODO verify the endActivity
        }
    }
}
