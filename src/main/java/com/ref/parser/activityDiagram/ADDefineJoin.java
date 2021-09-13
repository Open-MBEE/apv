package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;

import com.ref.exceptions.ParsingException;

import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ADDefineJoin {

	private IActivity ad;

	private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
	private HashMap<Pair<IActivity, String>, String> syncChannelsEdge;
	private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
	private HashMap<String, String> objectEdges;
	private ADUtils adUtils;


	public ADDefineJoin(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
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

	public String defineJoin(IActivityNode activityNode) throws ParsingException {
		StringBuilder joinNode = new StringBuilder();
		ArrayList<String> alphabet = new ArrayList<>();
		String nameJoin = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName());
		String nameJoinTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + "_t";
		String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
		IFlow[] outFlows = activityNode.getOutgoings();
		IFlow[] inFlows = activityNode.getIncomings();
		HashMap<String, String> nameObjects = new HashMap<>();
		HashMap<String, String> typeObjects = new HashMap<>();
		String typeObject = null;
		boolean sync2Bool = false;

		if (outFlows.length != 1) {
			throw new ParsingException("Join node must have exactly one outgoing edge.");
		}

		joinNode.append(nameJoin + "(id) = (");

		ArrayList<String> ceInitials = new ArrayList<>();
		for (int i = 0; i < inFlows.length; i++) {
			ceInitials.add(inFlows[i].getId());
			Pair<IActivity, String> key = new Pair<IActivity, String>(ad, inFlows[i].getId());
			if (inFlows[i] instanceof IObjectFlow) {
				try {
					typeObject = ((IObjectFlow) inFlows[i]).getBase().getName();
				} catch (NullPointerException e) {
					throw new ParsingException("Object flow does not have a type.");
				}
				nameObjects.put(inFlows[i].getId(), "oe" + i);
				typeObjects.put(inFlows[i].getId(), typeObject);

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
				joinNode.append("(");

				if (i >= 0 && i < inFlows.length - 1) {
					adUtils.oe(alphabet, joinNode, oeIn, "?oe" + i, " -> ");
					adUtils.setLocalInput(alphabet, joinNode, "oe" + i,
							adUtils.nameDiagramResolver(activityNode.getName()), "oe" + i, oeIn, typeObject);
					joinNode.append("SKIP) ||| ");
				} else {
					adUtils.oe(alphabet, joinNode, oeIn, "?oe" + i, " -> ");
					adUtils.setLocalInput(alphabet, joinNode, "oe" + i,
							adUtils.nameDiagramResolver(activityNode.getName()), "oe" + i, oeIn, typeObject);
					joinNode.append("SKIP)");
				}
				sync2Bool = true;

			} else {
				String ceIn;
				joinNode.append("(");
				if (syncChannelsEdge.containsKey(key)) {
					ceIn = syncChannelsEdge.get(key);
				} else {
					ceIn = adUtils.createCE();
					syncChannelsEdge.put(key, ceIn);
				}

				if (i >= 0 && i < inFlows.length - 1) {
					adUtils.ce(alphabet, joinNode, ceIn, " -> SKIP) ||| ");
				} else {
					adUtils.ce(alphabet, joinNode, ceIn, " -> SKIP)");
				}
			}
		}

		joinNode.append("); ");

		adUtils.update(alphabet, joinNode, inFlows.length, outFlows.length, false);

		if (sync2Bool) {
			for (String key : nameObjects.keySet()) {
				adUtils.getLocal(alphabet, joinNode, nameObjects.get(key),
						adUtils.nameDiagramResolver(activityNode.getName()), nameObjects.get(key),
						typeObjects.get(key));
			}
		}

		joinNode.append("(");

		Pair<IActivity, String> key = new Pair<IActivity, String>(ad, outFlows[0].getId());
		if (sync2Bool) {
			String oeOut;

			try {
				typeObject = ((IObjectFlow) outFlows[0]).getBase().getName();
			} catch (NullPointerException e) {
				throw new ParsingException("Object flow does not have a type.");
			}
			if (syncObjectsEdge.containsKey(key)) {
				oeOut = syncObjectsEdge.get(key);
				if (!objectEdges.containsKey(oeOut)) {
					objectEdges.put(oeOut, typeObject);
				}
			} else {
				oeOut = adUtils.createOE();
				syncObjectsEdge.put(key, oeOut);
				objectEdges.put(oeOut, typeObject);
			}

			int i = 0;
			for (String id : nameObjects.keySet()) { // creates the parallel output channels

				if (!typeObjects.get(id).equals(typeObject)) {
					continue;
				}

				joinNode.append("(");

				if (i >= 0 && i < nameObjects.keySet().size() - 1) {
					adUtils.oe(alphabet, joinNode, oeOut, "!" + nameObjects.get(id), " -> SKIP) |~| ");
				} else {
					adUtils.oe(alphabet, joinNode, oeOut, "!" + nameObjects.get(id), " -> SKIP)");
				}
				i++;
			}
		} else {
			String ceOut;
			if (syncChannelsEdge.containsKey(key)) {
				ceOut = syncChannelsEdge.get(key);
			} else {
				ceOut = adUtils.createCE();
				syncChannelsEdge.put(key, ceOut);
			}

			joinNode.append("(");

			adUtils.ce(alphabet, joinNode, ceOut, " -> SKIP)");
		}

		joinNode.append("); ");

		joinNode.append(nameJoin + "(id)\n");

		joinNode.append(nameJoinTermination + "(id) = ");

		for (int i = 0; i < nameObjects.keySet().size(); i++) {
			joinNode.append("(");
		}

		joinNode.append("(" + nameJoin + "(id) /\\ " + endDiagram + "(id))");

		for (String id: nameObjects.keySet()) { // creates the parallel output channels
			joinNode.append(" [|{|");
			joinNode.append("get_" + nameObjects.get(id) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()) + ",");
			joinNode.append("set_" + nameObjects.get(id) + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()) + ",");
			joinNode.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()) + ".id|}|] ");
			joinNode.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
					+ adUtils.nameDiagramResolver(ad.getName()) + "_" + nameObjects.get(id) + "_t(id,"
					+ adUtils.getDefaultValue(typeObjects.get(id)) + "))");
		}

		if (nameObjects.keySet().size() > 0) {
			joinNode.append(" \\{|");
			int i = 0;
			for (String id: nameObjects.keySet()) {
				joinNode.append("get_" + nameObjects.get(id) + "_" + adUtils.nameDiagramResolver(activityNode.getName())
						+ "_" + adUtils.nameDiagramResolver(ad.getName()) + ",");
				joinNode.append("set_" + nameObjects.get(id) + "_" + adUtils.nameDiagramResolver(activityNode.getName())
						+ "_" + adUtils.nameDiagramResolver(ad.getName()));
				if (i < nameObjects.keySet().size() - 1) {
					joinNode.append(",");
				}
				i++;
			}

			joinNode.append("|}");

		}

		joinNode.append("\n");

		alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()) + ".id");
		key = new Pair<IActivity, String>(ad,
				adUtils.nameDiagramResolver(activityNode.getName()));
		alphabetNode.put(key, alphabet);

		return joinNode.toString();
	}
}
