package dev.cworldstar.extreme;

import java.util.Map;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import com.github.relativobr.supreme.Supreme;
import com.github.relativobr.supreme.resource.SupremeComponents;
import com.github.relativobr.supreme.resource.magical.SupremeCore;

import dev.cworldstar.utils.ItemStackBuilder;
import dev.cworldstar.utils.PlayerHeadBuilder;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import net.guizhanss.infinityexpansion2.implementation.IEItems;
import net.guizhanss.infinityexpansion2.implementation.items.materials.SimpleMaterial;
import net.guizhanss.infinityexpansion2.implementation.items.materials.Singularity;
import net.guizhanss.infinityexpansion2.implementation.recipes.IERecipeTypes;

/**
 * Static class to hold resources.
 */
public class ExtremeResource {
	public static Singularity SUPREME_SINGULARITY;
	public static Singularity CORE_OF_LIFE_SINGULARITY;
	public static Singularity CORE_OF_ALLOY_SINGULARITY;
	public static Singularity CORE_OF_BLOCK_SINGULARITY;
	public static Singularity CORE_OF_COLOR_SINGULARITY;
	public static Singularity CORE_OF_DEATH_SINGULARITY;
	public static Singularity CORE_OF_NATURE_SINGULARITY;

	public static SimpleMaterial RHEANIUM; // core of life singularity + rheanium -> life alloy
	public static SimpleMaterial BEYOND_ALLOY;
	public static SimpleMaterial LIFE_ALLOY;
	
	public static void register() {
		SUPREME_SINGULARITY.register(Supreme.inst());
		CORE_OF_LIFE_SINGULARITY.register(Supreme.inst());
		CORE_OF_ALLOY_SINGULARITY.register(Supreme.inst());
		CORE_OF_BLOCK_SINGULARITY.register(Supreme.inst());
		CORE_OF_COLOR_SINGULARITY.register(Supreme.inst());
		CORE_OF_DEATH_SINGULARITY.register(Supreme.inst());
		CORE_OF_NATURE_SINGULARITY.register(Supreme.inst());
		RHEANIUM.register(Supreme.inst());
		BEYOND_ALLOY.register(Supreme.inst());
		LIFE_ALLOY.register(Supreme.inst());
	}
	
