package net.twistedmc.twistedpass.util.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class AbstractItem {

    private Player pp;
    private int slot;
    private ItemStack itemStack;
    private AbstractAction action;
    private Map<Integer, AbstractItem> abstractItems = new HashMap<>();

    public AbstractItem(ItemStack itemStack, Player pp, int slot) {
        this.itemStack = itemStack;
        this.pp = pp;
        this.slot = slot;

        pp.getPlayer().getInventory().setItem(slot, itemStack);
    }

    public AbstractItem(ItemStack itemStack, Player pp, int slot, AbstractAction action) {
        this.itemStack = itemStack;
        this.pp = pp;
        this.slot = slot;
        this.action = action;

        pp.getPlayer().getInventory().setItem(slot, itemStack);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public AbstractAction getAction() {
        return action;
    }

    public Map<Integer, AbstractItem> getAbstractItems() {
        return abstractItems;
    }

    public interface AbstractAction {
        void interact(InteractType interactType);
    }

    public enum InteractType { LEFT, RIGHT, OTHER }
}
