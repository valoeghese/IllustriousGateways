package tk.valoeghese.teleport;

import net.minecraft.tile.Tile;
import net.minecraft.tile.material.Material;

public class Teleporter extends Tile {
	Teleporter(int id) {
		super(id, Material.METAL);
		this.sounds(Tile.GLASS_SOUNDS);
		this.setName("teleporter");
		this.setHardness(Tile.PISTON.getHardness());
		this.method_1577(0.8F); // lightness

		// this is probably cutout render layer so no
		// this.method_1591();
	}

	public int getTextureForSide(int side) {
		if (side == 1) { // top
			return Tile.PISTON_HEAD.getTextureForSide(0);
		} else {
			return Tile.DOUBLE_STONE_SLAB.getTextureForSide(0);
		}
	}
}
