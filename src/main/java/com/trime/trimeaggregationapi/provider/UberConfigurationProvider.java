package com.trime.trimeaggregationapi.provider;

import com.uber.sdk.core.client.ServerTokenSession;
import com.uber.sdk.core.client.SessionConfiguration;
import com.uber.sdk.rides.client.UberRidesApi;
import com.uber.sdk.rides.client.services.RidesService;
import org.springframework.stereotype.Component;

@Component
public class UberConfigurationProvider {

    public RidesService getRidesService() {
        SessionConfiguration config = new SessionConfiguration.Builder()
                .setClientId("208qya9IOMag4takR133LUfKXIsUnKkq")
                .setServerToken("F7Atyv6f_Gx48wyiCecQJlEH27qEBeWFEzytQsBk")
                .build();

        ServerTokenSession session = new ServerTokenSession(config);

        UberRidesApi ridesApi = UberRidesApi.with(session).build();

        return ridesApi.createService();
    }
}
