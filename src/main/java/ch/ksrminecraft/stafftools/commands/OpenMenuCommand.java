package ch.ksrminecraft.stafftools.commands;

import ch.ksrminecraft.stafftools.utils.MenuUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class OpenMenuCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(commandSender instanceof Player)) {
            return true;
        }

        Player player = (Player) commandSender;

        // Menü vom Player öffnen
        MenuUtils.openStaffMenu(player);


        return true;
    }
}
