import java.util.Calendar;

public class HoaDon {
    public String soBan;
    public String gioVao;
    public String gioRa = "";
    public int[] soLuong = new int[5];
    public double thanhTien;
    public double tongTien;

    public HoaDon(String soBan, String gioVao, int[] soLuong, double thanhTien, double tongTien) {
        this.soBan = soBan;
        this.gioVao = gioVao;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
        this.tongTien = tongTien;
    }
}
