package org.plc.rest;


import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.jboss.logging.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.plc.dto.SecurityEventDTO;

import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class PLCRestClient {

    private static final Logger log = Logger.getLogger(PLCRestClient.class);

    private RestConfig config;

    private ResteasyWebTarget webTarget;

    public PLCRestClient() {
        config = new RestConfig();

        ResteasyClient client = new ResteasyClientBuilder().build();
        client.register((ClientRequestFilter) requestContext -> {
            log.info(requestContext.getHeaders());
            log.info(requestContext.getHeaderString(HttpHeaders.CONTENT_TYPE));
            log.info(requestContext.getMethod());
            log.info(requestContext.getUri());
            log.info(requestContext.getEntity());
        });

        webTarget = client.target(config.getBaseUri());
    }

    public void saveData(SecurityEventDTO securityEventDTO){
        log.info("preparing request with path " + config.getSavePath());

        Invocation.Builder builder = webTarget.path(config.getSavePath()).request(MediaType.APPLICATION_JSON);
        Entity<SecurityEventDTO> body = Entity.json(securityEventDTO);
        Response response = builder.buildPost(body).invoke();

        if (response.getStatus() == HttpStatus.SC_OK) {
            log.info("data received ");
            SecurityEventDTO bodyResponse = response.readEntity(SecurityEventDTO.class);
        } else {
            log.error("cant save data-lake event info response status " + response.getStatus());
        }
    }

}
