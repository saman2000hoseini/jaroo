package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private String username;
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public Client(Socket client) throws IOException {
        objectInputStream = new ObjectInputStream(client.getInputStream());
        objectOutputStream = new ObjectOutputStream(client.getOutputStream());
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }
}
