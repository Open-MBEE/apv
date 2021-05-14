action def deadlock2 {
	
	first start;
	
	action act1;
	
	succession start then act1;
	
	action act2;
	
	succession act1 then act2;
	
	decide decisionNode;
		then act1;
		then done;
	
	succession act2 then decisionNode;

}