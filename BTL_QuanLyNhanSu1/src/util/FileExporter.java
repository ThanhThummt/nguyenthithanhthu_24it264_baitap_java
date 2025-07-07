package util;

import model.Nhansu;
import controller.NhanSuController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileExporter {

    public static boolean xuatNhanSuTuDatabaseCSV(String filePath) {
        NhanSuController controller = new NhanSuController();
        List<Nhansu> danhSach = controller.layDanhSachNhanSu();  // Gọi từ Controller

        try {
            // Đảm bảo thư mục tồn tại
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }

            try (
                OutputStreamWriter writer = new OutputStreamWriter(
                        new FileOutputStream(file), StandardCharsets.UTF_8);
                PrintWriter pw = new PrintWriter(writer)
            ) {
                // Ghi BOM để mở được bằng Excel tiếng Việt
                pw.write('\uFEFF');

                // Ghi dòng tiêu đề
                pw.println("MaNV,HoTen,GioiTinh,NgaySinh,Cmnd,DiaChi,SoDT,Email,PhongBan,ChucVu,NgayVaoLam,TrinhDo,TinhTrang");

                // Ghi dữ liệu
                for (Nhansu ns : danhSach) {
                    pw.printf("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s%n",
                            ns.getMaNV(), ns.getHoTen(), ns.getGioiTinh(), ns.getNgaySinh(),
                            ns.getCmnd(), ns.getDiaChi(), ns.getSoDienThoai(), ns.getEmail(),
                            ns.getPhongBan(), ns.getChucVu(), ns.getNgayVaoLam(),
                            ns.getTrinhDoHocVan(), ns.getTinhTrangLamViec());
                }

                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
