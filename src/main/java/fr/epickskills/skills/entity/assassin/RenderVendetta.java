package fr.epickskills.skills.entity.assassin;

import fr.epickskills.skills.References;
import fr.epickskills.skills.proxy.ClientProxy;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;


@SideOnly(Side.CLIENT)
public class RenderVendetta extends RenderLivingBase {

    private static final ResourceLocation vendetta1 = new ResourceLocation(References.MODID, "textures/entity/vendetta1.png"),
            vendetta2 = new ResourceLocation(References.MODID, "textures/entity/vendetta2.png"),
            vendetta3 = new ResourceLocation(References.MODID, "textures/entity/vendetta3.png");

    public RenderVendetta(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelVendetta(0.0f, true), 0.0F);
    }

    @Nullable
    @Override
    public ResourceLocation getEntityTexture(Entity entity) {
        switch (ClientProxy.playerUlti) {
            case 2:
                return vendetta2;
            case 3:
                return vendetta3;
            default:
                return vendetta1;
        }
    }
}
