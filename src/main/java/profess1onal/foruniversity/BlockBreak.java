package profess1onal.foruniversity;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

import static profess1onal.foruniversity.TeamEvent.*;
import static profess1onal.foruniversity.gameevents.StartGame.gameStatus;
import static org.bukkit.Material.*;

public class BlockBreak implements Listener {
    public static int blockRed = 0;
    public static int blockBlue = 0;
    public static int blockGreen = 0;
    public static int blockYellow = 0;
    public static int blockAqua = 0;
    public static HashMap<Player, Integer> blockCount = new HashMap<>();

    @EventHandler
    public void playerblockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        event.setCancelled(true);
        Block block = event.getBlock();
        Material mat = block.getType();
        if (gameStatus.equalsIgnoreCase("round 1") || gameStatus.equalsIgnoreCase("round 2")) {
            if (!(mat.equals(PURPLE_WOOL) || mat.equals(SAND) || mat.equals(LIGHT_BLUE_TERRACOTTA) || mat.equals(LIGHT_GRAY_CONCRETE) || mat.equals(BLACK_TERRACOTTA)))
                return;
            if (mat.equals(PURPLE_WOOL)) {
                for (String entity : redTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    if (playerTeam.getName().equalsIgnoreCase(player.getName()))
                        blockRed++;
                }

                for (String entity : blueTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    if (playerTeam.getName().equalsIgnoreCase(player.getName()))
                        blockBlue++;
                }

                for (String entity : greenTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    if (playerTeam.getName().equalsIgnoreCase(player.getName()))
                        blockGreen++;
                }

                for (String entity : yellowTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    if (playerTeam.getName().equalsIgnoreCase(player.getName()))
                        blockYellow++;
                }

                for (String entity : aquaTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    if (playerTeam.getName().equalsIgnoreCase(player.getName()))
                        blockAqua++;
                }

                blockCount.putIfAbsent(player, 0);
                blockCount.put(player, blockCount.get(player) + 1);

                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                block.getWorld().spawnParticle(Particle.END_ROD, block.getLocation().add(0.5, 0.5, 0.5), 20);

            }
            if (!mat.equals(PURPLE_WOOL))
                player.getInventory().addItem(new ItemStack(mat));
            if (block.getRelative(BlockFace.UP).getType().equals(SAND)) {
                block.getRelative(BlockFace.UP).setType(STONE);
                block.getRelative(BlockFace.UP).setType(SAND);
            }

            block.setType(AIR);
        }
    }
}
