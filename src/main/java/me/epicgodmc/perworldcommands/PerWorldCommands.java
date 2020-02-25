package me.epicgodmc.perworldcommands;

import me.epicgodmc.perworldcommands.commands.CmdRoot;
import me.epicgodmc.perworldcommands.listeners.PreCommand;
import me.epicgodmc.perworldcommands.util.MessageManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PerWorldCommands extends JavaPlugin
{

    private static PerWorldCommands instance;
    public static PerWorldCommands getInstance()
    {
        return instance;
    }

    public MessageManager mm;
    public CmdRoot cmdRoot;


    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        registerInstances();
        this.getServer().getPluginManager().registerEvents(new PreCommand(), this);
        cmdRoot.setup();

    }


    @Override
    public void onDisable() {
        instance = null;
    }

    private void registerInstances()
    {
        mm = new MessageManager();
        cmdRoot = new CmdRoot();
    }

}
