action def behavior3 {
		
	first start;
	
	action cb1 : CB1;
	
	action cb2 : CB2;
	
	succession start then cb1;
	
	succession cb1 then cb2;
	
	succession cb2 then done;
		
}

action def CB1 {
	
	first start;
			
	action act1;
			
	succession start then act1;
		
	succession act1 then done;
			
}

action def CB2 {
	
	first start;
			
	action act1;
			
	succession start then act1;
		
	succession act1 then done;
			
}