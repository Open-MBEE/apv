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


public class ADParserTestJoinNode {

	private static Injector injector;
	private static XtextResourceSet resourceSet;
	private static ADParser parser1;
	private static ADParser parser2;
	private static ADParser parser3;
	
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
	public void TestJoin1() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/join1.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("start_join1(id) = update_join1.id.1!(2-0) -> ((ce_join1.id.1 -> SKIP) ||| (ce_join1.id.2 -> SKIP))\n"
					+ "start_join1_t(id) = start_join1(id) /\\ END_DIAGRAM_join1(id)\n"
					+ "act1_join1(id) = ((ce_join1.id.2 -> SKIP)); event_act1_join1.id -> ((ce_join1.id.3 -> SKIP)); act1_join1(id)\n"
					+ "act1_join1_t(id) = act1_join1(id) /\\ END_DIAGRAM_join1(id)\n"
					+ "act2_join1(id) = ((ce_join1.id.1 -> SKIP)); event_act2_join1.id -> ((ce_join1.id.4 -> SKIP)); act2_join1(id)\n"
					+ "act2_join1_t(id) = act2_join1(id) /\\ END_DIAGRAM_join1(id)\n"
					+ "joinNode1_join1(id) = ((ce_join1.id.4 -> SKIP) ||| (ce_join1.id.3 -> SKIP)); update_join1.id.2!(1-2) -> ((ce_join1.id.5 -> SKIP)); joinNode1_join1(id)\n"
					+ "joinNode1_join1_t(id) = (joinNode1_join1(id) /\\ END_DIAGRAM_join1(id))\n"
					+ "done_join1(id) = ((ce_join1.id.5 -> SKIP)); clear_join1.id.1 -> SKIP\n"
					+ "done_join1_t(id) = done_join1(id) /\\ END_DIAGRAM_join1(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestJoin2() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/join2.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser2.clearBuffer();
			
			String actual = parser2.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestJoin3() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/join3.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser3 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser3.clearBuffer();
			
			String actual = parser3.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_join3_t(id) = update_join3.id.1!(1-0) -> get_x_join3.id.1?x -> ((oe_1_join3.id!x -> SKIP))\n"
					+ "start_join3(id) = update_join3.id.2!(1-0) -> ((ce_join3.id.1 -> SKIP))\n"
					+ "start_join3_t(id) = start_join3(id) /\\ END_DIAGRAM_join3(id)\n"
					+ "joinNode1_join3(id) = ((ce_join3.id.1 -> SKIP) ||| ((oe_1_join3.id?oe1 -> set_oe1_joinNode1_join3.id.1!oe1 -> SKIP)); update_join3.id.3!(1-2) -> get_oe1_joinNode1_join3.id.2?oe1 -> ((oe_2_join3.id!oe1 -> SKIP)); joinNode1_join3(id)\n"
					+ "joinNode1_join3_t(id) = ((joinNode1_join3(id) /\\ END_DIAGRAM_join3(id)) [|{|get_oe1_joinNode1_join3,set_oe1_joinNode1_join3,endDiagram_join3.id|}|] Mem_joinNode1_join3_oe1_t(id,0)) \\{|get_oe1_joinNode1_join3,set_oe1_joinNode1_join3|}\n"
					+ "act1_join3(id) = ((oe_2_join3.id?x -> set_x_act1_join3.id.2!x -> SKIP)); event_act1_join3.id -> get_x_act1_join3.id.3?x -> ((ce_join3.id.2 -> SKIP)); act1_join3(id)\n"
					+ "act1_join3_t(id) = ((act1_join3(id) /\\ END_DIAGRAM_join3(id)) [|{|get_x_act1_join3.id,set_x_act1_join3.id,endDiagram_join3.id|}|] Mem_act1_join3_x_t(id,0)) \\{|get_x_act1_join3.id,set_x_act1_join3.id|}\n"
					+ "done_join3(id) = ((ce_join3.id.2 -> SKIP)); clear_join3.id.1 -> SKIP\n"
					+ "done_join3_t(id) = done_join3(id) /\\ END_DIAGRAM_join3(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
}

