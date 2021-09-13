package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;
import com.ref.interfaces.activityDiagram.IActivityNode;


public class ADParser {

    private IActivity ad;
    private IActivityDiagram adDiagram;

    public int countGet_ad;
    public int countSet_ad;
    public int countCe_ad;
    public int countOe_ad;
    public int countUpdate_ad;
    public int countClear_ad;
    public int limiteInf;
    public int limiteSup;

    private static String firstDiagram = null;
    public static boolean containsCallBehavior = false;
    public static HashMap<String, Integer> countCall = new HashMap<>();
    public static HashMap<String,List<Pair<String,String>>> countcallBehavior = new HashMap<>();
    private HashMap<Pair<IActivity,String>, ArrayList<String>> alphabetNode;
    private HashMap<Pair<IActivity,String>, ArrayList<String>> parameterAlphabetNode;
    public HashMap<Pair<IActivity,String>, String> syncChannelsEdge;            //ID flow, channel
    public HashMap<Pair<IActivity,String>, String> syncObjectsEdge;
    public static Set<String> alphabetPool = new HashSet<String>();
    public static List<IActivity> callBehaviourList = new ArrayList<>();
    private HashMap<String, String> objectEdges;                //channel; name
    private List<IActivityNode> queueNode;
    private List<IActivityNode> queueRecreateNode;
    private static List<IActivity> callBehaviourListCreated = new ArrayList<>();
    private List<String> eventChannel;
    private List<String> lockChannel;
    private List<String> allInitial;
    private ArrayList<String> alphabetAllInitialAndParameter;
    private HashMap<String, String> parameterNodesInput;        //name; type
    private HashMap<String, String> parameterNodesOutput;
    private HashMap<String, String> parameterNodesOutputObject; //name; object
    private List<Pair<String, Integer>> callBehaviourNumber;     //name; int
    private Map<Pair<String, String>,String> memoryLocal;             //nameNode, nameObject
    private List<Pair<String, String>> memoryLocalChannel;
    private List<ArrayList<String>> unionList;
    private HashMap<String, String> typeUnionList;
    private static HashMap<String, List<String>> callBehaviourInputs = new HashMap<>(); //name; List inputs
    private static HashMap<String, List<String>> callBehaviourOutputs = new HashMap<>(); //name; List outputs
    private static List<Pair<String, Integer>> countSignal = new ArrayList<>();
    private static List<Pair<String, Integer>> countAccept = new ArrayList<>();
    private static HashMap<String,List<IActivity>> signalChannels = new HashMap<>();
    private static HashMap<String,List<String>> signalPins = new HashMap<>();
    private List<String> signalChannelsLocal;
    private List<String> localSignalChannelsSync;
    private List<String> createdSignal;
    private List<String> createdAccept;
    private HashMap<String,Integer> allGuards;
    public static HashMap<String,Integer> IdSignals = new HashMap<>();
    private static HashMap<String, Pair<String, String>> parameterSignal = new HashMap<>();//TODO ADUtils alimentar isso
    
    private ADAlphabet alphabetAD;
    public ADDefineChannels dChannels;
    public ADDefineTypes dTypes;
    public ADDefineMemories dMemories;
    public ADDefineMainNodes dMainNodes;
    public ADDefineNodesActionAndControl dNodesActionAndControl;
    public ADDefineTokenManager dTokenManager;
    public ADDefineProcessSync dProcessSync;
    public ADDefinePool dPool;

    public static HashMap<String,String> Definitions = new HashMap<>();
    public static String GlobalDefinition = "";
    public List<String> primitivesUsed = new ArrayList<>();
    public static final List<String> primitives = Collections.unmodifiableList(
    		new ArrayList<String>() {{
    			add("Bool");
    			add("Complex");
    			add("Int");
    			add("Real");
    			add("String");
    			add("Number");
    			add("UnlimitedNatural");
    			add("Float");
    			add("Rational");
    		}}
    		);
    
