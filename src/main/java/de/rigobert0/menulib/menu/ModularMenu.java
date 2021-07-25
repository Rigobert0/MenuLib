package de.rigobert0.menulib.menu;

import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.area.Area;
import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.area.RootArea;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Complex menu base class. Uses Areas as Wrapper for MenuComponents, making it possible to have different functionality
 * in one menu
 *
 * @see Area
 */
public abstract class ModularMenu extends Menu {

	private final RootArea rootArea = new RootArea();

	/**
	 * Creates a menu with the given size and title.
	 *
	 * @param size  The number of slots the menu will have. Must be a value that
	 *              {@link org.bukkit.Bukkit#createInventory(InventoryHolder, int, String)} allows
	 * @param title The title that will be displayed at the top of the inventory
	 */
	protected ModularMenu(final int size, final String title) {
		super(size, title);
	}

	@Override
	protected ItemStack[] generateContent() {
		return buildContentsFrom(rootArea.toInventoryPositions(), getSize());
	}

	@Override
	protected MenuComponent<?> get(final int index) {
		return rootArea.get(index);
	}

	protected void addArea(Pos2D pos2D, Area area) {
		rootArea.setChild(pos2D, area);
	}
}
