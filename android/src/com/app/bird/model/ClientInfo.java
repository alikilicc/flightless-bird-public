
package com.app.bird.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClientInfo {

    @SerializedName("mobilesdk_app_id")
    @Expose
    private String mobilesdkAppId;
    @SerializedName("android_client_info")
    @Expose
    private AndroidClientInfo androidClientInfo;

    public String getMobilesdkAppId() {
        return mobilesdkAppId;
    }

    public void setMobilesdkAppId(String mobilesdkAppId) {
        this.mobilesdkAppId = mobilesdkAppId;
    }

    public AndroidClientInfo getAndroidClientInfo() {
        return androidClientInfo;
    }

    public void setAndroidClientInfo(AndroidClientInfo androidClientInfo) {
        this.androidClientInfo = androidClientInfo;
    }

}
