package fr.epickskills.skills.entity;

import fr.epickskills.skills.network.skills.PaquetDamageEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.skills.Utilities.spawnParticle;
import static fr.epickskills.skills.skills.classes.Assassin.Ulti.isUltiAssassin;

public class EntityArcanisteSkill extends EntitySnowball {
    private double damages = 10;
    private double radius = 2;
    private EntityLivingBase thrower;
    private EnumParticleTypes particleTrail = EnumParticleTypes.CLOUD;
    private EnumParticleTypes particleTrail2 = EnumParticleTypes.CLOUD;

    public EntityArcanisteSkill(World worldIn) {
        super(worldIn);
    }

    public EntityArcanisteSkill(World worldIn, EntityLivingBase throwerIn, EnumParticleTypes particleTrail, EnumParticleTypes particleTrail2, double damages, double radius) {
        super(worldIn, throwerIn);
        this.damages = damages;
        this.radius = radius;
        this.particleTrail = particleTrail;
        this.particleTrail2 = particleTrail2;
        this.thrower = throwerIn;
    }

    public EntityArcanisteSkill(World worldIn, double x, double y, double z, EnumParticleTypes particleTrail, EnumParticleTypes particleTrail2, double damages, double radius) {
        super(worldIn, x, y, z);
        this.damages = damages;
        this.radius = radius;
        this.particleTrail = particleTrail;
        this.particleTrail2 = particleTrail2;
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.world.spawnParticle(particleTrail, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        this.world.spawnParticle(particleTrail2, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    }

    @Override
    protected void onImpact(RayTraceResult result) {

            if (result.entityHit != null) {
                if(!thrower.equals(result.entityHit)){
//                    network.sendToServer(new PaquetDamageEntity(result.entityHit.getEntityId(), (int) damages));
                    int limit = 0;
                    try {
                        List<EntityLivingBase> entities = result.entityHit.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(result.entityHit.posX - (radius), result.entityHit.posY - (radius), result.entityHit.posZ - (radius), result.entityHit.posX + (radius), result.entityHit.posY + (radius), result.entityHit.posZ + (radius)));
                        for(Iterator<EntityLivingBase> it = entities.iterator();it.hasNext();){
                            EntityLivingBase entityLivingBase = it.next();
                            if (limit == 5) {
                                break;
                            }
                            if (entityLivingBase != null) {
                                if (!(entityLivingBase instanceof EntityPlayer) && entityLivingBase.isEntityAlive()) {
                                    network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) damages));
                                }
                            }
                            limit++;
                        }
                    } catch (Exception e) {
                    }
                    spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, result.entityHit.posX, result.entityHit.posY + 1.0D, result.entityHit.posZ, 0.0D, 0.0D, 0.0D);
                }
            }

        if (this.world.isRemote) {
            this.setDead();
        }
    }
}





