package p.agualobby.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import p.agualobby.AguaLobby;

import java.util.List;

public class CustomMenu implements Listener {
    private Inventory inventory;

    public CustomMenu(String title, int size) {
        this.inventory = Bukkit.createInventory(null, size, title);
    }

    public void setItem(int slot, Material material, String displayName, List<String> lore, String command) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if (command != null && !command.isEmpty()) {
            meta.getPersistentDataContainer().set(new NamespacedKey(AguaLobby.getInstance(), "command"), PersistentDataType.STRING, command);
            item.setItemMeta(meta);
        }
        inventory.setItem(slot, item);
    }

    public void open(Player player) {
        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(inventory)) {
            event.setCancelled(true); // Cancela el evento si el jugador intenta interactuar con el menú
            if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY || event.getAction() == InventoryAction.DROP_ALL_SLOT || event.getAction() == InventoryAction.DROP_ONE_SLOT) {
                event.setCancelled(true); // Cancela el evento si el jugador intenta mover o dejar caer un ítem en el menú
            } else {
                Player player = (Player) event.getWhoClicked();
                ItemStack clickedItem = event.getCurrentItem();
                if (clickedItem != null) {
                    ItemMeta meta = clickedItem.getItemMeta();
                    if (meta != null && meta.getPersistentDataContainer().has(new NamespacedKey(AguaLobby.getInstance(), "command"), PersistentDataType.STRING)) {
                        String command = meta.getPersistentDataContainer().get(new NamespacedKey(AguaLobby.getInstance(), "command"), PersistentDataType.STRING);
                        if (command != null && !command.isEmpty()) {
                            Bukkit.dispatchCommand(player, command);
                        }
                    }
                }
            }
        }
    }
}


