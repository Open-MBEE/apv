action behavior1 {
		
	first start;
		
	action CB1 {
		first start;
		
		action act1;
		
		succession start then act1;
	
		succession act1 then done;
	}
	
	succession start then CB1;
	
	succession CB1 then done;
		
}