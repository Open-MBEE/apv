package com.ref.astah.adapter;

import java.util.HashMap;

import com.change_vision.jude.api.inf.model.IAction;
import com.change_vision.jude.api.inf.model.IActivityParameterNode;
import com.change_vision.jude.api.inf.model.IControlNode;
import com.change_vision.jude.api.inf.model.IObjectNode;
import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IPartition;
import com.ref.interfaces.activityDiagram.IPin;

public class Activity implements IActivity{
	private com.change_vision.jude.api.inf.model.IActivity activity;
	private IActivityDiagram activityDiagram;
	private IActivityNode[] activityNodes;
	private HashMap<String,String> owners;
	private IPartition[] partitions;
	
	
	public Activity(com.change_vision.jude.api.inf.model.IActivity activity) throws WellFormedException {
		super();
		this.activity = activity;
		owners = new HashMap<>();
		

		if (activity.getPartitions() != null && activity.getPartitions().length > 0) {
			this.partitions = new IPartition[activity.getPartitions().length];
			for (int i=0; i < activity.getPartitions().length; i++)  {
				partitions[i] = new Partition(activity.getPartitions()[i]);
			}
		}
		
		this.activityNodes = new IActivityNode[activity.getActivityNodes().length];
		for (int i = 0; i < activityNodes.length; i++) {
			com.change_vision.jude.api.inf.model.IActivityNode node = activity.getActivityNodes()[i];
			if(node instanceof com.change_vision.jude.api.inf.model.IAction) {
				this.activityNodes[i] = new Action((IAction) node);
				((com.ref.interfaces.activityDiagram.IAction)this.activityNodes[i]).getInputs();
			}else if(node instanceof com.change_vision.jude.api.inf.model.IControlNode) {
				this.activityNodes[i] = new ControlNode((IControlNode) node);
			}else if(node instanceof com.change_vision.jude.api.inf.model.IActivityParameterNode) {
				this.activityNodes[i] = new ActivityParameterNode((IActivityParameterNode) node);
			}else if(node instanceof com.change_vision.jude.api.inf.model.IPin) {
				com.change_vision.jude.api.inf.model.IAction owner = (IAction) node.getOwner();//Finds the owner of the pin
				
				for(com.change_vision.jude.api.inf.model.IPin pin : owner.getInputs()) {//Look the input Pins of the node
					if(pin.getId().equals(node.getId())) {//If find the right one
						this.activityNodes[i] = new InputPin((com.change_vision.jude.api.inf.model.IInputPin)node);
						owners.put(this.activityNodes[i].getId(), owner.getId());
					}
				}
				for(com.change_vision.jude.api.inf.model.IPin pin : owner.getOutputs()) {//Look the output Pins of the node
					if(pin.getId().equals(node.getId())) {//If find the right one
						this.activityNodes[i] = new OutputPin((com.change_vision.jude.api.inf.model.IOutputPin)node);
						owners.put(this.activityNodes[i].getId(), owner.getId());
					}
				}
				
			}else if(node instanceof com.change_vision.jude.api.inf.model.IObjectNode) {
				this.activityNodes[i] = new ObjectNode((IObjectNode) node);
			}			
		}
		
		//Goes through the nodes and assigns the pins on the respective nodes and vice-versa 
		for(int i = 0; i<this.activityNodes.length;i++) {
			if(owners.containsKey(this.activityNodes[i].getId())) {//If is a pin
				for (int j = 0; j < activityNodes.length; j++) {
					if(activityNodes[j].getId().equals(owners.get(activityNodes[i].getId()))) {//Looks for the owner of the pin
						((IPin) activityNodes[i]).setOwner((com.ref.interfaces.activityDiagram.IAction) activityNodes[j]);//sets the owner of the pin
						if(((IPin) activityNodes[i]) instanceof InputPin) {//If is an input pin
							((Action) activityNodes[j]).addPin(((InputPin) activityNodes[i]));

						}else {//If is an output pin
							((Action) activityNodes[j]).addPin(((OutputPin) activityNodes[i]));
						}
					}
				}
			}
		}
	}

	public void setActivityDiagram(IActivityDiagram activityDiagram) {
		this.activityDiagram = activityDiagram;
	}

	@Override
	public String getId() {
		return activity.getId();
	}

	@Override
	public String getName() {
		return activity.getName();
	}

	@Override
	public String getDefinition() {
		return activity.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return activity.getStereotypes();
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
		} catch (com.change_vision.jude.api.inf.exception.InvalidEditingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public IPartition[] getPartitions() {
		return partitions;
	}
	

}
