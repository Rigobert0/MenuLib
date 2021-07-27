package de.rigobert0.menulib.area.paginated;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.area.ControlArea;
import de.rigobert0.menulib.menu.clickhandler.ClickEventAction;
import de.rigobert0.menulib.util.ItemStackManipulator;

public class PageControlArea extends ControlArea {

	public PageControlArea(final ClickEventAction reload) {
		super(reload);
	}

	@Override
	protected ItemStack nextButtonStack() {
		return new ItemStackManipulator(Material.GRAY_STAINED_GLASS_PANE)
				.name("Next Page")
				.unmodifiable()
				.manipulate();
	}

	@Override
	protected ItemStack prevButtonStack() {
		return new ItemStackManipulator(Material.GRAY_STAINED_GLASS_PANE)
				.name("Previous page")
				.unmodifiable()
				.manipulate();
	}


}
