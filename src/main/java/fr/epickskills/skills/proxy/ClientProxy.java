package fr.epickskills.skills.proxy;

import fr.epickskills.skills.References;
import fr.epickskills.skills.entity.render.RenderHandler;
import fr.epickskills.skills.gui.GuiSkillsMonitoring;
import fr.epickskills.skills.network.PaquetSkillFireSkillUpdateServer;
import fr.epickskills.skills.network.PaquetSkillOnJoinServer;
import fr.epickskills.skills.network.PaquetSkillUpdateServer;
import fr.epickskills.skills.network.skills.PaquetResetGlowing;
import fr.epickskills.skills.particles.ParticlesHandler;
import fr.epickskills.skills.particles.particle.*;
import fr.epickskills.skills.skills.SkillHUD;
import fr.epickskills.skills.skills.Timeout;
import fr.epickskills.skills.skills.classes.Arcaniste;
import fr.epickskills.skills.skills.classes.Assassin;
import fr.epickskills.skills.skills.classes.Druide;
import fr.epickskills.skills.skills.classes.Guerrier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import static fr.epickskills.skills.Main.network;


public class ClientProxy extends ServerProxy {

    public static String playerClasse = "default";
    //            keyBindGuiMonitor;
    public static int playerSkill1 = 0, playerSkill2 = 0, playerSkill3=0, playerUlti = 0,skillsPointsForGui=0;
    private static KeyBinding keyBindskill1, keyBindskill2, keyBindskill3, keyBindulti;
    public static boolean openSkillsGui=false;
    public ClientProxy() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new Timeout());
        MinecraftForge.EVENT_BUS.register(new SkillHUD());
        MinecraftForge.EVENT_BUS.register(new Assassin.Skill1());
        MinecraftForge.EVENT_BUS.register(new Assassin.Skill3());
        MinecraftForge.EVENT_BUS.register(new Assassin.Ulti());
        MinecraftForge.EVENT_BUS.register(new Guerrier.Skill1());
        MinecraftForge.EVENT_BUS.register(new Guerrier.Skill3());
        MinecraftForge.EVENT_BUS.register(new Guerrier.Ulti());
        MinecraftForge.EVENT_BUS.register(new Druide.Skill1());
        MinecraftForge.EVENT_BUS.register(new Druide.Skill2());
        MinecraftForge.EVENT_BUS.register(new Druide.Skill3());
        MinecraftForge.EVENT_BUS.register(new Druide.Ulti());
        MinecraftForge.EVENT_BUS.register(new Arcaniste.Skill1());
        MinecraftForge.EVENT_BUS.register(new Arcaniste.Skill2());
        MinecraftForge.EVENT_BUS.register(new Arcaniste.Skill3());
        MinecraftForge.EVENT_BUS.register(new Arcaniste.Ulti());
        //SKILLS
        keyBindskill1 = new KeyBinding("Compétence 1", Keyboard.KEY_W, "Dagloth");
        keyBindskill2 = new KeyBinding("Compétence 2", Keyboard.KEY_X, "Dagloth");
        keyBindskill3 = new KeyBinding("Compétence 3", Keyboard.KEY_C, "Dagloth");
        keyBindulti = new KeyBinding("Compétence Ultime", Keyboard.KEY_V, "Dagloth");
//        keyBindGuiMonitor = new KeyBinding("Interface des Compétences", Keyboard.KEY_P, "key.categories.gameplay");

//        ClientCommandHandler.instance.registerCommand(new SkillGUI());
        //Register
        ClientRegistry.registerKeyBinding(keyBindskill2);
        ClientRegistry.registerKeyBinding(keyBindskill1);
        ClientRegistry.registerKeyBinding(keyBindskill3);
        ClientRegistry.registerKeyBinding(keyBindulti);
//        ClientRegistry.registerKeyBinding(keyBindGuiMonitor);

        RenderHandler.registerEntityRenders();
    }


    @Override
    public void register() {
        OBJLoader.INSTANCE.addDomain(References.MODID);
    }

    @SubscribeEvent

    //On press
    public void onEvent(InputEvent.KeyInputEvent event) throws InterruptedException {
        if (!Minecraft.getMinecraft().player.isRiding()) {
            //ATTENTION LES INDEX SONT DANS L'ORDRE D'AJOUT DES COMPETENCES
            if (keyBindskill1.isPressed()) {
               network.sendToServer(new PaquetSkillFireSkillUpdateServer(1));
            }

            if (keyBindskill2.isPressed()) {
                network.sendToServer(new PaquetSkillFireSkillUpdateServer(2));
            }

            if (keyBindskill3.isPressed()) {
                network.sendToServer(new PaquetSkillFireSkillUpdateServer(3));
            }

            if (keyBindulti.isPressed()) {
                network.sendToServer(new PaquetSkillFireSkillUpdateServer(4));
            }


        } else if ((keyBindskill1.isPressed() || keyBindskill2.isPressed() || keyBindskill3.isPressed() || keyBindulti.isPressed())) {
            Minecraft.getMinecraft().player.sendMessage(new TextComponentString("§4Vous ne pouvez pas utiliser vos compétences sur une monture !"));
        }

//        if (keyBindGuiMonitor.isPressed()) {
//            Minecraft.getMinecraft().displayGuiScreen(new GuiSkillsMonitoring(Minecraft.getMinecraft()));
//        }
    }


    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onEntityJoin(EntityJoinWorldEvent evt) {
        if (evt.getEntity() instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer) evt.getEntity();
            if(!entityPlayer.isCreative()){
                entityPlayer.capabilities.allowFlying = true;
                entityPlayer.capabilities.isFlying = true;
            }
            network.sendToServer(new PaquetSkillOnJoinServer());
            network.sendToServer(new PaquetSkillUpdateServer());
        }
    }

    @Override
    public void registerParticles() {
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_1_LVL1.getParticleID(), new ParticleAracSkill1Level1.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_1_LVL2.getParticleID(), new ParticleAracSkill1Level2.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_1_LVL3.getParticleID(), new ParticleAracSkill1Level3.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL1.getParticleID(), new ParticleAracSkill2Level1.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL2.getParticleID(), new ParticleAracSkill2Level2.MagicFactory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL3.getParticleID(), new ParticleAracSkill2Level3.MagicFactory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_2_LVL3_2.getParticleID(), new ParticleAracSkill2Level3_2.MagicFactory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL2.getParticleID(), new ParticleAracSkill3Level2.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL2_2.getParticleID(), new ParticleAracSkill3Level2_2.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL3.getParticleID(), new ParticleAracSkill3Level3.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_3_LVL3_2.getParticleID(), new ParticleAracSkill3Level3_2.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_ARCA_ULTI.getParticleID(), new ParticleAracSkillUlti.Factory());


        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL1.getParticleID(), new ParticleGuerSkill2Level1.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL2.getParticleID(), new ParticleGuerSkill2Level2.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_GUER_2_LVL3.getParticleID(), new ParticleGuerSkill2Level3.Factory());
        Minecraft.getMinecraft().effectRenderer.registerParticle(ParticlesHandler.CUSTOM_PORTAL_GUER_3.getParticleID(), new ParticleGuerSkill3.Factory());
    }

    private int tickInOpen = 0;
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTickPlayer(TickEvent.PlayerTickEvent event){
        if(openSkillsGui){
            tickInOpen+=1;
            if(tickInOpen==3){
                openSkillsGui=false;
                tickInOpen=0;
                Minecraft.getMinecraft().displayGuiScreen(new GuiSkillsMonitoring(Minecraft.getMinecraft()));
            }
        }
    }

}
