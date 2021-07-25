package de.rigobert0.menulib.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import de.rigobert0.menulib.menu.PersistentDataHandler;

public class ItemStackManipulator {

	private final ItemStack itemStack;
	private final ItemMeta meta;

	public ItemStackManipulator(ItemStack stack) {
		this.itemStack = stack;
		if (stack.getItemMeta() == null) {
			throw new IllegalArgumentException("Stuck must be capable of having meta");
		}
		this.meta = stack.getItemMeta();
	}

	public ItemStackManipulator(Material material) {
		this(new ItemStack(material));
	}

	public ItemStackManipulator name(String name) {
		meta.setDisplayName(name);
		return this;
	}

	public ItemStackManipulator lore(String... lore) {
		return lore(switch (lore.length) {
			case 0 -> Collections.emptyList();
			case 1 -> Collections.singletonList(lore[0]);
			default -> Arrays.asList(lore);
		});
	}

	public ItemStackManipulator lore(List<String> lore) {
		meta.setLore(lore);
		return this;
	}

	public ItemStackManipulator unmodifiable() {
		return addPersistentDatum(PersistentDataHandler.getUnmodifiableKey(), PersistentDataType.INTEGER, 0);
	}

	public ItemStack manipulate() {
		itemStack.setItemMeta(meta);
		return itemStack;
	}

	private <T> ItemStackManipulator addPersistentDatum(NamespacedKey key,
														PersistentDataType<?, T> persistentDataType, T value) {
		meta.getPersistentDataContainer().set(key, persistentDataType, value);
		return this;
	}

}
