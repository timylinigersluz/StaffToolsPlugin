package ch.ksrminecraft.stafftools.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.UUID;

public class MenuUtils {

    private static Player player;

    // Methode, die den Inventarnamen überprüft
    public static boolean isMenu(InventoryClickEvent event, String inventoryName) {
        return event.getView().getTitle().equalsIgnoreCase(inventoryName);
    }

    public static void openStaffMenu(Player player) {

        Inventory staffgui = Bukkit.createInventory(player, 9, "StaffTools");

        // Wetter-Icon
        ItemStack weather = new ItemStack(Material.SUNFLOWER, 1);
        ItemMeta weatherMeta = weather.getItemMeta();
        weatherMeta.setDisplayName(ChatColor.YELLOW + "schönes Wetter");
        weather.setItemMeta(weatherMeta);
        staffgui.setItem(1, weather);

        // Mittag-Icon
        ItemStack midday = new ItemStack(Material.CLOCK, 1);
        ItemMeta middayMeta = midday.getItemMeta();
        middayMeta.setDisplayName(ChatColor.YELLOW + "Mittag setzen");
        midday.setItemMeta(middayMeta);
        staffgui.setItem(3, midday);

        // Player-Menü-Icon
        ItemStack playerMenu = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta playerMeta = playerMenu.getItemMeta();
        playerMeta.setDisplayName("§eSpieler-Menü");
        playerMenu.setItemMeta(playerMeta);
        staffgui.setItem(5, playerMenu);

        // GameMode-Menü-Icon
        ItemStack gamemode = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta gamemodeMeta = gamemode.getItemMeta();
        gamemodeMeta.setDisplayName("§eGamemode-Menü");
        gamemode.setItemMeta(gamemodeMeta);
        staffgui.setItem(7, gamemode);

        player.openInventory(staffgui);
    }

    public static void openPlayerListMenu(Player player) {

        // Liste von allen Online-Spielern erstellen
        ArrayList<Player> list = new ArrayList<Player>(player.getServer().getOnlinePlayers());

        Inventory playerlist = Bukkit.createInventory(player, 54, "Spielerliste");

        for (int i = 0; i < list.size(); i++) {
            ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerHead.getItemMeta();
            meta.setDisplayName(list.get(i).getDisplayName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GOLD + "Player Health: " + ChatColor.RED + list.get(i).getHealth());
            lore.add(ChatColor.GOLD + "EXP: " + ChatColor.RED + list.get(i).getExp());
            meta.setLore(lore);
            playerHead.setItemMeta(meta);
            playerlist.addItem(playerHead);
        }

        player.openInventory(playerlist);

    }

    public static void openGamemodeMenu(Player player) {

        Inventory gamemodegui = Bukkit.createInventory(player, 9, "GameMode");

        // Survival-Icon
        ItemStack survial = new ItemStack(Material.IRON_SWORD, 1);
        ItemMeta survialMeta = survial.getItemMeta();
        survialMeta.setDisplayName(ChatColor.YELLOW + "Survial");
        survial.setItemMeta(survialMeta);
        gamemodegui.setItem(3, survial);

        // Creative-Icon
        ItemStack creative = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta creativeMeta = creative.getItemMeta();
        creativeMeta.setDisplayName(ChatColor.YELLOW + "Creative");
        creative.setItemMeta(creativeMeta);
        gamemodegui.setItem(4, creative);

        // Spectator-Icon
        ItemStack spectator = new ItemStack(Material.SPYGLASS, 1);
        ItemMeta spectatorMeta = spectator.getItemMeta();
        spectatorMeta.setDisplayName(ChatColor.YELLOW + "Spectator");
        spectator.setItemMeta(spectatorMeta);
        gamemodegui.setItem(5, spectator);

        // Cancel-Icon
        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.YELLOW + "Abbrechen");
        cancel.setItemMeta(cancelMeta);
        gamemodegui.setItem(8, cancel);

        player.openInventory(gamemodegui);
    }

    public static void openPlayerMenu(Player player, Player target) {

        Inventory playergui = Bukkit.createInventory(player, 9, "Spieler-Menü");

        // Player-Info-Icon 0
        ItemStack playerInfo = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta playerInfoMeta = playerInfo.getItemMeta();
        playerInfoMeta.setDisplayName("Spieler-Info:");
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.GOLD + "Player Health: " + ChatColor.RED + target.getHealth());
        lore.add(ChatColor.GOLD + "EXP: " + ChatColor.RED + target.getExp());
        playerInfoMeta.setLore(lore);
        playerInfo.setItemMeta(playerInfoMeta);
        playergui.setItem(0, playerInfo);

        // Player-Teleport-Icon 2
        ItemStack playerTeleport = new ItemStack(Material.ENDER_PEARL);
        ItemMeta playerTeleportMeta = playerTeleport.getItemMeta();
        playerTeleportMeta.setDisplayName("Zum Spieler teleportieren");
        playerTeleport.setItemMeta(playerTeleportMeta);
        playergui.setItem(2, playerTeleport);

        // Player-Kick-Icon 3
        ItemStack playerKick = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta playerKickMeta = playerKick.getItemMeta();
        playerKickMeta.setDisplayName("Spieler kicken");
        playerKickMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        AttributeModifier modifier = new AttributeModifier(UUID.randomUUID(), "generic.armor", 0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        playerKickMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, modifier);
        playerKick.setItemMeta(playerKickMeta);
        playergui.setItem(3, playerKick);

        // Player-Warn-Icon 4
        ItemStack playerWarn = new ItemStack(Material.ORANGE_CANDLE, 1);
        ItemMeta playerWarnMeta = playerWarn.getItemMeta();
        playerWarnMeta.setDisplayName("§eSpieler warnen");
        playerWarn.setItemMeta(playerWarnMeta);
        playergui.setItem(4, playerWarn);

        // Player-Ban-Icon 5
        ItemStack playerBan = new ItemStack(Material.IRON_DOOR, 1);
        ItemMeta playerBanMeta = playerBan.getItemMeta();
        playerBanMeta.setDisplayName("§eSpieler bannen");
        playerBan.setItemMeta(playerBanMeta);
        playergui.setItem(5, playerBan);

        // Cancel-Icon 8
        ItemStack cancel = new ItemStack(Material.BARRIER, 1);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName("§cAbbrechen");
        cancel.setItemMeta(cancelMeta);
        playergui.setItem(8, cancel);

        player.openInventory(playergui);
    }

    public static void closeMenu(Player player) {
        player.closeInventory();
    }


}
