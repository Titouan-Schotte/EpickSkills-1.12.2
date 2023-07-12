package fr.epickskills.skills.network.skills;

import fr.epickskills.skills.network.particles.PaquetGuerrierSkill3Client;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

import static fr.epickskills.skills.Main.network;
import static net.minecraft.init.MobEffects.*;

public class PaquetGuerrierSkill3Spell implements IMessage {


    private String id;
    private int activation, strongRate;


    public PaquetGuerrierSkill3Spell() {
    }

    public PaquetGuerrierSkill3Spell(String id, int activation, int strongRate) {
        this.activation = activation;
        this.id = id;
        this.strongRate = strongRate;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        id = ByteBufUtils.readUTF8String(buf);
        strongRate = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        ByteBufUtils.writeUTF8String(buf, id);
        buf.writeInt(strongRate);
    }

    public static class Handler implements IMessageHandler<PaquetGuerrierSkill3Spell, IMessage> {
        @Override
        public IMessage onMessage(PaquetGuerrierSkill3Spell message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                EntityPlayer playerTarget = ctx.getServerHandler().player;
                World world = DimensionManager.getWorld(playerTarget.dimension);
                EntityPlayer player = world.getPlayerEntityByUUID(UUID.fromString(message.id));
                player.addPotionEffect(new PotionEffect(RESISTANCE, 20 * message.activation, message.strongRate));
                player.addPotionEffect(new PotionEffect(FIRE_RESISTANCE, 20 * message.activation, message.strongRate));
                network.sendToAllAround(new PaquetGuerrierSkill3Client(), new NetworkRegistry.TargetPoint(playerTarget.dimension, player.posX,player.posY, player.posZ, 20));

//                for(int i=0;i<5;i++){
//                    world.spawnAlwaysVisibleParticle(ParticlesHandler.CUSTOM_PORTAL_GUER_3.getParticleID(), player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
//                    world.spawnAlwaysVisibleParticle(EnumParticleTypes.CRIT.getParticleID(), player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
//                }
            });
            return null;
        }
    }
}