package org.plc;



import org.jboss.logging.Logger;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.AuthDetails;
import org.plc.dto.SecurityEventDTO;
import org.plc.rest.PLCRestClient;

import java.util.Map;

public class PLCEventListener implements EventListenerProvider {
    private static final Logger log = Logger.getLogger(PLCEventListener.class);

    private PLCRestClient eventRestClient;

    public PLCEventListener() {
        eventRestClient = new PLCRestClient();
    }

    @Override
    public void onEvent(Event event) {
        log.info(" ********** Event ********");
        Map<String, String> details = event.getDetails();

        eventRestClient.saveData(convertToDTO(event));

    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
        System.out.println(" **********  ADMIN Event ********");
        AuthDetails details = adminEvent.getAuthDetails();

        System.out.println(details.getClientId());
        System.out.println(details.getIpAddress());
        System.out.println(details.getRealmId());
        System.out.println(details.getUserId());
        System.out.println(" ******************");
    }

    @Override
    public void close() {

    }

    //TODO: we also need convert de AdminEvent to DTO
    private SecurityEventDTO convertToDTO(Event event) {
        SecurityEventDTO dto = new SecurityEventDTO();
        dto.setClientID(event.getClientId());
        dto.setRealmId(event.getRealmId());
        dto.setSessionId(event.getSessionId());
        dto.setError(event.getError());
        dto.setIpAddress(event.getIpAddress());
        dto.setEventType(event.getType().name());
        dto.setTime(event.getTime());
        dto.setUserId(event.getUserId());
        // TODO: we need add the details, should this be another class?
        return dto;
    }
}
