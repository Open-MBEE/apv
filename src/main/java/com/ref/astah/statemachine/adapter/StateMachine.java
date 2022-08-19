package com.ref.astah.statemachine.adapter;

import java.util.ArrayList;

import com.change_vision.jude.api.inf.model.IVertex;
import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.stateMachine.IPseudostate;
import com.ref.interfaces.stateMachine.IState;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.interfaces.stateMachine.IStateMachineDiagram;
import com.ref.interfaces.stateMachine.ITransition;

public class StateMachine implements IStateMachine{

	private com.change_vision.jude.api.inf.model.IStateMachine stateMachine; 
	private ITransition[] transitions;
	private IState[] states;
	private IPseudostate[] pseudostates;
	private IStateMachineDiagram smd;
	private ArrayList<IState> allStates;
	private ArrayList<IPseudostate> allPseudostates;

	public StateMachine(com.change_vision.jude.api.inf.model.IStateMachine sm) throws WellFormedException {
		this.stateMachine = sm;
		this.allStates = new ArrayList<>();
		this.allPseudostates = new ArrayList<>();
		int i=0;
		if(stateMachine.getTransitions() != null) {
			this.transitions = new ITransition[stateMachine.getTransitions().length];
			for (com.change_vision.jude.api.inf.model.ITransition transition : sm.getTransitions()) {
				this.transitions[i++] = new Transition(transition);
			}
		}
		i=0;

		if(stateMachine.getVertexes() != null) {
			int s = 0;
			for(IVertex vertex : stateMachine.getVertexes()) {
				if(vertex instanceof com.change_vision.jude.api.inf.model.IState) {
					if(!(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState)) {
						s++;
					}
				}
			}
			this.states = new IState[s];
			for(IVertex vertex : stateMachine.getVertexes()) {
				if(vertex instanceof com.change_vision.jude.api.inf.model.IState) {
					if(!(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState)) {
						this.states[i++] = new State((com.change_vision.jude.api.inf.model.IState) vertex);
						this.allStates.add(this.states[i-1]);
					}
				}
			}
		}

		i=0;

		if(stateMachine.getVertexes() != null) {	
			int g = 0;
			for (com.change_vision.jude.api.inf.model.IVertex vertex : sm.getVertexes()) {
				if(!(vertex instanceof com.change_vision.jude.api.inf.model.IState)) {
					g++;
				}else if(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState) {
					g++;
				}
			}
			this.pseudostates = new IPseudostate[g];

			for (com.change_vision.jude.api.inf.model.IVertex vertex : sm.getVertexes()) {
				if(!(vertex instanceof com.change_vision.jude.api.inf.model.IState)) {
					this.pseudostates[i++] = new Pseudostate((com.change_vision.jude.api.inf.model.IPseudostate) vertex);
					this.allPseudostates.add(this.pseudostates[i-1]);
				}else if(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState) {
					this.pseudostates[i++] = new Pseudostate((com.change_vision.jude.api.inf.model.IFinalState) vertex);
					this.allPseudostates.add(this.pseudostates[i-1]);
				}
			}
		}


		settingCompoundStates(sm);
		settingTransitionsSourceTarget(sm);
		settingStatesIncomingsOutgoings(sm);
		settingFirstState();
	}

