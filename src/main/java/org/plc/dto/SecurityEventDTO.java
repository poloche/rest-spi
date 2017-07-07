package org.plc.dto;


public class SecurityEventDTO {
    private String clientID;
    private String realmId;
    private String sessionId;
    private String error;
    private String ipAddress;
    private String eventType;
    private Long time;
    private String userId;

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getClientID() {
        return clientID;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getRealmId() {
        return realmId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }
}
