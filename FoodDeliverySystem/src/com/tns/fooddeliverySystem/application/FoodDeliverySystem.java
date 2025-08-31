package com.tns.fooddeliverySystem.application;

import java.util.*;

import fooddeliverysystem.entities.Customer;
import fooddeliverysystem.entities.DeliveryPerson;
import fooddeliverysystem.entities.FoodItem;
import fooddeliverysystem.entities.Order;
import fooddeliverysystem.entities.Restaurant;

public class FoodDeliverySystem {
    private static final Scanner sc = new Scanner(System.in);

    // Repositories
    private static final List<Restaurant> restaurants = new ArrayList<>();
    private static final List<Customer> customers = new ArrayList<>();
    private static final List<Order> orders = new ArrayList<>();
    private static final List<DeliveryPerson> deliveryPeople = new ArrayList<>();

    private static int orderIdGen = 1;

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> adminMenu();
                case 2 -> customerMenu();
                case 3 -> exit = true;
                default -> System.out.println("Invalid choice.");
            }
        }
        System.out.println("System exited.");
    }

    // ------------------- Admin Menu -------------------
    private static void adminMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Add Restaurant");
            System.out.println("2. Add Food Item to Restaurant");
            System.out.println("3. Remove Food Item from Restaurant");
            System.out.println("4. View Restaurants and Menus");
            System.out.println("5. View Orders");
            System.out.println("6. Add Delivery Person");
            System.out.println("7. Assign Delivery Person to Order");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addRestaurant();
                case 2 -> addFoodItemToRestaurant();
                case 3 -> removeFoodItemFromRestaurant();
                case 4 -> showRestaurants();
                case 5 -> showOrders();
                case 6 -> addDeliveryPerson();
                case 7 -> assignDeliveryPerson();
                case 8 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ------------------- Customer Menu -------------------
    private static void customerMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. Add Customer");
            System.out.println("2. View Food Items");
            System.out.println("3. Add Food to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Place Order");
            System.out.println("6. View Orders");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addCustomer();
                case 2 -> showRestaurants();
                case 3 -> addFoodToCart();
                case 4 -> viewCart();
                case 5 -> placeOrder();
                case 6 -> showOrders();
                case 7 -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ------------------- Admin Functions -------------------
    private static void addRestaurant() {
        System.out.print("Enter Restaurant ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Restaurant Name: ");
        String name = sc.nextLine();
        restaurants.add(new Restaurant(id, name));
        System.out.println("Restaurant added successfully!");
    }

    private static void addFoodItemToRestaurant() {
        System.out.print("Enter Restaurant ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        Restaurant r = findRestaurantById(id);
        if (r == null) {
            System.out.println("Restaurant not found!");
            return;
        }
        System.out.print("Enter Food Item ID: ");
        int fid = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Food Item Name: ");
        String fname = sc.nextLine();
        System.out.print("Enter Price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        r.addFoodItem(new FoodItem(fid, fname, price));
        System.out.println("Food item added successfully!");
    }

    private static void removeFoodItemFromRestaurant() {
        System.out.print("Enter Restaurant ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        Restaurant r = findRestaurantById(id);
        if (r == null) {
            System.out.println("Restaurant not found!");
            return;
        }
        System.out.print("Enter Food Item ID to remove: ");
        int fid = sc.nextInt();
        sc.nextLine();
        r.removeFoodItem(fid);
        System.out.println("Food item removed!");
    }

    private static void addDeliveryPerson() {
        System.out.print("Enter Delivery Person ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Contact No: ");
        long contact = sc.nextLong();
        sc.nextLine();
        deliveryPeople.add(new DeliveryPerson(id, name, contact));
        System.out.println("Delivery person added successfully!");
    }

    private static void assignDeliveryPerson() {
        System.out.print("Enter Order ID: ");
        int oid = sc.nextInt();
        sc.nextLine();
        Order o = findOrderById(oid);
        if (o == null) {
            System.out.println("Order not found!");
            return;
        }
        System.out.print("Enter Delivery Person ID: ");
        int did = sc.nextInt();
        sc.nextLine();
        DeliveryPerson dp = deliveryPeople.stream().filter(d -> d.getDeliveryPersonId() == did).findFirst().orElse(null);
        if (dp == null) {
            System.out.println("Delivery person not found!");
            return;
        }
        o.setDeliveryPerson(dp);
        System.out.println("Delivery person assigned to order successfully!");
    }

    // ------------------- Customer Functions -------------------
    private static void addCustomer() {
        System.out.print("Enter User ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Username: ");
        String uname = sc.nextLine();
        System.out.print("Enter Contact No.: ");
        long contact = sc.nextLong();
        sc.nextLine();
        customers.add(new Customer(id, uname, contact));
        System.out.println("Customer created successfully!");
    }

    private static void addFoodToCart() {
        Customer c = chooseCustomer();
        if (c == null) return;
        System.out.print("Enter Restaurant ID: ");
        int rid = sc.nextInt();
        sc.nextLine();
        Restaurant r = findRestaurantById(rid);
        if (r == null) {
            System.out.println("Restaurant not found!");
            return;
        }
        System.out.print("Enter Food Item ID: ");
        int fid = sc.nextInt();
        sc.nextLine();
        FoodItem f = r.getMenu().stream().filter(x -> x.getId() == fid).findFirst().orElse(null);
        if (f == null) {
            System.out.println("Food item not found!");
            return;
        }
        System.out.print("Enter Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();
        c.getCart().addItem(f, qty);
        System.out.println("Food item added to cart!");
    }

    private static void viewCart() {
        Customer c = chooseCustomer();
        if (c == null) return;
        System.out.println("Cart: " + c.getCart());
    }

    private static void placeOrder() {
        Customer c = chooseCustomer();
        if (c == null) return;
        Order o = new Order(orderIdGen++, c);
        for (var e : c.getCart().getItems().entrySet()) {
            o.addItem(e.getKey(), e.getValue());
        }
        orders.add(o);
        System.out.println("Order placed successfully! Your order ID is: " + o.getOrderId());
    }

    // ------------------- Utility -------------------
    private static void showRestaurants() {
        System.out.println("\nRestaurants and Menus:");
        for (Restaurant r : restaurants) {
            System.out.println("Restaurant ID: " + r.getId() + ", Name: " + r.getName());
            for (FoodItem f : r.getMenu()) {
                System.out.println("- Food Item ID: " + f.getId() + ", Name: " + f.getName() + ", Price: Rs. " + f.getPrice());
            }
        }
    }

    private static void showOrders() {
        System.out.println("Orders:");
        for (Order o : orders) {
            System.out.println(o);
        }
    }

    private static Restaurant findRestaurantById(int id) {
        return restaurants.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    private static Order findOrderById(int id) {
        return orders.stream().filter(o -> o.getOrderId() == id).findFirst().orElse(null);
    }

    private static Customer chooseCustomer() {
        if (customers.isEmpty()) {
            System.out.println("No customers found!");
            return null;
        }
        System.out.print("Enter Customer ID: ");
        int cid = sc.nextInt();
        sc.nextLine();
        return customers.stream().filter(c -> c.getUserId() == cid).findFirst().orElse(null);
    }
}
