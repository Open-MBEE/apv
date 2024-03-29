package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;

public class ADDefineChannels {

    private HashMap<String,Integer> allGuards;
    private IActivity ad;
    private HashMap<String, String> parameterNodesInput;        //name; type
    private HashMap<String, String> parameterNodesOutput;
    private Map<Pair<String, String>,String> memoryLocal;             //nameNode, nameObject
    private HashMap<String, String> parameterNodesOutputObject; //name; object
    private HashMap<Pair<IActivity, String>, String> syncObjectsEdge;
    private HashMap<String, String> objectEdges;                //channel; name
    private List<String> eventChannel;
    private String firstDiagram;
    private HashMap<String, List<IActivity>> signalChannels;
    private ADUtils adUtils;
    private ADParser adParser;
    private HashMap<String, Pair<String, String>> parameterSignal;
	private HashMap<String, List<String>> signalPins;

    public ADDefineChannels(HashMap<String, Integer> allGuards, IActivity ad, HashMap<String, String> parameterNodesInput, HashMap<String, String> parameterNodesOutput,
                            Map<Pair<String, String>, String> memoryLocal, HashMap<String, String> parameterNodesOutputObject, HashMap<Pair<IActivity, String>, String> syncObjectsEdge2,
                            HashMap<String, String> objectEdges, List<String> eventChannel, List<?> lockChannel, String firstDiagram, HashMap<String, List<IActivity>> signalChannels2,
                            HashMap<String, List<String>> signalPins, HashMap<String, Pair<String, String>> parameterSignal2, ADUtils adUtils, ADParser adParser) {
        this.allGuards = allGuards;
        this.ad = ad;
        this.parameterNodesInput = parameterNodesInput;
        this.parameterNodesOutput = parameterNodesOutput;
        this.memoryLocal = memoryLocal;
        this.parameterNodesOutputObject = parameterNodesOutputObject;
        this.syncObjectsEdge = syncObjectsEdge2;
        this.objectEdges = objectEdges;
        this.eventChannel = eventChannel;
        this.firstDiagram = firstDiagram;
        this.signalChannels = signalChannels2;
        this.signalPins = signalPins;
        this.adUtils = adUtils;
        this.adParser = adParser;
        this.parameterSignal = parameterSignal2;
        
    }

