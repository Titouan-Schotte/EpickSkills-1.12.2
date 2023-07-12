package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

public class PaquetSetGlowing implements IMessage {


    public PaquetSetGlowing() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<PaquetSetGlowing, IMessage> {
        @Override
        public IMessage onMessage(PaquetSetGlowing message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                NetHandlerPlayServer mc = ctx.getServerHandler();
//                Scoreboard scoreboard = mc.player.getWorldScoreboard();
//                scoreboard.addPlayerToTeam(mc.player.getCachedUniqueIdString(), message.teamName);
//                if(scoreboard.getTeam(message.teamName)==null){
//                boolean isWorth=true;
//                if(scoreboard.getPlayersTeam(mc.player.getName())!=null){
//                    for(ScorePlayerTeam team : scoreboard.getTeams()){
//                        System.out.println(team.getName());
//            //                    if(team.getName().equals(message.teamName)){
//            //                        isWorth=false;
//            //                    }
//                        if(scoreboard.getPlayersTeam(mc.player.getName()).equals(team.getName())){
//                            scoreboard.removePlayerFromTeam(mc.player.getCachedUniqueIdString(), team);
//                        }
//                    }
//                }

//                if(isWorth){
//                    scoreboard.createTeam(message.teamName);
//                }
//
//                }
//                scoreboard.removePlayerFromTeams(mc.player.getName());
//                System.out.println(scoreboard.getPlayersTeam(mc.player.getName()));
//                for(ScorePlayerTeam team : scoreboard.getTeams()){
//                    System.out.println(team.getName() +" " + team.getColor());
//                }
////                scoreboard.getTeam(message.teamName).setColor(TextFormatting.fromColorIndex(message.colorindex));
//                scoreboard.addPlayerToTeam(mc.player.getName(), message.teamName);
//                System.out.println(scoreboard.getPlayersTeam(mc.player.getName()).getName());
//                System.out.println(scoreboard.getPlayersTeam(mc.player.getName()).getColor());
                mc.player.setGlowing(true);
            });
            return null;
        }
    }
}
