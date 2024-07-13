package profess1onal.foruniversity.gameevents;

import profess1onal.foruniversity.AntsButtle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import java.io.IOException;

import static profess1onal.foruniversity.gameevents.StartGame.gameStatus;

public class BossBarEvent {
    BossBar bossBar = Bukkit.createBossBar(ChatColor.RED + "Ошибка загрузки... Error: №001", BarColor.PURPLE, BarStyle.SOLID);

    public BossBar getBossBar() {
        return bossBar;
    }

    StartGame startGame = new StartGame();

    public void addPlayer(Player player) {
        bossBar.addPlayer(player);
    }

    public void delete() {
        bossBar.removeAll();
    }

    int Task;

    public void updateStatusBar() {
        bossBar.setProgress(1.0);
        Bukkit.getScheduler().cancelTask(Task);

        final String[] message = {ChatColor.RED + "Ошибка загрузки... Error: №002"};

        switch (gameStatus) {
            case "lobby":
                message[0] = ChatColor.LIGHT_PURPLE + "Ожидание игроков...";
                break;

            case "round 1":
                Task = Bukkit.getScheduler().runTaskTimer(AntsButtle.getInstance(), new Runnable() {
                    int second = 0;
                    int min = 0;
                    double prog = 1.0;
                    double timeMin = 5;

                    @Override
                    public void run() {
                        second++;
                        if (second == 60) {
                            min++;
                            second = 0;
                        }

                        message[0] = ChatColor.LIGHT_PURPLE + "Раунд №1 " + ChatColor.GRAY + min + ":" + second;
                        prog = prog - 1.0 / (60 * timeMin);
                        bossBar.setTitle(message[0]);

                        if (min >= timeMin) {
                            prog = 1.0;
                            try {
                                startGame.updateStatusGame();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        bossBar.setProgress(prog);
                    }
                }, 0, 20).getTaskId();

                return;
            case "round 2":
                Task = Bukkit.getScheduler().runTaskTimer(AntsButtle.getInstance(), new Runnable() {
                    int second = 0;
                    int min = 0;
                    double prog = 1.0;
                    double timeMin = 5.0;

                    @Override
                    public void run() {
                        second++;
                        if (second == 60) {
                            min++;
                            second = 0;
                        }

                        message[0] = ChatColor.LIGHT_PURPLE + "Раунд №2 " + ChatColor.GRAY + min + ":" + second;
                        prog = prog - 1.0 / (60 * timeMin);
                        bossBar.setTitle("" + message[0]);

                        if (min >= timeMin) {
                            prog = 1.0;
                            try {
                                startGame.updateStatusGame();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                        bossBar.setProgress(prog);
                    }
                }, 0, 20).getTaskId();
                return;
            case "stolb":
                Task = Bukkit.getScheduler().runTaskTimer(AntsButtle.getInstance(), new Runnable() {
                    int second = 0;
                    int min = 0;
                    double prog = 1.0;
                    double timeMin = 1.0;

                    @Override
                    public void run() {
                        second++;
                        if (second == 60) {
                            min++;
                            second = 0;
                        }
                        message[0] = ChatColor.LIGHT_PURPLE + "Раунд №3 " + ChatColor.WHITE + "У кого длиннее столб??? Время строить!";
                        prog = prog - 1.0 / (60 * timeMin);
                        bossBar.setTitle("" + message[0]);
                        if (min >= timeMin) {
                            prog = 1.0;
                            try {
                                startGame.updateStatusGame();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        bossBar.setProgress(prog);
                    }
                }, 0, 20).getTaskId();

                return;

            case "shop":
                Task = Bukkit.getScheduler().runTaskTimer(AntsButtle.getInstance(), new Runnable() {
                    int second = 0;
                    int min = 0;
                    double prog = 1.0;
                    double timeMin = 1.0;

                    @Override
                    public void run() {
                        second++;
                        if (second == 60) {
                            min++;
                            second = 0;
                        }

                        prog = prog - 1.0 / (60 * timeMin);

                        message[0] = ChatColor.LIGHT_PURPLE + "Время до pVp";
                        bossBar.setTitle(message[0]);

                        if (min >= timeMin) {
                            prog = 1.0;
                            try {
                                startGame.updateStatusGame();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        bossBar.setProgress(prog);
                    }
                }, 0, 20).getTaskId();

                return;

            case "pvp":
                bossBar.removeAll();
                return;
        }
        bossBar.setTitle(message[0]);
    }
}
