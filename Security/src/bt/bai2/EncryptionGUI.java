package bt.bai2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EncryptionGUI extends JFrame {
    private JTextArea inputTextArea, outputTextArea;
    private JComboBox<String> algorithmComboBox;
    private JButton encryptButton, decryptButton;

    private Encryptable aes = new AESEncryption();
    private Encryptable rsa;

    public EncryptionGUI() {
        setTitle("Encryption GUI - AES & RSA");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            rsa = new RSAEncryption();
        } catch (Exception e) {
            e.printStackTrace();
        }

        inputTextArea = new JTextArea(5, 30);
        outputTextArea = new JTextArea(5, 30);
        outputTextArea.setEditable(false);

        algorithmComboBox = new JComboBox<>(new String[]{"AES", "RSA"});

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(new JScrollPane(inputTextArea));
        topPanel.add(new JScrollPane(outputTextArea));

        JPanel centerPanel = new JPanel();
        centerPanel.add(new JLabel("Algorithm:"));
        centerPanel.add(algorithmComboBox);
        centerPanel.add(encryptButton);
        centerPanel.add(decryptButton);

        add(topPanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.SOUTH);

        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(true);
            }
        });

        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(false);
            }
        });

        setVisible(true);
    }

    private void performOperation(boolean isEncrypt) {
        String input = inputTextArea.getText();
        String algorithm = (String) algorithmComboBox.getSelectedItem();
        Encryptable encryptor = algorithm.equals("AES") ? aes : rsa;

        try {
            String result = isEncrypt ?
                    encryptor.encrypt(input) :
                    encryptor.decrypt(input);
            outputTextArea.setText(result);
        } catch (Exception ex) {
            outputTextArea.setText("Lỗi: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new EncryptionGUI();
    }
}

