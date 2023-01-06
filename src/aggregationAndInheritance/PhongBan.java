package aggregationAndInheritance;

import java.util.ArrayList;

public class PhongBan {
    private String tenPhongBan;
    private byte soNhanVien = 0;
    public static final byte SO_NHAN_VIEN_MAX = 100;
    ArrayList<NhanVien> danhSachNV = new ArrayList<>();

    public PhongBan(String tenPhongBan, NhanVien nhanVien) {
        this.tenPhongBan = tenPhongBan;
        danhSachNV.add(nhanVien);
        soNhanVien = (byte) ((int)soNhanVien + 1);
    }

    public boolean themNV(NhanVien nhanVienMoi) {
        if(soNhanVien == SO_NHAN_VIEN_MAX) {
            return false;
        }
        danhSachNV.add(nhanVienMoi);
        soNhanVien = (byte) ((int)soNhanVien + 1);
        return true;
    }

    public NhanVien xoaNV() throws Exception {
        if(soNhanVien == 1) {
            throw new Exception("Phong ban phai co it nhat 1 nhan vien");
        }
        int lastIndex = (int) soNhanVien - 1;
        soNhanVien = (byte) ((int)soNhanVien - 1);
        return danhSachNV.remove(lastIndex);
    }

    public double tongLuong() {
        int luong = 0;
        for(int i = 0; i < soNhanVien; i++) {
            luong += danhSachNV.get(i).TinhLuong();
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

        for(int i = 0; i < soNhanVien; i++) {
            danhSachNV.get(i).inTTin();
        }
    }
}
