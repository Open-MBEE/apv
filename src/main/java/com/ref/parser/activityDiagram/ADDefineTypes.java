package com.ref.parser.activityDiagram;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;

public class ADDefineTypes {

    private IActivity ad;
    private IActivityDiagram adDiagram;

    private String firstDiagram;

    private HashMap<String, Integer> countCall;
    private HashMap<Pair<IActivity,String>, ArrayList<String>> alphabetNode;
    private HashMap<String, String> parameterNodesInput;
    private HashMap<String, String> parameterNodesOutput;
    private List<ArrayList<String>> unionList;
    private List<Pair<String, Integer>> countSignal;
    private List<Pair<String, Integer>> countAccept;
    private ADUtils adUtils;
    private ADParser adParser;

    public ADDefineTypes(IActivity ad, IActivityDiagram adDiagram, String firstDiagram, HashMap<String, Integer> countCall, HashMap<Pair<IActivity, String>, ArrayList<String>> alphabetNode2,
                         HashMap<String, String> objectEdges, HashMap<String, String> parameterNodesInput, HashMap<String, String> parameterNodesOutput,
                         List<Pair<String, String>> memoryLocalChannel, List<ArrayList<String>> unionList, HashMap<String, String> typeUnionList,
                         List<Pair<String, Integer>> countSignal, List<Pair<String, Integer>> countAccept, ADUtils adUtils, ADParser adParser) {
        this.ad = ad;
        this.adDiagram = adDiagram;
        this.firstDiagram = firstDiagram;
        this.countCall = countCall;
        this.alphabetNode = alphabetNode2;
        this.parameterNodesInput = parameterNodesInput;
        this.parameterNodesOutput = parameterNodesOutput;
        this.unionList = unionList;
        this.countSignal = countSignal;
        this.countAccept = countAccept;
        this.adUtils = adUtils;
        this.adParser = adParser;
    }

