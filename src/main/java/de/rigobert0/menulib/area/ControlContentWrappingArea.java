package de.rigobert0.menulib.area;

import java.util.Map;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class ControlContentWrappingArea extends NodeArea {

	private final ControlArea controlArea;
	private final ContentArea contentArea;

	public ControlContentWrappingArea(Pos2D contentPos, ContentArea contentArea,
									  Pos2D controllingAreaPos, ControlArea controllingArea) {
		super(Map.of(contentPos, contentArea, controllingAreaPos, controllingArea));
		this.controlArea = controllingArea;
		this.contentArea = contentArea;
		controllingArea.setContentArea(contentArea);
	}

	@Override
	protected boolean canAdd(Pos2D pos2D, Area child) {
		return false;
	}

	public void addToControlArea(Pos2D pos2D, MenuComponent<?> component) {
		controlArea.setChild(pos2D, component.asArea());
	}

	public void addComponent(MenuComponent<?> component) {
		contentArea.addComponent(component);
	}

	public MenuComponent<?> defaultPrevPageButton() {
		return controlArea.prevPageButton();
	}

	public MenuComponent<?> defaultNextPageButton() {
		return controlArea.nextPageButton();
	}
}
