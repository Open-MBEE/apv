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


public class ADParserTestForkNode {

	private static Injector injector;
	private static XtextResourceSet resourceSet;
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
	
	@Test
	public void TestFork1() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/fork1.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("start_fork1(id) = update_fork1.id.1!(1-0) -> ((ce_fork1.id.1 -> SKIP))\n"
					+ "start_fork1_t(id) = start_fork1(id) /\\ END_DIAGRAM_fork1(id)\n"
					+ "forkNode1_fork1(id) = ce_fork1.id.1 -> update_fork1.id.2!(2-1) -> ((ce_fork1.id.2 -> SKIP) ||| (ce_fork1.id.3 -> SKIP)); forkNode1_fork1(id)\n"
					+ "forkNode1_fork1_t(id) = forkNode1_fork1(id) /\\ END_DIAGRAM_fork1(id)\n"
					+ "act1_fork1(id) = ((ce_fork1.id.3 -> SKIP)); event_act1_fork1.id -> ((ce_fork1.id.4 -> SKIP)); act1_fork1(id)\n"
					+ "act1_fork1_t(id) = act1_fork1(id) /\\ END_DIAGRAM_fork1(id)\n"
					+ "act2_fork1(id) = ((ce_fork1.id.2 -> SKIP)); event_act2_fork1.id -> ((ce_fork1.id.5 -> SKIP)); act2_fork1(id)\n"
					+ "act2_fork1_t(id) = act2_fork1(id) /\\ END_DIAGRAM_fork1(id)\n"
					+ "done_fork1(id) = ((ce_fork1.id.5 -> SKIP) [] (ce_fork1.id.4 -> SKIP)); clear_fork1.id.1 -> SKIP\n"
					+ "done_fork1_t(id) = done_fork1(id) /\\ END_DIAGRAM_fork1(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestFork2() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/fork2.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser2.clearBuffer();
			
			String actual = parser2.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_fork2_t(id) = update_fork2.id.1!(1-0) -> get_x_fork2.id.1?x -> ((oe_1_fork2.id!x -> SKIP))\n"
					+ "forkNode1_fork2(id) = oe_1_fork2.id?x -> update_fork2.id.2!(2-1) -> ((oe_2_fork2.id!x -> SKIP) ||| (oe_3_fork2.id!x -> SKIP)); forkNode1_fork2(id)\n"
					+ "forkNode1_fork2_t(id) = forkNode1_fork2(id) /\\ END_DIAGRAM_fork2(id)\n"
					+ "act1_fork2(id) = ((oe_3_fork2.id?x -> set_x_act1_fork2.id.1!x -> SKIP)); event_act1_fork2.id -> get_x_act1_fork2.id.2?x -> ((ce_fork2.id.1 -> SKIP)); act1_fork2(id)\n"
					+ "act1_fork2_t(id) = ((act1_fork2(id) /\\ END_DIAGRAM_fork2(id)) [|{|get_x_act1_fork2.id,set_x_act1_fork2.id,endDiagram_fork2.id|}|] Mem_act1_fork2_x_t(id,0)) \\{|get_x_act1_fork2.id,set_x_act1_fork2.id|}\n"
					+ "act2_fork2(id) = ((oe_2_fork2.id?x -> set_x_act2_fork2.id.2!x -> SKIP)); event_act2_fork2.id -> get_x_act2_fork2.id.3?x -> ((ce_fork2.id.2 -> SKIP)); act2_fork2(id)\n"
					+ "act2_fork2_t(id) = ((act2_fork2(id) /\\ END_DIAGRAM_fork2(id)) [|{|get_x_act2_fork2.id,set_x_act2_fork2.id,endDiagram_fork2.id|}|] Mem_act2_fork2_x_t(id,0)) \\{|get_x_act2_fork2.id,set_x_act2_fork2.id|}\n"
					+ "done_fork2(id) = ((ce_fork2.id.2 -> SKIP) [] (ce_fork2.id.1 -> SKIP)); clear_fork2.id.1 -> SKIP\n"
					+ "done_fork2_t(id) = done_fork2(id) /\\ END_DIAGRAM_fork2(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
}

