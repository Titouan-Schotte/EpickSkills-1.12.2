package fr.epickskills.skills.network;

import fr.epickskills.skills.network.json.PaquetGetSkillsPropertiesServer;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.proxy.ClientProxy.*;

public class PaquetSkillUpdateClient implements IMessage {

    private int skill1, skill2,skill3, ulti,skillsPoints;
    private String classe;

    public PaquetSkillUpdateClient() {
    }

    public PaquetSkillUpdateClient(int skill1, int skill2,int skill3, int ulti,int skillsPoints, String classe) {
        this.skill1 = skill1;
        this.skill2 = skill2;
        this.skill3 = skill3;
        this.ulti = ulti;
        this.skillsPoints = skillsPoints;
        this.classe = classe;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        skill1 = buf.readInt();
        skill2 = buf.readInt();
        skill3 = buf.readInt();
        ulti = buf.readInt();
        skillsPoints = buf.readInt();
        classe = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(skill1);
        buf.writeInt(skill2);
        buf.writeInt(skill3);
        buf.writeInt(ulti);
        buf.writeInt(skillsPoints);
        ByteBufUtils.writeUTF8String(buf, classe);
    }

    public static class Handler implements IMessageHandler<PaquetSkillUpdateClient, IMessage> {
        @Override
        public IMessage onMessage(PaquetSkillUpdateClient message, MessageContext ctx) {


            playerSkill1 = message.skill1;
            playerSkill2 = message.skill2;
            playerSkill3 = message.skill3;
            playerUlti = message.ulti;
            skillsPointsForGui = message.skillsPoints;
            network.sendToServer(new PaquetGetSkillsPropertiesServer(message.classe, 1, message.skill1));
            network.sendToServer(new PaquetGetSkillsPropertiesServer(message.classe, 2, message.skill2));
            network.sendToServer(new PaquetGetSkillsPropertiesServer(message.classe, 3, message.skill3));
            network.sendToServer(new PaquetGetSkillsPropertiesServer(message.classe, 4, message.ulti));
            playerClasse = message.classe;
            return null;
        }
    }
}
