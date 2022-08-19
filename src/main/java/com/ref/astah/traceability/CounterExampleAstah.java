package com.ref.astah.traceability;

import java.awt.geom.Point2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.ActivityDiagramEditor;
import com.change_vision.jude.api.inf.editor.BasicModelEditor;
import com.change_vision.jude.api.inf.editor.ModelEditorFactory;
import com.change_vision.jude.api.inf.editor.StateMachineDiagramEditor;
import com.change_vision.jude.api.inf.editor.TransactionManager;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IAction;
import com.change_vision.jude.api.inf.model.IVertex;
import com.change_vision.jude.api.inf.model.IState;
import com.change_vision.jude.api.inf.model.IPseudostate;
import com.change_vision.jude.api.inf.model.ITransition;
import com.change_vision.jude.api.inf.model.IFinalState;
import com.change_vision.jude.api.inf.model.IActivity;
import com.change_vision.jude.api.inf.model.IStateMachine;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.IStateMachineDiagram;
import com.change_vision.jude.api.inf.model.IActivityNode;
import com.change_vision.jude.api.inf.model.IActivityParameterNode;
import com.change_vision.jude.api.inf.model.IControlNode;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.change_vision.jude.api.inf.model.IFlow;
import com.change_vision.jude.api.inf.model.IInputPin;
import com.change_vision.jude.api.inf.model.IModel;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IObjectNode;
import com.change_vision.jude.api.inf.model.IOutputPin;
import com.change_vision.jude.api.inf.model.IPackage;
import com.change_vision.jude.api.inf.model.IPin;
import com.change_vision.jude.api.inf.presentation.ILinkPresentation;
import com.change_vision.jude.api.inf.presentation.INodePresentation;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.ref.ActivityController.VerificationType;
//import com.ref.StateMachineController.VerificationType;
import com.ref.astah.ui.CounterView;
import com.ref.parser.stateMachine.SMUtils;

public class CounterExampleAstah {
    private static HashMap<String, INodePresentation> nodeAdded;
    private static HashMap<String, ILinkPresentation> transitionAdded;
    private static HashMap<String, INodePresentation> objPresent;
    private static HashMap<String, String> typeAdded;
	private static IPackage packageCounterExample;
	private static IActivityDiagram ad;
	private static IStateMachineDiagram smd;
    
	public static void createCounterExample(HashMap<com.ref.interfaces.activityDiagram.IActivity, List<String>> counterExample, IDiagram diagrama,
			com.ref.ActivityController.VerificationType type) {
		
		 try {
	            Date hoje = new Date();
	            SimpleDateFormat df;
	            df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
	            String data = df.format(hoje);

	            nodeAdded = new HashMap<>();
	            objPresent = new HashMap<>();
	            
	            IDiagram[] diagrams = AstahAPI.getAstahAPI().getProjectAccessor().getProject().getDiagrams();	            
	            IDiagram diagram = AstahAPI.getAstahAPI().getViewManager().getDiagramViewManager().getCurrentDiagram();
	            
	            ProjectAccessor prjAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
	            IModel project = prjAccessor.getProject();
	            BasicModelEditor basicModelEditor = ModelEditorFactory.getBasicModelEditor();

	            TransactionManager.beginTransaction();
	            createPackage(basicModelEditor, project,type);
	            ActivityDiagramEditor adEditor = prjAccessor.getDiagramEditorFactory().getActivityDiagramEditor();
	            for(IDiagram ADdiagrams: diagrams) {//For each diagram
	            	if(ADdiagrams instanceof IActivityDiagram) {
	            		com.ref.interfaces.activityDiagram.IActivity aux = null;
		            	boolean contains = false;
		            	for(com.ref.interfaces.activityDiagram.IActivity act: counterExample.keySet()) {
		            		if(act.getId().equals(((IActivityDiagram)ADdiagrams).getActivity().getId())) {
		            			contains = true;
		            			aux = act;
		            		}
		            	}
		            	if(ADdiagrams == diagram || contains) {//If is related to the counterExample
		            		IActivity diagrama1 = getDiagram(ADdiagrams,diagrams);
			            	ad = adEditor.createActivityDiagram(packageCounterExample, ADdiagrams.getName()+ "#" + data);
			            	for (IActivityNode node : ((IActivityDiagram) ADdiagrams).getActivity().getActivityNodes()) {
			            		
			                    createNode(node, adEditor,diagrama1,counterExample.get(aux));
			                }
		            	}
	            	}
	            }
	            TransactionManager.endTransaction();
	        } catch (Exception e) {
	            TransactionManager.abortTransaction();
	        }		
	}
	
