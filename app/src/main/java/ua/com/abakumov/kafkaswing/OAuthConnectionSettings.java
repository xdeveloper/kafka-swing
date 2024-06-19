package ua.com.abakumov.kafkaswing;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

public class OAuthConnectionSettings extends ConnectionSettings {
    private String clientId;
    private String clientSecret;
    private String tokenEndpoint;
    private String kafkaBroker;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getKafkaBroker() {
        return kafkaBroker;
    }

    public void setKafkaBroker(String kafkaBroker) {
        this.kafkaBroker = kafkaBroker;
    }


    @Override
    public Set<UIModel> getUIModel() {
        Set<UIModel> baseModel = super.getUIModel();

        var model = new LinkedHashSet<UIModel>();

        model.add(new UIModel(Type.String, "Client ID", Optional.of((v) -> this.setClientId(v.toString())), this::getClientId));
        model.add(new UIModel(Type.String, "Client Secret", Optional.of((v) -> this.setClientSecret(v.toString())), this::getClientSecret));
        model.add(new UIModel(Type.String, "Token Endpoint", Optional.of((v) -> this.setTokenEndpoint(v.toString())), this::getTokenEndpoint));
        model.add(new UIModel(Type.String, "Kafka Broker", Optional.of((v) -> this.setKafkaBroker(v.toString())), this::getKafkaBroker));

        baseModel.addAll(model);

        return baseModel;
    }
}
