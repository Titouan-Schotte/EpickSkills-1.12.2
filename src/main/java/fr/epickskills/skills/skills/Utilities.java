package fr.epickskills.skills.skills;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class Utilities {

    @SideOnly(Side.CLIENT)
    public static void spawnParticle(EnumParticleTypes particleType, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
        Minecraft.getMinecraft().world.spawnParticle(particleType, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
    }


    public static String getLoreByClass(String classe, int skill) {
        String skillDescr = "";
        switch (skill) {
            case 1:
                switch (classe) {
                    case "assassin":
                        skillDescr = "Vous propulsez plusieurs plusieurs shurikens en avant qui transpersent les ennemis.";
                        break;
                    case "arcaniste":
                        skillDescr = "Vous invoquez une tornade qui est attirée par les ennemis les plus proches.";
                        break;
                    case "druide":
                        skillDescr = "Vous invoquez une zone qui régénère vous et les autres joueurs.";
                        break;
                    case "guerrier":
                        skillDescr = "Vous sautez en l'air et infligez de lourd dégâts aux ennemis lors de votre atterissage";
                        break;

                }
                break;
            case 2:
                switch (classe) {
                    case "assassin":
                        skillDescr = "Vous vous propulsez en avant.";
                        break;
                    case "arcaniste":
                        skillDescr = "Vous invoquez une prison qui finie par exploser sur ses victimes.";
                        break;
                    case "druide":
                        skillDescr = "Vous faites apparaître une zone où les ennemis sont brulés et immobilisés.\nEn activant de nouveau la compétence, vous pouvez déplacer la terre brulée.";
                        break;
                    case "guerrier":
                        skillDescr = "Vous vous énervez et infligez de lourds dégats aux ennemis.";
                        break;

                }
                break;
            case 3:
                switch (classe) {
                    case "assassin":
                        skillDescr = "Vous étourdissez les ennemis dans un rayon donné.";
                        break;
                    case "arcaniste":
                        skillDescr = "Vous faites écraser un météor cosmique sur vos ennemis.";
                        break;
                    case "druide":
                        skillDescr = "Vous prenez une forme d'oiseau rapide et agile.";
                        break;
                    case "guerrier":
                        skillDescr = "Vous invoquez un bouclier vous rendant plus fort vous et vos compagnons.";
                        break;

                }
                break;
            case 4:
                switch (classe) {
                    case "assassin":
                        skillDescr = "Vous lancez une pluie de projectiles qui détruisent les ennemis.";
                        break;
                    case "arcaniste":
                        skillDescr = "Vos pouvoirs se démultiplient et vous lancez des éclairs de vos mains.";
                        break;
                    case "druide":
                        skillDescr = "Vous vous transformez en une bête sauvage qui ravage tout sur son passage !";
                        break;
                    case "guerrier":
                        skillDescr = "Vous faites apparaître deux défenses qui vous protègent face aux ennemis.";
                        break;

                }
                break;
        }

        return skillDescr;
    }

    public static String getNameByClass(String classe, int skill) {
        String skillName = "";
        switch (skill) {
            case 1:
                switch (classe) {
                    case "assassin":
                        skillName = "Lancer Discret";
                        break;
                    case "arcaniste":
                        skillName = "Tornade Flambante";
                        break;
                    case "druide":
                        skillName = "Zone Sainte";
                        break;
                    case "guerrier":
                        skillName = "Chute Fatale";
                        break;

                }
                break;
            case 2:
                switch (classe) {
                    case "assassin":
                        skillName = "Propulsion Assinatrice";
                        break;
                    case "arcaniste":
                        skillName = "Bombe Arcaïque";
                        break;
                    case "druide":
                        skillName = "Terre Brulée";
                        break;
                    case "guerrier":
                        skillName = "Frénésie";
                        break;

                }
                break;
            case 3:
                switch (classe) {
                    case "assassin":
                        skillName = "Percussion Corporelle";
                        break;
                    case "arcaniste":
                        skillName = "Météor Cosmique";
                        break;
                    case "druide":
                        skillName = "Forme de Voyage";
                        break;
                    case "guerrier":
                        skillName = "Bouclier du Barbare";
                        break;

                }
                break;
            case 4:
                switch (classe) {
                    case "assassin":
                        skillName = "Pluie de Minuit";
                        break;
                    case "arcaniste":
                        skillName = "Electroproduction";
                        break;
                    case "druide":
                        skillName = "Métamorphose";
                        break;
                    case "guerrier":
                        skillName = "Défenses des Anciens";
                        break;

                }
                break;
        }

        return skillName;
    }


    public static String[] getUpgradeLoreOf(int playerActualSkillLevel, int skill, String playerClasse){
        String[] toReturn = {""};
        switch(playerClasse){
            case "assassin":
                switch(skill){
                    case 1: //SKILL 1 ASSASSIN
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Dégats"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Dégats"};
                                break;
                        }
                        break;
                    case 2: //SKILL 2 ASSASSIN
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r 1 Activation"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r 1 Activation"};
                                break;
                        }
                        break;
                    case 3: //SKILL3 ASSASSIN
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                        }
                        break;
                    case 4: //ULTI ASSASSIN
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                        }
                        break;
                }
                break;
            case "arcaniste":
                switch(skill){
                    case 1: //SKILL 1 ARCANISTE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru", "§lElément différent§r"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru", "§lElément différent§r"};
                                break;
                        }
                        break;
                    case 2: //SKILL 2 ARCANISTE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Rayon de la prison", "§a§l+§r Durée", "§lElément différent§r"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Rayon de la prison", "§a§l+§r Durée", "§lElément différent§r"};
                                break;
                        }
                        break;
                    case 3: //SKILL3 ARCANISTE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Hauteur d'expulsion", "§a§l+§r Rayon accru"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Hauteur d'expulsion", "§a§l+§r Rayon accru"};
                                break;
                        }
                        break;
                    case 4: //ULTI ARCANISTE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Dégats", "§a§l+§r Durée"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Dégats", "§a§l+§r Durée"};
                                break;
                        }
                        break;
                }
                break;
            case "druide":
                switch(skill){
                    case 1: //SKILL 1 DRUIDE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                        }
                        break;
                    case 2:  //SKILL 2 DRUIDE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§a§l+§r Rayon accru"};
                                break;
                        }
                        break;
                    case 3:  //SKILL3 DRUIDE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§a§l+§r Rapidité"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§a§l+§r Rapidité"};
                                break;
                        }
                        break;
                    case 4: //ULTI DRUIDE
                        switch (playerActualSkillLevel){
                            case 1: //LEVEL 1 TO LEVEL 2
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Rapidité", "§a§l+§r Fréquence des Sauts"};
                                break;
                            case 2: //LEVEL 2 TO LEVEL 3
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Rapidité", "§a§l+§r Fréquence des Sauts"};
                                break;
                        }
                        break;
                }
                break;
            case "guerrier":
                switch(skill){
                    case 1:
                        switch (playerActualSkillLevel){
                            case 1:
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Hauteur", "§a§l+§r Explosion accrue"};
                                break;
                            case 2:
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Hauteur", "§a§l+§r Explosion accrue"};
                                break;
                        }
                        break;
                    case 2:
                        switch (playerActualSkillLevel){
                            case 1:
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée"};
                                break;
                            case 2:
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée"};
                                break;
                        }
                        break;
                    case 3:
                        switch (playerActualSkillLevel){
                            case 1:
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§4§l-§r Vie Consommée"};
                                break;
                            case 2:
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§4§l-§r Vie Consommée"};
                                break;
                        }
                        break;
                    case 4:
                        switch (playerActualSkillLevel){
                            case 1:
                                toReturn = new String[]{"§bLevel 2§r : ", "§a§l+§r Durée", "§a§l+§r Rayon Accru"};
                                break;
                            case 2:
                                toReturn = new String[]{"§bLevel 3§r : ", "§a§l+§r Durée", "§a§l+§r Rayon Accru"};
                                break;
                        }
                        break;
                }
                break;
        }
        return toReturn;
    }

    //FOR SKILLS

    public static Entity getPointedEntity() {
        Entity entity = Minecraft.getMinecraft().getRenderViewEntity();
        Entity pointedEntity = null;
        if (entity != null) {
            if (Minecraft.getMinecraft().world != null) {
                Minecraft.getMinecraft().mcProfiler.startSection("pick");
                Minecraft.getMinecraft().pointedEntity = null;
                double d0 = (double) Minecraft.getMinecraft().playerController.getBlockReachDistance() * 2;
                Minecraft.getMinecraft().objectMouseOver = entity.rayTrace(d0, 20);
                Vec3d vec3d = entity.getPositionEyes(20);
                boolean flag = false;
                int i = 3;
                double d1 = d0;
                if (Minecraft.getMinecraft().playerController.extendedReach()) {
                    //d1 = 6.0D;
                    d1 = 14.0D;
                    d0 = d1;
                } else {
                    if (d0 > 3.0D) {
                        flag = true;
                    }
                }
                if (Minecraft.getMinecraft().objectMouseOver != null) {
                    d1 = Minecraft.getMinecraft().objectMouseOver.hitVec.distanceTo(vec3d);
                }
                Vec3d vec3d1 = entity.getLook(1.0F);
                Vec3d vec3d2 = vec3d.addVector(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0);
                pointedEntity = null;
                Vec3d vec3d3 = null;
                float f = 1.0F;
                List<Entity> list = Minecraft.getMinecraft().world.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().expand(vec3d1.x * d0, vec3d1.y * d0, vec3d1.z * d0).grow(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>() {
                    public boolean apply(@Nullable Entity p_apply_1_) {
                        return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
                    }
                }));
                double d2 = d1;
                for (Entity entity1 : list) {
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow((double) entity1.getCollisionBorderSize());
                    RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(vec3d, vec3d2);
                    if (axisalignedbb.contains(vec3d)) {
                        if (d2 >= 0.0D) {
                            pointedEntity = entity1;
                            vec3d3 = raytraceresult == null ? vec3d : raytraceresult.hitVec;
                            d2 = 0.0D;
                        }
                    } else if (raytraceresult != null) {
                        double d3 = vec3d.distanceTo(raytraceresult.hitVec);
                        if (d3 < d2 || d2 == 0.0D) {
                            if (entity1.getLowestRidingEntity() == entity.getLowestRidingEntity() && !entity1.canRiderInteract()) {
                                if (d2 == 0.0D) {
                                    pointedEntity = entity1;
                                    vec3d3 = raytraceresult.hitVec;
                                }
                            } else {
                                pointedEntity = entity1;
                                vec3d3 = raytraceresult.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }
                if (pointedEntity != null && flag && vec3d.distanceTo(vec3d3) > 20D) {
                    pointedEntity = null;
                    Minecraft.getMinecraft().objectMouseOver = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing) null, new BlockPos(vec3d3));
                }
                if (pointedEntity != null && (d2 < d1 || Minecraft.getMinecraft().objectMouseOver == null)) {
                    Minecraft.getMinecraft().objectMouseOver = new RayTraceResult(pointedEntity, vec3d3);
                    if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame) {
                        Minecraft.getMinecraft().pointedEntity = pointedEntity;
                    }
                }
                Minecraft.getMinecraft().mcProfiler.endSection();
            }
        }
        return pointedEntity;
    }


            public static void LookAt(double px, double py, double pz , EntityPlayer me)
        {
            double dirx = me.posX - px;
            double diry = me.posY - py;
            double dirz = me.posZ - pz;

            double len = Math.sqrt(dirx*dirx + diry*diry + dirz*dirz);

            dirx /= len;
            diry /= len;
            dirz /= len;

            double pitch = Math.asin(diry);
            double yaw = Math.atan2(dirz, dirx);

            //en dégrés
            pitch = pitch * 180.0 / Math.PI;
            yaw = yaw * 180.0 / Math.PI;

            yaw += 90f;
            me.rotationPitch = (float)pitch;
            me.rotationYaw = (float)yaw;
        }
}
