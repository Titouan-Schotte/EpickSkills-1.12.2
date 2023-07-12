package fr.epickskills.skills.entity.druide;


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
public class RenderWereWolf extends RenderLivingBase {

    private static final ResourceLocation rl1 = new ResourceLocation(References.MODID, "textures/entity/wolfbrown.png"),
            rl2 = new ResourceLocation(References.MODID, "textures/entity/wolfblack.png"),
            rl3 = new ResourceLocation(References.MODID, "textures/entity/wolftimber.png");

    public RenderWereWolf(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelWereWolf(), 0.0F);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        switch (ClientProxy.playerUlti) {
            case 2:
                return rl2;
            case 3:
                return rl3;
            default:
                return rl1;
        }
    }


}
