package me.WingedMLGPro.Listeners;

import me.WingedMLGPro.TDMMain;
import me.WingedMLGPro.Util.GameState;
import me.WingedMLGPro.Util.Manager;
import me.WingedMLGPro.Util.TDMScoreboard;
import me.dylzqn.PlayerAPI.SwiftyPlayer;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Random;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyTDM was created by 35047
 * © at 25/06/15 at 5:37 PM
 */
public class UtilEvents implements Listener {

    int redKills = TDMMain.getInstance().getConfig().getInt("Teams.Red.Kills");
    int blueKills = TDMMain.getInstance().getConfig().getInt("Teams.Blue.Kills");

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        e.setCancelled(true);
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            SwiftyPlayer user = new SwiftyPlayer(p);
            if (Manager.getState() == GameState.LOBBY || Manager.getState() == GameState.ENDED || SwiftyTeams.inTeam("Spectator", user)) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onDeathEvent(PlayerDeathEvent e){
        if (e.getEntity() instanceof Player){
            Player p = (Player)e.getEntity();
            if (p.getKiller() instanceof Player){
                Player k = p.getKiller();
                SwiftyPlayer killer = new SwiftyPlayer(k);
                if (SwiftyTeams.inTeam("Red", killer)) {
                    TDMMain.getInstance().getConfig().set("Teams.Red.Kills", redKills + 1);
                    TDMMain.getInstance().saveConfig();
                    TDMScoreboard.updateScoreboard(p);
                }
                if (SwiftyTeams.inTeam("Blue", killer)) {
                    TDMMain.getInstance().getConfig().set("Teams.Blue.Kills", blueKills + 1);
                    TDMMain.getInstance().saveConfig();
                    TDMScoreboard.updateScoreboard(p);
                }
                if (redKills >= 100 && blueKills != 100){
                    Bukkit.broadcastMessage(TDMMain.TDMTag+"The §cRed §eTeam has won TeamDeathMatch!");
                    Manager.setState(GameState.ENDED);
                    Manager.setTicks(11);
                }
                if (blueKills >= 100 && redKills != 100){
                    Bukkit.broadcastMessage(TDMMain.TDMTag+"The §9Blue §eTeam has won TeamDeathMatch!");
                    Manager.setState(GameState.ENDED);
                    Manager.setTicks(11);
                }
                if (blueKills >= 100 && redKills >= 100){
                    Bukkit.broadcastMessage(TDMMain.TDMTag+"The game has been Tied!");
                    Manager.setState(GameState.ENDED);
                    Manager.setTicks(11);
                }
            }
        }
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        if (SwiftyTeams.inTeam("Red", user)){
            SwiftyTeams.removePlayer("Red", user);
        }
        if (SwiftyTeams.inTeam("Blue", user)){
            SwiftyTeams.removePlayer("Blue", user);
        }
        if (SwiftyTeams.inTeam("Spectator", user)){
            SwiftyTeams.removePlayer("Spectator", user);
        }
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        SwiftyPlayer user = new SwiftyPlayer(p);
        TDMScoreboard.updateScoreboard(p);
        if (Manager.getState() == GameState.LOBBY) {
            if (SwiftyTeams.getTeamSize("Red") > SwiftyTeams.getTeamSize("Blue")) {
                SwiftyTeams.addPlayer("Blue", user);
                user.setPlayerListName("§9" + p.getName());
            }
            if (SwiftyTeams.getTeamSize("Blue") > SwiftyTeams.getTeamSize("Red")) {
                SwiftyTeams.addPlayer("Red", user);
                user.setPlayerListName("§c" + p.getName());
            }
            if (SwiftyTeams.getTeamSize("Red") == SwiftyTeams.getTeamSize("Blue")) {
                int chance = new Random().nextInt(50) + 1;

                if (chance >= 0 && chance <= 25) {
                    SwiftyTeams.addPlayer("Red", user);
                    user.setPlayerListName("§c" + p.getName());
                }
                if (chance >= 25 && chance <= 50) {
                    SwiftyTeams.addPlayer("Blue", user);
                    user.setPlayerListName("§9" + p.getName());
                }
            }
        }
        else if (Manager.getState()!=GameState.LOBBY){
            SwiftyTeams.addPlayer("Spectator", user);
            user.setGameMode(GameMode.SPECTATOR);
        }
    }
}
