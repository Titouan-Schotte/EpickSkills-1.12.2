package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetResetPotion implements IMessage {


    public PaquetResetPotion() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PaquetResetPotion, IMessage> {
        @Override
        public IMessage onMessage(PaquetResetPotion message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                ctx.getServerHandler().player.clearActivePotions();
            });
            return null;
        }
    }
}
