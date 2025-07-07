package controller;

import dao.NhanSuDAO;
import model.Nhansu;

import java.util.ArrayList;

public class NhanSuController {
    private NhanSuDAO nhanSuDAO;

    public NhanSuController() {
        nhanSuDAO = new NhanSuDAO();
    }

    public ArrayList<Nhansu> layDanhSachNhanSu() {
        return nhanSuDAO.layDanhSachNhanSu();
    }
    public ArrayList<Nhansu> layDanhSachDangLam() {
        return new NhanSuDAO().layDanhSachDangLam();
    }


    public boolean themNhanSu(Nhansu ns) throws Exception {
        return nhanSuDAO.themNhanSu(ns);
    }

    public boolean xoaNhanSu(String maNV) {
        return nhanSuDAO.xoaNhanSu(maNV);
    }

    public Nhansu timNhanSuTheoMa(String maNV) {
        return nhanSuDAO.timTheoMaNV(maNV);
    }

    public ArrayList<Nhansu> timKiemNhanSu(String tuKhoa, String tieuChi) {
        return nhanSuDAO.timKiem(tuKhoa, tieuChi);
    }

    public boolean capNhatNhanSu(Nhansu ns) {
        return nhanSuDAO.capNhatNhanSu(ns);
    }
    
    public String layTinhTrang(String maNV) {
        Nhansu ns = new NhanSuDAO().timTheoMaNV(maNV);
        return ns != null ? ns.getTinhTrangLamViec() : "";
    }

}
