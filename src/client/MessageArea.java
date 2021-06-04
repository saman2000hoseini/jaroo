package client;

import javax.swing.*;
import java.awt.*;

public class MessageArea extends JPanel
{
    private static final String BTN_TXT = " Send Message ";
    JTextField textField;
    JButton btn;

    public MessageArea() throws HeadlessException {
        super();
        this.setLayout(new BorderLayout());
        textField = new JTextField();
        add(textField, BorderLayout.CENTER);
        btn = new JButton(BTN_TXT);

        add(btn, BorderLayout.EAST);
        setVisible(true);
    }

    public void setTextField(String textField) {
        this.textField.setText(textField);
    }

    public JButton getBtn() {
        return btn;
    }

    public JTextField getTextField() {
        return textField;
    }
}
