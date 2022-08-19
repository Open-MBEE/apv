package com.ref.stateMachine;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.change_vision.jude.api.inf.AstahAPI;
import com.change_vision.jude.api.inf.exception.ProjectNotFoundException;
import com.change_vision.jude.api.inf.model.INamedElement;
import com.change_vision.jude.api.inf.model.IStateMachineDiagram;
import com.change_vision.jude.api.inf.project.ModelFinder;
import com.change_vision.jude.api.inf.project.ProjectAccessor;
import com.ref.astah.statemachine.adapter.StateMachine;
import com.ref.astah.statemachine.adapter.StateMachineDiagram;
import com.ref.exceptions.ParsingException;
import com.ref.interfaces.stateMachine.IStateMachine;
import com.ref.parser.stateMachine.SMParser;

public class SMParserTestStates {

	public static IStateMachineDiagram smd;
	private static SMParser parser1;
	private static SMParser parser2;
	private static SMParser parser3;
	private static SMParser parser4;
	private static SMParser parser5;
	private static SMParser parser6;
	private static SMParser parser7;
	private static SMParser parser8;
	private static SMParser parser9;
	private static SMParser parser10;
	private static SMParser parser11;
	private static SMParser parser12;
	private static SMParser parser13;
	private static SMParser parser14;
	private static SMParser parser15;
	private static SMParser parser16;
	private static SMParser parser17;

	@BeforeClass
	public static void GetDiagram() throws Exception{
		try {
			ProjectAccessor projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/st1.asta");
			INamedElement[] findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			IStateMachine sm = new StateMachine(smd.getStateMachine());
			StateMachineDiagram smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser1 = new SMParser(sm,sm.getName(), smd_);

			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/st2.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser2 = new SMParser(sm,sm.getName(), smd_);


			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/str3Teste.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser3 = new SMParser(sm,sm.getName(), smd_);

			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/st4.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser4 = new SMParser(sm,sm.getName(), smd_);

			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/st5.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser5 = new SMParser(sm,sm.getName(), smd_);

			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/st6.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser6 = new SMParser(sm,sm.getName(), smd_);

			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/st7.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser7 = new SMParser(sm,sm.getName(), smd_);

			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/choiceJunction1.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser8 = new SMParser(sm,sm.getName(), smd_);

			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/choiceJunction2.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser9 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/choiceJunction3.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser10 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/compoundSimples.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser11 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/compoundTest.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser12 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/compoundJunctionIn.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser13 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/compoundJunctionOut.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser14 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/compoundJunctionOutIn.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser15 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/deadlock.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser16 = new SMParser(sm,sm.getName(), smd_);
			
			projectAccessor = AstahAPI.getAstahAPI().getProjectAccessor();
			projectAccessor.open("src/test/resources/stateMachine/compoundSimples.asta");
			findElements = findElements(projectAccessor);

			smd = (IStateMachineDiagram) findElements[0];
			sm = new StateMachine(smd.getStateMachine());
			smd_ = new StateMachineDiagram(smd);

			sm.setStateMachineDiagram(smd_);

			parser17 = new SMParser(sm,sm.getName(), smd_);

		} catch(ProjectNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static INamedElement[] findElements(ProjectAccessor projectAccessor) throws ProjectNotFoundException {
		INamedElement[] foundElements = projectAccessor.findElements(new ModelFinder() {
			public boolean isTarget(INamedElement namedElement) {
				return namedElement instanceof IStateMachineDiagram;
			}
		});
		return foundElements;
	}

	@Before
	public void clearBuffer() {
	}

	@AfterClass
	public static void CloseProject() throws Exception {
		AstahAPI.getAstahAPI().getProjectAccessor().close();
	}
	@Ignore
	@Test
	public void TestStates() throws ParsingException{
		String actual = parser1.defineStateProcess();
		StringBuffer expected = new StringBuffer();
		expected.append("\n"
				+ "State_st_State0(init) = (init & enter.st_State0-> State_st_State0_Entry) [] (not(init) & State_st_State0_Entry)\n"
				+ "State_st_State0_Entry = EntryProc(st_State0)\n"
				+ "State_st_State0_Do = ((activateTr.tr_2x2_7f839bb5202181c6745985840802aea3 -> SKIP); DoProc(st_State0)) /\\ exit.st_State0 -> State_st_State0_Exit\n"
				+ "State_st_State0_Exit = ExitProc(st_State0)\n"
				+ "\n"
				+ "State_st_State1 = enter.st_State1 -> State_st_State1_Entry\n"
				+ "State_st_State1_Entry = EntryProc(st_State1)\n"
				+ "State_st_State1_Do = ((activateTr.tr_3i8_7f839bb5202181c6745985840802aea3 -> SKIP); DoProc(st_State1)) /\\ exit.st_State1 -> State_st_State1_Exit\n"
				+ "State_st_State1_Exit = ExitProc(st_State1)\n");

		assertEquals(expected.toString(), actual);
	}
	@Ignore
	@Test
	public void TestTransitions() throws ParsingException{
		parser1.defineMemoryProcess();
		String actual = parser1.defineTurnTransition();
		StringBuffer expected = new StringBuffer();
		expected.append("\n"
				+ "Tr_Turn_st_State1_BY_st_State0_2x2_7f839bb5202181c6745985840802aea3 = activateTr.tr_2x2_7f839bb5202181c6745985840802aea3 -> ((turnState1_.tr_2x2_7f839bb5202181c6745985840802aea3 -> exit.st_State0 -> exited.st_State0 -> enter.st_State1 -> Tr_Turn_st_State1_BY_st_State0_2x2_7f839bb5202181c6745985840802aea3))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_3i8_7f839bb5202181c6745985840802aea3 = activateTr.tr_3i8_7f839bb5202181c6745985840802aea3 -> ((exit.st_State1 -> exited.st_State1 -> final -> Tr_Turn_st_FinalState0_BY_st_State1_3i8_7f839bb5202181c6745985840802aea3))\n");

		assertEquals(expected.toString(), actual);
	}
	@Ignore
	@Test
	public void TestEntrys() throws ParsingException{
		String actual = parser1.defineEntryProcess();
		StringBuffer expected = new StringBuffer();
		expected.append("\nEntryProc(st_State0) = entry.st_State0 -> State_st_State0_Do\n"
				+ "EntryProc(st_State1) = entry.st_State1 -> State_st_State1_Do\n");
		assertEquals(expected.toString(), actual);
	}
	@Ignore
	@Test
	public void TestDos() throws ParsingException{
		String actual = parser1.defineDoProcess();
		StringBuffer expected = new StringBuffer();
		expected.append("\nDoProc(st_State0) = do.st_State0 -> SKIP\n"
				+ "DoProc(st_State1) = do.st_State1 -> SKIP\n");
		assertEquals(expected.toString(), actual);
	}
	@Ignore
	@Test
	public void TestExits() throws ParsingException{
		String actual = parser1.defineExitProcess();
		StringBuffer expected = new StringBuffer();
		expected.append("\nExitProc(st_State0) = exited.st_State0 -> State_st_State0(true)\n"
				+ "ExitProc(st_State1) = exited.st_State1 -> State_st_State1\n");
		assertEquals(expected.toString(), actual);
	}
	@Ignore
	@Test
	public void TestChannels() throws ParsingException{
		parser1.defineMemoryProcess();
		parser1.defineTurnTransition();
		String actual = parser1.defineChannels();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID = st_State0 | st_State1\n"
				+ "datatype TR_ID = tr_2x2_7f839bb5202181c6745985840802aea3 | tr_3i8_7f839bb5202181c6745985840802aea3\n\n"
				+ "channel activateTr: TR_ID\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID\n"
				+ "channel final, finalState\n"
				+ "channel turnState1_ : TR_ID\n");
		assertEquals(expected.toString(), actual);
	}
	@Ignore
	@Test
	public void TestStartSync() throws ParsingException{
		String actual = parser1.defineStartSyncProcess();
		StringBuffer expected = new StringBuffer();
		expected.append("\nStates = (State_st_State0(false) ||| State_st_State1)\n"
				+ "Transitions = (Tr_Turn_st_State1 ||| Tr_Turn_st_FinalState0)\n"
				+ "StartSync = (States [|{|activateTr, exit, exited, enter|}|] Transitions) /\\ finalState -> SKIP\n"
				+ "\n"
				+ "FinalProcess = final -> finalState -> SKIP\n"
				+ "\n"
				+ "Start = (StartSync [|{|final,finalState|}|] FinalProcess)");
		assertEquals(expected.toString(), actual);
	}

	//---------ST1-----------
	
	@Test
	public void TestResult() throws ParsingException{
		String actual = parser1.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_stateMachine1 = st_State0_stateMachine1 | st_State1_stateMachine1\n"
				+ "datatype TR_ID_stateMachine1 = tr_1yj_9b94cb436c2c111df01f159ccc55be7d | tr_2jp_9b94cb436c2c111df01f159ccc55be7d\n"
				+ "\n"
				+ "channel activateTr: TR_ID_stateMachine1\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_stateMachine1\n"
				+ "channel final_stateMachine1, finalState_stateMachine1\n"
				+ "channel turnState1, internal_stateMachine1 : TR_ID_stateMachine1\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_stateMachine1\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "State_st_State0_stateMachine1 = (enter.st_State0_stateMachine1 -> State_st_State0_Do_stateMachine1) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_stateMachine1 = interrupt.st_State0_stateMachine1 -> SKIP; exit.st_State0_stateMachine1 -> exited.st_State0_stateMachine1 -> State_st_State0_stateMachine1\n"
				+ "\n"
				+ "State_st_State1_stateMachine1 = (enter.st_State1_stateMachine1 -> State_st_State1_Do_stateMachine1) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_stateMachine1 = interrupt.st_State1_stateMachine1 -> SKIP; exit.st_State1_stateMachine1 -> exited.st_State1_stateMachine1 -> State_st_State1_stateMachine1\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_1yj_9b94cb436c2c111df01f159ccc55be7d = ((turnState1.tr_1yj_9b94cb436c2c111df01f159ccc55be7d -> exit.st_State0_stateMachine1 -> exited.st_State0_stateMachine1 -> enter.st_State1_stateMachine1 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_2jp_9b94cb436c2c111df01f159ccc55be7d = ((internal_stateMachine1.tr_2jp_9b94cb436c2c111df01f159ccc55be7d -> exit.st_State1_stateMachine1 -> exited.st_State1_stateMachine1 -> final_stateMachine1-> SKIP))\n"
				+ "\n"
				+ "States_stateMachine1 = ([|{|end|}|] x: {State_st_State0_stateMachine1, State_st_State1_stateMachine1, FinalProcess_stateMachine1} @ x)\n"
				+ "Transitions_stateMachine1 = ((Tr_Turn_st_State1_BY_st_State0_1yj_9b94cb436c2c111df01f159ccc55be7d; Transitions_stateMachine1) [] (Tr_Turn_st_FinalState0_BY_st_State1_2jp_9b94cb436c2c111df01f159ccc55be7d; Transitions_stateMachine1)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_stateMachine1= (States_stateMachine1 [[interrupt.st_State0_stateMachine1 <- turnState1.tr_1yj_9b94cb436c2c111df01f159ccc55be7d, interrupt.st_State1_stateMachine1 <- internal_stateMachine1.tr_2jp_9b94cb436c2c111df01f159ccc55be7d]]  [|{|enter.st_State0_stateMachine1, exit.st_State0_stateMachine1, exited.st_State0_stateMachine1, turnState1.tr_1yj_9b94cb436c2c111df01f159ccc55be7d, enter.st_State1_stateMachine1, exit.st_State1_stateMachine1, exited.st_State1_stateMachine1, internal_stateMachine1.tr_2jp_9b94cb436c2c111df01f159ccc55be7d, end, final_stateMachine1|}|] (enter.st_State0_stateMachine1 -> Transitions_stateMachine1)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_stateMachine1 = final_stateMachine1 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_stateMachine1); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]");
		assertEquals(expected.toString(), actual);
	}


	//------------------ ST2 -------------------
	
