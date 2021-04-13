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


public class ADParserTestDecisionNode {

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
	public void TestDecision2() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/decision2.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("decision2_start_decision2(id) = update_decision2.id.1!(1-0) -> ((ce_decision2.id.1 -> SKIP))\n"
					+ "decision2_start_decision2_t(id) = decision2_start_decision2(id) /\\ END_DIAGRAM_decision2(id)\n"
					+ "decision2_decisionNode_decision2(id) = ce_decision2.id.1 -> ((dc -> ce_decision2.id.2 -> SKIP) [] (dc -> ce_decision2.id.3 -> SKIP)); decision2_decisionNode_decision2(id)\n"
					+ "decision2_decisionNode_decision2_t(id) = decision2_decisionNode_decision2(id) /\\ END_DIAGRAM_decision2(id) \\{|dc|}\n"
					+ "decision2_act1_decision2(id) = ((ce_decision2.id.2 -> SKIP)); event_decision2_act1_decision2.id -> ((ce_decision2.id.4 -> SKIP)); decision2_act1_decision2(id)\n"
					+ "decision2_act1_decision2_t(id) = decision2_act1_decision2(id) /\\ END_DIAGRAM_decision2(id)\n"
					+ "decision2_act2_decision2(id) = ((ce_decision2.id.3 -> SKIP)); event_decision2_act2_decision2.id -> ((ce_decision2.id.5 -> SKIP)); decision2_act2_decision2(id)\n"
					+ "decision2_act2_decision2_t(id) = decision2_act2_decision2(id) /\\ END_DIAGRAM_decision2(id)\n"
					+ "decision2_done_decision2(id) = ((ce_decision2.id.5 -> SKIP) [] (ce_decision2.id.4 -> SKIP)); clear_decision2.id.1 -> SKIP\n"
					+ "decision2_done_decision2_t(id) = decision2_done_decision2(id) /\\ END_DIAGRAM_decision2(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestDecision3() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/decision3.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser2.clearBuffer();
			
			String actual = parser2.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_z_decision3_t(id) = update_decision3.id.1!(1-0) -> get_z_decision3.id.1?z -> ((oe_1_decision3.id!z -> SKIP))\n"
					+ "decision3_decisionNode_decision3(id) = oe_1_decision3.id?z -> (z <= 0 & (dc -> oe_2_decision3.id!z -> SKIP) [] z > 0 & (dc -> oe_3_decision3.id!z -> SKIP)); decision3_decisionNode_decision3(id)\n"
					+ "decision3_decisionNode_decision3_t(id) = (decision3_decisionNode_decision3(id) /\\ END_DIAGRAM_decision3(id)) \\{|dc|}\n"
					+ "decision3_act1_decision3(id) = ((oe_3_decision3.id?z -> set_z_decision3_act1_decision3.id.1!z -> SKIP)); event_decision3_act1_decision3.id -> get_z_decision3_act1_decision3.id.2?z -> ((ce_decision3.id.1 -> SKIP)); decision3_act1_decision3(id)\n"
					+ "decision3_act1_decision3_t(id) = ((decision3_act1_decision3(id) /\\ END_DIAGRAM_decision3(id)) [|{|get_z_decision3_act1_decision3.id,set_z_decision3_act1_decision3.id,endDiagram_decision3.id|}|] Mem_decision3_act1_decision3_z_t(id,0)) \\{|get_z_decision3_act1_decision3.id,set_z_decision3_act1_decision3.id|}\n"
					+ "decision3_act2_decision3(id) = ((oe_2_decision3.id?z -> set_z_decision3_act2_decision3.id.2!z -> SKIP)); event_decision3_act2_decision3.id -> get_z_decision3_act2_decision3.id.3?z -> ((ce_decision3.id.2 -> SKIP)); decision3_act2_decision3(id)\n"
					+ "decision3_act2_decision3_t(id) = ((decision3_act2_decision3(id) /\\ END_DIAGRAM_decision3(id)) [|{|get_z_decision3_act2_decision3.id,set_z_decision3_act2_decision3.id,endDiagram_decision3.id|}|] Mem_decision3_act2_decision3_z_t(id,0)) \\{|get_z_decision3_act2_decision3.id,set_z_decision3_act2_decision3.id|}\n"
					+ "decision3_done_decision3(id) = ((ce_decision3.id.2 -> SKIP) [] (ce_decision3.id.1 -> SKIP)); clear_decision3.id.1 -> SKIP\n"
					+ "decision3_done_decision3_t(id) = decision3_done_decision3(id) /\\ END_DIAGRAM_decision3(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestDecision6() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/decision6.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser3 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser3.clearBuffer();
			
			String actual = parser3.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_decision6_t(id) = update_decision6.id.1!(1-0) -> get_x_decision6.id.1?x -> ((oe_1_decision6.id!x -> SKIP))\n"
					+ "decision6_decisionNode1_decision6(id) = oe_1_decision6.id?x -> (x > 0 & (dc -> oe_2_decision6.id!x -> SKIP) [] x == 0 & (dc -> oe_3_decision6.id!x -> SKIP)); decision6_decisionNode1_decision6(id)\n"
					+ "decision6_decisionNode1_decision6_t(id) = (decision6_decisionNode1_decision6(id) /\\ END_DIAGRAM_decision6(id)) \\{|dc|}\n"
					+ "decision6_act1_decision6(id) = ((oe_4_decision6.id?x -> set_x_decision6_act1_decision6.id.1!x -> SKIP)); event_decision6_act1_decision6.id -> get_x_decision6_act1_decision6.id.2?x -> ((ce_decision6.id.1 -> SKIP)); decision6_act1_decision6(id)\n"
					+ "decision6_act1_decision6_t(id) = ((decision6_act1_decision6(id) /\\ END_DIAGRAM_decision6(id)) [|{|get_x_decision6_act1_decision6.id,set_x_decision6_act1_decision6.id,endDiagram_decision6.id|}|] Mem_decision6_act1_decision6_x_t(id,0)) \\{|get_x_decision6_act1_decision6.id,set_x_decision6_act1_decision6.id|}\n"
					+ "decision6_mergeNode1_decision6(id) = ((oe_3_decision6.id?oe0 -> set_mergeMem_decision6_mergeNode1_decision6.id.2!oe0 -> SKIP) [] (oe_5_decision6.id?oe1 -> set_mergeMem_decision6_mergeNode1_decision6.id.3!oe1 -> SKIP)); get_mergeMem_decision6_mergeNode1_decision6.id.3?mergeMem -> oe_4_decision6.id!mergeMem -> decision6_mergeNode1_decision6(id)\n"
					+ "decision6_mergeNode1_decision6_t(id) = ((decision6_mergeNode1_decision6(id) /\\ END_DIAGRAM_decision6(id)) [|{|get_mergeMem_decision6_mergeNode1_decision6,set_mergeMem_decision6_mergeNode1_decision6,endDiagram_decision6|}|] Mem_decision6_mergeNode1_decision6_mergeMem_t(id,0)) \\{|get_mergeMem_decision6_mergeNode1_decision6,set_mergeMem_decision6_mergeNode1_decision6|}\n"
					+ "decision6_decisionNode3_decision6(id) = oe_2_decision6.id?x -> (x == 1 & (dc -> oe_5_decision6.id!x -> SKIP) [] true & (dc -> oe_6_decision6.id!x -> SKIP)); decision6_decisionNode3_decision6(id)\n"
					+ "decision6_decisionNode3_decision6_t(id) = (decision6_decisionNode3_decision6(id) /\\ END_DIAGRAM_decision6(id)) \\{|dc|}\n"
					+ "decision6_done_decision6(id) = ((ce_decision6.id.1 -> SKIP) [] (oe_6_decision6.id?x -> SKIP)); clear_decision6.id.1 -> SKIP\n"
					+ "decision6_done_decision6_t(id) = decision6_done_decision6(id) /\\ END_DIAGRAM_decision6(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
}

