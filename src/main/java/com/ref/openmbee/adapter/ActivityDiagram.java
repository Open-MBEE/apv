package com.ref.openmbee.adapter;

import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;

public class ActivityDiagram implements IActivityDiagram{
	private Activity act;
	private String id;
	private String name;
	private String definition;
	private String[] stereotypes;
	
	public ActivityDiagram(String id, String name, String definition, String[] stereotypes) {
		super();
		this.id = id;
		this.name = name;
		this.definition = definition;
		this.stereotypes = stereotypes;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDefinition() {
		return definition;
	}

	@Override
	public String[] getStereotypes() {
		return stereotypes;
	}

	@Override
	public IActivity getActivity() {
		return act;
	}

	public void setActivity(Activity act) {
		this.act = act;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

}
