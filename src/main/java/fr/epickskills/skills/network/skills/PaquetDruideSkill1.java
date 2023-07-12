package fr.epickskills.skills.network.skills;

import fr.epickskills.skills.network.particles.PaquetDruideSkill1Client;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.UUID;

import static fr.epickskills.skills.Main.network;

public class PaquetDruideSkill1 implements IMessage {


    private int activation;
    private String uuid;

    public PaquetDruideSkill1() {
    }

    public PaquetDruideSkill1(String uuid, int activation) {
        this.activation = activation;
        this.uuid = uuid;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        activation = buf.readInt();
        uuid = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(activation);
        ByteBufUtils.writeUTF8String(buf, uuid);
    }

    public static class Handler implements IMessageHandler<PaquetDruideSkill1, IMessage> {
        @Override
        public IMessage onMessage(PaquetDruideSkill1 message, MessageContext ctx) {
            EntityPlayerMP playerTarget = ctx.getServerHandler().player;
            World world = DimensionManager.getWorld(playerTarget.dimension);
            EntityPlayer player = world.getPlayerEntityByUUID(UUID.fromString(message.uuid));
            player.setHealth((float) (player.getHealth()+player.getMaxHealth()*0.03));
            network.sendToAllAround(new PaquetDruideSkill1Client(), new NetworkRegistry.TargetPoint(playerTarget.dimension, player.posX,player.posY, player.posZ, 20));
//            for(int i=0;i<5;i++){
//                playerTarget.getServerWorld().spawnAlwaysVisibleParticle(EnumParticleTypes.HEART.getParticleID(), player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
//                playerTarget.getServerWorld().spawnAlwaysVisibleParticle(EnumParticleTypes.NOTE.getParticleID(), player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
//            }
            return null;
        }
    }
}