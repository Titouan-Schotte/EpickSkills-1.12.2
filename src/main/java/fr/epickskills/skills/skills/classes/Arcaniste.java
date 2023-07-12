package fr.epickskills.skills.skills.classes;

import fr.epickskills.skills.entity.EntityArcanisteSkill;
import fr.epickskills.skills.network.skills.*;
import fr.epickskills.skills.particles.ParticlesHandler;
import fr.epickskills.skills.skills.Timeout;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.json.SkillVariables.*;
import static fr.epickskills.skills.skills.Timeout.*;
import static fr.epickskills.skills.skills.Utilities.getPointedEntity;
import static fr.epickskills.skills.skills.Utilities.spawnParticle;
import static fr.epickskills.skills.skills.classes.Arcaniste.Skill1.isSkill1Arcaniste;
import static fr.epickskills.skills.skills.classes.Arcaniste.Skill1.sqr;
import static fr.epickskills.skills.skills.classes.Arcaniste.Skill2.isSkill2Arcaniste;
import static fr.epickskills.skills.skills.classes.Arcaniste.Skill3.isSkill3Arcaniste;
import static fr.epickskills.skills.skills.classes.Arcaniste.Ulti.isUltiArcaniste;
import static fr.epickskills.skills.skills.classes.Druide.Skill3.isSkill3Druide;

public class Arcaniste {
    private static EntityPlayer player = Minecraft.getMinecraft().player;

    public static class Skill1 {
        public static boolean isSkill1Arcaniste = false, isLevel2, isLevel3;
        private static double posXSpell, posYSpell, posZSpell;
        private static EnumParticleTypes particle1, particle2;
        private static int ticks, timestampStart, radiusActivation, radiusTornado;
        private static List<EntityLivingBase> entities = null;

