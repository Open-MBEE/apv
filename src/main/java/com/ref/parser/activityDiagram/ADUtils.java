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
import com.ref.interfaces.activityDiagram.IControlFlow;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IObjectFlow;
import com.ref.interfaces.activityDiagram.IOutputPin;

public class ADUtils {

    private IActivity ad;
    private IActivityDiagram adDiagram;

    public HashMap<String, Integer> countCall;
    private List<String> eventChannel;
    private List<String> lockChannel;
    private HashMap<String, String> parameterNodesOutputObject;
    private List<Pair<String, Integer>> callBehaviourNumber;
    private Map<Pair<String, String>,String> memoryLocal;
    private List<Pair<String, String>> memoryLocalChannel;
    private HashMap<String, List<String>> callBehaviourInputs;
    private HashMap<String, List<String>> callBehaviourOutputs;
    private List<Pair<String, Integer>> countSignal;
    private List<Pair<String, Integer>> countAccept;
    private HashMap<String, List<IActivity>> signalChannels;
    private HashMap<String, List<String>> signalPins;
    private List<String> signalChannelsLocal;
    private List<String> localSignalChannelsSync;
    private HashMap<String,Integer> allGuards;
    public HashMap<Pair<IActivity,String>, String> syncChannelsEdge;
    public HashMap<Pair<IActivity,String>, String> syncObjectsEdge;
    private HashMap<String, String> objectEdges;
    private ADParser adParser;
    private HashMap<String, Pair<String,String>> parameterSignal;

    public ADUtils(IActivity ad, IActivityDiagram adDiagram, HashMap<String, Integer> countCall, List<String> eventChannel,
                   List<String> lockChannel, HashMap<String, String> parameterNodesOutputObject, List<Pair<String, Integer>> callBehaviourNumber,
                   Map<Pair<String, String>,String> memoryLocal, List<Pair<String, String>> memoryLocalChannel, HashMap<String, List<String>> callBehaviourInputs,
                   HashMap<String, List<String>> callBehaviourOutputs, List<Pair<String, Integer>> countSignal, List<Pair<String, Integer>> countAccept,
                   HashMap<String, List<IActivity>> signalChannels2, HashMap<String, List<String>> signalPins2, List<String> localSignalChannelsSync, HashMap<String, Integer> allGuards,
                   List<String> createdSignal, List<String> createdAccept, HashMap<Pair<IActivity, String>, String> syncChannelsEdge2,
                   HashMap<Pair<IActivity, String>, String> syncObjectsEdge2, HashMap<String, String> objectEdges2, List<String> signalChannelsLocal,
                   HashMap<String, Pair<String, String>> parameterSignal, ADParser adParser) {

        this.ad = ad;
        this.adDiagram = adDiagram;
        this.countCall = countCall;
        this.eventChannel = eventChannel;
        this.lockChannel = lockChannel;
        this.parameterNodesOutputObject = parameterNodesOutputObject;
        this.callBehaviourNumber = callBehaviourNumber;
        this.memoryLocal = memoryLocal;
        this.memoryLocalChannel = memoryLocalChannel;
        this.callBehaviourInputs = callBehaviourInputs;
        this.callBehaviourOutputs = callBehaviourOutputs;
        this.countSignal = countSignal;
        this.countAccept = countAccept;
        this.signalChannels = signalChannels2;
        this.signalPins = signalPins2;
        this.localSignalChannelsSync = localSignalChannelsSync;
        this.allGuards = allGuards;
        this.syncChannelsEdge = syncChannelsEdge2;
        this.syncObjectsEdge = syncObjectsEdge2;
        this.signalChannelsLocal = signalChannelsLocal;
        this.objectEdges = objectEdges2;
        this.parameterSignal = parameterSignal;
        this.adParser = adParser;
    }

    public String createCE() {
        return "ce_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countCe_ad++;
    }

    public String createOE() {
        return "oe_" + adParser.countOe_ad++ + "_" + nameDiagramResolver(ad.getName()) + ".id" ;
    }

    
    
