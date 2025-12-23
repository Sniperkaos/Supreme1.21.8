package dev.cworldstar.protocol;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.scheduler.BukkitRunnable;

import com.github.relativobr.supreme.Supreme;

import dev.cworldstar.utils.FormatUtils;
import dev.cworldstar.utils.ItemStackBuilder;
import net.kyori.adventure.text.Component;

public class ItemNameLerpPassthrough {
	private double ticks = 0;
	private boolean reverse = false;
	
	public Component replacePlaceholder(Component base, String placeholder, String replacement) {
		return FormatUtils.replace(base, placeholder, replacement);
	}
	
	public static ItemNameLerpPassthrough start() {
		return new ItemNameLerpPassthrough();
	}
	
	private ItemNameLerpPassthrough() {
		new BukkitRunnable() {
			@Override
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()) {
					PlayerInventory inv = p.getInventory();
					for(int i=0; i<=35; i++) {
						ItemStack item = inv.getItem(i);
						if(item == null) continue;
						ItemMeta meta = item.getItemMeta();
						if(meta != null) {
							PersistentDataContainer pdc = meta.getPersistentDataContainer();
							if(pdc.has(ItemStackBuilder.COLOR_LERP_KEY)) {
								ItemEditorProtocol.updateItem(i, item.clone(), p);
							}
						}
					}
				}
				
				if(reverse) {
					ticks -= 1;
				} else {
					ticks += 1;
				}
				if(ticks >= 100 || ticks <= -100) {
					reverse = !reverse;
				}
			}
		}.runTaskTimer(Supreme.inst(), 0L, 10L);
		ItemEditorProtocol.passthrough((ItemStack item, Player player) -> {
			ItemMeta meta = item.getItemMeta();
			if(meta != null) {
				PersistentDataContainer pdc = meta.getPersistentDataContainer();
				if(pdc.has(ItemStackBuilder.COLOR_LERP_KEY)) {
					if(meta.hasItemName()) {
						meta.itemName(
							replacePlaceholder(meta.itemName(), "%rainbow%", "<rainbow:"+String.valueOf(0-(ticks/100))+">")
						);
					}
					if(meta.hasLore()) {
						List<Component> lore = meta.lore();
						int line = 0;
						for(Component c : lore) {
							lore.set(line, replacePlaceholder(c, "%rainbow%", "<rainbow:"+String.valueOf(1-(ticks/100))+">"));
							line++;
						}
					}
					item.setItemMeta(meta);
				}
			}
			return item;
		});
	}
}
