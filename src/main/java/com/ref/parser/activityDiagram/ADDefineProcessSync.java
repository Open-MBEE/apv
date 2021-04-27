package com.ref.parser.activityDiagram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityNode;


public class ADDefineProcessSync {

    private IActivity ad;
    private HashMap<Pair<IActivity,String>, ArrayList<String>> alphabetNode;
    private ADUtils adUtils;

    public ADDefineProcessSync(IActivity ad, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2, ADUtils adUtils) {
        this.ad = ad;
        this.alphabetNode = alphabetNode2;
        this.adUtils = adUtils;
    }

    public String defineProcessSync() {
        StringBuilder processSync = new StringBuilder();
        String nameDiagram = adUtils.nameDiagramResolver(ad.getName());
        String termination = "_" + nameDiagram + "_t";
        String terminationAlphabet = "_" + nameDiagram + "_t_alphabet";
        StringBuilder alphabetDiagram = new StringBuilder();
        Set<Pair<IActivity, String>> keys = alphabetNode.keySet();
        
        Object[] obj = keys.toArray();
        
        Arrays.sort(obj); // Keeping the same generated order for the alphabet nodes
        
        for(Object pair : obj) {
        	Pair<IActivity, String> node = (Pair) pair;
            ArrayList<String> alphabet = alphabetNode.get(node);
            IActivityNode Activitynode = findCBANode(node.getValue());
            if(Activitynode != null) {
        		processSync.append("AlphabetDiagram_" + nameDiagram + "(id," + node.getValue() + terminationAlphabet + ") = union({|");
                alphabetDiagram.append("AlphabetDiagram_" + nameDiagram + "(id," + node.getValue() + terminationAlphabet + ")"+"SUB");
                for (int i = 0; i < alphabet.size(); i++) {
                    processSync.append(alphabet.get(i));
                    if (i < alphabet.size() - 1) {
                        processSync.append(",");
                    }
                }
                List<Pair<String,String>> CBAList = ADParser.countcallBehavior.get(((IAction) Activitynode).getCallingActivity().getId());//Gets the List with every node the invokes the CBA
            	int index = 1;
            	for(int i=0;i<CBAList.size();i++) {//sweeps the List searching for the index of the node
            		if(Activitynode.getId().equals(CBAList.get(i).getKey())) {
            			index = i+1;
            		}
            	}
                processSync.append("|},AlphabetDiagram_"+ADUtils.nameResolver(((IAction)Activitynode).getCallingActivity().getName())+"_t("+index+"))\n");
        	}else {
        		processSync.append("AlphabetDiagram_" + nameDiagram + "(id," + node.getValue() + terminationAlphabet + ") = {|");
                alphabetDiagram.append("AlphabetDiagram_" + nameDiagram + "(id," + node.getValue() + terminationAlphabet + ")"+"SUB");
                for (int i = 0; i < alphabet.size(); i++) {
                    processSync.append(alphabet.get(i));
                    if (i < alphabet.size() - 1) {
                        processSync.append(",");
                    }
                }
        		processSync.append("|}\n");
        	}           
             
        }
   
        processSync.append("AlphabetDiagram_" + nameDiagram +"_t(id) = ");
        for(int i = 1; i< alphabetNode.size();i++) {
        	processSync.append("union(");
        }
        alphabetDiagram.replace(alphabetDiagram.indexOf("SUB"), alphabetDiagram.indexOf("SUB")+3,",");
        alphabetDiagram.replace(alphabetDiagram.lastIndexOf("SUB"), alphabetDiagram.lastIndexOf("SUB")+3,")\n\n");
        String aux = alphabetDiagram.toString().replaceAll("SUB", "),");
        processSync.append(aux);
        
        
        for(Object pair : obj) {
        	Pair<IActivity, String> node = (Pair) pair;
            processSync.append("ProcessDiagram_" + nameDiagram + "(id," + node.getValue() + terminationAlphabet + ") = normal(");
            processSync.append(node.getValue() + termination + "(id))\n");
        }

        processSync.append("Node_" + nameDiagram + "(id) = || x:alphabet_" + nameDiagram + " @ [AlphabetDiagram_" + nameDiagram + "(id,x)] ProcessDiagram_" + nameDiagram + "(id,x)\n");

        return processSync.toString();
    }
    
    public IActivityNode findCBANode(String nodeName) {
    	IActivityNode[] nodes = ad.getActivityNodes();//Gets all nodes
    	IAction nodeFound;
		for(int i=0; i<nodes.length;i++) {//sweeps the nodes
			if(ADUtils.nameResolver(nodes[i].getName()).equals(nodeName) && nodes[i] instanceof IAction) {
				nodeFound = (IAction) nodes[i];
				if(nodeFound.isCallBehaviorAction()) {
					return nodeFound;
				}
			}
		}
		return null;
	}
}
