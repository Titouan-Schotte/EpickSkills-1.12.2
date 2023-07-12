package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Iterator;
import java.util.List;

import static fr.epickskills.skills.Main.network;

public class PaquetDruideSkill2 implements IMessage {


    private double coordX, coordY, coordZ;
    private int radius;


    public PaquetDruideSkill2() {
    }

    public PaquetDruideSkill2(double[] coord, int radius) {
        this.coordX = coord[0];
        this.coordY = coord[1];
        this.coordZ = coord[2];
        this.radius = radius;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        this.coordX = buf.readDouble();
        this.coordY = buf.readDouble();
        this.coordZ = buf.readDouble();
        this.radius = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeDouble(this.coordX);
        buf.writeDouble(this.coordY);
        buf.writeDouble(this.coordZ);
        buf.writeInt(this.radius);
    }

    public static class Handler implements IMessageHandler<PaquetDruideSkill2, IMessage> {
        @Override
        public IMessage onMessage(PaquetDruideSkill2 message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                World world = ctx.getServerHandler().player.world;
                List<EntityLivingBase> entities = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(message.coordX - (message.radius), message.coordY - (message.radius), message.coordZ - (message.radius), message.coordX + (message.radius), message.coordY + (message.radius), message.coordZ + (message.radius)));
                for(Iterator<EntityLivingBase> it = entities.iterator(); it.hasNext();){
                    EntityLivingBase entityLivingBase = it.next();
                    if(!(entityLivingBase.getEntityId() == ctx.getServerHandler().player.getEntityId()) && !(entityLivingBase instanceof EntityPlayer)){
                        DamageSource damageSource = DamageSource.causePlayerDamage(ctx.getServerHandler().player);
//                                entityLivingBase.setHealth(entityLivingBase.getHealth()-2);
                        entityLivingBase.setInWeb();
                        entityLivingBase.attackEntityFrom(damageSource, 1);
                        entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 10, 6, false, true));
                    }

                }
            });
            return null;
        }
    }
}