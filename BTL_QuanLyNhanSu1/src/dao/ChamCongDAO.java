package dao;


import dbConnection.DBConnection;
import model.ChamCong;

import java.sql.*;
import java.util.ArrayList;

public class ChamCongDAO {

    public boolean daChamCong(String maNV, String ngay) {
        String sql = "SELECT * FROM cham_cong WHERE ma_nv = ? AND ngay = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            ps.setString(2, ngay);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean ghiChamCong(String maNV, String ngay, String trangThai) {
        String sql = "INSERT INTO cham_cong(ma_nv, ngay, trang_thai) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            ps.setString(2, ngay);
            ps.setString(3, trangThai);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean capNhatChamCong(String maNV, String ngay, String trangThai) {
        String sql = "UPDATE cham_cong SET trang_thai = ? WHERE ma_nv = ? AND ngay = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            ps.setString(2, maNV);
            ps.setString(3, ngay);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public ArrayList<ChamCong> layLichSuChamCongTheoNgay(Date ngay) {
        ArrayList<ChamCong> ds = new ArrayList<>();
        String sql = "SELECT cc.ma_nv, nv.ho_ten, cc.trang_thai FROM cham_cong cc " +
                     "JOIN nhanvien nv ON cc.ma_nv = nv.ma_nv WHERE cc.ngay = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, ngay);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChamCong cc = new ChamCong();
                cc.setMaNV(rs.getString("ma_nv"));
                cc.setHoTen(rs.getString("ho_ten"));
                cc.setTrangThai(rs.getString("trang_thai"));
                cc.setNgay(ngay);
                ds.add(cc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
}

