package com.ref.astah.statemachine.adapter;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;

public class StateMachineDiagram implements IStateMachineDiagram{
	
	private com.change_vision.jude.api.inf.model.IStateMachineDiagram stateMachineDiagram;
	private IStateMachine sm;
	
	public StateMachineDiagram(com.change_vision.jude.api.inf.model.IStateMachineDiagram smd) throws WellFormedException {
		this.stateMachineDiagram = smd;
		this.sm = new StateMachine(smd.getStateMachine());
	}
	
	@Override
	public String getId() {
		return this.stateMachineDiagram.getId();
	}

	@Override
	public String getName() {
		return this.stateMachineDiagram.getName();
	}

	@Override
	public String getDefinition() {
		return this.stateMachineDiagram.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return this.stateMachineDiagram.getStereotypes();
	}

	@Override
	public IStateMachine getStateMachine() {
		return this.sm;
	}

}
