package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static net.minecraft.init.MobEffects.*;

public class PaquetGuerrierSkill3 implements IMessage {


    private int activation, strongRate, healthPercent;


    public PaquetGuerrierSkill3() {
    }

    public PaquetGuerrierSkill3(int activation, int strongRate, int healthPercent) {
        this.activation = activation;
        this.strongRate = strongRate;
        this.healthPercent = healthPercent;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        strongRate = buf.readInt();
        healthPercent = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        buf.writeInt(strongRate);
        buf.writeInt(healthPercent);
    }

    public static class Handler implements IMessageHandler<PaquetGuerrierSkill3, IMessage> {
        @Override
        public IMessage onMessage(PaquetGuerrierSkill3 message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                EntityPlayerMP playerMP = ctx.getServerHandler().player;
                playerMP.addPotionEffect(new PotionEffect(RESISTANCE, 20 * message.activation, message.strongRate));
                playerMP.addPotionEffect(new PotionEffect(FIRE_RESISTANCE, 20 * message.activation, message.strongRate));
                int healthToRemove = (int) (playerMP.getMaxHealth()*(message.healthPercent)/100);
                if(playerMP.getHealth() - healthToRemove <= 0 ){
                    playerMP.setHealth(1);
                } else {
                    playerMP.setHealth(playerMP.getHealth() - healthToRemove);
                }
            });

            return null;
        }
    }
}