package me.epicgodmc.perworldcommands.commands.subcommands;

import me.epicgodmc.perworldcommands.PerWorldCommands;
import me.epicgodmc.perworldcommands.objects.SubCommand;
import me.epicgodmc.perworldcommands.util.MessageManager;
import org.bukkit.command.CommandSender;

import java.util.List;

public class AddWorld extends SubCommand
{
    private PerWorldCommands plugin = PerWorldCommands.getInstance();
    private MessageManager mm = plugin.mm;


    // pwc addworld <cmd> <world>
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0)
        {
            mm.getUsage().forEach((e) -> {
                sender.sendMessage(mm.applyCC(e));
            });
        }else if (args.length == 1)
        {
            sender.sendMessage(mm.getMessage("notEnoughArgs"));
        }else if (args.length == 2)
        {
            String cmd = args[0];
            String world = args[1];
            if (cmdExists(cmd))
            {
                if (!containsWorld(cmd, world))
                {
                    addWorld(cmd, world);
                    sender.sendMessage(mm.getMessage("worldAdded").replace("%world%", world).replace("%cmd%", cmd));
                }else{
                    sender.sendMessage(mm.getMessage("worldAlreadyExists").replace("%world%", world));
                }
            }else{
                sender.sendMessage(mm.getMessage("cmdNotFound").replace("%cmd%", cmd));
            }
        }else{
            sender.sendMessage(mm.getMessage("tooManyArgs"));
        }


    }
    private void addWorld(String cmd, String worldName)
    {
        List<String> confList = plugin.getConfig().getStringList("commands."+cmd+".allowedWorlds");
        confList.add(worldName);

        plugin.getConfig().set("commands."+cmd+".allowedWorlds", confList);
        plugin.saveConfig();
    }

    private boolean containsWorld(String cmd, String world)
    {
        return plugin.getConfig().getStringList("commands."+cmd+".allowedWorlds").contains(world);
    }

    private boolean cmdExists(String cmd)
    {
        return plugin.getConfig().isSet("commands."+cmd);
    }



    @Override
    public String name() {
        return plugin.cmdRoot.addworld;
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
