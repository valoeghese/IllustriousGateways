package tk.valoeghese.teleport;

import java.util.function.IntFunction;

import io.github.minecraftcursedlegacy.api.client.AtlasMap;
import io.github.minecraftcursedlegacy.api.recipe.Recipes;
import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import io.github.minecraftcursedlegacy.api.registry.TileItems;
import io.github.minecraftcursedlegacy.api.registry.Translations;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.item.PlaceableTileItem;
import net.minecraft.recipe.DyeRecipes;
import net.minecraft.recipe.SmeltingRecipeRegistry;
import net.minecraft.tile.Tile;

public class Mod implements ModInitializer {
	@Override
	public void onInitialize() {
		System.out.println("Setting up Illustrious Gateways");

		// teleporter
		teleporter = Registries.TILE.register(new Id("illustrious_gateways", "teleporter"), Teleporter::new);
		TileItems.registerTileItem(new Id("illustrious_gateways", "teleporter"), teleporter, i -> (teleporterItem = new PlaceableTileItem(i)));
		teleporterItem.setTexturePosition(1, 0);
		AtlasMap.registerAtlas(teleporterItem, "/assets/teleport/item_textures.png");
		Translations.addTileTranslation(teleporter, "Teleporter");

		// items

		seekingStone = createItem("seeking_stone", 0, 1, BasicItem::new);
		seekingStone.setName("seekingStone");
		Translations.addItemTranslation(seekingStone, "Seeking Stone");

		seekingGem = createItem("seeking_gem", 1, 1, SeekingGem::new);
		seekingGem.setName("seekingGem");
		Translations.addItemTranslation(seekingGem, "Seeking Gem");

		lodestone = createItem("lodestone", 0, 0, BasicItem::new);
		lodestone.setName("lodestone");
		Translations.addItemTranslation(lodestone, "Lodestone");

		Recipes.addShapelessRecipe(new ItemInstance(seekingStone), lodestone, new ItemInstance(ItemType.dyePowder, 1, 4), Tile.REDSTONE_DUST.id);
		Recipes.addShapelessRecipe(new ItemInstance(seekingGem), seekingStone);
		Recipes.addShapedRecipe(new ItemInstance(teleporter), "XXX", "OBO", "CCC");
//		SmeltingRecipeRegistry.getInstance().addSmeltingRecipe(, output); TODO remap smelting recipes so this can work
	}

	private static ItemType createItem(String idName, int u, int v, IntFunction<ItemType> constructor) {
		ItemType result = Registries.ITEM_TYPE.register(new Id("illustrious_gateways", idName), constructor);
		result.setTexturePosition(u, v);
		AtlasMap.registerAtlas(result, "/assets/teleport/item_textures.png");
		return result;
	}

	public static Tile teleporter;
	public static PlaceableTileItem teleporterItem;
	public static ItemType seekingStone;
	public static ItemType seekingGem;
	public static ItemType lodestone;
}
