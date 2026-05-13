package system;

import java.util.Comparator;
import java.util.List;

import client.Client;
import database.Database;
import restaurant.Menu;
import restaurant.Restaurant;
import deliveryman.Deliveryman;
import orders.Order;
import orders.Status;

public class AppSystem {
    private List<Client> clients;
    private List<Restaurant> restaurants;
    private List<Deliveryman> deliverymen;
    private List<Order> orders;
    private Database database;

    public AppSystem() {
        this.database = new Database();
        this.clients = database.loadClients();
        this.restaurants = database.loadRestaurants();
        this.deliverymen = database.loadDeliverymen();
        this.orders = database.loadOrders(clients, restaurants, deliverymen);
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public Order findOrderById(int id) {
        return orders.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }
    
    public Restaurant findRestaurantById(int id) {
        return restaurants.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public List<Client> getClients() {
        return clients;
    }

    public Client findClientById(int id) {
        return clients.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    //Registra cliente, restaurantes e entregadores no banco e na lista da memoria
    public Client registerClient(String name, String email){
        int id = database.insertClient(name, email);
        if (id == -1) return null;
        Client c = new Client(name, id, email);
        clients.add(c);
        return c;
    }

    public Restaurant registerRestaurant(String name, String email){
        int id = database.insertRestaurant(name, email);
        if (id == -1) return null;
        Restaurant r = new Restaurant(name, id, email);
        restaurants.add(r);
        return r;
    }

    public Menu addMenuItem(Restaurant r, String desc, int price) {
        int id = database.insertMenuItem(r.getId(), desc, price);
        if (id == -1) return null;
        Menu item = new Menu(id, desc, price);
        r.add(item);
        return item;
    }

    public Deliveryman registerDeliveryman(String name, String email, String vehicle){
        int id = database.insertDeliveryman(name, email, vehicle);
        if (id == -1) return null;
        Deliveryman d = new Deliveryman(name, id, email, vehicle, true);
        deliverymen.add(d);
        return d;
    }

    //Cria pedido novo e tambem salva no banco
    public Order makeOrder(Client client, Restaurant restaurant, List<Menu> items){
        Order temp = new Order(0, client, restaurant, items);
        int id = database.insertOrder(temp);
        if (id == -1) return null;
        Order newer = new Order(id, client, restaurant, items);
        orders.add(newer);
        return newer;
    }

    //Atribuir entregador disponivel
    public boolean assignDeliveryman(Order order){
        for(Deliveryman d : deliverymen){
            if(d.getAvailable()){
                order.delivery(d); //link ao pedido
                d.setAvailable(false); //OCUPADO
                
                boolean updatedOrder = database.updateOrderDeliveryman(order.getId(), d.getId());
                boolean updatedDel = database.updateDeliverymanAvailable(d.getId(), false);
                
                return updatedOrder && updatedDel; //FUNCIONOU
            }
        }
        return false; //SEM ENTREGADORES
    }

    //Atualiza o status do pedido chamando a classe update status do orders > Order.java
    public boolean updateStatusOrder(Order order, Status newStatus){
        order.updateStatus(newStatus);
        return database.updateOrderStatus(order.getId(), newStatus);
    }

    //Lista de pedidos ordenados por status
    public String getOrderListString() {
        orders.sort(Comparator.comparing(Order::getStatus));

        StringBuilder sb = new StringBuilder();
        for (Order p : orders) {
            sb.append(p.getOrderResume());
            sb.append("\n");
        }
        return sb.toString();
    }
}
