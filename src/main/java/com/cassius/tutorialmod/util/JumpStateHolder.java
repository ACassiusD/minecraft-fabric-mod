package com.cassius.tutorialmod.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Holds the jumping value we are getting from ServerPlayNetworkHandlerMixin
 */
public class JumpStateHolder {
    private static final Map<UUID, Boolean> JUMPING = new ConcurrentHashMap<>();
    private static final Map<UUID, Boolean> SNEAKING = new ConcurrentHashMap<>();

    private static final Map<UUID, Boolean> SPRINTING = new ConcurrentHashMap<>();

    public static void setJumping(UUID uuid, boolean jumping) {
        JUMPING.put(uuid, jumping);
    }
    public static boolean isJumping(UUID uuid) {
        return JUMPING.getOrDefault(uuid, false);
    }

    public static void setSneaking(UUID uuid, boolean sneaking) {
        SNEAKING.put(uuid, sneaking);
    }
    public static boolean isSneaking(UUID uuid) {
        return SNEAKING.getOrDefault(uuid, false);
    }

    public static void setSprinting(UUID uuid, boolean sprinting) {
        SPRINTING.put(uuid, sprinting);
    }
    public static boolean isSprinting(UUID uuid) {
        return SPRINTING.getOrDefault(uuid, false);
    }
}