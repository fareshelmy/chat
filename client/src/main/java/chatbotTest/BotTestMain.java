package chatbotTest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author yasmin
 */
public class BotTestMain {

    public static void main(String[] args) {

        String botname = "MNg8eRRCT05PCWJcAgW1DAmuYhFfAUv88qe3WucpyHhiYPQ";
        MagicParameters magicParameters = new MagicParameters();
        PandorabotsAPI papi = new PandorabotsAPI(
                "aiaas.pandorabots.com",
                "una17f03ca", "a7a28cfcdef9bb9402b4b25841860873");

        try {
            String request = "Hello";
            String response = papi.talk(botname, request);
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
