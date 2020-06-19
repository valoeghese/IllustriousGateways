package tk.valoeghese.teleport;

import net.minecraft.util.maths.TilePos;

public interface SeekingItem {
	TilePos getSeekingPos();
	void setSeekingPos(int x, int y, int z);
	boolean hasPosition();
}
