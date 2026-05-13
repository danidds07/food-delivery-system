package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import client.Client;
import deliveryman.Deliveryman;
import orders.Order;
import orders.Status;
import restaurant.Menu;
import restaurant.Restaurant;

public class Database {
    private final String url = "jdbc:sqlite:delivery.db";

    public Database() {
        createTables();
    }

    private Connection connect() throws SQLException {
        return DriverManager.getConnection(url);
    }

    private void createTables() {
        try (Connection conn = connect(); Statement st = conn.createStatement()) {
            st.execute("CREATE TABLE IF NOT EXISTS clients (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL)");

            st.execute("CREATE TABLE IF NOT EXISTS restaurants (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL)");

            st.execute("CREATE TABLE IF NOT EXISTS menu_items (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "restaurant_id INTEGER NOT NULL," +
                    "description TEXT NOT NULL," +
                    "price INTEGER NOT NULL)");

            st.execute("CREATE TABLE IF NOT EXISTS deliverymen (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "vehicle TEXT NOT NULL," +
                    "available INTEGER NOT NULL)");

            st.execute("CREATE TABLE IF NOT EXISTS orders_table (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "client_id INTEGER NOT NULL," +
                    "restaurant_id INTEGER NOT NULL," +
                    "deliveryman_id INTEGER," +
                    "status TEXT NOT NULL)");

            st.execute("CREATE TABLE IF NOT EXISTS order_items (" +
                    "order_id INTEGER NOT NULL," +
                    "menu_item_id INTEGER NOT NULL)");
        } catch (SQLException e) {
            System.out.println("Erro ao criar banco: " + e.getMessage());
        }
    }

    public int insertClient(String name, String email) {
        String sql = "INSERT INTO clients(name, email) VALUES(?, ?)";
        return insertUser(sql, name, email);
    }

    public int insertRestaurant(String name, String email) {
        String sql = "INSERT INTO restaurants(name, email) VALUES(?, ?)";
        return insertUser(sql, name, email);
    }

