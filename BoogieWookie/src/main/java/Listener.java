import Commands.Constants;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Listener extends ListenerAdapter {
    private final CommandManager manager = new CommandManager();

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        System.out.printf("Logged in as %s", event.getJDA().getSelfUser().getName());
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        User author = event.getAuthor();
        Message message = event.getMessage();
        String content = message.getContentRaw();

        if (event.isFromType(ChannelType.TEXT)) {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            System.out.printf("%s %s %s %s  \n", guild.getName(), textChannel.getName(), author, content);
        } else if (event.isFromType(ChannelType.PRIVATE)) {
            System.out.printf("|PRIVATE| <%s>: %S \n", author, content);
        }
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().equalsIgnoreCase(Constants.PREFIX + "shutdown") && event.getAuthor().getIdLong() == Constants.OWNER) {
            shutdown(event.getJDA());
            return;
        }

        if (!event.getAuthor().isBot() && !event.getMessage().isWebhookMessage() && event.getMessage().getContentRaw().startsWith(Constants.PREFIX)) {
            manager.handleCommand(event);
        }
    }

    private void shutdown(JDA jda) {
        jda.shutdown();
    }

}
