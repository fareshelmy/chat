package chatbotTest;

/**
 *
 * @author yasmin
 */
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
//Packges notes

/* 
commons-codec-1.6.jar -> simple encoder and decoders for various formats such as Base64 and Hexadecimal and fonts :)
commons-logging-1.2.jar ->thin adapter allowing configurable bridging to other, well known logging systems ^_^
commons-io-2.4.jar -> utility classes, stream implementations, file filters, file comparators, endian transformation classes 0_0
fluent-hc-4.4-beta1.jar -> Apache HttpComponents Client fluent API
httpclient-4.4-beta1.jar -> Apache HttpComponents Client
httpcore-4.4-beta1.jar -> Apache HttpComponents Core (blocking I/O)
json-20090211.jar -> implement JSON encoders/decoders in Java. It also includes the capability to convert between JSON and 
XML, HTTP headers, Cookies, and CDL. This is a reference implementation..  WAW!
 */
public class MagicParameters {

    /**
     * Version.
     */
    private String version = "1.1.0";

    /**
     * The User Key assigned by Pandorabots.
     */
    private String userKey = "a7a28cfcdef9bb9402b4b25841860873";

    /**
     * The Application ID assigned by Pandorabots.
     */
    private String appId = "una17f03ca";

    /**
     * server name of pandorabots API
     */
    private String hostName = "mrhappy.pandorabots.com";

    /**
     * flag to indicate verbosity of output.
     */
    private boolean debug = false;

    /**
     * header for requests
     */
    private String referrer = null;

    public String getVersion() {
        return version;
    }

    public String getUserKey() {
        return userKey;
    }

    public String getAppId() {
        return appId;
    }

    public String getHostName() {
        return hostName;
    }

    public boolean isDebug() {
        return debug;
    }

    public String getReferrer() {
        return referrer;
    }

    /**
     * Constructor.
     *
     * <p>
     * Read config.txt in user.dir directory.
     * </p>
     *
     */
    public MagicParameters() {
        //String cwd = System.getProperty("user.dir");
        //readParameters(cwd + "/config.txt");
    }

    /**
     * Read parameter file.
     *
     * @param configFileName
     * @since 0.0.1
     * @edited 1.1.0
     */
    private void readParameters(String configFileName) {
        try {
            List<String> lines = readLines(configFileName,
                    Charset.defaultCharset());
            for (String line : lines) {
                String[] pair = line.split(":");
                if (pair.length >= 2) {
                    if (pair[0].equals("user_key")) {
                        // userKey = pair[1];
                        userKey = "a7a28cfcdef9bb9402b4b25841860873";
                    } else if (pair[0].equals("app_id")) {
                        //appId = pair[1];
                        appId = "una17f03ca";
                    } else if (pair[0].equals("hostname")) {
                        // hostName = pair[1];
                        hostName = "mrhappy.pandorabots.com";
                    } else if (pair[0].equals("debug")) {
                        if (pair[1].equals("true")) {
                            debug = true;
                        } else {
                            debug = false;
                        }
                    } else if (pair[0].equals("referrer")) {
                        // referrer = pair[1];

                    }
                }
            }
            if (referrer == null) {
                referrer = "pbJava";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Helper for reading file as list of lines.
     *
     * @param path
     * @param encoding
     * @return content of file as string
     * @throws IOException
     * @since 0.0.1
     */
    private List<String> readLines(String path, Charset encoding)
            throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(path), encoding);
        return lines;
    }
}
