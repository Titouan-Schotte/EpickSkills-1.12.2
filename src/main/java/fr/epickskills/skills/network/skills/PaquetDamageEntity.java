package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EntityTracker;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetDamageEntity implements IMessage {


    private int id = 0, damages = 0, fire = 0, timeFire = 0, ice = 0, timeIce = 0, motionY = 0, web=0,poison=0,timePoison=0,volVie=0;

    public PaquetDamageEntity() {
    }
    public PaquetDamageEntity(int id, int damages, boolean fire, int timeFire, boolean ice, int timeIce, boolean web, boolean poison, int timePoison, int motionY) {
        this.id = id;
        this.damages = damages;
        this.timeFire = timeFire;
        if (fire) {
            this.fire = 1;
        } else {
            this.fire = 0;
        }
        this.timeIce = timeIce;
        if (ice) {
            this.ice = 1;
        } else {
            this.ice = 0;
        }

        if (web) {
            this.web = 1;
        } else {
            this.web = 0;
        }

        this.timePoison=timePoison;
        if (poison) {
            this.poison = 1;
        } else {
            this.poison = 0;
        }
        this.motionY = motionY;
    }
    public PaquetDamageEntity(int id, int damages, boolean fire, int timeFire, boolean ice, int timeIce, boolean web, boolean poison, int timePoison) {
        this.id = id;
        this.damages = damages;
        this.timeFire = timeFire;
        if (fire) {
            this.fire = 1;
        } else {
            this.fire = 0;
        }
        this.timeIce = timeIce;
        if (ice) {
            this.ice = 1;
        } else {
            this.ice = 0;
        }

        if (web) {
            this.web = 1;
        } else {
            this.web = 0;
        }

        this.timePoison=timePoison;
        if (poison) {
            this.poison = 1;
        } else {
            this.poison = 0;
        }

    }
    public PaquetDamageEntity(int id, int damages, boolean fire, int timeFire, boolean ice, int timeIce, boolean web) {
        this.id = id;
        this.damages = damages;
        this.timeFire = timeFire;
        if (fire) {
            this.fire = 1;
        } else {
            this.fire = 0;
        }
        this.timeIce = timeIce;
        if (ice) {
            this.ice = 1;
        } else {
            this.ice = 0;
        }

        if (web) {
            this.web = 1;
        } else {
            this.web = 0;
        }

    }
//    public PaquetDamageEntity(int id, int damages, boolean volVie) {
//        this.id = id;
//        this.damages = damages;
//        if (volVie) {
//            this.volVie = 1;
//        } else {
//            this.volVie = 0;
//        }
//
//    }

    public PaquetDamageEntity(int id, int damages, boolean fire, int timeFire, boolean ice, int timeIce) {
        this.id = id;
        this.damages = damages;
        this.timeFire = timeFire;
        if (fire) {
            this.fire = 1;
        } else {
            this.fire = 0;
        }
        this.timeIce = timeIce;
        if (ice) {
            this.ice = 1;
        } else {
            this.ice = 0;
        }

    }

    public PaquetDamageEntity(int id, int damages, boolean fire, int timeFire) {
        this.id = id;
        this.damages = damages;
        this.timeFire = timeFire;
        if (fire) {
            this.fire = 1;
        } else {
            this.fire = 0;
        }
    }
    public PaquetDamageEntity(int id, int damages, int motionY, boolean fire, int timeFire) {
        this.id = id;
        this.damages = damages;
        this.timeFire = timeFire;
        if (fire) {
            this.fire = 1;
        } else {
            this.fire = 0;
        }
        this.motionY = motionY;
    }
    public PaquetDamageEntity(int id, int damages, int motionY) {
        this.id = id;
        this.damages = damages;
        this.timeFire = timeFire;
        this.motionY = motionY;

    }

    public PaquetDamageEntity(int id, int damages) {
        this.id = id;
        this.damages = damages;
    }


    @Override
    public void fromBytes(ByteBuf buf) {
        id = buf.readInt();
        damages = buf.readInt();
        motionY = buf.readInt();
        fire = buf.readInt();
        timeFire = buf.readInt();
        ice = buf.readInt();
        timeIce = buf.readInt();
        web = buf.readInt();
        poison = buf.readInt();
        timePoison = buf.readInt();
//        volVie = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeInt(damages);
        buf.writeInt(motionY);
        buf.writeInt(fire);
        buf.writeInt(timeFire);
        buf.writeInt(ice);
        buf.writeInt(timeIce);
        buf.writeInt(web);
        buf.writeInt(poison);
        buf.writeInt(timePoison);
//        buf.writeInt(volVie);
    }

    public static class Handler implements IMessageHandler<PaquetDamageEntity, IMessage> {
        @Override
        public IMessage onMessage(PaquetDamageEntity message, MessageContext ctx) {
            try {
                FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
                {
                boolean fire = message.fire == 1;
                boolean ice = message.ice == 1;
                boolean web = message.web == 1;
                boolean poison = message.poison == 1;
                boolean volVie = message.volVie == 1;
                if (ctx.getServerHandler().player.world.getEntityByID(message.id) instanceof EntityLivingBase) {
                    EntityLivingBase entityLivingBase = (EntityLivingBase) ctx.getServerHandler().player.world.getEntityByID(message.id);
                    if (entityLivingBase != null) {
                        if (!entityLivingBase.equals(ctx.getServerHandler().player) && !(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive() && !entityLivingBase.isInvisible() && entityLivingBase.canBeHitWithPotion() && entityLivingBase.canBeAttackedWithItem() && entityLivingBase.canBeCollidedWith() && entityLivingBase.addedToChunk && entityLivingBase.canBePushed() && !entityLivingBase.isBeingRidden()) {
                            DamageSource damageSource = DamageSource.causePlayerDamage(ctx.getServerHandler().player);
                            entityLivingBase.attackEntityFrom(damageSource, message.damages);
                            if (fire) {
                                damageSource.setFireDamage();
                                entityLivingBase.setFire(message.timeFire);
                            }

                            if (ice) {
                                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * message.ice, 5, false, true));
                            }

                            if(web){
                                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20 * 2, 6, false, true));
                                entityLivingBase.setInWeb();
                            }

                            if(poison){
                                entityLivingBase.addPotionEffect(new PotionEffect(MobEffects.POISON, 20 * message.timePoison, 1, false, true));
                            }

                            if (message.motionY != 0) {
                                entityLivingBase.motionY = message.motionY;
                            }

//                            if(volVie){
//                                EntityPlayerMP playerMP = ctx.getServerHandler().player;
//                            }
                        }
                    }
                }
                });

            } catch (Exception e) {
                return null;
            }






            return null;
        }
    }
}
