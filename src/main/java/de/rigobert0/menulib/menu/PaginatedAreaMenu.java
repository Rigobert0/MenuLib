package de.rigobert0.menulib.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.area.PageContentArea;
import de.rigobert0.menulib.area.PageControlArea;
import de.rigobert0.menulib.area.PaginatedArea;
import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.area.RootArea;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public abstract class PaginatedAreaMenu extends Menu {

	private final RootArea rootArea;
	private final PageControlArea controlArea = new PageControlArea();
	private final PageContentArea contentArea;

	/**
	 * Creates a menu with the given size and title.
	 *
	 * @param size  The number of slots the menu will have. Must be a value that
	 *              {@link org.bukkit.Bukkit#createInventory(InventoryHolder, int, String)} allows
	 * @param title The title that will be displayed at the top of the inventory
	 */
	protected PaginatedAreaMenu(final int size, final String title) {
		super(size, title);
		rootArea = new RootArea();
		int space = size - ROW_LENGTH;
		List<Pos2D> positions = IntStream.range(0, space).mapToObj(Pos2D::of).collect(Collectors.toList());
		contentArea = new PageContentArea(new ArrayList<>(), positions);
		setup(space);
	}

	private void setup(int space) {
		addToControlArea(0, controlArea.prevPageButton());
		addToControlArea(ROW_LENGTH - 1, controlArea.nextPageButton());
		PaginatedArea paginatedArea = new PaginatedArea(Pos2D.ZERO, contentArea,
				Pos2D.of(space), controlArea);
		rootArea.setChild(Pos2D.ZERO, paginatedArea);
	}


	@Override
	protected ItemStack[] generateContent() {
		return buildContentsFrom(rootArea.toInventoryPositions(), getSize());
	}

	@Override
	protected MenuComponent<?> get(final int index) {
		return rootArea.get(index);
	}

	protected void addComponent(MenuComponent<?> component) {
		contentArea.addComponent(component);
	}

	public void addToControlArea(int pos, MenuComponent<?> component) {
		if (pos < 0 || pos >= ROW_LENGTH)
			throw new IllegalArgumentException("Position must be in between 1 and 8");
		controlArea.setChild(Pos2D.of(pos), component.asArea());
	}

}
