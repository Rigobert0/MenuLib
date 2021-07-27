package de.rigobert0.menulib.demoimpl.shopexample;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.bukkit.Material;

public enum PriceRegulator {
	INSTANCE;
	private final Map<Material, Double> prices = new HashMap<>();

	PriceRegulator() {
		init();
	}

	private void setPrice(Material material, double price) {
		prices.put(material, price);
	}

	private void init() {
		setPrice(Material.STONE, 5d);
		setPrice(Material.DIAMOND, 1000d);
		setPrice(Material.IRON_INGOT, 100d);
		setPrice(Material.COBBLESTONE, 2d);
		setPrice(Material.RED_WOOL, 15d);
		setPrice(Material.GRAY_WOOL, 15d);
		setPrice(Material.GREEN_WOOL, 15d);
		setPrice(Material.LIME_WOOL, 15d);
		setPrice(Material.ORANGE_WOOL, 15d);
		setPrice(Material.BLACK_WOOL, 15d);
		setPrice(Material.PINK_WOOL, 25d);
	}

	public String getPriceInfo(Material material) {
		return prices.get(material).toString();
	}

	public Set<Material> getRegulatedMaterials() {
		return prices.keySet();
	}

}
