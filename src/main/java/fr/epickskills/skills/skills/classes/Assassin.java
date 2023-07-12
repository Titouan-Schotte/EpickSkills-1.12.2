package fr.epickskills.skills.skills.classes;


import fr.epickskills.skills.entity.EntityAssassinSkill;
import fr.epickskills.skills.network.skills.PaquetAssassinSkill3;
import fr.epickskills.skills.network.skills.PaquetDamageEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import noppes.npcs.api.NpcAPI;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.json.SkillVariables.*;
import static fr.epickskills.skills.skills.Timeout.*;
import static fr.epickskills.skills.skills.Utilities.*;
import static fr.epickskills.skills.skills.classes.Assassin.Ulti.isUltiAssassin;
import static fr.epickskills.skills.skills.classes.Assassin.Ulti.sqr;

public class Assassin {
    private static EntityPlayer player = Minecraft.getMinecraft().player;

    public static class Skill1 {

        private static int tick=0;
        private static int nb=0;
        private static int nbBuff=0;
        private static boolean isSkill1Assassin=true;
        public static void fire(int level) {
            if (!isSkill1Timeout() && !isUltiAssassin) {
//                if (level == 1) {
//                    level1();
//                } else if (level == 2) {
//                    level2();
//                } else {
//                    level3();
//                }
                tick=0;
                nb=level*2;
                nbBuff=0;
                isSkill1Assassin=true;
                setSkill1Timeout((int) propAssSkill1Cooldown);
            }
        }

        public static void level1() {

            for (int i = -3; i <= 3; i += 2) {
                EntityAssassinSkill entity = new EntityAssassinSkill(Minecraft.getMinecraft().world, Minecraft.getMinecraft().player, propAssSkill1Damages);
                entity.shoot(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.rotationPitch, Minecraft.getMinecraft().player.rotationYaw - i, 0.0F, 1F, 5.0F);
                Minecraft.getMinecraft().world.spawnEntity(entity);
            }
        }

        public static void level2() {
            for (int i = -5; i <= 5; i += 2) {
                EntityAssassinSkill entity = new EntityAssassinSkill(Minecraft.getMinecraft().world, Minecraft.getMinecraft().player, propAssSkill1Damages);
                entity.shoot(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.rotationPitch, Minecraft.getMinecraft().player.rotationYaw - i, 0.0F, 1F, 5.0F);
                Minecraft.getMinecraft().world.spawnEntity(entity);
            }
        }

        public static void level3() {
            for (int i = -6; i <= 6; i += 2) {
                EntityAssassinSkill entity = new EntityAssassinSkill(Minecraft.getMinecraft().world, Minecraft.getMinecraft().player, propAssSkill1Damages);
                entity.shoot(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.rotationPitch, Minecraft.getMinecraft().player.rotationYaw - i, 0.0F, 1F, 5.0F);
                Minecraft.getMinecraft().world.spawnEntity(entity);
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill1Assassin) {
                if(tick%10==0){
                    if(nbBuff<=nb){
                        EntityAssassinSkill entity = new EntityAssassinSkill(Minecraft.getMinecraft().world, Minecraft.getMinecraft().player, propAssSkill1Damages);
                        entity.shoot(Minecraft.getMinecraft().player, Minecraft.getMinecraft().player.rotationPitch, Minecraft.getMinecraft().player.rotationYaw, 0.0F, 1F, 5.0F);
                        Minecraft.getMinecraft().world.spawnEntity(entity);
                        nbBuff++;
                    } else {
                        isSkill1Assassin=false;
                    }
                }
                tick++;
            }
        }
    }


    public static class Skill2 {
        public static int AssassinSkill2BumpNb = 0;

        public static void fire(int level) {

            if (!isSkill2Timeout() && !isUltiAssassin) {

                player = Minecraft.getMinecraft().player;
                if (AssassinSkill2BumpNb < propAssSkill2Bump) {
                    Skill2.bump();
                    AssassinSkill2BumpNb++;
                    if (AssassinSkill2BumpNb == propAssSkill2Bump) {
                        AssassinSkill2BumpNb = 0;
                        setSkill2Timeout((int) propAssSkill2Cooldown);
                    }
                }
            }
        }

