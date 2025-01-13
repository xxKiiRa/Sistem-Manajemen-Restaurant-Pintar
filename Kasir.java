import java.util.ArrayList;
import java.util.List;

//relasi asosiasi searah
public class Kasir {
    //relasi komposisi
    private List<Pesanan> pemesananList;

    public Kasir() {
        this.pemesananList = new ArrayList<>();
    }

    public void setPemesananList(List<Pesanan> pemesananList) {
        this.pemesananList = pemesananList;
    }

    public void tampilkanPesananSelesai() {
        for (Pesanan pesanan : pemesananList) {
            if (pesanan.getStatusPesanan().equalsIgnoreCase("Selesai")) {
                pesanan.setStatusPesanan();
                break;
            }
        }
    }
}