package fr.epickskills.skills.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityAssassinItem extends EntityThrowable {
    private EnumParticleTypes particleTrail;
    private EnumParticleTypes particleCollision;
    private int damages;

    public EntityAssassinItem(World worldIn) {
        super(worldIn);
    }

    public EntityAssassinItem(World worldIn, EntityLivingBase throwerIn, EnumParticleTypes particleTrail, EnumParticleTypes particleCollision, int damages) {
        super(worldIn, throwerIn);
        this.particleTrail = particleTrail;
        this.particleCollision = particleCollision;
        this.damages = damages;
    }

    public EntityAssassinItem(World worldIn, double x, double y, double z) {
        super(worldIn, x, y, z);
    }

    @Override
    public void onUpdate() {
        this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);

        super.onUpdate();
    }

    @SideOnly(Side.CLIENT)
    public void handleStatusUpdate(byte id) {
        if (id == 3) {
            for (int i = 0; i < 8; ++i) {
                this.world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void onImpact(RayTraceResult result) {
        if (!this.world.isRemote) {
            setDead();
            if (result.entityHit instanceof EntityLivingBase && !(result.entityHit instanceof EntityPlayer)) {
                EntityLivingBase entityLivingBase = (EntityLivingBase) result.entityHit;
                entityLivingBase.attackEntityFrom(DamageSource.causeThrownDamage(this, this.thrower), 20);
            }
        }
    }
}
