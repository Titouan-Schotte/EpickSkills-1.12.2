package fr.epickskills.skills.entity.druide;


import fr.epickskills.skills.References;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@SideOnly(Side.CLIENT)
public class RenderFox extends RenderLivingBase {
    private static final ResourceLocation FOX_TEXTURES = new ResourceLocation(References.MODID + ":textures/entity/fox.png");

    public RenderFox(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelFox(), 0.5F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        return FOX_TEXTURES;
    }

}
