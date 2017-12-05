package com.horstmann.corejava.v2ch04;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Test {

    public static void main(String[] args) throws URISyntaxException {

//        URI uri=new URI("http://user-info@127.0.0.1:8080/java/test/?id=1&name=2#fragment");
//
//        System.out.println(uri.getScheme());
//
//        System.out.println(uri.getSchemeSpecificPart());
//        System.out.println(uri.getAuthority());
//        System.out.println(uri.getUserInfo());
//        System.out.println(uri.getHost());
//        System.out.println(uri.getPort());
//        System.out.println(uri.getPath());
//        System.out.println(uri.getQuery());
//
//        System.out.println(uri.getFragment());

//        URI uri0 = new URI("http://docs.mycompany.com/api/java");
//        URI uri1 = new URI("http://docs.mycompany.com/api/java/net/Socket.html#Socket()");
//        URI uri2 = new URI("http://docs.mycompany.com/api/java/net/ServerSocket.html");
//
//        URI relative = uri0.relativize(uri2);
//        System.out.println(relative);
//
//        URI combined = uri0.resolve(relative);
//        System.out.println(combined);

        String s = "Aa0-_.~ 好@";

        Base64.Encoder encoder = Base64.getEncoder();
        String encodeString = encoder.encodeToString(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("encodeString: " + encodeString);

        Base64.Decoder decoder = Base64.getDecoder();
        String decodeString = new String(decoder.decode(encodeString), StandardCharsets.UTF_8);
        System.out.println("decodeString: " + decodeString);

        try {
            String urlEncodeString = URLEncoder.encode(s, "UTF-8");
            System.out.println("urlEncodeString: " + urlEncodeString);

            String urlDecodeString = URLDecoder.decode(urlEncodeString, "UTF-8");
            System.out.println("urlDecodeString: " + urlDecodeString);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println('\u007E');


    }
}