    public int startActivity(ArrayList<String> alphabetNode, StringBuilder action, String nameAD, List<String> inputPins) {
        int count = 0;
        count = addCountCall(nameDiagramResolver(nameAD));
        String startActivity = "startActivity_" + nameDiagramResolver(nameAD) + "." + count;
        alphabetNode.add(startActivity);
        callBehaviourNumber.add(new Pair<>(nameDiagramResolver(nameAD), count));

        List<String> outputPinsUsed = callBehaviourInputs.get(nameDiagramResolver(nameAD));
        if (outputPinsUsed == null) {
            outputPinsUsed = inputPins;
            callBehaviourInputs.put(nameAD, inputPins);
        }

        for (String pin : outputPinsUsed) {
            startActivity += "!" + pin;
        }

        action.append(startActivity + " -> ");

        return count;
    }

    public void endActivity(ArrayList<String> alphabetNode, StringBuilder action, String nameAD, List<String> outputPins, int count) {
        String endActivity = "endActivity_" + nameDiagramResolver(nameAD) + "." + count;
        alphabetNode.add(endActivity);

        List<String> outputPinsUsed = callBehaviourOutputs.get(nameDiagramResolver(nameAD));
        if (outputPinsUsed == null) {
            outputPinsUsed = outputPins;
            callBehaviourOutputs.put(nameAD, outputPins);
        }

        for (String pin : outputPinsUsed) {
            endActivity += "?" + pin;
        }

        action.append(endActivity + " -> ");
    }

    public void get(ArrayList<String> alphabetNode, StringBuilder action, String nameObject) {
        String get = "get_" + nameObject + "_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countGet_ad++;
        alphabetNode.add(get);
        action.append(get + "?" + nameObject + " -> ");
    }

    public void set(ArrayList<String> alphabetNode, StringBuilder action, String nameMemory, String nameObject) {
        String set = "set_" + nameMemory + "_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countSet_ad++;
        alphabetNode.add(set);
        action.append(set +"!" + nameObject + " -> ");
        parameterNodesOutputObject.put(nameMemory, nameObject);
    }

    public void setLocal(ArrayList<String> alphabetNode, StringBuilder action, String nameObject, String nameNode, String data, String datatype) {
        String set = "set_" + nameObject + "_" + nameNode + "_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countSet_ad++;
        alphabetNode.add(set);
        action.append(set + "!" + data + " -> ");
        Pair<String, String> memoryLocalPair = new Pair<String, String>(nameNode, nameObject);
        if (!memoryLocal.keySet().contains(memoryLocalPair)) {
            memoryLocal.put(memoryLocalPair,datatype);
        }
    }

    public void getLocal(ArrayList<String> alphabetNode, StringBuilder action, String nameObject, String nameNode, String data, String datatype) {
        String get = "get_" + nameObject + "_" + nameNode + "_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countGet_ad++;
        alphabetNode.add(get);
        action.append(get + "?" + data + " -> ");
        Pair<String, String> memoryLocalPair = new Pair<String, String>(nameNode, nameObject);
        if (!memoryLocal.keySet().contains(memoryLocalPair)) {
        	memoryLocal.put(memoryLocalPair,datatype);
        }
    }

    public void setLocalInput(ArrayList<String> alphabetNode, StringBuilder action, String nameObject, String nameNode, String data, String oeIn, String datatype) {
        String set = "set_" + nameObject + "_" + nameNode + "_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countSet_ad++;
        alphabetNode.add(set);
        action.append(set + "!" + data + " -> ");
        Pair<String, String> memoryLocalPair = new Pair<String, String>(nameNode, nameObject);
        memoryLocalChannel.add(new Pair<String, String>(oeIn, nameObject));

        if (!memoryLocal.keySet().contains(memoryLocalPair)) {
        	memoryLocal.put(memoryLocalPair,datatype);
        }
    }

    public void lock(ArrayList<String> alphabetNode, StringBuilder action, int inOut, String nameNode) {
        if (ADParser.containsCallBehavior) {
            if (inOut == 0) {
                String lock = "lock_" + nameNode;
                alphabetNode.add(lock);
                lockChannel.add(nameNode);
                action.append(lock + ".id.lock -> ");
            } else {
                String lock = "lock_" + nameNode;
                action.append(lock + ".id.unlock -> ");
            }
        }
    }

