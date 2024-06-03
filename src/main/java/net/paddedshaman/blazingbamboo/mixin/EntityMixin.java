package net.paddedshaman.blazingbamboo.mixin;

import net.minecraft.world.entity.Entity;
import net.paddedshaman.blazingbamboo.entity.BBRaftUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "lavaHurt()V", at = @At("HEAD"), cancellable = true)
    public void onLavaHurt(CallbackInfo callbackInfo) {
        if(BBRaftUtil.isImmune(getSelf())) {
            callbackInfo.cancel();
        }
    }

    @Inject(method = "isOnFire()Z", at = @At("HEAD"), cancellable = true)
    public void onFire(CallbackInfoReturnable<Boolean> callbackInfo) {
        if(BBRaftUtil.isImmune(getSelf())) {
            callbackInfo.setReturnValue(false);
        }
    }

    private Entity getSelf() {
        return (Entity) (Object) this;
    }
}
