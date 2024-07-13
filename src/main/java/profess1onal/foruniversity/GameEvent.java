package profess1onal.foruniversity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

import static profess1onal.foruniversity.BlockBreak.*;
import static profess1onal.foruniversity.TeamEvent.*;
import static profess1onal.foruniversity.gameevents.StartGame.gameStatus;

public class GameEvent {
    public void addItem(Material material, String displayName, ArrayList<String> list, org.bukkit.inventory.Inventory inventory, Integer slot) {
        ItemStack itemStack = new ItemStack(material);

        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore(list);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(slot, itemStack);
    }

    int Task2;

    public void actionBarBlock() {
        Task2 = Bukkit.getScheduler().runTaskTimer(AntsButtle.getInstance(), () -> {
            if (gameStatus.equals("round 1") || gameStatus.equals("round 2") || gameStatus.equals("stolb")) {
                for (String entity : redTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    playerTeam.sendActionBar("グБлоки команды: " + ChatColor.LIGHT_PURPLE + blockRed + ChatColor.WHITE + " | ʚУ вас блоков: " + ChatColor.DARK_PURPLE + blockCount.get(playerTeam));
                }

                for (String entity : blueTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    playerTeam.sendActionBar("グБлоки команды: " + ChatColor.LIGHT_PURPLE + blockBlue + ChatColor.WHITE + " | ʚУ вас блоков: " + ChatColor.DARK_PURPLE + blockCount.get(playerTeam));
                }

                for (String entity : greenTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    playerTeam.sendActionBar("グБлоки команды: " + ChatColor.LIGHT_PURPLE + blockGreen + ChatColor.WHITE + " | ʚУ вас блоков: " + ChatColor.DARK_PURPLE + blockCount.get(playerTeam));

                }

                for (String entity : yellowTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    playerTeam.sendActionBar("グ Блоки команды: " + ChatColor.LIGHT_PURPLE + blockYellow + ChatColor.WHITE + " | ʚУ вас блоков: " + ChatColor.DARK_PURPLE + blockCount.get(playerTeam));

                }

                for (String entity : aquaTeam.getEntries()) {
                    Player playerTeam = Bukkit.getPlayer(entity);
                    playerTeam.sendActionBar("グБлоки команды: " + ChatColor.LIGHT_PURPLE + blockAqua + ChatColor.WHITE + " | ʚУ вас блоков: " + ChatColor.DARK_PURPLE + blockCount.get(playerTeam));

                }
            }
            if (gameStatus.equals("shop")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    int blockMONEY = 0;

                    for (int i = 0; i < player.getInventory().getContents().length; i++) {
                        if (player.getInventory().getItem(i) != null) {
                            if (player.getInventory().getItem(i).getType().equals(Material.PURPLE_WOOL)) {
                                blockMONEY = blockMONEY + player.getInventory().getItem(i).getAmount();
                            }
                        }
                    }
                    player.sendActionBar("ʚУ вас блоков: " + ChatColor.DARK_PURPLE + blockMONEY);
                }
            }
        }, 0, 20).getTaskId();
    }

    TeamEvent teamEvent = new TeamEvent();

    public void getMessageRound() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(getScreenBlackDispay(), "", 5, 7, 9);
        }

        Bukkit.getScheduler().runTaskLater(AntsButtle.getInstance(), () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (gameStatus.equals("round 1")) {
                    player.playSound(player.getLocation(), Sound.EVENT_RAID_HORN, 100, 2);
                    player.sendTitle(ChatColor.LIGHT_PURPLE + "Первый раунд", "Действуйте сообща чтобы победить!");
                    if (teamEvent.playerGetNumber(player) == 1) {
                        player.sendMessage("у вас есть 5 минут чтобы выкопать!");
                        player.sendMessage(ChatColor.GOLD + "Удачи!" + ChatColor.WHITE + " Вам нужно выкопать " + ChatColor.LIGHT_PURPLE + "Фиолетовый блок!");
                        break;
                    }
                    if (teamEvent.playerGetNumber(player) == 2) {
                        player.sendMessage("у вас есть 5 минут чтобы подсказывать!");
                        player.sendMessage(ChatColor.GOLD + "Удачи!" + ChatColor.WHITE + " Вам нужно подсказывать вашему другу! Он должен выкопать как можно больше " + ChatColor.LIGHT_PURPLE + "Фиолетовых блоков!");
                    }
                }

                if (gameStatus.equals("round 2")) {
                    player.playSound(player.getLocation(), Sound.EVENT_RAID_HORN, 100, 2);
                    player.sendTitle(ChatColor.LIGHT_PURPLE + "Второй раунд", "Действуйте сообща чтобы победить!");
                    if (teamEvent.playerGetNumber(player) == 2) {
                        player.sendMessage("у вас есть 5 минут чтобы подсказывать!");
                        player.sendMessage(ChatColor.GOLD + "Удачи!" + ChatColor.WHITE + " Вам нужно подсказывать вашему другу! Он должен выкопать как можно больше " + ChatColor.LIGHT_PURPLE + "Фиолетовых блоков!");
                    }
                    if (teamEvent.playerGetNumber(player) == 1) {
                        player.sendMessage("у вас есть 5 минут чтобы выкопать!");
                        player.sendMessage(ChatColor.GOLD + "Удачи!" + ChatColor.WHITE + " Вам нужно выкопать " + ChatColor.LIGHT_PURPLE + "Фиолетовый блок!");
                    }
                }

                if (gameStatus.equals("stolb")) {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 100, 0);
                    player.sendTitle(ChatColor.LIGHT_PURPLE + "Третий раунд", "Стройте свои столбы! У кого больше?!");
                    player.sendMessage(ChatColor.GOLD + "Стройтесь верх! Посмотрим у кого длиннее столб!");

                    Bukkit.getScheduler().runTaskLater(AntsButtle.getInstance(), () -> {
                        getWinnerStolb(player);
                    }, 20 * 55);
                }

                if (gameStatus.equals("shop")) {
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 100, 1);
                    player.sendTitle(ChatColor.LIGHT_PURPLE + "Время закупки", "У вас есть 1 минута чтобы купить себе вещи для пвп");
                }

                if (gameStatus.equals("pvp")) {
                    player.playSound(player.getLocation(), Sound.EVENT_RAID_HORN, 100, 1);
                    player.sendTitle(ChatColor.LIGHT_PURPLE + "PVP", ChatColor.RED + "У вас есть 1 жизнь" + ChatColor.WHITE + " Убейте всех!");
                    player.sendMessage(ChatColor.GOLD + "15 секунд мирного времени!");
                    Bukkit.getScheduler().runTaskLater(AntsButtle.getInstance(), () -> {
                        player.sendMessage(ChatColor.GOLD + "Мирное время окончено!!! Время для уличной драки =)");
                    }, 20 * 15);
                }
            }
        }, 10 + 3 + 9);
    }

    String screen = ChatColor.BLACK + "⌂";
    String gameStart = ChatColor.WHITE + "⨋";
    //ʚɘ
    String death = ChatColor.WHITE + "ɜ";
    String gameover = ChatColor.WHITE + "Ĝ";

    public String getScreenBlackDispay() {
        return screen;
    }

    public String getScreenGameOver() {
        return gameover;
    }

    public void getWinnerStolb(Player player) {
        HashMap<String, Integer> hashBlock = new HashMap<>();
        hashBlock.put(ChatColor.RED + "Красных - ", blockRed);
        hashBlock.put(ChatColor.BLUE + "Синих - ", blockBlue);
        hashBlock.put(ChatColor.YELLOW + "Зеленых - ", blockGreen);
        hashBlock.put(ChatColor.GREEN + "Желтых - ", blockYellow);
        hashBlock.put(ChatColor.AQUA + "Бирюзовых - ", blockAqua);

        List<Map.Entry<String, Integer>> blocks = new ArrayList<>(hashBlock.entrySet());
        blocks.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));

        List<Map.Entry<String, Integer>> top5 = blocks.subList(0, 5);

        player.sendTitle(ChatColor.YELLOW + "Раунд завершен!", "Победила команда " + top5.get(0).getKey() + top5.get(0).getValue());
        player.sendMessage("Самый высокий столб у команды " + top5.get(0).getKey() + top5.get(0).getValue());
        player.playSound(player.getLocation(), Sound.EVENT_RAID_HORN, 100, 2);

        for (int i = 1; i < top5.size(); i++) {
            Map.Entry<String, Integer> top = top5.get(i);
            player.sendMessage("" + ChatColor.GOLD + "№" + (i + 1) + ChatColor.WHITE + " Команда " + top.getKey() + ChatColor.WHITE + top.getValue());

        }
    }
}