	private void settingCompoundStates(com.change_vision.jude.api.inf.model.IStateMachine sm) throws WellFormedException {
		if(this.states.length != 0) {
			for(com.change_vision.jude.api.inf.model.IVertex st : sm.getVertexes()) {
				if(st instanceof com.change_vision.jude.api.inf.model.IState) {
					if(((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes().length > 0) {
						int i = 0;
						for(IState st2 : this.states) {
							if(st.getId().equals(st2.getId())) {
								st2.setIsCompound(true);
								//ADICIONANDO SUB-ESTADOS
								int s = 0;
								for(com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
									if(vertex instanceof com.change_vision.jude.api.inf.model.IState) {
										if(!(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState)) {
											s++;
										}
									}
								}
								IState[] substates = new IState[s];
								for(com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
									if(vertex instanceof com.change_vision.jude.api.inf.model.IState) {
										if(!(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState)) {
											substates[i++] = new State((com.change_vision.jude.api.inf.model.IState) vertex);
											substates[i-1].setSuperiorState(st2);
											if(((com.change_vision.jude.api.inf.model.IState) vertex).getSubvertexes().length > 0) {
												substates[i-1].setIsCompound(true);
												substates[i-1].setSubstates(settingSubstate((com.change_vision.jude.api.inf.model.IState) vertex, substates[i-1]));
												substates[i-1].setSubpseudostates(settingSubpseudostate((com.change_vision.jude.api.inf.model.IState) vertex, substates[i-1]));
												this.allStates.add(substates[i-1]);
											}else {
												this.allStates.add(substates[i-1]);
											}
										}
									}
								}
								//ADICIONANDO SUB-PSEUDO
								i=0;	
								int g = 0;
								for (com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
									if(!(vertex instanceof com.change_vision.jude.api.inf.model.IState)) {
										g++;
									}else if(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState) {
										g++;
									}
								}
								IPseudostate[] subpseudostates = new IPseudostate[g];

								for (com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
									if(!(vertex instanceof com.change_vision.jude.api.inf.model.IState)) {
										subpseudostates[i++] = new Pseudostate((com.change_vision.jude.api.inf.model.IPseudostate) vertex);
										subpseudostates[i-1].setSuperiorState(st2);
										this.allPseudostates.add(subpseudostates[i-1]);
									}else if(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState) {
										subpseudostates[i++] = new Pseudostate((com.change_vision.jude.api.inf.model.IFinalState) vertex);
										subpseudostates[i-1].setSuperiorState(st2);
										this.allPseudostates.add(subpseudostates[i-1]);
									}
								}
								//SETANDO
								st2.setSubstates(substates);
								st2.setSubpseudostates(subpseudostates);
							}
						}
					}
				}
			}
		}
	}

	private IState[] settingSubstate(com.change_vision.jude.api.inf.model.IState st, IState ist) throws WellFormedException {
		//ADICIONANDO SUB-ESTADOS
		int i = 0;
		int s = 0;
		for(com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
			if(vertex instanceof com.change_vision.jude.api.inf.model.IState) {
				if(!(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState)) {
					s++;
				}
			}
		}
		IState[] substates = new IState[s];
		for(com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
			if(vertex instanceof com.change_vision.jude.api.inf.model.IState) {
				if(!(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState)) {
					substates[i++] = new State((com.change_vision.jude.api.inf.model.IState) vertex);
					substates[i-1].setSuperiorState(ist);
					if(((com.change_vision.jude.api.inf.model.IState) vertex).getSubvertexes().length > 0) {
						substates[i-1].setIsCompound(true);
						substates[i-1].setSubstates(settingSubstate((com.change_vision.jude.api.inf.model.IState) vertex, substates[i-1]));
						substates[i-1].setSubpseudostates(settingSubpseudostate((com.change_vision.jude.api.inf.model.IState) vertex, substates[i-1]));
						this.allStates.add(substates[i-1]);
					}else {
						this.allStates.add(substates[i-1]);
					}
				}
			}
		}
		return substates;
	}

	private IPseudostate[] settingSubpseudostate(com.change_vision.jude.api.inf.model.IState st, IState ist) {
		int i = 0;	
		int g = 0;
		for (com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
			if(!(vertex instanceof com.change_vision.jude.api.inf.model.IState)) {
				g++;
			}else if(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState) {
				g++;
			}
		}
		IPseudostate[] subpseudostates = new IPseudostate[g];

		for (com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) st).getSubvertexes()) {
			if(!(vertex instanceof com.change_vision.jude.api.inf.model.IState)) {
				subpseudostates[i++] = new Pseudostate((com.change_vision.jude.api.inf.model.IPseudostate) vertex);
				subpseudostates[i-1].setSuperiorState(ist);
				this.allPseudostates.add(subpseudostates[i-1]);
			}else if(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState) {
				subpseudostates[i++] = new Pseudostate((com.change_vision.jude.api.inf.model.IFinalState) vertex);
				subpseudostates[i-1].setSuperiorState(ist);
				this.allPseudostates.add(subpseudostates[i-1]);
			}
		}

		return subpseudostates;
	}

	private void settingTransitionsSourceTarget(com.change_vision.jude.api.inf.model.IStateMachine sm) throws WellFormedException {
		if(this.transitions.length != 0) {	
			for(ITransition tr : this.transitions) {
				if(sm.getTransitions().length != 0) {
					for(com.change_vision.jude.api.inf.model.ITransition trAst : sm.getTransitions()){
						if(tr.getId().equals(trAst.getId())) {
							if(this.allStates.size() != 0) {	
								for(IState st : this.allStates) {
									if(st.getId().equals(trAst.getSource().getId())) {
										tr.setSource(st);
									}else if(st.getId().equals(trAst.getTarget().getId())) {
										tr.setTarget(st);
									}
								}
							}
							if(this.allPseudostates.size() != 0) {	
								for(IPseudostate pst : this.allPseudostates) {
									if(trAst.getSource() != null) {
										if(pst.getId().equals(trAst.getSource().getId())) {
											tr.setSource(pst);
										}
									}if(trAst.getTarget() != null) {
										if(pst.getId().equals(trAst.getTarget().getId())) {
											tr.setTarget(pst);
										}
									}
								}
							}
						}
					}
				}else {
					throw new WellFormedException("State Machine " + sm.getName() + "has no transitions");
				}
			}
		}
	}

