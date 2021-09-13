import ScalarValues::*;

action chargeBattery (in battery : Real) {
	
	doc /* Real={0..100} */
	
	bind chargeBattery::battery = continueCharging;
	merge continueCharging;
	flow continueCharging to monitor::batteryIn;
	action monitor (in batteryIn : Real, out batteryOut : Real){
		language "Alf"
		/* batteryOut = batteryIn; */
	}
	flow monitor::batteryOut to decisionNode;
	decide decisionNode;
		if monitor::batteryOut < 100 then addCharge::batteryIn;
		if monitor::batteryOut > 100 then endCharging;			
	action addCharge (in batteryIn : Real, out batteryOut : Real) {
		language "Alf"
		/* batteryOut = batteryIn + 1; */	
	}
	flow addCharge::batteryOut to continueCharging;
	action endCharging;
	succession endCharging then done;
}