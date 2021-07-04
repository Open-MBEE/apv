action ProductionPlan {
	first start;
	succession start then fork1;
	fork fork1;
	succession fork1 then comunication1;
	succession fork1 then comunication2;
	succession fork1 then process;
	action comunication1 : Comunication;
	succession comunication1 then join1;
	succession comunication1 then process;
	action comunication2 : Comunication;
	succession comunication2 then join1;
	succession comunication2 then process;
	action process : Process;
	succession process then finalization;
	action finalization : Finalization;
	succession finalization then join1;
	join join1;
	succession join1 then done;	
}

action def Comunication {
	first start;
	succession start then sendCommunication;	
	action sendCommunication;
	succession sendCommunication then done;	
}

action def Process {
	first start;
	succession start then startProcess;
	action startProcess;
	succession startProcess then endProcess;
	action endProcess;
	succession endProcess then done;	
}

action def Finalization {
	first start;
	succession start then finishProduction;
	action finishProduction;
	succession finishProduction then done;	
}