    public ADParser(IActivity ad, String nameAD, IActivityDiagram adDiagram) {
        this.ad = ad;
        this.adDiagram = adDiagram;
        setName(nameAD);
        setFirstDiagram();
        this.countGet_ad = 1;
        this.countSet_ad = 1;
        this.countCe_ad = 1;
        this.countOe_ad = 1;
        this.countUpdate_ad = 1;
        this.countClear_ad = 1;
        this.limiteInf = 99;
        this.limiteSup = -99;
        this.alphabetNode = new HashMap<>();
        this.parameterAlphabetNode = new HashMap<>();
        syncChannelsEdge = new HashMap<>();
        syncObjectsEdge = new HashMap<>();
        objectEdges = new HashMap<>();
        queueNode = new ArrayList<>();
        queueRecreateNode = new ArrayList<>();
        eventChannel = new ArrayList<>();
        lockChannel = new ArrayList<>();
        allInitial = new ArrayList<>();
        alphabetAllInitialAndParameter = new ArrayList<>();
        parameterNodesInput = new HashMap<>();
        parameterNodesOutput = new HashMap<>();
        parameterNodesOutputObject = new HashMap<>();
        memoryLocal = new HashMap<Pair<String,String>,String>();
        memoryLocalChannel = new ArrayList<>();
        unionList = new ArrayList<>();
        typeUnionList = new HashMap<>();
        callBehaviourNumber = new ArrayList<>();
        localSignalChannelsSync = new ArrayList<>();
        signalChannelsLocal = new ArrayList<>();
        createdSignal = new ArrayList<>();
        createdAccept = new ArrayList<>();
        allGuards = new HashMap<>();
    }

    private void setFirstDiagram() {
        if (firstDiagram == null) {
            firstDiagram = ad.getId();
        }
    }

    public void checkCountCallInitial() {
        ADUtils adUtils = defineADUtils();

        if (countCall.get(adUtils.nameDiagramResolver(ad.getName())) == null) {
            adUtils.addCountCall(adUtils.nameDiagramResolver(ad.getName()));
        }
    }

    public void clearBuffer() {
        this.countGet_ad = 1;
        this.countSet_ad = 1;
        this.countCe_ad = 1;
        this.countOe_ad = 1;
        this.countUpdate_ad = 1;
        this.countClear_ad = 1;
        this.limiteInf = 99;
        this.limiteSup = -99;
        this.alphabetNode = new HashMap<>();
        this.parameterAlphabetNode = new HashMap<>();
        syncChannelsEdge = new HashMap<>();
        syncObjectsEdge = new HashMap<>();
        objectEdges = new HashMap<>();
        queueNode = new ArrayList<>();
        queueRecreateNode = new ArrayList<>();
        eventChannel = new ArrayList<>();
        lockChannel = new ArrayList<>();
        allInitial = new ArrayList<>();
        alphabetAllInitialAndParameter = new ArrayList<>();
        parameterNodesInput = new HashMap<>();
        parameterNodesOutput = new HashMap<>();
        parameterNodesOutputObject = new HashMap<>();
        memoryLocal = new HashMap<Pair<String,String>,String>();
        memoryLocalChannel = new ArrayList<>();
        unionList = new ArrayList<>();
        typeUnionList = new HashMap<>();
        callBehaviourInputs = new HashMap<>();
        callBehaviourNumber = new ArrayList<>();
        localSignalChannelsSync = new ArrayList<>();
        signalChannelsLocal = new ArrayList<>();
        createdSignal = new ArrayList<>();
        createdAccept = new ArrayList<>();
        resetStatic();
        allGuards = new HashMap<>();
        parameterSignal = new HashMap<>();
        firstDiagram = ad.getId(); //set first diagram
    }

    public void resetStatic() {
        firstDiagram = null;
        containsCallBehavior = false;
        countCall = new HashMap<>();
        countcallBehavior = new HashMap<>();
        callBehaviourList = new ArrayList<>();
        callBehaviourInputs = new HashMap<>();
        callBehaviourOutputs = new HashMap<>();
        callBehaviourListCreated = new ArrayList<>();
        countSignal = new ArrayList<>();
        countAccept = new ArrayList<>();
        signalChannels = new HashMap<>();
        signalPins = new HashMap<>();
        alphabetPool = new HashSet<String>();
        primitivesUsed = new ArrayList<>();
        Definitions = new HashMap<>();
        GlobalDefinition = "";
    }

    private void setName(String nameAD) {
        this.ad.setName(nameAD);
    }

    /*
     * Master Function
     * */

