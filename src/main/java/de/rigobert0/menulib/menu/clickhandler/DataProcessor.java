package de.rigobert0.menulib.menu.clickhandler;

import org.bukkit.event.inventory.InventoryClickEvent;

/**
 * Functional interface to process data and perform advanced operations on an InventoryClickEvent
 *
 * @param <T> The type of data which should be processed
 */
@FunctionalInterface
public interface DataProcessor<T> {

	/**
	 * Processes Data using the information of an InventoryClickEvent
	 *
	 * @param event            The InventoryClickEvent to be processed
	 * @param representedDatum The datum to be processed
	 */
	void process(InventoryClickEvent event, T representedDatum);

	/**
	 * Supplier of an default implementation of a DataProcessor
	 * which sends an informative message to the player on click
	 *
	 * @param <T> The type of data the DataProcessor has to accept
	 * @return An instance of this implementation
	 */
	static <T> DataProcessor<T> sendDatum() {
		return (event, representedDatum) -> event.getWhoClicked().sendMessage("You clicked " + representedDatum);
	}

}
