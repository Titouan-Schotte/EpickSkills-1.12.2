package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetGuerrierUlti implements IMessage {


    private int activation;
    private float motionValue;
    private int entityId;


    public PaquetGuerrierUlti() {
    }

    public PaquetGuerrierUlti(int activation, float motionValue, int entityId) {
        this.activation = activation;
        this.motionValue = motionValue;
        this.entityId = entityId;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        motionValue = buf.readFloat();
        entityId = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        buf.writeFloat(motionValue);
        buf.writeFloat(entityId);
    }

    public static class Handler implements IMessageHandler<PaquetGuerrierUlti, IMessage> {
        @Override
        public IMessage onMessage(PaquetGuerrierUlti message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                Entity entity = ctx.getServerHandler().player.world.getEntityByID(message.entityId);
                entity.motionY += message.motionValue;
            });
//            ctx.getClientHandler().worl
//            ctx.getServerHandler().player.addPotionEffect(new PotionEffect(STRENGTH, 20*message.activation, message.strenghtRate));
//            ctx.getServerHandler().player.addPotionEffect(new PotionEffect(ABSORPTION, 20*message.activation, message.absorptionRate));
            return null;
        }
    }
}