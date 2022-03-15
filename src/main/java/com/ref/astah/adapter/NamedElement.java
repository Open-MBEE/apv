package com.ref.astah.adapter;

import com.ref.interfaces.INamedElement;

public class NamedElement implements INamedElement{

	com.change_vision.jude.api.inf.model.INamedElement namedElement;
	
	public NamedElement(com.change_vision.jude.api.inf.model.INamedElement namedElement) {
		super();
		this.namedElement = namedElement;
	}

	@Override
	public String getId() {
		return namedElement.getId();
	}

	@Override
	public String getName() {
		return namedElement.getName();
	}

	@Override
	public String getDefinition() {
		return namedElement.getDefinition();
	}

	@Override
	public String[] getStereotypes() {
		return namedElement.getStereotypes();
	}

	public boolean equals(INamedElement element) {
		return this.getId().equals(namedElement.getId());
	}
	
	public String toString() {
		return this.getName();
	}
	
}
