package model.control.implementations;

import com.chat.common.PandoraBotHandler;
import com.pandorabots.api.MagicParameters;
import com.pandorabots.api.PandorabotsAPI;
import java.io.IOException;
import java.net.URISyntaxException;
import org.json.JSONException;

public class PandoraBotHandlerImp implements PandoraBotHandler {

    @Override
    public String response(String request) {
        String responseMsg = "Sorry Mr.Happy is not avaliable :(";
        MagicParameters magicParameters = new MagicParameters();
//        PandorabotsAPI papi = new PandorabotsAPI(magicParameters.getHostName(), magicParameters.getAppId(),
//                magicParameters.getUserKey(), magicParameters.isDebug());
//        System.err.println(magicParameters.getHostName() + magicParameters.getAppId()
//                + magicParameters.getUserKey() + magicParameters.isDebug());

        PandorabotsAPI papi = new PandorabotsAPI("api.pandorabots.com", "una17f03ca",
                "a7a28cfcdef9bb9402b4b25841860873", true);
        try {
            responseMsg = papi.talk("mrhappy", "yasmin", request);
            System.out.println("User: " + request);
            System.out.println("Mr Happy: " + responseMsg);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (JSONException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        return responseMsg;
    }
}
