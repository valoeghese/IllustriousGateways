package tk.valoeghese.teleport.item;

import net.minecraft.item.ItemType;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.texture.UVPair;

public class GeneratedItemModel extends Model {
	public GeneratedItemModel(ItemType type, String texture) {
		this.addTexture("texture", texture);
	}

	private ItemType type;

	@Override
	public void renderBlock() {
		// no
	}

	@Override
	public void renderItem() {
		Shape.setUV(particleUV());
		// this is where I use ShapeItem and spam tesselator calls
		// if you are reading this, just use the old deprecated api for now until I make it somewhat sane in Cursed Legacy API 1.1.0
	}

	@Override
	public boolean hasItem() {
		return true;
	}

	@Override
	public UVPair particleUV() {
		return this.getUV("texture");
	}
}
