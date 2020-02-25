package me.epicgodmc.perworldcommands.commands.subcommands;

import me.epicgodmc.perworldcommands.PerWorldCommands;
import me.epicgodmc.perworldcommands.objects.SubCommand;
import me.epicgodmc.perworldcommands.util.MessageManager;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class AddCmd extends SubCommand
{
    private PerWorldCommands plugin = PerWorldCommands.getInstance();
    private MessageManager mm = plugin.mm;


// pwc addcmd <cmd>
    @Override
    public void onCommand(CommandSender sender, String[] args) {
        if (args.length == 0)
        {
            mm.getUsage().forEach((e) -> {
                sender.sendMessage(mm.applyCC(e));
            });
        }else if (args.length == 1){
            String cmd = args[0];
            if (!cmdExists(cmd))
            {
                addWorldToConfig(cmd);
                sender.sendMessage(mm.getMessage("cmdAdded").replace("%cmd%", cmd));
            }else{
                sender.sendMessage(mm.getMessage("cmdAlreadyExists").replace("%cmd%", cmd));
            }
        }else{
            sender.sendMessage(mm.getMessage("tooManyArgs"));
        }

    }

    private void addWorldToConfig(String cmd)
    {
        List<String> worldList = new ArrayList<>();
        plugin.getConfig().set("commands."+cmd+".onlyCheckCmdName", false);
        plugin.getConfig().set("commands."+cmd+".allowedWorlds", worldList);
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
        return plugin.cmdRoot.addcmd;
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
