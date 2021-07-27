package de.rigobert0.menulib.util;

import java.util.Optional;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import de.rigobert0.menulib.plugin.MenuLibPlugin;

/**
 * Utility class to simplify the access of persistent data
 */
public class PersistentDataHandler {
	/**
	 * NameSpacedKey which prevents items from being dragged around when assigned to an {@link ItemMeta}s {@link org.bukkit.persistence.PersistentDataContainer}
	 */
	private static final NamespacedKey UNMODIFIABLE_KEY =
			new NamespacedKey(MenuLibPlugin.getInstance(), "unmodifiable");

	/**
	 * Checks if an ItemStack has a specific NameSpacedKey
	 *
	 * @param stack The stack which shall be checked
	 * @param key   The Key which shall be checked
	 * @param type  The PersistentDataType which shall be checked having the key or not
	 * @return True if the key was present, false otherwise
	 */
	private static boolean hasPersistentDatum(ItemStack stack, NamespacedKey key, PersistentDataType<?, ?> type) {
		return Optional.ofNullable(stack)
				.map(ItemStack::getItemMeta)
				.map(ItemMeta::getPersistentDataContainer)
				.map(p -> p.has(key, type))
				.orElse(false);
	}

	/**
	 * Checks if the given ItemStack has the {@link #UNMODIFIABLE_KEY}
	 *
	 * @param stack The stack to be checked
	 * @return True if the ItemStack had the key, false otherwise
	 */
	public static boolean isUnmodifiable(ItemStack stack) {
		return hasPersistentDatum(stack, UNMODIFIABLE_KEY, PersistentDataType.INTEGER);
	}


	public static NamespacedKey getUnmodifiableKey() {
		return UNMODIFIABLE_KEY;
	}
}
