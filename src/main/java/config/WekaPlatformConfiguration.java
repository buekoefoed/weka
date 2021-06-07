package config;

import io.dropwizard.Configuration;

public class WekaPlatformConfiguration extends Configuration {
    private String baseURL;

    public String getBaseURL() {
        return baseURL;
    }

    public void setBaseURL(String baseURL) {
        this.baseURL = baseURL;
    }
}
