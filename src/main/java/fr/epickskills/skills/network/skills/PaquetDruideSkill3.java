package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.init.MobEffects.*;

public class PaquetDruideSkill3 implements IMessage {


    private int activation, speedRate;


    public PaquetDruideSkill3() {
    }

    public PaquetDruideSkill3(int activation, int speedRate) {
        this.activation = activation;
        this.speedRate = speedRate;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        speedRate = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        buf.writeInt(speedRate);
    }

    public static class Handler implements IMessageHandler<PaquetDruideSkill3, IMessage> {
        @Override
        public IMessage onMessage(PaquetDruideSkill3 message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(SPEED, 20 * message.activation, message.speedRate, false, false));
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(NIGHT_VISION, 20 * message.activation, 1, false, false));
            });
            return null;
        }
    }
}