package p.agualobby.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import p.agualobby.Utils.Utils;

import java.util.List;

public class CustomMenu implements Listener {
    private Inventory inventory;

    public CustomMenu(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public void setItem(int slot, Material material, String displayName, List<String> lore, List<String> commands) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if (commands != null && !commands.isEmpty()) {
            for (String command : commands) {
                item = Utils.addRightClickCommand(item, command);
            }
        }
        inventory.setItem(slot, item);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory().equals(inventory)) {
            event.setCancelled(true); // Cancela el evento si el jugador intenta interactuar con el menú
        }
    }
}


