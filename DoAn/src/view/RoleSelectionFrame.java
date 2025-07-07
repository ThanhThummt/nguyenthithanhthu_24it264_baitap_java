package view;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RoleSelectionFrame extends JFrame {

    public RoleSelectionFrame() {
        setTitle("Chọn vai trò đăng nhập");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // ========== Panel Trái: Hình ảnh ==========
        ImageIcon icon = new ImageIcon("src/Images/vaitro.jpg"); 
        Image scaledImg = icon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImg));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(Color.WHITE);
        imagePanel.add(imageLabel, BorderLayout.CENTER);

        // ========== Panel Phải: Chọn vai trò ==========
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBackground(Color.WHITE);
        rightPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

        // Tiêu đề
        JLabel titleLabel = new JLabel("CHỌN VAI TRÒ ĐĂNG NHẬP");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));

        // Nút Quản lí
        Border border = BorderFactory.createLineBorder(new Color(33, 150, 243), 2, true);
        JButton btnAdmin = new JButton("QUẢN LÍ");
        btnAdmin.setFont(new Font("Arial", Font.PLAIN, 16));
        btnAdmin.setBackground(Color.WHITE);
        btnAdmin.setBorder(border);
        btnAdmin.setFocusPainted(false);
        btnAdmin.setMaximumSize(new Dimension(190, 40));
        btnAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdmin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Nút Người dùng
        JButton btnUser = new JButton("NGƯỜI DÙNG");
        btnUser.setFont(new Font("Arial", Font.PLAIN, 16));
        btnUser.setBackground(Color.WHITE);
        btnUser.setBorder(border);
        btnUser.setFocusPainted(false);
        btnUser.setMaximumSize(new Dimension(190, 40));
        btnUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnUser.setCursor(new Cursor(Cursor.HAND_CURSOR));

        
        btnAdmin.addActionListener((ActionEvent e) -> {
            dispose();
            new Login().setVisible(true);
        });

        btnUser.addActionListener((ActionEvent e) -> {
            dispose();
            new UserForm().setVisible(true);
        });

        rightPanel.add(titleLabel);
        rightPanel.add(Box.createVerticalStrut(40));
        rightPanel.add(btnAdmin);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        rightPanel.add(btnUser);

        mainPanel.add(imagePanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

}
