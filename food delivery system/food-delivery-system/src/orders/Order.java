package orders;

import java.util.List;

import client.Client;
import deliveryman.Deliveryman;
import restaurant.Menu;
import restaurant.Restaurant;

public class Order {
    private Client cliente;
    private Restaurant restaurante;
    private List<Menu> itens;
    private double price;
    private Deliveryman entregador;
    private Status status;
    private int id;

    private void total(){
        double sum = 0;

        for (Menu item:itens){
            sum += item.getNumber();
        }

        this.price = sum;
    }

    public Order(int id, Client cliente, Restaurant restaurante, List<Menu> itens){
        this.id = id;
        this.cliente = cliente;
        this.restaurante = restaurante;
        this.itens = itens;
        this.entregador = null;
        this.status = Status.REALIZADO;

        total();
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
        this.entregador = e;
    }

    public void orderResume(){
        System.out.println("Order ID: " + id);

        System.out.println("Client: " + cliente.getName());
        System.out.println("Restaurant: " + restaurante.getName());
        System.out.println("Status: " + status);

        System.out.println("\nItens:");
        for (Menu item : itens) {
            System.out.println("- " + item.getDesc() + " | R$ " + item.getNumber());
        }

        System.out.println("\nFinal Price: R$ " + price);

        if (entregador != null) {
            System.out.println("Delivery Man: " + entregador.getName() + " (" + entregador.getEmail() + ")");
        } else {
            System.out.println("No delivery person available");
        }

        System.out.println("--------------------");
    }

}