package orders;

import java.util.List;

import client.Client;
import deliveryman.Deliveryman;
import restaurant.Menu;
import restaurant.Restaurant;

public class Order {
    private Client client;
    private Restaurant restaurant;
    private List<Menu> items;
    private double price;
    private Deliveryman deliveryman;
    private Status status;
    private int id;

    private void total(){
        this.price = items.stream().mapToDouble(Menu::getPrice).sum();
    }

    public Order(int id, Client client, Restaurant restaurant, List<Menu> items){
        this.id = id;
        this.client = client;
        this.restaurant = restaurant;
        this.items = items;
        this.deliveryman = null;
        this.status = Status.REALIZADO;

        total();
    }

    public Client getClient() {
        return client;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public List<Menu> getItems() {
        return items;
    }

    public double getPrice() {
        return price;
    }

    public Deliveryman getDeliveryman() {
        return deliveryman;
    }

    public Status getStatus(){
        return status;
    }

    public int getId() {
        return id;
    }

    public void updateStatus(Status newStatus){
        this.status = newStatus;
    }

    public void delivery(Deliveryman e){
        this.deliveryman = e;
    }

    public String getOrderResume(){
        StringBuilder sb = new StringBuilder();
        sb.append("Order ID: ").append(id).append("\n");
        sb.append("Client: ").append(client.getName()).append("\n");
        sb.append("Restaurant: ").append(restaurant.getName()).append("\n");
        sb.append("Status: ").append(status).append("\n\n");
        
        sb.append("Itens:\n");
        for (Menu item : items) {
            sb.append("- ").append(item.getDesc()).append(" | R$ ").append(item.getPrice()).append("\n");
        }
        
        sb.append("\nFinal Price: R$ ").append(price).append("\n");
        
        if (deliveryman != null) {
            sb.append("Delivery Man: ").append(deliveryman.getName()).append(" (").append(deliveryman.getEmail()).append(")\n");
        } else {
            sb.append("No delivery person available\n");
        }
        sb.append("--------------------\n");
        
        return sb.toString();
    }

}
