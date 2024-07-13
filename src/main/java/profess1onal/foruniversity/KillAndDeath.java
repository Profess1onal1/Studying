package profess1onal.foruniversity;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;

import static profess1onal.foruniversity.TeamEvent.lobby;
import static profess1onal.foruniversity.gameevents.StartGame.gameStatus;

public class KillAndDeath implements Listener {
    GameEvent gameEvent = new GameEvent();
    public HashMap<Player, Integer> deathCount = new HashMap<>();

    @EventHandler
    public void death(PlayerDeathEvent event) {
        if (gameStatus.equalsIgnoreCase("pvp")) {
            event.setDeathMessage(null);

            deathCount.putIfAbsent(event.getPlayer(), 2);

            if (event.getEntity().getKiller() == null)
                return;

            Player player = event.getPlayer();
            Player killer = event.getEntity().getKiller();

            event.setDeathMessage("⁾ Игрок " + player.getCustomName() + ChatColor.WHITE + " был попущен игроком " + killer.getCustomName());

            deathCount.put(player, deathCount.get(player) - 1);

            if (deathCount.get(player) == 0) {
                player.setCustomName(ChatColor.DARK_GRAY + "H " + player.getName());
                lobby.addPlayer(player);

                for (int i = 0; i < player.getInventory().getContents().length; i++) {
                    if (player.getInventory().getItem(i) != null) {
                        player.getWorld().dropItem(player.getLocation(), new ItemStack(player.getInventory().getItem(i)));
                    }
                }
            }
        }
    }

    @EventHandler
    public void respawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 5 * 20, 99, false, false));

        if (gameStatus.equalsIgnoreCase("pvp")) {
            if (deathCount.get(player) >= 1) {
                player.sendTitle("ɘ", "У вас есть 5 секунд бессмертия");
            } else {
                player.sendTitle("ɜ", "");
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
    }
}
