package com.ref.parser.activityDiagram;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ref.interfaces.activityDiagram.IActivity;

public class ADDefineMainNodes {

    private IActivity ad;

    private String firstDiagram;
    private List<String> lockChannel;
    private HashMap<String, String> parameterNodesInput;
    private HashMap<String, String> parameterNodesOutput;
    private HashMap<String, List<String>> callBehaviourInputs;
    private ADUtils adUtils;
    private ADParser adParser;

    public ADDefineMainNodes(IActivity ad, String firstDiagram, List<String> lockChannel, HashMap<String, String> parameterNodesInput, HashMap<String, String> parameterNodesOutput,
                             List<Pair<String, Integer>> callBehaviourNumber, HashMap<String, List<String>> callBehaviourInputs,
                             List<String> localSignalChannelsSync, List<String> signalChannelsLocal, ADUtils adUtils, ADParser adParser) {
        this.ad = ad;
        this.firstDiagram = firstDiagram;
        this.lockChannel = lockChannel;
        this.parameterNodesInput = parameterNodesInput;
        this.parameterNodesOutput = parameterNodesOutput;
        this.callBehaviourInputs = callBehaviourInputs;
        this.adUtils = adUtils;
        this.adParser = adParser;
    }

    public String defineMainNodes() {
        StringBuilder mainNode = new StringBuilder();
        String nameDiagram = adUtils.nameDiagramResolver(ad.getName());
        ArrayList<String> alphabet = new ArrayList<>();

        if (firstDiagram.equals(ad.getId())) {
            mainNode.append("MAIN = normal(" + nameDiagram + "(1)); LOOP\n");
            mainNode.append("LOOP = loop -> LOOP\n");
        }

        mainNode.append("END_DIAGRAM_" + nameDiagram + "(id) = endDiagram_" + nameDiagram + ".id -> SKIP\n");
        mainNode.append(nameDiagram + "(ID_" + nameDiagram + ") = ");

        if (parameterNodesInput.size() + parameterNodesOutput.size() > 0) {
            mainNode.append("(");
        }

        if (adParser.countUpdate_ad > 0) {
            mainNode.append("(");
        }

        if (lockChannel.size() > 0) {
            mainNode.append("(");
        }


        if(firstDiagram.equals(ad.getId()) && ADParser.alphabetPool.size() > 0) mainNode.append("(");

        mainNode.append("Internal_" + nameDiagram + "(ID_" + nameDiagram + ")");

        mainNode.append(" [|{|update_" + nameDiagram + ",clear_" + nameDiagram + ",endDiagram_" + nameDiagram + "|}|] ");
        mainNode.append("TokenManager_" + nameDiagram + "_t(ID_"+nameDiagram+",0,0))");
        
        if(firstDiagram.equals(ad.getId()) && ADParser.alphabetPool.size() > 0) {//If is the first diagram
        	mainNode.append("[|AlphabetPool|]pools(ID_"+nameDiagram+"))");
        }       

        if (lockChannel.size() > 0) {
            mainNode.append(" [|{|");
            for (String lock : lockChannel) {
                mainNode.append("lock_" + lock + ",");
            }
            mainNode.append("endDiagram_" + nameDiagram + "|}|] ");
        }

        if (parameterNodesInput.size() + parameterNodesOutput.size() > 0) {
            if (lockChannel.size() > 0) {
                mainNode.append("Lock_" + nameDiagram + ")");
            }

            mainNode.append(" [|{|");
            StringBuilder getSet = new StringBuilder();
            for (String input : parameterNodesInput.keySet()) {
            	getSet.append("get_" + input + "_" + nameDiagram + ",set_" + input +"_" + nameDiagram+",");
                mainNode.append("get_" + input + "_" + nameDiagram + ",");
                mainNode.append("set_" + input + "_" + nameDiagram + ",");
            }

            for (String output : parameterNodesOutput.keySet()) {
            	getSet.append("get_" + output + "_" + nameDiagram + ",set_" + output +"_" + nameDiagram+",");
                mainNode.append("get_" + output + "_" + nameDiagram + ",");
                mainNode.append("set_" + output + "_" + nameDiagram + ",");
            }       
        	mainNode.append("endActivity_" + nameDiagram + "|}|] ");          
            
            if(!parameterNodesInput.isEmpty() || !parameterNodesOutput.isEmpty()) {
            	getSet.replace(getSet.lastIndexOf(","), getSet.lastIndexOf(",")+1,"");//tira a ultima ,
            	mainNode.append("Mem_" + nameDiagram + "(ID_"+nameDiagram+")) \\{|"+getSet+"|}\n");
            	
            }else {
            	mainNode.append("Mem_" + nameDiagram + "(ID_"+nameDiagram+"))\n");
            }
        } else if (lockChannel.size() > 0) {
            mainNode.append("Lock_" + nameDiagram + ")\n");
        } else {
            mainNode.append("\n");
        }

        mainNode.append("Internal_" + nameDiagram + "(id) = ");
        mainNode.append("StartActivity_" + nameDiagram + "(id); Node_" + nameDiagram + "(id); EndActivity_" + nameDiagram + "(id)\n");


        mainNode.append("StartActivity_" + nameDiagram + "(id) = ");
        mainNode.append("startActivity_" + nameDiagram + ".id");

        if (parameterNodesInput.size() > 0) {
            if (callBehaviourInputs.containsKey(adUtils.nameDiagramResolver(ad.getName()))) {
                for (String input : callBehaviourInputs.get(adUtils.nameDiagramResolver(ad.getName()))) {
                    mainNode.append("?" + input);
                }
            } else {
                for (String input : parameterNodesInput.keySet()) {
                    mainNode.append("?" + input);
                }
            }


            mainNode.append(" -> ");

            if (callBehaviourInputs.containsKey(adUtils.nameDiagramResolver(ad.getName()))) {
                for (String input : callBehaviourInputs.get(adUtils.nameDiagramResolver(ad.getName()))) {
                    adUtils.set(alphabet, mainNode, input, input);
                }
            } else {
                for (String input : parameterNodesInput.keySet()) {
                    adUtils.set(alphabet, mainNode, input, input);
                }
            }

            mainNode.append("SKIP\n");
        } else {
            mainNode.append(" -> SKIP\n");
        }


        mainNode.append("EndActivity_" + nameDiagram + "(id) = ");

        if (parameterNodesOutput.size() > 0) {
            for (String input : parameterNodesOutput.keySet()) {
                adUtils.get(alphabet, mainNode, input);
            }

            mainNode.append("endActivity_" + nameDiagram + ".id");

            for (String output : parameterNodesOutput.keySet()) {
                mainNode.append("!" + output);
            }

            mainNode.append(" -> SKIP");
        } else {
            mainNode.append("endActivity_" + nameDiagram + ".id -> SKIP");
        }

        mainNode.append("\n");
        return mainNode.toString();
    }
}
