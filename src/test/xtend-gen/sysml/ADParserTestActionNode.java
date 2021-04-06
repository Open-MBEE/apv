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


public class ADParserTestActionNode {

	private static Injector injector;
	private static XtextResourceSet resourceSet;
	private static ADParser parser1;
	private static ADParser parser2;
	private static ADParser parser3;
	private static ADParser parser4;
	private static ADParser parser5;
	
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
	public void TestAction1() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/action1.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);			
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("start_action1(id) = update_action1.id.1!(1-0) -> ((ce_action1.id.1 -> SKIP))\n"
					+ "start_action1_t(id) = start_action1(id) /\\ END_DIAGRAM_action1(id)\n"
					+ "act1_action1(id) = ((ce_action1.id.1 -> SKIP)); event_act1_action1.id -> ((ce_action1.id.2 -> SKIP)); act1_action1(id)\n"
					+ "act1_action1_t(id) = act1_action1(id) /\\ END_DIAGRAM_action1(id)\n"
					+ "done_action1(id) = ((ce_action1.id.2 -> SKIP)); clear_action1.id.1 -> SKIP\n"
					+ "done_action1_t(id) = done_action1(id) /\\ END_DIAGRAM_action1(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestAction2() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/action2.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser2.clearBuffer();
	    	
			String actual = parser2.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("start_action2(id) = update_action2.id.1!(1-0) -> ((ce_action2.id.1 -> SKIP))\n"
					+ "start_action2_t(id) = start_action2(id) /\\ END_DIAGRAM_action2(id)\n"
					+ "act1_action2(id) = ((ce_action2.id.1 -> SKIP)); event_act1_action2.id -> update_action2.id.2!(2-1) -> ((ce_action2.id.2 -> SKIP) ||| (ce_action2.id.3 -> SKIP)); act1_action2(id)\n"
					+ "act1_action2_t(id) = act1_action2(id) /\\ END_DIAGRAM_action2(id)\n"
					+ "act2_action2(id) = ((ce_action2.id.3 -> SKIP)); event_act2_action2.id -> ((ce_action2.id.4 -> SKIP)); act2_action2(id)\n"
					+ "act2_action2_t(id) = act2_action2(id) /\\ END_DIAGRAM_action2(id)\n"
					+ "act3_action2(id) = ((ce_action2.id.2 -> SKIP)); event_act3_action2.id -> ((ce_action2.id.5 -> SKIP)); act3_action2(id)\n"
					+ "act3_action2_t(id) = act3_action2(id) /\\ END_DIAGRAM_action2(id)\n"
					+ "done_action2(id) = ((ce_action2.id.5 -> SKIP) [] (ce_action2.id.4 -> SKIP)); clear_action2.id.1 -> SKIP\n"
					+ "done_action2_t(id) = done_action2(id) /\\ END_DIAGRAM_action2(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestAction3() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/action3.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			
			parser3 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser3.clearBuffer();
	    	
			String actual = parser3.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_action3_t(id) = update_action3.id.1!(1-0) -> get_x_action3.id.1?x -> ((oe_1_action3.id!x -> SKIP))\n"
					+ "start_action3(id) = update_action3.id.2!(1-0) -> ((ce_action3.id.1 -> SKIP))\n"
					+ "start_action3_t(id) = start_action3(id) /\\ END_DIAGRAM_action3(id)\n"
					+ "act1_action3(id) = ((ce_action3.id.1 -> SKIP) ||| (oe_1_action3.id?x -> set_x_act1_action3.id.1!x -> SKIP)); event_act1_action3.id -> get_x_act1_action3.id.2?x -> ((((x) >= 0 and (x) <= 1) & oe_2_action3.id!(x) -> SKIP) ||| (((x) >= 0 and (x) <= 1) & oe_3_action3.id!(x) -> SKIP)); act1_action3(id)\n"
					+ "act1_action3_t(id) = ((act1_action3(id) /\\ END_DIAGRAM_action3(id)) [|{|get_x_act1_action3.id,set_x_act1_action3.id,endDiagram_action3.id|}|] Mem_act1_action3_x_t(id,0)) \\{|get_x_act1_action3.id,set_x_act1_action3.id|}\n"
					+ "act2_action3(id) = ((oe_2_action3.id?z -> set_z_act2_action3.id.2!z -> SKIP)); event_act2_action3.id -> get_z_act2_action3.id.3?z -> ((ce_action3.id.2 -> SKIP)); act2_action3(id)\n"
					+ "act2_action3_t(id) = ((act2_action3(id) /\\ END_DIAGRAM_action3(id)) [|{|get_z_act2_action3.id,set_z_act2_action3.id,endDiagram_action3.id|}|] Mem_act2_action3_z_t(id,0)) \\{|get_z_act2_action3.id,set_z_act2_action3.id|}\n"
					+ "act3_action3(id) = ((oe_3_action3.id?w -> set_w_act3_action3.id.3!w -> SKIP)); event_act3_action3.id -> get_w_act3_action3.id.4?w -> ((ce_action3.id.3 -> SKIP)); act3_action3(id)\n"
					+ "act3_action3_t(id) = ((act3_action3(id) /\\ END_DIAGRAM_action3(id)) [|{|get_w_act3_action3.id,set_w_act3_action3.id,endDiagram_action3.id|}|] Mem_act3_action3_w_t(id,0)) \\{|get_w_act3_action3.id,set_w_act3_action3.id|}\n"
					+ "done_action3(id) = ((ce_action3.id.3 -> SKIP) [] (ce_action3.id.2 -> SKIP)); clear_action3.id.1 -> SKIP\n"
					+ "done_action3_t(id) = done_action3(id) /\\ END_DIAGRAM_action3(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestAction4() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/action4.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			
			parser4 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser4.clearBuffer();
	    	
			String actual = parser4.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_action4_t(id) = update_action4.id.1!(1-0) -> get_x_action4.id.1?x -> ((oe_1_action4.id!x -> SKIP))\n"
					+ "parameter_y_action4_t(id) = update_action4.id.2!(1-0) -> get_y_action4.id.2?y -> ((oe_2_action4.id!y -> SKIP))\n"
					+ "act1_action4(id) = ((oe_1_action4.id?x -> set_x_act1_action4.id.1!x -> SKIP) ||| (oe_2_action4.id?y -> set_y_act1_action4.id.2!y -> SKIP)); event_act1_action4.id -> get_y_act1_action4.id.3?y -> set_x_act1_action4.id.3!(y) -> get_x_act1_action4.id.4?x -> get_y_act1_action4.id.5?y -> ((((x) >= 0 and (x) <= 1) & oe_3_action4.id!(x) -> SKIP) ||| (((x) >= 0 and (x) <= 1) & oe_4_action4.id!(x) -> SKIP)); act1_action4(id)\n"
					+ "act1_action4_t(id) = (((act1_action4(id) /\\ END_DIAGRAM_action4(id)) [|{|get_x_act1_action4.id,set_x_act1_action4.id,endDiagram_action4.id|}|] Mem_act1_action4_x_t(id,0)) [|{|get_y_act1_action4.id,set_y_act1_action4.id,endDiagram_action4.id|}|] Mem_act1_action4_y_t(id,0)) \\{|get_x_act1_action4.id,set_x_act1_action4.id,get_y_act1_action4.id,set_y_act1_action4.id|}\n"
					+ "act2_action4(id) = ((oe_4_action4.id?w -> set_w_act2_action4.id.4!w -> SKIP)); event_act2_action4.id -> get_w_act2_action4.id.6?w -> ((ce_action4.id.1 -> SKIP)); act2_action4(id)\n"
					+ "act2_action4_t(id) = ((act2_action4(id) /\\ END_DIAGRAM_action4(id)) [|{|get_w_act2_action4.id,set_w_act2_action4.id,endDiagram_action4.id|}|] Mem_act2_action4_w_t(id,0)) \\{|get_w_act2_action4.id,set_w_act2_action4.id|}\n"
					+ "act3_action4(id) = ((oe_3_action4.id?w -> set_w_act3_action4.id.5!w -> SKIP)); event_act3_action4.id -> get_w_act3_action4.id.7?w -> ((ce_action4.id.2 -> SKIP)); act3_action4(id)\n"
					+ "act3_action4_t(id) = ((act3_action4(id) /\\ END_DIAGRAM_action4(id)) [|{|get_w_act3_action4.id,set_w_act3_action4.id,endDiagram_action4.id|}|] Mem_act3_action4_w_t(id,0)) \\{|get_w_act3_action4.id,set_w_act3_action4.id|}\n"
					+ "done_action4(id) = ((ce_action4.id.2 -> SKIP) [] (ce_action4.id.1 -> SKIP)); clear_action4.id.1 -> SKIP\n"
					+ "done_action4_t(id) = done_action4(id) /\\ END_DIAGRAM_action4(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestAction5() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/action5.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			
			parser5 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser5.clearBuffer();
	    	
			String actual = parser5.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_action5_t(id) = update_action5.id.1!(1-0) -> get_x_action5.id.1?x -> ((oe_1_action5.id!x -> SKIP))\n"
					+ "parameter_y_action5(id) = ((oe_2_action5.id?oe0 -> set_outParam_y_action5.id.1!oe0 -> SKIP)); get_outParam_y_action5.id.2?outParam -> set_y_action5.id.2!outParam -> update_action5.id.2!(0-1) -> parameter_y_action5(id)\n"
					+ "parameter_y_action5_t(id) = ((parameter_y_action5(id) /\\ END_DIAGRAM_action5(id)) [|{|get_outParam_y_action5.id,set_outParam_y_action5.id,endDiagram_action5.id|}|] Mem_y_action5_outParam_t(id,0)) \\{|get_outParam_y_action5.id,set_outParam_y_action5.id|}\n"
					+ "act1_action5(id) = ((oe_1_action5.id?x -> set_x_act1_action5.id.3!x -> SKIP)); event_act1_action5.id -> get_x_act1_action5.id.3?x -> ((((x) >= 0 and (x) <= 1) & oe_2_action5.id!(x) -> SKIP)); act1_action5(id)\n"
					+ "act1_action5_t(id) = ((act1_action5(id) /\\ END_DIAGRAM_action5(id)) [|{|get_x_act1_action5.id,set_x_act1_action5.id,endDiagram_action5.id|}|] Mem_act1_action5_x_t(id,0)) \\{|get_x_act1_action5.id,set_x_act1_action5.id|}\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
}