	private void settingStatesIncomingsOutgoings(com.change_vision.jude.api.inf.model.IStateMachine sm) {
		int i = 0;
		for(IState st: this.states) {
			i=0;
			if(this.getStates().length != 0) {
				for(com.change_vision.jude.api.inf.model.IVertex vertex : sm.getVertexes()) {
					if(vertex instanceof com.change_vision.jude.api.inf.model.IState) {
						if(!(vertex instanceof com.change_vision.jude.api.inf.model.IFinalState)) {	
							if(st.getId().equals(vertex.getId())) {

								if(((com.change_vision.jude.api.inf.model.IState) vertex).getSubvertexes().length > 0) {
									settingCompoundStatesIncomingsOutgoings(st, vertex);
								}

								if(vertex.getIncomings().length != 0){
									ITransition[] incomings = new ITransition[vertex.getIncomings().length];
									for(com.change_vision.jude.api.inf.model.ITransition trIncoming : vertex.getIncomings()) {
										if(this.transitions.length != 0) {
											for(ITransition tr : this.getTransitions()) {
												if(tr.getId().equals(trIncoming.getId())) {
													incomings[i++] = tr;
													break;
												}
											}
										}
									}
									st.setIncomings(incomings);
								}

								i=0;

								if(vertex.getOutgoings().length != 0) {
									ITransition[] outgoings = new ITransition[vertex.getOutgoings().length];
									for(com.change_vision.jude.api.inf.model.ITransition trOutgoing : vertex.getOutgoings()) {
										if(this.transitions.length != 0) {
											for(ITransition tr : this.getTransitions()) {
												if(tr.getId().equals(trOutgoing.getId())) {
													outgoings[i++] = tr;
													break;
												}
											}
										}
										st.setOutgoings(outgoings);
									}
								}
								break;
							}
						}
					}
				}
			}
		}

		i = 0;
		for(IPseudostate st: this.pseudostates) {
			i=0;
			if(this.getPseudostates().length != 0) {
				for(com.change_vision.jude.api.inf.model.IVertex vertex : sm.getVertexes()) {
					if(vertex instanceof com.change_vision.jude.api.inf.model.IPseudostate) {	
						if(st.getId().equals(vertex.getId())) {
							if(vertex.getIncomings().length != 0){
								ITransition[] incomings = new ITransition[vertex.getIncomings().length];
								for(com.change_vision.jude.api.inf.model.ITransition trIncoming : vertex.getIncomings()) {
									if(this.transitions.length != 0) {
										for(ITransition tr : this.getTransitions()) {
											if(tr.getId().equals(trIncoming.getId())) {
												incomings[i++] = tr;
												break;
											}
										}
									}
								}
								st.setIncomings(incomings);
							}

							i=0;

							if(vertex.getOutgoings().length != 0) {
								ITransition[] outgoings = new ITransition[vertex.getOutgoings().length];
								for(com.change_vision.jude.api.inf.model.ITransition trOutgoing : vertex.getOutgoings()) {
									if(this.transitions.length != 0) {
										for(ITransition tr : this.getTransitions()) {
											if(tr.getId().equals(trOutgoing.getId())) {
												outgoings[i++] = tr;
												break;
											}
										}
									}
									st.setOutgoings(outgoings);
								}
							}
							break;
						}
					}
				}
			}
		}

	}


