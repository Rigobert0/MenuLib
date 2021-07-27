package de.rigobert0.menulib.menu;

import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;
import de.rigobert0.menulib.util.Constants;

/**
 * Base class for all menus
 * Implements {@link MenuLibInventoryHolder} for event handling.
 * Wraps around an final {@link Inventory}
 */
public abstract class Menu implements MenuLibInventoryHolder {

	protected static final int ROW_LENGTH = Constants.ROW_LENGTH.value();

	private final Inventory inventory;

	/**
	 * Creates a menu with the given size and title.
	 *
	 * @param size  The number of slots the menu will have. Must be a value that
	 *              {@link Bukkit#createInventory(InventoryHolder, int, String)} allows
	 * @param title The title that will be displayed at the top of the inventory
	 */
	protected Menu(int size, String title) {
		this.inventory = Bukkit.createInventory(this, size, title);
	}

	/**
	 * Getter for the Inventory this Menu will display.
	 * Implementation of the {@link InventoryHolder} interface.
	 *
	 * @return The inventory of this MenuObject
	 */
	@Override
	public final Inventory getInventory() {
		reload();
		return inventory;
	}

	/**
	 * Setter for the whole Inventory content
	 *
	 * @param contents The new content of the inventory
	 */
	protected void setContents(ItemStack[] contents) {
		inventory.setContents(contents);
	}

	/**
	 * This method shall return the menu content as ItemStacks
	 *
	 * @return The content that will be displayed in the menu
	 */
	protected abstract ItemStack[] generateContent();

	/**
	 * Getter for MenuComponents at the given slot in the currently displayed view of this menu
	 *
	 * @param index The index of the slot you want to have the MenuComponent of
	 * @return The MenuComponent at the given slot or null
	 */
	protected abstract MenuComponent<?> get(int index);

	/**
	 * Reloads the view of the inventory. Could change content and trigger re-rendering
	 */
	protected final void reload() {
		setContents(generateContent());
	}

	/**
	 * @return the size of the Inventory
	 */
	public final int getSize() {
		return inventory.getSize();
	}

	/**
	 * Utility method to render whole Slot-MenuComponent maps
	 * Fills out unassigned slots with null ItemStacks
	 *
	 * @param menuObjectMap The Mapping of the MenuComponents
	 * @param limit         The space you want to fill out, eventually using empty slots
	 * @return An array consisting of the rendered MenuComponents as ItemStacks,
	 */
	protected static ItemStack[] buildContentsFrom(Map<Integer, MenuComponent<?>> menuObjectMap, int limit) {
		return IntStream.range(0, limit)
				.mapToObj(menuObjectMap::get)
				.map(Optional::ofNullable)
				.map(o -> o.map(MenuComponent::getStack).orElse(null))
				.toArray(ItemStack[]::new);
	}

	/**
	 * Delegates an ClickEvent in this Inventory to the MenuComponent assigned to the clicked slot.
	 * If no MenuComponent was assigned, nothing will happen.
	 *
	 * @param event The InventoryClickEvent to be delegated
	 */
	@Override
	public void delegateClick(InventoryClickEvent event) {
		Optional.ofNullable(get(event.getSlot())).ifPresent(o -> MenuComponent.performAction(o, event));
	}

}
