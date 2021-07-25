package de.rigobert0.menulib.area;

import java.util.Collections;
import java.util.Map;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Represents a single inventory slot.
 * It will be located at the Pos2D defined by the parent Area.
 *
 * @see NodeArea
 * @see LeafArea
 */
public class MenuComponentArea extends LeafArea {

	private final MenuComponent<?> child;

	public MenuComponentArea(final MenuComponent<?> child) {
		this.child = child;
	}

	@Override
	public Map<Pos2D, MenuComponent<?>> build(final Pos2D pos2D) {
		return Collections.singletonMap(pos2D, child);
	}

}