	public static void createCounterExampleSM(HashMap<com.ref.interfaces.stateMachine.IStateMachine, List<String>> counterExample, IDiagram diagrama,
			com.ref.StateMachineController.VerificationType type) {
		
		 try {
	            Date hoje = new Date();
	            SimpleDateFormat df;
	            df = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
	            String data = df.format(hoje);

	            nodeAdded = new HashMap<>();
	            transitionAdded = new HashMap<>();
	            typeAdded = new HashMap<>(); 
	            
	            IDiagram[] diagrams = AstahAPI.getAstahAPI().getProjectAccessor().getProject().getDiagrams();	            
	            IDiagram diagram = AstahAPI.getAstahAPI().getViewManager().getDiagramViewManager().getCurrentDiagram();
	            
	            ProjectAccessor prjAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
	            IModel project = prjAccessor.getProject();
	            BasicModelEditor basicModelEditor = ModelEditorFactory.getBasicModelEditor();

	            TransactionManager.beginTransaction();
	            createPackage(basicModelEditor, project,type);
	            StateMachineDiagramEditor smEditor = prjAccessor.getDiagramEditorFactory().getStateMachineDiagramEditor();
	            for(IDiagram SMdiagrams: diagrams) {//For each diagram
	            	if(SMdiagrams instanceof IStateMachineDiagram) {
	            		com.ref.interfaces.stateMachine.IStateMachine aux = null;
		            	boolean contains = false;
		            	for(com.ref.interfaces.stateMachine.IStateMachine act: counterExample.keySet()) {
		            		if(act.getId().equals(((IStateMachineDiagram)SMdiagrams).getStateMachine().getId())) {
		            			contains = true;
		            			aux = act;
		            		}
		            	}
		            	if(SMdiagrams == diagram || contains) {//If is related to the counterExample
		            		IStateMachine diagrama1 = getStateMachineDiagram(SMdiagrams,diagrams);
			            	smd = smEditor.createStatemachineDiagram(packageCounterExample, SMdiagrams.getName()+ "#" + data);
			            	for (IVertex vertex : ((IStateMachineDiagram) SMdiagrams).getStateMachine().getVertexes()) {
			                    createVertex(vertex, smEditor,diagrama1,counterExample.get(aux),null);
			                    createSubstates(vertex, smEditor,diagrama1,counterExample.get(aux));
			                }
			            	for(ITransition transition : ((IStateMachineDiagram) SMdiagrams).getStateMachine().getTransitions()) {
			            		createTransitions(transition, smEditor,diagrama1,counterExample.get(aux));
			            	}
			            	
			            	CounterView.setCouterView(smd,nodeAdded,transitionAdded,typeAdded);
		            	}
	            	}
	            }
	            TransactionManager.endTransaction();
	        } catch (Exception e) {
	            TransactionManager.abortTransaction();
	        }		 
	}
	
	private static void createSubstates(IVertex vertex, StateMachineDiagramEditor smEditor,IStateMachine diagram, List<String> trace) {
		if(vertex instanceof IState) {
        	if(((IState) vertex).getSubvertexes() != null) {
        		if(((IState) vertex).getSubvertexes().length > 0) {
        			for (IVertex vtx : ((IState) vertex).getSubvertexes()) {
	            		INodePresentation parent = nodeAdded.get(SMUtils.nameResolver(vertex.getName() + "_" + diagram));
	                    createVertex(vtx, smEditor,diagram,trace,parent);
	                    createSubstates(vtx, smEditor, diagram, trace);
	                }
        		}
        	}
        }
	}
	private static IActivity getDiagram(IDiagram aDdiagrams,IDiagram[] diagrams) {
		for(IDiagram diagram : diagrams) {
			if(aDdiagrams.getId().equals(diagram.getId())) {
				return ((IActivityDiagram) diagram).getActivity();
			}
		}
		return null;
	}
	
	private static IStateMachine getStateMachineDiagram(IDiagram smDiagrams,IDiagram[] diagrams) {
		for(IDiagram diagram : diagrams) {
			if(smDiagrams.getId().equals(diagram.getId())) {
				return ((IStateMachineDiagram) diagram).getStateMachine();
			}
		}
		return null;
	}
	
