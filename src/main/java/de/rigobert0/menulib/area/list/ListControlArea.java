package de.rigobert0.menulib.area.list;

import org.bukkit.inventory.ItemStack;

import de.rigobert0.menulib.area.ControlArea;
import de.rigobert0.menulib.menu.clickhandler.ClickEventAction;

public class ListControlArea extends ControlArea {


	public ListControlArea(final ClickEventAction reload) {
		super(reload);
	}

	@Override
	protected ItemStack nextButtonStack() {
		return null;
	}

	@Override
	protected ItemStack prevButtonStack() {
		return null;
	}
}