    public String parserDiagram() throws ParsingException {
    	
        boolean reset = false;
        String check = "";
        String callBehaviour = "";

        if (countCall.size() == 0) { //If first occurrence
            check = "\nassert MAIN :[deadlock free]" +
                    "\nassert MAIN :[divergence free]" +
                    "\nassert MAIN :[deterministic]";
            reset = true;
        }
        
        addDefinitionToGlobalDefinition();
        
        defineCallBehaviourList();
        
        checkCountCallInitial();
        
        definePoolAlphabet();

        String nodes = defineNodesActionAndControl();

        
        for (IActivity adCalling: callBehaviourList) {
            if (!callBehaviourListCreated.contains(adCalling)) {
                callBehaviourListCreated.add(adCalling);
                ADParser adParser = new ADParser(adCalling, adCalling.getActivityDiagram().getName(), adCalling.getActivityDiagram());
                callBehaviour += "\n" + (adParser.parserDiagram());
                alphabetAD = new ADCompositeAlphabet(ad);
                this.alphabetAD.add(adParser.getAlphabetAD());
            }
        }

        String channel = defineChannels();
        String main = defineMainNodes();
        String type = defineTypes();
        String tokenManager = defineTokenManager();
        String memory = defineMemories();
        String processSync = defineProcessSync();
        String pool = definePool();

        String parser = (firstDiagram.equals(ad.getId())?"transparent normal\n":"")+ 
        		type +
                channel +
                main +
                processSync +
                nodes +
                memory +
                tokenManager +
                /*lock +*/
                pool +            
                (firstDiagram.equals(ad.getId())?"\nAlphabetPool = {|endDiagram_"+ADUtils.nameResolver(ad.getName())+(!alphabetPool.isEmpty()?","+alphabetPoolToString():"")+"|}\n":"")+
                callBehaviour +
                check;

        //reset the static values
        if (reset) {
        	//generate primitives used
        	parser += generatePrimitives();
        	
            resetStatic();
        }

        //alphabet used on the counterexample
        if(alphabetAD == null) {
        	this.alphabetAD = new ADLeafAlphabet(ad);
        }
        alphabetAD.setAlphabetAD(alphabetNode);
        alphabetAD.setSyncChannelsEdge(syncChannelsEdge);
        alphabetAD.setSyncObjectsEdge(syncObjectsEdge);
        alphabetAD.setParameterAlphabetNode(parameterAlphabetNode);
        
        return parser;
    }


	private void addDefinitionToGlobalDefinition() {
		String adDefinition = this.ad.getDefinition();
		if(!adDefinition.equals("")) {
			String[] defSplit = adDefinition.split("\n");
			for(String lineSplit : defSplit) {
				String[] lineSplited = lineSplit.split("=");
				if(!ADParser.Definitions.containsKey(lineSplited[0])) {
					ADParser.Definitions.put(lineSplited[0], lineSplited[1]);
					ADParser.GlobalDefinition += lineSplited[0] +"="+ lineSplited[1] +"\n";
				}
			}
		}
		
	}
	
	public static String[] getDefinitionReplaced() {
		String globalDef = ADParser.GlobalDefinition;
		String[] globalDefLines = globalDef.split("\n");
		String[] globalDefReplaced = new String [0];
		ArrayList<String> lines = new ArrayList<>();
		for(String globalDefLine : globalDefLines) {
			if(!globalDefLine.startsWith("datatype")) {
				//.replace("\n", "").replace(" ", "").split(";");
				lines.add(globalDefLine.replace("\n", "").replace(" ", "").replace(";",""));
			}else {
				int index = globalDefLine.lastIndexOf(";");
				StringBuilder line = new StringBuilder(globalDefLine);
				if(line.length()-1 == index) {
					line.deleteCharAt(index);
					lines.add(line.toString().replace("\n", "").replace(" ", ""));
				}else {
					lines.add(line.toString().replace("\n", "").replace(" ", ""));
				}
			
			}
		}
		globalDefReplaced = lines.toArray(new String[0]);
		return globalDefReplaced;
	}
	private String generatePrimitives() {
		String primitivesDefinition = "\n";
		Set<String> set = new HashSet<>(primitivesUsed);
		primitivesUsed.clear();
		primitivesUsed.addAll(set);
		
		for(String primitive: primitivesUsed) {
			switch(primitive) {
			case "Bool":
				primitivesDefinition +="Bool = true | false\n";
				break;
				
			case "Complex":
				primitivesDefinition +="Complex = {1..1}\n";
				break;
				
			case "Int":
				primitivesDefinition +="Int = {1..1}\n";
				break;
				
			case "Real":
				primitivesDefinition +="Real = {1..1}\n";
				break;
				
			case "String":
				primitivesDefinition +="String = {1..1}\n";
				break;
				
			case "Number":
				primitivesDefinition +="Number = {1..1}\n";
				break;
				
			case "UnlimitedNatural":
				primitivesDefinition +="UnlimitedNatural = {1..1}\n";
				break;
				
			case "Float":
				primitivesDefinition +="Float = {1..1}\n";
				break;
				
			case "Rational":
				primitivesDefinition +="Rational = {1..1}\n";
				break;
				
					
			}
		}
		return primitivesDefinition;
	}

	public ADAlphabet getAlphabetAD() {
		return this.alphabetAD;
	}

