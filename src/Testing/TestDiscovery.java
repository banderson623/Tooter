package Testing;

import com.digitalxyncing.communication.Sniffy;
import com.digitalxyncing.communication.impl.ZmqSniffy;

import java.util.List;

/**
 * User: brian_anderson
 * Date: 4/29/13
 * <p/>
 * Add some readme here about how this operates
 */
public class TestDiscovery {

    public static void main(String[] args) {


        Sniffy sniff = new ZmqSniffy();
        List<String> hosts =  sniff.discoverXyncersOnPort(5050);


        System.out.println("Found: " + hosts.size() + " hosts");
        for(String h : hosts){
            System.out.println(h);
        }
    }
}
