import Commands.Constants;
import Commands.HelloCommand;
import Commands.ICommand;
import Commands.PingCommand;
import Music.PlayCommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class CommandManager {
    private final Map<String, ICommand> commands= new HashMap<>();

    public CommandManager(){
         addCommand(new PingCommand());
         addCommand(new PlayCommand());
         addCommand(new HelloCommand());
    }
    private void addCommand(ICommand command){
        if(!commands.containsKey(command.getInvoke())){
            commands.put(command.getInvoke(),command);
        }

    }
    void handleCommand(GuildMessageReceivedEvent event){
        final String[] split = event.getMessage().getContentRaw().replaceFirst(
                "(?i)" + Pattern.quote(Constants.PREFIX), "").split("\\s+");
        final String invoke=split[0].toLowerCase();

        if(commands.containsKey(invoke)){
            final List<String> args = Arrays.asList(split).subList(1,split.length);
            commands.get(invoke).handle(args,event);
        }
    }



}
