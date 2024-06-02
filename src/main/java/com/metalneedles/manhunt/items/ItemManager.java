package com.metalneedles.manhunt.items;

import com.metalneedles.manhunt.events.MyEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

    public static ItemStack tracker;

    public static void createTracker(Location pLoc) {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        CompassMeta meta = (CompassMeta) item.getItemMeta();
        // CompassMeta compMeta = (CompassMeta) item.getItemMeta();
        // compMeta.setLodestone(MyEvents.runnerLocation);
        // compMeta.setLodestoneTracked(true);
        meta.displayName(Component.text("Tracker").color(NamedTextColor.BLUE).decorate(TextDecoration.BOLD));
        pLoc.setY(1000);
        meta.setLodestone(pLoc);
        meta.setLodestoneTracked(false);
        item.setItemMeta(meta);
        tracker = item;
    }

}
