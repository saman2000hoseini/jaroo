package server;

import model.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Vector;

public class Server implements Runnable {
    protected static final String name = "Server";

    private final ServerSocket serverSocket;
    static HashMap<String, Client> clientsByID;
    static Vector<String> users = new Vector<>();

    public Server() throws IOException {
        int port = 6500;
        serverSocket = new ServerSocket(port);
        clientsByID = new HashMap<>();
    }

    public static void main(String[] args) {
        try {
            Thread t = new Thread(new Server());
            t.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Client client = new Client(serverSocket.accept());
                ClientHandler clientHandler = new ClientHandler(client);

                new Thread(clientHandler).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}