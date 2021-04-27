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


public class ADParserTestSignalNode {

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
	public void TestSignal1() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/signal1.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);			
			parser1 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser1.clearBuffer();
			
			String actual = parser1.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_signal1 = {1..1}\n"
					+ "countSignal_signal1_sendSignal = {1..1}\n"
					+ "countAccept_signal1_sendSignal = {1..1}\n"
					+ "datatype alphabet_signal1 = signal_signal1_sendSignal_1_signal1_t_alphabet | signal1_done_signal1_t_alphabet| signal1_act1_signal1_t_alphabet| parameter_x_signal1_t_alphabet| accept_signal1_sendSignal_1_signal1_t_alphabet\n"
					+ "Integer_signal1 = {0..1}\n"
					+ "countGet_signal1 = {1..3}\n"
					+ "countSet_signal1 = {1..3}\n"
					+ "countCe_signal1 = {1..1}\n"
					+ "countOe_signal1 = {1..2}\n"
					+ "countUpdate_signal1 = {1..1}\n"
					+ "countClear_signal1 = {1..1}\n"
					+ "limiteUpdate_signal1 = {(1)..(1)}\n"
					+ "channel startActivity_signal1: ID_signal1.Integer_signal1\n"
					+ "channel endActivity_signal1: ID_signal1\n"
					+ "channel get_x_signal1: ID_signal1.countGet_signal1.Integer_signal1\n"
					+ "channel set_x_signal1: ID_signal1.countSet_signal1.Integer_signal1\n"
					+ "channel get_w_signal1_act1_signal1: ID_signal1.countGet_signal1.Integer_signal1\n"
					+ "channel set_w_signal1_act1_signal1: ID_signal1.countSet_signal1.Integer_signal1\n"
					+ "channel get_x_signal1_sendSignal_signal1: ID_signal1.countGet_signal1.Integer_signal1\n"
					+ "channel set_x_signal1_sendSignal_signal1: ID_signal1.countSet_signal1.Integer_signal1\n"
					+ "channel ce_signal1: ID_signal1.countCe_signal1\n"
					+ "channel oe_1_signal1: ID_signal1.Integer_signal1\n"
					+ "channel oe_2_signal1: ID_signal1.Integer_signal1\n"
					+ "channel clear_signal1: ID_signal1.countClear_signal1\n"
					+ "channel update_signal1: ID_signal1.countUpdate_signal1.limiteUpdate_signal1\n"
					+ "channel endDiagram_signal1: ID_signal1\n"
					+ "channel event_signal1_act1_signal1: ID_signal1\n"
					+ "channel signal_signal1_sendSignal: ID_signal1.countSignal_signal1_sendSignal.Integer_signal1\n"
					+ "channel accept_signal1_sendSignal: ID_signal1.countAccept_signal1_sendSignal.countSignal_signal1_sendSignal.Integer_signal1\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(signal1(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_signal1(id) = endDiagram_signal1.id -> SKIP\n"
					+ "signal1(ID_signal1) = (((Internal_signal1(ID_signal1) [|{|update_signal1,clear_signal1,endDiagram_signal1|}|] TokenManager_signal1_t(ID_signal1,0,0))[|AlphabetPool|]pools(ID_signal1)) [|{|get_x_signal1,set_x_signal1,endActivity_signal1|}|] Mem_signal1(ID_signal1)) \\{|get_x_signal1,set_x_signal1|}\n"
					+ "Internal_signal1(id) = StartActivity_signal1(id); Node_signal1(id); EndActivity_signal1(id)\n"
					+ "StartActivity_signal1(id) = startActivity_signal1.id?x -> set_x_signal1.id.3!x -> SKIP\n"
					+ "EndActivity_signal1(id) = endActivity_signal1.id -> SKIP\n"
					+ "AlphabetDiagram_signal1(id,signal_signal1_sendSignal_1_signal1_t_alphabet) = {|oe_1_signal1.id,set_x_signal1_sendSignal_signal1.id.1,get_x_signal1_sendSignal_signal1.id.2,signal_signal1_sendSignal.id.1,endDiagram_signal1.id|}\n"
					+ "AlphabetDiagram_signal1(id,signal1_done_signal1_t_alphabet) = {|ce_signal1.id.1,clear_signal1.id.1,endDiagram_signal1.id|}\n"
					+ "AlphabetDiagram_signal1(id,signal1_act1_signal1_t_alphabet) = {|oe_2_signal1.id,set_w_signal1_act1_signal1.id.2,event_signal1_act1_signal1.id,get_w_signal1_act1_signal1.id.3,ce_signal1.id.1,endDiagram_signal1.id|}\n"
					+ "AlphabetDiagram_signal1(id,parameter_x_signal1_t_alphabet) = {|update_signal1.id.1,get_x_signal1.id.1,oe_1_signal1.id|}\n"
					+ "AlphabetDiagram_signal1(id,accept_signal1_sendSignal_1_signal1_t_alphabet) = {|accept_signal1_sendSignal.id.1,oe_2_signal1.id,endDiagram_signal1.id|}\n"
					+ "AlphabetDiagram_signal1_t(id) = union(union(union(union(AlphabetDiagram_signal1(id,signal_signal1_sendSignal_1_signal1_t_alphabet),AlphabetDiagram_signal1(id,signal1_done_signal1_t_alphabet)),AlphabetDiagram_signal1(id,signal1_act1_signal1_t_alphabet)),AlphabetDiagram_signal1(id,parameter_x_signal1_t_alphabet)),AlphabetDiagram_signal1(id,accept_signal1_sendSignal_1_signal1_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_signal1(id,signal_signal1_sendSignal_1_signal1_t_alphabet) = normal(signal_signal1_sendSignal_1_signal1_t(id))\n"
					+ "ProcessDiagram_signal1(id,signal1_done_signal1_t_alphabet) = normal(signal1_done_signal1_t(id))\n"
					+ "ProcessDiagram_signal1(id,signal1_act1_signal1_t_alphabet) = normal(signal1_act1_signal1_t(id))\n"
					+ "ProcessDiagram_signal1(id,parameter_x_signal1_t_alphabet) = normal(parameter_x_signal1_t(id))\n"
					+ "ProcessDiagram_signal1(id,accept_signal1_sendSignal_1_signal1_t_alphabet) = normal(accept_signal1_sendSignal_1_signal1_t(id))\n"
					+ "Node_signal1(id) = || x:alphabet_signal1 @ [AlphabetDiagram_signal1(id,x)] ProcessDiagram_signal1(id,x)\n"
					+ "parameter_x_signal1_t(id) = update_signal1.id.1!(1-0) -> get_x_signal1.id.1?x -> ((oe_1_signal1.id!x -> SKIP))\n"
					+ "signal_signal1_sendSignal_1_signal1(id) = ((oe_1_signal1.id?x -> set_x_signal1_sendSignal_signal1.id.1!x -> SKIP)); get_x_signal1_sendSignal_signal1.id.2?x -> signal_signal1_sendSignal.id!1!x -> signal_signal1_sendSignal_1_signal1(id)\n"
					+ "signal_signal1_sendSignal_1_signal1_t(id) = ((signal_signal1_sendSignal_1_signal1(id) /\\ END_DIAGRAM_signal1(id)) [|{|get_x_signal1_sendSignal_signal1.id,set_x_signal1_sendSignal_signal1.id,endDiagram_signal1.id|}|] Mem_signal1_sendSignal_signal1_x_t(id,0)) \\{|get_x_signal1_sendSignal_signal1.id,set_x_signal1_sendSignal_signal1.id|}\n"
					+ "accept_signal1_sendSignal_1_signal1(id) = accept_signal1_sendSignal.id.1?accept_signal1_sendSignal_1_signal1?accept_signal1_sendSignal_1_signal1_y -> ((oe_2_signal1.id!accept_signal1_sendSignal_1_signal1_y -> SKIP)); accept_signal1_sendSignal_1_signal1(id)\n"
					+ "accept_signal1_sendSignal_1_signal1_t(id) = accept_signal1_sendSignal_1_signal1(id) /\\ END_DIAGRAM_signal1(id)\n"
					+ "signal1_act1_signal1(id) = ((oe_2_signal1.id?w -> set_w_signal1_act1_signal1.id.2!w -> SKIP)); event_signal1_act1_signal1.id -> get_w_signal1_act1_signal1.id.3?w -> ((ce_signal1.id.1 -> SKIP)); signal1_act1_signal1(id)\n"
					+ "signal1_act1_signal1_t(id) = ((signal1_act1_signal1(id) /\\ END_DIAGRAM_signal1(id)) [|{|get_w_signal1_act1_signal1.id,set_w_signal1_act1_signal1.id,endDiagram_signal1.id|}|] Mem_signal1_act1_signal1_w_t(id,0)) \\{|get_w_signal1_act1_signal1.id,set_w_signal1_act1_signal1.id|}\n"
					+ "signal1_done_signal1(id) = ((ce_signal1.id.1 -> SKIP)); clear_signal1.id.1 -> SKIP\n"
					+ "signal1_done_signal1_t(id) = signal1_done_signal1(id) /\\ END_DIAGRAM_signal1(id)\n"
					+ "Mem_signal1_act1_signal1_w(id,w) = get_w_signal1_act1_signal1.id?c!w -> Mem_signal1_act1_signal1_w(id,w) [] set_w_signal1_act1_signal1.id?c?w -> Mem_signal1_act1_signal1_w(id,w)\n"
					+ "Mem_signal1_act1_signal1_w_t(id,w) = Mem_signal1_act1_signal1_w(id,w) /\\ END_DIAGRAM_signal1(id)\n"
					+ "Mem_signal1_sendSignal_signal1_x(id,x) = get_x_signal1_sendSignal_signal1.id?c!x -> Mem_signal1_sendSignal_signal1_x(id,x) [] set_x_signal1_sendSignal_signal1.id?c?x -> Mem_signal1_sendSignal_signal1_x(id,x)\n"
					+ "Mem_signal1_sendSignal_signal1_x_t(id,x) = Mem_signal1_sendSignal_signal1_x(id,x) /\\ END_DIAGRAM_signal1(id)\n"
					+ "Mem_signal1_x(id,x) = get_x_signal1.id?c!x -> Mem_signal1_x(id,x) [] set_x_signal1.id?c?x -> Mem_signal1_x(id,x)\n"
					+ "Mem_signal1_x_t(id,x) = Mem_signal1_x(id,x) /\\ (endActivity_signal1.id -> SKIP)\n"
					+ "Mem_signal1(id) = Mem_signal1_x_t(id,0)\n"
					+ "TokenManager_signal1(id,x,init) = update_signal1.id?c?y:limiteUpdate_signal1 -> x+y < 10 & x+y > -10 & TokenManager_signal1(id,x+y,1) [] clear_signal1.id?c -> endDiagram_signal1.id -> SKIP [] x == 0 & init == 1 & endDiagram_signal1.id -> SKIP\n"
					+ "TokenManager_signal1_t(id,x,init) = TokenManager_signal1(id,x,init)\n"
					+ "datatype POOLNAME = signal1_sendSignal\n"
					+ "POOL(id,signal1_sendSignal) = pool_signal1_sendSignal_t(id,<>)\n"
					+ "pools(id) =[|{|endDiagram_signal1.id|}|]x:POOLNAME @ POOL(id,x)\n"
					+ "pool_signal1_sendSignal(l) = (signal_signal1_sendSignal?id?event_signal1_sendSignal_signal1 -> if length(l) < 5 then pool_signal1_sendSignal(l^<event_signal1_sendSignal_signal1>) else pool_signal1_sendSignal(l)) [] (length(l) > 0 & accept_signal1_sendSignal?id.1!head(l) -> pool_signal1_sendSignal(tail(l)))\n"
					+ "pool_signal1_sendSignal_t(id,l) = pool_signal1_sendSignal(l) /\\ END_DIAGRAM_signal1(id)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_signal1,signal_signal1_sendSignal,accept_signal1_sendSignal|}\n"
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
	public void TestSignal2() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/signal2.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);			
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser2.clearBuffer();
			
			String actual = parser2.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_signal2 = {1..1}\n"
					+ "countSignal_signal2_sendSignal = {1..1}\n"
					+ "countAccept_signal2_sendSignal = {1..1}\n"
					+ "datatype alphabet_signal2 = signal_signal2_sendSignal_1_signal2_t_alphabet | signal2_done_signal2_t_alphabet| signal2_act2_signal2_t_alphabet| signal2_act1_signal2_t_alphabet| parameter_x_signal2_t_alphabet| accept_signal2_sendSignal_1_signal2_t_alphabet\n"
					+ "Integer_signal2 = {0..1}\n"
					+ "countGet_signal2 = {1..4}\n"
					+ "countSet_signal2 = {1..4}\n"
					+ "countCe_signal2 = {1..2}\n"
					+ "countOe_signal2 = {1..3}\n"
					+ "countUpdate_signal2 = {1..1}\n"
					+ "countClear_signal2 = {1..1}\n"
					+ "limiteUpdate_signal2 = {(1)..(1)}\n"
					+ "channel startActivity_signal2: ID_signal2.Integer_signal2\n"
					+ "channel endActivity_signal2: ID_signal2\n"
					+ "channel get_x_signal2: ID_signal2.countGet_signal2.Integer_signal2\n"
					+ "channel set_x_signal2: ID_signal2.countSet_signal2.Integer_signal2\n"
					+ "channel get_x_signal2_sendSignal_signal2: ID_signal2.countGet_signal2.Integer_signal2\n"
					+ "channel set_x_signal2_sendSignal_signal2: ID_signal2.countSet_signal2.Integer_signal2\n"
					+ "channel get_w_signal2_act1_signal2: ID_signal2.countGet_signal2.Integer_signal2\n"
					+ "channel set_w_signal2_act1_signal2: ID_signal2.countSet_signal2.Integer_signal2\n"
					+ "channel get_z_signal2_act2_signal2: ID_signal2.countGet_signal2.Integer_signal2\n"
					+ "channel set_z_signal2_act2_signal2: ID_signal2.countSet_signal2.Integer_signal2\n"
					+ "channel ce_signal2: ID_signal2.countCe_signal2\n"
					+ "channel oe_1_signal2: ID_signal2.Integer_signal2\n"
					+ "channel oe_2_signal2: ID_signal2.Integer_signal2\n"
					+ "channel oe_3_signal2: ID_signal2.Integer_signal2\n"
					+ "channel clear_signal2: ID_signal2.countClear_signal2\n"
					+ "channel update_signal2: ID_signal2.countUpdate_signal2.limiteUpdate_signal2\n"
					+ "channel endDiagram_signal2: ID_signal2\n"
					+ "channel event_signal2_act1_signal2,event_signal2_act2_signal2: ID_signal2\n"
					+ "channel signal_signal2_sendSignal: ID_signal2.countSignal_signal2_sendSignal.Integer_signal2\n"
					+ "channel accept_signal2_sendSignal: ID_signal2.countAccept_signal2_sendSignal.countSignal_signal2_sendSignal.Integer_signal2\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(signal2(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_signal2(id) = endDiagram_signal2.id -> SKIP\n"
					+ "signal2(ID_signal2) = (((Internal_signal2(ID_signal2) [|{|update_signal2,clear_signal2,endDiagram_signal2|}|] TokenManager_signal2_t(ID_signal2,0,0))[|AlphabetPool|]pools(ID_signal2)) [|{|get_x_signal2,set_x_signal2,endActivity_signal2|}|] Mem_signal2(ID_signal2)) \\{|get_x_signal2,set_x_signal2|}\n"
					+ "Internal_signal2(id) = StartActivity_signal2(id); Node_signal2(id); EndActivity_signal2(id)\n"
					+ "StartActivity_signal2(id) = startActivity_signal2.id?x -> set_x_signal2.id.4!x -> SKIP\n"
					+ "EndActivity_signal2(id) = endActivity_signal2.id -> SKIP\n"
					+ "AlphabetDiagram_signal2(id,signal_signal2_sendSignal_1_signal2_t_alphabet) = {|oe_1_signal2.id,set_x_signal2_sendSignal_signal2.id.1,get_x_signal2_sendSignal_signal2.id.2,signal_signal2_sendSignal.id.1,endDiagram_signal2.id|}\n"
					+ "AlphabetDiagram_signal2(id,signal2_done_signal2_t_alphabet) = {|ce_signal2.id.1,ce_signal2.id.2,clear_signal2.id.1,endDiagram_signal2.id|}\n"
					+ "AlphabetDiagram_signal2(id,signal2_act2_signal2_t_alphabet) = {|oe_3_signal2.id,set_z_signal2_act2_signal2.id.3,event_signal2_act2_signal2.id,get_z_signal2_act2_signal2.id.4,ce_signal2.id.2,endDiagram_signal2.id|}\n"
					+ "AlphabetDiagram_signal2(id,signal2_act1_signal2_t_alphabet) = {|oe_2_signal2.id,set_w_signal2_act1_signal2.id.2,event_signal2_act1_signal2.id,get_w_signal2_act1_signal2.id.3,ce_signal2.id.1,endDiagram_signal2.id|}\n"
					+ "AlphabetDiagram_signal2(id,parameter_x_signal2_t_alphabet) = {|update_signal2.id.1,get_x_signal2.id.1,oe_1_signal2.id|}\n"
					+ "AlphabetDiagram_signal2(id,accept_signal2_sendSignal_1_signal2_t_alphabet) = {|accept_signal2_sendSignal.id.1,oe_2_signal2.id,oe_3_signal2.id,endDiagram_signal2.id|}\n"
					+ "AlphabetDiagram_signal2_t(id) = union(union(union(union(union(AlphabetDiagram_signal2(id,signal_signal2_sendSignal_1_signal2_t_alphabet),AlphabetDiagram_signal2(id,signal2_done_signal2_t_alphabet)),AlphabetDiagram_signal2(id,signal2_act2_signal2_t_alphabet)),AlphabetDiagram_signal2(id,signal2_act1_signal2_t_alphabet)),AlphabetDiagram_signal2(id,parameter_x_signal2_t_alphabet)),AlphabetDiagram_signal2(id,accept_signal2_sendSignal_1_signal2_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_signal2(id,signal_signal2_sendSignal_1_signal2_t_alphabet) = normal(signal_signal2_sendSignal_1_signal2_t(id))\n"
					+ "ProcessDiagram_signal2(id,signal2_done_signal2_t_alphabet) = normal(signal2_done_signal2_t(id))\n"
					+ "ProcessDiagram_signal2(id,signal2_act2_signal2_t_alphabet) = normal(signal2_act2_signal2_t(id))\n"
					+ "ProcessDiagram_signal2(id,signal2_act1_signal2_t_alphabet) = normal(signal2_act1_signal2_t(id))\n"
					+ "ProcessDiagram_signal2(id,parameter_x_signal2_t_alphabet) = normal(parameter_x_signal2_t(id))\n"
					+ "ProcessDiagram_signal2(id,accept_signal2_sendSignal_1_signal2_t_alphabet) = normal(accept_signal2_sendSignal_1_signal2_t(id))\n"
					+ "Node_signal2(id) = || x:alphabet_signal2 @ [AlphabetDiagram_signal2(id,x)] ProcessDiagram_signal2(id,x)\n"
					+ "parameter_x_signal2_t(id) = update_signal2.id.1!(1-0) -> get_x_signal2.id.1?x -> ((oe_1_signal2.id!x -> SKIP))\n"
					+ "signal_signal2_sendSignal_1_signal2(id) = ((oe_1_signal2.id?x -> set_x_signal2_sendSignal_signal2.id.1!x -> SKIP)); get_x_signal2_sendSignal_signal2.id.2?x -> signal_signal2_sendSignal.id!1!x -> signal_signal2_sendSignal_1_signal2(id)\n"
					+ "signal_signal2_sendSignal_1_signal2_t(id) = ((signal_signal2_sendSignal_1_signal2(id) /\\ END_DIAGRAM_signal2(id)) [|{|get_x_signal2_sendSignal_signal2.id,set_x_signal2_sendSignal_signal2.id,endDiagram_signal2.id|}|] Mem_signal2_sendSignal_signal2_x_t(id,0)) \\{|get_x_signal2_sendSignal_signal2.id,set_x_signal2_sendSignal_signal2.id|}\n"
					+ "accept_signal2_sendSignal_1_signal2(id) = accept_signal2_sendSignal.id.1?accept_signal2_sendSignal_1_signal2?accept_signal2_sendSignal_1_signal2_y -> ((oe_2_signal2.id!accept_signal2_sendSignal_1_signal2_y -> SKIP) ||| (oe_3_signal2.id!accept_signal2_sendSignal_1_signal2_y -> SKIP)); accept_signal2_sendSignal_1_signal2(id)\n"
					+ "accept_signal2_sendSignal_1_signal2_t(id) = accept_signal2_sendSignal_1_signal2(id) /\\ END_DIAGRAM_signal2(id)\n"
					+ "signal2_act1_signal2(id) = ((oe_2_signal2.id?w -> set_w_signal2_act1_signal2.id.2!w -> SKIP)); event_signal2_act1_signal2.id -> get_w_signal2_act1_signal2.id.3?w -> ((ce_signal2.id.1 -> SKIP)); signal2_act1_signal2(id)\n"
					+ "signal2_act1_signal2_t(id) = ((signal2_act1_signal2(id) /\\ END_DIAGRAM_signal2(id)) [|{|get_w_signal2_act1_signal2.id,set_w_signal2_act1_signal2.id,endDiagram_signal2.id|}|] Mem_signal2_act1_signal2_w_t(id,0)) \\{|get_w_signal2_act1_signal2.id,set_w_signal2_act1_signal2.id|}\n"
					+ "signal2_act2_signal2(id) = ((oe_3_signal2.id?z -> set_z_signal2_act2_signal2.id.3!z -> SKIP)); event_signal2_act2_signal2.id -> get_z_signal2_act2_signal2.id.4?z -> ((ce_signal2.id.2 -> SKIP)); signal2_act2_signal2(id)\n"
					+ "signal2_act2_signal2_t(id) = ((signal2_act2_signal2(id) /\\ END_DIAGRAM_signal2(id)) [|{|get_z_signal2_act2_signal2.id,set_z_signal2_act2_signal2.id,endDiagram_signal2.id|}|] Mem_signal2_act2_signal2_z_t(id,0)) \\{|get_z_signal2_act2_signal2.id,set_z_signal2_act2_signal2.id|}\n"
					+ "signal2_done_signal2(id) = ((ce_signal2.id.1 -> SKIP) [] (ce_signal2.id.2 -> SKIP)); clear_signal2.id.1 -> SKIP\n"
					+ "signal2_done_signal2_t(id) = signal2_done_signal2(id) /\\ END_DIAGRAM_signal2(id)\n"
					+ "Mem_signal2_sendSignal_signal2_x(id,x) = get_x_signal2_sendSignal_signal2.id?c!x -> Mem_signal2_sendSignal_signal2_x(id,x) [] set_x_signal2_sendSignal_signal2.id?c?x -> Mem_signal2_sendSignal_signal2_x(id,x)\n"
					+ "Mem_signal2_sendSignal_signal2_x_t(id,x) = Mem_signal2_sendSignal_signal2_x(id,x) /\\ END_DIAGRAM_signal2(id)\n"
					+ "Mem_signal2_act1_signal2_w(id,w) = get_w_signal2_act1_signal2.id?c!w -> Mem_signal2_act1_signal2_w(id,w) [] set_w_signal2_act1_signal2.id?c?w -> Mem_signal2_act1_signal2_w(id,w)\n"
					+ "Mem_signal2_act1_signal2_w_t(id,w) = Mem_signal2_act1_signal2_w(id,w) /\\ END_DIAGRAM_signal2(id)\n"
					+ "Mem_signal2_act2_signal2_z(id,z) = get_z_signal2_act2_signal2.id?c!z -> Mem_signal2_act2_signal2_z(id,z) [] set_z_signal2_act2_signal2.id?c?z -> Mem_signal2_act2_signal2_z(id,z)\n"
					+ "Mem_signal2_act2_signal2_z_t(id,z) = Mem_signal2_act2_signal2_z(id,z) /\\ END_DIAGRAM_signal2(id)\n"
					+ "Mem_signal2_x(id,x) = get_x_signal2.id?c!x -> Mem_signal2_x(id,x) [] set_x_signal2.id?c?x -> Mem_signal2_x(id,x)\n"
					+ "Mem_signal2_x_t(id,x) = Mem_signal2_x(id,x) /\\ (endActivity_signal2.id -> SKIP)\n"
					+ "Mem_signal2(id) = Mem_signal2_x_t(id,0)\n"
					+ "TokenManager_signal2(id,x,init) = update_signal2.id?c?y:limiteUpdate_signal2 -> x+y < 10 & x+y > -10 & TokenManager_signal2(id,x+y,1) [] clear_signal2.id?c -> endDiagram_signal2.id -> SKIP [] x == 0 & init == 1 & endDiagram_signal2.id -> SKIP\n"
					+ "TokenManager_signal2_t(id,x,init) = TokenManager_signal2(id,x,init)\n"
					+ "datatype POOLNAME = signal2_sendSignal\n"
					+ "POOL(id,signal2_sendSignal) = pool_signal2_sendSignal_t(id,<>)\n"
					+ "pools(id) =[|{|endDiagram_signal2.id|}|]x:POOLNAME @ POOL(id,x)\n"
					+ "pool_signal2_sendSignal(l) = (signal_signal2_sendSignal?id?event_signal2_sendSignal_signal2 -> if length(l) < 5 then pool_signal2_sendSignal(l^<event_signal2_sendSignal_signal2>) else pool_signal2_sendSignal(l)) [] (length(l) > 0 & accept_signal2_sendSignal?id.1!head(l) -> pool_signal2_sendSignal(tail(l)))\n"
					+ "pool_signal2_sendSignal_t(id,l) = pool_signal2_sendSignal(l) /\\ END_DIAGRAM_signal2(id)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_signal2,signal_signal2_sendSignal,accept_signal2_sendSignal|}\n"
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
	public void TestSignal3() {
	    try {
	    	Resource resource = resourceSet.getResource(URI.createFileURI("./src/test/resources/sysml/signal3.sysml"), true);
			Namespace model = (Namespace) resource.getContents().get(0);
			ActivityDiagram ad = new ActivityDiagram(model);			
			parser3 = new ADParser(ad.getActivity(), ad.getName(), ad);	
			parser3.clearBuffer();
			
			String actual = parser3.parserDiagram();
			StringBuffer expected = new StringBuffer();
			
			expected.append("transparent normal\n"
					+ "ID_signal3 = {1..1}\n"
					+ "countSignal_signal3_sendSignal = {1..1}\n"
					+ "countAccept_signal3_sendSignal = {1..1}\n"
					+ "datatype alphabet_signal3 = signal_signal3_sendSignal_1_signal3_t_alphabet | signal3_start_signal3_t_alphabet| signal3_done_signal3_t_alphabet| signal3_act2_signal3_t_alphabet| signal3_act1_signal3_t_alphabet| parameter_x_signal3_t_alphabet| accept_signal3_sendSignal_1_signal3_t_alphabet\n"
					+ "Integer_signal3 = {0..1}\n"
					+ "countGet_signal3 = {1..3}\n"
					+ "countSet_signal3 = {1..3}\n"
					+ "countCe_signal3 = {1..4}\n"
					+ "countOe_signal3 = {1..2}\n"
					+ "countUpdate_signal3 = {1..2}\n"
					+ "countClear_signal3 = {1..1}\n"
					+ "limiteUpdate_signal3 = {(1)..(1)}\n"
					+ "channel startActivity_signal3: ID_signal3.Integer_signal3\n"
					+ "channel endActivity_signal3: ID_signal3\n"
					+ "channel get_x_signal3: ID_signal3.countGet_signal3.Integer_signal3\n"
					+ "channel set_x_signal3: ID_signal3.countSet_signal3.Integer_signal3\n"
					+ "channel get_w_signal3_act1_signal3: ID_signal3.countGet_signal3.Integer_signal3\n"
					+ "channel set_w_signal3_act1_signal3: ID_signal3.countSet_signal3.Integer_signal3\n"
					+ "channel get_x_signal3_sendSignal_signal3: ID_signal3.countGet_signal3.Integer_signal3\n"
					+ "channel set_x_signal3_sendSignal_signal3: ID_signal3.countSet_signal3.Integer_signal3\n"
					+ "channel ce_signal3: ID_signal3.countCe_signal3\n"
					+ "channel oe_1_signal3: ID_signal3.Integer_signal3\n"
					+ "channel oe_2_signal3: ID_signal3.Integer_signal3\n"
					+ "channel clear_signal3: ID_signal3.countClear_signal3\n"
					+ "channel update_signal3: ID_signal3.countUpdate_signal3.limiteUpdate_signal3\n"
					+ "channel endDiagram_signal3: ID_signal3\n"
					+ "channel event_signal3_act1_signal3,event_signal3_act2_signal3: ID_signal3\n"
					+ "channel signal_signal3_sendSignal: ID_signal3.countSignal_signal3_sendSignal.Integer_signal3\n"
					+ "channel accept_signal3_sendSignal: ID_signal3.countAccept_signal3_sendSignal.countSignal_signal3_sendSignal.Integer_signal3\n"
					+ "channel loop\n"
					+ "channel dc\n"
					+ "MAIN = normal(signal3(1)); LOOP\n"
					+ "LOOP = loop -> LOOP\n"
					+ "END_DIAGRAM_signal3(id) = endDiagram_signal3.id -> SKIP\n"
					+ "signal3(ID_signal3) = (((Internal_signal3(ID_signal3) [|{|update_signal3,clear_signal3,endDiagram_signal3|}|] TokenManager_signal3_t(ID_signal3,0,0))[|AlphabetPool|]pools(ID_signal3)) [|{|get_x_signal3,set_x_signal3,endActivity_signal3|}|] Mem_signal3(ID_signal3)) \\{|get_x_signal3,set_x_signal3|}\n"
					+ "Internal_signal3(id) = StartActivity_signal3(id); Node_signal3(id); EndActivity_signal3(id)\n"
					+ "StartActivity_signal3(id) = startActivity_signal3.id?x -> set_x_signal3.id.3!x -> SKIP\n"
					+ "EndActivity_signal3(id) = endActivity_signal3.id -> SKIP\n"
					+ "AlphabetDiagram_signal3(id,signal_signal3_sendSignal_1_signal3_t_alphabet) = {|ce_signal3.id.1,oe_1_signal3.id,set_x_signal3_sendSignal_signal3.id.1,get_x_signal3_sendSignal_signal3.id.2,signal_signal3_sendSignal.id.1,endDiagram_signal3.id|}\n"
					+ "AlphabetDiagram_signal3(id,signal3_start_signal3_t_alphabet) = {|update_signal3.id.2,ce_signal3.id.1,endDiagram_signal3.id|}\n"
					+ "AlphabetDiagram_signal3(id,signal3_done_signal3_t_alphabet) = {|ce_signal3.id.3,ce_signal3.id.4,clear_signal3.id.1,endDiagram_signal3.id|}\n"
					+ "AlphabetDiagram_signal3(id,signal3_act2_signal3_t_alphabet) = {|ce_signal3.id.2,event_signal3_act2_signal3.id,ce_signal3.id.4,endDiagram_signal3.id|}\n"
					+ "AlphabetDiagram_signal3(id,signal3_act1_signal3_t_alphabet) = {|oe_2_signal3.id,set_w_signal3_act1_signal3.id.2,event_signal3_act1_signal3.id,get_w_signal3_act1_signal3.id.3,ce_signal3.id.3,endDiagram_signal3.id|}\n"
					+ "AlphabetDiagram_signal3(id,parameter_x_signal3_t_alphabet) = {|update_signal3.id.1,get_x_signal3.id.1,oe_1_signal3.id|}\n"
					+ "AlphabetDiagram_signal3(id,accept_signal3_sendSignal_1_signal3_t_alphabet) = {|accept_signal3_sendSignal.id.1,ce_signal3.id.2,oe_2_signal3.id,endDiagram_signal3.id|}\n"
					+ "AlphabetDiagram_signal3_t(id) = union(union(union(union(union(union(AlphabetDiagram_signal3(id,signal_signal3_sendSignal_1_signal3_t_alphabet),AlphabetDiagram_signal3(id,signal3_start_signal3_t_alphabet)),AlphabetDiagram_signal3(id,signal3_done_signal3_t_alphabet)),AlphabetDiagram_signal3(id,signal3_act2_signal3_t_alphabet)),AlphabetDiagram_signal3(id,signal3_act1_signal3_t_alphabet)),AlphabetDiagram_signal3(id,parameter_x_signal3_t_alphabet)),AlphabetDiagram_signal3(id,accept_signal3_sendSignal_1_signal3_t_alphabet))\n"
					+ "\n"
					+ "ProcessDiagram_signal3(id,signal_signal3_sendSignal_1_signal3_t_alphabet) = normal(signal_signal3_sendSignal_1_signal3_t(id))\n"
					+ "ProcessDiagram_signal3(id,signal3_start_signal3_t_alphabet) = normal(signal3_start_signal3_t(id))\n"
					+ "ProcessDiagram_signal3(id,signal3_done_signal3_t_alphabet) = normal(signal3_done_signal3_t(id))\n"
					+ "ProcessDiagram_signal3(id,signal3_act2_signal3_t_alphabet) = normal(signal3_act2_signal3_t(id))\n"
					+ "ProcessDiagram_signal3(id,signal3_act1_signal3_t_alphabet) = normal(signal3_act1_signal3_t(id))\n"
					+ "ProcessDiagram_signal3(id,parameter_x_signal3_t_alphabet) = normal(parameter_x_signal3_t(id))\n"
					+ "ProcessDiagram_signal3(id,accept_signal3_sendSignal_1_signal3_t_alphabet) = normal(accept_signal3_sendSignal_1_signal3_t(id))\n"
					+ "Node_signal3(id) = || x:alphabet_signal3 @ [AlphabetDiagram_signal3(id,x)] ProcessDiagram_signal3(id,x)\n"
					+ "parameter_x_signal3_t(id) = update_signal3.id.1!(1-0) -> get_x_signal3.id.1?x -> ((oe_1_signal3.id!x -> SKIP))\n"
					+ "signal3_start_signal3(id) = update_signal3.id.2!(1-0) -> ((ce_signal3.id.1 -> SKIP))\n"
					+ "signal3_start_signal3_t(id) = signal3_start_signal3(id) /\\ END_DIAGRAM_signal3(id)\n"
					+ "signal_signal3_sendSignal_1_signal3(id) = ((ce_signal3.id.1 -> SKIP) ||| (oe_1_signal3.id?x -> set_x_signal3_sendSignal_signal3.id.1!x -> SKIP)); get_x_signal3_sendSignal_signal3.id.2?x -> signal_signal3_sendSignal.id!1!x -> signal_signal3_sendSignal_1_signal3(id)\n"
					+ "signal_signal3_sendSignal_1_signal3_t(id) = ((signal_signal3_sendSignal_1_signal3(id) /\\ END_DIAGRAM_signal3(id)) [|{|get_x_signal3_sendSignal_signal3.id,set_x_signal3_sendSignal_signal3.id,endDiagram_signal3.id|}|] Mem_signal3_sendSignal_signal3_x_t(id,0)) \\{|get_x_signal3_sendSignal_signal3.id,set_x_signal3_sendSignal_signal3.id|}\n"
					+ "accept_signal3_sendSignal_1_signal3(id) = accept_signal3_sendSignal.id.1?accept_signal3_sendSignal_1_signal3?accept_signal3_sendSignal_1_signal3_y -> ((ce_signal3.id.2 -> SKIP) ||| (oe_2_signal3.id!accept_signal3_sendSignal_1_signal3_y -> SKIP)); accept_signal3_sendSignal_1_signal3(id)\n"
					+ "accept_signal3_sendSignal_1_signal3_t(id) = accept_signal3_sendSignal_1_signal3(id) /\\ END_DIAGRAM_signal3(id)\n"
					+ "signal3_act1_signal3(id) = ((oe_2_signal3.id?w -> set_w_signal3_act1_signal3.id.2!w -> SKIP)); event_signal3_act1_signal3.id -> get_w_signal3_act1_signal3.id.3?w -> ((ce_signal3.id.3 -> SKIP)); signal3_act1_signal3(id)\n"
					+ "signal3_act1_signal3_t(id) = ((signal3_act1_signal3(id) /\\ END_DIAGRAM_signal3(id)) [|{|get_w_signal3_act1_signal3.id,set_w_signal3_act1_signal3.id,endDiagram_signal3.id|}|] Mem_signal3_act1_signal3_w_t(id,0)) \\{|get_w_signal3_act1_signal3.id,set_w_signal3_act1_signal3.id|}\n"
					+ "signal3_act2_signal3(id) = ((ce_signal3.id.2 -> SKIP)); event_signal3_act2_signal3.id -> ((ce_signal3.id.4 -> SKIP)); signal3_act2_signal3(id)\n"
					+ "signal3_act2_signal3_t(id) = signal3_act2_signal3(id) /\\ END_DIAGRAM_signal3(id)\n"
					+ "signal3_done_signal3(id) = ((ce_signal3.id.3 -> SKIP) [] (ce_signal3.id.4 -> SKIP)); clear_signal3.id.1 -> SKIP\n"
					+ "signal3_done_signal3_t(id) = signal3_done_signal3(id) /\\ END_DIAGRAM_signal3(id)\n"
					+ "Mem_signal3_act1_signal3_w(id,w) = get_w_signal3_act1_signal3.id?c!w -> Mem_signal3_act1_signal3_w(id,w) [] set_w_signal3_act1_signal3.id?c?w -> Mem_signal3_act1_signal3_w(id,w)\n"
					+ "Mem_signal3_act1_signal3_w_t(id,w) = Mem_signal3_act1_signal3_w(id,w) /\\ END_DIAGRAM_signal3(id)\n"
					+ "Mem_signal3_sendSignal_signal3_x(id,x) = get_x_signal3_sendSignal_signal3.id?c!x -> Mem_signal3_sendSignal_signal3_x(id,x) [] set_x_signal3_sendSignal_signal3.id?c?x -> Mem_signal3_sendSignal_signal3_x(id,x)\n"
					+ "Mem_signal3_sendSignal_signal3_x_t(id,x) = Mem_signal3_sendSignal_signal3_x(id,x) /\\ END_DIAGRAM_signal3(id)\n"
					+ "Mem_signal3_x(id,x) = get_x_signal3.id?c!x -> Mem_signal3_x(id,x) [] set_x_signal3.id?c?x -> Mem_signal3_x(id,x)\n"
					+ "Mem_signal3_x_t(id,x) = Mem_signal3_x(id,x) /\\ (endActivity_signal3.id -> SKIP)\n"
					+ "Mem_signal3(id) = Mem_signal3_x_t(id,0)\n"
					+ "TokenManager_signal3(id,x,init) = update_signal3.id?c?y:limiteUpdate_signal3 -> x+y < 10 & x+y > -10 & TokenManager_signal3(id,x+y,1) [] clear_signal3.id?c -> endDiagram_signal3.id -> SKIP [] x == 0 & init == 1 & endDiagram_signal3.id -> SKIP\n"
					+ "TokenManager_signal3_t(id,x,init) = TokenManager_signal3(id,x,init)\n"
					+ "datatype POOLNAME = signal3_sendSignal\n"
					+ "POOL(id,signal3_sendSignal) = pool_signal3_sendSignal_t(id,<>)\n"
					+ "pools(id) =[|{|endDiagram_signal3.id|}|]x:POOLNAME @ POOL(id,x)\n"
					+ "pool_signal3_sendSignal(l) = (signal_signal3_sendSignal?id?event_signal3_sendSignal_signal3 -> if length(l) < 5 then pool_signal3_sendSignal(l^<event_signal3_sendSignal_signal3>) else pool_signal3_sendSignal(l)) [] (length(l) > 0 & accept_signal3_sendSignal?id.1!head(l) -> pool_signal3_sendSignal(tail(l)))\n"
					+ "pool_signal3_sendSignal_t(id,l) = pool_signal3_sendSignal(l) /\\ END_DIAGRAM_signal3(id)\n"
					+ "\n"
					+ "AlphabetPool = {|endDiagram_signal3,accept_signal3_sendSignal,signal_signal3_sendSignal|}\n"
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

