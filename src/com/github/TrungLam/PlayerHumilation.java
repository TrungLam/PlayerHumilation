package com.github.TrungLam;

import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

public class PlayerHumilation extends JavaPlugin implements Listener{
	public static PlayerHumilation plugin;
	public final Logger logger = Logger.getLogger("Minecraft");
	
	public void onEnable(){
		logger.info(this.getDescription().getFullName() + " is enabled");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable(){
		logger.info(this.getDescription().getFullName() + " is disabled");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args){
		
		if (Label.equalsIgnoreCase("ph")){
			Player player = (Player) sender;
			Server server = player.getServer();
			
			//wrong number of arguments
			if (args.length < 3){
				player.sendMessage("Correct Usage:");
				player.sendMessage("/ph say <player> <message>");
				player.sendMessage("/ph fling <player> <reason>");
				player.sendMessage("/ph pot <player> <effect>");
				player.sendMessage("/ph sorry <player> <reason>");
			}
			
			//correct number of arguments
			else {
				
				//
				if (args[0].equalsIgnoreCase("say")){
					Player targetPlayer = server.getPlayer(args[1]);
					
					try {
						if (targetPlayer.isOnline()){
							String message = "";
							for (int index = 2; index < args.length; index++){
								message += args[index] + " ";
							}
							server.broadcastMessage("<" + targetPlayer.getDisplayName() + "> " +  message);
						}
						else {
							player.sendMessage("Player is offline");
						}
					}
					catch (NullPointerException e){
						player.sendMessage("No such player");
					}
				}
				
				//
				else if (args[0].equalsIgnoreCase("fling")){
					Player targetPlayer = server.getPlayer(args[1]);
					
					try {
						if (targetPlayer.isOnline()){
							targetPlayer.teleport(targetPlayer.getLocation().add(0, 30, 0));
							String message = "";
							for (int index = 2; index < args.length; index++){
								message += args[index] + " ";
							}
							server.broadcastMessage(targetPlayer.getDisplayName() + " has been flung for " + message);
						}
						else {
							player.sendMessage("Player is offline");
						}
					}
					catch (NullPointerException e){
						player.sendMessage("No such player");
					}	
				}
				
				//
				else if (args[0].equalsIgnoreCase("pot")){
					Player targetPlayer = server.getPlayer(args[1]);
					
					try{
						if (targetPlayer.isOnline()){
							PotionType pt = PotionType.getByDamageValue(Integer.parseInt(args[2]));
							PotionEffect pot = new PotionEffect(pt.getEffectType(), 1200, 1);
							targetPlayer.addPotionEffect(pot);
							server.broadcastMessage(targetPlayer.getDisplayName() + " has been given the " + pt.toString() + " potion");
						}
						else {
							server.broadcastMessage("Player is offline");
						}
					}
					catch (NullPointerException e){
						player.sendMessage("No such player or potion has no effect");
					}
				}
				
				
				else if (args[0].equalsIgnoreCase("sorry")){
					Player targetPlayer = server.getPlayer(args[1]);
					
					try {
						if (targetPlayer.isOnline()){
							String message = "";
							for (int index = 2; index < args.length; index++){
								message += args[index] + " ";
							}
							server.broadcastMessage(targetPlayer.getDisplayName() + " is sorry for " + message);
						}
					}
					catch (NullPointerException e){
						player.sendMessage("No such player");
					}
				}
				else{
					player.sendMessage("Correct Usage:");
					player.sendMessage("/ph say <player> <message>");
					player.sendMessage("/ph fling <player> <reason>");
					player.sendMessage("/ph pot <player> <effect>");
					player.sendMessage("/ph sorry <player> <reason>");					
				}
			}
		}
		return false;
	}
}
