package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

import static net.minecraft.init.MobEffects.REGENERATION;

public class PaquetDruideSkill1Particles implements IMessage {


    private int activation;
    private int rayon;

    public PaquetDruideSkill1Particles() {
    }

    public PaquetDruideSkill1Particles(int activation, int rayon) {
        this.activation = activation;
        this.rayon = rayon;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        rayon = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        buf.writeInt(rayon);
    }

    public static class Handler implements IMessageHandler<PaquetDruideSkill1Particles, IMessage> {

        private static double spellX = 0,spellY = 0,spellZ = 0;
        private static int rayon = 0,activation = 0;


        @Override
        public IMessage onMessage(PaquetDruideSkill1Particles message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            spellX=player.posX;
            spellY=player.posX;
            spellZ=player.posX;

            rayon=message.rayon;
            activation=message.activation;
            return null;
        }
    }
}