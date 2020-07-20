package com.example.demo;

import com.example.demo.socket.SocketClient;
import com.example.demo.socket.SocketHelper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.jupiter.api.Test;

public class SocketTest {
    @Test
    public void socketTest() {
        SocketHelper helper = SocketHelper.getInstance("127.0.0.1", 9000);

        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setMaxTotal(10);
        genericObjectPoolConfig.setMinIdle(5);

        helper.setConfiguration(genericObjectPoolConfig);
        helper.logStatus();

        for (int i = 0; i < 30; i++) {
            SocketClient socket = helper.getSocket();
            String received = socket.execute("test message");
            System.out.println(received);
            System.out.println(helper);
            System.out.println(socket);
            helper.returnSocket(socket);
        }
    }
}
