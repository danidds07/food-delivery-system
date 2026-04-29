package system;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import client.Client;
import restaurant.Menu;
import restaurant.Restaurant;
import deliveryman.Deliveryman;
import orders.Order;
import orders.Status;

public class AppSystem {
    private List<Client> clientes;
    private List<Restaurant> restaurantes;
    private List<Deliveryman> entregadores;
    private List<Order> pedidos;

    public AppSystem() {
        this.clientes = new ArrayList<>();
        this.restaurantes = new ArrayList<>();
        this.entregadores = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    public List<Restaurant> getRestaurantes() {
        return restaurantes;
    }

    public Order findOrderById(int id) {
        for (Order p : pedidos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
    
    public Restaurant findRestaurantById(int id) {
        for (Restaurant r : restaurantes) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public List<Client> getClientes() {
        return clientes;
    }

    public Client findClientById(int id) {
        for (Client c : clientes) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    private int nextClientId = 1;
    private int nextRestaurantId = 1;
    private int nextDeliverymanId = 1;
    private int nextOrderId = 1;

    //Registra cliente, restaurantes e entregadores
    public Client registerClient(String name, String email){
        Client c = new Client(name, nextClientId, email);
        clientes.add(c);
        nextClientId++;
        return c;
    }

    public Restaurant registerRestaurant(String name, String email){
        Restaurant r = new Restaurant(name, nextRestaurantId, email);
        restaurantes.add(r);
        nextRestaurantId++;
        return r;
    }

    public Deliveryman registerDeliveryman(String name, String email, String vehicle){
        Deliveryman d = new Deliveryman(name, nextDeliverymanId, email, vehicle, true);
        entregadores.add(d);
        nextDeliverymanId++;
        return d;
    }

    //Cria pedido novos
    public Order makOrder(Client cliente, Restaurant restaurante, List<Menu> itens){
        Order newer =  new Order(nextOrderId, cliente, restaurante, itens);
        pedidos.add(newer);
        nextOrderId++;
        return newer;
    }

    //Atribuir entregador disponivel
    public boolean atributeDeliveryman(Order pedido){
        for(Deliveryman d:entregadores){
            if(d.getAvailable()){
                pedido.delivery(d); //link ao pedido
                d.setAvailable(false); //OCUPADO
                return true; //FUNCIONOU
            }
        }
        return false; //SEM ENTREGADORES
    }

    //Atualiza o status do pedido chamando a classe update status do orders > Order.java
    public void updateStatusOrder(Order pedido, Status newStatus){
        pedido.updateStatus(newStatus);
    }

    //Lista de pedidos ordenados por status
    public void orderList() {
        pedidos.sort(Comparator.comparing(Order::getStatus));

        for (Order p : pedidos) {
            p.orderResume();
            System.out.println();
        }
    }
}