package de.rigobert0.menulib.area;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class RowArea extends LeafArea {

	private final int length;
	private final MenuComponent<?>[] menuComponents;

	public RowArea(final int length) {
		validateLength(length);
		this.length = length;
		menuComponents = new MenuComponent[length];
	}

	public void setComponent(int index, MenuComponent<?> component) {
		menuComponents[index] = component;
	}

	@Override
	public Map<Pos2D, MenuComponent<?>> build(final Pos2D pos2D) {
		return IntStream.range(0, length).boxed().collect(Collectors.toMap(Pos2D::of, i -> menuComponents[i]));
	}

	private void validateLength(int length) throws IllegalArgumentException {
		if (length < 0 || length > ROW_LENGTH)
			throw new IllegalArgumentException("Row length must be positive and not exceed " + ROW_LENGTH);
	}

}
