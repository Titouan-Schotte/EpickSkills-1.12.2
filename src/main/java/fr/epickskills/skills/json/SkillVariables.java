package fr.epickskills.skills.json;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class SkillVariables {
    public static double propAssSkill1Cooldown = 20, propAssSkill1Damages = 2;
    public static double propAssSkill2Cooldown = 20, propAssSkill2Bump = 1;
    public static double propAssSkill3Cooldown = 20, propAssSkill3Activation = 1, propAssSkill3Radius=10;
    public static double propAssUltiCooldown = 20, propAssUltiActivation = 1, propAssUltiDamages = 1, propAssUltiRayon = 2, propAssUltiRange = 10;

    public static double propArcSkill1Cooldown = 20, propArcSkill1Damages = 2, propArcSkill1Activation = 15, propArcSkill1RadiusActivation = 8, propArcSkill1RadiusTornado = 2;
    public static double propArcSkill2Cooldown = 20, propArcSkill2Damages = 10, propArcSkill2Radius = 1.0, propArcSkill2Activation=10, propArcSkill2RadiusActivation=10;
    public static double propArcSkill3Cooldown = 20, propArcSkill3Damages = 10,propArcSkill3MotionY = 10,propArcSkill3Fire= 3, propArcSkill3Radius = 2.5;
    public static double propArcUltiCooldown = 20, propArcUltiActivation = 10, propArcUltiRadius = 7, propArcUltiDamagesTicks = 3, propArcUltiDamagesPointed = 20;

    public static double propGueSkill1Cooldown = 20, propGueSkill1Damages = 2, propGueSkill1Radius = 4, propGueSkill1Jump = 1.7;
    public static double propGueSkill2Cooldown = 20, propGueSkill2Activation = 10;
    public static double propGueSkill3Cooldown = 10,propGueSkill3Activation = 20, propGueSkill3LifeLeft = 40, propGueSkill3Radius = 5, propGueSkill3ResistancePerso = 2, propGueSkill3ResistanceMulti = 1;
    public static double propGueUltiCooldown = 20, propGueUltiDamages = 2, propGueUltiRadius = 1.0, propGueUltiSpeed = 1.0, propGueUltiTurns = 2, propGueUltiDistance = 2.5;

    public static double propDruSkill1Cooldown = 20, propDruSkill1Activation = 15, propDruSkill1Radius = 5;
    public static double propDruSkill2Cooldown = 20, propDruSkill2Activation = 15, propDruSkill2Radius = 5, propDruSkill2Damages = 1;
    public static double propDruSkill3Cooldown = 20, propDruSkill3Activation = 15,  propDruSkill3SpeedRate = 0.5, propDruSkill3Range = 5;
    public static double propDruUltiCooldown = 20, propDruUltiDamages = 5, propDruUltiActivation = 15, propDruUltiRadius = 1, propDruUltiSpeedRate = 1, propDruUltiJumpRate = 1;

}
