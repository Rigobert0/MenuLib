package de.rigobert0.menulib.menu.menucomponent;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.clickhandler.ClickEventHandler;

/**
 * MenuComponent that has a fixed ItemStack and is capable of handling {@link InventoryClickEvent}s
 */
class ActionMenuComponent implements MenuComponent<Object> {

	private final ItemStack stack;
	private final ClickEventHandler clickEventHandler;

	/**
	 * Creates a new ActionMenuComponent with the given ItemStack and ClickEventHandler
	 *
	 * @param stack             The ItemStack which will represent this MenuObject
	 * @param clickEventHandler The EventHandler that will process the InventoryClickEvents related to this MenuObject
	 */
	ActionMenuComponent(final ItemStack stack, final ClickEventHandler clickEventHandler) {
		this.stack = stack;
		this.clickEventHandler = clickEventHandler;
	}

	@Override
	public Object getData() {
		return null;
	}

	@Override
	public ItemStack getStack() {
		return stack;
	}

	@Override
	public void handleClick(final InventoryClickEvent event) {
		clickEventHandler.handle(event);
	}
}
