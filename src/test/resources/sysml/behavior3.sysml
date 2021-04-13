action behavior3 {
		
	first start;
		
	action CB1 {
		first start;
		
		action act1;
		
		succession start then act1;
	
		succession act1 then done;
	}
	
	action CB2 {
		first start;
		
		action act1;
		
		succession start then act1;
	
		succession act1 then done;
	}
	
	succession start then CB1;
	
	succession CB1 then CB2;
	
	succession CB2 then done;
		
}