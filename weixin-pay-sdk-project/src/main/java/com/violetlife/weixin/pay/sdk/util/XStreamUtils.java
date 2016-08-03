package com.violetlife.weixin.pay.sdk.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;

/**
 * 描述:com.violetlife.weixin.pay.sdk.util
 * 作者:zhaoruilong
 * 时间:2016/8/3 11:28
 */
public class XStreamUtils {
    /**
     * 将XML字符串转换为T 对象  (仅为最简单情况)
     * @param xml
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> T fromXml(String xml, Class<T> tClass) {
        XStream xStream = new XStream();
        xStream.alias("xml", tClass);
        xStream.ignoreUnknownElements();
        Object fromXML = xStream.fromXML(xml);
        if (fromXML != null && fromXML.getClass().isAssignableFrom(tClass)) {
            return (T) fromXML;
        }
        return null;
    }

    /**
     * 将target对象转换为字符串 (仅为最简单情况)
     * @param target
     * @param <T>
     * @return
     */
    public static <T> String toXML(T target) {
        XStream xStream = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("_-", "_")));
        xStream.alias("xml", target.getClass());
        xStream.processAnnotations(target.getClass());
        return xStream.toXML(target);
    }
}
