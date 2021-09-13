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

public class ADDefineSignal {

    private IActivity ad;

    private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
    private List<Pair<String, Integer>> countSignal;
    private List<String> createdSignal;
    private ADUtils adUtils;


	
    public ADDefineSignal(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
                          List<Pair<String, Integer>> countSignal, List<String> createdSignal,ADUtils adUtils) {
        this.ad = ad;
        this.alphabetNode = alphabetNode2;
        this.countSignal = countSignal;
        this.createdSignal = createdSignal;
        this.adUtils = adUtils;        
    }

    
    public String defineSignal(IActivityNode activityNode) throws ParsingException {
        StringBuilder signal = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
        IFlow[] outFlows = activityNode.getOutgoings();
        IFlow[] inFlows = activityNode.getIncomings();
        IOutputPin[] outPins = ((IAction) activityNode).getOutputs();
        IInputPin[] inPins = ((IAction) activityNode).getInputs();
        List<String> namesMemoryLocal = new ArrayList<>();
        HashMap<String, String> typeMemoryLocal = new HashMap<>();
        int countInFlowPin = 0;
        int countOutFlowPin = 0;
        
        int idSignal = 1;
        for (int i = 0; i < countSignal.size(); i++) {
            if (countSignal.get(i).getKey().equals(adUtils.nameDiagramResolver(activityNode.getName()))) {
                idSignal = countSignal.get(i).getValue();
                break;
            }
        }

        String nameSignal = adUtils.nameDiagramResolver("signal_" + activityNode.getName()) + "_" + idSignal + "_" + adUtils.nameDiagramResolver(ad.getName());
        String nameSignalTermination = adUtils.nameDiagramResolver("signal_" + activityNode.getName()) + "_" + idSignal + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        
        String definition = activityNode.getDefinition();
        String[] definitionFinal = new String[0];

        if (definition != null && !(definition.equals(""))) {
            definitionFinal = definition.replace(" ", "").split(";");
        }
        
        signal.append(nameSignal + "(id) = ");
        adUtils.incomingEdges(activityNode, signal, alphabet, inFlows, inPins, namesMemoryLocal, typeMemoryLocal);
        
        for (int i = 0; i < namesMemoryLocal.size(); i++) {
            for (int j = 0; j < definitionFinal.length; j++) {
                String[] expression = definitionFinal[j].split("=");
                if (expression[0].equals(namesMemoryLocal.get(i))) {
                    List<String> expReplaced = adUtils.replaceExpression(expression[1]);    //get expression replace '+','-','*','/'
                    for (String value : expReplaced) {                //get all parts
                        for (int x = 0; x < namesMemoryLocal.size(); x++) {
                            if (value.equals(namesMemoryLocal.get(x))) {
                                adUtils.getLocal(alphabet, signal, namesMemoryLocal.get(x), adUtils.nameDiagramResolver(activityNode.getName()), namesMemoryLocal.get(x),typeMemoryLocal.get(namesMemoryLocal.get(x)));
                            }
                        }
                    }

                    adUtils.setLocal(alphabet, signal, expression[0], adUtils.nameDiagramResolver(activityNode.getName()), "(" + expression[1] + ")",expression[0]);

                }
            }
        }
        
        //count outFlowsPin
        for (int i = 0; i < inPins.length; i++) {
            countInFlowPin += inPins[i].getIncomings().length;
        }

        for (int i = 0; i < outPins.length; i++) {
            countOutFlowPin += outPins[i].getOutgoings().length;
        }
        adUtils.update(alphabet, signal, inFlows.length + countInFlowPin, outFlows.length + countOutFlowPin, false);
        
        for (String nameObj : namesMemoryLocal) {
            adUtils.getLocal(alphabet, signal, nameObj, adUtils.nameDiagramResolver(activityNode.getName()), nameObj,typeMemoryLocal.get(nameObj));
        }
        
        adUtils.signal(alphabet, adUtils.nameDiagramResolver(activityNode.getName()), signal,activityNode, namesMemoryLocal,typeMemoryLocal);
        
        adUtils.outgoingEdges(signal, alphabet, outFlows, outPins, definitionFinal, null);
        
        signal.append(nameSignal + "(id)\n");

        signal.append(nameSignalTermination + "(id) = ");
        if (namesMemoryLocal.size() > 0) {
            for (int i = 0; i < namesMemoryLocal.size(); i++) {
            	signal.append("(");
            }
            signal.append("(" + nameSignal + "(id) /\\ " + endDiagram + "(id)) ");

            for (int i = 0; i < namesMemoryLocal.size(); i++) {
            	signal.append("[|{|");
            	signal.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                signal.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                signal.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
                signal.append("|}|] ");

                String typeObj = typeMemoryLocal.get(namesMemoryLocal.get(i));                   

                signal.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_" + namesMemoryLocal.get(i) + "_t(id," + adUtils.getDefaultValue(typeObj) + ")) ");
            }

            signal.append("\\{|");

            for (int i = 0; i < namesMemoryLocal.size(); i++) {
                if (i == namesMemoryLocal.size() - 1) {
                	signal.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                	signal.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) +".id");
                } else {
                	signal.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                	signal.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                }
            }

            signal.append("|}\n");

        } else {
        	signal.append(nameSignal + "(id) /\\ " + endDiagram + "(id)\n");
        }
        

        alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
        Pair<IActivity,String> key = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver("signal_" + activityNode.getName() + "_" + idSignal));
        alphabetNode.put(key, alphabet);
        createdSignal.add(activityNode.getId());
        
        return signal.toString();
    }
    
}
