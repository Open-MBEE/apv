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


public class ADParserTestCallBehavior {

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
	public void TestBehavior1() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/behavior1.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_CB1 = {1..1}\n"
					+ "ID_behavior1 = {1..1}\n"
					+ "datatype alphabet_behavior1 = behavior1_start_behavior1_t_alphabet | behavior1_done_behavior1_t_alphabet| behavior1_cb1_behavior1_t_alphabet\n"
					+ "countCe_behavior1 = {1..2}\n"
					+ "countUpdate_behavior1 = {1..1}\n"
					+ "countClear_behavior1 = {1..1}\n"
					+ "limiteUpdate_behavior1 = {(1)..(1)}\n"
					+ "channel startActivity_behavior1: ID_behavior1\n"
					+ "channel endActivity_behavior1: ID_behavior1\n"
					+ "channel ce_behavior1: ID_behavior1.countCe_behavior1\n"
					+ "channel clear_behavior1: ID_behavior1.countClear_behavior1\n"
					+ "channel update_behavior1: ID_behavior1.countUpdate_behavior1.limiteUpdate_behavior1\n"
					+ "channel endDiagram_behavior1: ID_behavior1\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(behavior1(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_behavior1(id) = endDiagram_behavior1.id -> SKIP\n"
					+ "behavior1(ID_behavior1) = (Internal_behavior1(ID_behavior1) [|{|update_behavior1,clear_behavior1,endDiagram_behavior1|}|] TokenManager_behavior1_t(ID_behavior1,0,0))\n"
					+ "Internal_behavior1(id) = StartActivity_behavior1(id); Node_behavior1(id); EndActivity_behavior1(id)\n"
					+ "StartActivity_behavior1(id) = startActivity_behavior1.id -> SKIP\n"
					+ "EndActivity_behavior1(id) = endActivity_behavior1.id -> SKIP\n"
					+ "AlphabetDiagram_behavior1(id,behavior1_start_behavior1_t_alphabet) = {|update_behavior1.id.1,ce_behavior1.id.1,endDiagram_behavior1.id|}\n"
					+ "AlphabetDiagram_behavior1(id,behavior1_done_behavior1_t_alphabet) = {|ce_behavior1.id.2,clear_behavior1.id.1,endDiagram_behavior1.id|}\n"
					+ "AlphabetDiagram_behavior1(id,behavior1_cb1_behavior1_t_alphabet) = union({|ce_behavior1.id.1,startActivity_CB1.1,endActivity_CB1.1,ce_behavior1.id.2,endDiagram_behavior1.id|},AlphabetDiagram_CB1_t(1))\n"
					+ "AlphabetDiagram_behavior1_t(id) = union(union(AlphabetDiagram_behavior1(id,behavior1_start_behavior1_t_alphabet),AlphabetDiagram_behavior1(id,behavior1_done_behavior1_t_alphabet)),AlphabetDiagram_behavior1(id,behavior1_cb1_behavior1_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_behavior1(id,behavior1_start_behavior1_t_alphabet) = normal(behavior1_start_behavior1_t(id))\n"
					+ "ProcessDiagram_behavior1(id,behavior1_done_behavior1_t_alphabet) = normal(behavior1_done_behavior1_t(id))\n"
					+ "ProcessDiagram_behavior1(id,behavior1_cb1_behavior1_t_alphabet) = normal(behavior1_cb1_behavior1_t(id))\n"
					+ "Node_behavior1(id) = || x:alphabet_behavior1 @ [AlphabetDiagram_behavior1(id,x)] ProcessDiagram_behavior1(id,x)\n"
					+ "behavior1_start_behavior1(id) = update_behavior1.id.1!(1-0) -> ((ce_behavior1.id.1 -> SKIP))\n"
					+ "behavior1_start_behavior1_t(id) = behavior1_start_behavior1(id) /\\ END_DIAGRAM_behavior1(id)\n"
					+ "behavior1_cb1_behavior1(id) = ((ce_behavior1.id.1 -> SKIP)); normal(CB1(1));((ce_behavior1.id.2 -> SKIP)); behavior1_cb1_behavior1(id)\n"
					+ "behavior1_cb1_behavior1_t(id) = behavior1_cb1_behavior1(id) /\\ END_DIAGRAM_behavior1(id)\n"
					+ "behavior1_done_behavior1(id) = ((ce_behavior1.id.2 -> SKIP)); clear_behavior1.id.1 -> SKIP\n"
					+ "behavior1_done_behavior1_t(id) = behavior1_done_behavior1(id) /\\ END_DIAGRAM_behavior1(id)\n"
					+ "AlphabetMembehavior1_cb1_behavior1(id) = {|endDiagram_behavior1.id|}\n"
					+ "\n"
					+ "TokenManager_behavior1(id,x,init) = update_behavior1.id?c?y:limiteUpdate_behavior1 -> x+y < 10 & x+y > -10 & TokenManager_behavior1(id,x+y,1) [] clear_behavior1.id?c -> endDiagram_behavior1.id -> SKIP [] x == 0 & init == 1 & endDiagram_behavior1.id -> SKIP\n"
					+ "TokenManager_behavior1_t(id,x,init) = TokenManager_behavior1(id,x,init)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_behavior1|}\n"
					+ "\n"
					+ "datatype alphabet_CB1 = CB1_start_CB1_t_alphabet | CB1_done_CB1_t_alphabet| CB1_act1_CB1_t_alphabet\n"
					+ "countCe_CB1 = {1..2}\n"
					+ "countUpdate_CB1 = {1..1}\n"
					+ "countClear_CB1 = {1..1}\n"
					+ "limiteUpdate_CB1 = {(1)..(1)}\n"
					+ "channel startActivity_CB1: ID_CB1\n"
					+ "channel endActivity_CB1: ID_CB1\n"
					+ "channel ce_CB1: ID_CB1.countCe_CB1\n"
					+ "channel clear_CB1: ID_CB1.countClear_CB1\n"
					+ "channel update_CB1: ID_CB1.countUpdate_CB1.limiteUpdate_CB1\n"
					+ "channel endDiagram_CB1: ID_CB1\n"
					+ "channel event_CB1_act1_CB1: ID_CB1\n"
					+ "END_DIAGRAM_CB1(id) = endDiagram_CB1.id -> SKIP\n"
					+ "CB1(ID_CB1) = (Internal_CB1(ID_CB1) [|{|update_CB1,clear_CB1,endDiagram_CB1|}|] TokenManager_CB1_t(ID_CB1,0,0))\n"
					+ "Internal_CB1(id) = StartActivity_CB1(id); Node_CB1(id); EndActivity_CB1(id)\n"
					+ "StartActivity_CB1(id) = startActivity_CB1.id -> SKIP\n"
					+ "EndActivity_CB1(id) = endActivity_CB1.id -> SKIP\n"
					+ "AlphabetDiagram_CB1(id,CB1_start_CB1_t_alphabet) = {|update_CB1.id.1,ce_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet) = {|ce_CB1.id.2,clear_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = {|ce_CB1.id.1,event_CB1_act1_CB1.id,ce_CB1.id.2,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1_t(id) = union(union(AlphabetDiagram_CB1(id,CB1_start_CB1_t_alphabet),AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet)),AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_CB1(id,CB1_start_CB1_t_alphabet) = normal(CB1_start_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_done_CB1_t_alphabet) = normal(CB1_done_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = normal(CB1_act1_CB1_t(id))\n"
					+ "Node_CB1(id) = || x:alphabet_CB1 @ [AlphabetDiagram_CB1(id,x)] ProcessDiagram_CB1(id,x)\n"
					+ "CB1_start_CB1(id) = update_CB1.id.1!(1-0) -> ((ce_CB1.id.1 -> SKIP))\n"
					+ "CB1_start_CB1_t(id) = CB1_start_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "CB1_act1_CB1(id) = ((ce_CB1.id.1 -> SKIP)); event_CB1_act1_CB1.id -> ((ce_CB1.id.2 -> SKIP)); CB1_act1_CB1(id)\n"
					+ "CB1_act1_CB1_t(id) = CB1_act1_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "CB1_done_CB1(id) = ((ce_CB1.id.2 -> SKIP)); clear_CB1.id.1 -> SKIP\n"
					+ "CB1_done_CB1_t(id) = CB1_done_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "\n"
					+ "TokenManager_CB1(id,x,init) = update_CB1.id?c?y:limiteUpdate_CB1 -> x+y < 10 & x+y > -10 & TokenManager_CB1(id,x+y,1) [] clear_CB1.id?c -> endDiagram_CB1.id -> SKIP [] x == 0 & init == 1 & endDiagram_CB1.id -> SKIP\n"
					+ "TokenManager_CB1_t(id,x,init) = TokenManager_CB1(id,x,init)\n"
					+ "\n"
					+ "assert MAIN :[deadlock free]\n"
					+ "assert MAIN :[divergence free]\n"
					+ "assert MAIN :[deterministic]");
			
			assertEquals(expected.toString(), actual);

	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestBehavior2() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/behavior2.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser4 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser4.clearBuffer();
			
			String actual = parser4.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_CB1 = {1..2}\n"
					+ "ID_behavior2 = {1..1}\n"
					+ "datatype alphabet_behavior2 = behavior2_start_behavior2_t_alphabet | behavior2_done_behavior2_t_alphabet| behavior2_cb2_behavior2_t_alphabet| behavior2_cb1_behavior2_t_alphabet\n"
					+ "countCe_behavior2 = {1..3}\n"
					+ "countUpdate_behavior2 = {1..1}\n"
					+ "countClear_behavior2 = {1..1}\n"
					+ "limiteUpdate_behavior2 = {(1)..(1)}\n"
					+ "channel startActivity_behavior2: ID_behavior2\n"
					+ "channel endActivity_behavior2: ID_behavior2\n"
					+ "channel ce_behavior2: ID_behavior2.countCe_behavior2\n"
					+ "channel clear_behavior2: ID_behavior2.countClear_behavior2\n"
					+ "channel update_behavior2: ID_behavior2.countUpdate_behavior2.limiteUpdate_behavior2\n"
					+ "channel endDiagram_behavior2: ID_behavior2\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(behavior2(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_behavior2(id) = endDiagram_behavior2.id -> SKIP\n"
					+ "behavior2(ID_behavior2) = (Internal_behavior2(ID_behavior2) [|{|update_behavior2,clear_behavior2,endDiagram_behavior2|}|] TokenManager_behavior2_t(ID_behavior2,0,0))\n"
					+ "Internal_behavior2(id) = StartActivity_behavior2(id); Node_behavior2(id); EndActivity_behavior2(id)\n"
					+ "StartActivity_behavior2(id) = startActivity_behavior2.id -> SKIP\n"
					+ "EndActivity_behavior2(id) = endActivity_behavior2.id -> SKIP\n"
					+ "AlphabetDiagram_behavior2(id,behavior2_start_behavior2_t_alphabet) = {|update_behavior2.id.1,ce_behavior2.id.1,endDiagram_behavior2.id|}\n"
					+ "AlphabetDiagram_behavior2(id,behavior2_done_behavior2_t_alphabet) = {|ce_behavior2.id.3,clear_behavior2.id.1,endDiagram_behavior2.id|}\n"
					+ "AlphabetDiagram_behavior2(id,behavior2_cb2_behavior2_t_alphabet) = union({|ce_behavior2.id.2,startActivity_CB1.2,endActivity_CB1.2,ce_behavior2.id.3,endDiagram_behavior2.id|},AlphabetDiagram_CB1_t(2))\n"
					+ "AlphabetDiagram_behavior2(id,behavior2_cb1_behavior2_t_alphabet) = union({|ce_behavior2.id.1,startActivity_CB1.1,endActivity_CB1.1,ce_behavior2.id.2,endDiagram_behavior2.id|},AlphabetDiagram_CB1_t(1))\n"
					+ "AlphabetDiagram_behavior2_t(id) = union(union(union(AlphabetDiagram_behavior2(id,behavior2_start_behavior2_t_alphabet),AlphabetDiagram_behavior2(id,behavior2_done_behavior2_t_alphabet)),AlphabetDiagram_behavior2(id,behavior2_cb2_behavior2_t_alphabet)),AlphabetDiagram_behavior2(id,behavior2_cb1_behavior2_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_behavior2(id,behavior2_start_behavior2_t_alphabet) = normal(behavior2_start_behavior2_t(id))\n"
					+ "ProcessDiagram_behavior2(id,behavior2_done_behavior2_t_alphabet) = normal(behavior2_done_behavior2_t(id))\n"
					+ "ProcessDiagram_behavior2(id,behavior2_cb2_behavior2_t_alphabet) = normal(behavior2_cb2_behavior2_t(id))\n"
					+ "ProcessDiagram_behavior2(id,behavior2_cb1_behavior2_t_alphabet) = normal(behavior2_cb1_behavior2_t(id))\n"
					+ "Node_behavior2(id) = || x:alphabet_behavior2 @ [AlphabetDiagram_behavior2(id,x)] ProcessDiagram_behavior2(id,x)\n"
					+ "behavior2_start_behavior2(id) = update_behavior2.id.1!(1-0) -> ((ce_behavior2.id.1 -> SKIP))\n"
					+ "behavior2_start_behavior2_t(id) = behavior2_start_behavior2(id) /\\ END_DIAGRAM_behavior2(id)\n"
					+ "behavior2_cb1_behavior2(id) = ((ce_behavior2.id.1 -> SKIP)); normal(CB1(1));((ce_behavior2.id.2 -> SKIP)); behavior2_cb1_behavior2(id)\n"
					+ "behavior2_cb1_behavior2_t(id) = behavior2_cb1_behavior2(id) /\\ END_DIAGRAM_behavior2(id)\n"
					+ "behavior2_cb2_behavior2(id) = ((ce_behavior2.id.2 -> SKIP)); normal(CB1(2));((ce_behavior2.id.3 -> SKIP)); behavior2_cb2_behavior2(id)\n"
					+ "behavior2_cb2_behavior2_t(id) = behavior2_cb2_behavior2(id) /\\ END_DIAGRAM_behavior2(id)\n"
					+ "behavior2_done_behavior2(id) = ((ce_behavior2.id.3 -> SKIP)); clear_behavior2.id.1 -> SKIP\n"
					+ "behavior2_done_behavior2_t(id) = behavior2_done_behavior2(id) /\\ END_DIAGRAM_behavior2(id)\n"
					+ "AlphabetMembehavior2_cb1_behavior2(id) = {|endDiagram_behavior2.id|}\n"
					+ "AlphabetMembehavior2_cb2_behavior2(id) = {|endDiagram_behavior2.id|}\n"
					+ "\n"
					+ "TokenManager_behavior2(id,x,init) = update_behavior2.id?c?y:limiteUpdate_behavior2 -> x+y < 10 & x+y > -10 & TokenManager_behavior2(id,x+y,1) [] clear_behavior2.id?c -> endDiagram_behavior2.id -> SKIP [] x == 0 & init == 1 & endDiagram_behavior2.id -> SKIP\n"
					+ "TokenManager_behavior2_t(id,x,init) = TokenManager_behavior2(id,x,init)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_behavior2|}\n"
					+ "\n"
					+ "datatype alphabet_CB1 = CB1_start_CB1_t_alphabet | CB1_done_CB1_t_alphabet| CB1_act1_CB1_t_alphabet\n"
					+ "countCe_CB1 = {1..2}\n"
					+ "countUpdate_CB1 = {1..1}\n"
					+ "countClear_CB1 = {1..1}\n"
					+ "limiteUpdate_CB1 = {(1)..(1)}\n"
					+ "channel startActivity_CB1: ID_CB1\n"
					+ "channel endActivity_CB1: ID_CB1\n"
					+ "channel ce_CB1: ID_CB1.countCe_CB1\n"
					+ "channel clear_CB1: ID_CB1.countClear_CB1\n"
					+ "channel update_CB1: ID_CB1.countUpdate_CB1.limiteUpdate_CB1\n"
					+ "channel endDiagram_CB1: ID_CB1\n"
					+ "channel event_CB1_act1_CB1: ID_CB1\n"
					+ "END_DIAGRAM_CB1(id) = endDiagram_CB1.id -> SKIP\n"
					+ "CB1(ID_CB1) = (Internal_CB1(ID_CB1) [|{|update_CB1,clear_CB1,endDiagram_CB1|}|] TokenManager_CB1_t(ID_CB1,0,0))\n"
					+ "Internal_CB1(id) = StartActivity_CB1(id); Node_CB1(id); EndActivity_CB1(id)\n"
					+ "StartActivity_CB1(id) = startActivity_CB1.id -> SKIP\n"
					+ "EndActivity_CB1(id) = endActivity_CB1.id -> SKIP\n"
					+ "AlphabetDiagram_CB1(id,CB1_start_CB1_t_alphabet) = {|update_CB1.id.1,ce_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet) = {|ce_CB1.id.2,clear_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = {|ce_CB1.id.1,event_CB1_act1_CB1.id,ce_CB1.id.2,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1_t(id) = union(union(AlphabetDiagram_CB1(id,CB1_start_CB1_t_alphabet),AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet)),AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_CB1(id,CB1_start_CB1_t_alphabet) = normal(CB1_start_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_done_CB1_t_alphabet) = normal(CB1_done_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = normal(CB1_act1_CB1_t(id))\n"
					+ "Node_CB1(id) = || x:alphabet_CB1 @ [AlphabetDiagram_CB1(id,x)] ProcessDiagram_CB1(id,x)\n"
					+ "CB1_start_CB1(id) = update_CB1.id.1!(1-0) -> ((ce_CB1.id.1 -> SKIP))\n"
					+ "CB1_start_CB1_t(id) = CB1_start_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "CB1_act1_CB1(id) = ((ce_CB1.id.1 -> SKIP)); event_CB1_act1_CB1.id -> ((ce_CB1.id.2 -> SKIP)); CB1_act1_CB1(id)\n"
					+ "CB1_act1_CB1_t(id) = CB1_act1_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "CB1_done_CB1(id) = ((ce_CB1.id.2 -> SKIP)); clear_CB1.id.1 -> SKIP\n"
					+ "CB1_done_CB1_t(id) = CB1_done_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "\n"
					+ "TokenManager_CB1(id,x,init) = update_CB1.id?c?y:limiteUpdate_CB1 -> x+y < 10 & x+y > -10 & TokenManager_CB1(id,x+y,1) [] clear_CB1.id?c -> endDiagram_CB1.id -> SKIP [] x == 0 & init == 1 & endDiagram_CB1.id -> SKIP\n"
					+ "TokenManager_CB1_t(id,x,init) = TokenManager_CB1(id,x,init)\n"
					+ "\n"
					+ "assert MAIN :[deadlock free]\n"
					+ "assert MAIN :[divergence free]\n"
					+ "assert MAIN :[deterministic]");
			
			assertEquals(expected.toString(), actual);

	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestBehavior3() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/behavior3.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser2.clearBuffer();
			
			String actual = parser2.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_CB2 = {1..1}\n"
					+ "ID_CB1 = {1..1}\n"
					+ "ID_behavior3 = {1..1}\n"
					+ "datatype alphabet_behavior3 = behavior3_start_behavior3_t_alphabet | behavior3_done_behavior3_t_alphabet| behavior3_cb2_behavior3_t_alphabet| behavior3_cb1_behavior3_t_alphabet\n"
					+ "countCe_behavior3 = {1..3}\n"
					+ "countUpdate_behavior3 = {1..1}\n"
					+ "countClear_behavior3 = {1..1}\n"
					+ "limiteUpdate_behavior3 = {(1)..(1)}\n"
					+ "channel startActivity_behavior3: ID_behavior3\n"
					+ "channel endActivity_behavior3: ID_behavior3\n"
					+ "channel ce_behavior3: ID_behavior3.countCe_behavior3\n"
					+ "channel clear_behavior3: ID_behavior3.countClear_behavior3\n"
					+ "channel update_behavior3: ID_behavior3.countUpdate_behavior3.limiteUpdate_behavior3\n"
					+ "channel endDiagram_behavior3: ID_behavior3\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(behavior3(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_behavior3(id) = endDiagram_behavior3.id -> SKIP\n"
					+ "behavior3(ID_behavior3) = (Internal_behavior3(ID_behavior3) [|{|update_behavior3,clear_behavior3,endDiagram_behavior3|}|] TokenManager_behavior3_t(ID_behavior3,0,0))\n"
					+ "Internal_behavior3(id) = StartActivity_behavior3(id); Node_behavior3(id); EndActivity_behavior3(id)\n"
					+ "StartActivity_behavior3(id) = startActivity_behavior3.id -> SKIP\n"
					+ "EndActivity_behavior3(id) = endActivity_behavior3.id -> SKIP\n"
					+ "AlphabetDiagram_behavior3(id,behavior3_start_behavior3_t_alphabet) = {|update_behavior3.id.1,ce_behavior3.id.1,endDiagram_behavior3.id|}\n"
					+ "AlphabetDiagram_behavior3(id,behavior3_done_behavior3_t_alphabet) = {|ce_behavior3.id.3,clear_behavior3.id.1,endDiagram_behavior3.id|}\n"
					+ "AlphabetDiagram_behavior3(id,behavior3_cb2_behavior3_t_alphabet) = union({|ce_behavior3.id.2,startActivity_CB2.1,endActivity_CB2.1,ce_behavior3.id.3,endDiagram_behavior3.id|},AlphabetDiagram_CB2_t(1))\n"
					+ "AlphabetDiagram_behavior3(id,behavior3_cb1_behavior3_t_alphabet) = union({|ce_behavior3.id.1,startActivity_CB1.1,endActivity_CB1.1,ce_behavior3.id.2,endDiagram_behavior3.id|},AlphabetDiagram_CB1_t(1))\n"
					+ "AlphabetDiagram_behavior3_t(id) = union(union(union(AlphabetDiagram_behavior3(id,behavior3_start_behavior3_t_alphabet),AlphabetDiagram_behavior3(id,behavior3_done_behavior3_t_alphabet)),AlphabetDiagram_behavior3(id,behavior3_cb2_behavior3_t_alphabet)),AlphabetDiagram_behavior3(id,behavior3_cb1_behavior3_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_behavior3(id,behavior3_start_behavior3_t_alphabet) = normal(behavior3_start_behavior3_t(id))\n"
					+ "ProcessDiagram_behavior3(id,behavior3_done_behavior3_t_alphabet) = normal(behavior3_done_behavior3_t(id))\n"
					+ "ProcessDiagram_behavior3(id,behavior3_cb2_behavior3_t_alphabet) = normal(behavior3_cb2_behavior3_t(id))\n"
					+ "ProcessDiagram_behavior3(id,behavior3_cb1_behavior3_t_alphabet) = normal(behavior3_cb1_behavior3_t(id))\n"
					+ "Node_behavior3(id) = || x:alphabet_behavior3 @ [AlphabetDiagram_behavior3(id,x)] ProcessDiagram_behavior3(id,x)\n"
					+ "behavior3_start_behavior3(id) = update_behavior3.id.1!(1-0) -> ((ce_behavior3.id.1 -> SKIP))\n"
					+ "behavior3_start_behavior3_t(id) = behavior3_start_behavior3(id) /\\ END_DIAGRAM_behavior3(id)\n"
					+ "behavior3_cb1_behavior3(id) = ((ce_behavior3.id.1 -> SKIP)); normal(CB1(1));((ce_behavior3.id.2 -> SKIP)); behavior3_cb1_behavior3(id)\n"
					+ "behavior3_cb1_behavior3_t(id) = behavior3_cb1_behavior3(id) /\\ END_DIAGRAM_behavior3(id)\n"
					+ "behavior3_cb2_behavior3(id) = ((ce_behavior3.id.2 -> SKIP)); normal(CB2(1));((ce_behavior3.id.3 -> SKIP)); behavior3_cb2_behavior3(id)\n"
					+ "behavior3_cb2_behavior3_t(id) = behavior3_cb2_behavior3(id) /\\ END_DIAGRAM_behavior3(id)\n"
					+ "behavior3_done_behavior3(id) = ((ce_behavior3.id.3 -> SKIP)); clear_behavior3.id.1 -> SKIP\n"
					+ "behavior3_done_behavior3_t(id) = behavior3_done_behavior3(id) /\\ END_DIAGRAM_behavior3(id)\n"
					+ "AlphabetMembehavior3_cb2_behavior3(id) = {|endDiagram_behavior3.id|}\n"
					+ "AlphabetMembehavior3_cb1_behavior3(id) = {|endDiagram_behavior3.id|}\n"
					+ "\n"
					+ "TokenManager_behavior3(id,x,init) = update_behavior3.id?c?y:limiteUpdate_behavior3 -> x+y < 10 & x+y > -10 & TokenManager_behavior3(id,x+y,1) [] clear_behavior3.id?c -> endDiagram_behavior3.id -> SKIP [] x == 0 & init == 1 & endDiagram_behavior3.id -> SKIP\n"
					+ "TokenManager_behavior3_t(id,x,init) = TokenManager_behavior3(id,x,init)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_behavior3|}\n"
					+ "\n"
					+ "datatype alphabet_CB1 = CB1_start_CB1_t_alphabet | CB1_done_CB1_t_alphabet| CB1_act1_CB1_t_alphabet\n"
					+ "countCe_CB1 = {1..2}\n"
					+ "countUpdate_CB1 = {1..1}\n"
					+ "countClear_CB1 = {1..1}\n"
					+ "limiteUpdate_CB1 = {(1)..(1)}\n"
					+ "channel startActivity_CB1: ID_CB1\n"
					+ "channel endActivity_CB1: ID_CB1\n"
					+ "channel ce_CB1: ID_CB1.countCe_CB1\n"
					+ "channel clear_CB1: ID_CB1.countClear_CB1\n"
					+ "channel update_CB1: ID_CB1.countUpdate_CB1.limiteUpdate_CB1\n"
					+ "channel endDiagram_CB1: ID_CB1\n"
					+ "channel event_CB1_act1_CB1: ID_CB1\n"
					+ "END_DIAGRAM_CB1(id) = endDiagram_CB1.id -> SKIP\n"
					+ "CB1(ID_CB1) = (Internal_CB1(ID_CB1) [|{|update_CB1,clear_CB1,endDiagram_CB1|}|] TokenManager_CB1_t(ID_CB1,0,0))\n"
					+ "Internal_CB1(id) = StartActivity_CB1(id); Node_CB1(id); EndActivity_CB1(id)\n"
					+ "StartActivity_CB1(id) = startActivity_CB1.id -> SKIP\n"
					+ "EndActivity_CB1(id) = endActivity_CB1.id -> SKIP\n"
					+ "AlphabetDiagram_CB1(id,CB1_start_CB1_t_alphabet) = {|update_CB1.id.1,ce_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet) = {|ce_CB1.id.2,clear_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = {|ce_CB1.id.1,event_CB1_act1_CB1.id,ce_CB1.id.2,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1_t(id) = union(union(AlphabetDiagram_CB1(id,CB1_start_CB1_t_alphabet),AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet)),AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_CB1(id,CB1_start_CB1_t_alphabet) = normal(CB1_start_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_done_CB1_t_alphabet) = normal(CB1_done_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = normal(CB1_act1_CB1_t(id))\n"
					+ "Node_CB1(id) = || x:alphabet_CB1 @ [AlphabetDiagram_CB1(id,x)] ProcessDiagram_CB1(id,x)\n"
					+ "CB1_start_CB1(id) = update_CB1.id.1!(1-0) -> ((ce_CB1.id.1 -> SKIP))\n"
					+ "CB1_start_CB1_t(id) = CB1_start_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "CB1_act1_CB1(id) = ((ce_CB1.id.1 -> SKIP)); event_CB1_act1_CB1.id -> ((ce_CB1.id.2 -> SKIP)); CB1_act1_CB1(id)\n"
					+ "CB1_act1_CB1_t(id) = CB1_act1_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "CB1_done_CB1(id) = ((ce_CB1.id.2 -> SKIP)); clear_CB1.id.1 -> SKIP\n"
					+ "CB1_done_CB1_t(id) = CB1_done_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "\n"
					+ "TokenManager_CB1(id,x,init) = update_CB1.id?c?y:limiteUpdate_CB1 -> x+y < 10 & x+y > -10 & TokenManager_CB1(id,x+y,1) [] clear_CB1.id?c -> endDiagram_CB1.id -> SKIP [] x == 0 & init == 1 & endDiagram_CB1.id -> SKIP\n"
					+ "TokenManager_CB1_t(id,x,init) = TokenManager_CB1(id,x,init)\n"
					+ "\n"
					+ "datatype alphabet_CB2 = CB2_start_CB2_t_alphabet | CB2_done_CB2_t_alphabet| CB2_act1_CB2_t_alphabet\n"
					+ "countCe_CB2 = {1..2}\n"
					+ "countUpdate_CB2 = {1..1}\n"
					+ "countClear_CB2 = {1..1}\n"
					+ "limiteUpdate_CB2 = {(1)..(1)}\n"
					+ "channel startActivity_CB2: ID_CB2\n"
					+ "channel endActivity_CB2: ID_CB2\n"
					+ "channel ce_CB2: ID_CB2.countCe_CB2\n"
					+ "channel clear_CB2: ID_CB2.countClear_CB2\n"
					+ "channel update_CB2: ID_CB2.countUpdate_CB2.limiteUpdate_CB2\n"
					+ "channel endDiagram_CB2: ID_CB2\n"
					+ "channel event_CB2_act1_CB2: ID_CB2\n"
					+ "END_DIAGRAM_CB2(id) = endDiagram_CB2.id -> SKIP\n"
					+ "CB2(ID_CB2) = (Internal_CB2(ID_CB2) [|{|update_CB2,clear_CB2,endDiagram_CB2|}|] TokenManager_CB2_t(ID_CB2,0,0))\n"
					+ "Internal_CB2(id) = StartActivity_CB2(id); Node_CB2(id); EndActivity_CB2(id)\n"
					+ "StartActivity_CB2(id) = startActivity_CB2.id -> SKIP\n"
					+ "EndActivity_CB2(id) = endActivity_CB2.id -> SKIP\n"
					+ "AlphabetDiagram_CB2(id,CB2_start_CB2_t_alphabet) = {|update_CB2.id.1,ce_CB2.id.1,endDiagram_CB2.id|}\n"
					+ "AlphabetDiagram_CB2(id,CB2_done_CB2_t_alphabet) = {|ce_CB2.id.2,clear_CB2.id.1,endDiagram_CB2.id|}\n"
					+ "AlphabetDiagram_CB2(id,CB2_act1_CB2_t_alphabet) = {|ce_CB2.id.1,event_CB2_act1_CB2.id,ce_CB2.id.2,endDiagram_CB2.id|}\n"
					+ "AlphabetDiagram_CB2_t(id) = union(union(AlphabetDiagram_CB2(id,CB2_start_CB2_t_alphabet),AlphabetDiagram_CB2(id,CB2_done_CB2_t_alphabet)),AlphabetDiagram_CB2(id,CB2_act1_CB2_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_CB2(id,CB2_start_CB2_t_alphabet) = normal(CB2_start_CB2_t(id))\n"
					+ "ProcessDiagram_CB2(id,CB2_done_CB2_t_alphabet) = normal(CB2_done_CB2_t(id))\n"
					+ "ProcessDiagram_CB2(id,CB2_act1_CB2_t_alphabet) = normal(CB2_act1_CB2_t(id))\n"
					+ "Node_CB2(id) = || x:alphabet_CB2 @ [AlphabetDiagram_CB2(id,x)] ProcessDiagram_CB2(id,x)\n"
					+ "CB2_start_CB2(id) = update_CB2.id.1!(1-0) -> ((ce_CB2.id.1 -> SKIP))\n"
					+ "CB2_start_CB2_t(id) = CB2_start_CB2(id) /\\ END_DIAGRAM_CB2(id)\n"
					+ "CB2_act1_CB2(id) = ((ce_CB2.id.1 -> SKIP)); event_CB2_act1_CB2.id -> ((ce_CB2.id.2 -> SKIP)); CB2_act1_CB2(id)\n"
					+ "CB2_act1_CB2_t(id) = CB2_act1_CB2(id) /\\ END_DIAGRAM_CB2(id)\n"
					+ "CB2_done_CB2(id) = ((ce_CB2.id.2 -> SKIP)); clear_CB2.id.1 -> SKIP\n"
					+ "CB2_done_CB2_t(id) = CB2_done_CB2(id) /\\ END_DIAGRAM_CB2(id)\n"
					+ "\n"
					+ "TokenManager_CB2(id,x,init) = update_CB2.id?c?y:limiteUpdate_CB2 -> x+y < 10 & x+y > -10 & TokenManager_CB2(id,x+y,1) [] clear_CB2.id?c -> endDiagram_CB2.id -> SKIP [] x == 0 & init == 1 & endDiagram_CB2.id -> SKIP\n"
					+ "TokenManager_CB2_t(id,x,init) = TokenManager_CB2(id,x,init)\n"
					+ "\n"
					+ "assert MAIN :[deadlock free]\n"
					+ "assert MAIN :[divergence free]\n"
					+ "assert MAIN :[deterministic]");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestBehavior4() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/behavior4.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser3 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser3.clearBuffer();
			
			String actual = parser3.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_CB1 = {1..1}\n"
					+ "ID_behavior4 = {1..1}\n"
					+ "datatype alphabet_behavior4 = parameter_x_behavior4_t_alphabet | behavior4_done_behavior4_t_alphabet| behavior4_cb1_behavior4_t_alphabet\n"
					+ "Integer_behavior4 = {0..1}\n"
					+ "countGet_behavior4 = {1..2}\n"
					+ "countSet_behavior4 = {1..2}\n"
					+ "countCe_behavior4 = {1..1}\n"
					+ "countOe_behavior4 = {1..1}\n"
					+ "countUpdate_behavior4 = {1..1}\n"
					+ "countClear_behavior4 = {1..1}\n"
					+ "limiteUpdate_behavior4 = {(1)..(1)}\n"
					+ "channel startActivity_behavior4: ID_behavior4.Integer_behavior4\n"
					+ "channel endActivity_behavior4: ID_behavior4\n"
					+ "channel get_x_behavior4: ID_behavior4.countGet_behavior4.Integer_behavior4\n"
					+ "channel set_x_behavior4: ID_behavior4.countSet_behavior4.Integer_behavior4\n"
					+ "channel get_z_behavior4_cb1_behavior4: ID_behavior4.countGet_behavior4.Integer_behavior4\n"
					+ "channel set_z_behavior4_cb1_behavior4: ID_behavior4.countSet_behavior4.Integer_behavior4\n"
					+ "channel ce_behavior4: ID_behavior4.countCe_behavior4\n"
					+ "channel oe_1_behavior4: ID_behavior4.Integer_behavior4\n"
					+ "channel clear_behavior4: ID_behavior4.countClear_behavior4\n"
					+ "channel update_behavior4: ID_behavior4.countUpdate_behavior4.limiteUpdate_behavior4\n"
					+ "channel endDiagram_behavior4: ID_behavior4\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(behavior4(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_behavior4(id) = endDiagram_behavior4.id -> SKIP\n"
					+ "behavior4(ID_behavior4) = ((Internal_behavior4(ID_behavior4) [|{|update_behavior4,clear_behavior4,endDiagram_behavior4|}|] TokenManager_behavior4_t(ID_behavior4,0,0)) [|{|get_x_behavior4,set_x_behavior4,endActivity_behavior4|}|] Mem_behavior4(ID_behavior4)) \\{|get_x_behavior4,set_x_behavior4|}\n"
					+ "Internal_behavior4(id) = StartActivity_behavior4(id); Node_behavior4(id); EndActivity_behavior4(id)\n"
					+ "StartActivity_behavior4(id) = startActivity_behavior4.id?x -> set_x_behavior4.id.2!x -> SKIP\n"
					+ "EndActivity_behavior4(id) = endActivity_behavior4.id -> SKIP\n"
					+ "AlphabetDiagram_behavior4(id,parameter_x_behavior4_t_alphabet) = {|update_behavior4.id.1,get_x_behavior4.id.1,oe_1_behavior4.id|}\n"
					+ "AlphabetDiagram_behavior4(id,behavior4_done_behavior4_t_alphabet) = {|ce_behavior4.id.1,clear_behavior4.id.1,endDiagram_behavior4.id|}\n"
					+ "AlphabetDiagram_behavior4(id,behavior4_cb1_behavior4_t_alphabet) = union({|oe_1_behavior4.id,set_z_behavior4_cb1_behavior4.id.1,get_z_behavior4_cb1_behavior4.id.2,startActivity_CB1.1,endActivity_CB1.1,ce_behavior4.id.1,endDiagram_behavior4.id|},AlphabetDiagram_CB1_t(1))\n"
					+ "AlphabetDiagram_behavior4_t(id) = union(union(AlphabetDiagram_behavior4(id,parameter_x_behavior4_t_alphabet),AlphabetDiagram_behavior4(id,behavior4_done_behavior4_t_alphabet)),AlphabetDiagram_behavior4(id,behavior4_cb1_behavior4_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_behavior4(id,parameter_x_behavior4_t_alphabet) = normal(parameter_x_behavior4_t(id))\n"
					+ "ProcessDiagram_behavior4(id,behavior4_done_behavior4_t_alphabet) = normal(behavior4_done_behavior4_t(id))\n"
					+ "ProcessDiagram_behavior4(id,behavior4_cb1_behavior4_t_alphabet) = normal(behavior4_cb1_behavior4_t(id))\n"
					+ "Node_behavior4(id) = || x:alphabet_behavior4 @ [AlphabetDiagram_behavior4(id,x)] ProcessDiagram_behavior4(id,x)\n"
					+ "parameter_x_behavior4_t(id) = update_behavior4.id.1!(1-0) -> get_x_behavior4.id.1?x -> ((oe_1_behavior4.id!x -> SKIP))\n"
					+ "behavior4_cb1_behavior4(id) = ((oe_1_behavior4.id?z -> set_z_behavior4_cb1_behavior4.id.1!z -> SKIP)); get_z_behavior4_cb1_behavior4.id.2?z -> (normal(CB1(1)) [|{|startActivity_CB1.1,endActivity_CB1.1|}|] (startActivity_CB1.1!z -> endActivity_CB1.1 -> SKIP));((ce_behavior4.id.1 -> SKIP)); behavior4_cb1_behavior4(id)\n"
					+ "behavior4_cb1_behavior4_t(id) = ((behavior4_cb1_behavior4(id)) [|AlphabetMembehavior4_cb1_behavior4(id)|] Mem_behavior4_cb1_behavior4(id)) \\diff(AlphabetMembehavior4_cb1_behavior4(id),{|endDiagram_behavior4.id|}) /\\ END_DIAGRAM_behavior4(id)\n"
					+ "behavior4_done_behavior4(id) = ((ce_behavior4.id.1 -> SKIP)); clear_behavior4.id.1 -> SKIP\n"
					+ "behavior4_done_behavior4_t(id) = behavior4_done_behavior4(id) /\\ END_DIAGRAM_behavior4(id)\n"
					+ "Mem_behavior4_cb1_behavior4_z(id,z) = get_z_behavior4_cb1_behavior4.id?c!z -> Mem_behavior4_cb1_behavior4_z(id,z) [] set_z_behavior4_cb1_behavior4.id?c?z -> Mem_behavior4_cb1_behavior4_z(id,z)\n"
					+ "Mem_behavior4_cb1_behavior4_z_t(id,z) = Mem_behavior4_cb1_behavior4_z(id,z) /\\ END_DIAGRAM_behavior4(id)\n"
					+ "AlphabetMembehavior4_cb1_behavior4(id) = {|get_z_behavior4_cb1_behavior4.id,set_z_behavior4_cb1_behavior4.id,endDiagram_behavior4.id|}\n"
					+ "Mem_behavior4_cb1_behavior4(id) = Mem_behavior4_cb1_behavior4_z_t(id,0) \n"
					+ "Mem_behavior4_x(id,x) = get_x_behavior4.id?c!x -> Mem_behavior4_x(id,x) [] set_x_behavior4.id?c?x -> Mem_behavior4_x(id,x)\n"
					+ "Mem_behavior4_x_t(id,x) = Mem_behavior4_x(id,x) /\\ (endActivity_behavior4.id -> SKIP)\n"
					+ "Mem_behavior4(id) = Mem_behavior4_x_t(id,0)\n"
					+ "TokenManager_behavior4(id,x,init) = update_behavior4.id?c?y:limiteUpdate_behavior4 -> x+y < 10 & x+y > -10 & TokenManager_behavior4(id,x+y,1) [] clear_behavior4.id?c -> endDiagram_behavior4.id -> SKIP [] x == 0 & init == 1 & endDiagram_behavior4.id -> SKIP\n"
					+ "TokenManager_behavior4_t(id,x,init) = TokenManager_behavior4(id,x,init)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_behavior4|}\n"
					+ "\n"
					+ "datatype alphabet_CB1 = parameter_z_CB1_t_alphabet | CB1_done_CB1_t_alphabet| CB1_act1_CB1_t_alphabet\n"
					+ "Integer_CB1 = {0..1}\n"
					+ "countGet_CB1 = {1..2}\n"
					+ "countSet_CB1 = {1..2}\n"
					+ "countCe_CB1 = {1..1}\n"
					+ "countOe_CB1 = {1..1}\n"
					+ "countUpdate_CB1 = {1..1}\n"
					+ "countClear_CB1 = {1..1}\n"
					+ "limiteUpdate_CB1 = {(1)..(1)}\n"
					+ "channel startActivity_CB1: ID_CB1.Integer_CB1\n"
					+ "channel endActivity_CB1: ID_CB1\n"
					+ "channel get_z_CB1: ID_CB1.countGet_CB1.Integer_CB1\n"
					+ "channel set_z_CB1: ID_CB1.countSet_CB1.Integer_CB1\n"
					+ "channel get_x_CB1_act1_CB1: ID_CB1.countGet_CB1.Integer_CB1\n"
					+ "channel set_x_CB1_act1_CB1: ID_CB1.countSet_CB1.Integer_CB1\n"
					+ "channel ce_CB1: ID_CB1.countCe_CB1\n"
					+ "channel oe_1_CB1: ID_CB1.Integer_CB1\n"
					+ "channel clear_CB1: ID_CB1.countClear_CB1\n"
					+ "channel update_CB1: ID_CB1.countUpdate_CB1.limiteUpdate_CB1\n"
					+ "channel endDiagram_CB1: ID_CB1\n"
					+ "channel event_CB1_act1_CB1: ID_CB1\n"
					+ "END_DIAGRAM_CB1(id) = endDiagram_CB1.id -> SKIP\n"
					+ "CB1(ID_CB1) = ((Internal_CB1(ID_CB1) [|{|update_CB1,clear_CB1,endDiagram_CB1|}|] TokenManager_CB1_t(ID_CB1,0,0)) [|{|get_z_CB1,set_z_CB1,endActivity_CB1|}|] Mem_CB1(ID_CB1)) \\{|get_z_CB1,set_z_CB1|}\n"
					+ "Internal_CB1(id) = StartActivity_CB1(id); Node_CB1(id); EndActivity_CB1(id)\n"
					+ "StartActivity_CB1(id) = startActivity_CB1.id?z -> set_z_CB1.id.2!z -> SKIP\n"
					+ "EndActivity_CB1(id) = endActivity_CB1.id -> SKIP\n"
					+ "AlphabetDiagram_CB1(id,parameter_z_CB1_t_alphabet) = {|update_CB1.id.1,get_z_CB1.id.1,oe_1_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet) = {|ce_CB1.id.1,clear_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = {|oe_1_CB1.id,set_x_CB1_act1_CB1.id.1,event_CB1_act1_CB1.id,get_x_CB1_act1_CB1.id.2,ce_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1_t(id) = union(union(AlphabetDiagram_CB1(id,parameter_z_CB1_t_alphabet),AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet)),AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_CB1(id,parameter_z_CB1_t_alphabet) = normal(parameter_z_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_done_CB1_t_alphabet) = normal(CB1_done_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = normal(CB1_act1_CB1_t(id))\n"
					+ "Node_CB1(id) = || x:alphabet_CB1 @ [AlphabetDiagram_CB1(id,x)] ProcessDiagram_CB1(id,x)\n"
					+ "parameter_z_CB1_t(id) = update_CB1.id.1!(1-0) -> get_z_CB1.id.1?z -> ((oe_1_CB1.id!z -> SKIP))\n"
					+ "CB1_act1_CB1(id) = ((oe_1_CB1.id?x -> set_x_CB1_act1_CB1.id.1!x -> SKIP)); event_CB1_act1_CB1.id -> get_x_CB1_act1_CB1.id.2?x -> ((ce_CB1.id.1 -> SKIP)); CB1_act1_CB1(id)\n"
					+ "CB1_act1_CB1_t(id) = ((CB1_act1_CB1(id) /\\ END_DIAGRAM_CB1(id)) [|{|get_x_CB1_act1_CB1.id,set_x_CB1_act1_CB1.id,endDiagram_CB1.id|}|] Mem_CB1_act1_CB1_x_t(id,0)) \\{|get_x_CB1_act1_CB1.id,set_x_CB1_act1_CB1.id|}\n"
					+ "CB1_done_CB1(id) = ((ce_CB1.id.1 -> SKIP)); clear_CB1.id.1 -> SKIP\n"
					+ "CB1_done_CB1_t(id) = CB1_done_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "Mem_CB1_act1_CB1_x(id,x) = get_x_CB1_act1_CB1.id?c!x -> Mem_CB1_act1_CB1_x(id,x) [] set_x_CB1_act1_CB1.id?c?x -> Mem_CB1_act1_CB1_x(id,x)\n"
					+ "Mem_CB1_act1_CB1_x_t(id,x) = Mem_CB1_act1_CB1_x(id,x) /\\ END_DIAGRAM_CB1(id)\n"
					+ "Mem_CB1_z(id,z) = get_z_CB1.id?c!z -> Mem_CB1_z(id,z) [] set_z_CB1.id?c?z -> Mem_CB1_z(id,z)\n"
					+ "Mem_CB1_z_t(id,z) = Mem_CB1_z(id,z) /\\ (endActivity_CB1.id -> SKIP)\n"
					+ "Mem_CB1(id) = Mem_CB1_z_t(id,0)\n"
					+ "TokenManager_CB1(id,x,init) = update_CB1.id?c?y:limiteUpdate_CB1 -> x+y < 10 & x+y > -10 & TokenManager_CB1(id,x+y,1) [] clear_CB1.id?c -> endDiagram_CB1.id -> SKIP [] x == 0 & init == 1 & endDiagram_CB1.id -> SKIP\n"
					+ "TokenManager_CB1_t(id,x,init) = TokenManager_CB1(id,x,init)\n"
					+ "\n"
					+ "assert MAIN :[deadlock free]\n"
					+ "assert MAIN :[divergence free]\n"
					+ "assert MAIN :[deterministic]");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
	@Test
	public void TestBehavior5() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/behavior5.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);
			parser5 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser5.clearBuffer();
			
			String actual = parser5.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_CB1 = {1..2}\n"
					+ "ID_behavior5 = {1..1}\n"
					+ "datatype alphabet_behavior5 = parameter_x_behavior5_t_alphabet | behavior5_done_behavior5_t_alphabet| behavior5_cb2_behavior5_t_alphabet| behavior5_cb1_behavior5_t_alphabet\n"
					+ "Integer_behavior5 = {0..1}\n"
					+ "countGet_behavior5 = {1..3}\n"
					+ "countSet_behavior5 = {1..3}\n"
					+ "countCe_behavior5 = {1..2}\n"
					+ "countOe_behavior5 = {1..2}\n"
					+ "countUpdate_behavior5 = {1..1}\n"
					+ "countClear_behavior5 = {1..1}\n"
					+ "limiteUpdate_behavior5 = {(2)..(2)}\n"
					+ "channel startActivity_behavior5: ID_behavior5.Integer_behavior5\n"
					+ "channel endActivity_behavior5: ID_behavior5\n"
					+ "channel get_x_behavior5: ID_behavior5.countGet_behavior5.Integer_behavior5\n"
					+ "channel set_x_behavior5: ID_behavior5.countSet_behavior5.Integer_behavior5\n"
					+ "channel get_z_behavior5_cb2_behavior5: ID_behavior5.countGet_behavior5.Integer_behavior5\n"
					+ "channel set_z_behavior5_cb2_behavior5: ID_behavior5.countSet_behavior5.Integer_behavior5\n"
					+ "channel get_z_behavior5_cb1_behavior5: ID_behavior5.countGet_behavior5.Integer_behavior5\n"
					+ "channel set_z_behavior5_cb1_behavior5: ID_behavior5.countSet_behavior5.Integer_behavior5\n"
					+ "channel ce_behavior5: ID_behavior5.countCe_behavior5\n"
					+ "channel oe_1_behavior5: ID_behavior5.Integer_behavior5\n"
					+ "channel oe_2_behavior5: ID_behavior5.Integer_behavior5\n"
					+ "channel clear_behavior5: ID_behavior5.countClear_behavior5\n"
					+ "channel update_behavior5: ID_behavior5.countUpdate_behavior5.limiteUpdate_behavior5\n"
					+ "channel endDiagram_behavior5: ID_behavior5\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(behavior5(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_behavior5(id) = endDiagram_behavior5.id -> SKIP\n"
					+ "behavior5(ID_behavior5) = ((Internal_behavior5(ID_behavior5) [|{|update_behavior5,clear_behavior5,endDiagram_behavior5|}|] TokenManager_behavior5_t(ID_behavior5,0,0)) [|{|get_x_behavior5,set_x_behavior5,endActivity_behavior5|}|] Mem_behavior5(ID_behavior5)) \\{|get_x_behavior5,set_x_behavior5|}\n"
					+ "Internal_behavior5(id) = StartActivity_behavior5(id); Node_behavior5(id); EndActivity_behavior5(id)\n"
					+ "StartActivity_behavior5(id) = startActivity_behavior5.id?x -> set_x_behavior5.id.3!x -> SKIP\n"
					+ "EndActivity_behavior5(id) = endActivity_behavior5.id -> SKIP\n"
					+ "AlphabetDiagram_behavior5(id,parameter_x_behavior5_t_alphabet) = {|update_behavior5.id.1,get_x_behavior5.id.1,oe_1_behavior5.id,oe_2_behavior5.id|}\n"
					+ "AlphabetDiagram_behavior5(id,behavior5_done_behavior5_t_alphabet) = {|ce_behavior5.id.1,ce_behavior5.id.2,clear_behavior5.id.1,endDiagram_behavior5.id|}\n"
					+ "AlphabetDiagram_behavior5(id,behavior5_cb2_behavior5_t_alphabet) = union({|oe_1_behavior5.id,set_z_behavior5_cb2_behavior5.id.2,get_z_behavior5_cb2_behavior5.id.3,startActivity_CB1.2,endActivity_CB1.2,ce_behavior5.id.2,endDiagram_behavior5.id|},AlphabetDiagram_CB1_t(2))\n"
					+ "AlphabetDiagram_behavior5(id,behavior5_cb1_behavior5_t_alphabet) = union({|oe_2_behavior5.id,set_z_behavior5_cb1_behavior5.id.1,get_z_behavior5_cb1_behavior5.id.2,startActivity_CB1.1,endActivity_CB1.1,ce_behavior5.id.1,endDiagram_behavior5.id|},AlphabetDiagram_CB1_t(1))\n"
					+ "AlphabetDiagram_behavior5_t(id) = union(union(union(AlphabetDiagram_behavior5(id,parameter_x_behavior5_t_alphabet),AlphabetDiagram_behavior5(id,behavior5_done_behavior5_t_alphabet)),AlphabetDiagram_behavior5(id,behavior5_cb2_behavior5_t_alphabet)),AlphabetDiagram_behavior5(id,behavior5_cb1_behavior5_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_behavior5(id,parameter_x_behavior5_t_alphabet) = normal(parameter_x_behavior5_t(id))\n"
					+ "ProcessDiagram_behavior5(id,behavior5_done_behavior5_t_alphabet) = normal(behavior5_done_behavior5_t(id))\n"
					+ "ProcessDiagram_behavior5(id,behavior5_cb2_behavior5_t_alphabet) = normal(behavior5_cb2_behavior5_t(id))\n"
					+ "ProcessDiagram_behavior5(id,behavior5_cb1_behavior5_t_alphabet) = normal(behavior5_cb1_behavior5_t(id))\n"
					+ "Node_behavior5(id) = || x:alphabet_behavior5 @ [AlphabetDiagram_behavior5(id,x)] ProcessDiagram_behavior5(id,x)\n"
					+ "parameter_x_behavior5_t(id) = update_behavior5.id.1!(2-0) -> get_x_behavior5.id.1?x -> ((oe_1_behavior5.id!x -> SKIP) ||| (oe_2_behavior5.id!x -> SKIP))\n"
					+ "behavior5_cb1_behavior5(id) = ((oe_2_behavior5.id?z -> set_z_behavior5_cb1_behavior5.id.1!z -> SKIP)); get_z_behavior5_cb1_behavior5.id.2?z -> (normal(CB1(1)) [|{|startActivity_CB1.1,endActivity_CB1.1|}|] (startActivity_CB1.1!z -> endActivity_CB1.1 -> SKIP));((ce_behavior5.id.1 -> SKIP)); behavior5_cb1_behavior5(id)\n"
					+ "behavior5_cb1_behavior5_t(id) = ((behavior5_cb1_behavior5(id)) [|AlphabetMembehavior5_cb1_behavior5(id)|] Mem_behavior5_cb1_behavior5(id)) \\diff(AlphabetMembehavior5_cb1_behavior5(id),{|endDiagram_behavior5.id|}) /\\ END_DIAGRAM_behavior5(id)\n"
					+ "behavior5_cb2_behavior5(id) = ((oe_1_behavior5.id?z -> set_z_behavior5_cb2_behavior5.id.2!z -> SKIP)); get_z_behavior5_cb2_behavior5.id.3?z -> normal(CB1(2));((ce_behavior5.id.2 -> SKIP)); behavior5_cb2_behavior5(id)\n"
					+ "behavior5_cb2_behavior5_t(id) = ((behavior5_cb2_behavior5(id)) [|AlphabetMembehavior5_cb2_behavior5(id)|] Mem_behavior5_cb2_behavior5(id)) \\diff(AlphabetMembehavior5_cb2_behavior5(id),{|endDiagram_behavior5.id|}) /\\ END_DIAGRAM_behavior5(id)\n"
					+ "behavior5_done_behavior5(id) = ((ce_behavior5.id.1 -> SKIP) [] (ce_behavior5.id.2 -> SKIP)); clear_behavior5.id.1 -> SKIP\n"
					+ "behavior5_done_behavior5_t(id) = behavior5_done_behavior5(id) /\\ END_DIAGRAM_behavior5(id)\n"
					+ "Mem_behavior5_cb2_behavior5_z(id,z) = get_z_behavior5_cb2_behavior5.id?c!z -> Mem_behavior5_cb2_behavior5_z(id,z) [] set_z_behavior5_cb2_behavior5.id?c?z -> Mem_behavior5_cb2_behavior5_z(id,z)\n"
					+ "Mem_behavior5_cb2_behavior5_z_t(id,z) = Mem_behavior5_cb2_behavior5_z(id,z) /\\ END_DIAGRAM_behavior5(id)\n"
					+ "Mem_behavior5_cb1_behavior5_z(id,z) = get_z_behavior5_cb1_behavior5.id?c!z -> Mem_behavior5_cb1_behavior5_z(id,z) [] set_z_behavior5_cb1_behavior5.id?c?z -> Mem_behavior5_cb1_behavior5_z(id,z)\n"
					+ "Mem_behavior5_cb1_behavior5_z_t(id,z) = Mem_behavior5_cb1_behavior5_z(id,z) /\\ END_DIAGRAM_behavior5(id)\n"
					+ "AlphabetMembehavior5_cb2_behavior5(id) = {|get_z_behavior5_cb2_behavior5.id,set_z_behavior5_cb2_behavior5.id,get_z_behavior5_cb1_behavior5.id,set_z_behavior5_cb1_behavior5.id,endDiagram_behavior5.id|}\n"
					+ "Mem_behavior5_cb2_behavior5(id) = Mem_behavior5_cb2_behavior5_z_t(id,0) \n"
					+ "AlphabetMembehavior5_cb1_behavior5(id) = {|get_z_behavior5_cb2_behavior5.id,set_z_behavior5_cb2_behavior5.id,get_z_behavior5_cb1_behavior5.id,set_z_behavior5_cb1_behavior5.id,endDiagram_behavior5.id|}\n"
					+ "Mem_behavior5_cb1_behavior5(id) = Mem_behavior5_cb1_behavior5_z_t(id,0) \n"
					+ "Mem_behavior5_x(id,x) = get_x_behavior5.id?c!x -> Mem_behavior5_x(id,x) [] set_x_behavior5.id?c?x -> Mem_behavior5_x(id,x)\n"
					+ "Mem_behavior5_x_t(id,x) = Mem_behavior5_x(id,x) /\\ (endActivity_behavior5.id -> SKIP)\n"
					+ "Mem_behavior5(id) = Mem_behavior5_x_t(id,0)\n"
					+ "TokenManager_behavior5(id,x,init) = update_behavior5.id?c?y:limiteUpdate_behavior5 -> x+y < 10 & x+y > -10 & TokenManager_behavior5(id,x+y,1) [] clear_behavior5.id?c -> endDiagram_behavior5.id -> SKIP [] x == 0 & init == 1 & endDiagram_behavior5.id -> SKIP\n"
					+ "TokenManager_behavior5_t(id,x,init) = TokenManager_behavior5(id,x,init)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_behavior5|}\n"
					+ "\n"
					+ "datatype alphabet_CB1 = parameter_z_CB1_t_alphabet | CB1_done_CB1_t_alphabet| CB1_act1_CB1_t_alphabet\n"
					+ "Integer_CB1 = {0..1}\n"
					+ "countGet_CB1 = {1..2}\n"
					+ "countSet_CB1 = {1..2}\n"
					+ "countCe_CB1 = {1..1}\n"
					+ "countOe_CB1 = {1..1}\n"
					+ "countUpdate_CB1 = {1..1}\n"
					+ "countClear_CB1 = {1..1}\n"
					+ "limiteUpdate_CB1 = {(1)..(1)}\n"
					+ "channel startActivity_CB1: ID_CB1.Integer_CB1\n"
					+ "channel endActivity_CB1: ID_CB1\n"
					+ "channel get_z_CB1: ID_CB1.countGet_CB1.Integer_CB1\n"
					+ "channel set_z_CB1: ID_CB1.countSet_CB1.Integer_CB1\n"
					+ "channel get_x_CB1_act1_CB1: ID_CB1.countGet_CB1.Integer_CB1\n"
					+ "channel set_x_CB1_act1_CB1: ID_CB1.countSet_CB1.Integer_CB1\n"
					+ "channel ce_CB1: ID_CB1.countCe_CB1\n"
					+ "channel oe_1_CB1: ID_CB1.Integer_CB1\n"
					+ "channel clear_CB1: ID_CB1.countClear_CB1\n"
					+ "channel update_CB1: ID_CB1.countUpdate_CB1.limiteUpdate_CB1\n"
					+ "channel endDiagram_CB1: ID_CB1\n"
					+ "channel event_CB1_act1_CB1: ID_CB1\n"
					+ "END_DIAGRAM_CB1(id) = endDiagram_CB1.id -> SKIP\n"
					+ "CB1(ID_CB1) = ((Internal_CB1(ID_CB1) [|{|update_CB1,clear_CB1,endDiagram_CB1|}|] TokenManager_CB1_t(ID_CB1,0,0)) [|{|get_z_CB1,set_z_CB1,endActivity_CB1|}|] Mem_CB1(ID_CB1)) \\{|get_z_CB1,set_z_CB1|}\n"
					+ "Internal_CB1(id) = StartActivity_CB1(id); Node_CB1(id); EndActivity_CB1(id)\n"
					+ "StartActivity_CB1(id) = startActivity_CB1.id?z -> set_z_CB1.id.2!z -> SKIP\n"
					+ "EndActivity_CB1(id) = endActivity_CB1.id -> SKIP\n"
					+ "AlphabetDiagram_CB1(id,parameter_z_CB1_t_alphabet) = {|update_CB1.id.1,get_z_CB1.id.1,oe_1_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet) = {|ce_CB1.id.1,clear_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = {|oe_1_CB1.id,set_x_CB1_act1_CB1.id.1,event_CB1_act1_CB1.id,get_x_CB1_act1_CB1.id.2,ce_CB1.id.1,endDiagram_CB1.id|}\n"
					+ "AlphabetDiagram_CB1_t(id) = union(union(AlphabetDiagram_CB1(id,parameter_z_CB1_t_alphabet),AlphabetDiagram_CB1(id,CB1_done_CB1_t_alphabet)),AlphabetDiagram_CB1(id,CB1_act1_CB1_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_CB1(id,parameter_z_CB1_t_alphabet) = normal(parameter_z_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_done_CB1_t_alphabet) = normal(CB1_done_CB1_t(id))\n"
					+ "ProcessDiagram_CB1(id,CB1_act1_CB1_t_alphabet) = normal(CB1_act1_CB1_t(id))\n"
					+ "Node_CB1(id) = || x:alphabet_CB1 @ [AlphabetDiagram_CB1(id,x)] ProcessDiagram_CB1(id,x)\n"
					+ "parameter_z_CB1_t(id) = update_CB1.id.1!(1-0) -> get_z_CB1.id.1?z -> ((oe_1_CB1.id!z -> SKIP))\n"
					+ "CB1_act1_CB1(id) = ((oe_1_CB1.id?x -> set_x_CB1_act1_CB1.id.1!x -> SKIP)); event_CB1_act1_CB1.id -> get_x_CB1_act1_CB1.id.2?x -> ((ce_CB1.id.1 -> SKIP)); CB1_act1_CB1(id)\n"
					+ "CB1_act1_CB1_t(id) = ((CB1_act1_CB1(id) /\\ END_DIAGRAM_CB1(id)) [|{|get_x_CB1_act1_CB1.id,set_x_CB1_act1_CB1.id,endDiagram_CB1.id|}|] Mem_CB1_act1_CB1_x_t(id,0)) \\{|get_x_CB1_act1_CB1.id,set_x_CB1_act1_CB1.id|}\n"
					+ "CB1_done_CB1(id) = ((ce_CB1.id.1 -> SKIP)); clear_CB1.id.1 -> SKIP\n"
					+ "CB1_done_CB1_t(id) = CB1_done_CB1(id) /\\ END_DIAGRAM_CB1(id)\n"
					+ "Mem_CB1_act1_CB1_x(id,x) = get_x_CB1_act1_CB1.id?c!x -> Mem_CB1_act1_CB1_x(id,x) [] set_x_CB1_act1_CB1.id?c?x -> Mem_CB1_act1_CB1_x(id,x)\n"
					+ "Mem_CB1_act1_CB1_x_t(id,x) = Mem_CB1_act1_CB1_x(id,x) /\\ END_DIAGRAM_CB1(id)\n"
					+ "Mem_CB1_z(id,z) = get_z_CB1.id?c!z -> Mem_CB1_z(id,z) [] set_z_CB1.id?c?z -> Mem_CB1_z(id,z)\n"
					+ "Mem_CB1_z_t(id,z) = Mem_CB1_z(id,z) /\\ (endActivity_CB1.id -> SKIP)\n"
					+ "Mem_CB1(id) = Mem_CB1_z_t(id,0)\n"
					+ "TokenManager_CB1(id,x,init) = update_CB1.id?c?y:limiteUpdate_CB1 -> x+y < 10 & x+y > -10 & TokenManager_CB1(id,x+y,1) [] clear_CB1.id?c -> endDiagram_CB1.id -> SKIP [] x == 0 & init == 1 & endDiagram_CB1.id -> SKIP\n"
					+ "TokenManager_CB1_t(id,x,init) = TokenManager_CB1(id,x,init)\n"
					+ "\n"
					+ "assert MAIN :[deadlock free]\n"
					+ "assert MAIN :[divergence free]\n"
					+ "assert MAIN :[deterministic]");
			
			assertEquals(expected.toString(), actual);
			
	    } catch (Throwable _e) {
	      throw Exceptions.sneakyThrow(_e);
	    }
	  }
	
}

