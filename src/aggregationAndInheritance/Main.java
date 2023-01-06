package aggregationAndInheritance;

public class Main {
    public static void main(String[] args) {
        NhanVien nhanVien1 = new NhanVien("Khoa", 5.0);
        PhongBan phongBan = new PhongBan("Khoa's phong ban", nhanVien1);
        System.out.println(phongBan.tongLuong());
    }
}
