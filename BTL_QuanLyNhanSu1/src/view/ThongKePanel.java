package view;

import dao.NhanSuDAO;
import dao.LuongDAO;
import model.Nhansu;
import model.Luong;
import controller.LuongController;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ThongKePanel extends JPanel {
    private JLabel lblTongNV;
    private JTextArea taPhongBan, taChucVu, taTinhTrang;
    private JButton btnLamMoi;
    private JPanel chartLuongPanel;

    public ThongKePanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        lblTongNV = new JLabel();
        lblTongNV.setFont(new Font("Arial", Font.BOLD, 18));
        lblTongNV.setForeground(new Color(0, 102, 204));
        topPanel.add(lblTongNV, BorderLayout.WEST);

        btnLamMoi = new JButton("Làm mới thống kê");
        btnLamMoi.setBackground(new Color(76, 138, 184));
        btnLamMoi.setForeground(Color.WHITE);
        btnLamMoi.addActionListener(e -> capNhatThongKe());
        topPanel.add(btnLamMoi, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // ==== CENTER: Thống kê theo phòng ban, chức vụ, tình trạng ====
        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        centerPanel.setBackground(Color.WHITE);

        taPhongBan = new JTextArea();
        taChucVu = new JTextArea();
        taTinhTrang = new JTextArea();

        JScrollPane sp1 = new JScrollPane(taPhongBan);
        JScrollPane sp2 = new JScrollPane(taChucVu);
        JScrollPane sp3 = new JScrollPane(taTinhTrang);

        sp1.setBorder(BorderFactory.createTitledBorder("Theo phòng ban"));
        sp2.setBorder(BorderFactory.createTitledBorder("Theo chức vụ"));
        sp3.setBorder(BorderFactory.createTitledBorder("Tình trạng làm việc"));

        centerPanel.add(sp1);
        centerPanel.add(sp2);
        centerPanel.add(sp3);

        add(centerPanel, BorderLayout.CENTER);

        // ==== SOUTH: Biểu đồ lương theo tháng ====
        chartLuongPanel = new JPanel(new BorderLayout());
        chartLuongPanel.setBackground(Color.WHITE);
        chartLuongPanel.setBorder(BorderFactory.createTitledBorder("Biểu đồ lương nhân viên theo tháng"));
        add(chartLuongPanel, BorderLayout.SOUTH);
        capNhatThongKe();
    }

    public void capNhatThongKe() {
        int thang = java.time.LocalDate.now().getMonthValue();
        int nam = java.time.LocalDate.now().getYear();

        LuongController luongController = new LuongController();
        ArrayList<Luong> danhSachLuongMoi = luongController.tinhLuong(thang, nam);

        LuongDAO luongDAO = new LuongDAO();
        luongDAO.luuBangLuong(danhSachLuongMoi, thang, nam);

        NhanSuDAO nhanSuDAO = new NhanSuDAO();
        ArrayList<Nhansu> dsNhanSu = nhanSuDAO.layDanhSachNhanSu();

        lblTongNV.setText("Tổng số nhân viên: " + dsNhanSu.size());
        hienThiThongKe(dsNhanSu);
        hienThiBieuDoLuong(danhSachLuongMoi);
    }

    private void hienThiThongKe(ArrayList<Nhansu> ds) {
        HashMap<String, Integer> mapPhong = new HashMap<>();
        HashMap<String, Integer> mapChucVu = new HashMap<>();
        HashMap<String, Integer> mapTinhTrang = new HashMap<>();

        for (Nhansu ns : ds) {
            mapPhong.put(ns.getPhongBan(), mapPhong.getOrDefault(ns.getPhongBan(), 0) + 1);
            mapChucVu.put(ns.getChucVu(), mapChucVu.getOrDefault(ns.getChucVu(), 0) + 1);
            mapTinhTrang.put(ns.getTinhTrangLamViec(), mapTinhTrang.getOrDefault(ns.getTinhTrangLamViec(), 0) + 1);
        }

        taPhongBan.setText("• Thống kê theo phòng ban:\n");
        for (String key : mapPhong.keySet()) {
            taPhongBan.append("- " + key + ": " + mapPhong.get(key) + "\n");
        }

        taChucVu.setText("• Thống kê theo chức vụ:\n");
        for (String key : mapChucVu.keySet()) {
            taChucVu.append("- " + key + ": " + mapChucVu.get(key) + "\n");
        }

        taTinhTrang.setText("• Thống kê tình trạng làm việc:\n");
        for (String key : mapTinhTrang.keySet()) {
            taTinhTrang.append("- " + key + ": " + mapTinhTrang.get(key) + "\n");
        }

        taPhongBan.setEditable(false);
        taChucVu.setEditable(false);
        taTinhTrang.setEditable(false);
    }

    private void hienThiBieuDoLuong(ArrayList<Luong> danhSachLuong) {
        chartLuongPanel.removeAll();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Luong l : danhSachLuong) {
            dataset.addValue(l.getTongLuong(), "Lương", l.getMaNV());
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Lương nhân viên", "Mã NV", "Tổng lương (VNĐ)",
            dataset, PlotOrientation.VERTICAL, false, true, false
        );

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 370)); 
        chartLuongPanel.add(chartPanel, BorderLayout.CENTER);
        chartLuongPanel.revalidate();
        chartLuongPanel.repaint();
    }
} 
