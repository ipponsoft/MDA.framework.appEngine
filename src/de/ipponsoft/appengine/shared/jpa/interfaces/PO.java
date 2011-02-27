package de.ipponsoft.appengine.shared.jpa.interfaces;

public interface PO {

	Object getId();
	DTO asDTO();
	void fromDTO(DTO from);
}
