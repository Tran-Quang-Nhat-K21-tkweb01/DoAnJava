import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static SanPham[] sanPhams = new SanPham[500];
    static HoaDon[] hoaDons = new HoaDon[200];
    static int soLuongSanPham = 0;
    static int soLuongBan = 0;
    static double taiKhoanThanhToan = 50000000;
    public static void main(String[] args) {

        while(true) {
            int chon1 = MenuChinh();
            if (chon1 == 0) break;
            switch(chon1) {
                case 1: {
                    InThongTinSanPham(soLuongSanPham);
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    System.out.println("1. Nhập thêm món vào menu");
                    System.out.println("2. Nhập menu từ file có sẵn");
                    System.out.println("0. Thoát");
                    System.out.println("---------------------------");
                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon6 = KiemTraDauVao(0, 2);
                    if (chon6 == 0) break;
                    switch (chon6) {
                        case 1:{
                            System.out.println("NHẬP SỐ LƯỢNG MẶT HÀNG CẦN THÊM VÀO MENU : ");
                            int soSPThem= KiemTraDauVao(0, 1000);
                            soLuongSanPham = NhapThongTinSanPham(soSPThem);
                            InThongTinSanPham(soLuongSanPham);
                            break;
                        }
                    }
                    break;
                }
                case 7: {
                    break;
                }
            }
        }
    }

    //Hàm menu chính
    public static int MenuChinh(){
        Scanner sc = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("||  1. IN MENU CHÍNH              ||");
        System.out.println("====================================");
        System.out.println("||  2. GỌI MÓN                    ||");
        System.out.println("====================================");
        System.out.println("||  3. TÌM KIẾM                   ||");
        System.out.println("====================================");
        System.out.println("||  4. CHỈNH SỬA HÓA ĐƠN          ||");
        System.out.println("====================================");
        System.out.println("||  5. THANH TOÁN                 ||");
        System.out.println("====================================");
        System.out.println("||  6. NHẬP THÔNG TIN             ||");
        System.out.println("====================================");
        System.out.println("||  7. XUẤT THÔNG TIN             ||");
        System.out.println("====================================");
        System.out.println("||  8. THỐNG KÊ                   ||");
        System.out.println("====================================");
        System.out.println("||  9. BÁO CÁO                    ||");
        System.out.println("====================================");
        System.out.println("||  10. QUẢN LÝ KHO HÀNG          ||");
        System.out.println("====================================");
        System.out.println("||  0. THOÁT                      ||");
        System.out.println("_______________________________");
        System.out.println("Mời bạn chọn : ");
        int chon1 = KiemTraDauVao(0, 10);
        return chon1;
    }

    //Hàm kiểm tra và bắt lỗi khi nhập dữ liệu
    public static int KiemTraDauVao(int a, int b) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.next();
                int chon = Integer.parseInt(input);
                if (chon >= a && chon <= b) {
                    return chon;
                } else {
                    System.out.println("Số lượng nằm ngoài phạm vi , vui lòng nhập lại");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại:");
            }
        }
    }


    public static int NhapThongTinSanPham(int soLuongSP) {
        Scanner sc = new Scanner(System.in);
        for (int i = soLuongSanPham; i < soLuongSanPham + soLuongSP; i++) {
            System.out.print("Nhập tên mặt hàng thứ " + (i+1) + " : " + "\n");
            String tenSanPham = sc.nextLine();
            System.out.print("Nhập giá bán : " + "\n");
            int giaBan = KiemTraDauVao(0, 1000000);
            System.out.print("Nhập số lượng : " + "\n");
            int soLuong = KiemTraDauVao(0, 10000);
            sanPhams[i] = new SanPham(tenSanPham,giaBan,soLuong);
        }
        soLuongSanPham += soLuongSP;
        return soLuongSanPham;
    }
    public static void InThongTinSanPham(int soSPThem){
        if(soSPThem > 0) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.printf("|%7s\t |%-40s|%21s\t|%13s\t|%n","STT", "TÊN SẢN PHẨM", "GIÁ BÁN", "SỐ LƯỢNG");
            System.out.println("-------------------------------------------------------------------------------------------------");
            for(int i = 0; i < soLuongSanPham; i++) {
                System.out.printf("|%7d\t |%-40s|%21d\t|%13d\t|%n",
                        (i + 1), sanPhams[i].tenSanPham, sanPhams[i].giaBan, sanPhams[i].tonKho);
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        }else {
            System.out.println("Không có dữ liệu");
        }
    }

    //Hàm xuất dữ liệu ra file
    public static void WriteFile(SanPham[] sanPhams) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("thongtin.txt"))) {
            // Ghi thông tin sản phẩm vào file
            for (SanPham sp : sanPhams) {
                writer.write("Tên sản phẩm: " + sp.tenSanPham + "\n");
                writer.write("Giá bán: " + sp.giaBan + "\n");
                writer.write("Tồn kho: " + sp.tonKho + "\n");
            }
            System.out.println("Dữ liệu đã được ghi vào file 'thongtin.txt'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Hàm đọc dữ liệu từ file

}