package profess1onal.foruniversity.gameevents;

import profess1onal.foruniversity.AntsButtle;
import profess1onal.foruniversity.GameEvent;
import profess1onal.foruniversity.TeamEvent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.structure.Mirror;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import static profess1onal.foruniversity.BlockBreak.blockCount;
import static profess1onal.foruniversity.TeamEvent.*;
import static org.bukkit.block.structure.StructureRotation.NONE;

public class StartGame {
    public static String gameStatus = "lobby";

    public String getGameStatus() {
        return gameStatus;
    }

    TeamEvent teamEvent = new TeamEvent();
    GameEvent gameEvent = new GameEvent();

    public void updateStatusGame() throws IOException {
        switch (gameStatus) {
            case "lobby":
                gameStatus = "round 1";
                teamEvent.registerNomberPlayer();

                teleportPlayerInArenaManing();
                regenArea();

                gameEvent.actionBarBlock();
                clearAllPlayerInventory();

                break;
            case "round 1":
                gameStatus = "round 2";
                teleportPlayerInArenaManing();

                regenArea();
                clearAllPlayerInventory();

                break;
            case "round 2":
                gameStatus = "stolb";
                regenAreraStobl();

                clearAllPlayerInventory();
                teleportPlayerInArenaManing();
                break;
            case "stolb":
                gameStatus = "shop";
                clearAllPlayerInventory();

                teleportPlayerInArenaManing();
                break;
            case "shop":
                gameStatus = "pvp";
                teleportPlayerInArenaManing();

                winEvent();
                break;
        }
        gameEvent.getMessageRound();
        AntsButtle.barEvent.updateStatusBar();
    }

    public void clearAllPlayerInventory() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            player.setGameMode(GameMode.SURVIVAL);

