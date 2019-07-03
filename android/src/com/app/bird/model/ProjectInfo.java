
package com.app.bird.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProjectInfo {

    @SerializedName("project_number")
    @Expose
    private String projectNumber;
    @SerializedName("firebase_url")
    @Expose
    private String firebaseUrl;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("storage_bucket")
    @Expose
    private String storageBucket;

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getFirebaseUrl() {
        return firebaseUrl;
    }

    public void setFirebaseUrl(String firebaseUrl) {
        this.firebaseUrl = firebaseUrl;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getStorageBucket() {
        return storageBucket;
    }

    public void setStorageBucket(String storageBucket) {
        this.storageBucket = storageBucket;
    }

}
