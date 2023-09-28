import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Lớp cơ sở cho tất cả khách hàng
class KhachHang {
    String maKhachHang;
    String hoTen;
    String ngayRaHoaDon;

    public KhachHang(String maKhachHang, String hoTen, String ngayRaHoaDon) {
        this.maKhachHang = maKhachHang;
        this.hoTen = hoTen;
        this.ngayRaHoaDon = ngayRaHoaDon;
    }

    // Phương thức trừu tượng tính thành tiền, sẽ được ghi đè trong lớp con
    public double tinhThanhTien() {
        return 0;
    }

    // Phương thức trừu tượng xuất hóa đơn, sẽ được ghi đè trong lớp con
    public void xuatHoaDon() {
    }
}

// Lớp cho khách hàng Việt Nam
class KhachHangVietNam extends KhachHang {
    String doiTuong;
    int soLuong;
    double donGia;
    int dinhMuc;

    public KhachHangVietNam(String maKhachHang, String hoTen, String ngayRaHoaDon, String doiTuong, int soLuong, double donGia, int dinhMuc) {
        super(maKhachHang, hoTen, ngayRaHoaDon);
        this.doiTuong = doiTuong;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.dinhMuc = dinhMuc;
    }

    @Override
    public double tinhThanhTien() {
        if (soLuong <= dinhMuc) {
            return soLuong * donGia;
        } else {
            return soLuong * donGia * dinhMuc + (soLuong - dinhMuc) * donGia * 2.5;
        }
    }

    @Override
    public void xuatHoaDon() {
        System.out.println("Khach hang Viet Nam - Ma: " + maKhachHang + ", Ho ten: " + hoTen + ", Ngay ra hoa đon: " + ngayRaHoaDon + ", Thanh tien: " + tinhThanhTien());
    }
}

// Lớp cho khách hàng nước ngoài
class KhachHangNuocNgoai extends KhachHang {
    String quocTich;
    int soLuong;
    double donGia;

    public KhachHangNuocNgoai(String maKhachHang, String hoTen, String ngayRaHoaDon, String quocTich, int soLuong, double donGia) {
        super(maKhachHang, hoTen, ngayRaHoaDon);
        this.quocTich = quocTich;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    @Override
    public double tinhThanhTien() {
        return soLuong * donGia;
    }

    @Override
    public void xuatHoaDon() {
        System.out.println("Khach hang Nuoc ngoai - Ma: " + maKhachHang + ", Ho ten: " + hoTen + ", Ngay ra hoa don: " + ngayRaHoaDon + ", Thanh tien: " + tinhThanhTien());
    }
}

public class QuanLyHoaDon1 {
    public static void main(String[] args) {
        List<KhachHang> danhSachHoaDon = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Nhập danh sách hóa đơn từ người dùng
        int soLuongHoaDon;
        System.out.print("Nhap so luong hoa don: ");
        soLuongHoaDon = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự xuống dòng sau khi nhập số lượng

        for (int i = 0; i < soLuongHoaDon; i++) {
            System.out.println("Nhap thong tin cho hoa đon thu " + (i + 1) + ":");
            System.out.print("Ma khach hang: ");
            String maKhachHang = scanner.nextLine();
            System.out.print("Ho ten: ");
            String hoTen = scanner.nextLine();
            System.out.print("Ngay ra hoa don (ngay/thang/nam): ");
            String ngayRaHoaDon = scanner.nextLine();

            // Tạo đối tượng khách hàng Việt Nam hoặc nước ngoài tùy thuộc vào loại khách hàng
            System.out.print("Loai khach hang (Viet Nam hoac nuoc ngoai): ");
            String loaiKhachHang = scanner.nextLine();
            if (loaiKhachHang.equalsIgnoreCase("viet nam")) {
                System.out.print("Doi tuong khach hang (sinh hoat, kinh doanh, san xuat): ");
                String doiTuong = scanner.nextLine();
                System.out.print("So luong KW tieu thu: ");
                int soLuongKW = scanner.nextInt();
                System.out.print("Don gia: ");
                double donGia = scanner.nextDouble();
                System.out.print("Dinh muc: ");
                int dinhMuc = scanner.nextInt();

                danhSachHoaDon.add(new KhachHangVietNam(maKhachHang, hoTen, ngayRaHoaDon, doiTuong, soLuongKW, donGia, dinhMuc));
            } else if (loaiKhachHang.equalsIgnoreCase("nuoc ngoai")) {
                scanner.nextLine(); // Đọc ký tự xuống dòng sau khi nhập loại khách hàng
                System.out.print("Quoc tich: ");
                String quocTich = scanner.nextLine();
                System.out.print("So luong KW tieu thu: ");
                int soLuongKW = scanner.nextInt();
                System.out.print("Don gia: ");
                double donGia = scanner.nextDouble();

                danhSachHoaDon.add(new KhachHangNuocNgoai(maKhachHang, hoTen, ngayRaHoaDon, quocTich, soLuongKW, donGia));
            } else {
                i--; // Quay lại vòng lặp trước đó để nhập lại thông tin cho hóa đơn này.
            }
            scanner.nextLine(); // Đọc ký tự xuống dòng sau khi nhập loại khách hàng
        }

        // Xuất danh sách hóa đơn
        System.out.println("Danh sach hoa don:");
        for (KhachHang hoaDon : danhSachHoaDon) {
            hoaDon.xuatHoaDon();
        }

        // Tính tổng số lượng cho từng loại khách hàng
        int tongSoLuongKhachHangVietNam = 0;
        int tongSoLuongKhachHangNuocNgoai = 0;
        for (KhachHang hoaDon : danhSachHoaDon) {
            if (hoaDon instanceof KhachHangVietNam) {
                tongSoLuongKhachHangVietNam++;
            } else if (hoaDon instanceof KhachHangNuocNgoai) {
                tongSoLuongKhachHangNuocNgoai++;
            }
        }

        System.out.println("Tong so luong khach hang Viet Nam: " + tongSoLuongKhachHangVietNam);
        System.out.println("Tong so luong khach hang Nuoc ngoai: " + tongSoLuongKhachHangNuocNgoai);

        // Tính trung bình thành tiền của khách hàng nước ngoài
        double tongThanhTienKhachHangNuocNgoai = 0;
        int soKhachHangNuocNgoai = 0;
        for (KhachHang hoaDon : danhSachHoaDon) {
            if (hoaDon instanceof KhachHangNuocNgoai) {
                tongThanhTienKhachHangNuocNgoai += hoaDon.tinhThanhTien();
                soKhachHangNuocNgoai++;
            }
        }

        if (soKhachHangNuocNgoai > 0) {
            double trungBinhThanhTien = tongThanhTienKhachHangNuocNgoai / soKhachHangNuocNgoai;
            System.out.println("Trung binh thanh tien cua khach hang Nuoc ngoai: " + trungBinhThanhTien);
        } else {
            System.out.println("Khong co khach hang Nuoc ngoai trong danh sach.");
        }

        // Xuất các hoá đơn trong tháng 09 năm 2013
        System.out.println("Cac hoa don trong thang 09 nam 2013:");
        for (KhachHang hoaDon : danhSachHoaDon) {
            String[] ngayRaHoaDonParts = hoaDon.ngayRaHoaDon.split("/");
            if (ngayRaHoaDonParts.length == 3) {
                int thang = Integer.parseInt(ngayRaHoaDonParts[1]);
                int nam = Integer.parseInt(ngayRaHoaDonParts[2]);
                if (thang == 9 && nam == 2013) {
                    hoaDon.xuatHoaDon();
                }
            }
        }

        scanner.close();
    }
}
