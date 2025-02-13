package ua.com.abakumov.kafkaswing.connections;

import ua.com.abakumov.kafkaswing.ConnectionSettings;
import ua.com.abakumov.kafkaswing.OAuthConnectionSettings;

import java.time.Instant;

public class ConnectionSettingsManager {

    public ConnectionSettings getConnectionSettings(String connectionId) {

        if (connectionId.equals("connectionId1")) {
            OAuthConnectionSettings settings = new OAuthConnectionSettings();

            settings.setId("169d7aff-bd64-4256-83d2-5aadd0eeeb0c");
            settings.setDescription("FDT NonProd Brook cluster");
            settings.setNote("FDT NonProd Brook cluster using OAuth credentials");
            settings.setActivated(true);
            settings.setCreated(Instant.now());
            settings.setLastUpdated(Instant.now());

            settings.setClientId("0oa1ye87qpkTNHHUk0h8");
            settings.setClientSecret("yljNynwKOjcYMFmmXihqfZIGuD-9BmSnKWgBe_lKuNbPxbf3agppfciwL1NvZuF6");
            settings.setTokenEndpoint("https://nordstrom.oktapreview.com/oauth2/ausud5wgoxiBdIYLX0h7/v1/token");
            settings.setKafkaBroker("brook.nonprod.us-west-2.aws.proton.r53.nordstrom.net:9093");
            return settings;
        }

        throw new IllegalStateException("Ooops!");
    }
}
