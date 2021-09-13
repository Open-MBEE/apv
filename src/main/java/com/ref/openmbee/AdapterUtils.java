package com.ref.openmbee;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

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
			//primitives.put("_18_0_5_c0402fd_1467061244795_711510_185960", "LiteralBoolean");//errado
			
		}
	}
}
