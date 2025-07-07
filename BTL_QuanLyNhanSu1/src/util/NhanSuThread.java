package util;

import javax.swing.table.DefaultTableModel;
import dbConnection.DBConnection;
import model.Nhansu;

import java.sql.*;
import java.util.Vector;

public class NhanSuThread extends Thread {
    private DefaultTableModel tableModel;

    public NhanSuThread(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }

    @Override
    public void run() {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT ma_nv, ho_ten, gioi_tinh, ngay_sinh, phong_ban, chuc_vu, ngay_vao_lam, tinh_trang FROM nhanvien";
            try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                tableModel.setRowCount(0);

                while (rs.next()) {
                    Vector<String> row = new Vector<>();
                    row.add(rs.getString("ma_nv"));
                    row.add(rs.getString("ho_ten"));
                    row.add(rs.getString("gioi_tinh"));
                    row.add(rs.getString("ngay_sinh"));
                    row.add(rs.getString("phong_ban"));
                    row.add(rs.getString("chuc_vu"));
                    row.add(rs.getString("ngay_vao_lam"));
                    row.add(rs.getString("tinh_trang"));

                    tableModel.addRow(row);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
