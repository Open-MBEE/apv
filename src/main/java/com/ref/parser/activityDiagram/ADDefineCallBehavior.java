package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IOutputPin;

public class ADDefineCallBehavior {

    private IActivity ad;

    private HashMap<Pair<IActivity,String>, ArrayList<String>> alphabetNode;
    private ADUtils adUtils;

    public ADDefineCallBehavior(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2, 
                             ADUtils adUtils) {
        this.ad = ad;
        this.alphabetNode = alphabetNode2;
        this.adUtils = adUtils;
    }
    
    public String defineCallBehaviour(IActivityNode activityNode) throws ParsingException { 
        StringBuilder callBehaviour = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String nameCallBehaviour = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName());
        String namCallBehaviourTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
        IFlow[] outFlows = activityNode.getOutgoings();
        IFlow[] inFlows = activityNode.getIncomings();
        IOutputPin[] outPins = ((IAction) activityNode).getOutputs();
        IInputPin[] inPins = ((IAction) activityNode).getInputs();
        List<String> namesMemoryLocal = new ArrayList<>();
        List<String> namesOutpins = new ArrayList<>();
        HashMap<String, String> typeMemoryLocal = new HashMap<>();
        int countInFlowPin = 0;
        int countOutFlowPin = 0;
    	
        ADDefineMemories.CBAMemAlphabet.put(activityNode,(IActivity) ((IAction)activityNode).getCallingActivity());
        
        for (int i = 0; i < outPins.length; i++) {
            namesOutpins.add(outPins[i].getName());
        }
        
        callBehaviour.append(nameCallBehaviour + "(id) = ");

        adUtils.incomingEdges(activityNode, callBehaviour, alphabet, inFlows, inPins, namesMemoryLocal, typeMemoryLocal);
        
        for (String nameObj : namesMemoryLocal) {
            adUtils.getLocal(alphabet, callBehaviour, nameObj, adUtils.nameDiagramResolver(activityNode.getName()), nameObj, typeMemoryLocal.get(nameObj));
        }
       
        try {
            	((IAction) activityNode).getCallingActivity().getActivityDiagram().getName();
			} 
        catch (Exception e) {
				throw new ParsingException("The Call Behavior Action "+activityNode.getName()+" is unlinked with other diagram\n");
		}
        callBehavior(alphabet, callBehaviour, ((IAction) activityNode).getCallingActivity().getActivityDiagram().getName(), namesMemoryLocal, namesOutpins,activityNode);
       
        
        //count outFlowsPin
        for (int i = 0; i < inPins.length; i++) {
            countInFlowPin += inPins[i].getIncomings().length;
        }

        for (int i = 0; i < outPins.length; i++) {
            countOutFlowPin += outPins[i].getOutgoings().length;
        }
        
        adUtils.update(alphabet, callBehaviour, inFlows.length + countInFlowPin, outFlows.length + countOutFlowPin, false);
        
        adUtils.outgoingEdges(callBehaviour, alphabet, outFlows, outPins, null, null);
        
        callBehaviour.append(nameCallBehaviour+"(id)\n");

        callBehaviour.append(namCallBehaviourTermination + "(id) = ");

        if (namesMemoryLocal.size() > 0) {
            for (int i = 0; i < namesMemoryLocal.size(); i++) {
                callBehaviour.append("(");
            }
            callBehaviour.append("("+nameCallBehaviour+"(id)) ");
            for(int i = 0; i < namesMemoryLocal.size(); i++) {
            	callBehaviour.append("[|AlphabetMem"+nameCallBehaviour+"(id)|] "
            						+"Mem_"+nameCallBehaviour+"(id)) \\diff(AlphabetMem"+nameCallBehaviour
            						+"(id),{|endDiagram_"+adUtils.nameDiagramResolver(ad.getName())+".id|}) /\\ "+ endDiagram+ "(id)\n");
            }
     
        } else {
            callBehaviour.append(nameCallBehaviour + "(id) /\\ " + endDiagram + "(id)\n");
        }

        alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
        Pair<IActivity,String> key = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver(activityNode.getName()));
        alphabetNode.put(key, alphabet);

        return callBehaviour.toString();

    }

    private void callBehavior(ArrayList<String> alphabetNode, StringBuilder action, String nameAD, List<String> inputPins, List<String> outputPins,IActivityNode activityNode) {
    	int count = 0;
    	count = adUtils.addCountCall(adUtils.nameDiagramResolver(nameAD));
    	String Activity = "";
    	String setMem = "setMemOutParam"+adUtils.nameDiagramResolver(nameAD);	
    	String startAct = "startActivity_" + adUtils.nameDiagramResolver(nameAD) + "." + count;
    	String endAct = "endActivity_" + adUtils.nameDiagramResolver(nameAD) + "." + count;
    	String id = ((IAction) activityNode).getCallingActivity().getId();
    	List<Pair<String,String>> CBAList = ADParser.countcallBehavior.get(id);//Gets the List with everyone that calls this CBA
    	int index = 1;
    	for(int i=0;i<CBAList.size();i++) {//sweeps throgh the List to find the index of the node
    		if(activityNode.getId().equals(CBAList.get(i).getKey())) {
    			index = i+1;
    		}
    	}
    	
    	alphabetNode.add(startAct);
    	alphabetNode.add(endAct);
    	adUtils.getCallBehaviourNumber().add(new Pair<>(adUtils.nameDiagramResolver(nameAD), count));
    	
    	List<String> outputPinsUsed = adUtils.getCallBehaviourOutputs().get(adUtils.nameDiagramResolver(nameAD));
        if (outputPinsUsed == null) {
            outputPinsUsed = inputPins;
            HashMap<String,List<String>> aux = adUtils.getCallBehaviourInputs();
            aux.put(nameAD, inputPins);
            adUtils.setCallBehaviourInputs(aux);
        }
        boolean pinsUsed = false;
        if(!outputPinsUsed.isEmpty()) {
        	for (String pin : outputPinsUsed) {
                Activity += "!" + pin;
                pinsUsed = true;   
            }
        	action.append("(");
            action.append("normal("+adUtils.nameDiagramResolver(nameAD)+"("+index+")) [|{|"+startAct+","+endAct+"|}|] (");
        }else {
        	action.append("normal("+adUtils.nameDiagramResolver(nameAD)+"("+index+"))");
        }

        
        action.append((Activity != ""?startAct+Activity + " -> ":""));
        
        Activity = "";	
        
        outputPinsUsed = adUtils.getCallBehaviourOutputs().get(adUtils.nameDiagramResolver(nameAD));
        if (outputPinsUsed == null) {
            outputPinsUsed = outputPins;
        	HashMap<String,List<String>> aux =	adUtils.getCallBehaviourOutputs();
            aux.put(nameAD, outputPins);
            adUtils.setCallBehaviourOutputs(aux);
        }
        
        for (String pin : outputPinsUsed) {
            Activity += "?" + pin;
            setMem += "!" + pin;
        }
        
       
        action.append((Activity != "" || pinsUsed?endAct+Activity + " -> SKIP))":""));
        
        for(IOutputPin pin : ((IAction)activityNode).getOutputs()) {
        	adUtils.setLocal(alphabetNode,action,pin.getName(),adUtils.nameDiagramResolver(activityNode.getName()),pin.getName(),pin.getBase().getName()); //TODO adicionar um setLocal que pegue aqui	
        }
        action.append(!setMem.equals("setMemOutParam"+adUtils.nameDiagramResolver(nameAD))?"SKIP));":";");
        
    }
}
