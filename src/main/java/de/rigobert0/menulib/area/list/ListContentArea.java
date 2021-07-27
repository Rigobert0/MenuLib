package de.rigobert0.menulib.area.list;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import de.rigobert0.menulib.area.ContentArea;
import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class ListContentArea extends ContentArea {

	private final int stepSize;
	private int start = 0;

	public ListContentArea(final List<MenuComponent<?>> componentList, final List<Pos2D> coverArea,
						   int stepSize) {
		super(componentList, coverArea);
		this.stepSize = stepSize;
	}

	@Override
	public Map<Pos2D, MenuComponent<?>> build(final Pos2D pos2D) {
		return IntStream.range(0, coverArea.size()).boxed()
				.collect(Collectors.toMap(this::getCoverPos, i -> getComponent(shiftIndex(i))));
	}

	private int shiftIndex(int index) {
		return index + start;
	}

	@Override
	public void nextContent() {
		int covSize = coverArea.size();
		int comRemaining = componentList.size() - start;
		if (covSize < comRemaining - start) {
			start += Math.min(stepSize, comRemaining - covSize);
		}
	}

	@Override
	public void prevContent() {
		if (start > 0) {
			start -= Math.min(stepSize, start);
		}
	}

}
