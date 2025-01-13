import java.util.List;

//relasi realisasi
public class PembuatLaporan implements Laporan {
    @Override
    public String buatLaporanHarian(List<Pesanan> pemesananList) {
        StringBuilder laporan = new StringBuilder("Laporan Harian:\n");
        double totalPendapatanHarian = 0;

        if (pemesananList.isEmpty()) {
            laporan.append("Tidak ada pesanan hari ini.\n");
        } else {
            for (Pesanan pesanan : pemesananList) {
                laporan.append("ID Pesanan: ").append(pesanan.getIdPesanan()).append("\n");
                laporan.append("Nama Pelanggan: ").append(pesanan.getNamaPelanggan()).append("\n");
                for (int i = 0; i < pesanan.getItem().size(); i++) {
                    Menu menu = pesanan.getItem().get(i);
                    int jumlah = pesanan.getJumlahItem().get(i);
                    laporan.append("Total item yang dipesan: ").append(jumlah).append(" ").append(menu.getNamaMenu()).append("\n");
                }

                laporan.append("Total Harga: Rp").append(pesanan.getTotalHarga()).append("\n");
                totalPendapatanHarian += pesanan.getTotalHarga();
            }
            laporan.append("Total Pendapatan Hari Ini: Rp").append(totalPendapatanHarian).append("\n\n");
            return laporan.toString();
        }
        return "";
    }
}
