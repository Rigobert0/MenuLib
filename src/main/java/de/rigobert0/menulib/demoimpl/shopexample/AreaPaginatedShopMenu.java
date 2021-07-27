package de.rigobert0.menulib.demoimpl.shopexample;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

import de.rigobert0.menulib.menu.PaginatedAreaMenu;
import de.rigobert0.menulib.menu.clickhandler.DataProcessor;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class AreaPaginatedShopMenu extends PaginatedAreaMenu {

	private static final int SIZE = 18;

	public AreaPaginatedShopMenu() {
		super(SIZE, "Shop");
		List<Material> mats = new ArrayList<>(PriceRegulator.INSTANCE.getRegulatedMaterials());
		mats.stream().map(mat -> MenuComponent.ofFixedData(mat, DisplayUtils::priceStack, DataProcessor.sendDatum()))
				.forEach(this::addComponent);
	}

}
