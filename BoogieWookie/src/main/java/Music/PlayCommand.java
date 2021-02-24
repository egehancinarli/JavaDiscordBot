package Music;

import Commands.ICommand;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class PlayCommand implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        PlayerManager manager= PlayerManager.getINSTANCE();
        manager.loadAndPlay(event.getChannel(),"https://www.youtube.com/watch?v=y6120QOlsfU&ab_channel=Darude");
        manager.getGuildMusicManager(event.getGuild()).player.setVolume(32);
    }

    @Override
    public String getHelp() {
        return null;
    }

    @Override
    public String getInvoke() {
        return null;
    }
}
