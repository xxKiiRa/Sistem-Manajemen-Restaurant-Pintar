//inheritance
//relasi generalisasi
public class Staf extends User {
    private String jadwalKerja;

    public Staf(Integer id, String nama, String jadwalKerja) {
        super(id, nama);
        this.jadwalKerja = jadwalKerja;
    }

    public String getJadwalKerja() {
        return jadwalKerja;
    }

//overriding
    @Override
    public void info() {
        System.out.println("Id staf: " + id + ", nama staf: " + nama + ", jadwal kerja staf: " + jadwalKerja);
    }
}
