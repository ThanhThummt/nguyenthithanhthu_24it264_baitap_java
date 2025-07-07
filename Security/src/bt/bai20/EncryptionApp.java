package bt.bai20;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class EncryptionApp extends JFrame {
    private JTextField inputField;
    private JTextArea encryptedArea;
    private JTextArea decryptedArea;
    private Encryptable encryptor;

    public EncryptionApp() {
        setTitle("Chương trình mã hóa & giải mã đa luồng");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        String[] options = { "AES", "RSA" };
        String choice = (String) JOptionPane.showInputDialog(this,
                "Chọn thuật toán mã hóa:", "Chọn thuật toán",
                JOptionPane.QUESTION_MESSAGE, null,
                options, options[0]);

        try {
            if ("AES".equals(choice)) {
                encryptor = new AESEncryptor();
            } else {
                encryptor = new RSAEncryptor();
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khởi tạo thuật toán: " + e.getMessage());
        }

        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        topPanel.add(new JLabel("Nhập dữ liệu: "), BorderLayout.WEST);
        topPanel.add(inputField, BorderLayout.CENTER);

        encryptedArea = new JTextArea(5, 40);
        decryptedArea = new JTextArea(5, 40);
        encryptedArea.setLineWrap(true);
        decryptedArea.setLineWrap(true);
        encryptedArea.setWrapStyleWord(true);
        decryptedArea.setWrapStyleWord(true);

        JButton processButton = new JButton("Mã hóa & Giải mã (đa luồng)");
        processButton.addActionListener(this::processData);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1));
        centerPanel.add(new JLabel("Dữ liệu đã mã hóa:"));
        centerPanel.add(new JScrollPane(encryptedArea));
        centerPanel.add(new JLabel("Dữ liệu đã giải mã:"));
        centerPanel.add(new JScrollPane(decryptedArea));

        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(processButton, BorderLayout.SOUTH);
    }

    private void processData(ActionEvent event) {
        String inputText = inputField.getText();
        encryptedArea.setText("");
        decryptedArea.setText("");

        if (inputText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu!");
            return;
        }

        Thread encryptionThread = new Thread(() -> {
            try {
                String encryptedText = encryptor.encrypt(inputText);
                SwingUtilities.invokeLater(() -> encryptedArea.setText(encryptedText));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread decryptionThread = new Thread(() -> {
            try {
                Thread.sleep(500); // Chờ mã hóa xong
                String encryptedText = encryptedArea.getText();
                if (!encryptedText.isEmpty()) {
                    String decryptedText = encryptor.decrypt(encryptedText);
                    SwingUtilities.invokeLater(() -> decryptedArea.setText(decryptedText));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        encryptionThread.start();
        decryptionThread.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EncryptionApp().setVisible(true);
        });
    }
}
