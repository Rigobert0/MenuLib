package de.rigobert0.menulib.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import de.rigobert0.menulib.menu.MenuLibInventoryHolder;
import de.rigobert0.menulib.util.PersistentDataHandler;

/**
 * Singleton {@link Listener} for {@link InventoryClickEvent}s
 */
public enum InventoryClickListener implements Listener {
	INSTANCE;

	/**
	 * Processes an InventoryClickEvent
	 *
	 * @param event The event to be processed.
	 */
	@EventHandler
	public void handle(InventoryClickEvent event) {
		if (event.getClickedInventory() == null) {
			return;
		}
		if (!(event.getClickedInventory().getHolder() instanceof MenuLibInventoryHolder holder)) {
			return;
		}
		if (PersistentDataHandler.isUnmodifiable(event.getCurrentItem())) {
			event.setCancelled(true);
		}
		holder.delegateClick(event);
	}

}
