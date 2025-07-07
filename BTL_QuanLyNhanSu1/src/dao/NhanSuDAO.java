package dao;

import dbConnection.DBConnection;
import model.Nhansu;
import util.EncryptionUtil;

import java.sql.*;
import java.util.ArrayList;

public class NhanSuDAO {

    public ArrayList<Nhansu> layDanhSachNhanSu() {
        ArrayList<Nhansu> ds = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ds.add(chuyenResultSetThanhNhanSu(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public boolean tonTaiMaNV(String maNV) {
        String sql = "SELECT 1 FROM nhanvien WHERE ma_nv = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true nếu có dữ liệu
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean themNhanSu(Nhansu ns) throws Exception {
        if (tonTaiMaNV(ns.getMaNV())) {
            throw new Exception("❌ Mã nhân viên '" + ns.getMaNV() + "' đã tồn tại!");
        }

        String sql = "INSERT INTO nhanvien(ma_nv, ho_ten, gioi_tinh, ngay_sinh, cmnd, dia_chi, so_dt, email, phong_ban, chuc_vu, ngay_vao_lam, hoc_van, tinh_trang) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ganGiaTriChoPreparedStatementInsert(ps, ns);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("❌ Lỗi khi thêm nhân viên vào cơ sở dữ liệu!");
        }
    }

    public boolean xoaNhanSu(String maNV) {
        String sqlXoaChamCong = "DELETE FROM cham_cong WHERE ma_nv = ?";
        String sqlXoaBangLuong = "DELETE FROM bang_luong WHERE ma_nv = ?";
        String sqlXoaNhanVien = "DELETE FROM nhanvien WHERE ma_nv = ?";

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Bắt đầu transaction

            // 1. Xoá chấm công
            try (PreparedStatement ps1 = conn.prepareStatement(sqlXoaChamCong)) {
                ps1.setString(1, maNV);
                ps1.executeUpdate();
            }

            // 2. Xoá bảng lương
            try (PreparedStatement ps2 = conn.prepareStatement(sqlXoaBangLuong)) {
                ps2.setString(1, maNV);
                ps2.executeUpdate();
            }

            // 3. Xoá nhân viên
            int affectedRows;
            try (PreparedStatement ps3 = conn.prepareStatement(sqlXoaNhanVien)) {
                ps3.setString(1, maNV);
                affectedRows = ps3.executeUpdate();
            }

            conn.commit(); // Thành công thì commit
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean capNhatNhanSu(Nhansu ns) {
        String sql = "UPDATE nhanvien SET ho_ten=?, gioi_tinh=?, ngay_sinh=?, cmnd=?, dia_chi=?, so_dt=?, email=?, phong_ban=?, chuc_vu=?, ngay_vao_lam=?, hoc_van=?, tinh_trang=? WHERE ma_nv=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ganGiaTriChoPreparedStatementUpdate(ps, ns);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Nhansu timTheoMaNV(String maNV) {
        String sql = "SELECT * FROM nhanvien WHERE ma_nv = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maNV);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return chuyenResultSetThanhNhanSu(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Nhansu> timKiem(String tuKhoa, String tieuChi) {
        ArrayList<Nhansu> ds = new ArrayList<>();
        String sql;

        if (tieuChi.equalsIgnoreCase("Tên nhân viên")) {
            sql = "SELECT * FROM nhanvien WHERE ho_ten LIKE ?";
        } else if (tieuChi.equalsIgnoreCase("Giới tính")) {
            sql = "SELECT * FROM nhanvien WHERE gioi_tinh LIKE ?";
        } else {
            return ds;
        }

        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + tuKhoa + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ds.add(chuyenResultSetThanhNhanSu(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ds;
    }

    // ======= Các hàm tiện ích nội bộ =======

    private Nhansu chuyenResultSetThanhNhanSu(ResultSet rs) throws SQLException {
        return new Nhansu(
            rs.getString("ma_nv"),
            rs.getString("ho_ten"),
            rs.getString("gioi_tinh"),
            rs.getDate("ngay_sinh"),
            EncryptionUtil.decrypt(rs.getString("cmnd")),
            rs.getString("dia_chi"),
            rs.getString("so_dt"),
            rs.getString("email"),
            rs.getString("phong_ban"),
            rs.getString("chuc_vu"),
            rs.getDate("ngay_vao_lam"),
            rs.getString("hoc_van"),
            rs.getString("tinh_trang")
        );
    }

    private void ganGiaTriChoPreparedStatementInsert(PreparedStatement ps, Nhansu ns) throws SQLException {
        ps.setString(1, ns.getMaNV());
        ps.setString(2, ns.getHoTen());
        ps.setString(3, ns.getGioiTinh());
        ps.setDate(4, ns.getNgaySinh());
        ps.setString(5, EncryptionUtil.encrypt(ns.getCmnd())); 
        ps.setString(6, ns.getDiaChi());
        ps.setString(7, ns.getSoDienThoai());
        ps.setString(8, ns.getEmail());
        ps.setString(9, ns.getPhongBan());
        ps.setString(10, ns.getChucVu());
        ps.setDate(11, ns.getNgayVaoLam());
        ps.setString(12, ns.getTrinhDoHocVan());
        ps.setString(13, ns.getTinhTrangLamViec());
    }

    private void ganGiaTriChoPreparedStatementUpdate(PreparedStatement ps, Nhansu ns) throws SQLException {
        ps.setString(1, ns.getHoTen());        
        ps.setString(2, ns.getGioiTinh());
        ps.setDate(3, ns.getNgaySinh());
        ps.setString(4, EncryptionUtil.encrypt(ns.getCmnd()));
        ps.setString(5, ns.getDiaChi());
        ps.setString(6, ns.getSoDienThoai());
        ps.setString(7, ns.getEmail());
        ps.setString(8, ns.getPhongBan());
        ps.setString(9, ns.getChucVu());
        ps.setDate(10, ns.getNgayVaoLam());
        ps.setString(11, ns.getTrinhDoHocVan());
        ps.setString(12, ns.getTinhTrangLamViec());
        ps.setString(13, ns.getMaNV());
    }
    //==================================//
    public int demSoNhanVien() {
        String sql = "SELECT COUNT(*) FROM nhanvien";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
 // Lấy ds nhân viên Đag làm để chấm công 
    public ArrayList<Nhansu> layDanhSachDangLam() {
        ArrayList<Nhansu> ds = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE tinh_trang = 'Đang làm'";

        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
            	Nhansu ns = new Nhansu();
            	ns.setMaNV(rs.getString("ma_nv"));
            	ns.setHoTen(rs.getString("ho_ten"));
            	ns.setGioiTinh(rs.getString("gioi_tinh"));
            	ns.setNgaySinh(rs.getDate("ngay_sinh"));
            	EncryptionUtil.decrypt(rs.getString("cmnd"));
            	ns.setDiaChi(rs.getString("dia_chi"));
            	ns.setSoDienThoai(rs.getString("so_dt"));
            	ns.setEmail(rs.getString("email"));
            	ns.setPhongBan(rs.getString("phong_ban"));
            	ns.setChucVu(rs.getString("chuc_vu"));
            	ns.setNgayVaoLam(rs.getDate("ngay_vao_lam"));
            	ns.setTrinhDoHocVan(rs.getString("hoc_van"));
            	ns.setTinhTrangLamViec(rs.getString("tinh_trang"));

                ds.add(ns);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }


}