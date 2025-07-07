package controller;

import model.ChamCong;
import dao.ChamCongDAO;

import java.sql.Date;
import java.util.ArrayList;

public class ChamCongController {
    private ChamCongDAO chamCongDAO;

    public ChamCongController() {
        chamCongDAO = new ChamCongDAO();
    }

    public boolean kiemTraDaChamCong(String maNV, String ngay) {
        return chamCongDAO.daChamCong(maNV, ngay);
    }

    public void chamCong(String maNV, String ngay, String trangThai) {
        if (!chamCongDAO.daChamCong(maNV, ngay)) {
            chamCongDAO.ghiChamCong(maNV, ngay, trangThai);
        } else {
            chamCongDAO.capNhatChamCong(maNV, ngay, trangThai); // Thêm dòng này để cập nhật
        }
    }


    public ArrayList<ChamCong> getLichSuChamCong(Date ngay) {
        return chamCongDAO.layLichSuChamCongTheoNgay(ngay);
    }
}

