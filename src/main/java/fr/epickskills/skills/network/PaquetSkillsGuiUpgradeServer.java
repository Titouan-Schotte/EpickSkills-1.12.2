package fr.epickskills.skills.network;

import fr.epickskills.skills.json.JsonManagement;
import fr.epickskills.skills.network.json.PaquetGetSkillsPropertiesServer;
import io.netty.buffer.ByteBuf;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.proxy.ClientProxy.*;

public class PaquetSkillsGuiUpgradeServer implements IMessage {

    private int skill;

    public PaquetSkillsGuiUpgradeServer() {
    }

    public PaquetSkillsGuiUpgradeServer(int skill) {
        this.skill=skill;

    }


    @Override
    public void fromBytes(ByteBuf buf) {
        skill = buf.readInt();

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(skill);

    }

    public static class Handler implements IMessageHandler<PaquetSkillsGuiUpgradeServer, IMessage> {
        @Override
        public IMessage onMessage(PaquetSkillsGuiUpgradeServer message, MessageContext ctx) {

            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                JsonManagement.removeSkillPT(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID(), 1);
                switch (message.skill){
                    case 1://SKILL1
                        JsonManagement.upgradeSkill1(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID(), 1);
                        break;
                    case 2://SKILL2
                        JsonManagement.upgradeSkill2(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID(), 1);
                        break;
                    case 3://SKILL3
                        JsonManagement.upgradeSkill3(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID(), 1);
                        break;
                    case 4://ULTI
                        JsonManagement.upgradeUlti(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID(), 1);
                        break;
                }
                network.sendTo(new PaquetSkillUpdateClient(JsonManagement.getSkill1(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getSkill2(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getSkill3(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getUlti(ctx.getServerHandler().player.getUniqueID()),JsonManagement.getSkillPT(ctx.getServerHandler().player.getUniqueID()), JsonManagement.getClasse(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID())), ctx.getServerHandler().player);
                ctx.getServerHandler().player.sendMessage(new TextComponentString("Amélioration avec succès !"));
            });



            return null;
        }
    }
}
