package profess1onal.foruniversity;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static profess1onal.foruniversity.gameevents.StartGame.gameStatus;
import static org.bukkit.Material.*;

public class PlaceBlock implements Listener {
    TeamEvent teamEvent = new TeamEvent();

    @EventHandler
    public void placeBlockPlayer(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();

        event.setCancelled(true);

        Material otherBlock = block.getType();
        if (otherBlock.equals(SAND) || otherBlock.equals(LIGHT_BLUE_TERRACOTTA) || otherBlock.equals(LIGHT_GRAY_CONCRETE) || otherBlock.equals(BLACK_TERRACOTTA)) {
            event.setCancelled(false);
        }
        if (gameStatus.equalsIgnoreCase("stolb")) {
            if (!block.getType().equals(Material.PURPLE_WOOL))
                return;
            Block blockCheck = Bukkit.getWorld(block.getWorld().getUID()).getBlockAt(block.getLocation().getBlockX(), 19, block.getLocation().getBlockZ());

            if (teamEvent.getPlayerTeam(player).equalsIgnoreCase("red")) {
                if (blockCheck.getType().equals(PURPLE_WOOL) &&
                        blockCheck.getRelative(1, 0, 0).getType().equals(Material.RED_CONCRETE_POWDER)) {
                    event.setCancelled(false);
                }
            }

            if (teamEvent.getPlayerTeam(player).equalsIgnoreCase("blue")) {
                if (blockCheck.getRelative(BlockFace.EAST).getType().equals(Material.BLUE_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.NORTH).getType().equals(Material.BLUE_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.WEST).getType().equals(Material.BLUE_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.SOUTH).getType().equals(Material.BLUE_CONCRETE_POWDER)) {
                    event.setCancelled(false);
                }
            }

            if (teamEvent.getPlayerTeam(player).equalsIgnoreCase("green")) {
                if (blockCheck.getRelative(BlockFace.EAST).getType().equals(Material.LIME_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.NORTH).getType().equals(Material.LIME_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.WEST).getType().equals(Material.LIME_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.SOUTH).getType().equals(Material.LIME_CONCRETE_POWDER)) {
                    event.setCancelled(false);

                }
            }

            if (teamEvent.getPlayerTeam(player).equalsIgnoreCase("yellow")) {
                if (blockCheck.getRelative(BlockFace.EAST).getType().equals(Material.YELLOW_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.NORTH).getType().equals(Material.YELLOW_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.WEST).getType().equals(Material.YELLOW_CONCRETE_POWDER) &&
                        blockCheck.getRelative(BlockFace.SOUTH).getType().equals(Material.YELLOW_CONCRETE_POWDER)) {
                    event.setCancelled(false);

                }
            }

            if (teamEvent.getPlayerTeam(player).equalsIgnoreCase("aqua")) {
                if (blockCheck.getRelative(BlockFace.EAST).getType().equals(Material.LIGHT_BLUE_CONCRETE) &&
                        blockCheck.getRelative(BlockFace.NORTH).getType().equals(Material.LIGHT_BLUE_CONCRETE) &&
                        blockCheck.getRelative(BlockFace.WEST).getType().equals(Material.LIGHT_BLUE_CONCRETE) &&
                        blockCheck.getRelative(BlockFace.SOUTH).getType().equals(Material.LIGHT_BLUE_CONCRETE)) {
                    event.setCancelled(false);

                }
            }
        }
    }
}
