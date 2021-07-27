package de.rigobert0.menulib.area;

import java.util.List;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public abstract class ContentArea extends Area {
	protected final List<MenuComponent<?>> componentList;
	protected final List<Pos2D> coverArea;

	public ContentArea(final List<MenuComponent<?>> componentList, final List<Pos2D> coverArea) {
		this.componentList = componentList;
		this.coverArea = coverArea;
	}

	protected Pos2D getCoverPos(int index) {
		return index < coverArea.size() ? coverArea.get(index) : null;
	}

	protected MenuComponent<?> getComponent(int index) {
		return index < componentList.size() ? componentList.get(index) : null;
	}

	public void addComponent(MenuComponent<?> component) {
		componentList.add(component);
	}

	public abstract void nextContent();

	public abstract void prevContent();

}
