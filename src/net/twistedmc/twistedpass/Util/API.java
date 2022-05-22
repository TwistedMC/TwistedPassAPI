package net.twistedmc.twistedpass.Util;

import net.twistedmc.twistedpass.Main;
//import net.twistedmc.twistedpass.MySQL;
import net.twistedmc.twistedpass.Util.color.c;
import net.twistedmc.twistedpass.events.LevelUpEvent;
import net.twistedmc.twistedpass.events.XPAddedEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import static net.twistedmc.twistedpass.Main.MySQL;
import static net.twistedmc.twistedpass.Main.season;

public class API {
    // PRIVATE API METHODS \\
    public static String fetchClaimedLevels(Player p) {
        try {
            Statement stm = MySQL.openConnection().createStatement();
            ResultSet set = stm.executeQuery("SELECT * FROM `" + season + "` WHERE `UUID`='" + p.getUniqueId() + "'");
            while(set.next()){
                return set.getString("ClaimedLevels");
            }
            MySQL.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     *
     * @param p - Player to fetch
     * @return <code>int[]</code> with slot 0 being the user's level, and slot 1 being their XP. <code>null</code> if the record doesn't exist
     */
    public static int[] fetchLevelandXP(Player p) {
        int[] LevelArray = new int[2];
        try {
            Statement stm = MySQL.openConnection().createStatement();
            ResultSet set = stm.executeQuery("SELECT * FROM `" + season + "` WHERE `UUID`='" + p.getUniqueId() + "'");
            while(set.next()){
                int level = set.getInt("Level");
                int XP = set.getInt("XP");
                LevelArray[0] = level;
                LevelArray[1] = XP;
                return LevelArray;
            }
            MySQL.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getLevelsArray(Player p) {
        String userLevels = fetchClaimedLevels(p);
        if (userLevels != null) {
           return Main.GSON.fromJson(userLevels,String[].class);
        } else {
            System.out.println("Error fetching levels for user: " + p.getName() + " | UUID: " + p.getUniqueId() + " | ERROR CODE: 404-A");
        }
      return null;
    }

    /***
     *
     * @param XP The XP you are adding, Supercharged XP Calculations are done here.
     * @param LevelsArray The array from API.fetchLevelandXP.
     * @param supercharged Used from API.addXPToPlayer, whether XP Supercharge is active or not.
     * @return an Int array with the first slot (0) being LEVELS added, and the 2nd slot (1) being returned XP.
     */
    public static int[] Calculations(int XP, int[] LevelsArray, boolean supercharged) {
        int multiplier = 1;
        int[] retuner = new int[2];
        int MaxXP = Main.MaximumXPperLevel;
        if (supercharged) { multiplier = Main.SuperchargedXPMultiplier; }
        int newXP = (XP * multiplier) + LevelsArray[1];
        int div = (newXP / MaxXP);
        if (div >= 1) {
            div = (int) Math.floor(div);
            retuner[0] = div;
            int minus =  (div * MaxXP);
            retuner[1] = (newXP - minus);
            return retuner;
        } else {
            retuner[0] = 0;
            retuner[1] = newXP;
            return retuner;
        }
    }
    // PUBLIC API METHODS \\
    public static boolean UserHasClaimedLevel(Player p,int level) {
        String[] levels = getLevelsArray(p);
        if (levels == null) {  Bukkit.getLogger().log(Level.SEVERE,"[TwistedPassAPI] GetLevelsArray returned null; This cannot be null;"); }
        List<String> userLevels = Arrays.asList(levels); // Not as small, but still nicely clean and pretty well put together. -N
        if (userLevels.contains("" + level)) { return true; }
        return false;
    }
    public static boolean UserHasProfile(Player p) {
        try {
            Statement stm = MySQL.openConnection().createStatement();
            ResultSet set = stm.executeQuery("SELECT * FROM `" + season + "` WHERE `UUID`='" + p.getUniqueId() + "'");
            while (set.next()){
             return set.getString("UUID") != null;
            }
            MySQL.closeConnection();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     *
     * @param p The player you're giving XP to.
     * @param xp The amount of XP you're giving.
     * @return boolean, True on success, False on mysql error
     */
    public static boolean addXPtoPlayer(Player p,int xp) {
        int[] levels = fetchLevelandXP(p);
        boolean Supercharged = Main.SuperchargeActive;
        XPAddedEvent xpAddedEvent = new XPAddedEvent(p,xp,Supercharged);
        Bukkit.getPluginManager().callEvent(xpAddedEvent);
        int[] newLevels = Calculations(xp,levels,Supercharged);
        if (newLevels[0] >= 1) {
            //Easier to call an event here if any listeners are present rather
            //then making a whole function or extra mess to this function to *possibly* tell a player
            //They've leveled up. That way it can be handled separately,and the event  can be modified if it needs to.
            LevelUpEvent levelUpEvent = new LevelUpEvent(p,newLevels[0]);
            Bukkit.getPluginManager().callEvent(levelUpEvent);
        }
        try {
            Statement stm = MySQL.openConnection().createStatement();
            int set = stm.executeUpdate("UPDATE `"+ season + "` SET `Level`="+newLevels[0]+",`XP`="+newLevels[1]+" WHERE `UUID`='" + p.getUniqueId() +"'");
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
    /***
     *
     * @param p Player to create a profile for.
     * @return Boolean (True if the profile was created, false if an error occurs.)
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public static boolean CreateUserProfile(Player p) {
        try {
            Statement stm = MySQL.openConnection().createStatement();
            int set = stm.executeUpdate("INSERT INTO `"+ season + "`(`id`, `UUID`, `Level`, `XP`, `ClaimedLevels`) VALUES (0,"+ p.getUniqueId() +",0,0,\"[]\")");
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            if (Main.NotifyUsersofError)  { p.sendMessage(c.red + "There was a problem creating your profile! Please contact staff! (Error Code: Err-754)"); }
            return false;
        }
    }
}
