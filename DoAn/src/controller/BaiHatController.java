package controller;

import dao.BaiHatDao;
import model.BaiHat;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class BaiHatController {
    private BaiHatDao dao = new BaiHatDao();

    public List<BaiHat> layTatCaBaiHat() {
        return dao.getAll();
    }

    public boolean themBaiHat(String tenBaiHat, String caSi, String theLoai, String namPhatHanh) {
        try {
            int nam = Integer.parseInt(namPhatHanh);
            BaiHat bh = new BaiHat(0, tenBaiHat, caSi, theLoai, nam);
            dao.insert(bh);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaBaiHat(int id, String tenBaiHat, String caSi, String theLoai, String namPhatHanh) {
        try {
            int nam = Integer.parseInt(namPhatHanh);
            BaiHat bh = new BaiHat(id, tenBaiHat, caSi, theLoai, nam);
            dao.update(bh);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaBaiHat(int id) {
        try {
            dao.delete(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<BaiHat> timKiemBaiHat(String keyword) {
        return dao.search(keyword);
    }
    private List<BaiHat> danhSachBaiHat;

    public void loadBaiHat() {
        danhSachBaiHat = dao.getAll(); 
    }

    public BaiHat timBaiHatTheoId(int id) {
        if (danhSachBaiHat == null) {
            loadBaiHat(); 
        }

        for (BaiHat bh : danhSachBaiHat) {
            if (bh.getId() == id) {
                return bh;
            }
        }
        return null;
    }

   
}
