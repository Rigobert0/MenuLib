package de.rigobert0.menulib.area;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.ClickEventAction;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;
import de.rigobert0.menulib.util.ItemStackManipulator;

public class PageControlArea extends NodeArea {

	private final ClickEventAction reload;
	private PageContentArea contentArea;

	public PageControlArea(final ClickEventAction reload) {
		this.reload = reload;
	}

	protected void nextPage() {
		contentArea.nextPage();
		reload.run();
	}

	protected void prevPage() {
		contentArea.prevPage();
		reload.run();
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

	public MenuComponent<?> nextPageButton() {
		return MenuComponent.createButton(nextButtonStack(), (ClickEventAction) this::nextPage);
	}

	public MenuComponent<?> prevPageButton() {
		return MenuComponent.createButton(prevButtonStack(), (ClickEventAction) this::prevPage);
	}

	public void setContentArea(final PageContentArea contentArea) {
		this.contentArea = contentArea;
	}
}
