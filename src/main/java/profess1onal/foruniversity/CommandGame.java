package profess1onal.foruniversity;

import profess1onal.foruniversity.gameevents.StartGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CommandGame implements CommandExecutor {
    public int o = 0;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        if (o == 0 || player.getName().equalsIgnoreCase("X_THEBEST_")) {
            player.sendMessage("true");
            var startGame = new StartGame();

            try {
                startGame.updateStatusGame();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            o++;
        } else {
            player.sendMessage("Пропустить раунд может только Бест, у вас нет прав!");
        }
        return false;
    }
}