            if (gameStatus.equals("round 1")) {
                blockCount.putIfAbsent(player, 0);

                player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_SHOVEL));

                player.setInvulnerable(true);
            }
            if (gameStatus.equals("round 2")) {
                blockCount.putIfAbsent(player, 0);

                player.getInventory().addItem(new ItemStack(Material.DIAMOND_PICKAXE));
                player.getInventory().addItem(new ItemStack(Material.DIAMOND_SHOVEL));
            }
        }
    }

    public void tp(int player1, int player2) {
        int z = -1193;
        int zp = -1206;

        for (String t : redTeam.getEntries()) {
            Player player = Bukkit.getPlayer(t);
            if (teamEvent.playerGetNumber(player) == player1) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1080, 30, zp, -90, 0));
            }
            if (teamEvent.playerGetNumber(player) == player2) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1056, 52, z));
            }
        }
        z = z + 218;
        zp = zp + 218;

        for (String t : blueTeam.getEntries()) {
            Player player = Bukkit.getPlayer(t);
            if (teamEvent.playerGetNumber(player) == player1) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1080, 30, zp, -90, 0));
            }
            if (teamEvent.playerGetNumber(player) == player2) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1056, 52, z));
            }
        }

        z = z + 217;
        zp = zp + 217;

        for (String t : greenTeam.getEntries()) {
            Player player = Bukkit.getPlayer(t);
            if (teamEvent.playerGetNumber(player) == player1) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1080, 30, zp, -90, 0));
            }
            if (teamEvent.playerGetNumber(player) == player2) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1056, 52, z));
            }
        }

        z = z + 215;
        zp = zp + 216;

        for (String t : yellowTeam.getEntries()) {
            Player player = Bukkit.getPlayer(t);
            if (teamEvent.playerGetNumber(player) == player1) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1080, 30, zp, -90, 0));
            }
            if (teamEvent.playerGetNumber(player) == player2) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1056, 52, z));
            }
        }

        z = z + 215;
        zp = zp + 215;

        for (String t : aquaTeam.getEntries()) {
            Player player = Bukkit.getPlayer(t);

            if (teamEvent.playerGetNumber(player) == player1) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1080, 30, zp, -90, 0));
            }

            if (teamEvent.playerGetNumber(player) == player2) {
                assert player != null;
                player.teleport(new Location(player.getWorld(), -1056, 52, z));
            }
        }
    }

    public void teleportPlayerInArenaManing() {
        Bukkit.getScheduler().runTaskLater(AntsButtle.getInstance(), () -> {
            if (gameStatus.equals("round 1")) {
                tp(1, 2);
            }

            if (gameStatus.equals("round 2")) {
                tp(2, 1);
            }

            if (gameStatus.equals("stolb")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    blockCount.putIfAbsent(player, 0);

                    player.getInventory().addItem(new ItemStack(Material.PURPLE_WOOL, blockCount.get(player)));
                    player.teleport(new Location(player.getWorld(), -872, 21, -541));
                }
            }
            if (gameStatus.equals("shop")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.teleport(new Location(player.getWorld(), -977, 19, -760, 180, 0));
                    player.getInventory().addItem(new ItemStack(Material.PURPLE_WOOL, blockCount.get(player)));
                }
            }
            if (gameStatus.equals("pvp")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setInvulnerable(false);
                    player.teleport(new Location(player.getWorld(), -986, 5, -433));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 15 * 20, 99, false, false));
                }
            }
            Bukkit.getScheduler().runTaskLater(AntsButtle.getInstance(), () -> {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.setBedSpawnLocation(player.getLocation(), true);
                }
            }, 29);

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.ENTITY_SHULKER_TELEPORT, 1, 1);
            }
        }, 10);
    }

    public void regenArea() throws IOException {
        StructureManager mg = Bukkit.getStructureManager();
        File file = null;

        if (gameStatus.equals("round 1")) {
            file = new File(AntsButtle.getInstance().getDataFolder().getAbsolutePath() + "/stolb1.nbt");
        }

        if (gameStatus.equals("round 2")) {
            file = new File(AntsButtle.getInstance().getDataFolder().getAbsolutePath() + "/stolb2.nbt");
        }

        assert file != null;
        Structure structure = mg.loadStructure(file);

        int z = -134;
        for (int i = 0; i < 6; i++) {
            structure.place(new Location(Bukkit.getWorld("world"), -1057, 4, z), false, NONE, Mirror.NONE, 0, 1, new Random());
            Block block = Bukkit.getWorld("world").getBlockAt(-1078, 1, z + 10);
            block.setType(Material.REDSTONE_BLOCK);
            z = z - 217;
        }
    }

    public void regenAreraStobl() throws IOException {
        StructureManager mg = Bukkit.getStructureManager();
        File file = new File(AntsButtle.getInstance().getDataFolder().getAbsolutePath() + "/arena.nbt");
        Structure structure = mg.loadStructure(file);
        structure.place(new Location(Bukkit.getWorld("world"), -875, 20, -552), false, NONE, Mirror.NONE, 0, 1, new Random());
    }

    int Task;

    public void winEvent() {
        Task = Bukkit.getScheduler().runTaskTimer(AntsButtle.getInstance(), () -> {
            if (redTeam.getSize() >= 1 && blueTeam.getSize() == 0 && greenTeam.getSize() == 0 && yellowTeam.getSize() == 0 && aquaTeam.getSize() == 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String team = ChatColor.RED + "Красная команда" + ChatColor.WHITE;
                    player.sendTitle(gameEvent.getScreenGameOver(), " победила эту игру!");
                    player.sendMessage("Игра завершена! " + team + " победила эту игру!");
                    Bukkit.getScheduler().cancelTask(Task);
                }
                winVisual();
            }
            if (redTeam.getSize() == 0 && blueTeam.getSize() >= 1 && greenTeam.getSize() == 0 && yellowTeam.getSize() == 0 && aquaTeam.getSize() == 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String team = ChatColor.BLUE + "Синяя команда" + ChatColor.WHITE;
                    player.sendTitle(gameEvent.getScreenGameOver(), " победила эту игру!");
                    player.sendMessage("Игра завершена! " + team + " победила эту игру!");
                    Bukkit.getScheduler().cancelTask(Task);
                }
                winVisual();
            }
            if (redTeam.getSize() == 0 && blueTeam.getSize() == 0 && greenTeam.getSize() >= 1 && yellowTeam.getSize() == 0 && aquaTeam.getSize() == 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String team = ChatColor.GREEN + "Зеленая команда" + ChatColor.WHITE;
                    player.sendTitle(gameEvent.getScreenGameOver(), " победила эту игру!");
                    player.sendMessage("Игра завершена! " + team + " победила эту игру!");
                    Bukkit.getScheduler().cancelTask(Task);
                }
                winVisual();
            }
            if (redTeam.getSize() == 0 && blueTeam.getSize() == 0 && greenTeam.getSize() == 0 && yellowTeam.getSize() >= 1 && aquaTeam.getSize() == 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String team = ChatColor.YELLOW + "Желтая команда" + ChatColor.WHITE;
                    player.sendTitle(gameEvent.getScreenGameOver(), " победила эту игру!");
                    player.sendMessage("Игра завершена! " + team + " победила эту игру!");
                    Bukkit.getScheduler().cancelTask(Task);
                }
                winVisual();
            }
            if (redTeam.getSize() == 0 && blueTeam.getSize() == 0 && greenTeam.getSize() == 0 && yellowTeam.getSize() == 0 && aquaTeam.getSize() >= 1) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    String team = ChatColor.AQUA + "Бирюзовая команда" + ChatColor.WHITE;
                    player.sendTitle(gameEvent.getScreenGameOver(), " победила эту игру!");
                    player.sendMessage("Игра завершена! " + team + " победила эту игру!");
                    Bukkit.getScheduler().cancelTask(Task);
                }
                winVisual();
            }
        }, 0, 27).getTaskId();
    }

    public void winVisual() {
        int Task;

        Task = Bukkit.getScheduler().runTaskTimer(AntsButtle.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (!player.getGameMode().equals(GameMode.SPECTATOR)) {
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
                }
            }
        }, 0, 20).getTaskId();

        Bukkit.getScheduler().runTaskLater(AntsButtle.getInstance(), () -> {
            Bukkit.getScheduler().cancelTask(Task);
            //
        }, 5 * 20);
    }
}