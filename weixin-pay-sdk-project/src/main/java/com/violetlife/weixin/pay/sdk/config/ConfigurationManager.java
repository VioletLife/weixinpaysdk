package com.violetlife.weixin.pay.sdk.config;

import com.violetlife.weixin.pay.sdk.util.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * 描述:com.violetlife.weixin.pay.sdk.config
 * 作者:zhaoruilong
 * 时间:2016/8/3 9:50
 */
public class ConfigurationManager {

    private static SDKConfiguration sdkConfiguration = new SDKConfiguration();


    private static boolean isInited = false;


    /**
     * 执行需要SSL证书的HTTPS请求
     */
    private static CloseableHttpClient httpClient;

    /**
     * 执行普通HTTP请求
     */
    private static CloseableHttpClient defaultHttpClient;

    /**
     * 执行默认X509 SSL的HTTPS请求
     */
    private static CloseableHttpClient defaultNoSSLHttpClient;

    static {
        initProperties();
        initSSLContext();
        initDefaultHttpClient();
        initDefaultNoSSLHttpClient();
    }


    private static void initProperties() {
        //初始化配置信息


        Properties properties = new Properties();
        try {
            properties.load(ClassLoader.getSystemResourceAsStream("weixinpaysdk.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (properties != null) {
            sdkConfiguration.setAppId(StringUtils.convertNullToEmpty(properties.get("wxsdk.mp.appid")));
            sdkConfiguration.setAppSecrect(StringUtils.convertNullToEmpty(properties.get("wxsdk.mp.appsecret")));
            sdkConfiguration.setMuchId(StringUtils.convertNullToEmpty(properties.get("wxsdk.pay.muchid")));
            sdkConfiguration.setApiKey(StringUtils.convertNullToEmpty(properties.get("wxsdk.pay.apikey")));
            sdkConfiguration.setSslPath(StringUtils.convertNullToEmpty(properties.get("wxsdk.pay.sslpath")));
            sdkConfiguration.setSslPassword(StringUtils.convertNullToEmpty(properties.get("wxsdk.pay.sslpassword")));
            sdkConfiguration.setSslCertificate("true".equalsIgnoreCase(StringUtils.convertNullToEmpty(properties.get("wxsdk.pay.sslcertificate"))));
        }
        isInited = true;
    }

    public static void initProperties(SDKConfiguration sdkConfiguration) {
        sdkConfiguration = sdkConfiguration;
        isInited = true;
    }

    private static void initSSLContext() {
        if (StringUtils.isNotBlank(getSdkConfiguration().getSslPath())) {
            //初始化证书SSL配置
            FileInputStream inputStream = null;
            try {
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                inputStream = new FileInputStream(new File(getSdkConfiguration().getSslPath()));
                keyStore.load(inputStream, getSdkConfiguration().getSslPassword().toCharArray());
                SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(keyStore, getSdkConfiguration().getSslPassword().toCharArray())
                        .build();

                SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                        sslContext,
                        new String[]{"TLSv1"},
                        null,
                        SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER
                );
                httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory).build();
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            } finally {
                try {
                    assert inputStream != null;
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void initDefaultHttpClient() {
        defaultHttpClient = HttpClients.createDefault();
    }

    private static void initDefaultNoSSLHttpClient() {
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
            defaultNoSSLHttpClient = HttpClients.custom().setSSLSocketFactory(new SSLConnectionSocketFactory(sslContext)).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    public static SDKConfiguration getSdkConfiguration() {
        return sdkConfiguration;
    }

    public static boolean isInited() {
        return isInited;
    }


    public static CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public static CloseableHttpClient getDefaultHttpClient() {
        return defaultHttpClient;
    }

    public static CloseableHttpClient getDefaultNoSSLHttpClient() {
        return defaultNoSSLHttpClient;
    }
}
