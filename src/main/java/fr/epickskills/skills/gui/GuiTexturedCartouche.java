package fr.epickskills.skills.gui;

import fr.epickskills.skills.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.ParametersAreNonnullByDefault;

import static fr.epickskills.skills.proxy.ClientProxy.skillsPointsForGui;

public class GuiTexturedCartouche extends GuiButton {
    private final ResourceLocation pathNormal;
    private final ResourceLocation pathHover;
    private final ResourceLocation pathLocked;
    public boolean selected = false;
    public boolean unlock = true;
    private final int textureX;
    private final int textureY;
    public GuiTexturedCartouche(int buttonId, int x, int y, int widthIn, int heightIn, String path_normal, String path_hover, String path_locked, int textureX, int textureY) {
        super(buttonId, x, y, widthIn, heightIn, "");
        this.textureX = textureX;
        this.textureY = textureY;
        this.pathNormal = new ResourceLocation(References.MODID, path_normal);
        this.pathHover = new ResourceLocation(References.MODID, path_hover);
        this.pathLocked = new ResourceLocation(References.MODID, path_locked);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.visible) {
            boolean mouseHover = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
             if ((mouseHover && this.enabled && this.unlock) || this.selected ) {
                mc.getTextureManager().bindTexture(this.pathHover);
            } else if (this.enabled && this.unlock) {
                mc.getTextureManager().bindTexture(this.pathNormal);
            } else {
                mc.getTextureManager().bindTexture(this.pathLocked);
            }
            FontRenderer fontrenderer = mc.fontRenderer;


            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            drawModalRectWithCustomSizedTexture(this.x, this.y, 0, 0, this.width, this.height, this.width, this.height);

            if(skillsPointsForGui>0 && mouseHover && !this.unlock){
                mc.getTextureManager().bindTexture(new ResourceLocation(References.MODID, "textures/gui/lock.png"));
                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                drawModalRectWithCustomSizedTexture(this.x + this.width/2 - 19, this.y, 0, 0, 38, 38, 38, 38);
            }
            this.mouseDragged(mc, mouseX, mouseY);
        }
    }
}