package dao;

import model.BaiHat;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaiHatDao {
    private final String JDBC_URL = "jdbc:mysql://localhost:3306/qlbaihat";
    private final String JDBC_USER = "root";
    private final String JDBC_PASS = "";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public List<BaiHat> getAll() {
        List<BaiHat> list = new ArrayList<>();
        String sql = "SELECT * FROM bai_hat";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                BaiHat bh = new BaiHat(
                    rs.getInt("id"),
                    rs.getString("tenBaiHat"),
                    rs.getString("caSi"),
                    rs.getString("theLoai"),
                    rs.getInt("namPhatHanh")
                );
                list.add(bh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(BaiHat bh) {
        String sql = "INSERT INTO bai_hat (tenBaiHat, caSi, theLoai, namPhatHanh) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bh.getTenBaiHat());
            ps.setString(2, bh.getCaSi());
            ps.setString(3, bh.getTheLoai());
            ps.setInt(4, bh.getNamPhatHanh());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void update(BaiHat bh) {
        String sql = "UPDATE bai_hat SET tenBaiHat=?, caSi=?, theLoai=?, namPhatHanh=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, bh.getTenBaiHat());
            ps.setString(2, bh.getCaSi());
            ps.setString(3, bh.getTheLoai());
            ps.setInt(4, bh.getNamPhatHanh());
            ps.setInt(5, bh.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM bai_hat WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<BaiHat> search(String keyword) {
        List<BaiHat> list = new ArrayList<>();
        String sql = "SELECT * FROM bai_hat WHERE tenBaiHat LIKE ? OR caSi LIKE ? OR theLoai LIKE ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ps.setString(3, pattern);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BaiHat bh = new BaiHat(
                    rs.getInt("id"),
                    rs.getString("tenBaiHat"),
                    rs.getString("caSi"),
                    rs.getString("theLoai"),
                    rs.getInt("namPhatHanh")
                );
                list.add(bh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
