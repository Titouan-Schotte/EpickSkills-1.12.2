package fr.epickskills.skills;

import fr.epickskills.skills.command.*;
import fr.epickskills.skills.entity.EntitysMod;
import fr.epickskills.skills.items.EpickaItemTab;
import fr.epickskills.skills.items.InitItems;
import fr.epickskills.skills.network.*;
import fr.epickskills.skills.network.json.PaquetGetSkillsPropertiesClient;
import fr.epickskills.skills.network.json.PaquetGetSkillsPropertiesServer;
import fr.epickskills.skills.network.particles.PaquetDruideSkill1Client;
import fr.epickskills.skills.network.particles.PaquetGuerrierSkill3Client;
import fr.epickskills.skills.network.skills.*;
import fr.epickskills.skills.particles.ParticlesHandler;
import fr.epickskills.skills.proxy.ServerProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = References.MODID, name = References.NAME, version = References.VERSION, acceptedMinecraftVersions = References.MINECRAFT_VERSION)
public class Main {
    public static final CreativeTabs creativeTab = new EpickaItemTab("epickskill");
    @Mod.Instance
    public static Main instance;
    @SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY, modId = References.MODID)
    public static ServerProxy proxy;
    public static SimpleNetworkWrapper network;

    static {
        FluidRegistry.enableUniversalBucket();
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        network = NetworkRegistry.INSTANCE.newSimpleChannel("ChannelSkill");

        //GLOBAL
        network.registerMessage(PaquetSkillOnJoinServer.Handler.class, PaquetSkillOnJoinServer.class , 1, Side.SERVER);
        network.registerMessage(PaquetSkillFireSkillUpdateServer.Handler.class, PaquetSkillFireSkillUpdateServer.class, 2, Side.SERVER);
        network.registerMessage(PaquetSkillFireSkillUpdateClient.Handler.class, PaquetSkillFireSkillUpdateClient.class, 3, Side.CLIENT);
        network.registerMessage(PaquetGetSkillsPropertiesServer.Handler.class, PaquetGetSkillsPropertiesServer.class, 4, Side.SERVER);
        network.registerMessage(PaquetGetSkillsPropertiesClient.Handler.class, PaquetGetSkillsPropertiesClient.class, 5, Side.CLIENT);
        network.registerMessage(PaquetSkillUpdateServer.Handler.class, PaquetSkillUpdateServer.class, 6, Side.SERVER);
        network.registerMessage(PaquetSkillUpdateClient.Handler.class, PaquetSkillUpdateClient.class, 7, Side.CLIENT);

        //SKILLS
        network.registerMessage(PaquetAssassinUlti.Handler.class, PaquetAssassinUlti.class, 8, Side.SERVER);
        network.registerMessage(PaquetAssassinUltiEnd.Handler.class, PaquetAssassinUltiEnd.class, 16, Side.SERVER);
        network.registerMessage(PaquetResetPotion.Handler.class, PaquetResetPotion.class, 9, Side.SERVER);
        network.registerMessage(PaquetGuerrierSkill2.Handler.class, PaquetGuerrierSkill2.class, 10, Side.SERVER);
        network.registerMessage(PaquetGuerrierUlti.Handler.class, PaquetGuerrierUlti.class, 11, Side.SERVER);
        network.registerMessage(PaquetDruideSkill1.Handler.class, PaquetDruideSkill1.class, 12, Side.SERVER);
        network.registerMessage(PaquetDruideSkill2.Handler.class, PaquetDruideSkill2.class, 13, Side.SERVER);
        network.registerMessage(PaquetDruideUlti.Handler.class, PaquetDruideUlti.class, 14, Side.SERVER);
        network.registerMessage(PaquetDamageEntity.Handler.class, PaquetDamageEntity.class, 15, Side.SERVER);
        network.registerMessage(PaquetGuerrierSkill3.Handler.class, PaquetGuerrierSkill3.class, 16, Side.SERVER);
        network.registerMessage(PaquetDruideSkill3.Handler.class, PaquetDruideSkill3.class, 17, Side.SERVER);
        network.registerMessage(PaquetAssassinSkill3.Handler.class, PaquetAssassinSkill3.class, 18, Side.SERVER);
        network.registerMessage(PaquetGuerrierSkill3Spell.Handler.class, PaquetGuerrierSkill3Spell.class, 19, Side.SERVER);
        network.registerMessage(PaquetGuerrierSkill1.Handler.class, PaquetGuerrierSkill1.class, 20, Side.SERVER);
        network.registerMessage(PaquetArcanisteUltiPotion.Handler.class, PaquetArcanisteUltiPotion.class, 21, Side.SERVER);
        network.registerMessage(PaquetResetGlowing.Handler.class, PaquetResetGlowing.class, 22, Side.SERVER);
        network.registerMessage(PaquetSetGlowing.Handler.class, PaquetSetGlowing.class, 23, Side.SERVER);
        network.registerMessage(PaquetHealth.Handler.class, PaquetHealth.class, 24, Side.SERVER);
        network.registerMessage(PaquetGuerrierSkill3Client.Handler.class, PaquetGuerrierSkill3Client.class, 25, Side.CLIENT);
        network.registerMessage(PaquetDruideSkill1Client.Handler.class, PaquetDruideSkill1Client.class, 26, Side.CLIENT);
        //GUI
        network.registerMessage(PaquetSkillsGui.Handler.class, PaquetSkillsGui.class, 27, Side.CLIENT);
        network.registerMessage(PaquetSkillsGuiUpgradeServer.Handler.class, PaquetSkillsGuiUpgradeServer.class, 28, Side.SERVER);


        InitItems.init();
        EntitysMod.registerEntities();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.register();
        ParticlesHandler.registration();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }

    @EventHandler
    public void serverInit(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSkillManagement());
        event.registerServerCommand(new CommandClasseManagement());
        event.registerServerCommand(new CommandSkillPointsManagement());
        event.registerServerCommand(new CommandSkillsGui());
        event.registerServerCommand(new CommandReload());
    }
}