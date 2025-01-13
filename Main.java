import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    private static List<Menu> menu = new ArrayList<>();
    private static List<Pesanan> pemesananList = new ArrayList<>();
    private static List<Staf> stafList = new ArrayList<>();
    private static Dapur dapur = new Dapur();
    private static Kasir kasir = new Kasir();
    private static List<Integer> jumlahList = new ArrayList<>();
    private static Stok stok = new Stok();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistem Manajemen Restoran Pintar");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Selamat Datang di Sistem Manajemen Restoran Pintar");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton ManajemenMenu = new JButton("Manajemen Menu");
        JButton ManajemenPesanan = new JButton("Manajemen Pesanan");
        JButton KelolaStaf = new JButton("Kelola Staf");
        JButton ManajemenStok = new JButton("Manajemen Stok");
        JButton Dapur = new JButton("Dapur");
        JButton Kasir = new JButton("Kasir");
        JButton LaporanHarian = new JButton("Laporan Harian");

        JPanel panel = new JPanel();
        panel.add(ManajemenMenu);
        panel.add(ManajemenPesanan);
        panel.add(KelolaStaf);
        panel.add(ManajemenStok);
        panel.add(Dapur);
        panel.add(Kasir);
        panel.add(LaporanHarian);

        frame.add(label, "North");
        frame.add(panel, "Center");

        stafList.add(new Staf(332, "Saif", "09:00 - 12:00\n"));
        stafList.add(new Staf(334, "Lutfi", "12:00 - 15.00\n"));
        stafList.add(new Staf(336, "Dava", "15:00 - 18.00\n"));
        stafList.add(new Staf(358, "Pael", "18:00 - 21:00"));

        ManajemenMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Tambah Menu", "Ubah Menu", "Hapus Menu", "Lihat Menu"};
                int pil = JOptionPane.showOptionDialog(null, "Pilih aksi untuk menu:", "Kelola Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (pil == 0) {
                    String idMenu = JOptionPane.showInputDialog("Masukkan Id menu:");
                    if (idMenu == null || idMenu.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "ID tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String namaMenu = JOptionPane.showInputDialog("Masukkan nama menu:");
                    String Kategori = JOptionPane.showInputDialog("Masukkan kategori menu (makanan/minuman):");
                    try {
                        double Harga = Double.parseDouble(JOptionPane.showInputDialog("Masukkan harga menu:"));
                        Menu menu = new Menu(idMenu, namaMenu, Kategori, Harga);
                        Main.menu.add(menu);
                        FileInputOutput.simpanMenuKeFile(Main.menu);
                        JOptionPane.showMessageDialog(null, "Menu berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Harga tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } else if (pil == 1) {
                    String idMenu = JOptionPane.showInputDialog("Masukkan id menu yang ingin diubah: ");
                    if (idMenu == null || idMenu.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Id tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    Menu ubahMenu = null;
                    for (Menu menu : Main.menu) {
                        if (menu.getIdMenu().equals(idMenu)) {
                            ubahMenu = menu;
                            break;
                        }
                    }

                    if (ubahMenu == null) {
                        JOptionPane.showMessageDialog(null, "Menu dengan id tersebut tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    String namaMenu = JOptionPane.showInputDialog("Masukkan nama menu baru: ");
                    if (namaMenu != null && !namaMenu.isEmpty()) {
                        ubahMenu.setNamaMenu(namaMenu);
                    }

                    String Kategori = JOptionPane.showInputDialog("Masukkan kategori menu baru:");
                    if (Kategori != null && !Kategori.isEmpty()) {
                        ubahMenu.setKategori(Kategori);
                    }

                    String Harga = JOptionPane.showInputDialog("Masukkan harga menu baru: ");
                    if (Harga != null && !Harga.isEmpty()) {
                        try {
                            double harga = Double.parseDouble(Harga);
                            ubahMenu.setHarga(harga);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Harga tidak valid.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Menu berhasil diubah!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } else if (pil == 2) {
                    try {
                        String idMenu = JOptionPane.showInputDialog("Masukkan Id menu yang ingin dihapus:");
                        if (idMenu == null || idMenu.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Id tidak boleh kosong.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        Menu hapusMenu = null;
                        for (Menu menu : Main.menu) {
                            hapusMenu = menu;
                            break;
                        }

                        if (hapusMenu != null) {
                            menu.remove(hapusMenu);
                            JOptionPane.showMessageDialog(null, "Menu berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(null, "Menu dengan id " + idMenu + " tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menghapus menu.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (pil == 3) {
                    StringBuilder menuList = new StringBuilder("Daftar Menu:\n");
                    if (menu.isEmpty()) {
                        menuList.append("Tidak ada menu.");
                    } else {
                        for (Menu item : menu) {
                            menuList.append("Id menu: ").append(item.getIdMenu());
                            menuList.append("\nNama menu: ").append(item.getNamaMenu());
                            menuList.append("\nKategori (makanan/minuman): ").append(item.getKategori());
                            menuList.append("\nHarga: ").append(item.getHarga()).append("\n\n");
                        }
                    }
                    JOptionPane.showMessageDialog(null, menuList.toString());
                }
            }
        });

        ManajemenPesanan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Tambah Pesanan", "Status Pesanan", "Lihat Pesanan"};
                int pil = JOptionPane.showOptionDialog(null, "Pilih aksi untuk pesanan:", "Kelola Pesanan", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                if (pil == 0) {
                    String idPesanan = JOptionPane.showInputDialog("Masukkan id pesanan: ");
                    String namaPelanggan = JOptionPane.showInputDialog("Masukkan nama pelanggan: ");
                    String itemInput = JOptionPane.showInputDialog("Masukkan item yang dipesan (pisahkan dengan koma): ");
                    String[] itemArray = itemInput.split(",");
                    List<Menu> itemList = new ArrayList<>();
                    double totalHarga = 0;

                    for (String item : itemArray) {
                        for (Menu menu : Main.menu) {
                            if (menu.getNamaMenu().equalsIgnoreCase(item.trim())) {
                                String jumlahInput = JOptionPane.showInputDialog("Masukkan jumlah untuk " + menu.getNamaMenu() + ": ");
                                int jumlah;
                                try {
                                    jumlah = Integer.parseInt(jumlahInput);
                                    if (jumlah <= 0) {
                                        JOptionPane.showMessageDialog(null, "Jumlah harus lebih dari 0.", "Error", JOptionPane.ERROR_MESSAGE);
                                        return;
                                    }
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                                    return;
                                }

                                itemList.add(menu);
                                jumlahList.add(jumlah);
                                totalHarga += menu.getHarga() * jumlah;
                            }
                        }
                    }

                    Pesanan pesanan = new Pesanan(idPesanan, namaPelanggan, itemList, jumlahList, "Dalam Antrian.");
                    pemesananList.add(pesanan);
                    FileInputOutput.simpanPemesananKeFile(pemesananList);
                    JOptionPane.showMessageDialog(null, "Pesanan berhasil ditambahkan!\nTotal Harga: Rp" + totalHarga, "Sukses", JOptionPane.INFORMATION_MESSAGE);
                } else if (pil == 1) {
                    StringBuilder antreanPesanan = new StringBuilder("Status Pesanan:\n");
                    if (pemesananList.isEmpty()) {
                        antreanPesanan.append("Tidak ada pesanan dalam antrean.");
                    } else {
                        for (Pesanan pesanan : pemesananList) {
                            antreanPesanan.append("Id ").append(pesanan.getIdPesanan()).append(" - ").append(pesanan.getStatusPesanan()).append("\n");
                        }
                    }
                    JOptionPane.showMessageDialog(frame, antreanPesanan.toString(), "Antrean Pesanan", JOptionPane.INFORMATION_MESSAGE);
                } else if (pil == 2) {
                    StringBuilder daftarPesanan = new StringBuilder("Daftar Pesanan:\n");
                    if (pemesananList.isEmpty()) {
                        daftarPesanan.append("Tidak ada pesanan yang telah dibuat.");
                    } else {
                        for (Pesanan pesanan : pemesananList) {
                            daftarPesanan.append("ID Pesanan: ").append(pesanan.getIdPesanan()).append("\n");
                            daftarPesanan.append("Nama Pelanggan: ").append(pesanan.getNamaPelanggan()).append("\n");
                            daftarPesanan.append("Item Pesanan:\n");
                            int totalItem = 0;
                            for (int i = 0; i < pesanan.getItem().size(); i++) {
                                Menu menu = pesanan.getItem().get(i);
                                int jumlah = pesanan.getJumlahItem().get(i);
                                daftarPesanan.append("- ").append(menu.getNamaMenu()).append(" (Rp").append(menu.getHarga()).append(")\n");
                                totalItem += jumlah;
                            }
                            daftarPesanan.append("Total Item: ").append(totalItem).append("\n");
                            daftarPesanan.append("Total Harga: Rp").append(pesanan.getTotalHarga()).append("\n\n");
                        }
                    }
                    JOptionPane.showMessageDialog(frame, daftarPesanan.toString(), "Daftar Pesanan", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        KelolaStaf.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder stafInfo = new StringBuilder("Staf: \n");
                for (Staf staf : stafList) {
                    stafInfo.append("ID: ").append(staf.getId()).append(", Nama: ").append(staf.getNama()).append(", Jadwal Kerja: ").append(staf.getJadwalKerja());
                }
                JOptionPane.showMessageDialog(frame, stafInfo.toString());
            }
        });

        ManajemenStok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] options = {"Tampilkan Stok", "Pakai Stok"};
                int pil = JOptionPane.showOptionDialog(null, "Pilih aksi untuk stok:", "Kelola Stok", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (pil == 0) {
                    StringBuilder stokTerkini = new StringBuilder("Stok Saat Ini:\n");
                    for (Map.Entry<String, Integer> entry : stok.stok.entrySet()) {
                        String item = entry.getKey();
                        int jumlah = entry.getValue();
                        stokTerkini.append(item)
                                .append(": ")
                                .append(jumlah)
                                .append(jumlah < stok.minimalStok.get(item) ? " (Di Bawah Minimum!)\n" : "\n");
                    }
                    JOptionPane.showMessageDialog(frame, stokTerkini.toString(), "Stok Terkini", JOptionPane.INFORMATION_MESSAGE);
                } else if (pil == 1) {
                    String namaBarang = JOptionPane.showInputDialog("Masukkan nama bahan baku yang ingin digunakan: ");
                    int jumlahKurang;
                    try {
                        jumlahKurang = Integer.parseInt(JOptionPane.showInputDialog("Masukkan jumlah yang ingin digunakan:"));
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Jumlah harus berupa angka.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    if (stok.cekStok(namaBarang, jumlahKurang)) {
                        stok.kurangiStok(namaBarang, jumlahKurang);

                        JOptionPane.showMessageDialog(null, "Stok " + namaBarang + " berhasil dikurangi sebanyak " + jumlahKurang + ".", "Sukses", JOptionPane.INFORMATION_MESSAGE);

                        dapur.tambahkanKeDapur(namaBarang, jumlahKurang);
                        dapur.mulaiMasak();
                    } else {
                        JOptionPane.showMessageDialog(null, "Stok tidak mencukupi untuk barang: " + namaBarang, "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    StringBuilder stokTerkiniSetelah = new StringBuilder("Stok Saat Ini Setelah Pengurangan:\n");
                    for (Map.Entry<String, Integer> entry : stok.stok.entrySet()) {
                        String item = entry.getKey();
                        int jumlah = entry.getValue();
                        stokTerkiniSetelah.append(item)
                                .append(": ")
                                .append(jumlah)
                                .append(jumlah < stok.minimalStok.get(item) ? " (Di Bawah Minimum!)\n" : "\n");
                    }
                    JOptionPane.showMessageDialog(frame, stokTerkiniSetelah.toString(), "Stok Terkini", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        Dapur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!dapur.tambahkanKeDapur()) {
                    if (!pemesananList.isEmpty()) {
                        dapur.mulaiMasak();
                        JOptionPane.showMessageDialog(frame, "Dapur mulai memasak.", "Dapur", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Belum ada stok yang dikurangi. Silakan kurangi stok terlebih dahulu.", "Dapur", JOptionPane.WARNING_MESSAGE);
                    }
                }
                    dapur.selesaiMemasak();
                    JOptionPane.showMessageDialog(frame, "Dapur telah selesai memasak.", "Dapur", JOptionPane.WARNING_MESSAGE);
            }
        });

        Kasir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder pesananSelesai = new StringBuilder("Kasir:\n");
                if (pemesananList.isEmpty()) {
                    pesananSelesai.append("Input pesanan dahulu.");
                } else {
                    for (Pesanan pesanan : pemesananList) {
                        kasir.tampilkanPesananSelesai();
                        kasir.setPemesananList(pemesananList);
                        pesananSelesai.append("Pesanan atas nama pelanggan: ").append(pesanan.getNamaPelanggan()).append(", telah selesai").append("\n\n");
                    }
                }
                JOptionPane.showMessageDialog(frame, pesananSelesai, "Kasir", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        LaporanHarian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PembuatLaporan laporann = new PembuatLaporan();
                String laporan = laporann.buatLaporanHarian(pemesananList);
                JOptionPane.showMessageDialog(frame, laporan, "Laporan Harian", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}