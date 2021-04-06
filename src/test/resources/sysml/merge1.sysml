action merge1 {
		
	first start;
	
	succession start then act1;
	
	succession start then act2;
		
	action act1;
	
	action act2;
	
	succession act1 then mergeNode1;
	
	succession act2 then mergeNode1;
	
	merge mergeNode1;
	
	succession mergeNode1 then done;
		
}