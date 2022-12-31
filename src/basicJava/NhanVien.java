package basicJava;

public class NhanVien {
    private String tenNhanVien;
    private double luongCoBan;
    private double heSoLuong;
    private double luongMax;
    static int demSoDoiTuong = 0;

    public NhanVien(String tenNhanVien, double luongCoBan, double heSoLuong, double luongMax) {
        this.tenNhanVien = tenNhanVien;
        this.luongCoBan = luongCoBan;
        this.heSoLuong = heSoLuong;
        this.luongMax = luongMax;
        demSoDoiTuong += 1;
    }
}
