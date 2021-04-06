package com.ref.sysml.adapter;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.ActionUsage;
import org.omg.sysml.xtext.sysml.DecisionNode;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.ForkNode;
import org.omg.sysml.xtext.sysml.JoinNode;
import org.omg.sysml.xtext.sysml.MergeNode;
import org.omg.sysml.xtext.sysml.ParameterMembership;
import org.omg.sysml.xtext.sysml.Usage;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;

public class ActivityNode implements IActivityNode{
	protected EObject activityNode;
	protected String name;
	
	public ActivityNode(EObject activityNode) throws WellFormedException {
		super();
		this.activityNode = activityNode;
		
		if (activityNode instanceof ActionUsage) {
			name = ((ActionUsage) this.activityNode).getName();
			
		} else if (activityNode instanceof FeatureMembership) {
			name = ((FeatureMembership) this.activityNode).getFeatureName();

		} else if (activityNode instanceof MergeNode) {
			name = ((MergeNode) this.activityNode).getName();

		} else if (activityNode instanceof ForkNode) {
			name = ((ForkNode) this.activityNode).getName();

		} else if (activityNode instanceof JoinNode) {
			name = ((JoinNode) this.activityNode).getName();

		} else if (activityNode instanceof DecisionNode) {
			name = ((DecisionNode) this.activityNode).getName();

		} else if (activityNode instanceof ParameterMembership) {
			Usage usage = ((ParameterMembership) this.activityNode).getOwnedMemberParameter_comp();
			
			name = usage.getName();
		}
		
	}
	
	public ActivityNode(String name) {
		super();
		
		this.name = name;
	
	}
	
	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(activityNode));
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDefinition() {
		return ""; // Ainda nao definido
	}

	@Override
	public String[] getStereotypes() {
		return new String[0]; // Ainda nao definido
	}

	@Override
	public IFlow[] getIncomings() {
		
		String name = this.getName();
		
		if (activityNode instanceof ParameterMembership) {
			name = AdapterUtils.parameterName.get(this.getId());
		}
		
		List<IFlow> flows = AdapterUtils.getInFlows(name);
		
		return flows.toArray(new IFlow[flows.size()]);
	}

	@Override
	public IFlow[] getOutgoings() {
		String name = this.getName();
		
		if (activityNode instanceof ParameterMembership) {
			name = AdapterUtils.parameterName.get(this.getId());
		}
		
		List<IFlow> flows = AdapterUtils.getOutFlows(name);
		
		return flows.toArray(new IFlow[flows.size()]);
	}
	
	public void setName(String name) {
		this.name = name;
	}

}
