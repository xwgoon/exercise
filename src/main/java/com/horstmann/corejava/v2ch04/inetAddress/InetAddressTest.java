package com.horstmann.corejava.v2ch04.inetAddress;

import java.io.*;
import java.net.*;

/**
 * This program demonstrates the InetAddress class. Supply a host name as command-line argument, or
 * run without command-line arguments to see the address of the local host.
 *
 * @author Cay Horstmann
 * @version 1.02 2012-06-05
 */
public class InetAddressTest {
    public static void main(String[] args) throws IOException {

        InetAddress localHostAddress = InetAddress.getLocalHost();
        System.out.println(localHostAddress);

        String host = "www.baidu.com";
        InetAddress[] addresses = InetAddress.getAllByName(host);
        for (InetAddress a : addresses)
            System.out.println(a.getHostAddress());
    }
}
