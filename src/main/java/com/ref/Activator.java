package com.ref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

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
import org.json.JSONObject;
import org.json.JSONArray;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.ref.openmbee.adapter.Activity;
import com.ref.fdr.FdrWrapper;
import com.ref.openmbee.Communication;

public class Activator implements BundleActivator {

	public void start(BundleContext context) {
		try {
			FdrWrapper wrapper = FdrWrapper.getInstance();
			Properties prop = new Properties();
			InputStream input;
			input = new FileInputStream("ref.properties");
			prop.load(input);
			wrapper.loadFDR(prop.getProperty("fdr3_jar_location"));
			wrapper.loadClasses();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void stop(BundleContext context) {
		String fs = System.getProperty("file.separator");
		String uh = System.getProperty("user.home");
		File directory = new File(uh + fs + "TempAstah");

		try {
			delete(directory);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void delete(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				delete(c);
		}
		if (!f.delete())
			throw new FileNotFoundException("Failed to delete file: " + f);
	}

	
	
	public static void main(String args[]) throws ClientProtocolException, IOException {
		// Given
		/*HttpClient httpClient = null;
		CookieStore httpCookieStore = new BasicCookieStore();
		HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
		httpClient = builder.build();
		String username = "ufrpe";
		String password = "thisisapassword";
		HttpPost httpRequest = new HttpPost("http://18.188.75.184/authentication");
		httpRequest.setHeader("Content-Type", "application/json");
		httpRequest.setHeader("accept", "application/json");
		StringEntity body =new StringEntity("{\"username\": \""+username+"\",\"password\": \""+password+"\"}");
		httpRequest.setEntity(body);
		 
		HttpResponse response = httpClient.execute(httpRequest);
		
		String jsonToken = EntityUtils.toString(response.getEntity());
		System.out.println(jsonToken);

		JSONObject token = new JSONObject(jsonToken);
		System.out.println(token.get("token"));

		
		HttpGet httpGet = new HttpGet("http://18.188.75.184/projects/tmt/refs/master/elements/_17_0_2_3_41e01aa_1386799574481_308312_64121");
	
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
		
		response = httpClient.execute(httpGet);
		
		JSONObject activity = new JSONObject(EntityUtils.toString(response.getEntity()));
		System.out.println(activity.toString());
		
		System.out.println(activity.get("elements"));
		//System.out.println(activity.getJSONObject("elements"));
		
		
		httpGet = new HttpGet("http://18.188.75.184/projects/tmt/refs/master/elements/_17_0_2_3_41e01aa_1386574999817_391486_76808");
		
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
		
		response = httpClient.execute(httpGet);
		
		JSONObject edge = new JSONObject(EntityUtils.toString(response.getEntity())); 
		System.out.println(edge.toString());
		JSONArray a = edge.getJSONArray("elements");
		//System.out.println(((JSONObject)a.get(0)).get("sourceId"));
		JSONObject element = ((JSONObject)a.get(0));
		//System.out.println(obj.get("type"));
		String type = element.getString("type");
		String ownerId = element.getString("ownerId"); 
		String id = element.getString("id");
		JSONArray ownedParameterIds = element.getJSONArray("ownedParameterIds");
		JSONArray nodeIds = element.getJSONArray("nodeIds");
		JSONArray edgeIds = element.getJSONArray("edgeIds");
		ArrayList<String> array = (ArrayList<String>)ownedParameterIds.get(0);*/
		String url = "http://18.188.75.184/projects/tmt/refs/master/elements/";
		String login = "ufrpe";
		String password = "thisisapassword";
		String idActivity = "_17_0_2_3_41e01aa_1386574999817_391486_76808";
		Activity activity = Communication.buildActivity(url,login,password,idActivity);
		System.out.println(activity);
	}


}