	public static void addCountCallBehavior(String idKey,String idCalling,String nameCalling) {
        int i = 1;
        List<Pair<String,String>> aux1;
        if (countcallBehavior.containsKey(idKey)) {//If already has the CBA
            aux1 = countcallBehavior.get(idKey);
            List<String>aux2 = new ArrayList<String>();
            for(Pair<String,String> a:aux1) {//get the ids
            	aux2.add(a.getKey());
            }
            for(String diagram:aux2) {//sweeps throught the calling diagram 
            	if(diagram.equals(idCalling)){
            		break;
            	}
            	i++;
            }
            if(i > aux1.size()) {//If it isn't there, add it
            	Pair<String,String> pair = new Pair<String, String>(idCalling, nameCalling);
            	aux1.add(pair);
            	countcallBehavior.replace(idKey,aux1);	
            }
        } else {//IF the CBA doesn't exist
        	aux1 = new ArrayList<Pair<String,String>>();
        	Pair<String,String> pair = new Pair<String, String>(idCalling, nameCalling);
        	aux1.add(pair);
            countcallBehavior.put(idKey, aux1);
        }
    }
    
    private void defineCallBehaviourList() throws ParsingException {
    	if(countCall.size() == 0) {//Gets the CBAs of the first diagram
        	for (IActivityNode activityNode : ad.getActivityNodes()) {//Get all nodes
        		if (activityNode instanceof IAction) {
                    if (((IAction) activityNode).isCallBehaviorAction()) {
                    	if(((IAction) activityNode).getCallingActivity() == null) {
                    		throw new ParsingException("Call Behavior Action "+activityNode.getName() +" not linked\n");
                    	}else {
                    		if(!containsCBA(((IAction) activityNode).getCallingActivity())) {
		                		callBehaviourList.add(((IAction) activityNode).getCallingActivity());
                    		}
                    		addCountCallBehavior(((IAction) activityNode).getCallingActivity().getId(), activityNode.getId(),activityNode.getName());
	                		
                    	}
                    }
        		}
        	}
        	
        	boolean mudou =true;
        	List<IActivity> aux1 = new ArrayList<>();
        	List<IActivity> aux3 = new ArrayList<>();
        	aux3.addAll(callBehaviourList);
        	
        	while(mudou) {
	        	if(callBehaviourList.size() != 0) {//Gets the CBA that is inside a CBA
	        		for(IActivity CBAs: callBehaviourList) {//For each CBA
	        			for(IActivityNode adCBAs: CBAs.getActivityNodes()) {//For each node
	        				if(adCBAs instanceof IAction) {
	        					if(((IAction) adCBAs).isCallBehaviorAction() && !aux1.contains(((IAction) adCBAs).getCallingActivity())) {//se for CBA e n tiver sido add
	        						aux1.add(((IAction) adCBAs).getCallingActivity());
	        					}
	        					if(((IAction) adCBAs).isCallBehaviorAction()) {
	        						addCountCallBehavior(((IAction) adCBAs).getCallingActivity().getId(), adCBAs.getId(),adCBAs.getName());
	        					}
	        				}
	        			}
	        		}
	        	}
	        	
	        	for(IActivity CBA:aux1) {//Joins the sets
	        		if(!containsCBA(CBA)) {
	        			callBehaviourList.add(CBA);
	        		}
	        	}
	        	if(aux3.equals(callBehaviourList)) {
	        		mudou = false;
	        	}else {
	        		aux3 = callBehaviourList;
	        	}
        	}
        }
	}

	private boolean containsCBA(IActivity callingActivity) {
		for(IActivity activity: callBehaviourList) {
			if(callingActivity.getId().equals(activity.getId())) {
				return true;
			}
		}
		return false;
	}

	private void definePoolAlphabet() {
		if(firstDiagram.equals(ad.getId())) {//Gets from the first diagram
        	for (IActivityNode activityNode : ad.getActivityNodes()) {//Gets all nodes
        		if (activityNode instanceof IAction) {
                    if (((IAction) activityNode).isAcceptEventAction()||((IAction) activityNode).isSendSignalAction()) {//If is accept or send
                    	alphabetPool.add("signal_"+((IAction) activityNode).getName());
    					alphabetPool.add("accept_"+((IAction) activityNode).getName());
                    }
        		}
        	}
        	for(IActivity CBAs: callBehaviourList) {
        		for(IActivityNode sendOrAcceptSignal: CBAs.getActivityNodes()) {
        			if(sendOrAcceptSignal instanceof IAction) {
        				if(((IAction)sendOrAcceptSignal).isAcceptEventAction() ||((IAction)sendOrAcceptSignal).isSendSignalAction()) {
        					alphabetPool.add("signal_"+((IAction) sendOrAcceptSignal).getName());
        					alphabetPool.add("accept_"+((IAction) sendOrAcceptSignal).getName());
        				}
        			}
        		}
        	}
		}
	}
	
