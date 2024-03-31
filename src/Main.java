import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.text.DecimalFormat;
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

    static HoaDon hoaDonDaThanhToan[] = new HoaDon[1000];
    static int soLuongHoaDonDaThanhToan = 0;
    static long taiKhoanThanhToan = 50000000;

    public static void main(String[] args) {

        while (true) {
            int chon1 = menuChinh();
            if (chon1 == 0) break;
            switch (chon1) {
                case 1: {
                    System.out.println("-------------------------------------");
                    System.out.println("1. Danh sách Menu");
                    System.out.println("2. Các bàn đang hoạt động");
                    System.out.println("2. Sắp xếp các bàn đang hoạt động theo tổng tiền");
                    System.out.println("0. Thoát");
                    System.out.println("-------------------------------------");
                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon1B = kiemTraDauVao(0, 3);
                    if (chon1B == 0) break;
                    switch (chon1B) {
                        case 1: {
                            inThongTinSanPham(soLuongSanPham);
                            anPhimBatKy();
                            break;
                        }
                        case 2: {
                            if (soLuongHoaDon <= 0) {
                                System.out.println("Chưa có bàn hoạt động");
                                break;
                            } else {
                                inHoaDonHienCo(0, soLuongHoaDon);
                            }

                            anPhimBatKy();
                            break;
                        }
                        case 3:{
                            if (soLuongHoaDon <= 0) {
                                System.out.println("Chưa có bàn nào hoạt động");
                                break;
                            }
                            sapXepTheoTongTien();

                            anPhimBatKy();
                            break;
                        }
                    }
                    break;
                }
                case 2: {
                    while (true) {
                        System.out.println("-------------------------------------");
                        System.out.println("1. Oder Thêm");
                        System.out.println("2. Nhập Bàn Mới");
                        System.out.println("0. Thoát");
                        System.out.println("-------------------------------------");
                        System.out.print("MỜI BẠN CHỌN : ");
                        int chon2 = kiemTraDauVao(0, 2);
                        if (chon2 == 0) break;
                        switch (chon2) {
                            case 1: {
                                if (soLuongHoaDon <= 0) {
                                    System.out.println("Vui lòng tạo một hóa đơn mới trước khi thực hiện ");
                                    break;
                                }
                                inHoaDonHienCo(0, soLuongHoaDon);
                                Scanner sc2 = new Scanner(System.in);
                                System.out.print("Nhập Số Bàn : ");
                                String timKiem2 = sc2.next();

                                int ketQuaTK2 = timKiemTheoSoBan(timKiem2);
                                if (ketQuaTK2 < 0) {
                                    System.out.println("Không tìm thấy hóa đơn liên quan");
                                    break;
                                }
                                goiThemMon(ketQuaTK2);
                                System.out.println("Thêm món cho bàn " + hoaDons[ketQuaTK2].soBan + " thành công !");
                                break;
                            }
                            case 2: {
                                if (soLuongSanPham <= 0) {
                                    System.out.println("Dữ liệu MENU trống");
                                    break;
                                } else {
                                    int sw22 = inThongTinSanPham(soLuongSanPham);
                                    if (sw22 <= 0) break;
                                    nhapBanMoi();

                                    int in2 = soLuongHoaDon - 1;
                                    inHoaDonHienCo(in2, in2);
                                    System.out.println("Tạo bàn mới thành công");
                                }

                                anPhimBatKy();
                                break;
                            }
                        }

                    }
                    break;
                }
                case 3: {
                    System.out.println("-------------------------------------");
                    System.out.println("1. Tìm kiếm hóa đơn theo số bàn");
                    System.out.println("2. Tìm kiếm hóa đơn theo tên món");
                    System.out.println("0. Thoát");
                    System.out.println("-------------------------------------");
                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon3 = kiemTraDauVao(0, 2);
                    if (chon3 == 0) break;
                    switch (chon3) {
                        case 1: {
                            Scanner scanner = new Scanner(System.in);
                            System.out.print("Nhập vào số bàn muốn tìm kiếm : ");
                            String timKiem = scanner.next();
                            int ketQua = timKiemTheoSoBan(timKiem);
                            if (ketQua < 0) {
                                System.out.println("Không tìm thấy hóa đơn liên quan");
                                break;
                            } else {
                                inHoaDonHienCo(ketQua, ketQua);
                            }

                            break;
                        }
                        case 2: {
                            int kQ = timKiemTheoTenMon();
                            if (kQ <= 0) {
                                System.out.println("Không có kết quả trùng khớp");
                            } else {
                                System.out.println("Có " + kQ + "tìm kiếm trùng khớp ");
                            }
                            break;
                        }
                    }
                    break;
                }
                case 4: {
                    if (soLuongHoaDon <= 0) {
                        System.out.println("Chưa có bàn hoạt động. Vui lòng tạo một hóa đơn mới trước khi thực hiện");
                        break;
                    }
                    inHoaDonHienCo(0, soLuongHoaDon);
                    Scanner sc4 = new Scanner(System.in);
                    System.out.print("Nhập Số Bàn : ");
                    String timKiem4 = sc4.next();

                    int ketQuaTK4 = timKiemTheoSoBan(timKiem4);
                    if (ketQuaTK4 < 0) {
                        System.out.println("Không tìm thấy hóa đơn liên quan");
                        break;
                    }
                    chinhSoLuong(ketQuaTK4);

                    anPhimBatKy();
                    break;
                }
                case 5: {
                    if (soLuongHoaDon <= 0) {
                        System.out.println("Chưa có bàn hoạt động");
                        break;
                    }
                    inHoaDonHienCo(0, soLuongHoaDon);

                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Nhập Số Bàn Muốn Thanh Toán : ");
                    String timKiem5 = scanner.next();

                    int n = timKiemTheoSoBan(timKiem5);
                    if (n < 0) {
                        System.out.println("Không tìm thấy bàn này");
                    } else {
                        thanhToanHoaDon(n);
                    }

                    anPhimBatKy();
                    break;
                }
                case 6: {
                    System.out.println("-------------------------------------");
                    System.out.println("1. Nhập menu từ file có sẵn");
                    System.out.println("2. Nhập danh sách hóa đơn từ file có sẵn");
                    System.out.println("0. Thoát");
                    System.out.println("-------------------------------------");
                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon6 = kiemTraDauVao(0, 2);
                    if (chon6 == 0) break;
                    switch (chon6) {
                        case 1: {
                            nhapTuFile("thongtin.txt");
                            inThongTinSanPham(soLuongSanPham);

                            anPhimBatKy();
                            break;
                        }
                        case 2: {
                            if (soLuongSanPham <= 0) {
                                System.out.println("Không thể thực hiện, vui lòng nhập dữ liệu cho danh sách Menu");
                            } else {
                                docHoaDonTuFile("Hoadon.txt");
                            }
                            break;
                        }
                    }
                    break;
                }
                case 7: {
                    System.out.println("-------------------------------------");
                    System.out.println("1. Xuất hóa đơn ra file");
                    System.out.println("2. Xuât danh sách menu ra file");
                    System.out.println("0. Thoát");
                    System.out.println("-------------------------------------");
                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon7 = kiemTraDauVao(0, 2);
                    if (chon7 == 0) break;
                    switch (chon7) {
                        case 1: {
                            if (soLuongHoaDon <= 0) {
                                System.out.println("Chưa có bàn hoạt động");
                                break;
                            } else {
                                xuatHoaDonRaFile("Hoadon.txt");
                            }
                            anPhimBatKy();
                            break;
                        }
                        case 2: {
                            xuatRaFile("thongtin.txt");
                            anPhimBatKy();
                            break;
                        }
                    }
                    break;
                }
                case 8: {
                    thongKe();

                    anPhimBatKy();
                    break;
                }
                case 9:{
                    if (soLuongHoaDonDaThanhToan <= 0){
                        System.out.println("Không có lịch sử giao dịch gần đây");
                        break;
                    }
                    lichSuGiaoDich(0, soLuongHoaDonDaThanhToan);
                    anPhimBatKy();
                    break;
                }
                case 10: {
                    System.out.println("-------------------------------------");
                    System.out.println("1. Nhập thêm món vào menu");
                    System.out.println("2. Nhập thêm số lượng vào kho hàng");
                    System.out.println("0. Thoát");
                    System.out.println("-------------------------------------");
                    System.out.print("MỜI BẠN CHỌN : ");
                    int chon10 = kiemTraDauVao(0, 2);
                    if (chon10 == 0) break;
                    switch (chon10) {
                        case 1: {
                            System.out.println("NHẬP SỐ LƯỢNG MẶT HÀNG CẦN THÊM VÀO MENU : ");
                            int soSPThem = kiemTraDauVao(0, 1000);
                            soLuongSanPham = nhapThongTinSanPham(soSPThem);
                            inThongTinSanPham(soLuongSanPham);

                            anPhimBatKy();
                            break;
                        }
                        case 2: {
                            if (soLuongSanPham <= 0 ) {
                                System.out.println("Vui lòng nhập dư liệu menu trước khi thực hiện");
                                break;
                            }
                            inThongTinSanPham(soLuongSanPham);
                            System.out.println("Nhập vào số thứ tự sản phẩm bạn muốn nhập kho");
                            int nhap10 = kiemTraDauVao(0, soLuongSanPham);
                            System.out.println("Nhập số lượng thêm vào : ");
                            int nhapThem10 = kiemTraDauVao(0, 500);
                            sanPhams[nhap10-1].tonKho += nhapThem10;
                            System.out.println("Nhập kho thành công !");

                            anPhimBatKy();
                            break;
                        }
                    }
                }
            }
        }
    }

    //Hàm menu chính
    public static int menuChinh() {
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
        System.out.println("||  9. BÁO CÁO LỊCH SỬ GIAO DỊCH  ||");
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

    public static void anPhimBatKy() {
        try {
            System.in.read(); // Đọc một ký tự từ bàn phím
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Hàm in hóa đơn tìm kiếm có mã số mặt hàng
    public static void inHoaDonCoMaMon(int timKiem2) {
        System.out.println("____________________________________________________________________");
        System.out.println("         Bàn số : " + hoaDons[timKiem2].soBan);
        System.out.println("                                       giờ vào : " + hoaDons[timKiem2].gioVao);
        System.out.println("                                       giờ ra  : ");
        System.out.printf("%10s\t %-25s%10s%9s%14s \n", "MÃ SỐ MÓN", "Tên Hàng", "Số Lượng", "Đơn giá", "Thành tiền");
        for (int j = 0; j < soLuongSanPham; j++) {
            if (hoaDons[timKiem2].soLuong[j] > 0) {
                long thanhTien = (hoaDons[timKiem2].soLuong[j] * sanPhams[j].giaBan);
                System.out.printf("%10s\t %-25s%10d%9d%14d \n",
                        "00" + j, sanPhams[j].tenSanPham, hoaDons[timKiem2].soLuong[j], sanPhams[j].giaBan, thanhTien);
            }
        }
        System.out.println("Tổng cộng : " + hoaDons[timKiem2].tongTien);
        System.out.println("____________________________________________________________________");
    }

    //1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
//1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
    //Hàm in danh sách menu ra màn hình
    public static int inThongTinSanPham(int soSPThem) {
        if (soSPThem > 0) {
            System.out.println("-------------------------------------------------------------------------------------------------");
            System.out.printf("|%7s\t |%-40s|%21s\t|%13s\t|%n", "STT", "TÊN SẢN PHẨM", "GIÁ BÁN", "SL CÒN LẠI");
            System.out.println("-------------------------------------------------------------------------------------------------");
            for (int i = 0; i < soLuongSanPham; i++) {
                System.out.printf("|%7d\t |%-40s|%21d\t|%13d\t|%n",
                        (i + 1), sanPhams[i].tenSanPham, sanPhams[i].giaBan, sanPhams[i].tonKho);
            }
            System.out.println("-------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Không có dữ liệu");
        }

        return soLuongSanPham;
    }

    //Hàm in tất cả hóa đơn hiện có
    public static void inHoaDonHienCo(int a, int b) {
        int i = a;
        do {
            System.out.println("____________________________________________________________________");
            System.out.println("         Bàn số : " + hoaDons[i].soBan);
            System.out.println("                                       giờ vào : " + hoaDons[i].gioVao);
            System.out.println("                                       giờ ra  : ");
            System.out.printf("%-25s%10s%9s%14s \n", "Tên Hàng", "Số Lượng", "Đơn giá", "Thành tiền");
            for (int j = 0; j < soLuongSanPham; j++) {
                if (hoaDons[i].soLuong[j] > 0) {
                    long thanhTien = (hoaDons[i].soLuong[j] * sanPhams[j].giaBan);
                    System.out.printf("%-25s%10d%9d%14d \n",
                            sanPhams[j].tenSanPham, hoaDons[i].soLuong[j], sanPhams[j].giaBan, thanhTien);
                }
            }
            System.out.println("Tổng cộng : " + hoaDons[i].tongTien);
            System.out.println("____________________________________________________________________");
            i++;
        } while (i < b);
    }

    //Sắp xếp và in hóa đơn theo tổng tiền từ cao đến thấp
    public static void sapXepTheoTongTien() {
        HoaDon[] hoaDonsClone = hoaDons.clone();
        for(int i = 0; i < soLuongHoaDon - 1; i++) {
            for(int j = i + 1; j < soLuongHoaDon; j++) {
                if(hoaDonsClone[i].tongTien < hoaDonsClone[j].tongTien) {
                    HoaDon temp = hoaDonsClone[i];
                    hoaDonsClone[i] = hoaDonsClone[j];
                    hoaDonsClone[j] = temp;
                }
            }
        }
        for (int i = 0; i < soLuongHoaDon; i++) {
            System.out.println("_______________________________________________");
            System.out.println("BÀN SỐ : " + hoaDonsClone[i].soBan + "    ( Tổng Tiền - " + hoaDonsClone[i].tongTien + " )" );
            System.out.println("_______________________________________________");
        }
    }

    //2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
//2222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222222
    // Hàm gọi thêm món
    public static void goiThemMon(int timKiem2) {
        inHoaDonCoMaMon(timKiem2);
        System.out.println("Nhập mã số mặt hàng : ");
        int nhapMaMon = kiemTraDauVao(0, soLuongSanPham);
        System.out.println("Nhập số lượng thêm : ");
        int nhapThem = kiemTraDauVao(0, sanPhams[nhapMaMon].tonKho - hoaDons[timKiem2].soLuong[nhapMaMon]);
        hoaDons[timKiem2].soLuong[nhapMaMon] += nhapThem;
        sanPhams[nhapMaMon].tonKho -= nhapThem;

        hoaDons[timKiem2].tongTien += (nhapThem * sanPhams[nhapMaMon].giaBan);
    }

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
            int chonMon = kiemTraDauVao(0, soLuongSanPham);
            if (chonMon == 0) break;
            System.out.print("Nhập số lượng : ");
            int sL = kiemTraDauVao(1, sanPhams[chonMon - 1].tonKho);
            soLuong[chonMon - 1] += sL;
        }
        long tongTien = 0;
        for (int i = 0; i < soLuongSanPham; i++) {
            if (soLuong[i] > 0) {
                tongTien += sanPhams[i].giaBan * soLuong[i];
            }
        }

        if (tongTien == 0) {
            return 0;
        } else {
            hoaDons[soLuongHoaDon] = new HoaDon(soBan, gioVao, soLuong, tongTien);
            for (int i = 0; i < soLuongSanPham; i++) {
                if (hoaDons[soLuongHoaDon].soLuong[i] > 0) {
                    sanPhams[i].tonKho -= hoaDons[soLuongHoaDon].soLuong[i];
                }
            }
        }

        //Số lượng hóa đơn cộng thêm 1
        soLuongHoaDon++;
        return tongTien;
    }
//3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333
//3333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333333

    // Hàm tìm kiếm theo số bàn
    public static int timKiemTheoSoBan(String timKiem) {

        for (int i = 0; i < soLuongHoaDon; i++) {
            if (timKiem.equals(hoaDons[i].soBan)) {
                return i;
            }
        }
        return -1;
    }

    // Hàm tìm kiếm theo tên món
    public static int timKiemTheoTenMon() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập vào tên mặt hàng bạn muốn tìm kiếm");

        int kQ = 0;
        String tenMon = sc.next();

        for (int i = 0; i < soLuongHoaDon; i++) {
            for (int j = 0; j < soLuongSanPham; j++) {
                if (hoaDons[i].soLuong[j] > 0 && sanPhams[j].tenSanPham.indexOf(tenMon) >= 0) {
                    inHoaDonHienCo(i, i);
                    kQ++;
                    break;
                }
            }
        }

        return kQ;
    }

    //4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
