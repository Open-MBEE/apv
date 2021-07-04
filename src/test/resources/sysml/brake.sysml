import ScalarValues::*;

attribute def BrakePressure;

action brake {

	doc /* Real={0..1}; BrakePressure={0..1} */

	first start;
	succession start then TurnOn;
	action TurnOn;	
	succession TurnOn then forkNode;
	fork forkNode;
	succession forkNode then monitorBrakePedal;
	succession forkNode then monitorTraction;
	succession forkNode then braking;	
	action monitorBrakePedal (out pressure : BrakePressure) {
		language "Alf"
		/* pressure = 1; */	
	}
	succession monitorBrakePedal then joinNode;	
	flow monitorBrakePedal::pressure to braking::brakePressure;
	action monitorTraction (out modulationFrequency : Real){
		language "Alf"
		/* modulationFrequency = 2; */	
	}
	succession monitorTraction then joinNode;
	flow monitorTraction::modulationFrequency to braking::modulationFrequency;
	action braking (in brakePressure : BrakePressure, in modulationFrequency : Real);
	succession braking then joinNode;	
	join joinNode;
	succession joinNode then done;
}