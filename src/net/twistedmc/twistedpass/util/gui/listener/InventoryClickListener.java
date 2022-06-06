package net.twistedmc.twistedpass.util.gui.listener;

import net.twistedmc.twistedpass.util.gui.AbstractGUI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.SQLException;
import java.util.UUID;

public class InventoryClickListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClickEvent(InventoryClickEvent e) throws SQLException, ClassNotFoundException {

        if (!(e.getWhoClicked() instanceof Player)){
            return;
        }

        Player player = (Player) e.getWhoClicked();
        UUID playerUUID = player.getUniqueId();

        UUID inventoryUUID = AbstractGUI.openInventories.get(playerUUID);
        if (inventoryUUID != null){
            e.setCancelled(true);
            AbstractGUI gui = AbstractGUI.getInventoriesByUUID().get(inventoryUUID);
            AbstractGUI.AbstractAction action = gui.getActions().get(e.getCurrentItem().getItemMeta());

            if (action != null){

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        try {
                            action.click(player);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        } catch (ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (NoSuchFieldException ex) {
                            throw new RuntimeException(ex);
                        } catch (IllegalAccessException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }.runTaskLater(Bukkit.getPluginManager().getPlugin("TwistedPassAPI"), 0);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        Player player = (Player) e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        AbstractGUI.openInventories.remove(playerUUID);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        UUID playerUUID = player.getUniqueId();

        AbstractGUI.openInventories.remove(playerUUID);
    }

}
