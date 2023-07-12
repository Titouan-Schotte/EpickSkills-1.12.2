package fr.epickskills.skills.particles.particle;

import fr.epickskills.skills.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

public class ParticleAracSkill3Level3 extends ParticleFlame {

    public ParticleAracSkill3Level3(World world, double xPos, double yPos, double zPos, double p_i1209_8_, double p_i1209_10_, double p_i1209_12_) {
        super(world, xPos, yPos, zPos, p_i1209_8_, p_i1209_10_, p_i1209_12_);
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(References.MODID + ":textures/particles/particles_2.png"));
        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
    }

    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory {
        @Nullable
        @Override
        public Particle createParticle(int particleID, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... p_178902_15_) {
            return new ParticleAracSkill3Level3(world, x, y, z, xSpeed, ySpeed, zSpeed);
        }
    }
}