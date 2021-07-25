package de.rigobert0.menulib.menu;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

/**
 * Interface to recognize menus.
 * Also adds the capability to handle InventoryClickEvents
 */
public interface MenuLibInventoryHolder extends InventoryHolder {

	/**
	 * Method that should delegate an InventoryClickEvent to a
	 * {@link de.rigobert0.menulib.menu.menucomponent.MenuComponent}
	 *
	 * @param event The event to be delegated
	 */
	void delegateClick(InventoryClickEvent event);

}
