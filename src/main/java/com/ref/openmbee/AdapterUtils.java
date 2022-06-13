package com.ref.openmbee;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;

import com.ref.openmbee.adapter.Activity;

public class AdapterUtils {

	public static HashMap<String,String> primitives = new HashMap<>();
	
	public static ArrayList<String> JSONArrayToArrayList(JSONArray array) {
		ArrayList<String> arrayList = new ArrayList<String>();
		if(array != null) {
			for (int i = 0; i < array.length(); i++) {
				arrayList.add(array.getString(i));
			}
		}
		return arrayList;
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
	
	public static void definePrimitives() {
		if(primitives.isEmpty()) {
			/*
			String booleano = "_16_5_1_12c903cb_1245415335546_39033_4086";
			String complexo = "_11_5EAPbeta_be00301_1147431846238_895928_1691";
			String inteiro = "_16_5_1_12c903cb_1245415335546_8641_4088";
			String real = "_11_5EAPbeta_be00301_1147431819399_50461_1671";
			String string = "_16_5_1_12c903cb_1245415335546_479030_4092";
			String numero = "_16_5_1_12c903cb_1245415335546_535327_4089";
			String naturalIlimitado = "_16_6beta_17530432_1249976300015_220785_665";
			String pontoFlutuante = "_18_0_5_c0402fd_1465851733888_531785_151013";
			String racional = "_16_5_1_12c903cb_1245415335546_799389_4090";
			*/
			primitives.put("_16_5_1_12c903cb_1245415335546_39033_4086", "Bool");
			primitives.put("_11_5EAPbeta_be00301_1147431846238_895928_1691", "Complex");
			primitives.put("_16_5_1_12c903cb_1245415335546_8641_4088", "Int");
			primitives.put("_11_5EAPbeta_be00301_1147431819399_50461_1671", "Real");
			primitives.put("_16_5_1_12c903cb_1245415335546_479030_4092", "String");
			primitives.put("_16_5_1_12c903cb_1245415335546_535327_4089", "Number");
			primitives.put("_16_6beta_17530432_1249976300015_220785_665", "UnlimitedNatural");
			primitives.put("_18_0_5_c0402fd_1465851733888_531785_151013", "Float");
			primitives.put("_16_5_1_12c903cb_1245415335546_799389_4090", "Rational");
			primitives.put("_18_0_4_baa02e2_1441307939187_553193_205904", "LiteralReal");
			//primitives.put("_18_0_5_c0402fd_1467061244795_711510_185960", "LiteralBoolean");//errado
			
		}
	}

	public static void generateDiagramPrimitives(Activity act) {

		String createdDiagramTypes = Communication.createdTypes.get(act.getId());
			if(createdDiagramTypes != null) {
			String[] diagramTypes = createdDiagramTypes.split(",");
			String typesDefinition = "";
			ArrayList<String> typesCreated = new ArrayList<>();
			for(String type : diagramTypes) {
				if (!typesCreated.contains(type)) {
					String[] typeSplited = type.split("_");
					switch (typeSplited[0]) {
					case "Bool":
						typesDefinition += "Bool";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "Complex":
						typesDefinition += "Complex";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "Int":
						typesDefinition += "Int";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "Real":
						typesDefinition += "Real";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "String":
						typesDefinition += "String";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "Number":
						typesDefinition += "Number";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "UnlimitedNatural":
						typesDefinition += "UnlimitedNatural";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "Float":
						typesDefinition += "Float";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
	
					case "Rational":
						typesDefinition += "Rational";
						for(int i = 1; i<typeSplited.length; i++) {
							typesDefinition += "_"+typeSplited[i];						
						}
						typesDefinition += " = {0..1}\n";
						break;
					}
					typesCreated.add(type);
				}
			
			}
			
			act.setDefinition(act.getDefinition()+typesDefinition);
		}
	}
	
	
	public static void addDiagramUsedTypesToDiagramDefinition(Activity act) {
		HashMap<String,String> diagramTypes = Communication.activityDiagramTypes.get(act.getId());
		if(diagramTypes != null) {
			Set<String> keysSet = diagramTypes.keySet();
			List<String> keys = new ArrayList<>(keysSet);
			Collection<String> valuesSet =  diagramTypes.values();
			List<String> values = new ArrayList<>(valuesSet);
			String definition = "";
			
			for (int i = 0; i < keys.size(); i++) {
				definition += keys.get(i)+" = "+values.get(i)+"\n";
			}
			act.setDefinition(act.getDefinition()+definition);
		}
	}
	
}
