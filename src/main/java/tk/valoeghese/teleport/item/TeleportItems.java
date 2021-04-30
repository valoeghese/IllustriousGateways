package tk.valoeghese.teleport.item;

import java.util.function.IntFunction;

import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import io.github.minecraftcursedlegacy.api.registry.TileItems;
import io.github.minecraftcursedlegacy.api.registry.Translations;
import net.minecraft.item.ItemType;
import net.minecraft.item.PlaceableTileItem;
import paulevs.corelib.CoreLib;
import paulevs.corelib.model.TextureInfo;
import paulevs.corelib.model.prefab.FullCubeModel;
import paulevs.corelib.registry.ModelRegistry;
import paulevs.corelib.texture.Texture2D;
import tk.valoeghese.teleport.TeleporterMod;

// 
// DISCLAIMER
// If you are referencing this and want to do textures
// Use the old item texture atlas api
// I'm in the middle of making it use corelib and this is stupidly difficult
// Might end up undeprecating the item texture atlas api in cursed legacy at this rate
//
public class TeleportItems {
	public static void addItems() {
		teleporterItem = (PlaceableTileItem) TileItems.registerTileItem(new Id("illustrious_gateways", "teleporter"), TeleporterMod.teleporter);
		Translations.addTileTranslation(TeleporterMod.teleporter, "Teleporter");

		// items

		seekingStone = createItem("seeking_stone", 0, 1, BasicItem::new);
		seekingStone.setName("seekingStone");
		Translations.addItemTranslation(seekingStone, "Seeking Stone");

		//ModelRegistry.addItemModel(seekingStone, Model);

		seekingGem = createItem("seeking_gem", 1, 1, SeekingGem::new);
		seekingGem.setName("seekingGem");
		Translations.addItemTranslation(seekingGem, "Seeking Gem");

		lodestone = createItem("lodestone", 0, 0, BasicItem::new);
		lodestone.setName("lodestone");
		Translations.addItemTranslation(lodestone, "Lodestone");
	}

	private static ItemType createItem(String idName, int u, int v, IntFunction<ItemType> constructor) {
		ItemType result = Registries.ITEM_TYPE.register(new Id("illustrious_gateways", idName), constructor);
		result.setTexturePosition(u, v);
		return result;
	}

	public static PlaceableTileItem teleporterItem;
	public static ItemType seekingStone;
	public static ItemType seekingGem;
	public static ItemType lodestone;
}
