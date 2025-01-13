import java.io.*;
import java.util.List;

public class FileInputOutput {
    //relasi dependensi
    public static void simpanMenuKeFile(List<Menu> menu) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Menu.txt"))) {
            for (Menu item : menu) {
                writer.write(item.getIdMenu() + "," + item.getNamaMenu() + "," + item.getKategori() + "," + item.getHarga());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Gagal menyimpan data menu ke file: " + ex.getMessage());
        }
    }

    public static void simpanPemesananKeFile(List<Pesanan> pemesananList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Pesanan.txt"))) {
            for (Pesanan pesanan : pemesananList) {
                writer.write(pesanan.getIdPesanan() + "," + pesanan.getNamaPelanggan() + "," + pesanan.getItem() + ", " +
                        pesanan.getTotalHarga() + "," + pesanan.getStatusPesanan());
                writer.newLine();
            }
        } catch (IOException ex) {
            System.err.println("Gagal menyimpan data pemesanan ke file: " + ex.getMessage());
        }
    }
}