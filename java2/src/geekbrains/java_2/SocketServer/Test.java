package geekbrains.java_2.SocketServer;

import java.util.HashMap;

/**
 * Created by Bro on 17.10.2016.
 */
public class Test {
    public static void main(String[] args) {
        HashMap msg = new HashMap();
            msg.put("Broadcast","BR");
            msg.put("Login_result","LR");
            msg.put("Alert","A");
        System.out.println( msg.get("Login_resulqt"));
    }
}
