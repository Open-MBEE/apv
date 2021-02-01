package com.ref.astah.adapter;

import com.ref.astah.adapter.AdapterUtils.WhiteDiamondNodeType;
import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IControlNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IObjectFlow;

public class ControlNode extends ActivityNode implements IControlNode{

	public ControlNode(com.change_vision.jude.api.inf.model.IControlNode controlNode) throws WellFormedException {
		super(controlNode);
		
		if(isMergeNode()) {//Every incoming flow must be of the same type  
			IFlow edge = incomings[0];
			for(IFlow flow : incomings) {
				if(!flow.getClass().equals(edge.getClass()) ) {
					throw new WellFormedException("There is Control and Object flows on the incoming flows.\n");
				}
			}
		}
		
		if(isDecisionNode()) {//Every outgoing flow must be of the same type
			IFlow edge = outgoings[0];
			for(IFlow flow : outgoings) {
				if(!flow.getClass().equals(edge.getClass()) ) {
					throw new WellFormedException("There are Control and Object flows on the incoming flows.\n");
				}
			}
		}
		
		if(isInitialNode()) {//All flows must be a control flow
			for(int i = 0; i < outgoings.length ; i++) {
				if(outgoings[i] instanceof IObjectFlow) {
					throw new WellFormedException("The outgoing edges of a initialNode must be a Control Flow.\n");
				}
			}
		}
		
		if(isForkNode()) {//Every outgoing flow must be of the same type of the incoming flow
			IFlow edge = incomings[0];
			for(IFlow flow : outgoings) {
				if(!flow.getClass().equals(edge.getClass()) ) {
					throw new WellFormedException("At least one outgoing flow is not of the same type of the incoming flow.\n");
				}
			}
		}
		
		if(isJoinNode()) {//If there is at least 1 incoming flow that is a object flow then the outgoings flow must be object flow
			boolean object = false;
			for(IFlow flow : incomings) {
				if(flow instanceof IObjectFlow){
					object = true;
				}
			}
			if(outgoings[0] instanceof IObjectFlow) {
				if(!object) {
					throw new WellFormedException("There is at least one incoming Object Flow but the outgoing is a Control Flow. \n");
				}
			}else {
				if(object) {
					throw new WellFormedException("There are no incoming Object Flow but the outgoing is a Object Flow. \n");
				}
			}
		}
	}

	@Override
	public IFlow[] getIncomings() {	
		return this.incomings;		
	}

	@Override
	public IFlow[] getOutgoings() {
		return this.outgoings;	
	}

	@Override
	public String getId() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).getId();
	}

	@Override
	public String getName() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).getName();
	}

	@Override
	public String getDefinition() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).getStereotypes();
	}

	@Override
	public boolean isInitialNode() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).isInitialNode();
	}

	@Override
	public boolean isFlowFinalNode() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).isFlowFinalNode();
	}

	@Override
	public boolean isFinalNode() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).isFinalNode();
	}

	@Override
	public boolean isForkNode() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).isForkNode();
	}

	@Override
	public boolean isJoinNode() {
		return ((com.change_vision.jude.api.inf.model.IControlNode)activityNode).isJoinNode();
	}

	@Override
	public boolean isMergeNode() {
		return AdapterUtils.wDNodeType(((com.change_vision.jude.api.inf.model.IControlNode)activityNode)) == WhiteDiamondNodeType.MERGE_NODE;
	}

	@Override
	public boolean isDecisionNode() {
		return AdapterUtils.wDNodeType(((com.change_vision.jude.api.inf.model.IControlNode)activityNode)) == WhiteDiamondNodeType.DECISION_NODE; 
	}
}
