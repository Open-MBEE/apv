package com.ref.sysml.adapter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.AcceptActionUsage;
import org.omg.sysml.xtext.sysml.ActionUsage;
import org.omg.sysml.xtext.sysml.ControlNode;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.Membership;
import org.omg.sysml.xtext.sysml.ParameterMembership;
import org.omg.sysml.xtext.sysml.SendActionUsage;
import org.omg.sysml.xtext.sysml.Succession;
import org.omg.sysml.xtext.sysml.SuccessionItemFlow;
import org.omg.sysml.xtext.sysml.TextualRepresentation;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IOutputPin;
import com.ref.interfaces.activityDiagram.IPin;


public class Action extends ActivityNode implements IAction {
	private IInputPin[] inputs;
	private IOutputPin[] outputs;
	private IActivity activity;
	
	public Action(EObject action, IActivity owner) throws WellFormedException {
		super(action, owner);

		List<EObject> parameterList = ((ActionUsage) this.activityNode).getOwnedFeatureMembership_comp();
		
		List<InputPin> inputs = new ArrayList<>();
		List<OutputPin> outputs = new ArrayList<>();
		
		for (EObject x : parameterList) {
			
			if (x instanceof ParameterMembership) {
				ParameterMembership parameter = (ParameterMembership) x;
				
				if (parameter.getDirection().getName().equals("in")) {
					
					InputPin inPin = new InputPin(parameter, this.owner);
					inPin.setOwner(this);
//					inPin.setName(this.name + "_" + inPin.getName());

					inputs.add(inPin);
					
					//AdapterUtils.nodes.put(inPin.getName(), inPin);
					AdapterUtils.nodesType.put( this.name + "_" + inPin.getName(), parameter.getOwnedMemberParameter_comp());
					AdapterUtils.parameterName.put(inPin.getId(), this.name + "_" + inPin.getName());
					
				} else if (parameter.getDirection().getName().equals("out")) {
					
					OutputPin outPin = new OutputPin(parameter, this.owner);
					outPin.setOwner(this);
//					outPin.setName(this.name + "_" + outPin.getName());
					
					outputs.add(outPin);

					//AdapterUtils.nodes.put(outPin.getName(), outPin);
					AdapterUtils.nodesType.put( this.name + "_" + outPin.getName(), parameter.getOwnedMemberParameter_comp());
					AdapterUtils.parameterName.put(outPin.getId(),  this.name + "_" + outPin.getName());
				}
			}
		}
		
		this.inputs = inputs.toArray(new InputPin[inputs.size()]);
		this.outputs = outputs.toArray(new OutputPin[outputs.size()]);
		
		if (isCallBehaviorAction()) {
			ActivityDiagram ad = new ActivityDiagram((ActionUsage) action);
			this.activity = ad.getActivity();
		}
	}
	
	public Action(String name, IActivity owner) {
		super(name, owner);
		
		this.inputs = new IInputPin[0];
		this.outputs = new IOutputPin[0];
	}

	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(activityNode));
	}

	@Override
	public String getDefinition() {
		
		List<EObject> docs = ((ActionUsage) this.activityNode).getOwnedMembership_comp();
		
		for (EObject x : docs) {
			
			EObject definition = ((Membership) x).getOwnedMemberElement_comp();
			
			if (definition instanceof TextualRepresentation) {
				return ((TextualRepresentation) definition).getBody().replace("/*", "").replace("*/", "").trim();
			}
		};
		
		return "";
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
	public IActivity getCallingActivity() {
		return this.activity;
	}

	@Override
	public boolean isCallBehaviorAction() {
		if (this.activityNode instanceof ActionUsage) {			
			if (((ActionUsage) this.activityNode).getOwnedMembership_comp().size() > 0) {
				for (EObject x : ((ActionUsage) this.activityNode).getOwnedMembership_comp()) {
					if (x instanceof FeatureMembership
							|| x instanceof ActionUsage
							|| x instanceof Succession
							|| x instanceof SuccessionItemFlow
							|| x instanceof ControlNode
							|| x instanceof AcceptActionUsage
							|| x instanceof SendActionUsage) {
						return true;
					}
				};
			}
			
		}
		
		return false;
	}

	@Override
	public boolean isSendSignalAction() {
		return this.activityNode instanceof SendActionUsage;
	}

	@Override
	public boolean isAcceptEventAction() {
		return this.activityNode instanceof AcceptActionUsage;
	}

	@Override
	public String[] getStereotypes() {
		return null; // Ainda nao definido
	}

	@Override
	public void addPin(IPin pin) {
		if(pin instanceof InputPin) {
			for(int i = 0; i<inputs.length;i++) {
				if(inputs[i] == null) {
					inputs[i] = (IInputPin) pin;
				}
			}
		}else if(pin instanceof OutputPin){
			for(int i = 0; i<outputs.length;i++) {
				if(outputs[i] == null) {
					outputs[i] = (IOutputPin) pin;
				}
			}
		}
		else {
			//something went wrong
		}
	}
}
