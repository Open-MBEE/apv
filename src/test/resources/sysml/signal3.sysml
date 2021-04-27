import ScalarValues::*;

action signal3(in x : Integer) {
		
	doc /* Integer={0..1} */
	
	first start;
		
	action sendSignal send signal3::x to acceptEvent;
	
	succession start then sendSignal;
	
	action acceptEvent accept y : Integer;
	
	action act1(in w : Integer);
	
	action act2;
			
	flow acceptEvent::y to act1::w;
	
	succession acceptEvent then act2;
						
	succession act1 then done;
				
	succession act2 then done;
	
}