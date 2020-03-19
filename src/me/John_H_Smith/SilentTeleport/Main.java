package me.John_H_Smith.SilentTeleport;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public Logger log = this.getLogger();

	@Override
	public void onEnable() {
		log.info("[SilentTeleport] by John_H_Smith aktiviert!");
	}
	
	@Override
	public void onDisable() {
		log.info("[SilentTeleport] by John_H_Smith deaktiviert!");
	}
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			String noperm = ChatColor.RED + "Not enough permissions";
			String wrongPlayer = " is not a player";
			
			if(cmd.getName().equalsIgnoreCase("silentteleport")) {
				if(player.hasPermission("stp.stp")) {
					if(args.length == 1) {
						Player ziel = Bukkit.getPlayer(args[0]);
						if(ziel != null) 
							player.teleport(ziel);
						else
							player.sendMessage(ChatColor.RED + args[0] + wrongPlayer);
						return true;
					}
					if(args.length == 2) {
						Player ziel1 = Bukkit.getPlayer(args[0]);
						Player ziel2 = Bukkit.getPlayer(args[1]);
						if(ziel1 == null) {
							player.sendMessage(ChatColor.RED + args[0] + wrongPlayer);
							return true;
						}
						if(ziel2 == null) {
							player.sendMessage(ChatColor.RED + args[1] + wrongPlayer);
							return true;
						}
						ziel1.teleport(ziel2);
						return true;
					}
				} else { player.sendMessage(noperm); return true; }
			}
			
			if(cmd.getName().equalsIgnoreCase("silentteleportposition")) {
				if(player.hasPermission("stp.stppos")) {
					if(args.length == 3) {
						try {
							int coordsX = Integer.parseInt(args[0]);
							int coordsY = Integer.parseInt(args[1]);
							int coordsZ = Integer.parseInt(args[2]);
						} catch(Exception e) {
							player.sendMessage(ChatColor.RED + "Wrong format. Check the coordinates");
							return true;
						}
						Location ziel = new Location(player.getWorld(), coordsX+0.5, coordsY+0.5, coordsZ+0.5);
						player.teleport(ziel);
						return true;
					}
				} else player.sendMessage(noperm);
			}
			
			if(cmd.getName().equalsIgnoreCase("silentteleporthere")) {
				if(player.hasPermission("stp.stphere")) {
					if(args.length == 1) {
						Player ziel = Bukkit.getPlayer(args[0]);
						if(ziel != null) 
							ziel.teleport(player);
						else 
							player.sendMessage(ChatColor.RED + args[0] + wrongPlayer);
						return true;
					}
				} else { player.sendMessage(noperm); return true; }
			}
			
		} else { sender.sendMessage("This is an player only command!"); return true; }
		return false;
	}
	
}