    public void event(ArrayList<String> alphabet, String nameAction, StringBuilder action) {
        alphabet.add("event_" + nameAction+".id");
        eventChannel.add("event_" + nameAction);
        action.append("event_" + nameAction + ".id -> ");
    }

    public void ce(ArrayList<String> alphabetNode, StringBuilder action, String ce, String posCe) {
        alphabetNode.add(ce);
        action.append(ce + posCe);
    }

    public void oe(ArrayList<String> alphabetNode, StringBuilder action, String oe, String data, String posOe) {
        alphabetNode.add(oe);
        action.append(oe + data + posOe);
    }

    public void update(ArrayList<String> alphabetNode, StringBuilder action, int countInFlows, int countOutFlows, boolean canBeNegative) {
        int result = countOutFlows - countInFlows;

        if (result != 0) {
            if (countOutFlows == 0 && canBeNegative || countOutFlows > 0) {
                String update = "update_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countUpdate_ad++;
                alphabetNode.add(update);
                action.append(update + "!(" + countOutFlows + "-" + countInFlows + ") -> ");

                if (result < adParser.limiteInf) {
                    adParser.limiteInf = result;

                    if (adParser.limiteSup == -99) {
                        adParser.limiteSup = result;
                    }

                }

                if (result > adParser.limiteSup) {
                    adParser.limiteSup = result;

                    if (adParser.limiteInf == 99) {
                        adParser.limiteInf = result;
                    }

                }
            }
        }

    }

    public void clear(ArrayList<String> alphabetNode, StringBuilder action) {
        String clear = "clear_" + nameDiagramResolver(ad.getName()) + ".id." + adParser.countClear_ad++;
        alphabetNode.add(clear);
        action.append(clear + " -> ");
    }

    public List<String> replaceExpression(String expression) {
        String value = "";
        char[] expChar = expression.toCharArray();
        List<String> expReplaced = new ArrayList<>();
        for (int i = 0; i < expChar.length; i++) {
            if (expChar[i] == '+' || expChar[i] == '-' || expChar[i] == '*' || expChar[i] == '/') {
                expReplaced.add(value);
                value = "";
            } else {
                value += expChar[i];
            }
        }

        if (!value.equals("")) {    //add last value
            expReplaced.add(value);
        }

        return expReplaced;
    }

    public List<String> getObjects(IFlow flow, List<String> nodes) {
        List<String> objects = new ArrayList<>();

        if (!nodes.contains(flow.getSource().getId())) {
            nodes.add(flow.getSource().getId());
            if (flow.getSource() instanceof IActivityParameterNode) {
                objects.add(flow.getSource().getName());
            } else if (flow.getSource() instanceof IOutputPin) {
                IInputPin[] inPins = ((IAction) ((IOutputPin)flow.getSource()).getOwner()).getInputs();
                for (int x = 0; x < inPins.length; x++) {
                    for (IFlow flowNode : inPins[x].getIncomings()) {
                        objects.addAll(getObjects(flowNode, nodes));
                    }
                }
            } else {
                for (IFlow flowNode : flow.getSource().getIncomings()) {
                    objects.addAll(getObjects(flowNode, nodes));
                }
            }
        }

        return objects;
    }