	private static void createPackage(BasicModelEditor basicModelEditor, IModel project,com.ref.StateMachineController.VerificationType cet) {
        try {
        	if(cet == com.ref.StateMachineController.VerificationType.DEADLOCK) {
        		packageCounterExample = basicModelEditor.createPackage(project, "DeadlockCounterExample");	
        	}else if(cet == com.ref.StateMachineController.VerificationType.DETERMINISM) {
        		packageCounterExample = basicModelEditor.createPackage(project, "DeterminismCounterExample");
        	}
            
        } catch (InvalidEditingException e) {
            INamedElement[] objects = project.getOwnedElements();

            for (INamedElement object : objects) {
                if (object.getName().equals("DeadlockCounterExample")) {
                    packageCounterExample = (IPackage) object;
                }
            }
        }
    }
	
	private static void createPackage(BasicModelEditor basicModelEditor, IModel project,com.ref.ActivityController.VerificationType cet) {
        try {
        	if(cet == com.ref.ActivityController.VerificationType.DEADLOCK) {
        		packageCounterExample = basicModelEditor.createPackage(project, "DeadlockCounterExample");	
        	}else if(cet == com.ref.ActivityController.VerificationType.DETERMINISM) {
        		packageCounterExample = basicModelEditor.createPackage(project, "DeterminismCounterExample");
        	}
            
        } catch (InvalidEditingException e) {
            INamedElement[] objects = project.getOwnedElements();

            for (INamedElement object : objects) {
                if (object.getName().equals("DeadlockCounterExample")) {
                    packageCounterExample = (IPackage) object;
                }
            }
        }
    }
	
