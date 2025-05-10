package com.cassius.tutorialmod.util;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Holds the jumping value we are getting from ServerPlayNetworkHandlerMixin
 */
public class JumpStateHolder {
    private static final Map<UUID, Boolean> JUMPING = new ConcurrentHashMap<>();
    public static void setJumping(UUID uuid, boolean jumping) {
        JUMPING.put(uuid, jumping);
    }
    public static boolean isJumping(UUID uuid) {
        return JUMPING.getOrDefault(uuid, false);
    }
}