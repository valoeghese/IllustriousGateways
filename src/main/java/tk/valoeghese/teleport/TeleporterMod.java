package tk.valoeghese.teleport;

import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.tile.Tile;
import tk.valoeghese.teleport.item.TeleportItems;

public class TeleporterMod implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Setting up Illustrious Gateways");

		// teleporter
		teleporter = Registries.TILE.register(new Id("illustrious_gateways", "teleporter"), Teleporter::new);

		Recipes.addShapelessRecipe(new ItemInstance(TeleportItems.seekingStone), new ItemInstance(ItemType.dyePowder, 1, 4), TeleportItems.lodestone, Tile.REDSTONE_DUST);
		// temporary: see below
		Recipes.addShapelessRecipe(new ItemInstance(TeleportItems.seekingGem), TeleportItems.seekingStone);

		// Tile.WOOD = planks
		Recipes.addShapedRecipe(new ItemInstance(teleporter), "XXX", "CDC", "CGC", 'X', Tile.WOOD, 'C', Tile.STONEBRICK, 'D', ItemType.diamond, 'G', TeleportItems.seekingStone);

		// TODO remap smelting recipes so this can work
		// SmeltingRecipeRegistry.getInstance().addSmeltingRecipe(, output);
	}

	public static Tile teleporter;
}
