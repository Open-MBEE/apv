package com.ref.sysml.adapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.DecisionNode;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.ForkNode;
import org.omg.sysml.xtext.sysml.JoinNode;
import org.omg.sysml.xtext.sysml.MergeNode;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IControlNode;

public class ControlNode extends ActivityNode implements IControlNode{

	public ControlNode(EObject controlNode, IActivity owner) throws WellFormedException {
		super(controlNode, owner);
	}

	public ControlNode(String name, IActivity owner) {
		super(name, owner);
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
		Pattern p = Pattern.compile("_done$");
		Matcher m = p.matcher(getName());
		return m.find();
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
