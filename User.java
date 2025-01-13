//polymorphism
public abstract class User {
    protected Integer id;
    protected String nama;

    public User(Integer id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public Integer getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }
    public abstract void info();
}
