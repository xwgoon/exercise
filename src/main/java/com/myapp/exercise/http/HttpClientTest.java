package com.myapp.exercise.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {

    private static final ContentType TEXT_PLAIN_UTF8 = ContentType.create("text/plain", StandardCharsets.UTF_8);

    public static void main(String[] args) {
        File file = new File("C:\\Users\\Administrator\\Desktop\\人普\\ppt\\1.jpg");
        Map<String, Object> params = new HashMap<>();
        params.put("name", "张三");
        params.put("file", file);
        String res = postForm("http://localhost:8888/contextPath/xmlServletPath/controller0/post", params);
        System.out.println(res);
    }

    public static String postForm(String url, Map<String, Object> params) {
        if (StringUtils.isBlank(url)) {
            throw new IllegalArgumentException("url不能为空");
        }

        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        if (params != null) {
            params.forEach((key, value) -> {
                if (value instanceof File) {
                    builder.addBinaryBody(key, (File) value);
                } else {
                    String strVal = value == null ? null : value.toString();
                    builder.addTextBody(key, strVal, TEXT_PLAIN_UTF8);
                }
            });
        }
        httpPost.setEntity(builder.build());


        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpPost)) {
            // 即使服务器Controller中方法的返回类型是void，response.getEntity()也不会是null，
            //EntityUtils.toString(response.getEntity())的值是空字符串""。
            return EntityUtils.toString(response.getEntity());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

}
