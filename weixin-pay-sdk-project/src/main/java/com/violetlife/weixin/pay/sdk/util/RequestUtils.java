package com.violetlife.weixin.pay.sdk.util;

import com.violetlife.weixin.pay.sdk.abstracts.AbstractUserDefineModel;
import com.violetlife.weixin.pay.sdk.config.ConfigurationManager;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * 描述:com.violetlife.weixin.pay.sdk.util
 * 作者:zhaoruilong
 * 时间:2016/8/3 11:26
 */
public class RequestUtils {
    public static <T extends AbstractUserDefineModel> String execute(String url, T target) throws IOException {
        String result = "";
        if (StringUtils.isNotEmpty(url) && target != null) {
            HttpPost httpPost = new HttpPost(url);
            StringEntity entity = new StringEntity(XStreamUtils.toXML(target), "UTF-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.setEntity(entity);
            CloseableHttpResponse execute = ConfigurationManager.getHttpClient().execute(httpPost);
            result = EntityUtils.toString(execute.getEntity(), Charset.forName("UTF-8"));
        }
        return result;
    }
}
