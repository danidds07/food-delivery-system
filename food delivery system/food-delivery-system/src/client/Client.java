package client;
import users.User;

public class Client extends User {

    public Client(String name, int id, String email){
        super(name, id, email);
    }

    @Override
    public void viewProfile(){
        System.out.println("--- Client Profile ---");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
    }
}