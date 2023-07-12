package fr.epickskills.skills.skills.classes;

import fr.epickskills.skills.network.skills.*;
import fr.epickskills.skills.particles.ParticlesHandler;
import fr.epickskills.skills.skills.Timeout;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
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

public class Guerrier {
    private static EntityPlayer player = Minecraft.getMinecraft().player;

    public static class Skill1 {
        private static boolean isGuerrierSkill1 = false;
        private static int radius = 0;
        private static double posXSpell, posYSpell, posZSpell;
        private static int groundBugged = 0;
        private static int levelIn = 1;

        public static void fire(int level) {
            if (!isSkill1Timeout() && !isGuerrierSkill1) {
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                player = Minecraft.getMinecraft().player;
                radius = (int) propGueSkill1Radius;
                groundBugged = 0;
                player.motionY = propGueSkill1Jump;
                levelIn = level;
                network.sendToServer(new PaquetGuerrierSkill1());
                Guerrier.Skill1.isGuerrierSkill1 = true;
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
            if (!isSkill1Timeout() && isGuerrierSkill1) {
                if (player.onGround) {
                    groundBugged++;
                    if (groundBugged == 2) {
                        groundBugged = 0;


                        List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - radius, player.posY - radius, player.posZ - radius, player.posX + radius, player.posY + radius, player.posZ + radius));
                        for (int d = 0; d < 300; d+=30) {
                            double r = Math.toRadians(d);
                            double x = r * Math.cos(r);
                            double z = r * Math.sin(r);
                            double playX = event.player.posX + x / 10;
                            double playY = event.player.posY + d / 700;
                            double playZ = event.player.posZ + z / 10;
                            spawnParticle(EnumParticleTypes.LAVA, playX, playY, playZ, 0, 0, 0);
                        }
                        spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, event.player.posX, event.player.posY, event.player.posZ, 10, 10, 10);
                        for (EntityLivingBase entityLivingBase : entities) {
                            if (!(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable()) {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propGueSkill1Damages, true, 4+levelIn));

                            }
                        }
                        isGuerrierSkill1 = false;
                        setSkill1Timeout((int) propGueSkill1Cooldown);
                    }
                }
            }
        }
    }

    public static class Skill2 {
        public static void fire(int level) {
            if (!isSkill2Timeout()) {
                player = Minecraft.getMinecraft().player;
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
            }
        }

        public static void level1() {
            int activationTime = (int) propGueSkill2Activation;
            network.sendToServer(new PaquetGuerrierSkill2(activationTime, 1, 2));
            setSkill2Timeout((int) (propGueSkill2Cooldown + activationTime));
        }

        public static void level2() {
            int activationTime = (int) propGueSkill2Activation;
            network.sendToServer(new PaquetGuerrierSkill2(activationTime, 2, 3));
            setSkill2Timeout((int) (propGueSkill2Cooldown + activationTime));
        }

        public static void level3() {
            int activationTime = (int) propGueSkill2Activation;
            network.sendToServer(new PaquetGuerrierSkill2(activationTime, 3, 4));
            setSkill2Timeout((int) (propGueSkill2Cooldown + activationTime));
        }
    }

    public static class Skill3 {

        private static int radius = 0;
        private static int ticks = 0;
        private static int timestampStart = 20;
        private static int cooldown = 5;
        private static double spellX = 0, spellY = 0, spellZ= 0;
        public static boolean isSkill3Guerrier = false;
        public static void fire(int level) {
            if (!isSkill3Timeout() && !isSkill3Guerrier) {
                player = Minecraft.getMinecraft().player;
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                isSkill3Guerrier=true;
                spellX=player.posX;
                spellY=player.posY;
                spellZ=player.posZ;
                network.sendToServer(new PaquetSetGlowing());

                timestampStart= Math.toIntExact(System.currentTimeMillis() / 1000) + (int) propGueSkill3Activation;
                radius= (int) propGueSkill3Radius;
                cooldown= (int) propGueSkill3Cooldown;
                ticks=0;
            }



        }
        public static void level1() {
            int activationTime = (int) propGueSkill3Activation;
            network.sendToServer(new PaquetGuerrierSkill3(activationTime, (int) propGueSkill3ResistancePerso, (int) propGueSkill3LifeLeft));
        }

        public static void level2() {
            int activationTime = (int) propGueSkill3Activation;
            network.sendToServer(new PaquetGuerrierSkill3(activationTime, (int) propGueSkill3ResistancePerso, (int) propGueSkill3LifeLeft));
        }

        public static void level3() {
            int activationTime = (int) propGueSkill3Activation;
            network.sendToServer(new PaquetGuerrierSkill3(activationTime, (int) propGueSkill3ResistancePerso, (int) propGueSkill3LifeLeft));
        }
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill3Guerrier && !Timeout.isSkill3Timeout()) {
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    network.sendToServer(new PaquetResetGlowing());
                    ticks=0;
                    isSkill3Guerrier=false;
                    setSkill3Timeout(cooldown);
                    return;
                }

                    for(double l=Math.floor(Math.random()*40);l<360;l+=10+Math.floor(Math.random()*40)){

                        double lR=Math.toRadians(l);
                        for(double h=-60+Math.floor(Math.random()*40);h<180;h+=10+Math.floor(Math.random()*40)){
                            double hR=Math.toRadians(h);

                            double npcx=spellX+Math.cos(lR)*Math.cos(hR)*radius;
                            double npcy=spellY+Math.sin(hR)*radius;
                            double npcz=spellZ+Math.sin(lR)*Math.cos(hR)*radius;
                            spawnParticle(ParticlesHandler.CUSTOM_PORTAL_GUER_3,npcx,npcy,npcz,0,0,0);
                        }
                    }
                    if (ticks % 20 == 0) {
                        List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(spellX - (radius), spellY - (radius), spellZ - (radius), spellX + (radius), spellY + (radius), spellZ + (radius)));
                        for (EntityLivingBase entityLivingBase : entities) {
                            if (entityLivingBase instanceof EntityPlayer) {
                                if(entityLivingBase.getEntityId()!=player.getEntityId()){
                                    network.sendToServer(new PaquetGuerrierSkill3Spell(String.valueOf(entityLivingBase.getUniqueID()), (int) propGueSkill3ResistanceMulti, (int) propGueSkill3ResistanceMulti));
                                }
                            }
                        }
                    }




            }
            ticks++;

        }
    }

    public static class Ulti {
        public static boolean isUltiGuerrier = false;
        private static int ticks = 0, turns = 3;
        private static double radius = 1, speed = 1, distance = 1;
        private static EnumParticleTypes particle;
        private static List<EntityLivingBase> entities = null;

        public static void fire(int level) {
            if (!Timeout.isUltiTimeout() && !isUltiGuerrier) {
                radius = propGueUltiRadius;
                speed = propGueUltiSpeed;
                turns = (int) propGueUltiTurns;
                distance = propGueUltiDistance;
                player = Minecraft.getMinecraft().player;
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                isUltiGuerrier = true;
            }
        }

        public static void level1() {
            particle = ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL1;
        }

        public static void level2() {
            particle = ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL2;
        }

        public static void level3() {
            particle = ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL3;
        }


        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isUltiGuerrier && !Timeout.isUltiTimeout()) {
                if (ticks < 360 * turns) {
                    player = Minecraft.getMinecraft().player;
                    //BALL 1
                    double r = Math.toRadians(ticks * speed);
                    double x = distance * Math.cos(r);
                    double z = distance * Math.sin(r);

                    double spellX = player.posX + x;
                    double spellZ = player.posZ + z;
                    for (int l = 0; l < 360; l  += (35 + ThreadLocalRandom.current().nextDouble(0, 10 + 1))) {
                        double lR = Math.toRadians(l);
                        for (int h = -180; h < 180; h += (35 + ThreadLocalRandom.current().nextDouble(0, 10 + 1))) {
                            double hR = Math.toRadians(h);
                            double spellXBall = spellX + Math.cos(lR) * Math.cos(hR) * radius;
                            double spellYBall = player.posY + Math.sin(hR) * radius;
                            double spellZBall = spellZ + Math.sin(lR) * Math.cos(hR) * radius;
                            spawnParticle(particle, spellXBall, spellYBall + radius, spellZBall, 0, 0, 0);
                        }

                    }

                    if (ticks % 9 == 0) {
                        int limit = 0;
                        entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX + x - (radius), player.posY - (radius), player.posZ + z - (radius), player.posX + x + (radius), player.posY + (radius), player.posZ + z + (radius)));
                        for (EntityLivingBase entityLivingBase : entities) {
                            if (!(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable() && limit <= 5) {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propGueUltiDamages, false, 0));
                                network.sendToServer(new PaquetHealth());
                                for(int i=0;i<10;i++){
                                    spawnParticle(EnumParticleTypes.HEART, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
                                }
                            }
                            limit++;
                        }
                    }


                    //BALL 2
                    r = Math.toRadians(ticks * speed + 180);
                    x = distance * Math.cos(r);
                    z = distance * Math.sin(r);
                    spellX = player.posX + x;
                    spellZ = player.posZ + z;
                    for (int l = 0; l < 360; l += (35 + ThreadLocalRandom.current().nextDouble(0, 10 + 1))) {
                        double lR = Math.toRadians(l);
                        for (int h = -180; h < 180; h += (35 + ThreadLocalRandom.current().nextDouble(0, 10 + 1))) {
                            double hR = Math.toRadians(h);
                            double spellXBall = spellX + Math.cos(lR) * Math.cos(hR) * radius;
                            double spellYBall = player.posY + Math.sin(hR) * radius;
                            double spellZBall = spellZ + Math.sin(lR) * Math.cos(hR) * radius;
                            spawnParticle(particle, spellXBall, spellYBall + radius, spellZBall, 0, 0, 0);
                        }
                    }

                    if (ticks % 18 == 0) {
                        List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX + x - (radius), player.posY - (radius), player.posZ + z - (radius), player.posX + x + (radius), player.posY + (radius), player.posZ + z + (radius)));
                        for (EntityLivingBase entityLivingBase : entities) {
                            if (!(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable()) {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propGueUltiDamages, false, 0));
                                network.sendToServer(new PaquetHealth());
                                for(int i=0;i<10;i++){
                                    spawnParticle(EnumParticleTypes.HEART, player.posX+ ThreadLocalRandom.current().nextInt(-1, 2), player.posY + ThreadLocalRandom.current().nextInt(-1, 2), player.posZ+ ThreadLocalRandom.current().nextInt(-1, 2), 0.0D, 0.0D, 0.0D);
                                }
                            }
                        }
                    }

                } else {
                    ticks = 0;
                    isUltiGuerrier = false;
                    player = Minecraft.getMinecraft().player;
                    setUltiTimeout((int) propGueUltiCooldown);
                }
                ticks++;
            }
        }

        @SubscribeEvent
        @SideOnly(Side.CLIENT)
        public void attack(LivingAttackEvent event) {
            if (isUltiGuerrier && !Timeout.isUltiTimeout()) {
                if (event.getSource().getTrueSource() instanceof EntityPlayer) {
                    EntityPlayer entityPlayer = (EntityPlayer) event.getSource().getTrueSource();
                    if (entityPlayer.getUniqueID().equals(Minecraft.getMinecraft().player.getUniqueID())) {
                        network.sendToServer(new PaquetHealth());
                    }
                }
            }
        }
    }
}
