### PLC-event-listener

This module create an SPI for login events that will send the information to our rest api

## registration
because we have more extensions we need register this as a module, to do that we need follow those steps


* register the module
  ```bash
      sh $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add \
       --name=com.saasforge.keycloak.chassi-event-listener \
       --resources=modules/chassi-event-listener-1.0-SNAPSHOT.jar  \
       --dependencies=org.keycloak.keycloak-core,org.keycloak.keycloak-server-spi,org.keycloak.keycloak-server-spi-private,javax.api,javax.ws.rs.api,org.jboss.resteasy.resteasy-jaxrs,org.jboss.logging"
  ```
* configure the module, open keycloak-server file
    ```bash
         vi $KEYCLOAK_HOME/standalone/configuration/keycloak-server.json
    ```
* add the new modules
    ```json
    {
    "providers": [
        "classpath:${jboss.home.dir}/providers/*",
        "module:com.saasforge.keycloak.chassi-event-listener"
    ],
    "other_properties":"..."
  }
    ```