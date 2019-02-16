package chatbotTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

public class BotTestMain {

    public static void main(String[] args) {

        MagicParameters magicParameters = new MagicParameters();
        PandorabotsAPI papi = new PandorabotsAPI(magicParameters.getHostName(), magicParameters.getAppId(),
                magicParameters.getUserKey(), magicParameters.isDebug());

        try {
            String request = "hey";
            String response = papi.talk(magicParameters.getBotName(), "yasmin", request);
            System.out.println("User: " + request);
            System.out.println("Mr Happy: " + response);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
    }
}
