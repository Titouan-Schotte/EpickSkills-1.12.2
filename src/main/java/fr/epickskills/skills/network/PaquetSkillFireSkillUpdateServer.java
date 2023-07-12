package fr.epickskills.skills.network;

import fr.epickskills.skills.json.JsonManagement;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static fr.epickskills.skills.Main.network;

public class PaquetSkillFireSkillUpdateServer implements IMessage {


    private int skill;

    public PaquetSkillFireSkillUpdateServer() {
    }

    public PaquetSkillFireSkillUpdateServer(int skill) {
        this.skill = skill;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        skill = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(skill);
    }

    public static class Handler implements IMessageHandler<PaquetSkillFireSkillUpdateServer, PaquetSkillFireSkillUpdateClient> {
        @Override
        public PaquetSkillFireSkillUpdateClient onMessage(PaquetSkillFireSkillUpdateServer message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                int level = 0;
                switch (message.skill) {
                    case 1: // SKILL1
                        level = JsonManagement.getSkill1(ctx.getServerHandler().player.getUniqueID());
                        break;
                    case 2: // SKILL2
                        level = JsonManagement.getSkill2(ctx.getServerHandler().player.getUniqueID());
                        break;
                    case 3: // SKILL 3
                        level = JsonManagement.getSkill3(ctx.getServerHandler().player.getUniqueID());
                        break;
                    case 4: // ULTI
                        level = JsonManagement.getUlti(ctx.getServerHandler().player.getUniqueID());
                        break;
                    default:
                        break;
                }
                String classe = JsonManagement.getClasse(ctx.getServerHandler().player.getName(), ctx.getServerHandler().player.getUniqueID());
                network.sendTo(new PaquetSkillFireSkillUpdateClient(message.skill, level, classe), ctx.getServerHandler().player);

            });

            return null;
        }
    }
}