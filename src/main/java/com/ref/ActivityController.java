package com.ref;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
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

import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.IDiagram;
import com.ref.astah.adapter.Activity;
import com.ref.astah.adapter.ActivityDiagram;
import com.ref.astah.adapter.AdapterUtils;
import com.ref.astah.traceability.CounterExampleAstah;
import com.ref.exceptions.FDRException;
import com.ref.exceptions.ParsingException;
import com.ref.exceptions.WellFormedException;
import com.ref.fdr.FdrWrapper;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.parser.activityDiagram.ADParser;
import com.ref.parser.activityDiagram.ADUtils;
import com.ref.traceability.activityDiagram.CounterExampleBuilder;
import com.ref.ui.CheckingProgressBar;
import com.ref.wellformedness.WellFormedness;


public class ActivityController {
	public static enum VerificationType { DEADLOCK, DETERMINISM};
	private static Properties prop;
	
	public static boolean firstInteration = true;
	
	public static final String FDR3_PROPERTY_FILE = System.getProperty("user.home") + System.getProperty("file.separator") + "ref.properties";
	public static final String FDR3_LOCATION_PROPERTY = "fdr3_location";
	public static final String FDR3_JAR_LOCATION_PROPERTY = "fdr3_jar_location";
	
	private static ActivityController controller;
	
	
	private ActivityController() throws IOException {
		File propertyFile = new File(FDR3_PROPERTY_FILE);
		if (!propertyFile.exists()) {
			propertyFile.createNewFile();
			prop = new Properties();
			prop.load(new FileInputStream(propertyFile));
			prop.setProperty(FDR3_LOCATION_PROPERTY, "");
			prop.setProperty(FDR3_JAR_LOCATION_PROPERTY, "");
			prop.store(new FileOutputStream(propertyFile), null);
		} else {
			prop = new Properties();
			prop.load(new FileInputStream(propertyFile));
		}
	
	}
	
	public static ActivityController getInstance() throws IOException {
		if (controller == null) {
			controller = new ActivityController();
		} 
		return controller;
	}
	
	public void OpenMBEEInvocation(String activityId,VerificationType type, CheckingProgressBar progressBar) throws ClientProtocolException, IOException{
		
		/*fazer estilo ADDefineNodesActionAndControl 
		 * activity = communication.buildActivity(url,login,password,idActivity);
		 * 
		 * */
		
		//TODO ser ajustado(sair daqui e ir pro communication)
		HttpClient httpClient = null;
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
		
		
		httpGet = new HttpGet("http://18.188.75.184/projects/tmt/refs/master/elements/_18_0_2_baa02e2_1430169721070_811705_501834");
		
		httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token.get("token"));
		
		response = httpClient.execute(httpGet);
		
