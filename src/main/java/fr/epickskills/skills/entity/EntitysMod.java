package fr.epickskills.skills.entity;

import fr.epickskills.skills.Main;
import fr.epickskills.skills.References;
import fr.epickskills.skills.items.InitItems;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntitysMod {
    public static void registerEntities() {
        registerProjectile("assassin_projectile", 1, EntityAssassinItem.class, InitItems.assassin_projectile);
//        registerProjectile("basic_staff_projectile1",2, EntityBasicStaff.class, InitItems.basic_staff);
    }

    private static void registerEntity(String name, Class<? extends Entity> entity, int id, int range, int color1, int color2) {
        EntityRegistry.registerModEntity(new ResourceLocation(References.MODID + ":" + name), entity, name, id, Main.instance, range, 1, true, color1, color2);
    }

    public static void registerProjectile(String name, int id, Class<? extends Entity> entity, Item item) {
        EntityRegistry.registerModEntity(new ResourceLocation(References.MODID + ":" + name), entity, name, id, Main.instance, 64, 10, true);
    }
}
