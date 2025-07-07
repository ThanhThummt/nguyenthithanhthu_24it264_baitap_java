 







package view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox rememberMe;
    private JButton signInButton;
    private JButton cancelButton;
    private JLabel usernameError;
    private JLabel passwordError;

    public Login() {
        setTitle("Đăng Nhập Admin");
        setSize(350, 380);
        setLocationRelativeTo(null);
        setUndecorated(true); // bo viền ngoài
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setBorder(new LineBorder(Color.GRAY, 1, true)); // bo góc
        setContentPane(panel);

        JLabel signInLabel = new JLabel("Đăng nhập");
        signInLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        signInLabel.setBounds(120, 40, 200, 30);
        panel.add(signInLabel);
        
        JLabel lblUsername = new JLabel("Tên đăng nhập:");
        lblUsername.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblUsername.setBounds(50, 75, 100, 20);
        panel.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(50, 100, 250, 40);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(usernameField);

        usernameError = new JLabel("Vui lòng nhập tên đăng nhập");
        usernameError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        usernameError.setForeground(Color.RED);
        usernameError.setBounds(50, 140, 150, 15);
        usernameError.setVisible(false);
        panel.add(usernameError);

        JLabel lblPassword = new JLabel("Mật khẩu:");
        lblPassword.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblPassword.setBounds(50, 145, 100, 20);
        panel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(50, 170, 250, 40);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(passwordField);

        passwordError = new JLabel("Vui lòng nhập mật khẩu");
        passwordError.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        passwordError.setForeground(Color.RED);
        passwordError.setBounds(50, 210, 200, 15); 
        passwordError.setVisible(false);
        panel.add(passwordError);

        rememberMe = new JCheckBox("Ghi nhớ");
        rememberMe.setBounds(50, 240, 150, 25);
        rememberMe.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        rememberMe.setBackground(Color.WHITE);
        panel.add(rememberMe);

        JLabel forgotPassword = new JLabel("<HTML><U>Quên mật khẩu?</U></HTML>");
        forgotPassword.setForeground(Color.BLACK);
        forgotPassword.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        forgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        forgotPassword.setBounds(200, 240, 150, 25);
        panel.add(forgotPassword);

        signInButton = new JButton("Đăng nhập");
        signInButton.setBounds(40, 290, 130, 40);
        signInButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        signInButton.setBackground(new Color(96, 96, 96));
        signInButton.setForeground(Color.WHITE);
        signInButton.setBorder(new LineBorder(Color.GRAY, 1, true));
        signInButton.setFocusPainted(false);
        panel.add(signInButton); 
        
        cancelButton = new JButton("Thoát");
        cancelButton.setBounds(190, 290, 130, 40);
        cancelButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        cancelButton.setBackground(Color.LIGHT_GRAY);
        cancelButton.setForeground(Color.BLACK);
        cancelButton.setBorder(new LineBorder(Color.GRAY, 1, true));
        cancelButton.setFocusPainted(false);
        panel.add(cancelButton);

        // Button event
        signInButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            boolean valid = true;

            if (username.isEmpty()) {
                usernameField.setBorder(new LineBorder(Color.RED));
                usernameError.setVisible(true);
                valid = false;
            } else {
                usernameField.setBorder(UIManager.getBorder("TextField.border"));
                usernameError.setVisible(false);
            }

            if (password.isEmpty()) {
                passwordField.setBorder(new LineBorder(Color.RED));
                passwordError.setVisible(true);
                valid = false;
            } else {
                passwordField.setBorder(UIManager.getBorder("TextField.border"));
                passwordError.setVisible(false);
            }

            if (valid) {
                if (username.equals("admin") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(this, "Đăng Nhập Thành Công!");
                    new AdminForm().setVisible(true);
                    dispose();
                } else {
                	JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu!", "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        cancelButton.addActionListener(e -> {
            dispose(); // Đóng Login
            new RoleSelectionFrame().setVisible(true); // Mở lại giao diện chọn vai trò
        });


        forgotPassword.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Password recovery feature coming soon.");
            }
        });
    }

}