	private static IActivityNode getIActivityNode(INodePresentation nodePresent) {
        IActivityNode result = null;
        try {
            for (IActivityNode actNode : ad.getActivity().getActivityNodes()) {
                if (actNode.getPresentations()[0] == nodePresent) {
                    result = actNode;
                    break;
                }
            }
        } catch (InvalidUsingException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static IFlow getIFlow(ILinkPresentation flowPresent) {
        IFlow result = null;
        try {
            for (IActivityNode actNode : ad.getActivity().getActivityNodes()) {
                if (actNode instanceof IAction) {
                    IFlow[] outflows = actNode.getOutgoings();
                    for (IFlow flow : outflows){
                        if (flow.getPresentations()[0] == flowPresent) {
                            result = flow;
                            break;
                        }
                    }

                    //outPins
                    IOutputPin[] outPins = ((IAction) actNode).getOutputs();
                    for (IOutputPin outPin : outPins) {
                        outflows = outPin.getOutgoings();
                        for (IFlow flow : outflows){
                            if (flow.getPresentations()[0] == flowPresent) {
                                result = flow;
                                break;
                            }
                        }
                    }
                } else {
                    IFlow[] outflows = actNode.getOutgoings();
                    for (IFlow flow : outflows){
                        if (flow.getPresentations()[0] == flowPresent) {
                            result = flow;
                            break;
                        }
                    }
                }
            }
        } catch (InvalidUsingException e) {
            e.printStackTrace();
        }

        return result;
    }

    private static void setFlowPoints(ILinkPresentation flow, IFlow targetOutFlow) {
        try {
            flow.setAllPoints(((ILinkPresentation) targetOutFlow.getPresentations()[0]).getAllPoints());
        } catch (Exception e) { }
    }
	
	private static INodePresentation createNode(IActivityNode node, ActivityDiagramEditor adEditor,IActivity diagram, List<String> trace) {
        INodePresentation nodePresent = null;

        if (!nodeAdded.containsKey(node.getId())) {
            if (node instanceof IAction) {
                if (((IAction) node).isCallBehaviorAction()) {
                    nodePresent = createAction(node, adEditor, true,diagram,trace);
                } else {
                    nodePresent = createAction(node, adEditor, false,diagram,trace);
                }
            } else if (node instanceof IControlNode) {
                if (((IControlNode) node).isFinalNode()) {
                    nodePresent = createFinal(node, adEditor,diagram,trace);
                } else if (((IControlNode) node).isFlowFinalNode()) {
                    nodePresent = createFlowFinal(node, adEditor,diagram,trace);
                } else if (((IControlNode) node).isInitialNode()) {
                    nodePresent = createInitial(node, adEditor,diagram,trace);
                } else if (((IControlNode) node).isForkNode()) {
                    nodePresent = createFork(node, adEditor,diagram,trace);
                } else if (((IControlNode) node).isJoinNode()) {
                    nodePresent = createJoin(node, adEditor,diagram,trace);
                } else if (((IControlNode) node).isDecisionMergeNode()) {
                    nodePresent = createDecisionAndMerge(node, adEditor,diagram,trace);
                }
            } else if (node instanceof IActivityParameterNode) {
                nodePresent = createParameter(node, adEditor,diagram,trace);
            } else if (node instanceof IObjectNode && !(node instanceof IPin)) {
                nodePresent = createObjectNode(node, adEditor,diagram,trace);
            }
        } else {
            nodePresent = nodeAdded.get(node.getId());
        }

        return nodePresent;
    }
	
	private static INodePresentation createVertex(IVertex vertex, StateMachineDiagramEditor smEditor,IStateMachine diagram, List<String> trace, INodePresentation parent) {
        INodePresentation nodePresent = null;

        if (!nodeAdded.containsKey(vertex.getId())) {
            if (vertex instanceof IFinalState) {
            	nodePresent = createFinal(vertex, smEditor,diagram,trace,parent);
            } else if (vertex instanceof IPseudostate) {
                if (((IPseudostate) vertex).isJunctionPseudostate()) {
                    nodePresent = createJunctionPseudostate(vertex, smEditor,diagram,trace,parent);
                } else if (((IPseudostate) vertex).isChoicePseudostate()) {
                    nodePresent = createChoicePseudostate(vertex, smEditor,diagram,trace,parent);
                } else if (((IPseudostate) vertex).isInitialPseudostate()) {
                    nodePresent = createInitialPseudostate(vertex, smEditor,diagram,trace,parent);
                }
            } else if (vertex instanceof IState) {
                nodePresent = createState(vertex, smEditor,diagram,trace,parent);
            } 
        } else {
            nodePresent = nodeAdded.get(vertex.getId());
        }

        return nodePresent;
    }
	
	private static ILinkPresentation createTransitions(ITransition transition, StateMachineDiagramEditor smEditor,IStateMachine diagram, List<String> trace) throws InvalidUsingException {
        ILinkPresentation nodePresent = null;

        if (!nodeAdded.containsKey(transition.getId())) {
            if (transition instanceof ITransition) {
            	nodePresent = createTransition(transition, smEditor,diagram,trace);
            } 
        } else {
            nodePresent = (ILinkPresentation) nodeAdded.get(transition.getId());
        }

        return nodePresent;
    }
	
	 private static INodePresentation createAction(IActivityNode node, ActivityDiagramEditor adEditor, boolean callBehaviour, IActivity diagram, List<String> trace) {
	        IFlow[] outFlows = node.getOutgoings();
	        IInputPin[] inPins = ((IAction) node).getInputs();
	        IOutputPin[] outPins = ((IAction) node).getOutputs();
	        INodePresentation actionNode = null;

	        try {
	            if (callBehaviour) {
	                actionNode = adEditor.createCallBehaviorAction(node.getName(), null, ((INodePresentation) node.getPresentations()[0]).getLocation());
	            } else {
	            	if(((IAction) node).isAcceptEventAction()) {
	            		actionNode = adEditor.createAcceptEventAction(node.getName(), ((INodePresentation) node.getPresentations()[0]).getLocation());            		
	            	}else if(((IAction) node).isSendSignalAction()) {
	            		actionNode = adEditor.createSendSignalAction(node.getName(), ((INodePresentation) node.getPresentations()[0]).getLocation());
	            	}else {
	            		actionNode = adEditor.createAction(node.getName(), ((INodePresentation) node.getPresentations()[0]).getLocation());
	            	}
	            }

	            IActivityNode actNode = getIActivityNode(actionNode);
	            actNode.setDefinition(node.getDefinition());
	            if(trace.contains(node.getId())) {
	            	actionNode.setProperty("fill.color", "#FF0000");
	            }
	            nodeAdded.put(node.getId(), actionNode);

	            for (int i = 0; i < inPins.length; i++) {
	                createInputPin(node, adEditor, actionNode, inPins[i],trace);	          
	            }

	            for (int i = 0; i < outPins.length; i++) {
	                createOutputPin(node, adEditor, actionNode, outPins[i],trace);
	            }

	            for (int i = 0; i < outFlows.length; i++) {
	                INodePresentation targetPresent = createNode(outFlows[i].getTarget(), adEditor,diagram,trace);
	                ILinkPresentation flow = adEditor.createFlow(actionNode, targetPresent);
	                flow.setLabel(outFlows[i].getGuard());

	                IFlow flowPresent = getIFlow(flow);
	                for (String stereotype : outFlows[i].getStereotypes()) {
	                    flowPresent.addStereotype(stereotype);
	                }

	                setFlowPoints(flow, outFlows[i]);
	     
	                if(trace.contains(outFlows[i].getId())) {
                   	 flow.setProperty("line.color", "#FF0000");
                   }
	                
	            }

	            for (int i = 0; i < outPins.length; i++) {
	                IFlow[] targetOutFlows = outPins[i].getOutgoings();
	                for (int x = 0; x < targetOutFlows.length; x++) {
	                    if (targetOutFlows[x].getTarget() instanceof IInputPin) {
	                        createNode((IActivityNode) targetOutFlows[x].getTarget().getOwner(), adEditor,diagram,trace);
	                        INodePresentation targetPresent = objPresent.get(targetOutFlows[x].getTarget().getId());
	                        INodePresentation pinPresent = objPresent.get(outPins[i].getId());
	                        ILinkPresentation flow = adEditor.createFlow(pinPresent, targetPresent);
	                        flow.setLabel(targetOutFlows[x].getGuard());

	                        IFlow flowPresent = getIFlow(flow);
	                        for (String stereotype : targetOutFlows[x].getStereotypes()) {
	                            flowPresent.addStereotype(stereotype);
	                        }

	                        setFlowPoints(flow, targetOutFlows[x]);
	                        
	                        if(trace.contains(targetOutFlows[x].getId())) {//TODO verify here
		                    	 flow.setProperty("line.color", "#FF0000");
		                    	 pinPresent.setProperty("fill.color", "#FF0000");
		                    }
	                        
	                    } else {
	                        INodePresentation targetPresent = createNode(targetOutFlows[x].getTarget(), adEditor,diagram,trace);
	                        INodePresentation pinPresent = objPresent.get(outPins[i].getId());
	                        ILinkPresentation flow = adEditor.createFlow(pinPresent, targetPresent);
	                        flow.setLabel(targetOutFlows[x].getGuard());

	                        IFlow flowPresent = getIFlow(flow);
	                        for (String stereotype : targetOutFlows[x].getStereotypes()) {
	                            flowPresent.addStereotype(stereotype);
	                        }

	                        setFlowPoints(flow, targetOutFlows[x]);
	                        
	                        if(trace.contains(targetOutFlows[i].getId())) {
		                    	 flow.setProperty("line.color", "#FF0000");
		                    	 pinPresent.setProperty("fill.color", "#FF0000");
		                    }

	                    }
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        return actionNode;

	    }
	 
	 private static INodePresentation createInitial(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        IFlow[] outFlows = node.getOutgoings();
	        INodePresentation initialNode = null;

	        try {
	            initialNode = adEditor.createInitialNode(node.getName(), ((INodePresentation) node.getPresentations()[0]).getLocation());

	            if(trace.contains(node.getId())) {
	            	initialNode.setProperty("fill.color", "#FF0000");
	            }
	            nodeAdded.put(node.getId(), initialNode);

	            for (int i = 0; i < outFlows.length; i++) {
	                INodePresentation targetPresent = createNode(outFlows[i].getTarget(), adEditor,diagram,trace);
	                ILinkPresentation flow = adEditor.createFlow(initialNode, targetPresent);
	                flow.setLabel(outFlows[i].getGuard());

	                IFlow flowPresent = getIFlow(flow);
	                for (String stereotype : outFlows[i].getStereotypes()) {
	                    flowPresent.addStereotype(stereotype);
	                }

	                setFlowPoints(flow, outFlows[i]);
	                
	                if(trace.contains(outFlows[i].getId())) {
                   	 flow.setProperty("line.color", "#FF0000");
                   }

	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return initialNode;
	    }

	    private static INodePresentation createParameter(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        IFlow[] outFlows = node.getOutgoings();
	        INodePresentation parameterNode = null;

	        try {
	            parameterNode = adEditor.createActivityParameterNode(node.getName(), ((IActivityParameterNode) node).getBase(), ((INodePresentation) node.getPresentations()[0]).getLocation());
	            
	            if(trace.contains(node.getId())) {
	            	parameterNode.setProperty("fill.color", "#FF0000");
	            }
	            nodeAdded.put(node.getId(), parameterNode);

	            for (int i = 0; i < outFlows.length; i++) {
	                if (outFlows[i].getTarget() instanceof IInputPin) {
	                    createNode((IActivityNode) outFlows[i].getTarget().getOwner(), adEditor,diagram,trace);
	                    INodePresentation targetPresent = objPresent.get(outFlows[i].getTarget().getId());
	                    ILinkPresentation flow = adEditor.createFlow(parameterNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);

	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	                    
	                } else {
	                    INodePresentation targetPresent = createNode(outFlows[i].getTarget(), adEditor,diagram,trace);
	                    ILinkPresentation flow = adEditor.createFlow(parameterNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);

	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	                 
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return parameterNode;
	    }

	    private static INodePresentation createDecisionAndMerge(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        IFlow[] outFlows = node.getOutgoings();
	        INodePresentation decisionNode = null;

	        try {
	            decisionNode = adEditor.createDecisionMergeNode(null, ((INodePresentation) node.getPresentations()[0]).getLocation());
	            decisionNode.setLabel(node.getName());
	            
	            if(trace.contains(node.getId())) {
	            	decisionNode.setProperty("fill.color", "#FF0000");
	            }

	            nodeAdded.put(node.getId(), decisionNode);

	            for (int i = 0; i < outFlows.length; i++) {
	                if (outFlows[i].getTarget() instanceof IInputPin) {
	                    createNode((IActivityNode) outFlows[i].getTarget().getOwner(), adEditor,diagram,trace);
	                    INodePresentation targetPresent = objPresent.get(outFlows[i].getTarget().getId());
	                    ILinkPresentation flow = adEditor.createFlow(decisionNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);

	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	                  
	                } else {
	                    INodePresentation targetPresent = createNode(outFlows[i].getTarget(), adEditor,diagram,trace);
	                    ILinkPresentation flow = adEditor.createFlow(decisionNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);
	                    
	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	                    
	                }
	            }

	        } catch (Exception e) {
	           e.printStackTrace();
	        }

	        return decisionNode;
	    }

	    private static INodePresentation createFork(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        IFlow[] outFlows = node.getOutgoings();
	        INodePresentation forkNode = null;

	        try {
	            forkNode = adEditor.createForkNode(null, ((INodePresentation) node.getPresentations()[0]).getLocation(),
	                    ((INodePresentation) node.getPresentations()[0]).getWidth(), ((INodePresentation) node.getPresentations()[0]).getHeight());
	            forkNode.setLabel(node.getName());

	            if(trace.contains(node.getId())) {
	            	forkNode.setProperty("fill.color", "#FF0000");
	            }

	            nodeAdded.put(node.getId(), forkNode);

	            for (int i = 0; i < outFlows.length; i++) {
	                if (outFlows[i].getTarget() instanceof IInputPin) {
	                    createNode((IActivityNode) outFlows[i].getTarget().getOwner(), adEditor,diagram,trace);
	                    INodePresentation targetPresent = objPresent.get(outFlows[i].getTarget().getId());
	                    ILinkPresentation flow = adEditor.createFlow(forkNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);
	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	               

	                } else {
	                    INodePresentation targetPresent = createNode(outFlows[i].getTarget(), adEditor,diagram,trace);
	                    ILinkPresentation flow = adEditor.createFlow(forkNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);
	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	                    
	                }
	            }

	        } catch (Exception e) {
	           e.printStackTrace();
	        }

	        return forkNode;
	    }

	    private static INodePresentation createJoin(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        IFlow[] outFlows = node.getOutgoings();
	        INodePresentation joinNode = null;

	        try {
	            joinNode = adEditor.createJoinNode(null, ((INodePresentation) node.getPresentations()[0]).getLocation(),
	                    ((INodePresentation) node.getPresentations()[0]).getWidth(), ((INodePresentation) node.getPresentations()[0]).getHeight());
	            joinNode.setLabel(node.getName());

	            if(trace.contains(node.getId())) {
	            	joinNode.setProperty("fill.color", "#FF0000");
	            }

	            nodeAdded.put(node.getId(), joinNode);

	            for (int i = 0; i < outFlows.length; i++) {
	                if (outFlows[i].getTarget() instanceof IInputPin) {
	                    createNode((IActivityNode) outFlows[i].getTarget().getOwner(), adEditor,diagram,trace);
	                    INodePresentation targetPresent = objPresent.get(outFlows[i].getTarget().getId());
	                    ILinkPresentation flow = adEditor.createFlow(joinNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);

	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	                    
	                } else {
	                    INodePresentation targetPresent = createNode(outFlows[i].getTarget(), adEditor,diagram,trace);
	                    ILinkPresentation flow = adEditor.createFlow(joinNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);

	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	            

	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return joinNode;
	    }

	    private static INodePresentation createFinal(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        INodePresentation FinalNode = null;

	        try {
	            FinalNode = adEditor.createFinalNode(node.getName(), ((INodePresentation) node.getPresentations()[0]).getLocation());

	            nodeAdded.put(node.getId(), FinalNode);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return FinalNode;
	    }

	    
	    
	    private static INodePresentation createFlowFinal(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        INodePresentation flowFinalNode = null;

	        try {
	            flowFinalNode = adEditor.createFlowFinalNode(node.getName(), ((INodePresentation) node.getPresentations()[0]).getLocation());
	            
	            if(trace.contains(node.getId())) {
	            	flowFinalNode.setProperty("fill.color", "#FF0000");
	            }

	            nodeAdded.put(node.getId(), flowFinalNode);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return flowFinalNode;
	    }

	    private static INodePresentation createInputPin(IActivityNode node, ActivityDiagramEditor adEditor, INodePresentation actionNode, IInputPin pin, List<String> trace) throws InvalidEditingException {

	        INodePresentation targetPresent = null;

	        try{
	            targetPresent = adEditor.createPin(pin.getName(), pin.getBase(), true, actionNode, ((INodePresentation) pin.getPresentations()[0]).getLocation());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if(trace.contains(node.getId())) {
	        	targetPresent.setProperty("fill.color", "#FF0000");
            }
	        objPresent.put(pin.getId(), targetPresent);

	        return targetPresent;
	    }

	    private static INodePresentation createOutputPin(IActivityNode node, ActivityDiagramEditor adEditor, INodePresentation actionNode, IOutputPin pin, List<String> trace) throws InvalidEditingException {

	        INodePresentation targetPresent = null;

	        try {
	            targetPresent = adEditor.createPin(pin.getName(), pin.getBase(), false, actionNode, ((INodePresentation) pin.getPresentations()[0]).getLocation());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        if(trace.contains(node.getId())) {
	        	targetPresent.setProperty("fill.color", "#FF0000");
            }
	        objPresent.put(pin.getId(), targetPresent);

	        return targetPresent;
	    }

	    private static INodePresentation createObjectNode(IActivityNode node, ActivityDiagramEditor adEditor, IActivity diagram, List<String> trace) {
	        IFlow[] outFlows = node.getOutgoings();
	        INodePresentation objectNode = null;

	        try {
	            objectNode = adEditor.createObjectNode(node.getName(), null, ((INodePresentation) node.getPresentations()[0]).getLocation());
	            
	            if(trace.contains(node.getId())) {
	            	objectNode.setProperty("fill.color", "#FF0000");
	            }
	          
	            nodeAdded.put(node.getId(), objectNode);

	            for (int i = 0; i < outFlows.length; i++) {
	                if (outFlows[i].getTarget() instanceof IInputPin) {
	                    createNode((IActivityNode) outFlows[i].getTarget().getOwner(), adEditor,diagram,trace);
	                    INodePresentation targetPresent = objPresent.get(outFlows[i].getTarget().getId());
	                    ILinkPresentation flow = adEditor.createFlow(objectNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);
	                    
	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	               

	                } else {
	                    INodePresentation targetPresent = createNode(outFlows[i].getTarget(), adEditor,diagram,trace);
	                    ILinkPresentation flow = adEditor.createFlow(objectNode, targetPresent);
	                    flow.setLabel(outFlows[i].getGuard());

	                    IFlow flowPresent = getIFlow(flow);
	                    for (String stereotype : outFlows[i].getStereotypes()) {
	                        flowPresent.addStereotype(stereotype);
	                    }

	                    setFlowPoints(flow, outFlows[i]);
	                    
	                    if(trace.contains(outFlows[i].getId())) {
	                    	 flow.setProperty("line.color", "#FF0000");
	                    }
	                  

	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return objectNode;
	    }
	    
	    //StateMachine Methods
	    private static INodePresentation createFinal(IVertex vertex, StateMachineDiagramEditor smEditor, IStateMachine diagram, List<String> trace, INodePresentation parent) {
	        INodePresentation FinalState = null;

	        try {
	        	FinalState = smEditor.createFinalState(parent, ((INodePresentation) vertex.getPresentations()[0]).getLocation());
	        	double h = ((INodePresentation) vertex.getPresentations()[0]).getHeight();
	        	double w = ((INodePresentation) vertex.getPresentations()[0]).getWidth();
	        	FinalState.setHeight(h);
	        	FinalState.setWidth(w);
	            nodeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), FinalState);
	            typeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), "FinalState");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return FinalState;
	    }
	    
	    private static INodePresentation createJunctionPseudostate(IVertex vertex, StateMachineDiagramEditor smEditor, IStateMachine diagram, List<String> trace, INodePresentation parent) {
	        INodePresentation Junction = null;

	        try {
	        	Junction = smEditor.createJunctionPseudostate(parent, ((INodePresentation) vertex.getPresentations()[0]).getLocation());
	        	double h = ((INodePresentation) vertex.getPresentations()[0]).getHeight();
	        	double w = ((INodePresentation) vertex.getPresentations()[0]).getWidth();
	        	Junction.setHeight(h);
	        	Junction.setWidth(w);
	            nodeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), Junction);
	            typeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), "Junction");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return Junction;
	    }
	    
	    private static INodePresentation createChoicePseudostate(IVertex vertex, StateMachineDiagramEditor smEditor, IStateMachine diagram, List<String> trace, INodePresentation parent) {
	        INodePresentation Choice = null;

	        try {
	        	Choice = smEditor.createChoicePseudostate(parent, ((INodePresentation) vertex.getPresentations()[0]).getLocation());
	        	double h = ((INodePresentation) vertex.getPresentations()[0]).getHeight();
	        	double w = ((INodePresentation) vertex.getPresentations()[0]).getWidth();
	        	Choice.setHeight(h);
	        	Choice.setWidth(w);
	            nodeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), Choice);
	            typeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), "Choice");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return Choice;
	    }
	    
	    private static INodePresentation createInitialPseudostate(IVertex vertex, StateMachineDiagramEditor smEditor, IStateMachine diagram, List<String> trace, INodePresentation parent) {
	        INodePresentation Initial = null;

	        try {
	        	Initial = smEditor.createInitialPseudostate(parent, ((INodePresentation) vertex.getPresentations()[0]).getLocation());
	        	double h = ((INodePresentation) vertex.getPresentations()[0]).getHeight();
	        	double w = ((INodePresentation) vertex.getPresentations()[0]).getWidth();
	        	Initial.setHeight(h);
	        	Initial.setWidth(w);
	            nodeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), Initial);
	            typeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), "Initial");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return Initial;
	    }
	    
	    private static INodePresentation createState(IVertex vertex, StateMachineDiagramEditor smEditor, IStateMachine diagram, List<String> trace, INodePresentation parent) {
	        INodePresentation State = null;
	        try {
	        	State = smEditor.createState(vertex.getName(), parent, ((INodePresentation) vertex.getPresentations()[0]).getLocation());
	        	double h = ((INodePresentation) vertex.getPresentations()[0]).getHeight();
	        	double w = ((INodePresentation) vertex.getPresentations()[0]).getWidth();
	        	
	        	State.setLabel(vertex.getName());
        		State.setWidth(w);
	        	State.setHeight(h);
	        	
	        	if(vertex instanceof IState) {
	        		if(((IState) vertex).getEntry() != "") {
	        			((IState) State.getModel()).setEntry(((IState) vertex).getEntry());
	        		}
	        		if(((IState) vertex).getDoActivity() != "") {
	        			((IState) State.getModel()).setDoActivity(((IState) vertex).getDoActivity());
	        		}
	        		if(((IState) vertex).getExit() != "") {
	        			((IState) State.getModel()).setExit(((IState) vertex).getExit());
	        		}
	        	}
	            nodeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), State);
	            typeAdded.put(SMUtils.nameResolver(vertex.getName() + "_" + diagram.getName()), "State");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return State;
	    }
	    
	    @SuppressWarnings("deprecation")
		private static ILinkPresentation createTransition(ITransition transition, StateMachineDiagramEditor smEditor, IStateMachine diagram, List<String> trace) throws InvalidUsingException {
	    	ILinkPresentation Transition = null;
	    	INodePresentation State1 = null;
	    	INodePresentation State2 = null; 
	    	
	    	String label = transition.getEvent();
	    	if(transition.getGuard() != "") {
	    		label += " [" + transition.getGuard() + "]";
	    	}
	    	if(transition.getAction() != "") {
	    		label += "/" + transition.getAction();
	    	}
	    	Point2D[] allPoints = ((ILinkPresentation) transition.getPresentations()[0]).getAllPoints();
	        try {
	        	State1 = nodeAdded.get(SMUtils.nameResolver(transition.getSource().getName() + "_" + diagram.getName()));
	        	State2 = nodeAdded.get(SMUtils.nameResolver(transition.getTarget().getName() + "_" + diagram.getName()));
	        	Transition = smEditor.createTransition(State1,State2);
	        	if(allPoints.length > 2) {
	        		Transition.setAllPoints(allPoints);
	        	}
	        	Transition.setLabel(label);
	        	transitionAdded.put(SMUtils.nameResolver(transition.getId()), Transition);
	        	typeAdded.put(SMUtils.nameResolver(transition.getId()), "Transition");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return Transition;
	    }
}
