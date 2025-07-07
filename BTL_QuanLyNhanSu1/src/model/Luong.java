package model;

public class Luong {
    private String maNV, hoTen, chucVu;
    private double soNgayCong;
    private double tongLuong;

    public Luong() {}

    public Luong(String maNV, String hoTen, String chucVu, double soNgayCong, double tongLuong) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.chucVu = chucVu;
        this.soNgayCong = soNgayCong;
        this.tongLuong = tongLuong;
    }

    public String getMaNV() { return maNV; }
    public void setMaNV(String maNV) { this.maNV = maNV; }

    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }

    public String getChucVu() { return chucVu; }
    public void setChucVu(String chucVu) { this.chucVu = chucVu; }

    public double getSoNgayCong() { return soNgayCong; }
    public void setSoNgayCong(double soNgayCong) { this.soNgayCong = soNgayCong; }

    public double getTongLuong() { return tongLuong; }
    public void setTongLuong(double tongLuong) { this.tongLuong = tongLuong; }
}
