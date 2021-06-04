package server;

import model.*;

import java.io.IOException;

public class ClientHandler implements Runnable {
    private final Client client;

    public ClientHandler(Client client) throws IOException {
        this.client = client;
    }

    @Override
    public void run() {
        while (true) {
            Object request;
            try {
                request = client.getObjectInputStream().readObject();

                if (request instanceof Message)
                    handleMessage((Message) request, client);
                else if (request instanceof Handshake)
                    handleHandshake((Handshake) request, client);

            } catch (IOException | ClassNotFoundException e) {
                Server.clientsByID.remove(client.getUsername());
                Server.users.remove(client.getUsername());

                Handshake handshake = new Handshake(client.getUsername(), false);
                try {
                    sendToAllUsers(handshake);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                return;
            }
        }
    }

    private synchronized void handleMessage(Message message, Client client) throws IOException {
        if (message.getReceiver().equals("all")) {
            sendToAllUsers(message);
        } else if (Server.clientsByID.containsKey(message.getReceiver())) {
            Server.clientsByID.get(message.getReceiver()).getObjectOutputStream().writeObject(message);
        } else {
            Message msg = new Message(Server.name, client.getUsername(), userNotFound(message.getReceiver()));
            client.getObjectOutputStream().writeObject(msg);
        }
    }

    private synchronized void handleHandshake(Handshake handshake, Client client) throws IOException {
        client.getObjectOutputStream().writeObject(new OnlineUsers(Server.users));

        client.setUsername(handshake.getUsername());
        Server.users.add(handshake.getUsername());
        Server.clientsByID.put(handshake.getUsername(), client);

        sendToAllUsers(handshake);
    }

    private synchronized void sendToAllUsers(Object request) throws IOException {
        for (Client c : Server.clientsByID.values()) {
            c.getObjectOutputStream().writeObject(request);
        }
    }

    private String userNotFound(String user) {
        return String.format("Username %s not Found !!!", user);
    }
}