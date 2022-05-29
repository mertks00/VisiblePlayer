package net.oyuncozucu.visibleplayer;

import net.oyuncozucu.visibleplayer.commands.VisibleCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class VisiblePlayer extends JavaPlugin implements CommandExecutor {


    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        reloadConfig();
        getCommand("visible").setExecutor(new VisibleCommand(this));
        getCommand("reload").setExecutor(this);
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN+"Reload Visible Player");

    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equals("reload")){
            if(sender instanceof Player){
                if (sender.hasPermission("visibleplayer.admin")){
                    this.reloadConfig();
                    sender.sendMessage(ChatColor.GREEN+"VisiblePlayer reloaded.");
                }else{
                    sender.sendMessage(ChatColor.RED+"You don't have permission for that.");
                }

            }

        }
        return true;
    }



}
