package Commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;


import java.util.List;

public class PingCommand implements ICommand{
    @Override
    public void handle(List<String> args, GuildMessageReceivedEvent event) {
        event.getChannel().sendMessage("Pong! ").queue((message) ->
                message.editMessageFormat("Ping is %sms", event.getJDA().getRestPing()));
    }

    @Override
    public String getHelp() {
         return "Pong!\n" +
                "Usage: `" + Constants.PREFIX + getInvoke() + "`";
    }

    @Override
    public String getInvoke() {
        return "ping";
    }
}
