package fr.epickskills.skills.network.json;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static fr.epickskills.skills.json.SkillVariables.*;

public class PaquetGetSkillsPropertiesClient implements IMessage {
    private String classe;
    private int skill, level;
    private double[] props;


    public PaquetGetSkillsPropertiesClient() {
    }

    public PaquetGetSkillsPropertiesClient(String classe, int skill, int level, double[] props) {
        this.classe = classe;
        this.skill = skill;
        this.level = level;
        this.props = props;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        classe = ByteBufUtils.readUTF8String(buf);
        skill = buf.readInt();
        level = buf.readInt();
        this.props = new double[buf.readInt()];
        for (int i = 0; i < props.length; i++) {
            props[i] = buf.readDouble();
        }
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, classe);
        buf.writeInt(skill);
        buf.writeInt(level);
        buf.writeInt(this.props.length);
        for (double prop : this.props) {
            buf.writeDouble(prop);
        }
    }

    public static class Handler implements IMessageHandler<PaquetGetSkillsPropertiesClient, IMessage> {
        @Override
        public IMessage onMessage(PaquetGetSkillsPropertiesClient message, MessageContext ctx) {
            switch (message.classe) {
                case "assassin":
                    switch (message.skill) {
                        case 1:
                            propAssSkill1Cooldown = message.props[0];
                            propAssSkill1Damages = message.props[1];
                            break;
                        case 2:
                            propAssSkill2Cooldown = message.props[0];
                            propAssSkill2Bump = message.props[1];
                            break;
                        case 3:
                            propAssSkill3Cooldown=message.props[0];
                            propAssSkill3Activation=message.props[1];
                            propAssSkill3Radius=message.props[2];
                            break;
                        case 4:
                            propAssUltiCooldown = message.props[0];
                            propAssUltiActivation = message.props[1];
                            propAssUltiDamages = message.props[2];
                            propAssUltiRayon = message.props[3];
                            propAssUltiRange = message.props[4];
                            break;
                    }
                    break;
                case "arcaniste":
                    switch (message.skill) {
                        case 1:
                            propArcSkill1Cooldown = message.props[0];
                            propArcSkill1Damages = message.props[1];
                            propArcSkill1Activation = message.props[2];
                            propArcSkill1RadiusActivation = message.props[3];
                            propArcSkill1RadiusTornado = message.props[4];
                            break;
                        case 2:
                            propArcSkill2Cooldown = message.props[0];
                            propArcSkill2Damages = message.props[1];
                            propArcSkill2Radius = message.props[2];
                            propArcSkill2Activation = message.props[3];
                            propArcSkill2RadiusActivation=message.props[4];
                            break;
                        case 3:
                            propArcSkill3Cooldown = message.props[0];
                            propArcSkill3Damages = message.props[1];
                            propArcSkill3MotionY = message.props[2];
                            propArcSkill3Fire = message.props[3];
                            propArcSkill3Radius=message.props[4];
                            break;
                        case 4:
                            propArcUltiCooldown = message.props[0];
                            propArcUltiRadius = message.props[1];
                            propArcUltiActivation = message.props[2];
                            propArcUltiDamagesTicks = message.props[3];
                            propArcUltiDamagesPointed = message.props[4];
                            break;
                    }
                    break;
                case "guerrier":
                    switch (message.skill) {
                        case 1:
                            propGueSkill1Cooldown = message.props[0];
                            propGueSkill1Damages = message.props[1];
                            propGueSkill1Radius = message.props[2];
                            propGueSkill1Jump = message.props[3];
                            break;
                        case 2:
                            propGueSkill2Cooldown = message.props[0];
                            propGueSkill2Activation = message.props[1];
                            break;
                        case 3:
                            propGueSkill3Cooldown = message.props[0];
                            propGueSkill3Activation = message.props[1];
                            propGueSkill3LifeLeft = message.props[2];
                            propGueSkill3Radius = message.props[3];
                            propGueSkill3ResistancePerso = message.props[4];
                            propGueSkill3ResistanceMulti = message.props[5];
                            break;
                        case 4:
                            propGueUltiCooldown = message.props[0];
                            propGueUltiDamages = message.props[1];
                            propGueUltiRadius = message.props[2];
                            propGueUltiSpeed = message.props[3];
                            propGueUltiTurns = message.props[4];
                            propGueUltiDistance = message.props[5];
                            break;
                    }
                    break;
                case "druide":
                    switch (message.skill) {
                        case 1:
                            propDruSkill1Cooldown = message.props[0];
                            propDruSkill1Activation = message.props[1];
                            propDruSkill1Radius = message.props[2];
                            break;
                        case 2:
                            propDruSkill2Cooldown = message.props[0];
                            propDruSkill2Activation = message.props[1];
                            propDruSkill2Radius = message.props[2];
                            propDruSkill2Damages = message.props[3];
                            break;
                        case 3:
                            propDruSkill3Cooldown = message.props[0];
                            propDruSkill3Activation = message.props[1];
                            propDruSkill3SpeedRate = message.props[2];
                            propDruSkill3Range = message.props[3];
                            break;
                        case 4:
                            propDruUltiCooldown = message.props[0];
                            propDruUltiDamages = message.props[1];
                            propDruUltiActivation = message.props[2];
                            propDruUltiRadius = message.props[3];
                            propDruUltiSpeedRate = message.props[4];
                            propDruUltiJumpRate = message.props[5];
                            break;
                    }
                    break;
                case "necromancien":
                    switch (message.skill) {
                        case 1:

                            break;
                        case 2:

                            break;
                        case 3:

                            break;
                        case 4:

                            break;
                    }
                    break;
            }
            return null;
        }
    }
}
