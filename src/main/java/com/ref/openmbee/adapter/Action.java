package com.ref.openmbee.adapter;

import java.util.ArrayList;

import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IOutputPin;
import com.ref.interfaces.activityDiagram.IPin;

public abstract class Action extends ActivityNode implements IAction{

	private IInputPin[] inputs;
	private IOutputPin[] outputs;
	
	public Action(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		inputs = new InputPin[0];
		outputs = new OutputPin[0];
	}

	@Override
	public IInputPin[] getInputs() {
		return this.inputs;
	}

	@Override
	public IOutputPin[] getOutputs() {
		return this.outputs;
	}

	@Override
	public void addPin(IPin pin) {
		if(pin instanceof InputPin) {
			if(inputs !=null) {
				IInputPin[] aux = inputs;
				inputs = new InputPin[inputs.length+1];
				for (int i = 0; i < aux.length; i++) {
					inputs[i] = aux[i];
				}
				inputs[aux.length] = (InputPin) pin;
			}else {
				inputs = new InputPin[1];
				inputs[0] = (InputPin) pin;
			}
		}else if(pin instanceof OutputPin){
			if(outputs !=null) {
				IOutputPin[] aux = outputs;
				outputs = new OutputPin[outputs.length+1];
				for (int i = 0; i < aux.length; i++) {
					outputs[i] = aux[i];
				}
				outputs[aux.length] = (OutputPin) pin;
			}else {
				outputs = new OutputPin[1];
				outputs[0] = (OutputPin) pin;
			}
		}
		else {
			//something went wrong
		}
	}

	@Override
	public IActivity getCallingActivity() {
		if(this instanceof CallBehavior) {
			return ((CallBehavior)this).getCallBehavior();
		}
		return null;
	}

	@Override
	public boolean isCallBehaviorAction() {
		return this instanceof CallBehavior;
	}

	@Override
	public boolean isSendSignalAction() {
		return this instanceof SendSignal;
	}

	@Override
	public boolean isAcceptEventAction() {
		return this instanceof AcceptSignal;
	}
	
}
