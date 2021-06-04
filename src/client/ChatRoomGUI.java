package client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChatRoomGUI extends JFrame {
    private final ArrayList<String> users = new ArrayList<>();
    ParticipantsArea participantsArea = new ParticipantsArea();
    MessageArea messageArea = new MessageArea();
    ChatArea chatBox = new ChatArea();

    public ChatRoomGUI() {
        super();
        this.setFont(new Font("serif", Font.PLAIN, 40));
        String WINDOWS_TITLE = "AUT Chat Room";
        this.setTitle(WINDOWS_TITLE);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int HEIGHT = 500;
        int WIDTH = 500;
        this.setSize(WIDTH, HEIGHT);
        int x = 850;
        int y = 470;
        this.setLocation(x, y);
        this.add(new JScrollPane(chatBox), BorderLayout.CENTER);
        this.add(participantsArea, BorderLayout.WEST);
        this.add(messageArea, BorderLayout.SOUTH);
        this.pack();
        this.setVisible(true);
    }

    public ChatArea getChatBox() {
        return chatBox;
    }

    public MessageArea getMessageArea() {
        return messageArea;
    }

    public void showGUI() {
        this.setVisible(true);
    }

    public void addNewPart(String user) {
        users.add(user);
        participantsArea.addPart(user);
    }

    public void removePart(String user) {
        try {
            users.remove(user);
            participantsArea.getModel().removeElement(user);
            participantsArea.decCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}