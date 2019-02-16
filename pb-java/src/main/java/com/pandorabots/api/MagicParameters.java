/**
 * MagicParameters.java
 */
package com.pandorabots.api;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

//Packges notes by us ^_^ 

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
/**
 * Global storage for Magic Parameters associated with PandorabotsAPI.
 * <p>
 * Created by User on 7/10/2014.<br>
 * <ul>
 * <li>One need to create a parameter file called config.txt in the directory
 * where the Java program runs.
 * <li>The parameters file config.txt should contain one parameter per line,
 * with the format parametername:value e.g.
 * <table>
 * <tr>
 * <td>user_key:f0123456789abcdef0123456789abcde</td>
 * </tr>
 * <tr>
 * <td>app_id:1234567890123</td>
 * </tr>
 * <tr>
 * <td>hostname:aiaas.pandorabots.com</td>
 * </tr>
 * <tr>
 * <td>debug:true</td>
 * </tr>
 * </table>
 * <li>The user_key and app_id are provided at developer.pandorabots.com as
 * "User Key" and "Application ID respectively.
 * </ul>
 * </p>
 *
 * @author Richard Wallace
 * @edited by Aadish Joshi
 * @since 0.0.1
 * @edited 1.1.0
 */
public class MagicParameters {

    /**
     * Version.
     */
    private String version = "1.1.0";

    /**
     * The User Key assigned by Pandorabots.
     */
    private String userKey = "";

    /**
     * The Application ID assigned by Pandorabots.
     */
    private String appId = "";

    /**
     * server name of pandorabots API
     */
    private String hostName = "";

    /**
     * flag to indicate verbosity of output.
     */
    private boolean debug = false;

    /**
     * header for requests
     */
    private String referrer = null;

    // added by us ^_^
    private String botName = null;

    public String getBotName() {
        return botName;
    }

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @return the userKey
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @return the debug
     */
    public boolean isDebug() {
        return debug;
    }

    /**
     * @return the referrer
     */
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
        String cwd = System.getProperty("user.dir");
        readParameters("/pandoraBotsConfig.txt");
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
                        userKey = pair[1];
                    } else if (pair[0].equals("app_id")) {
                        appId = pair[1];
                    } else if (pair[0].equals("hostname")) {
                        hostName = pair[1];
                    } else if (pair[0].equals("debug")) {
                        if (pair[1].equals("true")) {
                            debug = true;
                        } else {
                            debug = false;
                        }
                    } else if (pair[0].equals("referrer")) {
                        referrer = pair[1];

                    } // add by us ^_^
                    else if (pair[0].equals("botname")) {
                        botName = pair[1];
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
