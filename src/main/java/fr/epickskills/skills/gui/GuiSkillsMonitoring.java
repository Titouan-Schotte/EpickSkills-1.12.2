package fr.epickskills.skills.gui;

import fr.epickskills.skills.References;
import fr.epickskills.skills.network.PaquetSkillsGuiUpgradeServer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.ParametersAreNonnullByDefault;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

import static fr.epickskills.skills.Main.network;
import static fr.epickskills.skills.proxy.ClientProxy.*;
import static fr.epickskills.skills.skills.Utilities.getLoreByClass;
import static fr.epickskills.skills.skills.Utilities.getUpgradeLoreOf;

@SideOnly(Side.CLIENT)
public class GuiSkillsMonitoring extends GuiScreen {
    private final ResourceLocation background = new ResourceLocation(References.MODID, "textures/gui/gui_base.png"); // 256x202
    private final int xSize = 425;
    private final int ySize = 238;
    private final Minecraft minecraft;
    private int guiLeft;
    private int guiTop;

    public GuiSkillsMonitoring(Minecraft mc) {
        minecraft=mc;
    }

    public void initGui() {
        guiLeft = (this.width - this.xSize) / 2;
        guiTop = (this.height - this.ySize) / 2;
        //Init
        buttonList.add(new GuiTexturedButton(0, guiLeft+385,guiTop+40, 16,16, "textures/gui/hotbar/profil/profil_normal.png", "textures/gui/hotbar/profil/profil_hover.png", "textures/gui/hotbar/profil/profil_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(1, guiLeft+386,guiTop+70, 14,15, "textures/gui/hotbar/skills/skills_normal.png", "textures/gui/hotbar/skills/skills_hover.png", "textures/gui/hotbar/skills/skills_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(2, guiLeft+387,guiTop+98, 14,19, "textures/gui/hotbar/jobs/jobs_normal.png", "textures/gui/hotbar/jobs/jobs_hover.png", "textures/gui/hotbar/jobs/jobs_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(3, guiLeft+387,guiTop+128, 14,14, "textures/gui/hotbar/mounts/mounts_normal.png", "textures/gui/hotbar/mounts/mounts_hover.png", "textures/gui/hotbar/mounts/mounts_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(4, guiLeft+387,guiTop+157, 13,14, "textures/gui/hotbar/pets/pet_normal.png", "textures/gui/hotbar/pets/pet_hover.png", "textures/gui/hotbar/social/pets/pet_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(5, guiLeft+387,guiTop+187, 14,14, "textures/gui/hotbar/settings/settings_normal.png", "textures/gui/hotbar/settings/settings_hover.png", "textures/gui/hotbar/settings/settings_locked.png",0,0));
        buttonList.add(new GuiTexturedButton(6, guiLeft + 253, guiTop + 142, 100, 20, "textures/gui/ameliorer.png","textures/gui/ameliorer-hover.png","textures/gui/ameliorer-lock.png", 0, 0));
        buttonList.add(new GuiTexturedButton(7, guiLeft + 230, guiTop + 190, 40, 20, "textures/gui/arrow-left.png", "textures/gui/arrow-left-hover.png", "textures/gui/arrow-left.png",0,0));
        buttonList.add(new GuiTexturedButton(8, guiLeft + 320, guiTop + 190, 40, 20, "textures/gui/arrow-right.png", "textures/gui/arrow-right-hover.png", "textures/gui/arrow-right-hover.png",0,0));
        buttonList.get(7).visible=false;
        // SKILLS CARTOUCHE
        if(playerClasse.equals("default")){
            buttonList.add(new GuiTexturedButton(9, guiLeft+53,guiTop+70, 139,35, "textures/gui/base.png", "textures/gui/base.png", "textures/gui/base.png",0,0));
            buttonList.add(new GuiTexturedButton(10, guiLeft+53,guiTop+112+3, 139,35, "textures/gui/base.png", "textures/gui/base.png", "textures/gui/base.png",0,0));
            buttonList.add(new GuiTexturedButton(11, guiLeft+53,guiTop+154+6, 139,35, "textures/gui/base.png", "textures/gui/base.png", "textures/gui/base.png",0,0));
            buttonList.add(new GuiTexturedButton(12, guiLeft+53,guiTop+70, 139,35, "textures/gui/base.png", "textures/gui/base.png", "textures/gui/base.png",0,0));
            buttonList.get(9).enabled=false;
            buttonList.get(10).enabled=false;
            buttonList.get(11).enabled=false;
            buttonList.get(12).enabled=false;
            buttonList.get(12).visible=false;
            buttonList.get(6).visible=false;
        } else {
            buttonList.get(6).enabled=false;
            String filename = playerClasse.toLowerCase().substring(0,4);
            buttonList.add(new GuiTexturedCartouche(9, guiLeft+53,guiTop+70, 139,35, "textures/gui/"+filename+"1.png", "textures/gui/"+filename+"1-hover.png", "textures/gui/"+filename+"1-lock.png",0,0));
            buttonList.add(new GuiTexturedCartouche(10, guiLeft+53,guiTop+112+3, 139,35, "textures/gui/"+filename+"2.png", "textures/gui/"+filename+"2-hover.png", "textures/gui/"+filename+"2-lock.png",0,0));
            buttonList.add(new GuiTexturedCartouche(11, guiLeft+53,guiTop+154+6, 139,35, "textures/gui/"+filename+"3.png", "textures/gui/"+filename+"3-hover.png", "textures/gui/"+filename+"3-lock.png",0,0));
            buttonList.add(new GuiTexturedCartouche(12, guiLeft+53,guiTop+70, 139,35, "textures/gui/"+filename+"ulti.png", "textures/gui/"+filename+"ulti-hover.png", "textures/gui/"+filename+"ulti-lock.png",0,0));
            buttonList.get(12).visible=false;
            //SKILL1
            if(playerSkill1==0){
                ((GuiTexturedCartouche) buttonList.get(9)).unlock=false;
            }
            //SKILL2
            if (playerSkill1>0) {
                if(playerSkill2==0){
                    buttonList.get(10).enabled=true;
                    ((GuiTexturedCartouche) buttonList.get(10)).unlock=false;
                } else {
                    buttonList.get(10).enabled=true;
                }
            } else {
                buttonList.get(10).enabled=false;
            }

            //SKILL3
            if (playerSkill2>0) {
                if(playerSkill3==0){
                    buttonList.get(11).enabled=true;
                    ((GuiTexturedCartouche) buttonList.get(11)).unlock=false;
                } else {
                    buttonList.get(11).enabled=true;
                }
            } else {
                buttonList.get(11).enabled=false;
            }

            //ULTI
            if (playerSkill3>0) {
                if(playerUlti==0){
                    buttonList.get(12).enabled=true;
                    ((GuiTexturedCartouche) buttonList.get(12)).unlock=false;
                } else {
                    buttonList.get(12).enabled=true;
                }
            } else {
                buttonList.get(12).enabled=false;
            }
            //TODO WARNING CHANGE ID WHEN NEW SPELL INCOMING !

        }
        buttonList.get(6).enabled=false;
        buttonList.add(new GuiButton(1000, guiLeft + 1000, guiTop + 1000, 1, 1, ".."));


    }

    public boolean doesGuiPauseGame() {
        return false;
    }


    @ParametersAreNonnullByDefault
    public void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:

                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.getMinecraft().player.sendChatMessage("/profil");
                break;
            case 1:

                break;
            case 2:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.getMinecraft().player.sendChatMessage("/jobs");
                break;
            case 3:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.getMinecraft().player.sendChatMessage("/mounts");
                break;
            case 4:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.getMinecraft().player.sendChatMessage("/pets");
                break;
            case 5:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                Minecraft.getMinecraft().player.sendChatMessage("/confrerie");
                break;
            case 9:
                if(((GuiTexturedCartouche) buttonList.get(9)).unlock){
                    ((GuiTexturedCartouche) buttonList.get(9)).selected = true;
                    ((GuiTexturedCartouche) buttonList.get(10)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(11)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(12)).selected = false;
                    buttonList.get(6).enabled=false;
                    if(playerSkill1<3 && skillsPointsForGui>0){
                        buttonList.get(6).enabled=true;
                    }
                } else if(skillsPointsForGui>0 && !((GuiTexturedCartouche) buttonList.get(9)).unlock){
                    this.mc.displayGuiScreen(null);
                    this.mc.setIngameFocus();
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(1));
                }
                break;
            case 10:
                if(((GuiTexturedCartouche) buttonList.get(10)).unlock){
                    ((GuiTexturedCartouche) buttonList.get(10)).selected = true;
                    ((GuiTexturedCartouche) buttonList.get(9)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(11)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(12)).selected = false;
                    buttonList.get(6).enabled=false;
                    if(playerSkill2<3 && skillsPointsForGui>0){
                        buttonList.get(6).enabled=true;
                    }
                } else if(skillsPointsForGui>0 && !((GuiTexturedCartouche) buttonList.get(10)).unlock){
                    this.mc.displayGuiScreen(null);
                    this.mc.setIngameFocus();
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(2));
                }
                break;
            case 11:
                if(((GuiTexturedCartouche) buttonList.get(11)).unlock){
                    ((GuiTexturedCartouche) buttonList.get(11)).selected = true;
                    ((GuiTexturedCartouche) buttonList.get(9)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(10)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(12)).selected = false;
                    buttonList.get(6).enabled=false;
                    if(playerSkill3<3 && skillsPointsForGui>0){
                        buttonList.get(6).enabled=true;
                    }
                } else if(skillsPointsForGui>0 && !((GuiTexturedCartouche) buttonList.get(11)).unlock){
                    this.mc.displayGuiScreen(null);
                    this.mc.setIngameFocus();
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(3));
                }
                break;
            case 12:
                if(((GuiTexturedCartouche) buttonList.get(12)).unlock){
                    ((GuiTexturedCartouche) buttonList.get(12)).selected = true;
                    ((GuiTexturedCartouche) buttonList.get(9)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(10)).selected = false;
                    ((GuiTexturedCartouche) buttonList.get(11)).selected = false;
                    buttonList.get(6).enabled=false;
                    if(playerUlti<3 && skillsPointsForGui>0){
                        buttonList.get(6).enabled=true;
                    }
                } else if(skillsPointsForGui>0 && !((GuiTexturedCartouche) buttonList.get(12)).unlock){
                    this.mc.displayGuiScreen(null);
                    this.mc.setIngameFocus();
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(4));
                }
                break;
            case 6:
                this.mc.displayGuiScreen(null);
                this.mc.setIngameFocus();
                if(((GuiTexturedCartouche) buttonList.get(9)).selected){
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(1));
                } else if (((GuiTexturedCartouche) buttonList.get(10)).selected){
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(2));
                } else if (((GuiTexturedCartouche) buttonList.get(11)).selected){
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(3));
                } else if (((GuiTexturedCartouche) buttonList.get(12)).selected){
                    network.sendToServer(new PaquetSkillsGuiUpgradeServer(4));
                }

                break;
            case 7:
                buttonList.get(12).visible=false;
                buttonList.get(9).visible=true;
                buttonList.get(10).visible=true;
                buttonList.get(11).visible=true;
                buttonList.get(7).visible=false;
                buttonList.get(8).visible=true;
                break;
            case 8:
                buttonList.get(12).visible=true;
                buttonList.get(9).visible=false;
                buttonList.get(10).visible=false;
                buttonList.get(11).visible=false;
                buttonList.get(7).visible=true;
                buttonList.get(8).visible=false;
                break;

        }
    }

    public void buttonHoveringText(GuiButton button, int mouseX, int mouseY, String[] text, int posX, int posY) {
        if (button.visible && button.enabled && mouseX >= button.x && mouseY >= button.y && mouseX < button.x + button.width && mouseY < button.y + button.height) {
            List<String> temp = Arrays.asList(text);
            if(button.id != 6){
                drawHoveringText(temp, posX, posY);
            } else {
                if(((GuiTexturedCartouche) buttonList.get(9)).selected){
                    if(playerSkill1>2 || skillsPointsForGui==0){
                        return;
                    }
                    temp = Arrays.asList(getUpgradeLoreOf(playerSkill1,1, playerClasse));
                } else if(((GuiTexturedCartouche) buttonList.get(10)).selected) {
                    if(playerSkill2>2 || skillsPointsForGui==0){
                        return;
                    }
                    temp = Arrays.asList(getUpgradeLoreOf(playerSkill2,2, playerClasse));
                } else if(((GuiTexturedCartouche) buttonList.get(11)).selected) {
                    if(playerSkill3>2 || skillsPointsForGui==0){
                        return;
                    }
                    temp = Arrays.asList(getUpgradeLoreOf(playerSkill3,3, playerClasse));
                } else if(((GuiTexturedCartouche) buttonList.get(12)).selected) {
                    if(playerUlti>2 || skillsPointsForGui==0){
                        return;
                    }
                    temp = Arrays.asList(getUpgradeLoreOf(playerUlti,4, playerClasse));
                } else {
                    return;
                }
                drawHoveringText(temp, posX, posY);
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX,mouseY,partialTicks);
        drawBackgroundImage();

        drawText();
        super.drawScreen(mouseX,mouseY,partialTicks);

        buttonHoveringText(buttonList.get(0), mouseX, mouseY, new String[]{"Profil"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(1), mouseX, mouseY, new String[]{"Compétences"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(2), mouseX, mouseY, new String[]{"Métiers"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(3), mouseX, mouseY, new String[]{"Montures"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(4), mouseX, mouseY, new String[]{"Familiers"}, mouseX, mouseY);
        buttonHoveringText(buttonList.get(5), mouseX, mouseY, new String[]{"Confrérie"}, mouseX, mouseY);

        //SKILLS HOVERING TEXT
        if(!playerClasse.equals("default")){
            if(((GuiTexturedCartouche) buttonList.get(9)).unlock){
                buttonHoveringText(buttonList.get(9), mouseX, mouseY, new String[]{"§bLevel "+playerSkill1+"§r", getLoreByClass(playerClasse, 1)}, mouseX, mouseY);
            }
            if(((GuiTexturedCartouche) buttonList.get(10)).unlock){
                buttonHoveringText(buttonList.get(10), mouseX, mouseY, new String[]{"§bLevel "+playerSkill2+"§r", getLoreByClass(playerClasse, 2)}, mouseX, mouseY);
            }
            if(((GuiTexturedCartouche) buttonList.get(11)).unlock){

                buttonHoveringText(buttonList.get(11), mouseX, mouseY, new String[]{"§bLevel "+playerSkill3+"§r", getLoreByClass(playerClasse, 3)}, mouseX, mouseY);
            }
            if(((GuiTexturedCartouche) buttonList.get(12)).unlock){
                buttonHoveringText(buttonList.get(12), mouseX, mouseY, new String[]{"§bLevel "+playerUlti+"§r", getLoreByClass(playerClasse, 4)}, mouseX, mouseY);
            }
            //UPGRADE HOVERING TEXT
            buttonHoveringText(buttonList.get(6), mouseX, mouseY, new String[]{""}, mouseX, mouseY);
        }
    }

    public void drawBackgroundImage() {
        minecraft.getTextureManager().bindTexture(background);
        drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, xSize, ySize, xSize, ySize);
    }

    public void drawText() {
        drawCenteredString(fontRenderer, String.valueOf(skillsPointsForGui), guiLeft + 359, guiTop + 92, Color.ORANGE.getRGB());
        String classe = "...";
        int colorClasse = Color.BLACK.getRGB();
        switch(playerClasse){
            case "arcaniste":
                classe="Arcaniste";
                colorClasse=Color.MAGENTA.getRGB();
                break;
            case "guerrier":
                classe="Guerrier";
                colorClasse=Color.RED.getRGB();
                break;
            case "druide":
                classe="Druide";
                colorClasse=Color.GREEN.getRGB();
                break;
            case "assassin":
                classe="Assassin";
                colorClasse=Color.DARK_GRAY.getRGB();

                break;
        }
        drawCenteredString(fontRenderer, classe, guiLeft + 341, guiTop + 73, colorClasse);
    }
}