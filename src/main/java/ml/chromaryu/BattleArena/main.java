package ml.chromaryu.BattleArena;

import ml.chromaryu.BattleArena.commands.commandListener;
import org.pircbotx.Configuration;
import org.pircbotx.PircBotX;
import ml.chromaryu.BattleArena.api.sqlapi;
/**
 * Created by midgard on 16/11/21.
 */
public class main {
    public static sqlapi sqlapi = new sqlapi();
    public static void main(String[] args) throws Exception{
        sqlapi.maketable();
        Configuration conf = new Configuration.Builder()
                .addAutoJoinChannel("##BattleArena")
                .addServer(new Configuration.ServerEntry("irc.esper.net",6667))
                .setName("JBA_Test1")
                .setLogin("JBAScr")
                .addListener(new commandListener())
                .buildConfiguration();
        PircBotX bot = new PircBotX(conf);
        bot.startBot();
    }
}
