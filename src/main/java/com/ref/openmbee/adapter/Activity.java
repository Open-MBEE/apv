package com.ref.openmbee.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IPartition;

public class Activity implements IActivity{
	private String type;
	private String ownerId;
	private String id;
	private ArrayList<String> ownedParameterIds;
	private ArrayList<String> nodeIds;
	private ArrayList<String> edgeIds;
	
	private List<ActivityNode> nodes;
	private List<Flow> edges;
	private String name;
	private IPartition[] partitions;
	private IActivityDiagram activityDiagram;
	private String[] stereotypes;
	private String definition;
	
	public Activity(String type, String ownerId, String id, ArrayList<String> ownedParameterIds,
			ArrayList<String> nodeIds, ArrayList<String> edgeIds,String name, String[] stereotypes, String definition) {
		super();
		this.type = type;
		this.ownerId = ownerId;
		this.id = id;
		this.ownedParameterIds = ownedParameterIds;
		this.nodeIds = nodeIds;
		this.edgeIds = edgeIds;
		
		this.nodes = new ArrayList<>();
		this.edges = new ArrayList<>();
		this.name = name;
		this.stereotypes = stereotypes;
		this.definition = definition;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<String> getOwnedParameterIds() {
		return ownedParameterIds;
	}

	public void setOwnedParameterIds(ArrayList<String> ownedParameterIds) {
		this.ownedParameterIds = ownedParameterIds;
	}

	public ArrayList<String> getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(ArrayList<String> nodeIds) {
		this.nodeIds = nodeIds;
	}

	public ArrayList<String> getEdgeIds() {
		return edgeIds;
	}

	public void setEdgeIds(ArrayList<String> edgeIds) {
		this.edgeIds = edgeIds;
	}

	public List<ActivityNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<ActivityNode> nodes) {
		this.nodes = nodes;
	}

	public List<Flow> getEdges() {
		return edges;
	}

	public void setEdges(List<Flow> edges) {
		this.edges = edges;
	}

	public void addNode(ActivityNode node) {
		this.nodes.add(node);
	}
	public void addEdge(Flow edge) {
		this.edges.add(edge);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDefinition() {
		return this.definition;
	}

	@Override
	public String[] getStereotypes() {
		return this.stereotypes;
	}

	@Override
	public IActivityDiagram getActivityDiagram() {
		return this.activityDiagram;
	}

	@Override
	public void setActivityDiagram(IActivityDiagram activityDiagram) {
		this.activityDiagram = activityDiagram;
	}

	@Override
	public IActivityNode[] getActivityNodes() {
		IActivityNode[] actNodes = new IActivityNode[nodes.size()];
		for (int i = 0; i < nodes.size(); i++) {
			actNodes[i] = nodes.get(i);
		}
		return actNodes;
	}

	@Override
	public void setName(String nameAD) {
		this.name = nameAD;
		
	}

	@Override
	public IPartition[] getPartitions() {
		return this.partitions;
	}

	public void addPartition(IPartition newPartition) {
		IPartition[] aux = this.partitions;
		this.partitions = new IPartition[aux.length+1];
		
		for (int i = 0; i < aux.length; i++) {
			this.partitions[i] = aux[i];
		}
		this.partitions[aux.length] = newPartition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	
}
