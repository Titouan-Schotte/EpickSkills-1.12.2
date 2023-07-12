package fr.epickskills.skills.network;

import fr.epickskills.skills.json.JsonManagement;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetSkillOnJoinServer implements IMessage {


    public PaquetSkillOnJoinServer() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PaquetSkillOnJoinServer, IMessage> {
        @Override
        public IMessage onMessage(PaquetSkillOnJoinServer message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                JsonManagement.SearchUserAccount(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID());
            });
            return null;
        }
    }
}