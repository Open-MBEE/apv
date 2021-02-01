package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ADDefineObjectNode {

	private IActivity ad;

	private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
	private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
	private HashMap<String, String> objectEdges;
	private ADUtils adUtils;

	public ADDefineObjectNode(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
			HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
			HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges,
			ADUtils adUtils) {
		this.ad = ad;
		this.alphabetNode = alphabetNode2;
		this.syncObjectsEdge = syncObjectsEdge2;
		this.objectEdges = objectEdges;
		this.adUtils = adUtils;
	}

	public String defineObjectNode(IActivityNode activityNode) throws ParsingException {
		StringBuilder objectNode = new StringBuilder();
		ArrayList<String> alphabet = new ArrayList<>();
		String nameObjectNode = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName());
		String nameObjectNodeTermination = adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + "_t";
		String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
		IFlow[] outFlows = activityNode.getOutgoings();
		IFlow[] inFlows = activityNode.getIncomings();
		String nameObjectUnique = "";
		String parameterType = ((IActivityParameterNode) activityNode).getBase().getName();

		objectNode.append(nameObjectNode + "(id) = (");

		for (int i = 0; i < inFlows.length; i++) {
			String typeObject;
			Pair<IActivity, String> key = new Pair<IActivity, String>(ad, inFlows[i].getId());
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
			nameObjectUnique = "objMem";
			objectNode.append("(");
			adUtils.oe(alphabet, objectNode, oeIn, "?oe" + i, " -> ");
			adUtils.setLocalInput(alphabet, objectNode, nameObjectUnique,
					adUtils.nameDiagramResolver(activityNode.getName()), "oe" + i, oeIn, typeObject);
			if (i >= 0 && i < inFlows.length - 1) {
				objectNode.append("SKIP) [] ");
			} else {
				objectNode.append("SKIP)");
			}
		}
		
		objectNode.append("); ");

		adUtils.update(alphabet, objectNode, 1, activityNode.getOutgoings().length, false);
		
		adUtils.getLocal(alphabet, objectNode, nameObjectUnique,
				adUtils.nameDiagramResolver(activityNode.getName()), nameObjectUnique, parameterType);

		objectNode.append("(");
		
		for (int i = 0; i < outFlows.length; i++) {
			Pair<IActivity, String> key = new Pair<IActivity, String>(ad, outFlows[i].getId());
			String typeObject = ((IObjectFlow) outFlows[i]).getBase().getName();
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
			objectNode.append("(");

			if (i >= 0 && (i < outFlows.length - 1)) {
				adUtils.oe(alphabet, objectNode, oe, "!" + nameObjectUnique, " -> SKIP) ||| ");
			} else {
				adUtils.oe(alphabet, objectNode, oe, "!" + nameObjectUnique, " -> SKIP)");
			}
		}

		objectNode.append("); ");

		objectNode.append(nameObjectNode + "(id)\n");
		objectNode.append(nameObjectNodeTermination + "(id) = ");

		objectNode.append("((" + nameObjectNode + "(id) /\\ " + endDiagram + "(id)) ");

		objectNode.append("[|{|");
		objectNode.append("get_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName())
				+ "_" + adUtils.nameDiagramResolver(ad.getName()) + ",");
		objectNode.append("set_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName())
				+ "_" + adUtils.nameDiagramResolver(ad.getName()) + ",");
		objectNode.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()));
		objectNode.append("|}|] ");
		objectNode.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + "_" + nameObjectUnique + "_t(id,"
				+ adUtils.getDefaultValue(parameterType) + ")) ");

		objectNode.append("\\{|");
		objectNode.append("get_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName())
				+ "_" + adUtils.nameDiagramResolver(ad.getName()) + ",");
		objectNode.append("set_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName())
				+ "_" + adUtils.nameDiagramResolver(ad.getName()));
		objectNode.append("|}\n");



		alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()) + ".id");
		Pair<IActivity, String> key = new Pair<IActivity, String>(ad,
				adUtils.nameDiagramResolver(activityNode.getName()));
		alphabetNode.put(key, alphabet);

		return objectNode.toString();

	}
}
