package com.ref.parser.activityDiagram;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IOutputPin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ADDefineAction {

    private IActivity ad;

    private HashMap<Pair<IActivity,String>, ArrayList<String>> alphabetNode;
    private ADUtils adUtils;

    public ADDefineAction(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2, 
    		ADUtils adUtils) {
        this.ad = ad;
        this.alphabetNode = alphabetNode2;
        this.adUtils = adUtils;
    }
    
    
    public String defineAction(IActivityNode activityNode) throws ParsingException {
    	StringBuilder action = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String nameAction = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName());
        String nameActionTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
        IFlow[] outFlows = activityNode.getOutgoings();
        IFlow[] inFlows = activityNode.getIncomings();
        IOutputPin[] outPins = ((IAction) activityNode).getOutputs();
        IInputPin[] inPins = ((IAction) activityNode).getInputs();
        List<String> namesMemoryLocal = new ArrayList<>();
        HashMap<String, String> typeMemoryLocal = new HashMap<>();
        int countInFlowPin = 0;
        int countOutFlowPin = 0;
        if(Character.isDigit(nameAction.charAt(0))) {
        	throw new ParsingException("The node name "+adUtils.nameDiagramResolver(activityNode.getName())+" cannot start with a number\n");
        }
        
        
        //if node has opaque expressions
        String definition = activityNode.getDefinition();
        String[] definitionFinal = new String[0];

        if (definition != null && !(definition.equals(""))) {
            definitionFinal = definition.replace(" ", "").split(";");
        }

        //name of the csp process
        action.append(nameAction + "(id) = ");

        //inputs of the action
        adUtils.incomingEdges(activityNode, action, alphabet, inFlows, inPins, namesMemoryLocal, typeMemoryLocal);
        
        
        //defining the event of the action 
        adUtils.event(alphabet, nameAction, action);

        //treating expressions inside opaque actions
        for (int i = 0; i < namesMemoryLocal.size(); i++) {
            for (int j = 0; j < definitionFinal.length; j++) {
                String[] expression = definitionFinal[j].split("=");
                if (expression[0].equals(namesMemoryLocal.get(i))) {
                    List<String> expReplaced = adUtils.replaceExpression(expression[1]);    //get expression replace '+','-','*','/'
                    for (String value : expReplaced) {                //get all parts
                        for (int x = 0; x < namesMemoryLocal.size(); x++) {
                            if (value.equals(namesMemoryLocal.get(x))) {
                                adUtils.getLocal(alphabet, action, namesMemoryLocal.get(x), adUtils.nameDiagramResolver(activityNode.getName()), namesMemoryLocal.get(x),typeMemoryLocal.get(namesMemoryLocal.get(x)));
                            }
                        }
                    }

                    adUtils.setLocal(alphabet, action, expression[0], adUtils.nameDiagramResolver(activityNode.getName()), "(" + expression[1] + ")",expression[0]);

                }
            }
        }
        
        //count input flows and output flows to calculate the update channel
        for (int i = 0; i < inPins.length; i++) {
            countInFlowPin += inPins[i].getIncomings().length;
        }
        for (int i = 0; i < outPins.length; i++) {
            countOutFlowPin += outPins[i].getOutgoings().length;
        }
        adUtils.update(alphabet, action, inFlows.length + countInFlowPin, outFlows.length + countOutFlowPin, false);

        // get any local data calculated in the actions to be sent in outgoing edges
        for (String nameObj : namesMemoryLocal) {
            adUtils.getLocal(alphabet, action, nameObj, adUtils.nameDiagramResolver(activityNode.getName()), nameObj,typeMemoryLocal.get(nameObj));
        }

        
        adUtils.outgoingEdges(action, alphabet, outFlows, outPins, definitionFinal, null);

        // defining the recursion
        action.append(nameAction + "(id)\n");

        // defining the terminating process for this action
        action.append(nameActionTermination + "(id) = ");

        if (namesMemoryLocal.size() > 0) {
            for (int i = 0; i < namesMemoryLocal.size(); i++) {
                action.append("(");
            }
            action.append("(" + nameAction + "(id) /\\ " + endDiagram + "(id)) ");

            // defining the parallelism with memory process
            for (int i = 0; i < namesMemoryLocal.size(); i++) {
                action.append("[|{|");
                action.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                action.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                action.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
                action.append("|}|] ");

                String typeObj = typeMemoryLocal.get(namesMemoryLocal.get(i));

                action.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_" + namesMemoryLocal.get(i) + "_t(id," + adUtils.getDefaultValue(typeObj) + ")) ");
            }

            action.append("\\{|");

            for (int i = 0; i < namesMemoryLocal.size(); i++) {
                if (i == namesMemoryLocal.size() - 1) {
                    action.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                    action.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) +".id");
                } else {
                    action.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                    action.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                }
            }

            action.append("|}\n");

        } else {
            action.append(nameAction + "(id) /\\ " + endDiagram + "(id)\n");
        }

        alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()+".id"));
        Pair<IActivity,String> pair = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver(activityNode.getName()));
        alphabetNode.put(pair, alphabet);

        return action.toString();
    }	
}