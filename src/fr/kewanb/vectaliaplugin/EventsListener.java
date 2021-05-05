package fr.kewanb.vectaliaplugin;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

import net.ess3.api.Economy;
@SuppressWarnings("deprecation")
public class EventsListener implements Listener
{
private Vectalia main;
    
    public EventsListener(final Vectalia vectalia) {
        this.main = vectalia;
    }
    
	@EventHandler
    public void onDamage(final EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player && event.getEntity() instanceof Player) {
            final Player playerDamager = (Player)event.getDamager();
            final Player playerDamagee = (Player)event.getEntity();
            final ItemStack item = playerDamager.getInventory().getItemInHand();
            if (item.hasItemMeta()) {
                final ItemMeta itemMeta = item.getItemMeta();
                if (itemMeta.hasDisplayName() && itemMeta.hasLore()) {
                    final String swordName = item.getItemMeta().getLore().get(0).replace(" I", "").replace("�7", "").toLowerCase();
                    final ItemStack customsword = this.main.customSwords.CustomSwords.get(swordName);
                    final ItemMeta customswordMeta = customsword.getItemMeta();
                    if (this.main.getConfig().getString("customswords." + swordName + ".name") != null && itemMeta.equals(customswordMeta)) {
                        if (swordName.equals("corruption")) {
                            System.out.println("corruption");
                            final double moneyToRemove = this.main.getConfig().getDouble("customswords." + swordName + ".moneytoremove");
                            if (Math.random() * 100.0 >= this.main.getConfig().getDouble("customswords." + swordName + ".chance") * 10.0) {
                                return;
                            }
                            try {
                                if (Economy.getMoney(playerDamagee.getName()) >= 500.0) {
                                    playerDamager.sendMessage("Vous venez de voler " + moneyToRemove + "$ \u00e0 votre adversaire (Ep�e de corruption) !");
                                    playerDamagee.sendMessage("Votre adversaire viens de vous voler " + moneyToRemove + "$ (Ep�e de corruption) !");
                                    System.out.println(String.valueOf(playerDamager.getDisplayName()) + " viens de voler " + moneyToRemove + "$ \u00e0 " + playerDamagee.getDisplayName() + " ! (Ep�e corruption)");
                                    try {
										Economy.subtract(playerDamagee.getName(), moneyToRemove);
										Economy.add(playerDamager.getName(), moneyToRemove);
									} catch (NoLoanPermittedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

                                }
                                return;
                            }
                            catch (UserDoesNotExistException e2) {
                                e2.printStackTrace();
                                return;
                            }
                        }else if (swordName.equals("servitude")) {
                            System.out.println("servitude");
                            final Integer duree = this.main.getConfig().getInt("customswords." + swordName + ".duree");
                            if (Math.random() * 100.0 < this.main.getConfig().getDouble("customswords." + swordName + ".chance") * 10.0) {
                                playerDamager.sendMessage("Vous venez de ralentir votre adversaire pour une dur\u00e9e de " + duree + " secondes (Ep�e de servitude) !");
                                playerDamagee.sendMessage("Votre adversaire viens de vous ralentir pour une dur\u00e9e de " + duree + " secondes (Ep�e de servitude) !");
                                System.out.println(String.valueOf(playerDamager.getDisplayName()) + " viens de ralentir " + playerDamagee.getDisplayName() + " pour une dur\u00e9e de " + duree + " secondes ! (Ep�e servitude)");
                                playerDamagee.addPotionEffect(new PotionEffect(PotionEffectType.getByName(this.main.getConfig().getString("customswords." + swordName + ".effect")), duree * 20, this.main.getConfig().getInt("customswords." + swordName + ".effectLvl")));
                            }
                        }else if (swordName.equals("dilitro")) {
                            System.out.println("dilitro");
                            final Integer duree2 = this.main.getConfig().getInt("customswords." + swordName + ".duree");
                            if (Math.random() * 100.0 < this.main.getConfig().getDouble("customswords." + swordName + ".chance") * 10.0) {
                                playerDamager.sendMessage("Vous venez de d'empoisoner votre adversaire pour une dur\u00e9e de " + duree2 + " secondes (Ep�e dilitro) !");
                                playerDamagee.sendMessage("Votre adversaire viens de vous empoisoner pour une dur\u00e9e de " + duree2 + " secondes (Ep�e dilitro) !");
                                System.out.println(String.valueOf(playerDamager.getDisplayName()) + " viens d'empoisoner " + playerDamagee.getDisplayName() + " pour une dur\u00e9e de " + duree2 + " secondes ! (Ep�e dilitro)");
                                playerDamagee.addPotionEffect(new PotionEffect(PotionEffectType.getByName(this.main.getConfig().getString("customswords." + swordName + ".effect")), duree2 * 20, this.main.getConfig().getInt("customswords." + swordName + ".effectLvl")));
                            }
                        }else if (swordName.equals("fulminante")) {
                            System.out.println("fulminante");
                            if (Math.random() * 100.0 < this.main.getConfig().getDouble("customswords." + swordName + ".chance") * 10.0) {
                            	playerDamager.sendMessage("Vous venez de foudroyer votre adversaire (Ep�e fulminante) !");
                                playerDamagee.sendMessage("Votre adversaire viens de vous foudroyer (Ep�e fulminante) !");
                                System.out.println(String.valueOf(playerDamager.getDisplayName()) + " viens de foudroyer " + playerDamagee.getDisplayName() + " ! (Ep�e fulminante)");
                                playerDamagee.getWorld().createExplosion(playerDamagee.getLocation().getX(), playerDamagee.getLocation().getY(), playerDamagee.getLocation().getZ(), 4, true);
                            }
                        }else if (swordName.equals("foudroyante")) {
                            System.out.println("foudroyante");
                            if (Math.random() * 100.0 < this.main.getConfig().getDouble("customswords." + swordName + ".chance") * 10.0) {
                            	playerDamager.sendMessage("Vous venez de foudroyer votre adversaire (Ep�e foudroyante) !");
                                playerDamagee.sendMessage("Votre adversaire viens de vous foudroyer (Ep�e foudroyante) !");
                                System.out.println(String.valueOf(playerDamager.getDisplayName()) + " viens de foudroyer " + playerDamagee.getDisplayName() + " ! (Ep�e foudroyante)");
                                playerDamagee.getWorld().strikeLightning(playerDamagee.getLocation());
                            }
                        }else if (swordName.equals("misericorde")) {
                            System.out.println("misericorde");
                            final Integer duree2 = this.main.getConfig().getInt("customswords." + swordName + ".duree");
                            if (Math.random() * 100.0 < this.main.getConfig().getDouble("customswords." + swordName + ".chance") * 10.0) {
                                playerDamager.sendMessage("Vous venez de d'aveugler votre adversaire pour une dur\u00e9e de " + duree2 + " secondes (Ep�e misericorde) !");
                                playerDamagee.sendMessage("Votre adversaire viens de vous aveugler pour une dur\u00e9e de " + duree2 + " secondes (Ep�e misericorde) !");
                                System.out.println(String.valueOf(playerDamager.getDisplayName()) + " viens d'aveugler " + playerDamagee.getDisplayName() + " pour une dur\u00e9e de " + duree2 + " secondes ! (Ep�e misericorde)");
                                playerDamagee.addPotionEffect(new PotionEffect(PotionEffectType.getByName(this.main.getConfig().getString("customswords." + swordName + ".effect")), duree2 * 20, this.main.getConfig().getInt("customswords." + swordName + ".effectLvl")));
                            }
                        }else if (swordName.equals("damnation")) {
                            System.out.println("damnation");
                            if (Math.random() * 100.0 < this.main.getConfig().getDouble("customswords." + swordName + ".chance") * 10.0) {
                                playerDamager.sendMessage("Vous venez de perdre tout vos effets de potion (Ep�e damnation) !");
                                playerDamagee.sendMessage("Votre adversaire viens de perdre tout ses effets de potion (Ep�e damnation) !");
                                System.out.println(String.valueOf(playerDamager.getDisplayName()) + " viens d'aveugler " + playerDamagee.getDisplayName() + " pour une dur\u00e9e de secondes ! (Ep�e damnation)");
                                for (PotionEffect playerEffect: playerDamagee.getActivePotionEffects()) {
									playerDamagee.removePotionEffect(playerEffect.getType());
								}
                            }
                        }
                    }
                }
            }
        }
    }
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack currentItem = event.getCurrentItem();
		if(currentItem == null) return;
		
		
	
		if(inv.getName().equals("�7Ep�es Custom")) {
			event.setCancelled(true);
			if(currentItem.hasItemMeta()) {
				ItemMeta currentItemMeta = currentItem.getItemMeta();
				if (currentItemMeta.hasDisplayName() && currentItemMeta.hasLore()) {
					final String swordName = currentItemMeta.getLore().get(0).replace(" I", "").replace("�7", "").toLowerCase();
					final ItemStack customsword = this.main.customSwords.CustomSwords.get(swordName);
					final ItemMeta customswordMeta = customsword.getItemMeta();
					if (main.customSwords.CustomSwords.get(swordName) != null && currentItemMeta.equals(customswordMeta)) {
						player.getInventory().addItem(this.main.customSwords.CustomSwords.get(swordName));
					}
				}
			}
		}
	
	}
	
}