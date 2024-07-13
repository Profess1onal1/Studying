package profess1onal.foruniversity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class TeamEvent {
    public static Team redTeam;
    public static Team blueTeam;
    public static Team yellowTeam;
    public static Team greenTeam;
    public static Team lobby;
    public static Team aquaTeam;

    public void registerTeam() {
        var mainScore = Bukkit.getScoreboardManager().getMainScoreboard();

        if (mainScore.getTeam("redTeam") != null) {
            mainScore.getTeam("redTeam").unregister();
        }

        if (mainScore.getTeam("blueTeam") != null) {
            mainScore.getTeam("blueTeam").unregister();
        }

        if (mainScore.getTeam("yellowTeam") != null) {
            mainScore.getTeam("yellowTeam").unregister();
        }

        if (mainScore.getTeam("greenTeam") != null) {
            mainScore.getTeam("greenTeam").unregister();
        }

        if (mainScore.getTeam("aquaTeam") != null) {
            mainScore.getTeam("aquaTeam").unregister();
        }

        if (mainScore.getTeam("lobby") != null) {
            mainScore.getTeam("lobby").unregister();
        }
    }

    private void setupTeams() {
        var mainScore = Bukkit.getScoreboardManager().getMainScoreboard();

        redTeam = mainScore.registerNewTeam("redTeam");
        blueTeam = mainScore.registerNewTeam("blueTeam");
        yellowTeam = mainScore.registerNewTeam("yellowTeam");
        greenTeam = mainScore.registerNewTeam("greenTeam");
        lobby = mainScore.registerNewTeam("lobby");
        aquaTeam = mainScore.registerNewTeam("aquaTeam");


        redTeam.setAllowFriendlyFire(false);
        blueTeam.setAllowFriendlyFire(false);
        yellowTeam.setAllowFriendlyFire(false);
        greenTeam.setAllowFriendlyFire(false);
        lobby.setAllowFriendlyFire(false);
        aquaTeam.setAllowFriendlyFire(false);

        redTeam.setPrefix(ChatColor.RED + "K | ");
        blueTeam.setPrefix(ChatColor.BLUE + "Б | ");
        yellowTeam.setPrefix(ChatColor.YELLOW + "Ж | ");
        greenTeam.setPrefix(ChatColor.GREEN + "З | ");
        lobby.setPrefix(ChatColor.GRAY + "H | ");
        aquaTeam.setPrefix(ChatColor.AQUA + "Б | ");
    }


    public static HashMap<Player, Integer> playerNumber = new HashMap<>();

    public void registerNomberPlayer() {
        int nomber = 1;
        for (String entity : redTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            playerNumber.put(player, nomber);
            nomber++;
        }

        nomber = 1;
        for (String entity : blueTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            playerNumber.put(player, nomber);
            nomber++;
        }

        nomber = 1;
        for (String entity : greenTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            playerNumber.put(player, nomber);
            nomber++;
        }

        nomber = 1;
        for (String entity : yellowTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            playerNumber.put(player, nomber);
            nomber++;
        }

        nomber = 1;
        for (String entity : aquaTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            playerNumber.put(player, nomber);
            nomber++;
        }
    }

    public int playerGetNumber(Player player) {
        if (playerNumber.get(player) == 1) {
            return 1;
        }

        if (playerNumber.get(player) == 2) {
            return 2;
        }

        return 0;
    }

    public String getPlayerTeam(Player playerCheck) {
        String team = "null";

        for (String entity : redTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            if (player.getName().equalsIgnoreCase(playerCheck.getName())) {
                team = "red";
            }
        }

        for (String entity : blueTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            if (player.getName().equalsIgnoreCase(playerCheck.getName())) {
                team = "blue";
            }
        }

        for (String entity : greenTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            if (player.getName().equalsIgnoreCase(playerCheck.getName())) {
                team = "green";
            }
        }

        for (String entity : yellowTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            if (player.getName().equalsIgnoreCase(playerCheck.getName())) {
                team = "yellow";
            }
        }

        for (String entity : aquaTeam.getEntries()) {
            Player player = Bukkit.getPlayer(entity);
            if (player.getName().equalsIgnoreCase(playerCheck.getName())) {
                team = "aqua";
            }
        }

        return team;
    }
}
