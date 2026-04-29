package deliveryman;
import users.User;

public class Deliveryman extends User {
    private String vehicle;
    private boolean available;

    public Deliveryman(String name, int id, String email, String vehicle, boolean available){
        super(name, id, email);
        this.vehicle = vehicle;
        this.available = available;
    }

    public boolean getAvailable(){
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public void viewProfile(){
        System.out.println("--- Delivery Man Profile ---");
        System.out.println("Name: " + getName());
        System.out.println("ID: " + getId());
        System.out.println("Email: " + getEmail());
        System.out.println("Vehicle: " + vehicle);
        System.out.println("Availability: " + (available ? "Yes" : "No"));
    }
}