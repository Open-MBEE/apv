action def nonDeterminism1 {
	first start;
	succession start then act1;
	action act1;
	succession act1 then decisionNode;
	decide decisionNode;
		then act2;
		then done;
	action act2;
	succession act2 then done;
}