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

public class ADDefineAccept {

    private IActivity ad;

    private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
	private List<Pair<String, Integer>> countAccept;
    private List<String> createdAccept;
    private ADUtils adUtils;

    public ADDefineAccept(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
    		List<Pair<String, Integer>> countAccept, List<String> createdAccept, ADUtils adUtils) {
        this.ad = ad;
        this.alphabetNode = alphabetNode2;
        this.countAccept = countAccept;
        this.createdAccept = createdAccept;
        this.adUtils = adUtils;
    }
    
    

	public String defineAccept(IActivityNode activityNode) throws ParsingException {
		StringBuilder accept = new StringBuilder();
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
		
		
		
		int idAccept = 1;
        for (int i = 0; i < countAccept.size(); i++) {
            if (countAccept.get(i).getKey().equals(adUtils.nameDiagramResolver(activityNode.getName()))) {
                idAccept = countAccept.get(i).getValue();
                break;
            }
        }
        
        String nameAccept = adUtils.nameDiagramResolver("accept_" + activityNode.getName()) + "_" + idAccept + "_" + adUtils.nameDiagramResolver(ad.getName());
        String nameAcceptTermination = adUtils.nameDiagramResolver("accept_" + activityNode.getName()) + "_" + idAccept + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";

        String definition = activityNode.getDefinition();
        String[] definitionFinal = new String[0];

        if (definition != null && !(definition.equals(""))) {
            definitionFinal = definition.replace(" ", "").split(";");
        }
        
        accept.append(nameAccept + "(id) = ");

        adUtils.incomingEdges(activityNode, accept, alphabet, inFlows, inPins, namesMemoryLocal, typeMemoryLocal);
        adUtils.accept(alphabet ,adUtils.nameDiagramResolver(activityNode.getName()), accept,activityNode, nameAccept, outPins);
        
        for (int i = 0; i < namesMemoryLocal.size(); i++) {
            for (int j = 0; j < definitionFinal.length; j++) {
                String[] expression = definitionFinal[j].split("=");
                if (expression[0].equals(namesMemoryLocal.get(i))) {
                    List<String> expReplaced = adUtils.replaceExpression(expression[1]);    //get expression replace '+','-','*','/'
                    for (String value : expReplaced) {                //get all parts
                        for (int x = 0; x < namesMemoryLocal.size(); x++) {
                            if (value.equals(namesMemoryLocal.get(x))) {
                                adUtils.getLocal(alphabet, accept, namesMemoryLocal.get(x), adUtils.nameDiagramResolver(activityNode.getName()), namesMemoryLocal.get(x),typeMemoryLocal.get(namesMemoryLocal.get(x)));
                            }
                        }
                    }

                    adUtils.setLocal(alphabet, accept, expression[0], adUtils.nameDiagramResolver(activityNode.getName()), "(" + expression[1] + ")",expression[0]);

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
        
        if (inFlows.length == 0 && countInFlowPin == 0) {
            adUtils.update(alphabet, accept, 1, outFlows.length, false); // outFlows - 1
        } else {
        	adUtils.update(alphabet, accept, inFlows.length + countInFlowPin, outFlows.length + countOutFlowPin, false);
        }
        
        for (String nameObj : namesMemoryLocal) {
            adUtils.getLocal(alphabet, accept, nameObj, adUtils.nameDiagramResolver(activityNode.getName()), nameObj,typeMemoryLocal.get(nameObj));
        }
        
        adUtils.outgoingEdges(accept, alphabet, outFlows, outPins, definitionFinal, nameAccept);

        accept.append(nameAccept + "(id)\n");

        accept.append(nameAcceptTermination + "(id) = ");
        
        if (namesMemoryLocal.size() > 0) {
            for (int i = 0; i < namesMemoryLocal.size(); i++) {
            	accept.append("(");
            }
            accept.append("(" + nameAccept + "(id) /\\ " + endDiagram + "(id)) ");

            for (int i = 0; i < namesMemoryLocal.size(); i++) {
            	accept.append("[|{|");
                accept.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                accept.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                accept.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
                accept.append("|}|] ");

                String typeObj = typeMemoryLocal.get(namesMemoryLocal.get(i));                   

                accept.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_" + namesMemoryLocal.get(i) + "_t(id," + adUtils.getDefaultValue(typeObj) + ")) ");
            }

            accept.append("\\{|");

            for (int i = 0; i < namesMemoryLocal.size(); i++) {
                if (i == namesMemoryLocal.size() - 1) {
                	accept.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                    accept.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) +".id");
                } else {
                	accept.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                    accept.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                }
            }

            accept.append("|}\n");

        } else {
        	accept.append(nameAccept + "(id) /\\ " + endDiagram + "(id)\n");
        }
        

        alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
        Pair<IActivity,String> key = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver("accept_" + activityNode.getName() + "_" + idAccept));
        alphabetNode.put(key, alphabet);
        createdAccept.add(activityNode.getId());
        
		return accept.toString();
	}
    
}