package me.epicgodmc.perworldcommands.listeners;

import me.epicgodmc.perworldcommands.PerWorldCommands;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.Set;

public class PreCommand implements Listener {
    private PerWorldCommands plugin = PerWorldCommands.getInstance();

    private Set<String> commandList = plugin.getConfig().getConfigurationSection("commands").getKeys(false);

    @EventHandler
    public void preCommand(PlayerCommandPreprocessEvent event) {
        Player sender = event.getPlayer();
        String cmd = event.getMessage().replaceFirst("/", "");

        if (isSet(cmd)) {

            String cmdName = cmd.split(" ")[0];
            List<String> allowedWorlds = plugin.getConfig().getStringList("commands." + cmdName + ".allowedWorlds");
            String playerWorld = event.getPlayer().getWorld().getName();

            if (allowedWorlds.isEmpty()) {
                event.setCancelled(true);
                sender.sendMessage(plugin.mm.getMessage("denied"));
                return;
            }

            if (!allowedWorlds.contains(playerWorld)) {
                if (plugin.getConfig().getBoolean("commands." + cmdName + ".onlyCheckCmdName")) {
                    if (containsCmd(commandList, cmd)) {
                        event.setCancelled(true);
                        sender.sendMessage(plugin.mm.getMessage("denied"));
                    }

                } else if (containsCmd(commandList, cmd)) {
                    event.setCancelled(true);
                    sender.sendMessage(plugin.mm.getMessage("denied"));
                }
            }
        }
    }

    public boolean isSet(String cmd)
    {
        return plugin.getConfig().isSet("commands."+cmd);
    }

    public boolean containsCmd(Set<String> list, String cmd)
    {
        for (String str : list)
        {
            if (str.equalsIgnoreCase(cmd)) return true;
        }
        return false;
    }

}
