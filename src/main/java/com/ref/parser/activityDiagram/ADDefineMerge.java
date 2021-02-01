package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ADDefineMerge {

	private IActivity ad;

	private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
	private HashMap<Pair<IActivity, String>, String> syncChannelsEdge;
	private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
	private HashMap<String, String> objectEdges;
	private ADUtils adUtils;

	public ADDefineMerge(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
			HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
			HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges,
			HashMap<String, String> parameterNodesInput, List<ArrayList<String>> unionList,
			HashMap<String, String> typeUnionList, ADUtils adUtils) {
		this.ad = ad;
		this.alphabetNode = alphabetNode2;
		this.syncChannelsEdge = syncChannelsEdge2;
		this.syncObjectsEdge = syncObjectsEdge2;
		this.objectEdges = objectEdges;
		this.adUtils = adUtils;
	}

	public String defineMerge(IActivityNode activityNode) throws ParsingException {
		StringBuilder merge = new StringBuilder();
		ArrayList<String> alphabet = new ArrayList<>();
		String nameMerge = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName());
		String nameMergeTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + "_t";
		String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
		IFlow[] outFlows = activityNode.getOutgoings();
		IFlow[] inFlows = activityNode.getIncomings();
		String nameObjectUnique = "";


		if (outFlows.length != 1) {
			throw new ParsingException("Merge node must have exactly one outgoing edge.");
		}

		merge.append(nameMerge + "(id) = (");

		if (outFlows[0] instanceof IObjectFlow) {
			for (int i = 0; i < inFlows.length; i++) {
				String typeObject;
				Pair<IActivity, String> key = new Pair<IActivity, String>(ad, inFlows[i].getId());
				if (!(inFlows[i] instanceof IObjectFlow)) {
					throw new ParsingException("Merge Node " + activityNode.getName()
							+ ": if the outgoing edge is a ObjectFlow, all incoming edges must be ObjectFlows.");
				} else {
					try {
						typeObject = ((IObjectFlow) inFlows[i]).getBase().getName();
					} catch (NullPointerException e) {
						throw new ParsingException("Object flow does not have a type.");
					}
					if (!typeObject.equals((((IObjectFlow)outFlows[0]).getBase().getName()))) {
						throw new ParsingException("Merge Node " + activityNode.getName() + ": incompatible types of incoming and outgoing edges.");
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
					nameObjectUnique = "mergeMem";
					merge.append("(");
					adUtils.oe(alphabet, merge, oeIn, "?oe" + i, " -> ");
					adUtils.setLocalInput(alphabet, merge, nameObjectUnique,
							adUtils.nameDiagramResolver(activityNode.getName()), "oe"+i, oeIn, typeObject);
					if (i >= 0 && i < inFlows.length - 1) {
						merge.append("SKIP) [] ");
					} else {
						merge.append("SKIP)");
					}
				}
			}
		} else {
			for (int i = 0; i < inFlows.length; i++) {
				Pair<IActivity, String> key = new Pair<IActivity, String>(ad, inFlows[i].getId());
				if (inFlows[i] instanceof IObjectFlow) {
					throw new ParsingException("Merge Node " + activityNode.getName()
							+ ": if the outgoing edge is a ControlFlow, all incoming edges must be ControlFlows.");
				} else {

					String ceIn;

					if (syncChannelsEdge.containsKey(key)) {
						ceIn = syncChannelsEdge.get(key);
					} else {
						ceIn = adUtils.createCE();
						syncChannelsEdge.put(key, ceIn);
					}
					merge.append("(");

					if (i >= 0 && i < inFlows.length - 1) {
						adUtils.ce(alphabet, merge, ceIn, " -> SKIP) [] ");
					} else {
						adUtils.ce(alphabet, merge, ceIn, " -> SKIP)");
					}
				}
			}
		}
		
		merge.append("); ");
		Pair<IActivity, String> key = new Pair<IActivity, String>(ad, outFlows[0].getId());
		if (!nameObjectUnique.equals("")) {
			String typeObject = ((IObjectFlow) outFlows[0]).getBase().getName();
			
			adUtils.getLocal(alphabet, merge, nameObjectUnique, adUtils.nameDiagramResolver(activityNode.getName()),
					nameObjectUnique, typeObject);
			
			
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
			
			adUtils.oe(alphabet, merge, oe, "!" + nameObjectUnique, " -> ");

			merge.append(nameMerge + "(id)\n");
			merge.append(nameMergeTermination + "(id) = ");

			merge.append("((" + nameMerge + "(id) /\\ " + endDiagram + "(id)) ");

			merge.append("[|{|");
			merge.append("get_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()) + ",");
			merge.append("set_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()) + ",");
			merge.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()));
			merge.append("|}|] ");
			merge.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()) + "_" + nameObjectUnique + "_t(id,"
					+ adUtils.getDefaultValue(typeObject) + ")) ");

			merge.append("\\{|");
			merge.append("get_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()) + ",");
			merge.append("set_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()));
			merge.append("|}\n");

		} else {
			String ce; // creates output channels
			
			if (syncChannelsEdge.containsKey(key)) {
				ce = syncChannelsEdge.get(key);
			} else {
				ce = adUtils.createCE();
				syncChannelsEdge.put(key, ce);
			}
			adUtils.ce(alphabet, merge, ce, " -> ");

			merge.append(nameMerge + "(id)\n");
			merge.append(nameMergeTermination + "(id) = ");
			merge.append(nameMerge + "(id) /\\ " + endDiagram + "(id)\n");
		}

		alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName() + ".id"));
		key = new Pair<IActivity, String>(ad, adUtils.nameDiagramResolver(activityNode.getName()));
		alphabetNode.put(key, alphabet);


		return merge.toString();
	}

}
