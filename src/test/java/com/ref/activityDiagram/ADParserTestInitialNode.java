package com.ref.activityDiagram;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.model.IActivityDiagram;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.project.ModelFinder;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.ref.astah.adapter.Activity;
import com.ref.astah.adapter.ActivityDiagram;
import com.ref.exceptions.ParsingException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.parser.activityDiagram.ADParser;

public class ADParserTestInitialNode {

	public static IActivityDiagram ad;
	private static ADParser parser1;
	private static ADParser parser2;
	private static ADParser parser3;
	private static ADParser parser4;
	private static ADParser parser5;
	private static ADParser parser6;
	private static ADParser parser7;
	
	@BeforeClass
	public static void GetDiagram() throws Exception {
		try {
			ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/activityDiagram/action1.asta");
			INamedElement[] findElements = findElements(projectAccessor);

			ad = (IActivityDiagram) findElements[0];
			IActivity ad_ = new Activity(ad.getActivity());
			ActivityDiagram adm_ = new ActivityDiagram(ad);
			
			ad_.setActivityDiagram(adm_);
			
			parser1 = new ADParser(ad_, ad_.getName(), adm_);
			
			/*projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/activityDiagram/initial2.asta");
			findElements = findElements(projectAccessor);

			ad = (IActivityDiagram) findElements[0];
			
			parser2 = new ADParser(ad.getActivity(), ad.getName(), ad);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/activityDiagram/join1.asta");
			findElements = findElements(projectAccessor);

			ad = (IActivityDiagram) findElements[0];
			
			parser3 = new ADParser(ad.getActivity(), ad.getName(), ad);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/activityDiagram/decision1.asta");
			findElements = findElements(projectAccessor);

			ad = (IActivityDiagram) findElements[0];
			
			parser4 = new ADParser(ad.getActivity(), ad.getName(), ad);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/activityDiagram/decision3.asta");
			findElements = findElements(projectAccessor);

			ad = (IActivityDiagram) findElements[0];
			
			parser5 = new ADParser(ad.getActivity(), ad.getName(), ad);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/activityDiagram/join2.asta");
			findElements = findElements(projectAccessor);

			ad = (IActivityDiagram) findElements[0];
			
			parser6 = new ADParser(ad.getActivity(), ad.getName(), ad);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/activityDiagram/merge3.asta");
			findElements = findElements(projectAccessor);

			ad = (IActivityDiagram) findElements[0];
			
			parser7 = new ADParser(ad.getActivity(), ad.getName(), ad);
		
		*/
	
		} catch (ProjectNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static INamedElement[] findElements(ProjectAccessor projectAccessor) throws ProjectNotFoundException {
		INamedElement[] foundElements = projectAccessor.findElements(new ModelFinder() {
			public boolean isTarget(INamedElement namedElement) {
				return namedElement instanceof IActivityDiagram;
			}
		});
		return foundElements;
	}
	
	@Before
	public void clearBuffer() {
		parser1.clearBuffer();
		/*
		parser2.clearBuffer();
		parser3.clearBuffer();
		parser4.clearBuffer();
		parser5.clearBuffer();
		parser6.clearBuffer();
		parser7.clearBuffer();
		*/
	}
	
	@AfterClass
	public static void CloseProject() throws Exception {
		AstahAPI.getAstahAPI().getProjectAccessor().close();
	}
	
	/*
	 * Teste de Tradução Initial Node
	 * */
	@Test
	public void TestNodesInitial1() throws ParsingException {
		String actual = parser1.defineNodesActionAndControl();
		StringBuffer expected = new StringBuffer();
		expected.append("nit1_Activity0(id) = update_Activity0.id.1!(1-0) -> ((ce_Activity0.id.1 -> SKIP))\n"
				+ "init1_Activity0_t(id) = init1_Activity0(id) /\\ END_DIAGRAM_Activity0(id)\n"
				+ "act1_Activity0(id) = ((ce_Activity0.id.1 -> SKIP)); event_act1_Activity0.id -> ((ce_Activity0.id.2 -> SKIP)); act1_Activity0(id)\n"
				+ "act1_Activity0_t(id) = act1_Activity0(id) /\\ END_DIAGRAM_Activity0(id)\n"
				+ "fin1_Activity0(id) = ((ce_Activity0.id.2 -> SKIP)); clear_Activity0.id.1 -> SKIP\n"
				+ "fin1_Activity0_t(id) = fin1_Activity0(id) /\\ END_DIAGRAM_Activity0(id)\n"
				+ "");
		
		assertEquals(expected.toString(), actual);
	}
	
	/*
	 * Teste de Tradução Initial Node
	 * */
	@Ignore
	@Test
	public void TestNodesInitial2() throws ParsingException {
		String actual = parser2.defineNodesActionAndControl();
		StringBuffer expected = new StringBuffer();
		expected.append("init1_initial2_t = update_initial2.1!(1-0) -> ((ce_initial2.1 -> SKIP))\n" +
				"act1_initial2 = ((ce_initial2.1 -> SKIP)); event_act1_initial2 -> ((ce_initial2.2 -> SKIP)); act1_initial2\n" +
				"act1_initial2_t = act1_initial2 /\\ END_DIAGRAM_initial2\n" +
				"init2_initial2_t = update_initial2.2!(1-0) -> ((ce_initial2.3 -> SKIP))\n" +
				"act2_initial2 = ((ce_initial2.3 -> SKIP)); event_act2_initial2 -> ((ce_initial2.4 -> SKIP)); act2_initial2\n" +
				"act2_initial2_t = act2_initial2 /\\ END_DIAGRAM_initial2\n" +
				"fin1_initial2 = ((ce_initial2.2 -> SKIP) [] (ce_initial2.4 -> SKIP)); clear_initial2.1 -> SKIP\n" +
				"fin1_initial2_t = fin1_initial2 /\\ END_DIAGRAM_initial2\n" +
				"init_initial2_t = (init1_initial2_t ||| init2_initial2_t) /\\ END_DIAGRAM_initial2\n");
		
		assertEquals(expected.toString(), actual);
	}
	
	/*
	 * Teste de Tradução Initial Node
	 * */
	@Ignore
	@Test
	public void TestNodesInitial3() throws ParsingException {
		String actual = parser3.defineNodesActionAndControl();
		StringBuffer expected = new StringBuffer();
		expected.append("init1_join1_t = update_join1.1!(2-0) -> ((ce_join1.1 -> SKIP) ||| (ce_join1.2 -> SKIP))\n" +
				"act1_join1 = ((ce_join1.1 -> SKIP)); event_act1_join1 -> ((ce_join1.3 -> SKIP)); act1_join1\n" +
				"act1_join1_t = act1_join1 /\\ END_DIAGRAM_join1\n" +
				"fin1_join1 = ((ce_join1.4 -> SKIP)); clear_join1.1 -> SKIP\n" +
				"fin1_join1_t = fin1_join1 /\\ END_DIAGRAM_join1\n" +
				"act2_join1 = ((ce_join1.2 -> SKIP)); event_act2_join1 -> ((ce_join1.5 -> SKIP)); act2_join1\n" +
				"act2_join1_t = act2_join1 /\\ END_DIAGRAM_join1\n" +
				"join1_join1 = ((ce_join1.3 -> SKIP) ||| (ce_join1.5 -> SKIP)); update_join1.2!(1-2) -> ((ce_join1.4 -> SKIP)); join1_join1\n" +
				"join1_join1_t = (join1_join1 /\\ END_DIAGRAM_join1)\n" +
				"init_join1_t = (init1_join1_t) /\\ END_DIAGRAM_join1\n");
		
		assertEquals(expected.toString(), actual);
	}
	
	/*
	 * Teste de Tradução Initial Node
	 * */
	@Ignore
	@Test
	public void TestNodesInitial4() throws ParsingException {
		String actual = parser4.defineNodesActionAndControl();
		StringBuffer expected = new StringBuffer();
		expected.append("init1_decision1_t = update_decision1.1!(1-0) -> ((ce_decision1.1 -> SKIP))\n" +
				"act1_decision1 = ((ce_decision1.2 -> SKIP)); event_act1_decision1 -> ((ce_decision1.4 -> SKIP)); act1_decision1\n" +
				"act1_decision1_t = act1_decision1 /\\ END_DIAGRAM_decision1\n" +
				"parameter_x_decision1_t = update_decision1.2!(1-0) -> get_x_decision1.1?x -> ((oe_x_decision1.1!x -> SKIP))\n" +
				"act2_decision1 = ((ce_decision1.3 -> SKIP)); event_act2_decision1 -> ((ce_decision1.5 -> SKIP)); act2_decision1\n" +
				"act2_decision1_t = act2_decision1 /\\ END_DIAGRAM_decision1\n" +
				"dec1_decision1 = ((ce_decision1.1 -> SKIP) ||| (oe_x_decision1.1?x -> set_x_dec1_decision1.1!x -> SKIP)); update_decision1.3!(1-2) -> get_x_dec1_decision1.2?x -> (x == 1 & (dc -> ce_decision1.2 -> SKIP) [] x == 0 & (dc -> ce_decision1.3 -> SKIP)); dec1_decision1\n" +
				"dec1_decision1_t = ((dec1_decision1 /\\ END_DIAGRAM_decision1) [|{|get_x_dec1_decision1,set_x_dec1_decision1,endDiagram_decision1|}|] Mem_dec1_decision1_x_t(0)) \\{|get_x_dec1_decision1,set_x_dec1_decision1,dc|}\n" +
				"fin1_decision1 = ((ce_decision1.5 -> SKIP) [] (ce_decision1.4 -> SKIP)); clear_decision1.1 -> SKIP\n" +
				"fin1_decision1_t = fin1_decision1 /\\ END_DIAGRAM_decision1\n" +
				"init_decision1_t = (init1_decision1_t ||| parameter_x_decision1_t) /\\ END_DIAGRAM_decision1\n");
		
		assertEquals(expected.toString(), actual);
	}

	/*
	 * Teste de Tradução Initial Node
	 * */
	@Ignore
	@Test
	public void TestNodesInitial5() throws ParsingException {
		String actual = parser5.defineNodesActionAndControl();
		StringBuffer expected = new StringBuffer();
		expected.append("parameter_z_decision3_t = update_decision3.1!(1-0) -> get_z_decision3.1?z -> ((oe_z_decision3.1!z -> SKIP))\n" +
				"dec1_decision3 = oe_z_decision3.1?z -> (z > 0 & (dc -> oe_z_decision3.2!z -> SKIP) [] z <= 0 & (dc -> oe_z_decision3.3!z -> SKIP)); dec1_decision3\n" +
				"dec1_decision3_t = (dec1_decision3 /\\ END_DIAGRAM_decision3) \\{|dc|}\n" +
				"act1_decision3 = ((oe_z_decision3.2?z -> set_z_act1_decision3.1!z -> SKIP)); event_act1_decision3 -> get_z_act1_decision3.2?z -> ((((z) >= 0 and (z) <= 1) & oe_z_decision3.4!(z) -> SKIP)); act1_decision3\n" +
				"act1_decision3_t = ((act1_decision3 /\\ END_DIAGRAM_decision3) [|{|get_z_act1_decision3,set_z_act1_decision3,endDiagram_decision3|}|] Mem_act1_decision3_z_t(0)) \\{|get_z_act1_decision3,set_z_act1_decision3|}\n" +
				"act2_decision3 = ((oe_z_decision3.3?z -> set_z_act2_decision3.2!z -> SKIP)); event_act2_decision3 -> get_z_act2_decision3.3?z -> ((((z) >= 0 and (z) <= 1) & oe_z_decision3.5!(z) -> SKIP)); act2_decision3\n" +
				"act2_decision3_t = ((act2_decision3 /\\ END_DIAGRAM_decision3) [|{|get_z_act2_decision3,set_z_act2_decision3,endDiagram_decision3|}|] Mem_act2_decision3_z_t(0)) \\{|get_z_act2_decision3,set_z_act2_decision3|}\n" +
				"fin1_decision3 = ((oe_z_decision3.5?z -> SKIP) [] (oe_z_decision3.4?z -> SKIP)); clear_decision3.1 -> SKIP\n" +
				"fin1_decision3_t = fin1_decision3 /\\ END_DIAGRAM_decision3\n" +
				"init_decision3_t = (parameter_z_decision3_t) /\\ END_DIAGRAM_decision3\n");
		
		assertEquals(expected.toString(), actual);
	}
	
	/*
	 * Teste de Tradução Initial Node
	 * */
	@Ignore
	@Test
	public void TestNodesInitial6() throws ParsingException {
		String actual = parser6.defineNodesActionAndControl();
		StringBuffer expected = new StringBuffer();
		expected.append("parameter_x_join2_t = update_join2.1!(2-0) -> get_x_join2.1?x -> ((oe_x_join2.1!x -> SKIP) ||| (oe_x_join2.2!x -> SKIP))\n" +
				"act2_join2 = ((oe_x_join2.1?x -> set_x_act2_join2.1!x -> SKIP)); event_act2_join2 -> get_x_act2_join2.2?x -> ((((x) >= 0 and (x) <= 1) & oe_x_join2.3!(x) -> SKIP)); act2_join2\n" +
				"act2_join2_t = ((act2_join2 /\\ END_DIAGRAM_join2) [|{|get_x_act2_join2,set_x_act2_join2,endDiagram_join2|}|] Mem_act2_join2_x_t(0)) \\{|get_x_act2_join2,set_x_act2_join2|}\n" +
				"fin1_join2 = ((oe_x_join2.4?x -> SKIP)); clear_join2.1 -> SKIP\n" +
				"fin1_join2_t = fin1_join2 /\\ END_DIAGRAM_join2\n" +
				"act1_join2 = ((oe_x_join2.2?x -> set_x_act1_join2.2!x -> SKIP)); event_act1_join2 -> get_x_act1_join2.3?x -> ((((x) >= 0 and (x) <= 1) & oe_x_join2.5!(x) -> SKIP)); act1_join2\n" +
				"act1_join2_t = ((act1_join2 /\\ END_DIAGRAM_join2) [|{|get_x_act1_join2,set_x_act1_join2,endDiagram_join2|}|] Mem_act1_join2_x_t(0)) \\{|get_x_act1_join2,set_x_act1_join2|}\n" +
				"join1_join2 = ((oe_x_join2.3?x -> set_x_join1_join2.3!x -> SKIP) ||| (oe_x_join2.5?x -> set_x_join1_join2.4!x -> SKIP)); update_join2.2!(1-2) -> get_x_join1_join2.4?x -> ((oe_x_join2.4!x -> SKIP)); join1_join2\n" +
				"join1_join2_t = ((join1_join2 /\\ END_DIAGRAM_join2) [|{|get_x_join1_join2,set_x_join1_join2,endDiagram_join2|}|] Mem_join1_join2_x_t(0)) \\{|get_x_join1_join2,set_x_join1_join2|}\n" +
				"init_join2_t = (parameter_x_join2_t) /\\ END_DIAGRAM_join2\n");
		
		assertEquals(expected.toString(), actual);
	}
	
	/*
	 * Teste de Tradução Initial Node
	 * */
	@Ignore
	@Test
	public void TestNodesInitial7() throws ParsingException {
		String actual = parser7.defineNodesActionAndControl();
		StringBuffer expected = new StringBuffer();
		expected.append("parameter_x_merge3_t = update_merge3.1!(1-0) -> get_x_merge3.1?x -> ((oe_x_merge3.1!x -> SKIP))\n" +
				"act1_merge3 = ((oe_yx_merge3.2?x -> set_x_act1_merge3.1!x -> SKIP)); event_act1_merge3 -> get_x_act1_merge3.2?x -> ((((x) >= 0 and (x) <= 1) & oe_yx_merge3.3!(x) -> SKIP)); act1_merge3\n" +
				"act1_merge3_t = ((act1_merge3 /\\ END_DIAGRAM_merge3) [|{|get_x_act1_merge3,set_x_act1_merge3,endDiagram_merge3|}|] Mem_act1_merge3_x_t(0)) \\{|get_x_act1_merge3,set_x_act1_merge3|}\n" +
				"fin1_merge3 = ((oe_yx_merge3.3?yx -> SKIP)); clear_merge3.1 -> SKIP\n" +
				"fin1_merge3_t = fin1_merge3 /\\ END_DIAGRAM_merge3\n" +
				"parameter_y_merge3_t = update_merge3.2!(1-0) -> get_y_merge3.3?y -> ((oe_y_merge3.4!y -> SKIP))\n" +
				"merge1_merge3 = ((oe_y_merge3.4?y -> set_yx_merge1_merge3.2!y -> SKIP) [] (oe_x_merge3.1?x -> set_yx_merge1_merge3.3!x -> SKIP)); get_yx_merge1_merge3.4?yx -> oe_yx_merge3.2!yx -> merge1_merge3\n" +
				"merge1_merge3_t = ((merge1_merge3 /\\ END_DIAGRAM_merge3) [|{|get_yx_merge1_merge3,set_yx_merge1_merge3,endDiagram_merge3|}|] Mem_merge1_merge3_yx_t(0)) \\{|get_yx_merge1_merge3,set_yx_merge1_merge3|}\n" +
				"init_merge3_t = (parameter_x_merge3_t ||| parameter_y_merge3_t) /\\ END_DIAGRAM_merge3\n");
		
		assertEquals(expected.toString(), actual);
	}
	
}
