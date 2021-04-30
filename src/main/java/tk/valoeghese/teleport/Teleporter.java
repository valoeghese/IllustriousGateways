package tk.valoeghese.teleport;

import net.minecraft.tile.Tile;
import net.minecraft.tile.material.Material;

public class Teleporter extends Tile {
	Teleporter(int id) {
		super(id, Material.METAL);
		this.sounds(Tile.GLASS_SOUNDS);
		this.name("teleporter");
		this.hardness(Tile.PISTON.getHardness());
		this.luminance(0.8F); // lightness
	}

	public int getTextureForSide(int side) {
		if (side == 1) { // top
			return Tile.PISTON_HEAD.getTextureForSide(0);
		} else {
			return Tile.DOUBLE_STONE_SLAB.getTextureForSide(0);
		}
	}
}
