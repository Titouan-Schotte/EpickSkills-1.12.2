package fr.epickskills.skills.entity.render;

import fr.epickskills.skills.References;
import fr.epickskills.skills.entity.EntityArcanisteSkill;
import fr.epickskills.skills.entity.EntityAssassinItem;
import fr.epickskills.skills.entity.EntityAssassinSkill;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class RenderHandler {
    public static void registerEntityRenders() {
        RenderingRegistry.registerEntityRenderingHandler(EntityAssassinItem.class, renderManager -> new Render2D(renderManager, new ResourceLocation(References.MODID, "textures/items/assassin_projectile.png"), 0.25F));
        RenderingRegistry.registerEntityRenderingHandler(EntityAssassinSkill.class, renderManager -> new Render2D(renderManager, new ResourceLocation(References.MODID, "textures/items/assassin_projectile.png"), 0.25F));
        RenderingRegistry.registerEntityRenderingHandler(EntityArcanisteSkill.class, renderManager -> new Render2D(renderManager, new ResourceLocation(References.MODID, "textures/items/orb1.png"), 0.25F));
//        RenderingRegistry.registerEntityRenderingHandler(EntityFlames.class, new RenderFlames(0));
    }
    //        RenderingRegistry.registerEntityRenderingHandler(EntityAssassinItem.class, new RenderSnowball<EntityAssassinItem>(Minecraft.getMinecraft().getRenderManager(), InitItems.assassin_projectile, Minecraft.getMinecraft().getRenderItem()));
}
