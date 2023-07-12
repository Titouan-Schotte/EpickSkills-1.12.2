package fr.epickskills.skills.command;

import fr.epickskills.skills.json.JsonManagement;
import fr.epickskills.skills.network.PaquetSkillUpdateClient;
import fr.epickskills.skills.network.json.PaquetGetSkillsPropertiesServer;
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
import java.util.UUID;

import static fr.epickskills.skills.Main.network;

public class CommandReload extends CommandBase {

    public String getName() {
        return "epickskillsreload";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }


    public String getUsage(ICommandSender sender) {
        return "epickskillsreload";
    }

    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 1) {
            UUID playerId = sender.getCommandSenderEntity().getUniqueID();
            String playerName = sender.getName();
            EntityPlayerMP playerEntity = (EntityPlayerMP) server.getEntityFromUuid(playerId);
            network.sendTo(new PaquetSkillUpdateClient(JsonManagement.getSkill1(playerId), JsonManagement.getSkill2(playerId), JsonManagement.getSkill3(playerId), JsonManagement.getUlti(playerId),JsonManagement.getSkillPT(playerId), JsonManagement.getClasse(playerName, playerId)), playerEntity);
            switch (args[0]) {
                case "skill1":
                    network.sendToServer(new PaquetGetSkillsPropertiesServer(JsonManagement.getClasse(playerName, playerId), 1, JsonManagement.getSkill1(sender.getCommandSenderEntity().getUniqueID())));
                    break;
                case "skill2":
                    network.sendToServer(new PaquetGetSkillsPropertiesServer(JsonManagement.getClasse(playerName, playerId), 2, JsonManagement.getSkill2(sender.getCommandSenderEntity().getUniqueID())));
                    break;
                case "ulti":
                    network.sendToServer(new PaquetGetSkillsPropertiesServer(JsonManagement.getClasse(playerName, playerId), 3, JsonManagement.getUlti(sender.getCommandSenderEntity().getUniqueID())));
                    break;
            }
            network.sendTo(new PaquetSkillUpdateClient(JsonManagement.getSkill1(playerId), JsonManagement.getSkill2(playerId), JsonManagement.getSkill3(playerId), JsonManagement.getUlti(playerId),JsonManagement.getSkillPT(playerId), JsonManagement.getClasse(playerName, playerId)), playerEntity);
            sender.sendMessage(new TextComponentString("Reloaded !").setStyle(new Style().setColor(TextFormatting.GREEN)));
        }
    }


    public boolean isUsernameIndex(String[] args, int index) {
        return index == 0;
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1) {
            return Arrays.asList("skill1", "skill2", "skill3", "ulti");
        }
        return Collections.emptyList();

    }
}