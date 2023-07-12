package fr.epickskills.skills.particles.particle;

import fr.epickskills.skills.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ParticleAracSkill3Level2_2 extends ParticlePortal {
    protected ParticleAracSkill3Level2_2(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.particleRed = 0.153F;
        this.particleGreen = 1;
        this.particleBlue = 0;
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        @Override
        public Particle createParticle(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... p_178902_15_) {
            return new ParticleAracSkill3Level2_2(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}