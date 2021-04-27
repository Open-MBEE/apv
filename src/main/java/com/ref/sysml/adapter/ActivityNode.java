package com.ref.sysml.adapter;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.AcceptActionUsage;
import org.omg.sysml.xtext.sysml.ActionUsage;
import org.omg.sysml.xtext.sysml.DecisionNode;
import org.omg.sysml.xtext.sysml.Expression;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.ForkNode;
import org.omg.sysml.xtext.sysml.JoinNode;
import org.omg.sysml.xtext.sysml.MergeNode;
import org.omg.sysml.xtext.sysml.ParameterMembership;
import org.omg.sysml.xtext.sysml.SendActionUsage;
import org.omg.sysml.xtext.sysml.TransitionUsage;
import org.omg.sysml.xtext.sysml.Usage;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.parser.activityDiagram.Pair;

public class ActivityNode implements IActivityNode{
	protected EObject activityNode;
	protected String name;
	protected String realName;
	protected IActivity owner;
	
	public ActivityNode(EObject activityNode, IActivity owner) throws WellFormedException {
		super();
		this.activityNode = activityNode;
		this.owner = owner;
	
		if (activityNode instanceof FeatureMembership) {
			name = ((FeatureMembership) this.activityNode).getFeatureName();
			
		} else if (activityNode instanceof SendActionUsage) {
			name = ((SendActionUsage) this.activityNode).getName();
			
		} else if (activityNode instanceof AcceptActionUsage) {
			name = AdapterUtils.signals.get(((AcceptActionUsage) this.activityNode).getName());
			this.realName = ((AcceptActionUsage) this.activityNode).getName();
			
		} else if (activityNode instanceof MergeNode) {
			name = ((MergeNode) this.activityNode).getName();
			
		} else if (activityNode instanceof ForkNode) {
			name = ((ForkNode) this.activityNode).getName();
			
		} else if (activityNode instanceof JoinNode) {
			name = ((JoinNode) this.activityNode).getName();
			
		} else if (activityNode instanceof DecisionNode) {
			DecisionNode decision = ((DecisionNode) this.activityNode);
			
			name = decision.getName();
			
			for (TransitionUsage x : decision.getGuards()) {
				boolean isParam = false;
				
				List<IFlow> flows = AdapterUtils.getInFlows(this.owner.getName() + "_" + name);
				IFlow[] inFlows = flows.toArray(new IFlow[flows.size()]);
				
				String paramName = "";
				Usage usage = null;
				
				if (inFlows.length == 1) {
					paramName = AdapterUtils.parameterName.get(AdapterUtils.getSource(inFlows[0]).getId());
					usage = AdapterUtils.nodesType.get(AdapterUtils.getSource(inFlows[0]).getName());
					if (paramName != null || usage != null) {
						isParam = true;
					}
				}
				
				if (isParam) { // SuccessionItemFlow
					String from = this.owner.getName() + "_" + name;
					String to = "";
					
					if (x.getTarget().contains("::")) {
						to = x.getTarget().replace("::", "_");
					} else {
						to = this.owner.getName() + "_" + x.getTarget();
					}
					
					ObjectFlow object = new ObjectFlow(x);
					
					if (x.getExpression() != null) {
						object.setGuard(createGuard(x.getExpression()));
					} else if(x.getType().equals("else")) {
						object.setGuard("else");
					}
					
					if (usage == null) {
						usage = AdapterUtils.nodesType.get(paramName);
					}
					
					Class base = new Class(usage);
					object.setBase(base);
					
					AdapterUtils.nodesType.put(this.owner.getName() + "_" + name, usage);
					
					AdapterUtils.edges.put(new Pair<String, String>(from, to), object);
					
				} else { // Succession
					String from = this.owner.getName() + "_" + name;
					String to = this.owner.getName() + "_" + x.getTarget().replace("::", "_");
					
					if (to.equalsIgnoreCase(this.owner.getName() + "_done") 
							&& AdapterUtils.nodes.get(this.owner.getName() + "_done") == null) {
						
						ControlNode finalNode = new ControlNode(this.owner.getName() + "_done", this.owner);
						
//						listNodes.add(finalNode);
						AdapterUtils.nodes.put(finalNode.getName(), finalNode);
					}
					
					ControlFlow control = new ControlFlow(x);
					
					if (x.getExpression() != null) {
						control.setGuard(createGuard(x.getExpression()));
					}
					
					AdapterUtils.edges.put(new Pair<String, String>(from, to), control);
					
				}
				
			}
			
			
		} else if (activityNode instanceof ParameterMembership) {
			Usage usage = ((ParameterMembership) this.activityNode).getOwnedMemberParameter_comp();
			
			name = usage.getName();
			
		} else if (activityNode instanceof ActionUsage) {
			name = ((ActionUsage) this.activityNode).getName();
			
		}
		
	}
	
	public ActivityNode(String name, IActivity owner) {
		super();
		
		this.name = name;
		this.realName = name;
		this.owner = owner;
	
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
		return ""; 
	}

	@Override
	public String[] getStereotypes() {
		return new String[0];
	}

	@Override
	public IFlow[] getIncomings() {
		
		String name = this.realName;
		
		if (activityNode instanceof ParameterMembership) {
			name = AdapterUtils.parameterName.get(this.getId());
		}
		
		List<IFlow> flows = AdapterUtils.getInFlows(name);
		
		return flows.toArray(new IFlow[flows.size()]);
	}

	@Override
	public IFlow[] getOutgoings() {
		String name = this.realName;
		
		if (activityNode instanceof ParameterMembership) {
			name = AdapterUtils.parameterName.get(this.getId());
		}
		
		List<IFlow> flows = AdapterUtils.getOutFlows(name);
		IFlow[] arrayFlows = flows.toArray(new IFlow[flows.size()]);
		
		if (this.activityNode instanceof DecisionNode && arrayFlows[0] instanceof ObjectFlow) {
			Arrays.sort(arrayFlows);
		} 
		
		return arrayFlows;
	}
	
	public void setName(String name) {
		this.name = name;
		
		if (!(this.activityNode instanceof AcceptActionUsage)) { // not a Accept Event
			this.realName = name;			
		}
		
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getRealName() {
		return this.realName;
	}
	
	private String createGuard(Expression exp) {
		String value1 = "";
		String value2 = "";
		
		if (exp.getValue1().contains("::")) {
			value1 = exp.getValue1().split("::")[1];
		} else {
			value1 = exp.getValue1();
		}
		
		if (exp.getValue2().contains("::")) {
			value2 = exp.getValue2().split("::")[1];
		} else {
			value2 = exp.getValue2();
		}
		
		String expression = value1  + " " + exp.getOperator() + " " + value2;
		
		if (exp.getNextExpression() != null) {
			expression += " " + exp.getConditional() + " " + createGuard(exp.getNextExpression());
		}
		
		return expression;
	}

}