//4444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444444
    // Hàm chỉnh sửa số lượng món
    public static void chinhSoLuong(int timKiem4) {
        inHoaDonCoMaMon(timKiem4);
        System.out.println("Nhập mã số mặt hàng : ");
        int nhapMaMon = kiemTraDauVao(0, soLuongSanPham);
        System.out.println("Sửa số lượng thành (Nhấn phím 0 để xóa món) : ");
        int chinhSua = kiemTraDauVao(0, sanPhams[nhapMaMon].tonKho);
        // Hàm xóa món trong hóa đơn nếu bạn nhập chinhSua = 0;
        sanPhams[nhapMaMon].tonKho = sanPhams[nhapMaMon].tonKho - (chinhSua - hoaDons[timKiem4].soLuong[nhapMaMon]);
        hoaDons[timKiem4].soLuong[nhapMaMon] = chinhSua;
        long thanhTienMoi = sanPhams[nhapMaMon].giaBan * (chinhSua - hoaDons[timKiem4].soLuong[nhapMaMon]);
        hoaDons[timKiem4].tongTien += thanhTienMoi;
    }

//5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555
//5555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555555

    //Hàm Xuất hóa đơn thanh toán
    public static void thanhToanHoaDon(int x) {
        LocalDateTime gioHienTai = LocalDateTime.now();
        // Định dạng ngày và giờ theo ý muốn
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy  HH:mm");
        // Chuyển đổi ngày và giờ thành chuỗi theo định dạng đã chỉ định
        String gioRa = gioHienTai.format(formatter);

        System.out.println("+____________________________________________________________+");
        System.out.println("|                        PXU PUB BEER GARDEN                 |");
        System.out.println("|              176 Trần Phú, P Vĩnh Ninh, Thành phố Huế      |");
        System.out.println("|                          Tel : 0766663708                  |");
        System.out.println("|                        ___________________                 |");
        System.out.println("|                          HÓA ĐƠN BÁN HÀNG                  |");
        System.out.println("|         Bàn số : " + hoaDons[x].soBan + "|");
        System.out.println("|                               giờ vào : " + hoaDons[x].gioVao + "  |");
        System.out.println("|                               giờ ra  : " + gioRa + "  |");
        System.out.println("|____________________________________________________________|");
        System.out.printf("|%-25s%10s%9s%14s  |\n", "Tên Hàng", "Số Lượng", "Đơn giá", "Thành tiền");
        System.out.println("|------------------------------------------------------------|");

        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        for (int j = 0; j < soLuongSanPham; j++) {
            if (hoaDons[x].soLuong[j] > 0) {
                long thanhTien = (hoaDons[x].soLuong[j] * sanPhams[j].giaBan);
                String thanhTienDinhDang = decimalFormat.format(thanhTien);
                System.out.printf("|%-25s%10d%9d%14s |\n",
                        sanPhams[j].tenSanPham, hoaDons[x].soLuong[j], sanPhams[j].giaBan, thanhTienDinhDang);
            }
        }
        System.out.println("+____________________________________________________________+");
        String tongTienDinhDang = decimalFormat.format(hoaDons[x].tongTien);
        System.out.println("                                   Tổng cộng : " + tongTienDinhDang + "  vnd");

        //Lưu hóa đơn đã thanh toán vào một mảng khác
        hoaDonDaThanhToan[soLuongHoaDonDaThanhToan] = hoaDons[x];
        hoaDonDaThanhToan[soLuongHoaDonDaThanhToan].gioRa = gioRa;
        //Cộng tiền vào tài khoản
        taiKhoanThanhToan += hoaDons[x].tongTien;

        //Xóa bàn vừa thanh toán khỏi danh sách
        for (int i = x; i < soLuongHoaDon; i++) {
            hoaDons[i] = hoaDons[i + 1];
        }

        soLuongHoaDonDaThanhToan += 1;
        soLuongHoaDon -= 1;
    }