    public void signal(ArrayList<String> alphabet, String nameSignal, StringBuilder signal, IActivityNode activityNode, List<String> namesMemoryLocal, HashMap<String, String> typeMemoryLocal) {
        if (!localSignalChannelsSync.contains("signal_" + nameSignal)) {
            localSignalChannelsSync.add("signal_" + nameSignal);
        }

        if (!signalChannels.containsKey(nameSignal)) {
        	List<IActivity> list = new ArrayList<>();
        	list.add(ad);
            signalChannels.put(nameSignal,list );
            if(((IAction)activityNode).getInputs().length>0) {
            	List<String> types = new ArrayList<>();
            	for(IInputPin inputPin :((IAction)activityNode).getInputs()) {
            		types.add(inputPin.getBase().getName());
            	}
            	signalPins.put(nameSignal, types);
            }
            
        }
        
        if (!signalChannelsLocal.contains(nameSignal)) {
            signalChannelsLocal.add(nameSignal);
        }

        int idSignal = 1;
        int index = -1;

        for (int i = 0; i < countSignal.size(); i++) {
            if (countSignal.get(i).getKey().equals(nameSignal)) {
                idSignal = countSignal.get(i).getValue();
                index = i;
                break;
            }
        }

        alphabet.add("signal_" + nameSignal + ".id." + idSignal);
        
        if (namesMemoryLocal.size() > 0) {
        	signal.append("signal_" + nameSignal + ".id!" + idSignal + "!" + namesMemoryLocal.get(0) + " -> ");
        	Pair<String, String> newPair = new Pair<String, String>(namesMemoryLocal.get(0), typeMemoryLocal.get(namesMemoryLocal.get(0)));
        	//parameterSignal.put(nameSignal, namesMemoryLocal.get(0));
        	parameterSignal.put(nameSignal, newPair);
        	
        } else {
        	signal.append("signal_" + nameSignal + ".id!" + idSignal + " -> ");   
        }
        

        if (index >= 0) {
            countSignal.set(index, new Pair<String, Integer>(nameSignal, idSignal + 1));
            ADParser.IdSignals.put(activityNode.getId(),idSignal);
            
        } else {
            countSignal.add(new Pair<String, Integer>(nameSignal, idSignal + 1));
            ADParser.IdSignals.put(activityNode.getId(),idSignal);
        }

    }

    public void accept(ArrayList<String> alphabet, String nameAccept, StringBuilder accept, IActivityNode activityNode, String nodeName, IOutputPin[] outPins) {
        if (!localSignalChannelsSync.contains("signal_" + nameAccept)) {
            localSignalChannelsSync.add("signal_" + nameAccept);
        }

        if (!signalChannels.containsKey(nameAccept)) {
        	List<IActivity> list = new ArrayList<>();
        	list.add(ad);
            signalChannels.put(nameAccept,list );
            if(((IAction)activityNode).getOutputs().length>0) {
            	List<String> types = new ArrayList<>();
            	for(IOutputPin outputPin :((IAction)activityNode).getOutputs()) {
            		types.add(outputPin.getBase().getName());
            	}
            	signalPins.put(nameAccept, types);
            }
        }

        int idAccept = 1;
        int index = -1;

        for (int i = 0; i < countAccept.size(); i++) {
            if (countAccept.get(i).getKey().equals(nameAccept)) {
                idAccept = countAccept.get(i).getValue();
                index = i;
                break;
            }
        }

        alphabet.add("accept_" + nameAccept + ".id." + idAccept);
        
        if (outPins.length > 0) {
        	accept.append("accept_" + nameAccept + ".id." + idAccept + "?" + nodeName + "?" + nodeName + "_" + outPins[0].getName() + " -> ");
        	
        } else {
        	accept.append("accept_" + nameAccept + ".id." + idAccept + "?" + nodeName + " -> ");
        }

        if (index >= 0) {
            countAccept.set(index, new Pair<String, Integer>(nameAccept, idAccept + 1));
            ADParser.IdSignals.put(activityNode.getId(),idAccept);
        } else {
            countAccept.add(new Pair<String, Integer>(nameAccept, idAccept + 1));
            ADParser.IdSignals.put(activityNode.getId(),idAccept);
        }
    }

