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

public class ADDefineValueSpecification {
	  private IActivity ad;
	  private HashMap<Pair<IActivity,String>, ArrayList<String>> alphabetNode;
	  private ADUtils adUtils;

	public ADDefineValueSpecification(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode,
			ADUtils adUtils) {
		this.ad = ad;
        this.alphabetNode = alphabetNode;
        this.adUtils = adUtils;
	}

	public Object defineValueSpecification(IActivityNode activityNode) throws ParsingException{
		StringBuilder valueSpecification = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String nameValueSpecification = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName());
        String nameValueSpecificationTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
        IFlow[] outFlows = activityNode.getOutgoings();
        IFlow[] inFlows = activityNode.getIncomings();
        IOutputPin[] outPins = ((IAction) activityNode).getOutputs();
        IInputPin[] inPins = ((IAction) activityNode).getInputs();
        List<String> namesMemoryLocal = new ArrayList<>();
        HashMap<String, String> typeMemoryLocal = new HashMap<>();
        int countInFlowPin = 0;
        int countOutFlowPin = 0;
        if(Character.isDigit(nameValueSpecification.charAt(0))) {
        	throw new ParsingException("The node name "+adUtils.nameDiagramResolver(activityNode.getName())+" cannot start with a number\n");
        }
        
        String definition = activityNode.getDefinition();
        String[] definitionFinal = new String[0];

        if (definition != null && !(definition.equals(""))) {
            definitionFinal = definition.replace(" ", "").split(";");
        }
        
        valueSpecification.append(nameValueSpecification + "(id) = ");
        
        //if(inFlows.length> 0 || inPins.length>0) {
        	adUtils.incomingEdges(activityNode, valueSpecification, alphabet, inFlows, inPins, namesMemoryLocal, typeMemoryLocal);
       /* }else {
        	adUtils.update(alphabet, valueSpecification, inFlows.length, outFlows.length, false);
        }*/
        	adUtils.update(alphabet, valueSpecification, inFlows.length+inPins.length, outFlows.length+outPins.length, false);
       
        for (int i = 0; i < namesMemoryLocal.size(); i++) {
            for (int j = 0; j < definitionFinal.length; j++) {
                String[] expression = definitionFinal[j].split("=");
                if (expression[0].equals(namesMemoryLocal.get(i))) {
                    List<String> expReplaced = adUtils.replaceExpression(expression[1]);    //get expression replace '+','-','*','/'
                    for (String value : expReplaced) {                //get all parts
                        for (int x = 0; x < namesMemoryLocal.size(); x++) {
                            if (value.equals(namesMemoryLocal.get(x))) {
                                adUtils.getLocal(alphabet, valueSpecification, namesMemoryLocal.get(x), adUtils.nameDiagramResolver(activityNode.getName()), namesMemoryLocal.get(x),typeMemoryLocal.get(namesMemoryLocal.get(x)));
                            }
                        }
                    }

                    adUtils.setLocal(alphabet, valueSpecification, expression[0], adUtils.nameDiagramResolver(activityNode.getName()), "(" + expression[1] + ")",expression[0]);

                }
            }
        }
        
        for (int i = 0; i < inPins.length; i++) {
            countInFlowPin += inPins[i].getIncomings().length;
        }

        for (int i = 0; i < outPins.length; i++) {
            countOutFlowPin += outPins[i].getOutgoings().length;
        }
        
        if (inFlows.length == 0 && countInFlowPin == 0) {
            adUtils.update(alphabet, valueSpecification, 1, outFlows.length, false); // outFlows - 1
        } else {
        	adUtils.update(alphabet, valueSpecification, inFlows.length + countInFlowPin, outFlows.length + countOutFlowPin, false);
        }
        
        for (String nameObj : namesMemoryLocal) {
            adUtils.getLocal(alphabet, valueSpecification, nameObj, adUtils.nameDiagramResolver(activityNode.getName()), nameObj,typeMemoryLocal.get(nameObj));
        }      
    
        adUtils.valueSpecification(alphabet, nameValueSpecification, valueSpecification, activityNode, ADUtils.nameResolver(activityNode.getName()), inPins,outPins,ad);
              
        adUtils.outgoingEdges(valueSpecification, alphabet, outFlows, outPins, definitionFinal, nameValueSpecification);

        
        if(activityNode.getIncomings().length > 0 || inPins.length > 0) {
        	valueSpecification.append(nameValueSpecification + "(id)\n");
        }else {
        	valueSpecification.append("SKIP\n");
        }
        valueSpecification.append(nameValueSpecificationTermination + "(id) = ");
        
        if (namesMemoryLocal.size() > 0) {
            for (int i = 0; i < namesMemoryLocal.size(); i++) {
            	valueSpecification.append("(");
            }
            valueSpecification.append("(" + nameValueSpecification + "(id) /\\ " + endDiagram + "(id)) ");

            for (int i = 0; i < namesMemoryLocal.size(); i++) {
            	valueSpecification.append("[|{|");
            	valueSpecification.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
            	valueSpecification.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
            	valueSpecification.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
            	valueSpecification.append("|}|] ");

                String typeObj = typeMemoryLocal.get(namesMemoryLocal.get(i));                   

                valueSpecification.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_" + namesMemoryLocal.get(i) + "_t(id," + adUtils.getDefaultValue(typeObj) + ")) ");
            }

            valueSpecification.append("\\{|");

            for (int i = 0; i < namesMemoryLocal.size(); i++) {
                if (i == namesMemoryLocal.size() - 1) {
                	valueSpecification.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                	valueSpecification.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) +".id");
                } else {
                	valueSpecification.append("get_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                	valueSpecification.append("set_" + namesMemoryLocal.get(i) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + ".id,");
                }
            }

            valueSpecification.append("|}\n");

        } else {
        	valueSpecification.append(nameValueSpecification + "(id) /\\ " + endDiagram + "(id)\n");
        }
        
        alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()+".id"));
        Pair<IActivity,String> pair = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver(activityNode.getName()));
        alphabetNode.put(pair, alphabet);
        
        return valueSpecification;
	}



}
