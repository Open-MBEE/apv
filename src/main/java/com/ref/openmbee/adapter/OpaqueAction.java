package com.ref.openmbee.adapter;

import java.util.ArrayList;

public class OpaqueAction extends Action{
	private ArrayList<String> inputValueIds;
	private ArrayList<String> localPostconditionIds;
	private ArrayList<String> localPreconditionIds;
	
	public OpaqueAction(String id, String type, String ownerId, String activityId, ArrayList<String> outgoingsIds,
			ArrayList<String> incomingIds, ArrayList<String> inputValueIds,
			ArrayList<String> localPostconditionIds, ArrayList<String> localPreconditionIds,
			String name, String[] stereotypes, String definition) {
		super(id, type, ownerId, activityId, outgoingsIds, incomingIds, name, stereotypes, definition);
		this.inputValueIds = inputValueIds;
		this.localPostconditionIds = localPostconditionIds;
		this.localPreconditionIds = localPreconditionIds;

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
	
}
