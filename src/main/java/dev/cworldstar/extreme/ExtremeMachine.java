package dev.cworldstar.extreme;

import org.bukkit.inventory.ItemStack;

import com.github.relativobr.supreme.Supreme;

import dev.cworldstar.extreme.machines.ExtremeCraftingTable;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import net.guizhanss.infinityexpansion2.implementation.IEItems;

public class ExtremeMachine {
	
	public static ExtremeCraftingTable EXTREME_CRAFTING_TABLE;
	
	public static void register() {
		Supreme s = Supreme.inst();
		
		EXTREME_CRAFTING_TABLE.setCapacity(100000).setProcessingSpeed(1).setEnergyConsumption(1);
		EXTREME_CRAFTING_TABLE.register(s);
	}
	
	public static void setup() {
		EXTREME_CRAFTING_TABLE = new ExtremeCraftingTable(
				Extreme.EXTREME_MACHINE_GROUP,
				ExtremeCraftingTable.EXTREME_CRAFTING_TABLE_ITEM,
				RecipeType.ENHANCED_CRAFTING_TABLE,
				new ItemStack[] {
						ExtremeResource.BEYOND_ALLOY.getItem(), ExtremeResource.BEYOND_ALLOY.getItem(), ExtremeResource.BEYOND_ALLOY.getItem(),
						ExtremeResource.BEYOND_ALLOY.getItem(), IEItems.INSTANCE.getINFINITY_WORKBENCH().item(), ExtremeResource.BEYOND_ALLOY.getItem(),
						ExtremeResource.BEYOND_ALLOY.getItem(), ExtremeResource.BEYOND_ALLOY.getItem(), ExtremeResource.BEYOND_ALLOY.getItem()
				}
		);
	}
}
