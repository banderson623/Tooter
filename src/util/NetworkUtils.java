package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtils {

   public static String getIpAddress() {
       try {
           return InetAddress.getLocalHost().getHostAddress();
       } catch (UnknownHostException e) {
           System.err.println(e);
           return null;
       }
   }

}
