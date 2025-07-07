package view;

import controller.ChamCongController;
import model.ChamCong;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class LichSuChamCongDialog extends JDialog {
    private JTable table;
    private DefaultTableModel model;
    private JButton btnXem, btnDong;
    private JComboBox<Integer> cbNgay, cbThang, cbNam;

    public LichSuChamCongDialog(JFrame parent) {
        super(parent, "Lịch sử chấm công", true);
        setSize(670, 450);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        JLabel lblNhapNgay = new JLabel("Chọn ngày:");
        lblNhapNgay.setFont(new Font("Arial", Font.BOLD, 14));
        topPanel.add(lblNhapNgay);

        cbNgay = new JComboBox<>();
        cbNgay.setBackground(Color.WHITE);
        cbNgay.setPreferredSize(new Dimension(60, 30));
        cbThang = new JComboBox<>();
        cbThang.setBackground(Color.WHITE);
        cbThang.setPreferredSize(new Dimension(60, 30));
        cbNam = new JComboBox<>();
        cbNam.setBackground(Color.WHITE);
        cbNam.setPreferredSize(new Dimension(60, 30));

        // Năm từ 2020 - 2030
        for (int y = 2020; y <= 2030; y++) {
            cbNam.addItem(y);
        }

        // Tháng 1 - 12
        for (int m = 1; m <= 12; m++) {
            cbThang.addItem(m);
        }

     // Set mặc định là năm, tháng, ngày hiện tại
        int namNow = java.time.LocalDate.now().getYear();
        int thangNow = java.time.LocalDate.now().getMonthValue();
        int ngayNow = java.time.LocalDate.now().getDayOfMonth();

        cbNam.setSelectedItem(namNow);
        cbThang.setSelectedItem(thangNow);

        // Cập nhật ngày theo tháng, năm hiện tại
        updateNgayTheoThang();
        cbNgay.setSelectedItem(ngayNow);

        // Khi thay đổi tháng/năm thì cập nhật lại ngày
        cbNam.addActionListener(e -> {
            updateNgayTheoThang();
            cbNgay.setSelectedIndex(0); // chọn ngày đầu tiên sau khi thay đổi
        });

        cbThang.addActionListener(e -> {
            updateNgayTheoThang();
            cbNgay.setSelectedIndex(0);
        });


        topPanel.add(cbNgay);
        topPanel.add(cbThang);
        topPanel.add(cbNam);

        Color btncolor = new Color(110, 153, 190);
        
        ImageIcon xemIcon = new ImageIcon("src/Images/tim.png");
        Image scaledImg1 = xemIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnXem = new JButton("Xem", new ImageIcon(scaledImg1));
        btnXem.setPreferredSize(new Dimension(120, 30));
        btnXem.setBackground(btncolor);
        btnXem.setForeground(Color.WHITE);
        btnXem.setFont(new Font("Arial", Font.PLAIN, 16));
        topPanel.add(btnXem);

        add(topPanel, BorderLayout.NORTH);
        
        topPanel.add(new JLabel("Lọc trạng thái:"));
        JComboBox<String> cbTrangThai = new JComboBox<>(new String[]{"Tất cả", "Đi làm", "Nghỉ phép", "Đi muộn"});
        cbTrangThai.setBackground(Color.WHITE);
        cbTrangThai.setPreferredSize(new Dimension(100, 30));
        topPanel.add(cbTrangThai);


        model = new DefaultTableModel(new String[]{"Mã NV", "Họ tên", "Trạng thái"}, 0);
        table = new JTable(model) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                String trangThai = (String) getValueAt(row, 2); // cột "Trạng thái"

                if (trangThai != null) {
                    switch (trangThai) {
                        case "Đi làm":
                            c.setBackground(new Color(200, 255, 200)); // xanh nhạt
                            break;
                        case "Nghỉ phép":
                            c.setBackground(new Color(255, 230, 200)); // cam nhạt
                            break;
                        case "Đi muộn":
                            c.setBackground(new Color(255, 180, 180)); // đỏ nhạt
                            break;
                        default:
                            c.setBackground(Color.WHITE);
                    }
                } else {
                    c.setBackground(Color.WHITE);
                }

                return c;
            }
        };

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        table.getTableHeader().setBackground(Color.WHITE);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(Color.WHITE);
        ImageIcon dongIcon = new ImageIcon("src/Images/dong.png");
        Image scaledImg2 = dongIcon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        btnDong = new JButton("Đóng", new ImageIcon(scaledImg2));
        btnDong.setPreferredSize(new Dimension(120, 30));
        btnDong.setBackground(btncolor);
        btnDong.setForeground(Color.WHITE);
        btnDong.setFont(new Font("Arial", Font.PLAIN, 16));

        bottomPanel.add(btnDong);
        add(bottomPanel, BorderLayout.SOUTH);

        btnDong.addActionListener(e -> dispose());

        btnXem.addActionListener(e -> {
            try {
                if (cbNgay.getSelectedItem() == null || cbThang.getSelectedItem() == null || cbNam.getSelectedItem() == null) {
                    JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ ngày, tháng, năm", "Thiếu thông tin", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int ngay = (int) cbNgay.getSelectedItem();
                int thang = (int) cbThang.getSelectedItem();
                int nam = (int) cbNam.getSelectedItem();
                String ngayStr = String.format("%04d-%02d-%02d", nam, thang, ngay);

                Date ngaySql = Date.valueOf(ngayStr);
                ChamCongController controller = new ChamCongController();
                ArrayList<ChamCong> ds = controller.getLichSuChamCong(ngaySql);

                model.setRowCount(0); // clear bảng

                String locTrangThai = (String) cbTrangThai.getSelectedItem();

                for (ChamCong cc : ds) {
                    if ("Tất cả".equals(locTrangThai) || cc.getTrangThai().equals(locTrangThai)) {
                        model.addRow(new Object[]{cc.getMaNV(), cc.getHoTen(), cc.getTrangThai()});
                    }
                }

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(this, "Không có dữ liệu chấm công cho ngày " + ngayStr + " với trạng thái '" + locTrangThai + "'", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xem dữ liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

    }

    private void updateNgayTheoThang() {
        int thang = (cbThang.getSelectedItem() != null) ? (int) cbThang.getSelectedItem() : 1;
        int nam = (cbNam.getSelectedItem() != null) ? (int) cbNam.getSelectedItem() : 2020;

        int soNgay;
        switch (thang) {
            case 2:
                if ((nam % 4 == 0 && nam % 100 != 0) || (nam % 400 == 0))
                    soNgay = 29;
                else
                    soNgay = 28;
                break;
            case 4: case 6: case 9: case 11:
                soNgay = 30;
                break;
            default:
                soNgay = 31;
        }

        cbNgay.removeAllItems();
        for (int i = 1; i <= soNgay; i++) {
            cbNgay.addItem(i);
        }
    }

    public LichSuChamCongDialog(ChamCongDialog chamCongDialog) {
        this((JFrame) SwingUtilities.getWindowAncestor(chamCongDialog));
    }
}
