package de.rigobert0.menulib.area;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.ClickEventAction;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;
import de.rigobert0.menulib.util.ItemStackManipulator;

public class PageControlArea extends NodeArea {

	private PageContentArea contentArea;

	public PageControlArea() {
	}

	protected void nextPage() {
		contentArea.nextPage();
	}

	protected void prevPage() {
		contentArea.prevPage();
	}

	protected ItemStack nextButtonStack() {
		return new ItemStackManipulator(Material.GRAY_STAINED_GLASS_PANE)
				.name("Next Page")
				.unmodifiable()
				.manipulate();
	}

	protected ItemStack prevButtonStack() {
		return new ItemStackManipulator(Material.GRAY_STAINED_GLASS_PANE)
				.name("Previous Page")
				.unmodifiable()
				.manipulate();
	}

	protected MenuComponent<?> nextButton() {
		return MenuComponent.createButton(nextButtonStack(), (ClickEventAction) this::nextPage);
	}

	protected MenuComponent<?> prevPageButton() {
		return MenuComponent.createButton(prevButtonStack(), (ClickEventAction) this::prevPage);
	}

	public void setContentArea(final PageContentArea contentArea) {
		this.contentArea = contentArea;
	}
}
