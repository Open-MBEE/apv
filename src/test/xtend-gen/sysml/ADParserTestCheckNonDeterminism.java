package sysml;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.sysml.xtext.SysMLStandaloneSetupGenerated;
import org.omg.sysml.xtext.sysml.Namespace;

import com.google.inject.Injector;
import com.ref.ActivityController.VerificationType;
import com.ref.exceptions.ParsingException;
import com.ref.exceptions.WellFormedException;
import com.ref.fdr.FdrWrapper;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.parser.activityDiagram.ADParser;
import com.ref.sysml.adapter.ActivityDiagram;
import com.ref.sysml.adapter.AdapterUtils;
import com.ref.sysml.traceability.CounterExampleSysml;
import com.ref.traceability.activityDiagram.CounterExampleBuilder;
import com.ref.ui.CheckingProgressBar;


public class ADParserTestCheckNonDeterminism {

	private static Injector injector;
	private static XtextResourceSet resourceSet;
	public static boolean loadClassFDR = false;
	private static ADParser parser1;
	private static ADParser parser2;
	
	@BeforeAll
	public static void GetDiagram() throws Exception {
		injector = new SysMLStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
		resourceSet = injector.getInstance(XtextResourceSet.class);
	}
	
	@BeforeEach
	public void clearBuffer() {
		AdapterUtils.clearBuffer();
	}
	
	@BeforeAll
	public static void loadClassesFDR() {
		FdrWrapper wrapper = FdrWrapper.getInstance();
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("win") >= 0) {
			wrapper.loadFDR("C:\\Program Files\\FDR\\bin\\fdr.jar");
		} else if (os.indexOf("mac") >= 0) {
			wrapper.loadFDR("/Applications/FDR4.app/Contents/Frameworks/fdr.jar");
		}

		try {
			wrapper.loadClasses();
		} catch (MalformedURLException | ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		loadClassFDR = true;
	}
	
	@Test
	public void TestNonDeterminism1() throws WellFormedException, ParsingException, IOException {
		String path = "./src/test/resources/sysml/nonDeterminism1.sysml";
		Resource resource = resourceSet.getResource(URI.createFileURI(path), true);
		Namespace model = (Namespace) resource.getContents().get(0);
		ActivityDiagram ad = new ActivityDiagram(model);			
		parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
		parser1.clearBuffer();
		
		String sysmlfile = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

		String diagramCSP = parser1.parserDiagram();
		
		String fs = System.getProperty("file.separator");
		String uh = System.getProperty("user.home");
		File directory = new File(uh+fs+"TempSysml");
		directory.mkdirs();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(uh + fs + "TempSysml" + fs + "nonDeterminism1.csp", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        writer.print(diagramCSP);
		writer.flush();
		writer.close();

        List<String> actual = new ArrayList<String>();
        HashMap<IActivity, List<String>> counterExample = null;
        
        try {
			CheckingProgressBar progressBar = new CheckingProgressBar();
			progressBar.setNewTitle("Checking deadlock");
			progressBar.setAssertion(0);
            actual = FdrWrapper.getInstance().checkDeterminism(uh + fs + "TempSysml" + fs + "nonDeterminism1.csp", parser1, "nonDeterminism1", progressBar);
            
            if (actual !=null && !actual.isEmpty()) {//If there is a trace
				CounterExampleBuilder cb = new CounterExampleBuilder(actual, ad.getActivity(), parser1.getAlphabetAD(), ADParser.IdSignals);
				/* responsible for link the CSP counter example events to the ID of the diagram element of each diagram  
				 * */
				counterExample = cb.createCounterExample(ad.getActivity());//who should be painted in each diagram
				/* creates a copy of the diagrams and paints the elements that is on the counter example 
				 * */
			}
            
            if(counterExample != null) {
				CounterExampleSysml.createCounterExample(sysmlfile, counterExample, ad.getActivity().getActivityNodes(), AdapterUtils.edges.values(), VerificationType.DETERMINISM);
			}
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

		assertFalse(actual.isEmpty());
		
	  }
	
	@Test
	public void TestChargeBattery() throws WellFormedException, ParsingException, IOException {
		String path = "./src/test/resources/sysml/chargeBattery.sysml";
		Resource resource = resourceSet.getResource(URI.createFileURI(path), true);
		Namespace model = (Namespace) resource.getContents().get(0);
		ActivityDiagram ad = new ActivityDiagram(model);			
		parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
		parser2.clearBuffer();
		
		String sysmlfile = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);

		String diagramCSP = parser2.parserDiagram();
		
		String fs = System.getProperty("file.separator");
		String uh = System.getProperty("user.home");
		File directory = new File(uh+fs+"TempSysml");
		directory.mkdirs();

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(uh + fs + "TempSysml" + fs + "chargeBattery.csp", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        
        writer.print(diagramCSP);
		writer.flush();
		writer.close();

        List<String> actual = new ArrayList<String>();
        HashMap<IActivity, List<String>> counterExample = null;
        
        try {
			CheckingProgressBar progressBar = new CheckingProgressBar();
			progressBar.setNewTitle("Checking deadlock");
			progressBar.setAssertion(0);
            actual = FdrWrapper.getInstance().checkDeterminism(uh + fs + "TempSysml" + fs + "chargeBattery.csp", parser2, "chargeBattery", progressBar);
            
            if (actual !=null && !actual.isEmpty()) {//If there is a trace
				CounterExampleBuilder cb = new CounterExampleBuilder(actual, ad.getActivity(), parser2.getAlphabetAD(), ADParser.IdSignals);
				/* responsible for link the CSP counter example events to the ID of the diagram element of each diagram  
				 * */
				counterExample = cb.createCounterExample(ad.getActivity());//who should be painted in each diagram
				/* creates a copy of the diagrams and paints the elements that is on the counter example 
				 * */
			}
            
            if(counterExample != null) {
				CounterExampleSysml.createCounterExample(sysmlfile, counterExample, ad.getActivity().getActivityNodes(), AdapterUtils.edges.values(), VerificationType.DETERMINISM);
			}
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }

		assertFalse(actual.isEmpty());
		
	  }
	
}

