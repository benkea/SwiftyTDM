package me.WingedMLGPro.Util;

import me.WingedMLGPro.TDMMain;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyTDM was created by 35047
 * © at 25/06/15 at 5:55 PM
 */
public class TDMScoreboard {

    public static void updateScoreboard(Player p){

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard scoreboard = (Scoreboard) manager.getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("TDM", "dummy");
        obj.setDisplayName("§6§lTeam§e§lDeath§6§lMatch");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score redkills = obj.getScore("§cRed's Kills§7:");
        redkills.setScore(TDMMain.getInstance().getConfig().getInt("Teams.Red.Kills"));
        Score bluekills = obj.getScore("§9Blue's Kills§7:");
        bluekills.setScore(TDMMain.getInstance().getConfig().getInt("Teams.Blue.Kills"));
        Score redplayers = obj.getScore("§cRed's Players§7:");
        redplayers.setScore(SwiftyTeams.getTeamSize("Red"));
        Score blueplayers = obj.getScore("§Blue's Players§7:");
        blueplayers.setScore(SwiftyTeams.getTeamSize("Blue"));
        Score Specs = obj.getScore("§eSpecs§7:");
        Specs.setScore(SwiftyTeams.getTeamSize("Spectator"));

        p.setScoreboard(scoreboard);
    }
}
