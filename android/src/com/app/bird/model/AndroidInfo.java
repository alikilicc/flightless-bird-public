
package com.app.bird.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AndroidInfo {

    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("certificate_hash")
    @Expose
    private String certificateHash;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCertificateHash() {
        return certificateHash;
    }

    public void setCertificateHash(String certificateHash) {
        this.certificateHash = certificateHash;
    }

}
