package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IActivityParameterNode;
import com.ref.interfaces.activityDiagram.IControlNode;
import com.ref.interfaces.activityDiagram.IObjectNode;
import com.ref.interfaces.activityDiagram.IPin;

public class ADDefineNodesActionAndControl {

    private IActivity ad;
    private IActivityDiagram adDiagram;

    private HashMap<String, Integer> countCall;
    private HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode;
    private HashMap<Pair<IActivity, String>, ArrayList<String>> parameterAlphabetNode;
    private HashMap<Pair<IActivity, String>, String> syncChannelsEdge;
    private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
    private HashMap<String, String> objectEdges;
    private List<IActivityNode> queueNode;
    private List<String> eventChannel;
    private List<String> lockChannel;
    private List<String> allInitial;
    private ArrayList<String> alphabetAllInitialAndParameter;
    private HashMap<String, String> parameterNodesInput;
    private HashMap<String, String> parameterNodesOutput;
    private HashMap<String, String> parameterNodesOutputObject;
    private List<Pair<String, Integer>> callBehaviourNumber;
    private Map<Pair<String, String>,String> memoryLocal;
    private List<Pair<String, String>> memoryLocalChannel;
    private List<ArrayList<String>> unionList;
    private HashMap<String, String> typeUnionList;
    private HashMap<String, List<String>> callBehaviourInputs;
    private HashMap<String, List<String>> callBehaviourOutputs;
    private List<Pair<String, Integer>> countSignal;
    private List<Pair<String, Integer>> countAccept;
    private HashMap<String, List<IActivity>> signalChannels;
    private List<String> signalChannelsLocal;
    private List<String> localSignalChannelsSync;
    private List<String> createdSignal;
    private List<String> createdAccept;
    private HashMap<String,Integer> allGuards;
    private ADParser adParser;
    private ADDefineAction dAction;
    private ADDefineFinalNode dFinalNode;
    private ADDefineInitialNode dInitialNode;
    private ADDefineCallBehavior dCallBehavior;
    private ADDefineFork dFork;
    private ADDefineJoin dJoin;
    private ADDefineMerge dMerge;
    private ADDefineDecision dDecision;
    private ADDefineFlowFinal dFlowFinal;
    private ADDefineInputParameterNode dInputParameterNode;
    private ADDefineOutputParameterNode dOutputParameterNode;
    private ADDefineObjectNode dObjectNode;
    private ADDefineSignal dSignal;
    private ADDefineAccept dAccept;
    private HashMap<String, Pair<String, String>> parameterSignal;
	private HashMap<String, List<String>> signalPins;

