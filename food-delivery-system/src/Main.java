import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import client.Client;
import deliveryman.Deliveryman;
import orders.Order;
import orders.Status;
import restaurant.Menu;
import restaurant.Restaurant;
import system.AppSystem;

public class Main {

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Could not clear screen.");
        }
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AppSystem sistema = new AppSystem();

        while (true) {
            clearScreen();
            System.out.println("=== DELIVERY SYSTEM ===");
            System.out.println("1. Register client");
            System.out.println("2. Register restaurant");
            System.out.println("3. Register deliveryman");
            System.out.println("4. New order");
            System.out.println("5. Assign deliveryman");
            System.out.println("6. Update order status");
            System.out.println("7. Order list");
            System.out.println("8. Exit");
            System.out.print("Option: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                // REGISTER CLIENT
                case 1:
                    clearScreen();
                    System.out.println("--- REGISTER CLIENT ---");

                    System.out.print("Client name: ");
                    String nomeCliente = sc.nextLine();

                    System.out.print("Client email: ");
                    String emailCliente = sc.nextLine();

                    Client novoCliente = sistema.registerClient(nomeCliente, emailCliente);

                    System.out.println("\nClient registered! ID: " + novoCliente.getId());
                    sc.nextLine();
                    break;

                // REGISTER RESTAURANT
                case 2:
                    clearScreen();
                    System.out.println("--- REGISTER RESTAURANT ---");

                    System.out.print("Restaurant name: ");
                    String restaurantName = sc.nextLine();

                    System.out.print("Restaurant email: ");
                    String restaurantEmail = sc.nextLine();

                    Restaurant newRestaurant = sistema.registerRestaurant(restaurantName, restaurantEmail);

                    System.out.println("\nHow many menu items does this restaurant have?");
                    int qtd = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < qtd; i++) {
                        System.out.println("\nItem #" + (i + 1));
                        System.out.print("Description: ");
                        String desc = sc.nextLine();

                        System.out.print("Price: ");
                        int price = sc.nextInt();
                        sc.nextLine();

                        newRestaurant.addMenuItem(desc, price);
                    }

                    System.out.println("\nRestaurant registered! ID: " + newRestaurant.getId());
                    sc.nextLine();
                    break;

                // REGISTER DELIVERYMAN
                case 3:
                    clearScreen();
                    System.out.println("--- REGISTER DELIVERYMAN ---");

                    System.out.print("Name: ");
                    String deliverymanName = sc.nextLine();

                    System.out.print("Email: ");
                    String deliverymanEmail = sc.nextLine();

                    System.out.print("Vehicle: ");
                    String deliverymanVehicle = sc.nextLine();

                    Deliveryman newDeliveryman = sistema.registerDeliveryman(deliverymanName, deliverymanEmail, deliverymanVehicle);

                    System.out.println("\nDeliveryman registered! ID: " + newDeliveryman.getId());
                    sc.nextLine();
                    break;

                // NEW ORDER
                case 4:
                    clearScreen();
                    System.out.println("--- CREATE NEW ORDER ---");

                    System.out.println("\nChoose a client:");
                    for (Client c : sistema.getClientes()) {
                        System.out.println(c.getId() + " - " + c.getName());
                    }

                    System.out.print("\nClient ID: ");
                    int clientId = sc.nextInt();
                    sc.nextLine();

                    Client selectedClient = sistema.findClientById(clientId);
                    if (selectedClient == null) {
                        System.out.println("Client not found.");
                        sc.nextLine();
                        break;
                    }

                    clearScreen();
                    System.out.println("Client selected: " + selectedClient.getName());

                    System.out.println("\nChoose a restaurant:");
                    for (Restaurant r : sistema.getRestaurantes()) {
                        System.out.println(r.getId() + " - " + r.getName());
                    }

                    System.out.print("\nRestaurant ID: ");
                    int restId = sc.nextInt();
                    sc.nextLine();

                    Restaurant selectedRestaurant = sistema.findRestaurantById(restId);
                    if (selectedRestaurant == null) {
                        System.out.println("Restaurant not found.");
                        sc.nextLine();
                        break;
                    }

                    clearScreen();
                    System.out.println("--- MENU OF " + selectedRestaurant.getName() + " ---");

                    List<Menu> menu = selectedRestaurant.getMenu();
                    if (menu.isEmpty()) {
                        System.out.println("This restaurant has no items.");
                        sc.nextLine();
                        break;
                    }

                    for (int i = 0; i < menu.size(); i++) {
                        Menu item = menu.get(i);
                        System.out.println((i + 1) + " - " + item.getDesc() + " | R$ " + item.getNumber());
                    }

                    List<Menu> chosenItems = new ArrayList<>();

                    while (true) {
                        System.out.print("\nChoose item number (0 to finish): ");
                        int itemNumber = sc.nextInt();
                        sc.nextLine();

                        if (itemNumber == 0) break;

                        if (itemNumber > 0 && itemNumber <= menu.size()) {
                            chosenItems.add(menu.get(itemNumber - 1));
                            System.out.println("Item added!");
                        } else {
                            System.out.println("Invalid item.");
                        }
                    }

                    Order newOrder = sistema.makOrder(selectedClient, selectedRestaurant, chosenItems);

                    System.out.println("\nOrder created! ID: " + newOrder.getId());
                    sc.nextLine();
                    break;

                // ASSIGN DELIVERYMAN
                case 5:
                    clearScreen();
                    System.out.println("--- ASSIGN DELIVERYMAN ---");

                    sistema.orderList();

                    System.out.print("\nEnter order ID: ");
                    int orderId = sc.nextInt();
                    sc.nextLine();

                    Order selectedOrder = sistema.findOrderById(orderId);
                    if (selectedOrder == null) {
                        System.out.println("Order not found.");
                        sc.nextLine();
                        break;
                    }

                    boolean success = sistema.atributeDeliveryman(selectedOrder);

                    System.out.println(success ? "Deliveryman assigned!" : "No available deliverymen.");
                    sc.nextLine();
                    break;

                // UPDATE STATUS
                case 6:
                    clearScreen();
                    System.out.println("--- UPDATE ORDER STATUS ---");

                    sistema.orderList();

                    System.out.print("\nEnter order ID: ");
                    int idStatus = sc.nextInt();
                    sc.nextLine();

                    Order orderToUpdate = sistema.findOrderById(idStatus);
                    if (orderToUpdate == null) {
                        System.out.println("Order not found.");
                        sc.nextLine();
                        break;
                    }

                    System.out.println("\nChoose new status:");
                    System.out.println("1 - REALIZADO");
                    System.out.println("2 - EM_PREPARO");
                    System.out.println("3 - EM_ENTREGA");
                    System.out.println("4 - ENTREGUE");

                    System.out.print("Option: ");
                    int statusOption = sc.nextInt();
                    sc.nextLine();

                    Status newStatus = switch (statusOption) {
                        case 1 -> Status.REALIZADO;
                        case 2 -> Status.EM_PREPARO;
                        case 3 -> Status.EM_ENTREGA;
                        case 4 -> Status.ENTREGUE;
                        default -> null;
                    };

                    if (newStatus != null) {
                        sistema.updateStatusOrder(orderToUpdate, newStatus);
                        System.out.println("Status updated!");
                    } else {
                        System.out.println("Invalid status.");
                    }

                    sc.nextLine();
                    break;

                // ORDER LIST
                case 7:
                    clearScreen();
                    System.out.println("--- ORDER(S) LIST ---");
                    System.out.println("--- ORDER(S) RESUME ---");
                    sistema.orderList();
                    sc.nextLine();
                    break;

                // EXIT
                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option.");
                    sc.nextLine();
            }
        }
    }
}