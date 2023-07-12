package fr.epickskills.skills.network.json;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.json.SkillJson.getSkillProp;

public class PaquetGetSkillsPropertiesServer implements IMessage {
    private String classe;
    private int skill, level;


    public PaquetGetSkillsPropertiesServer() {
    }

    public PaquetGetSkillsPropertiesServer(String classe, int skill, int level) {
        this.classe = classe;
        this.skill = skill;
        this.level = level;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        classe = ByteBufUtils.readUTF8String(buf);
        skill = buf.readInt();
        level = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, classe);
        buf.writeInt(skill);
        buf.writeInt(level);
    }

    public static class Handler implements IMessageHandler<PaquetGetSkillsPropertiesServer, IMessage> {
        @Override
        public IMessage onMessage(PaquetGetSkillsPropertiesServer message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                double[] props = null;
                switch (message.classe) {
                    case "assassin":
                        switch (message.skill) {
                            case 1:
                                props = new double[]{getSkillProp("ASSASSIN", "SKILL1", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ASSASSIN", "SKILL1", "LEVEL" + message.level, "DAMAGES")};
                                break;
                            case 2:
                                props = new double[]{getSkillProp("ASSASSIN", "SKILL2", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ASSASSIN", "SKILL2", "LEVEL" + message.level, "BUMP")};
                                break;
                            case 3:
                                props = new double[]{
                                        getSkillProp("ASSASSIN", "SKILL3", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ASSASSIN", "SKILL3", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("ASSASSIN", "SKILL3", "LEVEL" + message.level, "RADIUS")
                                };
                                break;
                            case 4:
                                props = new double[]{getSkillProp("ASSASSIN", "ULTI", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ASSASSIN", "ULTI", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("ASSASSIN", "ULTI", "LEVEL" + message.level, "DAMAGES"),
                                        getSkillProp("ASSASSIN", "ULTI", "LEVEL" + message.level, "RAYON"),
                                        getSkillProp("ASSASSIN", "ULTI", "LEVEL" + message.level, "RANGE")

                                };
                                break;
                        }
                        break;
                    case "arcaniste":
                        switch (message.skill) {
                            case 1:
                                props = new double[]{
                                        getSkillProp("ARCANISTE", "SKILL1", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ARCANISTE", "SKILL1", "LEVEL" + message.level, "DAMAGES"),
                                        getSkillProp("ARCANISTE", "SKILL1", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("ARCANISTE", "SKILL1", "LEVEL" + message.level, "RADIUS-ACTIVATION"),
                                        getSkillProp("ARCANISTE", "SKILL1", "LEVEL" + message.level, "RADIUS-TORNADO")
                                };
                                break;
                            case 2:
                                props = new double[]{
                                        getSkillProp("ARCANISTE", "SKILL2", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ARCANISTE", "SKILL2", "LEVEL" + message.level, "DAMAGES"),
                                        getSkillProp("ARCANISTE", "SKILL2", "LEVEL" + message.level, "RADIUS"),
                                        getSkillProp("ARCANISTE", "SKILL2", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("ARCANISTE", "SKILL2", "LEVEL" + message.level, "RADIUS-ACTIVATION")
                                };
                                break;
                            case 3:
                                props = new double[]{
                                        getSkillProp("ARCANISTE", "SKILL3", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ARCANISTE", "SKILL3", "LEVEL" + message.level, "DAMAGES"),
                                        getSkillProp("ARCANISTE", "SKILL3", "LEVEL" + message.level, "MOTIONY"),
                                        getSkillProp("ARCANISTE", "SKILL3", "LEVEL" + message.level, "FIRE"),
                                        getSkillProp("ARCANISTE", "SKILL3", "LEVEL" + message.level, "RADIUS")
                                };
                                break;
                            case 4:
                                props = new double[]{
                                        getSkillProp("ARCANISTE", "ULTI", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("ARCANISTE", "ULTI", "LEVEL" + message.level, "RADIUS"),
                                        getSkillProp("ARCANISTE", "ULTI", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("ARCANISTE", "ULTI", "LEVEL" + message.level, "DAMAGES-TICKS"),
                                        getSkillProp("ARCANISTE", "ULTI", "LEVEL" + message.level, "DAMAGES-POINTED")
                                };
                                break;
                        }
                        break;
                    case "guerrier":
                        switch (message.skill) {
                            case 1:
                                props = new double[]{
                                        getSkillProp("GUERRIER", "SKILL1", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("GUERRIER", "SKILL1", "LEVEL" + message.level, "DAMAGES"),
                                        getSkillProp("GUERRIER", "SKILL1", "LEVEL" + message.level, "RADIUS"),
                                        getSkillProp("GUERRIER", "SKILL1", "LEVEL" + message.level, "JUMP")
                                };
                                break;
                            case 2:
                                props = new double[]{
                                        getSkillProp("GUERRIER", "SKILL2", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("GUERRIER", "SKILL2", "LEVEL" + message.level, "ACTIVATION")
                                };
                                break;
                            case 3:
                                props = new double[]{
                                        getSkillProp("GUERRIER", "SKILL3", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("GUERRIER", "SKILL3", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("GUERRIER", "SKILL3", "LEVEL" + message.level, "LIFELEFT"),
                                        getSkillProp("GUERRIER", "SKILL3", "LEVEL" + message.level, "RADIUS"),
                                        getSkillProp("GUERRIER", "SKILL3", "LEVEL" + message.level, "RESISTANCE-PERSO"),
                                        getSkillProp("GUERRIER", "SKILL3", "LEVEL" + message.level, "RESISTANCE-MULTI")
                                };
                                break;
                            case 4:
                                props = new double[]{
                                        getSkillProp("GUERRIER", "ULTI", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("GUERRIER", "ULTI", "LEVEL" + message.level, "DAMAGES"),
                                        getSkillProp("GUERRIER", "ULTI", "LEVEL" + message.level, "RADIUS"),
                                        getSkillProp("GUERRIER", "ULTI", "LEVEL" + message.level, "SPEED"),
                                        getSkillProp("GUERRIER", "ULTI", "LEVEL" + message.level, "TURNS"),
                                        getSkillProp("GUERRIER", "ULTI", "LEVEL" + message.level, "DISTANCE")
                                };
                                break;
                        }
                        break;
                    case "druide":
                        switch (message.skill) {
                            case 1:
                                props = new double[]{
                                        getSkillProp("DRUIDE", "SKILL1", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("DRUIDE", "SKILL1", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("DRUIDE", "SKILL1", "LEVEL" + message.level, "RADIUS")
                                };
                                break;
                            case 2:
                                props = new double[]{
                                        getSkillProp("DRUIDE", "SKILL2", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("DRUIDE", "SKILL2", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("DRUIDE", "SKILL2", "LEVEL" + message.level, "RADIUS"),
                                        getSkillProp("DRUIDE", "SKILL2", "LEVEL" + message.level, "DAMAGES")
                                };
                                break;
                            case 3:
                                props = new double[]{
                                        getSkillProp("DRUIDE", "SKILL3", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("DRUIDE", "SKILL3", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("DRUIDE", "SKILL3", "LEVEL" + message.level, "SPEEDRATE"),
                                        getSkillProp("DRUIDE", "SKILL3", "LEVEL" + message.level, "RANGE")
                                };
                                break;
                            case 4:
                                props = new double[]{
                                        getSkillProp("DRUIDE", "ULTI", "LEVEL" + message.level, "COOLDOWN"),
                                        getSkillProp("DRUIDE", "ULTI", "LEVEL" + message.level, "DAMAGES"),
                                        getSkillProp("DRUIDE", "ULTI", "LEVEL" + message.level, "ACTIVATION"),
                                        getSkillProp("DRUIDE", "ULTI", "LEVEL" + message.level, "RADIUS"),
                                        getSkillProp("DRUIDE", "ULTI", "LEVEL" + message.level, "SPEEDRATE"),
                                        getSkillProp("DRUIDE", "ULTI", "LEVEL" + message.level, "JUMPRATE")
                                };
                                break;
                        }
                        break;
                    case "necromancien":
                        switch (message.skill) {
                            case 1:
                                props = new double[]{

                                };
                                break;
                            case 2:
                                props = new double[]{

                                };
                                break;
                            case 3:
                                props = new double[]{

                                };
                                break;
                            case 4:
                                props = new double[]{

                                };
                                break;
                        }
                        break;
                    default:
                        return;
                }
                network.sendTo(new PaquetGetSkillsPropertiesClient(message.classe, message.skill, message.level, props), ctx.getServerHandler().player);
                return;
            });

            return null;
        }
    }
}
