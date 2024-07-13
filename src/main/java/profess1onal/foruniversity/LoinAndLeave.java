package profess1onal.foruniversity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static profess1onal.foruniversity.AntsButtle.barEvent;
import static profess1onal.foruniversity.TeamEvent.lobby;
import static profess1onal.foruniversity.gameevents.StartGame.gameStatus;

public class LoinAndLeave implements Listener {
    @EventHandler
    public void joinPlayer(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        lobby.addPlayer(player);
        player.teleport(new Location(player.getWorld(), -872, 21, -561));
        barEvent.addPlayer(player);
        player.setCustomName(ChatColor.GRAY + "H | " + player.getName());
        if (gameStatus.equals("lobby"))
            event.setJoinMessage("Игрок " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " присоединился к игрке " + ChatColor.GRAY + "[" + Bukkit.getOnlinePlayers().size() + "/10]");
    }

    @EventHandler
    public void leavePlayer(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        lobby.addPlayer(player);
        player.setCustomName(ChatColor.GRAY + "H | " + player.getName());

        if (gameStatus.equals("lobby"))
            event.setQuitMessage("Игрок " + ChatColor.YELLOW + player.getName() + ChatColor.WHITE + " покинул игру " + ChatColor.GRAY + "[" + Bukkit.getOnlinePlayers().size() + "/10]");
    }
}
