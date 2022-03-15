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
			projectAccessor.open("src/test/resources/stateMachine/st3.asta");
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
	@Ignore
	@Test
	public void TestResult() throws ParsingException{
		String actual = parser1.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_stateMachine1 = st_State0_stateMachine1 | st_State1_stateMachine1\n"
				+ "datatype TR_ID_stateMachine1 = tr_2x2_7f839bb5202181c6745985840802aea3 | tr_3i8_7f839bb5202181c6745985840802aea3\n"
				+ "\n"
				+ "channel activateTr: TR_ID_stateMachine1\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_stateMachine1\n"
				+ "channel final_stateMachine1, finalState_stateMachine1\n"
				+ "channel turnState1_, internal_stateMachine1 : TR_ID_stateMachine1\n"
				+ "\n"
				+ "State_st_State0_stateMachine1(init) = (init & enter.st_State0_stateMachine1-> State_st_State0_Entry_stateMachine1) [] (not(init) & State_st_State0_Entry_stateMachine1)\n"
				+ "State_st_State0_Entry_stateMachine1 = EntryProc(st_State0_stateMachine1)\n"
				+ "State_st_State0_Do_stateMachine1 = ((activateTr.tr_2x2_7f839bb5202181c6745985840802aea3 -> SKIP); DoProc(st_State0_stateMachine1)) /\\ exit.st_State0_stateMachine1 -> State_st_State0_Exit_stateMachine1\n"
				+ "State_st_State0_Exit_stateMachine1= ExitProc(st_State0_stateMachine1)\n"
				+ "\n"
				+ "State_st_State1_stateMachine1 = enter.st_State1_stateMachine1 -> State_st_State1_Entry_stateMachine1\n"
				+ "State_st_State1_Entry_stateMachine1 = EntryProc(st_State1_stateMachine1)\n"
				+ "State_st_State1_Do_stateMachine1 = ((activateTr.tr_3i8_7f839bb5202181c6745985840802aea3 -> SKIP); DoProc(st_State1_stateMachine1)) /\\ exit.st_State1_stateMachine1 -> State_st_State1_Exit_stateMachine1\n"
				+ "State_st_State1_Exit_stateMachine1= ExitProc(st_State1_stateMachine1)\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_2x2_7f839bb5202181c6745985840802aea3 = activateTr.tr_2x2_7f839bb5202181c6745985840802aea3 -> ((turnState1_.tr_2x2_7f839bb5202181c6745985840802aea3 -> exit.st_State0_stateMachine1 -> exited.st_State0_stateMachine1 -> enter.st_State1_stateMachine1 -> Tr_Turn_st_State1_BY_st_State0_2x2_7f839bb5202181c6745985840802aea3))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_3i8_7f839bb5202181c6745985840802aea3 = activateTr.tr_3i8_7f839bb5202181c6745985840802aea3 -> ((internal_stateMachine1.tr_3i8_7f839bb5202181c6745985840802aea3 -> exit.st_State1_stateMachine1 -> exited.st_State1_stateMachine1 -> final_stateMachine1-> Tr_Turn_st_FinalState0_BY_st_State1_3i8_7f839bb5202181c6745985840802aea3))\n"
				+ "\n"
				+ "EntryProc(st_State0_stateMachine1) = entry.st_State0_stateMachine1 -> State_st_State0_Do_stateMachine1\n"
				+ "EntryProc(st_State1_stateMachine1) = entry.st_State1_stateMachine1 -> State_st_State1_Do_stateMachine1\n"
				+ "\n"
				+ "DoProc(st_State0_stateMachine1) = do.st_State0_stateMachine1 -> SKIP\n"
				+ "DoProc(st_State1_stateMachine1) = do.st_State1_stateMachine1 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State0_stateMachine1) = exited.st_State0_stateMachine1 -> State_st_State0_stateMachine1(true)\n"
				+ "ExitProc(st_State1_stateMachine1) = exited.st_State1_stateMachine1 -> State_st_State1_stateMachine1\n"
				+ "\n"
				+ "States_stateMachine1 = (State_st_State0_stateMachine1(false) ||| State_st_State1_stateMachine1)\n"
				+ "Transitions_stateMachine1 = ((Tr_Turn_st_State1_BY_st_State0_2x2_7f839bb5202181c6745985840802aea3) ||| (Tr_Turn_st_FinalState0_BY_st_State1_3i8_7f839bb5202181c6745985840802aea3))\n"
				+ "\n"
				+ "StartSync_stateMachine1= (States_stateMachine1 [|{|activateTr, exit, exited, enter|}|] Transitions_stateMachine1) /\\ finalState_stateMachine1  -> SKIP\n"
				+ "\n"
				+ "FinalProcess_stateMachine1 = final_stateMachine1-> finalState_stateMachine1-> SKIP\n"
				+ "\n"
				+ "Start_stateMachine1 = (StartSync_stateMachine1 [|{|final_stateMachine1,finalState_stateMachine1|}|] FinalProcess_stateMachine1)");
		assertEquals(expected.toString(), actual);
	}


	//------------------ ST2 -------------------
	@Ignore
	@Test
	public void TestSt2() throws ParsingException{
		String actual = parser2.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State2_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_16t_22510ae7be7873da31c667c64a086efe | tr_1rz_22510ae7be7873da31c667c64a086efe | tr_2d9_22510ae7be7873da31c667c64a086efe | tr_2i_a2f7167c1975eb0344c63b077bbf885b\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Trigger, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0(init) = (init & enter.st_State0_StatemachineDiagram0-> State_st_State0_Entry_StatemachineDiagram0) [] (not(init) & State_st_State0_Entry_StatemachineDiagram0)\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = ((activateTr.tr_16t_22510ae7be7873da31c667c64a086efe -> SKIP); DoProc(st_State0_StatemachineDiagram0)) /\\ exit.st_State0_StatemachineDiagram0 -> State_st_State0_Exit_StatemachineDiagram0\n"
				+ "State_st_State0_Exit_StatemachineDiagram0= ExitProc(st_State0_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = enter.st_State1_StatemachineDiagram0 -> State_st_State1_Entry_StatemachineDiagram0\n"
				+ "State_st_State1_Entry_StatemachineDiagram0 = EntryProc(st_State1_StatemachineDiagram0)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((activateTr.tr_1rz_22510ae7be7873da31c667c64a086efe -> SKIP); DoProc(st_State1_StatemachineDiagram0)) /\\ exit.st_State1_StatemachineDiagram0 -> State_st_State1_Exit_StatemachineDiagram0\n"
				+ "State_st_State1_Exit_StatemachineDiagram0= ExitProc(st_State1_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = enter.st_State2_StatemachineDiagram0 -> State_st_State2_Entry_StatemachineDiagram0\n"
				+ "State_st_State2_Entry_StatemachineDiagram0 = EntryProc(st_State2_StatemachineDiagram0)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = ((activateTr.tr_2d9_22510ae7be7873da31c667c64a086efe -> SKIP ||| activateTr.tr_2i_a2f7167c1975eb0344c63b077bbf885b -> SKIP); DoProc(st_State2_StatemachineDiagram0)) /\\ exit.st_State2_StatemachineDiagram0 -> State_st_State2_Exit_StatemachineDiagram0\n"
				+ "State_st_State2_Exit_StatemachineDiagram0= ExitProc(st_State2_StatemachineDiagram0)\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_16t_22510ae7be7873da31c667c64a086efe = activateTr.tr_16t_22510ae7be7873da31c667c64a086efe -> ((Trigger.tr_16t_22510ae7be7873da31c667c64a086efe -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> Tr_Turn_st_State1_BY_st_State0_16t_22510ae7be7873da31c667c64a086efe))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_State1_1rz_22510ae7be7873da31c667c64a086efe = activateTr.tr_1rz_22510ae7be7873da31c667c64a086efe -> ((Trigger.tr_1rz_22510ae7be7873da31c667c64a086efe -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> Tr_Turn_st_State2_BY_st_State1_1rz_22510ae7be7873da31c667c64a086efe))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State2_2d9_22510ae7be7873da31c667c64a086efe = activateTr.tr_2d9_22510ae7be7873da31c667c64a086efe -> ((Trigger.tr_2d9_22510ae7be7873da31c667c64a086efe -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> Tr_Turn_st_State0_BY_st_State2_2d9_22510ae7be7873da31c667c64a086efe) [] (internal_StatemachineDiagram0.tr_2i_a2f7167c1975eb0344c63b077bbf885b -> Tr_Turn_st_State0_BY_st_State2_2d9_22510ae7be7873da31c667c64a086efe))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State2_2i_a2f7167c1975eb0344c63b077bbf885b = activateTr.tr_2i_a2f7167c1975eb0344c63b077bbf885b -> ((internal_StatemachineDiagram0.tr_2i_a2f7167c1975eb0344c63b077bbf885b -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> Tr_Turn_st_State1_BY_st_State2_2i_a2f7167c1975eb0344c63b077bbf885b) [] (Trigger.tr_2d9_22510ae7be7873da31c667c64a086efe -> Tr_Turn_st_State1_BY_st_State2_2i_a2f7167c1975eb0344c63b077bbf885b))\n"
				+ "\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State1_StatemachineDiagram0) = entry.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State2_StatemachineDiagram0) = entry.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State1_StatemachineDiagram0) = do.st_State1_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State2_StatemachineDiagram0) = do.st_State2_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State0_StatemachineDiagram0) = exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0(true)\n"
				+ "ExitProc(st_State1_StatemachineDiagram0) = exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "ExitProc(st_State2_StatemachineDiagram0) = exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = (State_st_State0_StatemachineDiagram0(false) ||| State_st_State1_StatemachineDiagram0 ||| State_st_State2_StatemachineDiagram0)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State1_BY_st_State0_16t_22510ae7be7873da31c667c64a086efe) ||| (Tr_Turn_st_State2_BY_st_State1_1rz_22510ae7be7873da31c667c64a086efe) ||| (Tr_Turn_st_State0_BY_st_State2_2d9_22510ae7be7873da31c667c64a086efe [|{|Trigger.tr_2d9_22510ae7be7873da31c667c64a086efe, internal_StatemachineDiagram0.tr_2i_a2f7167c1975eb0344c63b077bbf885b|}|] Tr_Turn_st_State1_BY_st_State2_2i_a2f7167c1975eb0344c63b077bbf885b))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [|{|activateTr, exit, exited, enter|}|] Transitions_StatemachineDiagram0) /\\ finalState_StatemachineDiagram0  -> SKIP\n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0)");
		assertEquals(expected.toString(), actual);
	}
	///------------------ ST3 -------------------------
	@Ignore
	@Test
	public void TestSt3() throws ParsingException{
		String actual = parser3.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State2_StatemachineDiagram0 | st_State3_StatemachineDiagram0 | st_State4_StatemachineDiagram0 | st_State5_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00 | tr_322_7f03c6b1d589de544b44b05fe2ca9e00 | tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00 | tr_4ti_7f03c6b1d589de544b44b05fe2ca9e00 | tr_5k0_7f03c6b1d589de544b44b05fe2ca9e00 | tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00 | tr_73o_7f03c6b1d589de544b44b05fe2ca9e00 | tr_7o2_7f03c6b1d589de544b44b05fe2ca9e00 | tr_8s1_7f03c6b1d589de544b44b05fe2ca9e00 | tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00 | tr_bxo_7f03c6b1d589de544b44b05fe2ca9e00\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Volta, Trigger, semi, FIm, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0(init) = (init & enter.st_State0_StatemachineDiagram0-> State_st_State0_Entry_StatemachineDiagram0) [] (not(init) & State_st_State0_Entry_StatemachineDiagram0)\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = ((activateTr.tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP ||| activateTr.tr_322_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP ||| activateTr.tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP); DoProc(st_State0_StatemachineDiagram0)) /\\ exit.st_State0_StatemachineDiagram0 -> State_st_State0_Exit_StatemachineDiagram0\n"
				+ "State_st_State0_Exit_StatemachineDiagram0= ExitProc(st_State0_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = enter.st_State1_StatemachineDiagram0 -> State_st_State1_Entry_StatemachineDiagram0\n"
				+ "State_st_State1_Entry_StatemachineDiagram0 = EntryProc(st_State1_StatemachineDiagram0)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((activateTr.tr_4ti_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP ||| activateTr.tr_5k0_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP); DoProc(st_State1_StatemachineDiagram0)) /\\ exit.st_State1_StatemachineDiagram0 -> State_st_State1_Exit_StatemachineDiagram0\n"
				+ "State_st_State1_Exit_StatemachineDiagram0= ExitProc(st_State1_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = enter.st_State2_StatemachineDiagram0 -> State_st_State2_Entry_StatemachineDiagram0\n"
				+ "State_st_State2_Entry_StatemachineDiagram0 = EntryProc(st_State2_StatemachineDiagram0)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = ((activateTr.tr_8s1_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP); DoProc(st_State2_StatemachineDiagram0)) /\\ exit.st_State2_StatemachineDiagram0 -> State_st_State2_Exit_StatemachineDiagram0\n"
				+ "State_st_State2_Exit_StatemachineDiagram0= ExitProc(st_State2_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = enter.st_State3_StatemachineDiagram0 -> State_st_State3_Entry_StatemachineDiagram0\n"
				+ "State_st_State3_Entry_StatemachineDiagram0 = EntryProc(st_State3_StatemachineDiagram0)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = ((activateTr.tr_7o2_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP); DoProc(st_State3_StatemachineDiagram0)) /\\ exit.st_State3_StatemachineDiagram0 -> State_st_State3_Exit_StatemachineDiagram0\n"
				+ "State_st_State3_Exit_StatemachineDiagram0= ExitProc(st_State3_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State4_StatemachineDiagram0 = enter.st_State4_StatemachineDiagram0 -> State_st_State4_Entry_StatemachineDiagram0\n"
				+ "State_st_State4_Entry_StatemachineDiagram0 = EntryProc(st_State4_StatemachineDiagram0)\n"
				+ "State_st_State4_Do_StatemachineDiagram0 = ((activateTr.tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP ||| activateTr.tr_73o_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP ||| activateTr.tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP); DoProc(st_State4_StatemachineDiagram0)) /\\ exit.st_State4_StatemachineDiagram0 -> State_st_State4_Exit_StatemachineDiagram0\n"
				+ "State_st_State4_Exit_StatemachineDiagram0= ExitProc(st_State4_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State5_StatemachineDiagram0 = enter.st_State5_StatemachineDiagram0 -> State_st_State5_Entry_StatemachineDiagram0\n"
				+ "State_st_State5_Entry_StatemachineDiagram0 = EntryProc(st_State5_StatemachineDiagram0)\n"
				+ "State_st_State5_Do_StatemachineDiagram0 = ((activateTr.tr_bxo_7f03c6b1d589de544b44b05fe2ca9e00 -> SKIP); DoProc(st_State5_StatemachineDiagram0)) /\\ exit.st_State5_StatemachineDiagram0 -> State_st_State5_Exit_StatemachineDiagram0\n"
				+ "State_st_State5_Exit_StatemachineDiagram0= ExitProc(st_State5_StatemachineDiagram0)\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_2gw_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00 -> ((internal_StatemachineDiagram0.tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> Tr_Turn_st_State1_BY_st_State0_2gw_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_322_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State1_BY_st_State0_2gw_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State1_BY_st_State0_2gw_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State0_322_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_322_7f03c6b1d589de544b44b05fe2ca9e00 -> ((internal_StatemachineDiagram0.tr_322_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> Tr_Turn_st_State3_BY_st_State0_322_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State3_BY_st_State0_322_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State3_BY_st_State0_322_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State4_BY_st_State0_3q0_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00 -> ((internal_StatemachineDiagram0.tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State4_StatemachineDiagram0 -> Tr_Turn_st_State4_BY_st_State0_3q0_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State4_BY_st_State0_3q0_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_322_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State4_BY_st_State0_3q0_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State1_4ti_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_4ti_7f03c6b1d589de544b44b05fe2ca9e00 -> ((internal_StatemachineDiagram0.tr_4ti_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> Tr_Turn_st_State3_BY_st_State1_4ti_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_5k0_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State3_BY_st_State1_4ti_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_State1_5k0_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_5k0_7f03c6b1d589de544b44b05fe2ca9e00 -> ((internal_StatemachineDiagram0.tr_5k0_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> Tr_Turn_st_State2_BY_st_State1_5k0_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_4ti_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State2_BY_st_State1_5k0_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State4_6ai_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00 -> ((internal_StatemachineDiagram0.tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> Tr_Turn_st_State3_BY_st_State4_6ai_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_73o_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State3_BY_st_State4_6ai_7f03c6b1d589de544b44b05fe2ca9e00) [] (semi.tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State3_BY_st_State4_6ai_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State4_BY_st_State4_73o_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_73o_7f03c6b1d589de544b44b05fe2ca9e00 -> ((internal_StatemachineDiagram0.tr_73o_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_State4_StatemachineDiagram0 -> Tr_Turn_st_State4_BY_st_State4_73o_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State4_BY_st_State4_73o_7f03c6b1d589de544b44b05fe2ca9e00) [] (semi.tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State4_BY_st_State4_73o_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State3_7o2_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_7o2_7f03c6b1d589de544b44b05fe2ca9e00 -> ((Volta.tr_7o2_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> Tr_Turn_st_State1_BY_st_State3_7o2_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State4_BY_st_State2_8s1_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_8s1_7f03c6b1d589de544b44b05fe2ca9e00 -> ((Trigger.tr_8s1_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State4_StatemachineDiagram0 -> Tr_Turn_st_State4_BY_st_State2_8s1_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_State5_BY_st_State4_b4e_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00 -> ((semi.tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_State5_StatemachineDiagram0 -> Tr_Turn_st_State5_BY_st_State4_b4e_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State5_BY_st_State4_b4e_7f03c6b1d589de544b44b05fe2ca9e00) [] (internal_StatemachineDiagram0.tr_73o_7f03c6b1d589de544b44b05fe2ca9e00 -> Tr_Turn_st_State5_BY_st_State4_b4e_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State5_bxo_7f03c6b1d589de544b44b05fe2ca9e00 = activateTr.tr_bxo_7f03c6b1d589de544b44b05fe2ca9e00 -> ((FIm.tr_bxo_7f03c6b1d589de544b44b05fe2ca9e00 -> exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_State5_bxo_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State1_StatemachineDiagram0) = entry.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State2_StatemachineDiagram0) = entry.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State3_StatemachineDiagram0) = entry.st_State3_StatemachineDiagram0 -> State_st_State3_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State4_StatemachineDiagram0) = entry.st_State4_StatemachineDiagram0 -> State_st_State4_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State5_StatemachineDiagram0) = entry.st_State5_StatemachineDiagram0 -> State_st_State5_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State1_StatemachineDiagram0) = do.st_State1_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State2_StatemachineDiagram0) = do.st_State2_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State3_StatemachineDiagram0) = do.st_State3_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State4_StatemachineDiagram0) = do.st_State4_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State5_StatemachineDiagram0) = do.st_State5_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State0_StatemachineDiagram0) = exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0(true)\n"
				+ "ExitProc(st_State1_StatemachineDiagram0) = exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "ExitProc(st_State2_StatemachineDiagram0) = exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "ExitProc(st_State3_StatemachineDiagram0) = exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "ExitProc(st_State4_StatemachineDiagram0) = exited.st_State4_StatemachineDiagram0 -> State_st_State4_StatemachineDiagram0\n"
				+ "ExitProc(st_State5_StatemachineDiagram0) = exited.st_State5_StatemachineDiagram0 -> State_st_State5_StatemachineDiagram0\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = (State_st_State0_StatemachineDiagram0(false) ||| State_st_State1_StatemachineDiagram0 ||| State_st_State2_StatemachineDiagram0 ||| State_st_State3_StatemachineDiagram0 ||| State_st_State4_StatemachineDiagram0 ||| State_st_State5_StatemachineDiagram0)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State4_BY_st_State2_8s1_7f03c6b1d589de544b44b05fe2ca9e00) ||| (Tr_Turn_st_State1_BY_st_State3_7o2_7f03c6b1d589de544b44b05fe2ca9e00) ||| (Tr_Turn_st_FinalState0_BY_st_State5_bxo_7f03c6b1d589de544b44b05fe2ca9e00) ||| ((Tr_Turn_st_State1_BY_st_State0_2gw_7f03c6b1d589de544b44b05fe2ca9e00 [|{|internal_StatemachineDiagram0.tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00, internal_StatemachineDiagram0.tr_322_7f03c6b1d589de544b44b05fe2ca9e00, internal_StatemachineDiagram0.tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00|}|] Tr_Turn_st_State3_BY_st_State0_322_7f03c6b1d589de544b44b05fe2ca9e00) [|{|internal_StatemachineDiagram0.tr_2gw_7f03c6b1d589de544b44b05fe2ca9e00, internal_StatemachineDiagram0.tr_322_7f03c6b1d589de544b44b05fe2ca9e00, internal_StatemachineDiagram0.tr_3q0_7f03c6b1d589de544b44b05fe2ca9e00|}|] Tr_Turn_st_State4_BY_st_State0_3q0_7f03c6b1d589de544b44b05fe2ca9e00) ||| (Tr_Turn_st_State3_BY_st_State1_4ti_7f03c6b1d589de544b44b05fe2ca9e00 [|{|internal_StatemachineDiagram0.tr_4ti_7f03c6b1d589de544b44b05fe2ca9e00, internal_StatemachineDiagram0.tr_5k0_7f03c6b1d589de544b44b05fe2ca9e00|}|] Tr_Turn_st_State2_BY_st_State1_5k0_7f03c6b1d589de544b44b05fe2ca9e00) ||| ((Tr_Turn_st_State3_BY_st_State4_6ai_7f03c6b1d589de544b44b05fe2ca9e00 [|{|internal_StatemachineDiagram0.tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00, internal_StatemachineDiagram0.tr_73o_7f03c6b1d589de544b44b05fe2ca9e00, semi.tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00|}|] Tr_Turn_st_State4_BY_st_State4_73o_7f03c6b1d589de544b44b05fe2ca9e00) [|{|internal_StatemachineDiagram0.tr_6ai_7f03c6b1d589de544b44b05fe2ca9e00, internal_StatemachineDiagram0.tr_73o_7f03c6b1d589de544b44b05fe2ca9e00, semi.tr_b4e_7f03c6b1d589de544b44b05fe2ca9e00|}|] Tr_Turn_st_State5_BY_st_State4_b4e_7f03c6b1d589de544b44b05fe2ca9e00))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [|{|activateTr, exit, exited, enter|}|] Transitions_StatemachineDiagram0) /\\ finalState_StatemachineDiagram0  -> SKIP\n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0)");
		assertEquals(expected.toString(), actual);
	}

	//-- ST4
	@Ignore
	@Test
	public void TestSt4() throws ParsingException{
		String actual = parser4.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State2_StatemachineDiagram0 | st_State3_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1hj_fe35c56ba095d87365478df4c07eca61 | tr_22p_fe35c56ba095d87365478df4c07eca61 | tr_2yh_fe35c56ba095d87365478df4c07eca61 | tr_3jr_fe35c56ba095d87365478df4c07eca61 | tr_4df_fe35c56ba095d87365478df4c07eca61 | tr_5ci_fe35c56ba095d87365478df4c07eca61 | tr_601_fe35c56ba095d87365478df4c07eca61 | tr_70_dcfc16efd476476895a6e4cb37a6c403\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Ida1, Ida2, problema, tentarNovamente, retornoInterno, vitoria : TR_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0(init) = (init & enter.st_State0_StatemachineDiagram0-> State_st_State0_Entry_StatemachineDiagram0) [] (not(init) & State_st_State0_Entry_StatemachineDiagram0)\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = ((activateTr.tr_1hj_fe35c56ba095d87365478df4c07eca61 -> SKIP ||| activateTr.tr_22p_fe35c56ba095d87365478df4c07eca61 -> SKIP ||| activateTr.tr_601_fe35c56ba095d87365478df4c07eca61 -> SKIP); DoProc(st_State0_StatemachineDiagram0)) /\\ exit.st_State0_StatemachineDiagram0 -> State_st_State0_Exit_StatemachineDiagram0\n"
				+ "State_st_State0_Exit_StatemachineDiagram0= ExitProc(st_State0_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = enter.st_State1_StatemachineDiagram0 -> State_st_State1_Entry_StatemachineDiagram0\n"
				+ "State_st_State1_Entry_StatemachineDiagram0 = EntryProc(st_State1_StatemachineDiagram0)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((activateTr.tr_3jr_fe35c56ba095d87365478df4c07eca61 -> SKIP ||| activateTr.tr_70_dcfc16efd476476895a6e4cb37a6c403 -> SKIP); DoProc(st_State1_StatemachineDiagram0)) /\\ exit.st_State1_StatemachineDiagram0 -> State_st_State1_Exit_StatemachineDiagram0\n"
				+ "State_st_State1_Exit_StatemachineDiagram0= ExitProc(st_State1_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = enter.st_State2_StatemachineDiagram0 -> State_st_State2_Entry_StatemachineDiagram0\n"
				+ "State_st_State2_Entry_StatemachineDiagram0 = EntryProc(st_State2_StatemachineDiagram0)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = ((activateTr.tr_2yh_fe35c56ba095d87365478df4c07eca61 -> SKIP); DoProc(st_State2_StatemachineDiagram0)) /\\ exit.st_State2_StatemachineDiagram0 -> State_st_State2_Exit_StatemachineDiagram0\n"
				+ "State_st_State2_Exit_StatemachineDiagram0= ExitProc(st_State2_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = enter.st_State3_StatemachineDiagram0 -> State_st_State3_Entry_StatemachineDiagram0\n"
				+ "State_st_State3_Entry_StatemachineDiagram0 = EntryProc(st_State3_StatemachineDiagram0)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = ((activateTr.tr_4df_fe35c56ba095d87365478df4c07eca61 -> SKIP ||| activateTr.tr_5ci_fe35c56ba095d87365478df4c07eca61 -> SKIP); DoProc(st_State3_StatemachineDiagram0)) /\\ exit.st_State3_StatemachineDiagram0 -> State_st_State3_Exit_StatemachineDiagram0\n"
				+ "State_st_State3_Exit_StatemachineDiagram0= ExitProc(st_State3_StatemachineDiagram0)\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_1hj_fe35c56ba095d87365478df4c07eca61 = activateTr.tr_1hj_fe35c56ba095d87365478df4c07eca61 -> ((Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> Tr_Turn_st_State1_BY_st_State0_1hj_fe35c56ba095d87365478df4c07eca61) [] (Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State1_BY_st_State0_1hj_fe35c56ba095d87365478df4c07eca61) [] (retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State1_BY_st_State0_1hj_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_State0_22p_fe35c56ba095d87365478df4c07eca61 = activateTr.tr_22p_fe35c56ba095d87365478df4c07eca61 -> ((Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> Tr_Turn_st_State2_BY_st_State0_22p_fe35c56ba095d87365478df4c07eca61) [] (Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State2_BY_st_State0_22p_fe35c56ba095d87365478df4c07eca61) [] (retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State2_BY_st_State0_22p_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State2_2yh_fe35c56ba095d87365478df4c07eca61 = activateTr.tr_2yh_fe35c56ba095d87365478df4c07eca61 -> ((problema.tr_2yh_fe35c56ba095d87365478df4c07eca61 -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> Tr_Turn_st_State3_BY_st_State2_2yh_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State1_3jr_fe35c56ba095d87365478df4c07eca61 = activateTr.tr_3jr_fe35c56ba095d87365478df4c07eca61 -> ((problema.tr_3jr_fe35c56ba095d87365478df4c07eca61 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> Tr_Turn_st_State3_BY_st_State1_3jr_fe35c56ba095d87365478df4c07eca61) [] (vitoria.tr_70_dcfc16efd476476895a6e4cb37a6c403 -> Tr_Turn_st_State3_BY_st_State1_3jr_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State3_4df_fe35c56ba095d87365478df4c07eca61 = activateTr.tr_4df_fe35c56ba095d87365478df4c07eca61 -> ((tentarNovamente.tr_4df_fe35c56ba095d87365478df4c07eca61 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> Tr_Turn_st_State0_BY_st_State3_4df_fe35c56ba095d87365478df4c07eca61) [] (retornoInterno.tr_5ci_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State0_BY_st_State3_4df_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_State3_5ci_fe35c56ba095d87365478df4c07eca61 = activateTr.tr_5ci_fe35c56ba095d87365478df4c07eca61 -> ((retornoInterno.tr_5ci_fe35c56ba095d87365478df4c07eca61 -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> Tr_Turn_st_State3_BY_st_State3_5ci_fe35c56ba095d87365478df4c07eca61) [] (tentarNovamente.tr_4df_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State3_BY_st_State3_5ci_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State0_601_fe35c56ba095d87365478df4c07eca61 = activateTr.tr_601_fe35c56ba095d87365478df4c07eca61 -> ((retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> Tr_Turn_st_State0_BY_st_State0_601_fe35c56ba095d87365478df4c07eca61) [] (Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State0_BY_st_State0_601_fe35c56ba095d87365478df4c07eca61) [] (Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_State0_BY_st_State0_601_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_70_dcfc16efd476476895a6e4cb37a6c403 = activateTr.tr_70_dcfc16efd476476895a6e4cb37a6c403 -> ((vitoria.tr_70_dcfc16efd476476895a6e4cb37a6c403 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_State1_70_dcfc16efd476476895a6e4cb37a6c403) [] (problema.tr_3jr_fe35c56ba095d87365478df4c07eca61 -> Tr_Turn_st_FinalState0_BY_st_State1_70_dcfc16efd476476895a6e4cb37a6c403))\n"
				+ "\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State1_StatemachineDiagram0) = entry.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State2_StatemachineDiagram0) = entry.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State3_StatemachineDiagram0) = entry.st_State3_StatemachineDiagram0 -> State_st_State3_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State1_StatemachineDiagram0) = do.st_State1_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State2_StatemachineDiagram0) = do.st_State2_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State3_StatemachineDiagram0) = do.st_State3_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State0_StatemachineDiagram0) = exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0(true)\n"
				+ "ExitProc(st_State1_StatemachineDiagram0) = exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "ExitProc(st_State2_StatemachineDiagram0) = exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "ExitProc(st_State3_StatemachineDiagram0) = exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = (State_st_State0_StatemachineDiagram0(false) ||| State_st_State1_StatemachineDiagram0 ||| State_st_State2_StatemachineDiagram0 ||| State_st_State3_StatemachineDiagram0)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_State3_BY_st_State2_2yh_fe35c56ba095d87365478df4c07eca61) ||| ((Tr_Turn_st_State1_BY_st_State0_1hj_fe35c56ba095d87365478df4c07eca61 [|{|Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61, Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61, retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61|}|] Tr_Turn_st_State2_BY_st_State0_22p_fe35c56ba095d87365478df4c07eca61) [|{|Ida1.tr_1hj_fe35c56ba095d87365478df4c07eca61, Ida2.tr_22p_fe35c56ba095d87365478df4c07eca61, retornoInterno.tr_601_fe35c56ba095d87365478df4c07eca61|}|] Tr_Turn_st_State0_BY_st_State0_601_fe35c56ba095d87365478df4c07eca61) ||| (Tr_Turn_st_State3_BY_st_State1_3jr_fe35c56ba095d87365478df4c07eca61 [|{|problema.tr_3jr_fe35c56ba095d87365478df4c07eca61, vitoria.tr_70_dcfc16efd476476895a6e4cb37a6c403|}|] Tr_Turn_st_FinalState0_BY_st_State1_70_dcfc16efd476476895a6e4cb37a6c403) ||| (Tr_Turn_st_State0_BY_st_State3_4df_fe35c56ba095d87365478df4c07eca61 [|{|tentarNovamente.tr_4df_fe35c56ba095d87365478df4c07eca61, retornoInterno.tr_5ci_fe35c56ba095d87365478df4c07eca61|}|] Tr_Turn_st_State3_BY_st_State3_5ci_fe35c56ba095d87365478df4c07eca61))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0= (States_StatemachineDiagram0 [|{|activateTr, exit, exited, enter|}|] Transitions_StatemachineDiagram0) /\\ finalState_StatemachineDiagram0  -> SKIP\n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0)");
		assertEquals(expected.toString(), actual);
	}

	// ST5
	@Ignore
	@Test
	public void TestSt5() throws ParsingException{
		String actual = parser5.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_smp_bat = st_On_smp_bat | st_Recharge_smp_bat | st_SOS_smp_bat | st_Off_smp_bat\n"
				+ "datatype TR_ID_smp_bat = tr_5ub_83c2583f83e5e7797d82ad5650498d96 | tr_5uu_83c2583f83e5e7797d82ad5650498d96 | tr_5vt_83c2583f83e5e7797d82ad5650498d96 | tr_5wa_83c2583f83e5e7797d82ad5650498d96 | tr_61i_83c2583f83e5e7797d82ad5650498d96 | tr_64x_83c2583f83e5e7797d82ad5650498d96 | tr_65f_83c2583f83e5e7797d82ad5650498d96 | tr_65x_83c2583f83e5e7797d82ad5650498d96 | tr_2e_151c15ac07aa17dadd42b370fe8fd617\n"
				+ "\n"
				+ "channel activateTr: TR_ID_smp_bat\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_smp_bat\n"
				+ "channel final_smp_bat, finalState_smp_bat\n"
				+ "channel internal_smp_bat : TR_ID_smp_bat\n"
				+ "\n"
				+ "State_st_On_smp_bat(init) = (init & enter.st_On_smp_bat-> State_st_On_Entry_smp_bat) [] (not(init) & State_st_On_Entry_smp_bat)\n"
				+ "State_st_On_Entry_smp_bat = EntryProc(st_On_smp_bat)\n"
				+ "State_st_On_Do_smp_bat = ((activateTr.tr_5wa_83c2583f83e5e7797d82ad5650498d96 -> SKIP ||| activateTr.tr_61i_83c2583f83e5e7797d82ad5650498d96 -> SKIP ||| activateTr.tr_64x_83c2583f83e5e7797d82ad5650498d96 -> SKIP ||| activateTr.tr_65f_83c2583f83e5e7797d82ad5650498d96 -> SKIP); DoProc(st_On_smp_bat)) /\\ exit.st_On_smp_bat -> State_st_On_Exit_smp_bat\n"
				+ "State_st_On_Exit_smp_bat= ExitProc(st_On_smp_bat)\n"
				+ "\n"
				+ "State_st_Recharge_smp_bat = enter.st_Recharge_smp_bat -> State_st_Recharge_Entry_smp_bat\n"
				+ "State_st_Recharge_Entry_smp_bat = EntryProc(st_Recharge_smp_bat)\n"
				+ "State_st_Recharge_Do_smp_bat = ((activateTr.tr_5ub_83c2583f83e5e7797d82ad5650498d96 -> SKIP ||| activateTr.tr_2e_151c15ac07aa17dadd42b370fe8fd617 -> SKIP); DoProc(st_Recharge_smp_bat)) /\\ exit.st_Recharge_smp_bat -> State_st_Recharge_Exit_smp_bat\n"
				+ "State_st_Recharge_Exit_smp_bat= ExitProc(st_Recharge_smp_bat)\n"
				+ "\n"
				+ "State_st_SOS_smp_bat = enter.st_SOS_smp_bat -> State_st_SOS_Entry_smp_bat\n"
				+ "State_st_SOS_Entry_smp_bat = EntryProc(st_SOS_smp_bat)\n"
				+ "State_st_SOS_Do_smp_bat = ((activateTr.tr_5vt_83c2583f83e5e7797d82ad5650498d96 -> SKIP); DoProc(st_SOS_smp_bat)) /\\ exit.st_SOS_smp_bat -> State_st_SOS_Exit_smp_bat\n"
				+ "State_st_SOS_Exit_smp_bat= ExitProc(st_SOS_smp_bat)\n"
				+ "\n"
				+ "State_st_Off_smp_bat = enter.st_Off_smp_bat -> State_st_Off_Entry_smp_bat\n"
				+ "State_st_Off_Entry_smp_bat = EntryProc(st_Off_smp_bat)\n"
				+ "State_st_Off_Do_smp_bat = ((activateTr.tr_5uu_83c2583f83e5e7797d82ad5650498d96 -> SKIP ||| activateTr.tr_65x_83c2583f83e5e7797d82ad5650498d96 -> SKIP); DoProc(st_Off_smp_bat)) /\\ exit.st_Off_smp_bat -> State_st_Off_Exit_smp_bat\n"
				+ "State_st_Off_Exit_smp_bat= ExitProc(st_Off_smp_bat)\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Recharge_5ub_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_5ub_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_5ub_83c2583f83e5e7797d82ad5650498d96 -> exit.st_Recharge_smp_bat -> exited.st_Recharge_smp_bat -> enter.st_On_smp_bat -> Tr_Turn_st_On_BY_st_Recharge_5ub_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_2e_151c15ac07aa17dadd42b370fe8fd617 -> Tr_Turn_st_On_BY_st_Recharge_5ub_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Off_5uu_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_5uu_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_5uu_83c2583f83e5e7797d82ad5650498d96 -> exit.st_Off_smp_bat -> exited.st_Off_smp_bat -> enter.st_On_smp_bat -> Tr_Turn_st_On_BY_st_Off_5uu_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_65x_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_On_BY_st_Off_5uu_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_SOS_5vt_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_5vt_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_5vt_83c2583f83e5e7797d82ad5650498d96 -> exit.st_SOS_smp_bat -> exited.st_SOS_smp_bat -> enter.st_Off_smp_bat -> Tr_Turn_st_Off_BY_st_SOS_5vt_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_5wa_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_Off_smp_bat -> Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_61i_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_Recharge_smp_bat -> Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_64x_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_SOS_smp_bat -> Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_65f_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96 -> exit.st_On_smp_bat -> exited.st_On_smp_bat -> enter.st_Off_smp_bat -> Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Off_65x_83c2583f83e5e7797d82ad5650498d96 = activateTr.tr_65x_83c2583f83e5e7797d82ad5650498d96 -> ((internal_smp_bat.tr_65x_83c2583f83e5e7797d82ad5650498d96 -> exit.st_Off_smp_bat -> exited.st_Off_smp_bat -> enter.st_Recharge_smp_bat -> Tr_Turn_st_Recharge_BY_st_Off_65x_83c2583f83e5e7797d82ad5650498d96) [] (internal_smp_bat.tr_5uu_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Recharge_BY_st_Off_65x_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Recharge_2e_151c15ac07aa17dadd42b370fe8fd617 = activateTr.tr_2e_151c15ac07aa17dadd42b370fe8fd617 -> ((internal_smp_bat.tr_2e_151c15ac07aa17dadd42b370fe8fd617 -> exit.st_Recharge_smp_bat -> exited.st_Recharge_smp_bat -> enter.st_Recharge_smp_bat -> Tr_Turn_st_Recharge_BY_st_Recharge_2e_151c15ac07aa17dadd42b370fe8fd617) [] (internal_smp_bat.tr_5ub_83c2583f83e5e7797d82ad5650498d96 -> Tr_Turn_st_Recharge_BY_st_Recharge_2e_151c15ac07aa17dadd42b370fe8fd617))\n"
				+ "\n"
				+ "EntryProc(st_On_smp_bat) = entry.st_On_smp_bat -> State_st_On_Do_smp_bat\n"
				+ "EntryProc(st_Recharge_smp_bat) = entry.st_Recharge_smp_bat -> State_st_Recharge_Do_smp_bat\n"
				+ "EntryProc(st_SOS_smp_bat) = entry.st_SOS_smp_bat -> State_st_SOS_Do_smp_bat\n"
				+ "EntryProc(st_Off_smp_bat) = entry.st_Off_smp_bat -> State_st_Off_Do_smp_bat\n"
				+ "\n"
				+ "DoProc(st_On_smp_bat) = do.st_On_smp_bat -> SKIP\n"
				+ "DoProc(st_Recharge_smp_bat) = do.st_Recharge_smp_bat -> SKIP\n"
				+ "DoProc(st_SOS_smp_bat) = do.st_SOS_smp_bat -> SKIP\n"
				+ "DoProc(st_Off_smp_bat) = do.st_Off_smp_bat -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_On_smp_bat) = exited.st_On_smp_bat -> State_st_On_smp_bat(true)\n"
				+ "ExitProc(st_Recharge_smp_bat) = exited.st_Recharge_smp_bat -> State_st_Recharge_smp_bat\n"
				+ "ExitProc(st_SOS_smp_bat) = exited.st_SOS_smp_bat -> State_st_SOS_smp_bat\n"
				+ "ExitProc(st_Off_smp_bat) = exited.st_Off_smp_bat -> State_st_Off_smp_bat\n"
				+ "\n"
				+ "States_smp_bat = (State_st_On_smp_bat(false) ||| State_st_Recharge_smp_bat ||| State_st_SOS_smp_bat ||| State_st_Off_smp_bat)\n"
				+ "Transitions_smp_bat = ((Tr_Turn_st_Off_BY_st_SOS_5vt_83c2583f83e5e7797d82ad5650498d96) ||| (((Tr_Turn_st_Off_BY_st_On_5wa_83c2583f83e5e7797d82ad5650498d96 [|{|internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96|}|] Tr_Turn_st_Recharge_BY_st_On_61i_83c2583f83e5e7797d82ad5650498d96) [|{|internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96|}|] Tr_Turn_st_SOS_BY_st_On_64x_83c2583f83e5e7797d82ad5650498d96) [|{|internal_smp_bat.tr_5wa_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_61i_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_64x_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_65f_83c2583f83e5e7797d82ad5650498d96|}|] Tr_Turn_st_Off_BY_st_On_65f_83c2583f83e5e7797d82ad5650498d96) ||| (Tr_Turn_st_On_BY_st_Recharge_5ub_83c2583f83e5e7797d82ad5650498d96 [|{|internal_smp_bat.tr_5ub_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_2e_151c15ac07aa17dadd42b370fe8fd617|}|] Tr_Turn_st_Recharge_BY_st_Recharge_2e_151c15ac07aa17dadd42b370fe8fd617) ||| (Tr_Turn_st_On_BY_st_Off_5uu_83c2583f83e5e7797d82ad5650498d96 [|{|internal_smp_bat.tr_5uu_83c2583f83e5e7797d82ad5650498d96, internal_smp_bat.tr_65x_83c2583f83e5e7797d82ad5650498d96|}|] Tr_Turn_st_Recharge_BY_st_Off_65x_83c2583f83e5e7797d82ad5650498d96))\n"
				+ "\n"
				+ "StartSync_smp_bat= (States_smp_bat [|{|activateTr, exit, exited, enter|}|] Transitions_smp_bat) /\\ finalState_smp_bat  -> SKIP\n"
				+ "\n"
				+ "FinalProcess_smp_bat = final_smp_bat-> finalState_smp_bat-> SKIP\n"
				+ "\n"
				+ "Start_smp_bat = (StartSync_smp_bat [|{|final_smp_bat,finalState_smp_bat|}|] FinalProcess_smp_bat)");
		assertEquals(expected.toString(), actual);
	}
	//ST6
	@Ignore
	@Test
	public void TestSt6() throws ParsingException{
		String actual = parser6.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_light_bt = st_Recharge_light_bt | st_On_light_bt | st_Off_light_bt | st_SOS_light_bt\n"
				+ "datatype TR_ID_light_bt = tr_1ay_0c1aea76f28ac0dbed586ac2af18089c | tr_1bh_0c1aea76f28ac0dbed586ac2af18089c | tr_1ef_0c1aea76f28ac0dbed586ac2af18089c | tr_1ex_0c1aea76f28ac0dbed586ac2af18089c | tr_1fe_0c1aea76f28ac0dbed586ac2af18089c | tr_1fw_0c1aea76f28ac0dbed586ac2af18089c | tr_1ge_0c1aea76f28ac0dbed586ac2af18089c | tr_1l6_0c1aea76f28ac0dbed586ac2af18089c | tr_1oj_0c1aea76f28ac0dbed586ac2af18089c | tr_1qg_0c1aea76f28ac0dbed586ac2af18089c\n"
				+ "\n"
				+ "channel activateTr: TR_ID_light_bt\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_light_bt\n"
				+ "channel final_light_bt, finalState_light_bt\n"
				+ "channel TurnOn, BatteryDepleted, TurnRecharge, turnRecharge, TurnSOS_, TurnOff, internal_light_bt : TR_ID_light_bt\n"
				+ "channel get_bateria_light_bt, set_bateria_light_bt: {0..100}\n"
				+ "\n"
				+ "MEMORY_light_bt(bateria) = get_bateria_light_bt!bateria -> MEMORY_light_bt(bateria) [] set_bateria_light_bt?x -> MEMORY_light_bt(x) [] (bateria > 0) & TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria == 0) & BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria < 95) & internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria > 0) & TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria<=20) & turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria>=25) & TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria > 0) & TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ " [] (bateria > 10) & internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> MEMORY_light_bt(bateria)\n"
				+ "\n"
				+ "State_st_Recharge_light_bt = enter.st_Recharge_light_bt -> State_st_Recharge_Entry_light_bt\n"
				+ "State_st_Recharge_Entry_light_bt = EntryProc(st_Recharge_light_bt)\n"
				+ "State_st_Recharge_Do_light_bt = ((activateTr.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c -> SKIP ||| activateTr.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c -> SKIP); DoProc(st_Recharge_light_bt)) /\\ exit.st_Recharge_light_bt -> State_st_Recharge_Exit_light_bt\n"
				+ "State_st_Recharge_Exit_light_bt= ExitProc(st_Recharge_light_bt)\n"
				+ "\n"
				+ "State_st_On_light_bt(init) = (init & enter.st_On_light_bt-> State_st_On_Entry_light_bt) [] (not(init) & State_st_On_Entry_light_bt)\n"
				+ "State_st_On_Entry_light_bt = EntryProc(st_On_light_bt)\n"
				+ "State_st_On_Do_light_bt = ((activateTr.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> SKIP ||| activateTr.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> SKIP ||| activateTr.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> SKIP ||| activateTr.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> SKIP ||| activateTr.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> SKIP); DoProc(st_On_light_bt)) /\\ exit.st_On_light_bt -> State_st_On_Exit_light_bt\n"
				+ "State_st_On_Exit_light_bt= ExitProc(st_On_light_bt)\n"
				+ "\n"
				+ "State_st_Off_light_bt = enter.st_Off_light_bt -> State_st_Off_Entry_light_bt\n"
				+ "State_st_Off_Entry_light_bt = EntryProc(st_Off_light_bt)\n"
				+ "State_st_Off_Do_light_bt = ((activateTr.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c -> SKIP ||| activateTr.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c -> SKIP); DoProc(st_Off_light_bt)) /\\ exit.st_Off_light_bt -> State_st_Off_Exit_light_bt\n"
				+ "State_st_Off_Exit_light_bt= ExitProc(st_Off_light_bt)\n"
				+ "\n"
				+ "State_st_SOS_light_bt = enter.st_SOS_light_bt -> State_st_SOS_Entry_light_bt\n"
				+ "State_st_SOS_Entry_light_bt = EntryProc(st_SOS_light_bt)\n"
				+ "State_st_SOS_Do_light_bt = ((activateTr.tr_1oj_0c1aea76f28ac0dbed586ac2af18089c -> SKIP); DoProc(st_SOS_light_bt)) /\\ exit.st_SOS_light_bt -> State_st_SOS_Exit_light_bt\n"
				+ "State_st_SOS_Exit_light_bt= ExitProc(st_SOS_light_bt)\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Recharge_1ay_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c -> ((TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Recharge_light_bt -> exited.st_Recharge_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-1)) -> enter.st_On_light_bt -> Tr_Turn_st_On_BY_st_Recharge_1ay_0c1aea76f28ac0dbed586ac2af18089c) [] (internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_On_BY_st_Recharge_1ay_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> ((BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_Off_light_bt -> Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c) [] (internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> ((TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_Recharge_light_bt -> Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c) [] (BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c) [] (internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Recharge_1ex_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c -> ((internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Recharge_light_bt -> exited.st_Recharge_light_bt -> enter.st_Recharge_light_bt -> Tr_Turn_st_Recharge_BY_st_Recharge_1ex_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Recharge_BY_st_Recharge_1ex_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_Off_1fe_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c -> ((TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Off_light_bt -> exited.st_Off_light_bt -> enter.st_On_light_bt -> Tr_Turn_st_On_BY_st_Off_1fe_0c1aea76f28ac0dbed586ac2af18089c) [] (turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_On_BY_st_Off_1fe_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_Recharge_BY_st_Off_1fw_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c -> ((turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_Off_light_bt -> exited.st_Off_light_bt -> enter.st_Recharge_light_bt -> Tr_Turn_st_Recharge_BY_st_Off_1fw_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Recharge_BY_st_Off_1fw_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> ((TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_SOS_light_bt -> Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c) [] (BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c) [] (internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> ((TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_Off_light_bt -> Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c) [] (BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c) [] (internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_Off_BY_st_SOS_1oj_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1oj_0c1aea76f28ac0dbed586ac2af18089c -> ((TurnOff.tr_1oj_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_SOS_light_bt -> exited.st_SOS_light_bt -> enter.st_Off_light_bt -> Tr_Turn_st_Off_BY_st_SOS_1oj_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c = activateTr.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> ((internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c -> exit.st_On_light_bt -> exited.st_On_light_bt -> enter.st_On_light_bt -> Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c) [] (BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c) [] (TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c -> Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "EntryProc(st_Recharge_light_bt) = entry.st_Recharge_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(min(100,bateria+5)) -> State_st_Recharge_Do_light_bt\n"
				+ "EntryProc(st_On_light_bt) = entry.st_On_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-10)) -> State_st_On_Do_light_bt\n"
				+ "EntryProc(st_Off_light_bt) = entry.st_Off_light_bt -> State_st_Off_Do_light_bt\n"
				+ "EntryProc(st_SOS_light_bt) = entry.st_SOS_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-25)) -> State_st_SOS_Do_light_bt\n"
				+ "\n"
				+ "DoProc(st_Recharge_light_bt) = do.st_Recharge_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(min(100,bateria+5)) -> SKIP\n"
				+ "DoProc(st_On_light_bt) = do.st_On_light_bt -> get_bateria_light_bt?bateria -> set_bateria_light_bt!(max(0,bateria-10)) -> SKIP\n"
				+ "DoProc(st_Off_light_bt) = do.st_Off_light_bt -> SKIP\n"
				+ "DoProc(st_SOS_light_bt) = do.st_SOS_light_bt -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_Recharge_light_bt) = exited.st_Recharge_light_bt -> State_st_Recharge_light_bt\n"
				+ "ExitProc(st_On_light_bt) = exited.st_On_light_bt -> State_st_On_light_bt(true)\n"
				+ "ExitProc(st_Off_light_bt) = exited.st_Off_light_bt -> State_st_Off_light_bt\n"
				+ "ExitProc(st_SOS_light_bt) = exited.st_SOS_light_bt -> State_st_SOS_light_bt\n"
				+ "\n"
				+ "States_light_bt = (State_st_Recharge_light_bt ||| State_st_On_light_bt(false) ||| State_st_Off_light_bt ||| State_st_SOS_light_bt)\n"
				+ "Transitions_light_bt = ((Tr_Turn_st_Off_BY_st_SOS_1oj_0c1aea76f28ac0dbed586ac2af18089c) ||| (Tr_Turn_st_On_BY_st_Recharge_1ay_0c1aea76f28ac0dbed586ac2af18089c [|{|TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c|}|] Tr_Turn_st_Recharge_BY_st_Recharge_1ex_0c1aea76f28ac0dbed586ac2af18089c) ||| ((((Tr_Turn_st_Off_BY_st_On_1bh_0c1aea76f28ac0dbed586ac2af18089c [|{|BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c, TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c|}|] Tr_Turn_st_Recharge_BY_st_On_1ef_0c1aea76f28ac0dbed586ac2af18089c) [|{|BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c, TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c|}|] Tr_Turn_st_SOS_BY_st_On_1ge_0c1aea76f28ac0dbed586ac2af18089c) [|{|BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c, TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c|}|] Tr_Turn_st_Off_BY_st_On_1l6_0c1aea76f28ac0dbed586ac2af18089c) [|{|BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, TurnRecharge.tr_1ef_0c1aea76f28ac0dbed586ac2af18089c, TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c|}|] Tr_Turn_st_On_BY_st_On_1qg_0c1aea76f28ac0dbed586ac2af18089c) ||| (Tr_Turn_st_On_BY_st_Off_1fe_0c1aea76f28ac0dbed586ac2af18089c [|{|TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c, turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c|}|] Tr_Turn_st_Recharge_BY_st_Off_1fw_0c1aea76f28ac0dbed586ac2af18089c))\n"
				+ "\n"
				+ "StartSync_light_bt =((States_light_bt [|{|activateTr, exit, exited, enter|}|] Transitions_light_bt)[|{|get_bateria_light_bt, set_bateria_light_bt, TurnOn.tr_1ay_0c1aea76f28ac0dbed586ac2af18089c, BatteryDepleted.tr_1bh_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1ex_0c1aea76f28ac0dbed586ac2af18089c, TurnOn.tr_1fe_0c1aea76f28ac0dbed586ac2af18089c, turnRecharge.tr_1fw_0c1aea76f28ac0dbed586ac2af18089c, TurnSOS_.tr_1ge_0c1aea76f28ac0dbed586ac2af18089c, TurnOff.tr_1l6_0c1aea76f28ac0dbed586ac2af18089c, internal_light_bt.tr_1qg_0c1aea76f28ac0dbed586ac2af18089c|}|] MEMORY_light_bt(20))/\\ finalState_light_bt -> SKIP \n"
				+ "\n"
				+ "FinalProcess_light_bt = final_light_bt-> finalState_light_bt-> SKIP\n"
				+ "\n"
				+ "Start_light_bt = (StartSync_light_bt [|{|final_light_bt,finalState_light_bt|}|] FinalProcess_light_bt)");
		assertEquals(expected.toString(), actual);
	}

	//st7
	@Ignore
	@Test
	public void TestSt7() throws ParsingException{
		String actual = parser7.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 | tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 | tr_3do_66aebfeb7e090212b92b2faa15d2aab0 | tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 | tr_58_a198ebf31eae0bfb62967205c60511a7\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel Trigger, TR2, TR3, TR1, TR5 : TR_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (false) & Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==3) & TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (true) & TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (1==2) & TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (not(false) and not(1==3)and not(1==2)) & TR5.tr_58_a198ebf31eae0bfb62967205c60511a7 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0(init) = (init & enter.st_State0_StatemachineDiagram0-> State_st_State0_Entry_StatemachineDiagram0) [] (not(init) & State_st_State0_Entry_StatemachineDiagram0)\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = ((activateTr.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> SKIP ||| activateTr.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> SKIP ||| activateTr.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> SKIP ||| activateTr.tr_58_a198ebf31eae0bfb62967205c60511a7 -> SKIP); DoProc(st_State0_StatemachineDiagram0)) /\\ exit.st_State0_StatemachineDiagram0 -> State_st_State0_Exit_StatemachineDiagram0\n"
				+ "State_st_State0_Exit_StatemachineDiagram0= ExitProc(st_State0_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = enter.st_State1_StatemachineDiagram0 -> State_st_State1_Entry_StatemachineDiagram0\n"
				+ "State_st_State1_Entry_StatemachineDiagram0 = EntryProc(st_State1_StatemachineDiagram0)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((activateTr.tr_3do_66aebfeb7e090212b92b2faa15d2aab0 -> SKIP); DoProc(st_State1_StatemachineDiagram0)) /\\ exit.st_State1_StatemachineDiagram0 -> State_st_State1_Exit_StatemachineDiagram0\n"
				+ "State_st_State1_Exit_StatemachineDiagram0= ExitProc(st_State1_StatemachineDiagram0)\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0 = activateTr.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> ((Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0) [] (TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0) [] (TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0) [] (TR5.tr_58_a198ebf31eae0bfb62967205c60511a7 -> Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0 = activateTr.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> ((TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0) [] (Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0) [] (TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0) [] (TR5.tr_58_a198ebf31eae0bfb62967205c60511a7 -> Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State1_3do_66aebfeb7e090212b92b2faa15d2aab0 = activateTr.tr_3do_66aebfeb7e090212b92b2faa15d2aab0 -> ((TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0 -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_State1_3do_66aebfeb7e090212b92b2faa15d2aab0))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 = activateTr.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> ((TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2) [] (Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2) [] (TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2) [] (TR5.tr_58_a198ebf31eae0bfb62967205c60511a7 -> Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7 = activateTr.tr_58_a198ebf31eae0bfb62967205c60511a7 -> ((TR5.tr_58_a198ebf31eae0bfb62967205c60511a7 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7) [] (Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0 -> Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7) [] (TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0 -> Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7) [] (TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2 -> Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7))\n"
				+ "\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State1_StatemachineDiagram0) = entry.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State1_StatemachineDiagram0) = do.st_State1_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State0_StatemachineDiagram0) = exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0(true)\n"
				+ "ExitProc(st_State1_StatemachineDiagram0) = exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = (State_st_State0_StatemachineDiagram0(false) ||| State_st_State1_StatemachineDiagram0)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_FinalState0_BY_st_State1_3do_66aebfeb7e090212b92b2faa15d2aab0) ||| (((Tr_Turn_st_State1_BY_st_State0_1uj_66aebfeb7e090212b92b2faa15d2aab0 [|{|Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0, TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0, TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2, TR5.tr_58_a198ebf31eae0bfb62967205c60511a7|}|] Tr_Turn_st_FinalState0_BY_st_State0_2jj_66aebfeb7e090212b92b2faa15d2aab0) [|{|Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0, TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0, TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2, TR5.tr_58_a198ebf31eae0bfb62967205c60511a7|}|] Tr_Turn_st_FinalState0_BY_st_State0_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2) [|{|Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0, TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0, TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2, TR5.tr_58_a198ebf31eae0bfb62967205c60511a7|}|] Tr_Turn_st_FinalState0_BY_st_State0_58_a198ebf31eae0bfb62967205c60511a7))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [|{|activateTr, exit, exited, enter|}|] Transitions_StatemachineDiagram0)[|{|Trigger.tr_1uj_66aebfeb7e090212b92b2faa15d2aab0, TR2.tr_2jj_66aebfeb7e090212b92b2faa15d2aab0, TR3.tr_3do_66aebfeb7e090212b92b2faa15d2aab0, TR1.tr_2a_9489d6c7a0f652bc5a7d16c1f9cc45a2, TR5.tr_58_a198ebf31eae0bfb62967205c60511a7|}|] MEMORY_StatemachineDiagram0)/\\ finalState_StatemachineDiagram0 -> SKIP \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0)");
		assertEquals(expected.toString(), actual);
	}

	//ChoiceJunction1
	@Ignore
	@Test
	public void TestChoiceJunction1() throws ParsingException{
		String actual = parser8.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1_StatemachineDiagram0 | st_State2_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0 | st_Choice0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_13x_ef1c450fd9e597ffdd79b472efdec02e | tr_1n3_ef1c450fd9e597ffdd79b472efdec02e | tr_26d_ef1c450fd9e597ffdd79b472efdec02e | tr_33t_ef1c450fd9e597ffdd79b472efdec02e | tr_3tk_ef1c450fd9e597ffdd79b472efdec02e | tr_y4_9ade7c9c747f809615cb5e53e140113e | tr_dy_f41343e1325a6efb4e19d10984ddbe81\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel TR1, TR3, TR2, TR5, TR4, TR6, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (1==1) & TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e -> MEMORY_StatemachineDiagram0\n"
				+ " [] (3==3) & TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e -> MEMORY_StatemachineDiagram0\n"
				+ " [] (true) & internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0 = enter.st_State0_StatemachineDiagram0 -> State_st_State0_Entry_StatemachineDiagram0\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = ((activateTr.tr_13x_ef1c450fd9e597ffdd79b472efdec02e -> SKIP ||| activateTr.tr_26d_ef1c450fd9e597ffdd79b472efdec02e -> SKIP); DoProc(st_State0_StatemachineDiagram0)) /\\ exit.st_State0_StatemachineDiagram0 -> State_st_State0_Exit_StatemachineDiagram0\n"
				+ "State_st_State0_Exit_StatemachineDiagram0= ExitProc(st_State0_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State1_StatemachineDiagram0 = enter.st_State1_StatemachineDiagram0 -> State_st_State1_Entry_StatemachineDiagram0\n"
				+ "State_st_State1_Entry_StatemachineDiagram0 = EntryProc(st_State1_StatemachineDiagram0)\n"
				+ "State_st_State1_Do_StatemachineDiagram0 = ((activateTr.tr_1n3_ef1c450fd9e597ffdd79b472efdec02e -> SKIP); DoProc(st_State1_StatemachineDiagram0)) /\\ exit.st_State1_StatemachineDiagram0 -> State_st_State1_Exit_StatemachineDiagram0\n"
				+ "State_st_State1_Exit_StatemachineDiagram0= ExitProc(st_State1_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State2_StatemachineDiagram0 = enter.st_State2_StatemachineDiagram0 -> State_st_State2_Entry_StatemachineDiagram0\n"
				+ "State_st_State2_Entry_StatemachineDiagram0 = EntryProc(st_State2_StatemachineDiagram0)\n"
				+ "State_st_State2_Do_StatemachineDiagram0 = ((activateTr.tr_3tk_ef1c450fd9e597ffdd79b472efdec02e -> SKIP); DoProc(st_State2_StatemachineDiagram0)) /\\ exit.st_State2_StatemachineDiagram0 -> State_st_State2_Exit_StatemachineDiagram0\n"
				+ "State_st_State2_Exit_StatemachineDiagram0= ExitProc(st_State2_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Sync_StatemachineDiagram0\n"
				+ "State_st_JunctionPoint0_Sync_StatemachineDiagram0 = ((activateTr.tr_33t_ef1c450fd9e597ffdd79b472efdec02e -> SKIP ||| activateTr.tr_dy_f41343e1325a6efb4e19d10984ddbe81 -> SKIP)) /\\ exit.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_Choice0_StatemachineDiagram0(init) = (init & enter.st_Choice0_StatemachineDiagram0-> State_st_Choice0_Sync_StatemachineDiagram0) [] (not(init) & State_st_Choice0_Sync_StatemachineDiagram0)\n"
				+ "State_st_Choice0_Sync_StatemachineDiagram0 = ((activateTr.tr_y4_9ade7c9c747f809615cb5e53e140113e -> SKIP)) /\\ exit.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_StatemachineDiagram0(true)\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State0_13x_ef1c450fd9e597ffdd79b472efdec02e = activateTr.tr_13x_ef1c450fd9e597ffdd79b472efdec02e -> ((TR1.tr_13x_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> Tr_Turn_st_JunctionPoint0_BY_st_State0_13x_ef1c450fd9e597ffdd79b472efdec02e) [] (TR2.tr_26d_ef1c450fd9e597ffdd79b472efdec02e -> Tr_Turn_st_JunctionPoint0_BY_st_State0_13x_ef1c450fd9e597ffdd79b472efdec02e))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State1_1n3_ef1c450fd9e597ffdd79b472efdec02e = activateTr.tr_1n3_ef1c450fd9e597ffdd79b472efdec02e -> ((TR3.tr_1n3_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State1_StatemachineDiagram0 -> exited.st_State1_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> Tr_Turn_st_JunctionPoint0_BY_st_State1_1n3_ef1c450fd9e597ffdd79b472efdec02e))\n"
				+ "\n"
				+ "Tr_Turn_st_State1_BY_st_State0_26d_ef1c450fd9e597ffdd79b472efdec02e = activateTr.tr_26d_ef1c450fd9e597ffdd79b472efdec02e -> ((TR2.tr_26d_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1_StatemachineDiagram0 -> Tr_Turn_st_State1_BY_st_State0_26d_ef1c450fd9e597ffdd79b472efdec02e) [] (TR1.tr_13x_ef1c450fd9e597ffdd79b472efdec02e -> Tr_Turn_st_State1_BY_st_State0_26d_ef1c450fd9e597ffdd79b472efdec02e))\n"
				+ "\n"
				+ "Tr_Turn_st_State2_BY_st_JunctionPoint0_33t_ef1c450fd9e597ffdd79b472efdec02e = activateTr.tr_33t_ef1c450fd9e597ffdd79b472efdec02e -> ((TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_State2_StatemachineDiagram0 -> Tr_Turn_st_State2_BY_st_JunctionPoint0_33t_ef1c450fd9e597ffdd79b472efdec02e) [] (internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81 -> Tr_Turn_st_State2_BY_st_JunctionPoint0_33t_ef1c450fd9e597ffdd79b472efdec02e))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_State2_3tk_ef1c450fd9e597ffdd79b472efdec02e = activateTr.tr_3tk_ef1c450fd9e597ffdd79b472efdec02e -> ((TR4.tr_3tk_ef1c450fd9e597ffdd79b472efdec02e -> exit.st_State2_StatemachineDiagram0 -> exited.st_State2_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_State2_3tk_ef1c450fd9e597ffdd79b472efdec02e))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_Choice0_y4_9ade7c9c747f809615cb5e53e140113e = activateTr.tr_y4_9ade7c9c747f809615cb5e53e140113e -> ((TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e -> exit.st_Choice0_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> Tr_Turn_st_State0_BY_st_Choice0_y4_9ade7c9c747f809615cb5e53e140113e))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_dy_f41343e1325a6efb4e19d10984ddbe81 = activateTr.tr_dy_f41343e1325a6efb4e19d10984ddbe81 -> ((internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_dy_f41343e1325a6efb4e19d10984ddbe81) [] (TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e -> Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_dy_f41343e1325a6efb4e19d10984ddbe81))\n"
				+ "\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State1_StatemachineDiagram0) = entry.st_State1_StatemachineDiagram0 -> State_st_State1_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State2_StatemachineDiagram0) = entry.st_State2_StatemachineDiagram0 -> State_st_State2_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State1_StatemachineDiagram0) = do.st_State1_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State2_StatemachineDiagram0) = do.st_State2_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State0_StatemachineDiagram0) = exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0\n"
				+ "ExitProc(st_State1_StatemachineDiagram0) = exited.st_State1_StatemachineDiagram0 -> State_st_State1_StatemachineDiagram0\n"
				+ "ExitProc(st_State2_StatemachineDiagram0) = exited.st_State2_StatemachineDiagram0 -> State_st_State2_StatemachineDiagram0\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = (State_st_State0_StatemachineDiagram0 ||| State_st_State1_StatemachineDiagram0 ||| State_st_State2_StatemachineDiagram0 ||| State_st_JunctionPoint0_StatemachineDiagram0 ||| State_st_Choice0_StatemachineDiagram0(false))\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_JunctionPoint0_BY_st_State1_1n3_ef1c450fd9e597ffdd79b472efdec02e) ||| (Tr_Turn_st_FinalState0_BY_st_State2_3tk_ef1c450fd9e597ffdd79b472efdec02e) ||| (Tr_Turn_st_State0_BY_st_Choice0_y4_9ade7c9c747f809615cb5e53e140113e) ||| (Tr_Turn_st_JunctionPoint0_BY_st_State0_13x_ef1c450fd9e597ffdd79b472efdec02e [|{|TR1.tr_13x_ef1c450fd9e597ffdd79b472efdec02e, TR2.tr_26d_ef1c450fd9e597ffdd79b472efdec02e|}|] Tr_Turn_st_State1_BY_st_State0_26d_ef1c450fd9e597ffdd79b472efdec02e) ||| (Tr_Turn_st_State2_BY_st_JunctionPoint0_33t_ef1c450fd9e597ffdd79b472efdec02e [|{|TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e, internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81|}|] Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_dy_f41343e1325a6efb4e19d10984ddbe81))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [|{|activateTr, exit, exited, enter|}|] Transitions_StatemachineDiagram0)[|{|TR5.tr_33t_ef1c450fd9e597ffdd79b472efdec02e, TR6.tr_y4_9ade7c9c747f809615cb5e53e140113e, internal_StatemachineDiagram0.tr_dy_f41343e1325a6efb4e19d10984ddbe81|}|] MEMORY_StatemachineDiagram0)/\\ finalState_StatemachineDiagram0 -> SKIP \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0)");
		assertEquals(expected.toString(), actual);

	}
	//ChoiceJunction2
	@Ignore
	@Test
	public void TestChoiceJunction2() throws ParsingException{
		String actual = parser9.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_StatemachineDiagram0 = st_State0_StatemachineDiagram0 | st_State1__StatemachineDiagram0 | st_State3_StatemachineDiagram0 | st_State4_StatemachineDiagram0 | st_State5_StatemachineDiagram0 | st_Choice0_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1ua_179c9d9956518cdbb98c83cd532dd4bf | tr_5ta_179c9d9956518cdbb98c83cd532dd4bf | tr_7qf_179c9d9956518cdbb98c83cd532dd4bf | tr_8ek_179c9d9956518cdbb98c83cd532dd4bf | tr_9mr_179c9d9956518cdbb98c83cd532dd4bf | tr_ak6_179c9d9956518cdbb98c83cd532dd4bf | tr_bm7_179c9d9956518cdbb98c83cd532dd4bf | tr_cau_179c9d9956518cdbb98c83cd532dd4bf | tr_d49_179c9d9956518cdbb98c83cd532dd4bf | tr_ic_148a61f56aa3873257e76f90ba00d072 | tr_52_137e349f6bbd43344cbe2ec1b17a62b1\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel TR1, TR2, JTR1, JTR3, JTR2, TRFINAL, TRInit, Loop, internal_StatemachineDiagram0 : TR_ID_StatemachineDiagram0\n"
				+ "channel get_count_StatemachineDiagram0, set_count_StatemachineDiagram0: {0..10}\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0(count) = get_count_StatemachineDiagram0!count -> MEMORY_StatemachineDiagram0(count) [] set_count_StatemachineDiagram0?x -> MEMORY_StatemachineDiagram0(x) [] (count < 5) & internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> MEMORY_StatemachineDiagram0(count)\n"
				+ " [] (count >5) & internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> MEMORY_StatemachineDiagram0(count)\n"
				+ "[] (not(count < 5) and not(count >5)) & internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> MEMORY_StatemachineDiagram0(count)\n"
				+ "\n"
				+ "State_st_State0_StatemachineDiagram0(init) = (init & enter.st_State0_StatemachineDiagram0-> State_st_State0_Entry_StatemachineDiagram0) [] (not(init) & State_st_State0_Entry_StatemachineDiagram0)\n"
				+ "State_st_State0_Entry_StatemachineDiagram0 = EntryProc(st_State0_StatemachineDiagram0)\n"
				+ "State_st_State0_Do_StatemachineDiagram0 = ((activateTr.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf -> SKIP ||| activateTr.tr_52_137e349f6bbd43344cbe2ec1b17a62b1 -> SKIP); DoProc(st_State0_StatemachineDiagram0)) /\\ exit.st_State0_StatemachineDiagram0 -> State_st_State0_Exit_StatemachineDiagram0\n"
				+ "State_st_State0_Exit_StatemachineDiagram0= ExitProc(st_State0_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State1__StatemachineDiagram0 = enter.st_State1__StatemachineDiagram0 -> State_st_State1__Entry_StatemachineDiagram0\n"
				+ "State_st_State1__Entry_StatemachineDiagram0 = EntryProc(st_State1__StatemachineDiagram0)\n"
				+ "State_st_State1__Do_StatemachineDiagram0 = ((activateTr.tr_5ta_179c9d9956518cdbb98c83cd532dd4bf -> SKIP); DoProc(st_State1__StatemachineDiagram0)) /\\ exit.st_State1__StatemachineDiagram0 -> State_st_State1__Exit_StatemachineDiagram0\n"
				+ "State_st_State1__Exit_StatemachineDiagram0= ExitProc(st_State1__StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State3_StatemachineDiagram0 = enter.st_State3_StatemachineDiagram0 -> State_st_State3_Entry_StatemachineDiagram0\n"
				+ "State_st_State3_Entry_StatemachineDiagram0 = EntryProc(st_State3_StatemachineDiagram0)\n"
				+ "State_st_State3_Do_StatemachineDiagram0 = ((activateTr.tr_ak6_179c9d9956518cdbb98c83cd532dd4bf -> SKIP); DoProc(st_State3_StatemachineDiagram0)) /\\ exit.st_State3_StatemachineDiagram0 -> State_st_State3_Exit_StatemachineDiagram0\n"
				+ "State_st_State3_Exit_StatemachineDiagram0= ExitProc(st_State3_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State4_StatemachineDiagram0 = enter.st_State4_StatemachineDiagram0 -> State_st_State4_Entry_StatemachineDiagram0\n"
				+ "State_st_State4_Entry_StatemachineDiagram0 = EntryProc(st_State4_StatemachineDiagram0)\n"
				+ "State_st_State4_Do_StatemachineDiagram0 = ((activateTr.tr_cau_179c9d9956518cdbb98c83cd532dd4bf -> SKIP); DoProc(st_State4_StatemachineDiagram0)) /\\ exit.st_State4_StatemachineDiagram0 -> State_st_State4_Exit_StatemachineDiagram0\n"
				+ "State_st_State4_Exit_StatemachineDiagram0= ExitProc(st_State4_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_State5_StatemachineDiagram0 = enter.st_State5_StatemachineDiagram0 -> State_st_State5_Entry_StatemachineDiagram0\n"
				+ "State_st_State5_Entry_StatemachineDiagram0 = EntryProc(st_State5_StatemachineDiagram0)\n"
				+ "State_st_State5_Do_StatemachineDiagram0 = ((activateTr.tr_bm7_179c9d9956518cdbb98c83cd532dd4bf -> SKIP); DoProc(st_State5_StatemachineDiagram0)) /\\ exit.st_State5_StatemachineDiagram0 -> State_st_State5_Exit_StatemachineDiagram0\n"
				+ "State_st_State5_Exit_StatemachineDiagram0= ExitProc(st_State5_StatemachineDiagram0)\n"
				+ "\n"
				+ "State_st_Choice0_StatemachineDiagram0 = enter.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_Sync_StatemachineDiagram0\n"
				+ "State_st_Choice0_Sync_StatemachineDiagram0 = ((activateTr.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> SKIP ||| activateTr.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> SKIP ||| activateTr.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> SKIP)) /\\ exit.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0 = enter.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_Sync_StatemachineDiagram0\n"
				+ "State_st_JunctionPoint0_Sync_StatemachineDiagram0 = ((activateTr.tr_d49_179c9d9956518cdbb98c83cd532dd4bf -> SKIP ||| activateTr.tr_ic_148a61f56aa3873257e76f90ba00d072 -> SKIP)) /\\ exit.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0\n"
				+ "\n"
				+ "Tr_Turn_st_State1__BY_st_State0_1ua_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf -> ((TR1.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State1__StatemachineDiagram0 -> Tr_Turn_st_State1__BY_st_State0_1ua_179c9d9956518cdbb98c83cd532dd4bf) [] (Loop.tr_52_137e349f6bbd43344cbe2ec1b17a62b1 -> Tr_Turn_st_State1__BY_st_State0_1ua_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_Choice0_BY_st_State1__5ta_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_5ta_179c9d9956518cdbb98c83cd532dd4bf -> ((TR2.tr_5ta_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State1__StatemachineDiagram0 -> exited.st_State1__StatemachineDiagram0 -> enter.st_Choice0_StatemachineDiagram0 -> Tr_Turn_st_Choice0_BY_st_State1__5ta_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_State3_BY_st_Choice0_7qf_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> ((internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_Choice0_StatemachineDiagram0 -> enter.st_State3_StatemachineDiagram0 -> Tr_Turn_st_State3_BY_st_Choice0_7qf_179c9d9956518cdbb98c83cd532dd4bf) [] (internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State3_BY_st_Choice0_7qf_179c9d9956518cdbb98c83cd532dd4bf) [] (internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State3_BY_st_Choice0_7qf_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_State4_BY_st_Choice0_8ek_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> ((internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_Choice0_StatemachineDiagram0 -> enter.st_State4_StatemachineDiagram0 -> Tr_Turn_st_State4_BY_st_Choice0_8ek_179c9d9956518cdbb98c83cd532dd4bf) [] (internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State4_BY_st_Choice0_8ek_179c9d9956518cdbb98c83cd532dd4bf) [] (internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State4_BY_st_Choice0_8ek_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_State5_BY_st_Choice0_9mr_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> ((internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_Choice0_StatemachineDiagram0 -> enter.st_State5_StatemachineDiagram0 -> Tr_Turn_st_State5_BY_st_Choice0_9mr_179c9d9956518cdbb98c83cd532dd4bf) [] (internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State5_BY_st_Choice0_9mr_179c9d9956518cdbb98c83cd532dd4bf) [] (internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State5_BY_st_Choice0_9mr_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State3_ak6_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_ak6_179c9d9956518cdbb98c83cd532dd4bf -> ((JTR1.tr_ak6_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State3_StatemachineDiagram0 -> exited.st_State3_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> Tr_Turn_st_JunctionPoint0_BY_st_State3_ak6_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State5_bm7_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_bm7_179c9d9956518cdbb98c83cd532dd4bf -> ((JTR3.tr_bm7_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State5_StatemachineDiagram0 -> exited.st_State5_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> Tr_Turn_st_JunctionPoint0_BY_st_State5_bm7_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_JunctionPoint0_BY_st_State4_cau_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_cau_179c9d9956518cdbb98c83cd532dd4bf -> ((JTR2.tr_cau_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_State4_StatemachineDiagram0 -> exited.st_State4_StatemachineDiagram0 -> enter.st_JunctionPoint0_StatemachineDiagram0 -> Tr_Turn_st_JunctionPoint0_BY_st_State4_cau_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_d49_179c9d9956518cdbb98c83cd532dd4bf = activateTr.tr_d49_179c9d9956518cdbb98c83cd532dd4bf -> ((TRFINAL.tr_d49_179c9d9956518cdbb98c83cd532dd4bf -> exit.st_JunctionPoint0_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_d49_179c9d9956518cdbb98c83cd532dd4bf) [] (TRInit.tr_ic_148a61f56aa3873257e76f90ba00d072 -> Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_d49_179c9d9956518cdbb98c83cd532dd4bf))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_JunctionPoint0_ic_148a61f56aa3873257e76f90ba00d072 = activateTr.tr_ic_148a61f56aa3873257e76f90ba00d072 -> ((TRInit.tr_ic_148a61f56aa3873257e76f90ba00d072 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> set_count_StatemachineDiagram0!(0) -> enter.st_State0_StatemachineDiagram0 -> Tr_Turn_st_State0_BY_st_JunctionPoint0_ic_148a61f56aa3873257e76f90ba00d072) [] (TRFINAL.tr_d49_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State0_BY_st_JunctionPoint0_ic_148a61f56aa3873257e76f90ba00d072))\n"
				+ "\n"
				+ "Tr_Turn_st_State0_BY_st_State0_52_137e349f6bbd43344cbe2ec1b17a62b1 = activateTr.tr_52_137e349f6bbd43344cbe2ec1b17a62b1 -> ((Loop.tr_52_137e349f6bbd43344cbe2ec1b17a62b1 -> exit.st_State0_StatemachineDiagram0 -> exited.st_State0_StatemachineDiagram0 -> enter.st_State0_StatemachineDiagram0 -> Tr_Turn_st_State0_BY_st_State0_52_137e349f6bbd43344cbe2ec1b17a62b1) [] (TR1.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf -> Tr_Turn_st_State0_BY_st_State0_52_137e349f6bbd43344cbe2ec1b17a62b1))\n"
				+ "\n"
				+ "EntryProc(st_State0_StatemachineDiagram0) = entry.st_State0_StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(min(count+1,10)) -> State_st_State0_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State1__StatemachineDiagram0) = entry.st_State1__StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(max(count-1,0)) -> State_st_State1__Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State3_StatemachineDiagram0) = entry.st_State3_StatemachineDiagram0 -> set_count_StatemachineDiagram0!(0) -> State_st_State3_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State4_StatemachineDiagram0) = entry.st_State4_StatemachineDiagram0 -> set_count_StatemachineDiagram0!(10) -> State_st_State4_Do_StatemachineDiagram0\n"
				+ "EntryProc(st_State5_StatemachineDiagram0) = entry.st_State5_StatemachineDiagram0 -> State_st_State5_Do_StatemachineDiagram0\n"
				+ "\n"
				+ "DoProc(st_State0_StatemachineDiagram0) = do.st_State0_StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(min(10,count+1)) -> SKIP\n"
				+ "DoProc(st_State1__StatemachineDiagram0) = do.st_State1__StatemachineDiagram0 -> get_count_StatemachineDiagram0?count -> set_count_StatemachineDiagram0!(max(count-1,0)) -> SKIP\n"
				+ "DoProc(st_State3_StatemachineDiagram0) = do.st_State3_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State4_StatemachineDiagram0) = do.st_State4_StatemachineDiagram0 -> SKIP\n"
				+ "DoProc(st_State5_StatemachineDiagram0) = do.st_State5_StatemachineDiagram0 -> SKIP\n"
				+ "\n"
				+ "ExitProc(st_State0_StatemachineDiagram0) = exited.st_State0_StatemachineDiagram0 -> State_st_State0_StatemachineDiagram0(true)\n"
				+ "ExitProc(st_State1__StatemachineDiagram0) = exited.st_State1__StatemachineDiagram0 -> State_st_State1__StatemachineDiagram0\n"
				+ "ExitProc(st_State3_StatemachineDiagram0) = exited.st_State3_StatemachineDiagram0 -> State_st_State3_StatemachineDiagram0\n"
				+ "ExitProc(st_State4_StatemachineDiagram0) = exited.st_State4_StatemachineDiagram0 -> State_st_State4_StatemachineDiagram0\n"
				+ "ExitProc(st_State5_StatemachineDiagram0) = exited.st_State5_StatemachineDiagram0 -> State_st_State5_StatemachineDiagram0\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = (State_st_State0_StatemachineDiagram0(false) ||| State_st_State1__StatemachineDiagram0 ||| State_st_State3_StatemachineDiagram0 ||| State_st_State4_StatemachineDiagram0 ||| State_st_State5_StatemachineDiagram0 ||| State_st_Choice0_StatemachineDiagram0 ||| State_st_JunctionPoint0_StatemachineDiagram0)\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_Choice0_BY_st_State1__5ta_179c9d9956518cdbb98c83cd532dd4bf) ||| (Tr_Turn_st_JunctionPoint0_BY_st_State3_ak6_179c9d9956518cdbb98c83cd532dd4bf) ||| (Tr_Turn_st_JunctionPoint0_BY_st_State4_cau_179c9d9956518cdbb98c83cd532dd4bf) ||| (Tr_Turn_st_JunctionPoint0_BY_st_State5_bm7_179c9d9956518cdbb98c83cd532dd4bf) ||| (Tr_Turn_st_State1__BY_st_State0_1ua_179c9d9956518cdbb98c83cd532dd4bf [|{|TR1.tr_1ua_179c9d9956518cdbb98c83cd532dd4bf, Loop.tr_52_137e349f6bbd43344cbe2ec1b17a62b1|}|] Tr_Turn_st_State0_BY_st_State0_52_137e349f6bbd43344cbe2ec1b17a62b1) ||| ((Tr_Turn_st_State3_BY_st_Choice0_7qf_179c9d9956518cdbb98c83cd532dd4bf [|{|internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf|}|] Tr_Turn_st_State4_BY_st_Choice0_8ek_179c9d9956518cdbb98c83cd532dd4bf) [|{|internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf|}|] Tr_Turn_st_State5_BY_st_Choice0_9mr_179c9d9956518cdbb98c83cd532dd4bf) ||| (Tr_Turn_st_FinalState0_BY_st_JunctionPoint0_d49_179c9d9956518cdbb98c83cd532dd4bf [|{|TRFINAL.tr_d49_179c9d9956518cdbb98c83cd532dd4bf, TRInit.tr_ic_148a61f56aa3873257e76f90ba00d072|}|] Tr_Turn_st_State0_BY_st_JunctionPoint0_ic_148a61f56aa3873257e76f90ba00d072))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [|{|activateTr, exit, exited, enter|}|] Transitions_StatemachineDiagram0)[|{|get_count_StatemachineDiagram0, set_count_StatemachineDiagram0, internal_StatemachineDiagram0.tr_7qf_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_8ek_179c9d9956518cdbb98c83cd532dd4bf, internal_StatemachineDiagram0.tr_9mr_179c9d9956518cdbb98c83cd532dd4bf|}|] MEMORY_StatemachineDiagram0(0))/\\ finalState_StatemachineDiagram0 -> SKIP \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0)");
		assertEquals(expected.toString(), actual);
	}

	//ChoiceJunction3
	@Ignore
	@Test
	public void TestChoiceJunction3() throws ParsingException{
		String actual = parser10.parserStateMachine();
		StringBuffer expected = new StringBuffer();
		expected.append("datatype STATES_ID_StatemachineDiagram0 = st_Choice0_StatemachineDiagram0 | st_JunctionPoint0_StatemachineDiagram0\n"
				+ "datatype TR_ID_StatemachineDiagram0 = tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 | tr_23c_30e19f42c4f79ba4e7dd21cd84422a15\n"
				+ "\n"
				+ "channel activateTr: TR_ID_StatemachineDiagram0\n"
				+ "channel enter, do, entry, exit, exited: STATES_ID_StatemachineDiagram0\n"
				+ "channel final_StatemachineDiagram0, finalState_StatemachineDiagram0\n"
				+ "channel tr, t : TR_ID_StatemachineDiagram0\n"
				+ "\n"
				+ "MEMORY_StatemachineDiagram0 = (1==1) & tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 -> MEMORY_StatemachineDiagram0\n"
				+ " [] (true) & t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15 -> MEMORY_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_Choice0_StatemachineDiagram0 = enter.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_Sync_StatemachineDiagram0\n"
				+ "State_st_Choice0_Sync_StatemachineDiagram0 = ((activateTr.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15 -> SKIP)) /\\ exit.st_Choice0_StatemachineDiagram0 -> State_st_Choice0_StatemachineDiagram0\n"
				+ "\n"
				+ "State_st_JunctionPoint0_StatemachineDiagram0(init) = (init & enter.st_JunctionPoint0_StatemachineDiagram0-> State_st_JunctionPoint0_Sync_StatemachineDiagram0) [] (not(init) & State_st_JunctionPoint0_Sync_StatemachineDiagram0)\n"
				+ "State_st_JunctionPoint0_Sync_StatemachineDiagram0 = ((activateTr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 -> SKIP)) /\\ exit.st_JunctionPoint0_StatemachineDiagram0 -> State_st_JunctionPoint0_StatemachineDiagram0(true)\n"
				+ "\n"
				+ "Tr_Turn_st_Choice0_BY_st_JunctionPoint0_1m6_30e19f42c4f79ba4e7dd21cd84422a15 = activateTr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 -> ((tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15 -> exit.st_JunctionPoint0_StatemachineDiagram0 -> enter.st_Choice0_StatemachineDiagram0 -> Tr_Turn_st_Choice0_BY_st_JunctionPoint0_1m6_30e19f42c4f79ba4e7dd21cd84422a15))\n"
				+ "\n"
				+ "Tr_Turn_st_FinalState0_BY_st_Choice0_23c_30e19f42c4f79ba4e7dd21cd84422a15 = activateTr.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15 -> ((t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15 -> exit.st_Choice0_StatemachineDiagram0 -> final_StatemachineDiagram0-> Tr_Turn_st_FinalState0_BY_st_Choice0_23c_30e19f42c4f79ba4e7dd21cd84422a15))\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "\n"
				+ "States_StatemachineDiagram0 = (State_st_Choice0_StatemachineDiagram0 ||| State_st_JunctionPoint0_StatemachineDiagram0(false))\n"
				+ "Transitions_StatemachineDiagram0 = ((Tr_Turn_st_FinalState0_BY_st_Choice0_23c_30e19f42c4f79ba4e7dd21cd84422a15) ||| (Tr_Turn_st_Choice0_BY_st_JunctionPoint0_1m6_30e19f42c4f79ba4e7dd21cd84422a15))\n"
				+ "\n"
				+ "StartSync_StatemachineDiagram0 =((States_StatemachineDiagram0 [|{|activateTr, exit, exited, enter|}|] Transitions_StatemachineDiagram0)[|{|tr.tr_1m6_30e19f42c4f79ba4e7dd21cd84422a15, t.tr_23c_30e19f42c4f79ba4e7dd21cd84422a15|}|] MEMORY_StatemachineDiagram0)/\\ finalState_StatemachineDiagram0 -> SKIP \n"
				+ "\n"
				+ "FinalProcess_StatemachineDiagram0 = final_StatemachineDiagram0-> finalState_StatemachineDiagram0-> SKIP\n"
				+ "\n"
				+ "Start_StatemachineDiagram0 = (StartSync_StatemachineDiagram0 [|{|final_StatemachineDiagram0,finalState_StatemachineDiagram0|}|] FinalProcess_StatemachineDiagram0)");
		assertEquals(expected.toString(), actual);

	}
}
