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


public class ADParserTestMergeNode {

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
	public void TestMerge1() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/merge1.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("merge1_start_merge1(id) = update_merge1.id.1!(2-0) -> ((ce_merge1.id.1 -> SKIP) ||| (ce_merge1.id.2 -> SKIP))\n"
					+ "merge1_start_merge1_t(id) = merge1_start_merge1(id) /\\ END_DIAGRAM_merge1(id)\n"
					+ "merge1_act1_merge1(id) = ((ce_merge1.id.1 -> SKIP)); event_merge1_act1_merge1.id -> ((ce_merge1.id.3 -> SKIP)); merge1_act1_merge1(id)\n"
					+ "merge1_act1_merge1_t(id) = merge1_act1_merge1(id) /\\ END_DIAGRAM_merge1(id)\n"
					+ "merge1_act2_merge1(id) = ((ce_merge1.id.2 -> SKIP)); event_merge1_act2_merge1.id -> ((ce_merge1.id.4 -> SKIP)); merge1_act2_merge1(id)\n"
					+ "merge1_act2_merge1_t(id) = merge1_act2_merge1(id) /\\ END_DIAGRAM_merge1(id)\n"
					+ "merge1_mergeNode1_merge1(id) = ((ce_merge1.id.4 -> SKIP) [] (ce_merge1.id.3 -> SKIP)); ce_merge1.id.5 -> merge1_mergeNode1_merge1(id)\n"
					+ "merge1_mergeNode1_merge1_t(id) = merge1_mergeNode1_merge1(id) /\\ END_DIAGRAM_merge1(id)\n"
					+ "merge1_done_merge1(id) = ((ce_merge1.id.5 -> SKIP)); clear_merge1.id.1 -> SKIP\n"
					+ "merge1_done_merge1_t(id) = merge1_done_merge1(id) /\\ END_DIAGRAM_merge1(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestMerge2() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/merge3.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser2.clearBuffer();
			
			String actual = parser2.defineNodesActionAndControl();
			StringBuffer expected = new StringBuffer();
			
			expected.append("parameter_x_merge3_t(id) = update_merge3.id.1!(1-0) -> get_x_merge3.id.1?x -> ((oe_1_merge3.id!x -> SKIP))\n"
					+ "parameter_y_merge3_t(id) = update_merge3.id.2!(1-0) -> get_y_merge3.id.2?y -> ((oe_2_merge3.id!y -> SKIP))\n"
					+ "merge3_mergeNode1_merge3(id) = ((oe_1_merge3.id?oe0 -> set_mergeMem_merge3_mergeNode1_merge3.id.1!oe0 -> SKIP) [] (oe_2_merge3.id?oe1 -> set_mergeMem_merge3_mergeNode1_merge3.id.2!oe1 -> SKIP)); get_mergeMem_merge3_mergeNode1_merge3.id.3?mergeMem -> oe_3_merge3.id!mergeMem -> merge3_mergeNode1_merge3(id)\n"
					+ "merge3_mergeNode1_merge3_t(id) = ((merge3_mergeNode1_merge3(id) /\\ END_DIAGRAM_merge3(id)) [|{|get_mergeMem_merge3_mergeNode1_merge3,set_mergeMem_merge3_mergeNode1_merge3,endDiagram_merge3|}|] Mem_merge3_mergeNode1_merge3_mergeMem_t(id,0)) \\{|get_mergeMem_merge3_mergeNode1_merge3,set_mergeMem_merge3_mergeNode1_merge3|}\n"
					+ "merge3_act1_merge3(id) = ((oe_3_merge3.id?x -> set_x_merge3_act1_merge3.id.3!x -> SKIP)); event_merge3_act1_merge3.id -> get_x_merge3_act1_merge3.id.4?x -> ((ce_merge3.id.1 -> SKIP)); merge3_act1_merge3(id)\n"
					+ "merge3_act1_merge3_t(id) = ((merge3_act1_merge3(id) /\\ END_DIAGRAM_merge3(id)) [|{|get_x_merge3_act1_merge3.id,set_x_merge3_act1_merge3.id,endDiagram_merge3.id|}|] Mem_merge3_act1_merge3_x_t(id,0)) \\{|get_x_merge3_act1_merge3.id,set_x_merge3_act1_merge3.id|}\n"
					+ "merge3_done_merge3(id) = ((ce_merge3.id.1 -> SKIP)); clear_merge3.id.1 -> SKIP\n"
					+ "merge3_done_merge3_t(id) = merge3_done_merge3(id) /\\ END_DIAGRAM_merge3(id)\n");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
}

