package fr.epickskills.skills.skills;

import fr.epickskills.skills.skills.classes.Arcaniste;
import fr.epickskills.skills.skills.classes.Assassin;
import fr.epickskills.skills.skills.classes.Druide;
import fr.epickskills.skills.skills.classes.Guerrier;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

import static fr.epickskills.skills.json.SkillVariables.propAssSkill2Bump;
import static fr.epickskills.skills.proxy.ClientProxy.*;
import static fr.epickskills.skills.skills.TexturesVariables.*;
import static fr.epickskills.skills.skills.Timeout.*;
import static fr.epickskills.skills.skills.classes.Arcaniste.Skill1.isSkill1Arcaniste;
import static fr.epickskills.skills.skills.classes.Arcaniste.Skill2.isSkill2Arcaniste;
import static fr.epickskills.skills.skills.classes.Arcaniste.Ulti.isUltiArcaniste;
import static fr.epickskills.skills.skills.classes.Assassin.Skill2.AssassinSkill2BumpNb;
import static fr.epickskills.skills.skills.classes.Assassin.Skill3.isSkill3Assassin;
import static fr.epickskills.skills.skills.classes.Assassin.Ulti.isUltiAssassin;
import static fr.epickskills.skills.skills.classes.Druide.Skill1.isSkill1Druide;
import static fr.epickskills.skills.skills.classes.Druide.Skill2.isSkill2Druide;
import static fr.epickskills.skills.skills.classes.Druide.Skill3.isSkill3Druide;
import static fr.epickskills.skills.skills.classes.Druide.Ulti.isSkillDruideUlti;
import static net.minecraft.client.gui.Gui.drawModalRectWithCustomSizedTexture;

public class SkillHUD {
    private final int size = 24;
    public ResourceLocation Tskill1 = null, Tskill2 = null, Tskill3 = null;
    private ScaledResolution scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
    private int guiLeft = scaledresolution.getScaledWidth() / 2,
            guiTop = scaledresolution.getScaledHeight() / 2;
    private int iconAreaX = guiLeft, iconAreaY = guiTop, iconsSpace = 30;

