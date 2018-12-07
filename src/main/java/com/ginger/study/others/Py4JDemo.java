package com.ginger.study.others;

/**
 * Created by Ginger on 18-2-8
 */

import py4j.GatewayServer;

import java.util.Stack;

public class Py4JDemo {

    private Stack stack;

    public Py4JDemo() {
        stack = new Stack();
        stack.push("Initial Item");
    }

    public Stack getStack() {
        return stack;
    }


    public static void main(String[] args) {
        Py4JDemo app = new Py4JDemo();

        // app is now the gateway.entry_point
        GatewayServer server = new GatewayServer(app);
        server.start();
        System.out.println("Gateway Server Started");
    }

}




