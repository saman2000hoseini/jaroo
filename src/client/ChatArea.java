package client;

import javax.swing.*;

public class ChatArea extends JTextArea {
    private static final int ROWS = 10, COLUMNS = 30;

    public ChatArea() {
        super(ROWS, COLUMNS);
        this.setEditable(false);
        this.setLineWrap(true);
        setVisible(true);
    }

    public void addMessage(String username, String message) {
        this.append("\t" + username + ": " + message + "\n");
    }
}