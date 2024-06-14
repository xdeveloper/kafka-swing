package ua.com.abakumov.kafkaswing;

import java.time.Instant;

public class ConnectionSettingsManager {

    public ConnectionSettings getConnectionSettings(String connectionId) {

        if (connectionId.equals("conn-1")) {
            OAuthConnectionSettings settings = new OAuthConnectionSettings();
            settings.setId("conn-1");
            settings.setDescription("Connection 1");
            settings.setNote("Connection 1. I use it for testing / development purposes." +
                    " It's must be OAuth type connection");
            settings.setActivated(true);
            settings.setCreated(Instant.now());
            settings.setLastUpdated(Instant.now());

            settings.setClientId("cl-id");
            settings.setClientSecret("cl-secret");
            settings.setTokenEndpoint("t-endpoint");
            settings.setKafkaBroker("k-broker");
            return settings;
        }

        throw new IllegalStateException("Ooops!");
    }
}
