package fr.kewanb.vectaliaplugin;


import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


import fr.kewanb.vectaliaplugin.commands.CommandCustomSword;

public class Vectalia extends JavaPlugin {
	
	public ItemStack sword;
	public CustomSwords customSwords = new CustomSwords(this);

	@Override
	public void onEnable() {
		saveDefaultConfig();
		
		
		System.out.println("Chargement de VectaliaPlugin");
		getCommand("customsword").setExecutor(new CommandCustomSword(this));
		getServer().getPluginManager().registerEvents(new EventsListener(this), this);
	}
	
	@Override
	public void onDisable() {
	
		System.out.println("Arret de VectaliaPlugin");
	}
	
}
