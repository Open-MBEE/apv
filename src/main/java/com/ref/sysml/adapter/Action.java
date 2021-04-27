package com.ref.sysml.adapter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.AcceptActionUsage;
import org.omg.sysml.xtext.sysml.ActionDefinition;
import org.omg.sysml.xtext.sysml.ActionUsage;
import org.omg.sysml.xtext.sysml.FeatureMembership;
import org.omg.sysml.xtext.sysml.FeatureTyping;
import org.omg.sysml.xtext.sysml.Membership;
import org.omg.sysml.xtext.sysml.ParameterMembership;
import org.omg.sysml.xtext.sysml.SendActionUsage;
import org.omg.sysml.xtext.sysml.TextualRepresentation;
import org.omg.sysml.xtext.sysml.Usage;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IOutputPin;
import com.ref.interfaces.activityDiagram.IPin;
import com.ref.parser.activityDiagram.Pair;


public class Action extends ActivityNode implements IAction {
	private IInputPin[] inputs;
	private IOutputPin[] outputs;
	
	public Action(EObject action, IActivity owner) throws WellFormedException {
		super(action, owner);
		
		if (this.activityNode instanceof SendActionUsage) {
			SendActionUsage sendSignal = (SendActionUsage) this.activityNode;
			List<InputPin> inputs = new ArrayList<>();
			List<OutputPin> outputs = new ArrayList<>();
			
			String nameNode = sendSignal.getNameNode();
			String nameData = sendSignal.getNameData();
			
			String from = nameNode + "_" + nameData;
			String to = this.name + "_" + nameData;
			
			Usage type = AdapterUtils.nodesType.get(nameNode + "_" + nameData);
			Class base = new Class(type);
			
			// create input pin
			InputPin inPin = new InputPin(sendSignal, this.owner);
			inPin.setOwner(this);
			inPin.setName(nameData);
			inPin.setBase(base);
			
			inputs.add(inPin);
			
			AdapterUtils.nodesType.put(this.name + "_" + inPin.getName(), type);
			AdapterUtils.parameterName.put(inPin.getId(), this.name + "_" + inPin.getName());
			
			// create object flow
			ObjectFlow object = new ObjectFlow(sendSignal);
			object.setBase(base);
			
			AdapterUtils.edges.put(new Pair<String, String>(from, to), object);
			
			// set accept event to send signal
			AdapterUtils.signals.put(sendSignal.getTarget(), this.name);
			
			this.inputs = inputs.toArray(new InputPin[inputs.size()]);
			this.outputs = outputs.toArray(new OutputPin[outputs.size()]);
			
		} else if (this.activityNode instanceof AcceptActionUsage) {
			AcceptActionUsage acceptEvent = (AcceptActionUsage) this.activityNode;
			List<InputPin> inputs = new ArrayList<>();
			List<OutputPin> outputs = new ArrayList<>();
			
			FeatureMembership parameter = (FeatureMembership) acceptEvent.getParameter();
			String nameNode = acceptEvent.getName();		

			
			Action signalNode = (Action) AdapterUtils.nodes.get(owner.getName() + "_" + AdapterUtils.signals.get(nameNode));
			
			String SignalOutPinName = AdapterUtils.signals.get(nameNode);
			
			if (signalNode.getInputs().length > 0) {
				SignalOutPinName += "_" + signalNode.getInputs()[0].getName();
			}
			
			Usage usage = AdapterUtils.nodesType.get(SignalOutPinName);
			Class base = new Class(usage);
			
			// create input pin

			OutputPin outPin = new OutputPin(acceptEvent, this.owner);
			outPin.setOwner(this);
			outPin.setName(parameter.getMemberName());
			outPin.setBase(base);
			
			outputs.add(outPin);
			
			AdapterUtils.nodesType.put(this.realName + "_" + outPin.getName(), usage);
			AdapterUtils.parameterName.put(outPin.getId(), this.realName + "_" + outPin.getName());
			
			this.inputs = inputs.toArray(new InputPin[inputs.size()]);
			this.outputs = outputs.toArray(new OutputPin[outputs.size()]);
		} else if (this.activityNode instanceof ActionUsage) {
			List<EObject> parameterList = ((ActionUsage) this.activityNode).getOwnedFeatureMembership_comp();
			
			List<InputPin> inputs = new ArrayList<>();
			List<OutputPin> outputs = new ArrayList<>();
			
			for (EObject x : parameterList) {
				
				if (x instanceof ParameterMembership) {
					ParameterMembership parameter = (ParameterMembership) x;
					
					if (parameter.getDirection().getName().equals("in")) {
						
						InputPin inPin = new InputPin(parameter, this.owner);
						inPin.setOwner(this);
//						inPin.setName(this.name + "_" + inPin.getName());

						inputs.add(inPin);
						
						//AdapterUtils.nodes.put(inPin.getName(), inPin);
						AdapterUtils.nodesType.put( this.name + "_" + inPin.getName(), parameter.getOwnedMemberParameter_comp());
						AdapterUtils.parameterName.put(inPin.getId(), this.name + "_" + inPin.getName());
						
					} else if (parameter.getDirection().getName().equals("out")) {
						
						OutputPin outPin = new OutputPin(parameter, this.owner);
						outPin.setOwner(this);
//						outPin.setName(this.name + "_" + outPin.getName());
						
						outputs.add(outPin);

						//AdapterUtils.nodes.put(outPin.getName(), outPin);
						AdapterUtils.nodesType.put( this.name + "_" + outPin.getName(), parameter.getOwnedMemberParameter_comp());
						AdapterUtils.parameterName.put(outPin.getId(),  this.name + "_" + outPin.getName());
					}
				}
			}
			
			this.inputs = inputs.toArray(new InputPin[inputs.size()]);
			this.outputs = outputs.toArray(new OutputPin[outputs.size()]);
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
		FeatureTyping featureTyping = (FeatureTyping) ((ActionUsage) this.activityNode).getOwnedRelationship_comp().get(0);
		ActivityDiagram ad = AdapterUtils.activityDiagrams.get(((ActionDefinition) featureTyping.getType()).getName());
		
		return ad.getActivity();
	}

	@Override
	public boolean isCallBehaviorAction() {
		if (this.activityNode instanceof ActionUsage) {			
			return ((ActionUsage) this.activityNode).getOwnedRelationship_comp().size() > 0; // has a type?
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