        private static void bump() {
            float yaw = player.rotationYaw;
            float pitch = player.rotationPitch;
            float f = 5F;
            if (pitch < -9) {
                f = 3f;
            }
            if (pitch < -15) {
                f = 1f;
            }
            if (pitch < -30) {
                f = 0.4f;
            }
            if (pitch < -50) {
                player.sendMessage(new TextComponentString(player.getName() + " : §bSi je fonce vers les cieux je risque de me faire mal à l'atterissage"));
                return;
            }

            double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f;
            double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f;
            double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * f;
            player.setVelocity(motionX, motionY, motionZ);
        }



    }

    public static class Skill3 {

        public static boolean isSkill3Assassin = false;
        private static int ticks = 0;
        private static int radiusActivation = 0;
        private static int timestampStart = 20;
        public static void fire(int level) {
            if(!isSkill3Timeout() && !isSkill3Assassin){
                player=Minecraft.getMinecraft().player;
                isSkill3Assassin=true;
                radiusActivation= (int) propAssSkill3Radius;
                timestampStart=Math.toIntExact(System.currentTimeMillis() / 1000)+ (int) propAssSkill3Activation;
                ticks=0;
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill3Assassin) {
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    isSkill3Assassin=false;
                    setSkill3Timeout((int) propAssSkill3Cooldown);
                    return;
                }

                List<EntityLivingBase> entities = player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - radiusActivation, player.posY - radiusActivation, player.posZ - radiusActivation, player.posX + radiusActivation, player.posY + radiusActivation, player.posZ + radiusActivation));
                if(ticks%5==0){
                    for (EntityLivingBase entityLiving : entities) {
                        if(entityLiving.getEntityId()!=player.getEntityId()){
                            if (entityLiving.isEntityAlive() && entityLiving.attackable()) {
                                System.out.println(entityLiving.getName());
                                System.out.println(entityLiving.getClass());
                                network.sendToServer(new PaquetAssassinSkill3(entityLiving.getEntityId(), radiusActivation));
                            }
                            for(double d=0;d<360;d+=120){
                                double r=Math.toRadians(d+ticks*5);
                                double x=0.5 * Math.cos(r);
                                double z=0.5 * Math.sin(r);
                                double npcx=entityLiving.posX+x;
                                double npcy=entityLiving.posY;
                                double npcz=entityLiving.posZ+z;
                                spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,npcx,npcy+entityLiving.height+entityLiving.height*0.3,npcz,0,0,0);
                            }
                        }
                    }
                }

                ticks++;
            }
        }


