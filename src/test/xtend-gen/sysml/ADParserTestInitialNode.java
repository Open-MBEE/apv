package sysml;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.sysml.xtext.SysMLStandaloneSetupGenerated;
import org.omg.sysml.xtext.sysml.Namespace;

import com.google.inject.Injector;
import com.ref.parser.activityDiagram.ADParser;
import com.ref.sysml.adapter.ActivityDiagram;
import com.ref.sysml.adapter.AdapterUtils;


public class ADParserTestInitialNode {

	private static Injector injector;
	private static XtextResourceSet resourceSet;
	private static ADParser parser1;
	
	@BeforeAll
	public static void GetDiagram() throws Exception {
		injector = new SysMLStandaloneSetupGenerated().createInjectorAndDoEMFRegistration();
		resourceSet = injector.getInstance(XtextResourceSet.class);
	}
	
	@BeforeEach
	public void clearBuffer() {
		AdapterUtils.clearBuffer();
	}
	
	@Test
	public void TestInitial1() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/initial1.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("start_initial1(id) = update_initial1.id.1!(2-0) -> ((ce_initial1.id.1 -> SKIP) ||| (ce_initial1.id.2 -> SKIP))\n"
					+ "start_initial1_t(id) = start_initial1(id) /\\ END_DIAGRAM_initial1(id)\n"
					+ "act1_initial1(id) = ((ce_initial1.id.2 -> SKIP)); event_act1_initial1.id -> ((ce_initial1.id.3 -> SKIP)); act1_initial1(id)\n"
					+ "act1_initial1_t(id) = act1_initial1(id) /\\ END_DIAGRAM_initial1(id)\n"
					+ "act2_initial1(id) = ((ce_initial1.id.1 -> SKIP)); event_act2_initial1.id -> ((ce_initial1.id.4 -> SKIP)); act2_initial1(id)\n"
					+ "act2_initial1_t(id) = act2_initial1(id) /\\ END_DIAGRAM_initial1(id)\n"
					+ "done_initial1(id) = ((ce_initial1.id.4 -> SKIP) [] (ce_initial1.id.3 -> SKIP)); clear_initial1.id.1 -> SKIP\n"
					+ "done_initial1_t(id) = done_initial1(id) /\\ END_DIAGRAM_initial1(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
}

