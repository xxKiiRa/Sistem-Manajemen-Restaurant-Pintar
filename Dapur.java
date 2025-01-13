import java.util.ArrayList;
import java.util.List;

class Dapur {
    //encapsulasi
    private boolean tambahkanKeDapur = false;
    private boolean selesai = false;
    private boolean stokDikurangi = false;
    private List<Pesanan> pemesananList;

    public Dapur() {
        this.pemesananList = new ArrayList<>();
    }

    public void tambahkanKeDapur(String item, int jumlah) {
        stokDikurangi = true;
    }

    public void mulaiMasak() {
        if (!tambahkanKeDapur && !pemesananList.isEmpty()) {
            tambahkanKeDapur = true;
            stokDikurangi = true;
        }
    }

    public void selesaiMemasak() {
        stokDikurangi = false;
        tambahkanKeDapur = false;
    }

    //overloading
    public boolean tambahkanKeDapur() {
        return tambahkanKeDapur;
    }
}