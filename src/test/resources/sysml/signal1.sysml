import ScalarValues::*;

action signal1(in x : Integer) {
		
	doc /* Integer={0..1} */
		
	action sendSignal send signal1::x to acceptEvent;
	
	action acceptEvent accept y : Integer;
	
	action act1(in w : Integer);
			
	flow acceptEvent::y to act1::w;
			
	succession act1 then done;
				
}