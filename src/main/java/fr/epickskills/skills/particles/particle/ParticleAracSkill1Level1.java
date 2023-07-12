package fr.epickskills.skills.particles.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticlePortal;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ParticleAracSkill1Level1 extends ParticlePortal {
    protected ParticleAracSkill1Level1(World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
        super(world, x, y, z, xSpeed, ySpeed, zSpeed);
        this.particleRed = 0.7F;
        this.particleGreen = 0.4F;
        this.particleBlue = 1;
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        @Override
        public Particle createParticle(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... p_178902_15_) {
            return new ParticleAracSkill1Level1(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}