package aggregationAndInheritance;

public class NhanVien {
    private String tenNhanVien;
    private double heSoLuong;
    public static final double LUONG_CO_BAN = 750000;
    public static final double LUONG_MAX = 20000000;

    public NhanVien(String tenNhanVien, double heSoLuong) {
        this.heSoLuong = heSoLuong;
        this.tenNhanVien = tenNhanVien;
    }

    public double TinhLuong() {
        return LUONG_CO_BAN * heSoLuong;
    }

    public void inTTin() {
        System.out.printf("Ten nhan vien: ", tenNhanVien);
        System.out.println();
        System.out.printf("Luong co ban: ", LUONG_CO_BAN);
        System.out.println();
        System.out.printf("He so luong: ", heSoLuong);
        System.out.println();
        System.out.printf("Luong max: ", LUONG_MAX);
    }

    public boolean tangLuong(double luongTang) {
        luongTang += heSoLuong;

        if(LUONG_CO_BAN * luongTang > LUONG_MAX) {
            return false;
        }
        heSoLuong = luongTang;
        return true;
    }
}
