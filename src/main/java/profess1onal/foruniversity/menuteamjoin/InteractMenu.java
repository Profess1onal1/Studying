package profess1onal.foruniversity.menuteamjoin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import static profess1onal.foruniversity.TeamEvent.*;

public class InteractMenu implements Listener {
    @EventHandler
    public void menuclick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getCurrentItem() == null) {
            return;
        }

        var menu = new MenuTeam();
        if (!event.getView().getTitle().equalsIgnoreCase(menu.getNameMenu()))
            return;

        event.setCancelled(true);

        String team = ChatColor.RED + "| Error: ошибка загрузки вывода команды, напишите мне для решения |";
        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(menu.getNameRedTeam())) {
            if (redTeam.getEntries().size() < 2) {
                redTeam.addPlayer(player);
                player.setCustomName(ChatColor.RED + "K | " + player.getName());
                team = ChatColor.RED + "Красную";
            } else {
                player.sendMessage(ChatColor.RED + "Команда заполнена");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(menu.getNameBlueTeam())) {
            if (blueTeam.getEntries().size() < 2) {
                player.setCustomName(ChatColor.BLUE + "Б | " + player.getName());
                blueTeam.addPlayer(player);
                team = ChatColor.BLUE + "Синую";
            } else {
                player.sendMessage(ChatColor.RED + "Команда заполнена");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(menu.getNameGreenTeam())) {
            if (greenTeam.getEntries().size() < 2) {
                greenTeam.addPlayer(player);
                team = ChatColor.GREEN + "Зеленую";
                player.setCustomName(ChatColor.RED + "З | " + player.getName());

            } else {
                player.sendMessage(ChatColor.GREEN + "Команда заполнена");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(menu.getNameYellowTeam())) {
            if (yellowTeam.getEntries().size() < 2) {
                yellowTeam.addPlayer(player);
                team = ChatColor.YELLOW + "Желтую";
                player.setCustomName(ChatColor.YELLOW + "Ж | " + player.getName());

            } else {
                player.sendMessage(ChatColor.RED + "Команда заполнена");
                return;
            }
        }

        if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(menu.getNameAquaTeam())) {
            if (aquaTeam.getEntries().size() < 2) {
                aquaTeam.addPlayer(player);
                team = ChatColor.AQUA + "Бирюзовую";
                player.setCustomName(ChatColor.AQUA + "Б | " + player.getName());

            } else {
                player.sendMessage(ChatColor.RED + "Команда заполнена");
                return;
            }
        }

        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
        for (Player playerOnline : Bukkit.getOnlinePlayers()) {
            playerOnline.sendMessage("Игрок " + ChatColor.GOLD + player.getName() + ChatColor.WHITE + " зашел за " + team + ChatColor.WHITE + " команду");
        }

        player.closeInventory();
    }
}
