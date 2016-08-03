package com.violetlife.weixin.pay.sdk.config;

import junit.framework.TestCase;

/**
 * 描述:com.violetlife.weixin.pay.sdk.config
 * 作者:zhaoruilong
 * 时间:2016/8/3 11:56
 */
public class ConfigurationManagerTest extends TestCase {
    public void testInitProperties() throws Exception {
        SDKConfiguration sdkConfiguration = ConfigurationManager.getSdkConfiguration();
        assertEquals("test",sdkConfiguration.getAppId());
        assertEquals("test",sdkConfiguration.getAppSecrect());
        assertEquals("test",sdkConfiguration.getMuchId());
        assertEquals("test",sdkConfiguration.getApiKey());
        assertEquals("",sdkConfiguration.getSslPath());
        assertEquals("test",sdkConfiguration.getSslPassword());
        assertTrue(sdkConfiguration.isSslCertificate());

    }

    public void testGetSdkConfiguration() throws Exception {

    }

    public void testIsInited() throws Exception {

    }

    public void testGetHttpClient() throws Exception {

    }

    public void testGetDefaultHttpClient() throws Exception {

    }

    public void testGetDefaultNoSSLHttpClient() throws Exception {

    }

}