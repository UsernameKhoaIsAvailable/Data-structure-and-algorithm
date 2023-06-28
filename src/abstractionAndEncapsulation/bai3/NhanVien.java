package abstractionAndEncapsulation.bai3;

public class NhanVien {
    private String tenNhanVien;
    private double luongCoBan;
    private double heSoLuong;
    private double luongMax;

    public double TinhLuong() {
        return luongCoBan * heSoLuong;
    }

    public NhanVien(String tenNhanVien, double luongCoBan, double heSoLuong, double luongMax) {
        this.tenNhanVien = tenNhanVien;
        this.luongCoBan = luongCoBan;
        this.heSoLuong = heSoLuong;
        this.luongMax = luongMax;
    }

    public void inTTin() {
        System.out.printf("Ten nhan vien: ", tenNhanVien);
        System.out.println();
        System.out.printf("Luong co ban: ", luongCoBan);
        System.out.println();
        System.out.printf("He so luong: ", heSoLuong);
    }

    public boolean tangLuong(double luongTang) {
        luongTang += heSoLuong;

        if(luongCoBan * luongTang > luongMax) {
            return false;
        }
        heSoLuong = luongTang;
        return true;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setLuongCoBan(double luongCoBan) {
        this.luongCoBan = luongCoBan;
    }

    public double getLuongCoBan() {
        return luongCoBan;
    }

    public void setHeSoLuong(double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public double getHeSoLuong() {
        return heSoLuong;
    }

    public void setLuongMax(double luongMax) {
        this.luongMax = luongMax;
    }

    public double getLuongMax() {
        return luongMax;
    }
}
