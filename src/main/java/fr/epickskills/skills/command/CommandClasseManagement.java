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
import noppes.npcs.api.NpcAPI;
import noppes.npcs.api.entity.IPlayer;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static fr.epickskills.skills.Main.network;

public class CommandClasseManagement extends CommandBase {

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

    public String getName() {
        return "epickclasse";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getUsage(ICommandSender sender) {
        return "epickclasse";
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 2) {
            GameProfile player = server.getPlayerProfileCache().getGameProfileForUsername(args[1]);
            JsonManagement.setClasse(player.getName(), player.getId(), args[0]);
            if (server.getEntityFromUuid(player.getId()) instanceof EntityPlayerMP) {
                EntityPlayerMP playerEntity = (EntityPlayerMP) server.getEntityFromUuid(player.getId());
                network.sendTo(new PaquetSkillUpdateClient(JsonManagement.getSkill1(player.getId()), JsonManagement.getSkill2(player.getId()), JsonManagement.getSkill3(player.getId()), JsonManagement.getUlti(player.getId()),JsonManagement.getSkillPT(player.getId()), JsonManagement.getClasse(player.getName(), player.getId())), playerEntity);
            }
            sender.sendMessage(new TextComponentString("Success !").setStyle(new Style().setColor(TextFormatting.GREEN)));
            return;
        }
        sender.sendMessage(new TextComponentString("epickclasse <assassin|guerrier|arcaniste|druide|necromancien> <playerName>").setStyle(new Style().setColor(TextFormatting.RED)));
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 2) {
            return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
        } else if (args.length == 1) {
            return Arrays.asList("assassin", "guerrier", "druide","arcaniste", "necromancien");
        }
        return Collections.emptyList();

    }
}