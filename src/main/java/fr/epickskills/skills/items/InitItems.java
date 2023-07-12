package fr.epickskills.skills.items;

import fr.epickskills.skills.References;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = References.MODID)
public class InitItems {

    public static Item icon_epicka;
    public static Item saphire;
    public static Item assassin_projectile;
    public static Item arcaniste_skill2;

    //CLASS ITEMS

    //ARCANISTE STAFF
//    public static Item basic_staff;

    public static void init() {
        //Special
        icon_epicka = new ItemMod("icon_epicka");
        //ITEMS
        saphire = new ItemMod("saphire");
        assassin_projectile = new ItemShotProjectile("assassin_projectile_item");
        arcaniste_skill2 = new ItemShotProjectile("arcaniste_skill2_item");

//        basic_staff = new ItemStaff("basic_staff",10,10, EnumParticleTypes.PORTAL, EnumParticleTypes.EXPLOSION_NORMAL);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                icon_epicka,
                saphire,
                assassin_projectile,
//                basic_staff,
                arcaniste_skill2

        );

    }

    @SubscribeEvent
    public static void registerRenders(ModelRegistryEvent event) {
        registerRender(icon_epicka);
        registerRender(saphire);
        registerRender(assassin_projectile);
//        registerRender(basic_staff);
        registerRender(arcaniste_skill2);
    }

    private static void registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
    }

}
