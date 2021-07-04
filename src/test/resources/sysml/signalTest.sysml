import ScalarValues::*;

action ProductionPlan (in x : Integer) {
	doc /* Integer={0..1} */
	fork fork1;
	bind ProductionPlan::x = fork1;
	flow fork1 to communication1::x;
	flow fork1 to communication2::x;
	flow fork1 to inicialization::x;
	action inicialization : Inicialization (in x : Integer);
	succession inicialization then step1;
	action communication1 : Communication (in x : Integer);
	succession communication1 then join1;
	action communication2 : Communication (in x : Integer);
	succession communication2 then join1;
	action step1 : Process;
	succession step1 then step2;
	action step2 : Process;
	succession step2 then join1;
	join join1;
	succession join1 then done;	
}

action def acceptFirstCommunication;
action def acceptSecondCommunication;

action def Communication (in x : Integer) {	
	doc /* Integer={0..1} */	
	bind Communication::x = checkData::x;
	action checkData (in x : Integer);
	succession checkData then acceptSecondCommunication;
	action acceptSecondCommunication accept x : Integer;
	action sendFirstCommunication send acceptSecondCommunication::x to acceptFirstCommunication;
	succession sendFirstCommunication then saveData;
	action saveData;
	succession saveData then done;	
}

action def Inicialization (in x : Integer) {
	doc /* Integer={0..1} */
	bind Inicialization::x = inicializeProduction::x;
	action inicializeProduction (in x : Integer);
	succession inicializeProduction then done;
}

action def Process {
	doc /* Integer={0..1} */
	first start;
	succession start then acceptFirstCommunication;	
	action acceptFirstCommunication accept x : Integer;
	action sendSecondCommunication send acceptFirstCommunication::x to acceptSecondCommunication;
	succession sendSecondCommunication then done;
}