package com.violetlife.weixin.pay.sdk.config;

/**
 * 描述:com.violetlife.weixin.pay.sdk.config
 * 作者:zhaoruilong
 * 时间:2016/8/3 9:51
 */
public class SDKConfiguration {
    private String appId;
    private String appSecrect;
    private String muchId;
    private String apiKey;
    private String sslPath;
    private String sslPassword;
    private boolean sslCertificate;


    public SDKConfiguration() {
    }


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecrect() {
        return appSecrect;
    }

    public void setAppSecrect(String appSecrect) {
        this.appSecrect = appSecrect;
    }

    public String getMuchId() {
        return muchId;
    }

    public void setMuchId(String muchId) {
        this.muchId = muchId;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getSslPath() {
        return sslPath;
    }

    public void setSslPath(String sslPath) {
        this.sslPath = sslPath;
    }

    public boolean isSslCertificate() {
        return sslCertificate;
    }

    public void setSslCertificate(boolean sslCertificate) {
        this.sslCertificate = sslCertificate;
    }

    public String getSslPassword() {
        return sslPassword;
    }

    public void setSslPassword(String sslPassword) {
        this.sslPassword = sslPassword;
    }
}
