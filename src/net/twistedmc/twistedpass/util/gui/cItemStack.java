package net.twistedmc.twistedpass.util.gui;

import net.twistedmc.twistedpass.util.color.c;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class cItemStack extends ItemStack {

    public cItemStack(ItemStack itemStack) {
        super(itemStack);
    }

    public cItemStack(Material material) {
        super(material);
    }

    public cItemStack(Material material, int amount) {
        super(material, amount);
    }

    public cItemStack(Material material, int amount, short data) {
        super(material, amount, data);
    }

    public cItemStack(Material material, int amount, short data, String s) {
        super(material, amount, data);

        this.setDisplayName(s);
    }

    public cItemStack(ItemStack itemStack, int pointsAmount) {
        super(itemStack);
        this.setAmount(pointsAmount);
    }

    // Creates a player head item stack with a specific
    // skin determined by the player name you give it
    public cItemStack(String playerName, String s) {
        super(Material.SKULL_ITEM, 1, (short) 3);

        SkullMeta im = (SkullMeta) super.getItemMeta();
        im.setOwner(playerName);
        im.setDisplayName(s);

        super.setItemMeta(im);
    }

    public cItemStack(Material material, String s) {
        super(material);

        ItemMeta im = super.getItemMeta();
        im.setDisplayName(s);
        super.setItemMeta(im);
    }

    public cItemStack(Material material, String s, String... lore) {
        super(material);

        ItemMeta im = super.getItemMeta();
        im.setDisplayName(s);
        im.setLore(Arrays.asList(lore));
        super.setItemMeta(im);
    }

    public cItemStack setDisplayName(String s) {
        ItemMeta im = super.getItemMeta();
        im.setDisplayName(s);
        super.setItemMeta(im);

        return this;
    }

    public cItemStack setLore(String... lore) {
        ItemMeta im = super.getItemMeta();
        im.setLore(Arrays.asList(lore));
        super.setItemMeta(im);

        return this;
    }

    public cItemStack setItemAmount(int amount) {
        super.setAmount(amount);
        return this;
    }

    public cItemStack addFancyLore(String lore, String color) {
        StringBuilder sb = new StringBuilder(lore);

        int i = 0;
        while (i + 40 < sb.length() && (i = sb.lastIndexOf(" ", i + 40)) != -1)
            sb.replace(i, i + 1, "\n");

        String[] newLore = sb.toString().split("\n");

        for (String s : newLore)
            addLore(color + s);

        return this;
    }

    public cItemStack addBedWarsFancyLore(String lore, String color) {
        StringBuilder sb = new StringBuilder(lore);

        int i = 0;
        while (i + 33 < sb.length() && (i = sb.lastIndexOf(" ", i + 24)) != -1)
            sb.replace(i, i + 1, "\n");

        String[] newLore = sb.toString().split("\n");

        for (String s : newLore)
            addLore(color + s);

        return this;
    }

    public cItemStack addLore(String... lore) {

        ItemMeta im = super.getItemMeta();

        if (im.hasLore()) {

            List<String> newLore = im.getLore();
            newLore.addAll(Arrays.asList(lore));

            im.setLore(newLore);

            super.setItemMeta(im);
        }

        else
            setLore(lore);

        return this;
    }

    public cItemStack addEnchant(Enchantment enchantment, int level) {
        super.addUnsafeEnchantment(enchantment, level);

        return this;
    }

    public cItemStack addFlags(ItemFlag... itemFlags) {
        ItemMeta im = super.getItemMeta();
        im.addItemFlags(itemFlags);
        super.setItemMeta(im);

        return this;
    }



    public static cItemStack toItemStack(Player player) throws SQLException, ClassNotFoundException {


        cItemStack result = null;

        result = new cItemStack(player.getName(), c.green + "Character Information").addLore("");


        return result;
    }
}