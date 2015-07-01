package me.WingedMLGPro.Util;

/**
 * © This Document and Code is STRICTLY copyrited(©) to Ben.
 * © If anyone takes any part of this code and uses it for
 * © Something public, Share with someone, uses it for API's,
 * © implementing it to their code and taking the rights for
 * © it is NOT allowed unless you get permission from me!
 * © This project SwiftyTDM was created by 35047
 * © at 25/06/15 at 4:55 PM
 */
public class Manager {

    private static GameState state;
    private static int ticks;

    public static GameState getState() {
        return state;
    }

    public static void setState(GameState state) {
        Manager.state = state;
    }

    public static int getTicks() {
        return ticks;
    }

    public static void setTicks(int ticks) {
        Manager.ticks = ticks;
    }

    public static String formatTime(int seconds){

        int hours = seconds/ 3600;
        int remainder = seconds % 3600;
        int mins = remainder / 60;
        int secs = remainder % 60;

        if (hours > 0){
            return hours+" hrs and "+mins+" mins and "+secs+" secs";
        }
        else  if (mins > 0){
            return mins+" mins and "+secs+" secs";
        }
        else{
            return String.valueOf(secs+" secs");
        }
    }
}
