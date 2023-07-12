package fr.epickskills.skills.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import static fr.epickskills.skills.Main.network;

public class ServerProxy {
    public ServerProxy(){
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void register() {
    }

    public void registerParticles() {

    }

    //SKILLS REQUIRES PARTICLES

    public static Object[][] Skill1DruideParticleDico = new Object[][]{};
    public static Object[][] Skill3GuerrierParticleDico = new Object[][]{};
    public static ArrayList<String> playersSkill1Guerrier = new ArrayList<String>();

//    @SubscribeEvent
//    public void onTickServer(TickEvent.WorldTickEvent event){
//        for(Object[] particleSkill1Druide : Skill1DruideParticleDico){
//
//        }
//    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event){
        if(event.getEntity() instanceof EntityPlayerMP){
            boolean isWorth = false;
            String playerActualUUID = ((EntityPlayerMP) event.getEntity()).getUniqueID().toString();
            for(Iterator<String> it = playersSkill1Guerrier.iterator(); it.hasNext();){
                String playerUUID = it.next();
                if(playerUUID.equals(playerActualUUID)){
                    isWorth=true;
                }
            }
            if(isWorth){
                event.setCanceled(true);
                playersSkill1Guerrier.remove(playerActualUUID);
            }
        }
    }
}