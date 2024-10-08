package ait.chat.client;

import ait.chat.client.task.MessageReceiver;
import ait.chat.client.task.MessageSender;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClientAppl {
    public static void main(String[] args) {
        String serverHost = "127.0.0.1"; //localhost
        int port = 9090;

        try (Socket socket = new Socket(serverHost, port)) {
            Thread receiver = new Thread(new MessageReceiver(socket));
            Thread sender = new Thread(new MessageSender(socket));
            receiver.setDaemon(true);
            receiver.start();
            sender.start();

            sender.join();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

