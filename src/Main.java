import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SanPham[] sanPhams = new SanPham[6];

        // Khởi tạo mỗi phần tử của mảng
        for (int i = 0; i < sanPhams.length; i++) {
            sanPhams[i] = new SanPham();
        }

        sanPhams[0].tenSanPham = "Rau muống xào tỏi";
        sanPhams[0].giaBan = 50000;
        sanPhams[0].tonKho = 100;

        sanPhams[1].tenSanPham = "Sụn gà chiên mắm";
        sanPhams[1].giaBan = 60000;
        sanPhams[1].tonKho = 100;

        sanPhams[2].tenSanPham = "Trứng bách thảo";
        sanPhams[2].giaBan = 90000;
        sanPhams[2].tonKho = 100;

        sanPhams[3].tenSanPham = "Cocacola";
        sanPhams[3].giaBan = 15000;
        sanPhams[3].tonKho = 100;

        sanPhams[4].tenSanPham = "Tiger beer";
        sanPhams[4].giaBan = 20000;
        sanPhams[4].tonKho = 100;

        sanPhams[5].tenSanPham = "Huda beer";
        sanPhams[5].giaBan = 25000;
        sanPhams[5].tonKho = 100;
        while(true) {
            int chon1 = MenuChinh();
            if (chon1 == 0) break;
            switch(chon1) {
                case 1: {
                    WriteFile(sanPhams);
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
            }
        }
    }

    //Hàm menu chính
    public static int MenuChinh(){
        Scanner sc = new Scanner(System.in);
        System.out.println("====================================");
        System.out.println("||  1. GỌI MÓN                    ||");
        System.out.println("====================================");
        System.out.println("||  2. CHỈNH SỬA                  ||");
        System.out.println("====================================");
        System.out.println("||  3. THANH TOÁN                 ||");
        System.out.println("====================================");
        System.out.println("||  4. THỐNG KÊ                   ||");
        System.out.println("====================================");
        System.out.println("||  5. BÁO CÁO                    ||");
        System.out.println("====================================");
        System.out.println("||  0. THOÁT                      ||");
        System.out.println("====================================");
        System.out.println("___________________________________");
        int chon1 = KiemTraDauVao(0, 5);
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


    public static void ThongTin() {

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

}