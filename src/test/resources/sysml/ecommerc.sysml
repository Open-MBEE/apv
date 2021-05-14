action def ecommerc {
	
	first start;
	
	succession start then receiveOrder;
	
	action receiveOrder;
	
	succession receiveOrder then fork1;
	
	fork fork1;
	
	succession fork1 then checkCustomer;
	
	action checkCustomer;
	
	succession checkCustomer then fork2;
	
	fork fork2;
	
	succession fork2 then decision1;
	
	merge merge1;
	
	decide decision1;
		if "Customer ok" then merge1;
		else done;
		
	succession merge1 then sendBill;
		
	action sendBill;
	
	succession sendBill then wait3;
	
	action wait3;
	
	succession wait3 then sendReminder;
	
	succession sendReminder then wait3;
	
	action sendReminder;
	
	succession wait3 then handlePayment;
	
	action handlePayment;
	
	succession handlePayment then decision2;
	
	action wait5;
	
	action notifyCustomer;
	
	succession notifyCustomer then merge1;
	
	decide decision2;
		if "Payment ok" then wait5;
		else notifyCustomer;
		
	succession wait5 then join1;
		
	join join1;
	
	succession join1 then shipOrder;
	
	action shipOrder;
	
	succession shipOrder then done;
	
	succession fork1 then checkStock;
	
	action checkStock;
	
	succession checkStock then decision3;
	
	decide decision3;
		if "Insufficient stock" then makeProductionPlan;
		else merge2;
	
	action makeProductionPlan;
	
	merge merge2;
	
	succession makeProductionPlan then merge2;
	
	action wait1;
	
	succession merge2 then wait1;
	
	succession wait1 then join2;
	
	join join2;
	
	succession fork2 then wait2;
	
	action wait2;
	
	succession wait2 then join2;
	
	succession join2 then decision4;
	
	decide decision4;
		if "Customer ok" then decision5;
		else done;
		
	decide decision5;
		if "Insufficient stock" then produce;
		else fillOrder;
	
	action fillOrder;
	
	action produce;
	
	succession fillOrder then merge3;
	
	succession produce then merge3;
	
	merge merge3;
	
	succession merge3 then wait4;
	
	action wait4;
	
	succession wait4 then join1;

}