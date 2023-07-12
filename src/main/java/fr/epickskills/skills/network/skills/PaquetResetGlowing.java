package fr.epickskills.skills.network.skills;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PaquetResetGlowing implements IMessage {


    public PaquetResetGlowing() {
    }



    @Override
    public void fromBytes(ByteBuf buf) {
    }

    @Override
    public void toBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<PaquetResetGlowing, IMessage> {
        @Override
        public IMessage onMessage(PaquetResetGlowing message, MessageContext ctx) {
            FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->
            {
                NetHandlerPlayServer mc = ctx.getServerHandler();
//                Scoreboard scoreboard = mc.player.getWorldScoreboard();
//                scoreboard.removePlayerFromTeams(mc.player.getName());
//                boolean isWorth=true;
//                if(scoreboard.getPlayersTeam(mc.player.getName())!=null){
//                    for(ScorePlayerTeam team :scoreboard.getTeams()){
//                        System.out.println(team.getName());
//                        //                    if(team.getName().equals(message.teamName)){
//                        //                        isWorth=false;
//                        //                    }
//                        if(scoreboard.getPlayersTeam(mc.player.getName()).equals(team.getName())){
//                            scoreboard.removePlayerFromTeam(mc.player.getCachedUniqueIdString(), team);
//                        }
//                    }
//                }
//                scoreboard.removePlayerFromTeam(mc.player.getCachedUniqueIdString(), scoreboard.getTeam(message.teamName));
                ctx.getServerHandler().player.setGlowing(false);
            });
            return null;
        }
    }
}