	public static void setup() {
		CORE_OF_LIFE_SINGULARITY = 
			new Singularity(
				Extreme.EXTREME_RESOURCE_GROUP, 
				new SlimefunItemStack(
					"EXTREME_CORE_OF_LIFE_SINGULARITY", 
					new PlayerHeadBuilder()
						.texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWVmNTM5YjE2NTEyNWNmYTQ2YjA2ZmZiOTY1OWU3Y2Y4OTA4NGJiZDNlZGUxYjMxNGVkYzhmNDQzMzQzZDYxYyJ9fX0=")
						.name("&aLife Core Singularity")
						.lore(new String[] {
							"",
							"<gradient:#292f56:#ACFA70>A compressed core of life.",
							"",
							ExtremeLore.EXTREME_COMPONENT
						})
						.build()
				),
				IERecipeTypes.INSTANCE.getSINGULARITY_CONSTRUCTOR(),
				new ItemStack[0],
				2000,
				Map.of(
					SupremeCore.CORE_OF_LIFE.item(), 2
				)
			);
		CORE_OF_ALLOY_SINGULARITY = 
				new Singularity(
					Extreme.EXTREME_RESOURCE_GROUP, 
					new SlimefunItemStack(
						"EXTREME_CORE_OF_ALLOY_SINGULARITY", 
						new PlayerHeadBuilder()
							.texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTZlYjA4OWJjMTZiY2NlNGYyYmJkMWQ1MDM4M2NmMjQ1YjM3ZGY5NDA1ODI1MDBlNzRmYjhiMTdhMzM2ZGIwNSJ9fX0=")
							.name("&aAlloy Core Singularity")
							.lore(new String[] {
								"",
								"<gradient:#292f56:#ACFA70>A compressed core of alloys.",
								"",
								ExtremeLore.EXTREME_COMPONENT
							})
							.build()
					),
					IERecipeTypes.INSTANCE.getSINGULARITY_CONSTRUCTOR(),
					new ItemStack[0],
					2000,
					Map.of(
							SupremeCore.CORE_OF_ALLOY.item(), 2
					)
				);
		CORE_OF_BLOCK_SINGULARITY = 
				new Singularity(
					Extreme.EXTREME_RESOURCE_GROUP, 
					new SlimefunItemStack(
						"EXTREME_CORE_OF_BLOCK_SINGULARITY", 
						new PlayerHeadBuilder()
							.texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjVmMDRjMDk3OTMzMjNmNDFkYjliZGQ2M2ZiMDY4Njg4ZDk0YWIzY2MzN2EzOGMxNzdkZTllNWE5M2MyZjQ3YiJ9fX0=")
							.name("&aBlock Core Singularity")
							.lore(new String[] {
								"",
								"<gradient:#292f56:#ACFA70>A compressed core of blocks.",
								"",
								ExtremeLore.EXTREME_COMPONENT
							})
							.build()
					),
					IERecipeTypes.INSTANCE.getSINGULARITY_CONSTRUCTOR(),
					new ItemStack[0],
					2000,
					Map.of(
							SupremeCore.CORE_OF_BLOCK.item(), 2
					)
				);
		CORE_OF_COLOR_SINGULARITY = 
				new Singularity(
					Extreme.EXTREME_RESOURCE_GROUP, 
					new SlimefunItemStack(
						"EXTREME_CORE_OF_COLOR_SINGULARITY", 
						new PlayerHeadBuilder()
							.texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNGFjMTdiNGNiZGQ0Y2RhNDY3MjkzNDM4ZDE4NTExYzIzYzEwMjdiYjQwMDE1NDYxNGEyZDJjZjQ0MzE4YTYyYSJ9fX0=")
							.name("&aColor Core Singularity")
							.lore(new String[] {
								"",
								"<gradient:#292f56:#ACFA70>A compressed core of colors.",
								"",
								ExtremeLore.EXTREME_COMPONENT
							})
							.build()
					),
					IERecipeTypes.INSTANCE.getSINGULARITY_CONSTRUCTOR(),
					new ItemStack[0],
					2000,
					Map.of(
							SupremeCore.CORE_OF_COLOR.item(), 2
					)
				);
		CORE_OF_DEATH_SINGULARITY = 
				new Singularity(
					Extreme.EXTREME_RESOURCE_GROUP, 
					new SlimefunItemStack(
						"EXTREME_CORE_OF_DEATH_SINGULARITY", 
						new PlayerHeadBuilder()
							.texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2I5YTEyOTU4Y2RhZDljYjNmODc1NGY4OTFmNTA5Nzg0MmExNjYwY2Y3YjRkN2EyZWY0NTdmOTUyNmYyZjU0NyJ9fX0=")
							.name("&aColor Core Singularity")
							.lore(new String[] {
								"",
								"<gradient:#292f56:#ACFA70>A compressed core of death.",
								"",
								ExtremeLore.EXTREME_COMPONENT
							})
							.build()
					),
					IERecipeTypes.INSTANCE.getSINGULARITY_CONSTRUCTOR(),
					new ItemStack[0],
					2000,
					Map.of(
							SupremeCore.CORE_OF_DEATH.item(), 2
					)
				);
		CORE_OF_NATURE_SINGULARITY = 
				new Singularity(
					Extreme.EXTREME_RESOURCE_GROUP, 
					new SlimefunItemStack(
						"EXTREME_CORE_OF_NATURE_SINGULARITY", 
						new PlayerHeadBuilder()
							.texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzUxYWQzZGRlOWI2M2M3MWFkYmY5OGFkYjFmMmVhNDY0YzJlYjc4OTVkOWVmNTZjZTZhYjJjM2NmYzAyNCJ9fX0=")
							.name("&aNature Core Singularity")
							.lore(new String[] {
								"",
								"<gradient:#292f56:#ACFA70>A compressed core of nature.",
								"",
								ExtremeLore.EXTREME_COMPONENT
							})
							.build()
					),
					IERecipeTypes.INSTANCE.getSINGULARITY_CONSTRUCTOR(),
					new ItemStack[0],
					2000,
					Map.of(
							SupremeCore.CORE_OF_NATURE.item(), 2
					)
				);
		SUPREME_SINGULARITY = 
			new Singularity(
				Extreme.EXTREME_RESOURCE_GROUP, 
				new SlimefunItemStack(
					"EXTREME_SUPREME_SINGULARITY", 
					new PlayerHeadBuilder()
						.texture("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWQ2NTVkNzk1MjNmOGM3MzQ1ZDQ1MGVkOWZhY2VhNzA4ZWRlZjJlNzA4N2UwYzQ2MzE3N2E3ODQ0ODBmNDkzZSJ9fX0=")
						.name("&aSupreme Singularity")
						.lore(new String[] {
							"",
							"<gradient:#292f56:#ACFA70>A screaming cacophony of color and noise.",
							"<gradient:#ACFA70:#292f56>It's best to look away from it.",
							"",
							ExtremeLore.EXTREME_COMPONENT
						})
						.build()
				),
				IERecipeTypes.INSTANCE.getSINGULARITY_CONSTRUCTOR(),
				new ItemStack[0],
				4500,
				Map.of(
					SupremeComponents.SUPREME.item(), 9,
					SupremeComponents.SUPREME_NUGGET.item(), 1
				)
			);
		RHEANIUM = 
			new SimpleMaterial(
				Extreme.EXTREME_RESOURCE_GROUP,
				new SlimefunItemStack(
					"EXTREME_RHEANIUM_INGOT",
					new ItemStackBuilder(Material.RESIN_BRICK)
						.name("<gradient:#14a917:#68bd26>Rheanium Ingot")
						.lore(new String[] {
							"",
							"<gray>Found in the depths of the void,",
							"<gray>this metal has unknown properties.",
							"",
							ExtremeLore.EXTREME_COMPONENT
						})
						.build()
				),
				ExtremeRecipeType.DIMENSIONAL_QUARRY,
				new ItemStack[] {
						
				}
			);
		LIFE_ALLOY = 
				new SimpleMaterial(
						Extreme.EXTREME_RESOURCE_GROUP,
						new SlimefunItemStack(
							"EXTREME_LIFE_ALLOY_INGOT",
							new ItemStackBuilder(Material.IRON_INGOT)
								.name("<green>Life Alloy Ingot")
								.lore(new String[] {
									"",
									"<gray>Life is good.",
									"",
									ExtremeLore.EXTREME_COMPONENT
								})
								.build()
						),
						RecipeType.SMELTERY,
						new ItemStack[] {
								IEItems.INSTANCE.getINFINITY_INGOT().asQuantity(2), RHEANIUM.getItem().asQuantity(1), CORE_OF_LIFE_SINGULARITY.getItem().asQuantity(3)
						}
					);
		BEYOND_ALLOY = 
				new SimpleMaterial(
					Extreme.EXTREME_RESOURCE_GROUP,
					new SlimefunItemStack(
						"EXTREME_BEYOND_ALLOY_INGOT",
						new ItemStackBuilder(Material.RESIN_BRICK)
							.name("<gradient:white:green>Beyond Alloy Ingot")
							.lore(new String[] {
								"",
								"<gray>Have we gone too far?",
								"",
								ExtremeLore.EXTREME_COMPONENT
							})
							.build()
					),
					RecipeType.SMELTERY,
					new ItemStack[] {
							IEItems.INSTANCE.getINFINITY_INGOT().asQuantity(1), SupremeComponents.SUPREME.asQuantity(8), SlimefunItems.FUEL_BUCKET.asQuantity(16), SlimefunItems.CHEESE.asQuantity(64)
					}
				);
	}
}
