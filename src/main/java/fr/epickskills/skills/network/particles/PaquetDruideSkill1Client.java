package fr.epickskills.skills.network.particles;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.concurrent.ThreadLocalRandom;

public class PaquetDruideSkill1Client implements IMessage {



    public PaquetDruideSkill1Client() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PaquetDruideSkill1Client, IMessage> {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PaquetDruideSkill1Client message, MessageContext ctx) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.HEART, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
            Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.NOTE, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
            return null;
        }
    }
}