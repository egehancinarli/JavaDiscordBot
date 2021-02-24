import Music.PlayCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

public class bot {
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createDefault("Nzc5MDAyMTAzNDA1NDc3OTI4.X7aMPg.3QrPQiv_X3Diq2r3FjEAuzMqLZs").build();
        jda.addEventListener(new Listener());


    }
}
