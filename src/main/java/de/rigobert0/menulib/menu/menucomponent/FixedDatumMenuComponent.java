package de.rigobert0.menulib.menu.menucomponent;


import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.DataProcessor;
import de.rigobert0.menulib.menu.ItemRenderer;

/**
 * MenuComponent implementation for a fixed datum of the given type.
 * Capable of re-rendering and event processing
 * @param <T> The type of the fixed datum
 */
record FixedDatumMenuComponent<T>(T datum, ItemRenderer<T> renderer,
								  DataProcessor<T> dataProcessor) implements MenuComponent<T> {

	@Override
	public T getData() {
		return datum;
	}

	@Override
	public ItemStack getStack() {
		return renderer.render(datum);
	}

	@Override
	public void handleClick(final InventoryClickEvent event) {
		dataProcessor.process(event, datum);
	}
}
