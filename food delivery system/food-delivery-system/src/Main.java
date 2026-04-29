import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import client.Client;
import deliveryman.Deliveryman;
import orders.Order;
import orders.Status;
import restaurant.Menu;
import restaurant.Restaurant;
import system.AppSystem;

public class Main {

    private static AppSystem sistema = new AppSystem();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {}

        JFrame frame = new JFrame("Delivery System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setLayout(new GridLayout(8, 1, 10, 10));
        frame.setLocationRelativeTo(null);

        JButton btnRegisterClient = new JButton("1. Register client");
        JButton btnRegisterRestaurant = new JButton("2. Register restaurant");
        JButton btnRegisterDeliveryman = new JButton("3. Register deliveryman");
        JButton btnNewOrder = new JButton("4. New order");
        JButton btnAssignDeliveryman = new JButton("5. Assign deliveryman");
        JButton btnUpdateStatus = new JButton("6. Update order status");
        JButton btnOrderList = new JButton("7. Order list");
        JButton btnExit = new JButton("8. Exit");

        frame.add(btnRegisterClient);
        frame.add(btnRegisterRestaurant);
        frame.add(btnRegisterDeliveryman);
        frame.add(btnNewOrder);
        frame.add(btnAssignDeliveryman);
        frame.add(btnUpdateStatus);
        frame.add(btnOrderList);
        frame.add(btnExit);

        btnRegisterClient.addActionListener(e -> registerClient(frame));
        btnRegisterRestaurant.addActionListener(e -> registerRestaurant(frame));
        btnRegisterDeliveryman.addActionListener(e -> registerDeliveryman(frame));
        btnNewOrder.addActionListener(e -> newOrder(frame));
        btnAssignDeliveryman.addActionListener(e -> assignDeliveryman(frame));
        btnUpdateStatus.addActionListener(e -> updateStatus(frame));
        btnOrderList.addActionListener(e -> showOrderList(frame));
        btnExit.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }

