package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.interfaces.activityDiagram.IActivity;



public class ADCompositeAlphabet extends ADAlphabet{
	private List<ADAlphabet> alphabetList;
	private HashMap<Pair<IActivity,String>, ArrayList<String>> allAlphabets = new HashMap<>();
	private HashMap<Pair<IActivity,String>,String> allSyncChannels = new HashMap<Pair<IActivity,String>, String>();
	private HashMap<Pair<IActivity,String>,String> allSyncObject= new HashMap<Pair<IActivity,String>, String>();
	
	public ADCompositeAlphabet(IActivity ad) {
		super(ad);
		alphabetList = new ArrayList<>();
		allAlphabets = new HashMap<>();
	}
	
	public HashMap<Pair<IActivity,String>, ArrayList<String>> getAlphabetNodes() {
		return alphabetAD;
	}
	
	public HashMap<Pair<IActivity,String>, ArrayList<String>> getAllAlphabetNodes() {
		for(ADAlphabet alphabet: this.alphabetList) {//For each ADAlphabet of the List
			//If not leaf
			if(alphabet instanceof ADCompositeAlphabet && !((ADCompositeAlphabet)alphabet).alphabetList.isEmpty()) {
				allAlphabets.putAll(getLeafAlphabet(alphabet));//add the alphabet of the leaves
			}
			else{
				allAlphabets.putAll(alphabet.alphabetAD);
			}
		}
		allAlphabets.putAll(alphabetAD);
		return allAlphabets;
	}
	
	private HashMap<Pair<IActivity,String>, ArrayList<String>> getLeafAlphabet(ADAlphabet alphabet){
		//while not leaf
		if(alphabet instanceof ADCompositeAlphabet && !((ADCompositeAlphabet)alphabet).alphabetList.isEmpty()) {
			//For each element of the List
			for(ADAlphabet listAlphabet: ((ADCompositeAlphabet)alphabet).alphabetList) {
				allAlphabets.putAll(getLeafAlphabet(listAlphabet));
			}
		}
		
		return alphabet.alphabetAD;
	}
	
	public HashMap<Pair<IActivity,String>,String> getAllsyncChannelsEdge() {
		for (ADAlphabet alphabet : this.alphabetList) {//For each ADAlphabet of the List
			//If not leaf
			if (alphabet instanceof ADCompositeAlphabet) {
				allSyncChannels.putAll(getLeafsyncChannelsEdge(alphabet));//add the alphabet of the leaves
			}
			else {
				allSyncChannels.putAll(alphabet.syncChannelsEdge);//add the alphabet
			}
		}
		allSyncChannels.putAll(this.syncChannelsEdge);
		return allSyncChannels;
	}
	
	private HashMap<Pair<IActivity,String>,String> getLeafsyncChannelsEdge(ADAlphabet alphabet){
		//while not leaf
		if(alphabet instanceof ADCompositeAlphabet && !((ADCompositeAlphabet)alphabet).alphabetList.isEmpty()) {
			//For each element of the List
			for(ADAlphabet listAlphabet: ((ADCompositeAlphabet)alphabet).alphabetList) {
				allSyncChannels.putAll(getLeafsyncChannelsEdge(listAlphabet));
			}
		}
		return alphabet.syncChannelsEdge;
	}
	
	public HashMap<Pair<IActivity,String>,String> getAllsyncObjectsEdge() {
		for (ADAlphabet alphabet : this.alphabetList) {//For each ADAlphabet of the List
			//If not leaf
			if (alphabet instanceof ADCompositeAlphabet) {
				allSyncObject.putAll(getLeafsyncObjectsEdge(alphabet));//add the alphabet of the leaves
			}
			else {
				allSyncObject.putAll(alphabet.syncObjectsEdge);
			}
		}
		allSyncObject.putAll(this.syncObjectsEdge);
		return allSyncObject;
	}
	
	private HashMap<Pair<IActivity,String>,String> getLeafsyncObjectsEdge(ADAlphabet alphabet){
		//while not leaf
		if(alphabet instanceof ADCompositeAlphabet && !((ADCompositeAlphabet)alphabet).alphabetList.isEmpty()) {
			//For each element of the List
			for(ADAlphabet listAlphabet: ((ADCompositeAlphabet)alphabet).alphabetList) {
				allSyncObject.putAll(getLeafsyncObjectsEdge(listAlphabet));
			}
		}
		return alphabet.syncObjectsEdge;
	}
	
	public void add(ADAlphabet adAlphabet) {
		this.alphabetList.add(adAlphabet);
	}

	public List<ADAlphabet> getAlphabetList() {
		return alphabetList;
	}

	public void setAlphabetList(List<ADAlphabet> alphabetList) {
		this.alphabetList = alphabetList;
	}
}
