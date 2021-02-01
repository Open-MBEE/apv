package com.ref.astah.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.change_vision.jude.api.inf.model.IActivityNode;
import com.change_vision.jude.api.inf.model.IActivityParameterNode;
import com.change_vision.jude.api.inf.model.IControlNode;
import com.change_vision.jude.api.inf.model.IObjectNode;
import com.change_vision.jude.api.inf.model.IPin;
import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IFlow;

public class AdapterUtils {
    public static enum FlowType { OBJECT_FLOW, CONTROL_FLOW}
    public static enum WhiteDiamondNodeType {DECISION_NODE, MERGE_NODE, OTHERS}
    public static HashMap<String,IFlow> edges = new HashMap<>();

	public static FlowType flowType(com.change_vision.jude.api.inf.model.IFlow flow, List<String> searched) {
		IActivityNode previous = flow.getSource();
		
		if(previous instanceof IObjectNode ||
		   previous instanceof IActivityParameterNode ||
		   previous instanceof IPin) {
			return FlowType.OBJECT_FLOW;
		}
		else if(previous instanceof IControlNode){
			return findOrigin((IControlNode) previous, searched);
		}
		return FlowType.CONTROL_FLOW; //if is a actionNode then it must be a controlFlow
		
	}

	private static FlowType findOrigin(IControlNode controlNode,List<String> searched) {
		
		if(wDNodeType(controlNode) == WhiteDiamondNodeType.MERGE_NODE) {

			for(int i = 0; i< controlNode.getIncomings().length; i++) {
				
				if(!searched.contains((controlNode.getIncomings()[i].getId()))) {
					
					searched.add(controlNode.getIncomings()[i].getId());
					if(controlNode.getIncomings()[i].getSource() instanceof IControlNode) {
						return findOrigin((IControlNode) controlNode.getIncomings()[i].getSource(),searched);
					}else {
						return flowType(controlNode.getIncomings()[i],searched);
					}
				}
				
			}
			
		}else if(controlNode.isForkNode()) {
			if(controlNode.getIncomings()[0].getSource() instanceof IControlNode) {
				return findOrigin((IControlNode) controlNode.getIncomings()[0].getSource(),searched);
			}else {
				return flowType(controlNode.getIncomings()[0],searched);
			}			
		}else if(controlNode.isInitialNode()) {
			return FlowType.CONTROL_FLOW;
			
		}else if(controlNode.isJoinNode()) {
			List<FlowType> flowTypes = new ArrayList<AdapterUtils.FlowType>();
			for(int i = 0; i< controlNode.getIncomings().length; i++) {
				if (!searched.contains(controlNode.getIncomings()[i].getId())) {
					searched.add(controlNode.getIncomings()[i].getId());
					flowTypes.add(flowType(controlNode.getIncomings()[i], searched));
				}
			}
			if(flowTypes.contains(FlowType.OBJECT_FLOW)) {
				return FlowType.OBJECT_FLOW;
			}else {
				return FlowType.CONTROL_FLOW;
			}
			
		}else if(wDNodeType(controlNode) == WhiteDiamondNodeType.DECISION_NODE) {
			if(controlNode.getIncomings()[0].getSource() instanceof IControlNode) {
				return findOrigin((IControlNode) controlNode.getIncomings()[0].getSource(), searched);
			}else {
				return flowType(controlNode.getIncomings()[0], searched);
			}
		}
		return null; //if arrives in a final/flowFinal node
	}


	public static WhiteDiamondNodeType wDNodeType(IControlNode controlNode) {
		if(controlNode.isDecisionMergeNode() && 
				(controlNode.getIncomings().length > 1 && controlNode.getOutgoings().length == 1)) {
			return WhiteDiamondNodeType.MERGE_NODE;			
		}
		else if(controlNode.isDecisionMergeNode() && 
				(controlNode.getIncomings().length == 1 && controlNode.getOutgoings().length > 0 )){
			return WhiteDiamondNodeType.DECISION_NODE;
		}else {
			return WhiteDiamondNodeType.OTHERS;
		}
	}

	public static IFlow setFlow(com.change_vision.jude.api.inf.model.IFlow flow, String direction, ActivityNode activityNode) throws WellFormedException {
		if(!edges.containsKey(flow.getId())) {
			IFlow newFlow;
			if(flowType(flow, new ArrayList<>()) == FlowType.CONTROL_FLOW) {
				newFlow = new ControlFlow(flow);
			}else {
				newFlow = new ObjectFlow(flow);			
			}			
			edges.put(newFlow.getId(), newFlow);
		}
		if (direction.equals("source")) {	
			edges.get(flow.getId()).setSource(activityNode);
			return edges.get(flow.getId());
		}else {
			edges.get(flow.getId()).setTarget(activityNode);
			return edges.get(flow.getId());
		}
		
	}
	
	public static void resetStatics(){
		edges = new HashMap<>();
	}
}
