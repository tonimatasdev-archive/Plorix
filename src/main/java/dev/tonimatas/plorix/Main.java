package dev.tonimatas.plorix;

import dev.tonimatas.plorix.client.Client;
import dev.tonimatas.plorix.server.Server;

public class Main {
    public static void main(String[] args) {
        if (args[0].equalsIgnoreCase("-client")) {
            Client client = new Client();
            client.startClient(args[1], args[2]);
        }

        if (!args[0].equalsIgnoreCase("-client")) {
            Server server = new Server();
            server.startServerThread();
        }


    }
}