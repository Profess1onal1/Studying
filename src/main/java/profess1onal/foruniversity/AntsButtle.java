package profess1onal.foruniversity;

import profess1onal.foruniversity.gameevents.BossBarEvent;
import profess1onal.foruniversity.menuteamjoin.CommandOpenMenu;
import profess1onal.foruniversity.menuteamjoin.InteractMenu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntsButtle extends JavaPlugin {
    public static AntsButtle instance;

    public static AntsButtle getInstance() {
        return instance;
    }

    public static BossBarEvent barEvent = new BossBarEvent();

    @Override
    public void onEnable() {
        instance = this;

        var team = new TeamEvent();
        team.registerTeam();

        registerClass();

        getCommand("openMenuTeam").setExecutor(new CommandOpenMenu());
        getCommand("game").setExecutor(new CommandGame());

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        for (Player player : Bukkit.getOnlinePlayers()) {
            barEvent.addPlayer(player);
        }

        barEvent.updateStatusBar();
    }

    private void registerClass() {
        var pluginManager = getServer().getPluginManager();

        pluginManager.registerEvents(new InteractMenu(), this);
        pluginManager.registerEvents(new LoinAndLeave(), this);
        pluginManager.registerEvents(new BlockBreak(), this);
        pluginManager.registerEvents(new KillAndDeath(), this);
        pluginManager.registerEvents(new PlaceBlock(), this);
    }

    @Override
    public void onDisable() {
        barEvent.delete();
    }
}
