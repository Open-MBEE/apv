package com.ref.sysml.adapter;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.DecisionNode;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.ForkNode;
import org.omg.sysml.xtext.sysml.JoinNode;
import org.omg.sysml.xtext.sysml.MergeNode;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IControlNode;

public class ControlNode extends ActivityNode implements IControlNode{

	public ControlNode(EObject controlNode) throws WellFormedException {
		super(controlNode);
		
	}

	public ControlNode(String name) {
		super(name);
	}
	
	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(activityNode));
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
	public boolean isInitialNode() {
		return activityNode instanceof FeatureMembership;
	}

	@Override
	public boolean isFlowFinalNode() {
		return false; // Ainda nao definido
	}

	@Override
	public boolean isFinalNode() {
		return getName().equalsIgnoreCase("done");
	}

	@Override
	public boolean isForkNode() {
		return this.activityNode instanceof ForkNode;
	}

	@Override
	public boolean isJoinNode() {
		return this.activityNode instanceof JoinNode;
	}

	@Override
	public boolean isMergeNode() {
		return this.activityNode instanceof MergeNode;
	}

	@Override
	public boolean isDecisionNode() {
		return this.activityNode instanceof DecisionNode;
	}
}
