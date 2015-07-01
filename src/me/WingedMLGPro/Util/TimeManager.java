package me.WingedMLGPro.Util;

import me.WingedMLGPro.TDMMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyTDM was created by 35047
 * © at 25/06/15 at 4:56 PM
 */
public class TimeManager implements Runnable {


    @Override
    public void run() {

        int MIN_PLAYERS = 12;
        int LOBBY_SECS = 120; //2 Mins
        int INGAME_SECS = 1200; //20 Mins
        int ENDED_SECS = 11; //11 Secs

        if (Manager.getTicks() > 0){
            Manager.setTicks(Manager.getTicks() - 1);
        }
        if (Manager.getState().equals(GameState.LOBBY)){
            if (Manager.getTicks()==120 || Manager.getTicks()==100 || Manager.getTicks()==80 || Manager.getTicks()==60 || Manager.getTicks()==40
                    || Manager.getTicks()==20){
                Bukkit.broadcastMessage(TDMMain.TDMTag+"There is §a"+Manager.formatTime(Manager.getTicks())+"§b left until the game starts...");
            }
            if (Manager.getTicks()==0){
                if (Bukkit.getOnlinePlayers().size()>=MIN_PLAYERS){
                    Manager.setTicks(INGAME_SECS);
                    Manager.setState(GameState.INGAME);
                }
                if (Bukkit.getOnlinePlayers().size()<MIN_PLAYERS){
                    Manager.setTicks(LOBBY_SECS);
                    Bukkit.broadcastMessage(TDMMain.TDMTag+"The Game requires §a6 §bPlayers to start!");
                }
            }
        }
        if (Manager.getState().equals(GameState.INGAME)){

        }
        if (Manager.getState().equals(GameState.ENDED)){
            if (Manager.getTicks() > 2){
                Bukkit.broadcastMessage(TDMMain.TDMTag+"This Server is restarting in "+Manager.formatTime(Manager.getTicks()));
            }
            if (Manager.getTicks()== 2) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    Bukkit.getMessenger().registerOutgoingPluginChannel(TDMMain.getInstance(), "BungeeCord");
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    try {
                        out.writeUTF("Connect");
                        out.writeUTF("HB1");
                    } catch (IOException ex) {
                    }
                    all.sendPluginMessage(TDMMain.getInstance(), "BungeeCord", b.toByteArray());
                }
            }
            if (Manager.getTicks()== 1){
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "Restart");
            }
        }
    }
}