    public String nameDiagramResolver(String name) {
        return name.replace(" ", "").replace("!", "_").replace("@", "_")
                .replace("%", "_").replace("&", "_").replace("*", "_")
                .replace("(", "_").replace(")", "_").replace("+", "_")
                .replace("-", "_").replace("=", "_").replace("?", "_")
                .replace(":", "_").replace("/", "_").replace(";", "_")
                .replace(">", "_").replace("<", "_").replace(",", "_")
                .replace("{", "_").replace("}", "_").replace("|", "_")
                .replace("\\", "_").replace("\n", "_");
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

    public int addCountCall(String name) {
        int i = 1;
        if (countCall.containsKey(name)) {
            i = countCall.get(name);
            countCall.put(name, ++i);
        } else {
            countCall.put(name, i);
        }
        return i;
    }

    public int addCountGuard(String guard) {
        int i = 1;
        if (allGuards.containsKey(guard)) {
            i = allGuards.get(guard);
            allGuards.put(guard, ++i);
        } else {
            allGuards.put(guard, i);
        }

        return i;
    }

    public String getDefaultValue(String type) {
        HashMap<String, String> typesParameter = getParameterValueDiagram(type);

        String defaultValue = typesParameter.get(type).replace("{", "").replace("}", "").replace("(", "")
                .replace(")", "").split(",")[0];
        String defaultValueFinal = defaultValue.split("\\.\\.")[0];
        /*if(defaultValueFinal.contains(":")) {//se for datatype
        	String[] dtValues = defaultValueFinal.split(":");
        	defaultValueFinal = dtValues[0];
        }*/
        if(defaultValueFinal.contains(":")) {
    		String defaultValueDT = type;
        	if(defaultValueFinal.contains(";")) {//se tem mais de 1 atributo
        		String[] atributes = defaultValueFinal.split(";");
        		for(String atribute :atributes) {
        			String[] nameAndType = atribute.split(":");
        			if(ADParser.primitives.contains(nameAndType[1])) {
        				defaultValueDT +="."+primitiveDefaultValue(nameAndType[1]);
        			}else {
        				defaultValueDT +="."+getDefaultValue(nameAndType[1]);//TODO testar futuramente quando existir tipos compostos
        			}
        		}
        	}else {
        		String[] nameAndType = defaultValueFinal.split(":");
    			if(ADParser.primitives.contains(nameAndType[1])) {
    				defaultValueDT +="."+primitiveDefaultValue(nameAndType[1]);
    			}else {
    				defaultValueDT +="."+getDefaultValue(nameAndType[1]);//TODO testar futuramente quando existir tipos compostos
    			}
        	}
        	defaultValueFinal = defaultValueDT;
        }
        return defaultValueFinal;
    }

    private String primitiveDefaultValue(String primitive) {//TODO ver se é pra ser assim mesmo
    	if(primitive.equals("Bool")) {
    		return "true";
    	}else {
    		return "1";
    	}
	}

	public Pair<String, String> getInitialAndFinalParameterValue(String type) {
        Pair<String, String> initialAndFinalParameterValue;
        String[] allValues;
        String firstValue;
        String secondValue;
        HashMap<String, String> typesParameter = getParameterValueDiagram(type);

        String ListValue = typesParameter.get(type).replace("{", "").replace("}", "").replace("(", "")
                .replace(")", "");

        if (ListValue.contains("..")) {
            allValues = ListValue.split("\\.\\.");
            firstValue = allValues[0];
            secondValue = allValues[1];
        } else {
            allValues = ListValue.split(",");
            firstValue = allValues[0];
            secondValue = allValues[allValues.length - 1];
        }

        initialAndFinalParameterValue = new Pair<>(firstValue, secondValue);

        return initialAndFinalParameterValue;
    }

    public HashMap<String, String> getParameterValueDiagram(String type) {
        HashMap<String, String> typesParameter = new HashMap<>();
        //String[] definition = adDiagram.getDefinition().replace("\n", "").replace(" ", "").split(";");
        //String[] definition = ADParser.GlobalDefinition.replace("\n", "").replace(" ", "").split(";");
        String[] definition = ADParser.getDefinitionReplaced();
        //TODO ajeitar para os casos de datatype pois tem ; na definição
        
        for (String def : definition) {
        	String[] definitionAux = null;
        	
        	//teste pra enum e datatype
            if (def.startsWith("enum")) {
            	definitionAux= def.split("enum");
			}
            if (def.startsWith("datatype")) {
				definitionAux = def.split("datatype");
			}
            
            if(definitionAux != null) {
            	def = definitionAux[1];
            }
			String[] keyValue = def.split("=");

            
            if (keyValue.length == 1) {
                typesParameter.put(keyValue[0], keyValue[0]);
            } else {
                typesParameter.put(keyValue[0], keyValue[1]);
            }


        }
        
        return typesParameter;
    }

    public int countAmount(IActivityNode activityNode) {
        int input = 0;
        if (activityNode != null) {
            input = 0;
            IFlow[] inFlow = activityNode.getIncomings();

            for (int i = 0; i < inFlow.length; i++) {
            	Pair<IActivity,String> key = new Pair<IActivity, String>(ad,inFlow[i].getId());
                if (syncChannelsEdge.containsKey(key)) {
                    input++;
                }
            }


            if (activityNode instanceof IAction) {
                IInputPin[] inPin = ((IAction) activityNode).getInputs();

                for (int i = 0; i < inPin.length; i++) {
                    IFlow[] inFlowPin = inPin[i].getIncomings();
                    for (int x = 0; x < inFlowPin.length; x++) {
                    	Pair<IActivity,String> key = new Pair<IActivity, String>(ad,inFlowPin[x].getId());
                        if (syncObjectsEdge.containsKey(key)) {
                            input++;
                        }
                    }
                }

            } else {
                for (int i = 0; i < inFlow.length; i++) {
                	Pair<IActivity,String> key = new Pair<IActivity, String>(ad,inFlow[i].getId());
                    if (syncObjectsEdge.containsKey(key)) {
                        input++;
                    }
                }
            }
        }

        return input;
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    private static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

	public List<Pair<String, Integer>> getCallBehaviourNumber() {
		return callBehaviourNumber;
	}

	public void setCallBehaviourNumber(List<Pair<String, Integer>> callBehaviourNumber) {
		this.callBehaviourNumber = callBehaviourNumber;
	}

	public HashMap<String, List<String>> getCallBehaviourInputs() {
		return callBehaviourInputs;
	}

	public void setCallBehaviourInputs(HashMap<String, List<String>> callBehaviourInputs) {
		this.callBehaviourInputs = callBehaviourInputs;
	}

	public HashMap<String, List<String>> getCallBehaviourOutputs() {
		return callBehaviourOutputs;
	}

	public void setCallBehaviourOutputs(HashMap<String, List<String>> callBehaviourOutputs) {
		this.callBehaviourOutputs = callBehaviourOutputs;
	}
	
	public void incomingEdges(IActivityNode activityNode, StringBuilder action, ArrayList<String> alphabet, IFlow[] inFlows,
			IInputPin[] inPins, List<String> namesMemoryLocal, HashMap<String, String> typeMemoryLocal)
			throws ParsingException {
		if (inFlows.length > 0 || inPins.length > 0) {
			action.append("(");
			for (int i = 0; i < inFlows.length; i++) {
	            // first control flows
	        	Pair<IActivity,String> key = new Pair<IActivity, String>(ad, inFlows[i].getId());
	        	if (inFlows[i] instanceof IControlFlow) {
	                String ceIn;
	                if (syncChannelsEdge.containsKey(key)) {
	                    ceIn = syncChannelsEdge.get(key);
	                    
	                } else {
	                	ceIn = createCE();
	                    Pair<IActivity,String> pair = new Pair<IActivity, String>(ad, inFlows[i].getId());
	                    syncChannelsEdge.put(pair, ceIn);
	                }
	                action.append("(");
	                if (i < inFlows.length - 1 || inPins.length > 0) {
	                    ce(alphabet, action, ceIn, " -> SKIP) ||| ");
	                } else {
	                    ce(alphabet, action, ceIn, " -> SKIP)");
	                }
				} else {// then object flows, which are discarded as they are not sent to pins
					String oeIn; 
					String typeObject;
					try {
						typeObject = ((IObjectFlow)inFlows[i]).getBase().getName();
					} catch (NullPointerException e) {
						throw new ParsingException("Object flow does not have a type.");
					}
					
					if (syncObjectsEdge.containsKey(key)) {
	                    oeIn = syncObjectsEdge.get(key);
	                    if (!objectEdges.containsKey(oeIn)) {
			                objectEdges.put(oeIn, typeObject);
						}
	                } else {
	                	 oeIn = createOE();
	                     Pair<IActivity,String> pair = new Pair<IActivity, String>(ad,inFlows[i].getId());
	                     syncObjectsEdge.put(pair, oeIn);
	                     objectEdges.put(oeIn, typeObject);
	                }
					
					action.append("(");
					if (i < inFlows.length - 1 || inPins.length > 0) {
						oe(alphabet, action, oeIn, "?x", " -> ");
						action.append("SKIP) ||| ");
					} else {
						oe(alphabet, action, oeIn, "?x" , " -> ");
						action.append("SKIP)");
					}
				}

	        }

	        //then object flows
	        for (int i = 0; i < inPins.length; i++) {
	            IFlow[] inFlowPin = inPins[i].getIncomings();
	            for (int x = 0; x < inFlowPin.length; x++) {
	            	String type = ((IObjectFlow)inFlowPin[x]).getBase().getName();
	            	
	            	String aux = inPins[i].getBase().getName();
	            	if (!type.equals(inPins[i].getBase().getName())) {
	            		throw new ParsingException("Pin "+ inPins[i].getName() + " and object flow have incompatible types!");
					}
	            	
	            	Pair<IActivity,String> key = new Pair<IActivity, String>(ad, inFlowPin[x].getId());
	            	String nameObject = inPins[i].getName();
	            	String oeIn;
	            	if (syncObjectsEdge.containsKey(key)) {
	                    oeIn = syncObjectsEdge.get(key);
	                    if (!objectEdges.containsKey(oeIn)) {
			                objectEdges.put(oeIn, type);
						}
	                } else {
	                	 oeIn = createOE();
	                     Pair<IActivity,String> pair = new Pair<IActivity, String>(ad,inFlowPin[x].getId());
	                     syncObjectsEdge.put(pair, oeIn);
	                     objectEdges.put(oeIn, type);
	                }
	                
	                action.append("(");
	                if (i < inPins.length - 1 || x < inFlowPin.length - 1) {
	                    oe(alphabet, action, oeIn, "?" + nameObject, " -> ");
	                    try {
							setLocalInput(alphabet, action, inPins[i].getName(), nameDiagramResolver(activityNode.getName()), nameObject, oeIn,inPins[i].getBase().getName());
						} catch (Exception e) {
							throw new ParsingException("InputPin node "+inPins[i].getName()+" without base type\n");//TODO fix the type of exception
						}
	                    action.append("SKIP) ||| ");
	                } else {
	                    oe(alphabet, action, oeIn, "?" + nameObject, " -> ");
	                    try {
							setLocalInput(alphabet, action, inPins[i].getName(), nameDiagramResolver(activityNode.getName()), nameObject, oeIn,inPins[i].getBase().getName());
						} catch (Exception e) {
							throw new ParsingException("Pin node "+inPins[i].getName()+" without base type\n");//TODO fix the type of exception
						}
	                    action.append("SKIP)");
	                }

	                if (!namesMemoryLocal.contains(nameObject)) {
	                    namesMemoryLocal.add(nameObject);
	                    typeMemoryLocal.put(nameObject, inPins[i].getBase().getName());
	                }
	            }
	        }
	        action.append("); ");
		}

	}
	
	public void outgoingEdges(StringBuilder action, ArrayList<String> alphabet, IFlow[] outFlows,
			IOutputPin[] outPins, String[] definitionFinal, String nodeFullName) throws ParsingException {
		// defining outgoing edges
        if (outFlows.length > 0 || outPins.length > 0) {
            action.append("(");
        }

        // creates the outgoing control edges events
        for (int i = 0; i < outFlows.length; i++) {    
        	Pair<IActivity,String> key = new Pair<IActivity, String>(ad, outFlows[i].getId());
            String ceOut;
        	if (syncChannelsEdge.containsKey(key)) {
                ceOut = syncChannelsEdge.get(key);
                
            } else {
            	ceOut = createCE();
                Pair<IActivity,String> pair = new Pair<IActivity, String>(ad, outFlows[i].getId());
                syncChannelsEdge.put(pair, ceOut);
            }
        	
            action.append("(");

            if (i >= 0 && (i < outFlows.length - 1 || outPins.length > 0)) {
                ce(alphabet, action, ceOut, " -> SKIP) ||| ");
            } else {
                ce(alphabet, action, ceOut, " -> SKIP)");
            }
        }

        
        // creates the outgoing object edges events
        
        for (int i = 0; i < outPins.length; i++) {    
            IFlow[] outFlowPin = outPins[i].getOutgoings();
            
            for (int x = 0; x < outFlowPin.length; x++) {
            	String nameObject;
                String type;
            	try {
					nameObject = outPins[i].getBase().getName();
					type = ((IObjectFlow)outFlowPin[x]).getBase().getName();
	            	
	            	if (!type.equals(outPins[i].getBase().getName())) {
	            		throw new ParsingException("OutputPin "+ outPins[i].getName() + " and object flow have incompatible types!");
					}
				} catch (NullPointerException e) {
					throw new ParsingException("Pin "+outPins[i].getName()+" without base class\n");
				}

            	Pair<IActivity,String> pair = new Pair<IActivity, String>(ad,outFlowPin[x].getId());
            	String oe;
            	if (syncObjectsEdge.containsKey(pair)) {
					oe = syncObjectsEdge.get(pair);
					if (!objectEdges.containsKey(oe)) {
		                objectEdges.put(oe, type);
					}
				} else {
					oe = createOE();
	                syncObjectsEdge.put(pair, oe);
	                objectEdges.put(oe, type);
				}
            	
            	
            	if (definitionFinal != null) {//not call behavior
            		String value = "";
                    for (int j = 0; j < definitionFinal.length; j++) {
                        String[] expression = definitionFinal[j].split("=");
                        if (expression[0].equals(outPins[i].getName())) {
                            value = expression[1];
                        }
                    }

                    String typeObj = nameObject;

                    // defining bounds for model checking
                    Pair<String, String> initialAndFinalParameterValue = getInitialAndFinalParameterValue(typeObj);

                    if ((value != null && !value.equals("")) && ADUtils.isInteger(initialAndFinalParameterValue.getKey())) {
                        action.append("((");
                        action.append("(" + value + ") >= " + initialAndFinalParameterValue.getKey() + " and (" + value + ") <= "  + initialAndFinalParameterValue.getValue() + ") & ");
                    } else {
                        action.append("(");
                    }
                    if(value !=null && !value.equals("")) {
                        if (i >= 0 && (i < outPins.length - 1 || x < outFlowPin.length - 1)) {
                            oe(alphabet, action, oe, "!(" + value + ")", " -> SKIP) ||| ");
                        } else {
                            oe(alphabet, action, oe, "!(" + value + ")", " -> SKIP)");
                        }
                    }
                    else {
                    	
                    	if (nodeFullName != null) {
                    		if (i >= 0 && (i < outPins.length - 1 || x < outFlowPin.length - 1)) {
    	                        oe(alphabet, action, oe, "!" + nodeFullName + "_" + outPins[i].getName(), " -> SKIP) ||| ");
    	                    } else {
    	                        oe(alphabet, action, oe, "!" + nodeFullName + "_" + outPins[i].getName(), " -> SKIP)");
    	                    }
                    	} else if (i >= 0 && (i < outPins.length - 1 || x < outFlowPin.length - 1)) {
    	                        oe(alphabet, action, oe, "?unknown"+i, " -> SKIP) ||| ");
    	                    } else {
    	                        oe(alphabet, action, oe, "?unknown"+i, " -> SKIP)");
    	                    }
                    }

				} else {// node is a call behavior
					action.append("(");
	                    if (i >= 0 && (i < outPins.length - 1 || x < outFlowPin.length - 1)) {
	                    	getLocal(alphabet, action, nameResolver(outPins[i].getName()), nameResolver(outPins[i].getOwner().getName()), nameResolver(outPins[i].getName()),type);
	                        oe(alphabet, action, oe, "!(" + ((nodeFullName != null) ? nodeFullName : "") + outPins[i].getName() + ")", " -> SKIP) ||| ");
	                    } else {
	                    	getLocal(alphabet, action, nameResolver(outPins[i].getName()), nameResolver(outPins[i].getOwner().getName()), nameResolver(outPins[i].getName()),type);
	                        oe(alphabet, action, oe, "!(" + ((nodeFullName != null) ? nodeFullName : "") + outPins[i].getName() + ")", " -> SKIP)");
	                    }
				}
            	
            	
                
            }
        }

        if (outFlows.length > 0 || outPins.length > 0) {
            action.append("); ");
        }
	}
    

}
