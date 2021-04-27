package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class OpaqueAction extends Action{
	private ArrayList<String> body; //TODO remover esse atributo 
	private ArrayList<String> inputValueIds;
	private ArrayList<String> localPostconditionIds;
	private ArrayList<String> localPreconditionIds;
	private String name;
	private String definition;
	private String[] stereotypes;
	
	public OpaqueAction(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, ArrayList<String> body, ArrayList<String> inputValueIds,
			ArrayList<String> localPostconditionIds, ArrayList<String> localPreconditionIds,
			String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.body = body;
		this.inputValueIds = inputValueIds;
		this.localPostconditionIds = localPostconditionIds;
		this.localPreconditionIds = localPreconditionIds;
		
		this.name = name;
		this.stereotypes = stereotypes;
		this.definition = definition;
		/*
		 * TODO concatenar o body aqui pra virar o definition
		 * */
	}
	
	public ArrayList<String> getInputValueIds() {
		return inputValueIds;
	}
	public void setInputValueIds(ArrayList<String> inputValueIds) {
		this.inputValueIds = inputValueIds;
	}
	public ArrayList<String> getLocalPostconditionIds() {
		return localPostconditionIds;
	}
	public void setLocalPostconditionIds(ArrayList<String> localPostconditionIds) {
		this.localPostconditionIds = localPostconditionIds;
	}
	public ArrayList<String> getLocalPreconditionIds() {
		return localPreconditionIds;
	}
	public void setLocalPreconditionIds(ArrayList<String> localPreconditionIds) {
		this.localPreconditionIds = localPreconditionIds;
	}
	@Override
	public boolean isCallBehaviorAction() {
		return false;
	}
	@Override
	public boolean isSendSignalAction() {
		return false;
	}
	@Override
	public boolean isAcceptEventAction() {
		return false;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDefinition() {
		// TODO trocar por body transformar de ArrayList<String> pra String sem usar toString()
		return this.definition;
	}

	@Override
	public String[] getStereotypes() {
		return this.stereotypes;
	}
	
	
}
