package org.plc.rest;


import org.jboss.logging.Logger;

public class RestConfig {

    private static final Logger log = Logger.getLogger(RestConfig.class);
    private String protocol;
    private String address;
    private String port;
    private String savePath;

    public RestConfig() {
        protocol = getEnvVar("EVENT_API_PROTOCOL", "http");
        address = getEnvVar("EVENT_API_ADDRESS", "0.0.0.0");
        port = getEnvVar("EVENT_API_PORT", "8080");
        savePath = getEnvVar("EVENT_API_SAVE_PATH", "/api/v1/security/events/");
    }

    private String getEnvVar(String environmentVariable, String defaultValue) {
        return System.getenv(environmentVariable) == null ? defaultValue : System.getenv(environmentVariable);
    }

    public String getProtocol() {
        return protocol;
    }

    public String getAddress() {
        return address;
    }

    public String getPort() {
        return port;
    }

    public String getSavePath() {
        return savePath;
    }

    public String getBaseUri() {

        String pattern = "%s://%s:%s";
        String uri = String.format(pattern, protocol, address, port);
        log.info("Base URI for rest".concat(uri));
        return uri;
    }
}
