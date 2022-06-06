package net.twistedmc.twistedpass.util.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AbstractGUI {

    public static Map<UUID, AbstractGUI> inventoriesByUUID = new HashMap<>();
    public static Map<UUID, UUID> openInventories = new HashMap<>();

    private Inventory inventory;
    private UUID uuid;
    private HashMap<ItemMeta, AbstractAction> actions;
    private Map<Integer, ItemStack> items = new HashMap<>();

    public AbstractGUI(int rows, String title) {
        uuid = UUID.randomUUID();
        this.inventory = Bukkit.createInventory(null, rows * 9, title);
        actions = new HashMap<>();
        inventoriesByUUID.put(getUuid(), this);
    }

    public AbstractGUI(int rows, String title, Player pp) {
        this(rows, title);
        openInventory(pp);
    }

    public Inventory getInventory() {
        return inventory;
    }

    public static Map<UUID, AbstractGUI> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getOpenInventories() {
        return openInventories;
    }

    public HashMap<ItemMeta, AbstractAction> getActions() {
        return actions;
    }

    public void setItem(ItemStack item, int slot) {
        inventory.setItem(slot, item);
        items.put(slot, item);
    }

    public void setItem(ItemStack item, int slot, AbstractAction action) {
        inventory.setItem(slot, item);
        actions.put(item.getItemMeta(), action);
        items.put(slot, item);
    }

    public ItemStack getItemStack(int slot) {
        return items.get(slot);
    }

    public void openInventory(Player pp) {
        pp.getPlayer().openInventory(getInventory());

        openInventories.put(pp.getUniqueId(), getUuid());

        new BukkitRunnable() {
            @Override
            public void run() {
                pp.getPlayer().updateInventory();
            }
        }.runTaskLater(Bukkit.getPluginManager().getPlugin("TwistedPassAPI"), 2);
    }

    public UUID getUuid() {
        return this.uuid;
    }

    public void delete(){
        for (Player p : Bukkit.getOnlinePlayers()){
            UUID u = openInventories.get(p.getUniqueId());
            if (u.equals(getUuid())){
                p.closeInventory();
            }
        }
        inventoriesByUUID.remove(getUuid());
    }

    public void onClose() {}

    public interface AbstractAction {
        void click(Player player) throws SQLException, ClassNotFoundException, NoSuchFieldException, IllegalAccessException;
    }
}