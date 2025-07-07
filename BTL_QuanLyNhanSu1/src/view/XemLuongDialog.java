package view;

import dao.LuongDAO;
import model.Luong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class XemLuongDialog extends JDialog {
    private JTable table;
    private DefaultTableModel model;

    public XemLuongDialog(JFrame parent, int thang, int nam) {
        super(parent, "Xem bảng lương đã lưu", true);
        setSize(700, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitle = new JLabel("Bảng lương tháng " + thang + " năm " + nam);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.add(lblTitle);
        add(titlePanel, BorderLayout.NORTH);

        // Table
        String[] cols = {"Mã NV", "Họ tên", "Chức vụ", "Tháng", "Năm", "Số ngày công", "Tổng lương"};
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        table.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 13));
		table.getTableHeader().setBackground(Color.WHITE);
        add(new JScrollPane(table), BorderLayout.CENTER);

        LuongDAO dao = new LuongDAO();
        ArrayList<Luong> danhSach = dao.layLuongDaLuu(thang, nam);
        for (Luong l : danhSach) {
            model.addRow(new Object[]{
                l.getMaNV(), l.getHoTen(), l.getChucVu(),
                thang, nam, String.format("%.2f", l.getSoNgayCong()), l.getTongLuong()
            });
        }

        if (danhSach.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu lương cho tháng/năm này.");
        }
    }
}
