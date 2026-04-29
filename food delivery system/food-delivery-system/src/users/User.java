package users;

public abstract class User {
    protected String name;
    protected int id;
    protected String email;

    // Construtor
    public User(String name, int id, String email){
        this.name = name;
        this.id = id;
        this.email = email;
    }

    // Métodos
    public abstract void viewProfile();

    // Getters
    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}