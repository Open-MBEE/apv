action join1 {
		
	first start;
	
	succession start then act1;
	
	succession start then act2;
		
	action act1;
	
	action act2;
	
	succession act1 then joinNode1;
	
	succession act2 then joinNode1;
	
	join joinNode1;
	
	succession joinNode1 then done;
		
}