	private String alphabetPoolToString() {
		StringBuilder toString = new StringBuilder();
		String aux = "";
		for(String a:alphabetPool) {
			toString.append(a+",");
		}
		toString.replace(toString.lastIndexOf(","), toString.lastIndexOf(",")+1, "");
		aux = toString.toString();
		return aux.replace(" ", "").replace("!", "_").replace("@", "_")
                .replace("%", "_").replace("&", "_").replace("*", "_")
                .replace("(", "_").replace(")", "_").replace("+", "_")
                .replace("-", "_").replace("=", "_").replace("?", "_")
                .replace(":", "_").replace("/", "_").replace(";", "_")
                .replace(">", "_").replace("<", "_").replace("{", "_")
                .replace("}", "_").replace("|", "_").replace("\\", "_")
                .replace("\n", "_");
	}
    
    private ADUtils defineADUtils() {
        ADUtils adUtils = new ADUtils(ad, adDiagram, countCall, eventChannel, lockChannel, parameterNodesOutputObject, callBehaviourNumber,
                memoryLocal,  memoryLocalChannel, callBehaviourInputs, callBehaviourOutputs, countSignal, countAccept,
                signalChannels,signalPins, localSignalChannelsSync, allGuards, createdSignal, createdAccept, syncChannelsEdge, syncObjectsEdge, objectEdges,
                signalChannelsLocal, parameterSignal, this);
        return adUtils;
    }

    public String defineChannels() throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dChannels = new ADDefineChannels(allGuards, ad, parameterNodesInput, parameterNodesOutput,
                memoryLocal, parameterNodesOutputObject, syncObjectsEdge, objectEdges,
                eventChannel, lockChannel, firstDiagram, signalChannels,signalPins, parameterSignal, adUtils, this);

        return dChannels.defineChannels();
    }

    public String defineTypes() {
        ADUtils adUtils = defineADUtils();

        dTypes = new ADDefineTypes(ad, adDiagram, firstDiagram, countCall, alphabetNode, objectEdges, parameterNodesInput,
                parameterNodesOutput, memoryLocalChannel, unionList, typeUnionList, countSignal, countAccept, adUtils, this);

        return dTypes.defineTypes();
    }

    public String defineMemories() {
        ADUtils adUtils = defineADUtils();

        dMemories = new ADDefineMemories(ad, parameterNodesInput, parameterNodesOutput, memoryLocal, adUtils);

        return dMemories.defineMemories();
    }

    public String defineNodesActionAndControl() throws ParsingException {
        ADUtils adUtils = defineADUtils();

        dNodesActionAndControl = new ADDefineNodesActionAndControl(ad, adDiagram, countCall, alphabetNode, parameterAlphabetNode,
                syncChannelsEdge, syncObjectsEdge, objectEdges, queueNode, queueRecreateNode, callBehaviourList, eventChannel,
                lockChannel, allInitial, alphabetAllInitialAndParameter, parameterNodesInput, parameterNodesOutput, parameterNodesOutputObject,
                callBehaviourNumber, memoryLocal, memoryLocalChannel, unionList, typeUnionList, callBehaviourInputs, callBehaviourOutputs,
                countSignal, countAccept, signalChannels,signalPins, localSignalChannelsSync, createdSignal, createdAccept, allGuards, signalChannelsLocal,
                parameterSignal, adUtils, this);

        return dNodesActionAndControl.defineNodes();
    }

    public String defineTokenManager() {
        ADUtils adUtils = defineADUtils();

        dTokenManager = new ADDefineTokenManager(ad, adUtils);

        return dTokenManager.defineTokenManager();
    }

    public String definePool() {
        ADUtils adUtils = defineADUtils();

        dPool = new ADDefinePool(ad, signalChannels,signalPins, firstDiagram, countAccept, adUtils);

        return dPool.definePool();
    }

    public String defineProcessSync() {
        ADUtils adUtils = defineADUtils();

        dProcessSync = new ADDefineProcessSync(ad, alphabetNode, adUtils);

        return dProcessSync.defineProcessSync();
    }

    public String defineMainNodes() {
        ADUtils adUtils = defineADUtils();

        dMainNodes = new ADDefineMainNodes(ad, firstDiagram, lockChannel, parameterNodesInput, parameterNodesOutput, callBehaviourNumber,
                callBehaviourInputs, localSignalChannelsSync, signalChannelsLocal, adUtils, this);

        return dMainNodes.defineMainNodes();
    }

	
}