//        @SubscribeEvent
//        public void onSetTarget(LivingSetAttackTargetEvent event){
//            if(isSkill3Assassin){
//                if(event.getTarget().getEntityId()==player.getEntityId()){
//                    System.out.println(event.getTarget().getName() +" "+event.getEntity().getName());
//                    if(event.getEntity() instanceof EntityMob){
//                        EntityMob entityMob = (EntityMob) event.getEntity();
//                        List<EntityPlayer> entitiesPlayer = player.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(player.posX - radiusActivation, player.posY - radiusActivation, player.posZ - radiusActivation, player.posX + radiusActivation, player.posY + radiusActivation, player.posZ + radiusActivation));
//                        if(entitiesPlayer.size()==0){
//                            network.sendToServer(new PaquetAssassinSkill3(entityMob.getEntityId(), 0));
//                        } else {
//                            EntityPlayer target = entitiesPlayer.get((int) (Math.random() * entitiesPlayer.size()));
//                            if(target != null){
//                                if(target.getEntityId()!=player.getEntityId()){
//                                    network.sendToServer(new PaquetAssassinSkill3(entityMob.getEntityId(), target.getEntityId()));
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
    }

    public static class Ulti {
        public static boolean isUltiAssassin = false;
        private static int activation = 0, damages = 10, rayon = 2, range = 10,

        timestampStart = 0, ticks = 0;
        private static double spellPosX = 0, spellPosY = 0, spellPosZ = 0;

        public static void fire(int level) {
            if (!isUltiTimeout() && !isUltiAssassin) {
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                activation = (int) propAssUltiActivation;
                damages = (int) propAssUltiDamages;
                rayon = (int) propAssUltiRayon;
                range = (int) propAssUltiRange;
                player = Minecraft.getMinecraft().player;
                timestampStart = Math.toIntExact(System.currentTimeMillis() / 1000);

                //SET TARGET
                Entity entity = getPointedEntity();
                if (entity != null) {
                    spellPosX = entity.posX;
                    spellPosY = entity.posY + (entity.height * 1.7);
                    spellPosZ = entity.posZ;
                    if(spellPosY - player.posY < 3){
                        spellPosY = player.posY + 3;
                    }
                } else {
                    int closest = Integer.MAX_VALUE;
                    BlockPos lastPosition = player.rayTrace(100, 1.0F).getBlockPos();
                    int xLook=lastPosition.getX();
                    int yLook=lastPosition.getY();
                    int zLook=lastPosition.getZ();
                    List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(xLook - 3, yLook -  3, zLook - 3, xLook + 3, yLook + 3, zLook + 3));
                    EntityLivingBase thisOne=null;
                    for (EntityLivingBase entityLivingBase : entities) {
                        if (!(entityLivingBase instanceof EntityPlayer) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable() &&  Math.sqrt(Arcaniste.Skill1.sqr(entityLivingBase.posZ - zLook) + Arcaniste.Skill1.sqr(entityLivingBase.posX - xLook))<closest) {
                            thisOne = entityLivingBase;
                            closest = (int) Math.sqrt((entityLivingBase.posZ-zLook)+(entityLivingBase.posX-xLook));
                        }
                    }

                    if (entities.size() > 0 && thisOne!=null) {
                        spellPosX = thisOne.posX;
                        spellPosY = thisOne.posY + (thisOne.height * 1.7);
                        spellPosZ = thisOne.posZ;
                        if(spellPosY - player.posY < 3){
                            spellPosY = player.posY + 3;
                        }
                    } else {
                        player.sendMessage(new TextComponentString("§4Vous êtes trop loin des ennemis."));
                        return;
                    }
                }

                isUltiAssassin = true;
            }

        }

        public static void level1() {

        }

        public static void level2() {

        }

        public static void level3() {

        }
        static public double sqr(double a) {
            return a*a;
        }
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isUltiAssassin) {
                for (int i : new int[2]) {
                    spawnParticle(EnumParticleTypes.CLOUD, spellPosX + ThreadLocalRandom.current().nextDouble(-rayon, rayon + 1), spellPosY, spellPosZ + ThreadLocalRandom.current().nextDouble(-rayon, rayon + 1), 0, 0, 0);
                }
                if (ticks % 17 == 0) {
                    List<EntityLivingBase> entities = player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(spellPosX-rayon, 0, spellPosZ-rayon, spellPosX+rayon, spellPosY+rayon,spellPosZ+rayon));
                    for(EntityLivingBase entityLivingBase : entities){
                        if(!entityLivingBase.equals(player)){
                            EntityAssassinSkill entity = new EntityAssassinSkill(Minecraft.getMinecraft().world, entityLivingBase.posX, spellPosY, entityLivingBase.posZ, damages);
                            entity.shoot(Minecraft.getMinecraft().player, (float) (90 + ThreadLocalRandom.current().nextDouble(-1.5, 0.5 + 1)), 0, 0.0F, 1F, 7.0F);
                            Minecraft.getMinecraft().world.spawnEntity(entity);
                        }
                    }
//                    EntityAssassinSkill entity = new EntityAssassinSkill(Minecraft.getMinecraft().world, spellPosX + ThreadLocalRandom.current().nextDouble(-rayon, rayon + 1), spellPosY, spellPosZ + ThreadLocalRandom.current().nextDouble(-rayon, rayon + 1), damages);
//                    entity.shoot(Minecraft.getMinecraft().player, (float) (90 + ThreadLocalRandom.current().nextDouble(-1.5, 0.5 + 1)), 0, 0.0F, 1F, 7.0F);
//                    Minecraft.getMinecraft().world.spawnEntity(entity);
//                        entity.shoot(player.posX, player.posY, player.posZ, 1, 1);


                    int timestamp = Math.toIntExact(System.currentTimeMillis() / 1000);
                    //END
                    if (timestampStart + activation == timestamp) {
                        setUltiTimeout((int) propAssUltiCooldown);
                        isUltiAssassin = false;
                    }
                }
                ticks++;
            }
        }
//        @SideOnly(Side.CLIENT)
//        @SubscribeEvent
//        public void onAttack(AttackEntityEvent event) {
//            if(isUltiAssassin){
//                setUltiTimeout((int) propAssUltiCooldown);
//                isUltiAssassin=false;
//                network.sendToServer(new PaquetResetPotion());
//                network.sendToServer(new PaquetAssassinUltiEnd());
//            }
//        }


    }
}
