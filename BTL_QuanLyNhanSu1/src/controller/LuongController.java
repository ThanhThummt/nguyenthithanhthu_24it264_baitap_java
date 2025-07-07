package controller;

import model.Luong;

import java.util.ArrayList;

import dao.LuongDAO;

public class LuongController {
    private LuongDAO luongDAO;

    public LuongController() {
        luongDAO = new LuongDAO();
    }

    public ArrayList<Luong> tinhLuong(int thang, int nam) {
        return luongDAO.tinhLuong(thang, nam);
    }
}
