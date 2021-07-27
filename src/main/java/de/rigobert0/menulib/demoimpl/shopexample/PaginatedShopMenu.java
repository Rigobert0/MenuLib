package de.rigobert0.menulib.demoimpl.shopexample;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import de.rigobert0.menulib.menu.PaginatedMenu;
import de.rigobert0.menulib.menu.clickhandler.DataProcessor;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class PaginatedShopMenu extends PaginatedMenu {

	private static final int SIZE = 18;
	private static final String TITLE = ChatColor.YELLOW + "SHOP";

	public PaginatedShopMenu() {
		super(SIZE, TITLE);
		List<Material> mats = new ArrayList<>(PriceRegulator.INSTANCE.getRegulatedMaterials());
		mats.stream().map(mat -> MenuComponent.ofFixedData(mat, DisplayUtils::priceStack, DataProcessor.sendDatum()))
				.forEach(this::addMenuContent);
	}

}
