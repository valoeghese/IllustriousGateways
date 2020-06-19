package tk.valoeghese.teleport.item;

import net.minecraft.entity.player.Player;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.level.Level;
import net.minecraft.util.maths.TilePos;
import tk.valoeghese.teleport.TeleporterMod;

public class SeekingGem extends ItemType {
	public SeekingGem(int id) {
		super(id);

		this.setName("seekingStone");
		this.setMaxStackSize(1);
		this.setDurability(70);
	}

	@Override
	public boolean useOnTile(ItemInstance item, Player player, Level level, int x, int y, int z, int facing) {
		if (level.getTileId(x, y, z) == TeleporterMod.teleporter.id) {
			((SeekingItem) (Object) item).setSeekingPos(x, y, z);
			item.applyDamage(1, player);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ItemInstance use(ItemInstance item, Level level, Player player) {
		SeekingItem si = ((SeekingItem) (Object) item);

		if (si.hasPosition()) {
			TilePos pos = si.getSeekingPos();
			int x = pos.x;
			int y = pos.y;
			int z = pos.z;

			if (level.getTileId(x, y, z) == TeleporterMod.teleporter.id) {
				if (y >= 127 || level.getTileId(x, y + 1, z) == 0) {
					if (y >= 126 || level.getTileId(x, y + 2, z) == 0) {
						item.applyDamage(2, player);
						player.setPosition(x + 0.5, y + 3, z + 0.5);
					}
				}
			}
		}

		return item;
	}
}
