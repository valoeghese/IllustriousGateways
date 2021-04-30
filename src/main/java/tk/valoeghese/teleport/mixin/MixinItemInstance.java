package tk.valoeghese.teleport.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.item.ItemInstance;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.TilePos;
import tk.valoeghese.teleport.item.SeekingItem;

// TODO remove this, use Attached Data
@Mixin(ItemInstance.class)
public abstract class MixinItemInstance implements SeekingItem {
	private int teleport_x = 0;
	private int teleport_y = 0;
	private int teleport_z = 0;
	private boolean teleport_hasPosition = false;

	@Inject(at = @At("RETURN"), method = "copy")
	private void teleport_copyPosition(CallbackInfoReturnable<ItemInstance> info) {
		if (this.teleport_hasPosition) {
			((SeekingItem) (Object) info.getReturnValue()).setSeekingPos(this.teleport_x, this.teleport_y, this.teleport_z);
		}
	}

	@Inject(at = @At("RETURN"), method = "toTag")
	private void teleport_addPosition(CompoundTag tag, CallbackInfoReturnable<CompoundTag> info) {
		CompoundTag data = new CompoundTag();
		data.put("hasPosition", this.teleport_hasPosition);

		if (this.teleport_hasPosition) {
			data.put("x", this.teleport_x);
			data.put("y", this.teleport_y);
			data.put("z", this.teleport_z);
		}

		tag.put("illustrious_gateways", data);
	}

	@Inject(at = @At("RETURN"), method = "fromTag")
	private void teleport_readPosition(CompoundTag tag, CallbackInfo info) {
		if (tag.containsKey("illustrious_gateways")) {
			CompoundTag data = tag.getCompoundTag("illustrious_gateways");
			this.teleport_hasPosition = data.getBoolean("hasPosition");

			if (this.teleport_hasPosition) {
				this.teleport_x = data.getInt("x");
				this.teleport_y = data.getInt("y");
				this.teleport_z = data.getInt("z");
			}
		}
	}

	@Override
	public TilePos getSeekingPos() {
		return new TilePos(this.teleport_x, this.teleport_y, this.teleport_z);
	}

	@Override
	public void setSeekingPos(int x, int y, int z) {
		this.teleport_hasPosition = true;
		this.teleport_x = x;
		this.teleport_y = y;
		this.teleport_z = z;
	}

	@Override
	public boolean hasPosition() {
		return this.teleport_hasPosition;
	}
}
