package com.ref.sysml.adapter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.ActionUsage;
import org.omg.sysml.xtext.sysml.Comment;
import org.omg.sysml.xtext.sysml.Documentation;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.ParameterMembership;
import org.omg.sysml.xtext.sysml.Succession;
import org.omg.sysml.xtext.sysml.SuccessionItemFlow;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IPartition;
import com.ref.parser.activityDiagram.Pair;


public class Activity implements IActivity{
	private ActionUsage activity;
	private IActivityDiagram activityDiagram;
	private IActivityNode[] activityNodes;
	private IPartition[] partitions = null;
	List<IActivityNode> listNodes;
	
	
	public Activity(ActionUsage activity) throws WellFormedException {
		super();
		this.activity = activity;	
		
		List<EObject> parameterList = ((ActionUsage) this.activity).getOwnedFeatureMembership_comp();
		listNodes = new ArrayList<IActivityNode>();
		
		for (EObject x : parameterList) {
			
			if (x instanceof ParameterMembership) {
				ActivityParameterNode parameter = new ActivityParameterNode(x);
//				parameter.setName(this.getName() + "_" + parameter.getName());
				
				listNodes.add(parameter);
				AdapterUtils.nodes.put(parameter.getName(), parameter);
				AdapterUtils.parameterName.put(parameter.getId(), this.getName() + "_" + parameter.getName());
				AdapterUtils.nodesType.put(this.getName() + "_" + parameter.getName(), ((ParameterMembership) x).getOwnedMemberParameter_comp());
			}
		}
	
		for (int i = 0; i < activity.getOwnedMembership_comp().size(); i++) {
			
			EObject feature = activity.getOwnedMembership_comp().get(i);
			
			if(feature instanceof org.omg.sysml.xtext.sysml.ControlNode) {
				
				ControlNode controlNode = new ControlNode(feature);
				
				listNodes.add(controlNode);
				AdapterUtils.nodes.put(controlNode.getName(), controlNode);
				
			} else if (feature instanceof ActionUsage) {
				
				Action actionNode = new Action(feature);
				
				listNodes.add(actionNode);
				AdapterUtils.nodes.put(actionNode.getName(), actionNode);
		
			} else if (feature instanceof FeatureMembership) {
				
				ControlNode controlNode = new ControlNode(feature);
				
				listNodes.add(controlNode);

				AdapterUtils.nodes.put(controlNode.getName(), controlNode);
				
			} else if(feature instanceof Succession) {
				Succession succession = (Succession) feature;
				
				String from = succession.getOwnedFeatureMembershipFrom_comp();
				String to = succession.getOwnedFeatureMembershipTo_comp();
				
				if (to.equalsIgnoreCase("done") 
						&& AdapterUtils.nodes.get("done") == null) {
					
					ControlNode finalNode = new ControlNode("done");
					
					listNodes.add(finalNode);
					AdapterUtils.nodes.put(finalNode.getName(), finalNode);
				}
				
				AdapterUtils.edges.put(new Pair<String, String>(from, to), new ControlFlow(succession));
		
			} else if (feature instanceof SuccessionItemFlow) {
				SuccessionItemFlow successionItemFlow = (SuccessionItemFlow) feature;

				String nameNodeFrom = successionItemFlow.getNameNodeFrom();
				String nameDataFrom = successionItemFlow.getNameDataFrom();
				
				String nameNodeTo = successionItemFlow.getNameNodeTo();
				String nameDataTo = successionItemFlow.getNameDataTo();
				
				String from = nameNodeFrom + ((nameDataFrom != null) ? "_" + nameDataFrom : "");
				String to = nameNodeTo + ((nameDataTo != null) ? "_" + nameDataTo : "");

				AdapterUtils.edges.put(new Pair<String, String>(from, to), new ObjectFlow(successionItemFlow));
			}
		
		}
		
		this.activityNodes = listNodes.toArray(new IActivityNode[listNodes.size()]);
		
	}

	public void setActivityDiagram(IActivityDiagram activityDiagram) {
		this.activityDiagram = activityDiagram;
	}
	
	public List<IActivityNode> getListNodes() {
		return listNodes;
	}

	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(activity));
	}

	@Override
	public String getName() {
		return activity.getName();
	}

	@Override
	public String getDefinition() {
		Documentation doc = this.activity.getDocumentation_comp().get(0);
		Comment comment = doc.getDocumentingComment_comp();
		
		return comment.getBody();
	}

	@Override
	public String[] getStereotypes() {
		return new String[0]; // Ainda nao definido
	}

	@Override
	public IActivityDiagram getActivityDiagram() {
		return this.activityDiagram;
	}

	@Override
	public IActivityNode[] getActivityNodes() {
		return this.activityNodes;
	}

	@Override
	public void setName(String nameAD){
		try {
			this.activity.setName(nameAD);
		} catch (Exception e) {
			// TODO not sure if is 100% right
			e.printStackTrace();
		}
	}

	@Override
	public IPartition[] getPartitions() {
		return partitions;
	}
	

}