    public ADDefineNodesActionAndControl(IActivity ad, IActivityDiagram adDiagram, HashMap<String, Integer> countCall, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
                                         HashMap<Pair<IActivity, String>, ArrayList<String>> parameterAlphabetNode2, HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
                                         HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges, List<IActivityNode> queueNode,
                                         List<IActivityNode> queueRecreateNode, List<IActivity> callBehaviourList, List<String> eventChannel, List<String> lockChannel,
                                         List<String> allInitial, ArrayList<String> alphabetAllInitialAndParameter, HashMap<String, String> parameterNodesInput,
                                         HashMap<String, String> parameterNodesOutput, HashMap<String, String> parameterNodesOutputObject, List<Pair<String, Integer>> callBehaviourNumber,
                                         Map<Pair<String, String>,String> memoryLocal, List<Pair<String, String>> memoryLocalChannel, List<ArrayList<String>> unionList, HashMap<String, String> typeUnionList,
                                         HashMap<String, List<String>> callBehaviourInputs, HashMap<String, List<String>> callBehaviourOutputs, List<Pair<String, Integer>> countSignal,
                                         List<Pair<String, Integer>> countAccept, HashMap<String,List<IActivity>> signalChannels, HashMap<String, List<String>> signalPins, List<String> localSignalChannelsSync, List<String> createdSignal, List<String> createdAccept,
                                         HashMap<String, Integer> allGuards, List<String> signalChannelsLocal, HashMap<String, Pair<String, String>> parameterSignal2, ADUtils adUtils, ADParser adParser) {
        this.ad = ad;
        this.adDiagram = adDiagram;
        this.countCall = countCall;
        this.alphabetNode = alphabetNode2;
        this.parameterAlphabetNode = parameterAlphabetNode2;
        this.syncChannelsEdge = syncChannelsEdge2;
        this.syncObjectsEdge = syncObjectsEdge2;
        this.objectEdges = objectEdges;
        this.queueNode = queueNode;
        this.eventChannel = eventChannel;
        this.lockChannel = lockChannel;
        this.allInitial = allInitial;
        this.alphabetAllInitialAndParameter = alphabetAllInitialAndParameter;
        this.parameterNodesInput = parameterNodesInput;
        this.parameterNodesOutput = parameterNodesOutput;
        this.parameterNodesOutputObject = parameterNodesOutputObject;
        this.callBehaviourNumber = callBehaviourNumber;
        this.memoryLocal = memoryLocal;
        this.memoryLocalChannel = memoryLocalChannel;
        this.unionList = unionList;
        this.typeUnionList = typeUnionList;
        this.callBehaviourInputs = callBehaviourInputs;
        this.callBehaviourOutputs = callBehaviourOutputs;
        this.countSignal = countSignal;
        this.countAccept = countAccept;
        this.signalChannels = signalChannels;
        this.signalPins = signalPins;
        this.localSignalChannelsSync = localSignalChannelsSync;
        this.createdSignal = createdSignal;
        this.createdAccept = createdAccept;
        this.allGuards = allGuards;
        this.parameterSignal = parameterSignal2;
        this.signalChannelsLocal = signalChannelsLocal;
        this.adParser = adParser;
    }
    
    
    
    public String defineNodes() throws ParsingException {
    	StringBuilder nodes = new StringBuilder();
    	
    	for (IActivityNode activityNode : ad.getActivityNodes()) {
    		 if (activityNode instanceof IAction) {
                 if (((IAction) activityNode).isCallBehaviorAction()) {
                     nodes.append(defineCallBehaviour(activityNode));
                 } else if (((IAction) activityNode).isSendSignalAction()) {
                     nodes.append(defineSignal(activityNode));
                 } else if (((IAction) activityNode).isAcceptEventAction()) {
                     nodes.append(defineAccept(activityNode));
                 } else {//TODO else if value specification(new class)
                     nodes.append(defineAction(activityNode));    // create action node and set next action node
                 }
             } else if (activityNode instanceof IControlNode) {
                 if (((IControlNode) activityNode).isFinalNode()) {
                     nodes.append(defineFinalNode(activityNode)); // create final node and set next action node
                 } else if (((IControlNode) activityNode).isFlowFinalNode()) {
                     nodes.append(defineFlowFinal(activityNode)); // create flow final and set next action node
                 } else if (((IControlNode) activityNode).isInitialNode()) {
                     nodes.append(defineInitialNode(activityNode)); // create initial node and set next action node
                 } else if (((IControlNode) activityNode).isForkNode()) {
                	 nodes.append(defineFork(activityNode)); // create fork node and set next action node
                 } else if (((IControlNode) activityNode).isJoinNode()) {
                	 nodes.append(defineJoin(activityNode)); // create join node and set next action node
                 } else if (((IControlNode) activityNode).isDecisionNode()) {
                	 nodes.append(defineDecision(activityNode)); // create decision node and set next action node                          
                 }else if(((IControlNode) activityNode).isMergeNode()){
                 	nodes.append(defineMerge(activityNode)); // create merge node and set next action node
                 }
             } else if (activityNode instanceof IActivityParameterNode) {
                 if (activityNode.getOutgoings().length > 0) {
                	 nodes.append(defineInputParameterNode(activityNode));
                 } else if (activityNode.getIncomings().length > 0) {
                	 nodes.append(defineOutputParameterNode(activityNode));
                 } else {
                     activityNode = null;
                 }

             } else if (activityNode instanceof IObjectNode && !(activityNode instanceof IPin)) {
            	 nodes.append(defineObjectNode(activityNode));
             }
		}
    	
    	
    	
    	
    	
    	return nodes.toString();
    }

