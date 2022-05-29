package net.oyuncozucu.visibleplayer.commands;

import net.oyuncozucu.visibleplayer.VisiblePlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VisibleCommand implements CommandExecutor {

    private final VisiblePlayer plugin;
    public VisibleCommand(VisiblePlayer plugin) {

        this.plugin = plugin;
    }


    private List<UUID> visible = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.hasPermission("visibleplayer.visible")) {

                if (visible.contains(player.getUniqueId())) {
                    visible.remove(player.getUniqueId());

                    for (Player target : Bukkit.getOnlinePlayers()) {
                        player.showPlayer(target);

                    }
                         String smsg = plugin.getConfig().getString("show-message");
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', smsg));


                } else {
                    visible.add(player.getUniqueId());
                    for (Player target : Bukkit.getOnlinePlayers()) {
                        player.hidePlayer(target);
                    }
                    String hmsg = plugin.getConfig().getString("hide-message");
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', hmsg));

                }
            }
        }
        return true;
    }

}
