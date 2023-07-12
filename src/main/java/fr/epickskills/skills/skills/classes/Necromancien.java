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

public class Necromancien {
    private static EntityPlayer player = Minecraft.getMinecraft().player;

    public static class Skill1 {


        public static void fire(int level) {
            if (!isSkill1Timeout()) {
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

        }

        public static void level2() {

        }

        public static void level3() {

        }
    }

    public static class Skill2 {
        public static void fire(int level) {
            if (!isSkill2Timeout()) {

            }
        }

        public static void level1() {

        }

        public static void level2() {

        }

        public static void level3() {

        }
    }

    public static class Skill3 {


        public static void fire(int level) {
            if (!isSkill3Timeout()) {
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
        }

        public static void level2() {
        }

        public static void level3() {
        }
    }

    public static class Ulti {


        public static void fire(int level) {
            if (!Timeout.isUltiTimeout()) {

            }
        }

        public static void level1() {
        }

        public static void level2() {
        }

        public static void level3() {
        }
    }
}
