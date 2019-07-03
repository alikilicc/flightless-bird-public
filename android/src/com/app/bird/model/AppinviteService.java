
package com.app.bird.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppinviteService {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("other_platform_oauth_client")
    @Expose
    private List<OtherPlatformOauthClient> otherPlatformOauthClient = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<OtherPlatformOauthClient> getOtherPlatformOauthClient() {
        return otherPlatformOauthClient;
    }

    public void setOtherPlatformOauthClient(List<OtherPlatformOauthClient> otherPlatformOauthClient) {
        this.otherPlatformOauthClient = otherPlatformOauthClient;
    }

}
