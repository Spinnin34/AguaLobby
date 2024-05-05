package p.agualobby.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import p.agualobby.AguaLobby;
import p.agualobby.gui.CustomMenu;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class JoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();


        ItemStack item1 = createItem(Material.DIAMOND_SWORD, "§x§9§1§9§2§F§Bʀ§x§9§1§9§B§F§Aᴇ§x§9§1§A§4§F§9ᴅ§x§9§1§A§D§F§8ᴇ§x§9§1§B§6§F§7s §x§9§1§B§F§F§6s§x§9§1§C§9§F§5ᴏ§x§9§1§D§2§F§4ᴄ§x§9§1§D§B§F§3ɪ§x§9§1§E§4§F§2ᴀ§x§9§1§E§D§F§1ʟ§x§9§1§F§6§F§0ᴇ§x§9§1§F§F§E§Fs", Arrays.asList("", "§7Muestra las redes sociales", "§7Comando: §e/redes"), "plugins");
        addItemToInventory(player, item1, 0);

        ItemStack menuOpener = createMenuOpener();
        addItemToInventory(player, menuOpener, 4);

        ItemStack item3 = createItem(Material.ENDER_EYE, "§x§9§1§9§2§F§Bᴘ§x§9§1§A§4§F§9ʟ§x§9§1§B§6§F§7ᴀ§x§9§1§C§9§F§5ʏ§x§9§1§D§B§F§2ᴇ§x§9§1§E§D§F§0ʀ§x§9§1§F§F§E§Es", Arrays.asList("", "§7Haz invisble a los jugadores", ""), "playerhide toggle");
        addItemToInventory(player, item3, 8);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getInventory().getItemInMainHand();

            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            String rightClickCommand = itemInHand.getItemMeta().getPersistentDataContainer()
                    .get(new NamespacedKey(AguaLobby.getInstance(), "command"), PersistentDataType.STRING);

            if (rightClickCommand != null && !rightClickCommand.isEmpty()) {
                Bukkit.dispatchCommand(player, rightClickCommand);
            }

            // Si el jugador hace clic derecho con un ojo de Ender, se cancela el evento para evitar su uso normal
            if (itemInHand.getType() == Material.ENDER_EYE) {
                event.setCancelled(true);
            }

            if (itemInHand.getType() == Material.COMPASS) {
                event.setCancelled(true);

                CustomMenu menu = createMenuForItem2();
                menu.open(player);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // Cancela el evento si el jugador intenta interactuar con su inventario
        if (event.getClickedInventory() != null && event.getClickedInventory().equals(player.getInventory())) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack droppedItem = event.getItemDrop().getItemStack();
        if (player.getInventory().contains(droppedItem)) {
            event.setCancelled(true); // Evita que se tiren ítems del inventario principal
        }
    }

    @EventHandler
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        event.setCancelled(true); // Cancela el evento para evitar que el jugador recoja ítems del suelo
    }

    private ItemStack createItem(Material material, String displayName, List<String> lore, String command) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if (command != null && !command.isEmpty()) {
            meta.getPersistentDataContainer().set(new NamespacedKey(AguaLobby.getInstance(), "command"), PersistentDataType.STRING, command);
            item.setItemMeta(meta);
        }
        return item;
    }

    private void addItemToInventory(Player player, ItemStack item, int slot) {
        player.getInventory().setItem(slot, item);
    }
    private ItemStack createMenuOpener() {
        ItemStack menuOpener = new ItemStack(Material.COMPASS);
        ItemMeta meta = menuOpener.getItemMeta();
        meta.setDisplayName("§x§9§1§9§2§F§Bᴍ§x§9§1§9§D§F§Aᴏ§x§9§1§A§8§F§8ᴅ§x§9§1§B§3§F§7ᴀ§x§9§1§B§E§F§6ʟ§x§9§1§C§9§F§5ɪ§x§9§1§D§3§F§3ᴅ§x§9§1§D§E§F§2ᴀ§x§9§1§E§9§F§1ᴅ§x§9§1§F§4§E§Fᴇ§x§9§1§F§F§E§Es");
        menuOpener.setItemMeta(meta);
        return menuOpener;
    }

    private CustomMenu createMenuForItem2() {
        CustomMenu menu = new CustomMenu("§x§9§1§9§2§F§Bᴍ§x§9§1§9§D§F§Aᴏ§x§9§1§A§8§F§8ᴅ§x§9§1§B§3§F§7ᴀ§x§9§1§B§E§F§6ʟ§x§9§1§C§9§F§5ɪ§x§9§1§D§3§F§3ᴅ§x§9§1§D§E§F§2ᴀ§x§9§1§E§9§F§1ᴅ§x§9§1§F§4§E§Fᴇ§x§9§1§F§F§E§Es", 27);

        // Diamante
        List<String> lore1 = Collections.singletonList("§7Surivival Custom");
        menu.setItem(11, Material.DIAMOND, "§7Surivival Custom", lore1, "comando1");

        // Manzana
        List<String> lore2 = Collections.singletonList("§7Surivival Custom");
        menu.setItem(13, Material.IRON_INGOT, "§7Surivival Custom", lore2, "comando3");

        // Más ítems al menú con sus respectivos comandos
        menu.setItem(15, Material.GOLD_INGOT, "§7Surivival Custom", lore2, "comando3");

        return menu;
    }
}



