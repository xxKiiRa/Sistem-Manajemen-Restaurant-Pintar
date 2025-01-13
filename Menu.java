public class Menu {
    private String idMenu;
    private String namaMenu;
    private String kategori;
    private double harga;

    public Menu(String idMenu, String namaMenu, String kategori, double harga) {
        this.idMenu = idMenu;
        this.namaMenu = namaMenu;
        this.kategori = kategori;
        this.harga = harga;
    }

    public String getIdMenu() {
        return idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public double getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return namaMenu;
    }

    public void setHarga(double harga) {
        this.harga = harga;


    }
}