
package com.app.bird.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Services {

    @SerializedName("analytics_service")
    @Expose
    private AnalyticsService analyticsService;
    @SerializedName("appinvite_service")
    @Expose
    private AppinviteService appinviteService;
    @SerializedName("ads_service")
    @Expose
    private AdsService adsService;

    public AnalyticsService getAnalyticsService() {
        return analyticsService;
    }

    public void setAnalyticsService(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    public AppinviteService getAppinviteService() {
        return appinviteService;
    }

    public void setAppinviteService(AppinviteService appinviteService) {
        this.appinviteService = appinviteService;
    }

    public AdsService getAdsService() {
        return adsService;
    }

    public void setAdsService(AdsService adsService) {
        this.adsService = adsService;
    }

}
