package com.ref.astah.traceability;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.editor.ActivityDiagramEditor;
import com.change_vision.jude.api.inf.editor.BasicModelEditor;
import com.change_vision.jude.api.inf.editor.ModelEditorFactory;
import com.change_vision.jude.api.inf.editor.TransactionManager;
import com.change_vision.jude.api.inf.exception.InvalidEditingException;
import com.change_vision.jude.api.inf.exception.InvalidUsingException;
import com.change_vision.jude.api.inf.model.IAction;
import com.change_vision.jude.api.inf.model.IActivity;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
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
import com.ref.traceability.activityDiagram.ActivityController.VerificationType;

public class CounterExampleAstah {
    private static HashMap<String, INodePresentation> nodeAdded;
    private static HashMap<String, INodePresentation> objPresent;
	private static IPackage packageCounterExample;
	private static IActivityDiagram ad;
    
	public static void createCounterExample(HashMap<com.ref.interfaces.activityDiagram.IActivity, List<String>> counterExample, IDiagram diagrama,
			VerificationType type) {
		
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

	private static IActivity getDiagram(IDiagram aDdiagrams,IDiagram[] diagrams) {
		for(IDiagram diagram : diagrams) {
			if(aDdiagrams.getId().equals(diagram.getId())) {
				return ((IActivityDiagram) diagram).getActivity();
			}
		}
		return null;
	}
	
	private static void createPackage(BasicModelEditor basicModelEditor, IModel project,VerificationType cet) {
        try {
        	if(cet == VerificationType.DEADLOCK) {
        		packageCounterExample = basicModelEditor.createPackage(project, "DeadlockCounterExample");	
        	}else if(cet == VerificationType.DETERMINISM) {
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
}
