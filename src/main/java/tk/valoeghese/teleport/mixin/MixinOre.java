package tk.valoeghese.teleport.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.tile.Ore;
import net.minecraft.tile.Tile;
import net.minecraft.tile.material.Material;
import tk.valoeghese.teleport.Mod;

@Mixin(Ore.class)
public abstract class MixinOre extends Tile {
	protected MixinOre(int id, int tex, Material material) {
		super(id, tex, material);
	}

	@Inject(at = @At("HEAD"), method = "getDropId", cancellable = true)
	private void addLodestones(int meta, Random rand, CallbackInfoReturnable<Integer> info) {
		if (this.id == Tile.IRON_ORE.id) {
			if (rand.nextInt(22) == 0) {
				info.setReturnValue(Mod.lodestone.id);
			}
		}
	}
}
