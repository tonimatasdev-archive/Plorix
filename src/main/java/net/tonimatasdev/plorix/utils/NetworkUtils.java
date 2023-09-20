package net.tonimatasdev.plorix.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetworkUtils {
    public static String getPrivateIp() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();

                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();

                    if (inetAddress.isSiteLocalAddress() && !inetAddress.isLoopbackAddress()) {
                        String localIP = inetAddress.getHostAddress();
                        System.out.println("Starting client in " + localIP);
                        return localIP;
                    }
                }
            }

            System.out.println("Error on get the private ip.");
            return null;
        } catch (SocketException e) {
            System.out.println("Error on get the private ip.");
            return null;
        }
    }
}
