package client;

import model.Handshake;
import model.Message;
import model.OnlineUsers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network implements Runnable {
    private final ChatRoomGUI mainGUI;
    private ObjectOutputStream outStream;
    private ObjectInputStream inputStream;
    private Socket client;

    public Network(ChatRoomGUI mainGUI, Handshake handshake) {
        this.mainGUI = mainGUI;
        String serverName = "localhost";
        int port = 6500;
        try {
            client = new Socket(serverName, port);
            outStream = new ObjectOutputStream(client.getOutputStream());

            sendMsg(handshake);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Object request) {
        try {
            outStream.writeObject(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            inputStream = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                Object request = inputStream.readObject();

                if (request instanceof Message)
                    mainGUI.getChatBox().addMessage(((Message) request).getSender(), ((Message) request).getMessage());
                else if (request instanceof Handshake)
                    if (((Handshake) request).joined())
                        mainGUI.addNewPart(((Handshake) request).getUsername());
                    else
                        mainGUI.removePart(((Handshake) request).getUsername());
                else if (request instanceof OnlineUsers) {
                    for (String user : ((OnlineUsers) request).getUsers()) {
                        mainGUI.addNewPart(user);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}