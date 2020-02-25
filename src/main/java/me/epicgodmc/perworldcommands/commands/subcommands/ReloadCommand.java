package me.epicgodmc.perworldcommands.commands.subcommands;

import me.epicgodmc.perworldcommands.PerWorldCommands;
import me.epicgodmc.perworldcommands.objects.SubCommand;
import me.epicgodmc.perworldcommands.util.MessageManager;
import org.bukkit.command.CommandSender;

public class ReloadCommand extends SubCommand
{

    private PerWorldCommands plugin = PerWorldCommands.getInstance();
    private MessageManager mm = plugin.mm;

    @Override
    public void onCommand(CommandSender sender, String[] args) {
        plugin.reloadConfig();
        plugin.saveConfig();
        plugin.getServer().getPluginManager().disablePlugin(plugin);
        plugin.getServer().getPluginManager().enablePlugin(plugin);

        sender.sendMessage(mm.getMessage("reloaded"));

    }

    @Override
    public String name() {
        return plugin.cmdRoot.reload;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
