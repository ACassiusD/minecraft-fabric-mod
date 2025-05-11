package com.cassius.tutorialmod.mixin;

import com.cassius.tutorialmod.util.JumpStateHolder;
import net.minecraft.network.packet.c2s.play.PlayerInputC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixins are a powerful and important tool used in the Fabric ecosystem.
 * Their primary use case is modifying existing code in the base game,
 * whether it be through injecting custom logic, removing mechanics, or modifying values
 */
@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin {
    @Shadow public abstract ServerPlayerEntity getPlayer();

    /**
     * Injects at the end of the vanilla method ServerPlayNetworkHandler.onPlayerInput(..),
     * which is called whenever the client sends its current movement/jump/sneak/etc. state.
     *
     * @param packet  the incoming PlayerInputC2SPacket containing the player's input flags
     * @param ci      mixin callback info
     */
    @Inject(method = "onPlayerInput", at = @At("TAIL"))
    private void onPlayerInput(PlayerInputC2SPacket packet, CallbackInfo ci) {
        boolean isJumping  = packet.input().jump();
        boolean isSneaking = packet.input().sneak();
        boolean isSprinting = packet.input().sprint();
//        System.out.println("isSneaking = " + isSneaking + " isSprinting = " + isSprinting + " isJumping = " + isJumping);
        ServerPlayerEntity player = this.getPlayer();
        // Store value in util class, so it can be used elsewhere
        JumpStateHolder.setJumping(player.getUuid(), isJumping);
        JumpStateHolder.setSprinting(player.getUuid(), isSprinting);
        JumpStateHolder.setSneaking(player.getUuid(), isSneaking);
    }
}