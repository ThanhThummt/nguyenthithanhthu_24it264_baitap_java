package dao;

import dbConnection.DBConnection;
import model.Luong;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class LuongDAO {
    private Connection conn;

    public LuongDAO() {
        conn = DBConnection.getConnection();
    }

    public ArrayList<Luong> tinhLuong(int thang, int nam) {
        ArrayList<Luong> list = new ArrayList<>();

        HashMap<String, Double> heSoLuong = new HashMap<>();
        heSoLuong.put("Nhân viên", 300000.0);
        heSoLuong.put("Trưởng phòng", 500000.0);
        heSoLuong.put("Thực tập", 150000.0);

        String sql = "SELECT nv.ma_nv, nv.ho_ten, nv.chuc_vu, cc.trang_thai, COUNT(*) as so_lan " +
                     "FROM cham_cong cc JOIN nhanvien nv ON cc.ma_nv = nv.ma_nv " +
                     "WHERE MONTH(cc.ngay) = ? AND YEAR(cc.ngay) = ? " +
                     "GROUP BY nv.ma_nv, nv.ho_ten, nv.chuc_vu, cc.trang_thai";

        HashMap<String, Luong> luongMap = new HashMap<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, thang);
            ps.setInt(2, nam);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String maNV = rs.getString("ma_nv");
                String hoTen = rs.getString("ho_ten");
                String chucVu = rs.getString("chuc_vu");
                String trangThai = rs.getString("trang_thai");
                int soLan = rs.getInt("so_lan");

                Luong l = luongMap.get(maNV);
                if (l == null) {
                    double heSo = heSoLuong.getOrDefault(chucVu, 300000.0);
                    l = new Luong(maNV, hoTen, chucVu, 0, 0.0);
                    luongMap.put(maNV, l);
                }

                // Tính điểm công theo trạng thái
                switch (trangThai) {
                    case "Đi làm":
                        l.setSoNgayCong(l.getSoNgayCong() + soLan);
                        break;
                    case "Đi muộn":
                        l.setSoNgayCong(l.getSoNgayCong() + soLan * 0.75); // trừ 25%
                        break;
                    case "Nghỉ phép":
                        l.setSoNgayCong(l.getSoNgayCong() + soLan * 0.5); // trừ 50%
                        break;
                }
            }

            // Tính tổng lương sau khi gom xong
            for (Luong l : luongMap.values()) {
                double heSo = heSoLuong.getOrDefault(l.getChucVu(), 300000.0);
                l.setTongLuong(l.getSoNgayCong() * heSo);
                list.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    
    public void luuBangLuong(ArrayList<Luong> danhSach, int thang, int nam) {
        String deleteSql = "DELETE FROM bang_luong WHERE thang = ? AND nam = ?";
        String insertSql = "INSERT INTO bang_luong(ma_nv, ho_ten, chuc_vu, thang, nam, so_ngay_cong, tong_luong) " +
                           "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection()) {
            // Xoá dữ liệu cũ cùng tháng/năm
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                deleteStmt.setInt(1, thang);
                deleteStmt.setInt(2, nam);
                deleteStmt.executeUpdate();
            }

            // Thêm dữ liệu mới
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                for (Luong l : danhSach) {
                    insertStmt.setString(1, l.getMaNV());
                    insertStmt.setString(2, l.getHoTen());
                    insertStmt.setString(3, l.getChucVu());
                    insertStmt.setInt(4, thang);
                    insertStmt.setInt(5, nam);
                    insertStmt.setDouble(6, l.getSoNgayCong());
                    insertStmt.setDouble(7, l.getTongLuong());
                    insertStmt.addBatch();
                }
                insertStmt.executeBatch(); // Thực thi hàng loạt
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Luong> layLuongDaLuu(int thang, int nam) {
        ArrayList<Luong> ds = new ArrayList<>();
        String sql = "SELECT * FROM bang_luong WHERE thang = ? AND nam = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, thang);
            ps.setInt(2, nam);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Luong l = new Luong();
                l.setMaNV(rs.getString("ma_nv"));
                l.setHoTen(rs.getString("ho_ten"));
                l.setChucVu(rs.getString("chuc_vu"));
                l.setSoNgayCong(rs.getInt("so_ngay_cong"));
                l.setTongLuong(rs.getDouble("tong_luong"));
                ds.add(l);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ds;
    }


}
