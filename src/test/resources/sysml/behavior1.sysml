action def behavior1 {
		
	first start;
	
	action cb1 : CB1;
	
	succession start then cb1;
	
	succession cb1 then done;
		
}

action def CB1 {
	
	first start;
		
	action act1;
		
	succession start then act1;
	
	succession act1 then done;
	
}