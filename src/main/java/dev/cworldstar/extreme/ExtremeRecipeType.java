package dev.cworldstar.extreme;

import org.bukkit.Material;
import com.github.relativobr.supreme.Supreme;

import dev.cworldstar.extreme.machines.ExtremeCraftingTable;
import dev.cworldstar.extreme.machines.ExtremeCraftingTable.ExtremeCraftingTableRecipe;
import dev.cworldstar.utils.ItemStackBuilder;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;

public class ExtremeRecipeType {
	public static final RecipeType DIMENSIONAL_QUARRY = new RecipeType(
		Supreme.key("EXTREME_DIMENSIONAL_QUARRY"), 
		new ItemStackBuilder(Material.VAULT)
			.name("<gradient:purple:blue>Dimensional Quarry")
			.build()
	);
	public static final RecipeType EXTREME_CRAFTING = new RecipeType(
			Supreme.key("EXTREME_CRAFTING"), 
			new ItemStackBuilder(Material.STRIPPED_PALE_OAK_LOG)
				.name("<gradient:purple:gold>Extreme Crafting")
				.build(),
			(recipe, output) -> {
				ExtremeCraftingTable.addRecipe(ExtremeCraftingTableRecipe.create(recipe, output, output.getAmount()));
			}
		);
}
