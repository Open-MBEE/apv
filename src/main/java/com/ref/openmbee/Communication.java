package com.ref.openmbee;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ref.openmbee.adapter.Activity;

public class Communication {

	
	public static Activity buildActivity(String url, String username, String password, String IdElement) throws ClientProtocolException, IOException  {
		HttpClient httpClient = null;
		CookieStore httpCookieStore = new BasicCookieStore();
		HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
		httpClient = builder.build();
		HttpPost httpRequest = new HttpPost("http://18.188.75.184/authentication");
		httpRequest.setHeader("Content-Type", "application/json");
		httpRequest.setHeader("accept", "application/json");
		StringEntity body =new StringEntity("{\"username\": \""+username+"\",\"password\": \""+password+"\"}");
		httpRequest.setEntity(body);
		 
		HttpResponse response = httpClient.execute(httpRequest);
		
		String jsonToken = EntityUtils.toString(response.getEntity());
		//System.out.println(jsonToken);

		JSONObject token = new JSONObject(jsonToken);
		//System.out.println(token.get("token"));

		
		HttpGet httpGet = new HttpGet(url+IdElement);
	
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
		
		response = httpClient.execute(httpGet);
		
		JSONObject JSON = new JSONObject(EntityUtils.toString(response.getEntity()));
		JSONArray elements = JSON.getJSONArray("elements");
		JSONObject element = ((JSONObject)elements.get(0));
		String type = element.getString("type");
		String ownerId = element.getString("ownerId"); 
		String id = element.getString("id");
		ArrayList<String> ownedParameterIds = new ArrayList<>();
		ArrayList<String> nodeIds = new ArrayList<>();
		ArrayList<String> edgeIds = new ArrayList<>();
		ownedParameterIds = JSONArrayToArrayList(element.getJSONArray("ownedParameterIds"));
		nodeIds = JSONArrayToArrayList(element.getJSONArray("nodeIds"));
		edgeIds = JSONArrayToArrayList(element.getJSONArray("edgeIds"));
		
		Activity act = new Activity(type, ownerId, id, ownedParameterIds, nodeIds, edgeIds);
		
		return act;
	}
	
	private static ArrayList<String> JSONArrayToArrayList(JSONArray array){
		ArrayList<String> arrayList = new ArrayList<String>();
		if(array != null) {
			for (int i = 0; i < array.length(); i++) {
				arrayList.add(array.getString(i));
			}
		}
		return arrayList;
	}
}
