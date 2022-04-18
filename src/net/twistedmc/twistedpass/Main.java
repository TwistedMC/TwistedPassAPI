package net.twistedmc.twistedpass;

import com.google.gson.Gson;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    // CONFIGURATION
    public static String season = "season1"; // Pass season table name
    public static int SuperchargedXPMultiplier = 4;
    public static boolean SuperchargeActive = false;
    public static int MaximumXPperLevel = 500;
    // API CONFIGS
    public static boolean NotifyUsersofError = true; // This will auto inform users that have support for this if something happens. Usually on Database Rejection
    // Variables
    public static String sqlHost = "173.44.44.253";
    public static String sqlPort = "3306";
    public static String sqlDb = "twistedPass?useSSL=false";
    public static String sqlUser = "twistedPass";
    public static String sqlPw = "3jpE8i8Yw(GXUe)4";
    public static Gson GSON = new Gson();
    public static MySQL MySQL = new MySQL(sqlHost, sqlPort, sqlDb, sqlUser, sqlPw);
    public static java.sql.Connection con = null;
    //
}
