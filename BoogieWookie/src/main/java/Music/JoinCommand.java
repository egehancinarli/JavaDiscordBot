package Music;

import Commands.ICommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.security.UnrecoverableEntryException;
import java.util.List;

public class JoinCommand  implements ICommand {
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        TextChannel channel= event.getChannel();
        AudioManager audioManager=event.getGuild().getAudioManager();
        if(audioManager.isConnected()){
            channel.sendMessage("Already connected to a channel").queue();
            return;
        }

        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();

        if(!memberVoiceState.inVoiceChannel()){
            channel.sendMessage("Please join a voice channel first").queue();
            return;
        }
        VoiceChannel voiceChannel=memberVoiceState.getChannel();
        Member selfMember =event.getGuild().getSelfMember();

        if(!selfMember.hasPermission(voiceChannel, Permission.VOICE_CONNECT)){
            channel.sendMessage("I can't join to the channel").queue();
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        channel.sendMessage("Hey!!").queue();

    }

    @Override
    public String getHelp() {
        return "Makes the bot join your channel";
    }

    @Override
    public String getInvoke() {
        return "join";
    }
}
