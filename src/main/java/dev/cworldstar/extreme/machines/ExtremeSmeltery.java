package dev.cworldstar.extreme.machines;

import org.bukkit.Material;
import org.bukkit.persistence.PersistentDataType;

import com.github.relativobr.supreme.util.UtilEnergy;

import dev.cworldstar.utils.ItemStackBuilder;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;

public class ExtremeSmeltery {
	public static final SlimefunItemStack EXTREME_SMELTERY_I = new SlimefunItemStack("EXTREME_SMELTERY_I", 
			new ItemStackBuilder(Material.FURNACE)
			.name("<red>Extreme Smeltery <gray>(<white>Tier 1<gray>)")
			.lore(new String[] {
					"",
					"<white>This machine is used for crafting end-game alloys.",
					"<white>Requires 150,000J at the start of the craft, and",
					"<white>10,000J per tick.",
					"",
					"%rainbow%Extreme Machine</rainbow>",
					UtilEnergy.energyBuffer(500000),
					UtilEnergy.energyPowerPerItem(150000),
					UtilEnergy.energyPowerPerTick(10000),
					LoreBuilder.speed(8),
					"",
					"<gradient:#c13838:#8ba542:#672ca5:#a92e7f:#86dd5a>ᴇxᴛʀᴇᴍᴇ ᴍᴀᴄʜɪɴᴇ"
			})
			.pdc(container -> {
				container.set(ItemStackBuilder.COLOR_LERP_KEY, PersistentDataType.DOUBLE, -100D);
			})
	.build());
}
