package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ADDefineFork {

	private IActivity ad;

	private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
	private HashMap<Pair<IActivity, String>, String> syncChannelsEdge;
	private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
	private HashMap<String, String> objectEdges;
	private ADUtils adUtils;

	public ADDefineFork(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
			HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
			HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges,
			ADUtils adUtils) {
		this.ad = ad;
		this.alphabetNode = alphabetNode2;
		this.syncChannelsEdge = syncChannelsEdge2;
		this.syncObjectsEdge = syncObjectsEdge2;
		this.objectEdges = objectEdges;
		this.adUtils = adUtils;
	}

	public String defineFork(IActivityNode activityNode) throws ParsingException {
		StringBuilder forkNode = new StringBuilder();
		ArrayList<String> alphabet = new ArrayList<>();
		String nameFork = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName());
		String nameForkTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + "_t";
		String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
		IFlow[] outFlows = activityNode.getOutgoings();
		IFlow[] inFlows = activityNode.getIncomings();

	
		if (inFlows.length != 1) {
			throw new ParsingException("Fork node must have exactly one incoming edge.");
		}

		forkNode.append(nameFork + "(id) = ");

		IFlow inEdge = inFlows[0];
		Pair<IActivity, String> key = new Pair<IActivity, String>(ad, inEdge.getId());

		// case input is object
		if (inEdge instanceof IObjectFlow) {
			String oeIn;
        	String typeObject;
        	try {
				typeObject = ((IObjectFlow)inEdge).getBase().getName();
			} catch (NullPointerException e) {
				throw new ParsingException("Object flow does not have a type.");
			}
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
			
        	adUtils.oe(alphabet, forkNode, oeIn, "?x", " -> ");			

        	adUtils.update(alphabet, forkNode, inFlows.length, outFlows.length, false);

			forkNode.append("(");
			
			for (int i = 0; i < outFlows.length; i++) { // creates the parallel output channels
				
				if (!(outFlows[i] instanceof IObjectFlow)) {
					throw new ParsingException("If the incoming edge of fork node "+activityNode.getName()+" is a ObjectFlow, then\r\n" + 
							"all outgoing edges shall be ObjectFlows");
				}
				key = new Pair<IActivity, String>(ad, outFlows[i].getId());
				String oe;
	        	try {
					typeObject = ((IObjectFlow)outFlows[i]).getBase().getName();
				} catch (NullPointerException e) {
					throw new ParsingException("Object flow does not have a type.");
				}
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
				
				forkNode.append("(");

				if (i >= 0 && i < outFlows.length - 1) {
					adUtils.oe(alphabet, forkNode, oe, "!x" , " -> SKIP) ||| ");
				} else {
					adUtils.oe(alphabet, forkNode, oe, "!x" , " -> SKIP)");
				}
			}
		} else { // case is control
			String ceIn;

			if (syncChannelsEdge.containsKey(key)) {
				ceIn = syncChannelsEdge.get(key);
			} else {
				ceIn = adUtils.createCE();
				syncChannelsEdge.put(key, ceIn);
			}
			adUtils.ce(alphabet, forkNode, ceIn, " -> ");
			
			adUtils.update(alphabet, forkNode, inFlows.length, outFlows.length, false);

			forkNode.append("(");
			
			for (int i = 0; i < outFlows.length; i++) { // creates the parallel output channels
				if (outFlows[i] instanceof IObjectFlow) {
					throw new ParsingException("If the incoming edge of fork node "+activityNode.getName()+" is a ControlFlow, then\r\n" + 
							"all outgoing edges shall be ControlFlows");
				}
				key = new Pair<IActivity, String>(ad, outFlows[i].getId());
				String ce;
				if (syncChannelsEdge.containsKey(key)) {
					ce = syncChannelsEdge.get(key);
				} else {
					ce = adUtils.createCE();
					syncChannelsEdge.put(key, ce);
				}

				forkNode.append("(");

				if (i >= 0 && i < outFlows.length - 1) {
					adUtils.ce(alphabet, forkNode, ce, " -> SKIP) ||| ");
				} else {
					adUtils.ce(alphabet, forkNode, ce, " -> SKIP)");
				}
			}
		}
		forkNode.append("); ");

		forkNode.append(nameFork + "(id)\n");

		forkNode.append(nameForkTermination + "(id) = ");
		forkNode.append(nameFork + "(id) /\\ " + endDiagram + "(id)\n");

		alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()) + ".id");
		key = new Pair<IActivity, String>(ad, adUtils.nameDiagramResolver(activityNode.getName()));
		alphabetNode.put(key, alphabet);

		return forkNode.toString();
	}
}
