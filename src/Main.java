
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        while(true) {
            int chon1 = MenuChinh();
            if (chon1 == 0) break;
            switch(chon1) {
                case 1: {
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
    public static int KiemTraDauVao(int a, int b) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                String input = sc.next();
                int chon = Integer.parseInt(input);
                if (chon >= a && chon <= b) {
                    return chon;
                } else {
                    System.out.println("Nhập sai vui lòng nhập lại");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nhập không hợp lệ, vui lòng nhập lại:");
            }
        }
    }
}