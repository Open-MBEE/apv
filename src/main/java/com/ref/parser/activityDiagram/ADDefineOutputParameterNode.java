package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ADDefineOutputParameterNode {

	private IActivity ad;

	private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
	private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
	private HashMap<String, String> objectEdges;
	private ADUtils adUtils;
	private HashMap<String,String> parameterNodesOutput;


	public ADDefineOutputParameterNode(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
			HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
			HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges,
			ADUtils adUtils, HashMap<String,String> parameterNodesOutput) {
		this.ad = ad;
		this.alphabetNode = alphabetNode2;
		this.syncObjectsEdge = syncObjectsEdge2;
		this.objectEdges = objectEdges;
		this.adUtils = adUtils;
		this.parameterNodesOutput = parameterNodesOutput;
	}

	public String defineOutputParameterNode(IActivityNode activityNode) throws ParsingException {
		StringBuilder outParameter = new StringBuilder();
		ArrayList<String> alphabet = new ArrayList<>();
		String nameOutParameter = "parameter_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName());
		String nameOutParameterTermination = nameOutParameter + "_t";
		String endDiagram = "END_DIAGRAM_" + adUtils.nameDiagramResolver(ad.getName());
		IFlow[] inFlows = activityNode.getIncomings();
		String nameObjectUnique = "outParam";
		String parameterType = ((IActivityParameterNode) activityNode).getBase().getName();

		try {
			parameterNodesOutput.put(adUtils.nameDiagramResolver(activityNode.getName()), ((IActivityParameterNode) activityNode).getBase().getName());
		} catch (Exception e) {
			throw new ParsingException("Parameter node "+activityNode.getName()+" without base type\n");
		}
		
		outParameter.append(nameOutParameter + "(id) = ");

		outParameter.append("(");

		for (int i = 0; i < inFlows.length; i++) {
			Pair<IActivity, String> key = new Pair<IActivity, String>(ad, inFlows[i].getId());
			String typeObject;
			try {
				typeObject = ((IObjectFlow) inFlows[i]).getBase().getName();
			} catch (NullPointerException e) {
				throw new ParsingException("Object flow does not have a type.");
			}
			if (!typeObject.equals(parameterType)) {
				throw new ParsingException("Out Parameter " + activityNode.getName()
						+ ": incompatible types of incoming edge and parameter.");
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

			outParameter.append("(");

			adUtils.oe(alphabet, outParameter, oeIn, "?oe" + i, " -> ");
			adUtils.setLocalInput(alphabet, outParameter, nameObjectUnique,
					adUtils.nameDiagramResolver(activityNode.getName()), "oe"+i, oeIn, typeObject);
			if (i >= 0 && i < inFlows.length - 1) {
				outParameter.append("SKIP) [] ");
			} else {
				outParameter.append("SKIP)");
			}
		}

		outParameter.append("); ");

		adUtils.getLocal(alphabet, outParameter, nameObjectUnique, adUtils.nameDiagramResolver(activityNode.getName()),
				nameObjectUnique, parameterType);
		adUtils.set(alphabet, outParameter, activityNode.getName(), nameObjectUnique);

		adUtils.update(alphabet, outParameter, 1, 0, true);


		outParameter.append(nameOutParameter + "(id)\n");
		outParameter.append(nameOutParameterTermination + "(id) = ");

		outParameter.append("((" + nameOutParameter + "(id) /\\ " + endDiagram + "(id)) ");

		outParameter.append("[|{|");
		outParameter.append("get_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + ".id,");
		outParameter.append("set_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + ".id,");
		outParameter.append("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()) + ".id");
		outParameter.append("|}|] ");
		outParameter.append("Mem_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + "_" + nameObjectUnique + "_t(id,"
				+ adUtils.getDefaultValue(parameterType) + ")) ");

		outParameter.append("\\{|");
		outParameter.append("get_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + ".id,");
		outParameter.append("set_" + nameObjectUnique + "_" + adUtils.nameDiagramResolver(activityNode.getName()) + "_"
				+ adUtils.nameDiagramResolver(ad.getName()) + ".id");
		outParameter.append("|}\n");

		alphabet.add("endDiagram_" + adUtils.nameDiagramResolver(ad.getName()) + ".id");
		Pair<IActivity, String> key = new Pair<IActivity, String>(ad,
				adUtils.nameDiagramResolver("parameter_" + activityNode.getName()));
		alphabetNode.put(key, alphabet);

		return outParameter.toString();

	}
}
