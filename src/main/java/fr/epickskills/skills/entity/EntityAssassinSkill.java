package fr.epickskills.skills.entity;

import fr.epickskills.skills.network.skills.PaquetDamageEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.skills.classes.Assassin.Ulti.isUltiAssassin;

public class EntityAssassinSkill extends EntityThrowable {
    private double damages;
    private int ticks = 0;
    private EntityLivingBase thrower;
    public EntityAssassinSkill(World worldIn) {
        super(worldIn);
    }

    public EntityAssassinSkill(World worldIn, EntityLivingBase throwerIn, double damages) {
        super(worldIn, throwerIn);
        this.damages = damages;
        this.thrower = throwerIn;
    }

    public EntityAssassinSkill(World worldIn, double x, double y, double z, double damages) {
        super(worldIn, x, y, z);
        this.damages = damages;
    }

    @Override
    public void onUpdate() {
        if (!isUltiAssassin) {
            this.world.spawnParticle(EnumParticleTypes.REDSTONE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        } else {
            this.world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        }
        ticks++;
        super.onUpdate();
    }

    @Override
    protected void onImpact(RayTraceResult result) {
            if(this.world.isRemote){
                if (result.entityHit != null && !result.entityHit.equals(thrower)) {
                    if(isUltiAssassin){
                        network.sendToServer(new PaquetDamageEntity(result.entityHit.getEntityId(), (int) this.damages,false,0,true,3,true));
                    } else {
                        network.sendToServer(new PaquetDamageEntity(result.entityHit.getEntityId(), (int) this.damages,false,0,false,0));
                    }
                    this.world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
                }
            }
            if (!this.world.isRemote) {
                this.world.setEntityState(this, (byte) 3);
                this.setDead();
            }

    }
}
