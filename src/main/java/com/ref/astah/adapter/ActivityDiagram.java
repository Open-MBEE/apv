package com.ref.astah.adapter;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;

public class ActivityDiagram implements IActivityDiagram{
	private com.change_vision.jude.api.inf.model.IActivityDiagram activityDiagram;
	private IActivity activity;
	public ActivityDiagram(com.change_vision.jude.api.inf.model.IActivityDiagram ad) throws WellFormedException {
		this.activityDiagram = ad;
		this.activity = new Activity(ad.getActivity());
	}

	@Override
	public String getDefinition() {
		return activityDiagram.getDefinition();
	}

	@Override
	public String getName() {
		return activityDiagram.getName();
	}

	@Override
	public String getId() {
		return activityDiagram.getId();
	}

	@Override
	public String[] getStereotypes() {
		return activity.getStereotypes();
	}

	@Override
	public IActivity getActivity() {
		return activity;
	}

}
