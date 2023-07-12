package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import java.util.Iterator;
import java.util.List;

public class PaquetArcanisteUltiPotion implements IMessage {


    private int timer;
    private int level;


    public PaquetArcanisteUltiPotion() {
    }

    public PaquetArcanisteUltiPotion(int timer, int level) {

        this.timer = timer;
        this.level = level;
    }


    @Override
    public void fromBytes(ByteBuf buf) {

        this.timer = buf.readInt();
        this.level = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.timer);
        buf.writeInt(this.level);
    }

    public static class Handler implements IMessageHandler<PaquetArcanisteUltiPotion, IMessage> {
        @Override
        public IMessage onMessage(PaquetArcanisteUltiPotion message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 20 * message.timer, 1, false, false));
                ctx.getServerHandler().player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 20 * message.timer, message.level-1, false, false));
            });
            return null;
        }
    }
}