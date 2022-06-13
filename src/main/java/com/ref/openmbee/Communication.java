package com.ref.openmbee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
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
import org.json.JSONException;
import org.json.JSONObject;

import com.ref.exceptions.HttpException;
import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IAction;
import com.ref.interfaces.activityDiagram.IActivityNode;
import com.ref.interfaces.activityDiagram.IFlow;
import com.ref.interfaces.activityDiagram.IInputPin;
import com.ref.interfaces.activityDiagram.IOutputPin;
import com.ref.interfaces.activityDiagram.IPartition;
import com.ref.openmbee.adapter.Action;
import com.ref.openmbee.adapter.Activity;
import com.ref.openmbee.adapter.ActivityDiagram;
import com.ref.openmbee.adapter.ActivityNode;
import com.ref.openmbee.adapter.CallBehavior;
import com.ref.openmbee.adapter.ControlFlow;
import com.ref.openmbee.adapter.ControlNode;
import com.ref.openmbee.adapter.Flow;
import com.ref.openmbee.adapter.ObjectFlow;
import com.ref.openmbee.adapter.ObjectNode;
import com.ref.openmbee.adapter.Pin;
import com.ref.openmbee.adapter.Type;
import com.ref.openmbee.adapter.ValueSpecificationAction;
import com.ref.openmbee.builder.ActionBuilder;
import com.ref.openmbee.builder.ControlBuilder;
import com.ref.openmbee.builder.DiagramBuilder;
import com.ref.openmbee.builder.FlowBuilder;
import com.ref.openmbee.builder.ObjectBuilder;
import com.ref.openmbee.builder.PartitionBuilder;
import com.ref.openmbee.builder.TypeBuilder;

public class Communication {
	private static String mostExternalDiagram = "";
	private static List<Activity> doneActivitys = new ArrayList<>();
	private static HashMap<String,Flow> doneFlows = new HashMap<>();
	private static HashMap<String,ActivityNode> doneNodes = new HashMap<>();
	private static HashMap<String,ObjectNode> doneParameters = new HashMap<>();
	public static HashMap<String,String> createdTypes = new HashMap<>();//TODO verificar se é pra ser assim mesmo
	public static HashMap<String,HashMap<String,String>> activityDiagramTypes = new HashMap<>();
	
	
	//public static HashMap<String,String> primitives = new HashMap<>(); 

