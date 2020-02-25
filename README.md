Plugin requested by: **Xp10d3**


Commands:
  /perworldcommands | Aliases [pwc]
SubCommands:
  /pwc addCmd <cmd> | add a cmd to configuration
  /pwc addWorld <cmd> <world> | adds a world to a cmds whitelist
  /pwc reload reloads config/plugin | is needed after creating a cmd for it to register
  
Permissions:
  pwc.use | used for all commands


Config:
  
  in each command there is a key: **onlyCheckCmdName** if this is on false the plugin will check the whole command E.G. "/compass give 1"
  when this is on true it will check for "/compass" even though  a player writes "/compass give 1"
  when adding commands trough minecraft this will default to false
  
  the rest of the config speaks for itself
