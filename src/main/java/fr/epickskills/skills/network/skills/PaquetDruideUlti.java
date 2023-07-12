package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.init.MobEffects.*;

public class PaquetDruideUlti implements IMessage {


    private int activation, jumpRate, speedRate;


    public PaquetDruideUlti() {
    }

    public PaquetDruideUlti(int activation, int jumpRate, int speedRate) {
        this.activation = activation;
        this.jumpRate = jumpRate;
        this.speedRate = speedRate;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        jumpRate = buf.readInt();
        speedRate = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        buf.writeInt(jumpRate);
        buf.writeInt(speedRate);
    }

    public static class Handler implements IMessageHandler<PaquetDruideUlti, IMessage> {
        @Override
        public IMessage onMessage(PaquetDruideUlti message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(JUMP_BOOST, 20 * message.activation, message.jumpRate, false, false));
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(STRENGTH, 20 * message.activation, message.speedRate, false, false));
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(ABSORPTION, 20 * message.activation, message.speedRate, false, false));
            });

            return null;
        }
    }
}