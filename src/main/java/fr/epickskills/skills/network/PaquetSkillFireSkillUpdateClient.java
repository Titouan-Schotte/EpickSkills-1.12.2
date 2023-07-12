package fr.epickskills.skills.network;

import fr.epickskills.skills.skills.classes.*;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static fr.epickskills.skills.proxy.ClientProxy.*;

public class PaquetSkillFireSkillUpdateClient implements IMessage {


    private int skill, level;
    private String classe;

    public PaquetSkillFireSkillUpdateClient() {
    }

    public PaquetSkillFireSkillUpdateClient(int skill, int level, String classe) {
        this.skill = skill;
        this.level = level;
        this.classe = classe;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        skill = buf.readInt();
        level = buf.readInt();
        classe = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(skill);
        buf.writeInt(level);
        ByteBufUtils.writeUTF8String(buf, classe);
    }

    public static class Handler implements IMessageHandler<PaquetSkillFireSkillUpdateClient, IMessage> {
        @Override
        public IMessage onMessage(PaquetSkillFireSkillUpdateClient message, MessageContext ctx) {
            if (message.level != 0 && message.classe != "default") {
                Minecraft.getMinecraft().player.dismountRidingEntity();
//                network.sendToServer(new PaquetGetSkillsPropertiesServer(message.classe, message.skill, message.level));
                //network.sendToServer(new PaquetSkillUpdateServer());
                switch (message.classe) {
                    case "assassin":
                        if (message.skill == 1) {
                            Assassin.Skill1.fire(message.level);
                        } else if (message.skill == 2) {
                            Assassin.Skill2.fire(message.level);
                        } else if (message.skill == 3) {
                            Assassin.Skill3.fire(message.level);
                        } else if (message.skill == 4) {
                            Assassin.Ulti.fire(message.level);
                        }
                        break;
                    case "guerrier":
                        if (message.skill == 1) {
                            Guerrier.Skill1.fire(message.level);
                        } else if (message.skill == 2) {
                            Guerrier.Skill2.fire(message.level);
                        } else if (message.skill == 3) {
                            Guerrier.Skill3.fire(message.level);
                        } else if (message.skill == 4) {
                            Guerrier.Ulti.fire(message.level);
                        }
                        break;
                    case "arcaniste":
                        if (message.skill == 1) {
                            Arcaniste.Skill1.fire(message.level);
                        } else if (message.skill == 2) {
                            Arcaniste.Skill2.fire(message.level);
                        } else if (message.skill == 3) {
                            Arcaniste.Skill3.fire(message.level);
                        } else if (message.skill == 4) {
                            Arcaniste.Ulti.fire(message.level);
                        }
                        break;
                    case "druide":
                        if (message.skill == 1) {
                            Druide.Skill1.fire(message.level);
                        } else if (message.skill == 2) {
                            Druide.Skill2.fire(message.level);
                        } else if (message.skill == 3) {
                            Druide.Skill3.fire(message.level);
                        } else if (message.skill == 4) {
                            Druide.Ulti.fire(message.level);
                        }
                        break;
                    case "necromancien":
                        if (message.skill == 1) {
                            Necromancien.Skill1.fire(message.level);
                        } else if (message.skill == 2) {
                            Necromancien.Skill2.fire(message.level);
                        } else if (message.skill == 3) {
                            Necromancien.Skill3.fire(message.level);
                        } else if (message.skill == 4) {
                            Necromancien.Ulti.fire(message.level);
                        }
                        break;
                }

            } else {
                Minecraft.getMinecraft().player.sendMessage(new TextComponentString("§4Désolé, mais vous n'avez pas encore débloqué cette compétence !"));
            }

            switch (message.skill) {
                case 1: // SKILL 1
                    playerSkill1 = message.level;
                    break;
                case 2: // SKILL 2
                    playerSkill2 = message.level;
                    break;
                case 3: // SKILL 3
                    playerSkill3 = message.level;
                    break;
                case 4: // ULTI
                    playerUlti = message.level;
                    break;
            }
            playerClasse = message.classe;
            return null;
        }
    }
}