package fr.epickskills.skills.network.particles;

import fr.epickskills.skills.particles.ParticlesHandler;
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

public class PaquetGuerrierSkill3Client implements IMessage {



    public PaquetGuerrierSkill3Client() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PaquetGuerrierSkill3Client, IMessage> {
        @SideOnly(Side.CLIENT)
        @Override
        public IMessage onMessage(PaquetGuerrierSkill3Client message, MessageContext ctx) {
            EntityPlayer player = Minecraft.getMinecraft().player;
            Minecraft.getMinecraft().world.spawnParticle(ParticlesHandler.CUSTOM_PORTAL_GUER_3, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
            Minecraft.getMinecraft().world.spawnParticle(EnumParticleTypes.CRIT, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
            return null;
        }
    }
}