package tk.valoeghese.teleport.item;

import java.util.function.IntFunction;

import io.github.minecraftcursedlegacy.api.client.AtlasMap;
import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import io.github.minecraftcursedlegacy.api.registry.TileItems;
import io.github.minecraftcursedlegacy.api.registry.Translations;
import net.minecraft.item.ItemType;
import net.minecraft.item.PlaceableTileItem;
import tk.valoeghese.teleport.TeleporterMod;

public class TeleportItems {
	public static void addItems() {
		TileItems.registerTileItem(new Id("illustrious_gateways", "teleporter"), TeleporterMod.teleporter, i -> (teleporterItem = new PlaceableTileItem(i)));
		teleporterItem.setTexturePosition(1, 0);
		AtlasMap.registerAtlas(teleporterItem, "/assets/teleport/item_textures.png");
		Translations.addTileTranslation(TeleporterMod.teleporter, "Teleporter");

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
	}

	private static ItemType createItem(String idName, int u, int v, IntFunction<ItemType> constructor) {
		ItemType result = Registries.ITEM_TYPE.register(new Id("illustrious_gateways", idName), constructor);
		result.setTexturePosition(u, v);
		AtlasMap.registerAtlas(result, "/assets/teleport/item_textures.png");
		return result;
	}

	public static PlaceableTileItem teleporterItem;
	public static ItemType seekingStone;
	public static ItemType seekingGem;
	public static ItemType lodestone;
}
