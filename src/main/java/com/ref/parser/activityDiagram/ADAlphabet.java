package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;

import com.ref.interfaces.activityDiagram.IActivity;

public abstract class ADAlphabet {
	protected HashMap<Pair<IActivity,String>, ArrayList<String>> alphabetAD;
	protected HashMap<Pair<IActivity,String>, String> syncChannelsEdge;
	protected HashMap<Pair<IActivity,String>, String> syncObjectsEdge;
	protected HashMap<Pair<IActivity,String>, ArrayList<String>> parameterAlphabetNode;
	protected IActivity ad;
	
	public ADAlphabet(IActivity ad) {
		this.ad = ad;
		alphabetAD = new HashMap<>();
		syncChannelsEdge = new HashMap<>();
		syncObjectsEdge = new HashMap<>();
		parameterAlphabetNode = new HashMap<>();
	}
	
	public HashMap<Pair<IActivity, String>, ArrayList<String>> getAlphabetAD() {
		return alphabetAD;
	}

	public void setAlphabetAD(HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetAD) {
		this.alphabetAD = alphabetAD;
	}

	public HashMap<Pair<IActivity, String>, String> getSyncChannelsEdge() {
		return syncChannelsEdge;
	}

	public void setSyncChannelsEdge(HashMap<Pair<IActivity, String>, String> syncChannelsEdge) {
		this.syncChannelsEdge = syncChannelsEdge;
	}

	public HashMap<Pair<IActivity, String>, String> getSyncObjectsEdge() {
		return syncObjectsEdge;
	}

	public void setSyncObjectsEdge(HashMap<Pair<IActivity, String>, String> syncObjectsEdge) {
		this.syncObjectsEdge = syncObjectsEdge;
	}

	public HashMap<Pair<IActivity, String>, ArrayList<String>> getParameterAlphabetNode() {
		return parameterAlphabetNode;
	}

	public void setParameterAlphabetNode(HashMap<Pair<IActivity, String>, ArrayList<String>> parameterAlphabetNode) {
		this.parameterAlphabetNode = parameterAlphabetNode;
	}

	public void add(ADAlphabet adAlphabet) {		
	}
	
	public IActivity getAd() {
		return ad;
	}
	public void setAd(IActivity ad) {
		this.ad = ad;
	}
	
}
