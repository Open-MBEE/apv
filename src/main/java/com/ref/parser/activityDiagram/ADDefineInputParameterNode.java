package com.ref.parser.activityDiagram;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IFlow;

public class ADDefineInputParameterNode {

    private IActivity ad;

    private HashMap<Pair<IActivity, String>, ArrayList<String>> parameterAlphabetNode;
    private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
    private HashMap<String, String> objectEdges;
    private List<String> allInitial;
    private ArrayList<String> alphabetAllInitialAndParameter;
    private ADUtils adUtils;
    private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
	private HashMap<String,String> parameterNodesInput;


    public ADDefineInputParameterNode(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> parameterAlphabetNode2, 
    		HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges, 
    		List<String> allInitial, ArrayList<String> alphabetAllInitialAndParameter, ADUtils adUtils,
    		HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,HashMap<String,String> parameterNodesInput) {
        this.ad = ad;
        this.parameterAlphabetNode = parameterAlphabetNode2;
        this.syncObjectsEdge = syncObjectsEdge2;
        this.objectEdges = objectEdges;
        this.allInitial = allInitial;
        this.alphabetAllInitialAndParameter = alphabetAllInitialAndParameter;
        this.adUtils = adUtils;
        this.alphabetNode = alphabetNode2;
        this.parameterNodesInput = parameterNodesInput;
    }

    public String defineInputParameterNode(IActivityNode activityNode) throws ParsingException {
        StringBuilder parameterNode = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String nameParameterNode = "parameter_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        IFlow[] outFlows = activityNode.getOutgoings();
        IFlow[] inFlows = activityNode.getIncomings();

        try {
			parameterNodesInput.put(adUtils.nameDiagramResolver(activityNode.getName()), ((IActivityParameterNode) activityNode).getBase().getName());
		} catch (Exception e) {
			throw new ParsingException("Parameter node "+activityNode.getName()+" without base type\n");
		}
        
        parameterNode.append(nameParameterNode + "(id) = ");

        adUtils.update(alphabet, parameterNode, inFlows.length, outFlows.length, false);
        adUtils.get(alphabet, parameterNode, adUtils.nameDiagramResolver(activityNode.getName()));

        parameterNode.append("(");

        for (int i = 0; i < outFlows.length; i++) {    //creates the parallel output channels
        	String typeObject = ((IActivityParameterNode)activityNode).getBase().getName();
        	Pair<IActivity,String> key = new Pair<IActivity, String>(ad,outFlows[i].getId());
        	
        	String oe; // creates output channels
			if (syncObjectsEdge.containsKey(key)) {
				oe = syncObjectsEdge.get(key);
				if (!objectEdges.containsKey(oe)) {
					objectEdges.put(oe, typeObject);
				}
			} else {
				oe = adUtils.createOE();
				syncObjectsEdge.put(key, oe);
				objectEdges.put(oe, typeObject);
			}
        	        	
            parameterNode.append("(");

            if (i >= 0 && i < outFlows.length - 1) {
                adUtils.oe(alphabet, parameterNode, oe, "!" + adUtils.nameDiagramResolver(activityNode.getName()), " -> SKIP) ||| ");
            } else {
                adUtils.oe(alphabet, parameterNode, oe, "!" + adUtils.nameDiagramResolver(activityNode.getName()), " -> SKIP)");
            }
        }

        parameterNode.append(")\n");
        Pair<IActivity,String> key = new Pair<IActivity, String>(ad,"parameter_"+adUtils.nameDiagramResolver(activityNode.getName()));
        parameterAlphabetNode.put(key, alphabet);
        alphabetNode.put(key, alphabet);
        allInitial.add(nameParameterNode);
        for (String channel : alphabet) {
            if (!alphabetAllInitialAndParameter.contains(channel)) {
                alphabetAllInitialAndParameter.add(channel);
            }
        }
        
        return parameterNode.toString();
    }
    
}