    public String defineChannels() throws ParsingException {
        StringBuilder channels = new StringBuilder();
        String nameDiagram = adUtils.nameDiagramResolver(ad.getName());

        for (String guard : allGuards.keySet()) {
            channels.append("channel " + guard + ": ");
            for (int i = 0; i < allGuards.get(guard); i++) {
                if (i > 0) {
                    channels.append(".Bool");
                } else {
                    channels.append("Bool");
                }
            }
            channels.append("\n");
        }

        if (parameterNodesInput.size() > 0) {
            channels.append("channel startActivity_" + nameDiagram + ": ID_" + nameDiagram);

            for (String input : parameterNodesInput.values()) {
                //channels.append(".type_" + input /*+ "_" + nameDiagram*/);
            	//input = input.startsWith("type_")?input.split("type_")[1]:input;
                channels.append("." + input /*+ "_" + nameDiagram*/);
            }

            channels.append("\n");

        } else {
            channels.append("channel startActivity_" + nameDiagram + ": ID_" + nameDiagram + "\n");
        }

        if (parameterNodesOutput.size() > 0) {
            channels.append("channel endActivity_" + nameDiagram + ": ID_" + nameDiagram);

            for (String output : parameterNodesOutput.values()) {
                channels.append("." + output /*+ "_" + nameDiagram*/);
            }

            channels.append("\n");

        } else {
            channels.append("channel endActivity_" + nameDiagram + ": ID_" + nameDiagram + "\n");
        }

        if (parameterNodesInput.size() > 0 || parameterNodesOutput.size() > 0 || memoryLocal.size() > 0) {

            for (String in : parameterNodesInput.keySet()) {
                //channels.append("channel get_" + in + "_" + nameDiagram + ": ID_"+nameDiagram +".countGet_" + nameDiagram + ".type_" + parameterNodesInput.get(in) /*+ "_" + nameDiagram*/ + "\n");
                //channels.append("channel set_" + in + "_" + nameDiagram + ": ID_"+nameDiagram +".countSet_" + nameDiagram + ".type_" + parameterNodesInput.get(in) /*+ "_" + nameDiagram*/ + "\n");
            	String input = parameterNodesInput.get(in);
            	//input = input.startsWith("type_")?input.split("type_")[1]:input;
            	channels.append("channel get_" + in + "_" + nameDiagram + ": ID_"+nameDiagram +".countGet_" + nameDiagram + "." + input /*+ "_" + nameDiagram*/ + "\n");
                channels.append("channel set_" + in + "_" + nameDiagram + ": ID_"+nameDiagram +".countSet_" + nameDiagram + "." + input /*+ "_" + nameDiagram*/ + "\n");
            }

            for (String out : parameterNodesOutput.keySet()) {
                String object = parameterNodesOutput.get(out); // fixed parameterNodesOutputObject
                
                if (object == null) {
                    throw new ParsingException("Parameter node " + out + " is untyped.");
                }

                channels.append("channel get_" + out + "_" + nameDiagram + ": ID_"+nameDiagram +".countGet_" + nameDiagram + "." + object /*+ "_" + nameDiagram*/ + "\n");//TODO futuramente ver se vai precisar do type_
                channels.append("channel set_" + out + "_" + nameDiagram + ": ID_"+nameDiagram +".countSet_" + nameDiagram + "." + object /*+ "_" + nameDiagram*/ + "\n");
            }

            for (Pair<String, String> pair : memoryLocal.keySet()) {
            	String memoryType = memoryLocal.get(pair);
            	//memoryType = memoryType.startsWith("type_")?memoryType.split("type_")[1]:memoryType;
                //channels.append("channel get_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ": ID_"+nameDiagram +".countGet_" + nameDiagram + ".type_" + memoryType + "\n");
                //channels.append("channel set_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ": ID_"+nameDiagram +".countSet_" + nameDiagram + ".type_" + memoryType + "\n");
                channels.append("channel get_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ": ID_"+nameDiagram +".countGet_" + nameDiagram + "." + memoryType/*memoryLocal.get(pair)*/ /*+ "_" + nameDiagram*/ + "\n");
                channels.append("channel set_" + pair.getValue() + "_" + pair.getKey() + "_" + nameDiagram + ": ID_"+nameDiagram +".countSet_" + nameDiagram + "." + memoryType/*memoryLocal.get(pair)*/ /*+ "_" + nameDiagram*/ + "\n");
            }

        }

        if (adParser.countCe_ad > 1) {
            channels.append("channel ce_" + nameDiagram + ": ID_"+nameDiagram +".countCe_" + nameDiagram + "\n");
        }

        if (syncObjectsEdge.size() > 0) {
            ArrayList<String> allObjectEdges = new ArrayList<>();
            
            Collection<String> collection = syncObjectsEdge.values();
            ArrayList<String> list = new ArrayList<>(collection);
            Collections.sort(list);
            
            for (String objectEdge : list) {    //get sync channel
                         	
            	
            	String type = objectEdges.get(objectEdge);
            	//type = type.startsWith("type_")?type.split("type_")[1]:type;
            	objectEdge = objectEdge.substring(0, objectEdge.length()-3);

                if (!allObjectEdges.contains(type)) {
                    allObjectEdges.add(type);
                }
                //channels.append("channel " + objectEdge + ": ID_"+nameDiagram +".type_" + type + "\n");
                channels.append("channel " + objectEdge + ": ID_"+nameDiagram +"." + type /*+ "_" + nameDiagram*/ + "\n");
            }

        }

        channels.append("channel clear_" + nameDiagram + ": ID_"+nameDiagram +".countClear_" + nameDiagram + "\n");

        channels.append("channel update_" + nameDiagram + ": ID_"+nameDiagram +".countUpdate_" + nameDiagram + ".limiteUpdate_" + nameDiagram + "\n");

        channels.append("channel endDiagram_" + nameDiagram +": ID_"+nameDiagram +"\n");

        if (eventChannel.size() > 0) {
            channels.append("channel ");

            for (int i = 0; i < eventChannel.size(); i++) {
                channels.append(eventChannel.get(i));

                if ((i + 1) < eventChannel.size()) {
                    channels.append(",");
                }
            }

            channels.append(": ID_"+nameDiagram+"\n");
        }

        if (firstDiagram.equals(ad.getId())) {
        	List<String> keySignalChannels = new ArrayList<String>();
        	keySignalChannels.addAll(signalChannels.keySet());
        	
        	List<List<IActivity>> entry = new ArrayList<>();
        	entry.addAll(signalChannels.values());
        	String nameMax = nameDiagram;
        	int numMax = 0;
        	for(List<IActivity> valueList : entry) {
        		for(IActivity diagram : valueList) {
        			if(ADParser.countCall.get(ADUtils.nameResolver(diagram.getName()))>numMax) {
            			nameMax = ADUtils.nameResolver(diagram.getName());
            			numMax = ADParser.countCall.get(ADUtils.nameResolver(diagram.getName()));
            		}
        		}        		
        	}
        	
            for (String signalChannel : keySignalChannels) {

            	String parameterType = "";
            	String pinType = "";
            	/*if (parameterSignal.containsKey(signalChannel)) {
            		parameterSignal.get(signalChannel).getValue();
            		//parameterType = "." + parameterNodesInput.get(parameterSignal.get(signalChannel).getValue()) + "_" + nameDiagram;//TODO entender essa linha de codigo
            		parameterType = ".type_" + parameterSignal.get(signalChannel).getValue();
            	}*/
            	if(signalPins.containsKey(signalChannel)) {
            		List<String> types = signalPins.get(signalChannel);
            		for(String typeName: types) {
            			//pinType = ".type_"+typeName;
            			pinType = "."+typeName;
            		}
            	}
                //channels.append("channel signal_" + signalChannel + ": ID_"+nameMax +".countSignal_" + signalChannel + pinType + parameterType + "\n");
                //channels.append("channel accept_" + signalChannel + ": ID_"+nameMax +".countAccept_" + signalChannel + ".countSignal_" + signalChannel + pinType + parameterType +"\n");
                channels.append("channel signal_" + signalChannel + ": ID_"+nameMax +".countSignal_" + signalChannel + pinType + parameterType + "\n");
                channels.append("channel accept_" + signalChannel + ": ID_"+nameMax +".countAccept_" + signalChannel + ".countSignal_" + signalChannel + pinType + parameterType +"\n");
            }

            channels.append("channel loop\n");
            channels.append("channel dc\n");
        }

        return channels.toString();
    }
}