	private void settingCompoundStatesIncomingsOutgoings(IState state, com.change_vision.jude.api.inf.model.IVertex vt) {
		int i = 0;
		for(IState st: state.getSubstates()) {
			i=0;
			if(vt instanceof com.change_vision.jude.api.inf.model.IState) {
				if(!(vt instanceof com.change_vision.jude.api.inf.model.IFinalState)) {	
					for(com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) vt).getSubvertexes()) {
						if(st.getId().equals(vertex.getId())) {

							if(((com.change_vision.jude.api.inf.model.IState) vertex).getSubvertexes().length > 0) {
								settingCompoundStatesIncomingsOutgoings(st, vertex);
							}

							if(vertex.getIncomings().length != 0){
								ITransition[] incomings = new ITransition[vertex.getIncomings().length];
								for(com.change_vision.jude.api.inf.model.ITransition trIncoming : vertex.getIncomings()) {
									if(this.transitions.length != 0) {
										for(ITransition tr : this.getTransitions()) {
											if(tr.getId().equals(trIncoming.getId())) {
												incomings[i++] = tr;
												break;
											}
										}
									}
								}
								st.setIncomings(incomings);
							}

							i=0;

							if(vertex.getOutgoings().length != 0) {
								ITransition[] outgoings = new ITransition[vertex.getOutgoings().length];
								for(com.change_vision.jude.api.inf.model.ITransition trOutgoing : vertex.getOutgoings()) {
									if(this.transitions.length != 0) {
										for(ITransition tr : this.getTransitions()) {
											if(tr.getId().equals(trOutgoing.getId())) {
												outgoings[i++] = tr;
												break;
											}
										}
									}
									st.setOutgoings(outgoings);
								}
							}
							break;
						}
					}
				}
			}
		}

		i = 0;
		for(IPseudostate st: state.getSubpseudostates()) {
			i=0;
			if(this.getPseudostates().length != 0) {
				if(vt instanceof com.change_vision.jude.api.inf.model.IState) {
					for(com.change_vision.jude.api.inf.model.IVertex vertex : ((com.change_vision.jude.api.inf.model.IState) vt).getSubvertexes()) {
						if(vertex instanceof com.change_vision.jude.api.inf.model.IPseudostate) {	
							if(st.getId().equals(vertex.getId())) {
								if(vertex.getIncomings().length != 0){
									ITransition[] incomings = new ITransition[vertex.getIncomings().length];
									for(com.change_vision.jude.api.inf.model.ITransition trIncoming : vertex.getIncomings()) {
										if(this.transitions.length != 0) {
											for(ITransition tr : this.getTransitions()) {
												if(tr.getId().equals(trIncoming.getId())) {
													incomings[i++] = tr;
													break;
												}
											}
										}
									}
									st.setIncomings(incomings);
								}

								i=0;

								if(vertex.getOutgoings().length != 0) {
									ITransition[] outgoings = new ITransition[vertex.getOutgoings().length];
									for(com.change_vision.jude.api.inf.model.ITransition trOutgoing : vertex.getOutgoings()) {
										if(this.transitions.length != 0) {
											for(ITransition tr : this.getTransitions()) {
												if(tr.getId().equals(trOutgoing.getId())) {
													outgoings[i++] = tr;
													break;
												}
											}
										}
										st.setOutgoings(outgoings);
									}
								}
								break;
							}
						}
					}
				}
			}
		}
	}


	public void settingFirstState() {
		for(IPseudostate pseudostate : this.pseudostates) {
			if(pseudostate.isInitialPseudostate()) {
				for(ITransition tr : pseudostate.getOutgoings()) {
					if(tr.getTarget() instanceof IState) {
						for(IState state : this.states) {
							if(tr.getTarget().getId() == state.getId()) {
								state.setIsFirstState(true);
								break;
							}
						}
					}
					if(tr.getTarget() instanceof IPseudostate) {
						for(IPseudostate pst : this.pseudostates) {
							if(tr.getTarget().getId() == pst.getId()) {
								pst.setIsFirstState(true);
								break;
							}
						}
					}
				}
				break;
			}
		}
	}

	@Override
	public String getId() {
		return this.stateMachine.getId();
	}

	@Override
	public String getName() {
		return this.stateMachine.getName();
	}

	@Override
	public String getDefinition() {
		return this.stateMachine.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return this.stateMachine.getStereotypes();
	}

	@Override
	public IStateMachineDiagram getStateMachineDiagram() {
		return this.smd;
	}

	@Override
	public ITransition[] getTransitions() {
		return this.transitions;
	}

	@Override
	public IState[] getStates() {
		return this.states;
	}

	@Override
	public IPseudostate[] getPseudostates() {
		return this.pseudostates;
	}

	@Override
	public void setName(String nameSM) {
		try {
			this.stateMachine.setName(nameSM);
		} catch (com.change_vision.jude.api.inf.exception.InvalidEditingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setStateMachineDiagram(IStateMachineDiagram stateMachineDiagram) {
		this.smd = stateMachineDiagram;
	}

}
