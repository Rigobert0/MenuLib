package de.rigobert0.menulib.area;

import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.clickhandler.ClickEventAction;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public abstract class ControlArea extends NodeArea {
	protected final ClickEventAction reload;
	private ContentArea contentArea;

	public ControlArea(final ClickEventAction reload) {
		this.reload = reload;
	}

	protected void nextContent() {
		contentArea.nextContent();
		reload.run();
	}

	protected void prevContent() {
		contentArea.prevContent();
		reload.run();
	}

	protected abstract ItemStack nextButtonStack();

	protected abstract ItemStack prevButtonStack();

	public MenuComponent<?> nextPageButton() {
		return MenuComponent.createButton(nextButtonStack(), (ClickEventAction) this::nextContent);
	}

	public MenuComponent<?> prevPageButton() {
		return MenuComponent.createButton(prevButtonStack(), (ClickEventAction) this::prevContent);
	}

	public void setContentArea(final ContentArea contentArea) {
		this.contentArea = contentArea;
	}
}
