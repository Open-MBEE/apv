action def deadlock2 {
	first start;
	succession start then act1;
	action act1;
	succession act1 then act2;
	action act2;
	succession act2 then decisionNode;
	decide decisionNode;
		then act1;
		then done;
}