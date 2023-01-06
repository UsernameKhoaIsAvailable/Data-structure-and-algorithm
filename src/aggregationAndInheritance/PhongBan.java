package aggregationAndInheritance;

import java.util.Stack;

public class PhongBan {
    private String tenPhongBan;
    private byte soNhanVien = 0;
    public  byte soNhanVienMax = 100;
    Stack<NhanVien> danhSachNV = new Stack<>();

    public PhongBan(String tenPhongBan, NhanVien nhanVien) {
        this.tenPhongBan = tenPhongBan;
        danhSachNV.push(nhanVien);
        soNhanVien = (byte) ((int)soNhanVien + 1);
    }

    public boolean themNV(NhanVien nhanVienMoi) {
        if(soNhanVien == soNhanVienMax) {
            return false;
        }
        danhSachNV.push(nhanVienMoi);
        soNhanVien = (byte) ((int)soNhanVien + 1);
        return true;
    }

    public NhanVien xoaNV() throws Exception {
        if(soNhanVien == 1) {
            throw new Exception("Phong ban phai co it nhat 1 nhan vien");
        }
        soNhanVien = (byte) ((int)soNhanVien - 1);
        return danhSachNV.pop();
    }

    public double tongLuong() {
        Stack<NhanVien> copieDanhsachNV = (Stack<NhanVien>) danhSachNV.clone();

        int luong = 0;
        while (!copieDanhsachNV.empty()) {
            luong += copieDanhsachNV.pop().TinhLuong();
        }
        return luong;
    }

    public void inTTin() {
        System.out.printf("Phong ban");
        System.out.println();
        System.out.printf("Ten phong ban: ", tenPhongBan);
        System.out.println();
        System.out.printf("So nhan vien: ", soNhanVien);
        System.out.println();
        System.out.printf("Nhan vien");
        System.out.println();

        Stack<NhanVien> copieDanhsachNV = (Stack<NhanVien>) danhSachNV.clone();
        while (!copieDanhsachNV.empty()) {
            copieDanhsachNV.pop().inTTin();
        }
    }
}
