action decision2 {
		
	first start;
	
	decide decisionNode;
		then act1;
		then act2;
	
	succession start then decisionNode;
		
	action act1;
	
	action act2;
	
	succession act1 then done;
	
	succession act2 then done;
		
}