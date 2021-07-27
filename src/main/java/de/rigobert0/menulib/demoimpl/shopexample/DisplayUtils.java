package de.rigobert0.menulib.demoimpl.shopexample;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.util.ItemStackManipulator;

public class DisplayUtils {

	public static ItemStack priceStack(Material material) {
		return new ItemStackManipulator(material)
				.lore(PriceRegulator.INSTANCE.getPriceInfo(material) + "$")
				.unmodifiable()
				.manipulate();
	}

}
