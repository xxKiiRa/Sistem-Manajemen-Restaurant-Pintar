import java.util.HashMap;
import java.util.Map;

public class Stok {
    protected Map<String, Integer> stok;
    protected Map<String, Integer> minimalStok;

    public Stok() {
        stok = new HashMap<>();
        minimalStok = new HashMap<>();

        stok.put("ayam goreng", 5);
        stok.put("lele bakar", 5);
        stok.put("es teh", 5);
        stok.put("es jeruk", 5);

        minimalStok.put("ayam goreng", 2 );
        minimalStok.put("lele bakar", 2);
        minimalStok.put("es teh", 2);
        minimalStok.put("es jeruk", 2);
    }

    public boolean cekStok(String item, Integer jumlah) {
        return stok.getOrDefault(item, 0) >= jumlah;
    }

    public void kurangiStok(String item, Integer jumlah) {
        if (cekStok(item, jumlah)) {
            stok.put(item, stok.get(item) - jumlah);
        } else {
            System.out.println("Stok tidak cukup untuk " + item);
        }
    }
}