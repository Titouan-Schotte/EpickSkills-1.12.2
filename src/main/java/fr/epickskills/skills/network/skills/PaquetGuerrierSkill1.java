package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import static fr.epickskills.skills.proxy.ServerProxy.playersSkill1Guerrier;
import static net.minecraft.init.MobEffects.ABSORPTION;
import static net.minecraft.init.MobEffects.STRENGTH;

public class PaquetGuerrierSkill1 implements IMessage {



    public PaquetGuerrierSkill1() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PaquetGuerrierSkill1, IMessage> {
        @Override
        public IMessage onMessage(PaquetGuerrierSkill1 message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> playersSkill1Guerrier.add(String.valueOf(ctx.getServerHandler().player.getUniqueID())));
            return null;
        }
    }
}