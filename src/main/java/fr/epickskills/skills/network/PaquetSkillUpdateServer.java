package fr.epickskills.skills.network;

import fr.epickskills.skills.json.JsonManagement;
import fr.epickskills.skills.network.json.PaquetGetSkillsPropertiesClient;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static fr.epickskills.skills.Main.network;

public class PaquetSkillUpdateServer implements IMessage {


    public PaquetSkillUpdateServer() {
    }


    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PaquetSkillUpdateServer, PaquetSkillUpdateClient> {
        @Override
        public PaquetSkillUpdateClient onMessage(PaquetSkillUpdateServer message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                network.sendTo(new PaquetSkillUpdateClient(JsonManagement.getSkill1(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getSkill2(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getSkill3(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getUlti(ctx.getServerHandler().player.getUniqueID()),JsonManagement.getSkillPT(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getClasse(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID())), ctx.getServerHandler().player);
            });
            return null;
        }
    }
}