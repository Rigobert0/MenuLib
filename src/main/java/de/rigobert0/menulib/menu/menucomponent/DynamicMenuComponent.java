package de.rigobert0.menulib.menu.menucomponent;

import java.util.function.Supplier;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.DataProcessor;
import de.rigobert0.menulib.menu.ItemRenderer;

/**
 * Dynamic implementation of a MenuComponent.
 * Is capable of reloading and re-rendering data and processing ClickEvents
 *
 * @param <T> The type of data this MenuObject will represent
 */
record DynamicMenuComponent<T>(Supplier<T> dataSupplier, ItemRenderer<T> itemRenderer,
							   DataProcessor<T> dataProcessor)
		implements MenuComponent<T> {
	/**
	 * Empty MenuComponent to fill out slots. Not needed anymore
	 */
	@Deprecated
	private static final DynamicMenuComponent<?> empty = new DynamicMenuComponent<>(() -> null, o -> null, (e, c) -> {
	});

	@Deprecated
	public static <T> DynamicMenuComponent<T> empty() {
		@SuppressWarnings("unchecked")
		DynamicMenuComponent<T> t = (DynamicMenuComponent<T>) empty;
		return t;
	}
	@Override
	public ItemStack getStack() {
		return itemRenderer.render(dataSupplier.get());
	}

	@Override
	public void handleClick(InventoryClickEvent event) {
		dataProcessor.process(event, getData());
	}

	/**
	 * Supplier of empty menuComponents
	 *
	 * @param <T> The type the empty MenuComponent will have
	 * @return The empty MenuComponent
	 */


	@Override
	public T getData() {
		return dataSupplier.get();
	}

}
