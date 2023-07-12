package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.init.MobEffects.NIGHT_VISION;
import static net.minecraft.init.MobEffects.SPEED;

public class PaquetAssassinUlti implements IMessage {


    private int activation;

    public PaquetAssassinUlti() {
    }

    public PaquetAssassinUlti(int activation) {
        this.activation = activation;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
    }

    public static class Handler implements IMessageHandler<PaquetAssassinUlti, IMessage> {
        @Override
        public IMessage onMessage(PaquetAssassinUlti message, MessageContext ctx) {
            ctx.getServerHandler().player.addPotionEffect(new PotionEffect(SPEED, 20 * message.activation, 1));
            ctx.getServerHandler().player.addPotionEffect(new PotionEffect(NIGHT_VISION, 20 * message.activation, 6));
            return null;
        }
    }
}