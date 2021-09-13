package com.ref.parser.activityDiagram;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ADDefineInitialNode {

    private IActivity ad;
    private List<String> allInitial;
    private ArrayList<String> alphabetInitial;
    private List<IActivityNode> queueNode;
    private HashMap<Pair<IActivity,String>, String> syncChannelsEdge;
    private ADUtils adUtils;
    private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;

    public ADDefineInitialNode(IActivity ad, List<String> allInitial, ArrayList<String> alphabetAllInitialAndParameter,
                               List<IActivityNode> queueNode, HashMap<Pair<IActivity, String>, String> syncChannelsEdge2, ADUtils adUtils,
                               HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2) {
        this.ad = ad;
        this.allInitial = allInitial;
        this.alphabetInitial = alphabetAllInitialAndParameter;
        this.queueNode = queueNode;
        this.syncChannelsEdge = syncChannelsEdge2;
        this.adUtils = adUtils;
        this.alphabetNode = alphabetNode2;
    }

    public String defineInitialNode(IActivityNode activityNode) throws ParsingException {
        StringBuilder initialNode = new StringBuilder();
        ArrayList<String> alphabet = new ArrayList<>();
        String diagram = adUtils.nameDiagramResolver(ad.getName());
        String nameInitialNode = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName());
        String nameInitialNodeTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_" + adUtils.nameDiagramResolver(ad.getName()) + "_t";
        IFlow[] outFlows = activityNode.getOutgoings();
        IFlow[] inFlows = activityNode.getIncomings();

        initialNode.append(nameInitialNode + "(id) = ");

        adUtils.update(alphabet, initialNode, inFlows.length, outFlows.length, false);

        initialNode.append("(");
        for (int i = 0; i < outFlows.length; i++) { // creates the parallel output channels
			if (outFlows[i] instanceof IObjectFlow) {
				throw new ParsingException("All Outgoings of the InitialNode "+ activityNode.getName()+ " must be a controlFlow.\n");
			}
			String ce;
            Pair<IActivity,String> key = new Pair<IActivity, String>(ad, outFlows[i].getId());

			if (syncChannelsEdge.containsKey(key)) {
				ce = syncChannelsEdge.get(key);
			} else {
				ce = adUtils.createCE();
				syncChannelsEdge.put(key, ce);
			}

			initialNode.append("(");

			if (i >= 0 && i < outFlows.length - 1) {
				adUtils.ce(alphabet, initialNode, ce, " -> SKIP) ||| ");
			} else {
				adUtils.ce(alphabet, initialNode, ce, " -> SKIP)");
			}
		}

        initialNode.append(")\n");
        
        initialNode.append(nameInitialNodeTermination + "(id) = ");
        initialNode.append(nameInitialNode + "(id) /\\ END_DIAGRAM_"+ diagram +"(id)\n");
        alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName())+".id");
        Pair<IActivity,String> pair = new Pair<IActivity, String>(ad,adUtils.nameDiagramResolver(activityNode.getName()));
        alphabetNode.put(pair, alphabet);

        allInitial.add(nameInitialNodeTermination);
        for (String channel : alphabet) {
            if (!alphabetInitial.contains(channel)) {
                alphabetInitial.add(channel);
            }
        }

        activityNode = outFlows[0].getTarget();    //set next action or control node

        for (int i = 1; i < outFlows.length; i++) {    //puts the remaining nodes in the queue
            if (!queueNode.contains(outFlows[i].getTarget())) {
                queueNode.add(outFlows[i].getTarget());
            }
        }

        return initialNode.toString();
    }
}
