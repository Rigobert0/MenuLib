package de.rigobert0.menulib.area;

import java.util.Map;

public class PaginatedArea extends NodeArea {

	public PaginatedArea(Pos2D contentPos, PageContentArea contentArea,
						 Pos2D controllingAreaPos, PageControlArea controllingArea) {
		super(Map.of(contentPos, contentArea, controllingAreaPos, controllingArea));
		controllingArea.setContentArea(contentArea);
	}



	@Override
	protected boolean canAdd(Pos2D pos2D, Area child) {
		return false;
	}

}
