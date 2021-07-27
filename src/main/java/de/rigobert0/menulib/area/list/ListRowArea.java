package de.rigobert0.menulib.area.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import de.rigobert0.menulib.area.ContentArea;
import de.rigobert0.menulib.area.ControlArea;
import de.rigobert0.menulib.area.ControlContentWrappingArea;
import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.menu.clickhandler.ClickEventAction;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class ListRowArea extends ControlContentWrappingArea {

	private ListRowArea(final Pos2D contentPos, final ContentArea contentArea,
						final Pos2D controllingAreaPos, final ControlArea controllingArea) {
		super(contentPos, contentArea, controllingAreaPos, controllingArea);
	}

	public static class Builder {

		private Pos2D contentPos;
		private Pos2D controlPos;
		private ClickEventAction callback;
		private List<Pos2D> contentPositions;
		private int stepSize = 1;
		private List<MenuComponent<?>> componentList;

		public Builder contentPos(Pos2D contentPos) {
			this.contentPos = contentPos;
			return this;
		}

		public Builder controlPos(Pos2D controlPos) {
			this.controlPos = controlPos;
			return this;
		}

		public Builder reloadCallback(ClickEventAction callback) {
			this.callback = callback;
			return this;
		}

		public Builder contentPositions(List<Pos2D> contentPositions) {
			this.contentPositions = contentPositions;
			return this;
		}

		public Builder stepSize(int stepSize) {
			this.stepSize = stepSize;
			return this;
		}

		public Builder componentList(List<MenuComponent<?>> componentList) {
			this.componentList = componentList;
			return this;
		}

		private void validate() throws IllegalStateException {
			assert Stream.of(contentPos, controlPos, callback, contentPositions).allMatch(Objects::nonNull) :
					"There are unassigned fields left. Only componentList and step size may be left unassigned";
		}

		public ListRowArea build() {
			validate();
			if (componentList == null)
				componentList = new ArrayList<>();
			ListContentArea contentArea = new ListContentArea(componentList, contentPositions, stepSize);
			ControlArea controlArea = new ListControlArea(callback);
			controlArea.setContentArea(contentArea);

			return new ListRowArea(contentPos, contentArea, controlPos, controlArea);
		}

	}
}