//6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666
//6666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666666

    // Hàm đọc dữ liệu menu từ file
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

    // Hàm đọc thông tin hóa đơn từ file
    public static void docHoaDonTuFile(String file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int index = 0;
            while ((line = reader.readLine()) != null) {
                String soBan = line.trim();
                String gioVao = reader.readLine().trim();
                String soLuongString = reader.readLine().trim();
                String[] soLuongArr = soLuongString.split(" ");
                int[] soLuong = new int[soLuongArr.length];
                for (int i = 0; i < soLuongArr.length; i++) {
                    soLuong[i] = Integer.parseInt(soLuongArr[i]);
                    if (soLuong[i] > 0) {
                        sanPhams[i].tonKho -= soLuong[i];
                    }
                }
                long tongTien = Long.parseLong(reader.readLine().trim());
                hoaDons[index++] = new HoaDon(soBan, gioVao, soLuong, tongTien);

            }
            soLuongHoaDon = index;
            System.out.println("Dữ liệu hóa đơn đã được đọc từ file '" + file + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777
//7777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777777

    //Hàm xuất dữ liệu danh sách menu ra file
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

    // Hàm xuất thông tin hóa đơn ra file
    public static void xuatHoaDonRaFile(String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < soLuongHoaDon; i++) {
                writer.write(hoaDons[i].soBan);
                writer.newLine();
                writer.write(hoaDons[i].gioVao);
                writer.newLine();
                for (int j = 0; j < hoaDons[i].soLuong.length; j++) {
                    writer.write(hoaDons[i].soLuong[j] + " ");
                }
                writer.newLine();
                writer.write(hoaDons[i].tongTien + "");
                writer.newLine();
                System.out.println("Thông tin hóa đơn bàn số " + hoaDons[i].soBan + " đã được xuất ra file '" + file + "'");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    public static void thongKe() {
        long tongTienBanDuoc = 0;
        for (int i = 0; i < soLuongHoaDon; i++) {
            tongTienBanDuoc += hoaDons[i].tongTien;
        }
        long tongTienDaThanhToan = 0;
        for (int i = 0; i < soLuongHoaDonDaThanhToan; i++) {
            tongTienDaThanhToan += hoaDonDaThanhToan[i].tongTien;
        }
        DecimalFormat decimal = new DecimalFormat("#,###");
        String tongTienBanDuocDinhDang = decimal.format(tongTienBanDuoc + tongTienDaThanhToan);
        String taiKhoanThanhToanDinhDang = decimal.format(taiKhoanThanhToan);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("SỐ BÀN ĐANG HOẠT ĐỘNG             : " + soLuongHoaDon);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("SỐ BÀN ĐÃ THANH TOÁN              : " + soLuongHoaDonDaThanhToan);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("TỔNG SỐ TIỀN ĐÃ BÁN ĐƯỢC HÔM NAY  : " + (tongTienBanDuocDinhDang) + "  đ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("SỐ DƯ HIỆN TẠI           :  " + taiKhoanThanhToanDinhDang + "  đ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
//9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
//9999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999
    public static void lichSuGiaoDich(int a, int b) {
        System.out.println("LỊCH SỬ GIAO DỊCH  ");
        int i = a;
        do {
            System.out.println("____________________________________________________________________");
            System.out.println("         Bàn số : " + hoaDonDaThanhToan[i].soBan);
            System.out.println("                                       giờ vào : " + hoaDonDaThanhToan[i].gioVao);
            System.out.println("                                       giờ ra  : " + hoaDonDaThanhToan[i].gioRa);
            System.out.printf("%-25s%10s%9s%14s \n", "Tên Hàng", "Số Lượng", "Đơn giá", "Thành tiền");
            for (int j = 0; j < soLuongSanPham; j++) {
                if (hoaDonDaThanhToan[i].soLuong[j] > 0) {
                    long thanhTien = (hoaDonDaThanhToan[i].soLuong[j] * sanPhams[j].giaBan);
                    System.out.printf("%-25s%10d%9d%14d \n",
                            sanPhams[j].tenSanPham, hoaDonDaThanhToan[i].soLuong[j], sanPhams[j].giaBan, thanhTien);
                }
            }
            System.out.println("Tổng cộng : " + hoaDonDaThanhToan[i].tongTien);
            System.out.println("____________________________________________________________________");
            i++;
        } while (i < b);

    }
//1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010
//1010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010

    //Hàm nhập thêm món vào menu
    public static int nhapThongTinSanPham(int soLuongSP) {
        Scanner sc = new Scanner(System.in);
        for (int i = soLuongSanPham; i < soLuongSanPham + soLuongSP; i++) {
            System.out.print("Nhập tên mặt hàng thứ " + (i + 1) + " : " + "\n");
            String tenSanPham = sc.nextLine();
            System.out.print("Nhập giá bán : " + "\n");
            int giaBan = kiemTraDauVao(0, 1000000);
            System.out.print("Nhập số lượng : " + "\n");
            int soLuong = kiemTraDauVao(0, 10000);
            sanPhams[i] = new SanPham(tenSanPham, giaBan, soLuong);
        }
        soLuongSanPham += soLuongSP;
        return soLuongSanPham;
    }
}