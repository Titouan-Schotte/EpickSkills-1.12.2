package fr.epickskills.skills.command;

import com.mojang.authlib.GameProfile;
import fr.epickskills.skills.json.JsonManagement;
import fr.epickskills.skills.network.PaquetSkillUpdateClient;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fr.epickskills.skills.Main.network;

public class CommandSkillManagement extends CommandBase {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public String getName() {
        return "epickskill";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getUsage(ICommandSender sender) {
        return "epickskill";
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 4 && isInteger(args[3]) && (args[1].equals("skill1") || args[1].equals("skill2") || args[1].equals("skill3") ||args[1].equals("ulti")) && (args[0].equals("upgrade") || args[0].equals("downgrade") || args[0].equals("set"))) {
            int state = Integer.parseInt(args[3]);

            GameProfile player = server.getPlayerProfileCache().getGameProfileForUsername(args[2]);

            switch (args[1]) {
                case "skill1":
                    if (args[0].equals("set")) {
                        JsonManagement.setSkill1(player.getName(), player.getId(), state);
                    } else if (args[0].equals("upgrade")) {
                        JsonManagement.upgradeSkill1(player.getName(), player.getId(), state);
                    } else {
                        JsonManagement.downgradeSkill1(player.getName(), player.getId(), state);
                    }
                    break;
                case "skill2":
                    if (args[0].equals("set")) {
                        JsonManagement.setSkill2(player.getName(), player.getId(), state);
                    } else if (args[0].equals("upgrade")) {
                        JsonManagement.upgradeSkill2(player.getName(), player.getId(), state);
                    } else {
                        JsonManagement.downgradeSkill2(player.getName(), player.getId(), state);
                    }
                    break;
                case "skill3":
                    if (args[0].equals("set")) {
                        JsonManagement.setSkill3(player.getName(), player.getId(), state);
                    } else if (args[0].equals("upgrade")) {
                        JsonManagement.upgradeSkill3(player.getName(), player.getId(), state);
                    } else {
                        JsonManagement.downgradeSkill3(player.getName(), player.getId(), state);
                    }
                    break;
                case "ulti":
                    if (args[0].equals("set")) {
                        JsonManagement.setUlti(player.getName(), player.getId(), state);
                    } else if (args[0].equals("upgrade")) {
                        JsonManagement.upgradeUlti(player.getName(), player.getId(), state);
                    } else {
                        JsonManagement.downgradeUlti(player.getName(), player.getId(), state);
                    }
                    break;
            }
            if (server.getEntityFromUuid(player.getId()) instanceof EntityPlayerMP) {
                EntityPlayerMP playerEntity = (EntityPlayerMP) server.getEntityFromUuid(player.getId());
                network.sendTo(new PaquetSkillUpdateClient(JsonManagement.getSkill1(player.getId()), JsonManagement.getSkill2(player.getId()),JsonManagement.getSkill3(player.getId()), JsonManagement.getUlti(player.getId()),JsonManagement.getSkillPT(player.getId()), JsonManagement.getClasse(player.getName(), player.getId())), playerEntity);
            }
            sender.sendMessage(new TextComponentString("Success !").setStyle(new Style().setColor(TextFormatting.GREEN)));
            return;
        }
        sender.sendMessage(new TextComponentString("epickskill <skill1|skill2|skill3|ulti> <playerName> <0=>bloqué|1=>level1|2=>level2|3=>level3>").setStyle(new Style().setColor(TextFormatting.RED)));
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 4) {
            return Arrays.asList("0", "1", "2", "3");
        } else if (args.length == 3) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 2) {
            return Arrays.asList("skill1", "skill2", "skill3", "ulti");
        } else if (args.length == 1) {
            return Arrays.asList("upgrade", "downgrade", "set");
        }
        return Collections.emptyList();

    }


}