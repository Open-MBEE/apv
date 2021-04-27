package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;

public class ActivityDiagram implements IActivityDiagram{
	private Activity act;
	private String id;
	private String name;
	private String definition;
	private String[] stereotypes;
	
	/*
	 * 
	 * */
	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getStereotypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IActivity getActivity() {
		return act;
	}

}
