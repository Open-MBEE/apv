package com.ref.parser.stateMachine;

import java.util.ArrayList;

import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;

public class SMUtils {
	private IStateMachine sm;
	private IStateMachineDiagram smd;
	private ArrayList<String> arrayTurnName;
	private ArrayList<String> arrayAuxTurn;
	private ArrayList<String> arrayChannelMemory;
	private ArrayList<String> arraySyncMemory;
	private ArrayList<String> arrayNameMemory;
	
	public SMUtils(IStateMachine sm, IStateMachineDiagram smd) {
		this.sm = sm;
		this.smd = smd;
		this.arrayTurnName = new ArrayList<String>();
		this.arrayAuxTurn = new ArrayList<String>();
		this.arrayChannelMemory = new ArrayList<String>();
		this.arraySyncMemory = new ArrayList<String>();
		this.arrayNameMemory = new ArrayList<String>();
	}
	
	public static String nameResolver(String name) {
        return name.replace(" ", "").replace("!", "_").replace("@", "_")
                .replace("%", "_").replace("&", "_").replace("*", "_")
                .replace("(", "_").replace(")", "_").replace("+", "_")
                .replace("-", "_").replace("=", "_").replace("?", "_")
                .replace(":", "_").replace("/", "_").replace(";", "_")
                .replace(">", "_").replace("<", "_").replace(",", "_")
                .replace("{", "_").replace("}", "_").replace("|", "_")
                .replace("\\", "_").replace("\n", "_");
    }
	
	public void addNameMemory(String nMem) {
		this.arrayNameMemory.add(nMem);
	}
	
	public ArrayList<String> getArrayNameMemory() {
		return arrayNameMemory;
	}
	
	
	public void addChannelMemory(String cMem) {
		this.arrayChannelMemory.add(cMem);
	}
	
	public ArrayList<String> getArrayChannelMemory() {
		return arrayChannelMemory;
	}
	
	public void addSyncMemory(String sMem) {
		this.arraySyncMemory.add(sMem);
	}
	
	public ArrayList<String> getArraySyncMemory() {
		return arraySyncMemory;
	}

	public void addTurnName(String tName) {
		this.arrayTurnName.add(tName);
	}
	public ArrayList<String> getArrayTurnName() {
		return arrayTurnName;
	}
	
	public void addAuxTurn(String aux) {
		this.arrayAuxTurn.add(aux);
	}

	public ArrayList<String> getArrayAuxTurn() {
		return arrayAuxTurn;
	}

	public void setArrayAuxTurn(ArrayList<String> arrayAuxTurn) {
		this.arrayAuxTurn = arrayAuxTurn;
	}

	public void setArrayTurnName(ArrayList<String> arrayTurnName) {
		this.arrayTurnName = arrayTurnName;
	}	
	
}
