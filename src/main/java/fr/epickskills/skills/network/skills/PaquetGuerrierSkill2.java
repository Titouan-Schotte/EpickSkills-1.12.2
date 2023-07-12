package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.init.MobEffects.ABSORPTION;
import static net.minecraft.init.MobEffects.STRENGTH;

public class PaquetGuerrierSkill2 implements IMessage {


    private int activation, strenghtRate, absorptionRate;


    public PaquetGuerrierSkill2() {
    }

    public PaquetGuerrierSkill2(int activation, int strenghtRate, int absorptionRate) {
        this.activation = activation;
        this.strenghtRate = strenghtRate;
        this.absorptionRate = absorptionRate;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        strenghtRate = buf.readInt();
        absorptionRate = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        buf.writeInt(strenghtRate);
        buf.writeInt(absorptionRate);
    }

    public static class Handler implements IMessageHandler<PaquetGuerrierSkill2, IMessage> {
        @Override
        public IMessage onMessage(PaquetGuerrierSkill2 message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(STRENGTH, 20 * message.activation, message.strenghtRate));
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(ABSORPTION, 20 * message.activation, message.absorptionRate));
            });
            return null;
        }
    }
}