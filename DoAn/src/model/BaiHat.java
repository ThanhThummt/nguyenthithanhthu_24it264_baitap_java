package model;

public class BaiHat {
    private int id;
    private String tenBaiHat;
    private String caSi;
    private String theLoai;
    private int namPhatHanh;

    public BaiHat(int id, String tenBaiHat, String caSi, String theLoai, int namPhatHanh) {
        this.id = id;
        this.tenBaiHat = tenBaiHat;
        this.caSi = caSi;
        this.theLoai = theLoai;
        this.namPhatHanh = namPhatHanh;
    }

    public BaiHat(String tenBaiHat, String caSi, String theLoai, int namPhatHanh) {
        this(-1, tenBaiHat, caSi, theLoai, namPhatHanh);
    }

    public int getId() { return id; }
    public String getTenBaiHat() { return tenBaiHat; }
    public String getCaSi() { return caSi; }
    public String getTheLoai() { return theLoai; }
    public int getNamPhatHanh() { return namPhatHanh; }

    public void setId(int id) { this.id = id; }
    public void setTenBaiHat(String tenBaiHat) { this.tenBaiHat = tenBaiHat; }
    public void setCaSi(String caSi) { this.caSi = caSi; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }
    public void setNamPhatHanh(int namPhatHanh) { this.namPhatHanh = namPhatHanh; }
    @Override
    public String toString() {
        return tenBaiHat + " - " + caSi;
    }

}
