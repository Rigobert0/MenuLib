package de.rigobert0.menulib.menu;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bukkit.inventory.InventoryHolder;

import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.area.paginated.PaginatedArea;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public abstract class PaginatedAreaMenu extends ModularMenu {

	private final PaginatedArea paginatedArea;

	/**
	 * Creates a menu with the given size and title.
	 *
	 * @param size  The number of slots the menu will have. Must be a value that
	 *              {@link org.bukkit.Bukkit#createInventory(InventoryHolder, int, String)} allows
	 * @param title The title that will be displayed at the top of the inventory
	 */
	protected PaginatedAreaMenu(final int size, final String title) {
		super(size, title);
		int space = size - ROW_LENGTH;
		List<Pos2D> positions = IntStream.range(0, space).mapToObj(Pos2D::of).collect(Collectors.toList());
		paginatedArea = new PaginatedArea.Builder().reloadCallback(this::reload).contentPos(Pos2D.ZERO)
				.controlPos(Pos2D.of(space)).contentPositions(positions).build();
		addArea(Pos2D.ZERO, paginatedArea);
		setup();
	}

	protected void setup() {
		addToControlArea(0, paginatedArea.defaultPrevPageButton());
		addToControlArea(ROW_LENGTH - 1, paginatedArea.defaultNextPageButton());
	}

	protected void addComponent(MenuComponent<?> component) {
		paginatedArea.addComponent(component);
	}

	public void addToControlArea(int pos, MenuComponent<?> component) {
		if (pos < 0 || pos >= ROW_LENGTH)
			throw new IllegalArgumentException("Position must be in between 1 and 8");
		paginatedArea.addToControlArea(Pos2D.of(pos), component);
	}

}
