package de.rigobert0.menulib.area.paginated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import de.rigobert0.menulib.area.ControlArea;
import de.rigobert0.menulib.area.ControlContentWrappingArea;
import de.rigobert0.menulib.area.Pos2D;
import de.rigobert0.menulib.menu.clickhandler.ClickEventAction;
import de.rigobert0.menulib.menu.menucomponent.MenuComponent;

public class PaginatedArea extends ControlContentWrappingArea {


	private PaginatedArea(final Pos2D contentPos, final PageContentArea contentArea,
						  final Pos2D controllingAreaPos, final ControlArea controllingArea) {
		super(contentPos, contentArea, controllingAreaPos, controllingArea);
	}

	public static class Builder {

		private Pos2D contentPos;
		private Pos2D controlPos;
		private ClickEventAction callback;
		private List<Pos2D> contentPositions;
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

		public Builder componentList(List<MenuComponent<?>> componentList) {
			this.componentList = componentList;
			return this;
		}

		private boolean validate() {
			return Stream.of(contentPos, controlPos, callback, contentPositions).allMatch(Objects::nonNull);
		}

		public PaginatedArea build() {
			if (!validate()) {
				throw new IllegalStateException("There are unassigned fields left. " +
												"Only componentList may be left unassigned");
			}
			if (componentList == null)
				componentList = new ArrayList<>();
			PageContentArea contentArea = new PageContentArea(componentList, contentPositions);
			ControlArea controlArea = new PageControlArea(callback);
			controlArea.setContentArea(contentArea);

			return new PaginatedArea(contentPos, contentArea, controlPos, controlArea);
		}

	}

}
