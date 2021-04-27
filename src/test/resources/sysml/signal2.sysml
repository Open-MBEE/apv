import ScalarValues::*;

action signal2(in x : Integer) {
		
	doc /* Integer={0..1} */
		
	action sendSignal send signal2::x to acceptEvent;
	
	action acceptEvent accept y : Integer;
	
	action act1(in w : Integer);
	
	action act2(in z : Integer);
			
	flow acceptEvent::y to act1::w;
	
	flow acceptEvent::y to act2::z;
						
	succession act1 then done;
				
	succession act2 then done;
	
}