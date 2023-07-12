package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetAssassinUltiEnd implements IMessage {


    public PaquetAssassinUltiEnd() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PaquetAssassinUltiEnd, IMessage> {
        @Override
        public IMessage onMessage(PaquetAssassinUltiEnd message, MessageContext ctx) {

            return null;
        }
    }
}