    private static void registerClient(JFrame frame) {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        Object[] message = {"Client name:", nameField, "Client email:", emailField};
        int option = JOptionPane.showConfirmDialog(frame, message, "Register Client", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            if(!name.isEmpty() && !email.isEmpty()) {
                Client c = sistema.registerClient(name, email);
                JOptionPane.showMessageDialog(frame, "Client registered! ID: " + c.getId());
            } else {
                JOptionPane.showMessageDialog(frame, "Name and email cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void registerRestaurant(JFrame frame) {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        Object[] message = {"Restaurant name:", nameField, "Restaurant email:", emailField};
        int option = JOptionPane.showConfirmDialog(frame, message, "Register Restaurant", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            if(name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Name and email cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Restaurant r = sistema.registerRestaurant(name, email);
            
            String qtdStr = JOptionPane.showInputDialog(frame, "How many menu items does this restaurant have?");
            if (qtdStr != null && !qtdStr.trim().isEmpty()) {
                try {
                    int qtd = Integer.parseInt(qtdStr.trim());
                    for (int i = 0; i < qtd; i++) {
                        JTextField descField = new JTextField();
                        JTextField priceField = new JTextField();
                        Object[] itemMsg = {"Description (Item #" + (i + 1) + "):", descField, "Price:", priceField};
                        int itemOpt = JOptionPane.showConfirmDialog(frame, itemMsg, "Menu Item", JOptionPane.OK_CANCEL_OPTION);
                        if (itemOpt == JOptionPane.OK_OPTION) {
                            r.addMenuItem(descField.getText().trim(), Integer.parseInt(priceField.getText().trim()));
                        }
                    }
                    JOptionPane.showMessageDialog(frame, "Restaurant registered! ID: " + r.getId());
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(frame, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Restaurant registered without items! ID: " + r.getId());
            }
        }
    }

    private static void registerDeliveryman(JFrame frame) {
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField vehicleField = new JTextField();
        Object[] message = {"Name:", nameField, "Email:", emailField, "Vehicle:", vehicleField};
        int option = JOptionPane.showConfirmDialog(frame, message, "Register Deliveryman", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String vehicle = vehicleField.getText().trim();
            if(!name.isEmpty() && !email.isEmpty() && !vehicle.isEmpty()) {
                Deliveryman d = sistema.registerDeliveryman(name, email, vehicle);
                JOptionPane.showMessageDialog(frame, "Deliveryman registered! ID: " + d.getId());
            } else {
                JOptionPane.showMessageDialog(frame, "All fields must be filled.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void newOrder(JFrame frame) {
        List<Client> clients = sistema.getClientes();
        if (clients.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No clients registered.");
            return;
        }
        String[] clientArray = clients.stream().map(c -> c.getId() + " - " + c.getName()).toArray(String[]::new);
        String selectedClientStr = (String) JOptionPane.showInputDialog(frame, "Choose a client:", "New Order",
                JOptionPane.QUESTION_MESSAGE, null, clientArray, clientArray[0]);
        if (selectedClientStr == null) return;
        int clientId = Integer.parseInt(selectedClientStr.split(" - ")[0]);
        Client selectedClient = sistema.findClientById(clientId);

        List<Restaurant> restaurants = sistema.getRestaurantes();
        if (restaurants.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No restaurants registered.");
            return;
        }
        String[] restArray = restaurants.stream().map(r -> r.getId() + " - " + r.getName()).toArray(String[]::new);
        String selectedRestStr = (String) JOptionPane.showInputDialog(frame, "Choose a restaurant:", "New Order",
                JOptionPane.QUESTION_MESSAGE, null, restArray, restArray[0]);
        if (selectedRestStr == null) return;
        int restId = Integer.parseInt(selectedRestStr.split(" - ")[0]);
        Restaurant selectedRestaurant = sistema.findRestaurantById(restId);

        List<Menu> menu = selectedRestaurant.getMenu();
        if (menu.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "This restaurant has no items.");
            return;
        }

        List<Menu> chosenItems = new ArrayList<>();
        while (true) {
            String[] itemArray = new String[menu.size() + 1];
            itemArray[0] = "0 - Finish";
            for (int i = 0; i < menu.size(); i++) {
                Menu item = menu.get(i);
                itemArray[i + 1] = (i + 1) + " - " + item.getDesc() + " | R$ " + item.getNumber();
            }
            String selectedItemStr = (String) JOptionPane.showInputDialog(frame, "Choose item number:", "Menu of " + selectedRestaurant.getName(),
                    JOptionPane.QUESTION_MESSAGE, null, itemArray, itemArray[0]);
            if (selectedItemStr == null || selectedItemStr.startsWith("0")) break;
            
            int itemIndex = Integer.parseInt(selectedItemStr.split(" - ")[0]) - 1;
            chosenItems.add(menu.get(itemIndex));
            JOptionPane.showMessageDialog(frame, "Item added!");
        }

        if (!chosenItems.isEmpty()) {
            Order newOrder = sistema.makOrder(selectedClient, selectedRestaurant, chosenItems);
            JOptionPane.showMessageDialog(frame, "Order created! ID: " + newOrder.getId());
        } else {
            JOptionPane.showMessageDialog(frame, "Order cancelled. No items selected.");
        }
    }

    private static void assignDeliveryman(JFrame frame) {
        String orders = getOrderListString();
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No orders available.");
            return;
        }
        
        JTextArea textArea = new JTextArea("--- ORDER(S) RESUME ---\n" + orders);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        
        String idStr = JOptionPane.showInputDialog(frame, scrollPane, "Enter order ID:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int orderId = Integer.parseInt(idStr.trim());
                Order selectedOrder = sistema.findOrderById(orderId);
                if (selectedOrder == null) {
                    JOptionPane.showMessageDialog(frame, "Order not found.");
                    return;
                }
                boolean success = sistema.atributeDeliveryman(selectedOrder);
                JOptionPane.showMessageDialog(frame, success ? "Deliveryman assigned!" : "No available deliverymen.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void updateStatus(JFrame frame) {
        String orders = getOrderListString();
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No orders available.");
            return;
        }
        
        JTextArea textArea = new JTextArea("--- ORDER(S) RESUME ---\n" + orders);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 200));

        String idStr = JOptionPane.showInputDialog(frame, scrollPane, "Enter order ID:");
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                int idStatus = Integer.parseInt(idStr.trim());
                Order orderToUpdate = sistema.findOrderById(idStatus);
                if (orderToUpdate == null) {
                    JOptionPane.showMessageDialog(frame, "Order not found.");
                    return;
                }

                String[] statusOptions = {"1 - REALIZADO", "2 - EM_PREPARO", "3 - EM_ENTREGA", "4 - ENTREGUE"};
                String selectedStatus = (String) JOptionPane.showInputDialog(frame, "Choose new status:", "Update Status",
                        JOptionPane.QUESTION_MESSAGE, null, statusOptions, statusOptions[0]);
                if (selectedStatus != null) {
                    int statusOption = Integer.parseInt(selectedStatus.substring(0, 1));
                    Status newStatus = switch (statusOption) {
                        case 1 -> Status.REALIZADO;
                        case 2 -> Status.EM_PREPARO;
                        case 3 -> Status.EM_ENTREGA;
                        case 4 -> Status.ENTREGUE;
                        default -> null;
                    };
                    if (newStatus != null) {
                        sistema.updateStatusOrder(orderToUpdate, newStatus);
                        JOptionPane.showMessageDialog(frame, "Status updated!");
                    }
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void showOrderList(JFrame frame) {
        String output = getOrderListString();
        JTextArea textArea = new JTextArea(output.isEmpty() ? "No orders." : output);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 250));
        JOptionPane.showMessageDialog(frame, scrollPane, "Order(s) List", JOptionPane.INFORMATION_MESSAGE);
    }

    private static String getOrderListString() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        sistema.orderList();
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }
}