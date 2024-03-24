import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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
    static int soLuongHoaDon = 0;
    static double taiKhoanThanhToan = 50000000;
    public static void main(String[] args) {

        while(true) {
            int chon1 = menuChinh();
            if (chon1 == 0) break;
            switch(chon1) {
                case 1: {
                    inThongTinSanPham(soLuongSanPham);
                    anPhimBatKy();
                    break;
                }
                case 2: {
                    while (true) {
                        System.out.println("-------------------------------------");
                        System.out.println("1. Oder Thêm");
                        System.out.println("2. Nhập Bàn Mới");
                        System.out.println("0. Thoát");
                        System.out.println("-------------------------------------");                        System.out.print("MỜI BẠN CHỌN : ");
                        int chon2 = kiemTraDauVao(0, 2);
                        if (chon2 == 0) break;
                        switch (chon2) {
                            case 1:{

                                break;
                            }
                            case 2:{
                                if(soLuongSanPham <= 0) {
                                    System.out.println("Dữ liệu MENU trống");
                                    break;
                                } else {
                                    int sw22 = inThongTinSanPham(soLuongSanPham); if(sw22 <= 0) break;
                                    nhapBanMoi();
                                }

                                anPhimBatKy();
                                break;
                            }
                        }

                    }
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
                    System.out.println("-------------------------------------");                    System.out.println("1. Nhập thêm món vào menu");
                    System.out.println("2. Nhập menu từ file có sẵn");
                    System.out.println("0. Thoát");
                    System.out.println("-------------------------------------");                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon6 = kiemTraDauVao(0, 2);
                    if (chon6 == 0) break;
                    switch (chon6) {
                        case 1:{
                            System.out.println("NHẬP SỐ LƯỢNG MẶT HÀNG CẦN THÊM VÀO MENU : ");
                            int soSPThem= kiemTraDauVao(0, 1000);
                            soLuongSanPham = nhapThongTinSanPham(soSPThem);
                            inThongTinSanPham(soLuongSanPham);
                            break;
                        }
                        case 2:{
                            nhapTuFile("thongtin.txt");
                            inThongTinSanPham(soLuongSanPham);

                            anPhimBatKy();
                            break;
                        }
                    }
                    break;
                }
                case 7: {
                    System.out.println("-------------------------------------");                    System.out.println("1. Xuất hóa đơn ra file");
                    System.out.println("2. Xuât danh sách menu ra file");
                    System.out.println("0. Thoát");
                    System.out.println("-------------------------------------");                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon7 = kiemTraDauVao(0, 2);
                    if(chon7 == 0) break;
                    switch (chon7) {
                        case 1: {
                            break;
                        }
                        case 2:{
                            xuatRaFile("thongtin.txt");
                            anPhimBatKy();
                            break;
                        }
                    }
                    break;
                }
            }
        }
    }

    //Hàm menu chính
    public static int menuChinh(){
        Scanner sc = new Scanner(System.in);
        System.out.println("------------------------------------");
        System.out.println("||  1. IN THÔNG TIN RA MÀN HÌNH   ||");
        System.out.println("||  2. GỌI MÓN                    ||");
        System.out.println("||  3. TÌM KIẾM                   ||");
        System.out.println("||  4. CHỈNH SỬA HÓA ĐƠN          ||");
        System.out.println("||  5. THANH TOÁN                 ||");
        System.out.println("||  6. NHẬP TỪ FILE               ||");
        System.out.println("||  7. XUẤT RA FILE               ||");
        System.out.println("||  8. THỐNG KÊ                   ||");
        System.out.println("||  9. BÁO CÁO                    ||");
        System.out.println("||  10. QUẢN LÝ KHO HÀNG          ||");
        System.out.println("||  0. THOÁT                      ||");
        System.out.println("------------------------------------");
        System.out.println("_______________________________");
        System.out.println("Mời bạn chọn : ");
        int chon1 = kiemTraDauVao(0, 10);
        return chon1;
    }

    //Hàm kiểm tra và bắt lỗi khi nhập dữ liệu
    public static int kiemTraDauVao(int a, int b) {
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

    public static void anPhimBatKy(){
        try {
            System.in.read(); // Đọc một ký tự từ bàn phím
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    //Hàm in danh sách menu ra màn hình
    public static int inThongTinSanPham(int soSPThem){
        if(soSPThem > 0) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.printf("|%7s\t |%-40s|%21s\t|%13s\t|%n","STT", "TÊN SẢN PHẨM", "GIÁ BÁN", "SL CÒN LẠI");
            System.out.println("-------------------------------------------------------------------------------------------------");
            for(int i = 0; i < soLuongSanPham; i++) {
                System.out.printf("|%7d\t |%-40s|%21d\t|%13d\t|%n",
                        (i + 1), sanPhams[i].tenSanPham, sanPhams[i].giaBan, sanPhams[i].tonKho);
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        }else {
            System.out.println("Không có dữ liệu");
        }

        return soLuongSanPham;
    }

//2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
//2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
    public static double nhapBanMoi() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số bàn : ");
        String soBan = sc.next();

        // Lấy ngày và giờ hiện tại
        LocalDateTime gioHienTai = LocalDateTime.now();
        // Định dạng ngày và giờ theo ý muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
        // Chuyển đổi ngày và giờ thành chuỗi theo định dạng đã chỉ định
        String gioVao = gioHienTai.format(formatter);

        int[] soLuong = new int[soLuongSanPham];
        while (true) {
            System.out.println("Chọn Món Theo Số Thứ Tự");
            System.out.println("(Nhấn phím 0 để thoát)");
            int chonMon = kiemTraDauVao(0, soLuongSanPham);if(chonMon == 0) break;
            System.out.print("Nhập số lượng : ");
            int sL = kiemTraDauVao(1, sanPhams[chonMon - 1].tonKho);
            soLuong[chonMon-1] = sL;
        }
        double tongTien = 0;
        for (int i = 0; i < soLuongSanPham; i++){
            if(soLuong[i] > 0){
                tongTien += sanPhams[i].giaBan * soLuong[i];
            }
        }

        if(tongTien == 0) {
            return 0;
        }else{
            hoaDons[soLuongHoaDon] = new HoaDon(soBan,gioVao,soLuong,tongTien);
            for(int i = 0; i < soLuongSanPham; i++) {
                if(hoaDons[soLuongHoaDon].soLuong[i] > 0) {
                    sanPhams[i].tonKho -= hoaDons[soLuongHoaDon].soLuong[i];
                }
            }
        }

        //Số lượng hóa đơn cộng thêm 1
        soLuongHoaDon ++;
        return tongTien;
    }
//3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333
//3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333
//4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
//4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
//5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555
//5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555
//6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666
//6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666

    //Hàm nhập thêm món vào menu
    public static int nhapThongTinSanPham(int soLuongSP) {
        Scanner sc = new Scanner(System.in);
        for (int i = soLuongSanPham; i < soLuongSanPham + soLuongSP; i++) {
            System.out.print("Nhập tên mặt hàng thứ " + (i+1) + " : " + "\n");
            String tenSanPham = sc.nextLine();
            System.out.print("Nhập giá bán : " + "\n");
            int giaBan = kiemTraDauVao(0, 1000000);
            System.out.print("Nhập số lượng : " + "\n");
            int soLuong = kiemTraDauVao(0, 10000);
            sanPhams[i] = new SanPham(tenSanPham,giaBan,soLuong);
        }
        soLuongSanPham += soLuongSP;
        return soLuongSanPham;
    }



    //Hàm xuất dữ liệu ra file
    public static void xuatRaFile(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < soLuongSanPham; i++) {
                SanPham sp = sanPhams[i];
                String line = String.format("%40s|%21d|%13d", sp.tenSanPham, sp.giaBan, sp.tonKho);
                writer.write(line);
                writer.newLine(); // Xuống dòng sau mỗi sản phẩm
            }
            System.out.println("Dữ liệu đã được ghi vào file '" + file + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
//7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
    // Hàm đọc dữ liệu từ file
    public static void nhapTuFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|"); // Tách thông tin của mỗi sản phẩm
                if (parts.length == 3) {
                    String tenSanPham = parts[0].trim();
                    int giaBan = Integer.parseInt(parts[1].trim());
                    int tonKho = Integer.parseInt(parts[2].trim());
                    sanPhams[index++] = new SanPham(tenSanPham, giaBan, tonKho);
                }
            }
            soLuongSanPham = index;
            System.out.println("Dữ liệu đã được nhập từ file '" + file + "'");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Lỗi định dạng số khi nhập từ file.");
            e.printStackTrace();
        }
    }
}

//8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
//9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
//1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010
//1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010