package controller;

import java.util.ArrayList;

import dao.LuongDAO;
import dao.NhanSuDAO;
import model.Luong;

public class ThongKeController {
    private NhanSuDAO nhanSuDAO = new NhanSuDAO();
    private LuongDAO luongDAO = new LuongDAO();

    public int demTongNhanVien() {
        return nhanSuDAO.demSoNhanVien();
    }
}
