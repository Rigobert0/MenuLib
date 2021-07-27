package de.rigobert0.menulib.demoimpl.shopexample;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public enum OpenCommand implements CommandExecutor {
	INSTANCE;

	@Override
	public boolean onCommand(final CommandSender sender, final Command command, final String label,
							 final String[] args) {
		if (!(sender instanceof Player player)) {
			return true;
		}
		switch (args.length) {
			case 0 -> player.openInventory(new PaginatedShopMenu().getInventory());
			case 1 -> player.openInventory((
					switch (parseIntArgument(args[0])) {
						default -> new ShopMenu();
						case 1 -> new PaginatedShopMenu();
						case 2 -> new AreaPaginatedShopMenu();
					}).getInventory()
			);
		}

		return true;
	}

	private int parseIntArgument(String intString) {
		try {
			return Integer.parseInt(intString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
