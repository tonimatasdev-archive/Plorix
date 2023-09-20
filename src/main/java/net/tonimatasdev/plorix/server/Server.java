package net.tonimatasdev.plorix.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    Thread serverThread;

    public void startServerThread() {
        serverThread = new Thread(this);
        serverThread.start();
    }

    @Override
    public void run() {
        try {
            process();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void process() throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(38528);

        while (serverThread != null) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            if (clientSocket.isConnected()) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String fileName = bufferedReader.readLine();

                InputStream inputStream = clientSocket.getInputStream();

                new File(System.getProperty("user.dir") + "\\downloads").mkdirs();

                FileOutputStream fileOutputStream = new FileOutputStream(System.getProperty("user.dir") + "\\downloads\\" + fileName);

                byte[] buffer = new byte[1024];
                int bytesRead;

                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }

                bufferedReader.close();
                fileOutputStream.close();
                inputStream.close();
            }

            clientSocket.close();

            Thread.sleep(1000);
        }
    }
}
