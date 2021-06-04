package client;

import model.Handshake;
import model.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UsernameFrame extends JFrame implements ActionListener {
    private static final int WIDTH = 300, HEIGHT = 200;
    private Network ntw;
    public static ChatRoomGUI mainChatRoom;

    JTextField textField;
    JButton btn;
    public String userName;

    public static ChatRoomGUI getMainChatRoom() {
        return mainChatRoom;
    }

    public UsernameFrame() throws HeadlessException {
        super();
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        }
        this.setLayout(new BorderLayout());
        JLabel label = new JLabel("Choose Your UserName");
        add(label, BorderLayout.PAGE_START);
        textField = new JTextField();
        add(textField, BorderLayout.CENTER);
        btn = new JButton("Enter The Chat Area");
        btn.addActionListener(this);
        textField.addKeyListener(listener);
        add(btn, BorderLayout.PAGE_END);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
    }

    public String getUserName() {
        return userName;
    }

    private void actionToDo() {
        userName = textField.getText();
        setVisible(false);
        mainChatRoom = new ChatRoomGUI();

        Handshake handshake = new Handshake(userName, true);
        ntw = new Network(mainChatRoom, handshake);
        new Thread(ntw).start();

        JTextField txtField = mainChatRoom.getMessageArea().getTextField();
        txtField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER && !evt.isShiftDown()) {
                    sendMessage();
                } else if (evt.getKeyCode() == KeyEvent.VK_ENTER)
                    txtField.setText(txtField.getText() + '\n');
            }

        });
        JButton btn = mainChatRoom.getMessageArea().getBtn();
        btn.addActionListener(e -> sendMessage());
    }

    private void sendMessage() {
        String txt = mainChatRoom.getMessageArea().getTextField().getText();
        Message msg = new Message(userName, "all", txt);
        ntw.sendMsg(msg);
        mainChatRoom.getMessageArea().setTextField("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionToDo();
    }

    KeyListener listener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER)
                actionToDo();
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };
}