        public static void fire(int level) {
            if (!Timeout.isSkill1Timeout() && !isSkill2Arcaniste && !isUltiArcaniste && !isSkill1Arcaniste) {
                radiusActivation = (int) propArcSkill1RadiusActivation;
                radiusTornado = (int) propArcSkill1RadiusTornado;
                if(!isSkill1Arcaniste){
                    ticks = 0;
                }
                timestampStart=Math.toIntExact(System.currentTimeMillis() / 1000)+(int) propArcSkill1Activation;
                player = Minecraft.getMinecraft().player;

                entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - (radiusActivation), player.posY - (radiusActivation), player.posZ - (radiusActivation), player.posX + (radiusActivation), player.posY + (radiusActivation), player.posZ + (radiusActivation)));
                if (entities.size() > 1) {
                    if (level == 1) {
                        level1();
                    } else if (level == 2) {
                        level2();
                    } else {
                        level3();
                    }

                    //GET POINTED
                    Entity entity = getPointedEntity();
                    if (entity != null) {
                        posXSpell = entity.posX;
                        posYSpell = entity.posY;
                        posZSpell = entity.posZ;
                        isSkill1Arcaniste = true;
                        return;
                    }

                    //GET COORD POINTED
                    BlockPos lastPosition = player.rayTrace(100, 1.0F).getBlockPos();
                    int xLook=lastPosition.getX();
                    int yLook=lastPosition.getY();
                    int zLook=lastPosition.getZ();
                    entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(xLook - 3, yLook -  3, zLook - 3, xLook + 3, yLook + 3, zLook + 3));
                    int closest = Integer.MAX_VALUE;
                    EntityLivingBase thisOne=null;
                    for (EntityLivingBase entityLivingBase : entities) {
                        if (!(entityLivingBase instanceof EntityPlayer) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable() &&  Math.sqrt(sqr(entityLivingBase.posZ - zLook) + sqr(entityLivingBase.posX - xLook))<closest) {
                            thisOne = entityLivingBase;
                            closest = (int) Math.sqrt((entityLivingBase.posZ-zLook)+(entityLivingBase.posX-xLook));
                        }
                    }
                    if(thisOne != null){
                            if(thisOne.isEntityAlive() && thisOne.attackable()){
                                posXSpell = thisOne.posX;
                                posYSpell = thisOne.posY;
                                posZSpell = thisOne.posZ;
                                isSkill1Arcaniste = true;
                            } else {
                                player.sendMessage(new TextComponentString("§4Quel carnage vous avez tué tous les ennemis autour de vous !"));

                            }
                        return;

                    }
                }
                player.sendMessage(new TextComponentString("§4Vous êtes trop loin des ennemis."));
            }

        }

        public static void level1() {
            particle1 = ParticlesHandler.CUSTOM_PORTAL_ARCA_1_LVL1;
            particle2 = EnumParticleTypes.SPELL_WITCH;
        }

        public static void level2() {
            particle1 = ParticlesHandler.CUSTOM_PORTAL_ARCA_1_LVL2;
            particle2 = EnumParticleTypes.FLAME;
            isLevel2 = true;
        }

        public static void level3() {
            particle1 = EnumParticleTypes.SPELL;
            particle2 = ParticlesHandler.CUSTOM_PORTAL_ARCA_1_LVL3;
            isLevel3 = true;

        }

        static public double sqr(double a) {
            return a*a;
        }
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill1Arcaniste && !Timeout.isSkill1Timeout()) {
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    isSkill1Arcaniste = false;
                    isLevel2 = false;
                    isLevel3 = false;
                    ticks = 0;
                    player = Minecraft.getMinecraft().player;
                    Timeout.setSkill1Timeout((int) propArcSkill1Cooldown);
                    return;
                }
                entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(posXSpell - (radiusTornado), posYSpell - (radiusTornado), posZSpell - (radiusTornado), posXSpell + (radiusTornado), posYSpell + (radiusTornado), posZSpell + (radiusTornado)));
                boolean target = false;
                if (ticks % 15 == 0) {
                    int limit = 0;
                    for (Entity entityLivingBase : entities) {
                        if (!(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive() && limit <= 3) {
                            if (!target) {
                                posXSpell = entityLivingBase.posX;
                                posYSpell = entityLivingBase.posY;
                                posZSpell = entityLivingBase.posZ;
                                target = true;
                            }
                            if (isLevel2) {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcSkill1Damages, true, 2));
                            } else if (isLevel3) {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcSkill1Damages, false, 0, true, 2));
                            } else {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcSkill1Damages));
                            }

                        }
                        limit++;
                    }
                    for (int d = 0; d < 3000; d++) {
                        double r = Math.toRadians(d + ticks);
                        double x = r * Math.cos(r);
                        double z = r * Math.sin(r);
                        double playX = posXSpell + x / 15;
                        double playY = posYSpell + d / 360;
                        double playZ = posZSpell + z / 15;
                        int rate = 6;
                        if (isLevel2) {
                            rate = 8;
                        }
                        if (d % rate == 0) {
                            if (d % (rate * 2) == 0) {
                                spawnParticle(particle1, playX, playY + ThreadLocalRandom.current().nextDouble(0, 0.5 + 1), playZ, 0, 0, 0);
                            } else {
                                spawnParticle(particle2, playX, playY, playZ, 0, 0, 0);
                            }
                        }
                    }

                }
                ticks++;
            }
        }
    }

    public static class Skill2 {

        public static boolean isSkill2Arcaniste = false;
        private static double spellX, spellY, spellZ;
        private static double rayon=2, rayonActivation=10;
        private static int ticks=0,timestampStart=10;
        private static double damages=10;
        private static int levelIn=0;
        private static EntityPlayer player;
        private static EnumParticleTypes particle, particle2;

        public static void fire(int level) {
            if (!Timeout.isSkill2Timeout() && !isSkill1Arcaniste && !isUltiArcaniste && !isSkill2Arcaniste) {
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                timestampStart=Math.toIntExact(System.currentTimeMillis()/1000)+(int)propArcSkill2Activation;
                damages=propArcSkill2Damages;
                rayon=propArcSkill2Radius;
                rayonActivation=propArcSkill2RadiusActivation;
                levelIn=level;
                player = Minecraft.getMinecraft().player;
                isSkill2Arcaniste=true;
                ticks = 0;
                List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - (rayonActivation), player.posY - (rayonActivation), player.posZ - (rayonActivation), player.posX + (rayonActivation), player.posY + (rayonActivation), player.posZ + (rayonActivation)));
                if (entities.size() > 1) {
                    if (level == 1) {
                        level1();
                    } else if (level == 2) {
                        level2();
                    } else {
                        level3();
                    }


                    Entity entity = getPointedEntity();
                    if (entity != null) {
                        spellX = entity.posX;
                        spellY = entity.posY;
                        spellZ = entity.posZ;
                        isSkill2Arcaniste = true;
                        return;
                    }

                    //GET COORD POINTED
                    BlockPos lastPosition = player.rayTrace(100, 1.0F).getBlockPos();
                    int xLook=lastPosition.getX();
                    int yLook=lastPosition.getY();
                    int zLook=lastPosition.getZ();
                    entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(xLook - 3, yLook -  3, zLook - 3, xLook + 3, yLook + 3, zLook + 3));
                    int closest = Integer.MAX_VALUE;
                    EntityLivingBase thisOne=null;
                    for (EntityLivingBase entityLivingBase : entities) {
                        if (!(entityLivingBase instanceof EntityPlayer) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable() &&  Math.sqrt(sqr(entityLivingBase.posZ - zLook) + sqr(entityLivingBase.posX - xLook))<closest) {
                            thisOne = entityLivingBase;
                            closest = (int) Math.sqrt((entityLivingBase.posZ-zLook)+(entityLivingBase.posX-xLook));
                        }
                    }
                    if(thisOne != null){
                        if(thisOne.isEntityAlive() && thisOne.attackable()){
                            spellX = thisOne.posX;
                            spellY = thisOne.posY;
                            spellZ = thisOne.posZ;
                            isSkill1Arcaniste = true;
                        } else {
                            player.sendMessage(new TextComponentString("§4Quel carnage vous avez tué tous les ennemis autour de vous !"));

                        }
                        return;

                    }
                }
                spellX = player.posX;
                spellY = player.posY;
                spellZ = player.posZ;
            }
        }

        public static void level1() {
            particle = ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL1;
            particle2 = ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL2;
        }

        public static void level2() {
            particle = ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL2;
            particle2 = ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL1;
        }

        public static void level3() {
            particle = ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL3;
            particle2 = ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL3_2;
        }
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill2Arcaniste) {
                player = Minecraft.getMinecraft().player;
                //ZONE SPELL 1
                for(int d=0;d<360;d+=+ThreadLocalRandom.current().nextDouble(0, 30 + 1)){

                    double r=Math.toRadians(d);
                    double x=rayon * Math.cos(r);
                    double z=rayon * Math.sin(r);
                    double npcx=spellX+x;
                    double npcy=spellY;
                    double npcz=spellZ+z;
                    for(int h=0;h<5;h+=1){
                        spawnParticle(particle,npcx,npcy+ThreadLocalRandom.current().nextDouble(0, 4 + 1),npcz,0,0,0);
                        spawnParticle(particle2,npcx,npcy+ThreadLocalRandom.current().nextDouble(0, 4 + 1),npcz,0,0,0);
                    }
                }
                if (ticks % 18 == 0) {
                    List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(spellX - (rayon*0.7), spellY - (rayon), spellZ - (rayon*0.7), spellX + (rayon*0.7), spellY + (rayon), spellZ + (rayon*0.7)));
                    for (EntityLivingBase entityLivingBase : entities) {
                        if (!(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable()) {
                            if(levelIn==1){
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(),0,false,0,false,0,true));
                            } else if(levelIn==2) {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(),0,true,2,false,0,true));
                            } else {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(),0,false,0,false,0,true, true, 2));
                            }
                        }
                    }
                }


                //END
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(spellX - (rayon), spellY - (rayon), spellZ - (rayon), spellX + (rayon), spellY + (rayon), spellZ + (rayon)));
                    for (EntityLivingBase entityLivingBase : entities) {
                        if (!(entityLivingBase instanceof EntityPlayer) && entityLivingBase.isEntityAlive() && entityLivingBase.attackable()) {
                            network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcSkill2Damages));
                            spawnParticle(EnumParticleTypes.EXPLOSION_HUGE,entityLivingBase.posX,entityLivingBase.posY,entityLivingBase.posZ,0,0,0);
                        }
                    }
                    setSkill2Timeout((int)propArcSkill2Cooldown);
                    isSkill2Arcaniste=false;
                    ticks = 0;
                    return;
                }
                ticks++;
            }
        }

    }

    public static class Skill3 {
        private static EnumParticleTypes particle, particle2;
        public static float x1,y1,z1,x2,y2,z2,yTarget;
        private static int ticks;
        private static int levelIn;
        private static double radius;
        public static boolean isSkill3Arcaniste=false;
        public static void fire(int level) {
            if(!isSkill3Arcaniste && !isSkill3Timeout() && !isUltiArcaniste && !isSkill1Arcaniste && !isSkill2Arcaniste){
                levelIn=level;
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                player=Minecraft.getMinecraft().player;
                BlockPos lastPosition = player.rayTrace(100, 1.0F).getBlockPos();
                System.out.println(lastPosition.getX());
                x1=(float) player.posX;
                y1=(float) player.posY+7;
                z1=(float) player.posZ;
                x2=lastPosition.getX();
                y2=lastPosition.getY()-1;
                z2=lastPosition.getZ();
                yTarget=y1;
                ticks=0;
                radius=propArcSkill3Radius;
                isSkill3Arcaniste=true;
            }
        }
        public static void level1() {
            particle = EnumParticleTypes.FLAME;
            particle2 = ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL1;
        }

        public static void level2() {
            particle = ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL3;
            particle2 = ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL3_2;
        }

        public static void level3() {
            particle = ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL2;
            particle2 = ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL2_2;
        }
        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onRenderPre(TickEvent.PlayerTickEvent event) {
            if (isSkill3Arcaniste) {
                if (ticks < 20 * 2) {//LOADING
                    for (int l = 0; l < 360; l += (30 + ThreadLocalRandom.current().nextDouble(0, 10 + 1))) {
                        double lR = Math.toRadians(l);
                        for (int h = -180; h < 180; h += (30 + ThreadLocalRandom.current().nextDouble(0, 10 + 1))) {
                            double hR = Math.toRadians(h);
                            double spellXBall = x1 + Math.cos(lR) * Math.cos(hR) * radius;
                            double spellYBall = y1 + Math.sin(hR) * radius;
                            double spellZBall = z1 + Math.sin(lR) * Math.cos(hR) * radius;
                            spawnParticle(particle, spellXBall, spellYBall + radius, spellZBall, 0, 0, 0);
                        }
                    }
                    previewPathMeteor();
                    ticks++;
                } else if(yTarget>=y2) {//PUSH

                    float x=(x2-x1)/(y2-y1)*yTarget+x1-(x2-x1)/(y2-y1)*y1;
                    float z=(z2-z1)/(y2-y1)*yTarget+z1-(z2-z1)/(y2-y1)*y1;
                    for (int l = 0; l < 360; l += (30 + ThreadLocalRandom.current().nextDouble(0, 5 + 1))) {
                        double lR = Math.toRadians(l);
                        for (int h = -180; h < 180; h += (30 + ThreadLocalRandom.current().nextDouble(0, 10 + 1))) {
                            double hR = Math.toRadians(h);
                            double spellXBall = x + Math.cos(lR) * Math.cos(hR) * radius;
                            double spellYBall = yTarget + Math.sin(hR) * radius;
                            double spellZBall = z + Math.sin(lR) * Math.cos(hR) * radius;
                            spawnParticle(particle, spellXBall+ ThreadLocalRandom.current().nextDouble(0, 0.4 + 1), spellYBall+ ThreadLocalRandom.current().nextDouble(0, 0.4 + 1) + radius, spellZBall+ ThreadLocalRandom.current().nextDouble(0, 0.4 + 1), 0, 0, 0);
                            spawnParticle(particle2, spellXBall+ ThreadLocalRandom.current().nextDouble(0, 0.4 + 1), spellYBall+ ThreadLocalRandom.current().nextDouble(0, 0.4 + 1) + radius, spellZBall+ ThreadLocalRandom.current().nextDouble(0, 0.4 + 1), 0, 0, 0);
                            if(h%40==0){
                                spawnParticle(EnumParticleTypes.CLOUD, spellXBall+ ThreadLocalRandom.current().nextDouble(0, 0.7 + 1), spellYBall+ ThreadLocalRandom.current().nextDouble(0, 0.7 + 1) + radius, spellZBall+ ThreadLocalRandom.current().nextDouble(0, 0.7 + 1), 0, 0, 0);
                            }
                        }
                    }
                    previewPathMeteor();
                    yTarget-=0.4;
                } else {//EXPLOSION
                    for (int d = 0; d < 300; d+=ThreadLocalRandom.current().nextDouble(0, 0.8+1)) {
                        double r = Math.toRadians(d);
                        double x = r * Math.cos(r);
                        double z = r * Math.sin(r);
                        double playX = x2 + x / 10;
                        double playY = y2+1 + d / 700;
                        double playZ = z2 + z / 10;
                        spawnParticle(EnumParticleTypes.LAVA, playX, playY, playZ, 0, 0, 0);
                    }
                    spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, x2, y2+1, z2, 10, 10, 10);
                    List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(x2 - (radius+2), y2 - (radius+7), z2 - (radius+2), x2 + (radius+2), y2 + (radius+7), z2 + (radius+2)));
                    for (Entity entityLivingBase : entities) {
                        if (!(entityLivingBase instanceof EntityPlayer) && !(entityLivingBase instanceof EntityArmorStand) && entityLivingBase.isEntityAlive()) {
                            if(levelIn==3){
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcSkill3Damages, (int) propArcSkill3MotionY, true, (int) propArcSkill3Fire));
                            } else if(levelIn==2){
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcSkill3Damages, (int) propArcSkill3MotionY, true, (int) propArcSkill3Fire));
                            } else {
                                network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcSkill3Damages, (int) propArcSkill3MotionY, true, (int) propArcSkill3Fire));
                            }

                            spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, 0,0,0);
                        }
                    }
                    ticks=0;
                    isSkill3Arcaniste=false;
                    setSkill3Timeout((int) propArcSkill3Cooldown);
                }
            }
        }


        public void previewPathMeteor(){

            for(float y=y1;y>=y2;y-=0.4){
//                float x=(x2-x1)/(z2-z1)*z+x1-(x2-x1)/(z2-z1)*z1;
//                float y=(y2-y1)/(z2-z1)*z+y1-(y2-y1)/(z2-z1)*z1;

                float x=(x2-x1)/(y2-y1)*y+x1-(x2-x1)/(y2-y1)*y1;
                float z=(z2-z1)/(y2-y1)*y+z1-(z2-z1)/(y2-y1)*y1;
                spawnParticle(EnumParticleTypes.CLOUD, x, y, z, 0,0,0);
                spawnParticle(particle2, x, y, z, 0,0,0);
            }
        }

    }

    public static class Ulti {
        public static boolean isUltiArcaniste = false, prevIsFly, prevAllowFlying;
        public static double prevCoordY;
        private static int ticks = 0, timestampStart = 10, radius = 7, tempPrevThirdView = 0;
        private static float prevFlySpeed;

        public static void fire(int level) {
            if (!Timeout.isUltiTimeout() && !isUltiArcaniste && !isSkill1Arcaniste && !isSkill2Arcaniste && !isSkill3Arcaniste) {
                timestampStart = Math.toIntExact(System.currentTimeMillis() / 1000)+ (int) propArcUltiActivation;
                radius = (int) propArcUltiRadius;
                if (level == 1) {
                    level1();
                } else if (level == 2) {
                    level2();
                } else {
                    level3();
                }
                    player = Minecraft.getMinecraft().player;
                    tempPrevThirdView = Minecraft.getMinecraft().gameSettings.thirdPersonView;
                    Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
                    player.setPosition(player.posX, player.posY + 1, player.posZ);
                    prevCoordY = player.posY;
                    isUltiArcaniste = true;
                    prevFlySpeed = player.capabilities.getFlySpeed();
                    prevIsFly = player.capabilities.isFlying;
                    prevAllowFlying = player.capabilities.allowFlying;
                    player.capabilities.setFlySpeed(0.04F);
                    player.capabilities.allowFlying = true;
                    player.capabilities.isFlying = true;
                    network.sendToServer(new PaquetArcanisteUltiPotion((int) propArcUltiActivation, level));

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
            if (isUltiArcaniste) {
                //PARTICLES UNDER PLAYER
                for (int i : new int[2]) {
                    spawnParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_ULTI, player.posX + ThreadLocalRandom.current().nextDouble(-1.5, 0.5 + 1), player.posY + ThreadLocalRandom.current().nextDouble(-1.3, 0.5 + 1), player.posZ + ThreadLocalRandom.current().nextDouble(-1.5, 0.5 + 1), 0, 0, 0);
                    spawnParticle(EnumParticleTypes.CLOUD, player.posX + ThreadLocalRandom.current().nextDouble(-1.5, 0.5 + 1), player.posY + ThreadLocalRandom.current().nextDouble(-1, 0.5 + 1), player.posZ + ThreadLocalRandom.current().nextDouble(-1.5, 0.5 + 1), 0, 0, 0);
                }

                Minecraft.getMinecraft().gameSettings.thirdPersonView = 1;
                if (player.posY != prevCoordY) {
                    player.posY = prevCoordY;
                }
                player.motionY = 0;
                if(timestampStart<=Math.toIntExact(System.currentTimeMillis() / 1000)){
                    isUltiArcaniste = false;
                    ticks = 0;
                    Minecraft.getMinecraft().gameSettings.thirdPersonView = tempPrevThirdView;
                    player.posY = prevCoordY;
                    player.capabilities.setFlySpeed(prevFlySpeed);
                    player.capabilities.allowFlying = prevAllowFlying;
                    player.capabilities.isFlying = prevIsFly;
                    setUltiTimeout((int) propArcUltiCooldown);
                    return;
                }

                    if (ticks % 25 == 0) {
                        try {
                            EntityLivingBase pointedEntity = (EntityLivingBase) getPointedEntity();
                            if (pointedEntity != null) {
                                if (pointedEntity.isEntityAlive() && pointedEntity.attackable()) {
                                    network.sendToServer(new PaquetDamageEntity(pointedEntity.getEntityId(), (int) propArcUltiDamagesPointed));
                                    Minecraft.getMinecraft().world.spawnEntity(new EntityLightningBolt(Minecraft.getMinecraft().world, pointedEntity.posX, pointedEntity.posY, pointedEntity.posZ, false));
                                }

                            }
                        } catch (Exception e) {
                            return;
                        }

                    }
                    if (ticks % 40 == 0) {
                        List<EntityLivingBase> entities = Minecraft.getMinecraft().player.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(player.posX - (radius), player.posY - (radius), player.posZ - (radius), player.posX + (radius), player.posY + (radius), player.posZ + (radius)));
                        int limit = 0;
                        for (EntityLivingBase entityLivingBase : entities) {
                            if (limit == 5) {
                                break;
                            }
                            limit++;

                            if (!(entityLivingBase instanceof EntityPlayer)) {
                                if (entityLivingBase != null) {
                                    if (entityLivingBase.isEntityAlive() && entityLivingBase.attackable()) {
                                        network.sendToServer(new PaquetDamageEntity(entityLivingBase.getEntityId(), (int) propArcUltiDamagesTicks));
                                        Minecraft.getMinecraft().world.spawnEntity(new EntityLightningBolt(Minecraft.getMinecraft().world, entityLivingBase.posX, entityLivingBase.posY, entityLivingBase.posZ, false));
                                    }
                                }
                            }
                        }
                    }

                ticks++;
            }

//            if((isUltiArcaniste && !isSkill3Druide && !player.isCreative())){
//                if(player.capabilities.isFlying){
//                    player.capabilities.isFlying=false;
//                    player.capabilities.allowFlying=false;
//                }
//            }
            if((player.getRidingEntity() == null || (!isUltiArcaniste && !isSkill3Druide))  && !player.isCreative() && !player.isSpectator()){
                player.capabilities.isFlying=false;
                player.capabilities.allowFlying=false;
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent(priority = EventPriority.NORMAL)
        public void onSound(PlaySoundEvent event) {
            if (event.getName().equals("entity.lightning.impact") || event.getName().equals("entity.lightning.thunder")) {
                event.setResultSound(null);
            }
        }
    }

}
