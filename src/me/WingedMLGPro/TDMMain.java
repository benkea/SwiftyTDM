package me.WingedMLGPro;

import me.WingedMLGPro.Util.GameState;
import me.WingedMLGPro.Util.Manager;
import me.WingedMLGPro.Util.TimeManager;
import me.dylzqn.Utils.SwiftyTeams;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyTDM was created by 35047
 * © at 25/06/15 at 4:50 PM
 */
public class TDMMain extends JavaPlugin {

    public static String TDMTag = "§6§lT§e§lD§6§lM§7: §e";
    public static String Error = "§c§l/§4§l/ §6";
    public static String CantFindPlayer = Error+"Sorry, but we could not find the player ";
    public static String NoCONSOLE = Error+"Sorry, But the Console can't run this command!";
    public static String TAG = "§6§lSwi§e§lfty§6§l>§e§l>§r ";
    public static String TAG2 = "§6§l/§e§l/ ";
    public static String Arrows = "§e§l>§6§l> ";
    public static String SwiftyAnnounment = "§6§lSwi§e§lfty§6§l>§e§l>§r ";
    public static String PermCONSOLE = "§c§l/§4§l/ §cYou are not as cool as a Console, so stop trying to use this command!";
    public static String PermOWNER = "§c§l/§4§l/ §cYou are not as cool as a Owner, so stop trying to use this command!";
    public static String PermHEADADMIN = "§c§l/§4§l/ §cYou are not as cool as a HeadAdmin, so stop trying to use this command!";
    public static String PermAdmin = "§c§l/§4§l/ §cYou are not as cool as a Admin, so stop trying to use this command!";
    public static String PermMOD = "§c§l/§4§l/ §cYou are not as cool as a Mod, so stop trying to use this command!";
    public static String PermVIP = "§c§l/§4§l/ §cYou are not as cool as a VIP, so stop trying to use this command!";
    public static String PermHELPER = "§c§l/§4§l/ §cYou are not as cool as a Helper, so stop trying to use this command!";
    public static String PermIMMORTAL = "§c§l/§4§l/ §cYou are not as cool as a Immortal, so stop trying to use this command!";
    public static String PermLEGEND = "§c§l/§4§l/ §cYou are not as cool as a Legend, so stop trying to use this command!";
    public static String PermELITE = "§c§l/§4§l/ §cYou are not as cool as a Elite, so stop trying to use this command!";
    public static String PermHERO = "§c§l/§4§l/ §cYou are not as cool as a Hero, so stop trying to use this command!";
    public static String PermSergeant = "§c§l/§4§l/ §cYou are not as cool as a Sergeant, so stop trying to use this command!";

    private static TDMMain instance;

    public static TDMMain getInstance() {
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;

        SwiftyTeams.addTeam("Red");
        SwiftyTeams.addTeam("Blue");
        SwiftyTeams.addTeam("Spectator");

        Manager.setState(GameState.LOBBY);
        Manager.setTicks(120);

        Bukkit.getServer().getScheduler().runTaskTimer(this, new TimeManager(), 20L, 20L);

        getConfig().options().copyDefaults(true);
        saveConfig();

        getConfig().set("Teams.Red.Kills", 0);
        getConfig().set("Teams.Blue.Kills", 0);
        saveConfig();
    }
    @Override
    public void onDisable(){
        instance = null;
    }

}
