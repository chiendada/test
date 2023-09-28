import java.util.Scanner;

public class Student {
    private String maSinhVien;
    private String hoTen;
    private String ngaySinh;
    private String diaChi;

    public Student(String maSinhVien, String hoTen, String ngaySinh, String diaChi) {
        this.maSinhVien = maSinhVien;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String toString() {
        return "Ma sinh vien: " + maSinhVien + "\nHo va Ten: " + hoTen + "\nNgay sinh: " + ngaySinh + "\nDia chi: " + diaChi;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Nhap so luong sinh vien: ");
        int n = input.nextInt();
        input.nextLine(); // Đọc dòng trống sau khi nhập số lượng sinh viên

        Student[] danhSachSinhVien = new Student[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Nhap thong tin cho sinh vien thu " + (i + 1) + ":");
            System.out.print("Ma sinh vien: ");
            String maSinhVien = input.nextLine();
            System.out.print("Ho va Ten: ");
            String hoTen = input.nextLine();
            System.out.print("Ngay sinh: ");
            String ngaySinh = input.nextLine();
            System.out.print("dia chi: ");
            String diaChi = input.nextLine();

            danhSachSinhVien[i] = new Student(maSinhVien, hoTen, ngaySinh, diaChi);
        }

        System.out.println("\nDanh sach sinh vien:");
        for (int i = 0; i < n; i++) {
            System.out.println("\nSinh vien thu " + (i + 1) + ":");
            System.out.println(danhSachSinhVien[i]);
        }

        input.close();
    }
}
