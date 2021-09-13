package com.ref.utils;

import java.util.ArrayList;
import java.util.List;

import com.change_vision.jude.api.inf.model.IActivityParameterNode;
import com.change_vision.jude.api.inf.model.IObjectNode;
import com.change_vision.jude.api.inf.model.IPin;
import com.ref.astah.adapter.AdapterUtils;
import com.ref.astah.adapter.Class;
import com.ref.astah.adapter.AdapterUtils.WhiteDiamondNodeType;
import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IClass;
import com.ref.interfaces.activityDiagram.IControlNode;

public class ActivityAdapterUtils {
	//TODO deixar generico
	
	/*private com.change_vision.jude.api.inf.model.IActivityNode navegateForBase(com.change_vision.jude.api.inf.model.IControlNode base, List<String> searched) throws WellFormedException {
		if(AdapterUtils.wDNodeType(base) == WhiteDiamondNodeType.MERGE_NODE) {

			for(int i = 0; i< base.getIncomings().length; i++) {
				
				if(!searched.contains((base.getIncomings()[i].getId()))) {
					
					searched.add(base.getIncomings()[i].getId());
					if(base.getIncomings()[i].getSource() instanceof IControlNode) {
						return navegateForBase((com.change_vision.jude.api.inf.model.IControlNode) base.getIncomings()[i].getSource(),searched);
					}else {
						return findBase(base.getIncomings()[i].getSource(),searched);
					}
				}
				
			}
			
		}else if(AdapterUtils.wDNodeType(base) == WhiteDiamondNodeType.DECISION_NODE) {
			if(base.getIncomings()[0].getSource() instanceof com.change_vision.jude.api.inf.model.IControlNode) {
				return navegateForBase((com.change_vision.jude.api.inf.model.IControlNode) base.getIncomings()[0].getSource(), searched);
			}else {
				return findBase(base.getIncomings()[0].getSource(), searched);
			}
		}else if(base.isForkNode()) {
			if(base.getIncomings()[0].getSource() instanceof IControlNode) {
				return navegateForBase((com.change_vision.jude.api.inf.model.IControlNode) base.getIncomings()[0].getSource(),searched);
			}else {
				return findBase(base.getIncomings()[0].getSource(),searched);
			}			
		}else if(base.isJoinNode()) {
			List<IClass> types = new ArrayList<>();
			com.change_vision.jude.api.inf.model.IActivityNode baseNode = null;
			for(int i = 0; i< base.getIncomings().length; i++) {//finds the types of the flows
				if (!searched.contains(base.getIncomings()[i].getId())) {
					searched.add(base.getIncomings()[i].getId());
					if(AdapterUtils.flowType(base.getIncomings()[i], searched) == AdapterUtils.FlowType.OBJECT_FLOW) {//if the flow results in object  
						baseNode = findBase(base.getIncomings()[i].getSource(),searched);// not sure
						
						if( baseNode instanceof IObjectNode) {
							types.add(new Class(((IObjectNode) baseNode).getBase()));
						}
						else if(baseNode instanceof IActivityParameterNode) {
							types.add(new Class(((IActivityParameterNode) baseNode).getBase()));
						}else if(baseNode instanceof IPin) {
							types.add(new Class(((IPin) baseNode).getBase()));
						}
					}
				}
			}
			
			for(int i =0; i< types.size();i++) {//if any type is different, then something went wrong
				if(!(types.get(0).getName().equals(types.get(i).getName()))) {
					throw new WellFormedException("There is more then 1 type of objectFlow in the "+base.getName() +" Node. \n");
				}
			}
			return baseNode;
		}	
		return null;
	}*/
}
