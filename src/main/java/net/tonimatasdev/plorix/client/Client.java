package net.tonimatasdev.plorix.client;

import java.io.*;
import java.net.Socket;

public class Client {
    public void startClient() {
        sendFile("0.0.0.0", new File(System.getProperty("user.dir") + "\\run\\client\\test.mp4"));
    }

    public void sendFile(String ip, File file) {
        try {
            Socket socket = new Socket(ip, 38528);

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
        } catch (IOException e) {
            System.out.println("Error on send file.");
        }
    }
}