    @SideOnly(Side.CLIENT)//125 22
    @SubscribeEvent
    public void onRenderPre(RenderGameOverlayEvent.Pre event) {
        scaledresolution = new ScaledResolution(Minecraft.getMinecraft());
        guiLeft = scaledresolution.getScaledWidth() / 2 + 100;
        guiTop = scaledresolution.getScaledHeight() - 25;
        iconAreaX = guiLeft;
        iconAreaY = guiTop;
        if (event.getType() == RenderGameOverlayEvent.ElementType.POTION_ICONS) {
            event.setCanceled(true);
        }

        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL && !Objects.equals(playerClasse, "default")) {
            switch (playerClasse) {
                case "assassin":
                    //RENDER SKILL1 ICON
                    if (playerSkill1 > 0) {
                        if (isSkill1Timeout() || isUltiAssassin || isSkill3Assassin || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tassassin_skill1_no_dispo, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill1();
                        } else {
                            pushIcon(Tassassin_skill1, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL2 ICON
                    if (playerSkill2 > 0) {
                        if (isSkill2Timeout() || isUltiAssassin || isSkill3Assassin|| Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tassassin_skill2_no_dispo, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill2();
                        } else {
                            pushIcon(Tassassin_skill2, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                            Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf((int) (propAssSkill2Bump - AssassinSkill2BumpNb)), iconAreaX + iconsSpace + 9, iconAreaY + 8, 0xFFFFFF);
                        }
                    }

                    //RENDER SKILL3 ICON
                    if (playerSkill3 > 0) {
                        if (isSkill3Timeout() || isUltiAssassin || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tassassin_skill3_no_dispo, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill3();
                        } else if (isSkill3Assassin) {
                            pushIcon(Tassassin_skill3_use, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tassassin_skill3, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER ULTI ICON
                    if (playerUlti > 0) {
                        if (isUltiTimeout() || isSkill3Assassin || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tassassin_ulti_no_dispo, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownUlti();
                        } else if (Assassin.Ulti.isUltiAssassin) {
                            pushIcon(Tassassin_ulti_use, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tassassin_ulti, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }
                    break;


                case "guerrier":
                    //RENDER SKILL1 ICON
                    if (playerSkill1 > 0) {
                        if (isSkill1Timeout() || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tguerrier_skill1_no_dispo, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill1();
                        } else {
                            pushIcon(Tguerrier_skill1, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL2 ICON
                    if (playerSkill2 > 0) {
                        if (isSkill2Timeout() || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tguerrier_skill2_no_dispo, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill2();
                        } else {
                            pushIcon(Tguerrier_skill2, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL3 ICON
                    if (playerSkill3 > 0) {
                        if (isSkill3Timeout() || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tguerrier_skill3_no_dispo, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill3();
                        } else if (Guerrier.Skill3.isSkill3Guerrier) {
                            pushIcon(Tguerrier_skill3_use, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tguerrier_skill3, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER ULTI ICON
                    if (playerUlti > 0) {
                        if (isUltiTimeout() || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tguerrier_ulti_no_dispo, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownUlti();
                        } else if (Guerrier.Ulti.isUltiGuerrier) {
                            pushIcon(Tguerrier_ulti_use, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tguerrier_ulti, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }
                    break;


                case "arcaniste":
                    //RENDER SKILL1 ICON
                    if (playerSkill1 > 0) {
                        if (isSkill1Timeout() || isSkill2Arcaniste || isUltiArcaniste || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tarcaniste_skill1_no_dispo, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill1();
                        } else if (Arcaniste.Skill1.isSkill1Arcaniste) {
                            pushIcon(Tarcaniste_skill1_use, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tarcaniste_skill1, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL2 ICON
                    if (playerSkill2 > 0) {
                        if (isSkill2Timeout() || isSkill1Arcaniste || isUltiArcaniste || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tarcaniste_skill2_no_dispo, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill2();
                        }  else if (Arcaniste.Skill2.isSkill2Arcaniste) {
                            pushIcon(Tarcaniste_skill2_use, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tarcaniste_skill2, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL3 ICON
                    if (playerSkill3 > 0) {
                        if (isSkill3Timeout() || isSkill1Arcaniste || isUltiArcaniste || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tarcaniste_skill3_no_dispo, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill3();
                        }  else if (Arcaniste.Skill3.isSkill3Arcaniste) {
                            pushIcon(Tarcaniste_skill3_use, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tarcaniste_skill3, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER ULTI ICON
                    if (playerUlti > 0) {
                        if (isUltiTimeout() || isSkill1Arcaniste || isSkill2Arcaniste || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tarcaniste_ulti_no_dispo, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownUlti();
                        } else if (isUltiArcaniste) {
                            pushIcon(Tarcaniste_ulti_use, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tarcaniste_ulti, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }
                    break;


                case "druide":
                    //RENDER SKILL1 ICON
                    if (playerSkill1 > 0) {
                        if (isSkill1Timeout() || isSkillDruideUlti || isSkill3Druide || isSkill2Druide || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_skill1_no_dispo, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill1();
                        } else if (isSkill1Druide) {
                            pushIcon(Tdruide_skill1_use, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tdruide_skill1, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL2 ICON
                    if (playerSkill2 > 0) {
                        if (isSkill2Timeout() || isSkill1Druide || isSkill3Druide ||  isSkillDruideUlti || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_skill2_no_dispo, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill2();
                        } else if (isSkill2Druide) {
                            pushIcon(Tdruide_skill2_use, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                        }else {
                            pushIcon(Tdruide_skill2, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL3 ICON
                    if (playerSkill3 > 0) {
                        if (isSkill3Timeout() || isSkill1Druide || isSkill2Druide || isSkillDruideUlti || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_skill3_no_dispo, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill3();
                        } else if (isSkill3Druide) {
                            pushIcon(Tdruide_skill3_use, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        }else {
                            pushIcon(Tdruide_skill3, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER ULTI ICON
                    if (playerUlti > 0) {
                        if (isUltiTimeout() || isSkill1Druide || isSkill3Druide || isSkill2Druide || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_ulti_no_dispo, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownUlti();
                        } else if (Druide.Ulti.isSkillDruideUlti) {
                            pushIcon(Tdruide_ulti_use, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tdruide_ulti, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }
                    break;

                case "necromancien":
                    //RENDER SKILL1 ICON
                    if (playerSkill1 > 0) {
                        if (isSkill1Timeout() || isSkillDruideUlti || isSkill3Druide || isSkill2Druide || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_skill1_no_dispo, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill1();
                        } else if (isSkill1Druide) {
                            pushIcon(Tdruide_skill1_use, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tdruide_skill1, iconAreaX, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL2 ICON
                    if (playerSkill2 > 0) {
                        if (isSkill2Timeout() || isSkill1Druide || isSkill3Druide ||  isSkillDruideUlti || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_skill2_no_dispo, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill2();
                        } else if (isSkill2Druide) {
                            pushIcon(Tdruide_skill2_use, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                        }else {
                            pushIcon(Tdruide_skill2, iconAreaX + iconsSpace, iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER SKILL3 ICON
                    if (playerSkill3 > 0) {
                        if (isSkill3Timeout() || isSkill1Druide || isSkill2Druide || isSkillDruideUlti || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_skill3_no_dispo, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownSkill3();
                        } else if (isSkill3Druide) {
                            pushIcon(Tdruide_skill3_use, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        }else {
                            pushIcon(Tdruide_skill3, iconAreaX + (iconsSpace * 2), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }

                    //RENDER ULTI ICON
                    if (playerUlti > 0) {
                        if (isUltiTimeout() || isSkill1Druide || isSkill3Druide || isSkill2Druide || Minecraft.getMinecraft().player.isRiding()) {
                            pushIcon(Tdruide_ulti_no_dispo, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                            drawCooldownUlti();
                        } else if (Druide.Ulti.isSkillDruideUlti) {
                            pushIcon(Tdruide_ulti_use, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        } else {
                            pushIcon(Tdruide_ulti, iconAreaX + (iconsSpace * 3), iconAreaY, 0, 0, size, size, size, size);
                        }
                    }
                    break;
            }

        }
    }

    private void drawCooldownSkill1() {
        if (isSkill1Timeout() && !Minecraft.getMinecraft().player.isRiding()) {
            int minRest = getSkill1Timeout();
            if (minRest >= 100) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + 4, iconAreaY + 8, 0xFFFFFF);
            } else if (minRest >= 10) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + 6, iconAreaY + 8, 0xFFFFFF);
            } else {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + 9, iconAreaY + 8, 0xFFFFFF);
            }
        }
    }

    private void drawCooldownSkill2() {
        if (isSkill2Timeout() && !Minecraft.getMinecraft().player.isRiding()) {
            int minRest = getSkill2Timeout();
            if (minRest >= 100) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + iconsSpace + 4, iconAreaY + 8, 0xFFFFFF);
            } else if (minRest >= 10) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + iconsSpace + 6, iconAreaY + 8, 0xFFFFFF);
            } else {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + iconsSpace + 9, iconAreaY + 8, 0xFFFFFF);
            }
        }

    }

    private void drawCooldownSkill3() {
        if (isSkill3Timeout() && !Minecraft.getMinecraft().player.isRiding()) {
            int minRest = getSkill3Timeout();
            if (minRest >= 100) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + (iconsSpace * 2)+ 4, iconAreaY + 8, 0xFFFFFF);
            } else if (minRest >= 10) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + (iconsSpace * 2)+ 6, iconAreaY + 8, 0xFFFFFF);
            } else {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + (iconsSpace * 2)+ 9, iconAreaY + 8, 0xFFFFFF);
            }
        }

    }

    private void drawCooldownUlti() {
        if (isUltiTimeout() && !Minecraft.getMinecraft().player.isRiding()) {
            int minRest = getUltiTimeout();
            if (minRest >= 100) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + (iconsSpace * 3) + 4, iconAreaY + 8, 0xFFFFFF);
            } else if (minRest >= 10) {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + (iconsSpace * 3) + 6, iconAreaY + 8, 0xFFFFFF);
            } else {
                Minecraft.getMinecraft().fontRenderer.drawString(String.valueOf(minRest), iconAreaX + (iconsSpace * 3) + 9, iconAreaY + 8, 0xFFFFFF);
            }
        }
    }

    public void pushIcon(ResourceLocation Tskill, int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        Minecraft.getMinecraft().getTextureManager().bindTexture(Tskill);
        drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, textureWidth, textureHeight);
    }

}