		JSONObject edge = new JSONObject(EntityUtils.toString(response.getEntity()));
		System.out.println(edge.toString());
		
	}
	
	public void AstahInvocation(IDiagram diagram, VerificationType type, CheckingProgressBar progressBar) throws FDRException,ParsingException, FileNotFoundException, UnsupportedEncodingException, WellFormedException{		
			Activity activity = new Activity(((IActivityDiagram) diagram).getActivity());
			ActivityDiagram activityDiagram = new ActivityDiagram( (IActivityDiagram) diagram);
			activity.setActivityDiagram(activityDiagram);
			
			HashMap<IActivity, List<String>> counterExample = checkProperty(activity,activityDiagram,type,progressBar);
			if(counterExample != null) {
				CounterExampleAstah.createCounterExample(counterExample, diagram, type);//"our copy", astah original, counter example type
			}
	}
	
	public HashMap<IActivity, List<String>> checkProperty(Activity activity,
			ActivityDiagram activityDiagram, VerificationType type, CheckingProgressBar progressBar) throws FileNotFoundException, UnsupportedEncodingException, ParsingException, FDRException, WellFormedException{
		boolean wellformed = WellFormedness.WellFormed();

		settingFDR();
		
		if (wellformed) {
			ADParser parser = new ADParser(activity, activityDiagram.getName(),activityDiagram);
			String diagramCSP = parser.parserDiagram();
			
			String fs = System.getProperty("file.separator");
			String uh = System.getProperty("user.home");
			File directory = new File(uh+fs+"TempAstah");
			directory.mkdirs();
			PrintWriter writer;
			
			writer = new PrintWriter(uh + fs + "TempAstah" + fs + ADUtils.nameResolver(activity.getName()) + ".csp", "UTF-8");
			writer.print(diagramCSP);
			writer.flush();
			writer.close();
			
			List<String> traceCounterExample = null;
			if (type == VerificationType.DEADLOCK) {
				AdapterUtils.resetStatics();
				try {
					traceCounterExample = FdrWrapper.getInstance().checkDeadlock(uh + fs + "TempAstah" + fs + ADUtils.nameResolver(activityDiagram.getName()) + ".csp", parser, activityDiagram.getName(), progressBar);
				} catch (Exception e) {
					AdapterUtils.resetStatics();
					throw new FDRException("An error occurred during checking deadlock.");
				}
			} else {
				AdapterUtils.resetStatics();
				try {
					traceCounterExample = FdrWrapper.getInstance().checkDeterminism(uh + fs + "TempAstah" + fs + ADUtils.nameResolver(activityDiagram.getName()) + ".csp", parser, activityDiagram.getName(), progressBar);
				} catch (Exception e) {
					AdapterUtils.resetStatics();
					throw new FDRException("An error occurred during checking non-determinism.");
				}
			} 
			
			
			if (traceCounterExample!=null && !traceCounterExample.isEmpty()) {//If there is a trace
				CounterExampleBuilder cb = new CounterExampleBuilder(traceCounterExample, activity,parser.getAlphabetAD(),ADParser.IdSignals);
				/* responsible for link the CSP counter example events to the ID of the diagram element of each diagram  
				 * */
				return cb.createCounterExample(activity);//who should be painted in each diagram
				/* creates a copy of the diagrams and paints the elements that is on the counter example 
				 * */
			}
			return null;
		}else {
			//send not wellformed message
			throw new WellFormedException("not wellFormed");
		}
	}

	private void settingFDR() throws FDRException {
		File fdrProperty = new File(FDR3_PROPERTY_FILE);

		if (fdrProperty.exists()) {
			Properties prop = new Properties();
			try {
				prop.load(new FileInputStream(fdrProperty));
			} catch (IOException e1) {
				e1.printStackTrace();
				throw new FDRException(e1.getMessage());
			}
			FdrWrapper wrapper = FdrWrapper.getInstance();
			String pathFDR = prop.getProperty(FDR3_JAR_LOCATION_PROPERTY);

			if (!pathFDR.isEmpty()) {
				File fdrLocation = new File(pathFDR);

				if (fdrLocation.exists()) {
					wrapper.loadFDR(pathFDR);
					if (firstInteration) { // carrega as classes um unica vez
						try {
							wrapper.loadClasses();
						} catch (MalformedURLException | ClassNotFoundException e) {
							e.printStackTrace();
							throw new FDRException(e.getMessage());
						}
						firstInteration = false;
					}
				} else {
					throw new FDRException("FDR not found, set FDR location in Tools > Properties Plugin Configuration > FDR Location.");
				}
			} else {
				throw new FDRException("FDR not found, set FDR location in Tools > Properties Plugin Configuration > FDR Location.");
			}
		} else {
			throw new FDRException("FDR not found, set FDR location in Tools > Properties Plugin Configuration > FDR Location.");
		}
			
				
		
	}

	public void setFDRLocation(String location) throws FDRException {
		String filename = null;
		if (System.getProperty("os.name").startsWith("Mac OS X")) {
			filename = location + "/Contents/Frameworks/fdr.jar";

		} else if (System.getProperty("os.name").contains("Win")) {
			filename = location + "\\bin\\fdr.jar";
		}else {
			filename = location + "/lib/fdr.jar";
		}
		File fdrlib = new File(filename);
		if (!fdrlib.exists()) {
			throw new FDRException("Library fdr.jar not found!");
			
		} else {
			try {
				prop.setProperty(FDR3_LOCATION_PROPERTY, location);
				prop.setProperty(FDR3_JAR_LOCATION_PROPERTY, filename);
				FdrWrapper wrapper = FdrWrapper.getInstance();
				wrapper.loadFDR(filename);
				wrapper.loadClasses();
				prop.store(new FileOutputStream(new File(FDR3_PROPERTY_FILE)), null);
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			

		}
		
	}

	public String getFDRLocation() {
		return prop.getProperty(FDR3_LOCATION_PROPERTY);
	}
}