	public static Activity buildActivity(String url, String username, String password, String IdElement) throws ClientProtocolException, IOException, ParsingException, JSONException, HttpException  {
		
		AdapterUtils.definePrimitives();
		
		if (!doneActivitys.contains(FindActivity(IdElement))) {// se ainda não tiver sido criado
			FlowBuilder flowBuilder = new FlowBuilder();
			ActionBuilder actionBuilder = new ActionBuilder();
			ControlBuilder controlBuilder = new ControlBuilder();
			ObjectBuilder objectBuilder = new ObjectBuilder();
			PartitionBuilder partitionBuilder = new PartitionBuilder();
			DiagramBuilder diagramBuilder = new DiagramBuilder();
			TypeBuilder typeBuilder = new TypeBuilder();
			
			////////////////////////////////////////////////////////////////// parte de requisição http
			HttpClient httpClient = null;
			CookieStore httpCookieStore = new BasicCookieStore();
			HttpClientBuilder builder = HttpClientBuilder.create().setDefaultCookieStore(httpCookieStore);
			httpClient = builder.build();
			//HttpPost httpRequest = new HttpPost("http://18.188.75.184/authentication");
			HttpPost httpRequest = new HttpPost("http://192.168.56.101:8080/authentication");
			
			//HttpPost httpRequest = new HttpPost("https://mms.openmbee.org/alfresco/mms/swagger-ui/index.html#/authentication");
			//HttpPost httpRequest = new HttpPost("https://mms.openmbee.org/alfresco/service/api/login");
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

			
			if(type.equals("Diagram")) {
				defineMostExternalDiagramId(ownerId);

				Activity activity = buildActivity(url, username, password, ownerId);
				ActivityDiagram actDiagram = diagramBuilder.BuildElement(element);
				actDiagram.setActivity(activity);
				activity.setActivityDiagram(actDiagram);
				if(actDiagram.getDefinition().equals("")) {
					actDiagram.setDefinition(activity.getDefinition());
				}
				doneActivitys.add(activity);
				return activity;
			}else if(type.equals("Activity")){
				defineMostExternalDiagramId(IdElement);

				if (!element.isNull("ownedParameterIds")) {
					ownedParameterIds = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("ownedParameterIds"));
				}
				nodeIds = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("nodeIds"));
				edgeIds = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("edgeIds"));
				
				
				String name = (element.isNull("name")?id:element.getString("name"));
				String[] stereotypes = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("_appliedStereotypeIds")).toArray(new String[0]);
	
				String definition = (element.isNull("definition")?"":element.getString("definition"));
				
				Activity act = new Activity(type, ownerId, id, ownedParameterIds, nodeIds, edgeIds, name, stereotypes, definition);
				
				////////////////////////////////////////////////////////////////// parte das arestas
				for (int i = 0; i < act.getEdgeIds().size(); i++) {
					JSONObject edge = elementRequest(url, httpClient, token, act.getEdgeIds().get(i));
					ArrayList<String> stereotypesIds = AdapterUtils.JSONArrayToArrayList(edge.getJSONArray("_appliedStereotypeIds"));
					
					generateStereotypes(url, httpClient, token, stereotypes, edge, stereotypesIds);
										
					if (!doneFlows.containsKey(edge.getString("id"))) {// se a aresta ainda não foi criada
						if (edge.getString("type").equals("ControlFlow")) {
							Flow newFlow =(ControlFlow) flowBuilder.BuildElement(edge);
							act.addEdge(newFlow);
							doneFlows.put(edge.getString("id"), newFlow);
							
						} else if (edge.getString("type").equals("ObjectFlow")) {
							Flow newFlow = (ObjectFlow) flowBuilder.BuildElement(edge);
							act.addEdge(newFlow);
							doneFlows.put(edge.getString("id"), newFlow);					
						} 
					}
	
				}
				//////////////////////////////////////////////////////////////////
				
				////////////////////////////////////////////////////////////////// parte dos nós
				for (int i = 0; i < act.getNodeIds().size(); i++) {
 					JSONObject node = elementRequest(url, httpClient, token, act.getNodeIds().get(i));
					type = node.getString("type");
					
					ActivityNode newNode = null;

					if(!ghostNodeCondition(type, node, url, httpClient, token)) {//TODO tentativa de tratamento de nós invisives
						
						ArrayList<String> stereotypesIds = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("_appliedStereotypeIds"));
						
						generateStereotypes(url, httpClient, token, stereotypes, node, stereotypesIds);
						
						if(isActionNode(type)) {
							newNode = (ActivityNode) actionBuilder.BuildElement(node);
												
							if(newNode != null) {//se alguma coisa foi criada
								act.addNode(newNode);
		
								//verificar por pinos e call behavior
								if(type.equals("CallBehaviorAction")) {
									if(!node.isNull("behaviorId")) {
										Activity activityAux;
										if(FindActivity(node.getString("behaviorId")) != null) {	
											activityAux = FindActivity(node.getString("behaviorId"));
										}else {
											activityAux = buildActivity(url, username, password, node.getString("behaviorId"));
										}
										ActivityDiagram activityDiagram = buildActivityDiagram(url,httpClient,token,node.getString("behaviorId"),diagramBuilder);
										activityAux.setActivityDiagram(activityDiagram);
										activityDiagram.setActivity(activityAux);
										/*if(activityDiagram.getDefinition().equals("")) {//definition of the activity
											activityDiagram.setDefinition(activityAux.getDefinition());
										}*/
										((CallBehavior)newNode).setCallBehavior(activityAux);
									}
								}
								
								if(type.equals("ValueSpecificationAction")) {//só funciona se typeId e InstanceId não tiver como coexistir
									String valueInstanceId ="";
									String valueTypeId = "";
									JSONObject jsonObject = null;
									ArrayList<String> slotIds = new ArrayList<>();
									ArrayList<String> slots = new ArrayList<>();
									boolean primitive = AdapterUtils.primitives.containsKey(((ValueSpecificationAction) newNode).getValueTypeId()) 
											|| ((ValueSpecificationAction) newNode).getValueType().equals("LiteralBoolean"); 
									if(!primitive) {									
										if(!((ValueSpecificationAction)newNode).getValueInstanceId().equals("")) {
											valueInstanceId = ((ValueSpecificationAction)newNode).getValueInstanceId();
											jsonObject = elementRequest(url, httpClient, token, valueInstanceId);
										}
										if(!((ValueSpecificationAction)newNode).getValueTypeId().equals("")) {
											valueTypeId = ((ValueSpecificationAction)newNode).getValueTypeId();
											jsonObject = elementRequest(url, httpClient, token, valueTypeId);
										}
										
										((ValueSpecificationAction)newNode).setValueName(jsonObject.getString("name"));
										
										if(!jsonObject.isNull("slotIds")) {
											slotIds = AdapterUtils.JSONArrayToArrayList(jsonObject.getJSONArray("slotIds"));
										}
										
										for(String slot : slotIds) {
											JSONArray valueAux;
											jsonObject = elementRequest(url, httpClient, token, slot);
											valueAux = jsonObject.getJSONArray("value");
											if(((ValueSpecificationAction)newNode).getValueDefinition().equals("")) {
												for(Object valueObject: valueAux) {//se o valor for um int (descobrir maneira de deixar generico)
													Integer value = new Integer(((JSONObject)valueObject).getInt("value"));
													slots.add(value.toString());
												}
											}else {
												((ValueSpecificationAction)newNode).setDefinition(((ValueSpecificationAction)newNode).getValueDefinition());
											}
										}
										((ValueSpecificationAction)newNode).setValue(slots);
									}
									if(((ValueSpecificationAction)newNode).getValue().size() > 0) {//TODO ver se é pra ficar assim mesmo
										if(((ValueSpecificationAction)newNode).getValueDefinition().equals("")) {
											String defAux = "value = "+ ((ValueSpecificationAction)newNode).getValue().get(0);
											for(int j = 1; j < ((ValueSpecificationAction)newNode).getValue().size(); j++) {
												defAux += "."+((ValueSpecificationAction)newNode).getValue().get(j);
											}
											((ValueSpecificationAction)newNode).setDefinition(defAux);
										}else {
											((ValueSpecificationAction)newNode).setDefinition(((ValueSpecificationAction)newNode).getValueDefinition());
										}
									}
								}
					
								if(!node.isNull("resultId")) {//se tiver pino de saida (só achei pra 1 pino)
									createPin(url,httpClient,token,node.getString("resultId"),newNode, objectBuilder, typeBuilder, act);
								}
								
								if(!node.isNull("objectId")) {//se tiver pino de entrada (só achei para 1 pino)
									createPin(url,httpClient,token,node.getString("objectId"),newNode, objectBuilder, typeBuilder, act);
								}	
								
								if(!node.isNull("resultIds")) {//se tiver pinos de saida
									ArrayList<String> outpins = new ArrayList<>();
									outpins = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("resultIds"));//pega a lista de ids dos pinos
									for(int j =0; j < outpins.size(); j ++) {//para cada pino
										createPin(url,httpClient,token,outpins.get(j), newNode, objectBuilder, typeBuilder, act);
									}
								}
								
								if(!node.isNull("argumentIds")) {//pinos de entrada
									ArrayList<String> inpins = new ArrayList<>();
									inpins = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("argumentIds"));//pega a lista de ids dos pinos
									for(int j =0; j < inpins.size(); j ++) {//para cada pino
										createPin(url,httpClient,token,inpins.get(j), newNode, objectBuilder, typeBuilder, act);
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
							
						}else if(isControlNode(type)) {
							
							act.addNode((ActivityNode) controlBuilder.BuildElement(node));
							
						}else if(isObjectNode(type)) {
							ObjectNode actNode = (ObjectNode) objectBuilder.BuildElement(node); 
							Type baseType = generateType(url, typeBuilder, httpClient, token, node,act);
							actNode.setBaseType(baseType);
							actNode.setTypeId(baseType.getTypeId());
							act.addNode(actNode);
							
						}else if(type.equals("Partition")) {
							
							act.addPartition((IPartition) partitionBuilder.BuildElement(node));
							
						}else {
							throw new ParsingException("something went wrong in the if of the filter");
						}
					}
				}
				
				//////////////////////////////////////////////////////////////////
								
				
				designateFlowsToNodes(act);
				designateObjectFlowBaseType();
				getDefinitionOfObjects(act);
				AdapterUtils.addDiagramUsedTypesToDiagramDefinition(act);
				AdapterUtils.generateDiagramPrimitives(act);
				doneActivitys.add(act);
				/*if(mostExternalDiagram.equals(act.getId())) {
					defineMostExternalDiagramDefinition(act);
				}*/
				return act;
			}else {
				throw new ParsingException("something went wrong with the if of diagram/activity type");
			}
		}
		else {
			return FindActivity(IdElement);
		}
	}

	private static boolean ghostNodeCondition(String type, JSONObject node, String url, HttpClient httpClient, JSONObject token) throws ClientProtocolException, JSONException, IOException, HttpException {
		if(isControlNode(type)) {//se for um controlNode
			if(AdapterUtils.JSONArrayToArrayList(node.getJSONArray("outgoingIds")).size() == 0 &&
					AdapterUtils.JSONArrayToArrayList(node.getJSONArray("incomingIds")).size() == 0) {//se não tiver nenhuma aresta de entrada nem de saida
				return true;
			}else {
				return false;//tem aresta
			}	
		}else if(isActionNode(type)) {
			if(AdapterUtils.JSONArrayToArrayList(node.getJSONArray("outgoingIds")).size() == 0 &&
					AdapterUtils.JSONArrayToArrayList(node.getJSONArray("incomingIds")).size() == 0) {//se não tiver nenhuma aresta de entrada nem de saida
				//node.getString("id");
				String resultId = node.isNull("resultId")?"":node.getString("resultId");
				String objectId = node.isNull("objectId")?"":node.getString("objectId");
				ArrayList<String> argumentIds = node.isNull("argumentIds")?null:AdapterUtils.JSONArrayToArrayList(node.getJSONArray("argumentIds"));
				ArrayList<String> resultIds = node.isNull("resultIds")?null:AdapterUtils.JSONArrayToArrayList(node.getJSONArray("resultIds"));
								
				if(resultId.equals("") && objectId.equals("") && argumentIds == null && resultIds == null){// se não tiver nenhum pino
						return true;
					}else {//se tiver pino
						if(resultId.length() > 0) {//se tiver 1 pino de entrada
							JSONObject element = elementRequest(url, httpClient, token, node.getString("resultId"));
							if(AdapterUtils.JSONArrayToArrayList(element.getJSONArray("outgoingIds")).size() == 0 &&
									AdapterUtils.JSONArrayToArrayList(element.getJSONArray("incomingIds")).size() == 0) {
								return true;
							}else {
								return false;
							}
						}
						
						if(objectId.length() > 0) {//se tiver 1 pino de saida
							JSONObject element = elementRequest(url, httpClient, token, node.getString("objectId"));
							if(AdapterUtils.JSONArrayToArrayList(element.getJSONArray("outgoingIds")).size() == 0 &&
									AdapterUtils.JSONArrayToArrayList(element.getJSONArray("incomingIds")).size() == 0) {
								return true;
							}else {
								return false;
							}
						}
						
						if(argumentIds.size() > 0) {//se tiver pinos de entrada
							ArrayList<String> inpins = new ArrayList<>();
							inpins = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("argumentIds"));//pega a lista de ids dos pinos
							for(int j =0; j < inpins.size(); j ++) {//para cada pino
								JSONObject element = elementRequest(url, httpClient, token, inpins.get(j));
								if(AdapterUtils.JSONArrayToArrayList(element.getJSONArray("outgoingIds")).size() == 0 &&
										AdapterUtils.JSONArrayToArrayList(element.getJSONArray("incomingIds")).size() == 0) {
									return true;
								}
							}
							return false;
						}
						
						if(resultIds.size() > 0) {//se tiver pinos de saida
							ArrayList<String> outpins = new ArrayList<>();
							outpins = AdapterUtils.JSONArrayToArrayList(node.getJSONArray("resultIds"));//pega a lista de ids dos pinos
							for(int j =0; j < outpins.size(); j ++) {//para cada pino
								JSONObject element = elementRequest(url, httpClient, token, outpins.get(j));
								if(AdapterUtils.JSONArrayToArrayList(element.getJSONArray("outgoingIds")).size() == 0 &&
										AdapterUtils.JSONArrayToArrayList(element.getJSONArray("incomingIds")).size() == 0) {
									return true;
								}
							}
							return false;
						}						
						
					}
				return true;
			}else {
				return false;//tem aresta
			}
		}else if(isObjectNode(type)) {
			if(AdapterUtils.JSONArrayToArrayList(node.getJSONArray("outgoingIds")).size() == 0 &&
					AdapterUtils.JSONArrayToArrayList(node.getJSONArray("incomingIds")).size() == 0) {
				if((!node.isNull("resultId") || !node.isNull("objectId") || !node.isNull("resultIds") ||
						!node.isNull("argumentIds")) || type.equals("ActivityParameterNode") ) {
					return true;
				}else {
					return false;//tem pino ou é um parametro
				}
			}else {
				return false;//tem aresta
			}
		}else {
			return false;//não é pra entrar aqui
		}	
	}

	private static boolean isActionNode(String type) {
		if(type.equals("AcceptEventAction") || type.equals("SendSignalAction") || type.equals("CallBehaviorAction") ||
				type.equals("OpaqueAction") || type.equals("ValueSpecificationAction") || type.equals("ReadSelfAction") ||
				type.equals("ReadStructuralFeatureAction")) {
			return true;
		}
		return false;
	}

	private static boolean isControlNode(String type) {
		if(type.equals("DecisionNode") || type.equals("ActivityFinalNode") || type.equals("FinalFlow") ||
				type.equals("ForkNode") || type.equals("InitialNode") || type.equals("JoinNode") || type.equals("MergeNode")) {
			return true;
		}
		return false;
	}
	
	private static boolean isObjectNode(String type) {
		if(type.equals("Object") || type.equals("ActivityParameterNode")) {
			return true;
		}
		return false;
	}
	
	private static void defineMostExternalDiagramId(String idElement) {
		if(mostExternalDiagram.equals("")) {
			mostExternalDiagram = idElement;
		}
	}

	

	private static void getDefinitionOfObjects(Activity act) {
		String definitionNodes = "";
		String definitionFlows = "";
		
		for(IActivityNode actNode:act.getActivityNodes()) {
			if(actNode instanceof ObjectNode && !actNode.getDefinition().equals("") ) {
				definitionNodes += actNode.getDefinition() + "\n";
			}
		}
		
		for(Flow flow:act.getEdges()) {
			if (flow instanceof ObjectFlow && !flow.getDefinition().equals("")) {
				definitionFlows += flow.getDefinition() + "\n";
			}
		}
		act.setDefinition(act.getDefinition()+definitionNodes+definitionFlows);
	}

	private static void designateObjectFlowBaseType() throws ParsingException {
		List<ObjectFlow> objectFlows = new ArrayList<>();  
		Set<String> keys = doneFlows.keySet(); 
		for(String key :keys) {
			if(doneFlows.get(key) instanceof ObjectFlow) {
				objectFlows.add((ObjectFlow) doneFlows.get(key));
			}
		}
		
		for(ObjectFlow objectFlow: objectFlows) {
			ActivityNode source;
			Type baseType;
			if(objectFlow.getSource() instanceof ObjectNode) {
				source = (ObjectNode) objectFlow.getSource();
				baseType = ((ObjectNode)source).getBaseType();
				objectFlow.setBaseType(baseType);
				objectFlow.setBaseTypeId(baseType.getTypeId());// ta dando erro aqui porque o pino result ta sem tipo no TMT(Check for existing reference beam map <- Retrieve Reference Beam Map <- Setup APS, Acquire and Start Guiding)
			}else if(objectFlow.getSource() instanceof ControlNode) {
				source = controlNodeFlowSearch((ControlNode) objectFlow.getSource());
				baseType = ((ObjectNode)source).getBaseType();
				objectFlow.setBaseType(baseType);
				objectFlow.setBaseTypeId(baseType.getTypeId());
			}
		}
	}


	private static ObjectNode controlNodeFlowSearch(ControlNode source) throws ParsingException {//TODO testar pra garantir que esta certo
		
		ObjectFlow auxFlow = null;
		ActivityNode auxNode;
		for(IFlow incoming:source.getIncomings()) {
			if(incoming instanceof ObjectFlow) {
				auxFlow = (ObjectFlow) incoming;
			}
		}
		if(auxFlow != null) {
			auxNode = (ActivityNode) auxFlow.getSource();
			if(auxNode instanceof ObjectNode) {
				return (ObjectNode) auxNode;
			}else if(auxNode instanceof ControlNode){
				auxNode = controlNodeFlowSearch((ControlNode) auxNode);
			}
		}else {
			throw new ParsingException("Couldn't find the base type.\n");
		}
		return (ObjectNode) auxNode;
		
	}


	private static void designateFlowsToNodes(Activity act) {

		for (int i = 0; i < act.getActivityNodes().length; i++) {//para cada nó
			ActivityNode node = (ActivityNode) act.getActivityNodes()[i];
			IFlow[] incomings = new Flow[node.getIncomingIds().size()];
			IFlow[] outgoings = new Flow[node.getOutgoingsIds().size()];
			
			for (int j = 0; j < node.getIncomingIds().size(); j++) {// para cada aresta de entrada
				incomings[j] = doneFlows.get(node.getIncomingIds().get(j));//adiciona a aresta ao nó
				incomings[j].setTarget(node);//adiciona o nó a aresta
				doneFlows.replace(node.getIncomingIds().get(j), (Flow) incomings[j]);
			}
			
			for (int j = 0; j < node.getOutgoingsIds().size(); j++) {// para cada aresta de saida
				outgoings[j] = doneFlows.get(node.getOutgoingsIds().get(j));//adiciona a aresta ao nó
				outgoings[j].setSource(node);//adiciona o nó a aresta
				doneFlows.replace(node.getOutgoingsIds().get(j), (Flow) outgoings[j]);
			}
			node.setIncomings(incomings);
			node.setOutgoings(outgoings);
			
		}
	}


	private static void generateStereotypes(String url, HttpClient httpClient, JSONObject token, String[] stereotypes,
			JSONObject object, ArrayList<String> stereotypesIds) throws IOException, ClientProtocolException, JSONException, HttpException {
		object.put("stereotype", JSONObject.NULL);
		for (int j = 0; j < stereotypesIds.size(); j++) {
			JSONObject element = elementRequest(url, httpClient, token, stereotypesIds.get(j));
			stereotypes[j] = element.getString("name");
			object.accumulate("stereotypes", element.getString("name"));
		}
	}

	private static ActivityDiagram buildActivityDiagram(String url, HttpClient httpClient, JSONObject token, String idDiagram, DiagramBuilder diagramBuilder) throws ParseException, IOException, ClientProtocolException, JSONException, HttpException {
		JSONObject element = elementRequest(url, httpClient, token, idDiagram);
		ActivityDiagram activityDiagram = null;
		
		activityDiagram = diagramBuilder.BuildElement(element);
		return activityDiagram;
	}
	
	private static Type generateType(String url, TypeBuilder typeBuilder, HttpClient httpClient, JSONObject token,
			JSONObject object, Activity act) throws IOException, ClientProtocolException, JSONException, HttpException {
		List<String> ownedElements = new ArrayList<>();
		String definition = "";
		JSONObject jsonObjectAux;
		if(AdapterUtils.primitives.containsKey(object.getString("typeId"))) {
			String name = AdapterUtils.primitives.get(object.getString("typeId"))+"_"+AdapterUtils.nameResolver(act.getName());	
			Type base = typeBuilder.buildElement(object.getString("typeId"), /*AdapterUtils.primitives.get(object.getString("typeId"))*/name,
					definition, new String[0], object.getString("typeId"));
			String activityOwnerId = searchOwnerActivity(url,httpClient,token,object.getString("ownerId"));		
			String types = "";
			if(createdTypes.containsKey(activityOwnerId)) {
				types = createdTypes.get(activityOwnerId);
				types += ","+name;
				createdTypes.put(activityOwnerId, types);
			}else {
				createdTypes.put(activityOwnerId,name);
			}	
			return base;
		}else{
			JSONObject element = elementRequest(url, httpClient, token, object.getString("typeId"));
			
			Type base = (Type) typeBuilder.BuildElement(element);
			base.setName("type_"+base.getName()+"_"+AdapterUtils.nameResolver(act.getName()));
			ArrayList<String> dataTypeTypes = new ArrayList<String>();
			
			if(element.getString("type").equals("Enumeration")) {
				ownedElements = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("ownedLiteralIds"));
				//base.setName("enum "+AdapterUtils.nameResolver(base.getName()));
			}else if(element.getString("type").equals("DataType")) {
				ownedElements = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("ownedAttributeIds"));
				//base.setName("datatype " + AdapterUtils.nameResolver(base.getName()));
			}else if(!element.has("typeId") && element.getJSONArray("_appliedStereotypeIds") != null){//pegar tipo pelo stereotype
				ownedElements = AdapterUtils.JSONArrayToArrayList(element.getJSONArray("_appliedStereotypeIds"));
				List<String> stereotypesOwnedElements = new ArrayList<>();
				for(String ownedElement:ownedElements) {
					JSONObject stereotypes = elementRequest(url, httpClient, token, ownedElement);
					//element = elementRequest(url, httpClient, token, ownedElement);
					AdapterUtils.JSONArrayToArrayList(stereotypes.getJSONArray("ownedAttributeIds"));
					stereotypesOwnedElements.addAll(AdapterUtils.JSONArrayToArrayList(stereotypes.getJSONArray("ownedAttributeIds")));
				}
				ownedElements = stereotypesOwnedElements;
				
			}
			ArrayList<String> typeNames = new ArrayList<>();
			for(String ownedElementId: ownedElements) {
				jsonObjectAux = elementRequest(url, httpClient, token, ownedElementId);
				typeNames.add(jsonObjectAux.getString("name")+"_"+AdapterUtils.nameResolver(act.getName()));
				if(element.getString("type").equals("DataType")) {
					dataTypeTypes.add(jsonObjectAux.getString("typeId"));//metodo provisorio ate descobrir como é o certo
				}
			}
			
			if (typeNames.size()>0) {
				if(element.getString("type").equals("Enumeration")) {
					definition += "enum "+ AdapterUtils.nameResolver(base.getName()) + " = {" + AdapterUtils.nameResolver(typeNames.get(0));
					for (int i = 1; i < typeNames.size(); i++) {
						definition += "," + AdapterUtils.nameResolver(typeNames.get(i));
					}
					definition += "};\n";
	
				}else if(element.getString("type").equals("DataType")) {
					
					if(AdapterUtils.primitives.containsKey(AdapterUtils.nameResolver(dataTypeTypes.get(0)))) {
						definition += "datatype " + AdapterUtils.nameResolver(base.getName() /*element.getString("name")*/) + " = {" 
								+ AdapterUtils.nameResolver(typeNames.get(0)) + ":"+
								AdapterUtils.primitives.get(AdapterUtils.nameResolver(dataTypeTypes.get(0)))+"_"+AdapterUtils.nameResolver(act.getName());
					}else {
						definition += "datatype " + AdapterUtils.nameResolver(element.getString("name")) + "_" + AdapterUtils.nameResolver(act.getName()) + " = {" 
								+ AdapterUtils.nameResolver(typeNames.get(0)) + ":" + AdapterUtils.nameResolver(dataTypeTypes.get(0))+"_"+AdapterUtils.nameResolver(act.getName());
					}
					for (int i = 1; i < typeNames.size(); i++) {
						definition += "; " + AdapterUtils.nameResolver(typeNames.get(i)) + ":" ;
						if(AdapterUtils.primitives.containsKey(AdapterUtils.nameResolver(dataTypeTypes.get(i)))) {
							definition += AdapterUtils.primitives.get(AdapterUtils.nameResolver(dataTypeTypes.get(i)))+"_"+AdapterUtils.nameResolver(act.getName());
						}else {
							definition += AdapterUtils.nameResolver(dataTypeTypes.get(i))+"_"+AdapterUtils.nameResolver(act.getName());
						}
					}
					definition += "}\n";
					
					
					for (int i = 0; i < dataTypeTypes.size(); i++) {
						String activityOwnerId = searchOwnerActivity(url,httpClient,token,object.getString("ownerId"));
						String name = "";
						if(AdapterUtils.primitives.containsKey(AdapterUtils.nameResolver(dataTypeTypes.get(i)))) {
							name = AdapterUtils.primitives.get(AdapterUtils.nameResolver(dataTypeTypes.get(i)))+"_"+AdapterUtils.nameResolver(act.getName());
						}else {
							name = AdapterUtils.nameResolver(dataTypeTypes.get(i))+"_"+AdapterUtils.nameResolver(act.getName());
						}
						String types = "";
						if(createdTypes.containsKey(activityOwnerId)) {
							types = createdTypes.get(activityOwnerId);
							types += ","+name;
							createdTypes.put(activityOwnerId, types);
						}else {
							createdTypes.put(activityOwnerId,name);
						}	
						
					}
				}
			}
			if(activityDiagramTypes.containsKey(act.getId())) {//se esse diagrama ja foi inserido 
				HashMap<String,String> actDefinition = activityDiagramTypes.get(act.getId());
				String[] type = definition.split(" = ");
				if(!actDefinition.containsKey(type[0])) {
					actDefinition.put(type[0], type[1]);
				}
			}else {
				if(definition.length() > 0) {
					HashMap<String,String> actDefinition = new HashMap<>();
					String[] type = definition.split(" = ");
					actDefinition.put(type[0], type[1]);
					activityDiagramTypes.put(act.getId(), actDefinition);
				}
			}
			base.setDefinition(definition);
			return base;
		}
	}

	private static String searchOwnerActivity(String url, HttpClient httpClient, JSONObject token, String id) throws ClientProtocolException, IOException, JSONException, HttpException {
		JSONObject element = elementRequest(url, httpClient, token, id);
		if(element.getString("type").equals("Activity")) {
			return element.getString("id");
		}
		return searchOwnerActivity(url, httpClient, token, element.getString("ownerId"));
	}

	private static void createPin(String url, HttpClient httpClient, JSONObject token, String idPin, ActivityNode newNode, ObjectBuilder objectBuilder, TypeBuilder typeBuilder, Activity act) throws ClientProtocolException, IOException, ParsingException, JSONException, HttpException {
		JSONObject element = elementRequest(url, httpClient, token, idPin);
		Pin newPin = null;

		newPin = (Pin) objectBuilder.BuildElement(element);//cria o pino
		newPin.setOwner((IAction) newNode);// adiciona o dono do pino
		newPin.setActivityId(newNode.getActivityId());
		if(!element.isNull("typeId")) {
			Type base = generateType(url, typeBuilder, httpClient, token, element,act);
			newPin.setBaseType(base);
			newPin.setTypeId(base.getTypeId());
			/*if(newPin.getDefinition().equals("")) {//TODO ver se é assim mesmo
				newPin.setDefinition(base.getDefinition());
			}*/
		}		
		((Action)newNode).addPin(newPin);//adiciona o pino ao node
		doneNodes.put(newPin.getId(), newPin);//salva o pino
	}
	
	private static JSONObject elementRequest(String url, HttpClient httpClient, JSONObject token, String id) throws ClientProtocolException, IOException, JSONException, HttpException  {
		HttpResponse response;
		HttpGet httpGet;
		JSONArray elements;
		httpGet = new HttpGet(url + id);
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
		response = httpClient.execute(httpGet);
		JSONObject element = new JSONObject(EntityUtils.toString(response.getEntity()));
		JSONArray rejectedTest = element.isNull("rejected")?null:element.getJSONArray("rejected");
		if(rejectedTest != null & !rejectedTest.isEmpty()) {
			throw new HttpException(rejectedTest.toString());
		}
		elements = element.getJSONArray("elements");
		element =  ((JSONObject) elements.get(0));
		return element;
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
		AdapterUtils.primitives = new HashMap<>();
		createdTypes = new HashMap<>();
		activityDiagramTypes = new HashMap<>();
		mostExternalDiagram = "";
	}
}
