package fr.epickskills.skills.skills;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class Timeout {
    //SKILL1
    public static int timeoutSkill1 = 0;
    //SKILL2
    public static int timeoutSkill2 = 0;
    //SKILL2
    public static int timeoutSkill3 = 0;
    //ULTI
    public static int timeoutUlti = 0;
    int timestampBuffer = Math.toIntExact(System.currentTimeMillis() / 1000);

    public static int getSkill1Timeout() {
        return timeoutSkill1;
    }

    public static boolean isSkill1Timeout() {
        return timeoutSkill1 > 0;
    }

    public static void setSkill1Timeout(int timeout) {
        timeoutSkill1 = timeout;
    }

    public static int getSkill2Timeout() {
        return timeoutSkill2;
    }

    public static boolean isSkill2Timeout() {
        return timeoutSkill2 > 0;
    }

    public static void setSkill2Timeout(int timeout) {
        timeoutSkill2 = timeout;
    }

    public static int getSkill3Timeout() {
        return timeoutSkill3;
    }

    public static boolean isSkill3Timeout() {
        return timeoutSkill3 > 0;
    }

    public static void setSkill3Timeout(int timeout) {
        timeoutSkill3 = timeout;
    }

    public static int getUltiTimeout() {
        return timeoutUlti;
    }

    public static boolean isUltiTimeout() {
        return timeoutUlti > 0;
    }

    public static void setUltiTimeout(int timeout) {
        timeoutUlti = timeout;
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onRenderPre(TickEvent.PlayerTickEvent event) {
        int timestamp = Math.toIntExact(System.currentTimeMillis() / 1000);
        if (timestamp != timestampBuffer) {
            if (timeoutSkill1 > 0) {
                timeoutSkill1--;
            }
            if (timeoutSkill2 > 0) {
                timeoutSkill2--;
            }
            if (timeoutSkill3 > 0) {
                timeoutSkill3--;
            }
            if (timeoutUlti > 0) {
                timeoutUlti--;
            }
            timestampBuffer = timestamp;
        }

    }
}
