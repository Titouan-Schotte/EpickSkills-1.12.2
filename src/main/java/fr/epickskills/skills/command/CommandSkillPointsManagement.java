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

public class CommandSkillPointsManagement extends CommandBase {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public String getName() {
        return "epickskillpts";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getUsage(ICommandSender sender) {
        return "epickskillpts";
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 3) {
            GameProfile player = server.getPlayerProfileCache().getGameProfileForUsername(args[1]);
            int amount = Integer.parseInt(args[2]);
            switch (args[0]) {
                case "add":
                    JsonManagement.addSkillPT(player.getName(), player.getId(), amount);
                    break;
                case "remove":
                    JsonManagement.removeSkillPT(player.getName(), player.getId(), amount);
                    break;
                case "set":
                    JsonManagement.setSkillPT(player.getName(), player.getId(), amount);
                    break;
                case "view":
                    sender.sendMessage(new TextComponentString("Le joueur a " + String.valueOf(JsonManagement.getSkillPT(player.getId())) + " points de compétences"));
                    JsonManagement.removeSkillPT(player.getName(), player.getId(), amount);
                    break;
            }
            if (server.getEntityFromUuid(player.getId()) instanceof EntityPlayerMP) {
                EntityPlayerMP playerEntity = (EntityPlayerMP) server.getEntityFromUuid(player.getId());
                network.sendTo(new PaquetSkillUpdateClient(JsonManagement.getSkill1(player.getId()), JsonManagement.getSkill2(player.getId()), JsonManagement.getSkill3(player.getId()), JsonManagement.getUlti(player.getId()),JsonManagement.getSkillPT(player.getId()), JsonManagement.getClasse(player.getName(), player.getId())), playerEntity);
            }
            return;
        }
        sender.sendMessage(new TextComponentString("epickskillpts <add|remove|set|view> <playerName> <value>").setStyle(new Style().setColor(TextFormatting.RED)));
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 3) {
            return Arrays.asList("0", "1", "2", "3");
        } else if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 1) {
            return Arrays.asList("add", "remove", "set", "view");
        }
        return Collections.emptyList();

    }
}