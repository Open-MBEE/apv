package com.ref.sysml.adapter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.AcceptActionUsage;
import org.omg.sysml.xtext.sysml.ActionDefinition;
import org.omg.sysml.xtext.sysml.ActionUsage;
import org.omg.sysml.xtext.sysml.BindingConnector;
import org.omg.sysml.xtext.sysml.Comment;
import org.omg.sysml.xtext.sysml.Documentation;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.ParameterMembership;
import org.omg.sysml.xtext.sysml.SendActionUsage;
import org.omg.sysml.xtext.sysml.Succession;
import org.omg.sysml.xtext.sysml.SuccessionItemFlow;
import org.omg.sysml.xtext.sysml.Usage;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IPartition;
import com.ref.parser.activityDiagram.Pair;


public class Activity implements IActivity{
	private ActionDefinition activity;
	private IActivityDiagram activityDiagram;
	private IActivityNode[] activityNodes;
	private IPartition[] partitions = null;
	List<IActivityNode> listNodes;
	
	
	public Activity(ActionDefinition activity) throws WellFormedException {
		super();
		this.activity = activity;	
		
		List<EObject> parameterList = ((ActionDefinition) this.activity).getOwnedFeatureMembership_comp();
		listNodes = new ArrayList<IActivityNode>();
		
		for (EObject x : parameterList) {
			
			if (x instanceof ParameterMembership) {
				ActivityParameterNode parameter = new ActivityParameterNode(x, this);
				
				listNodes.add(parameter);
				AdapterUtils.nodes.put( this.getName() + "_" + parameter.getName(), parameter);
				AdapterUtils.parameterName.put(parameter.getId(),  this.getName() + "_" + parameter.getName());
				AdapterUtils.nodesType.put( this.getName() + "_" + parameter.getName(), ((ParameterMembership) x).getOwnedMemberParameter_comp());
			}
		}
	
		for (int i = 0; i < activity.getOwnedMembership_comp().size(); i++) {
			
			EObject feature = activity.getOwnedMembership_comp().get(i);
			
			if(feature instanceof org.omg.sysml.xtext.sysml.ControlNode) {
				
				ControlNode controlNode = new ControlNode(feature, this);
				controlNode.setName(this.getName() + "_" + controlNode.getName());
				
				listNodes.add(controlNode);
				AdapterUtils.nodes.put(controlNode.getName(), controlNode);
				
			} else if (feature instanceof SendActionUsage) { 
				
				Action actionNode = new Action(feature, this);
				actionNode.setName(this.getName() + "_" + actionNode.getName());
				
				listNodes.add(actionNode);
				AdapterUtils.nodes.put(actionNode.getName(), actionNode);
				
			} else if (feature instanceof AcceptActionUsage) { 
				
				Action actionNode = new Action(feature, this);
				actionNode.setName(this.getName() + "_" + actionNode.getName());
				actionNode.setRealName(this.getName() + "_" + actionNode.getRealName());
				
				listNodes.add(actionNode);
				AdapterUtils.nodes.put(actionNode.getName(), actionNode);
				
			} else if (feature instanceof ActionUsage) {
				
				Action actionNode = new Action(feature, this);
				actionNode.setName(this.getName() + "_" + actionNode.getName());
				
				listNodes.add(actionNode);
				AdapterUtils.nodes.put(actionNode.getName(), actionNode);
		
			} else if (feature instanceof FeatureMembership) {
				
				ControlNode controlNode = new ControlNode(feature, this);
				controlNode.setName(this.getName() + "_" + controlNode.getName());
				
				listNodes.add(controlNode);
				AdapterUtils.nodes.put(controlNode.getName(), controlNode);
				
			} else if(feature instanceof Succession) {
				Succession succession = (Succession) feature;
				
				String from = this.getName() + "_" + succession.getOwnedFeatureMembershipFrom_comp();
				String to = this.getName() + "_" + succession.getOwnedFeatureMembershipTo_comp();
				
				if (to.equalsIgnoreCase(this.getName() + "_done") 
						&& AdapterUtils.nodes.get(this.getName() + "_done") == null) {
					
					ControlNode finalNode = new ControlNode(this.getName() + "_done", this);
					
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
				
				String from = "";
				String to = "";
				
				if (nameDataFrom != null) {
					from = nameNodeFrom + "_" + nameDataFrom;
				} else {
					from = this.getName() + "_" + nameNodeFrom;
				}
				
				if (nameDataTo != null) {
					to = nameNodeTo + "_" + nameDataTo;
				} else {
					to = this.getName() + "_" + nameNodeTo;
				}
				
				Class base;
				ObjectFlow object = new ObjectFlow(successionItemFlow);
				
				Usage usage = AdapterUtils.nodesType.get(from);

				if (usage != null) {
					base = new Class(usage);
					object.setBase(base);
				} else {
					usage = AdapterUtils.nodesType.get(to);
					base = new Class(usage);
					object.setBase(base);
				}
				
				AdapterUtils.edges.put(new Pair<String, String>(from, to), object);
			} else if (feature instanceof BindingConnector) {
			
				BindingConnector bindingConnector = (BindingConnector) feature;

				String nameNodeFrom = bindingConnector.getNameNodeFrom();
				String nameDataFrom = bindingConnector.getNameDataFrom();
				
				String nameNodeTo = bindingConnector.getNameNodeTo();
				String nameDataTo = bindingConnector.getNameDataTo();
				
				String from = "";
				String to = "";
				
				if (nameDataFrom != null) {
					from = nameNodeFrom + "_" + nameDataFrom;
				} else {
					from = this.getName() + "_" + nameNodeFrom;
				}
				
				if (nameDataTo != null) {
					to = nameNodeTo + "_" + nameDataTo;
				} else {
					to = this.getName() + "_" + nameNodeTo;
				}
				
				Class base;
				ObjectFlow object = new ObjectFlow(bindingConnector);
				
				Usage usage = AdapterUtils.nodesType.get(from);

				if (usage != null) {
					base = new Class(usage);
					object.setBase(base);
				} else {
					usage = AdapterUtils.nodesType.get(to);
					base = new Class(usage);
					object.setBase(base);
				}
				
				AdapterUtils.edges.put(new Pair<String, String>(from, to), object);
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
		String definition = "";
		
		if (this.activity.getDocumentation_comp().size() > 0) {
			Documentation doc = this.activity.getDocumentation_comp().get(0);
			Comment comment = doc.getDocumentingComment_comp();
			definition = comment.getBody();
		}
		
		return definition;
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
