package com.ref.parser.activityDiagram;


import java.util.ArrayList;
import java.util.HashMap;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ADDefineFlowFinal {

    private IActivity ad;

    private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
    private HashMap<Pair<IActivity, String>, String> syncChannelsEdge;
    private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
    private HashMap<String, String> objectEdges;
    private ADUtils adUtils;

    public ADDefineFlowFinal(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2, HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
                             HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges, ADUtils adUtils) {
        this.ad = ad;
        this.alphabetNode = alphabetNode2;
        this.syncChannelsEdge = syncChannelsEdge2;
        this.syncObjectsEdge = syncObjectsEdge2;
        this.objectEdges = objectEdges;
        this.adUtils = adUtils;
    }

    public String defineFlowFinal(IActivityNode activityNode) throws ParsingException {
        StringBuilder flowFinal = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String nameFlowFinal = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName());
        String nameFlowFinalTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
        HashMap<String, String> nameObjects = new HashMap<>();
        IFlow[] inFlows = activityNode.getIncomings();
        
        if(nameFlowFinal.equals("_" + adUtils.nameDiagramResolver(ad.getName()))) {
        	throw new ParsingException("The final flow node is unnamed\n");
        }
        flowFinal.append(nameFlowFinal + "(id) = ");

        ArrayList<String> ceInitials = new ArrayList<>();

        for (int i = 0; i < inFlows.length; i++) {
            ceInitials.add(inFlows[i].getId());
            Pair<IActivity,String> key = new Pair<IActivity, String>(ad,inFlows[i].getId());
            if (syncObjectsEdge.containsKey(key)) {
                String ceIn2 = syncObjectsEdge.get(key);
                nameObjects.put(inFlows[i].getId(), objectEdges.get(ceIn2));
            }

        }

        flowFinal.append("(");
        for (int i = 0; i < ceInitials.size(); i++) {
        	Pair<IActivity,String> key = new Pair<IActivity, String>(ad,ceInitials.get(i));
        	if (inFlows[i] instanceof IObjectFlow) {
				String typeObject;
				try {
					typeObject = ((IObjectFlow) inFlows[i]).getBase().getName();
				} catch (NullPointerException e) {
					throw new ParsingException("Object flow does not have a type.");
				}
				String oeIn;
				if (syncObjectsEdge.containsKey(key)) {
					oeIn = syncObjectsEdge.get(key);
					if (!objectEdges.containsKey(oeIn)) {
						objectEdges.put(oeIn, typeObject);
					}
				} else {
					oeIn = adUtils.createOE();
					syncObjectsEdge.put(key, oeIn);
					objectEdges.put(oeIn, typeObject);
				}
				flowFinal.append("(");

				if (i >= 0 && i < ceInitials.size() - 1) {
					adUtils.ce(alphabet, flowFinal, oeIn, "?x" + " -> SKIP) [] ");
				} else {
					adUtils.ce(alphabet, flowFinal, oeIn, "?x" + " -> SKIP)");
				}

			} else {
				String ceIn;

				if (syncChannelsEdge.containsKey(key)) {
					ceIn = syncChannelsEdge.get(key);
				} else {
					ceIn = adUtils.createCE();
					syncChannelsEdge.put(key, ceIn);
				}
				
				flowFinal.append("(");

				if (i >= 0 && i < ceInitials.size() - 1) {
					adUtils.ce(alphabet, flowFinal, ceIn, " -> SKIP) [] ");
				} else {
					adUtils.ce(alphabet, flowFinal, ceIn, " -> SKIP)");
				}
			}

        }

        flowFinal.append("); ");

        adUtils.update(alphabet, flowFinal, 1, 0, true);

        flowFinal.append(nameFlowFinal + "(id)\n");

        flowFinal.append(nameFlowFinalTermination + "(id) = ");
        flowFinal.append(nameFlowFinal + "(id) /\\ " + endDiagram + "(id)\n");

        alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
    	Pair<IActivity,String> key = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver(activityNode.getName()));
        alphabetNode.put(key, alphabet);

        activityNode = null;

        return flowFinal.toString();
    }
}
