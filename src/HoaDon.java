import java.util.Calendar;

public class HoaDon {
    public String soBan;
    public String gioVao;
    public String gioRa = "";

    Main SL = new Main();
    int sLMenu = Main.soLuongSanPham;
    public int[] soLuong = new int[sLMenu];
    public long tongTien;

    public HoaDon(String soBan, String gioVao, int[] soLuong, long tongTien) {
        this.soBan = soBan;
        this.gioVao = gioVao;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
    }
}
