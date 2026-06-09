package cantaloupebengal.MeronNeko.itemmodel;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemModelExpansion extends PlaceholderExpansion {

    @Override
    public String getIdentifier() {
        return "itemmodel";
    }

    @Override
    public String getAuthor() {
        return "MeronNeko";
    }

    @Override
    public String getVersion() {
        return "1.1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player player, String params) {
        if (player == null) return "";

        ItemStack item = null;

        switch (params.toLowerCase()) {
            case "mainhand":
            case "main":
                item = player.getInventory().getItemInMainHand();
                break;
            case "offhand":
            case "off":
            case "left":
                item = player.getInventory().getItemInOffHand();
                break;
            default:
                return "invalid";
        }

        if (item == null || item.getType() == Material.AIR || item.getAmount() == 0) {
            return "minecraft:air";
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            NamespacedKey modelKey = meta.getItemModel();
            if (modelKey != null) {
                return modelKey.toString();   // This is the "namespace:item" you want
            }
        }

        // Fallback to material if no custom item_model is set
        return item.getType().getKey().toString();
    }
}