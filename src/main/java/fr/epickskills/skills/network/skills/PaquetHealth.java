package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetHealth implements IMessage {




    public PaquetHealth() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PaquetHealth, IMessage> {
        @Override
        public IMessage onMessage(PaquetHealth message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                playerMP.setHealth((float) (playerMP.getHealth()+playerMP.getMaxHealth()*0.1));
            });
            return null;
        }
    }
}