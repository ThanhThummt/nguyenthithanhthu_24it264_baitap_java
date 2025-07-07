package model;

import java.sql.Date;

public class ChamCong {
    private String maNV;
    private String hoTen;
    private Date ngay;
    private String trangThai;

    public ChamCong() {}
    public ChamCong(String maNV, Date ngay, String trangThai) {
        this.maNV = maNV;
        this.ngay = ngay;
        this.trangThai = trangThai;
    }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    
    public Date getNgay() { return ngay; }
    public void setNgay(Date ngay2) { this.ngay = ngay2; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}
