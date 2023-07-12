package fr.epickskills.skills.skills.classes;

import fr.epickskills.skills.entity.druide.RenderMagPie;
import fr.epickskills.skills.entity.druide.RenderWereWolf;
import fr.epickskills.skills.network.skills.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.json.SkillVariables.*;
import static fr.epickskills.skills.skills.Timeout.*;
import static fr.epickskills.skills.skills.Utilities.spawnParticle;
import static fr.epickskills.skills.skills.classes.Druide.Skill1.isSkill1Druide;
import static fr.epickskills.skills.skills.classes.Druide.Skill2.isSkill2Druide;
import static fr.epickskills.skills.skills.classes.Druide.Skill3.isSkill3Druide;
import static fr.epickskills.skills.skills.classes.Druide.Ulti.isSkillDruideUlti;

public class Druide {
    private static EntityPlayer player = Minecraft.getMinecraft().player;

    public static class Skill1 {
        public static boolean isSkill1Druide = false;
        private static double posXSpell, posYSpell, posZSpell;
        private static int ticks, timestampStart, radius;

        public static void fire(int level) {
            if (!isSkill1Timeout() && !isSkillDruideUlti && !isSkill3Druide && !isSkill2Druide && !isSkill1Druide) {
                player = Minecraft.getMinecraft().player;
                posXSpell = player.posX;
                posYSpell = player.posY;
                posZSpell = player.posZ;
                network.sendToServer(new PaquetSetGlowing());
                timestampStart = Math.toIntExact(System.currentTimeMillis()/1000)+(int) propDruSkill1Activation ;
                radius = (int) propDruSkill1Radius;
                if(!isSkill1Druide){
                    if (level == 1) {
                        level1();
                    } else if (level == 2) {
                        level2();
                    } else {
                        level3();
                    }
                }
                isSkill1Druide = true;
            }
        }

        public static void level1() {

        }

        public static void level2() {
        }

        public static void level3() {
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill1Druide) {
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    isSkill1Druide = false;
                    ticks = 0;
                    network.sendToServer(new PaquetResetGlowing());
                    setSkill1Timeout((int) propDruSkill1Cooldown);
                    return;
                }
                if (ticks % 4 == 0) {
                    spawnParticle(EnumParticleTypes.NOTE, posXSpell + ThreadLocalRandom.current().nextInt(-radius, radius + 1), posYSpell + ThreadLocalRandom.current().nextInt(0, 1 + 1), posZSpell + ThreadLocalRandom.current().nextInt(-radius, radius + 1), 10, 10, 10);
                } else if (ticks % 3 == 0) {
                    spawnParticle(EnumParticleTypes.HEART, posXSpell + ThreadLocalRandom.current().nextInt(-radius, radius + 1), posYSpell + ThreadLocalRandom.current().nextInt(0, 1 + 1), posZSpell + ThreadLocalRandom.current().nextInt(-radius, radius + 1), 10, 10, 10);
                }

