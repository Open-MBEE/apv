package com.ref.openmbee;

import java.util.ArrayList;

import org.json.JSONArray;

public class AdapterUtils {

	public static ArrayList<String> JSONArrayToArrayList(JSONArray array) {
		ArrayList<String> arrayList = new ArrayList<String>();
		if(array != null) {
			for (int i = 0; i < array.length(); i++) {
				arrayList.add(array.getString(i));
			}
		}
		return arrayList;
	}
}
