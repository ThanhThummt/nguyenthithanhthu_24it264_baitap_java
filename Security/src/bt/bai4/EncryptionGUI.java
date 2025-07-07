package bt.bai4;

import javax.swing.*;
import java.awt.*;

public class EncryptionGUI extends JFrame {
    private JTextArea inputText;
    private JTextArea resultText;
    private JComboBox<String> algorithmSelector;
    private Encryptable encryptor;

    public EncryptionGUI() {
        setTitle("Encryption Tool - AES & RSA");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inputText = new JTextArea(5, 40);
        resultText = new JTextArea(5, 40);
        resultText.setEditable(false);

        algorithmSelector = new JComboBox<>(new String[]{"AES", "RSA"});

        JButton encryptButton = new JButton("Encrypt");
        JButton decryptButton = new JButton("Decrypt");

        encryptButton.addActionListener(e -> {
            try {
                selectEncryptor();
                String input = inputText.getText();
                String encrypted = encryptor.encrypt(input);
                resultText.setText(encrypted);
            } catch (Exception ex) {
                showError(ex);
            }
        });

        decryptButton.addActionListener(e -> {
            try {
                selectEncryptor();
                String input = resultText.getText();
                String decrypted = encryptor.decrypt(input);
                inputText.setText(decrypted);
            } catch (Exception ex) {
                showError(ex);
            }
        });

        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        topPanel.add(new JScrollPane(inputText));
        topPanel.add(new JScrollPane(resultText));

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Algorithm:"));
        controlPanel.add(algorithmSelector);
        controlPanel.add(encryptButton);
        controlPanel.add(decryptButton);

        add(topPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void selectEncryptor() throws Exception {
        String selected = (String) algorithmSelector.getSelectedItem();
        if (selected.equals("AES")) {
            encryptor = new AESEncryption();
        } else if (selected.equals("RSA")) {
            encryptor = new RSAEncryption();
        }
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EncryptionGUI::new);
    }
}
