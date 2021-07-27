package de.rigobert0.menulib.demoimpl.shopexample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import de.rigobert0.menulib.menu.SinglePageMenu;
import de.rigobert0.menulib.menu.clickhandler.DataProcessor;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class ShopMenu extends SinglePageMenu {
	private static final int SIZE = 54;
	private static final String TITLE = ChatColor.GREEN + "SHOP";

	public ShopMenu() {
		super(SIZE, TITLE);
		List<Material> mats = new ArrayList<>(PriceRegulator.INSTANCE.getRegulatedMaterials());
		Collections.shuffle(mats);
		IntStream.range(0, Math.min(SIZE, mats.size()))
				.forEach(i -> put(i, MenuComponent.ofFixedData(mats.get(i),
						DisplayUtils::priceStack, DataProcessor.sendDatum())));
	}
}
