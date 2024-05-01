package p.agualobby.Listener;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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

        // Añade los ítems que no pueden ser movidos en slots específicos
        ItemStack item1 = createItem(Material.DIAMOND_SWORD, "Espada Especial", Arrays.asList("¡Una poderosa espada!", "¡Con un lore más largo!"), "/comando1", "/comando2");
        addItemToInventory(player, item1, 0);

        ItemStack menuOpener = createItem(Material.COMPASS, "§x§9§1§9§2§F§Bᴍ§x§9§1§9§D§F§Aᴏ§x§9§1§A§8§F§8ᴅ§x§9§1§B§3§F§7ᴀ§x§9§1§B§E§F§6ʟ§x§9§1§C§9§F§5ɪ§x§9§1§D§3§F§3ᴅ§x§9§1§D§E§F§2ᴀ§x§9§1§E§9§F§1ᴅ§x§9§1§F§4§E§Fᴇ§x§9§1§F§F§E§Es", Arrays.asList("¡Una poderosa espada!", "¡Con un lore más largo!"));
        addItemToInventory(player, menuOpener, 4); // Coloca en el slot 4

        ItemStack item3 = createItem(Material.ENDER_EYE, "§x§9§1§9§2§F§Bᴘ§x§9§1§A§4§F§9ʟ§x§9§1§B§6§F§7ᴀ§x§9§1§C§9§F§5ʏ§x§9§1§D§B§F§2ᴇ§x§9§1§E§D§F§0ʀ§x§9§1§F§F§E§Es", Collections.singletonList(""), "/playerhide toggle");
        addItemToInventory(player, item3, 8); // Coloca en el slot 8
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        // Aquí puedes agregar la lógica para manejar las interacciones con los ítems
        if (player.getInventory().getHeldItemSlot() == 4) {
            // Abre el menú especial
            CustomMenu menu = createMenuForItem2();
            menu.open(player);
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

    private ItemStack createItem(Material material, String displayName, List<String> lore, String... commands) {
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lore);
        item.setItemMeta(meta);
        if (commands.length > 0) {
            String rightClickCommand = commands[0];
            meta.getPersistentDataContainer().set(new NamespacedKey(AguaLobby.getInstance(), "rightClickCommand"), PersistentDataType.STRING, rightClickCommand);
            item.setItemMeta(meta);
        }
        if (commands.length > 1) {
            String leftClickCommand = commands[1];
            meta.getPersistentDataContainer().set(new NamespacedKey(AguaLobby.getInstance(), "leftClickCommand"), PersistentDataType.STRING, leftClickCommand);
            item.setItemMeta(meta);
        }
        return item;
    }

    private void addItemToInventory(Player player, ItemStack item, int slot) {
        player.getInventory().setItem(slot, item);
    }

    private CustomMenu createMenuForItem2() {
        CustomMenu menu = new CustomMenu("§x§9§1§9§2§F§Bᴍ§x§9§1§9§D§F§Aᴏ§x§9§1§A§8§F§8ᴅ§x§9§1§B§3§F§7ᴀ§x§9§1§B§E§F§6ʟ§x§9§1§C§9§F§5ɪ§x§9§1§D§3§F§3ᴅ§x§9§1§D§E§F§2ᴀ§x§9§1§E§9§F§1ᴅ§x§9§1§F§4§E§Fᴇ§x§9§1§F§F§E§Es", 27);
        List<String> lore1 = Collections.singletonList("¡Un diamante especial!");
        List<String> commands1 = Arrays.asList("/comando1", "/comando2");
        menu.setItem(11, Material.DIAMOND, "Diamante", lore1, commands1);

        // Agrega más ítems al menú con sus respectivos comandos
        List<String> lore2 = Collections.singletonList("¡Una manzana especial!");
        List<String> commands2 = Collections.singletonList("/comando3");
        menu.setItem(13, Material.APPLE, "Manzana", lore2, commands2);

        // Agrega más ítems al menú con sus respectivos comandos
        List<String> lore3 = Collections.singletonList("¡Una manzana especial!");
        List<String> commands3 = Collections.singletonList("/comando3");
        menu.setItem(15, Material.APPLE, "Manzana", lore2, commands2);

        return menu;
    }
}



