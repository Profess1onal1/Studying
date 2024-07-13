package profess1onal.foruniversity.menuteamjoin;

import profess1onal.foruniversity.GameEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.Set;

import static profess1onal.foruniversity.TeamEvent.*;

public class MenuTeam {

    String nameMenu = "Выбор команды";
    Inventory inventory = Bukkit.createInventory(null, InventoryType.CHEST, nameMenu);

    public void openMenu(Player player) {
        addIconMenu();
        player.openInventory(inventory);
    }

    String nameRedTeam = ChatColor.GRAY + "Команда: " + ChatColor.RED + " Красных";
    String nameBlueTeam = ChatColor.GRAY + "Команда: " + ChatColor.BLUE + "Синих";
    String nameYellowTeam = ChatColor.GRAY + "Команда: " + ChatColor.YELLOW + "Желтых";
    String nameGreenTeam = ChatColor.GRAY + "Команда: " + ChatColor.GREEN + "Зеленых";
    String nameAquaTeam = ChatColor.GRAY + "Команда: " + ChatColor.AQUA + "Бирюзовых";

    private void addIconMenu() {

        var gameEvent = new GameEvent();
        ArrayList<String> lore = new ArrayList<>();
        Set<String> players = redTeam.getEntries();

        lore.add("");
        lore.set(0, ChatColor.GRAY + redTeam.getEntries().toString());
        gameEvent.addItem(Material.ENCHANTED_BOOK, nameRedTeam, lore, inventory, 1);
        lore.set(0, ChatColor.GRAY + blueTeam.getEntries().toString());
        gameEvent.addItem(Material.ENCHANTED_BOOK, nameBlueTeam, lore, inventory, 2);
        lore.set(0, ChatColor.GRAY + greenTeam.getEntries().toString());
        gameEvent.addItem(Material.ENCHANTED_BOOK, nameYellowTeam, lore, inventory, 3);
        lore.set(0, ChatColor.GRAY + yellowTeam.getEntries().toString());
        gameEvent.addItem(Material.ENCHANTED_BOOK, nameGreenTeam, lore, inventory, 4);
        lore.set(0, ChatColor.GRAY + aquaTeam.getEntries().toString());
        gameEvent.addItem(Material.ENCHANTED_BOOK, nameAquaTeam, lore, inventory, 5);
    }

    public String getNameRedTeam() {
        return nameRedTeam;
    }

    public String getNameBlueTeam() {
        return nameBlueTeam;
    }

    public String getNameYellowTeam() {
        return nameYellowTeam;
    }

    public String getNameGreenTeam() {
        return nameGreenTeam;
    }

    public String getNameAquaTeam() {
        return nameAquaTeam;
    }

    public String getNameMenu() {
        return nameMenu;
    }
}
