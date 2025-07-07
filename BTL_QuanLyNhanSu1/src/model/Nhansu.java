package model;

import java.sql.Date;

public class Nhansu {
    private String maNV;
    private String hoTen;
    private String gioiTinh;
    private Date ngaySinh;
    private String cmnd;
    private String diaChi;
    private String soDienThoai;
    private String email;
    private String phongBan;
    private String chucVu;
    private Date ngayVaoLam;
    private String trinhDoHocVan;
    private String tinhTrangLamViec;

   
    public Nhansu() {
    }

    
    public Nhansu(String maNV, String hoTen, String gioiTinh, Date ngaySinh,
                  String cmnd, String diaChi, String soDienThoai, String email,
                  String phongBan, String chucVu, Date ngayVaoLam,
                  String trinhDoHocVan, String tinhTrangLamViec) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.cmnd = cmnd;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.phongBan = phongBan;
        this.chucVu = chucVu;
        this.ngayVaoLam = ngayVaoLam;
        this.trinhDoHocVan = trinhDoHocVan;
        this.tinhTrangLamViec = tinhTrangLamViec;
    }

    
    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhongBan() {
        return phongBan;
    }

    public void setPhongBan(String phongBan) {
        this.phongBan = phongBan;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public Date getNgayVaoLam() {
        return ngayVaoLam;
    }

    public void setNgayVaoLam(Date ngayVaoLam) {
        this.ngayVaoLam = ngayVaoLam;
    }

    public String getTrinhDoHocVan() {
        return trinhDoHocVan;
    }

    public void setTrinhDoHocVan(String trinhDoHocVan) {
        this.trinhDoHocVan = trinhDoHocVan;
    }

    public String getTinhTrangLamViec() {
        return tinhTrangLamViec;
    }

    public void setTinhTrangLamViec(String tinhTrangLamViec) {
        this.tinhTrangLamViec = tinhTrangLamViec;
    }
}

