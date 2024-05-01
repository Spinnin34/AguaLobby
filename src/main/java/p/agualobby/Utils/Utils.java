package p.agualobby.Utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import p.agualobby.AguaLobby;

public class Utils {

    public static ItemStack addRightClickCommand(ItemStack item, String command) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(AguaLobby.getInstance(), "rightClickCommand"), PersistentDataType.STRING, command);
        item.setItemMeta(meta);
        return item;
    }
}


