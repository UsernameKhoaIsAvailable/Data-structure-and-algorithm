package aggregationAndInheritance;

public class NhanVien {
    private String tenNhanVien;
    private double heSoLuong;
    public  double luongCoBan = 750000;
    public  double luongMax = 20000000;

    public NhanVien(String tenNhanVien, double heSoLuong) {
        this.heSoLuong = heSoLuong;
        this.tenNhanVien = tenNhanVien;
    }

    public double TinhLuong() {
        return luongCoBan * heSoLuong;
    }

    public void inTTin() {
        System.out.printf("Ten nhan vien: ", tenNhanVien);
        System.out.println();
        System.out.printf("Luong co ban: ", luongCoBan);
        System.out.println();
        System.out.printf("He so luong: ", heSoLuong);
        System.out.println();
        System.out.printf("Luong max: ", luongMax);
    }

    public boolean tangLuong(double luongTang) {
        luongTang += heSoLuong;

        if(luongCoBan * luongTang > luongMax) {
            return false;
        }
        heSoLuong = luongTang;
        return true;
    }
}
