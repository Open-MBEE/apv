import ScalarValues::*;

action join3(in x : Integer) {
	
	doc /* Integer={0..1} */
	
	first start;
	
	join joinNode1;
	
	action act1(in x : Integer);
	
	flow join3::x to joinNode1;
	
	succession start then joinNode1;
		
	flow joinNode1 to act1::x;
	
	succession act1 then done;
		
}