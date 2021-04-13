package com.ref.sysml.adapter;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.omg.sysml.xtext.sysml.ActionUsage;
import org.omg.sysml.xtext.sysml.Namespace;

import com.ref.exceptions.WellFormedException;
import com.ref.interfaces.activityDiagram.IActivity;
import com.ref.interfaces.activityDiagram.IActivityDiagram;

public class ActivityDiagram implements IActivityDiagram{
	protected EObject activityDiagram;
	protected IActivity activity;
	
	public ActivityDiagram(Namespace ad) throws WellFormedException {
		this.activityDiagram = ad;
		
		List<ActionUsage> memberships = ((Namespace) this.activityDiagram).getOwnedMembership_comp();
		
		for (ActionUsage actionDef : memberships) {
			this.activity = new Activity(actionDef);
			this.activity.setActivityDiagram(this);
			
			return;
		}
	}
	
	public ActivityDiagram(ActionUsage ad) throws WellFormedException {
		this.activityDiagram = ad;
		
		this.activity = new Activity(ad);
		this.activity.setActivityDiagram(this);
	}
	
	@Override
	public String getDefinition() {
		return this.activity.getDefinition().replace("/*", "").replace("*/", "").trim();
	}

	@Override
	public String getName() {
		return activity.getName();
	}

	@Override
	public String getId() {
		return String.valueOf(System.identityHashCode(activityDiagram));
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