    private String defineAction(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dAction = new ADDefineAction(ad, alphabetNode, adUtils);

        return dAction.defineAction(activityNode);
    }

    private String defineFinalNode(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dFinalNode = new ADDefineFinalNode(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge, objectEdges, adUtils);

        return dFinalNode.defineFinalNode(activityNode);
    }

    private String defineInitialNode(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dInitialNode = new ADDefineInitialNode(ad, allInitial, alphabetAllInitialAndParameter, queueNode, syncChannelsEdge, adUtils,alphabetNode);

        return dInitialNode.defineInitialNode(activityNode);
    }

    private String defineCallBehaviour(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dCallBehavior = new ADDefineCallBehavior(ad, alphabetNode, adUtils);

        return dCallBehavior.defineCallBehaviour(activityNode);
    }

    private String defineFork(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dFork = new  ADDefineFork(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge, objectEdges, adUtils);

        return dFork.defineFork(activityNode);
    }

    private String defineJoin(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dJoin = new ADDefineJoin(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge,  objectEdges, adUtils);

        return dJoin.defineJoin(activityNode);
    }

    private String defineMerge(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dMerge = new ADDefineMerge(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge, objectEdges, parameterNodesInput,
                unionList, typeUnionList, adUtils);

        return dMerge.defineMerge(activityNode);
    }

    private String defineDecision(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dDecision = new ADDefineDecision(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge, objectEdges, adUtils);

        return dDecision.defineDecision(activityNode);
    }

    private String defineFlowFinal(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dFlowFinal = new ADDefineFlowFinal(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge, objectEdges, adUtils);

        return dFlowFinal.defineFlowFinal(activityNode);
    }

    private String defineInputParameterNode(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dInputParameterNode = new ADDefineInputParameterNode(ad, parameterAlphabetNode, syncObjectsEdge, objectEdges,
                allInitial, alphabetAllInitialAndParameter, adUtils,alphabetNode, parameterNodesInput);

        return dInputParameterNode.defineInputParameterNode(activityNode);
    }

    private String defineOutputParameterNode(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dOutputParameterNode = new ADDefineOutputParameterNode(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge,
                objectEdges,  adUtils, parameterNodesOutput);

        return dOutputParameterNode.defineOutputParameterNode(activityNode);
    }

    private String defineObjectNode(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dObjectNode = new ADDefineObjectNode(ad, alphabetNode, syncChannelsEdge, syncObjectsEdge, objectEdges, adUtils);

        return dObjectNode.defineObjectNode(activityNode);
    }

    private String defineSignal(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dSignal = new ADDefineSignal(ad, alphabetNode, countSignal, createdSignal,adUtils);

        return dSignal.defineSignal(activityNode);
    }

    private String defineAccept(IActivityNode activityNode) throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dAccept = new ADDefineAccept(ad, alphabetNode, countAccept, createdAccept, adUtils);

        return dAccept.defineAccept(activityNode);
    }

    private ADUtils defineADUtils() {
        ADUtils adUtils = new ADUtils(ad, adDiagram, countCall, eventChannel, lockChannel, parameterNodesOutputObject, callBehaviourNumber,
                memoryLocal,  memoryLocalChannel, callBehaviourInputs, callBehaviourOutputs, countSignal, countAccept,
                signalChannels, signalPins, localSignalChannelsSync, allGuards, createdSignal, createdAccept, syncChannelsEdge, syncObjectsEdge, objectEdges,
                signalChannelsLocal, parameterSignal, adParser);
        return adUtils;
    }
}
