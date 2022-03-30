package net.twistedmc.twistedpass.Util;

import net.twistedmc.twistedpass.Main;
import net.twistedmc.twistedpass.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

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

    public static String[] getLevelsArray(Player p) {
        String userLevels = fetchClaimedLevels(p);
        if (userLevels != null) {
           return Main.GSON.fromJson(userLevels,String[].class);
        } else {
            System.out.println("Error fetching levels for user: " + p.getName() + " | UUID: " + p.getUniqueId() + " | ERROR CODE: 404-A");
        }
      return null;
    }
    // PUBLIC API METHODS \\
    public static boolean UserHasClaimedLevel(Player p,int level) {
        List<String> userLevels = Arrays.asList(getLevelsArray(p));
        if (userLevels == null) { Bukkit.getLogger().log(Level.SEVERE,"[TwistedPassAPI] Error"); } // Clean :)
        if (userLevels.contains("" + level)) { return true; }
        return false;
    }
    public static boolean UserHasProfile(Player p) {

    }
}