    public String defineTypes() {
        StringBuilder types = new StringBuilder();
        String nameDiagram = adUtils.nameDiagramResolver(ad.getName());

        if (firstDiagram.equals(ad.getId())) { //If is the first occurrence

            for (String id : countCall.keySet()) {
                types.append("ID_" + id + " = {1.." + countCall.get(id) + "}\n");
            }
            
            boolean flag = false;
            
            for (Pair<String, Integer> signal : countSignal) {
                types.append("countSignal_" + signal.getKey() + " = {1.." + (signal.getValue() - 1) + "}\n");
                for(Pair<String, Integer> signal2 :countAccept) {
                	if(signal2.getKey().equals(signal.getKey())) {
                		flag = true;break;
                	}
                }
                if(!flag) {
                	types.append("countAccept_" + signal.getKey() + " = {1..1}\n");
                }
                flag = false;
            }
            
            for (Pair<String, Integer> signal : countAccept) {
                types.append("countAccept_" + signal.getKey() + " = {1.." + (signal.getValue() - 1) + "}\n");
                for(Pair<String, Integer> signal2 :countSignal) {
                	if(signal2.getKey().equals(signal.getKey())) {
                		flag = true;break;
                	}
                }
                if(!flag) {
                	types.append("countSignal_" + signal.getKey() + " = {1..1}\n");
                }
                flag = false;
            }

        }

        if (alphabetNode.size() > 0) {
            String termination = "_" + nameDiagram + "_t_alphabet";
            types.append("datatype alphabet_" + nameDiagram + " = ");
            boolean first = true;
            Set<Pair<IActivity, String>> keys = alphabetNode.keySet();
            Object[] obj = keys.toArray();
            
            Arrays.sort(obj); // Keeping the same generated order for the alphabet nodes
            
            for (Object node : obj) {
            	Pair<IActivity, String> pair = (Pair) node;
                if (first) {
                    types.append(pair.getValue() + termination + " ");
                    first = false;
                } else {
                    types.append("| " + pair.getValue() + termination);
                }
            }
            types.append("\n");
        }
        if(firstDiagram.equals(ad.getId())) {
	        if (parameterNodesInput.size() > 0 || parameterNodesOutput.size() > 0 || adDiagram.getDefinition().replace(" ", "").length() > 0) {
	            HashMap<String, String> typesParameter = new HashMap<>();
	            //String[] definition = adDiagram.getDefinition().replace("\n", "").replace(" ", "").split(";");
	            //String[] definition = ADParser.GlobalDefinition.replace("\n", "").replace(" ", "").split(";");
	            String[] definition = ADParser.getDefinitionReplaced();
	            
	            
	            String typeName = null;
	            for (String def : definition) {
	                
	                boolean enumeration = false;
	                boolean datatype = false;
	                //teste pra enum e datatype
	            	String[] definitionAux = null;
	                if (def.startsWith("enum")) {
	                	enumeration = true;
	                	definitionAux= def.split("enum");
	    			}
	                if (def.startsWith("datatype")) {
	                	datatype = true;
	    				definitionAux = def.split("datatype");
	    			}
	                String[] aux = definitionAux[1].split("=");
	                typeName = aux[0];
	                if(definitionAux != null) {
	                	if(enumeration) {
	                		def = "enum "; 
	                	}
	                	if(datatype) {
	                		def = "datatype ";
	                	}
	                	
	                	def += definitionAux[1];
	                }
	                String[] keyValue = def.split("=");
	                if (keyValue.length == 1) {
	                    typesParameter.put(keyValue[0], keyValue[0]);
	                } else {
	                    typesParameter.put(keyValue[0], keyValue[1]);
	                }
	                
	
	            }
	            
	            List<String> buffer = new ArrayList<>();
	
	            for (ArrayList<String> union : unionList) {
	                String objectUnion = "";
	                String nameLast = "";
	                for (int i = 0; i < union.size(); i++) {
	                    objectUnion += union.get(i);
	                    nameLast = union.get(i);
	                }
	
	                if (!buffer.contains(objectUnion)) {
	                    types.append(objectUnion + "_" + nameDiagram + " = ");
	                    types.append(typesParameter.get(parameterNodesInput.get(nameLast)) + "\n");
	                    buffer.add(objectUnion);
	                }
	
	            }
	
	            for (String definitionName : typesParameter.keySet()) {
	            	if(!(definitionName.startsWith("enum") || definitionName.startsWith("datatype"))) {
	            		types.append(definitionName + /*"_" + nameDiagram +*/ " = " + typesParameter.get(definitionName) + "\n");
	            	}
	                String typesParameterAux = typesParameter.get(definitionName);
	                if(definitionName.startsWith("enum ")) {
	                	String[] name = definitionName.split("enum");
	                	String atributes = typesParameterAux.replace(",",/*"."+typeName+*/" | ");
	                    atributes = atributes.replace("{", "");
	                    atributes = atributes.replace("}", /*"."+typeName*/"");
	                    //types.append("datatype type_"+ typeName + " = " + atributes+"\n");
	                    types.append("datatype type_"+ name[1].replace(" ", "") + " = " + atributes+"\n");
	                    
	                    /*String[] dtOfEachEnum = typesParameterAux.replace("{", "").replace("}","").replace(" ","").split(",");
	                    for(String EnumValue: dtOfEachEnum) {              
	                    	types.append(EnumValue+" = {1..1}\n");
	                    }*/
	                }else if(definitionName.startsWith("datatype")) {
	                	List<String> atributeNames = new ArrayList<>();
	                    String datatypeAtributes = typesParameter.get(definitionName);
	                    datatypeAtributes = datatypeAtributes.replace("{", "");
	                    datatypeAtributes = datatypeAtributes.replace("}", "");
	                    String[] datatypeAtributesAux = datatypeAtributes.split(";");
	                    for(String dtAtribute: datatypeAtributesAux) {
	                    	String[] atributeSplited = dtAtribute.split(":");
	                    	types.append(atributeSplited[0]+" = "+atributeSplited[1]+"\n");
	                    	atributeNames.add(atributeSplited[0]);
	                    	if(ADParser.primitives.contains(ADUtils.nameResolver(atributeSplited[1]))) {
	                    		adParser.primitivesUsed.add(atributeSplited[1]);
	                    	}
	                    }
	                    String[] dtName = definitionName.split(" ");
	                    types.append(dtName[0]+" type_"+dtName[1]+" = " +dtName[1]);
	                    for(String atributeName: atributeNames) {
	                    	types.append("."+atributeName);
	                    }
	                    types.append("\n");
	                }
	                
	            }
	
	
	        }
    	}
        if (adParser.countGet_ad > 1 || adParser.countSet_ad > 1) {
            if (adParser.countGet_ad == 1) {
                types.append("countGet_" + nameDiagram + " = {1.." + adParser.countGet_ad + "}\n");
            } else {
                types.append("countGet_" + nameDiagram + " = {1.." + (adParser.countGet_ad - 1) + "}\n");
            }

            if (adParser.countSet_ad == 1) {
                types.append("countSet_" + nameDiagram + " = {1.." + adParser.countSet_ad + "}\n");
            } else {
                types.append("countSet_" + nameDiagram + " = {1.." + (adParser.countSet_ad - 1) + "}\n");
            }
        }

        if (adParser.countCe_ad > 1) {
            types.append("countCe_" + nameDiagram + " = {1.." + (adParser.countCe_ad - 1) + "}\n");
        }

        if (adParser.countOe_ad > 1) {
            types.append("countOe_" + nameDiagram + " = {1.." + (adParser.countOe_ad - 1) + "}\n");
        }

        types.append("countUpdate_" + nameDiagram + " = {1.." + (adParser.countUpdate_ad - 1) + "}\n");

        types.append("countClear_" + nameDiagram + " = {1.." + (adParser.countClear_ad - 1) + "}\n");

        types.append("limiteUpdate_" + nameDiagram + " = {(" + adParser.limiteInf + ")..(" + adParser.limiteSup + ")}\n");

        return types.toString();
    }
}
