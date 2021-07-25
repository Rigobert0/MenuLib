package de.rigobert0.menulib.menu;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Implementation of a simple menu.
 */
public abstract class SinglePageMenu extends Menu {
	private final Map<Integer, MenuComponent<?>> menuObjectMap;

	/**
	 * Creates a new SinglePageMenu with the given size and title
	 *
	 * @param size  The number of slots the menu will have. Must be a value that
	 *              {@link Bukkit#createInventory(InventoryHolder, int, String)} allows
	 * @param title The title that will be displayed at the top of the inventory
	 */
	protected SinglePageMenu(final int size, final String title) {
		super(size, title);
		menuObjectMap = new HashMap<>();
	}

	@Override
	protected ItemStack[] generateContent() {
		return buildContentsFrom(menuObjectMap, getSize());
	}

	@Override
	protected MenuComponent<?> get(final int index) {
		return menuObjectMap.get(index);
	}

	/**
	 * Assigns a MenuComponent to a slot
	 *
	 * @param index The index of the slot
	 * @param value The MenuComponent which shall be assigned to the slot
	 */
	protected void put(int index, final MenuComponent<?> value) {
		if (index < 0 || index >= getSize()) {
			throw new IllegalArgumentException("Index must match inventory size " + getSize() + " but was " + index + "!");
		}
		menuObjectMap.put(index, value);
	}

}
