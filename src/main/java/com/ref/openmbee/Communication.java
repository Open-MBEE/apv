package com.ref.openmbee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IOutputPin;
import com.ref.openmbee.adapter.Action;
import com.ref.openmbee.adapter.Activity;
import com.ref.openmbee.adapter.ActivityNode;
import com.ref.openmbee.adapter.CallBehavior;
import com.ref.openmbee.adapter.ControlFlow;
import com.ref.openmbee.adapter.Flow;
import com.ref.openmbee.adapter.ObjectFlow;
import com.ref.openmbee.adapter.ObjectNode;
import com.ref.openmbee.adapter.Pin;
import com.ref.openmbee.builder.ActionBuilder;
import com.ref.openmbee.builder.ControlBuilder;
import com.ref.openmbee.builder.FlowBuilder;
import com.ref.openmbee.builder.ObjectBuilder;

public class Communication {
	private static List<Activity> doneActivitys = new ArrayList<>();
	private static HashMap<String,Flow> doneFlows = new HashMap<>();
	private static HashMap<String,ActivityNode> doneNodes = new HashMap<>();
	private static HashMap<String,ObjectNode> doneParameters = new HashMap<>();
	
	
	public static Activity buildActivity(String url, String username, String password, String IdElement) throws ClientProtocolException, IOException  {
		
		if (!doneActivitys.contains(FindActivity(IdElement))) {// se ainda não tiver sido criado
			////////////////////////////////////////////////////////////////// parte de requisição http
			HttpClient httpClient = null;
			CookieStore httpCookieStore = new BasicCookieStore();
			HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
			httpClient = builder.build();
			HttpPost httpRequest = new HttpPost("http://18.188.75.184/authentication");
			httpRequest.setHeader("Content-Type", "application/json");
			httpRequest.setHeader("accept", "application/json");
			StringEntity body = new StringEntity(
					"{\"username\": \"" + username + "\",\"password\": \"" + password + "\"}");
			httpRequest.setEntity(body);
			HttpResponse response = httpClient.execute(httpRequest);
			String jsonToken = EntityUtils.toString(response.getEntity());
			JSONObject token = new JSONObject(jsonToken);
			HttpGet httpGet = new HttpGet(url + IdElement);
			httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
			response = httpClient.execute(httpGet);
			JSONObject JSON = new JSONObject(EntityUtils.toString(response.getEntity()));
			JSONArray elements = JSON.getJSONArray("elements");
			JSONObject element = ((JSONObject) elements.get(0));
			String type = element.getString("type");
			String ownerId = element.getString("ownerId");
			String id = element.getString("id");
			ArrayList<String> ownedParameterIds = new ArrayList<>();
			ArrayList<String> nodeIds = new ArrayList<>();
			ArrayList<String> edgeIds = new ArrayList<>();
			//////////////////////////////////////////////////////////////////
			
			FlowBuilder flowBuilder = new FlowBuilder();
			ActionBuilder actionBuilder = new ActionBuilder();
			ControlBuilder controlBuilder = new ControlBuilder();
			ObjectBuilder objectBuilder = new ObjectBuilder();
			
			if (!element.isNull("ownedParameterIds")) {
				ownedParameterIds = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("ownedParameterIds"));
			}
			nodeIds = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("nodeIds"));
			edgeIds = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("edgeIds"));
			
			
			String name = (element.isNull("name")?"":element.getString("name"));
			String[] stereotypes = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("_appliedStereotypeIds")).toArray(new String[0]);

			String definition = (element.isNull("definition")?"":element.getString("definition"));
			
			Activity act = new Activity(type, ownerId, id, ownedParameterIds, nodeIds, edgeIds, name, stereotypes, definition);
			
			
			////////////////////////////////////////////////////////////////// parte das arestas
			for (int i = 0; i < act.getEdgeIds().size(); i++) {
				httpGet = new HttpGet(url + act.getEdgeIds().get(i));
				httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
				response = httpClient.execute(httpGet);
				JSONObject edge = new JSONObject(EntityUtils.toString(response.getEntity()));
				elements = edge.getJSONArray("elements");
				edge = ((JSONObject) elements.get(0));	
				ArrayList<String> stereotypesIds = AdapterUtils.JSONArrayToArrayList(edge.getJSONArray("_appliedStereotypeIds"));
				
				generateStereotypes(url, httpClient, token, stereotypes, edge, stereotypesIds);
				/*
				 * 
				 * adicionar a parte de stereotypes
				 * (assim não precisa modificar a parte que ta dando erro nos builders)
				 * pegar o array de ids
				 * buscar cada um e pegar o nome do stereotype
				 * adicionar o array de nomes no jsonObject
				 * 
				 * duplicar essa parte na parte de nós
				 * */
				
				
				if (!doneFlows.containsKey(edge.getString("id"))) {// se a aresta ainda não foi criada
					if (edge.getString("type").equals("ControlFlow")) {
						Flow newFlow =(ControlFlow) flowBuilder.BuildElement(edge);
						act.addEdge(newFlow);
						
						doneFlows.put(edge.getString("id"), newFlow);
						
					} else if (edge.getString("type").equals("ObjectFlow")) {
						//////////////    testar essa parte       /////////////////
						JSONObject aux = edge.getJSONObject("weight");
						Object obj = aux.get("typeId");
						if (obj instanceof String) {
							Flow newFlow = (ObjectFlow) flowBuilder.BuildElement(edge);
							act.addEdge(newFlow);

							doneFlows.put(edge.getString("id"), newFlow);

						} else {
							Flow newFlow = (ObjectFlow) flowBuilder.BuildElement(edge);
							act.addEdge(newFlow);
							
							doneFlows.put(edge.getString("id"), newFlow);
							
						}
						////////////////////////////////////////////////////////////
					} 
				}

			}
			//////////////////////////////////////////////////////////////////
			
			////////////////////////////////////////////////////////////////// parte dos nós
			for (int i = 0; i < act.getNodeIds().size(); i++) {
				
				httpGet = new HttpGet(url + act.getNodeIds().get(i));
				httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
				response = httpClient.execute(httpGet);
				JSONObject node = new JSONObject(EntityUtils.toString(response.getEntity()));
				elements = node.getJSONArray("elements");
				node = ((JSONObject) elements.get(0));
				//System.out.println("nó: "+(node.isNull("name")?"sem nome":node.getString("name")+"\nTipo: "+node.getString("type")));
				type = node.getString("type");
				ActivityNode newNode = null;
				
				/*
				 * if(type == accept||send||call behavior || 4 tipos de action)
				 * builder de action
				 * buildElement(JSON)
				 * else if(tipos de controlNode)
				 * builder de control
				 * else
				 * builder de object
				 * */
				if(AdapterUtils.JSONArrayToArrayList(node.getJSONArray("outgoingIds")).size() > 0 ||
						AdapterUtils.JSONArrayToArrayList(node.getJSONArray("incomingIds")).size() > 0 ||
						!node.isNull("resultId") || !node.isNull("objectId") || 
						!node.isNull("resultIds") || !node.isNull("argumentIds") || type.equals("ActivityParameterNode")) {//TODO tentativa de tratamento de nós invisives
					ArrayList<String> stereotypesIds = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("_appliedStereotypeIds"));
					
					generateStereotypes(url, httpClient, token, stereotypes, node, stereotypesIds);
					
					if(type.equals("AcceptEventAction") || type.equals("SendSignalAction") || type.equals("CallBehaviorAction") ||
						type.equals("OpaqueAction") || type.equals("ValueSpecificationAction") || type.equals("ReadSelfAction") ||
						type.equals("ReadStructuralFeatureAction")) {
						newNode = (ActivityNode) actionBuilder.BuildElement(node);
											
						if(newNode != null) {//se alguma coisa foi criada
							act.addNode(newNode);
	
							//verificar por pinos e call behavior
							if(type.equals("CallBehaviorAction")) {
								if(!node.isNull("behaviorId")) {
									if(FindActivity(node.getString("behaviorId")) != null) {	
										((CallBehavior)newNode).setCallBehavior(FindActivity(node.getString("behaviorId")));
									}else {
										((CallBehavior)newNode).setCallBehavior(buildActivity(url, username, password, node.getString("behaviorId")));//invoca recursivamente
									}
								}
							}
				
							if(!node.isNull("resultId")) {//se tiver pino de saida (só achei pra 1 pino)
								createPin(node.getString("resultId"),newNode,username,password,url, objectBuilder);
							}
							
							if(!node.isNull("objectId")) {//se tiver pino de entrada (só achei para 1 pino)
								createPin(node.getString("objectId"),newNode,username,password,url, objectBuilder);
							}	
							
							if(!node.isNull("resultIds")) {//se tiver pinos de saida
								ArrayList<String> outpins = new ArrayList<>();
								outpins = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("resultIds"));//pega a lista de ids dos pinos
								for(int j =0; j < outpins.size(); j ++) {//para cada pino
									createPin(outpins.get(j), newNode,username,password,url, objectBuilder);
								}
							}
							
							if(!node.isNull("argumentIds")) {//pinos de entrada
								ArrayList<String> inpins = new ArrayList<>();
								inpins = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("argumentIds"));//pega a lista de ids dos pinos
								for(int j =0; j < inpins.size(); j ++) {//para cada pino
									createPin(inpins.get(j), newNode,username,password,url, objectBuilder);
								}
							}
							
							//adicionar os pinos como nodes
							IInputPin[] inPins = ((IAction)newNode).getInputs(); 
							if(inPins != null) {
								for (int j = 0; j < inPins.length; j++) {
									act.addNode((ActivityNode) inPins[j]);
								}
							}
							IOutputPin[] outPins = ((IAction)newNode).getOutputs(); 
							if(outPins != null) {
								for (int j = 0; j < outPins.length; j++) {
									act.addNode((ActivityNode) outPins[j]);
								}
							}
							
						}
						
					}else if(type.equals("DecisionNode") || type.equals("ActivityFinalNode") || type.equals("FinalFlow") ||
							type.equals("ForkNode") || type.equals("InitialNode") || type.equals("JoinNode") || type.equals("MergeNode")) {
						
						act.addNode((ActivityNode) controlBuilder.BuildElement(node));
						
					}else if(type.equals("Object") || type.equals("ActivityParameterNode")) {
						
						act.addNode((ActivityNode) objectBuilder.BuildElement(node));
						
					}else {
						System.out.println("algo deu errado no if dos filter");
					}
				}
			}
			
			//removeNullNodes(act);
			/*
			 * TODO fazer aqui a filtragem dos node nulls
			 * */
			doneActivitys.add(act);
			return act;
		}
		else {
			return FindActivity(IdElement);
		}
	}


	/*private static void removeNullNodes(Activity act) {//TODO olhar o tratamento que esta sendo feito
		List<ActivityNode> nodes = new ArrayList<>();
		for (int i = 0; i < act.getActivityNodes().length; i++) {
			nodes.add((ActivityNode) act.getActivityNodes()[i]);
		}
		nodes.removeAll(null);
		act.setNodes(nodes);
	}*/


	private static void generateStereotypes(String url, HttpClient httpClient, JSONObject token, String[] stereotypes,
			JSONObject object, ArrayList<String> stereotypesIds) throws IOException, ClientProtocolException {
		HttpResponse response;
		HttpGet httpGet;
		JSONArray elements;
		object.put("stereotype", JSONObject.NULL);
		for (int j = 0; j < stereotypesIds.size(); j++) {
			httpGet = new HttpGet(url + stereotypesIds.get(j));
			httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
			response = httpClient.execute(httpGet);
			JSONObject stereotype = new JSONObject(EntityUtils.toString(response.getEntity()));
			elements = stereotype.getJSONArray("elements");
			stereotype =  ((JSONObject) elements.get(0));
			stereotypes[j] = stereotype.getString("name");
			object.accumulate("stereotypes", stereotype.getString("name"));
		}
		//object.put("stereotypes", stereotypes);
	}


	private static void createPin(String idPin, ActivityNode newNode, String username, String password, String url, ObjectBuilder objectBuilder) throws ClientProtocolException, IOException {
		HttpClient httpClient = null;
		CookieStore httpCookieStore = new BasicCookieStore();
		HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
		httpClient = builder.build();
		HttpPost httpRequest = new HttpPost("http://18.188.75.184/authentication");
		httpRequest.setHeader("Content-Type", "application/json");
		httpRequest.setHeader("accept", "application/json");
		StringEntity body = new StringEntity(
				"{\"username\": \"" + username + "\",\"password\": \"" + password + "\"}");
		httpRequest.setEntity(body);
		HttpResponse response = httpClient.execute(httpRequest);
		String jsonToken = EntityUtils.toString(response.getEntity());
		JSONObject token = new JSONObject(jsonToken);
		HttpGet httpGet = new HttpGet(url + idPin);
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
		response = httpClient.execute(httpGet);
		JSONObject JSON = new JSONObject(EntityUtils.toString(response.getEntity()));
		JSONArray elements = JSON.getJSONArray("elements");
		JSONObject element = ((JSONObject) elements.get(0));
		Pin newPin = null;
		/*
		 * parte http
		 * */
		newPin = (Pin) objectBuilder.BuildElement(element);//cria o pino
		newPin.setOwner((IAction) newNode);// adiciona o dono do pino
		newPin.setActivityId(newNode.getActivityId());
		((Action)newNode).addPin(newPin);//adiciona o pino ao node
		doneNodes.put(newPin.getId(), newPin);//salva o pino
	}
	
	private static Activity FindActivity(String idElement) {
		for(Activity act:doneActivitys) {
			if(act.getId().equals(idElement)) {
				return act;
			}
		}
		return null;
	}

	
	
	public static void resetStatics() {
		doneActivitys = new ArrayList<>();
		doneFlows = new HashMap<>();
		doneNodes = new HashMap<>();
		doneParameters = new HashMap<>();
	}
}
