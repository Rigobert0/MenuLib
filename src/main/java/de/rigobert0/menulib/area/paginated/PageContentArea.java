package de.rigobert0.menulib.area.paginated;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.rigobert0.menulib.area.Area;
import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

/**
 * Represents a container for an Area with multiple pages of possible content.
 * Fills out its {@link #coverArea} in the order of the {@link #componentList} and continues on the next page.
 * Automatically calculates the last page and does not go further.
 */
public class PageContentArea extends Area {

	private final List<MenuComponent<?>> componentList;
	private final List<Pos2D> coverArea;

	private int page;

	public PageContentArea(final List<MenuComponent<?>> componentList, final List<Pos2D> coverArea) {
		this.componentList = componentList;
		this.coverArea = coverArea;
	}

	@Override
	public Map<Pos2D, MenuComponent<?>> build(final Pos2D pos2D) {
		return IntStream.range(0, coverArea.size())
				.boxed()
				.map(this::mapComponent)
				.filter(Objects::nonNull)
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}

	private Pos2D getCoverPos(int index) {
		return index < coverArea.size() ? coverArea.get(index) : null;
	}

	private MenuComponent<?> getComponent(int index) {
		return index < componentList.size() ? componentList.get(index) : null;
	}

	private int getInCurrentPage(int index) {
		return page * coverArea.size() + index;
	}

	public void nextPage() {
		if ((page + 1) * coverArea.size() < componentList.size()) {
			page++;
		}
	}

	public void prevPage() {
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
