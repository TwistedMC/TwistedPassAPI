package net.twistedmc.twistedpass.util.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    ItemStack itemStack;

    ItemMeta itemMeta;

    SkullMeta skullMeta;

    public ItemBuilder(Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = this.itemStack.getItemMeta();
    }

    public ItemBuilder setMaterial(Material material) {
        this.itemStack.setType(material);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setSubid(byte subid) {
        this.itemStack.getData().setData(subid);
        return this;
    }

    public ItemBuilder setAmount(int amount) {
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemBuilder setname(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder setDurability(short durability) {
        this.itemStack.setDurability(durability);
        return this;
    }

    public ItemBuilder setEnchantments(Map<Enchantment, Integer> enchantments) {
        enchantments.forEach((enchantment, level) -> this.itemStack.addEnchantment(enchantment, level.intValue()));
        return this;
    }

    public ItemBuilder addEnchantments(Enchantment enchantment, Integer level) {
        this.itemStack.addEnchantment(enchantment, level.intValue());
        return this;
    }

    public ItemBuilder clearEnchantments() {
        this.itemStack.getEnchantments().keySet().forEach(enchantment -> {

        });
        return this;
    }

    public ItemBuilder removeEnchantments(Enchantment enchantment) {
        this.itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        this.itemMeta.setLore(lore);
        return this;
    }

    public ItemBuilder addLore(String lore) {
        List<String> loreList = this.itemMeta.getLore();
        loreList.add(lore);
        this.itemMeta.setLore(loreList);
        return this;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ItemBuilder clearLore() {
        this.itemMeta.setLore(new ArrayList());
        return this;
    }

    public ItemBuilder removeLore(String lore) {
        this.itemMeta.getLore().remove(lore);
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        this.itemStack.setItemMeta(this.itemMeta);
        this.skullMeta = (SkullMeta)this.itemStack.getItemMeta();
        this.skullMeta.setOwner(owner);
        return this;
    }

    public ItemStack build() {
        this.itemStack.setItemMeta(this.itemMeta);
        return this.itemStack;
    }

    @SuppressWarnings("deprecation")
    public ItemStack buildSkull() {
        this.itemStack.setItemMeta((ItemMeta)this.skullMeta);
        this.itemStack.getData().setData((byte)3);
        return this.itemStack;
    }
}
