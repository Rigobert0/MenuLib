package de.rigobert0.menulib.menu.menucomponent;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * MenuComponent implementation as simple wrapper for an ItemStack object.
 * Ignores clicks and contains no other data.
 */
record ItemStackMenuComponent(ItemStack stack) implements MenuComponent<Object> {

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

	}

}
