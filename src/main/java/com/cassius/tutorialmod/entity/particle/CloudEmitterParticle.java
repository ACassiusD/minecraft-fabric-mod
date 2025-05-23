package com.cassius.tutorialmod.entity.particle;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.NoRenderParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;

@Environment(EnvType.CLIENT)
public class CloudEmitterParticle extends NoRenderParticle {
    private final double deviation;
    private final int interval;

    CloudEmitterParticle(ClientWorld world, double x, double y, double z, double deviation, int maxAge, int interval) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.deviation = deviation;
        this.maxAge = maxAge;
        this.interval = interval;
    }

    @Override
    public void tick() {
        if (this.age % (this.interval + 1) == 0) {
            for (int i = 0; i < 3; i++) {
                double d = this.x + (this.random.nextDouble() - this.random.nextDouble()) * this.deviation;
                double e = this.y + (this.random.nextDouble() - this.random.nextDouble()) * this.deviation;
                double f = this.z + (this.random.nextDouble() - this.random.nextDouble()) * this.deviation;
                this.world.addParticleClient(ParticleTypes.GUST, d, e, f, (float)this.age / this.maxAge, 0.0, 0.0);
            }
        }

        if (this.age++ == this.maxAge) {
            this.markDead();
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<SimpleParticleType> {
        private final double deviation;
        private final int maxAge;
        private final int interval;

        public Factory(double deviation, int maxAge, int interval) {
            this.deviation = deviation;
            this.maxAge = maxAge;
            this.interval = interval;
        }

        public Particle createParticle(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            return new CloudEmitterParticle(clientWorld, d, e, f, this.deviation, this.maxAge, this.interval);
        }
    }
}
