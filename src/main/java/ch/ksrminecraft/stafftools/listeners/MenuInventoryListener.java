package ch.ksrminecraft.stafftools.listeners;

import ch.ksrminecraft.stafftools.utils.MenuUtils;
import net.kyori.adventure.text.Component;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;

public class MenuInventoryListener implements Listener {

    private final Map<Player, Player> playerTargets = new HashMap<>();

    @EventHandler
    public void onMenuClick(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();
        Player target = playerTargets.get(player);

        if (event.getCurrentItem() == null) {
            return;
        }

        event.setCancelled(true);


        // Logik für das Hauptmenü (Staff-Tools)
        if (MenuUtils.isMenu(event, "StaffTools")) {
            if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                MenuUtils.openPlayerListMenu(player);
            } else if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {
                MenuUtils.openGamemodeMenu(player);
            } else if (event.getCurrentItem().getType() == Material.SUNFLOWER) {
                player.getWorld().setStorm(false);
                player.getWorld().setThundering(false);
                player.sendMessage(Component.text("Das Wetter wurde geändert"));
            } else if (event.getCurrentItem().getType() == Material.CLOCK) {
                player.getWorld().setTime(6000);
                player.sendMessage(Component.text("Es ist jetzt Mittag"));
            }
        }

        // Logik für das GameMode-Menü
        if (MenuUtils.isMenu(event, "GameMode")) {
            if (event.getCurrentItem().getType() == Material.IRON_SWORD) {
                player.setGameMode(GameMode.SURVIVAL);
                player.sendMessage(Component.text("Dein GameMode wurde in Survival geändert"));
            } else if (event.getCurrentItem().getType() == Material.GRASS_BLOCK) {
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(Component.text("Dein GameMode wurde in Creative geändert"));
            } else if (event.getCurrentItem().getType() == Material.SPYGLASS) {
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(Component.text("Dein GameMode wurde in Spectator geändert"));
            } else if (event.getCurrentItem().getType() == Material.BARRIER) {
                MenuUtils.openStaffMenu(player);
            }
        }

        // Logik für das PlayerList-Menü
        if (MenuUtils.isMenu(event, "Spielerliste")) {
            if (event.getCurrentItem().getType() == Material.PLAYER_HEAD) {

                String targetName = ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName());
                target = Bukkit.getPlayer(targetName);
                playerTargets.put(player, target);
                MenuUtils.openPlayerMenu(player, target);
            }
        }

        // Logik für das Player-Menü
        if (MenuUtils.isMenu(event, "Spieler-Menü")) {

            if (target == null) {
                player.sendMessage(Component.text(ChatColor.RED + "Es wurde keine Spieler ausgewählt!"));
                return;
            }

           if (event.getCurrentItem().getType() == Material.ENDER_PEARL) {
               player.teleport(target);
               player.sendMessage(Component.text("Teleportiert zu " + target.getName()));
           } else if (event.getCurrentItem().getType() == Material.LEATHER_BOOTS) {
               target.kick(Component.text("Du wurdest geckickt"));
               player.sendMessage(Component.text("Der Spieler " + target.getName() + " wurde geckickt."));
               MenuUtils.closeMenu(player);
           } else if (event.getCurrentItem().getType() == Material.ORANGE_CANDLE) {
               target.sendMessage(Component.text(ChatColor.RED + "Du wurdest vom Staff verwarnt"));
               player.sendMessage(Component.text("Du hast den Spieler " + target.getName() + " verwarnt."));
               MenuUtils.closeMenu(player);
           } else if (event.getCurrentItem().getType() == Material.IRON_DOOR) {
               Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), "Du wurdest gebannt", null, "StaffTool");
               target.kick(Component.text("Du wurdest gebannt!"));
               player.sendMessage(Component.text("Du hast den Spieler " + target.getName() + " gebannt."));
               MenuUtils.closeMenu(player);
           } else if (event.getCurrentItem().getType() == Material.BARRIER) {
               MenuUtils.openStaffMenu(player);

           }


        }

    }
}
