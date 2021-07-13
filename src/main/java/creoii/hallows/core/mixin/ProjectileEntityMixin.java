package creoii.hallows.core.mixin;

import creoii.hallows.core.registry.EntityRegistry;
import creoii.hallows.core.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityMixin {
    @Inject(method = "onCollision", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, target = "Lnet/minecraft/entity/projectile/ProjectileEntity;onEntityHit(Lnet/minecraft/util/hit/EntityHitResult)V", remap = false), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private static void negateGhostCollision(HitResult hitResult, CallbackInfo ci) {
        Entity entity = ((EntityHitResult) hitResult).getEntity();
        if (entity.getType() == EntityRegistry.GHOST) ci.cancel();
        else {
            for (ItemStack stack : entity.getArmorItems()) {
                if (stack.getItem() == ItemRegistry.GHOSTLY_DRAPE) ci.cancel();
            }
        }
    }
}