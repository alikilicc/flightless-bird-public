
package com.app.bird.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatabaseObject {

    @SerializedName("project_info")
    @Expose
    private ProjectInfo projectInfo;
    @SerializedName("client")
    @Expose
    private List<Client> client = null;
    @SerializedName("configuration_version")
    @Expose
    private String configurationVersion;

    public ProjectInfo getProjectInfo() {
        return projectInfo;
    }

    public void setProjectInfo(ProjectInfo projectInfo) {
        this.projectInfo = projectInfo;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }

    public String getConfigurationVersion() {
        return configurationVersion;
    }

    public void setConfigurationVersion(String configurationVersion) {
        this.configurationVersion = configurationVersion;
    }

}