                if (ticks % 20 == 0) {
                    List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posXSpell - (radius), posYSpell - (radius), posZSpell - (radius), posXSpell + (radius), posYSpell + (radius), posZSpell + (radius)));
                    for (EntityLivingBase entityLivingBase : entities) {
                        if (entityLivingBase instanceof EntityPlayer) {
                            network.sendToServer(new PaquetDruideSkill1(String.valueOf(entityLivingBase.getUniqueID()), 5));
                        }
                    }
                }
                ticks++;
            }
        }
    }

    public static class Skill2 {
        public static boolean isSkill2Druide = false;
        private static double[] coord = {0, 0, 0};
        private static int radius, timestampStart, damagesPerTick;
        private int ticks;

        public static void fire(int level) {
            if (!isSkill2Timeout() && !isSkill2Druide && !isSkill3Druide && !isSkill1Druide && !isSkillDruideUlti) {

                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                timestampStart=Math.toIntExact(System.currentTimeMillis() / 1000)+(int)propDruSkill2Activation;
                player = Minecraft.getMinecraft().player;
                radius = (int) propDruSkill2Radius;
                damagesPerTick = (int) propDruSkill2Activation;
                isSkill2Druide = true;
                coord = new double[]{player.posX, player.posY, player.posZ};
            }


        }

        public static void level1() {

        }

        public static void level2() {

        }

        public static void level3() {

        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill2Druide) {
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    isSkill2Druide = false;
                    ticks = 0;
                    setSkill2Timeout((int) propDruSkill2Cooldown);
                    return;
                }

                if (ticks % 40 == 0) {
                    network.sendToServer(new PaquetDruideSkill2(coord, radius));
                }
                spawnParticle(EnumParticleTypes.SPELL_WITCH, coord[0] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), coord[1] + ThreadLocalRandom.current().nextInt(0, (radius / 2) + 1), coord[2] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), 10, 10, 10);
                spawnParticle(EnumParticleTypes.SPELL_MOB, coord[0] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), coord[1] + ThreadLocalRandom.current().nextInt(0, (radius / 2) + 1), coord[2] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), 10, 10, 10);
                spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, coord[0] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), coord[1] + ThreadLocalRandom.current().nextInt(0, (radius / 2) + 1), coord[2] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), 10, 10, 10);
                spawnParticle(EnumParticleTypes.SPELL, coord[0] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), coord[1] + ThreadLocalRandom.current().nextInt(0, (radius / 2) + 1), coord[2] + ThreadLocalRandom.current().nextInt(-radius, radius + 1), 10, 10, 10);
                ticks++;
            }
        }

    }

    public static class Skill3 {

        public static boolean isSkill3Druide = false,prevIsFly, prevAllowFlying;;
        private static float prevFlySpeed;
        private static double prevCoordY;
        private static int timestampStart = 10, tempPrevThirdView = 0;
        private int ticks = 0;
        public static void fire(int level) {
            if (!isSkill3Timeout() && !isSkillDruideUlti && !isSkill3Druide && !isSkill1Druide && !isSkill2Druide) {
                player = Minecraft.getMinecraft().player;
                player.setPosition(player.posX, player.posY + 1, player.posZ);
                tempPrevThirdView = Minecraft.getMinecraft().gameSettings.thirdPersonView;
                timestampStart = Math.toIntExact(System.currentTimeMillis() / 1000) + (int) propDruSkill3Activation;
                network.sendToServer(new PaquetDruideSkill3((int) propDruSkill3Activation,(int) propDruSkill3SpeedRate));
                Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
                prevFlySpeed = player.capabilities.getFlySpeed();
                prevIsFly = player.capabilities.isFlying;
                prevAllowFlying = player.capabilities.allowFlying;
                player.capabilities.setFlySpeed((float) propDruSkill3SpeedRate);
                player.capabilities.allowFlying = true;
                player.capabilities.isFlying = true;
                prevCoordY=player.posY;
                isSkill3Druide = true;

            }
        }
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPlayer(RenderPlayerEvent.Pre event) {
            if (isSkill3Druide && event.getEntityPlayer().getUniqueID().equals(Minecraft.getMinecraft().player.getUniqueID())) {
                event.setCanceled(true);
                RenderManager manager = Minecraft.getMinecraft().getRenderManager();
                RenderMagPie bird = new RenderMagPie(manager);
                bird.doRender(event.getEntityLiving(), 0D, 0D, 0D, 0.65F, 0.65F);
                Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
                isSkill3Druide=true;
            }
        }
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill3Druide) {
                if(prevCoordY-propDruSkill3Range>=player.posY){
                    player.setPosition(player.posX, prevCoordY-(propDruSkill3Range-1), player.posZ);
                } else if(player.posY>=prevCoordY+propDruSkill3Range) {
                    player.setPosition(player.posX, prevCoordY+(propDruSkill3Range-1), player.posZ);
                }
                player.capabilities.setFlySpeed((float) propDruSkill3SpeedRate);
                player.capabilities.allowFlying = true;
                player.capabilities.isFlying = true;
                Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
                ////
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    ticks = 0;
                    isSkill3Druide = false;
                    player.capabilities.setFlySpeed(prevFlySpeed);
                    player.capabilities.allowFlying = prevAllowFlying;
                    player.capabilities.isFlying = prevIsFly;
                    Minecraft.getMinecraft().gameSettings.thirdPersonView = tempPrevThirdView;
                    setSkill3Timeout((int) propDruSkill3Cooldown);
                }
                ticks++;

            }
        }

    }
    public static class Ulti {
        public static boolean isSkillDruideUlti = false;
        private static int timestampStart = 10;
        private static int speedRate = 1;
        private static int radius = 1;
        private static int jumpRate = 1, tempPrevThirdView = 0;
        private int ticks = 0;

        public static void fire(int level) {
            if (!isUltiTimeout() && !isSkillDruideUlti && !isSkill3Druide && !isSkill1Druide && !isSkill2Druide) {
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                timestampStart = Math.toIntExact(System.currentTimeMillis() / 1000)+ (int) propDruUltiActivation;
                speedRate = (int) propDruUltiSpeedRate;
                radius = (int) propDruUltiRadius;
                jumpRate = (int) propDruUltiJumpRate;
                player = Minecraft.getMinecraft().player;
                tempPrevThirdView = Minecraft.getMinecraft().gameSettings.thirdPersonView;
                network.sendToServer(new PaquetDruideUlti((int) propDruUltiActivation, speedRate, jumpRate));
                isSkillDruideUlti = true;
                Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
            }

        }

        public static void level1() {

        }

        public static void level2() {

        }

        public static void level3() {

        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPlayer(RenderPlayerEvent.Pre event) {
            if (isSkillDruideUlti && event.getEntityPlayer().getUniqueID().equals(Minecraft.getMinecraft().player.getUniqueID())) {
                event.setCanceled(true);
                RenderManager manager = Minecraft.getMinecraft().getRenderManager();
                RenderWereWolf wereWolf = new RenderWereWolf(manager);
                wereWolf.doRender(event.getEntityLiving(), 0D, 0D, 0D, 0.65F, 0.65F);
                Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkillDruideUlti) {

                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    ticks = 0;
                    isSkillDruideUlti = false;
                    Minecraft.getMinecraft().gameSettings.thirdPersonView = tempPrevThirdView;
                    setUltiTimeout((int) propDruUltiCooldown);
                    return;
                }
                Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
                    if (ticks % 10 == 0) {
                        List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - (radius), player.posY - (radius), player.posZ - (radius), player.posX + (radius), player.posY + (radius), player.posZ + (radius)));
                        for (EntityLivingBase entityLivingBase : entities) {
                            if (!(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable()) {
                                spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, player.posX, player.posY + 1.0D, player.posZ, 0.0D, 0.0D, 0.0D);
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propDruUltiDamages, false, 0));
                                network.sendToServer(new PaquetHealth());
                                for(int i=0;i<10;i++){
                                    spawnParticle(EnumParticleTypes.HEART, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
                                }

                            }
                        }
                    }
                ticks++;

            }
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public void attack(LivingAttackEvent event) {
            if (isSkillDruideUlti && !isUltiTimeout()) {
                if (event.getSource().getTrueSource() instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) event.getSource().getTrueSource();
                    if (entityPlayer.getUniqueID().equals(Minecraft.getMinecraft().player.getUniqueID())) {
                        EntityPlayer player = Minecraft.getMinecraft().player;
                        network.sendToServer(new PaquetHealth());
                        for(int i=0;i<10;i++){
                            spawnParticle(EnumParticleTypes.HEART, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
                        }

                        float yaw = player.rotationYaw;
                        float pitch = player.rotationPitch;
                        float f = 1.5F;
                        if (pitch < -9) {
                            f = 0.5f;
                        }
                        if (pitch < -15) {
                            f = 0.4f;
                        }
                        if (pitch < -30) {
                            f = 0.3f;
                        }
                        if (pitch < -50) {
                            player.sendMessage(new TextComponentString(player.getName() + " : §bGroar, si je fonce vers les cieux mes pattes arrières vont prendre tarifs !"));
                            return;
                        }
                        double motionX = -MathHelper.sin(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f;
                        double motionZ = MathHelper.cos(yaw / 180.0F * (float) Math.PI) * MathHelper.cos(pitch / 180.0F * (float) Math.PI) * f;
                        double motionY = -MathHelper.sin((pitch) / 180.0F * (float) Math.PI) * f;
                        player.setVelocity(motionX, motionY, motionZ);
                        spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, player.posX, player.posY + 1.0D, player.posZ, 0.0D, 0.0D, 0.0D);

                    }
                }
            }

        }
    }

}
