package fr.epickskills.skills.particles;

import fr.epickskills.skills.Main;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;

public class ParticlesHandler {

    public static final int CATACOMBS_PORTAL_DEFAULT_ID = 1456278125;


    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_1_LVL1;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_1_LVL2;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_1_LVL3;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_2_LVL1;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_2_LVL2;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_2_LVL3;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_2_LVL3_2;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_3_LVL2;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_3_LVL2_2;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_3_LVL3;
    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_3_LVL3_2;

    public static EnumParticleTypes CUSTOM_PORTAL_ARCA_ULTI;

    public static EnumParticleTypes CUSTOM_PORTAL_GUER_2_LVL1;
    public static EnumParticleTypes CUSTOM_PORTAL_GUER_2_LVL2;
    public static EnumParticleTypes CUSTOM_PORTAL_GUER_2_LVL3;
    public static EnumParticleTypes CUSTOM_PORTAL_GUER_3;

    public static void registration() {
        Class<?>[] particlesParams = {
                String.class, int.class, boolean.class
        };


        CUSTOM_PORTAL_ARCA_1_LVL1 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_1_LVL1", particlesParams, "customPortalArca1Lvl1", 1456278125, false);
        CUSTOM_PORTAL_ARCA_1_LVL2 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_1_LVL2", particlesParams, "customPortalArca1Lvl2", 1456278126, false);
        CUSTOM_PORTAL_ARCA_1_LVL3 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_1_LVL3", particlesParams, "customPortalArca1Lvl3", 1456278127, false);
        CUSTOM_PORTAL_ARCA_2_LVL1 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_2_LVL1", particlesParams, "customPortalArca2Lvl1", 1456278128, false);
        CUSTOM_PORTAL_ARCA_2_LVL2 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_2_LVL2", particlesParams, "customPortalArca2Lvl2", 1456278129, false);
        CUSTOM_PORTAL_ARCA_2_LVL3 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_2_LVL3", particlesParams, "customPortalArca2Lvl3", 1456278130, false);
        CUSTOM_PORTAL_ARCA_2_LVL3_2 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_2_LVL3_2", particlesParams, "customPortalArca2Lvl3_2", 1456278741, false);
        CUSTOM_PORTAL_ARCA_3_LVL2 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_3_LVL2", particlesParams, "customPortalArca2Lvl2", 1456274741, false);
        CUSTOM_PORTAL_ARCA_3_LVL2_2 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_3_LVL2_2", particlesParams, "customPortalArca2Lvl2_2", 1456274742, false);
        CUSTOM_PORTAL_ARCA_3_LVL3 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_3_LVL3", particlesParams, "customPortalArca2Lvl3", 1456274743, false);
        CUSTOM_PORTAL_ARCA_3_LVL3_2 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_3_LVL3_2", particlesParams, "customPortalArca2Lvl3_2", 1456274746, false);
        CUSTOM_PORTAL_ARCA_ULTI = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_ARCA_ULTI", particlesParams, "customPortalArcaUlti", 1456278131, false);

        CUSTOM_PORTAL_GUER_2_LVL1 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_GUER_2_LVL1", particlesParams, "customPortalGuer2Lvl1", 1456278132, false);
        CUSTOM_PORTAL_GUER_2_LVL2 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_GUER_2_LVL2", particlesParams, "customPortalGuer2Lvl2", 1456278133, false);
        CUSTOM_PORTAL_GUER_2_LVL3 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_GUER_2_LVL3", particlesParams, "customPortalGuer2Lvl3", 1456278134, false);
        CUSTOM_PORTAL_GUER_3 = EnumHelper.addEnum(EnumParticleTypes.class, "CUSTOM_PORTAL_GUER_3", particlesParams, "customPortalGuer3", 1456278135, false);

        Main.proxy.registerParticles();
    }
}