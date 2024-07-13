package profess1onal.foruniversity.menuteamjoin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.bukkit.Sound.BLOCK_CHEST_OPEN;

public class CommandOpenMenu implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        var menu = new MenuTeam();
        menu.openMenu((Player) sender);
        Player player = (Player) sender;
        player.playSound(player.getLocation(), BLOCK_CHEST_OPEN, 1, 2);
        return false;
    }
}
