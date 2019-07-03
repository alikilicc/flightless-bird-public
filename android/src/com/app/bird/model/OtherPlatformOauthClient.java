
package com.app.bird.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtherPlatformOauthClient {

    @SerializedName("client_id")
    @Expose
    private String clientId;
    @SerializedName("client_type")
    @Expose
    private Integer clientType;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Integer getClientType() {
        return clientType;
    }

    public void setClientType(Integer clientType) {
        this.clientType = clientType;
    }

}
