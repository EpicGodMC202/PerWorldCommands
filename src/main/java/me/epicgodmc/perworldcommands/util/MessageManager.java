package me.epicgodmc.perworldcommands.util;

import me.epicgodmc.perworldcommands.PerWorldCommands;
import org.bukkit.ChatColor;

import java.util.List;

public class MessageManager
{
    private PerWorldCommands plugin = PerWorldCommands.getInstance();


    public String prefix = plugin.getConfig().getString("pluginPrefix");

    public String getMessage(String key)
    {
        return applyCC(prefix+plugin.getConfig().getString("messages."+key));
    }

    public String applyCC(String input)
    {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public List<String> getUsage()
    {
        return plugin.getConfig().getStringList("messages.usage");
    }



}
