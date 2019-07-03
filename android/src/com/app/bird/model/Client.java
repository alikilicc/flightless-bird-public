
package com.app.bird.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Client {

    @SerializedName("client_info")
    @Expose
    private ClientInfo clientInfo;
    @SerializedName("oauth_client")
    @Expose
    private List<OauthClient> oauthClient = null;
    @SerializedName("api_key")
    @Expose
    private List<ApiKey> apiKey = null;
    @SerializedName("services")
    @Expose
    private Services services;

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }

    public List<OauthClient> getOauthClient() {
        return oauthClient;
    }

    public void setOauthClient(List<OauthClient> oauthClient) {
        this.oauthClient = oauthClient;
    }

    public List<ApiKey> getApiKey() {
        return apiKey;
    }

    public void setApiKey(List<ApiKey> apiKey) {
        this.apiKey = apiKey;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

}
