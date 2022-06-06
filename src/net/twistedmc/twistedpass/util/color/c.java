package net.twistedmc.twistedpass.util.color;

import org.bukkit.ChatColor;

public class c {

    public static String head = ChatColor.AQUA.toString();
    public static String scramble = ChatColor.MAGIC.toString();
    public static String bold = ChatColor.BOLD.toString();
    public static String strike = ChatColor.STRIKETHROUGH.toString();
    public static String line = ChatColor.UNDERLINE.toString();
    public static String italics = ChatColor.ITALIC.toString();
    public static String reset = ChatColor.WHITE.toString();
    public static String aqua = ChatColor.AQUA.toString();
    public static String black = ChatColor.BLACK.toString();
    public static String blue = ChatColor.BLUE.toString();
    public static String daqua = ChatColor.DARK_AQUA.toString();
    public static String dblue = ChatColor.DARK_BLUE.toString();
    public static String dgray = ChatColor.DARK_GRAY.toString();
    public static String dgreen = ChatColor.DARK_GREEN.toString();
    public static String dpurple = ChatColor.DARK_PURPLE.toString();
    public static String dred = ChatColor.DARK_RED.toString();
    public static String gold = ChatColor.GOLD.toString();
    public static String gray = ChatColor.GRAY.toString();
    public static String green = ChatColor.GREEN.toString();
    public static String purple = ChatColor.LIGHT_PURPLE.toString();
    public static String red = ChatColor.RED.toString();
    public static String white = ChatColor.WHITE.toString();
    public static String yellow = ChatColor.YELLOW.toString();
    public static String split = "?";
    public static String Head = ChatColor.AQUA.toString();
    public static String Scramble = ChatColor.MAGIC.toString();
    public static String Bold = ChatColor.BOLD.toString();
    public static String Strike = ChatColor.STRIKETHROUGH.toString();
    public static String Line = ChatColor.UNDERLINE.toString();
    public static String Italics = ChatColor.ITALIC.toString();
    public static String Reset = ChatColor.WHITE.toString();
    public static String Aqua = ChatColor.AQUA.toString();
    public static String Black = ChatColor.BLACK.toString();
    public static String Blue = ChatColor.BLUE.toString();
    public static String DAqua = ChatColor.DARK_AQUA.toString();
    public static String DBlue = ChatColor.DARK_BLUE.toString();
    public static String DGray = ChatColor.DARK_GRAY.toString();
    public static String DGreen = ChatColor.DARK_GREEN.toString();
    public static String DPurple = ChatColor.DARK_PURPLE.toString();
    public static String DRed = ChatColor.DARK_RED.toString();
    public static String Gold = ChatColor.GOLD.toString();
    public static String Gray = ChatColor.GRAY.toString();
    public static String Green = ChatColor.GREEN.toString();
    public static String Purple = ChatColor.LIGHT_PURPLE.toString();
    public static String Red = ChatColor.RED.toString();
    public static String White = ChatColor.WHITE.toString();
    public static String Yellow = ChatColor.YELLOW.toString();
    public static String Split = "?";

    public static String strip(String t) {
        return ChatColor.stripColor(t);
    }

    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
