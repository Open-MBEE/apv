action fork1 {

	first start;
	
	succession start then forkNode1;
	
	fork forkNode1;
	
	succession forkNode1 then act1;
	
	succession forkNode1 then act2;
		
	action act1;
	
	action act2;
	
	succession act1 then done;
	
	succession act2 then done;
		
}