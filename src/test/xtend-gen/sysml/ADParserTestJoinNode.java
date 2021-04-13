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
			
			expected.append("join1_start_join1(id) = update_join1.id.1!(2-0) -> ((ce_join1.id.1 -> SKIP) ||| (ce_join1.id.2 -> SKIP))\n"
					+ "join1_start_join1_t(id) = join1_start_join1(id) /\\ END_DIAGRAM_join1(id)\n"
					+ "join1_act1_join1(id) = ((ce_join1.id.1 -> SKIP)); event_join1_act1_join1.id -> ((ce_join1.id.3 -> SKIP)); join1_act1_join1(id)\n"
					+ "join1_act1_join1_t(id) = join1_act1_join1(id) /\\ END_DIAGRAM_join1(id)\n"
					+ "join1_act2_join1(id) = ((ce_join1.id.2 -> SKIP)); event_join1_act2_join1.id -> ((ce_join1.id.4 -> SKIP)); join1_act2_join1(id)\n"
					+ "join1_act2_join1_t(id) = join1_act2_join1(id) /\\ END_DIAGRAM_join1(id)\n"
					+ "join1_joinNode1_join1(id) = ((ce_join1.id.3 -> SKIP) ||| (ce_join1.id.4 -> SKIP)); update_join1.id.2!(1-2) -> ((ce_join1.id.5 -> SKIP)); join1_joinNode1_join1(id)\n"
					+ "join1_joinNode1_join1_t(id) = (join1_joinNode1_join1(id) /\\ END_DIAGRAM_join1(id))\n"
					+ "join1_done_join1(id) = ((ce_join1.id.5 -> SKIP)); clear_join1.id.1 -> SKIP\n"
					+ "join1_done_join1_t(id) = join1_done_join1(id) /\\ END_DIAGRAM_join1(id)\n");
			
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
			
			String actual = parser2.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_join2_t(id) = update_join2.id.1!(2-0) -> get_x_join2.id.1?x -> ((oe_1_join2.id!x -> SKIP) ||| (oe_2_join2.id!x -> SKIP))\n"
					+ "join2_act1_join2(id) = ((oe_1_join2.id?x -> set_x_join2_act1_join2.id.1!x -> SKIP)); event_join2_act1_join2.id -> get_x_join2_act1_join2.id.2?x -> ((ce_join2.id.1 -> SKIP)); join2_act1_join2(id)\n"
					+ "join2_act1_join2_t(id) = ((join2_act1_join2(id) /\\ END_DIAGRAM_join2(id)) [|{|get_x_join2_act1_join2.id,set_x_join2_act1_join2.id,endDiagram_join2.id|}|] Mem_join2_act1_join2_x_t(id,0)) \\{|get_x_join2_act1_join2.id,set_x_join2_act1_join2.id|}\n"
					+ "join2_act2_join2(id) = ((oe_2_join2.id?x -> set_x_join2_act2_join2.id.2!x -> SKIP)); event_join2_act2_join2.id -> get_x_join2_act2_join2.id.3?x -> ((ce_join2.id.2 -> SKIP)); join2_act2_join2(id)\n"
					+ "join2_act2_join2_t(id) = ((join2_act2_join2(id) /\\ END_DIAGRAM_join2(id)) [|{|get_x_join2_act2_join2.id,set_x_join2_act2_join2.id,endDiagram_join2.id|}|] Mem_join2_act2_join2_x_t(id,0)) \\{|get_x_join2_act2_join2.id,set_x_join2_act2_join2.id|}\n"
					+ "join2_joinNode1_join2(id) = ((ce_join2.id.2 -> SKIP) ||| (ce_join2.id.1 -> SKIP)); update_join2.id.2!(1-2) -> ((ce_join2.id.3 -> SKIP)); join2_joinNode1_join2(id)\n"
					+ "join2_joinNode1_join2_t(id) = (join2_joinNode1_join2(id) /\\ END_DIAGRAM_join2(id))\n"
					+ "join2_done_join2(id) = ((ce_join2.id.3 -> SKIP)); clear_join2.id.1 -> SKIP\n"
					+ "join2_done_join2_t(id) = join2_done_join2(id) /\\ END_DIAGRAM_join2(id)\n");
			
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
					+ "join3_start_join3(id) = update_join3.id.2!(1-0) -> ((ce_join3.id.1 -> SKIP))\n"
					+ "join3_start_join3_t(id) = join3_start_join3(id) /\\ END_DIAGRAM_join3(id)\n"
					+ "join3_joinNode1_join3(id) = ((ce_join3.id.1 -> SKIP) ||| ((oe_1_join3.id?oe1 -> set_oe1_join3_joinNode1_join3.id.1!oe1 -> SKIP)); update_join3.id.3!(1-2) -> get_oe1_join3_joinNode1_join3.id.2?oe1 -> ((oe_2_join3.id!oe1 -> SKIP)); join3_joinNode1_join3(id)\n"
					+ "join3_joinNode1_join3_t(id) = ((join3_joinNode1_join3(id) /\\ END_DIAGRAM_join3(id)) [|{|get_oe1_join3_joinNode1_join3,set_oe1_join3_joinNode1_join3,endDiagram_join3.id|}|] Mem_join3_joinNode1_join3_oe1_t(id,0)) \\{|get_oe1_join3_joinNode1_join3,set_oe1_join3_joinNode1_join3|}\n"
					+ "join3_act1_join3(id) = ((oe_2_join3.id?x -> set_x_join3_act1_join3.id.2!x -> SKIP)); event_join3_act1_join3.id -> get_x_join3_act1_join3.id.3?x -> ((ce_join3.id.2 -> SKIP)); join3_act1_join3(id)\n"
					+ "join3_act1_join3_t(id) = ((join3_act1_join3(id) /\\ END_DIAGRAM_join3(id)) [|{|get_x_join3_act1_join3.id,set_x_join3_act1_join3.id,endDiagram_join3.id|}|] Mem_join3_act1_join3_x_t(id,0)) \\{|get_x_join3_act1_join3.id,set_x_join3_act1_join3.id|}\n"
					+ "join3_done_join3(id) = ((ce_join3.id.2 -> SKIP)); clear_join3.id.1 -> SKIP\n"
					+ "join3_done_join3_t(id) = join3_done_join3(id) /\\ END_DIAGRAM_join3(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
}

