package net.tonimatasdev.plorix;

import net.tonimatasdev.plorix.client.Client;
import net.tonimatasdev.plorix.server.Server;

public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        server.startServerThread();

        Client client = new Client();
        client.startClient();
    }
}