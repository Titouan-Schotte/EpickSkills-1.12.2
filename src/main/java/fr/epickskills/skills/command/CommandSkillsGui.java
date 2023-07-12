package fr.epickskills.skills.command;

import fr.epickskills.skills.json.JsonManagement;
import fr.epickskills.skills.network.PaquetSkillsGui;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;
import java.util.*;

import static fr.epickskills.skills.Main.network;

public class CommandSkillsGui extends CommandBase {

    public String getName() {
        return "skills";
    }

    public int getRequiredPermissionLevel() {
        return 2;
    }

    public String getUsage(ICommandSender sender) {
        return "skills";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {

        EntityPlayerMP playerEntity = (EntityPlayerMP) server.getEntityFromUuid(sender.getCommandSenderEntity().getUniqueID());

        network.sendTo(new PaquetSkillsGui(JsonManagement.getSkill1(sender.getCommandSenderEntity().getUniqueID()), JsonManagement.getSkill2(sender.getCommandSenderEntity().getUniqueID()), JsonManagement.getSkill3(sender.getCommandSenderEntity().getUniqueID()), JsonManagement.getUlti(sender.getCommandSenderEntity().getUniqueID()), JsonManagement.getClasse(sender.getCommandSenderEntity().getName(), sender.getCommandSenderEntity().getUniqueID()), JsonManagement.getSkillPT(sender.getCommandSenderEntity().getUniqueID())), playerEntity);
    }

    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return Collections.emptyList();

    }
}