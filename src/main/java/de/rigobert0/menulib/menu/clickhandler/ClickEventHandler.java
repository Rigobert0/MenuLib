package de.rigobert0.menulib.menu.clickhandler;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Functional interface to handle InventoryClickEvents
 */
@FunctionalInterface
public interface ClickEventHandler {

	/**
	 * Processes the passed InventoryClickEvent
	 *
	 * @param event the event to be processed
	 */
	void handle(InventoryClickEvent event);

}
