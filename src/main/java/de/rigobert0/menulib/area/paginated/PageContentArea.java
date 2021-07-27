package de.rigobert0.menulib.area.paginated;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.rigobert0.menulib.area.ContentArea;
import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Represents a container for an Area with multiple pages of possible content.
 * Fills out its {@link #coverArea} in the order of the {@link #componentList} and continues on the next page.
 * Automatically calculates the last page and does not go further.
 */
public class PageContentArea extends ContentArea {

	private int page = 0;

	public PageContentArea(final List<MenuComponent<?>> componentList, final List<Pos2D> coverArea) {
		super(componentList, coverArea);
	}

	@Override
	public Map<Pos2D, MenuComponent<?>> build(final Pos2D pos2D) {
		return IntStream.range(0, coverArea.size())
				.boxed()
				.map(this::mapComponent)
				.filter(Objects::nonNull)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	private int getInCurrentPage(int index) {
		return page * coverArea.size() + index;
	}

	@Override
	public void nextContent() {
		if ((page + 1) * coverArea.size() < componentList.size()) {
			page++;
		}
	}

	@Override
	public void prevContent() {
		if (page > 0) {
			page--;
		}
	}

	private Map.Entry<Pos2D, MenuComponent<?>> mapComponent(int i) {
		Pos2D pos2D = getCoverPos(i);
		MenuComponent<?> component = getComponent(getInCurrentPage(i));
		if (pos2D == null || component == null)
			return null;
		return Map.entry(pos2D, component);
	}

	public void addComponent(MenuComponent<?> component) {
		componentList.add(component);
	}

}
