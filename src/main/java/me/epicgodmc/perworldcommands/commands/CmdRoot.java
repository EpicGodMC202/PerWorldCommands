package me.epicgodmc.perworldcommands.commands;

import me.epicgodmc.perworldcommands.PerWorldCommands;
import me.epicgodmc.perworldcommands.commands.subcommands.AddCmd;
import me.epicgodmc.perworldcommands.commands.subcommands.AddWorld;
import me.epicgodmc.perworldcommands.commands.subcommands.ReloadCommand;
import me.epicgodmc.perworldcommands.objects.SubCommand;
import me.epicgodmc.perworldcommands.util.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class CmdRoot implements CommandExecutor
{

    private ArrayList<SubCommand> commands = new ArrayList<>();
    private PerWorldCommands plugin = PerWorldCommands.getInstance();
    private MessageManager mm = plugin.mm;


    public CmdRoot()
    {

    }


    public String main = "perworldcommands";
    //SubCmds
    public String addcmd = "addcmd";
    public String addworld = "addworld";
    public String reload = "reload";

    //

    public void setup(){
        plugin.getCommand(main).setExecutor(this);

        this.commands.add(new AddCmd());
        this.commands.add(new AddWorld());
        this.commands.add(new ReloadCommand());



    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (cmd.getName().equalsIgnoreCase(main))
        {
            if (sender.hasPermission("pwc.use")){
                if (args.length == 0) {
                    mm.getUsage().forEach((e) -> {
                        sender.sendMessage(mm.applyCC(e));
                    });
                    return true;
                }

                SubCommand target = this.get(args[0]);

                if (target == null) {
                    sender.sendMessage(mm.getMessage("cmdNotRecognized"));
                    return true;
                }

                ArrayList<String> argList = new ArrayList<>();
                argList.addAll(Arrays.asList(args));
                argList.remove(0);

                String[] arguments = argList.toArray(new String[argList.size()]);

                try {

                    target.onCommand(sender, arguments);
                } catch (Exception e) {
                    e.printStackTrace();
                    sender.sendMessage(mm.getMessage("error")); //code #0001
                    sender.sendMessage(mm.applyCC("Error Code: #0001"));
                    return true;
                }
            }else{
                sender.sendMessage(mm.getMessage("noPermission"));
            }
        }
        return true;
    }


    private SubCommand get(String name) {
        Iterator<SubCommand> subcommands = this.commands.iterator();

        while (subcommands.hasNext()) {
            SubCommand sCmd = (SubCommand) subcommands.next();

            if (sCmd.name().equalsIgnoreCase(name)) {
                return sCmd;
            }

            String[] aliases;
            int length = (aliases = sCmd.aliases()).length;

            for (int var5 = 0; var5 < length; ++var5) {
                String alias = aliases[var5];
                if (name.equalsIgnoreCase(alias)) {
                    return sCmd;
                }
            }
        }
        return null;
    }
}
