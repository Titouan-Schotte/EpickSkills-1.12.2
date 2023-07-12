package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.ICustomNpc;
import noppes.npcs.api.entity.IEntity;
import org.lwjgl.Sys;

import java.util.List;
import java.util.Objects;

import static fr.epickskills.skills.skills.Utilities.spawnParticle;
import static net.minecraft.init.MobEffects.NIGHT_VISION;
import static net.minecraft.init.MobEffects.SPEED;

public class PaquetAssassinSkill3 implements IMessage {


    private int id = 0;
    private int radiusActivation = 0;


    public PaquetAssassinSkill3() {
    }

    public PaquetAssassinSkill3(int id, int radiusActivation) {
        this.id = id;
        this.radiusActivation = radiusActivation;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
        radiusActivation = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(radiusActivation);

    }

    public static class Handler implements IMessageHandler<PaquetAssassinSkill3, IMessage> {
        @Override
        public IMessage onMessage(PaquetAssassinSkill3 message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                EntityPlayerMP entityPlayerMP = ctx.getServerHandler().player;
                if (entityPlayerMP.world.getEntityByID(message.id) != null) {
                    Entity entityMob = entityPlayerMP.world.getEntityByID(message.id);
                    if(NpcAPI.IsAvailable()){
                        IEntity ientity = NpcAPI.Instance().getIEntity(entityMob);
                        if(ientity!=null){
                            if(ientity instanceof ICustomNpc){
                                ICustomNpc iCustomNpc = (ICustomNpc) ientity;
                                iCustomNpc.setAttackTarget(null);
                                iCustomNpc.addPotionEffect(2,1*20,1,false);
                            }
                        }
                    }
//                    if(entityMob.getAttackTarget()!=null){
//                        System.out.println(entityMob.getAttackTarget().getName());
//                        if(entityMob.getAttackTarget().getEntityId()==entityPlayerMP.getEntityId()){
//                            List<EntityPlayer> entitiesPlayer = entityPlayerMP.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(entityPlayerMP.posX - message.radiusActivation, entityPlayerMP.posY - message.radiusActivation, entityPlayerMP.posZ - message.radiusActivation, entityPlayerMP.posX + message.radiusActivation, entityPlayerMP.posY + message.radiusActivation, entityPlayerMP.posZ + message.radiusActivation));
//                            System.out.println(entitiesPlayer.size());
//                            if(entitiesPlayer.size()<=1){
//                                entityMob.setAttackTarget(null);
//                            } else {
//                                EntityPlayer target = entitiesPlayer.get((int) (Math.random() * entitiesPlayer.size()));
//                                if(target != null){
//                                    if(target.getEntityId()!=entityPlayerMP.getEntityId()){
//                                        entityMob.setAttackTarget(target);
//                                    }
//                                }
//                            }
//                        }
//                    }


                }
//                if (ctx.getServerHandler().player.world.getEntityByID(message.id) instanceof EntityMob) {
//                    EntityMob entityMob = (EntityMob) ctx.getServerHandler().player.world.getEntityByID(message.id);
//                    if (entityMob != null) {
//                        if(message.id == 0){
//                            entityMob.setAttackTarget(null);
//                            return;
//                        }
//                        entityMob.setAttackTarget((EntityLivingBase) ctx.getServerHandler().player.world.getEntityByID(message.idPlayer));
//                    }
//                }
            });
            return null;
        }
    }
}