    private int insertUser(String sql, String name, String email) {
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuario: " + e.getMessage());
        }
        return -1;
    }

    public int insertDeliveryman(String name, String email, String vehicle) {
        String sql = "INSERT INTO deliverymen(name, email, vehicle, available) VALUES(?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, vehicle);
            ps.setInt(4, 1);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir entregador: " + e.getMessage());
        }
        return -1;
    }

    public int insertMenuItem(int restaurantId, String desc, int price) {
        String sql = "INSERT INTO menu_items(restaurant_id, description, price) VALUES(?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, restaurantId);
            ps.setString(2, desc);
            ps.setInt(3, price);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("Erro ao inserir item do menu: " + e.getMessage());
        }
        return -1;
    }

    public int insertOrder(Order order) {
        String sql = "INSERT INTO orders_table(client_id, restaurant_id, deliveryman_id, status) VALUES(?, ?, ?, ?)";

        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, order.getClient().getId());
            ps.setInt(2, order.getRestaurant().getId());
            ps.setNull(3, Types.INTEGER);
            ps.setString(4, order.getStatus().toString());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                insertOrderItems(orderId, order.getItems());
                return orderId;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao inserir pedido: " + e.getMessage());
        }
        return -1;
    }

    private void insertOrderItems(int orderId, List<Menu> itens) {
        String sql = "INSERT INTO order_items(order_id, menu_item_id) VALUES(?, ?)";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            for (Menu item : itens) {
                ps.setInt(1, orderId);
                ps.setInt(2, item.getId());
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar itens do pedido: " + e.getMessage());
        }
    }

    public boolean updateOrderStatus(int orderId, Status status) {
        String sql = "UPDATE orders_table SET status = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status.toString());
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar status: " + e.getMessage());
            return false;
        }
    }

    public boolean updateOrderDeliveryman(int orderId, int deliverymanId) {
        String sql = "UPDATE orders_table SET deliveryman_id = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, deliverymanId);
            ps.setInt(2, orderId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar entregador do pedido: " + e.getMessage());
            return false;
        }
    }

    public boolean updateDeliverymanAvailable(int deliverymanId, boolean available) {
        String sql = "UPDATE deliverymen SET available = ? WHERE id = ?";
        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, available ? 1 : 0);
            ps.setInt(2, deliverymanId);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar entregador: " + e.getMessage());
            return false;
        }
    }

    public List<Client> loadClients() {
        List<Client> list = new ArrayList<>();
        try (Connection conn = connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM clients")) {
            while (rs.next()) {
                list.add(new Client(rs.getString("name"), rs.getInt("id"), rs.getString("email")));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }
        return list;
    }

    public List<Restaurant> loadRestaurants() {
        List<Restaurant> restaurants = new ArrayList<>();
        Map<Integer, Restaurant> map = new HashMap<>();

        try (Connection conn = connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM restaurants")) {
            while (rs.next()) {
                Restaurant r = new Restaurant(rs.getString("name"), rs.getInt("id"), rs.getString("email"));
                restaurants.add(r);
                map.put(r.getId(), r);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar restaurantes: " + e.getMessage());
        }

        try (Connection conn = connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM menu_items")) {
            while (rs.next()) {
                int restaurantId = rs.getInt("restaurant_id");
                Restaurant r = map.get(restaurantId);
                if (r != null) {
                    Menu item = new Menu(rs.getInt("id"), rs.getString("description"), rs.getInt("price"));
                    r.add(item);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar menu: " + e.getMessage());
        }

        return restaurants;
    }

    public List<Deliveryman> loadDeliverymen() {
        List<Deliveryman> list = new ArrayList<>();
        try (Connection conn = connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM deliverymen")) {
            while (rs.next()) {
                boolean available = rs.getInt("available") == 1;
                list.add(new Deliveryman(rs.getString("name"), rs.getInt("id"), rs.getString("email"), rs.getString("vehicle"), available));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar entregadores: " + e.getMessage());
        }
        return list;
    }

    public List<Order> loadOrders(List<Client> clients, List<Restaurant> restaurants, List<Deliveryman> deliverymen) {
        List<Order> orders = new ArrayList<>();

        try (Connection conn = connect(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery("SELECT * FROM orders_table")) {
            while (rs.next()) {
                Client c = findClient(clients, rs.getInt("client_id"));
                Restaurant r = findRestaurant(restaurants, rs.getInt("restaurant_id"));
                if (c == null || r == null) continue;

                List<Menu> itens = loadItemsFromOrder(rs.getInt("id"), r);
                Order o = new Order(rs.getInt("id"), c, r, itens);
                o.updateStatus(Status.valueOf(rs.getString("status")));

                int deliverymanId = rs.getInt("deliveryman_id");
                if (!rs.wasNull()) {
                    Deliveryman d = findDeliveryman(deliverymen, deliverymanId);
                    if (d != null) o.delivery(d);
                }
                orders.add(o);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar pedidos: " + e.getMessage());
        }

        return orders;
    }

    private List<Menu> loadItemsFromOrder(int orderId, Restaurant restaurant) {
        List<Menu> itens = new ArrayList<>();
        String sql = "SELECT menu_item_id FROM order_items WHERE order_id = ?";

        try (Connection conn = connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Menu item = findMenuItem(restaurant.getMenu(), rs.getInt("menu_item_id"));
                if (item != null) itens.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao carregar itens do pedido: " + e.getMessage());
        }
        return itens;
    }

    private Client findClient(List<Client> clients, int id) {
        for (Client c : clients) if (c.getId() == id) return c;
        return null;
    }

    private Restaurant findRestaurant(List<Restaurant> restaurants, int id) {
        for (Restaurant r : restaurants) if (r.getId() == id) return r;
        return null;
    }

    private Deliveryman findDeliveryman(List<Deliveryman> deliverymen, int id) {
        for (Deliveryman d : deliverymen) if (d.getId() == id) return d;
        return null;
    }

    private Menu findMenuItem(List<Menu> menu, int id) {
        for (Menu m : menu) if (m.getId() == id) return m;
        return null;
    }
}
