package de.rigobert0.menulib.menu.clickhandler;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Functional interface for accessing parameterless methods as ClickEventHandler via method references <br>
 * Just reference a method with 0 parameters and cast to ClickEventAction
 */
@FunctionalInterface
public interface ClickEventAction extends ClickEventHandler {

	/**
	 * The Action that will be run on an inventory click
	 */
	void run();

	/**
	 * Implementation of the functional interface {@link ClickEventHandler} <br>
	 * Delegates the event handling to the implementation of {@link #run()}
	 *
	 * @param event The inventoryClickEvent that triggers the action to run.
	 */
	@Override
	default void handle(InventoryClickEvent event) {
		run();
	}
}
