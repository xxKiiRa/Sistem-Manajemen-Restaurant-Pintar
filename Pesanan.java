import java.util.List;

//relasi asosiasi dua arah
public class Pesanan {
    private String IdPesanan;
    private String namaPelanggan;
    //relasi agregasi
    private List<Menu> item;
    private double totalHarga;
    private String statusPesanan;
    private List<Integer> jumlahItem;

    public Pesanan(String idPesanan, String namaPelanggan, List<Menu> item, List<Integer> jumlahItem, String statusPesanan) {
        IdPesanan = idPesanan;
        this.namaPelanggan = namaPelanggan;
        this.item = item;
        this.jumlahItem = jumlahItem;
        this.totalHarga = hitungTotalHarga();
        this.statusPesanan = statusPesanan;
    }

    private double hitungTotalHarga() {
        double total = 0;
        for (int i = 0; i < item.size(); i++) {
            total += item.get(i).getHarga() * jumlahItem.get(i);
        }
        return total;
    }

    public String getIdPesanan() {
        return IdPesanan;
    }

    public String getNamaPelanggan() {
        return namaPelanggan;
    }

    public List<Menu> getItem() {
        return item;
    }

    public double getTotalHarga() {
        return totalHarga;
    }

    public String getStatusPesanan() {
        return statusPesanan;
    }

    public String setStatusPesanan() {
        return statusPesanan;
    }

    public List<Integer> getJumlahItem() {
        return jumlahItem;
    }
}