	@Test
	public void TestSt2() throws ParsingException{
		String actual = parser2.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State2_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_16t_22510ae7be7873da31c667c64a086efe | tr_1rz_22510ae7be7873da31c667c64a086efe | tr_2d9_22510ae7be7873da31c667c64a086efe | tr_2i_a2f7167c1975eb0344c63b077bbf885b\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Trigger, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = interrupt.st_State1_StatemachineDiagram0 -> SKIP; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = (enter.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = interrupt.st_State2_StatemachineDiagram0 -> SKIP; exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_16t_22510ae7be7873da31c667c64a086efe = ((Trigger.tr_16t_22510ae7be7873da31c667c64a086efe -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_State1_1rz_22510ae7be7873da31c667c64a086efe = ((Trigger.tr_1rz_22510ae7be7873da31c667c64a086efe -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State2_2d9_22510ae7be7873da31c667c64a086efe = ((Trigger.tr_2d9_22510ae7be7873da31c667c64a086efe -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State2_2i_a2f7167c1975eb0344c63b077bbf885b = ((internal_StatemachineDiagram0.tr_2i_a2f7167c1975eb0344c63b077bbf885b -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State0_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, State_st_State2_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State1_BY_st_State0_16t_22510ae7be7873da31c667c64a086efe; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State2_BY_st_State1_1rz_22510ae7be7873da31c667c64a086efe; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State0_BY_st_State2_2d9_22510ae7be7873da31c667c64a086efe; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State1_BY_st_State2_2i_a2f7167c1975eb0344c63b077bbf885b; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [[interrupt.st_State0_StatemachineDiagram0 <- Trigger.tr_16t_22510ae7be7873da31c667c64a086efe, interrupt.st_State1_StatemachineDiagram0 <- Trigger.tr_1rz_22510ae7be7873da31c667c64a086efe, interrupt.st_State2_StatemachineDiagram0 <- Trigger.tr_2d9_22510ae7be7873da31c667c64a086efe, interrupt.st_State2_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_2i_a2f7167c1975eb0344c63b077bbf885b]]  [|{|enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, Trigger.tr_16t_22510ae7be7873da31c667c64a086efe, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, Trigger.tr_1rz_22510ae7be7873da31c667c64a086efe, enter.st_State2_StatemachineDiagram0, exit.st_State2_StatemachineDiagram0, exited.st_State2_StatemachineDiagram0, Trigger.tr_2d9_22510ae7be7873da31c667c64a086efe, internal_StatemachineDiagram0.tr_2i_a2f7167c1975eb0344c63b077bbf885b, end, final_StatemachineDiagram0|}|] (enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]");
		assertEquals(expected.toString(), actual);
	}
	///------------------ ST3 -------------------------
	
	@Test
	public void TestSt3() throws ParsingException{
		String actual = parser3.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State2_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State0_StatemachineDiagram0 | st_State5_StatemachineDiagram0 | st_State6_StatemachineDiagram0 | st_State7_StatemachineDiagram0 | st_State8_StatemachineDiagram0 | st_State9_StatemachineDiagram0 | st_State10_StatemachineDiagram0 | st_State11_StatemachineDiagram0 | st_State12_StatemachineDiagram0 | st_State13_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1wb_4f082ffe90f2e260c3cdc0cf81fa56f2 | tr_22g_4f082ffe90f2e260c3cdc0cf81fa56f2 | tr_3us_5936f0942dd0f6e88d60ded4f7b8484e | tr_1lr_0224a60bdb586fb47f36232c82cae4c3 | tr_2lp_4e8b243018b5102228084363fe68263f | tr_36z_4e8b243018b5102228084363fe68263f | tr_3s9_4e8b243018b5102228084363fe68263f | tr_4dj_4e8b243018b5102228084363fe68263f | tr_4yt_4e8b243018b5102228084363fe68263f | tr_5k3_4e8b243018b5102228084363fe68263f | tr_65d_4e8b243018b5102228084363fe68263f | tr_6qn_4e8b243018b5102228084363fe68263f\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Trigger, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = (enter.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = interrupt.st_State2_StatemachineDiagram0 -> SKIP; exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = interrupt.st_State1_StatemachineDiagram0 -> SKIP; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State5_StatemachineDiagram0 = (enter.st_State5_StatemachineDiagram0 -> State_st_State5_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State5_Do_StatemachineDiagram0 = interrupt.st_State5_StatemachineDiagram0 -> SKIP; exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> State_st_State5_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State6_StatemachineDiagram0 = (enter.st_State6_StatemachineDiagram0 -> State_st_State6_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State6_Do_StatemachineDiagram0 = interrupt.st_State6_StatemachineDiagram0 -> SKIP; exit.st_State6_StatemachineDiagram0 -> exited.st_State6_StatemachineDiagram0 -> State_st_State6_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State7_StatemachineDiagram0 = (enter.st_State7_StatemachineDiagram0 -> State_st_State7_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State7_Do_StatemachineDiagram0 = interrupt.st_State7_StatemachineDiagram0 -> SKIP; exit.st_State7_StatemachineDiagram0 -> exited.st_State7_StatemachineDiagram0 -> State_st_State7_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State8_StatemachineDiagram0 = (enter.st_State8_StatemachineDiagram0 -> State_st_State8_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State8_Do_StatemachineDiagram0 = interrupt.st_State8_StatemachineDiagram0 -> SKIP; exit.st_State8_StatemachineDiagram0 -> exited.st_State8_StatemachineDiagram0 -> State_st_State8_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State9_StatemachineDiagram0 = (enter.st_State9_StatemachineDiagram0 -> State_st_State9_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State9_Do_StatemachineDiagram0 = interrupt.st_State9_StatemachineDiagram0 -> SKIP; exit.st_State9_StatemachineDiagram0 -> exited.st_State9_StatemachineDiagram0 -> State_st_State9_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State10_StatemachineDiagram0 = (enter.st_State10_StatemachineDiagram0 -> State_st_State10_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State10_Do_StatemachineDiagram0 = interrupt.st_State10_StatemachineDiagram0 -> SKIP; exit.st_State10_StatemachineDiagram0 -> exited.st_State10_StatemachineDiagram0 -> State_st_State10_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State11_StatemachineDiagram0 = (enter.st_State11_StatemachineDiagram0 -> State_st_State11_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State11_Do_StatemachineDiagram0 = interrupt.st_State11_StatemachineDiagram0 -> SKIP; exit.st_State11_StatemachineDiagram0 -> exited.st_State11_StatemachineDiagram0 -> State_st_State11_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State12_StatemachineDiagram0 = (enter.st_State12_StatemachineDiagram0 -> State_st_State12_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State12_Do_StatemachineDiagram0 = interrupt.st_State12_StatemachineDiagram0 -> SKIP; exit.st_State12_StatemachineDiagram0 -> exited.st_State12_StatemachineDiagram0 -> State_st_State12_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State13_StatemachineDiagram0 = (enter.st_State13_StatemachineDiagram0 -> State_st_State13_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State13_Do_StatemachineDiagram0 = interrupt.st_State13_StatemachineDiagram0 -> SKIP; exit.st_State13_StatemachineDiagram0 -> exited.st_State13_StatemachineDiagram0 -> State_st_State13_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_1wb_4f082ffe90f2e260c3cdc0cf81fa56f2 = ((internal_StatemachineDiagram0.tr_1wb_4f082ffe90f2e260c3cdc0cf81fa56f2 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_State1_22g_4f082ffe90f2e260c3cdc0cf81fa56f2 = ((internal_StatemachineDiagram0.tr_22g_4f082ffe90f2e260c3cdc0cf81fa56f2 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State5_BY_st_State2_3us_5936f0942dd0f6e88d60ded4f7b8484e = ((Trigger.tr_3us_5936f0942dd0f6e88d60ded4f7b8484e -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State5_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State6_BY_st_State5_1lr_0224a60bdb586fb47f36232c82cae4c3 = ((Trigger.tr_1lr_0224a60bdb586fb47f36232c82cae4c3 -> exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> enter.st_State6_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State7_BY_st_State6_2lp_4e8b243018b5102228084363fe68263f = ((Trigger.tr_2lp_4e8b243018b5102228084363fe68263f -> exit.st_State6_StatemachineDiagram0 -> exited.st_State6_StatemachineDiagram0 -> enter.st_State7_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State8_BY_st_State7_36z_4e8b243018b5102228084363fe68263f = ((Trigger.tr_36z_4e8b243018b5102228084363fe68263f -> exit.st_State7_StatemachineDiagram0 -> exited.st_State7_StatemachineDiagram0 -> enter.st_State8_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State9_BY_st_State8_3s9_4e8b243018b5102228084363fe68263f = ((Trigger.tr_3s9_4e8b243018b5102228084363fe68263f -> exit.st_State8_StatemachineDiagram0 -> exited.st_State8_StatemachineDiagram0 -> enter.st_State9_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State10_BY_st_State9_4dj_4e8b243018b5102228084363fe68263f = ((Trigger.tr_4dj_4e8b243018b5102228084363fe68263f -> exit.st_State9_StatemachineDiagram0 -> exited.st_State9_StatemachineDiagram0 -> enter.st_State10_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State11_BY_st_State10_4yt_4e8b243018b5102228084363fe68263f = ((Trigger.tr_4yt_4e8b243018b5102228084363fe68263f -> exit.st_State10_StatemachineDiagram0 -> exited.st_State10_StatemachineDiagram0 -> enter.st_State11_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State12_BY_st_State11_5k3_4e8b243018b5102228084363fe68263f = ((Trigger.tr_5k3_4e8b243018b5102228084363fe68263f -> exit.st_State11_StatemachineDiagram0 -> exited.st_State11_StatemachineDiagram0 -> enter.st_State12_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State13_BY_st_State12_65d_4e8b243018b5102228084363fe68263f = ((Trigger.tr_65d_4e8b243018b5102228084363fe68263f -> exit.st_State12_StatemachineDiagram0 -> exited.st_State12_StatemachineDiagram0 -> enter.st_State13_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State13_6qn_4e8b243018b5102228084363fe68263f = ((Trigger.tr_6qn_4e8b243018b5102228084363fe68263f -> exit.st_State13_StatemachineDiagram0 -> exited.st_State13_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State2_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, State_st_State0_StatemachineDiagram0, State_st_State5_StatemachineDiagram0, State_st_State6_StatemachineDiagram0, State_st_State7_StatemachineDiagram0, State_st_State8_StatemachineDiagram0, State_st_State9_StatemachineDiagram0, State_st_State10_StatemachineDiagram0, State_st_State11_StatemachineDiagram0, State_st_State12_StatemachineDiagram0, State_st_State13_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State5_BY_st_State2_3us_5936f0942dd0f6e88d60ded4f7b8484e; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State2_BY_st_State1_22g_4f082ffe90f2e260c3cdc0cf81fa56f2; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State1_BY_st_State0_1wb_4f082ffe90f2e260c3cdc0cf81fa56f2; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State6_BY_st_State5_1lr_0224a60bdb586fb47f36232c82cae4c3; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State7_BY_st_State6_2lp_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State8_BY_st_State7_36z_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State9_BY_st_State8_3s9_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State10_BY_st_State9_4dj_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State11_BY_st_State10_4yt_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State12_BY_st_State11_5k3_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State13_BY_st_State12_65d_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State13_6qn_4e8b243018b5102228084363fe68263f; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [[interrupt.st_State2_StatemachineDiagram0 <- Trigger.tr_3us_5936f0942dd0f6e88d60ded4f7b8484e, interrupt.st_State1_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_22g_4f082ffe90f2e260c3cdc0cf81fa56f2, interrupt.st_State0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_1wb_4f082ffe90f2e260c3cdc0cf81fa56f2, interrupt.st_State5_StatemachineDiagram0 <- Trigger.tr_1lr_0224a60bdb586fb47f36232c82cae4c3, interrupt.st_State6_StatemachineDiagram0 <- Trigger.tr_2lp_4e8b243018b5102228084363fe68263f, interrupt.st_State7_StatemachineDiagram0 <- Trigger.tr_36z_4e8b243018b5102228084363fe68263f, interrupt.st_State8_StatemachineDiagram0 <- Trigger.tr_3s9_4e8b243018b5102228084363fe68263f, interrupt.st_State9_StatemachineDiagram0 <- Trigger.tr_4dj_4e8b243018b5102228084363fe68263f, interrupt.st_State10_StatemachineDiagram0 <- Trigger.tr_4yt_4e8b243018b5102228084363fe68263f, interrupt.st_State11_StatemachineDiagram0 <- Trigger.tr_5k3_4e8b243018b5102228084363fe68263f, interrupt.st_State12_StatemachineDiagram0 <- Trigger.tr_65d_4e8b243018b5102228084363fe68263f, interrupt.st_State13_StatemachineDiagram0 <- Trigger.tr_6qn_4e8b243018b5102228084363fe68263f]]  [|{|enter.st_State2_StatemachineDiagram0, exit.st_State2_StatemachineDiagram0, exited.st_State2_StatemachineDiagram0, Trigger.tr_3us_5936f0942dd0f6e88d60ded4f7b8484e, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, internal_StatemachineDiagram0.tr_22g_4f082ffe90f2e260c3cdc0cf81fa56f2, enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, internal_StatemachineDiagram0.tr_1wb_4f082ffe90f2e260c3cdc0cf81fa56f2, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, Trigger.tr_1lr_0224a60bdb586fb47f36232c82cae4c3, enter.st_State6_StatemachineDiagram0, exit.st_State6_StatemachineDiagram0, exited.st_State6_StatemachineDiagram0, Trigger.tr_2lp_4e8b243018b5102228084363fe68263f, enter.st_State7_StatemachineDiagram0, exit.st_State7_StatemachineDiagram0, exited.st_State7_StatemachineDiagram0, Trigger.tr_36z_4e8b243018b5102228084363fe68263f, enter.st_State8_StatemachineDiagram0, exit.st_State8_StatemachineDiagram0, exited.st_State8_StatemachineDiagram0, Trigger.tr_3s9_4e8b243018b5102228084363fe68263f, enter.st_State9_StatemachineDiagram0, exit.st_State9_StatemachineDiagram0, exited.st_State9_StatemachineDiagram0, Trigger.tr_4dj_4e8b243018b5102228084363fe68263f, enter.st_State10_StatemachineDiagram0, exit.st_State10_StatemachineDiagram0, exited.st_State10_StatemachineDiagram0, Trigger.tr_4yt_4e8b243018b5102228084363fe68263f, enter.st_State11_StatemachineDiagram0, exit.st_State11_StatemachineDiagram0, exited.st_State11_StatemachineDiagram0, Trigger.tr_5k3_4e8b243018b5102228084363fe68263f, enter.st_State12_StatemachineDiagram0, exit.st_State12_StatemachineDiagram0, exited.st_State12_StatemachineDiagram0, Trigger.tr_65d_4e8b243018b5102228084363fe68263f, enter.st_State13_StatemachineDiagram0, exit.st_State13_StatemachineDiagram0, exited.st_State13_StatemachineDiagram0, Trigger.tr_6qn_4e8b243018b5102228084363fe68263f, end, final_StatemachineDiagram0|}|] (enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}

	//-- ST4
	
	@Test
	public void TestSt4() throws ParsingException{
		String actual = parser4.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State2_StatemachineDiagram0 | st_State3_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1hj_fe35c56ba095d87365478df4c07eca61 | tr_22p_fe35c56ba095d87365478df4c07eca61 | tr_2yh_fe35c56ba095d87365478df4c07eca61 | tr_3jr_fe35c56ba095d87365478df4c07eca61 | tr_4df_fe35c56ba095d87365478df4c07eca61 | tr_5ci_fe35c56ba095d87365478df4c07eca61 | tr_601_fe35c56ba095d87365478df4c07eca61 | tr_70_dcfc16efd476476895a6e4cb37a6c403\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Ida1, Ida2, problema, tentarNovamente, retornoInterno, vitoria : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = interrupt.st_State1_StatemachineDiagram0 -> SKIP; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = (enter.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = interrupt.st_State2_StatemachineDiagram0 -> SKIP; exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = (enter.st_State3_StatemachineDiagram0 -> State_st_State3_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = interrupt.st_State3_StatemachineDiagram0 -> SKIP; exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_1hj_fe35c56ba095d87365478df4c07eca61 = ((Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_State0_22p_fe35c56ba095d87365478df4c07eca61 = ((Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State2_2yh_fe35c56ba095d87365478df4c07eca61 = ((problema.tr_2yh_fe35c56ba095d87365478df4c07eca61 -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State1_3jr_fe35c56ba095d87365478df4c07eca61 = ((problema.tr_3jr_fe35c56ba095d87365478df4c07eca61 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State3_4df_fe35c56ba095d87365478df4c07eca61 = ((tentarNovamente.tr_4df_fe35c56ba095d87365478df4c07eca61 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State3_5ci_fe35c56ba095d87365478df4c07eca61 = ((retornoInterno.tr_5ci_fe35c56ba095d87365478df4c07eca61 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State0_601_fe35c56ba095d87365478df4c07eca61 = ((retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_70_dcfc16efd476476895a6e4cb37a6c403 = ((vitoria.tr_70_dcfc16efd476476895a6e4cb37a6c403 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State0_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, State_st_State2_StatemachineDiagram0, State_st_State3_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State1_BY_st_State0_1hj_fe35c56ba095d87365478df4c07eca61; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State2_BY_st_State0_22p_fe35c56ba095d87365478df4c07eca61; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State0_BY_st_State0_601_fe35c56ba095d87365478df4c07eca61; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State3_BY_st_State1_3jr_fe35c56ba095d87365478df4c07eca61; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State1_70_dcfc16efd476476895a6e4cb37a6c403; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State3_BY_st_State2_2yh_fe35c56ba095d87365478df4c07eca61; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State0_BY_st_State3_4df_fe35c56ba095d87365478df4c07eca61; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State3_BY_st_State3_5ci_fe35c56ba095d87365478df4c07eca61; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [[interrupt.st_State0_StatemachineDiagram0 <- Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61, interrupt.st_State0_StatemachineDiagram0 <- Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61, interrupt.st_State0_StatemachineDiagram0 <- retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61, interrupt.st_State1_StatemachineDiagram0 <- problema.tr_3jr_fe35c56ba095d87365478df4c07eca61, interrupt.st_State1_StatemachineDiagram0 <- vitoria.tr_70_dcfc16efd476476895a6e4cb37a6c403, interrupt.st_State2_StatemachineDiagram0 <- problema.tr_2yh_fe35c56ba095d87365478df4c07eca61, interrupt.st_State3_StatemachineDiagram0 <- tentarNovamente.tr_4df_fe35c56ba095d87365478df4c07eca61, interrupt.st_State3_StatemachineDiagram0 <- retornoInterno.tr_5ci_fe35c56ba095d87365478df4c07eca61]]  [|{|enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61, Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61, retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, problema.tr_3jr_fe35c56ba095d87365478df4c07eca61, vitoria.tr_70_dcfc16efd476476895a6e4cb37a6c403, enter.st_State2_StatemachineDiagram0, exit.st_State2_StatemachineDiagram0, exited.st_State2_StatemachineDiagram0, problema.tr_2yh_fe35c56ba095d87365478df4c07eca61, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, tentarNovamente.tr_4df_fe35c56ba095d87365478df4c07eca61, retornoInterno.tr_5ci_fe35c56ba095d87365478df4c07eca61, end, final_StatemachineDiagram0|}|] (enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}

	// ST5
	
	@Test
	public void TestSt5() throws ParsingException{
		String actual = parser5.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_smp_bat = st_On_smp_bat | st_Recharge_smp_bat | st_SOS_smp_bat | st_Off_smp_bat\n"
				+ "datatype TR_ID_smp_bat = tr_5ub_83c2583f83e5e7797d82ad5650498d96 | tr_5uu_83c2583f83e5e7797d82ad5650498d96 | tr_5vt_83c2583f83e5e7797d82ad5650498d96 | tr_5wa_83c2583f83e5e7797d82ad5650498d96 | tr_61i_83c2583f83e5e7797d82ad5650498d96 | tr_64x_83c2583f83e5e7797d82ad5650498d96 | tr_65f_83c2583f83e5e7797d82ad5650498d96 | tr_65x_83c2583f83e5e7797d82ad5650498d96 | tr_2e_151c15ac07aa17dadd42b370fe8fd617\n"
				+ "\n"
				+ "channel activateTr: TR_ID_smp_bat\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_smp_bat\n"
				+ "channel final_smp_bat, finalState_smp_bat\n"
				+ "channel internal_smp_bat : TR_ID_smp_bat\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_smp_bat\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "State_st_On_smp_bat = (enter.st_On_smp_bat -> State_st_On_Do_smp_bat) [] (end -> SKIP)\n"
				+ "State_st_On_Do_smp_bat = interrupt.st_On_smp_bat -> SKIP; exit.st_On_smp_bat -> exited.st_On_smp_bat -> State_st_On_smp_bat\n"
				+ "\n"
				+ "State_st_Recharge_smp_bat = (enter.st_Recharge_smp_bat -> State_st_Recharge_Do_smp_bat) [] (end -> SKIP)\n"
				+ "State_st_Recharge_Do_smp_bat = interrupt.st_Recharge_smp_bat -> SKIP; exit.st_Recharge_smp_bat -> exited.st_Recharge_smp_bat -> State_st_Recharge_smp_bat\n"
				+ "\n"
				+ "State_st_SOS_smp_bat = (enter.st_SOS_smp_bat -> State_st_SOS_Do_smp_bat) [] (end -> SKIP)\n"
				+ "State_st_SOS_Do_smp_bat = interrupt.st_SOS_smp_bat -> SKIP; exit.st_SOS_smp_bat -> exited.st_SOS_smp_bat -> State_st_SOS_smp_bat\n"
				+ "\n"
				+ "State_st_Off_smp_bat = (enter.st_Off_smp_bat -> State_st_Off_Do_smp_bat) [] (end -> SKIP)\n"
				+ "State_st_Off_Do_smp_bat = interrupt.st_Off_smp_bat -> SKIP; exit.st_Off_smp_bat -> exited.st_Off_smp_bat -> State_st_Off_smp_bat\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Recharge_5ub_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_5ub_83c2583f83e5e7797d82ad5650498d96 -> exit.st_Recharge_smp_bat -> exited.st_Recharge_smp_bat -> enter.st_On_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Off_5uu_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_5uu_83c2583f83e5e7797d82ad5650498d96 -> exit.st_Off_smp_bat -> exited.st_Off_smp_bat -> enter.st_On_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_SOS_5vt_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_5vt_83c2583f83e5e7797d82ad5650498d96 -> exit.st_SOS_smp_bat -> exited.st_SOS_smp_bat -> enter.st_Off_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_Off_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_Recharge_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_SOS_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_Off_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Off_65x_83c2583f83e5e7797d82ad5650498d96 = ((internal_smp_bat.tr_65x_83c2583f83e5e7797d82ad5650498d96 -> exit.st_Off_smp_bat -> exited.st_Off_smp_bat -> enter.st_Recharge_smp_bat -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Recharge_2e_151c15ac07aa17dadd42b370fe8fd617 = ((internal_smp_bat.tr_2e_151c15ac07aa17dadd42b370fe8fd617 -> exit.st_Recharge_smp_bat -> exited.st_Recharge_smp_bat -> enter.st_Recharge_smp_bat -> SKIP))\n"
				+ "\n"
				+ "States_smp_bat = ([|{|end|}|] x: {State_st_On_smp_bat, State_st_Recharge_smp_bat, State_st_SOS_smp_bat, State_st_Off_smp_bat, FinalProcess_smp_bat} @ x)\n"
				+ "Transitions_smp_bat = ((Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat) [] (Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat) [] (Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat) [] (Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat) [] (Tr_Turn_st_On_BY_st_Recharge_5ub_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat) [] (Tr_Turn_st_Recharge_BY_st_Recharge_2e_151c15ac07aa17dadd42b370fe8fd617; Transitions_smp_bat) [] (Tr_Turn_st_Off_BY_st_SOS_5vt_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat) [] (Tr_Turn_st_On_BY_st_Off_5uu_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat) [] (Tr_Turn_st_Recharge_BY_st_Off_65x_83c2583f83e5e7797d82ad5650498d96; Transitions_smp_bat)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_smp_bat= (States_smp_bat [[interrupt.st_On_smp_bat <- internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96, interrupt.st_On_smp_bat <- internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96, interrupt.st_On_smp_bat <- internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96, interrupt.st_On_smp_bat <- internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96, interrupt.st_Recharge_smp_bat <- internal_smp_bat.tr_5ub_83c2583f83e5e7797d82ad5650498d96, interrupt.st_Recharge_smp_bat <- internal_smp_bat.tr_2e_151c15ac07aa17dadd42b370fe8fd617, interrupt.st_SOS_smp_bat <- internal_smp_bat.tr_5vt_83c2583f83e5e7797d82ad5650498d96, interrupt.st_Off_smp_bat <- internal_smp_bat.tr_5uu_83c2583f83e5e7797d82ad5650498d96, interrupt.st_Off_smp_bat <- internal_smp_bat.tr_65x_83c2583f83e5e7797d82ad5650498d96]]  [|{|enter.st_On_smp_bat, exit.st_On_smp_bat, exited.st_On_smp_bat, internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96, enter.st_Recharge_smp_bat, exit.st_Recharge_smp_bat, exited.st_Recharge_smp_bat, internal_smp_bat.tr_5ub_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_2e_151c15ac07aa17dadd42b370fe8fd617, enter.st_SOS_smp_bat, exit.st_SOS_smp_bat, exited.st_SOS_smp_bat, internal_smp_bat.tr_5vt_83c2583f83e5e7797d82ad5650498d96, enter.st_Off_smp_bat, exit.st_Off_smp_bat, exited.st_Off_smp_bat, internal_smp_bat.tr_5uu_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_65x_83c2583f83e5e7797d82ad5650498d96, end, final_smp_bat|}|] (enter.st_On_smp_bat -> Transitions_smp_bat)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_smp_bat = final_smp_bat -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_smp_bat); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}
	//ST6
	
	@Test
	public void TestSt6() throws ParsingException{
		String actual = parser6.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_light_bt = st_Recharge_light_bt | st_On_light_bt | st_Off_light_bt | st_SOS_light_bt\n"
				+ "datatype TR_ID_light_bt = tr_1ay_0c1aea76f28ac0dbed586ac2af18089c | tr_1bh_0c1aea76f28ac0dbed586ac2af18089c | tr_1ef_0c1aea76f28ac0dbed586ac2af18089c | tr_1ex_0c1aea76f28ac0dbed586ac2af18089c | tr_1fe_0c1aea76f28ac0dbed586ac2af18089c | tr_1fw_0c1aea76f28ac0dbed586ac2af18089c | tr_1ge_0c1aea76f28ac0dbed586ac2af18089c | tr_1l6_0c1aea76f28ac0dbed586ac2af18089c | tr_1oj_0c1aea76f28ac0dbed586ac2af18089c | tr_1qg_0c1aea76f28ac0dbed586ac2af18089c\n"
				+ "\n"
				+ "channel activateTr: TR_ID_light_bt\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_light_bt\n"
				+ "channel final_light_bt, finalState_light_bt\n"
				+ "channel TurnOn, BatteryDepleted, TurnRecharge, turnRecharge, TurnSOS_, TurnOff, internal_light_bt : TR_ID_light_bt\n"
				+ "channel get_bateria_light_bt, set_bateria_light_bt: {0..100}\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_light_bt\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_light_bt(bateria) = (end -> SKIP) []\n"
				+ "get_bateria_light_bt!bateria -> MEMORY_light_bt(bateria) [] set_bateria_light_bt?x -> MEMORY_light_bt(x) [] (bateria > 0) & TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria == 0) & BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria < 95) & internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria > 0) & TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria<=20) & turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria>=25) & TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria > 0) & TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria > 10) & internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ "\n"
				+ "State_st_Recharge_light_bt = (enter.st_Recharge_light_bt -> State_st_Recharge_Entry_light_bt) [] (end -> SKIP)\n"
				+ "State_st_Recharge_Entry_light_bt = EntryProc(st_Recharge_light_bt)\n"
				+ "State_st_Recharge_Do_light_bt = (DoProc(st_Recharge_light_bt)); exit.st_Recharge_light_bt -> exited.st_Recharge_light_bt -> State_st_Recharge_light_bt\n"
				+ "\n"
				+ "State_st_On_light_bt = (enter.st_On_light_bt -> State_st_On_Entry_light_bt) [] (end -> SKIP)\n"
				+ "State_st_On_Entry_light_bt = EntryProc(st_On_light_bt)\n"
				+ "State_st_On_Do_light_bt = (DoProc(st_On_light_bt)); exit.st_On_light_bt -> exited.st_On_light_bt -> State_st_On_light_bt\n"
				+ "\n"
				+ "State_st_Off_light_bt = (enter.st_Off_light_bt -> State_st_Off_Do_light_bt) [] (end -> SKIP)\n"
				+ "State_st_Off_Do_light_bt = interrupt.st_Off_light_bt -> SKIP; exit.st_Off_light_bt -> exited.st_Off_light_bt -> State_st_Off_light_bt\n"
				+ "\n"
				+ "State_st_SOS_light_bt = (enter.st_SOS_light_bt -> State_st_SOS_Entry_light_bt) [] (end -> SKIP)\n"
				+ "State_st_SOS_Entry_light_bt = EntryProc(st_SOS_light_bt)\n"
				+ "State_st_SOS_Do_light_bt = interrupt.st_SOS_light_bt -> SKIP; exit.st_SOS_light_bt -> exited.st_SOS_light_bt -> State_st_SOS_light_bt\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Recharge_1ay_0c1aea76f28ac0dbed586ac2af18089c = ((TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Recharge_light_bt -> exited.st_Recharge_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-1)) -> enter.st_On_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c = ((BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_Off_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c = ((TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_Recharge_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Recharge_1ex_0c1aea76f28ac0dbed586ac2af18089c = ((internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Recharge_light_bt -> exited.st_Recharge_light_bt -> enter.st_Recharge_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Off_1fe_0c1aea76f28ac0dbed586ac2af18089c = ((TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Off_light_bt -> exited.st_Off_light_bt -> enter.st_On_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Off_1fw_0c1aea76f28ac0dbed586ac2af18089c = ((turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Off_light_bt -> exited.st_Off_light_bt -> enter.st_Recharge_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c = ((TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_SOS_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c = ((TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_Off_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_SOS_1oj_0c1aea76f28ac0dbed586ac2af18089c = ((TurnOff.tr_1oj_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_SOS_light_bt -> exited.st_SOS_light_bt -> enter.st_Off_light_bt -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c = ((internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_On_light_bt -> SKIP))\n"
				+ "\n"
				+ "EntryProc(st_Recharge_light_bt) = entry.st_Recharge_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(min(100,bateria+5)) -> State_st_Recharge_Do_light_bt\n"
				+ "\n"
				+ "EntryProc(st_On_light_bt) = entry.st_On_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-10)) -> State_st_On_Do_light_bt\n"
				+ "\n"
				+ "EntryProc(st_SOS_light_bt) = entry.st_SOS_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-25)) -> State_st_SOS_Do_light_bt\n"
				+ "\n"
				+ "DoProc(st_Recharge_light_bt) = do.st_Recharge_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(min(100,bateria+5)) -> LOOP /\\ interrupt.st_Recharge_light_bt -> SKIP\n"
				+ "\n"
				+ "DoProc(st_On_light_bt) = do.st_On_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-10)) -> LOOP /\\ interrupt.st_On_light_bt -> SKIP\n"
				+ "\n"
				+ "States_light_bt = ([|{|end|}|] x: {State_st_Recharge_light_bt, State_st_On_light_bt, State_st_Off_light_bt, State_st_SOS_light_bt, FinalProcess_light_bt} @ x)\n"
				+ "Transitions_light_bt = ((Tr_Turn_st_On_BY_st_Recharge_1ay_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_Recharge_BY_st_Recharge_1ex_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_On_BY_st_Off_1fe_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_Recharge_BY_st_Off_1fw_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt) [] (Tr_Turn_st_Off_BY_st_SOS_1oj_0c1aea76f28ac0dbed586ac2af18089c; Transitions_light_bt)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_light_bt =((States_light_bt [[interrupt.st_Recharge_light_bt <- TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_Recharge_light_bt <- internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_On_light_bt <- BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_On_light_bt <- TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_On_light_bt <- TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_On_light_bt <- TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_On_light_bt <- internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_Off_light_bt <- TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_Off_light_bt <- turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c, interrupt.st_SOS_light_bt <- TurnOff.tr_1oj_0c1aea76f28ac0dbed586ac2af18089c]]  [|{|enter.st_Recharge_light_bt, exit.st_Recharge_light_bt, exited.st_Recharge_light_bt, TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c, enter.st_On_light_bt, exit.st_On_light_bt, exited.st_On_light_bt, BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c, TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c, enter.st_Off_light_bt, exit.st_Off_light_bt, exited.st_Off_light_bt, TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c, turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c, enter.st_SOS_light_bt, exit.st_SOS_light_bt, exited.st_SOS_light_bt, TurnOff.tr_1oj_0c1aea76f28ac0dbed586ac2af18089c, end, final_light_bt|}|](enter.st_On_light_bt -> Transitions_light_bt))[|{|get_bateria_light_bt, set_bateria_light_bt, TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c, BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c, TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c, turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c, TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c, end|}|] MEMORY_light_bt(20)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_light_bt = final_light_bt -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_light_bt); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}

	//st7
	
	@Test
	public void TestSt7() throws ParsingException{
		String actual = parser7.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 | tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 | tr_3do_66aebfeb7e090212b92b2faa15d2aab0 | tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 | tr_58_a198ebf31eae0bfb62967205c60511a7\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Trigger, TR2, TR3, TR1, TR5 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (end -> SKIP) [] \n"
				+ "(false) & Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==3) & TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (true) & TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==2) & TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (not(false) and not(1==3)and not(1==2)) & TR5.tr_58_a198ebf31eae0bfb62967205c60511a7 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = interrupt.st_State1_StatemachineDiagram0 -> SKIP; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0 = ((Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0 = ((TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_3do_66aebfeb7e090212b92b2faa15d2aab0 = ((TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 = ((TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7 = ((TR5.tr_58_a198ebf31eae0bfb62967205c60511a7 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State0_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State1_3do_66aebfeb7e090212b92b2faa15d2aab0; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_State0_StatemachineDiagram0 <- Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0, interrupt.st_State0_StatemachineDiagram0 <- TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0, interrupt.st_State0_StatemachineDiagram0 <- TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2, interrupt.st_State0_StatemachineDiagram0 <- TR5.tr_58_a198ebf31eae0bfb62967205c60511a7, interrupt.st_State1_StatemachineDiagram0 <- TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0]]  [|{|enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0, TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0, TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2, TR5.tr_58_a198ebf31eae0bfb62967205c60511a7, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0, end, final_StatemachineDiagram0|}|](enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0, TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0, TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0, TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2, TR5.tr_58_a198ebf31eae0bfb62967205c60511a7, end|}|] MEMORY_StatemachineDiagram0) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}

	//ChoiceJunction1
	
	@Test
	public void TestChoiceJunction1() throws ParsingException{
		String actual = parser8.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State2_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0 | st_Choice0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_13x_ef1c450fd9e597ffdd79b472efdec02e | tr_1n3_ef1c450fd9e597ffdd79b472efdec02e | tr_26d_ef1c450fd9e597ffdd79b472efdec02e | tr_33t_ef1c450fd9e597ffdd79b472efdec02e | tr_3tk_ef1c450fd9e597ffdd79b472efdec02e | tr_y4_9ade7c9c747f809615cb5e53e140113e | tr_dy_f41343e1325a6efb4e19d10984ddbe81\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel TR1, TR3, TR2, TR5, TR4, TR6, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (end -> SKIP) [] \n"
				+ "(1==1) & TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e -> MEMORY_StatemachineDiagram0\n"
				+ " [] (3==3) & TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e -> MEMORY_StatemachineDiagram0\n"
				+ " [] (true) & internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = interrupt.st_State1_StatemachineDiagram0 -> SKIP; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = (enter.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = interrupt.st_State2_StatemachineDiagram0 -> SKIP; exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = (enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint0_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint0_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_Choice0_StatemachineDiagram0 = (enter.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_Choice0_Do_StatemachineDiagram0 = (interrupt.st_Choice0_StatemachineDiagram0 -> SKIP); exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State0_13x_ef1c450fd9e597ffdd79b472efdec02e = ((TR1.tr_13x_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State1_1n3_ef1c450fd9e597ffdd79b472efdec02e = ((TR3.tr_1n3_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_26d_ef1c450fd9e597ffdd79b472efdec02e = ((TR2.tr_26d_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_JunctionPoint0_33t_ef1c450fd9e597ffdd79b472efdec02e = ((TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State2_3tk_ef1c450fd9e597ffdd79b472efdec02e = ((TR4.tr_3tk_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_Choice0_y4_9ade7c9c747f809615cb5e53e140113e = ((TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e -> exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_dy_f41343e1325a6efb4e19d10984ddbe81 = ((internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State0_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, State_st_State2_StatemachineDiagram0, State_st_JunctionPoint0_StatemachineDiagram0, State_st_Choice0_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_JunctionPoint0_BY_st_State0_13x_ef1c450fd9e597ffdd79b472efdec02e; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State1_BY_st_State0_26d_ef1c450fd9e597ffdd79b472efdec02e; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_JunctionPoint0_BY_st_State1_1n3_ef1c450fd9e597ffdd79b472efdec02e; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State2_3tk_ef1c450fd9e597ffdd79b472efdec02e; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State2_BY_st_JunctionPoint0_33t_ef1c450fd9e597ffdd79b472efdec02e; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_dy_f41343e1325a6efb4e19d10984ddbe81; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State0_BY_st_Choice0_y4_9ade7c9c747f809615cb5e53e140113e; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_State0_StatemachineDiagram0 <- TR1.tr_13x_ef1c450fd9e597ffdd79b472efdec02e, interrupt.st_State0_StatemachineDiagram0 <- TR2.tr_26d_ef1c450fd9e597ffdd79b472efdec02e, interrupt.st_State1_StatemachineDiagram0 <- TR3.tr_1n3_ef1c450fd9e597ffdd79b472efdec02e, interrupt.st_State2_StatemachineDiagram0 <- TR4.tr_3tk_ef1c450fd9e597ffdd79b472efdec02e, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81, interrupt.st_Choice0_StatemachineDiagram0 <- TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e]]  [|{|enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, TR1.tr_13x_ef1c450fd9e597ffdd79b472efdec02e, TR2.tr_26d_ef1c450fd9e597ffdd79b472efdec02e, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, TR3.tr_1n3_ef1c450fd9e597ffdd79b472efdec02e, enter.st_State2_StatemachineDiagram0, exit.st_State2_StatemachineDiagram0, exited.st_State2_StatemachineDiagram0, TR4.tr_3tk_ef1c450fd9e597ffdd79b472efdec02e, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e, internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81, enter.st_Choice0_StatemachineDiagram0, exit.st_Choice0_StatemachineDiagram0, exited.st_Choice0_StatemachineDiagram0, TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e, end, final_StatemachineDiagram0|}|](enter.st_Choice0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e, TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e, internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81, end|}|] MEMORY_StatemachineDiagram0) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);

	}
	//ChoiceJunction2
	
	@Test
	public void TestChoiceJunction2() throws ParsingException{
		String actual = parser9.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1__StatemachineDiagram0 | st_State3_StatemachineDiagram0 | st_State4_StatemachineDiagram0 | st_State5_StatemachineDiagram0 | st_Choice0_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1ua_179c9d9956518cdbb98c83cd532dd4bf | tr_5ta_179c9d9956518cdbb98c83cd532dd4bf | tr_7qf_179c9d9956518cdbb98c83cd532dd4bf | tr_8ek_179c9d9956518cdbb98c83cd532dd4bf | tr_9mr_179c9d9956518cdbb98c83cd532dd4bf | tr_ak6_179c9d9956518cdbb98c83cd532dd4bf | tr_bm7_179c9d9956518cdbb98c83cd532dd4bf | tr_cau_179c9d9956518cdbb98c83cd532dd4bf | tr_d49_179c9d9956518cdbb98c83cd532dd4bf | tr_ic_148a61f56aa3873257e76f90ba00d072 | tr_52_137e349f6bbd43344cbe2ec1b17a62b1\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel TR1, TR2, JTR1, JTR3, JTR2, TRFINAL, TRInit, Loop, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel get_count_StatemachineDiagram0, set_count_StatemachineDiagram0: {0..10}\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0(count) = (end -> SKIP) []\n"
				+ "get_count_StatemachineDiagram0!count -> MEMORY_StatemachineDiagram0(count) [] set_count_StatemachineDiagram0?x -> MEMORY_StatemachineDiagram0(x) [] (count < 5) & internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> MEMORY_StatemachineDiagram0(count)\n"
				+ " [] (count >5) & internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> MEMORY_StatemachineDiagram0(count)\n"
				+ "[] (not(count < 5) and not(count >5)) & internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> MEMORY_StatemachineDiagram0(count)\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Entry_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = (DoProc(st_State0_StatemachineDiagram0)); exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1__StatemachineDiagram0 = (enter.st_State1__StatemachineDiagram0 -> State_st_State1__Entry_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1__Entry_StatemachineDiagram0 = EntryProc(st_State1__StatemachineDiagram0)\n"
				+ "State_st_State1__Do_StatemachineDiagram0 = (DoProc(st_State1__StatemachineDiagram0)); exit.st_State1__StatemachineDiagram0 -> exited.st_State1__StatemachineDiagram0 -> State_st_State1__StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = (enter.st_State3_StatemachineDiagram0 -> State_st_State3_Entry_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State3_Entry_StatemachineDiagram0 = EntryProc(st_State3_StatemachineDiagram0)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = interrupt.st_State3_StatemachineDiagram0 -> SKIP; exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State4_StatemachineDiagram0 = (enter.st_State4_StatemachineDiagram0 -> State_st_State4_Entry_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State4_Entry_StatemachineDiagram0 = EntryProc(st_State4_StatemachineDiagram0)\n"
				+ "State_st_State4_Do_StatemachineDiagram0 = interrupt.st_State4_StatemachineDiagram0 -> SKIP; exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> State_st_State4_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State5_StatemachineDiagram0 = (enter.st_State5_StatemachineDiagram0 -> State_st_State5_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State5_Do_StatemachineDiagram0 = interrupt.st_State5_StatemachineDiagram0 -> SKIP; exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> State_st_State5_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_Choice0_StatemachineDiagram0 = (enter.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_Choice0_Do_StatemachineDiagram0 = (interrupt.st_Choice0_StatemachineDiagram0 -> SKIP); exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = (enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint0_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint0_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_State1__BY_st_State0_1ua_179c9d9956518cdbb98c83cd532dd4bf = ((TR1.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1__StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_Choice0_BY_st_State1__5ta_179c9d9956518cdbb98c83cd532dd4bf = ((TR2.tr_5ta_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State1__StatemachineDiagram0 -> exited.st_State1__StatemachineDiagram0 -> enter.st_Choice0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_Choice0_7qf_179c9d9956518cdbb98c83cd532dd4bf = ((internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State4_BY_st_Choice0_8ek_179c9d9956518cdbb98c83cd532dd4bf = ((internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> enter.st_State4_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State5_BY_st_Choice0_9mr_179c9d9956518cdbb98c83cd532dd4bf = ((internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> enter.st_State5_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State3_ak6_179c9d9956518cdbb98c83cd532dd4bf = ((JTR1.tr_ak6_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State5_bm7_179c9d9956518cdbb98c83cd532dd4bf = ((JTR3.tr_bm7_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State4_cau_179c9d9956518cdbb98c83cd532dd4bf = ((JTR2.tr_cau_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_d49_179c9d9956518cdbb98c83cd532dd4bf = ((TRFINAL.tr_d49_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_JunctionPoint0_ic_148a61f56aa3873257e76f90ba00d072 = ((TRInit.tr_ic_148a61f56aa3873257e76f90ba00d072 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> set_count_StatemachineDiagram0!(0) -> enter.st_State0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State0_52_137e349f6bbd43344cbe2ec1b17a62b1 = ((Loop.tr_52_137e349f6bbd43344cbe2ec1b17a62b1 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(min(count+1,10)) -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "EntryProc(st_State1__StatemachineDiagram0) = entry.st_State1__StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(max(count-1,0)) -> State_st_State1__Do_StatemachineDiagram0\n"
				+ "\n"
				+ "EntryProc(st_State3_StatemachineDiagram0) = entry.st_State3_StatemachineDiagram0 -> set_count_StatemachineDiagram0!(0) -> State_st_State3_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "EntryProc(st_State4_StatemachineDiagram0) = entry.st_State4_StatemachineDiagram0 -> set_count_StatemachineDiagram0!(10) -> State_st_State4_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(min(10,count+1)) -> LOOP /\\ interrupt.st_State0_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "DoProc(st_State1__StatemachineDiagram0) = do.st_State1__StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(max(count-1,0)) -> LOOP /\\ interrupt.st_State1__StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State0_StatemachineDiagram0, State_st_State1__StatemachineDiagram0, State_st_State3_StatemachineDiagram0, State_st_State4_StatemachineDiagram0, State_st_State5_StatemachineDiagram0, State_st_Choice0_StatemachineDiagram0, State_st_JunctionPoint0_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State1__BY_st_State0_1ua_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State0_BY_st_State0_52_137e349f6bbd43344cbe2ec1b17a62b1; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_Choice0_BY_st_State1__5ta_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_JunctionPoint0_BY_st_State3_ak6_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_JunctionPoint0_BY_st_State4_cau_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_JunctionPoint0_BY_st_State5_bm7_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State3_BY_st_Choice0_7qf_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State4_BY_st_Choice0_8ek_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State5_BY_st_Choice0_9mr_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_d49_179c9d9956518cdbb98c83cd532dd4bf; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State0_BY_st_JunctionPoint0_ic_148a61f56aa3873257e76f90ba00d072; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_State0_StatemachineDiagram0 <- TR1.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_State0_StatemachineDiagram0 <- Loop.tr_52_137e349f6bbd43344cbe2ec1b17a62b1, interrupt.st_State1__StatemachineDiagram0 <- TR2.tr_5ta_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_State3_StatemachineDiagram0 <- JTR1.tr_ak6_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_State4_StatemachineDiagram0 <- JTR2.tr_cau_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_State5_StatemachineDiagram0 <- JTR3.tr_bm7_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_Choice0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_Choice0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_Choice0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- TRFINAL.tr_d49_179c9d9956518cdbb98c83cd532dd4bf, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- TRInit.tr_ic_148a61f56aa3873257e76f90ba00d072]]  [|{|enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, TR1.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf, Loop.tr_52_137e349f6bbd43344cbe2ec1b17a62b1, enter.st_State1__StatemachineDiagram0, exit.st_State1__StatemachineDiagram0, exited.st_State1__StatemachineDiagram0, TR2.tr_5ta_179c9d9956518cdbb98c83cd532dd4bf, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, JTR1.tr_ak6_179c9d9956518cdbb98c83cd532dd4bf, enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, JTR2.tr_cau_179c9d9956518cdbb98c83cd532dd4bf, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, JTR3.tr_bm7_179c9d9956518cdbb98c83cd532dd4bf, enter.st_Choice0_StatemachineDiagram0, exit.st_Choice0_StatemachineDiagram0, exited.st_Choice0_StatemachineDiagram0, internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, TRFINAL.tr_d49_179c9d9956518cdbb98c83cd532dd4bf, TRInit.tr_ic_148a61f56aa3873257e76f90ba00d072, end, final_StatemachineDiagram0|}|](enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|get_count_StatemachineDiagram0, set_count_StatemachineDiagram0, internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf, end|}|] MEMORY_StatemachineDiagram0(0)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}

	//ChoiceJunction3
	
	@Test
	public void TestChoiceJunction3() throws ParsingException{
		String actual = parser10.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_Choice0_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 | tr_23c_30e19f42c4f79ba4e7dd21cd84422a15\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel tr, t : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (end -> SKIP) [] \n"
				+ "(1==1) & tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (true) & t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_Choice0_StatemachineDiagram0 = (enter.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_Choice0_Do_StatemachineDiagram0 = (interrupt.st_Choice0_StatemachineDiagram0 -> SKIP); exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = (enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint0_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint0_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_Choice0_BY_st_JunctionPoint0_1m6_30e19f42c4f79ba4e7dd21cd84422a15 = ((tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_Choice0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_Choice0_23c_30e19f42c4f79ba4e7dd21cd84422a15 = ((t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15 -> exit.st_Choice0_StatemachineDiagram0 -> exited.st_Choice0_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_Choice0_StatemachineDiagram0, State_st_JunctionPoint0_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_FinalState0_BY_st_Choice0_23c_30e19f42c4f79ba4e7dd21cd84422a15; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_Choice0_BY_st_JunctionPoint0_1m6_30e19f42c4f79ba4e7dd21cd84422a15; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_Choice0_StatemachineDiagram0 <- t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15]]  [|{|enter.st_Choice0_StatemachineDiagram0, exit.st_Choice0_StatemachineDiagram0, exited.st_Choice0_StatemachineDiagram0, t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15, end, final_StatemachineDiagram0|}|](enter.st_JunctionPoint0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15, t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15, end|}|] MEMORY_StatemachineDiagram0) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);

	}
	
	//CompoundTest
	
	@Test
	public void TestCompoundTest() throws ParsingException{
		String actual = parser11.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_st1_StatemachineDiagram0 | st_cp2_StatemachineDiagram0 | st_cp1_StatemachineDiagram0 | st_st2_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_2mk_11bc465cff37dbc3658e8c26dcb71fd0 | tr_2ny_11bc465cff37dbc3658e8c26dcb71fd0 | tr_2t4_11bc465cff37dbc3658e8c26dcb71fd0 | tr_6pr_5f9d7e7a02e95d1d2948d0578bd0a0be\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel t2, t5, t4, t1 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop, final_Compound_st_st1_StatemachineDiagram0\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "State_st_st1_init_StatemachineDiagram0 = State_st_st1_StatemachineDiagram0[|{|interrupt.st_st1_StatemachineDiagram0, enter.st_st1_StatemachineDiagram0, exit.st_st1_StatemachineDiagram0, exited.st_st1_StatemachineDiagram0, enter.st_cp2_StatemachineDiagram0, exit.st_cp2_StatemachineDiagram0, exited.st_cp2_StatemachineDiagram0, enter.st_cp1_StatemachineDiagram0, exit.st_cp1_StatemachineDiagram0, exited.st_cp1_StatemachineDiagram0, end|}|]Control_st_st1_StatemachineDiagram0(<>)\n"
				+ "State_st_st1_StatemachineDiagram0 = (enter.st_st1_StatemachineDiagram0 -> State_st_st1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_st1_Do_StatemachineDiagram0 = ((interrupt.st_st1_StatemachineDiagram0 -> SKIP) [|{|interrupt.st_st1_StatemachineDiagram0|}|]StartSync_Compound_st_st1_StatemachineDiagram0) \\ {end}; exit.st_st1_StatemachineDiagram0 -> exited.st_st1_StatemachineDiagram0 -> State_st_st1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_cp2_StatemachineDiagram0 = (enter.st_cp2_StatemachineDiagram0 -> State_st_cp2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_cp2_Do_StatemachineDiagram0 = interrupt.st_cp2_StatemachineDiagram0 -> SKIP; exit.st_cp2_StatemachineDiagram0 -> exited.st_cp2_StatemachineDiagram0 -> State_st_cp2_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_cp1_StatemachineDiagram0 = (enter.st_cp1_StatemachineDiagram0 -> State_st_cp1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_cp1_Do_StatemachineDiagram0 = interrupt.st_cp1_StatemachineDiagram0 -> SKIP; exit.st_cp1_StatemachineDiagram0 -> exited.st_cp1_StatemachineDiagram0 -> State_st_cp1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_st2_StatemachineDiagram0 = (enter.st_st2_StatemachineDiagram0 -> State_st_st2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_st2_Do_StatemachineDiagram0 = interrupt.st_st2_StatemachineDiagram0 -> SKIP; exit.st_st2_StatemachineDiagram0 -> exited.st_st2_StatemachineDiagram0 -> State_st_st2_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_cp2_2mk_11bc465cff37dbc3658e8c26dcb71fd0 = ((t2.tr_2mk_11bc465cff37dbc3658e8c26dcb71fd0 -> exit.st_cp2_StatemachineDiagram0 -> exited.st_cp2_StatemachineDiagram0 -> final_Compound_st_st1_StatemachineDiagram0 -> interrupt.st_st1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_st2_2ny_11bc465cff37dbc3658e8c26dcb71fd0 = ((t5.tr_2ny_11bc465cff37dbc3658e8c26dcb71fd0 -> exit.st_st2_StatemachineDiagram0 -> exited.st_st2_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_st2_BY_st_st1_2t4_11bc465cff37dbc3658e8c26dcb71fd0 = ((t4.tr_2t4_11bc465cff37dbc3658e8c26dcb71fd0 -> exit.st_st1_StatemachineDiagram0 -> exited.st_st1_StatemachineDiagram0 -> enter.st_st2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_cp2_BY_st_cp1_6pr_5f9d7e7a02e95d1d2948d0578bd0a0be = ((t1.tr_6pr_5f9d7e7a02e95d1d2948d0578bd0a0be -> exit.st_cp1_StatemachineDiagram0 -> exited.st_cp1_StatemachineDiagram0 -> enter.st_cp2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "States_Compound_st1_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_cp2_StatemachineDiagram0, State_st_cp1_StatemachineDiagram0, FinalProcess_Compound_st_st1_StatemachineDiagram0} @ x)\n"
				+ "Transitions_Compound_st1_StatemachineDiagram0 = ((Tr_Turn_st_FinalState1_BY_st_cp2_2mk_11bc465cff37dbc3658e8c26dcb71fd0; Transitions_Compound_st1_StatemachineDiagram0) [] (Tr_Turn_st_cp2_BY_st_cp1_6pr_5f9d7e7a02e95d1d2948d0578bd0a0be; Transitions_Compound_st1_StatemachineDiagram0)) [] (interrupt.st_st1_StatemachineDiagram0 -> exit?x:{st_cp2_StatemachineDiagram0, st_cp1_StatemachineDiagram0} -> exited.x -> end -> SKIP) [] (end -> SKIP)\n"
				+ "FinalProcess_Compound_st_st1_StatemachineDiagram0 = (final_Compound_st_st1_StatemachineDiagram0 -> interrupt.st_st1_StatemachineDiagram0 -> end -> SKIP) [] (end -> SKIP)\n"
				+ "StartSync_Compound_st_st1_StatemachineDiagram0= (States_Compound_st1_StatemachineDiagram0 [[interrupt.st_cp2_StatemachineDiagram0 <- t2.tr_2mk_11bc465cff37dbc3658e8c26dcb71fd0, interrupt.st_cp2_StatemachineDiagram0 <- interrupt.st_st1_StatemachineDiagram0, interrupt.st_cp1_StatemachineDiagram0 <- t1.tr_6pr_5f9d7e7a02e95d1d2948d0578bd0a0be, interrupt.st_cp1_StatemachineDiagram0 <- interrupt.st_st1_StatemachineDiagram0]]  [|{|enter.st_cp2_StatemachineDiagram0, exit.st_cp2_StatemachineDiagram0, exited.st_cp2_StatemachineDiagram0, t2.tr_2mk_11bc465cff37dbc3658e8c26dcb71fd0, enter.st_cp1_StatemachineDiagram0, exit.st_cp1_StatemachineDiagram0, exited.st_cp1_StatemachineDiagram0, t1.tr_6pr_5f9d7e7a02e95d1d2948d0578bd0a0be, final_Compound_st_st1_StatemachineDiagram0, interrupt.st_st1_StatemachineDiagram0, end|}|] (enter.st_cp1_StatemachineDiagram0 -> Transitions_Compound_st1_StatemachineDiagram0))\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_st1_init_StatemachineDiagram0, State_st_st2_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_st2_BY_st_st1_2t4_11bc465cff37dbc3658e8c26dcb71fd0; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_st2_2ny_11bc465cff37dbc3658e8c26dcb71fd0; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [[interrupt.st_st1_StatemachineDiagram0 <- t4.tr_2t4_11bc465cff37dbc3658e8c26dcb71fd0, interrupt.st_st2_StatemachineDiagram0 <- t5.tr_2ny_11bc465cff37dbc3658e8c26dcb71fd0]]  [|{|enter.st_st1_StatemachineDiagram0, exit.st_st1_StatemachineDiagram0, exited.st_st1_StatemachineDiagram0, t4.tr_2t4_11bc465cff37dbc3658e8c26dcb71fd0, enter.st_st2_StatemachineDiagram0, exit.st_st2_StatemachineDiagram0, exited.st_st2_StatemachineDiagram0, t5.tr_2ny_11bc465cff37dbc3658e8c26dcb71fd0, end, final_StatemachineDiagram0|}|] (enter.st_st1_StatemachineDiagram0 -> Transitions_StatemachineDiagram0)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "Control_st_st1_StatemachineDiagram0(s) = #s<5 & enter.st_st1_StatemachineDiagram0 -> Control_st_st1_StatemachineDiagram0(<exit.st_st1_StatemachineDiagram0,exited.st_st1_StatemachineDiagram0>^s)\n"
				+ "			 [] \n"
				+ "			 #s<5 & enter?state:{st_cp2_StatemachineDiagram0, st_cp1_StatemachineDiagram0} -> Control_st_st1_StatemachineDiagram0(<exit.state, exited.state>^s)\n"
				+ "          	 []\n"
				+ "			 #s>0 & head(s) -> Control_st_st1_StatemachineDiagram0(tail(s))\n"
				+ "			 []\n"
				+ "			 #s<5 & interrupt.st_st1_StatemachineDiagram0 -> ControlAux(s); Control_st_st1_StatemachineDiagram0(<>)\n"
				+ "			 []\n"
				+ "		 	 end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);

	}
	
	//CompoundSimple
	
    @Test
	public void TestCompoundSimple() throws ParsingException{
		String actual = parser12.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_st1_StatemachineDiagram0 | st_cp1_StatemachineDiagram0 | st_cp21_StatemachineDiagram0 | st_cp22_StatemachineDiagram0 | st_cp2_StatemachineDiagram0 | st_st2_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_189_f4ae06b2f4220e47e572f0f1cf78d991 | tr_18q_f4ae06b2f4220e47e572f0f1cf78d991 | tr_19n_f4ae06b2f4220e47e572f0f1cf78d991 | tr_1iz_f4ae06b2f4220e47e572f0f1cf78d991 | tr_e53_4121b76452c060d0abc65b8ca36ebf77 | tr_iru_4121b76452c060d0abc65b8ca36ebf77\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel t4, t5, t3, t2, t1, t6 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop, final_Compound_st_st1_StatemachineDiagram0, final_Compound_st_cp1_StatemachineDiagram0\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "State_st_st1_init_StatemachineDiagram0 = State_st_st1_StatemachineDiagram0[|{|interrupt.st_st1_StatemachineDiagram0, enter.st_st1_StatemachineDiagram0, exit.st_st1_StatemachineDiagram0, exited.st_st1_StatemachineDiagram0, enter.st_cp1_StatemachineDiagram0, exit.st_cp1_StatemachineDiagram0, exited.st_cp1_StatemachineDiagram0, enter.st_cp21_StatemachineDiagram0, exit.st_cp21_StatemachineDiagram0, exited.st_cp21_StatemachineDiagram0, enter.st_cp22_StatemachineDiagram0, exit.st_cp22_StatemachineDiagram0, exited.st_cp22_StatemachineDiagram0, enter.st_cp2_StatemachineDiagram0, exit.st_cp2_StatemachineDiagram0, exited.st_cp2_StatemachineDiagram0, end|}|]Control_st_st1_StatemachineDiagram0(<>)\n"
				+ "State_st_st1_StatemachineDiagram0 = (enter.st_st1_StatemachineDiagram0 -> State_st_st1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_st1_Do_StatemachineDiagram0 = ((interrupt.st_st1_StatemachineDiagram0 -> SKIP) [|{|interrupt.st_st1_StatemachineDiagram0|}|]StartSync_Compound_st_st1_StatemachineDiagram0) \\ {end}; exit.st_st1_StatemachineDiagram0 -> exited.st_st1_StatemachineDiagram0 -> State_st_st1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_cp1_init_StatemachineDiagram0 = State_st_cp1_StatemachineDiagram0[|{|interrupt.st_cp1_StatemachineDiagram0, enter.st_cp1_StatemachineDiagram0, exit.st_cp1_StatemachineDiagram0, exited.st_cp1_StatemachineDiagram0, enter.st_cp21_StatemachineDiagram0, exit.st_cp21_StatemachineDiagram0, exited.st_cp21_StatemachineDiagram0, enter.st_cp22_StatemachineDiagram0, exit.st_cp22_StatemachineDiagram0, exited.st_cp22_StatemachineDiagram0, end|}|]Control_st_cp1_StatemachineDiagram0(<>)\n"
				+ "State_st_cp1_StatemachineDiagram0 = (enter.st_cp1_StatemachineDiagram0 -> State_st_cp1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_cp1_Do_StatemachineDiagram0 = ((interrupt.st_cp1_StatemachineDiagram0 -> SKIP) [|{|interrupt.st_cp1_StatemachineDiagram0, interrupt.st_st1_StatemachineDiagram0|}|]StartSync_Compound_st_cp1_StatemachineDiagram0) \\ {end}; exit.st_cp1_StatemachineDiagram0 -> exited.st_cp1_StatemachineDiagram0 -> State_st_cp1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_cp21_StatemachineDiagram0 = (enter.st_cp21_StatemachineDiagram0 -> State_st_cp21_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_cp21_Do_StatemachineDiagram0 = interrupt.st_cp21_StatemachineDiagram0 -> SKIP; exit.st_cp21_StatemachineDiagram0 -> exited.st_cp21_StatemachineDiagram0 -> State_st_cp21_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_cp22_StatemachineDiagram0 = (enter.st_cp22_StatemachineDiagram0 -> State_st_cp22_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_cp22_Do_StatemachineDiagram0 = interrupt.st_cp22_StatemachineDiagram0 -> SKIP; exit.st_cp22_StatemachineDiagram0 -> exited.st_cp22_StatemachineDiagram0 -> State_st_cp22_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_cp2_StatemachineDiagram0 = (enter.st_cp2_StatemachineDiagram0 -> State_st_cp2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_cp2_Do_StatemachineDiagram0 = interrupt.st_cp2_StatemachineDiagram0 -> SKIP; exit.st_cp2_StatemachineDiagram0 -> exited.st_cp2_StatemachineDiagram0 -> State_st_cp2_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_st2_StatemachineDiagram0 = (enter.st_st2_StatemachineDiagram0 -> State_st_st2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_st2_Do_StatemachineDiagram0 = interrupt.st_st2_StatemachineDiagram0 -> SKIP; exit.st_st2_StatemachineDiagram0 -> exited.st_st2_StatemachineDiagram0 -> State_st_st2_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_st2_BY_st_st1_189_f4ae06b2f4220e47e572f0f1cf78d991 = ((t4.tr_189_f4ae06b2f4220e47e572f0f1cf78d991 -> exit.st_st1_StatemachineDiagram0 -> exited.st_st1_StatemachineDiagram0 -> enter.st_st2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_st2_18q_f4ae06b2f4220e47e572f0f1cf78d991 = ((t5.tr_18q_f4ae06b2f4220e47e572f0f1cf78d991 -> exit.st_st2_StatemachineDiagram0 -> exited.st_st2_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_cp2_19n_f4ae06b2f4220e47e572f0f1cf78d991 = ((t3.tr_19n_f4ae06b2f4220e47e572f0f1cf78d991 -> exit.st_cp2_StatemachineDiagram0 -> exited.st_cp2_StatemachineDiagram0 -> final_Compound_st_st1_StatemachineDiagram0 -> interrupt.st_st1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_cp2_BY_st_cp1_1iz_f4ae06b2f4220e47e572f0f1cf78d991 = ((t2.tr_1iz_f4ae06b2f4220e47e572f0f1cf78d991 -> exit.st_cp1_StatemachineDiagram0 -> exited.st_cp1_StatemachineDiagram0 -> enter.st_cp2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_cp22_BY_st_cp21_e53_4121b76452c060d0abc65b8ca36ebf77 = ((t1.tr_e53_4121b76452c060d0abc65b8ca36ebf77 -> exit.st_cp21_StatemachineDiagram0 -> exited.st_cp21_StatemachineDiagram0 -> enter.st_cp22_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_cp22_iru_4121b76452c060d0abc65b8ca36ebf77 = ((t6.tr_iru_4121b76452c060d0abc65b8ca36ebf77 -> exit.st_cp22_StatemachineDiagram0 -> exited.st_cp22_StatemachineDiagram0 -> final_Compound_st_cp1_StatemachineDiagram0 -> interrupt.st_cp1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_Compound_cp1_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_cp21_StatemachineDiagram0, State_st_cp22_StatemachineDiagram0, FinalProcess_Compound_st_cp1_StatemachineDiagram0} @ x)\n"
				+ "Transitions_Compound_cp1_StatemachineDiagram0 = ((Tr_Turn_st_cp22_BY_st_cp21_e53_4121b76452c060d0abc65b8ca36ebf77; Transitions_Compound_cp1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_cp22_iru_4121b76452c060d0abc65b8ca36ebf77; Transitions_Compound_cp1_StatemachineDiagram0)) [] (interrupt.st_cp1_StatemachineDiagram0 -> exit?x:{st_cp21_StatemachineDiagram0, st_cp22_StatemachineDiagram0} -> exited.x -> end -> SKIP) [] (end -> SKIP)\n"
				+ "FinalProcess_Compound_st_cp1_StatemachineDiagram0 = (final_Compound_st_cp1_StatemachineDiagram0 -> interrupt.st_cp1_StatemachineDiagram0 -> end -> SKIP) [] (end -> SKIP)\n"
				+ "StartSync_Compound_st_cp1_StatemachineDiagram0= (States_Compound_cp1_StatemachineDiagram0 [[interrupt.st_cp21_StatemachineDiagram0 <- t1.tr_e53_4121b76452c060d0abc65b8ca36ebf77, interrupt.st_cp21_StatemachineDiagram0 <- interrupt.st_cp1_StatemachineDiagram0, interrupt.st_cp21_StatemachineDiagram0 <- interrupt.st_st1_StatemachineDiagram0, interrupt.st_cp22_StatemachineDiagram0 <- t6.tr_iru_4121b76452c060d0abc65b8ca36ebf77, interrupt.st_cp22_StatemachineDiagram0 <- interrupt.st_cp1_StatemachineDiagram0, interrupt.st_cp22_StatemachineDiagram0 <- interrupt.st_st1_StatemachineDiagram0]]  [|{|enter.st_cp21_StatemachineDiagram0, exit.st_cp21_StatemachineDiagram0, exited.st_cp21_StatemachineDiagram0, t1.tr_e53_4121b76452c060d0abc65b8ca36ebf77, enter.st_cp22_StatemachineDiagram0, exit.st_cp22_StatemachineDiagram0, exited.st_cp22_StatemachineDiagram0, t6.tr_iru_4121b76452c060d0abc65b8ca36ebf77, final_Compound_st_cp1_StatemachineDiagram0, interrupt.st_cp1_StatemachineDiagram0, interrupt.st_st1_StatemachineDiagram0, end|}|] (enter.st_cp21_StatemachineDiagram0 -> Transitions_Compound_cp1_StatemachineDiagram0))\n"
				+ "States_Compound_st1_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_cp1_init_StatemachineDiagram0, State_st_cp2_StatemachineDiagram0, FinalProcess_Compound_st_st1_StatemachineDiagram0} @ x)\n"
				+ "Transitions_Compound_st1_StatemachineDiagram0 = ((Tr_Turn_st_cp2_BY_st_cp1_1iz_f4ae06b2f4220e47e572f0f1cf78d991; Transitions_Compound_st1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState1_BY_st_cp2_19n_f4ae06b2f4220e47e572f0f1cf78d991; Transitions_Compound_st1_StatemachineDiagram0)) [] (interrupt.st_st1_StatemachineDiagram0 -> exit?x:{st_cp1_StatemachineDiagram0, st_cp2_StatemachineDiagram0} -> exited.x -> end -> SKIP) [] (end -> SKIP)\n"
				+ "FinalProcess_Compound_st_st1_StatemachineDiagram0 = (final_Compound_st_st1_StatemachineDiagram0 -> interrupt.st_st1_StatemachineDiagram0 -> end -> SKIP) [] (end -> SKIP)\n"
				+ "StartSync_Compound_st_st1_StatemachineDiagram0= (States_Compound_st1_StatemachineDiagram0 [[interrupt.st_cp1_StatemachineDiagram0 <- t2.tr_1iz_f4ae06b2f4220e47e572f0f1cf78d991, interrupt.st_cp1_StatemachineDiagram0 <- interrupt.st_st1_StatemachineDiagram0, interrupt.st_cp2_StatemachineDiagram0 <- t3.tr_19n_f4ae06b2f4220e47e572f0f1cf78d991, interrupt.st_cp2_StatemachineDiagram0 <- interrupt.st_st1_StatemachineDiagram0]]  [|{|enter.st_cp1_StatemachineDiagram0, exit.st_cp1_StatemachineDiagram0, exited.st_cp1_StatemachineDiagram0, t2.tr_1iz_f4ae06b2f4220e47e572f0f1cf78d991, enter.st_cp2_StatemachineDiagram0, exit.st_cp2_StatemachineDiagram0, exited.st_cp2_StatemachineDiagram0, t3.tr_19n_f4ae06b2f4220e47e572f0f1cf78d991, final_Compound_st_st1_StatemachineDiagram0, interrupt.st_st1_StatemachineDiagram0, end|}|] (enter.st_cp1_StatemachineDiagram0 -> Transitions_Compound_st1_StatemachineDiagram0))\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_st1_init_StatemachineDiagram0, State_st_st2_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_st2_BY_st_st1_189_f4ae06b2f4220e47e572f0f1cf78d991; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_st2_18q_f4ae06b2f4220e47e572f0f1cf78d991; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [[interrupt.st_st1_StatemachineDiagram0 <- t4.tr_189_f4ae06b2f4220e47e572f0f1cf78d991, interrupt.st_st2_StatemachineDiagram0 <- t5.tr_18q_f4ae06b2f4220e47e572f0f1cf78d991]]  [|{|enter.st_st1_StatemachineDiagram0, exit.st_st1_StatemachineDiagram0, exited.st_st1_StatemachineDiagram0, t4.tr_189_f4ae06b2f4220e47e572f0f1cf78d991, enter.st_st2_StatemachineDiagram0, exit.st_st2_StatemachineDiagram0, exited.st_st2_StatemachineDiagram0, t5.tr_18q_f4ae06b2f4220e47e572f0f1cf78d991, end, final_StatemachineDiagram0|}|] (enter.st_st1_StatemachineDiagram0 -> Transitions_StatemachineDiagram0)) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "Control_st_st1_StatemachineDiagram0(s) = #s<7 & enter.st_st1_StatemachineDiagram0 -> Control_st_st1_StatemachineDiagram0(<exit.st_st1_StatemachineDiagram0,exited.st_st1_StatemachineDiagram0>^s)\n"
				+ "			 [] \n"
				+ "			 #s<7 & enter?state:{st_cp1_StatemachineDiagram0, st_cp21_StatemachineDiagram0, st_cp22_StatemachineDiagram0, st_cp2_StatemachineDiagram0} -> Control_st_st1_StatemachineDiagram0(<exit.state, exited.state>^s)\n"
				+ "          	 []\n"
				+ "			 #s>0 & head(s) -> Control_st_st1_StatemachineDiagram0(tail(s))\n"
				+ "			 []\n"
				+ "			 #s<7 & interrupt.st_st1_StatemachineDiagram0 -> ControlAux(s); Control_st_st1_StatemachineDiagram0(<>)\n"
				+ "			 []\n"
				+ "		 	 end -> SKIP\n"
				+ "\n"
				+ "Control_st_cp1_StatemachineDiagram0(s) = #s<5 & enter.st_cp1_StatemachineDiagram0 -> Control_st_cp1_StatemachineDiagram0(<exit.st_cp1_StatemachineDiagram0,exited.st_cp1_StatemachineDiagram0>^s)\n"
				+ "			 [] \n"
				+ "			 #s<5 & enter?state:{st_cp21_StatemachineDiagram0, st_cp22_StatemachineDiagram0} -> Control_st_cp1_StatemachineDiagram0(<exit.state, exited.state>^s)\n"
				+ "          	 []\n"
				+ "			 #s>0 & head(s) -> Control_st_cp1_StatemachineDiagram0(tail(s))\n"
				+ "			 []\n"
				+ "			 #s<5 & interrupt.st_cp1_StatemachineDiagram0 -> ControlAux(s); Control_st_cp1_StatemachineDiagram0(<>)\n"
				+ "			 []\n"
				+ "		 	 end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}
	
	//CompoundIn
	
	@Test
	public void TestCompoundJunctionIn() throws ParsingException{
		String actual = parser13.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State3_StatemachineDiagram0 | st_State4_StatemachineDiagram0 | st_State5_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_20cq_447a572eb36ae2725261712c8be8b810 | tr_20el_447a572eb36ae2725261712c8be8b810 | tr_20ll_447a572eb36ae2725261712c8be8b810 | tr_2hj5_447a572eb36ae2725261712c8be8b810 | tr_97y_dc0c0902fe6bcb3dcb6079627249d7ab | tr_d39_dc0c0902fe6bcb3dcb6079627249d7ab | tr_hk6_dc0c0902fe6bcb3dcb6079627249d7ab\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel tr7, tr5, tr6, tr1, tr2, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop, final_Compound_st_State1_StatemachineDiagram0\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (end -> SKIP) [] \n"
				+ "(1 == 1) & internal_StatemachineDiagram0.tr_d39_dc0c0902fe6bcb3dcb6079627249d7ab -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1 == 2) & internal_StatemachineDiagram0.tr_hk6_dc0c0902fe6bcb3dcb6079627249d7ab -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_init_StatemachineDiagram0 = State_st_State1_StatemachineDiagram0[|{|interrupt.st_State1_StatemachineDiagram0, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, end|}|]Control_st_State1_StatemachineDiagram0(<>)\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((interrupt.st_State1_StatemachineDiagram0 -> SKIP) [|{|interrupt.st_State1_StatemachineDiagram0|}|]StartSync_Compound_st_State1_StatemachineDiagram0) \\ {end}; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = (enter.st_State3_StatemachineDiagram0 -> State_st_State3_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = interrupt.st_State3_StatemachineDiagram0 -> SKIP; exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State4_StatemachineDiagram0 = (enter.st_State4_StatemachineDiagram0 -> State_st_State4_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State4_Do_StatemachineDiagram0 = interrupt.st_State4_StatemachineDiagram0 -> SKIP; exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> State_st_State4_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State5_StatemachineDiagram0 = (enter.st_State5_StatemachineDiagram0 -> State_st_State5_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State5_Do_StatemachineDiagram0 = interrupt.st_State5_StatemachineDiagram0 -> SKIP; exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> State_st_State5_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = (enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint0_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint0_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_20cq_447a572eb36ae2725261712c8be8b810 = ((tr7.tr_20cq_447a572eb36ae2725261712c8be8b810 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_State3_20el_447a572eb36ae2725261712c8be8b810 = ((tr5.tr_20el_447a572eb36ae2725261712c8be8b810 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_State5_20ll_447a572eb36ae2725261712c8be8b810 = ((tr6.tr_20ll_447a572eb36ae2725261712c8be8b810 -> exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_2hj5_447a572eb36ae2725261712c8be8b810 = ((tr1.tr_2hj5_447a572eb36ae2725261712c8be8b810 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State4_97y_dc0c0902fe6bcb3dcb6079627249d7ab = ((tr2.tr_97y_dc0c0902fe6bcb3dcb6079627249d7ab -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_JunctionPoint0_d39_dc0c0902fe6bcb3dcb6079627249d7ab = ((internal_StatemachineDiagram0.tr_d39_dc0c0902fe6bcb3dcb6079627249d7ab -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State5_BY_st_JunctionPoint0_hk6_dc0c0902fe6bcb3dcb6079627249d7ab = ((internal_StatemachineDiagram0.tr_hk6_dc0c0902fe6bcb3dcb6079627249d7ab -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State5_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "States_Compound_State1_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State3_StatemachineDiagram0, State_st_State4_StatemachineDiagram0, State_st_State5_StatemachineDiagram0, State_st_JunctionPoint0_StatemachineDiagram0, FinalProcess_Compound_st_State1_StatemachineDiagram0} @ x)\n"
				+ "Transitions_Compound_State1_StatemachineDiagram0 = ((Tr_Turn_st_FinalState1_BY_st_State3_20el_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_JunctionPoint0_BY_st_State4_97y_dc0c0902fe6bcb3dcb6079627249d7ab; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState1_BY_st_State5_20ll_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_State3_BY_st_JunctionPoint0_d39_dc0c0902fe6bcb3dcb6079627249d7ab; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_State5_BY_st_JunctionPoint0_hk6_dc0c0902fe6bcb3dcb6079627249d7ab; Transitions_Compound_State1_StatemachineDiagram0)) [] (interrupt.st_State1_StatemachineDiagram0 -> exit?x:{st_State3_StatemachineDiagram0, st_State4_StatemachineDiagram0, st_State5_StatemachineDiagram0, st_JunctionPoint0_StatemachineDiagram0} -> exited.x -> end -> SKIP) [] (end -> SKIP)\n"
				+ "FinalProcess_Compound_st_State1_StatemachineDiagram0 = (final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0 -> end -> SKIP) [] (end -> SKIP)\n"
				+ "StartSync_Compound_st_State1_StatemachineDiagram0= (States_Compound_State1_StatemachineDiagram0 [[interrupt.st_State3_StatemachineDiagram0 <- tr5.tr_20el_447a572eb36ae2725261712c8be8b810, interrupt.st_State3_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_State4_StatemachineDiagram0 <- tr2.tr_97y_dc0c0902fe6bcb3dcb6079627249d7ab, interrupt.st_State4_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_State5_StatemachineDiagram0 <- tr6.tr_20ll_447a572eb36ae2725261712c8be8b810, interrupt.st_State5_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_d39_dc0c0902fe6bcb3dcb6079627249d7ab, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_hk6_dc0c0902fe6bcb3dcb6079627249d7ab, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0]]  [|{|enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, tr5.tr_20el_447a572eb36ae2725261712c8be8b810, enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, tr2.tr_97y_dc0c0902fe6bcb3dcb6079627249d7ab, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, tr6.tr_20ll_447a572eb36ae2725261712c8be8b810, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, internal_StatemachineDiagram0.tr_d39_dc0c0902fe6bcb3dcb6079627249d7ab, internal_StatemachineDiagram0.tr_hk6_dc0c0902fe6bcb3dcb6079627249d7ab, final_Compound_st_State1_StatemachineDiagram0, interrupt.st_State1_StatemachineDiagram0, end|}|] (enter.st_State4_StatemachineDiagram0 -> Transitions_Compound_State1_StatemachineDiagram0))\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State0_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State1_BY_st_State0_2hj5_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State1_20cq_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_State0_StatemachineDiagram0 <- tr1.tr_2hj5_447a572eb36ae2725261712c8be8b810, interrupt.st_State1_StatemachineDiagram0 <- tr7.tr_20cq_447a572eb36ae2725261712c8be8b810]]  [|{|enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, tr1.tr_2hj5_447a572eb36ae2725261712c8be8b810, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, tr7.tr_20cq_447a572eb36ae2725261712c8be8b810, end, final_StatemachineDiagram0|}|](enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|internal_StatemachineDiagram0.tr_d39_dc0c0902fe6bcb3dcb6079627249d7ab, internal_StatemachineDiagram0.tr_hk6_dc0c0902fe6bcb3dcb6079627249d7ab, end|}|] MEMORY_StatemachineDiagram0) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "Control_st_State1_StatemachineDiagram0(s) = #s<5 & enter.st_State1_StatemachineDiagram0 -> Control_st_State1_StatemachineDiagram0(<exit.st_State1_StatemachineDiagram0,exited.st_State1_StatemachineDiagram0>^s)\n"
				+ "			 [] \n"
				+ "			 #s<5 & enter?state:{st_JunctionPoint0_StatemachineDiagram0, st_State3_StatemachineDiagram0, st_State4_StatemachineDiagram0, st_State5_StatemachineDiagram0} -> Control_st_State1_StatemachineDiagram0(<exit.state, exited.state>^s)\n"
				+ "          	 []\n"
				+ "			 #s>0 & head(s) -> Control_st_State1_StatemachineDiagram0(tail(s))\n"
				+ "			 []\n"
				+ "			 #s<5 & interrupt.st_State1_StatemachineDiagram0 -> ControlAux(s); Control_st_State1_StatemachineDiagram0(<>)\n"
				+ "			 []\n"
				+ "		 	 end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}
	
	//CompoundOut
	
	@Test
	public void TestCompoundJunctionOut() throws ParsingException{
		String actual = parser14.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State4_StatemachineDiagram0 | st_State3_StatemachineDiagram0 | st_State2_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_118_447a572eb36ae2725261712c8be8b810 | tr_26a_447a572eb36ae2725261712c8be8b810 | tr_3nn_447a572eb36ae2725261712c8be8b810 | tr_49p_447a572eb36ae2725261712c8be8b810 | tr_4sz_447a572eb36ae2725261712c8be8b810 | tr_fdr_447a572eb36ae2725261712c8be8b810 | tr_qoh_447a572eb36ae2725261712c8be8b810\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel tr1, tr5, tr4, tr2, tr3, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop, final_Compound_st_State1_StatemachineDiagram0\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (end -> SKIP) [] \n"
				+ "(2==2) & internal_StatemachineDiagram0.tr_26a_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==1) & internal_StatemachineDiagram0.tr_3nn_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_init_StatemachineDiagram0 = State_st_State1_StatemachineDiagram0[|{|interrupt.st_State1_StatemachineDiagram0, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, end|}|]Control_st_State1_StatemachineDiagram0(<>)\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((interrupt.st_State1_StatemachineDiagram0 -> SKIP) [|{|interrupt.st_State1_StatemachineDiagram0|}|]StartSync_Compound_st_State1_StatemachineDiagram0) \\ {end}; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State4_StatemachineDiagram0 = (enter.st_State4_StatemachineDiagram0 -> State_st_State4_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State4_Do_StatemachineDiagram0 = interrupt.st_State4_StatemachineDiagram0 -> SKIP; exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> State_st_State4_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = (enter.st_State3_StatemachineDiagram0 -> State_st_State3_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = interrupt.st_State3_StatemachineDiagram0 -> SKIP; exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = (enter.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = interrupt.st_State2_StatemachineDiagram0 -> SKIP; exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = (enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint0_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint0_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State0_118_447a572eb36ae2725261712c8be8b810 = ((tr1.tr_118_447a572eb36ae2725261712c8be8b810 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_JunctionPoint0_26a_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_26a_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_JunctionPoint0_3nn_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_3nn_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_49p_447a572eb36ae2725261712c8be8b810 = ((tr5.tr_49p_447a572eb36ae2725261712c8be8b810 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State2_4sz_447a572eb36ae2725261712c8be8b810 = ((tr4.tr_4sz_447a572eb36ae2725261712c8be8b810 -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State4_fdr_447a572eb36ae2725261712c8be8b810 = ((tr2.tr_fdr_447a572eb36ae2725261712c8be8b810 -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_State3_qoh_447a572eb36ae2725261712c8be8b810 = ((tr3.tr_qoh_447a572eb36ae2725261712c8be8b810 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_Compound_State1_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State4_StatemachineDiagram0, State_st_State3_StatemachineDiagram0, FinalProcess_Compound_st_State1_StatemachineDiagram0} @ x)\n"
				+ "Transitions_Compound_State1_StatemachineDiagram0 = ((Tr_Turn_st_State3_BY_st_State4_fdr_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState1_BY_st_State3_qoh_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0)) [] (interrupt.st_State1_StatemachineDiagram0 -> exit?x:{st_State4_StatemachineDiagram0, st_State3_StatemachineDiagram0} -> exited.x -> end -> SKIP) [] (end -> SKIP)\n"
				+ "FinalProcess_Compound_st_State1_StatemachineDiagram0 = (final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0 -> end -> SKIP) [] (end -> SKIP)\n"
				+ "StartSync_Compound_st_State1_StatemachineDiagram0= (States_Compound_State1_StatemachineDiagram0 [[interrupt.st_State4_StatemachineDiagram0 <- tr2.tr_fdr_447a572eb36ae2725261712c8be8b810, interrupt.st_State4_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_State3_StatemachineDiagram0 <- tr3.tr_qoh_447a572eb36ae2725261712c8be8b810, interrupt.st_State3_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0]]  [|{|enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, tr2.tr_fdr_447a572eb36ae2725261712c8be8b810, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, tr3.tr_qoh_447a572eb36ae2725261712c8be8b810, final_Compound_st_State1_StatemachineDiagram0, interrupt.st_State1_StatemachineDiagram0, end|}|] (enter.st_State4_StatemachineDiagram0 -> Transitions_Compound_State1_StatemachineDiagram0))\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State0_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, State_st_State2_StatemachineDiagram0, State_st_JunctionPoint0_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_JunctionPoint0_BY_st_State0_118_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State1_49p_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State2_4sz_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State1_BY_st_JunctionPoint0_26a_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State2_BY_st_JunctionPoint0_3nn_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_State0_StatemachineDiagram0 <- tr1.tr_118_447a572eb36ae2725261712c8be8b810, interrupt.st_State1_StatemachineDiagram0 <- tr5.tr_49p_447a572eb36ae2725261712c8be8b810, interrupt.st_State2_StatemachineDiagram0 <- tr4.tr_4sz_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_26a_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_3nn_447a572eb36ae2725261712c8be8b810]]  [|{|enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, tr1.tr_118_447a572eb36ae2725261712c8be8b810, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, tr5.tr_49p_447a572eb36ae2725261712c8be8b810, enter.st_State2_StatemachineDiagram0, exit.st_State2_StatemachineDiagram0, exited.st_State2_StatemachineDiagram0, tr4.tr_4sz_447a572eb36ae2725261712c8be8b810, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, internal_StatemachineDiagram0.tr_26a_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_3nn_447a572eb36ae2725261712c8be8b810, end, final_StatemachineDiagram0|}|](enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|internal_StatemachineDiagram0.tr_26a_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_3nn_447a572eb36ae2725261712c8be8b810, end|}|] MEMORY_StatemachineDiagram0) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "Control_st_State1_StatemachineDiagram0(s) = #s<5 & enter.st_State1_StatemachineDiagram0 -> Control_st_State1_StatemachineDiagram0(<exit.st_State1_StatemachineDiagram0,exited.st_State1_StatemachineDiagram0>^s)\n"
				+ "			 [] \n"
				+ "			 #s<5 & enter?state:{st_State4_StatemachineDiagram0, st_State3_StatemachineDiagram0} -> Control_st_State1_StatemachineDiagram0(<exit.state, exited.state>^s)\n"
				+ "          	 []\n"
				+ "			 #s>0 & head(s) -> Control_st_State1_StatemachineDiagram0(tail(s))\n"
				+ "			 []\n"
				+ "			 #s<5 & interrupt.st_State1_StatemachineDiagram0 -> ControlAux(s); Control_st_State1_StatemachineDiagram0(<>)\n"
				+ "			 []\n"
				+ "		 	 end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}
	
	//CompoundInOut
	
	@Test
	public void TestCompoundJunctionOutIn() throws ParsingException{
		String actual = parser15.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n"
				+ "\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State2_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State4_StatemachineDiagram0 | st_State3_StatemachineDiagram0 | st_State5_StatemachineDiagram0 | st_JunctionPoint1_StatemachineDiagram0 | st_State0_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_vgd_447a572eb36ae2725261712c8be8b810 | tr_vmj_447a572eb36ae2725261712c8be8b810 | tr_vng_447a572eb36ae2725261712c8be8b810 | tr_vpb_447a572eb36ae2725261712c8be8b810 | tr_vps_447a572eb36ae2725261712c8be8b810 | tr_vq9_447a572eb36ae2725261712c8be8b810 | tr_1ess_447a572eb36ae2725261712c8be8b810 | tr_1iiv_447a572eb36ae2725261712c8be8b810 | tr_1me5_447a572eb36ae2725261712c8be8b810 | tr_1qx3_447a572eb36ae2725261712c8be8b810\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel tr1, tr6, tr5, tr3, tr2, tr4, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop, final_Compound_st_State1_StatemachineDiagram0\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (end -> SKIP) [] \n"
				+ "(2==2) & internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (2==2) & internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==2) & internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==1) & internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = (enter.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = interrupt.st_State2_StatemachineDiagram0 -> SKIP; exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State1_init_StatemachineDiagram0 = State_st_State1_StatemachineDiagram0[|{|interrupt.st_State1_StatemachineDiagram0, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, enter.st_JunctionPoint1_StatemachineDiagram0, exit.st_JunctionPoint1_StatemachineDiagram0, exited.st_JunctionPoint1_StatemachineDiagram0, end|}|]Control_st_State1_StatemachineDiagram0(<>)\n"
				+ "State_st_State1_StatemachineDiagram0 = (enter.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((interrupt.st_State1_StatemachineDiagram0 -> SKIP) [|{|interrupt.st_State1_StatemachineDiagram0|}|]StartSync_Compound_st_State1_StatemachineDiagram0) \\ {end}; exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State4_StatemachineDiagram0 = (enter.st_State4_StatemachineDiagram0 -> State_st_State4_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State4_Do_StatemachineDiagram0 = interrupt.st_State4_StatemachineDiagram0 -> SKIP; exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> State_st_State4_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = (enter.st_State3_StatemachineDiagram0 -> State_st_State3_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = interrupt.st_State3_StatemachineDiagram0 -> SKIP; exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State5_StatemachineDiagram0 = (enter.st_State5_StatemachineDiagram0 -> State_st_State5_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State5_Do_StatemachineDiagram0 = interrupt.st_State5_StatemachineDiagram0 -> SKIP; exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> State_st_State5_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint1_StatemachineDiagram0 = (enter.st_JunctionPoint1_StatemachineDiagram0 -> State_st_JunctionPoint1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint1_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint1_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint1_StatemachineDiagram0 -> exited.st_JunctionPoint1_StatemachineDiagram0 -> State_st_JunctionPoint1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = (enter.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = interrupt.st_State0_StatemachineDiagram0 -> SKIP; exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = (enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint0_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint0_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State0_vgd_447a572eb36ae2725261712c8be8b810 = ((tr1.tr_vgd_447a572eb36ae2725261712c8be8b810 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_vmj_447a572eb36ae2725261712c8be8b810 = ((tr6.tr_vmj_447a572eb36ae2725261712c8be8b810 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State2_vng_447a572eb36ae2725261712c8be8b810 = ((tr5.tr_vng_447a572eb36ae2725261712c8be8b810 -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_JunctionPoint0_vpb_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_JunctionPoint0_vps_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_State3_vq9_447a572eb36ae2725261712c8be8b810 = ((tr3.tr_vq9_447a572eb36ae2725261712c8be8b810 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint1_BY_st_State4_1ess_447a572eb36ae2725261712c8be8b810 = ((tr2.tr_1ess_447a572eb36ae2725261712c8be8b810 -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_JunctionPoint1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_JunctionPoint1_1iiv_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint1_StatemachineDiagram0 -> exited.st_JunctionPoint1_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State5_BY_st_JunctionPoint1_1me5_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint1_StatemachineDiagram0 -> exited.st_JunctionPoint1_StatemachineDiagram0 -> enter.st_State5_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_State5_1qx3_447a572eb36ae2725261712c8be8b810 = ((tr4.tr_1qx3_447a572eb36ae2725261712c8be8b810 -> exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "States_Compound_State1_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State4_StatemachineDiagram0, State_st_State3_StatemachineDiagram0, State_st_State5_StatemachineDiagram0, State_st_JunctionPoint1_StatemachineDiagram0, FinalProcess_Compound_st_State1_StatemachineDiagram0} @ x)\n"
				+ "Transitions_Compound_State1_StatemachineDiagram0 = ((Tr_Turn_st_JunctionPoint1_BY_st_State4_1ess_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState1_BY_st_State3_vq9_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState1_BY_st_State5_1qx3_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_State3_BY_st_JunctionPoint1_1iiv_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_State5_BY_st_JunctionPoint1_1me5_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0)) [] (interrupt.st_State1_StatemachineDiagram0 -> exit?x:{st_State4_StatemachineDiagram0, st_State3_StatemachineDiagram0, st_State5_StatemachineDiagram0, st_JunctionPoint1_StatemachineDiagram0} -> exited.x -> end -> SKIP) [] (end -> SKIP)\n"
				+ "FinalProcess_Compound_st_State1_StatemachineDiagram0 = (final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0 -> end -> SKIP) [] (end -> SKIP)\n"
				+ "StartSync_Compound_st_State1_StatemachineDiagram0= (States_Compound_State1_StatemachineDiagram0 [[interrupt.st_State4_StatemachineDiagram0 <- tr2.tr_1ess_447a572eb36ae2725261712c8be8b810, interrupt.st_State4_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_State3_StatemachineDiagram0 <- tr3.tr_vq9_447a572eb36ae2725261712c8be8b810, interrupt.st_State3_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_State5_StatemachineDiagram0 <- tr4.tr_1qx3_447a572eb36ae2725261712c8be8b810, interrupt.st_State5_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_JunctionPoint1_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint1_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint1_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0]]  [|{|enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, tr2.tr_1ess_447a572eb36ae2725261712c8be8b810, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, tr3.tr_vq9_447a572eb36ae2725261712c8be8b810, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, tr4.tr_1qx3_447a572eb36ae2725261712c8be8b810, enter.st_JunctionPoint1_StatemachineDiagram0, exit.st_JunctionPoint1_StatemachineDiagram0, exited.st_JunctionPoint1_StatemachineDiagram0, internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810, final_Compound_st_State1_StatemachineDiagram0, interrupt.st_State1_StatemachineDiagram0, end|}|] (enter.st_State4_StatemachineDiagram0 -> Transitions_Compound_State1_StatemachineDiagram0))\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State2_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, State_st_State0_StatemachineDiagram0, State_st_JunctionPoint0_StatemachineDiagram0, FinalProcess_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_FinalState0_BY_st_State2_vng_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State1_vmj_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_JunctionPoint0_BY_st_State0_vgd_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State1_BY_st_JunctionPoint0_vpb_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State2_BY_st_JunctionPoint0_vps_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0)) [] (end -> SKIP)\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_State2_StatemachineDiagram0 <- tr5.tr_vng_447a572eb36ae2725261712c8be8b810, interrupt.st_State1_StatemachineDiagram0 <- tr6.tr_vmj_447a572eb36ae2725261712c8be8b810, interrupt.st_State0_StatemachineDiagram0 <- tr1.tr_vgd_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810]]  [|{|enter.st_State2_StatemachineDiagram0, exit.st_State2_StatemachineDiagram0, exited.st_State2_StatemachineDiagram0, tr5.tr_vng_447a572eb36ae2725261712c8be8b810, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, tr6.tr_vmj_447a572eb36ae2725261712c8be8b810, enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, tr1.tr_vgd_447a572eb36ae2725261712c8be8b810, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810, end, final_StatemachineDiagram0|}|](enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810, end|}|] MEMORY_StatemachineDiagram0) \\ {end} \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0 -> end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "Control_st_State1_StatemachineDiagram0(s) = #s<5 & enter.st_State1_StatemachineDiagram0 -> Control_st_State1_StatemachineDiagram0(<exit.st_State1_StatemachineDiagram0,exited.st_State1_StatemachineDiagram0>^s)\n"
				+ "			 [] \n"
				+ "			 #s<5 & enter?state:{st_JunctionPoint1_StatemachineDiagram0, st_State4_StatemachineDiagram0, st_State3_StatemachineDiagram0, st_State5_StatemachineDiagram0} -> Control_st_State1_StatemachineDiagram0(<exit.state, exited.state>^s)\n"
				+ "          	 []\n"
				+ "			 #s>0 & head(s) -> Control_st_State1_StatemachineDiagram0(tail(s))\n"
				+ "			 []\n"
				+ "			 #s<5 & interrupt.st_State1_StatemachineDiagram0 -> ControlAux(s); Control_st_State1_StatemachineDiagram0(<>)\n"
				+ "			 []\n"
				+ "		 	 end -> SKIP\n"
				+ "\n"
				+ "\n"
				+ "MAIN = wbisim((StartSync_StatemachineDiagram0); LOOP\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}
	
	//DEADLOCK
	@Ignore
	@Test
	public void TestDeadlock() throws ParsingException{
		String actual = parser16.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("transparent wbisim\n\n"
				+ "datatype STATES_ID_StatemachineDiagram0 = st_State2_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State4_StatemachineDiagram0 | st_State3_StatemachineDiagram0 | st_State5_StatemachineDiagram0 | st_JunctionPoint1_StatemachineDiagram0 | st_State0_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_vgd_447a572eb36ae2725261712c8be8b810 | tr_vmj_447a572eb36ae2725261712c8be8b810 | tr_vng_447a572eb36ae2725261712c8be8b810 | tr_vpb_447a572eb36ae2725261712c8be8b810 | tr_vps_447a572eb36ae2725261712c8be8b810 | tr_vq9_447a572eb36ae2725261712c8be8b810 | tr_1ess_447a572eb36ae2725261712c8be8b810 | tr_1iiv_447a572eb36ae2725261712c8be8b810 | tr_1me5_447a572eb36ae2725261712c8be8b810 | tr_1qx3_447a572eb36ae2725261712c8be8b810\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel tr1, tr6, tr5, tr3, tr2, tr4, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel end, loop, final_Compound_st_State1_StatemachineDiagram0\n"
				+ "channel interrupt: STATES_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "LOOP = loop -> LOOP\n"
				+ "\n"
				+ "max (a, b) = if a < b then b else a\n"
				+ "min (a, b) = if a < b then a else b\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (2==2) & internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (2==2) & internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==2) & internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==1) & internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = enter.st_State2_StatemachineDiagram0 -> State_st_State2_Entry_StatemachineDiagram0\n"
				+ "State_st_State2_Entry_StatemachineDiagram0 = EntryProc(st_State2_StatemachineDiagram0)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = (DoProc(st_State2_StatemachineDiagram0)); ExitProc(st_State2_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State1_init_StatemachineDiagram0 = State_st_State1_StatemachineDiagram0[|{|interrupt.st_State1_StatemachineDiagram0, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, enter.st_JunctionPoint1_StatemachineDiagram0, exit.st_JunctionPoint1_StatemachineDiagram0, exited.st_JunctionPoint1_StatemachineDiagram0|}|]Control_st_State1_StatemachineDiagram0(<>)\n"
				+ "State_st_State1_StatemachineDiagram0 = enter.st_State1_StatemachineDiagram0 -> State_st_State1_Entry_StatemachineDiagram0\n"
				+ "State_st_State1_Entry_StatemachineDiagram0 = EntryProc(st_State1_StatemachineDiagram0)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = (DoProc(st_State1_StatemachineDiagram0) [|{|interrupt.st_State1_StatemachineDiagram0|}|]StartSync_Compound_st_State1_StatemachineDiagram0) \\ {end}; ExitProc(st_State1_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State4_StatemachineDiagram0 = (enter.st_State4_StatemachineDiagram0 -> State_st_State4_Entry_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State4_Entry_StatemachineDiagram0 = EntryProc(st_State4_StatemachineDiagram0)\n"
				+ "State_st_State4_Do_StatemachineDiagram0 = (DoProc(st_State4_StatemachineDiagram0)); ExitProc(st_State4_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = (enter.st_State3_StatemachineDiagram0 -> State_st_State3_Entry_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State3_Entry_StatemachineDiagram0 = EntryProc(st_State3_StatemachineDiagram0)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = (DoProc(st_State3_StatemachineDiagram0)); ExitProc(st_State3_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State5_StatemachineDiagram0 = (enter.st_State5_StatemachineDiagram0 -> State_st_State5_Entry_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_State5_Entry_StatemachineDiagram0 = EntryProc(st_State5_StatemachineDiagram0)\n"
				+ "State_st_State5_Do_StatemachineDiagram0 = (DoProc(st_State5_StatemachineDiagram0)); ExitProc(st_State5_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_JunctionPoint1_StatemachineDiagram0 = (enter.st_JunctionPoint1_StatemachineDiagram0 -> State_st_JunctionPoint1_Do_StatemachineDiagram0) [] (end -> SKIP)\n"
				+ "State_st_JunctionPoint1_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint1_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint1_StatemachineDiagram0 -> exited.st_JunctionPoint1_StatemachineDiagram0 -> State_st_JunctionPoint1_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = enter.st_State0_StatemachineDiagram0 -> State_st_State0_Entry_StatemachineDiagram0\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = (DoProc(st_State0_StatemachineDiagram0)); ExitProc(st_State0_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Do_StatemachineDiagram0\n"
				+ "State_st_JunctionPoint0_Do_StatemachineDiagram0 = (interrupt.st_JunctionPoint0_StatemachineDiagram0 -> SKIP); exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State0_vgd_447a572eb36ae2725261712c8be8b810 = ((tr1.tr_vgd_447a572eb36ae2725261712c8be8b810 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_vmj_447a572eb36ae2725261712c8be8b810 = ((tr6.tr_vmj_447a572eb36ae2725261712c8be8b810 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State2_vng_447a572eb36ae2725261712c8be8b810 = ((tr5.tr_vng_447a572eb36ae2725261712c8be8b810 -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> final_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_JunctionPoint0_vpb_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_JunctionPoint0_vps_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> exited.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_State3_vq9_447a572eb36ae2725261712c8be8b810 = ((tr3.tr_vq9_447a572eb36ae2725261712c8be8b810 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint1_BY_st_State4_1ess_447a572eb36ae2725261712c8be8b810 = ((tr2.tr_1ess_447a572eb36ae2725261712c8be8b810 -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_JunctionPoint1_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_JunctionPoint1_1iiv_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint1_StatemachineDiagram0 -> exited.st_JunctionPoint1_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_State5_BY_st_JunctionPoint1_1me5_447a572eb36ae2725261712c8be8b810 = ((internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810 -> exit.st_JunctionPoint1_StatemachineDiagram0 -> exited.st_JunctionPoint1_StatemachineDiagram0 -> enter.st_State5_StatemachineDiagram0 -> SKIP))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState1_BY_st_State5_1qx3_447a572eb36ae2725261712c8be8b810 = ((tr4.tr_1qx3_447a572eb36ae2725261712c8be8b810 -> exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0-> SKIP))\n"
				+ "\n"
				+ "EntryProc(st_State2_StatemachineDiagram0) = entry.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State1_StatemachineDiagram0) = entry.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "EntryProc(st_State4_StatemachineDiagram0) = entry.st_State4_StatemachineDiagram0 -> State_st_State4_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State3_StatemachineDiagram0) = entry.st_State3_StatemachineDiagram0 -> State_st_State3_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State5_StatemachineDiagram0) = entry.st_State5_StatemachineDiagram0 -> State_st_State5_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State2_StatemachineDiagram0) = do.st_State2_StatemachineDiagram0 -> LOOP /\\ interrupt.st_State2_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State1_StatemachineDiagram0) = do.st_State1_StatemachineDiagram0 -> LOOP /\\ interrupt.st_State1_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "DoProc(st_State4_StatemachineDiagram0) = do.st_State4_StatemachineDiagram0 -> LOOP /\\ interrupt.st_State4_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State3_StatemachineDiagram0) = do.st_State3_StatemachineDiagram0 -> LOOP /\\ interrupt.st_State3_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State5_StatemachineDiagram0) = do.st_State5_StatemachineDiagram0 -> LOOP /\\ interrupt.st_State5_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> LOOP /\\ interrupt.st_State0_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State2_StatemachineDiagram0) = exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "ExitProc(st_State1_StatemachineDiagram0) = exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "ExitProc(st_State4_StatemachineDiagram0) = exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> State_st_State4_StatemachineDiagram0\n"
				+ "ExitProc(st_State3_StatemachineDiagram0) = exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "ExitProc(st_State5_StatemachineDiagram0) = exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> State_st_State5_StatemachineDiagram0\n"
				+ "ExitProc(st_State0_StatemachineDiagram0) = exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "\n"
				+ "States_Compound_State1_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State4_StatemachineDiagram0, State_st_State3_StatemachineDiagram0, State_st_State5_StatemachineDiagram0, State_st_JunctionPoint1_StatemachineDiagram0, FinalProcess_Compound_st_State1_StatemachineDiagram0} @ x)\n"
				+ "Transitions_Compound_State1_StatemachineDiagram0 = ((Tr_Turn_st_JunctionPoint1_BY_st_State4_1ess_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState1_BY_st_State3_vq9_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_FinalState1_BY_st_State5_1qx3_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_State3_BY_st_JunctionPoint1_1iiv_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0) [] (Tr_Turn_st_State5_BY_st_JunctionPoint1_1me5_447a572eb36ae2725261712c8be8b810; Transitions_Compound_State1_StatemachineDiagram0)) [] (interrupt.st_State1_StatemachineDiagram0 -> exit?x:{st_State4_StatemachineDiagram0, st_State3_StatemachineDiagram0, st_State5_StatemachineDiagram0, st_JunctionPoint1_StatemachineDiagram0} -> exited.x -> end -> SKIP) [] (end -> SKIP)\n"
				+ "FinalProcess_Compound_st_State1_StatemachineDiagram0 = (final_Compound_st_State1_StatemachineDiagram0 -> interrupt.st_State1_StatemachineDiagram0 -> end -> SKIP) [] (end -> SKIP)\n"
				+ "StartSync_Compound_st_State1_StatemachineDiagram0= (States_Compound_State1_StatemachineDiagram0 [[interrupt.st_State4_StatemachineDiagram0 <- tr2.tr_1ess_447a572eb36ae2725261712c8be8b810, interrupt.st_State4_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_State3_StatemachineDiagram0 <- tr3.tr_vq9_447a572eb36ae2725261712c8be8b810, interrupt.st_State3_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_State5_StatemachineDiagram0 <- tr4.tr_1qx3_447a572eb36ae2725261712c8be8b810, interrupt.st_State5_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0, interrupt.st_JunctionPoint1_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint1_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint1_StatemachineDiagram0 <- interrupt.st_State1_StatemachineDiagram0]]  [|{|enter.st_State4_StatemachineDiagram0, exit.st_State4_StatemachineDiagram0, exited.st_State4_StatemachineDiagram0, tr2.tr_1ess_447a572eb36ae2725261712c8be8b810, enter.st_State3_StatemachineDiagram0, exit.st_State3_StatemachineDiagram0, exited.st_State3_StatemachineDiagram0, tr3.tr_vq9_447a572eb36ae2725261712c8be8b810, enter.st_State5_StatemachineDiagram0, exit.st_State5_StatemachineDiagram0, exited.st_State5_StatemachineDiagram0, tr4.tr_1qx3_447a572eb36ae2725261712c8be8b810, enter.st_JunctionPoint1_StatemachineDiagram0, exit.st_JunctionPoint1_StatemachineDiagram0, exited.st_JunctionPoint1_StatemachineDiagram0, internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810, final_Compound_st_State1_StatemachineDiagram0, interrupt.st_State1_StatemachineDiagram0, end|}|] (enter.st_State4_StatemachineDiagram0 -> Transitions_Compound_State1_StatemachineDiagram0))\n"
				+ "States_StatemachineDiagram0 = ([|{|end|}|] x: {State_st_State2_StatemachineDiagram0, State_st_State1_StatemachineDiagram0, State_st_State0_StatemachineDiagram0, State_st_JunctionPoint0_StatemachineDiagram0} @ x)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_FinalState0_BY_st_State2_vng_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_FinalState0_BY_st_State1_vmj_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_JunctionPoint0_BY_st_State0_vgd_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State1_BY_st_JunctionPoint0_vpb_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0) [] (Tr_Turn_st_State2_BY_st_JunctionPoint0_vps_447a572eb36ae2725261712c8be8b810; Transitions_StatemachineDiagram0))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [[interrupt.st_State2_StatemachineDiagram0 <- tr5.tr_vng_447a572eb36ae2725261712c8be8b810, interrupt.st_State1_StatemachineDiagram0 <- tr6.tr_vmj_447a572eb36ae2725261712c8be8b810, interrupt.st_State0_StatemachineDiagram0 <- tr1.tr_vgd_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810, interrupt.st_JunctionPoint0_StatemachineDiagram0 <- internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810]]  [|{|enter.st_State2_StatemachineDiagram0, exit.st_State2_StatemachineDiagram0, exited.st_State2_StatemachineDiagram0, tr5.tr_vng_447a572eb36ae2725261712c8be8b810, enter.st_State1_StatemachineDiagram0, exit.st_State1_StatemachineDiagram0, exited.st_State1_StatemachineDiagram0, tr6.tr_vmj_447a572eb36ae2725261712c8be8b810, enter.st_State0_StatemachineDiagram0, exit.st_State0_StatemachineDiagram0, exited.st_State0_StatemachineDiagram0, tr1.tr_vgd_447a572eb36ae2725261712c8be8b810, enter.st_JunctionPoint0_StatemachineDiagram0, exit.st_JunctionPoint0_StatemachineDiagram0, exited.st_JunctionPoint0_StatemachineDiagram0, internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810|}|](enter.st_State0_StatemachineDiagram0 -> Transitions_StatemachineDiagram0))[|{|internal_StatemachineDiagram0.tr_vpb_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_vps_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_1iiv_447a572eb36ae2725261712c8be8b810, internal_StatemachineDiagram0.tr_1me5_447a572eb36ae2725261712c8be8b810|}|] MEMORY_StatemachineDiagram0)/\\ finalState_StatemachineDiagram0 -> SKIP \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0); LOOP\n"
				+ "ControlAux(s) = #(s) > 0 & head(s) -> ControlAux(tail(s))\n"
				+ "                []\n"
				+ "                #(s) == 0 & SKIP\n"
				+ "Control_st_State1_StatemachineDiagram0(s) = enter.st_State1_StatemachineDiagram0 -> Control_st_State1_StatemachineDiagram0(<exit.st_State1_StatemachineDiagram0,exited.st_State1_StatemachineDiagram0>^s)\n"
				+ "			 [] \n"
				+ "			 enter?state:{st_JunctionPoint1_StatemachineDiagram0, st_State4_StatemachineDiagram0, st_State3_StatemachineDiagram0, st_State5_StatemachineDiagram0} -> Control_st_State1_StatemachineDiagram0(<exit.state, exited.state>^s)\n"
				+ "          	 []\n"
				+ "			 #s>0 & head(s) -> Control_st_State1_StatemachineDiagram0(tail(s))\n"
				+ "			 []\n"
				+ "			 interrupt.st_State1_StatemachineDiagram0 -> ControlAux(s)\n"
				+ "\n\n"
				+ "MAIN = wbisim(Start_StatemachineDiagram0\\ {loop})\n"
				+ "\n"
				+ "assert MAIN :[deadlock free[F]]\n"
				+ "assert MAIN :[divergence free]\n"
				+ "assert MAIN :[deterministic[F]]"
				+ "");
		assertEquals(expected.toString(), actual);
	}
}
