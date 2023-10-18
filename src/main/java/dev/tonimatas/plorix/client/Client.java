package dev.tonimatas.plorix.client;

import java.io.*;
import java.net.Socket;

public class Client {
    public void startClient(String ip, String path) {
        sendFile(ip, new File(path));
    }

    public void sendFile(String ip, File file) {
        try {
            Socket socket = new Socket(ip, 38528);
            System.out.println("Connected to server: " + ip);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(file.getName());

            FileInputStream fileInputStream = new FileInputStream(file);
            OutputStream outputStream = socket.getOutputStream();

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            printWriter.close();
            fileInputStream.close();
            outputStream.close();

            System.out.println("File sent: " + file.getName());
        } catch (IOException e) {
            System.out.println("Error on send file.");
        }
    }
}
